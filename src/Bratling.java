/**
 * Die Klasse Bratling bildet eine Auspreagung der Klasse Zutat.
 *
 *
 * @author Emily Jung, Neele Wolf, Felix Wuest
 */
public class Bratling extends Zutat{

    protected int bratzeit;
    protected float hoehe;

    /**
     * Konstruktor für die Klasse Bratling
     * Instanziierung spezieller Parameter, bratzeit und hoehe, der Klasse
     * Aufruf der Superklasse Zutat
     * @param nummer Produktnummer
     * @param name Produktname
     * @param preis Preis des Produkts
     * @param klassisch klassisch?
     * @param vegan vegan?
     * @param vegetarisch vegetarisch?
     * @param bratzeit bratzeit des Bratling
     * @param hoehe hoehe des Bratling
     */
    public Bratling (int nummer, String name, float preis, boolean klassisch, boolean vegan, boolean vegetarisch, int bratzeit, float hoehe){
        super(nummer, name, preis, klassisch, vegan, vegetarisch);
        this.bratzeit=bratzeit;
        this.hoehe=hoehe;
    }

    /**
     * spezielle Zubereitung des Bratling
     * der Bratling hat eine Zubereitungszeit in Form von bratzeit
     * @return bratzeit in Sekunden
     */
    @Override
    public int zubereiten(){
        System.out.println(rezept());
        return bratzeit;
    }

    /**
     * Die Methode berechneHoehe gibt die hoehe des Bratlings zur
     * Dabei wird die Hoehe des Bratlings um 3.5 Prozent pro Minute veringert.
     * @return Hoehe des Bratlings in Millimeter
     */
    @Override
    public float berechneHoehe() {
        float bratHeight = (( bratzeit / 60.0f) * (hoehe / 100.0f) * 3.5f);
        return hoehe-bratHeight;

    }

    /**
     * Spezielle Zubereitungsanweisung fuer Zutaten des Typs Bratling.
     * @return Zubereitungshinweis als String
     */
    @Override
    public String rezept() {
        int minuten = (bratzeit/2)/60;
        int sekunden = (bratzeit/2)%60;

        String zubereitungszeit = minuten+" Minuten";

        if(sekunden>0){
            zubereitungszeit += " und "+sekunden+" Sekunden";
        }
        return "- "+name+" von jeder Seite "+zubereitungszeit+" grillen.";
    }
}
