/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package santiago;


public class Plateau {
    Parcelle[][] tabParcelle = new Parcelle[6][8];

    public Plateau(Parcelle[][] tabParcelle) {
        this.tabParcelle = tabParcelle;
    }
            
    public void initialisation() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                 this.tabParcelle[i][j]= new Parcelle();
            }
        }
    }
    
    
    
    
    
    
    
}
    
    
    
    
    
    
    
    

