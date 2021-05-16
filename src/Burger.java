/**
 * Klasse Burger zum Erstellen Verwalten der Burgerobjekte
 *
 * @author Emily Jung, Neele Wolf, Felix Wuest
 */
public class Burger {
    final static int MAXIMALE_ZUTATEN_ANZAHL = 12;

    protected String burgerName;
    protected int zubereitungsZeit;
    protected Zutat zutatenListe[] = new Zutat[MAXIMALE_ZUTATEN_ANZAHL];

    /**
     * Standardkonstruktor des Burgers
     * bei instanziierung wird die ZutatenListe mit null gefuellt
     */
    public Burger() {
        for (Zutat zutat : zutatenListe) {
            zutat = null;
        }
    }

    /**
     * Methode zum Berechnen der gesamten Höhe des Burgers
     * @return hoehe in cm
     */
    public float berechneHoehe() {
        float hoehe = 0;
        for (Zutat zutat : zutatenListe) {
            if (zutat != null) {
                hoehe += zutat.berechneHoehe();
            }
        }
        return (hoehe / 10);
    }

    /**
     * Methode zur Berechnung des Gesamtpreises des Burgers
     * @return Gesamtpreis in Euro
     */
    public float berechnePreis() {
        float preis = 0;
        for (Zutat zutat : zutatenListe) {
            if (zutat != null) {
                preis += zutat.getPreis();
            }
        }
        return preis;
    }

    /**
     * Methode zum Berechnen der Zubereitungszeit des Burgers
     * @return gesamtzeit in Sekunden
     */
    public int berechneZeit() {
        int gesamtZeit = 0;
        int offsetChar = 65; //Zur Auflistung mittels Buchstaben -> Umwandlung in Char: 65 = A

        for (int i = 0; i < zutatenListe.length; i++) {
            if (zutatenListe[i] != null) {
                System.out.print((char) (i + offsetChar) + " ");
                gesamtZeit += zutatenListe[i].zubereiten();
            }
        }
        return gesamtZeit;
    }

    /**
     * Methode zum Hinzufügen von Zutaten
     * Begrenzung auf genau 1 Broetchen
     * Begrenzung auf 12 Zutaten inklusive Brotchen
     * Wenn Burger kein Broetchen hat, ist als letzte Zutat nur noch ein Broetchen zulaessig!
     * @param neueZutat die zu belegende Zutat
     */
    public void belegen(Zutat neueZutat) {
        if (!hatBroetchen()) {
            if (zutatenListe[zutatenListe.length - 2] != null && zutatenListe[zutatenListe.length - 1] == null) {
                if (!(neueZutat instanceof Broetchen)) {
                    System.out.println("Die Zutat konnte nicht hinzugef\u00fcgt werden.");
                    System.out.println("Dein Burger ben\u00f6tigt noch ein Broetchen.\n");
                    return;
                }
            }
        }
        if (zutatenListe[zutatenListe.length - 1] == null) {
            if (neueZutat instanceof Broetchen && hatBroetchen()) {
                System.out.println("Der Burger hat bereits ein Br\u00f6tchen!");
                System.out.println("W\u00e4hle eine andere Zutat!\n");
            } else {
                for (int i = 0; i < zutatenListe.length; i++) {
                    if (zutatenListe[i] == null) {
                        zutatenListe[i] = neueZutat;
                        System.out.println("Die Zutat \"" + neueZutat.getName() + "\" - " + String.format("%.2f",neueZutat.getPreis()) + " € wurde hinzugef\u00fcgt!\n");
                        break;
                    }
                }
            }
        } else {
            System.out.println("Der Burger ist schon komplett belegt!\n");
        }
    }
    /**
     * Methode zum Ausgeben der Zubereitungsanweisungen aller genutzten Zutaten
     */
    public void zubereiten() {
        System.out.println("\nUnd so stellst du deinen Burger her:\n");
        this.zubereitungsZeit = berechneZeit();
    }

    /**
     * Methode zum Herausfinden ob jede Zutat vegan ist
     * @return Zutat = vegan? true oder false
     */
    public boolean istVegan() {
        boolean vegan = true;
        for (Zutat zutat : zutatenListe) {
            if (zutat != null) {
                if (!zutat.isVegan()) {
                    vegan = false;
                    break;
                }
            }
        }
        return vegan;
    }
    /**
     * Methode zum Herausfinden ob jede Zutat vegetarisch ist
     * @return Zutat = vegetarisch? true oder false
     */
    public boolean istVegetarisch() {
        boolean vegetarisch = true;
        for (Zutat zutat : zutatenListe) {
            if (zutat != null) {
                if (!zutat.isVegetarisch()) {
                    vegetarisch = false;
                    break;
                }
            }
        }
        return vegetarisch;
    }

