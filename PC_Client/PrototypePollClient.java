package console_pc_client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;

/**
 * This class demonstrates a client that polls data from a smartphone device
 * and receives its gyroscope data
 *  
 * @author Ulrich
 */

public class PrototypePollClient {

	public static Socket socket;
	public static BufferedReader inputReader;
	public static InputStreamReader iReader;
	
	final public static int PORT = 1337;
	
	public static void main(String[] args) throws IOException{
		
		//String ip = "127.0.0.1";	//in case you want to test with localhost
		String ip = "192.168.43.177";// "192.168.43.1";	//manually enter your smartphone ip
		String input = "";
		
		float x = 0f, y = 0f, z = 0f;
		
		try {
			
			socket = new Socket(ip, PORT);
			InetAddress address = socket.getInetAddress();
			
			System.out.println("connected with " + address);
			
			iReader = null;
			inputReader = null;
			
			while(socket.isConnected())
			{
				Date start = new Date(); //bad way for time measurement ... I think
				if(socket.isClosed())
					//Try-Catch for prototype unnecesary but prohibts exception if server closed or disconnects
					try{ socket = new Socket(ip, PORT);}
					catch(ConnectException connExc){
						System.out.println("Connection to server failed");
						break;
					}
				
				iReader = new InputStreamReader(socket.getInputStream());
				inputReader = new BufferedReader(iReader);
				
				input = inputReader.readLine();
				String[] gyroscope = input.split(" ");
				x = Float.valueOf(gyroscope[0]);
				y = Float.valueOf(gyroscope[1]);
				z = Float.valueOf(gyroscope[2]);
				
				Date end = new Date();
				System.out.println("x: " + x + " y: " + y + " z: " + z + " time: " + (end.getTime()-start.getTime()) + "ms");
				iReader.close();
				inputReader.close();
				socket.close();
				
			}
			
		} catch (IOException e) {
			System.err.println("NO CONNECTION");
			e.printStackTrace();
		}
		finally{
				socket.close();
				iReader.close();
				inputReader.close();
				System.out.println("all closed"); 
		}
	}
}