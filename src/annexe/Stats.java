package annexe;

import java.io.Serializable;

public class Stats implements Serializable {

    private static final long serialVersionUID = 1L;
    public Integer j1;
    public Integer j2;
    public Integer tours;

    public Stats(Integer j1, Integer j2, Integer tours) {
        this.j1 = j1;
        this.j2 = j2;
        this.tours = tours;
    }

    public Integer getJ1() {
        return this.j1;
    }

    public Integer getJ2() {
        return this.j2;
    }

    public Integer getTours() {
        return this.tours;
    }

    @Override 
    public String toString() {
        return this.j1+" "+this.j2+" "+this.tours;
    }
}