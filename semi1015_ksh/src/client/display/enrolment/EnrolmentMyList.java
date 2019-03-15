package client.display.enrolment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import client.display.enrolment.tablemodel.MylistTableModel;
import client.model.MyListModel;
import client.subject.Subject;
import timetable.DrawTimeTable;

public class EnrolmentMyList extends JPanel implements ActionListener{
	JTextField tf_th;
	JButton btn_refresh, btn_delete, btn_save;
	JTable n_jtb_myList;
	MylistTableModel n_tb_myList;
	MyListModel mlModel;
	JPanel p_south_3;
	DrawTimeTable dtt;
	
	public EnrolmentMyList() {
		dtt=new DrawTimeTable();
		addLayout();
		connectDB();
	}
	
	private void connectDB() {
		try {
			mlModel=new MyListModel();
		} catch (Exception e) {
			System.out.println("mlModel연결 실패");
		}
	}

	private void addLayout() {
		setLayout(new GridLayout(2, 1));
		JPanel p_north=new JPanel();
		p_north.setLayout(new BorderLayout());
		p_north.setBorder(new TitledBorder("MyList 조회"));
		JPanel p_south=new JPanel();
		add(p_north);
		add(p_south);
		
		//상 나머지 테이블
		JPanel p_north_remain=new JPanel();
		p_north.add(p_north_remain);
		n_tb_myList=new MylistTableModel();
		n_jtb_myList=new JTable(n_tb_myList);
		n_jtb_myList.setModel(n_tb_myList);
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		JScrollPane sp=new JScrollPane(n_jtb_myList);
		sp.setPreferredSize(new Dimension(screenSize.width/10*7, screenSize.height/4));
		p_north_remain.add(sp,BorderLayout.CENTER);
		
		//상 하
		JPanel p_north_south=new JPanel();
		p_north.add(p_north_south,BorderLayout.SOUTH);
		p_north_south.setLayout(new BorderLayout());
		
		//상 하 좌
		JPanel p_north_south_west=new JPanel();
		p_north_south.add(p_north_south_west,BorderLayout.WEST);
		btn_refresh=new JButton(new ImageIcon("refresh.png"));
		btn_refresh.setBackground(Color.white);
		p_north_south_west.add(btn_refresh);
		btn_refresh.addActionListener(this);
		
		//상 하 중
		JPanel p_north_south_center=new JPanel();
		p_north_south.add(p_north_south_center,BorderLayout.CENTER);
		JLabel th=new JLabel("총 학점");
		p_north_south_center.add(th);
		tf_th=new JTextField(2);
		p_north_south_center.add(tf_th);
		btn_delete=new JButton("삭제");
		p_north_south_center.add(btn_delete);
		btn_delete.addActionListener(this);
		btn_save=new JButton("저장");
		p_north_south_center.add(btn_save);
		btn_save.addActionListener(this);
		
		//하 이미지, 시간표
		p_south.setLayout(new GridLayout(1,4));
		JPanel p_south_1=new JPanel();
		p_south.add(p_south_1);
		
		JPanel p_south_2=new JPanel();
		p_south.add(p_south_2);
		p_south_2.setLayout(new BorderLayout());
		JLabel lb_s_w=new JLabel();
		ImageIcon image=new  ImageIcon("2.png");
		lb_s_w.setIcon(image);
		p_south_2.add(lb_s_w,BorderLayout.EAST);
		
		p_south_3=new JPanel();
		p_south_3.setLayout(new BorderLayout());
		p_south_3.add(dtt,BorderLayout.CENTER);
		p_south.add(p_south_3);
		
		JPanel p_south_4=new JPanel();
		p_south.add(p_south_4);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt=e.getSource();
		if (evt==btn_refresh) {
			refresh();
			hakjum();
			fillTimeTable();
		} else if (evt==btn_delete) {
			reTimeTable();
			delete();
			refresh();
			hakjum();
		} else if (evt==btn_save){
			JOptionPane.showMessageDialog(null, "수강목록을 저장하시겠습니까?");
		}
	}

	private void reTimeTable() {
		Subject sj=new Subject();
		int row=n_jtb_myList.getSelectedRow();
		sj.setStr((String) n_jtb_myList.getValueAt(row, 7));
		dtt.reTimeTable(sj);
		
	}

	private void fillTimeTable() {
		Subject sj=new Subject();
		String str;
		try {
			ArrayList scheldule=mlModel.fillTimeTable();
			for (int i = 0; i < scheldule.size(); i++) {
				str=(String) scheldule.get(i);
				sj.setStr(str);
				dtt.fillTimeTable(sj);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void refresh() {
		try {
			ArrayList data=mlModel.refresh();
			n_tb_myList.data=data;
			n_jtb_myList.setModel(n_tb_myList);
			n_tb_myList.fireTableDataChanged();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void hakjum() {
		try {
			ArrayList hakjum=mlModel.hakjum();
			String str=(String) hakjum.get(0);
			int thj=Integer.parseInt(str);
			if (thj<=21) {
				tf_th.setText(str);
			}else{
				tf_th.setText(str);
				JOptionPane.showMessageDialog(null, "학기당 최대이수학점(21)을 초과하였습니다");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "MyList에 내용이 없습니다.");
		}
	}

	private void delete() {
		Subject sj=new Subject();
		int row=n_jtb_myList.getSelectedRow();
		sj.setSubTime((String) n_jtb_myList.getValueAt(row, 0));
		sj.setSubCategory((String) n_jtb_myList.getValueAt(row, 1));
		sj.setSubNo(Integer.parseInt((String) n_jtb_myList.getValueAt(row, 2)));
		sj.setSubName((String) n_jtb_myList.getValueAt(row, 3));
		sj.setSubYear(Integer.parseInt((String) n_jtb_myList.getValueAt(row, 4)));
		sj.setSubGrade(Integer.parseInt((String) n_jtb_myList.getValueAt(row, 5)));
		sj.setSubProfessor((String) n_jtb_myList.getValueAt(row, 6));
		sj.setSubSchedule((String) n_jtb_myList.getValueAt(row, 7));
		sj.setSubClass((String) n_jtb_myList.getValueAt(row, 8));
		sj.setSubEtc((String) n_jtb_myList.getValueAt(row, 9));

		try {
			mlModel.delete(sj);
			JOptionPane.showMessageDialog(null, "목록에서 삭제되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
