package annexe;

import java.util.Hashtable;
import java.util.Random;

public class Pioche extends Hashtable<Character,int[]> {

    // VARIABLES
    public int n_pieces = 0;

    // CONSTANTES
    // vide

    // CONSTRUCTEUR
    public Pioche(){

        this.initialisation();
    }

    // METHODES
    public void initialisation(){
        
        this.put('A', new int[] {1, 9});
        this.put('B', new int[] {3, 2});
        this.put('C', new int[] {3, 2});
        this.put('D', new int[] {2, 3});
        this.put('E', new int[] {1, 15});
        this.put('F', new int[] {4, 2});
        this.put('G', new int[] {2, 2});
        this.put('H', new int[] {4, 2});
        this.put('I', new int[] {1, 8});
        this.put('J', new int[] {8, 1});
        this.put('K', new int[] {10, 1});
        this.put('L', new int[] {1, 5});
        this.put('M', new int[] {2, 3});
        this.put('N', new int[] {1, 6});
        this.put('O', new int[] {1, 6});
        this.put('P', new int[] {3, 2});
        this.put('Q', new int[] {8, 1});
        this.put('R', new int[] {1, 6});
        this.put('S', new int[] {1, 6});
        this.put('T', new int[] {1, 6});
        this.put('U', new int[] {1, 6});
        this.put('V', new int[] {4, 2});
        this.put('W', new int[] {10, 1});
        this.put('X', new int[] {10, 1});
        this.put('Y', new int[] {10, 1});
        this.put('Z', new int[] {10, 1});
        this.put('[', new int[] {0, 2});

        this.n_pieces = 102;
    }

    public char piocher(){

        // SI LE SAC EST PAS VIDE
        if (this.n_pieces > 0) {

            // TIRAGE ALEATOIRE D'UNE PIECE
            Random r = new Random();
            int num_lettre = 1 + r.nextInt(this.n_pieces); 

            // INITIALISATION DES VARIABLES
            int indice = 65; char lettre = (char) indice; int somme = this.get(lettre)[1];

            // RECHERCHE DE LA LETTRE
            while (somme < num_lettre) {

                indice ++; lettre = (char) indice;
                somme += this.get(lettre)[1];
            }

            // MISE A JOUR DES VARIABLES DE CLASSE
            int[] auxiliaire = this.get(lettre);
            this.put(lettre, new int[] { auxiliaire [0], auxiliaire [1] - 1});
            this.n_pieces --;

            // REMISE DE LA LETTRE
            return (char) indice;
        }
        
        else {

            // RIEN DANS LA PIOCHE
            return '#';
        }
    }

    public int getVal(char l){

        return this.get(l)[0];
    }

    public void ajouter(char lettre) {
        this.get(lettre)[1]++;
        this.n_pieces++;
    }

    @Override
    public String toString(){

        String txt = "\n";

        for (int i = 65; i < 65 + 27; i++){

            txt += "Lettre : " + (char) i + " infos : " + this.get((char) i)[0] + ", " + this.get((char) i)[1] + "\n";
        }

        return txt;
    }

    // TEST
    public static void main(String[] args) {

        Pioche test = new Pioche();

        for (int i = 0; i<102; i++){

            System.out.println(test.piocher());
            System.out.println(test);
        }
    }
}