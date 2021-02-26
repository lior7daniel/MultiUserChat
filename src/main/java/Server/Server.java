package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private int port = 1991;
	private ServerSocket serverSocket;

	public Server() {
		runServer();
	}

	private void runServer() {
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Waiting for connection...");
			while(true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println(clientSocket.getPort() + " is now online!");
                                ClientHandler client = new ClientHandler(clientSocket);
				client.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}


	}



	public static void main(String[] args) {
		Server myServer = new Server();
	}

}
