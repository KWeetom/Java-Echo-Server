package serversetup;
import java.io.*;
import java.net.*;

public class Udpthreads implements Runnable {

	DatagramSocket dsserver;
	DatagramPacket request;
	public Udpthreads(DatagramSocket dsser, DatagramPacket req)throws Exception {
		
		// TODO Auto-generated constructor stub
		//Takes data from client and converts to a string to be read
			dsserver = dsser;
			request = req;
//			String msg = new String(request.getData());
//		
//			System.out.println("Recieved: " + msg);
//			
//			//sets up bytes for the echo back to the client
//			byte [] echo = ("Server: "+ msg).getBytes();
//			 
//			//datagram packet setup for the echo message and sends back to client
//			DatagramPacket reply =  new DatagramPacket(echo, echo.length, request.getAddress(),request.getPort());
//			System.out.println("Port: " + request.getPort());
//			dsserver.send(reply);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//sets up bytes for the echo back to the client
		
		try{
		
				
					String msg;
					msg= new String(request.getData());
					
					
						System.out.println("Recieved: " + msg);
						
						//sets up bytes for the echo back to the client
						byte [] echo = ("Server: "+ msg).getBytes();
						 
						//datagram packet setup for the echo message and sends back to client
						DatagramPacket reply =  new DatagramPacket(echo, echo.length, request.getAddress(),request.getPort());
						//System.out.println("Port: " + request.getPort());
						dsserver.send(reply);
						msg = "";
				
			
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
