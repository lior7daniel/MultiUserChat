package Server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ClientHandler extends Thread{

	private Socket clientSocket;
	
	public ClientHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	@Override
	public void run() {
			try {
				handleClientSocket();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	
	private void handleClientSocket() throws IOException, InterruptedException {
		OutputStream outputStream = clientSocket.getOutputStream();
		outputStream.write(("Welcome! you are online!\r\n\r\n").getBytes());
		for(int i = 5; i > 0; i--) {
			outputStream.write((i + " seconds to disconnect.\r\n").getBytes());
			Thread.sleep(1000);
		}
		clientSocket.close();
	}
	
}
