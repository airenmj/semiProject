package client.display.enrolment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import client.display.enrolment.tablemodel.ManagerTableModel;
import client.display.enrolment.tablemodel.SubjectTableModel;
import client.model.ManagerModel;
import client.model.SubjectModel;
import client.subject.Subject;

public class EnrolmentManager extends JPanel implements ActionListener {
	JTextField n_tf_search, s_tf_1, s_tf_2, s_tf_3, s_tf_4, s_tf_5, s_tf_6, s_tf_7, s_tf_8, s_tf_9;
	JButton n_btn_search, s_btn_insert, s_btn_modify, s_btn_delete;
	JTextArea s_ta_etc;
	JTable n_jtb_manager;
	ManagerTableModel n_tb_manager;
	ManagerModel mModel;
	
	public EnrolmentManager() {
		connectDB();
		
		setLayout(new GridLayout(2, 1));
		JPanel p_north=new JPanel();
		p_north.setLayout(new BorderLayout());
		p_north.setBorder(new TitledBorder("���� ��ȸ"));
		JPanel p_south=new JPanel();
		p_south.setLayout(new BorderLayout());
		p_south.setBorder(new TitledBorder("���� ����"));
		add(p_north);
		add(p_south);
		
		//�� ������ ���̺�
		JPanel p_north_remain=new JPanel();
		p_north.add(p_north_remain);
		n_tb_manager=new ManagerTableModel();
		n_jtb_manager=new JTable(n_tb_manager);
		n_jtb_manager.setModel(n_tb_manager);
		n_jtb_manager.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row=n_jtb_manager.getSelectedRow();
				int col=2;
				String data=(String) n_jtb_manager.getValueAt(row, col);
				int no=Integer.parseInt(data);
				
				try {
					Subject sj = mModel.selecterByPK(no);
					selecterByPK(sj);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				super.mouseClicked(e);
			}
			public void selecterByPK(Subject sj) {
				s_tf_1.setText(sj.getSubTime());
				s_tf_2.setText(sj.getSubCategory());
				s_tf_3.setText(String.valueOf(sj.getSubNo()));
				s_tf_4.setText(sj.getSubName());
				s_tf_5.setText(String.valueOf(sj.getSubYear()));
				s_tf_6.setText(String.valueOf(sj.getSubGrade()));
				s_tf_7.setText(sj.getSubProfessor());
				s_tf_8.setText(sj.getSubSchedule());
				s_tf_9.setText(sj.getSubClass());
				s_ta_etc.setText(sj.getSubEtc());
			}
		});
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		JScrollPane sp=new JScrollPane(n_jtb_manager);
		sp.setPreferredSize(new Dimension(screenSize.width/10*7, screenSize.height/100*28));
		p_north_remain.add(sp,BorderLayout.CENTER);
				
		//�� �� ��ȸ ��ư
		JPanel p_north_south=new JPanel();
		p_north.add(p_north_south,BorderLayout.SOUTH);
