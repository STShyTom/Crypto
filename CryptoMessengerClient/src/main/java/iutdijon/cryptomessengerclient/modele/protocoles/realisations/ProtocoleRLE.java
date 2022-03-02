package iutdijon.cryptomessengerclient.modele.protocoles.realisations;

import iutdijon.cryptomessengerclient.modele.messages.Message;
import iutdijon.cryptomessengerclient.modele.protocoles.Protocole;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui gère le protocole RLE
 * @author th299374
 */
public class ProtocoleRLE extends Protocole{

    @Override
    public Message chiffrer(Message messageClair) {
        String corps = messageClair.getCorpsMessage() + " "; // Récupération du corps du message envoyé en ajoutant un espace à la fin pour prendre en compte le réel dernier caractère du message 
        int cle = Integer.parseInt(this.getCle("CLE_COMPRESSION")); // Récupération de la clé sous forme d'entier
        String messageChiffre = "";
        int compteur = 1; // Compteur qui stockera le nombre de caractères identiques à la suite
        
        for(int i = 0; i< corps.length(); i++){
            char caractere = corps.charAt(i); // On prend le caractère du message à la position i
            if(i!=0){ // On évite le tout premier caractère qui n'a pas de précédent
                char caracterePrecedent = corps.charAt(i-1); // On prend le caractère du message à la position précédente
                if(caractere == caracterePrecedent){ // Si le caractère actuel est identique au précédent
                    if(compteur<cle){ // Si on a pas atteint la valeur indiquée par la clé
                        compteur += 1; // On incrémente le compteur de 1
                    } else { // Si on a atteint le nombre maximal de caractères
                        messageChiffre += String.valueOf(compteur) + caracterePrecedent; // On ajoute au message le nombre d'apparition du caractère et le caractère
                        compteur = 1; // On reset le compteur à 1
                    }      
                } else { // Si le caractère n'est pas identique au précédent 
                    messageChiffre += String.valueOf(compteur) + caracterePrecedent; // On ajoute au message le nombre d'apparition du caractère et le caractère
                    compteur = 1; // On reset le compteur à 1
                }
           } else {
                // Ne rien faire
            }
        }
        Message nouveauMessage = new Message();
        nouveauMessage.setCorpsMessage(messageChiffre);
        return nouveauMessage;
    }

    @Override
    public Message dechiffrer(Message messageChiffre) {
        String corps = messageChiffre.getCorpsMessage(); // Récupération du corps du message chiffré
        int cle = Integer.parseInt(this.getCle("CLE_COMPRESSION")); // Récupération de la clé sous forme d'entier
        int tailleCLE = this.getCle("CLE_COMPRESSION").length(); // Récupération de la taille de la clé ( question 11 )
        String messageDechiffre = "";
        String partieTraitee = ""; // Partie traitée du message 
        int nbOccurences; // Nombre d'occurences de caractère
        
        while(corps.length()>0){ // Tant qu'il reste des caractères dans le message
            partieTraitee = corps.substring(0, 2); // On récupère les deux premiers caractères à chaque fois
            nbOccurences = partieTraitee.charAt(0)-48; // On récupère le nombre
            char c = partieTraitee.charAt(1); // On récupère le caractère
            for(int i=0;i<nbOccurences;i++){
                messageDechiffre += c; // On ajoute le caractère au message le nombre de fois donné
            }
            corps = corps.substring(2,corps.length()); // On supprime la partie traitée du message
        }
            
        Message nouveauMessage = new Message();
        nouveauMessage.setCorpsMessage(messageDechiffre);
        return nouveauMessage;
    }
}