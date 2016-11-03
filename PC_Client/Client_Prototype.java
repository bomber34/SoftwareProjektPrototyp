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
	public static BufferedReader inputReader;
	public static InputStreamReader iReader;

	public static Logger logger = null;

	final public static int PORT = 1337;

	static long start = 0;
	static long end = 0;

	public static void main(String[] args) throws IOException {

		// String ip = "127.0.0.1"; //in case you want to test with localhost
		String ip = "192.168.43.1";// "192.168.43.1"; //manually enter your
									// smartphone ip
		String input = "";

		float x = 0f, y = 0f, z = 0f;
		long timeStamp = 0;

		logger = Logger.getLogger();

		try {

			socket = new Socket(ip, PORT);
			InetAddress address = socket.getInetAddress();

			System.out.println("connected with " + address);

			iReader = null;
			inputReader = null;

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

				iReader = new InputStreamReader(socket.getInputStream());
				inputReader = new BufferedReader(iReader);
				input = inputReader.readLine();

				String[] data = input.split(" ");
				x = Float.valueOf(data[0]);
				y = Float.valueOf(data[1]);
				z = Float.valueOf(data[2]);
				
				timeStamp = Long.valueOf(data[3]);
				logger.write("" + (new Date().getTime() - timeStamp));
				iReader.close();
				inputReader.close();
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