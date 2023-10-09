package propagation;

public class Individu {

    private Statut statut;
    private int time;
    private int dE;
    private int dI;
    private int dR;
    private int x;
    private int y;

    public Individu(Statut stat, int time)
    {
        MTRandom random = new MTRandom();

        this.statut = stat;
        this.time = time;
        this.x = random.nextInt(300);
        this.y = random.nextInt(300);
        this.dE = (int) (-3 * Math.log(1 - random.nextDouble()));
        this.dI = (int) (-7 * Math.log(1 - random.nextDouble()));
        this.dR = (int) (-365 * Math.log(1 - random.nextDouble()));
    }

    public int getX() {return x;}
    public int getY() {return y;}
    public Statut getStatut() {return statut;}
    public int getTime() {return time;}
    public int getdE() {return dE;}
    public int getdI() {return dI;}
    public int getdR() {return dR;}

    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public void setStatut(Statut statut) {this.statut = statut;}
    public void setTime(int time) {this.time = time;}
    public void setdE(int dE) {this.dE = dE;}
    public void setdI(int dI) {this.dI = dI;}
    public void setdR(int dR) {this.dR = dR;}
}
