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
        private static ArrayList<ServerClientHandler> clientHandlersArray = new ArrayList<ServerClientHandler>();


	public Server(ServerGUI serverGUI) {
            this.myServerGUI = serverGUI;
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
                            ServerClientHandler newClientHandler = new ServerClientHandler(this, myServerGUI, newClientSocket);
                            newClientHandler.start();
                            clientHandlersArray.add(newClientHandler);
                            System.out.println(clientHandlersArray.isEmpty());
                            sendBroadcastMessage(newClientHandler.getId() + " is now online!");
                        }
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

        protected void sendBroadcastMessage(String str){
            for(ServerClientHandler clientHandler : clientHandlersArray) clientHandler.MessagesTransmitter(str);
            myServerGUI.setTextArea(str);   
        }
        
        
        
        protected void shutDown() throws IOException{
            serverSocket.close();
        }
        
        
        
}
