package iutdijon.cryptomessengerclient.modele.protocoles;

/**
 * Classe permettant de générer une clé pour le cryptogramme de Cesar
 * @author tomhu
 */
public class GenerateurCle {
    
    public static String genererCleCesar(){
        int n = (int)(Math.random() * 25);
        String nombre = String.valueOf(n);
        return nombre;
    }
}
