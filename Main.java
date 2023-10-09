import propagation.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Main
 */
public class Main {

    public static void main(String[] args) 
    {
        int replication = 100; // nombre replication
        int iteration = 730; //nombre iteration
        int sizeS = 19980;
        int sizeI = 20;
        Espace espace = new Espace();
        Individu[] tabInd = initTab(sizeS, sizeI, espace); // initalisation tableau individus
        
        int[] tabStat = new int[4]; // création tableau de status

        File directory = new File("fileStat");
        if (!directory.exists()) {
            directory.mkdir();
        }
        
        for (int rep = 0; rep < replication; rep++)
        {
            // reset du nombre de status
            for (int k = 0; k < tabStat.length; k++)
                tabStat[k] = 0;

            String fileName = "fileStat/donnees_replication_" + rep + ".csv";

            try (FileWriter csvWriter = new FileWriter(fileName)) 
            {
                csvWriter.append("SUSCEPTIBLE,EXPOSED,INFECTED,RECOVERED\n");
                for (int i = 0; i < iteration; i++) 
                {
                    espace.analyseInd(tabInd, (sizeS + sizeI), tabStat);
                    csvWriter.append(tabStat[0] + "," + tabStat[1] + "," + tabStat[2] + "," + tabStat[3] + "\n");
                    espace.moveAllInd(tabInd, (sizeS + sizeI));
                }
                csvWriter.flush();
                csvWriter.close();
                espace.resetGrille();
                resetInd(tabInd, sizeS, sizeI, espace);
            } catch (IOException e) {
                System.out.println("Erreur lors de l'ouverture du fichier");
                e.printStackTrace();
            }
        }
    }

    public static Individu[] initTab(int sizeS, int sizeI, Espace espace)
    {
        Individu[] tabInd = new Individu[(sizeS + sizeI)];
        for(int i = 0; i < (sizeS + sizeI); i++)
        {
            if (i < sizeS)
                tabInd[i] = new Individu(Statut.SUSCEPTIBLE, 0);
            else
                tabInd[i] = new Individu(Statut.INFECTED, 0);
            espace.addInd(tabInd[i], tabInd[i].getX(), tabInd[i].getY());
        }
        return tabInd;
    }

    public static void resetInd(Individu[] tabInd, int sizeS, int sizeI, Espace espace)
    {
        MTRandom random = new MTRandom();
        for(int i = 0; i < (sizeS + sizeI); i++)
        {
            if (i < sizeS) 
                tabInd[i].setStatut(Statut.SUSCEPTIBLE);
            else
                tabInd[i].setStatut(Statut.INFECTED);

            tabInd[i].setX(random.nextInt(300));
            tabInd[i].setY(random.nextInt(300));
            tabInd[i].setTime(0);
            tabInd[i].setdE((int) (-3 * Math.log(1 - random.nextDouble())));
            tabInd[i].setdI((int) (-7 * Math.log(1 - random.nextDouble())));
            tabInd[i].setdR((int) (-365 * Math.log(1 - random.nextDouble())));
            espace.addInd(tabInd[i], tabInd[i].getX(), tabInd[i].getY());
        }
    }

    public static void afficherInd(Individu[] tab, int size)
    {
        for (Individu individu : tab) {
            System.out.println("Etat:" + individu.getStatut() + ",x=" + individu.getX() + ",y=" + individu.getY() + ",dE:" + individu.getdE() + ",dI:" + individu.getdI() + "dR:" + individu.getdR());
        }
    }
}