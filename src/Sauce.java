/**
 * Die Klasse Sauce bildet eine Auspreagung der Klasse Zutat und kann zur Erzeugung verschiedener Saucen instanziiert werden.
 * @author Emily Jung, Neele Wolf, Felix Wuest
 */
public class Sauce extends Zutat{
    protected int menge;
    protected String geschmack;
    /**
     * Da Sauce keine speziellen Parameter hat, wird einfach der Super Konstruktor von Zutaten aufgerufen.
     * @param nummer produktnummer auf der Speisekarte
     * @param name  produktname
     * @param preis preis
     * @param klassisch klassische
     * @param vegan vegane Zutat?
     * @param vegetarisch vegetarische Zutat
     * @param menge menge der Sauce in milliliter
     * @param geschmack geschmacksrichtung der Sauce
     */
    public Sauce(int nummer, String name, float preis, boolean klassisch, boolean vegan, boolean vegetarisch, int menge, String geschmack) {
        super(nummer, name, preis, klassisch, vegan, vegetarisch);
        this.menge = menge;
        this.geschmack = geschmack;
    }

    /**
     * Sauce hat keine Zubereitungszeit und gibt daher 0 Sekunden als Zeit zurueck.
     * Gibt die Zubereitungshinweise aus.
     * @return zubereitungszeit in Sekunden
     */
    @Override
    public int zubereiten() {
        System.out.println(rezept());
        return 0;
    }

    /**
     * Spezielle Zubereitungsanweisung fuer Zutaten des Typs Sauce.
     * @return Zubereitungshinweis als String
     */
    @Override
    public String rezept() {
        return "- "+name +" wird gesch\u00fcttelt";
    }

    /**
     * Ueberschreibt die standardmaessige Implementierung der toString Methode, spezialisert auf Sauce
     * @return Zutateninformationen Geschmack als String
     */
    @Override
    public String toString() {
        return super.toString()+" | "+geschmack;
    }

    /**
     * Getter für den Geschmack der Sauce
     * @return geschmack der Sauce
     */
    public String getGeschmack() {
        return geschmack;
    }
}
