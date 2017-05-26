package serversetup;
import java.io.*;
import java.net.*;

public class Tcpthreads implements Runnable {

	BufferedReader reader;
	Socket socket;
	PrintWriter client;
	
	public Tcpthreads(Socket s, PrintWriter p)
	{
		client = p;
		try{
			
			socket = s;
			InputStreamReader Ireader = new InputStreamReader(socket.getInputStream());
			reader =new BufferedReader(Ireader);
		   
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String msg;
		try
		{
			while(true)
			{
					BufferedReader inFromClient =
				   new BufferedReader(new InputStreamReader(socket.getInputStream()));
				   DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
				 
				   String clientSentence;
				   while((clientSentence = inFromClient.readLine())!=null)
				   {	
					   System.out.println("Received: " + clientSentence);
					   System.out.println("port: " + socket.getPort());

					   String newsentence = clientSentence + '\n';
					   outToClient.writeBytes("Server: "+newsentence);
					   
			
				   }
//				   
//					reader.close();
//					inFromClient.close();
//					outToClient.close();
//					socket.close();
				
			}
		}
		catch(SocketException e)
		{
			System.out.println("Client has Disconnected");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
