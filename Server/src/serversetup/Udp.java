package serversetup;
import java.io.*;
import java.net.*;

public class Udp implements Runnable{

	//This class is meant to handle the UDP server 
	@Override
	public void run() {
		// TODO Auto-generated method stub
		DatagramSocket dsserver = null;
		System.out.println("UDP Start!");
		int threadsock = 4100;
		try{
			dsserver = new DatagramSocket(4000);
			byte [] buffer = new byte[1000];
			
			while(true)
			{
				buffer = new byte[1000]; //clears the buffer at the start of every loop
				
				//sets up datagram packet to receive the info and accpets it to server
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				dsserver.receive(request);   
				
				DatagramSocket cli = new DatagramSocket(threadsock);
				cli.receive(request);
				Thread clthread = new Thread(new Udpthreads(cli,request));
				clthread.start();
				System.out.println("UPD Client thread start");
				
				if(threadsock <= 4200)
				{	
					threadsock++;
				}
				else
				{
					dsserver.disconnect();
				}
				

				
//				//Takes data from client and converts to a string to be read
//				String msg = new String(request.getData());
//				
//					System.out.println("Recieved: " + msg);
//					
//					//sets up bytes for the echo back to the client
//					byte [] echo = ("Server: "+ msg).getBytes();
//					 
//					//datagram packet setup for the echo message and sends back to client
//					DatagramPacket reply =  new DatagramPacket(echo, echo.length, request.getAddress(),request.getPort());
//					System.out.println("Port: " + request.getPort());
//					dsserver.send(reply);
				
	
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
