package plateau;

/**
 * Created by Yannis on 18/03/2015.
 */
public class Canal {

    boolean irrigue=false;
   // Intersection debut;
   // Intersection fin;



    public Canal(boolean irrigue, Intersection debut, Intersection fin) {
        this.irrigue = irrigue;
    //    this.debut = debut;
    //    this.fin = fin;
    }

    public boolean isIrrigue() {
        return irrigue;
    }

    public void setIrrigue(boolean irrigue) {
        this.irrigue = irrigue;
    }

 /*   public Intersection getDebut() {
        return debut;
    }

    public void setDebut(Intersection debut) {
        this.debut = debut;
    }

    public Intersection getFin() {
        return fin;
    }

    public void setFin(Intersection fin) {
        this.fin = fin;
    }
*/
    @Override
    public String toString() {
        return "Canal{" +
                "irrigue=" + irrigue;
    }
}
