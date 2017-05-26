package serversetup;

public class Server {

	public static void main(String[] args)
	{
		Thread udp = new Thread(new Udp());
		Thread tcp = new Thread(new Tcp());
		System.out.println("Before start");
		udp.start();
		tcp.start();
	}
}
