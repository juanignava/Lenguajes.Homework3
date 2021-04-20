import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private ArrayList<ClientHandler> clients;
    DataOutputStream output;
    DataInputStream input;
    String strFromClient = "";


    public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> clients) throws IOException {

        this.client = clientSocket;
        this.clients = clients;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);

    }


    @Override
    public void run() {
        System.out.println("Listening thread active");

        // Receiving messages until getting the "stop"
        while (!strFromClient.equals("s")){
            try {
                // Waits for the client to send something
                input = new DataInputStream(this.client.getInputStream());
                strFromClient = this.readObject(input);
                System.out.println("Message from client: "+ strFromClient);
                this.followInstruction(strFromClient);
                
                // Replies to the client
                output = new DataOutputStream(client.getOutputStream());
                writeObject(output);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Description: Configures the socket message into a one Java could
     * understand.
     * Resturn: returns the string.
     */
    private String readObject(java.io.DataInputStream input) throws IOException {
        // Deletes \0 character from C strings
        Integer messageLenght = input.readInt()-1;
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
    private void writeObject(java.io.DataOutputStream output) throws IOException{
        
        String message = "dk,100,400;f1,200,250;f2,300,214;f3,_,_;c1,r,200,300;c2,a,200,400;....;/dk,_,_;f1,200,250;f2,300,214;f3,300,214;c1,r,200,300;c2,a,200,400;....;";
        Integer messageLength = message.length();
        output.writeInt(messageLength + 1);
        output.writeBytes(message);
        output.writeByte('\0');
        output.flush();
        System.out.println("Enviando: " + message);
    }

    /**
     * Description: Analices the string given by the client and calls the given funtions.
     * @param instruction String with the indication given by the client
     */
    private void followInstruction(String instruction){
        if (instruction.equals("a")) {
            System.out.println("The beggining of programming");
        }
    }

    /**
     * Description: method that sends a message to all the 
     * clients connected.
     * @param msg String with the message to display
     */
    private void outToAll(String msg) {

        for (ClientHandler aClient : clients) {

            aClient.out.println(msg);

        }

    }

}
