package iutdijon.cryptomessengerclient.modele.protocoles.realisations.transposition;

import java.util.Comparator;

/**
 * Comparateur de couples entre eux pour le tri
 * @author tomhu
 */
public class ComparateurCouple implements Comparator<Couple>{

    /**
     * Fonction qui compare deux couples entre eux
     * @param o1 Premier couple
     * @param o2 Deuxième couple
     * @return Un nombre négatif si le premier couple est avant, un positif si le deuxième couple est avant
     */
    @Override
    public int compare(Couple o1, Couple o2) {
        char c1 = o1.getCaractere(); // Caractère du premier couple
        char c2 = o2.getCaractere(); // Caractère du second couple
        int p1 = o1.getPosition(); // Position du premier couple
        int p2 = o2.getPosition(); // Position du second couple
        
        if(c1<c2){ // Si le caractère du premier couple est inférieur à celui du deuxième
            return c1-c2; // On le place devant car c1<c2
        } else if(c1>c2){
            return c1-c2; // On le place derrière car c1>c2
        } else{ // S'ils ont le même caractère
            if(p1<p2){ // Si la position du premier est inférieure à celle du deuxième
                return p1-p2;  // On le place devant car p1<p2
            } else {
                return p1-p2; // On le place derrière car p1>p2
            }
        }
    }
    
}
