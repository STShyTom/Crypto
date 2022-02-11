package iutdijon.cryptomessengerclient.modele.protocoles.realisations;

import iutdijon.cryptomessengerclient.modele.messages.Message;
import iutdijon.cryptomessengerclient.modele.protocoles.Protocole;

/**
 * Classe qui gère le protocole de Vigenere
 * @author th299374
 */
public class ProtocoleVigenere extends Protocole{

    /**
     * Méthode qui chiffre le message avec Vigenere
     * @param messageClair Le message de l'expéditeur
     * @return le message chiffré 
     */
    @Override
    public Message chiffrer(Message messageClair) {
        String corps = messageClair.getCorpsMessage(); // Récupération du corps du message envoyé
        String ancienneCle = this.getCle("CLE_SYMETRIQUE"); // Récupération de la clé sous forme de chaîne
        String cle = repliquerCle(corps, ancienneCle); // On réplique la clé
        String messageChiffre = "";
        
        for(int i = 0; i< corps.length(); i++){
            char caractereMessage = corps.charAt(i); // On prend le caractère du message à la position i
            char caractereCle = cle.charAt(i); // On prend le caractère de la clé à la position i
            if(caractereMessage >= 'A' && caractereMessage <= 'Z' ){ // Si le caractère du message est une majuscule
                caractereMessage = (char) (caractereMessage + caractereCle - 'A'); // Le caractère devient le nouveau caractère crypté
                if(caractereMessage > 'Z'){ // S'il dépasse Z
                    caractereMessage = (char) ('A' + (caractereMessage - 'Z' - 1)); // On repart de A
                }
                messageChiffre += caractereMessage; // On ajoute le caractère au message
                
            } else if(caractereMessage >= 'a' && caractereMessage <= 'z' ){ // Si la lettre est une minuscule
                caractereMessage = (char) (caractereMessage + caractereCle - 'A'); // Le caractère devient le nouveau caractère crypté
                caractereMessage = Character.toLowerCase(caractereMessage); // On passe le caractère en minuscule
                if(caractereMessage > 'z'){ // S'il dépasse z
                    caractereMessage = (char) ('a' + (caractereMessage - 'z' - 1)); // On repart de a
                }
                messageChiffre += caractereMessage; // On ajoute le caractère au message
            }else{
                messageChiffre += caractereMessage; // On ajoute le caractère au message sans le modifier car ce n'est pas une lettre
            }
        }
        
        Message nouveauMessage = new Message();
        nouveauMessage.setCorpsMessage(messageChiffre);
        return nouveauMessage;
    }

    /**
     * Méthode de déchiffrement d'un message avec le protocole de Vigenère
     * @param messageChiffre Le message chiffré précédemment
     * @return Le message initial déchiffré
     */
    @Override
    public Message dechiffrer(Message messageChiffre) {
        String corps = messageChiffre.getCorpsMessage(); // Récupération du corps du message chiffré
        String ancienneCle = this.getCle("CLE_SYMETRIQUE"); // Récupération de la clé sous forme de chaîne
        String cle = repliquerCle(corps, ancienneCle); // On réplique la clé
        String messageDechiffre = "";
        
        for(int i = 0; i< corps.length(); i++){
            char caractereMessage = corps.charAt(i); // On prend le caractère du message à la position i
            char caractereCle = (char) (156- cle.charAt(i)); // On prend le caractère de la clé à la position i
            if(caractereMessage >= 'A' && caractereMessage <= 'Z' ){ // Si le caractère du message est une majuscule
                caractereMessage = (char) (caractereMessage + caractereCle - 'A'); // Le caractère devient le nouveau caractère crypté
                if(caractereMessage > 'Z'){ // S'il dépasse Z
                    caractereMessage = (char) ('A' + (caractereMessage - 'Z' - 1)); // On repart de A
                }
                messageDechiffre += caractereMessage; // On ajoute le caractère au message
                
            } else if(caractereMessage >= 'a' && caractereMessage <= 'z' ){ // Si la lettre est une minuscule
                caractereMessage = (char) (caractereMessage + caractereCle - 'A'); // Le caractère devient le nouveau caractère crypté
                caractereMessage = Character.toLowerCase(caractereMessage); // On passe le caractère en minuscule
                if(caractereMessage > 'z'){ // S'il dépasse z
                    caractereMessage = (char) ('a' + (caractereMessage - 'z' - 1)); // On repart de a
                }
                messageDechiffre += caractereMessage; // On ajoute le caractère au message
            }else{
                messageDechiffre += caractereMessage; // On ajoute le caractère au message sans le modifier car ce n'est pas une lettre
            }
        }
        
        Message nouveauMessage = new Message();
        nouveauMessage.setCorpsMessage(messageDechiffre);
        return nouveauMessage;
    }
    
    /**
     * Méthode permettant de répliquer la clé pour la mettre au même nombre de caractères que le message
     * @param message
     * @param cle du message
     * @return La clé dupliquée
     */
    private String repliquerCle(String message, String cle){
        int nbMessage = message.length(); // Récupération du nombre de caractères du message
        int nbCle = cle.length(); // Récupération du nombre de caractères du messages
        int difference = nbMessage-nbCle; // Nombre de caractères à ajouter à la clé
        int quotient = difference/nbCle; // Récupération du quotient pour déterminer le nombre clé pleines à dupliquer
        int modulo = difference % nbCle; // Récupération du reste pour déterminer le nombre de lettres de la clé à ajouter à la fin
        int ligne = 0; // Nombre de colonnes
        
        if(difference>0){ // Si le message a plus de caractères que la clé
            cle += cle.repeat(quotient); // La clé se répète du nombre de quotient
            if( modulo > 0){ // Si la division euclidienne a un reste
                cle += cle.substring(0,modulo); // Ajout des caractère supplémentaires
            }     
        } else if(difference<0){
            cle = cle.substring(0, nbMessage);
        }
        return cle;
    }
}
