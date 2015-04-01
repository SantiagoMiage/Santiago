package plateau;

/**
 * Created by Yannis on 24/03/2015.
 */
public class Intersection {
    boolean irrigué= false;
    int i;
    int j;

    public Intersection(int i, int j) {
        this.j = j;
        this.i = i;
    }


    public Intersection(boolean irrigué, int i, int j) {
        this.irrigué = irrigué;
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public boolean isIrrigué() {
        return irrigué;
    }

    public void setIrrigué(boolean irrigué) {
        this.irrigué = irrigué;
    }

    @Override
    public String toString() {
        return "Intersection{" +
                "source=" + irrigué +
                ", i=" + i +
                ", j=" + j +
                '}';
    }
}
