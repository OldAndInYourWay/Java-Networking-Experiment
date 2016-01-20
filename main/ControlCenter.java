package main;

import server.Server;
import client.Client;

//The purpose of this class is to be the intermediate class that actually tells the client or the server to perform a task based on input
//from the GUI. So if on the GUI, as a client, the user tries to send a message, the GUI will alert this class which will alert the CLIENT class to transmit the message.
//This class is also responsible for the creation of the Client and Server classes from the Radiobutton.
public class ControlCenter {
	GUI gui;
	Client client;
	Server server;
	
	public ControlCenter(){
		//Startup the GUI, pass it this. We wait for the gui to ask for a client or a server, then we have a method that either creates a client or a server.
		gui = new GUI(this);
		server = new Server(this);
		client = new Client(this);
		
		
	}
	
	public static void main (String [] args){
		new ControlCenter();
	}

	public void setupServer(int port, String dispName) {
		server.initializeServer(port, dispName);
		server.start();
		
	}

	public void setupClient(String host, int port, String dispName) {
		client.initializeClient(host, port, dispName);
		client.start();
	}

	public void printMessage(String line, String hostName) {
		gui.print(hostName + ": " + line + "\n");
	}

	//The server sends a message to the client
	public void serverMessage(String message) {
		//sets the message to send
		server.setMessage(message); //which does 2 things. 1) Sends to out through the socket, 2) prints to the text area
	}

	//The client sends a message to the server
	public void clientMessage(String message) {
		
	}

}
