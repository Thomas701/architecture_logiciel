import propagation.*;
/**
 * Main
 */
public class Main {

    public static void main(String[] args) 
    {
        Individu[] tabInd = new Individu[2];
        double random = new MTRandom().nextDouble();
        Espace E = new Espace();
        Individu i = new Individu(null, 1, 0, 0, 0, 0, 0);
        Individu j = new Individu(null, 0, 0, 0, 0, 0, 0);
        E.addInd(i, 0, 0);
        E.addInd(j, 0, 0);
        for (Individu individu : (E.getGrille())[0][0]) {
            System.out.println(individu.toString() + "\n");
        }
        tabInd[0] = i;
        tabInd[1] = j;
        E.moveAllInd(tabInd);
    }
}