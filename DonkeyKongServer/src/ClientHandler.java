

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    // Sockets Attributes
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private DataOutputStream output;
    private DataInputStream input;
    private String incomingMessage = "";

    // Attributes for ClientHandler instance
    private ArrayList<ClientHandler> clients;

    private Controller controller;

    /**
     * Description: constructor method.
     */
     public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> clients, Controller controller)
            throws IOException {

        this.client = clientSocket;
        this.clients = clients;
        this.controller = controller;

        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);

    }

    /**
     * Description: main thread method.
     */
    @Override
    public void run() {

        System.out.println("Listening thread active");

        // Receiving messages until getting the "s" character
        while (!incomingMessage.equals("s")){

            try {

                // Waits for the client to send something
                input = new DataInputStream(this.client.getInputStream());
                incomingMessage = this.readObject(input);
                //System.out.println("Message from client: "+ incomingMessage);

                // Instruction that will be followed depending on what client message says
                Integer option = controller.followInstruction(incomingMessage);

                // The server always has to notify clients no matter what string it receives
                output = new DataOutputStream(client.getOutputStream());
                writeObject(output, option);

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

    }
    
    /**
     * Description: Configures the socket message into a one Java could understand.
     * Resturn: returns the string.
     */
    private String readObject(java.io.DataInputStream input) throws IOException {

        // Deletes \0 character from C strings
        Integer messageLenght = input.readInt() - 1;

        byte [] aux = null;
        aux = new byte[messageLenght];

        input.read(aux, 0, messageLenght);
        String message = new String(aux);
        input.read(aux, 0, 1);

        return message;

    }

    /**
     * Description: method that configures the message into a C understandable one
     * and also sends the message by an output stream.
     * @param output 
     * @throws IOException
     */
    private void writeObject(java.io.DataOutputStream output, Integer option) throws IOException{

        String message = controller.getMessage(option);

        Integer messageLength = message.length();

        output.writeInt(messageLength + 1);
        output.writeBytes(message);
        output.writeByte('\0');
        output.flush();
        //System.out.println("Enviando: " + message);

    }

}
