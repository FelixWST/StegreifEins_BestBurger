public class Burger {
    protected String burgerName;
    protected int zubereitungsZeit;
    protected Zutat zutatenListe[] = new Zutat[12];

    public Burger(){
        for(Zutat zutat : zutatenListe){
            zutat = null;
        }
    }


    public float berechneHoehe() {
        float hoehe = 0;
        for (Zutat zutat : zutatenListe) {
            if (zutat != null) {
                hoehe += zutat.berechneHoehe();
            }

        }
        return (hoehe/10);
    }

    public float berechnePreis(){
        float preis = 0;
        for(Zutat zutat : zutatenListe){
            if(zutat!=null){
                preis +=zutat.getPreis();
            }
        }
        return preis;
    }

    public int berechneZeit() {
        int gesamtZeit = 0;
        int offsetChar = 65;

        for(int i =0; i< zutatenListe.length;i++){
            if(zutatenListe[i]!=null){
                System.out.print((char)(i+offsetChar)+" ");
                gesamtZeit+=zutatenListe[i].zubereiten();
            }
        }
        return gesamtZeit/60.0f;
    }

    public void belegen(Zutat neueZutat) {
        if (!hatBroetchen()) {
            if (zutatenListe[zutatenListe.length - 2] != null && zutatenListe[zutatenListe.length - 1] == null) {
                if (!(neueZutat instanceof Broetchen)) {
                    System.out.println("Die Zutat konnte nicht hinzugef\u00fcgt werden.");
                    System.out.println("Dein Burger ben\u00f6tigt noch ein Broetchen.");
                    return;
                }
            }
        }
        if (zutatenListe[zutatenListe.length - 1] == null) {
            if (neueZutat instanceof Broetchen && hatBroetchen()) {
                System.out.println("Der Burger hat bereits ein Br\u00f6tchen!");
                System.out.println("W\u00e4hle eine andere Zutat!");
            } else {
                for (int i = 0; i < zutatenListe.length; i++) {
                    if (zutatenListe[i] == null) {
                        zutatenListe[i] = neueZutat;
                        System.out.println("Die Zutat " + neueZutat.getName() + " - " + neueZutat.getPreis() + " € wurde hinzugef\u00fcgt!");
                        break;
                    }
                }
            }
        } else {
            System.out.println("Der Burger ist schon komplett belegt!");
        }
    }

    public String zubereiten(){
        System.out.println("Und so geht's:");
        this.zubereitungsZeit = berechneZeit();
        return "";
    }

    public boolean istVegan(){
        boolean vegan = true;
        for(Zutat zutat : zutatenListe){
            if(zutat!=null){
                if(!zutat.isVegan()){
                    vegan = false;
                    break;
                }
            }
        }
        return vegan;
    }

    public boolean istVegetarisch(){
        boolean vegetarisch = true;
        for(Zutat zutat : zutatenListe){
            if(zutat!=null){
                if(!zutat.isVegetarisch()){
                    vegetarisch = false;
                    break;
                }
            }
        }
        return vegetarisch;
    }

    public boolean istKlassisch(){
        boolean klassisch = true;
        for(Zutat zutat : zutatenListe){
            if(zutat!=null){
                if(!zutat.isKlassisch()){
                    klassisch = false;
                    break;
                }
            }
        }
        return klassisch;
    }

   public String hatSauce(){
        //Umwandlung in String array
        String ausgabe="";
        for(Zutat zutat : zutatenListe){
            if(zutat!=null&&zutat instanceof Sauce){
                ausgabe += ((Sauce) zutat).getGeschmack()+", ";
            }
        }
        return ausgabe;
   }

    public void setBurgerName(String burgerName) {
        this.burgerName = burgerName;
    }

    public String getBurgerName(){
        return this.burgerName;
    }

    @Override
    public String toString() {
        String sorte = "";
        if (istKlassisch()) {
            sorte += ", klassisch";
        }
        if(istVegan()){
            sorte+= " , vegan";
        }else if(istVegetarisch()){
            sorte+= " , vegetarisch";
        }

        String saucen = hatSauce();

        String titelzeile ="Rezept -"+burgerName+" ("+berechneHoehe()+"cm "+ sorte + saucen+") - "+berechnePreis()+" Euro \n";
        String zutaten ="Zutaten: ";

        for(Zutat zutat : zutatenListe){
            if(zutat!=null){
                zutaten += zutat.getName()+",";
            }

        }

        String ausgabe = titelzeile + zutaten;

        return ausgabe;
    }

    public int getZubereitungsZeit() {
        return zubereitungsZeit;
    }

    public boolean hatBroetchen() {
        for (Zutat zutat : zutatenListe) {
            if (zutat instanceof Broetchen) {
                return true;
            }
        }
        return false;
    }

    public int[] wieVoll() {
        int[] fuellstand = new int[2];
        fuellstand[1] = zutatenListe.length;

        int zutatenZaehler = 0;

        for (Zutat zutat : zutatenListe) {
            if (zutat != null) {
                zutatenZaehler++;
            }
        }
        fuellstand[0] = zutatenZaehler;
        return fuellstand;
    }

    public void zutatenLeeren(){
        for(int i = 0; i<zutatenListe.length;i++){
            zutatenListe[i]=null;
        }
    }
}
