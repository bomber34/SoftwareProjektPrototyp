package pc_client;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

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
			//"141.44.217.184", 8888
			//"www.javatutorial.com", 80
			socket = new Socket();
			address = socket.getInetAddress();
			System.out.println("Connected to " + address);
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
