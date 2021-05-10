public class Bratling extends Zutat{

    protected int bratzeit;
    protected int hoehe;

    public Bratling (int nummer, String name, float preis, boolean klassisch, boolean vegan, boolean vegetarisch, int bratzeit, int hoehe){
        super(nummer, name, preis, klassisch, vegan, vegetarisch);
        this.bratzeit=bratzeit;
        this.hoehe=hoehe;
    }

    public int zubereiten(){
        int minuten = (bratzeit/2)/60;
        int sekunden = (bratzeit/2)%60;
        System.out.println("- "+name+" von jeder Seite "+minuten+" Minuten und "+sekunden+" Sekunden grillen.");
        return bratzeit;
    }

    public int berechneHoehe(){
    //Bratling verliert um 3,5% pro Minute Bratzeit an Höhe
        return 0;
    }

    public String toString(){
        String ausgabe = (super.toString());
        return ausgabe;

    }



}
