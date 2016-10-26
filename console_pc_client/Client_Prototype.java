package console_pc_client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Diese Klasse soll das ganze TCP Handy zu PC Unterfangen simpler darstellen als die GUI
 *  
 * @author Ulrich
 */

public class Client_Prototype {

	final public static int PORT = 1337;
	
	public static void main(String[] args){
		
		String ip = "2.247.253.158";
		DataInputStream inputScanner = null;
		
		try {
			Socket socket = new Socket(ip, PORT);
			InetAddress address = socket.getInetAddress();
			System.out.println("connected with " + address);
			
			inputScanner = new DataInputStream(socket.getInputStream());
			
			float x=0f ,y=0f ,z=0f;
			
			while(socket.isConnected())
			{
				x = inputScanner.readFloat();
				y = inputScanner.readFloat();
				z = inputScanner.readFloat();
				
				System.out.println(x + " | " + y + " " + z);
			}
			
			inputScanner.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
