package iutdijon.cryptomessengerclient.modele.protocoles.realisations;

import iutdijon.cryptomessengerclient.modele.messages.Message;
import iutdijon.cryptomessengerclient.modele.protocoles.Protocole;

/**
 * Classe qui gère le protocole par substitution
 * @author tomhu
 */
public class ProtocoleSubstitution extends Protocole{

    /**
     * Méthode qui chiffre le message avec substitution
     * @param messageClair Le message de l'expéditeur
     * @return 
     */
    @Override
    public Message chiffrer(Message messageClair) {
        String corps = messageClair.getCorpsMessage(); // Récupération du corps du message envoyé
        String cle = this.getCle("CLE_SYMETRIQUE"); // Récupération de la clé sous forme de chaîne
        String messageChiffre = "";
        int position; // Position ascii d'un caractère
        
        
        for(int i = 0; i< corps.length(); i++){
            char c = corps.charAt(i); // On prend le caractère à la position i
            if(c >= 'A' && c <= 'Z' ){ // Si le caractère est une majuscule
                position = c - 65; // On enlève 65 au code ASCII pour trouver la position dans l'alphabet du caractère
                c = cle.charAt(position); // Le caractère devient le caractère présent à sa position dans l'alphabet dans la clé
                messageChiffre += c; // On ajoute le caractère au message
                
            } else if(c >= 'a' && c <= 'z' ){ // Si la lettre est une minuscule
                position = c - 97; // On enlève 97 au code ASCII pour trouver la position dans l'alphabet du caractère
                c = cle.charAt(position); // Le caractère devient le caractère présent à sa position dans l'alphabet dans la clé
                c = Character.toLowerCase(c); // On repasse la lettre en minuscule           
                messageChiffre += c; // On ajoute le caractère au message
            }else{
                messageChiffre += c; // On ajoute le caractère au message sans le modifier car ce n'est pas une lettre
            }
        }
        
        Message nouveauMessage = new Message();
        nouveauMessage.setCorpsMessage(messageChiffre);
        return nouveauMessage;
    }

    /**
     * Méthode qui déchiffre le message avec substitution
     * @param messageChiffre Le message chiffré envoyé par l'expéditeur
     * @return Le message déchiffré
     */
    @Override
    public Message dechiffrer(Message messageChiffre) {
        String corps = messageChiffre.getCorpsMessage(); // Récupération du corps du message envoyé chiffré
        String cle = this.getCle("CLE_SYMETRIQUE"); // Récupération de la clé sous forme de chaîne
        String messageDechiffre = "";
        int position; // Position d'un caractère
        
        
        for(int i = 0; i< corps.length(); i++){
            char c = corps.charAt(i); // On prend le caractère à la position i
            if(c >= 'A' && c <= 'Z' ){ // Si le caractère est une majuscule
                position = cle.indexOf(c); // On récupère la position du caractère dans la clé
                c = (char) (position + 65); // Le caractère est celui placé au code ascii de la posiiton + 65
                messageDechiffre += c; // On ajoute le caractère au message
                
            } else if(c >= 'a' && c <= 'z' ){ // Si la lettre est une minuscule
                c = Character.toUpperCase(c); // On passe la lettre en majucule pour la recherche
                position = cle.indexOf(c); // On récupère la position du caractère dans la clé
                c = (char) (position + 97); // Le caractère est celui placé au code ascii de la posiiton + 97
                c = Character.toLowerCase(c); // On repasse la lettre en minuscule           
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
