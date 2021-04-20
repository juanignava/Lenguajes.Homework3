import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.IOException;

public class Server {

    private static final int PORT = 6666;

    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws IOException {

        ServerSocket listener = new ServerSocket(PORT);

        while (true) {

            System.out.println("[SERVER] Waiting for client connection...");
            Socket client = listener.accept();
            System.out.println("[SERVER] Client connected!");
            ClientHandler clientThread = new ClientHandler(client, clients);
            clients.add(clientThread);

            pool.execute(clientThread);

        }

    }
    /*
    public static void main(String[] args) {

        ServerSocket serverSocket = null;

        try {

            // Creating the socket with the specified port.
            serverSocket = new ServerSocket(6666);
            System.out.println("Server is Waiting for client request... ");

            // Listening a connection to be made between the server and the client.
            Socket socket = serverSocket.accept();

            Messages messagesThread = new Messages(socket);
            messagesThread.run();

        } catch (Exception exe) {
            exe.printStackTrace();
        }
    }
    */
}
