package annexe;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class Dictionnaire extends Hashtable<Integer,String> {

    // VARIABLES
    // vide

    // CONSTANTES
    // vide

    // CONSTRUCTEUR
    public Dictionnaire(){

        this.initialisation();
    }

    // METHODES
    public String initialisation() {


        // OUVERTURE DU DICTIONNAIRE
        try {
            BufferedReader in = new BufferedReader(new FileReader("ressources/dictionnaire.txt"));
           
            String line;

            // AJOUT DE CHAQUE MOT DANS LA HASTABLE
            while ((line = in.readLine()) != null) {
    
                   this.put(line.hashCode(),line);
                }
                
            // FERMETURE DU FICHIER
            in.close();

            return "ok";
        }
        catch (IOException e){
            return "Exception !";
        }
    }

    // TEST
    public static void main(String[] args) throws IOException{

        Dictionnaire test = new Dictionnaire();
        System.out.println(test.containsValue("vait"));
    }
}