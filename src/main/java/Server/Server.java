package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{

	private final int port = 1991;
	private ServerSocket serverSocket;
        
        ServerGUI myServerGUI;

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
				System.out.println();
                                myServerGUI.setTextArea("ServerMSG: " + clientSocket.getPort() + " is now online!");
                                ClientHandler client = new ClientHandler(clientSocket);
				client.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

        protected void shutDown() throws IOException{
            serverSocket.close();
        }
        
        
        
}
