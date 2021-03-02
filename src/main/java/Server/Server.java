package Server;



import Client.Client;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Thread{
    
    
    private ServerGUI myServerGUI;
    private final int port = 1991;
    private ServerSocket serverSocket;
    private static ArrayList<ServerClientHandler> onlineUsersArray;
    
    
    public Server(ServerGUI serverGUI) {
        this.myServerGUI = serverGUI;
        this.onlineUsersArray = new ArrayList<ServerClientHandler>();
    }
    
    @Override
    public void run(){
        runServer();
    }
    
    private void runServer() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Waiting for connection...");
            while(true) {
                Socket newClientSocket = serverSocket.accept();
                System.out.println("new client online!");
                ServerClientHandler newClientHandler = new ServerClientHandler(this, myServerGUI, newClientSocket);
                newClientHandler.start();
                onlineUsersArray.add(newClientHandler);
                System.out.println("onlineUsersArray: " + onlineUsersArray.toString());
                //sendBroadcastMessage(newClientHandler.getId() + " is now online!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    synchronized protected void sendBroadcastMessage(String str){
        if(!onlineUsersArray.isEmpty()){
            for(ServerClientHandler clientHandler : onlineUsersArray) clientHandler.MessagesTransmitter(str);
            myServerGUI.setTextArea(str);
        }
        else myServerGUI.setTextArea("There is no online users!");
    }
    
    protected void shutDown() throws IOException{
        serverSocket.close();
    }
    
    
    
}
