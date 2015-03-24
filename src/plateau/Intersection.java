package plateau;

/**
 * Created by Yannis on 24/03/2015.
 */
public class Intersection {
    boolean source=false;
    int i;
    int j;

    public Intersection(int j, int i) {
        this.j = j;
        this.i = i;
    }

    public Intersection(boolean source, int i, int j) {
        this.source = source;
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

    public boolean isSource() {
        return source;
    }

    public void setSource(boolean source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "Intersection{" +
                "source=" + source +
                ", i=" + i +
                ", j=" + j +
                '}';
    }
}
