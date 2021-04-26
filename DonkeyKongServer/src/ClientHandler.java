

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

    private ArrayList<DonkeyKongJr> players;

    private DonkeyKongJr player1;
    private DonkeyKongJr player2;

    private ArrayList<Alligator> alligators1;
    private ArrayList<Alligator> alligators2;

    private ArrayList<Fruit> fruits1;
    private ArrayList<Fruit> fruits2;

    // Constants
    private static final int PIXELS_UP_DOWN = 20;
    private static final int PIXELS_RIGHT_LEFT = 20;
    private static final int DK_INITIAL_X = 50;
    private static final int DK_INITIAL_Y = 565;

    /**
     * Description: constructor method.
     */
    public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> clients, ArrayList<DonkeyKongJr> players,
                         ArrayList<Alligator> alligators1, ArrayList<Alligator> alligators2,
                         ArrayList<Fruit> fruits1, ArrayList<Fruit> fruits2) throws IOException {

        this.client = clientSocket;
        this.clients = clients;
        this.players = players;
        this.alligators1 = alligators1;
        this.alligators2 = alligators2;
        this.fruits1 = fruits1;
        this.fruits2 = fruits2;

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
                this.followInstruction(incomingMessage);

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
    private void writeObject(java.io.DataOutputStream output) throws IOException{
        

        String message = getData();
        Integer messageLength = message.length();

        output.writeInt(messageLength + 1);
        output.writeBytes(message);
        output.writeByte('\0');
        output.flush();
        //System.out.println("Enviando: " + message);

    }

    /**
     * Description: Analyzes the string given by the client and calls the given functions.
     * @param instruction String with the indication given by the client
     */
    private void followInstruction(String instruction) throws IOException {

        // Instructions for player1
        if (instruction.contains("1")) {

            // Checks if the player1 is already instantiated or not
            if (players.size() == 0) {

                player1 = new DonkeyKongJr(DK_INITIAL_X, DK_INITIAL_Y, 1, 0);
                players.add(player1);

            }

            // Moves player1 to the right
            if (instruction.equals("r1")) {

                player1.setPositionX(PIXELS_RIGHT_LEFT);

            // Moves player1 to the left
            } else if (instruction.equals("l1")) {

                player1.setPositionX(PIXELS_RIGHT_LEFT * -1);

            // Moves player1 upwards
            } else if (instruction.equals("u1")) {

                player1.setPositionY(PIXELS_UP_DOWN);

            // Moves player1 downwards
            } else if (instruction.equals("d1")) {

                player1.setPositionY(PIXELS_UP_DOWN * -1);

            }

        // Instructions for player2
        } else if (instruction.contains("2")) {

            // Checks if the player2 is already instantiated or not
            if (players.size() == 1) {

                player2 = new DonkeyKongJr(0,0, 1, 0);
                players.add(player2);

            }

            // Moves player2 to the right
            if (instruction.equals("r2")) {

                player2.setPositionX(PIXELS_RIGHT_LEFT);

            // Moves player2 to the left
            } else if (instruction.equals("l2")) {

                player2.setPositionX(PIXELS_RIGHT_LEFT * -1);

            // Moves player2 upwards
            } else if (instruction.equals("u2")) {

                player2.setPositionY(PIXELS_UP_DOWN);

            // Moves player2 upwards
            } else if (instruction.equals("d2")) {

                player2.setPositionY(PIXELS_UP_DOWN * -1);

            }

        }

        // The server always has to notify clients no matter what string it receives
        output = new DataOutputStream(client.getOutputStream());
        writeObject(output);

    }

    /**
     * Description: return the full data of players, fruits and alligators in one simple string.
     */
    private String getData() {

        String data = "";
        ArrayList<Fruit> fruitsTemporal;
        ArrayList<Alligator> alligatorsTemporal;

        for (int i = 0; i < players.size(); i++) {

            // Player does not exist
            if (players.get(i) == null) {

                data += "dk,_,_;" +
                        "f1,_,_;f2,_,_;f3,_,_;f4,_,_f5,_,_f6,_,_;" +
                        "c1,_,_,_;c2,_,_,_;c3,_,_,_;c4,_,_,_;c5,_,_,_;c6,_,_,_;" +
                        "/";

            } else {

                players.get(i).colisions(fruits1, alligators2);

                data += "dk," + players.get(i).getPositionX() + "," + players.get(i).getPositionY() + ";";
                       // + players.get(i).getLifes() + "," + players.get(i).getScore() + ";";

                if (i == 0) {

                    fruitsTemporal = fruits1;
                    alligatorsTemporal = alligators1;

                } else {

                    fruitsTemporal = fruits2;
                    alligatorsTemporal = alligators2;

                }

                for (int j = 0; j < fruitsTemporal.size(); j++) {

                    // Fruit does not exist
                    if (fruitsTemporal.get(j) == null) {

                        data += "f" + (j + 1) + ",_,_;";

                    } else {

                        data += "f" + (j + 1) + "," + fruitsTemporal.get(j).getPositionX() + "," +
                                fruitsTemporal.get(j).getPositionY()+ ";";

                    }

                }

                for (int k = 0; k < alligatorsTemporal.size(); k++) {

                    // Alligator does not exist
                    if (alligatorsTemporal.get(k) == null) {

                        data += "c" + (k + 1) + ",_,_,_;";

                    } else {

                        alligatorsTemporal.get(k).setPositionY(PIXELS_UP_DOWN);

                        data += "c" + (k + 1) + "," + alligatorsTemporal.get(k).getColor() + "," +
                                alligatorsTemporal.get(k).getPositionX() + "," + alligatorsTemporal.get(k).getPositionY() + ";";

                    }

                }

                data += "/";
                //data += "/" + players.get(i).getLifes() + "," + players.get(i).getScore();
                players.get(i).colisions(fruits1, alligators1);;

            }

        }

        return data;

    }

}