//		p_north_south.setLayout(new BorderLayout());
		n_tf_search=new JTextField(10);
		n_tf_search.addActionListener(this);
		n_btn_search=new JButton("���� �˻�");
		n_btn_search.addActionListener(this);
		p_north_south.add(n_tf_search);
		p_north_south.add(n_btn_search);
		
		//�� �� ���̺�9, �ؽ�Ʈ�ʵ�9
		JPanel p_south_north=new JPanel();
		p_south.add(p_south_north,BorderLayout.NORTH);
		p_south_north.setLayout(new GridLayout(2, 9));
		p_south_north.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.LIGHT_GRAY,Color.LIGHT_GRAY));
		JLabel lb1=new JLabel("�̼�����");
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		p_south_north.add(lb1);
		JLabel lb2=new JLabel("��������");
		lb2.setHorizontalAlignment(SwingConstants.CENTER);
		p_south_north.add(lb2);
		JLabel lb3=new JLabel("���ǹ�ȣ");
		lb3.setHorizontalAlignment(SwingConstants.CENTER);
		p_south_north.add(lb3);
		JLabel lb4=new JLabel("���Ǹ�");
		lb4.setHorizontalAlignment(SwingConstants.CENTER);
		p_south_north.add(lb4);
		JLabel lb5=new JLabel("�г�");
		lb5.setHorizontalAlignment(SwingConstants.CENTER);
		p_south_north.add(lb5);
		JLabel lb6=new JLabel("����");
		lb6.setHorizontalAlignment(SwingConstants.CENTER);
		p_south_north.add(lb6);
		JLabel lb7=new JLabel("��米��");
		lb7.setHorizontalAlignment(SwingConstants.CENTER);
		p_south_north.add(lb7);
		JLabel lb8=new JLabel("����");
		lb8.setHorizontalAlignment(SwingConstants.CENTER);
		p_south_north.add(lb8);
		JLabel lb9=new JLabel("���ǽ�");
		lb9.setHorizontalAlignment(SwingConstants.CENTER);
		p_south_north.add(lb9);
		s_tf_1=new JTextField();
		p_south_north.add(s_tf_1);
		s_tf_2=new JTextField();
		p_south_north.add(s_tf_2);
		s_tf_3=new JTextField();
		s_tf_3.setEnabled(false);
		p_south_north.add(s_tf_3);
		s_tf_4=new JTextField();
		p_south_north.add(s_tf_4);
		s_tf_5=new JTextField();
		p_south_north.add(s_tf_5);
		s_tf_6=new JTextField();
		p_south_north.add(s_tf_6);
		s_tf_7=new JTextField();
		p_south_north.add(s_tf_7);
		s_tf_8=new JTextField();
		p_south_north.add(s_tf_8);
		s_tf_9=new JTextField();
		p_south_north.add(s_tf_9);
		
		//�� �� ���̺�1, �ؽ�Ʈ����1
		JPanel p_south_center=new JPanel();
		p_south.add(p_south_center,BorderLayout.CENTER);
		p_south_center.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.LIGHT_GRAY,Color.LIGHT_GRAY));
		p_south_center.setLayout(new BorderLayout());
		p_south_center.add(new JLabel("���� ��ȹ"),BorderLayout.NORTH);
		s_ta_etc=new JTextArea();
		p_south_center.add(s_ta_etc,BorderLayout.CENTER);
		
		//�� �� ��ư3
		JPanel p_south_south=new JPanel();
		p_south.add(p_south_south,BorderLayout.SOUTH);
		s_btn_insert=new JButton("����");
		p_south_south.add(s_btn_insert);
		s_btn_insert.addActionListener(this);
		s_btn_modify=new JButton("����");
		p_south_south.add(s_btn_modify);
		s_btn_modify.addActionListener(this);
		s_btn_delete=new JButton("��");
		p_south_south.add(s_btn_delete);
		s_btn_delete.addActionListener(this);
	}

	private void connectDB() {
		try {
			mModel=new ManagerModel();
		} catch (Exception e) {
			System.out.println("mModel���� ����");
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt=e.getSource();
		if (evt==n_tf_search || evt==n_btn_search) {
			searchSubject();
		} else if(evt==s_btn_insert) {
			insert();
		} else if(evt==s_btn_modify) {
			modify();
		} else if(evt==s_btn_delete) {
			delete();
		}
	}

	private void delete() {
		Subject sj=new Subject();
		sj.setSubTime(s_tf_1.getText());
		sj.setSubCategory(s_tf_2.getText());
		sj.setSubNo(Integer.parseInt(s_tf_3.getText()));
		sj.setSubName(s_tf_4.getText());
		sj.setSubYear(Integer.parseInt(s_tf_5.getText()));
		sj.setSubGrade(Integer.parseInt(s_tf_6.getText()));
		sj.setSubProfessor(s_tf_7.getText());
		sj.setSubSchedule(s_tf_8.getText());
		sj.setSubClass(s_tf_9.getText());
		sj.setSubEtc(s_ta_etc.getText());
		
		try {
			mModel.delete(sj);
			JOptionPane.showMessageDialog(null, s_tf_4.getText()+"��/�� �󰭵Ǿ����ϴ�.");
			s_tf_1.setText(null);
			s_tf_2.setText(null);
			s_tf_3.setText(null);
			s_tf_4.setText(null);
			s_tf_5.setText(null);
			s_tf_6.setText(null);
			s_tf_7.setText(null);
			s_tf_8.setText(null);
			s_tf_9.setText(null);
			s_ta_etc.setText(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void modify() {
		Subject sj=new Subject();
		sj.setSubTime(s_tf_1.getText());
		sj.setSubCategory(s_tf_2.getText());
		sj.setSubNo(Integer.parseInt(s_tf_3.getText()));
		sj.setSubName(s_tf_4.getText());
		sj.setSubYear(Integer.parseInt(s_tf_5.getText()));
		sj.setSubGrade(Integer.parseInt(s_tf_6.getText()));
		sj.setSubProfessor(s_tf_7.getText());
		sj.setSubSchedule(s_tf_8.getText());
		sj.setSubClass(s_tf_9.getText());
		sj.setSubEtc(s_ta_etc.getText());
		
		try {
			mModel.modify(sj);
			JOptionPane.showMessageDialog(null, "���� ������ ����Ǿ����ϴ�");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "���� ������ ������ �� �����ϴ�");
			e.printStackTrace();
		}
	}

	private void insert() {
		Subject sj=new Subject();
		sj.setSubTime(s_tf_1.getText());
		sj.setSubCategory(s_tf_2.getText());
		sj.setSubName(s_tf_4.getText());
		sj.setSubYear(Integer.parseInt(s_tf_5.getText()));
		sj.setSubGrade(Integer.parseInt(s_tf_6.getText()));
		sj.setSubProfessor(s_tf_7.getText());
		sj.setSubSchedule(s_tf_8.getText());
		sj.setSubClass(s_tf_9.getText());
		sj.setSubEtc(s_ta_etc.getText());
		
		try {
			mModel.insert(sj);
			JOptionPane.showMessageDialog(null, s_tf_4.getText()+"��/�� �����Ǿ����ϴ�.");
			s_tf_1.setText(null);
			s_tf_2.setText(null);
			s_tf_3.setText(null);
			s_tf_4.setText(null);
			s_tf_5.setText(null);
			s_tf_6.setText(null);
			s_tf_7.setText(null);
			s_tf_8.setText(null);
			s_tf_9.setText(null);
			s_ta_etc.setText(null);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, s_tf_4.getText()+"��/�� ������ �� �����ϴ�.");
			e.printStackTrace();
		}
	}

	private void searchSubject() {
		String str=n_tf_search.getText();
		
		try {
			ArrayList data=mModel.searchSubject(str);
			n_tb_manager.data=data;
			n_jtb_manager.setModel(n_tb_manager);
			n_tb_manager.fireTableDataChanged();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
