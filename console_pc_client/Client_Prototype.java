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

	public static Socket socket;
	
	final public static int PORT = 1337;
	
	public static void main(String[] args){
		
		String ip = "127.0.0.1";
		DataInputStream inputScanner = null;
		
		try {
			socket = new Socket(ip, PORT);
			InetAddress address = socket.getInetAddress();
			System.out.println("connected with " + address);
			
			
			
			int x=0 ,y=0 ,z=0;
			
			while(socket.isConnected())
			{
				inputScanner = new DataInputStream(socket.getInputStream());	//I need this in the loop and also close it in same, because?
				
				x = inputScanner.readInt();
				System.out.print("X: " + x + " | ");
				y = inputScanner.readInt();
				System.out.print("Y: " + y + " | ");
				z = inputScanner.readInt(); 	//Fails here: java.io.EOFException
												//java.io.DataInputStream.readInt(Unknown Source)
				System.out.print("Z: " + z + "\n");
				
				inputScanner.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				inputScanner.close();
				socket.close();
				System.out.println("all closed");
			} catch (IOException e) {
				e.printStackTrace();
				
				System.out.println("\n this failed");
			}
			
		}
	}
}
