package iutdijon.cryptomessengerclient.modele.protocoles;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Classe permettant de générer une clé pour le cryptogramme de Cesar
 * @author tomhu
 */
public class GenerateurCle {
    
    /**
     * Générateur d'une clé aléatoire pour le cryptogramme de César
     * @return Le nombre représentant la clé
     */
    public static String genererCleCesar(){
        int n = (int)(Math.random() * 25);
        String nombre = String.valueOf(n);
        return nombre;
    }
    
    /**
     * Générateur d'une clé aléatoire pour le protocole par substitution
     * @return La chaîne de caractères composée des 26 lettres représentant la clé
     */
    public static String genererCleSubstitution(){
        String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        List<String> list = Arrays.asList(alphabet); // Transformation du tableau en liste
        Collections.shuffle(list); // Mélange aléatoire de la liste
        list.toArray(alphabet); // On remet la liste en tableau
        String cle = Arrays.toString(alphabet); // Enfin on passe le tableau en chaîne de caractères
        cle = cle.replace(",", "").replace("[", "").replace("]", "").replace(" ", ""); // On retire dans un premier temps les [ ] et , qui sont dans la chaîne puis tous les espaces
        return cle;
    }
    
    /**
     * Générateur d'une clé aléatoire pour le protocole de transposition
     * @return La chaîne de caractères représentant la clé
     */
    public static String gerererCleTransposition(){
        int n = (int) (5 + Math.random() * 16); // Nombre aléatoire entre 5 et 20 pour la taille de la clé
        String cle = "";
        char c;
        
        for(int i = 0 ; i < n ; i++){ // Pour chaque emplacement
            Random rand = new Random();
            Random rand2 = new Random();
            
            int casse = (int)(Math.random() * 2); //Définition d'un tirage aléatoire pour décider entre majuscule et minuscule
            if(casse ==1){
                c = (char)(rand2.nextInt(26) + 97); // Lettre minuscule
            } else {
                c = (char)(rand2.nextInt(26) + 65); // Lettre majuscule
            }
            cle += c; // On ajoute le caractère à la clé
        }
        return cle;
    }
}
