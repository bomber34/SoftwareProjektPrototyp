package pc_client;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class AppClient
{
	private JFrame window;
	
	public AppClient()
	{
		window = new JFrame("APP CLIENT");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(800, 600);
		
		JPanel dataPanel = new DataPanel();
		window.add(dataPanel);
		
		InetAddress address;
		Socket socket = null; 
		
		try
		{
			
			//"www.javatutorial.com", 80 ... TestURL die definitiv klappt ...
			socket = new Socket("localhost",80);
			address = socket.getInetAddress();
			System.out.println("Connected to " + address);
			Scanner serverScanner = new Scanner(socket.getInputStream());
			socket.close();
		}
		catch(java.io.IOException e)
		{
			System.err.println("CONNECTION ERROR");
		}
		
		
		window.setVisible(true);
	}
	
	
	public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run() 
			{
				new AppClient();
				
			}
		});
	}
}
