/**
 * Die Klasse Speisekarte dient der Zutatenverwaltung, sowohl fuer die Benutzerauswahl einer Zutat, als auch fuer das belegen eines Burgers.
 *
 * @author Emily Jung, Neele Wolf, Felix Wuest
 */
public class Speisekarte {

    /**
     * fest vorgegebene Anzahl an Kategorien und Zutaten pro Kategorie.
     * Diese muss bei Erweiterung der Speisekarte angepasst werden.
     */
    final static int ANZAHL_ZUTATEN_KATEGORIEN = 6;
    final static int MAX_ZUTATEN_PRO_KATEGORIE = 5;

    static Zutat[][] zutatenListe = new Zutat[ANZAHL_ZUTATEN_KATEGORIEN][MAX_ZUTATEN_PRO_KATEGORIE];
    String[] kategorien = new String[ANZAHL_ZUTATEN_KATEGORIEN];

    /**
     * Standardkonstruktor der Speisekarte. Bei der Instanziierung wird die zutatenListe zuerst komplett auf null gesetzt, um Probleme zu vermeiden, wenn eine Kategorie nicht die gesamte Zeile fuellt.
     * danach wird den Produktnummern entsprechend das Array mit den einzelnen Zutaten gefuellt.
     */
    public Speisekarte(){

        for(Zutat kategorie[] : zutatenListe){
            for(Zutat zutat : kategorie){
                zutat = null;
            }
        }

        //Zutaten an Index [0][X] = Broetchen
        zutatenListe[0][0] = new Broetchen(10,"Hamburger (Standard)",0.85F,true,false,true, 90, 27);
        zutatenListe[0][1] = new Broetchen(11,"Hamburger Sesam",0.95F,true,false,true, 90, 28);
        zutatenListe[0][2] = new Broetchen(12,"Vegan-Broetchen",0.55F,false,true,true, 240, 34);
        zutatenListe[0][3] = new Broetchen(13,"Ciabatta",0.45F,false,false,true, 330, 41);

        //Zutaten an Index [1][X] = Bratlinge
        zutatenListe[1][0] = new Bratling(20,"Rindfleisch (Original)", 1.85F,true,false,false, 270, 25);
        zutatenListe[1][1] = new Bratling(21,"Haehnchenfleisch gegrillt", 1.15F, false, false, false,180 ,11);
        zutatenListe[1][2] = new Bratling(22,"Falafel-Bratling", 1.45F,false,true,true, 210, 21);
        zutatenListe[1][3] = new Bratling(23,"Gemuesebratling", 1.75F,false,false,true, 240, 25);

        //Zutaten an Index [2][X] = Salate
        zutatenListe[2][0] = new Salat(30,"Eisbergsalat",0.18f,true);
        zutatenListe[2][1] = new Salat(31,"Rucolasalat",0.25f,false);

        //Zutaten an Index [3][X] = Gemuese
        zutatenListe[3][0] = new Gemuese(40, "Tomate", 0.25F, true, 3,3);
        zutatenListe[3][1] = new Gemuese(41, "Salzgurke", 0.15F, true, 2,4);
        zutatenListe[3][2] = new Gemuese(42, "Rote Zwiebelringe", 0.08F, false, 2,5);
        zutatenListe[3][3] = new Gemuese(43, "Jalapeno-Ringe", 0.08F, false, 2,5);

        //Zutaten an Index [4][X] = Saucen
        zutatenListe[4][0] = new Sauce(50,"Ketchup",0.10f,true,true,true,5,"normal");
        zutatenListe[4][1] = new Sauce(51,"Sandwich-Sauce",0.15f,true,false,true,10,"normal");
        zutatenListe[4][2] = new Sauce(52,"Chili-Sauce",0.25f,false,true,true,8,"scharf");
        zutatenListe[4][3] = new Sauce(53,"Honig-Senf-Sauce",0.18f,false,false,true,8,"süß");
    }


    /**
     * Gibt die Speisekarte formatiert nach Kategorie aus.
     */
    public void zeigeSpeisekarte(){
        System.out.println("\nDie Speisekarte: \n");
        for(int i = 0; i<zutatenListe.length; i++){
        System.out.println(kategorien[i]);
            for(int j = 0; j<zutatenListe[0].length; j++){
                if(zutatenListe[i][j]!=null){
                    System.out.println(zutatenListe[i][j].toString());
                }
            }System.out.println();
        }
    }

    /**
     * Um eine Zutat der Speisekarte auszuwaehlen, kann diese ueber die Produktnummer adressiert werden.
     * Dafuer muessen die Zutaten dem arrayindex entsprechend richtig angelegt sein.
     *
     * Die Methode uebernimmt die Umrechnung von Produktnummer zu Arrayindex und gibt die richtige Zutat zurueck.
     * Gibt es die Zutat nicht, wird dies abgefangen und null zurueckgegben.
     *
     * @param zutatID Produktnummer entsprechend der Speisekarte
     * @return gibt die gewaehlte Zutat zurueck, sofern existent. Ansonsten null.
     */
    public static Zutat getZutatByID(int zutatID){
            int itemNumber = zutatID%10;
            int rowNumber = zutatID/10;

            if(zutatenListe.length>=rowNumber&&zutatenListe[rowNumber-1].length>itemNumber){
                if(zutatenListe[rowNumber-1][itemNumber]!=null){
                    return zutatenListe[rowNumber-1][itemNumber];
                }
            }
            System.out.println("Die Zutat mit der Nummer "+zutatID+" existiert nicht!\n");
            return null;

    }




}