    /**
     * Methode zum Herausfinden ob jede Zutat klassisch ist
     * @return Zutat = klassisch? true oder false
     */
    public boolean istKlassisch() {
        boolean klassisch = true;
        for (Zutat zutat : zutatenListe) {
            if (zutat != null) {
                if (!zutat.isKlassisch()) {
                    klassisch = false;
                    break;
                }
            }
        }
        return klassisch;
    }

    /**
     * Methode um den speziellen Geschmack anhand der Sauce festzulegen
     * @return String ausgabe mit Geschmack der Saucen ohne "normal"
     */
    public String hatSauce() {
        //Umwandlung in String array
        // String beginnt mit Komma, da an Burgerinfos angereiht
        String ausgabe = ", ";
        for (Zutat zutat : zutatenListe) {
            // guckt ob die zutat eine Sauce ist
            if (zutat != null && zutat instanceof Sauce) {
                // der Geschmack Sauce soll nicht mit ausgegeben werden
                if (!((Sauce) zutat).getGeschmack().equals("normal")) {
                    // dem ausgabe String wird der neue Geschmack hinzugefügt und ein komma ergänzt

                    ausgabe += ((Sauce) zutat).getGeschmack() + ", ";
                }
            }
        }
        // Um am Ende kein Komma zu haben, wird "#space" angehaengt und mit ".replace All" alle ", #space" ersetzt
        // da der String ausgabe mit ", " startet, wuerde dieser im Falle von keine Sauce leer zurueckgegeben werden
            ausgabe += "#space";
            ausgabe = ausgabe.replaceAll(", #space", "");

        return ausgabe;
    }
    /**
     * Methode um Burger einen eigenen Namen zu geben
     * @param burgerName der zu setztende Burgername
     */
    public void setBurgerName(String burgerName) {
        this.burgerName = burgerName;
    }

    /**
     * Getter für den Burgernamen, der in setBurgerName festgelegt wurde
     * @return burgername
     */
    public String getBurgerName() {
        return this.burgerName;
    }
    /**
     * Methode zum Ausgeben der Rezeptinformationen und benutzten Zutaten
     * @return Rezept: Titelzeile und Zutaten
     */
    @Override
    public String toString() {
        String sorte = "";
        if (istKlassisch()) {
            sorte += ", klassisch";
        }
        if (istVegan()) {
            sorte += ", vegan";
        } else if (istVegetarisch()) {
            sorte += ", vegetarisch";
        }

        String saucen = hatSauce();

        String titelzeile = "Rezept - " + burgerName + " (" + String.format("%.1f",berechneHoehe()) + " cm" + sorte + saucen + ") - " + String.format("%.2f",berechnePreis())+ " € \n" +
                "________________________________________________________________________________________\n \n";

        String zutaten = "Zutaten: ";

        for (Zutat zutat : zutatenListe) {
            if (zutat != null) {
                zutaten += zutat.getName() + ", ";
            }

        }
        // am Ende wird ein "#space" uebersetzt und eine passende Form zum Ueberschreiben zu erhalten
        zutaten += "#space";
        // Alle ", #space" werden ueberschrieben, sodass am Ende kein Komma steht
        zutaten = zutaten.replaceAll(", #space", "");
        String ausgabe = titelzeile + zutaten;

        return ausgabe;
    }

    /**
     * Getter für die Zubereitungszeit
     * @return zubereitungszeit in Sekunden
     */
    public int getZubereitungsZeit() {
        return zubereitungsZeit;
    }

    /**
     * Methode zur Kontrolle ob es shcon ein Broetchen gibt
     * @return boolean true oder false
     */
    public boolean hatBroetchen() {
        for (Zutat zutat : zutatenListe) {
            if (zutat instanceof Broetchen) {
                return true;
            }
        }
        return false;
    }
    /**
     * Methode um Anzahl der Zutaten auf dem Burger herauszufinden
     * @return Anzahl der benutzten Zutaten
     */
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

    /**
     * Methode um aktuelle Zutaten des Burger zu löschen
     */
    public void zutatenLeeren(){
        for(int i = 0; i<zutatenListe.length;i++){
            zutatenListe[i]=null;
        }
    }
}
