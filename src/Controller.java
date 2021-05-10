public class Controller {
    final static int MAX_BURGER_AMOUNT = 5;


    final static String[][] BEFEHLE = {
            {"menu", "Mit menu kannst Du Dir unsere Speisekarte ausgeben lassen!"},
            {"zutat (NR)", "Mit zutat und einer folgenden Zutatennummer, kannst Du deinen Burger mit einer gewuenschten Zutat belegen!"},
            {"meine Burger", "mit meine Burger kannst Du Dir jederzeit deine bisher erstellten Burger anzeigen lassen!"},
            {"ok", "Mit ok kannst Du Deine aktive zusammenstellung abschließen!"},
            {"einreichen", "Mit einreichen kannst Du mit deinen Kreationen am Wettbewerb teilnehmen!"}
    };

    public static void main(String[] args) {
        Burger[] burgerListe = new Burger[MAX_BURGER_AMOUNT];
        String eingabe;
        boolean abgeschlossen = false;
        boolean zusammenstellungAktiv = false;
        int burgerIndex = 0;

        Speisekarte speisekarte = new Speisekarte();


        System.out.println("Willkommen...");
        zeigeBefehle();


        while(!abgeschlossen){

            eingabe = StaticScanner.nextString();
            String[] eingabeFormatiert = eingabe.toLowerCase().split("\\s+");

            switch (eingabeFormatiert[0]) {

                case "menu":
                    speisekarte.zeigeSpeisekarte();
                    break;

                case "zutat":

                    if(eingabeFormatiert[1]!=null&&istStringInteger(eingabeFormatiert[1])){
                        System.out.println(eingabeFormatiert[1]);

                        int produktNummer = Integer.parseInt(eingabeFormatiert[1]);

                        if(zusammenstellungAktiv){
                            burgerListe[burgerIndex].belegen(Speisekarte.getZutatByID(produktNummer));
                        }else{
                            if(burgerListe[burgerIndex]==null&&burgerIndex<=MAX_BURGER_AMOUNT){
                                System.out.println("Ein neuer Burger wird gestartet...");
                                burgerListe[burgerIndex]=new Burger();
                                zusammenstellungAktiv=true;
                                burgerListe[burgerIndex].belegen(Speisekarte.getZutatByID(produktNummer));
                            }
                        }

                        System.out.println(Speisekarte.getZutatByID(produktNummer).getName()+" - "+Speisekarte.getZutatByID(produktNummer).getPreis()+"Euro");
                    }
                    break;

                case "meine":
                    if(eingabeFormatiert[1].equals("burger")){
                        for(int i = 0; i<burgerListe.length;i++){
                            if(burgerListe[i]!=null){
                                System.out.println(burgerListe[i].getBurgerName());
                                for(int j = 0; j<burgerListe[i].zutatenListe.length;j++){
                                    if(burgerListe[i].zutatenListe[j]!=null){
                                        System.out.println(burgerListe[i].zutatenListe[j].toString());
                                    }
                                }
                            }
                        }
                    }
                    break;

                case "ok":
                    if(zusammenstellungAktiv){
                        String burgerName;
                        do{
                            System.out.println("Bitte gib Deiner Kreation noch einen Namen:");
                            burgerName = StaticScanner.nextString();
                        }while(burgerName.equals(""));
                        burgerListe[burgerIndex].setBurgerName(burgerName);
                        System.out.println("Dein Burger wurde aufgenommen.");
                        zusammenstellungAktiv=false;
                        burgerIndex++;
                    }else{
                        System.out.println("Du hast keine aktive Zusammenstellung. Waehle eine Zutat, um einen Burger zusammenzustellen.");
                    }
                    break;

                case "einreichen":
                    float gesamtZubereitungsZeit=0;
                    float gesamtPreis=0;
                    for(Burger burger : burgerListe){
                        if(burger!=null){
                            //System.out.println("Dein(e) Burger nehmen nun am Wettbewerb teil!");
                            abgeschlossen = true;
                            System.out.println(burger.toString());
                            System.out.println(burger.zubereiten());
                            gesamtZubereitungsZeit+=burger.getZubereitungsZeit();
                            gesamtPreis += burger.berechnePreis();
                            System.out.println(burger.getZubereitungsZeit());
                        }
                    }
                    System.out.println("Gesamtzubereitungszeit: "+gesamtZubereitungsZeit);
                    System.out.println("Gesamtpreis: "+gesamtPreis);

                    if(!abgeschlossen){
                        System.out.println("Du hast noch keine Burger zum einreichen erstellt!");
                    }
                    break;

                default:
                    System.out.println(eingabe + " ist kein gueltiger Befehl!");
                    zeigeBefehle();
                    break;
            }

        }
    }public static void zeigeBefehle(){
        System.out.println("Befehle");
        for (String[] row : BEFEHLE) {
            for (String befehl : row) {
                System.out.print(befehl + " ");
            }
            System.out.println();
        }
    }

    public static boolean istStringInteger(String check) {
        try {
            int wert = Integer.parseInt(check);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
