import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

/**
 * Die Klasse BurgerExporter dient dazu, bei Bedarf die rezepte in einer Textdatei abzuspeichern.
 * @author Emily Jung, Neele Wolf, Felix Wuest
 */
public class BurgerExporter {

    /**
     * exportBurger erstellt fuer jeden Burger der BurgerListe eine Textdatei mit passenden Namen und darin dem dazugehoerigen Rezept.
     * Der Benutzer wird mit einem filechooser dazu aufgefordert einen Dateipfad auszuwaehlen.
     * In diesem Verzeichnis wird automatisch ein Ordner "Burger Rezepte" mit den einzelnen Rezepten erstellt.
     *
     * @param burgerListe der Array der erstellten Burger
     * @return Bestaetigung / Fehlermeldung ueber Verlauf des speicherns
     */
    public String exportBurger(Burger[] burgerListe){
        //JFileChooser, um Zielverzeichnis einfach auszuwaehlen
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showSaveDialog(null);

        //Wenn Fenster bestaetigt wurde, weiter zum abspeichern
        if(result == JFileChooser.APPROVE_OPTION){
            String speicherPfad = fileChooser.getCurrentDirectory().toString();
            speicherPfad+="/Burger Rezepte/";
            File directory = new File(speicherPfad);
            //Ueberpruefung, ob Pfad existiert (wegen Rezepte Ordner)
            if(!directory.exists()){
                //Wenn nicht, erstelle noetiges Verzeichnis
                directory.mkdir();
            }
            //Fuer jeden Burger, der nicht null ist, also auch wirklich erstellt wurde, wird ein Rezept gespeichert
            for(Burger burger : burgerListe) {
                if (burger != null) {
                    try {
                        BufferedWriter bw = new BufferedWriter(new FileWriter(directory + "/" + burger.getBurgerName() + ".txt"));
                        //Allgemeine Kopfzeile des Burgers
                        bw.write(burger.toString()+"\n\n");

                        //Zubereitungshinweise fuer jede ausgewaehlte Zutat des Burgers
                        for(Zutat zutat : burger.zutatenListe){
                            if(zutat!=null){
                                bw.write(zutat.rezept()+"\n");
                            }
                        }
                        int minuten = burger.getZubereitungsZeit()/60;
                        int sekunden = burger.getZubereitungsZeit()%60;

                        String zubereitungszeit = minuten+" Minuten";
                        if(sekunden>0){
                            zubereitungszeit += " und "+sekunden+" Sekunden.";
                        }
                        //Zubereitungszeit
                        bw.write("\n=======================================================================================\n");
                        bw.write("Die Zubereitungszeit für dieses Burger betr\u00e4gt "+zubereitungszeit);
                        bw.close();
                    } catch (Exception e) {
                        return e.toString();
                    }
                }
            }
            return "Deine Rezepte wurden Erfolgreich unter "+directory.toString()+" gespeichert!";
            //Wenn Fenster gecancelt wurde, rueckmeldung abbruch
        }else if(result == JFileChooser.CANCEL_OPTION){
            return "Der Export wurde abgebrochen!";
        }else{
            return "Ein unbekannter Fehler ist beim abspeichern aufgetreten.";
        }
    }
}
