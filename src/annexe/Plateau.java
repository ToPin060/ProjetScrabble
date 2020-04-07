package annexe;

public class Plateau {

    // VARIABLES
    private int[][] plateau = new int[15][15];

    // CONSTANTES
    private int cote = 15;

    // CONSTRUCTEUR
    public Plateau(){

        this.initialisation();
    }

    // METHODES
    public void initialisation(){

        // AU DESSUS OU EGAL DE 65 : CODE ASCII DE LA LETTRE SUR LA CASE
        // 0 : VIDE
        // -1 : LETTRE DOUBLE
        // -2 : LETTRE TRIPLE
        // -3 : MOT DOUBLE
        // -4 : MOT TRIPLE
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

    public int placer(int ligne, int colonne, char lettre){
        
        // SAUVEGARDE DU L'ETAT DE LA CASE
        int save = this.plateau[ligne][colonne];

        // REMPLACEMENT DANS LE PLATEAU
        this.plateau[ligne][colonne] = (int) lettre;

        // RENVOIE DE LA SAUVEGARDE
        return save;
    }

    public int get(int ligne, int colonne) {

        // RENVOIE LA VALEUR D'UN CASE DU PLATEAU
        return this.plateau[ligne][colonne];
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

    // TEST
    public static void main(String[] args) {

        Plateau test = new Plateau();
        System.out.println(test.placer(7,7,'A'));
        System.out.println(test);
    }
}