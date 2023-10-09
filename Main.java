import propagation.*;
/**
 * Main
 */
public class Main {

    public static void main(String[] args) 
    {
        Individu[] tabInd = initTab(19980, 20);
        afficherInd(tabInd, 20000);
    }

    public static Individu[] initTab(int sizeS, int sizeI)
    {
        Individu[] tabInd = new Individu[(sizeS + sizeI)];
        for(int i = 0; i < (sizeS + sizeI); i++)
        {
            if (i < sizeS)
                tabInd[i] = new Individu(Statut.SUSCEPTIBLE, 0);
            else
                tabInd[i] = new Individu(Statut.INFECTED, 0);
        }
        return tabInd;
    }

    public static void afficherInd(Individu[] tab, int size)
    {
        for (Individu individu : tab) {
            System.out.println("Etat:" + individu.getStatut() + ",x=" + individu.getX() + ",y=" + individu.getY() + ",dE:" + individu.getdE() + ",dI:" + individu.getdI() + "dR:" + individu.getdR());
        }
    }
}