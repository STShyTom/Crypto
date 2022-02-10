package iutdijon.cryptomessengerclient.modele.protocoles.realisations.transposition;

/**
 * Classe qui crée un couple avec un caractère et une position pour la transposition
 * @author tomhu
 */
public class Couple {
    
    private char caractere;
    private int position;

    /**
     * Getter du caractère
     * @return le carctère
     */
    public char getCaractere() {
        return caractere;
    }

    /**
     * Setter du caractère
     * @param caractere le caractère à modifier
     */
    public void setCaractere(char caractere) {
        this.caractere = caractere;
    }

    /**
     * Getter de la position
     * @return la position
     */
    public int getPosition() {
        return position;
    }

    /**
     * Setter de la posiiton
     * @param position la posittion à modifier
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Constructeur d'un couple
     * @param caractere 
     * @param position du caractère
     */
    public Couple(char caractere, int position) {
        this.caractere = caractere;
        this.position = position;
    }
    
}
