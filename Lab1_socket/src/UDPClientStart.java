import java.util.Scanner;

public class UDPClientStart {
    public static void main(String[] args){
        final String SERVER_HOST = Constants.HOST;
        final int SERVER_PORT = 15000;

        UDPClient client = new UDPClient(SERVER_HOST,SERVER_PORT);
        Scanner sc = new Scanner(System.in);
        System.out.print("Message: ");
        String msg = sc.nextLine();
        String result = client.request(msg);
        System.out.println("Received Data from Server: " + result);
    }
}