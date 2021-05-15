import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

public class BurgerExporter {

    public String exportBurger(Burger[] burgerListe){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showSaveDialog(null);

        if(result == JFileChooser.APPROVE_OPTION){
            String speicherPfad = fileChooser.getCurrentDirectory().toString();
            speicherPfad+="/Burger Rezepte/";
            File directory = new File(speicherPfad);
            if(! directory.exists()){
                directory.mkdir();
            }
            for(Burger burger : burgerListe) {
                if (burger != null) {
                    try {
                        BufferedWriter bw = new BufferedWriter(new FileWriter(directory + "/" + burger.getBurgerName() + ".txt"));
                        bw.write(burger.toString()); //Hier fehlt noch das genaue Rezept
                        bw.close();

                    } catch (Exception e) {
                        return e.toString();
                    }
                }
            }
            return "Deine Rezepte wurden Erfolgreich unter "+directory.toString()+" gespeichert!";
        }else if(result == JFileChooser.CANCEL_OPTION){
            return "Der Export wurde abgebrochen!";
        }else{
            return "Ein unbekannter Fehler ist beim abspeichern aufgetreten.";
        }
    }


}
