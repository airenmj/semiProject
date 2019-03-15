package countdown;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class CountDown extends JFrame {
	public CountDown() {
		setTitle("CountDown");
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		setSize(300,150);
		setLocation(500, 50);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(2, 1));
		
		String[]fontname=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		JPanel p_n=new JPanel();
		add(p_n);
		p_n.setLayout(new BorderLayout());
		p_n.setBackground(Color.WHITE);
		JLabel lb_n=new JLabel("서버 종료까지");
		lb_n.setFont(new Font("굴림", Font.BOLD, 30));
		lb_n.setHorizontalAlignment(SwingConstants.CENTER);
		p_n.add(lb_n);
		JPanel p_s=new JPanel();
		add(p_s);
		p_s.setLayout(new BorderLayout());
		p_s.setBackground(Color.WHITE);
		JLabel lb_s=new JLabel("CountDown");
		lb_s.setFont(new Font("굴림", Font.BOLD, 30));
		lb_s.setHorizontalAlignment(SwingConstants.CENTER);
		p_s.add(lb_s,BorderLayout.CENTER);

		Runnable task= () -> {
			for (int i = 9; i >= 0; i--) {
				for (int j = 59; j >= 0; j--) {
					try {
						Thread.sleep(1000);
						if (i==0) lb_s.setForeground(Color.red);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					lb_s.setText(i+"분 "+j+"초 남음");
				}
			}
			System.exit(1);
		};
		new Thread(task).start();
	}
}
