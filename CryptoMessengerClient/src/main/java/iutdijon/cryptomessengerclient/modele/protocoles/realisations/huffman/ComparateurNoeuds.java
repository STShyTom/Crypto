package iutdijon.cryptomessengerclient.modele.protocoles.realisations.huffman;

import java.util.Comparator;

/**
 * Comparateur de Noeuds : CLASSE A IMPLEMENTER
 * @author simonetma
 */
public class ComparateurNoeuds implements Comparator<Noeud> {

    @Override
    public int compare(Noeud o1, Noeud o2) {
        int res;
        if(o1.getNombreOccurences()<o2.getNombreOccurences()){
            res = -1;
        } else if(o1.getNombreOccurences()>o2.getNombreOccurences()){
            res = 1;
        } else {
            res = 0;
        }
        return res;
    }
}
