package annexe;

import java.util.Hashtable;
import java.util.Random;

public class Pioche {

    // VARIABLES/CONSTANTES DE CLASSE
    private Hashtable<Character,int[]> sac= new Hashtable<Character,int[]>();
    private int n_pieces = 0;

    // CONSTRUCTEUR
    public Pioche(){

        // INITIALISATION DU POT
        this.goInitialisation();
    }

    // FONCTION D'INITIALISATION
    public void goInitialisation(){
        
        // INITIALISATION DU DICO
        this.sac.put('A', new int[] {1, 9});
        this.sac.put('B', new int[] {3, 2});
        this.sac.put('C', new int[] {3, 2});
        this.sac.put('D', new int[] {2, 3});
        this.sac.put('E', new int[] {1, 15});
        this.sac.put('F', new int[] {4, 2});
        this.sac.put('G', new int[] {2, 2});
        this.sac.put('H', new int[] {4, 2});
        this.sac.put('I', new int[] {1, 8});
        this.sac.put('J', new int[] {8, 1});
        this.sac.put('K', new int[] {10, 1});
        this.sac.put('L', new int[] {1, 5});
        this.sac.put('M', new int[] {2, 3});
        this.sac.put('N', new int[] {1, 6});
        this.sac.put('O', new int[] {1, 6});
        this.sac.put('P', new int[] {3, 2});
        this.sac.put('Q', new int[] {8, 1});
        this.sac.put('R', new int[] {1, 6});
        this.sac.put('S', new int[] {1, 6});
        this.sac.put('T', new int[] {1, 6});
        this.sac.put('U', new int[] {1, 6});
        this.sac.put('V', new int[] {4, 2});
        this.sac.put('W', new int[] {10, 1});
        this.sac.put('X', new int[] {10, 1});
        this.sac.put('Y', new int[] {10, 1});
        this.sac.put('Z', new int[] {10, 1});
        this.sac.put('[', new int[] {0, 2});

        // INITIALISATION DU NOMBRES DE PIECES DANS LE POT
        this.n_pieces = 102;
    }

    // METHODE DE PIOCHE-
    public char goPiocher(){

        if (this.n_pieces > 0) {

            // TIRAGE D'UNE PIECE [1:this.n]
            Random r = new Random();
            int num_lettre = 1 + r.nextInt(this.n_pieces); 

            // INITIALISATION DES VARIABLES
            int indice = 65;
            char lettre = (char) indice;
            int somme = sac.get(lettre)[1];


            // RECHERCHE DE LA LETTRE PIOCHEE
            while (somme < num_lettre) {

                indice ++; lettre = (char) indice;
                somme += sac.get(lettre)[1];
            }

            // MISE A JOUR DES VALEURS
            int[] auxiliaire = sac.get(lettre);
            sac.put(lettre, new int[] { auxiliaire [0], auxiliaire [1] - 1});
            this.n_pieces --;

            // REMISE DE LA LETTRE
            return (char) indice;
        }
        
        else {

            return '#';
        }
    }

    @Override
    public String toString(){

        String inventaire = "\n";

        for (int i = 65; i < 65 + 27; i++){

            inventaire += "Lettre : " + (char) i + " infos : " + sac.get((char) i)[0] + ", " + sac.get((char) i)[1] + "\n";
        }

        return inventaire;
    }

    public static void main(String[] args) {

        Pioche test = new Pioche();

        for (int i = 0; i<102; i++){

            System.out.println(test.goPiocher());
            System.out.println(test);
        }
    }
}