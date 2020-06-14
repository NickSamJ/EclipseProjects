package telran.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerJava implements Runnable {
ProtocolJava protocol;
ServerSocket serverSocket;
int port;
	
	@Override
	public void run() {
		try {
			while(true) {
				Socket socket = serverSocket.accept();
				ServerClientJava client = new ServerClientJava(socket, protocol);
				Thread thread = new Thread(client);
				thread.start();
						
			}
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	public ServerJava(ProtocolJava protocol, int port) {
		super();
		this.protocol = protocol;
		this.port = port;
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server is listening on port "+ port);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
