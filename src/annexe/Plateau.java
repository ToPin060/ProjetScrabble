package annexe;

import java.util.ArrayList;

import mvc.VuePiece;

public class Plateau {

    // VARIABLES
    public int[][] plateau = new int[15][15];

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

        //System.out.println(this);

        this.dico = d;    
    }

    public int placer(int ligne, int colonne, int lettre){
        
        // SAUVEGARDE DU L'ETAT DE LA CASE
        int save = this.plateau[ligne][colonne];

        // REMPLACEMENT DANS LE PLATEAU
        this.plateau[ligne][colonne] = lettre;

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

    /*
    public boolean verif(ArrayList<VuePiece> motCourant) {

        int dir; int i; boolean ok = true;

        if (motCourant.length == 1) {
            boolean haut,bas,gauche,droite;

            haut = this.get(motCourant[0][0] - 1, motCourant[0][1]) > 0;
            gauche = this.get(motCourant[0][0], motCourant[0][1] - 1) > 0;
            bas = this.get(motCourant[0][0] + 1, motCourant[0][1]) > 0;
            droite = this.get(motCourant[0][0], motCourant[0][1] + 1) > 0;

            if ( (haut || bas) && (gauche || droite) ){
                
                return getMot(motCourant[0], 0, "", false)[0] & getMot(motCourant[0], 1, "", false)[0];
            }
            else {
                if (haut || bas) { return  getMot(motCourant[0], 1, "", false)[0]; }
                else { return  getMot(motCourant[0], 1, "", false)[0]; }
            }
        }
        else {
            if (motCourant[0][0]-motCourant[1][0] == 0) { dir = 0; }
            else { dir = 1; }

            ok &= getMot(motCourant[0], dir, "", true)[0];
            
            if (dir == 0) { dir = 1;}
            else { dir = 0;}

            for (i = 0; i < motCourant.length; i++) {
                
                boolean save[] = getMot(motCourant[i], dir, "", false);
                if (!save[1]) {
                    
                    ok &= save[0];
                }
            }
        }
        
        return ok;
    }
*/
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
            if (mot.length() > 1){
                System.out.println(mot);
            }
            return new boolean[] {this.dico.containsValue(mot),mot.length() <= 1};
        }
    }

    public boolean verif(ArrayList<VuePiece> cases) {

        int dir; int i; boolean ok = true;

        if (cases.size() == 1) {
            boolean haut,bas,gauche,droite;

            haut = this.get(cases.get(0).lig - 1, cases.get(0).col) > 0;
            gauche = this.get(cases.get(0).lig, cases.get(0).col - 1) > 0;
            bas = this.get(cases.get(0).lig + 1, cases.get(0).col) > 0;
            droite = this.get(cases.get(0).lig, cases.get(0).col + 1) > 0;

            if ( (haut || bas) && (gauche || droite) ){
                
                System.out.println("2cotes");
                return getMot(new int[] {cases.get(0).lig,cases.get(0).col}, 0, "", true)[0] & getMot(new int[] {cases.get(0).lig,cases.get(0).col}, 1, "", true)[0];
            }
            else {
                if (haut || bas) {
                    System.out.println("vertical");
                    return  getMot(new int[] {cases.get(0).lig,cases.get(0).col}, 1, "", true)[0];
                }
                if (gauche || droite) {
                    System.out.println("horizontal");
                    return  getMot(new int[] {cases.get(0).lig,cases.get(0).col}, 0, "", true)[0];
                }
            }
        }
        else {
            if (cases.get(0).lig - cases.get(1).lig == 0) { dir = 0; }
            else { dir = 1; }

            ok &= getMot(new int[] {cases.get(0).lig,cases.get(0).col}, dir, "", true)[0];
            
            if (dir == 0) { dir = 1;}
            else { dir = 0;}

            for (i = 0; i < cases.size(); i++) {
                
                boolean save[] = getMot(new int[] {cases.get(i).lig,cases.get(i).col}, dir, "", false);
                if (!save[1]) {
                    
                    ok &= save[0];
                }
            }
        }
        
        return ok;
    }

    public boolean verifierPlateau() {
        ArrayList<String> horz = new ArrayList<String>();
        ArrayList<String> vert = new ArrayList<String>();

        for (int i = 0; i < this.plateau.length; i++) {
            String s1 = "";
            String s2 = "";

            for (int j = 0; j < this.plateau.length; j++) {
                //horizontaux
                if (this.plateau[i][j] <= 0 && s1.length()>1) {
                    horz.add(s1);
                    s1 = "";
                }

                else if (this.plateau[i][j] >= 97 && this.plateau[i][j] <= 122) {
                    String str1 = new Character((char) this.plateau[i][j]).toString();
                    s1 += str1;
                }

                else {
                    s1 = "";
                }

                //verticaux
                if (this.plateau[j][i] <= 0 && s2.length()>1) {
                    vert.add(s2);
                    s2 = "";
                }

                else if (this.plateau[j][i] >= 97 && this.plateau[j][i] <= 122) {
                    String str2 = new Character((char) this.plateau[j][i]).toString();
                    s2 += str2;
                }

                else {
                    s2 = "";
                }
            }

            if (s1.length()>1) {
                horz.add(s1);
            }

            if (s2.length()>1) {
                vert.add(s2);
            }

            s1 = "";
            s2 = "";
        }

        //System.out.println(horz);
        //System.out.println(vert);

        for (int i = 0; i < horz.size(); i++) {
            if (!(this.dico.contains(horz.get(i)))) {
                return false;
            }
        }

        for (int i = 0; i < vert.size(); i++) {
            if (!(this.dico.contains(vert.get(i)))) {
                return false;
            }
        }

        return true;
    }

    // TEST

}