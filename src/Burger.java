public class Burger {
    protected String burgerName;
    protected float zubereitungsZeit;
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
            if(zutat!=null){
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

    public float berechneZeit(){
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

    public String toString(){
        String sorte ="";
        if(istKlassisch()){
            sorte += " , klassisch";
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

    public float getZubereitungsZeit() {
        return zubereitungsZeit;
    }
}
