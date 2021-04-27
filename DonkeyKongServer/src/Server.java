

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.IOException;

public class Server {

    // Number of clients the server can manage
    private static ExecutorService pool = Executors.newFixedThreadPool(6);

    private static ArrayList<ClientHandler> clients = new ArrayList<>();

    private static ServerSocket listener;

    private static Controller controller;

    public static void main(String[] args) throws IOException {

        listener = new ServerSocket(Constants.PORT);

        controller = new Controller();

        new GUI(controller);


        while (true) {

            System.out.println("Waiting for a client connection...");
            Socket client = listener.accept();

            System.out.println("Client connected successfully");
            ClientHandler clientThread = new ClientHandler(client, clients, controller);
            clients.add(clientThread);

            pool.execute(clientThread);

            if (pool.isTerminated()) {

                dispose(listener);

            }

        }

    }

    /**
     * Description: destructor method.
     * @param listener
     * @throws IOException
     */
    private static void dispose(ServerSocket listener) throws IOException {

        listener.close();

    }

}

