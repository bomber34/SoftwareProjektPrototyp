package console_pc_client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ExampleServer {

	final public static int PORT = 1337;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
	
		int sendX = 0, sendY = 0, sendZ = 0;
		
		ServerSocket server = new ServerSocket(PORT);
		System.out.println("Server is open");
		while(true){
			Socket socket = server.accept();
			
			PrintStream outputStr = new PrintStream(socket.getOutputStream());
			sendX = (int) (Math.random()*10) +10;
			sendY = (int) (Math.random()*10) +5;
			sendY = (int) (Math.random()*10);
			
			outputStr.println(sendX);
			outputStr.println(sendY);
			outputStr.println(sendZ);	// Can't read this for some reason?
			
			socket.close();
			outputStr.close();
		}
		
		
	}
}
