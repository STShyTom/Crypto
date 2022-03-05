package iutdijon.cryptomessengerclient.modele.protocoles.realisations;

import iutdijon.cryptomessengerclient.modele.messages.Message;
import iutdijon.cryptomessengerclient.modele.protocoles.Protocole;
import iutdijon.cryptomessengerclient.modele.protocoles.realisations.huffman.ComparateurNoeuds;
import iutdijon.cryptomessengerclient.modele.protocoles.realisations.huffman.Noeud;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * @author simonetma
 */
public class ProtocoleHuffman extends Protocole {

    //ETAPE 1 -- Compter les occurences de chaque caractère
    public HashMap<Character,Integer> compterCaracteres(String corpMessage) {
        HashMap<Character,Integer> map = new HashMap<>(); // Création d'une hashmap vide

        for(int i = 0; i< corpMessage.length(); i++){
            char caractere = corpMessage.charAt(i); // On prend le caractère du message à la position i   
            if(map.get(caractere) == null){ // Si le car n'est pas présent dans la map
                map.put(caractere, 1); // On ajoute le caractère à la map
            } else {
                map.put(caractere, map.get(caractere)+1); // On ajoute 1 à la valeur associée
            }
        }
        return map;
    }
    
    //ETAPE 2 -- Création de l'arbre
    public PriorityQueue<Noeud> creationListeNoeuds(HashMap<Character,Integer> mapComptageCaracteres) {
        ComparateurNoeuds cn = new ComparateurNoeuds();
        PriorityQueue<Noeud> pq = new PriorityQueue<Noeud>(cn);
        for(Character c : mapComptageCaracteres.keySet()){ // Pour chaque car de la map
            Noeud n = new Noeud(c.toString(),mapComptageCaracteres.get(c)); // Création du noeud avec le caractère et sa valeur associée
            pq.add(n); // Ajout du noeud à la pq
        }
        return pq;
    }
    
    public Noeud creationArbre(HashMap<Character,Integer> mapComptageCaracteres) {
        PriorityQueue<Noeud> pq = creationListeNoeuds(mapComptageCaracteres); // Génération de la pq créée pécédemment
        while(pq.size()>=2){ // Tant qu'il reste au moins 2 éléments dans la pq
            Noeud n1 = pq.poll(); // Récupération du premier noeud
            Noeud n2 = pq.poll(); // Récupération du second noeud
            String concatenation = n1.getNom() + n2.getNom(); // Obtention de la concaténation du nom des deux noeuds
            int valeur = n1.getNombreOccurences() + n2.getNombreOccurences(); // Obtention de la somme de la valeur des deux noeuds
            Noeud nouveauNoeud = new Noeud(concatenation,valeur); // Création du nouveau noeud
            nouveauNoeud.ajouterFils(n1); // On a joute n1 comme fils du nouveau noeud créé
            nouveauNoeud.ajouterFils(n2); // On a joute n2 comme fils du nouveau noeud créé
            pq.add(nouveauNoeud); // Ajout du nouveau noeud à la pq
        }
        Noeud dernierNoeud = pq.peek(); // Récupération du dernier noeud
        return dernierNoeud;
    }
    
    //ETAPE 3 -- Création du dictionnaire
    public HashMap<Character,String> creationDictionnaire(Noeud racine) {
        HashMap<Character,String> dictionnaire = new HashMap<Character, String>(); // Création d'un dico vide
        racine.calculCode(dictionnaire); // Lancement du calcul 
        return dictionnaire;
    }
    
    //ETAPE 4 - Chiffrement du message
    private String envoiDictionnaire(HashMap<Character,String> dictionnaire) {
        String dicoChiffre = "";
        for(Character c : dictionnaire.keySet()){ // Pour chaque caractère de la map
            dicoChiffre += c + "|" + dictionnaire.get(c) + "|"; // Ajout du caractère et de son code au message
        }
        return dicoChiffre;
    }
    
    private String chiffrerMessage(String message,HashMap<Character,String> dictionnaire) {
        String messageChiffre = "";
        for(int i = 0 ; i < message.length();i++){
            char caractere = message.charAt(i); // On prend le caractère du message à la position i   
            messageChiffre += dictionnaire.get(caractere) +  "|"; // On ajoute au fur et à mesure le code de chaque caractère
        }
        return messageChiffre;
    }
    
    
    @Override
    public Message chiffrer(Message messageClair) {
        String corps = messageClair.getCorpsMessage(); // Récupération du corps du message envoyé
        String messageChiffre = "";
        
        HashMap<Character,Integer> map = compterCaracteres(corps); // On récupère la hash map comptant le nombre d'occurences de chaque caractère
        
        PriorityQueue<Noeud> pq = creationListeNoeuds(map); // Création de la liste des noeuds de l'arbre
        Noeud noeud = creationArbre(map); // Création de l'arbre et récupération du noeud racine pour la suite
        
        HashMap<Character,String> dictionnaire = creationDictionnaire(noeud); // Création du dico
        
        messageChiffre = envoiDictionnaire(dictionnaire); // Ajout du dictionnaire au message
        messageChiffre += "§" + chiffrerMessage(corps, dictionnaire); // Récupération du message final chiffré
        
        Message nouveauMessage = new Message();
        nouveauMessage.setCorpsMessage(messageChiffre);
        return nouveauMessage;
    }

    @Override
    public Message dechiffrer(Message messageChiffre) {
        String corps = messageChiffre.getCorpsMessage(); // Récupération du corps du message chiffré
        String messageDechiffre = "";
        HashMap<String,Character> dictionnaire = new HashMap<String,Character>(); 
                
        String[] separation = corps.split("§"); // On casse le message pour séparer le dictionnaire du message
        String dico = separation[0]; // On récupère le dictionnaire
        String listeCaracteres = separation[1]; // On récupère le message crypté
        
        dictionnaire = creerDictionnaire(dico); // On crée le dictionnaire
        messageDechiffre = creerMessage(dictionnaire,listeCaracteres); // On crée le message
        
        Message nouveauMessage = new Message();
        nouveauMessage.setCorpsMessage(messageDechiffre);
        return nouveauMessage;
    }
    
    private HashMap<String,Character> creerDictionnaire(String dico){
        HashMap<String,Character> dictionnaire = new HashMap<String,Character>();

        String[] separation = dico.split("|"); // On sépare toutes les parties
                
        while(separation.length>0){ // Tant qu'il reste des caractères à traiter dans le dictionnaire
            char caractere = separation[0].charAt(0); // On récupère le caractère
            String code = separation[1]; // On récupère le code associé
            dictionnaire.put(code, caractere); // On insère le caractère et son code dans le dictionnaire
            List<String> list = new ArrayList<>();
            for (int i=0; i<separation.length; i++){
                list.add(separation[i]);
            }
            list.remove(1);
            list.remove(0); // On retire les deux premiers éléments
            list.toArray(separation); // Conversion inverse
        }
        return dictionnaire;    
    }
    
    private String creerMessage(HashMap<String,Character> dictionnaire ,String message){
        String messageDechiffre = "";
        String[] separation = message.split("|"); // On sépare toutes les parties
        for(String code : separation){ // Pour chaque code
            char c = dictionnaire.get(code); // On récupère le caractère associé
            messageDechiffre += c; // On ajoute le caractère au message
        }
        return messageDechiffre;
    }
}
