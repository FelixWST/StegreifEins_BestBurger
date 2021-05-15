public class Salat extends Zutat {

    public Salat(int nummer, String name, float preis, boolean klassisch) {
        super(nummer, name, preis, klassisch, true, true);
    }

    @Override
    public int zubereiten() {
        System.out.println ("- "+ name + " wird gewaschen");
        return 0;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
