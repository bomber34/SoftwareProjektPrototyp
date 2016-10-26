package pc_client_gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * <head><b>Server Client Panel<\b><\head>
 * <br>
 * Panel that builds a connection to a certain IP and disconnects if wanted
 * @author PC
 *
 */
@SuppressWarnings("serial")
public class SCPanel extends JPanel implements ActionListener{

	private String ip;
	private JTextField ipField;
	private JLabel connectionStatus;
	private JButton startConnBtn;
	
	private Socket socket;
	
	public SCPanel()
	{
		ip = "127.0.0.1";
		ipField = new JTextField("127.0.0.1");
		connectionStatus = new JLabel("Inactive");
		
		startConnBtn = new JButton("Connect");
		startConnBtn.addActionListener(this);
		
		socket = new Socket();
		
		this.setLayout(new BorderLayout());
		this.add(startConnBtn, BorderLayout.WEST);
		this.add(ipField, BorderLayout.CENTER);
		this.add(connectionStatus, BorderLayout.EAST);
	}

	// 2.247.253.158
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		ip = ipField.getText();
		InetAddress address;
		try{
			socket = new Socket(ip, 1337);
			address = socket.getInetAddress();
			System.out.println("connected to " + address);
			connectionStatus.setText("Connected");
			Scanner serverScanner = new Scanner(socket.getInputStream());
			socket.close();
			serverScanner.close();
		}catch(UnknownHostException unknownExc){
			connectionStatus.setText("Unknown host");
		}catch(IOException ioExc)
		{
			connectionStatus.setText("Connection failed");
			System.err.println(ioExc.getMessage());
		}
	}
}
