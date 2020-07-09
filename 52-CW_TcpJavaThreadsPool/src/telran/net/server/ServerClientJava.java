package telran.net.server;
import java.net.*;

import javax.management.RuntimeErrorException;

import telran.net.*;

import java.io.*;
public class ServerClientJava implements Runnable {
	private final int CLIENT_WAIT = 5000;
	public static boolean running = true;
	ObjectInputStream input;
	ObjectOutputStream output;
	Socket socket;
	ProtocolJava protocol;
	
	@Override
	public void run() {
		try {
			while(running) {
				try {
					RequestJava request = (RequestJava) input.readObject();
					ResponseJava response = protocol.getResponse(request);
					output.writeObject(response);
				} catch (SocketTimeoutException e) {
				}
			}
			socket.close();
		}catch (Exception e) {
			System.out.println("Illegal closing exception");
		}
	}
	public ServerClientJava(Socket socket, ProtocolJava protoocl) {
		this.socket = socket;
		try {
			socket.setSoTimeout(CLIENT_WAIT);
		} catch (SocketException e1) {
		}
		this.protocol = protoocl;
		try {
			input = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		
		
	}
}
