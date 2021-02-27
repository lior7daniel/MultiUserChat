package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread{

	private final int port = 1991;
	private ServerSocket serverSocket;
        private ArrayList<Socket> onlineSocketsArray = new ArrayList<Socket>();
        
        private ServerGUI myServerGUI;

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
				Socket clientSocket = serverSocket.accept();
                                ClientHandler clientHandler = new ClientHandler(myServerGUI, clientSocket);
				clientHandler.start();
                                onlineSocketsArray.add(clientSocket);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

        protected void shutDown() throws IOException{
            serverSocket.close();
        }
        
        
        
}
