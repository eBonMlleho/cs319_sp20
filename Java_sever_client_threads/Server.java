package hw2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {

	public static void main(String[] args) throws IOException {

		ServerSocket serverSocket = null; // 1. serversocket
		int clientNum = 0; // keeps track of how many clients were created
		ArrayList<ClientsHandler> clients = new ArrayList<ClientsHandler>();
		// ArrayList<Thread> clientsss = new ArrayList<Thread>();
		// 1. CREATE A NEW SERVERSOCKET
		try {
			serverSocket = new ServerSocket(6666); // provide MYSERVICE at port 6666
			// System.out.println(serverSocket);
		} catch (IOException e) {
			System.out.println("Could not listen on port: 6666");
			System.exit(-1);
		}

		// 2. LOOP FOREVER - SERVER IS ALWAYS WAITING TO PROVIDE SERVICE!
		while (true) { // 3.
			Socket clientSocket = null;
			try {

				// 2.1 WAIT FOR CLIENT TO TRY TO CONNECT TO SERVER
				// System.out.println("Waiting for client " + (clientNum + 1) + " to connect!");
				clientSocket = serverSocket.accept(); //

				// 2.2 SPAWN A THREAD TO HANDLE CLIENT REQUEST
				System.out.println("Server is authenticating to client" + ++clientNum);
				ClientsHandler handler = new ClientsHandler(clientSocket, clientNum, clients);
				Thread t = new Thread(handler);
				t.start();
				clients.add(handler);
				// System.out.println(clientNum);

			} catch (IOException e) {
				System.out.println("Accept failed: 6666");
				System.exit(-1);
			}

			// 2.3 GO BACK TO WAITING FOR OTHER CLIENTS
			// (While the thread that was created handles the connected client's
			// request)

		} // end while loop

	} // end of main method

//	public void sendAll() {
//		
//	}

} // end of class Server

class ClientsHandler implements Runnable {
	Socket s; // this is socket on the server side that connects to the CLIENT
	int num; // keeps track of its number just for identifying purposes
	String accessCode = "cs319spring2020";
	public String message; // TODO
	PrintWriter out;
	Boolean isConnected = false;
	ArrayList<ClientsHandler> array;

	ClientsHandler(Socket s, int n, ArrayList<ClientsHandler> array) {
		this.s = s;
		num = n;
		this.array = array;
	}

	// This is the client handling code
	public void run() {

		// PrintWriter out;
		Scanner in;

		try {
			// 1. USE THE SOCKET TO READ WHAT THE CLIENT IS SENDING
			in = new Scanner(new BufferedInputStream(s.getInputStream()));
			String clientName = in.nextLine();
			String clientAccessCode = in.nextLine();

			// 2. Verification

			out = new PrintWriter(new BufferedOutputStream(s.getOutputStream()));

			while (true) { // keep listenning message from client until break (client disconnect)

				if (!accessCode.equals(clientAccessCode)) { // access code unmatched! try AGAIN!
					out.println("incorrect access code");
					out.flush();
					if (in.hasNext()) {
						clientAccessCode = in.nextLine();
					} else {
						System.out.println(clientName + " connection failed!");
						break;
					}
				} else { // access code matched!
					out.println("you are connected");
					out.flush();
					System.out.println(clientName + " is connected successfully!");
					isConnected = true;
					break;
				}

			}

			/**
			 * receive message from client and print out
			 */
			while (isConnected) {
				// if (in.hasNext()) {
				message = clientName + ":" + in.nextLine();
				System.out.println(message);
				// }

				for (ClientsHandler eachClient : array) {

					if (eachClient.isConnected && eachClient != this) {
						Socket tempSocket;
						tempSocket = eachClient.s;
						eachClient.out = new PrintWriter(new BufferedOutputStream(tempSocket.getOutputStream()));

						eachClient.out.println("\n" + message);
						eachClient.out.flush();
					}
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		// This handling code dies after doing all the printing
	} // end of method run()

} // end of class ClientHandler
