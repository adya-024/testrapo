import java.io.*;
import java.net.*;

public class Client {
    private static final String SERVER_IP = "127.0.0.1"; // or server IP
    private static final int PORT = 1234;

    public static void main(String[] args) {
        try (
            Socket socket = new Socket(SERVER_IP, PORT);
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            System.out.print("Enter your name: ");
            String name = keyboard.readLine();
            out.println(name);

            // Thread to read messages from server
            Thread readerThread = new Thread(() -> {
                try {
                    String serverMsg;
                    while ((serverMsg = in.readLine()) != null) {
                        System.out.println(serverMsg);
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected from server.");
                }
            });
            readerThread.start();

            // Main thread to send messages to server
            String msg;
            while ((msg = keyboard.readLine()) != null) {
                out.println(msg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
