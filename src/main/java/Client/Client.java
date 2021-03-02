package Client;



import Server.ServerClientHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client extends Thread{

    	private ClientGUI myClientGUI;
        private String ipAdress;
        private int port;
        private Socket myClientSocket;
	private BufferedReader reader;
        private PrintWriter writer;
        
	public Client(ClientGUI myClientGUI, String ipAdress, int port) {
            this.myClientGUI = myClientGUI;
            this.port = port;
            this.ipAdress = ipAdress;
	}
	
	@Override
	public void run() {           
            connectToServer();
            MessagesListener();
        }
        
        
        private void connectToServer(){
            try {
                this.myClientSocket = new Socket(ipAdress, port);
            } catch (IOException ex) {
                Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        
        private void MessagesListener(){
                try {
                    this.reader = new BufferedReader(new InputStreamReader(myClientSocket.getInputStream()));
                    this.writer = new PrintWriter(myClientSocket.getOutputStream(), true);
                    String line;
                    while(!myClientSocket.isClosed()){
                        line = reader.readLine();
                        if(line != null){
                            myClientGUI.setTextArea(line);
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    protected void sendBroadcastMessage(String str){
        writer.println(str);
    }
        
        
}
