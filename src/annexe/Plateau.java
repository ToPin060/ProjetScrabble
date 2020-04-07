package annexe;

public class Plateau {
    // VARIABLES/CONSTANTES DE CLASSE
    private int[][] plateau = new int[15][15];

    private int cote = 15;

    // CONSTRUCTEUR
    public Plateau(){

        this.goInitialisation();
    }

    public void goInitialisation(){

        // 1 = LETTRE POSSEE // 0 = VIDE // -1 = LT // -2 = LT // -3 = MD // -4 = MT 
        int i = 0;
        this.plateau[i] = new int[] {-4,0,0,-1,0,0,0,-4,0,0,0,-1,0,0,-4}; this.plateau[this.cote - i - 1] = new int[] {-4,0,0,-1,0,0,0,-4,0,0,0,-1,0,0,-4}; i++;
        this.plateau[i] = new int[] {0,-3,0,0,0,-2,0,0,0,-2,0,0,0,-3,0}; this.plateau[this.cote - i - 1] = new int[] {0,-3,0,0,0,-2,0,0,0,-2,0,0,0,-3,0}; i++;
        this.plateau[i] = new int[] {0,0,-3,0,0,0,-1,0,-1,0,0,0,-3,0,0}; this.plateau[this.cote - i - 1] = new int[] {0,0,-3,0,0,0,-1,0,-1,0,0,0,-3,0,0}; i++;
        this.plateau[i] = new int[] {-1,0,0,-3,0,0,0,-1,0,0,0,-3,0,0,-1}; this.plateau[this.cote - i - 1] = new int[] {-1,0,0,-3,0,0,0,-1,0,0,0,-3,0,0,-1}; i++;
        this.plateau[i] = new int[] {0,0,0,0,-3,0,0,0,0,0,-3,0,0,0,0}; this.plateau[this.cote - i - 1] = new int[] {0,0,0,0,-3,0,0,0,0,0,-3,0,0,0,0}; i++;
        this.plateau[i] = new int[] {0,-2,0,0,0,-2,0,0,0,-2,0,0,0,-2,0}; this.plateau[this.cote - i - 1] = new int[] {0,-2,0,0,0,-2,0,0,0,-2,0,0,0,-2,0}; i++;
        this.plateau[i] = new int[] {0,0,-1,0,0,0,-1,0,-1,0,0,0,-1,0,0}; this.plateau[this.cote - i - 1] = new int[] {0,0,-1,0,0,0,-1,0,-1,0,0,0,-1,0,0}; i++;
        this.plateau[i] = new int[] {-4,0,0,-1,0,0,0,0,0,0,0,-1,0,0,-4};
    }

    public int place(int ligne, int colonne, char lettre){
        
        int save = this.plateau[ligne][colonne];
        this.plateau[ligne][colonne] = (int) lettre;
        return save;
    }

    @Override
    public String toString(){

        String text = "\n" + "\t";
        for (int x = 0; x < this.cote; x++){

            text += "[" + x + "]" + "\t";
        }

        text += "\n";

        for (int i = 0; i < this.cote; i++){

            text += "[" + i + "]" + "\t";

            for (int j = 0; j < this.cote; j++){
            
                text += this.plateau[i][j] + "\t";
            }

            text += "\n";
        }

        return text;
    }

    public static void main(String[] args) {

        Plateau test = new Plateau();
        System.out.println(test.place(7,7,'A'));
        System.out.println(test);
    }
}