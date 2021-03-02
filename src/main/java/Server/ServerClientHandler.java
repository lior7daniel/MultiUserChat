package Server;



import Client.ClientGUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServerClientHandler extends Thread{
    
    private Server myServer;
    private ServerGUI myServerGUI;
    
    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;
    
    
    public ServerClientHandler(Server myServer, ServerGUI myServerGUI, Socket clientSocket){
        this.myServer = myServer;
        this.myServerGUI = myServerGUI;
        this.clientSocket = clientSocket;
        try {
            this.reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.writer = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println(this.getId() + " is now running!");
        MessagesListener();
    }
    
    private void MessagesListener(){
        String line;
            while(true){
                try {
                    line = reader.readLine();
                    if(line != null){
                        myServer.sendBroadcastMessage(line);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ServerClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
    }
    
    protected void MessagesTransmitter(String str){
        writer.println(str);
    }
    
    public String toString(){
        return String.valueOf(this.getId());
    }
    
}
