import java.net.ServerSocket;
import java.net.Socket;

public class Server {

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
}
