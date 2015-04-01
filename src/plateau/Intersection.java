package plateau;

/**
 * Created by Yannis on 24/03/2015.
 */
public class Intersection {
    Canal source;
    int i;
    int j;

    public Intersection(int i, int j) {
        this.j = j;
        this.i = i;
    }


    public Intersection(Canal source, int i, int j) {
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

    public Canal isSource() {
        return source;
    }

    public void setSource(Canal source) {
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
