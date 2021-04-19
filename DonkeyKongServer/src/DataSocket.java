import java.io.*;

public class DataSocket implements Serializable {

    public Integer messageLength;
    public String message;

    public DataSocket(String message) {

        if (message != null) {

            this.messageLength = message.length();
            this.message = message;

        }

    }

    public void writeObject(java.io.DataOutputStream output) throws IOException {

        output.writeInt(messageLength + 1);
        output.writeBytes(message);
        output.writeByte('\0');

    }

    public void readObject(java.io.DataInputStream input) throws IOException {

        messageLength = input.readInt() - 1;

        byte [] aux = null;
        aux = new byte[messageLength];

        input.read(aux, 0, messageLength);
        message = new String(aux);

        input.read(aux, 0, 1);

    }

}