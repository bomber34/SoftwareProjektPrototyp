package console_pc_client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Diese Klasse soll das ganze TCP Handy zu PC Unterfangen simpler darstellen als die GUI
 *  
 * @author Ulrich
 */

public class Client_Prototype {

	public static Socket socket;
	public static BufferedReader inputReader;
	public static InputStreamReader iReader;
	
	final public static int PORT = 1337;
	
	public static void main(String[] args) throws IOException{
		
		//String ip = "127.0.0.1";
		String ip = "192.168.43.1";
		String input = "";
		
		float x = 0f, y = 0f, z = 0f;
		
		try {
			
			socket = new Socket(ip, PORT);
			InetAddress address = socket.getInetAddress();
			
			System.out.println("connected with " + address);
			
			iReader = null;
			inputReader = null;
			
			while(true)
			{
				iReader = new InputStreamReader(socket.getInputStream());
				inputReader = new BufferedReader(iReader);
				
				input = inputReader.readLine();
				String[] gyroscope = input.split(" ");
				x = Float.valueOf(gyroscope[0]);
				y = Float.valueOf(gyroscope[1]);
				z = Float.valueOf(gyroscope[2]);
				
				System.out.println("x: " + x + " y: " + y + " z: " + z);
				
				iReader.close();
				inputReader.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
				socket.close();
				iReader.close();
				inputReader.close();
				System.out.println("all closed"); 
		}
	}
}