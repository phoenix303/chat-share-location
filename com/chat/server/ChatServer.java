package com.chat.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatServer {
	private static ServerSocket serverSocket = null;
	// The client socket.
	private static Socket clientSocket = null;
	// List of the clients currently connected to the Server
	private static final List<ClientThreads> threads=Collections.synchronizedList(new ArrayList<ClientThreads>());
	private static final int maxClients=10;
	private String filePath;
	public ChatServer(){}
	public ChatServer(String filePath){
		this.filePath=filePath;
	}
	public void getServer(int portNumber){
		try {
			System.out.println("Welcome server!!");
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			System.out.println(e);
		}

		while (true) {
			try {
				clientSocket = serverSocket.accept();
				PrintStream os = new PrintStream(clientSocket.getOutputStream());
				ClientThreads clientobj=new ClientThreads(clientSocket, threads, filePath);
				threads.add(clientobj);
				clientobj.start();
				if (threads.size() == maxClients) {
					os.println("Server is too busy now. Please try again later.");
					os.close();
					clientSocket.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}	
	}
}