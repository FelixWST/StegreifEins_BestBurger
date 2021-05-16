/**
 * Die Klasse Controller fuehrt in ihrer Main methode den Nutzer durch das Programm.
 * Dabei kann der Benutzer eine definierte Menge an Befehlen Nutzen, um seinen Burger fuer den Wettbewerb einzureichen.
 *
 * @author Emily Jung, Neele Wolf, Felix Wuest
 */
public class Controller {
    //Festgelegte Maximalanzahl an Burgern, kann flexibel hier zentral angepasst werden
    final static int MAX_BURGER_AMOUNT = 5;

    //zentrale Auflistung aller moeglichen Befehle
    final static String[][] BEFEHLE = {
            {"menu", "\t\t| Lass' Dir die Speisekarte ausgeben."},
            {"zutat (NR)", "\t| Belege Deinen Burger mit der gew\u00fcnschten Zutat."},
            {"meine Burger", "| Deine bisher erstellten Burger anzeigen."},
            {"ok", "\t\t\t| Aktive Zusammenstellung abschließen."},
            {"einreichen", "\t| Mit Deinen Kreationen am Wettbewerb teilnehmen."},
            {"leeren","\t\t| Alle Zutaten der aktiven Zusammenstellung entfernen."},
            {"l\u00f6schen", "\t| Bereits zusammengestellte Burger l\u00f6schen."},
            {"hilfe", "\t\t| M\u00f6gliche Befehle anzeigen."}
    };

