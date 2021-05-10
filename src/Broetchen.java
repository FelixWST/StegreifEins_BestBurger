public class Broetchen extends Zutat{

    protected int backzeit;
    protected int hoehe;

    public Broetchen (int nummer, String name, float preis, boolean klassisch, boolean vegan, boolean vegetarisch, int backzeit, int hoehe){
        super(nummer, name, preis, klassisch, vegan, vegetarisch);
        this.backzeit=backzeit;
        this.hoehe=hoehe;
    }
    
    
    public int zubereiten(){

        return 0;
    }


    public int berechneHoehe() {
        // gewinnt pro Minute Backzeit 2% an Höhe
        //int backHoehe = (int)(((float)backzeit/60.0)*((float)hoehe/100.0*2.0));
        float backHeight = ((float) (backzeit) / 60.0f) * (((float) (hoehe) / 100.0f) * 2.0f);
        //System.out.println(backHeight);
        return hoehe;
    }

    public String toString(){
        return super.toString();
    }
}
