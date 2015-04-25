package reseau;

import joueur.Joueur;
import plateau.Parcelle;
import plateau.PileParcelle;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Client {

    private int codemess;
    private String mess = "unknow";
    private Boolean stopclient = false;

    Socket clientSocket = null;
    DataOutputStream os = null;
    BufferedReader is = null;
    ObjectInputStream ois = null;

    Thread att;

    ArrayList<Joueur> listeJoueur = null;
    private ArrayList<PileParcelle> pileParcelles = null;

    public void lancer() {

        final String hostname = "localhost";
        final int port = 6789;

        // declaration section:
        // clientSocket: our client socket
        // os: output stream
        // is: input stream


        // Initialization section:
        // Try to open a socket on the given port
        // Try to open input and output streams

        try {
            clientSocket = new Socket(hostname, port);
            os = new DataOutputStream(clientSocket.getOutputStream());
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ois = new ObjectInputStream(clientSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + hostname);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + hostname);
        }


        Runnable clientSend = new Runnable(){
            @Override
            public void run() {
                // If everything has been initialized then we want to write some data
                // to the socket we have opened a connection to on the given port

                if (clientSocket == null || os == null) {
                    System.out.println("Send");
                    System.out.println(os);
                    System.out.println(clientSocket);
                    System.err.println( "Something is wrong. One variable is null." );
                    return;
                }

                try {
                    while ( true ) {

                        String responseLine = is.readLine();
                        System.out.println("recu : " + responseLine);

                        if(Integer.parseInt(responseLine) == 1) {
                            while(mess.equals("unknow")){

                            }
                            os.writeBytes(Integer.toString(1) + "\n");
                            os.writeBytes(mess+ "\n");
                            System.out.println("pseudo send");
                        }

                        if(Integer.parseInt(responseLine) == 2){
                            System.out.println("récupération de la liste des joueurs");
                            listeJoueur = (ArrayList<Joueur>) ois.readObject();
                            System.out.println(listeJoueur);
                            os.writeBytes(2 + "\n");
                            synchronized (att) {
                                att.notify();
                            }

                        }

                        if(Integer.parseInt(responseLine) == 3){
                            System.out.println("récupération des piles parcelles");

                            pileParcelles = (ArrayList<PileParcelle>) ois.readObject();
                            System.out.println(pileParcelles);
                            os.writeBytes(2 + "\n");
                            synchronized (att) {
                                att.notify();
                            }
                        }



                        if ( stopclient ) {
                            break;
                        }

                        //String responseLine = is.readLine();
                        //System.out.println("Server returns its square as: " + responseLine);
                    }

                    // clean up:
                    // close the output stream
                    // close the input stream
                    // close the socket

                    os.close();
                    clientSocket.close();
                } catch (UnknownHostException e) {
                    System.err.println("Trying to connect to unknown host: " + e);
                } catch (IOException e) {
                    System.err.println("IOException:  " + e);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread serverThreadSend = new Thread(clientSend);
        serverThreadSend.start();

    }

    public void sendPseudo(String pseudo){
        mess = pseudo;
    }

    public ArrayList<Joueur> getJoueurs() {
        att = new Thread();
        att.start();
        synchronized (att) {
            while (listeJoueur == null) {
                System.out.println("att");
                try {
                    att.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return listeJoueur;
        }
    }

    public ArrayList<PileParcelle> getPileParcelles () {
        att = new Thread();
        att.start();
        synchronized (att) {
            while (pileParcelles == null) {
                System.out.println("att");
                try {
                    att.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return pileParcelles;
        }

    }
}