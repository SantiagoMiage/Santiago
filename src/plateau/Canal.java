package plateau;

/**
 * Created by Yannis on 18/03/2015.
 */
public class Canal {

    boolean irrigue=false;
    int xdeb;
    int ydeb;
    int xfin;
    int yfin;


    public Canal(boolean irrigue, int xdeb, int ydeb, int xfin, int yfin) {
        this.irrigue = irrigue;
        this.xdeb = xdeb;
        this.ydeb = ydeb;
        this.xfin = xfin;
        this.yfin = yfin;
    }

    public boolean isIrrigue() {
        return irrigue;
    }

    public void setIrrigue(boolean irrigue) {
        this.irrigue = irrigue;
    }


    @Override
    public String toString() {
        return "Canal{" +
                "irrigue=" + irrigue +
                ", debut = (" + xdeb +
                "," + ydeb +
                "), fin = (" + xfin +
                "," + yfin +
                ")}";
    }
}
