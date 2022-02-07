package iutdijon.cryptomessengerclient.modele.protocoles;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
}
