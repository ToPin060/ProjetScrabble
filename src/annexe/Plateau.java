package annexe;

import java.io.IOException;

public class Plateau {

    // VARIABLES
    private int[][] plateau = new int[15][15];

    // CONSTANTES
    private int cote = 15;
    Dictionnaire dico;

    // CONSTRUCTEUR
    public Plateau(){

        this.initialisation(new Dictionnaire());
    }

    // METHODES
    public void initialisation(Dictionnaire d){

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
        this.plateau[i] = new int[] {-4,0,0,-1,0,0,0,-3,0,0,0,-1,0,0,-4};

        this.dico = d;    
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

    public boolean verif(int[][] cases) {

        int dir; int i; boolean ok = true;

        if (cases.length == 1) {ok = getMot(cases[0], 0, "", true)[0] && getMot(cases[0], 1, "", true)[0];}
        else {
            if (cases[0][0]-cases[1][0] == 0) { dir = 0; }
            else { dir = 1; }

            ok &= getMot(cases[0], dir, "", true)[0];
            
            if (dir == 0) { dir = 1;}
            else { dir = 0;}

            for (i = 0; i < cases.length; i++) {
                
                boolean save[] = getMot(cases[i], dir, "", false);
                if (!save[1]) {
                    
                    ok &= save[0];
                }
            }
        }
        
        return ok;
    }

    public boolean[] getMot(int[] coord, int dir,String mot, boolean pre) {

        while (pre) {

            //System.out.println("En cours de placement...");

            if(dir == 0) {
                if (coord[1] == 0) { pre = false; }
                else {
                    if (this.get(coord[0],coord[1]-1) <= 0) { pre = false; }
                    else { coord[1]--; }
                }
            }
            else{
                if (coord[0] == 0) { pre = false; }
                else {
                    if (this.get(coord[0]-1,coord[1]) <= 0) { pre = false; }
                    else { coord[0]--; }
                }
            }
        }

        //System.out.println("\n" + "Je suis placé en : " + coord[0] + ", " + coord[1] + "\n");

        if ( coord[0] < 15 && coord[1] < 15 && this.get(coord[0],coord[1]) > 0) {
            
            int x = coord[0]; int y = coord[1];

            mot += (char) this.get(x,y);

            if (dir == 0) {coord[1]++;}
            else {coord[0]++;}

            return getMot(coord, dir, mot, false);
        }

        else {
            
            return new boolean[] {this.dico.containsValue(mot),mot.length() <= 1};
        }
    }

    // TEST

}