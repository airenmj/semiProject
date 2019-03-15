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
		setTitle("수강신청 프로그램 서버");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		JPanel p=new JPanel();
		p.setLayout(new GridLayout(2, 1));
		btnStart=new JButton("수강신청 서버 종료 10분전");
		btnStart.addActionListener(this);
		btnExit=new JButton("수강신청 서버 종료");
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
			taNotice.append(socket.getInetAddress().getHostAddress()+"님이 접속하였습니다.\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Date date=new Date();
		
		if (e.getSource()==btnStart) {
			cd=new CountDown();
			taNotice.append("종료 10분전 알림 시각 : "+date);
		} else if (e.getSource()==btnExit) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		new ServerEnrolment();
	}
}
