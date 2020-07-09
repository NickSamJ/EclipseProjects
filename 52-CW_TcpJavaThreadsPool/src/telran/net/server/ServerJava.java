package telran.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerJava implements Runnable {
private static final int N_THREADS = 4;
private static final int ACCEPT_TIMEOUT = 10_000;
private static final int CLIENT_TIMEOUT = 10;
ProtocolJava protocol;
ServerSocket serverSocket;
ExecutorService threadsPool;
int port;
	
	@Override
	public void run() {
		try {
			while(!threadsPool.isShutdown()) {
				try {
					Socket socket = serverSocket.accept();
					socket.setSoTimeout(CLIENT_TIMEOUT);
					ServerClientJava client = new ServerClientJava(socket, protocol);
					if(!threadsPool.isShutdown()) {
						threadsPool.execute(client);
					}
				} catch (SocketTimeoutException e) {
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	public ServerJava(ProtocolJava protocol, int port, int nThreads) {
		threadsPool = Executors.newFixedThreadPool(nThreads);
				
		this.protocol = protocol;
		this.port = port;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.setSoTimeout(ACCEPT_TIMEOUT);
			System.out.println("Server is listening on port "+ port);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public ServerJava(ProtocolJava protocol, int port) {
		this(protocol, port, N_THREADS);
	}
	public void stop() {
		threadsPool.shutdown();
		ServerClientJava.running = false;
		System.out.print("\nI`m finishing");
		beautifulEnding();
	}
	private void beautifulEnding() {
		for(int i = 0; i < 5; i++) {
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {}
			System.out.print(" . ");
		}
	}

}
