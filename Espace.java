package architecture_logiciel;

import java.util.ArrayList;
import java.util.List;

public class Espace 
{
    private List<Individu>[][] grille =  new ArrayList[300][300]; //Ajout de commentaire inutile
                                                                    //Second commentaire inutile

    public Espace()
    {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
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

    public List<Individu> getInd(int i, int j) {return grille[i][j];}
}
