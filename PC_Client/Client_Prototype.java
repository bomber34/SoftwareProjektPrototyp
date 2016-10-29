package console_pc_client;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;

import sp_common.*;

/**
 * This class demonstrates a client that polls data from a smartphone device
 * and receives its gyroscope data
 *  
 * @author Ulrich
 */

public class Client_Prototype {

	public static Socket socket;
	public static InputStream inputStream;
	public static ObjectInputStream objInputStream; 
	public static Logger logger;
	
	
	final public static int PORT = 1337;
	
	public static void main(String[] args) throws IOException{
		
		//String ip = "127.0.0.1";	//in case you want to test with localhost
		String ip =  "192.168.43.1";	//manually enter your smartphone ip
		
		try {
			
			socket = new Socket(ip, PORT);
			InetAddress address = socket.getInetAddress();
			
			System.out.println("connected with " + address);
			
			logger = Logger.getLogger();
			logger.write("The following data consists of the timestamps that the server sends");
			
			while(socket.isConnected())
			{
				if(socket.isClosed())
					//Try-Catch for prototype unnecesary but prohibts exception if server closed or disconnects
					try{ socket = new Socket(ip, PORT);}
					catch(ConnectException connExc){
						System.out.println("Connection to server failed or ended");
						break;
					}
				inputStream = socket.getInputStream();
				objInputStream = new ObjectInputStream(inputStream);
			
				
				
				SensorData sensorData = (SensorData) objInputStream.readObject();
				
				/*
	            System.out.println("SensorType: " + sensorData.sensorType);
	            System.out.println("Data: " + sensorData.data);
	            System.out.println("Timestamp: " + sensorData.timestamp);
	            System.out.println("Accuracy: " + sensorData.accuracy);
				*/
				
				String output = "SensorType: " + sensorData.sensorType + " | Data:[" 
						+ sensorData.data[0] + "\t, " + sensorData.data[1] + "\t, " + sensorData.data[2] + 
						 "]\t| Timestamp: " + sensorData.timestamp + "\t| Acc: " + sensorData.accuracy;
				
				System.out.println(output);
				logger.write(String.valueOf(sensorData.timestamp));
				
				
	            inputStream.close();
	            objInputStream.close();
				socket.close();
			}
			
		} catch (IOException e) {
			System.err.println("NO CONNECTION");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("WRONG CLASS RECEIVED");
			e.printStackTrace();
		}
		finally{
				if(!socket.isClosed())
					socket.close();
				
				inputStream.close();
				objInputStream.close();
				logger.close();
				
				System.out.println("all closed"); 
		}
	}
}
