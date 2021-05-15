public abstract class Zutat {
    protected int nummer;
    protected String name;
    protected float preis;
    protected boolean klassisch;
    protected boolean vegan;
    protected boolean vegetarisch;

    public Zutat(int nummer, String name, float preis, boolean klassisch, boolean vegan, boolean vegetarisch){
        this.nummer = nummer;
        this.name = name;
        this.preis = preis;
        this.klassisch = klassisch;
        this.vegan = vegan;
        this.vegetarisch = vegetarisch;
    }

    public abstract int zubereiten();

    public int berechneHoehe(){
        return 0;
    }

    public float getPreis(){
        return this.preis;
    }

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

    public boolean isKlassisch() {
        return klassisch;
    }

    public boolean isVegan() {
        return vegan;
    }

    public boolean isVegetarisch() {
        return vegetarisch;
    }

    public String getName() {
        return name;
    }
}
