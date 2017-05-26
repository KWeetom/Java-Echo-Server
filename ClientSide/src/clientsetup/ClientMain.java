package clientsetup;
import java.net.*;
import java.io.*;
import java.io.IOException;

public class ClientMain {

	public static void main(String[] args) {
			// TODO Auto-generated method stub
		Udpclient udpclient =  new Udpclient();
		Tcpclient tcpclient = new Tcpclient();
	
		clientgui gui = new clientgui(udpclient,tcpclient);
		gui.setVisible(true);
		gui.setResizable(false);

//		try{
//			//udpclient.clientsetup();
//			tcpclient.clientsetup();
//		}
//		catch(IOException e)
//		{
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.exit(0);
	}
}
