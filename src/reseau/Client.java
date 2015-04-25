package reseau;

import joueur.Joueur;
import plateau.Parcelle;
import plateau.PileParcelle;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Client {

    private int codemess;
    private String pseudo = "unknow";
    private Boolean stopclient = false;

    Socket clientSocket = null;
    ObjectOutputStream os = null;
    //BufferedReader is = null;
    ObjectInputStream ois = null;

    Thread att;

    ArrayList<Joueur> listeJoueur = null;
    private ArrayList<PileParcelle> pileParcelles = null;
    private int[] montantEnchere;
    private Parcelle ParcelleMain = null;
    private boolean prendParcelle= false;
    private Joueur j_actif = null;
    private Parcelle pChoisie = null;

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
            os = new ObjectOutputStream(clientSocket.getOutputStream());
            //is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
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
                    System.err.println("Something is wrong. One variable is null.");
                    return;
                }
                synchronized (this) {
                    try {
                        while (true) {

                            int responseLine = ois.readInt();
                            System.out.println("recu : " + responseLine);

                            if (responseLine == 1) {
                                while (pseudo.equals("unknow")) {

                                }
                                os.writeInt(1);
                                os.flush();
                                os.writeObject(pseudo);
                                os.flush();
                                System.out.println("pseudo send");
                            }

                            if (responseLine == 2) {
                                System.out.println("récupération de la liste des joueurs");
                                listeJoueur = new ArrayList<Joueur>();
                                for (int i = 0; i < 4; i++) {
                                    listeJoueur.add((Joueur) ois.readObject());
                                }
                                System.out.println(listeJoueur);
                                os.writeInt(2);
                                os.flush();
                                synchronized (att) {
                                    att.notify();
                                }

                            }

                            if (responseLine == 3) {
                                System.out.println("récupération des piles parcelles");
                                pileParcelles = new ArrayList<PileParcelle>();
                                for (int i = 0; i < 4; i++) {
                                    System.out.println("recup parti : " + i);
                                    pileParcelles.add((PileParcelle) ois.readObject());
                                }
                                System.out.println(pileParcelles);
                                os.writeInt(2);
                                os.flush();
                                synchronized (att) {
                                    att.notify();
                                }
                            }

                            if (responseLine == 4) {
                                System.out.println("récupération des enchères");

                                montantEnchere = (int[]) ois.readObject();
                                System.out.println(montantEnchere);
                                os.writeInt(2);
                                os.flush();
                                synchronized (att) {
                                    att.notify();
                                }
                            }

                            if (responseLine == 5) {
                                System.out.println("Demande de parcelle");
                                os.writeInt(2);
                                os.flush();
                                prendParcelle = true;
                                synchronized (att) {
                                    att.notify();
                                }
                            }

                            if (responseLine == 6) {
                                System.out.println("recupération choix autre joueur");

                                j_actif = (Joueur) ois.readObject();
                                pChoisie = (Parcelle) ois.readObject();
                                os.writeInt(2);
                                os.flush();
                                synchronized (att) {
                                    att.notify();
                                }
                            }


                            if (stopclient) {
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
            }
        };

        Thread serverThreadSend = new Thread(clientSend);
        serverThreadSend.start();

    }

    public void sendPseudo(String pseudo){
        this.pseudo = pseudo;
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
            ArrayList<Joueur> ret = new ArrayList<Joueur>(listeJoueur);
            listeJoueur =null;
            return ret;
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

    public int[] getMontantEnchere() {
        att = new Thread();
        att.start();
        synchronized (att) {
            while (montantEnchere == null) {
                System.out.println("att");
                try {
                    att.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int [] res = (int[]) montantEnchere.clone();
            montantEnchere = null;
            return res;
        }
    }

    public void sendEnchere(int i) {
        try {
            //code message
            os.writeInt(3);
            os.flush();
            //montantEnchere
            os.writeInt(i);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Boolean getParcelleMain(){
        att = new Thread();
        att.start();
        synchronized (att) {
            while (!prendParcelle) {
                System.out.println("att Parcelle Main");
                try {
                    att.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            prendParcelle =false;
            return true;
        }
    }

    public Parcelle getParcellePrise(){
        att = new Thread();
        att.start();
        synchronized (att) {
            while (pChoisie == null) {
                System.out.println("att ParcellePrise");
                try {
                    att.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Parcelle res = new Parcelle(pChoisie);
            pChoisie = null;
            return res;
        }
    }

    public Joueur getJoueur() {
        att = new Thread();
        att.start();
        synchronized (att) {
            while (j_actif == null) {
                System.out.println("att");
                try {
                    att.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Joueur res = new Joueur(j_actif);
            j_actif =null;
            return res;
        }
    }

    public void sendParcelle(Parcelle pChoisie) {
        try {
            //code message
            os.writeInt(4);
            os.flush();
            //montantEnchere
            os.reset();
            os.writeObject(pChoisie);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}