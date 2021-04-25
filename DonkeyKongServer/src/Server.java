

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.IOException;

public class Server {

    private static final int PORT = 6666;

    // Number of clients the server can manage
    private static ExecutorService pool = Executors.newFixedThreadPool(6);

    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ArrayList<DonkeyKongJr> players = new ArrayList<>();
    private static ArrayList<Alligator> alligators1 = new ArrayList<>();
    private static ArrayList<Alligator> alligators2 = new ArrayList<>();
    private static ArrayList<Fruit> fruits1 = new ArrayList<>();
    private static ArrayList<Fruit> fruits2 = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        // Loop to add two null instances to players
        /*
        for (int i = 0; i <= 1; i++) {

            players.add(null);

        }
        */

        // Loop to add six null instances to alligators1
        for (int i = 0; i < 6; i++) {

            alligators1.add(null);

        }

        // Loop to add six null instances to alligators2
        for (int i = 0; i < 6; i++) {

            alligators2.add(null);

        }

        // Loop to add six null instances to fruits1
        for (int i = 0; i < 6; i++) {

            fruits1.add(null);

        }

        // Loop to add six null instances to fruits2
        for (int i = 0; i < 6; i++) {

            fruits2.add(null);

        }

        ServerSocket listener = new ServerSocket(PORT);

        // Displaying the administrator user window
        new GUI(players, alligators1, alligators2, fruits1, fruits2);


        while (true) {

            System.out.println("Waiting for a client connection...");
            Socket client = listener.accept();

            System.out.println("Client connected successfully");
            ClientHandler clientThread = new ClientHandler(client, clients, players, alligators1,
                    alligators2, fruits1, fruits2);
            clients.add(clientThread);

            pool.execute(clientThread);

        }

    }

}

