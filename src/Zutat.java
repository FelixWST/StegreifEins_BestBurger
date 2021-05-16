/**
 * Die Klasse Zutat bildet das Grundkonstrukt fuer weitere Auspraegungen in Form von speziellen Zutaten.
 * Daher sollte diese nicht instanziiert werden und ist somit eine abstrakte Klasse.
 *
 * @author Emily Jung, Neele Wolf, Felix Wuest
 */
public abstract class Zutat {
    protected int nummer;
    protected String name;
    protected float preis;
    protected boolean klassisch;
    protected boolean vegan;
    protected boolean vegetarisch;

    /**
     * Super Konstruktor Zutat, der durch einzelne Zutatenauspraegungen aufgerufen wird.
     *
     * @param nummer produktnummer auf der Speisekarte
     * @param name  produktname
     * @param preis preis
     * @param klassisch klassische Zutat?
     * @param vegan vegane Zutat?
     * @param vegetarisch vegetarische Zutat?
     */
    public Zutat(int nummer, String name, float preis, boolean klassisch, boolean vegan, boolean vegetarisch){
        this.nummer = nummer;
        this.name = name;
        this.preis = preis;
        this.klassisch = klassisch;
        this.vegan = vegan;
        this.vegetarisch = vegetarisch;
    }

    /**
     * Abstrakte formulierung der zubereiten Methode.
     * zubereiten soll die Zubereitungsanweisung einer Zutat ausgeben und die Zubereitungszeit als int in Sekunden zurueckgeben.
     *
     * @return Zubereitungszeit der Zutat in Sekunden
     */
    public abstract int zubereiten();

    /**
     * Abstrakte Methode zur Ausgabe des Rezeptes.
     * Wird durch zubereiten aufgerufen, um die Anweisungen auf der Konsole auszugeben.
     * Wird auch fuer den Export des Rezeptes verwendet.
     *
     * @return Zubereitungsanweisung als String
     */
    public abstract String rezept();

    /**
     * Die Methode berechneHoehe gibt die hoehe der jeweiligen Zutat zurueck.
     * Standardmaessig gibt sie 0 zurueck, falls eine Zutat keine Hoehe hat.
     * Sollte eine Zutat doch eine Hoehe haben, muss die Klasse diese Methode ueberschreiben.
     *
     * @return Hoehe der Zutat in millimeter
     */
    public float berechneHoehe(){
        return 0;
    }

    /**
     * Ueberschreibt die standardmaessige Implementierung der toString Methode, spezialisert auf eine Zutat.
     * Diese Methode gibt die gemeinsamen Attribute jeder Zutat aus.
     * Fuer spezielle zusatzinformationen muss die toString Methode in der speziellen Zutat ueberschrieben werden und die super Methode aufrufen.
     *
     * @return Zutateninformationen als String
     */
    @Override
    public String toString(){
        String ausgabe = nummer+" - "+name+" | "+preis+" Euro";

        if(klassisch){
            ausgabe += " | klassisch";
        }
        if(vegan){
            ausgabe+= " | vegan";
        }
        if(vegetarisch){
            ausgabe+= " | vegetarisch";
        }
        return ausgabe;
    }

    /**
     * Getter fuer den Preis der Zutat
     *
     * @return Zutatenpreis in Euro als float
     */
    public float getPreis(){
        return this.preis;
    }

    /**
     * Getter fuer den Boolean klassisch der Zutat
     * @return wahrheitswert, ob Zutat klassisch oder nicht
     */
    public boolean isKlassisch() {
        return klassisch;
    }

    /**
     * Getter fuer den boolean vegan der Zutat
     * @return wahrheitswert, ob Zutat vegan oder nicht
     */
    public boolean isVegan() {
        return vegan;
    }

    /**
     * Getter fuer den boolean vegetarisch der Zutat
     * @return wahrheitswert, ob Zutat vegetarisch oder nicht
     */
    public boolean isVegetarisch() {
        return vegetarisch;
    }

    /**
     * Getter fuer den Namen der Zutat
     * @return name der Zutat als String
     */
    public String getName() {
        return name;
    }
}