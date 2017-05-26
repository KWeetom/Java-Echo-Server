package clientsetup;

import java.io.*;
import java.net.*;
import java.util.Arrays;

import javax.sound.midi.Receiver;

public class Udpclient implements Runnable {

	
	String hostname;
	clientgui ref;
	String msg ="";
	int port;
	DatagramSocket dssocket;
	BufferedReader userin;
	InetAddress host;
	
	byte [] sentdata ;
	byte [] recieveddata ;
	
	public void clientsetup() throws Exception
	{
		
		userin = new BufferedReader(new InputStreamReader(System.in));
		dssocket = new DatagramSocket();
		host = InetAddress.getByName("localhost");
		
		sentdata = new byte[1000];
		recieveddata = new byte[1000];
		
			
	}

	public void send(String guimsg, boolean emp) 
	{
		port = 4000;
		try{
			
				if(emp == false)
				{
					msg =guimsg;
					if(ref.isActive())
					{
						ref.chatwindow.append(hostname+": " + msg +"\n");
						sentdata = (msg).getBytes();
				
						DatagramPacket sentPacket = new DatagramPacket(sentdata, sentdata.length, host, port);
						dssocket.send(sentPacket);
						
						DatagramPacket recievedPacket = new DatagramPacket(recieveddata, recieveddata.length);
						dssocket.receive(recievedPacket);
						
						
						String newmsg = new String(recievedPacket.getData());
						ref.chatwindow.append(newmsg+"\n");
						ref.sendarea.setText("");
						port = recievedPacket.getPort();
						System.out.println(port);
						//System.out.println(hostname + ": " +newmsg);
					}
					else
					{
						dssocket.close();
						//break;
					}
				}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void getgui(clientgui g)
	{
		ref = g;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			clientsetup();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
