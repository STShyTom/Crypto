package iutdijon.cryptomessengerclient.network;

import iutdijon.cryptomessengerclient.modele.messages.Message;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * TODO Classe à coder par l'étudiant (donner le squelette)
 * @author simonetma
 */
public class Network {
          
    private static Network instance;
    private Socket socket;
    private BufferedReader fluxEntrant;
    private PrintWriter fluxSortant;
    private boolean connecte;
    
    /**
     * Constructeur qui initialise le booleen connecte à false
     */
    private Network() {
        this.connecte = false;
    }
    
    /**
     * Getter du singleton
     * @return l'instance
     */
    private static Network get() {
        if(instance == null){
            instance = new Network();
        }
        return instance;
    }
    
    /**
     * Création de la socket
     * @throws IOException 
     */
    private void creationSocket() throws IOException {
        String IP = Settings.getIpServeur();
        int port = Settings.getPortServeur();
        socket = new Socket(IP,port);
    }
    
    /**
     * Création des flux
     * @throws IOException 
     */
    private void creationFlux() throws IOException {
        InputStream i = socket.getInputStream();
        InputStreamReader truc = new InputStreamReader(i);
        fluxEntrant = new BufferedReader(truc);
        
        OutputStream o = socket.getOutputStream();
        fluxSortant = new PrintWriter(o,true);
    }
    
    /**
     * Connexion
     * @throws IOException 
     */
    public static void connexion() throws Exception {
        get().creationSocket();
        get().creationFlux();
        
        String message = Network.recevoirLigne();
        System.out.println(message);
        envoyerLigne(Settings.getNomUtilisateur());
        String message2 = Network.recevoirLigne();
        System.out.println(message2);
        
        if(message2.equals("Connexion établie")){
           get().connecte = true; 
        }
    }
    
    /**
     * Envoyer une ligne
     * @param message le message à envoyer 
     */
    private static void envoyerLigne(String message) {
        get().fluxSortant.println(message);
    }
    
    /**
     * Envoyer un message
     * @param message le message à envoyer au serveur
     */
    public static void envoyer(Message message) {
        envoyerLigne("ENVOI");
        
        envoyerLigne(message.getDestinataire());
        envoyerLigne(message.getProtocoleUtilise().toString());
        
        envoyerLigne(message.getCorpsMessage().replace("\n","_CeciEstUnSautDeLigne_")); // On remplace les sauts de ligne de la chaine du message
                                                                           // par une chaine de caractère afin que les sauts de ligne soient lus par l'envoi
    }
    
    /**
     * Recevoir une ligne
     * @return la ligne reçue
     * @throws IOException 
     */
    private static String recevoirLigne() throws Exception {
        String messageRecu = get().fluxEntrant.readLine();
        return messageRecu;
    }
    
    /**
     * Recevoir un message
     * @param message le message recu
     */
    public static void recevoir(Message message) {
        envoyerLigne("RECEPTION");
        
        try {
            message.setExpediteur(recevoirLigne());
            message.setProtocoleUtilise(recevoirLigne());
           /* message.setCorpsMessage(recevoirLigne());  Ancien code */
           /* Nouveau code */
            String _message = recevoirLigne().replace("_CeciEstUnSautDeLigne_", "\n"); // On retransforme les chaines crées en sauts de ligne
            message.setCorpsMessage(_message);                                         // pour la lecture du message
        } catch(Exception e){
            System.err.println(e);
        }
    }
    
    /**
     * Le client est-il connecté ?
     * @return est-ce que le client est connecté ou non.
     */
    public static boolean estConnecte() {
        return get().connecte;
    }
}
