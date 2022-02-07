package iutdijon.cryptomessengerclient.modele.protocoles.realisations;

import iutdijon.cryptomessengerclient.modele.messages.Message;
import iutdijon.cryptomessengerclient.modele.protocoles.Protocole;

/**
 * Classe qui gère le protocole de César
 * @author tomhu
 */
public class ProtocoleCesar extends Protocole {

    /**
     * Fonction qui effectue le chiffrement d'un message avec le protocole César
     * @param messageClair Le message envoyé par l'expéditeur
     * @return Le message crypté
     */
    @Override
    public Message chiffrer(Message messageClair) {
        String corps = messageClair.getCorpsMessage(); // Récupération du corps du message envoyé
        int cle = Integer.parseInt(this.getCle("CLE_SYMETRIQUE")); // Récupération de la clé sous forme d'entier
        String messageChiffre = "";
        
        for(int i = 0; i< corps.length(); i++){
            char c = corps.charAt(i); // On prend le caractère à la position i
            if(c >= 'A' && c <= 'Z' ){ // Si le caractère est une majuscule
                c = (char) (c + cle); // Le caractère devient le nouveau caractère crypté
                if(c > 'Z'){ // S'il dépasse Z
                    c = (char) ('A' + (c - 'Z' - 1)); // On repart de A
                }
                messageChiffre += c; // On ajoute le caractère au message
                
            } else if(c >= 'a' && c <= 'z' ){ // Si la lettre est une minuscule
                c = (char) (c + cle); // Le caractère devient le nouveau caractère crypté
                if(c > 'z'){ // S'il dépasse z
                    c = (char) ('a' + (c - 'z' - 1)); // On repart de a
                }
                messageChiffre += c; // On ajoute le caractère au message
            }else{
                messageChiffre += c; // On ajoute le caractère au message sans le modifier car ce n'est pas une lettre
            }
        }
        
        Message nouveauMessage = new Message();
        nouveauMessage.setCorpsMessage(messageChiffre);
        return nouveauMessage;
    }

    @Override
    public Message dechiffrer(Message messageChiffre) {
        String corps = messageChiffre.getCorpsMessage(); // Récupération du corps du message reçu
        int cle = 26 - Integer.parseInt(this.getCle("CLE_SYMETRIQUE")); // Récupération de la clé inverse sous forme d'entier
        String messageDechiffre = "";
        
        for(int i = 0; i< corps.length(); i++){
            char c = corps.charAt(i); // On prend le caractère à la position i
            if(c >= 'A' && c <= 'Z' ){ // Si le caractère est une majuscule
                c = (char) (c + cle); // Le caractère devient le nouveau caractère décrypté
                if(c > 'Z'){ // S'il dépasse Z
                    c = (char) ('A' + (c - 'Z' - 1)); // On repart de A
                }
                messageDechiffre += c; // On ajoute le caractère au message
                
            } else if(c >= 'a' && c <= 'z' ){ // Si la lettre est une minuscule
                c = (char) (c + cle); // Le caractère devient le nouveau caractère décrypté
                if(c > 'z'){ // S'il dépasse z
                    c = (char) ('a' + (c - 'z' - 1)); // On repart de a
                }
                messageDechiffre += c; // On ajoute le caractère au message
            }else{
                messageDechiffre += c; // On ajoute le caractère au message sans le modifier car ce n'est pas une lettre
            }
        }
        
        Message nouveauMessage = new Message();
        nouveauMessage.setCorpsMessage(messageDechiffre);
        return nouveauMessage;
    }
    
}
