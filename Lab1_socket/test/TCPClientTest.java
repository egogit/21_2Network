import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TCPClientTest {

    @Test
    void request() {
        TCPClient client = new TCPClient(Constants.HOST,15000);
        assertEquals("THIS IS TCP TESTING", client.request("this is tcp testing"));
    }
}