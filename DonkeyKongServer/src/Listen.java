import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Listen extends Thread{

    Socket socket;
    DataInputStream input;
    String strFromClient = "";

    public Listen(Socket socket){
        this.socket = socket;
    }
    
    public void run(){
        System.out.println("Listening thread active");
        while (!strFromClient.equals("stop")){
            try {
                input = new DataInputStream(this.socket.getInputStream());
                strFromClient = this.readObject(input);
                System.out.println("Message from client: "+ strFromClient);
                this.followInstruction(strFromClient);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Exception");
            }
        }
    }

    private String readObject(java.io.DataInputStream input) throws IOException {
        Integer messageLenght = input.readInt()-1;
        byte [] aux = null;
        aux = new byte[messageLenght];

        input.read(aux, 0, messageLenght);
        String message = new String(aux);
        input.read(aux, 0, 1);
        return message;
    }

    private void followInstruction(String instruction){
        if (instruction.equals("Hello World\n")) {
            System.out.println("The beggining of programming");
        }
    }

}
