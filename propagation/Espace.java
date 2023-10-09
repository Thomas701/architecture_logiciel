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

    public void moveAllInd(Individu[] everyone)
    {
        MTRandom random = new MTRandom();
        int nextX, nextY;
        for(int i = 0; i < 20000; i++)
        {
            nextX = random.nextInt(300);
            nextY = random.nextInt(300);
            this.remInd(everyone[i], everyone[i].getX(), everyone[i].getY());
            this.addInd(everyone[i], nextX, nextY);
            everyone[i].setX(nextX);
            everyone[i].setY(nextY);
        }
    }

    public List<Individu> getInd(int i, int j) {return grille[i][j];}
}
