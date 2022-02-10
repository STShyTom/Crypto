package iutdijon.cryptomessengerclient.modele.protocoles.realisations;

import iutdijon.cryptomessengerclient.modele.messages.Message;
import iutdijon.cryptomessengerclient.modele.protocoles.Protocole;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * Classe qui gère la transposition 
 * @author th299374
 */
public class ProtocoleTransposition extends Protocole{

    private SecureRandom generateur; 
    
    @Override
    public Message chiffrer(Message messageClair) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Message dechiffrer(Message messageChiffre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Méthode qui créée le tableau et le remplit
     * @param message Le message envoyé
     * @param cle La clé du message
     * @return Le tableau rempli
     */
    private char[][] remplirTableauChiffrement(String message, String cle){
        //Création du générateur aléatoire
        ByteBuffer b = ByteBuffer.allocate(4) ;
        b.putInt((cle+message).hashCode()) ;
        this.generateur = new SecureRandom (b.array()) ;
        
        int nbCle = cle.length(); // Récupération du nombre de caractères de la clé
        int nbMessage = message.length(); // Récupération du nombre de caractères du message
        int quotient = nbMessage/nbCle; // Récupération du quotient pour déterminer le nombre de lignes
        int modulo = nbMessage % nbCle; // MODULO
        int ligne = 0; // Nombre de colonnes
        int position = 0; // Position d'un caractère dans le message
        
        if( modulo == 0){ // Si la division euclidienne n'a pas de reste
            ligne = quotient;  // Le tableau aura le quotient pour nombre de lignes
        } else{
            ligne = quotient +1; // Le tableau aura le quotient +1 pour nombre de lignes pour placer tous les caractères
            for(int bourrage = 0 ; bourrage < modulo ; bourrage++){ // Pour chaque case qui sera vide
                message += bourrage(); // On bourre avec un caractère aléatoire
            }     
        }
        char[][] tab = new char[ligne][nbCle]; // Création du tableau avec le bon nombre de caractères
        
        for(int i = 0; i<ligne ; i++){ // Pour chaque ligne
            for(int j = 0; j<nbCle ; j++){ // Pour chaque colonne
               tab[i][j] = message.charAt(position); // Remplissage du tableau avec les caractères du message
               position++;
            }
        }
        return tab;
    }
    
    /**
     * Méthode permettant de bourrer aléatoirement le message
     * @return Les caractères de bourrage
     */
    private char bourrage(){
        this.generateur = new SecureRandom(); // Création du générateur 
        boolean b = generateur.nextBoolean(); // Création d'un générateur aléatoire de booléen pour choisir entre majuscule et minuscule
        char c;
        
        if(b){
            c = (char) (generateur.nextInt(26)+'a'); // Minuscule
        } else{
            c = (char) (generateur.nextInt(26)+'A'); // Majuscule
        }
        return c;
    }
    
    /**
     * Méthode qui donne l'ordre des colonnes
     * @param cle Clé envoyée
     * @return Une liste contenant l'ordre dans lequel lire les colonnes
     */
    private ArrayList<Integer> getOrdreColonne(String cle){
        ArrayList<Integer> list = new ArrayList<>(); // Déclaration et initialisation de la liste
        for (int i = 0; i < cle.length() ; i++){ // Pour chaque caratère de la clé
           
        }
        return list;
    }
}
