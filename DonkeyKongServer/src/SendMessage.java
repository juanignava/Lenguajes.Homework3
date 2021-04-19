import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SendMessage extends Thread{

    Socket socket;
    DataOutputStream output = null;
    BufferedReader buffer = null;

    public SendMessage(Socket socket){
        this.socket = socket;
    }

    public void run(){
        while(true){
            try {
                buffer = new BufferedReader(new InputStreamReader(System.in));
                output = new DataOutputStream(socket.getOutputStream());
                writeObject(buffer.readLine(), output);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeObject(String message, java.io.DataOutputStream output) throws IOException{
        if (message != null) {

            Integer messageLength = message.length();
            output.writeInt(messageLength + 1);
            output.writeBytes(message);
            output.writeByte('\0');
            output.flush();
            System.out.println("Enviando: " + message);
        }
        
    }

}
