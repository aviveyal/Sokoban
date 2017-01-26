package Client;


public class Run {

	public static void main(String[] args) {
		String ip=args[0];
		int port = Integer.parseInt(args[1]);
		Cliclient client = new Cliclient();
		client.start(ip, port);
	}

}