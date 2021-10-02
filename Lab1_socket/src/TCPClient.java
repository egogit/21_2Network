import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TCPClient {
    private String host;
    private int requestPort;

    public TCPClient() {
        this.host = "localhost";
        this.requestPort = 15000;
    }

    public TCPClient(String host, int port) {
        this.host = host;
        this.requestPort = port;
    }

    public String request(String msg) {
        String upper_msg="";
        try{
            Socket socket = new Socket();
            System.out.println("Requesting Connection...");
            socket.connect(new InetSocketAddress(this.host, this.requestPort));
            System.out.println("Connection Success!");
            OutputStream outputs = socket.getOutputStream();
            outputs.write(msg.getBytes());
            InputStream inputs = socket.getInputStream();
            byte[] bytes = new byte[2048];
            int length = inputs.read(bytes);
            upper_msg = new String(bytes, 0, length);
            System.out.println(socket.getRemoteSocketAddress());
            socket.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return upper_msg;
    }
}