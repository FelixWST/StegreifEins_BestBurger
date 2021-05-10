public class Sauce extends Zutat{
    protected int menge;
    protected String geschmack;

    public Sauce(int nummer, String name, float preis, boolean klassisch, boolean vegan, boolean vegetarisch, int menge, String geschmack) {
        super(nummer, name, preis, klassisch, vegan, vegetarisch);
        this.menge = menge;
        this.geschmack = geschmack;
    }

    public int zubereiten() {
        return 0;
    }

    public String toString() {
        return super.toString()+" | "+geschmack;

    }

    public String getGeschmack() {
        return geschmack;
    }
}
