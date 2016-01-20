package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame {
	
	private JPanel contentPane;
	private JTextField displayName;
	private ControlCenter control; //responsible for passing off tasks to the client / server class
	private ButtonGroup radioButtonsGroup;
	private JRadioButton guestButton;
	private JRadioButton hostButton;
	JTextField portNumber;
	JTextPane hostIpAddress;
	JTextArea textArea;
	JTextArea userMessageInput;
	
	private String hostOrClient;
	
	public GUI(ControlCenter control){
		this.control = control;
		initialize();
		setVisible(true);
	}
	
	public void print(String text){
		textArea.append(text);
	}

	private void initialize() {
		setForeground(Color.WHITE);
		setTitle("Simple Chat Application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		JLabel lblEnter = new JLabel("Type In Your Message and Press \"Send\"");
		
		//USER MESSAGE INPUT
		userMessageInput = new JTextArea();
		userMessageInput.setLineWrap(true);
		userMessageInput.setWrapStyleWord(true);
		
		//SEND BUTTON
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent arg0) {
				String message = userMessageInput.getText();
				if(hostOrClient.compareTo("HOST") == 0){
					control.serverMessage(message);
				} else if(hostOrClient.compareTo("CLIENT") == 0){
					control.clientMessage(message);
				}
				
				userMessageInput.setText(""); //clear input area
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(4)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(userMessageInput, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
								.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
								.addComponent(lblEnter)))
						.addComponent(sendButton))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblEnter)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(userMessageInput, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(sendButton)))
					.addContainerGap())
		);
		
		//HOST IP ADDRESS
		hostIpAddress = new JTextPane();
		hostIpAddress.setText("localhost");
		
		JLabel lblNewLabel = new JLabel("Host IP Address");
		
		JLabel lblPortNumber = new JLabel("Port Number");
		
		//PORT NUMBER
		portNumber = new JTextField();
		portNumber.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		
		JLabel lblDisplayName = new JLabel("Display Name");
		
		displayName = new JTextField();
		displayName.setColumns(10);
		
		//HOST BUTTON
		hostButton = new JRadioButton("Host");
		hostButton.setBackground(Color.LIGHT_GRAY);
		hostButton.setSelected(true);
		
		//GUEST BUTTON
		guestButton = new JRadioButton("Guest");
		
		//RADIO BUTTONS GROUP
		radioButtonsGroup = new ButtonGroup();
		radioButtonsGroup.add(hostButton);
		radioButtonsGroup.add(guestButton);
		
		guestButton.setBackground(Color.LIGHT_GRAY);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(guestButton)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(lblPortNumber))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(portNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(hostIpAddress, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblDisplayName)
							.addGap(18)
							.addComponent(displayName, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
						.addComponent(hostButton)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(hostIpAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPortNumber)
						.addComponent(portNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDisplayName)
						.addComponent(displayName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(52)
					.addComponent(hostButton)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(guestButton)
					.addContainerGap(92, Short.MAX_VALUE))
		);
		
		//CONNECT BUTTON
		JButton connectButton = new JButton("Connect");
		connectButton.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent arg0) {
				String host = "";
				int port = 0;
				String dispName = "";
					
					
					//Specifies whether to instantiate a Host or a Guest
				try {
				//HOST / SERVER
					if(hostButton.isSelected()){ 
						hostOrClient = "HOST";
						System.out.println("HOST BUTTON");
						port = Integer.parseInt(portNumber.getText());
						dispName = displayName.getText();
							if(dispName.compareTo("") != 0){
								control.setupServer(port, dispName);			//Create a Server
							} else {
								System.out.println("Bad Format. Try again.");
							}
				//GUEST /CLIENT
					} else if (guestButton.isSelected()){ 
						hostOrClient = "CLIENT";
						System.out.println("GUEST BUTTON");
					    host = hostIpAddress.getText();
					    port = Integer.parseInt(portNumber.getText());
						dispName = displayName.getText();
							if((dispName.compareTo("") != 0) && (host.compareTo("") != 0)){
								control.setupClient(host, port, dispName);     //Create a Client
							} else {
								System.out.println("Bad Format. Try again.");
							}
					}
				} catch (Exception badFormat){
					System.out.println("Bad Format. Try again.");
				}
			}
		});
		
		JButton disconnectButton = new JButton("Disconnect");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addComponent(connectButton, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(disconnectButton, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(connectButton, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
				.addComponent(disconnectButton, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
		);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
	}

}
