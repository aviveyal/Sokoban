package controller.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * 
 * @author Aviv Eyal controller of the cli clients
 */
public class server {

	private int port;
	private ClientHandler ch;
	private volatile boolean stop;

	public server(int port, ClientHandler ch) {
		this.port = port;
		this.ch = ch;
		stop = false;
	}

	private void runServer() throws Exception {
		ServerSocket server = new ServerSocket(port);
		System.out.println("the port is: " + port);
		server.setSoTimeout(1000);
		while (!stop) {
			try {
				Socket aClient = server.accept(); // blocking call
				new Thread(new Runnable() { // WOW! we should control the number
											// of threads!
					public void run() {
						try {
							ch.handleClient(aClient.getInputStream(), aClient.getOutputStream());
							aClient.getInputStream().close();
							aClient.getOutputStream().close();
							aClient.close();
						} catch (IOException e) {
							/* ... */}
					}
				}).start();
			} catch (SocketTimeoutException e) {
				/* ... */}
		}
		server.close(); // WOW! we should wait for all threads before closing!
	}

	public void start() {
		new Thread(new Runnable() {
			public void run() {
				try {
					runServer();
				} catch (Exception e) {
				}
			}
		}).start();
	}

	public void stop() {
		stop = true;
	}
}
