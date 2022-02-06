package iutdijon.cryptomessengerclient.modele.protocoles;

import iutdijon.cryptomessengerclient.modele.protocoles.realisations.ProtocoleBrut;
import iutdijon.cryptomessengerclient.modele.protocoles.realisations.ProtocoleCesar;


/**
 * Fabrique des protocoles
 * @author simonetma
 */
public class FabriqueProtocole {
    
    /**
     * Fabrique un protocole
     * @param nom nom du protocole
     * @return le protocole
     */
    public static Protocole create(NomProtocole nom) {
        Protocole protocole = null;
        switch(nom) {
            case BRUT :
                protocole = new ProtocoleBrut();
                break;
            case CESAR :
                protocole = new ProtocoleCesar();
                break;
        }
        return protocole;
    }
    
}
