import propagation.*;
/**
 * Main
 */
public class Main {

    public static void main(String[] args) 
    {
        Individu[] tabInd = new Individu[20000];
        double random = new MTRandom().nextDouble();
        Espace E = new Espace();
        Individu i = new Individu(null, 1, 0, 0, 0, 1, 1);
        Individu j = new Individu(null, 0, 0, 0, 0, 1, 1);
        E.addInd(i, 1, 1);
        E.addInd(j, 1, 1);
        tabInd[0] = i;
        tabInd[1] = j;
        E.moveAllInd(tabInd);
    }
}