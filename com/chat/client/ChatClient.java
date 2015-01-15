package com.chat.client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient implements Runnable {
	// The client socket
	private static Socket clientSocket = null;
	// The output stream
	private static PrintStream os = null;
	// The input stream
	private static BufferedReader is = null;
	private static BufferedReader inputLine = null;
	private static boolean closed = false;
	public void getClient(String host,int portNumber){	
		System.out.println("Welcome to the Chat room!!!!");
		try {
			clientSocket = new Socket(host, portNumber);
			inputLine = new BufferedReader(new InputStreamReader(System.in));
			os = new PrintStream(clientSocket.getOutputStream());
			is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			URL ipAddress=new URL("http://checkip.amazonaws.com/");
			BufferedReader in=new BufferedReader(new InputStreamReader(ipAddress.openStream()));
			String pubIpAddress=in.readLine();
			os.println(pubIpAddress);
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + host);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to the host "+ host);
		}
		/*
		 * If everything has been initialized then we want to write some data to the
		 * socket we have opened a connection to on the port portNumber.
		 */
		if (clientSocket != null && os != null && is != null) {
			try {
				/* Create a thread to read from the server. */
				new Thread(new MultiClient()).start();
				while (!closed) {
					os.println(inputLine.readLine().trim());
				}
				/*
				 * Close the output stream, close the input stream, close the socket.
				 */
				os.close();
				is.close();
				clientSocket.close();
			} catch (IOException e) {
				System.err.println("IOException: " + e);
			}
		}
	}
	/*
	 * Create a thread to read from the server.
	 */
	public void run() {
		/*
		 * Keep on reading from the socket till we receive "Bye" from the
		 * server. Once we received that then we want to break.
		 */
		String responseLine;
		try {
			while ((responseLine = is.readLine()) != null) {
				System.out.println(responseLine);
				if (responseLine.indexOf("Bye") != -1)
					break;
			}
			closed = true;
		} catch (IOException e) {
			System.err.println("IOException: " + e);
		}
	}
}