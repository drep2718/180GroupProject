import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
  
    private Socket socket;
    private boolean connected;

    public Client() {
        this.connected = false;
    }
   
    public boolean connectToServer(String serverAddress, int port) {
    
    }

    public void disconnectFromServer() {
     
    }

    public boolean isConnected() {
        return connected;
    }

    public boolean login(String username, String password) {
       
    }

    public void sendRequest(String request) {
  
    }

    public String receiveResponse() {
      
    }

}

