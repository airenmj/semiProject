package server;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import countdown.CountDown;

public class ServerEnrolment extends JFrame implements ActionListener {
	JButton btnStart, btnExit;
	JTextArea taNotice;
	ServerSocket sSocket;
	Socket socket;
	CountDown cd;
	
	public ServerEnrolment() {
		setTitle("������û ���α׷� ����");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		JPanel p=new JPanel();
		p.setLayout(new GridLayout(2, 1));
		btnStart=new JButton("������û ���� ���� 10����");
		btnStart.addActionListener(this);
		btnExit=new JButton("������û ���� ����");
		btnExit.addActionListener(this);
		p.add(btnStart);
		p.add(btnExit);
		taNotice=new JTextArea();
		add(taNotice,BorderLayout.CENTER);
		add(p,BorderLayout.SOUTH);
		setBounds(150,50,350,200);
		setVisible(true);
		enrolmentStart();
	}
	
	private void enrolmentStart() {
		try {
			sSocket=new ServerSocket(9012);
			socket=sSocket.accept();
			taNotice.append(socket.getInetAddress().getHostAddress()+"���� �����Ͽ����ϴ�.\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Date date=new Date();
		
		if (e.getSource()==btnStart) {
			cd=new CountDown();
			taNotice.append("���� 10���� �˸� �ð� : "+date);
		} else if (e.getSource()==btnExit) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		new ServerEnrolment();
	}
}
