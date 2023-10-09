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
            everyone[i].setTime(everyone[i].getTime()+1);
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
                    /*Si y'a eu changement alors nb_state[2]++ | nb_state[0]++ Sinon */
                    b = updSus(everyone[i]);
                    if(b){nb_state[1]++;}
                    else{nb_state[0]++;}
                    break;
                case EXPOSED :
                    /*Insérer méthode EXPOSED -> INFECTED */
                    b = updExposed(everyone[i]);
                    if(b){nb_state[2]++;}
                    else{nb_state[1]++;}
                    break;
                case INFECTED :
                    /*Insérer méthode INFECTED -> RECOVERED */
                    b = updInf(everyone[i]);
                    if(b){nb_state[3]++;}
                    else{nb_state[2]++;}
                    break;
                case RECOVERED :
                    /*Insérer méthode RECOVERED -> SUSCEPTIBLE */
                    b = updRecov(everyone[i]);
                    if(b){nb_state[0]++;}
                    else{nb_state[3]++;}
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
            I.setTime(0);
        }
        return b;
    }

    public boolean updInf(Individu I)
    {
        boolean b = false;
        if(I.getTime() > I.getdI()){
            b = true;
            I.setStatut(Statut.RECOVERED);
            I.setTime(0);
        }
        return b;
    }

    public boolean updRecov(Individu I)
    {
        boolean b = false;
        if(I.getTime() > I.getdR()){
            b = true;
            I.setStatut(Statut.SUSCEPTIBLE);
            I.setTime(0);
        }
        return b;
    }

    public static int nb_Inf(List<Individu> l){
        int nb_inf = 0;
        for (Individu ind : l) {
            if(ind.getStatut() == Statut.INFECTED){nb_inf++;}
        }
        return nb_inf;
    }

    public boolean updSus(Individu I){
        int     nb_Inf = 0;
        boolean b = false;
        int x,y;                            /*Ces variables vont contenir les coordonnées du coin en haut à gauche, on effectue l'observation de gauche à droite et de bas en haut */
        x = I.getX() - 1;
        y = I.getY() - 1;
        if(x < 0){x = 299;}
        if(y < 0){y = 299;}
        int coordx, coordy;                /*On reprend de nouvelle variable qui s'occupe de se déplacer dans la zone d'observation */
        for(int k = 0; k < 3; k++){
            coordx = (x+k) % 300;
            for(int l = 0; l < 3; l++){
                coordy = (y+l) % 300;
                nb_Inf += nb_Inf(this.grille[coordx][coordy]);
            }
        }
        /*On voit maintenant si l'individu va être infecté ou non */
        double p = 1-Math.exp(-0.5*nb_Inf);
        double r = new MTRandom().nextDouble();
        if(r <= p){
            b = true;
            I.setStatut(Statut.EXPOSED);
            I.setTime(0);
        }
        return b;
    }

    public List<Individu> getInd(int i, int j) {return grille[i][j];}

    public void resetGrille()
    {
        for (int i = 0; i < 300; i++) 
        {
            for (int j = 0; j < 300; j++)
            {
                try {
                    this.grille[i][j].clear();
                } catch (Exception e) {
                    continue;
                } 
            }
        }
    }
}
