package com.chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

public class ClientThreads extends Thread {

	private BufferedReader is = null;
	private PrintStream os = null;
	private Socket clientSocket = null;
	private final List<ClientThreads> threads;
	private String filePath;

	public ClientThreads(Socket clientSocket, List<ClientThreads> threads, String filePath) {
		this.clientSocket = clientSocket;
		this.threads = threads;
		this.filePath=filePath;
	}
	public void run() {
		List<ClientThreads> threads = this.threads;
		try {
			/*
			 * Create input and output streams for this client.
			 */
			is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			os = new PrintStream(clientSocket.getOutputStream());
			String pubipaddress=is.readLine();
			os.println("Hi! What is your name?");
			String name = is.readLine().trim();
			os.println("Welcome " + name
					+ " to our chat room.\n1. To leave enter /quit."+
					"\n2. To share location enter /sharelocation.");
			enterChat(name);
			while (true) {
				String line = is.readLine();
				if (line.startsWith("/quit")) {
					break;
				}else if(line.startsWith("/sharelocation")){
					if(filePath!=null&&!filePath.isEmpty()){
						UserLocation location = (new ChatLocation()).getLocation(pubipaddress,filePath);
						os.println("You have shared your location: "
								+location.getCity()+","+ location.getRegion());
						shareLocation( name, location);
						continue;
					}
				}
				synchronized(this){
					for(ClientThreads value: threads){
						if(value!=this){
							value.os.println("<" + name + "> " + line);
						}
					}
				}
			}
			leaveChat(name);
			os.println("** Bye " + name + "! you will be missed **");
			remove();
			/*
			 * Close the output stream, close the input stream, close the socket.
			 */
			is.close();
			os.close();
			clientSocket.close();
		} catch (IOException e) {
		}
	}
	public synchronized void enterChat(String name){
		for(ClientThreads value: threads){
			if(value!=this){
				value.os.println("** A new user " + name
						+ " entered the chat room !!! **");
			}
		}
	}
	public synchronized void leaveChat(String name){
		for(ClientThreads value: threads){
			if(value!=this){
				value.os.println("** The user " + name
						+ " is leaving the chat room !!! **");
			}
		}
	}
	public synchronized void shareLocation(String name, UserLocation location){
		for(ClientThreads value: threads){
			if(value!=this){
				value.os.println( name + " has shared location :  " + 
						location.getCity()+","+ location.getRegion());
			}
		}
	}
	public synchronized void remove(){
		threads.remove(this);						
	}
}