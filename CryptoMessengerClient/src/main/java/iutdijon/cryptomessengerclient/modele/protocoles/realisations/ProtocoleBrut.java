package iutdijon.cryptomessengerclient.modele.protocoles.realisations;

import iutdijon.cryptomessengerclient.modele.messages.Message;
import iutdijon.cryptomessengerclient.modele.protocoles.Protocole;

/**
 * Classe correspondant au protocole brut pour l'envoi de messages
 * @author th299374
 */
public class ProtocoleBrut extends Protocole {

    @Override
    public Message chiffrer(Message messageClair) {
        return messageClair;
    }

    @Override
    public Message dechiffrer(Message messageChiffre) {
        return messageChiffre;
    }
    
}
