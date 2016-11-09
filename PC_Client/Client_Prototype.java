package console_pc_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;

/**
 * This class demonstrates a client that polls data from a smartphone device and
 * receives its gyroscope data
 * 
 * @author Ulrich
 */

public class Client_Prototype {

	public static Socket socket;
	public static BufferedReader inputReader = null;
	public static InputStreamReader iReader = null;

	final public static int PORT = 1337;
	
	private static String input = "";
	
	private static MouseMover mouse;
	
	static long start = 0;
	static long end = 0;


	public static String[] pollData() throws IOException{
		iReader = new InputStreamReader(socket.getInputStream());
		inputReader = new BufferedReader(iReader);
		input = inputReader.readLine();
		
		iReader.close();
		inputReader.close();
		
		String data[] = input.split(" ");
		
		return data;
	}
	
	
	public static void main(String[] args) throws IOException {

		// String ip = "127.0.0.1"; //in case you want to test with localhost
		
		String ip = "192.168.43.1";// "192.168.43.1"; //manually enter your
									// smartphone ip
	
		//server data
		float x = 0f, y = 0f, z = 0f;
		long timeStamp = 0;

		mouse = new MouseMover();

		try {
			
			socket = new Socket(ip, PORT);
			Logger.getLogger().write("Client time after connection:\n" + new Date().getTime());
			
			InetAddress address = socket.getInetAddress();

			System.out.println("connected with " + address);

			String data[] = pollData();
			Logger.getLogger().write("Servertime after serverSocker.accept():\n" + data[3]);
			Logger.getLogger().write("\nHandy | PC\n");
			socket.close();
			
			while (socket.isConnected()) {

				if (socket.isClosed())
					// Try-Catch for prototype unnecesary but prohibts exception
					// if server closed or disconnects
					try {
						socket = new Socket(ip, PORT);
					} catch (ConnectException connExc) {
						System.out.println("Connection to server failed");
						break;
					}

				data = pollData();
				x = Float.valueOf(data[ServerData.ACCELX]);
				y = Float.valueOf(data[ServerData.ACCELY]);
				z = Float.valueOf(data[ServerData.ACCELZ]);
				
				System.out.print("Accelerator: ["+ x + ", " + y + ", " + z + "]");
				
				mouse.moveMouse(x, y, z);
				
				timeStamp = Long.valueOf(data[ServerData.TIMESTAMP]);
				Logger.getLogger().write(timeStamp + ", " + new Date().getTime());
				socket.close();
			}

		} catch (IOException e) {
			System.err.println("NO CONNECTION");
			e.printStackTrace();
		} finally {
			socket.close();
			iReader.close();
			inputReader.close();
			System.out.println("all closed");
		}
	}
}