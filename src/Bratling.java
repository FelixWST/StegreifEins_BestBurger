public class Bratling extends Zutat{
//Kommentar
    protected int bratzeit;
    protected int hoehe;

    public Bratling (int nummer, String name, float preis, boolean klassisch, boolean vegan, boolean vegetarisch, int bratzeit, int hoehe){
        super(nummer, name, preis, klassisch, vegan, vegetarisch);
        this.bratzeit=bratzeit;
        this.hoehe=hoehe;
    }

    public int zubereiten(){

        return 0;
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
