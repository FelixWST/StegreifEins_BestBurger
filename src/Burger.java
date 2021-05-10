public class Burger {
    protected String burgerName;
    protected Zutat zutatenListe[] = new Zutat[12];
    //oder broetchen extra? -> Wechsel moeglich?

    public Burger(){
        for(Zutat zutat : zutatenListe){
            zutat = null;
        }
    }

    public float berechneHoehe(){
        float hoehe=0;
        for(Zutat zutat : zutatenListe){
            hoehe += zutat.berechneHoehe();
        }
        return hoehe;
    }

    public float berechnePreis(){
        float preis = 0;
        for(Zutat zutat : zutatenListe){
            preis +=zutat.getPreis();
        }
        return 0;
    }

    public float berechneZeit(){


        return 0;
    }

    public void belegen(Zutat neueZutat){
        if(zutatenListe[zutatenListe.length-1]==null){
            for(int i = 0; i<zutatenListe.length;i++){
                if(zutatenListe[i]==null){
                    zutatenListe[i]=neueZutat;
                    break;
                }
            }
        }else{
            System.out.println("Der Burger ist schon komplett belegt!");
        }


    }

    public String zubereiten(){
        return "";
    }

    public boolean istVegan(){
        return false;
    }

    public boolean istVegetarisch(){
        return false;
    }

    public boolean istKlassisch(){
        return false;
    }

    public boolean istNormal(){
        return false;
    }

    public boolean istScharf(){
        return false;
    }

    public boolean istSuess(){
        return false;
    }

    public void setBurgerName(String burgerName) {
        this.burgerName = burgerName;
    }

    public String getBurgerName(){
        return this.burgerName;
    }

}
