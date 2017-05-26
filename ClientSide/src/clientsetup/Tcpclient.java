package clientsetup;
import java.net.*;
import java.io.*;

public class Tcpclient implements Runnable {

	public String hostname;
	public clientgui ref;
	String sentence;
	 String modifiedSentence;
	 BufferedReader inFromUser;
	 Socket clientSocket;
	 DataOutputStream outToServer;
	 BufferedReader inFromServer;
	
	public void clientsetup() throws Exception
	{		 
		  inFromUser = new BufferedReader(new InputStreamReader(System.in));
		  clientSocket = new Socket("localhost", 4000);
		  outToServer = new DataOutputStream(clientSocket.getOutputStream());
		  inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		  //while(true){
//			  sentence = inFromUser.readLine();
//			  if(!sentence.equalsIgnoreCase("end"))
//			  {
//				  outToServer.writeBytes(sentence + '\n');
//				  modifiedSentence = inFromServer.readLine();
//				  System.out.println("FROM SERVER: " + modifiedSentence);
//				  
//			  }
//			  else{
//				  clientSocket.close();
//				  break;
//			  }
			  
		  
	}

	public void send(String guimsg,boolean emp)
	{
		try{
		  sentence = guimsg;
		  ref.chatwindow.append(hostname+": " + sentence +"\n");
		  outToServer.writeBytes(sentence + '\n');
		  modifiedSentence = inFromServer.readLine();
		  System.out.println("FROM SERVER: " + modifiedSentence);
		  ref.chatwindow.append(modifiedSentence+ "\n" );
		  ref.sendarea.setText("");
			  
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void closeSocket(){
		try{
			inFromUser.close();
			inFromServer.close();
			outToServer.close();
			clientSocket.close();
		}
		catch(Exception e)
		{
			
		}
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
