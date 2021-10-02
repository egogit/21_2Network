import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UDPServer {
    public static void main(String[] args) {
        final int SERVER_PORT = 15000;
        final String SERVER_HOST = "localhost";
        try {
            InetSocketAddress serverAdd = new InetSocketAddress(SERVER_HOST, SERVER_PORT);
            DatagramSocket serverSocket = new DatagramSocket(serverAdd);
            System.out.println("The Server is ready to receive");
            while (true) {
                byte[] msg = new byte[2048];
                DatagramPacket receivedPacket = new DatagramPacket(msg, msg.length);
                serverSocket.receive(receivedPacket); 
                String origin_msg = new String(receivedPacket.getData()).trim();
                System.out.println("Received Data: " + origin_msg);
                String modified_msg = origin_msg.trim().toUpperCase();
                DatagramPacket datagramPacket = new DatagramPacket(modified_msg.getBytes(),modified_msg.getBytes().length, receivedPacket.getSocketAddress());
                serverSocket.send(datagramPacket);
                System.out.println("Modified Msg \"" + modified_msg+ "\" is transferred");
                if(origin_msg.isEmpty()) break;
            }
            serverSocket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}