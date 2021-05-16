public class Sauce extends Zutat{
    protected int menge;
    protected String geschmack;

    public Sauce(int nummer, String name, float preis, boolean klassisch, boolean vegan, boolean vegetarisch, int menge, String geschmack) {
        super(nummer, name, preis, klassisch, vegan, vegetarisch);
        this.menge = menge;
        this.geschmack = geschmack;
    }

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

    public String getGeschmack() {
        return geschmack;
    }
}
