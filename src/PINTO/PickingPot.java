package PINTO;

import java.util.Hashtable;
import java.util.Random;

public class PickingPot {

    // CREATION DE VARIABLES
    private Hashtable<Character,int[]> pot = new Hashtable<Character,int[]>();
    private int n = 0;

    // CONSTRUCTEUR
    public PickingPot(){

        // INITIALISATION DU POT
        this.goInit();
    }

    // FONCTION D'INITIALISATION
    public void goInit(){
        
        // INITIALISATION DU DICO
        this.pot.put('A', new int[] {1, 9});
        this.pot.put('B', new int[] {3, 2});
        this.pot.put('C', new int[] {3, 2});
        this.pot.put('D', new int[] {2, 3});
        this.pot.put('E', new int[] {1, 15});
        this.pot.put('F', new int[] {4, 2});
        this.pot.put('G', new int[] {2, 2});
        this.pot.put('H', new int[] {4, 2});
        this.pot.put('I', new int[] {1, 8});
        this.pot.put('J', new int[] {8, 1});
        this.pot.put('K', new int[] {10, 1});
        this.pot.put('L', new int[] {1, 5});
        this.pot.put('M', new int[] {2, 3});
        this.pot.put('N', new int[] {1, 6});
        this.pot.put('O', new int[] {1, 6});
        this.pot.put('P', new int[] {3, 2});
        this.pot.put('Q', new int[] {8, 1});
        this.pot.put('R', new int[] {1, 6});
        this.pot.put('S', new int[] {1, 6});
        this.pot.put('T', new int[] {1, 6});
        this.pot.put('U', new int[] {1, 6});
        this.pot.put('V', new int[] {4, 2});
        this.pot.put('W', new int[] {10, 1});
        this.pot.put('X', new int[] {10, 1});
        this.pot.put('Y', new int[] {10, 1});
        this.pot.put('Z', new int[] {10, 1});
        this.pot.put('[', new int[] {0, 2});

        // INITIALISATION DU NOMBRES DE PIECES DANS LE POT
        this.n = 102;
    }

    // METHODE DE PIOCHE-
    public char goPick(){

        if (this.n > 0) {

            // TIRAGE D'UNE PIECE [1:this.n]
            Random r = new Random();
            int n_letter = 1 + r.nextInt(this.n); 

            // INITIALISATION DES VARIABLES
            int ind = 65;
            char letter = (char) ind;
            int s = pot.get(letter)[1];


            // RECHERCHE DE LA LETTRE PIOCHEE
            while (s < n_letter) {

                ind ++; letter = (char) ind;
                s += pot.get(letter)[1];
            }

            // MISE A JOUR DES VALEURS
            int[] aux = pot.get(letter);
            pot.put(letter, new int[] { aux[0], aux[1] - 1});
            this.n --;

            // REMISE DE LA LETTRE
            return (char) ind;
        }
        
        else {

            return '#';
        }
    }

    @Override
    public String toString(){

        String t = "\n";

        for (int i = 65; i < 65 + 27; i++){

            t += "Lettre : " + (char) i + " infos : " + pot.get((char) i)[0] + ", " + pot.get((char) i)[1] + "\n";
        }

        return t;
    }

    public static void main(String[] args) {

        PickingPot test = new PickingPot();

        for (int i = 0; i<102; i++){

            System.out.println(test.goPick());
            System.out.println(test);
        }
    }
}