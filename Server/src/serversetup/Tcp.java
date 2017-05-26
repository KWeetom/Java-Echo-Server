package serversetup;
import java.net.*;
import java.io.*;

public class Tcp implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Server Start");
		try{
//			ServerSocket tcpserver = new ServerSocket(4000);
//			Socket client = tcpserver.accept();
//		
			 String clientSentence;
			 ServerSocket welcomeSocket = new ServerSocket(4000);
			  

			  while (true) {
				   Socket connectionSocket = welcomeSocket.accept();
				   PrintWriter writer = new PrintWriter(connectionSocket.getOutputStream());
				   Thread clhandler = new Thread(new Tcpthreads(connectionSocket, writer));
				   clhandler.start();
				   System.out.println("TCP Client thread start");
				   
//				   BufferedReader inFromClient =
//				   new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
//				   DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
//				   clientSentence = inFromClient.readLine();
//				   	
//				   System.out.println("Received: " + clientSentence);
//				   capitalizedSentence = clientSentence.toUpperCase() + '\n';
//				   outToClient.writeBytes("Server: "+capitalizedSentence);
			   	
			   
			  }
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			
		}
	}
}



