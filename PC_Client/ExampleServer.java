package console_pc_client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ExampleServer {

	final public static int PORT = 1337;
	
	public static String randomString(){
		
		Random random = new Random();
		String randStr = "";
		for(int i = 0; i<5;i++){
			randStr += (char) (random.nextInt(26)+64);
		}
		return randStr;
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		ServerSocket server = new ServerSocket(PORT);
		System.out.println("Server is open");
		while(true){
			Socket socket = server.accept();
			
			PrintStream outputStr = new PrintStream(socket.getOutputStream());
			outputStr.println(randomString());
			
			socket.close();
			outputStr.close();
		}
		
		
	}
}
