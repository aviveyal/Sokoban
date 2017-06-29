package controller.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

import model.MyModel;

/**
 * 
 * @author Aviv Eyal Cli client handler
 */

public class MyClientHandler extends Observable implements ClientHandler {

	private InputStream in;
	private OutputStream out;
	boolean isStopped = false;

	public MyClientHandler(InputStream in, OutputStream out) {
		this.in = in;
		this.out = out;
	}

	public InputStream getIn() {
		return in;
	}

	public void setIn(InputStream in) {
		this.in = in;
	}

	public OutputStream getOut() {
		return out;
	}

	public void setOs(OutputStream out) {
		this.out = out;
	}

	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(inFromClient));
		PrintWriter writer = new PrintWriter(outToClient);
		String clientIn;
		;
		System.out.println("client connected");
		String msgtoclient = "";

		while (!isStopped) {

			writer.write(">load filename\n>display\n>move {up,dpwn,right,left}\n>save filename\n>exit\n");

			writer.flush();

			try {
				clientIn = reader.readLine();

				if (clientIn != null) {
					System.out.println(clientIn);
					String[] arr = clientIn.split(" ");
					List<String> params = new LinkedList<String>();

					for (String s : arr) {
						params.add(s);
					}

					setChanged();
					notifyObservers(params);

					if (clientIn.equals("exit")) {

						// isStopped=true;
						msgtoclient = "bye";

					}

					if (!msgtoclient.equals(null)) {
						writer.write(msgtoclient);
						writer.flush();
					}
				}
			}

			catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}

		}

	}

}
