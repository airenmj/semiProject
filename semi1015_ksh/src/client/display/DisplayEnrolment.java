package client.display;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import client.display.enrolment.EnrolmentManager;
import client.display.enrolment.EnrolmentMyList;
import client.display.enrolment.EnrolmentSubject;

public class DisplayEnrolment extends JPanel {
	EnrolmentSubject es;
	EnrolmentMyList eml;
	EnrolmentManager em;
	
	public DisplayEnrolment() {
		es=new EnrolmentSubject();
		eml=new EnrolmentMyList();
		em=new EnrolmentManager();
		
		JTabbedPane pane=new JTabbedPane();
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		pane.setPreferredSize(new Dimension(screenSize.width/4*3, screenSize.height/4*3));
		pane.addTab("개설강의 조회", es);
		pane.addTab("My List", eml);
		pane.addTab("강의 관리", em);
		pane.setSelectedIndex(0);
		add(pane);
	}
}
