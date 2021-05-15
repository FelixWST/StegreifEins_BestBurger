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

            if(zusammenstellungAktiv){
                System.out.println("Dein Burger besteht aktuell aus "+burgerListe[burgerIndex].wieVoll()[0]+" von "+burgerListe[burgerIndex].wieVoll()[1]+" Zutaten!");
            }else{
                System.out.println("Du hast "+burgerIndex+" von "+MAX_BURGER_AMOUNT+" m\u00f6glichen Burgern erstellt.");
            }
            eingabe = StaticScanner.nextString();
            String[] eingabeFormatiert = eingabe.toLowerCase().split("\\s+");

            switch (eingabeFormatiert[0]) {

                case "menu":
                    speisekarte.zeigeSpeisekarte();
                    break;

                case "zutat":
                    if(burgerIndex==MAX_BURGER_AMOUNT){
                        System.out.println("Du hast bereits alle "+MAX_BURGER_AMOUNT+" m\u00f6glichen Burger erstellt.\nDu kannst diese nun einreichen!");
                    }else{
                        if(eingabeFormatiert.length>1&&eingabeFormatiert[1]!=null){
                            if(istStringInteger(eingabeFormatiert[1])){
                                int produktNummer = Integer.parseInt(eingabeFormatiert[1]);
                                if(Speisekarte.getZutatByID(produktNummer)!=null){
                                    if(zusammenstellungAktiv){
                                        burgerListe[burgerIndex].belegen(Speisekarte.getZutatByID(produktNummer));
                                    }else{
                                        if(burgerListe[burgerIndex]==null&&burgerIndex<=MAX_BURGER_AMOUNT){
                                            System.out.println("Ein neuer Burger wird gestartet...");
                                            System.out.println("F\u00fcge eine weitere Zutat mit zutat (NR) hinzu oder beende deine Zusammenstelltung mit ok");
                                            burgerListe[burgerIndex]=new Burger();
                                            zusammenstellungAktiv=true;
                                            burgerListe[burgerIndex].belegen(Speisekarte.getZutatByID(produktNummer));
                                        }else{
                                            System.out.println("Du kannst keinen Burger mehr erstellen!");
                                        }
                                    }
                                }
                            }else{
                                System.out.println("Der zweite Parameter des Befehls Zutat muss eine Produktnummer sein!");
                            }
                        }else{
                            System.out.println("Der Befehl Zutat erwartet einen zweiten Parameter mit der dazugeh\u00f6rigen Nummer!");
                        }
                    }
                    break;

                case "meine":
                    if(eingabeFormatiert[1].equals("burger")){
                        for(int i = 0; i<burgerListe.length;i++){
                            if(burgerListe[i]!=null){
                                if(burgerListe[i].getBurgerName()!=null){
                                    System.out.println((i+1)+". "+burgerListe[i].getBurgerName());
                                }else{
                                    System.out.println((i+1)+". Aktuelle Zusammenstellung");
                                }
                                for(int j = 0; j<burgerListe[i].zutatenListe.length;j++){
                                    if(burgerListe[i].zutatenListe[j]!=null){
                                        System.out.println(burgerListe[i].zutatenListe[j].toString());
                                    }
                                }
                                System.out.println();
                            }

                        }
                    }
                    break;

                case "ok":
                    if(zusammenstellungAktiv){
                        if(!burgerListe[burgerIndex].hatBroetchen()){
                            System.out.println("Du kannst deinen Burger erst abschliessen, wenn Du ein Br\u00f6tchen hinzugefuegt hast!");
                            break;
                        }
                        String burgerName;
                        do{
                            System.out.println("Bitte gib Deiner Kreation noch einen Namen:");
                            burgerName = StaticScanner.nextString();
                        }while(burgerName.equals(""));
                        burgerListe[burgerIndex].setBurgerName(burgerName);
                        System.out.println("Dein Burger wurde aufgenommen.");
                        zusammenstellungAktiv=false;

                        if(burgerIndex<(MAX_BURGER_AMOUNT-1)){
                            burgerIndex++;
                        }else{
                            burgerIndex++;
                            System.out.println("Du hast nun die Maximalanzahl von "+MAX_BURGER_AMOUNT+" Burgern erreicht!");
                            System.out.println("Du kannst deine Burger nun einreichen!");
                        }

                    }else{
                        System.out.println("Du hast keine aktive Zusammenstellung. Waehle eine Zutat, um einen Burger zusammenzustellen.");
                    }
                    break;

                case "einreichen":
                    if(zusammenstellungAktiv){
                        System.out.println("Du stellst gerade einen Burger zusammen.");
                        System.out.println("Beende diese Zusammenstellung erst mit ok, um deine Burger einzureichen!");
                    }else if(burgerListe[0]==null){
                        System.out.println("Du hast noch keinen Burger zum einreichen erstellt!");
                    }else{
                        int gesamtZubereitungsZeit=0;
                        float gesamtPreis=0;
                        for(Burger burger : burgerListe){
                            if(burger!=null){
                                abgeschlossen = true;
                                System.out.println("=======================================================================================");
                                System.out.println(burger.toString());
                                System.out.println(burger.zubereiten());
                                gesamtZubereitungsZeit+=burger.getZubereitungsZeit();
                                gesamtPreis += burger.berechnePreis();

                                int minuten = burger.getZubereitungsZeit()/60;
                                int sekunden = burger.getZubereitungsZeit()%60;

                                String zubereitungszeit = minuten+" Minuten";
                                if(sekunden>0){
                                    zubereitungszeit += " und "+sekunden+" Sekunden.";
                                }

                                System.out.println("Die Zubereitungszeit für dieses Burger betr\u00e4gt "+zubereitungszeit);
                                System.out.println("=======================================================================================");
                            }
                        }

                        int minuten = gesamtZubereitungsZeit / 60;
                        int sekunden = gesamtZubereitungsZeit % 60;

                        String zubereitungszeit = minuten+" Minuten";
                        if(sekunden>0){
                            zubereitungszeit += " und "+sekunden+" Sekunden.";
                        }

                        System.out.println("Gesamtzubereitungszeit für alle Burger: "+zubereitungszeit);
                        System.out.println("Gesamtpreis für alle Burger: "+String.format("%.2f",gesamtPreis) + " €");
                    }
                    break;

                case "leeren":
                    if(zusammenstellungAktiv){
                        if(burgerListe[burgerIndex].wieVoll()[0]==0){
                            System.out.println("Deine aktive Zusammenstellung ist bereits Leer!");
                        }else{
                            System.out.println("M\u00f6chtest Du wirklich deine aktive Zusammenstellung leeren?");
                            System.out.println("Best\u00e4tige mit Ja. Mit jeder anderen eingabe kannst du abbrechen.");

                            if(StaticScanner.nextString().toLowerCase().equals("ja")){
                                burgerListe[burgerIndex].zutatenLeeren();
                                System.out.println("Deine aktive Zusammenstellung wurde geleert!");
                            }else{
                                System.out.println("Deine aktive Zusammenstellung wird nicht geleert!");
                            }
                        }
                    }else{
                        System.out.println("Du hast gerade keine aktive Zusammenstellung, die geleert werden kann.");
                    }
                    break;

                case "l\u00f6schen":
                    if(zusammenstellungAktiv){
                        System.out.println("Du kannst keinen Burger l\u00f6schen, w\u00e4hrend eine Zusammenstellung aktiv ist!");
                    }else{
                        //Burger auflisten
                        //jeder Burger Nummer
                        //Eingabe einer Nummer als auswahl
                        //Bestätigen
                        //Aufrücken im Array, wenn Burger weg?
                        //Anpassung BurgerIndex??
                    }

                    break;

                default:
                    System.out.println(eingabe + " ist kein gueltiger Befehl!");
                    zeigeBefehle();
                    break;
            }
        }
        System.out.println("Deine Burger wurden erfolgreich eingereicht!");
        System.out.println("Du hast nun noch die m\u00f6glichkeit, deine Kreationen in eine Datei zu exportieren, um sie sp\u00e4ter zubereiten zu k\u00f6nnen!");
        System.out.println("Mit ja kannst Du deine Rezepte Exportieren. Mit jeder anderen Eingabe beendest Du das Programm.");

        if(StaticScanner.nextString().toLowerCase().equals("ja")){
            BurgerExporter burgerExporter = new BurgerExporter();
            System.out.println(burgerExporter.exportBurger(burgerListe));
        }else{
            System.out.println("Deine Rezepte werden nicht exportiert!");
        }
        System.out.println("Vielen Dank...");




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