    /**
     * Die main Methode, die den Nutzer durch den Wettbewerb fuehrt.
     * @param args
     */
    public static void main(String[] args) {
        Burger[] burgerListe = new Burger[MAX_BURGER_AMOUNT]; //Dieser Array verwaltet spaeter die erstellten Burger
        String eingabe;
        boolean abgeschlossen = false; //Abbruchbedingung der While-Schleife, true, wenn eingereicht
        boolean zusammenstellungAktiv = false; //true, wenn der Nutzer gerade einen Burger zusammenstellt (noch nicht mit ok bestaetigt)
        int burgerIndex = 0; //aktueller Index, fuer burgerListe und maximalanzahl wichtig

        Speisekarte speisekarte = new Speisekarte();

        System.out.println("Willkommen bei You'll never Burger alone!");
        System.out.println("=========================================\n");
        delay(1000);
        System.out.println("Kreiere bis zu f\u00fcnf eigene Burger und reiche diese bei unserem Burger-Wettbewerb ein.");
        System.out.println("Durch verschiedene Befehle kannst du den Burger des Jahres 2021 erstellen, der durch eine hochrangige Jury gew\u00e4hlt wird!\n");
        delay(1000);
        zeigeBefehle();
        delay(1000);
        System.out.println("\nBedenke allerdings, dass dein Burger nur aus 12 Zutaten bestehen darf, davon muss exakt eine ein Burger-Br\u00f6tchen sein!");
        System.out.println("Viel Spaß beim Erstellen deiner Burger!\n");
        delay(1000);

        /**
         * Hauptbestandteil der Programmfuehrung
         * While-schleife erwartet Befehl und fuehrt diesen aus, bis eingereicht wurde
         */
        while(!abgeschlossen){
            delay(500);
            System.out.println("=========================================\n");
            if(zusammenstellungAktiv){
                System.out.println("Du hast eine aktive Zusammenstellung.");
                System.out.println("Dein Burger besteht aktuell aus "+burgerListe[burgerIndex].wieVoll()[0]+" von "+burgerListe[burgerIndex].wieVoll()[1]+" Zutaten!");
            }else{
                System.out.println("Du hast "+burgerIndex+" von "+MAX_BURGER_AMOUNT+" m\u00f6glichen Burgern erstellt.");
            }
            System.out.print("Gebe einen Befehl ein:");
            eingabe = StaticScanner.nextString();
            String[] eingabeFormatiert = eingabe.toLowerCase().split("\\s+");//Umwandlung der Eingabe in String array getrennt an Leerzeichen -> mehrteilige Befehle

            switch (eingabeFormatiert[0]) {

                //zeigt alle moeglichen Befehle
                case "hilfe":
                    System.out.println();
                    zeigeBefehle();
                    break;

                //zeigt die gesamte Speisekarte
                case "menu":
                    speisekarte.zeigeSpeisekarte();
                    break;

                //zutat zu einem Burger hinzufuegen
                case "zutat":
                    System.out.println();
                    if(burgerIndex==MAX_BURGER_AMOUNT){
                        System.out.println("Du hast bereits alle "+MAX_BURGER_AMOUNT+" m\u00f6glichen Burger erstellt.\nDu kannst diese nun einreichen!\n");
                    }else{
                        if(eingabeFormatiert.length>1&&eingabeFormatiert[1]!=null){
                            if(istStringInteger(eingabeFormatiert[1])){
                                int produktNummer = Integer.parseInt(eingabeFormatiert[1]);
                                if(Speisekarte.getZutatByID(produktNummer)!=null){
                                    //Wenn bereits eine zusammenstellung aktiv ist, wird die Zutat einfach der aktiven zusammenstellung angefuegt
                                    if(zusammenstellungAktiv){
                                        burgerListe[burgerIndex].belegen(Speisekarte.getZutatByID(produktNummer));
                                    //Ansonsten wird ein neuer Burger erstellt (nur wenn noch Platz in Burgerliste)
                                    }else{
                                        if(burgerListe[burgerIndex]==null&&burgerIndex<=MAX_BURGER_AMOUNT){
                                            System.out.println("Ein neuer Burger wird gestartet...\n");
                                            System.out.println("F\u00fcge weitere Zutaten hinzu, oder beende deine Zusammenstelltung mit \"ok\"\n");
                                            burgerListe[burgerIndex]=new Burger();
                                            zusammenstellungAktiv=true;//Zusammenstellung wird nun auf aktiv gesetzt
                                            burgerListe[burgerIndex].belegen(Speisekarte.getZutatByID(produktNummer));
                                        }else{
                                            System.out.println("Du kannst keinen Burger mehr erstellen!\n");
                                        }
                                    }
                                }
                            }else{//Abfangen Fehler: zweiter Parameter ist kein int
                                System.out.println("Der zweite Parameter des Befehls Zutat muss eine Produktnummer sein!\n");
                            }
                        }else{//Abfangen Fehler: fehlender zweiter Parameter (Produktnummer)
                            System.out.println("Der Befehl \"Zutat\" erwartet einen zweiten Parameter mit der dazugeh\u00f6rigen Nummer!\n");
                        }
                    }
                    break;

                //auflistung aller in dieser Runtime erstellten Burger
                case "meine":
                    System.out.println();
                    if(eingabeFormatiert.length>1&&eingabeFormatiert[1].equals("burger")) {
                        if (burgerListe[0] != null) { //Jeder Burger wird ausgegeben, sofern an der ersten Stelle der Liste bereits schon ein Burger erzeugt wurde
                            for (int i = 0; i < burgerListe.length; i++) {
                                if (burgerListe[i] != null) {
                                    if (burgerListe[i].getBurgerName() != null) {
                                        System.out.println((i + 1) + ". " + burgerListe[i].getBurgerName());
                                    } else {
                                        //Ist gerade eine Zusammenstellung aktiv wurde diese noch nicht bestatigt und hat keinen Namen.
                                        // Daher wird diese als aktive Zusammenstellung gekennzeichnet
                                        System.out.println((i + 1) + ". Aktuelle Zusammenstellung");
                                    }
                                    for (int j = 0; j < burgerListe[i].zutatenListe.length; j++) {
                                        if (burgerListe[i].zutatenListe[j] != null) {
                                            System.out.println("\t"+burgerListe[i].zutatenListe[j].toString());//Ausgabe der verwendeten Zutaten pro Burger
                                        }
                                    }
                                    System.out.println("\n");
                                }
                            }
                        }else{
                            System.out.println("Du hast bisher keine Burger zusammengestellt.\n");
                        }
                    }else{//Abfangen Fehler: wenn zweiter Befehlsteil nicht "burger"
                        System.out.println("\nVertippt?");
                        zeigeBefehle();
                    }
                    break;

                //abschliessen eines Burgers
                case "ok":
                    if(zusammenstellungAktiv){//Natuerlich kann nur ein Burger abgeschlossen werden, wenn gerade eine Zusammenstellung aktiv ist
                        if(!burgerListe[burgerIndex].hatBroetchen()){
                            //Da ein Burger aus genau einem Broetchen bestehen muss, kann der Burger nur abgeschlossen werden, wenn eine der Zutaten ein Broetchen ist!
                            System.out.println("\nDu kannst deinen Burger erst abschliessen, wenn Du ein Br\u00f6tchen hinzugefuegt hast!\n");
                            break;
                        }
                        String burgerName;
                        do{
                            System.out.print("\nBitte gib Deiner Kreation noch einen Namen:");
                            burgerName = StaticScanner.nextString();
                        }while(burgerName.equals(""));
                        burgerListe[burgerIndex].setBurgerName(burgerName);
                        System.out.println("\nDein Burger wurde aufgenommen.\n");
                        zusammenstellungAktiv=false;//Nun ist die zusammenstellung nicht mehr aktiv.

                        if(burgerIndex<(MAX_BURGER_AMOUNT-1)){
                            burgerIndex++;
                        }else{
                            burgerIndex++;
                            System.out.println("\nDu hast nun die Maximalanzahl von "+MAX_BURGER_AMOUNT+" Burgern erreicht!");
                            System.out.println("Du kannst deine Burger nun einreichen!\n");
                        }
                    }else{
                        System.out.println("\nDu hast keine aktive Zusammenstellung. W\u00e4hle eine Zutat, um einen Burger zusammenzustellen.\n");
                    }
                    break;

                //einreichen der Burger
                case "einreichen":
                    if(zusammenstellungAktiv){//Bevor eingereicht werden kann, muss jeder Burger abgeschlossen sein
                        System.out.println("\nDu stellst gerade einen Burger zusammen.");
                        System.out.println("Beende diese Zusammenstellung erst mit ok, um deine Burger einzureichen!\n");
                    }else if(burgerListe[0]==null){//Es wurde kein Burger erstellt
                        System.out.println("\nDu hast noch keinen Burger zum einreichen erstellt!\n");
                    }else{
                        System.out.println("\nDeine Burger wurden erfolgreich eingereicht!\nHier die Rezepte zu Deinen Kreationen:\n");
                        int gesamtZubereitungsZeit=0;
                        float gesamtPreis=0;
                        //Fuer jeden erstellten Burger wird nun die Zubereitung ausgegeben
                        for(Burger burger : burgerListe){
                            if(burger!=null){
                                abgeschlossen = true;
                                System.out.println("\n=======================================================================================");
                                System.out.println(burger.toString());
                                burger.zubereiten();
                                gesamtZubereitungsZeit+=burger.getZubereitungsZeit();
                                gesamtPreis += burger.berechnePreis();

                                int minuten = burger.getZubereitungsZeit()/60;
                                int sekunden = burger.getZubereitungsZeit()%60;

                                String zubereitungszeit = minuten+" Minuten";
                                if(sekunden>0){
                                    zubereitungszeit += " und "+sekunden+" Sekunden.";
                                }

                                System.out.println("\nDie Zubereitungszeit für dieses Burger betr\u00e4gt "+zubereitungszeit);
                                System.out.println("=======================================================================================\n");
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

                //leeren der Zutaten des momentan aktiv zusammenstellenden Burgers
                case "leeren":
                    if(zusammenstellungAktiv){
                        if(burgerListe[burgerIndex].wieVoll()[0]==0){
                            System.out.println("\nDeine aktive Zusammenstellung ist bereits Leer!");
                        }else{
                            System.out.println("\nM\u00f6chtest Du wirklich deine aktive Zusammenstellung leeren?");
                            System.out.println("Best\u00e4tige mit \"Ja\". Mit jeder anderen eingabe kannst du abbrechen.");
                            System.out.print("Deine Eingabe:");
                            if(StaticScanner.nextString().toLowerCase().equals("ja")){
                                burgerListe[burgerIndex].zutatenLeeren();
                                System.out.println("\nDeine aktive Zusammenstellung wurde geleert!\n");
                            }else{
                                System.out.println("\nDeine aktive Zusammenstellung wird nicht geleert!\n");
                            }
                        }
                    }else{
                        System.out.println("\nDu hast gerade keine aktive Zusammenstellung, die geleert werden kann.\n");
                    }
                    break;

                //löschen eines bereits erstellten Burgers
                case "l\u00f6schen":
                    //Loeschen ist nur moeglich, wenn alle Burger abgeschlossen sind
                    if(zusammenstellungAktiv){
                        System.out.println("\nDu kannst keinen Burger l\u00f6schen, w\u00e4hrend eine Zusammenstellung aktiv ist!\n");
                    }else if(burgerIndex==0){
                        System.out.println("\nDu hast bisher keinen Burger angelegt.\n");
                    }else{
                        int gespeicherteBurger=0;
                        System.out.println("\nDu kannst folgende Burger l\u00f6schen:\n");
                        //Auflistung aller Burger, die erstellt wurden
                        for(int i = 0; i<burgerListe.length;i++){
                            if(burgerListe[i]!=null){
                                System.out.println((i+1)+". "+burgerListe[i].getBurgerName());
                                gespeicherteBurger++;
                            }
                        }
                        int deleteIndex = -1;
                        //Benutzereingabe der zu loeschenden Burgernummer
                        //Umwandlung Burgernummer -> Burgerindex
                        //Eingabe muss wiederholt werden, wenn index nicht existiert
                        do{
                            System.out.print("\nGebe die Nummer des zu l\u00f6schenden Burgers ein:");
                            int deleteNumber = StaticScanner.nextInt();
                            deleteIndex = deleteNumber-1;

                            if(deleteIndex<0||deleteIndex>=gespeicherteBurger){
                                System.out.println("\nDen Burger mit der Nummer "+deleteNumber+" gibt es nicht!");
                            }
                        }while(deleteIndex<0||deleteIndex>=gespeicherteBurger);

                        System.out.println("\nDu m\u00f6chtest den Burger \""+burgerListe[deleteIndex].getBurgerName()+"\" l\u00f6schen?");
                        System.out.println("Best\u00e4tige mit \"Ja\". Mit jeder anderen Eingabe brichst Du den L\u00f6schvorgang ab.");
                        System.out.print("Deine Eingabe:");


                        //Bestaetigung fuer loeschen erforderlich
                        if(StaticScanner.nextString().toLowerCase().equals("ja")){
                            System.out.println("\nDer Burger \""+burgerListe[deleteIndex].getBurgerName()+"\" wird gel\u00f6scht!\n");
                            //Burger an gewaehltem Index wird geloescht
                            //alle folgenden Burger rutschen auf und der Burgerindex wird um 1 verringert
                            for(int i = deleteIndex; i<burgerListe.length; i++){
                                if((i+1)<burgerListe.length){
                                    burgerListe[i]=burgerListe[i+1];
                                }else{
                                    burgerListe[i]=null;
                                }
                            }
                            burgerIndex--;
                        }else{
                            System.out.println("\nDer Burger \""+burgerListe[deleteIndex].getBurgerName()+"\" wird nicht gel\u00f6scht!\n");
                        }
                    }
                    break;

                //default case faengt alle anderen Eingaben ab, die keinem definierten Befehl entsprechen
                //So werden dem Nutzer bei bspw. Tippfehlern noch einmal alle Befehle angezeigt
                default:
                    System.out.println("\n"+eingabe + " ist kein g\u00fcltiger Befehl!\n");
                    zeigeBefehle();
                    break;
            }
        }//Ende der While-Schleife

        //Nutzer kann nun die erstellten Burger als Rezepte exportierten
        System.out.println("\n=========================================");
        System.out.println("\nDu hast nun noch die M\u00f6glichkeit, deine Kreationen als Rezept zu exportieren, um sie sp\u00e4ter zubereiten zu k\u00f6nnen!");
        System.out.println("Mit \"ja\" kannst Du deine Rezepte Exportieren. Mit jeder anderen Eingabe beendest Du das Programm.");
        System.out.print("\nDeine Eingabe:");

        if(StaticScanner.nextString().toLowerCase().equals("ja")){
            System.out.println("\nBitte w\u00e4hle im folgenden Fenster den Speicherort aus...");
            BurgerExporter burgerExporter = new BurgerExporter();
            System.out.println("\n"+burgerExporter.exportBurger(burgerListe));
        }else{
            System.out.println("\nDeine Rezepte werden nicht exportiert!");
        }
        System.out.println("\nVielen Dank f\u00fcr Deine Teilnahme an unserem Burgerwettbewerb \"You'll never Burger alone!\"!");
        System.out.println("Bis zum n\u00e4chsten mal!");
        System.out.println("=========================================");
    }

    /**
     * Methode zum Ausgeben der möglichen Eingabebefehle
     */
    public static void zeigeBefehle(){
        System.out.println("Hier die m\u00f6glichen Befehle:\n");
        for(int i = 0; i<BEFEHLE.length;i++){
            System.out.println(BEFEHLE[i][0]+" \t"+BEFEHLE[i][1]);
        }
    }

    /**
     * Methode zum Herausfinden ob die Eingabe ein Integer-Wert ist
     * @return ist die Eingabe ein Integer?
     * @param check der zu pruefende String
     */
    public static boolean istStringInteger(String check) {
        try {
            int wert = Integer.parseInt(check);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Methode, um die Ausgabe durch kurzes pausieren des Programmablaufs, uebersichtlicher zu machen
     * @param timeInMillis zu pausierende Zeit in millisekunden
     */
    public static void delay(int timeInMillis){
        try{
            Thread.sleep(timeInMillis);
        }catch(Exception e){
        }
    }
}