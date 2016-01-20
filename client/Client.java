package client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import main.ControlCenter;


public class Client extends Thread{
	
	Socket socket;
	InputStreamReader streamReader;
	BufferedReader in;
	PrintWriter out;
	
	String host_address;
	int portNumber;
	String dispName;
	ControlCenter control;
	
	public Client(ControlCenter control){
		this.control = control;
		portNumber = 1234;
	}
	
    public void initializeClient(String host, int port, String dispName){
    	host_address = host;
    	portNumber = port;
    	this.dispName = dispName;
    }
	
	public void run(){
		while(true){
			try {
			//Create a new Socket	
				socket = new Socket(host_address, portNumber);
				System.out.println("Client - Created Socket");
				break;
			//If we cannot connect, we will retry in 1 second until we can connect	
			} catch (Exception e){
				System.out.println("Connection Failed - Attempting to Reconnect");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
				}
			}
		}
		
		//INITIALIZE THE STEAMS
		try {
		//Get the input stream from the Socket
				socket = new Socket(host_address, portNumber); //create the socket again, we know we are able to, but we just dont have the same visibility above (fixes problem)

				streamReader = new InputStreamReader(socket.getInputStream());	
	
				//Reads text from a character-input stream, buffering characters so as to provide the efficient reading of characters, arrays, and lines.
				in = new BufferedReader(streamReader);
				
				//"out" is the OUTPUT sending TO the client. out ---- > Client
			    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		
		//This is messed up, when	    
		while(!in.ready()){
			String line = in.readLine();
			if(line != null){
				//Display it to the GUI textArea
				System.out.println(line);
				control.printMessage(line, dispName);
			} else {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}
			}
		}

		} catch(Exception e){
			
		}
		
		
		//Send messages to server from GUI, the server will send the messages to all listeners.
		
		//Lets print out the string
	}
	
	private void shutdown(){
		try {
		in.close();
		out.close();
		} catch (Exception e){
			System.out.println("Could not close down client");
		}
	}
	

}
