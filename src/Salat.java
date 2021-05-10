public class Salat extends Zutat {

    public Salat(int nummer, String name, float preis, boolean klassisch) {
        super(nummer, name, preis, klassisch, true, true);
    }

    public int zubereiten() {

        return 0;
    }


    public String toString() {
        return super.toString();
    }
}
