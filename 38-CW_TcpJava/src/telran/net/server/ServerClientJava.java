package telran.net.server;
import java.net.*;

import javax.management.RuntimeErrorException;

import telran.net.*;

import java.io.*;
public class ServerClientJava implements Runnable {

	ObjectInputStream input;
	ObjectOutputStream output;
	Socket socket;
	ProtocolJava protocol;
	
	@Override
	public void run() {
		try {
			while(true) {
				RequestJava request = (RequestJava) input.readObject();
				ResponseJava response = protocol.getResponse(request);
				output.writeObject(response);
				
				
			}
		}catch(EOFException e) {
			System.out.println("Client closed connection");
		} 
		catch (Exception e) {
			System.out.println("Illegal closing exception");
		}
	}
	public ServerClientJava(Socket socket, ProtocolJava protoocl) {
		this.socket = socket;
		this.protocol = protoocl;
		try {
			input = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		
		
	}
}
