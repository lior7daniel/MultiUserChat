package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientHandler extends Thread{

	private ServerGUI myServerGUI;
        private Socket clientSocket;
        private OutputStream outputStream;
        private InputStream inputStream;

	
	public ClientHandler(ServerGUI myServerGUI, Socket clientSocket) {
		this.clientSocket = clientSocket;
                this.myServerGUI = myServerGUI;
	}
	
	@Override
	public void run() {           
            myServerGUI.setTextArea("ServerMSG: " + clientSocket.getPort() + " is now online!");
            
            try {
                    handleClientSocket();
            } catch (IOException e) {
                    e.printStackTrace();
            } catch (InterruptedException e) {
                    e.printStackTrace();
            }
	}
	
        // while there is input from the clientSocket, write it into the serverGUI textArea.
	private void handleClientSocket() throws IOException, InterruptedException {
                
            
              
	}
	
}
