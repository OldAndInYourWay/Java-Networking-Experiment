package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import main.ControlCenter;


public class Server extends Thread {
	int port;
	ControlCenter control;
	ServerSocket server;
	Socket socket;
	
	BufferedReader in;
	PrintWriter out;
	String message;
	String dispName;
	
	public Server(ControlCenter control){
		this.control = control;
		port = 1234;
	}
	
	public void run() {
		
		
		try {
		//The ServerSocket sets the server up to listen to a specific port.	
		server = new ServerSocket(port);
		
		
		//The .accept() method waits until a client tries to connect to the server. 
		//and when a client tries to connect, we create the socket variable which is a tunnel to the client. (IE: this is what we use to communicate with the client)
		socket = server.accept();  
		message = "Someone connected at: " + socket.getInetAddress() + " - Port: " + socket.getPort();
		//System.out.println("Server has connected to a client!\n");
		
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		//"out" is the OUTPUT sending TO the client. out ---- > Client
		out = new PrintWriter(socket.getOutputStream(), true);
		
		
		
		//A structure that just keeps listening for in and waiting to send messages as well
		while(true){
			System.out.println("fuck");
			if(message != null){
				sendMessage(message); //initial message upon connection
				message = null;		  //clear the message
			}
		}
		
		
		
		} catch (Exception e){
			System.out.println("Something didn't work!");
		}
	}
	
	public void sendMessage(String message){
		control.printMessage(message, dispName);
		out.print(message);
	}
	
	public void initializeServer(int port, String dispName) {
		this.port = port;
		this.dispName = dispName;
	}

	//Sets the message to a non-null value, which will get picked up by the while loop
	public void setMessage(String m) {
		this.message = m;
	}

}
