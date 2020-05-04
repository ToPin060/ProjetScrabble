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

        int i, v, dir, ajoutot = 0;
        int[] sav = new int[] {cases[0][0], cases[0][1]};

        if (cases.length == 1) {
            for (i=0;i<2;i++){
                v = scoreMot(sav, i, cases, 0, 0, true, 0);
                sav = new int[] {cases[0][0], cases[0][1]};
                ajoutot += v;
            }
        }

        else {
            if (cases[0][0]-cases[1][0] == 0) { dir = 0; }
            else { dir = 1; }
            if (cases.length == 7) { ajoutot += 50; }

            v = scoreMot(sav, dir, cases, 0, 0, true, 0);
            ajoutot += v;

            if (dir == 0) { dir = 1;}
            else { dir = 0;}
            for (i = 0; i < cases.length; i++) {
                v = scoreMot(cases[i], dir, cases, 0, 0, true, 0);
                ajoutot += v;
                System.out.println("!dir " + v);
            }
        }
        if(j == 1) { this.j1 += ajoutot; }
        else { this.j2 += ajoutot; }
    }

    public int scoreMot(int[] coord, int dir, int[][] cases,int ajout, int multi , boolean pre, int cnt) {

        while (pre) {
            if (coord[0] == 0 || coord[1] == 0) { pre = false; }
            else {
                if (dir == 0 && this.pl.get(coord[0], coord[1] - 1) > 0){ coord[1]--; }
                else if ( dir == 1 && this.pl.get(coord[0] - 1, coord[1]) > 0 ) { coord[0]--; }
                else { pre = false; }
            }
        }

        if (coord[0] < 15 && coord[1] < 15 && this.pl.get(coord[0],coord[1]) > 0){
            int i, coef = 1, x = coord[0], y = coord[1], v = this.pi.getVal((char) (this.pl.get(x,y) - 32));
            cnt++;
            for (i = 0; i < cases.length; i ++){
                if (cases[i][0] == coord[0] && cases[i][1] == coord[1]){
                    if (cases[i][2] == -1) { coef = 2; }
                    if (cases[i][2] == -2) { coef = 3; }
                    if (cases[i][2] == -3) { multi += 2; }
                    if (cases[i][2] == -4) { multi += 3; }
                }
            }
            if (dir == 0) {coord[1]++;}
            else {coord[0]++;}
            return scoreMot(coord, dir, cases, ajout += (v * coef), multi, false, cnt);
        } 
        else {
            if (cnt < 2 ){ return 0; }
            else if (multi != 0) { return ajout * multi;}
            else { return ajout; }
        }
    }
}