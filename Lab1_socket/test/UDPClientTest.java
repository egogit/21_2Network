import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UDPClientTest {

    @Test
    void request() {
        UDPClient client = new UDPClient(Constants.HOST,15000);
        assertEquals("THIS IS UDP TESTING", client.request("this is udp testing"));
    }
}