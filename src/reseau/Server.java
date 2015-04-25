package reseau;

import gui.LauncherGUI;
import joueur.Joueur;
import plateau.PileParcelle;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {

    // declare a server socket and a client socket for the server;
    // declare the number of connections

    ServerSocket echoServer = null;
    Socket clientSocket = null;
    int numConnections = 0;
    int port;
    ArrayList<Server2Connection> clientCo = new ArrayList<Server2Connection>();

    public Server(int port) {
        this.port = port;
    }

    public void stopServer() {
        System.out.println("Server cleaning up.");
        System.exit(0);
    }

    public void startServer() {
        // Try to open a server socket on the given port
        // Note that we can't choose a port less than 1024 if we are not
        // privileged users (root)
        Runnable serverTask = new Runnable() {
            @Override
            public void run() {
                try {
                    echoServer = new ServerSocket(port);
                    while (true) {
                        try {
                            clientSocket = echoServer.accept();
                            numConnections++;
                            Server2Connection oneconnection = new Server2Connection(clientSocket, numConnections, Server.this);
                            clientCo.add(oneconnection);
                            new Thread(oneconnection).start();
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                    }
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        };


        System.out.println("Server is started and is waiting for connections.");
        System.out.println("With multi-threading, multiple connections are allowed.");
        System.out.println("Any client can send -1 to stop the server.");

        Thread serverThread = new Thread(serverTask);
        serverThread.start();

        // Whenever a connection is received, start a new thread to process the connection
        // and wait for the next connection.


    }

    public String getPseudo(int id) {
        return clientCo.get(id).pseudoJoueur;
    }

    public int getNbCo() {
        return numConnections;
    }

    public void sendJoueur(ArrayList<Joueur> listeJoueurs, int i) {
        System.out.println("sendJoueur");
        try {
            clientCo.get(i).os.println(2);
            clientCo.get(i).os.flush();
            System.out.println("sendJoueur to " + i);
            System.out.println(listeJoueurs);
            clientCo.get(i).oos.writeObject(listeJoueurs);
            clientCo.get(i).oos.flush();
            //clientCo.get(i).oos.reset();
            //Thread.sleep(800);
        } catch (IOException e) {
            System.out.println("fail envoie");
            e.printStackTrace();
        }
    }

    public void sendPileParcelle(ArrayList<PileParcelle> pileParcelles, int i){
        System.out.println("sendPile");
        try {
            clientCo.get(i).os.println(3);
            clientCo.get(i).os.flush();
            System.out.println("sendJoueur to " + i);
            System.out.println(pileParcelles);
            clientCo.get(i).oos.writeObject(pileParcelles);
            clientCo.get(i).oos.flush();
        } catch (IOException e) {
            System.out.println("fail envoie");
            e.printStackTrace();
        }
    }

    public Boolean reponseClient(int i){
        if(clientCo.get(i).recu){
            clientCo.get(i).recu = false;
            return true;
        }
        return false;
    }

}

class Server2Connection implements Runnable {
    BufferedReader is;
    PrintStream os;
    ObjectOutputStream oos = null;
    Socket clientSocket;
    int id;
    Server server;
    Boolean recu = false;
    String pseudoJoueur = "unknow";

    public Server2Connection(Socket clientSocket, int id, Server server) {
        this.clientSocket = clientSocket;
        this.id = id;
        this.server = server;
        System.out.println( "Connection " + id + " established with: " + clientSocket );
        try {
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            os = new PrintStream(clientSocket.getOutputStream());
            oos = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println(e);
        }

        os.println(1);
        os.flush();
    }

    public void run() {
        String line;
        System.out.println("client thread crée");
        try {
            boolean serverStop = false;

            while (true) {
                line = is.readLine();
                System.out.println( "Received " + line + " from Connection " + id + "." );
                int n = Integer.parseInt(line);

                //1 = pseudo
                if(n == 1){
                    pseudoJoueur = is.readLine();
                    System.out.println(pseudoJoueur);
                }
                if(n == 2){
                    recu = true;
                }
                if(serverStop){
                    break;
                }

            }

            System.out.println( "Connection " + id + " closed." );
            is.close();
            os.close();
            oos.close();
            clientSocket.close();

            if ( serverStop ) server.stopServer();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
