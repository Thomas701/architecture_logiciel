package propagation;

import java.util.ArrayList;
import java.util.List;

public class Espace 
{
    private List<Individu>[][] grille = new ArrayList[300][300];

    public List<Individu>[][] getGrille() {return this.grille;}

    public Espace()
    {
        for (int i = 0; i < 300; i++) {
            for (int j = 0; j < 300; j++) {
                grille[i][j] = new ArrayList<Individu>();
            }
        }
    }

    public void addInd(Individu ind, int i, int j)
    {
        this.grille[i][j].add(ind);
    }

    public Individu remInd(Individu ind, int i, int j)
    {
        Individu individu;
        if (!grille[i][j].isEmpty())
        {
            individu = grille[i][j].get(0);
            grille[i][j].remove(individu);
            return individu;
        }
        else
        {
            System.out.println("Liste Vide");
            return null;
        }
    }

    public void moveAllInd(Individu[] everyone, int n)
    {
        MTRandom random = new MTRandom();
        int nextX, nextY;
        for(int i = 0; i < n; i++)
        {
            nextX = random.nextInt(300);
            nextY = random.nextInt(300);
            this.remInd(everyone[i], everyone[i].getX(), everyone[i].getY());
            this.addInd(everyone[i], nextX, nextY);
            everyone[i].setX(nextX);
            everyone[i].setY(nextY);
        }
    }

    public void analyseInd(Individu[] everyone, int n, int nb_state[]) //Syntaxe des états dans nb_state : SUS EXP INF REC
    {
        boolean b;

        /*Remise à zéro du nombre d'individu par état */
        nb_state[0] = 0;
        nb_state[1] = 0;
        nb_state[2] = 0;
        nb_state[3] = 0;

        /*Parcours des individus */
        for(int i = 0; i < n; i++)
        {
            switch(everyone[i].getStatut()){
                /*Chacunes des méthodes ci-dessous doit nous indiquer si y'a eu changement d'état ou non (on retourne un bool) */

                case SUSCEPTIBLE :
                    /*Insérer méthode SUSCEPTIBLE -> INFECTED*/
                    break;
                case EXPOSED :
                    /*Insérer méthode EXPOSED -> INFECTED */
                    b = updExposed(everyone[i]);
                    if(b){nb_state[1]++;}
                    break;
                case INFECTED :
                    /*Insérer méthode INFECTED -> RECOVERED */
                    b = updInf(everyone[i]);
                    if(b){nb_state[2]++;}
                    break;
                case RECOVERED :
                    /*Insérer méthode RECOVERED -> SUSCEPTIBLE */
                    b = updRecov(everyone[i]);
                    if(b){nb_state[3]++;}
                    break;
                default :
                    break;   
            }

        }
    }

    public boolean updExposed(Individu I)
    {
        boolean b = false;
        if(I.getTime() > I.getdE()){
            b = true;
            I.setStatut(Statut.INFECTED);
        }
        return b;
    }

    public boolean updInf(Individu I)
    {
        boolean b = false;
        if(I.getTime() > I.getdI()){
            b = true;
            I.setStatut(Statut.RECOVERED);
        }
        return b;
    }

    public boolean updRecov(Individu I)
    {
        boolean b = false;
        if(I.getTime() > I.getdR()){
            b = true;
            I.setStatut(Statut.SUSCEPTIBLE);
        }
        return b;
    }

    public List<Individu> getInd(int i, int j) {return grille[i][j];}
}
