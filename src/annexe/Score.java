package annexe;

public class Score {

    // VARIABLES
    public int j1; public int j2;
    public Plateau pl;
    public Pioche pi;

    // CONSTANTES

    // CONSTRUCTEUR
    public Score(Plateau plateau, Pioche pioche){

        this.initialisation(plateau, pioche);
    }

    // METHODES
    public void initialisation(Plateau plateau, Pioche pioche){

        this.j1 = 0; this.j2 = 0;
        this.pl = plateau;
        this.pi = pioche;
    }

    public void scoreInser(int[][] cases, int j) {

        int dir; int ajoutot = 0; int i;

        if (cases.length == 1) { ajoutot += scoreMot(cases[0], 0, cases, 0, 0, true, 0) + scoreMot(cases[0], 1, cases, 0, 0, true, 0); }
        else {
            if (cases[0][0]-cases[1][0] == 0) { dir = 0; }
            else { dir = 1; }

            if (cases.length == 7) { ajoutot += 50; }

            ajoutot += scoreMot(cases[0], dir, cases, 0, 0, true, 0);
            
            if (dir == 0) { dir = 1;}
            else { dir = 0;}

            for (i = 0; i < cases.length; i++) {
                
                ajoutot += scoreMot(cases[i], dir, cases, 0, 0, true, 0);
            }
        }
        
        if(j == 1) { this.j1 += ajoutot; }
        else { this.j2 += ajoutot; }
        System.out.println(this.j1 + ", " + this.j2);
    }

    public int scoreMot(int[] coord, int dir, int[][] cases,int ajout, int multi , boolean pre, int cnt) {

        while (pre) {

            //System.out.println("En cours de placement...");

            if(dir == 0) {
                if (coord[1] == 0) { pre = false; }
                else {
                    if (this.pl.get(coord[0],coord[1]-1) <= 0) { pre = false; }
                    else { coord[1]--; }
                }
            }
            else{
                if (coord[0] == 0) { pre = false; }
                else {
                    if (this.pl.get(coord[0]-1,coord[1]) <= 0) { pre = false; }
                    else { coord[0]--; }
                }
            }
        }

        //System.out.println("\n" + "Je suis placé en : " + coord[0] + ", " + coord[1] + "\n");
        if ( coord[0] < 15 && coord[1] < 15 && this.pl.get(coord[0],coord[1]) > 0) {
            cnt ++;

            int coef = 1; int i;

            for (i = 0; i < cases.length; i++){
                if (cases[i][0] == coord[0] && cases[i][1] == coord[1]) {
                    if (cases[i][2] == -1) {coef = 2;}
                    if (cases[i][2] == -2) {coef = 3;}
                    if (cases[i][2] == -3) {multi += 2;}
                    if (cases[i][2] == -4) {multi += 3;}
                }
            }

            int x = coord[0]; int y = coord[1];

            if (dir == 0) {coord[1]++;}
            else {coord[0]++;}

            return scoreMot(coord, dir, cases, ajout += this.pi.getVal((char) (this.pl.get(x,y) - 32)) * coef, multi, false, cnt);
        }

        else {
            if (cnt < 2 ){ return 0; }
            if (multi != 0) { return ajout * multi;}
            else { return ajout; }
        }
    }

    // TEST

}