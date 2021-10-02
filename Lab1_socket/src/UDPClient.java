import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;

public class UDPClient {
    private String host;
    private int port;

    public UDPClient(){
        this.host = "localhost";
        this.port = 15000;
    }
    public UDPClient(String host, int port){
        this.host = host;
        this.port = port;
    }

    public String request(String msg) {
        String upper_msg="";
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetSocketAddress serverAdd = new InetSocketAddress(this.host, this.port);
            DatagramPacket datagramPacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length, serverAdd);
            clientSocket.send(datagramPacket);
            byte[] modified_msg = new byte[2048];
            DatagramPacket receivedPacket = new DatagramPacket(modified_msg, modified_msg.length);
            clientSocket.receive(receivedPacket);
            upper_msg = new String(receivedPacket.getData()).trim();
            clientSocket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return upper_msg;
    }
}