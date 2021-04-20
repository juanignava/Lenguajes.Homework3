import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Messages extends Thread{

    // Sockets Attributes
    Socket socket;
    DataOutputStream output;
    DataInputStream input;
    String strFromClient = "";

    /**
     * Description: Initialices de server messaging class.
     * @param socket corresponding socket
     */
    public Messages(Socket socket){
        this.socket = socket;
    }
    
    /**
     * Description: main thread method.
     */
    public void run(){
        System.out.println("Listening thread active");

        // Receiving messages until getting the "stop"
        while (!strFromClient.equals("stop")){
            try {
                // Waits for the client to send something
                input = new DataInputStream(this.socket.getInputStream());
                strFromClient = this.readObject(input);
                System.out.println("Message from client: "+ strFromClient);
                this.followInstruction(strFromClient);
                
                // Replies to the client
                output = new DataOutputStream(socket.getOutputStream());
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
        
        String message = "dk:100,400;f1:200,250;f2:300,214;f2:300,214;cr1:300,214;dk:100,400;f1:200,250;f2:300,214;f2:300,214;cr1:300,214";
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
        if (instruction.equals("Hello World\n")) {
            System.out.println("The beggining of programming");
        }
    }

}
