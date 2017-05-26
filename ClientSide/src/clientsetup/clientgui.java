package clientsetup;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class clientgui extends JFrame implements WindowListener {

	private JPanel contentPane;
	private JTextField username;
	public JPanel chatpanel;
	JComboBox comboBox ;
	JTextArea chatwindow;
	JTextArea sendarea;
	Udpclient udpclient; 
	Tcpclient tcpclient;
	private JScrollPane scrollPane;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					clientgui frame = new clientgui();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	
	/**
	 * Create the frame.
	 */
	public clientgui(Udpclient u, Tcpclient t) {

		udpclient =u;
		tcpclient=t;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 305, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		CardLayout cli = new CardLayout();
		contentPane.setLayout(cli);
		
		
		JPanel loginpanel = new JPanel();
		contentPane.add(loginpanel, "login");
		loginpanel.setLayout(null);

		
		username = new JTextField();
		username.setBounds(10, 86, 254, 40);
		loginpanel.add(username);
		username.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"UDP", "TCP"}));
		comboBox.setBounds(10, 168, 254, 40);
		loginpanel.add(comboBox);
		
		JButton loginbutton = new JButton("Login\r\n");
		loginbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println("Pressed");
				cli.show(contentPane, "chat");
				startClient(comboBox.getSelectedItem().toString());
			
			}
		});
		loginbutton.setBounds(69, 244, 130, 40);
		loginpanel.add(loginbutton);
		
		JLabel usernamelabel = new JLabel("Username");
		usernamelabel.setBounds(10, 53, 145, 22);
		loginpanel.add(usernamelabel);
		
		JLabel lblConnectionType = new JLabel("Connection Type");
		lblConnectionType.setBounds(10, 137, 167, 20);
		loginpanel.add(lblConnectionType);
		
		//Chat Panel: Panel to hold the text areas for communications and the text to type message to server
		chatpanel = new JPanel();
		contentPane.add(chatpanel, "chat");
		chatpanel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 33, 239, 194);
		chatpanel.add(scrollPane);
		
		//Window for displaying the messages
		chatwindow = new JTextArea();
		scrollPane.setViewportView(chatwindow);
		chatwindow.setEditable(false);
		
//		JScrollPane scrollPane = new JScrollPane(chatwindow);
//		scrollPane.setBounds(0, 0, 2, 2);
//		scrollPane.setVisible(aFlag);
//		chatpanel.add(scrollPane);
//		
		//text area for sending string to server
		sendarea = new JTextArea();
		sendarea.setBounds(10, 251, 179, 79);
		chatpanel.add(sendarea);
		
		//Jbutton for sending info to server
		JButton sendbutton = new JButton("Send\r\n");
		sendbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println("Pressed");
				if(comboBox.getSelectedItem().equals("UDP"))
				{
					if(!sendarea.getText().equals(null))
					{
						udpclient.send(sendarea.getText().trim(), sendarea.getText().isEmpty());
					}

				}
				else{
					if(!sendarea.getText().equals(null))
					{
						tcpclient.send(sendarea.getText().trim(), sendarea.getText().isEmpty());
					}
				}
			}
		});
		sendbutton.setBounds(189, 307, 75, 23);
		chatpanel.add(sendbutton);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{loginpanel, chatpanel}));
	}
	
	//this method is used to start either tcp or udp client connection to server
	public void startClient(String s)
	{
		
		
			//udpclient.clientsetup();
			if(s.equals("UDP"))
			{	if(!username.getText().isEmpty())
				{
					udpclient.hostname = username.getText();
				}
				else
				{
					udpclient.hostname = "Client";
				}
				udpclient.getgui(this);
				Thread udp = new Thread(udpclient);
				udp.start();
				System.out.println("udp");
			}
			else
			{
				if(!username.getText().isEmpty())
				{
					tcpclient.hostname = username.getText();
				}
				else
				{
					tcpclient.hostname = "Client";
				}		
				tcpclient.ref = this;
				Thread tcp = new Thread(tcpclient);
				tcp.start();
				System.out.println("tcp");
			}
		}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		if(comboBox.getSelectedItem().equals("TCP"))
		{
			tcpclient.closeSocket();
		}
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
