import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) {
        final int SERVER_PORT = 15000;
        OutputStream modified_msg;
        try {
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            while(true) {
                System.out.println("The Server is Listening!!!");
                Socket socket = serverSocket.accept();
                System.out.println("The client " + socket.getInetAddress() + " is connected");
                InputStream msg = socket.getInputStream();
                byte[] bytes = new byte[2048];
                int length = msg.read(bytes);
                String origin_msg = new String(bytes, 0, length);
                System.out.println("Received Data: " + origin_msg);
                String msg2 = origin_msg.toUpperCase();
                modified_msg = socket.getOutputStream();
                modified_msg.write(msg2.getBytes());
                if(msg2.isEmpty()){
                    System.out.println("Blank messages is received > Server Shutdown");
                    break;
                }
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }
}