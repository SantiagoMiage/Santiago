package plateau;

/**
 * Created by Yannis on 24/03/2015.
 */
public class Intersection {
    boolean irrigue= false;
    int i;
    int j;

    public Intersection(int i, int j) {
        this.j = j;
        this.i = i;
    }


    public Intersection(boolean irrigue, int i, int j) {
        this.irrigue = irrigue;
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

    public boolean isirrigue() {
        return irrigue;
    }

    public void setirrigue(boolean irrigue) {
        this.irrigue = irrigue;
    }

    @Override
    public String toString() {
        return "Intersection{" +
                "source=" + irrigue +
                ", i=" + i +
                ", j=" + j +
                '}';
    }
}
