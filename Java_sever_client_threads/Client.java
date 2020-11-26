package hw2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	Socket serverSocket;
	String serverHostName = "localhost";
	int serverPortNumber = 6666;
	ServerListener sl;
	Boolean isConnected = false;
	String message;

	Client() throws IOException {
		// 1. CONNECT TO THE SERVER
		try {
			serverSocket = new Socket(serverHostName, serverPortNumber);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 2. SPAWN A LISTENER FOR THE SERVER. THIS WILL KEEP RUNNING
		// when a message is received, an appropriate method is called
		sl = new ServerListener(this, serverSocket);
		new Thread(sl).start();

		// 3. GET VERIFICATION MESSAGE FROM SYSTEMIN
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("> Enter your name:");
		String name = br.readLine();

		br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("> Enter access code:");
		String accesscode = br.readLine();

		PrintWriter out;
		try {
			out = new PrintWriter(new BufferedOutputStream(serverSocket.getOutputStream()));

			// 4. SEND VERIFICATION MESSAGE TO THE SOCKET TO SEND TO THE SERVER
			out.println(name);
			out.println(accesscode);
			out.flush(); // forces data from buffer to be sent to server

			while (true) { // client send message to server
				if (isConnected == true) {
					br = new BufferedReader(new InputStreamReader(System.in));
					System.out.print("write your message >");
					message = br.readLine();
					out.println(message);
					out.flush();
				}
			}
			// client dies here
			// out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void handleMessage(Client c, String s) {
		Client client = c;

		if (s.equals("incorrect access code")) { // the client is prompted again to enter the access code
			System.out.println("incorrect access code");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("> Enter access code:");
			try {
				String accesscode = br.readLine();

				PrintWriter out;
				try {
					out = new PrintWriter(new BufferedOutputStream(client.serverSocket.getOutputStream()));
					out.println(accesscode);
					out.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (s.equals("you are connected")) { // the server now connect to client
			System.out.println("you are connected");
			client.isConnected = true;
			// System.out.println("server sent message: " + s);
		} else { // The REAL MESSAGE FROM OTHER CLIENT
			// TODO display message from other clients (server)
			System.out.println(s);
		}

	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		Client client = new Client();
	}

}

class ServerListener implements Runnable {
	Client client;
	Scanner in; // this is used to read which is a blocking call

	ServerListener(Client client, Socket s) {
		try {
			this.client = client;
			in = new Scanner(new BufferedInputStream(s.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) { // run forever + keep listenning from server

			if (in.hasNext()) {
				String s = in.nextLine();
				client.handleMessage(client, s);

			} else { // in case server disconnected before client do
				System.out.println("lost connection to the server");
				break;
			}

		}

	}
}
