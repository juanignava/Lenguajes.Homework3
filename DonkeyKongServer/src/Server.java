import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    private static void convertMessageArray(String message) { //String[][]

        String[] array1 = message.split(";");

        Integer array1Length = array1.length;

        String[][] array2 = new String[array1Length][];

        Integer n = 0;

        for (String i : array1) {

            array2[n] = i.split(",");
            n++;

        }

        for (String[] a : array2) {

            for (String b : a) {

                System.out.println(b);

            }

            System.out.println("---------");

        }





    }

    public static void main(String[] args) {

        //String str = "dk,100,400;f1,200,200;f2,_,_;cr1,300,400;ca1,550,600";
        //convertMessageArray(str);

        DataInputStream input = null;
        ServerSocket serverSocket = null;
        DataOutputStream output = null;
        BufferedReader buffer = null;

        try {

            // Creating the socket with the specified port.
            serverSocket = new ServerSocket(6666);
            System.out.println("Server is Waiting for client request... ");

            // Listening a connection to be made between the server and the client.
            Socket socket = serverSocket.accept();

            input = new DataInputStream(socket.getInputStream());

            output = new DataOutputStream(socket.getOutputStream());

            buffer = new BufferedReader(new InputStreamReader(System.in));

            String strFromClient = "";
            String strToClient = "";

            while (!strFromClient.equals("stop") || !strToClient.equals("stop")) {

                DataSocket dataSend = new DataSocket(buffer.readLine());
                dataSend.writeObject(output);
                output.flush();
                strToClient = dataSend.message;
                System.out.println("Enviado: " + dataSend.message);

                DataSocket dataReceive = new DataSocket("");
                dataReceive.readObject(input);
                strFromClient = dataReceive.message;
                System.out.println("Recibido: " + dataReceive.message);

            }

        } catch (Exception exe) {

            exe.printStackTrace();

        }

        finally {

            try {

                if (buffer != null) {

                    buffer.close();

                }

                if (input != null) {

                    input.close();

                }

                if (output != null) {

                    output.close();

                }

                if (serverSocket != null) {

                    /*
                     * closes the server socket.
                     */
                    serverSocket.close();

                }

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

    }

}
