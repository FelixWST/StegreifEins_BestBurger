public class Gemuese extends Zutat{

    protected int scheibenDicke;
    protected int scheibenAnzahl;

    public Gemuese (int nummer, String name, float preis, boolean klassisch, int scheibenDicke, int scheibenAnzahl){
        super(nummer, name, preis, klassisch, true, true);
        this.scheibenDicke=scheibenDicke;
        this.scheibenAnzahl=scheibenAnzahl;
    }

    public int zubereiten(){

        return 0;
    }

    public int berechneHoehe (){

        return 0;
    }

    public String toString(){
        String ausgabe = (super.toString());
        return ausgabe;
    }

}
