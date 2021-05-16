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

    /**
     * Ein Broetchen hat eine Hoehe. Allerdings ist diese dynamisch, da das Broetchen mit der Backzeit um 2% pro Minute aufgeht.
     * @return dynamische Hoehe des Broetchens nach Backzeit
     */
    @Override
    public float berechneHoehe() {
        float backHeight = (( backzeit / 60.0f) * (hoehe / 100.0f) * 2.0f);
        return hoehe+backHeight;
    }

    /**
     * Spezielle Zubereitungsanweisung fuer Burgerbroetchen.
     * Sekunden werden nur mit ausgegeben, wenn es keine glatte Minutenzahl ist.
     *
     * @return Zubereitungshinweis als String
     */
    @Override
    public String rezept() {
        int minuten = backzeit / 60;
        int sekunden = backzeit % 60;

        String zubereitungszeit = minuten+" Minuten";
        if(sekunden>0){
            zubereitungszeit += " und "+sekunden+" Sekunden";
        }
        return "- " + name +" "+ zubereitungszeit+ " r\u00f6sten";
    }
}
