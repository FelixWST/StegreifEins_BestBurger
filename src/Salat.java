public class Salat extends Zutat {

    public Salat(int nummer, String name, float preis, boolean klassisch) {
        super(nummer, name, preis, klassisch, true, true);
    }

    @Override
    public int zubereiten() {
        System.out.println(rezept());
        return 0;
    }

    /**
     * Spezielle Zubereitungsanweisung fuer Zutaten des Typs Salat.
     * @return Zubereitungshinweis als String
     */
    @Override
    public String rezept() {
        return "- "+ name + " wird gewaschen";
    }
}
