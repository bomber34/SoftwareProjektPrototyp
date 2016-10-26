package pc_client_gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AppClient
{
	private JFrame window;
	
	public AppClient()
	{
		window = new JFrame("APP CLIENT");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(800, 600);
		
		JPanel dataPanel = new DataPanel();
		JPanel scpanel = new SCPanel();
		
		JPanel masterPanel = new JPanel(new BorderLayout());
		
		masterPanel.add(dataPanel, BorderLayout.CENTER);
		masterPanel.add(scpanel, BorderLayout.SOUTH);
		
		window.add(masterPanel);
//		InetAddress address;
//		Socket socket = null; 
//		
//		try
//		{
//			
//			//"www.javatutorial.com", 80 ... TestURL die definitiv klappt ...
//			System.out.println("VOR SOCKET");
//			socket = new Socket("2.247.253.136",8080);
//			System.out.println("NACH SOCKET");
//			address = socket.getInetAddress();
//			System.out.println("Connected to " + address);
//			Scanner serverScanner = new Scanner(socket.getInputStream());
//			socket.close();
//			serverScanner.close();
//		}
//		catch(java.io.IOException e)
//		{
//			System.err.println("CONNECTION ERROR");
//		}
		
		
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
