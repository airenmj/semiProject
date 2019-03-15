package client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import client.display.DisplayEnrolment;

public class ClientEnrolment extends JFrame implements ActionListener {
	CardLayout cl;
	JPanel p_main;
	JPanel p_lg, p_lg_west, p_lg_east;
	JTextField tf_id, tf_pw;
	JButton btn_idSearch, btn_pwSearch, btn_login;
	JLabel lb_title, lb_west_image;
	DisplayEnrolment p_en;
	Socket cs;
	String str;
	
	public ClientEnrolment() {
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		setTitle("수강신청 프로그램");
		setSize(screenSize.width/5*4,screenSize.height/5*4);
		setLocation(screenSize.width/10, screenSize.height/10);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		p_main=new JPanel();
		add(p_main);
		cl=new CardLayout();
		p_main.setLayout(cl);
		
		p_lg=new JPanel();	
		p_main.add(p_lg,"접속창");
		p_lg.setLayout(new GridLayout(1,2));
		//좌
		p_lg_west=new JPanel();
		p_lg.add(p_lg_west);
		p_lg_west.setBackground(Color.WHITE);
		p_lg_west.setLayout(new BorderLayout());
		lb_west_image=new JLabel();
		ImageIcon image=new ImageIcon("1.png");
		lb_west_image.setIcon(image);
		p_lg_west.add(lb_west_image,BorderLayout.EAST);
		
		//우
		p_lg_east=new JPanel();
		p_lg.add(p_lg_east);
		p_lg_east.setLayout(new GridLayout(3,3));
		
		JPanel p_lg_east1=new JPanel();
		JPanel p_lg_east2=new JPanel();
		JPanel p_lg_east3=new JPanel();
		JPanel p_lg_east4=new JPanel();
		JPanel p_lg_east6=new JPanel();
		JPanel p_lg_east7=new JPanel();
		JPanel p_lg_east8=new JPanel();
		JPanel p_lg_east9=new JPanel();
		p_lg_east1.setBackground(Color.black);
		p_lg_east2.setBackground(Color.BLUE);
		p_lg_east3.setBackground(Color.cyan);
		p_lg_east4.setBackground(Color.DARK_GRAY);
		p_lg_east6.setBackground(Color.GREEN);
		p_lg_east7.setBackground(Color.magenta);
		p_lg_east8.setBackground(Color.yellow);
		p_lg_east9.setBackground(Color.red);
		
		JPanel p_lg_east_center=new JPanel();
		p_lg_east_center.setLayout(new BorderLayout());
		p_lg_east_center.setBorder(BorderFactory.createLineBorder(Color.orange,3));
		
		JPanel p_lg_east_center_north=new JPanel();
		lb_title=new JLabel("수강신청 시스템");
		lb_title.setFont(new Font("Serif", Font.PLAIN, 25));
		lb_title.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.gray, Color.GRAY));
		p_lg_east_center_north.add(lb_title, BorderLayout.SOUTH);
		
		JPanel p_lg_east_center_center=new JPanel();
		p_lg_east_center_center.setLayout(new GridLayout(2, 2));
		p_lg_east_center_center.add(new Label("학    번",Label.CENTER));
		tf_id=new JTextField("localhost", 10);
		p_lg_east_center_center.add(tf_id);
		p_lg_east_center_center.add(new Label("비밀번호",Label.CENTER));
		tf_pw=new JTextField("********", 10);
		p_lg_east_center_center.add(tf_pw);
		
		JPanel p_lg_east_center_south=new JPanel();
		p_lg_east_center_south.setLayout(new GridLayout(1, 3));
		btn_idSearch=new JButton("ID조회");
		btn_pwSearch=new JButton("PW조회");
		btn_login=new JButton("로그인");
		p_lg_east_center_south.add(btn_idSearch);
		p_lg_east_center_south.add(btn_pwSearch);
		p_lg_east_center_south.add(btn_login);
		btn_login.addActionListener(this);
		p_lg_east_center.add(p_lg_east_center_north,BorderLayout.NORTH);
		p_lg_east_center.add(p_lg_east_center_center,BorderLayout.CENTER);
		p_lg_east_center.add(p_lg_east_center_south,BorderLayout.SOUTH);
		
		p_lg_east.add(p_lg_east1);
		p_lg_east.add(p_lg_east2);
		p_lg_east.add(p_lg_east3);
		p_lg_east.add(p_lg_east4);
		p_lg_east.add(p_lg_east_center);
		p_lg_east.add(p_lg_east6);
		p_lg_east.add(p_lg_east7);
		p_lg_east.add(p_lg_east8);
		p_lg_east.add(p_lg_east9);
		
		p_en=new DisplayEnrolment();
		p_main.add(p_en,"실행창");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btn_login) {
			cl.show(p_main, "실행창");
			str=tf_id.getText();
			try {
				cs=new Socket(str, 9012);
			} catch (Exception e1) {
			}
		}
	}

	public static void main(String[] args) {
		new ClientEnrolment();
	}
}
