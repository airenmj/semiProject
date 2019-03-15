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
import javax.swing.JComboBox;
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

import client.display.enrolment.tablemodel.SubjectTableModel;
import client.model.SubjectModel;
import client.subject.Subject;


public class EnrolmentSubject extends JPanel implements ActionListener {
	JTextField n_tf_search, s_tf_1, s_tf_2, s_tf_3, s_tf_4, s_tf_5, s_tf_6, s_tf_7, s_tf_8, s_tf_9;
	JComboBox n_cb_category;	
	JButton n_btn_search, n_btn_insert;
	JTextArea s_ta_etc;
	JTable n_jtb_subject;
	SubjectTableModel n_tb_subject;
	SubjectModel sModel; 
	
	public EnrolmentSubject() {
		addLayout();
		connectDB();
	}
	
	private void connectDB() {
		try {
			sModel=new SubjectModel();
		} catch (Exception e) {
			System.out.println("sModel���� ����");
			e.printStackTrace();
		}
	}

	private void addLayout() {
		setLayout(new GridLayout(2, 1));
		JPanel p_north=new JPanel();
		p_north.setLayout(new BorderLayout());
		p_north.setBorder(new TitledBorder("���� ��ȸ"));
		JPanel p_south=new JPanel();
		p_south.setLayout(new BorderLayout());
		p_south.setBorder(new TitledBorder("���� ����"));
		add(p_north);
		add(p_south);

		//�� ��
		JPanel p_north_north=new JPanel();
		p_north.add(p_north_north,BorderLayout.NORTH);
		p_north_north.setLayout(new BorderLayout());
		
		//�� �� �� �޺��ڽ�1, �ؽ�Ʈ�ʵ�1, ��ư1
		JPanel p_north_north_west=new JPanel();
		p_north_north.add(p_north_north_west,BorderLayout.WEST);
		String[]category={"�̼�����","���Ǹ�","��米��"};
		n_cb_category=new JComboBox(category);
		n_tf_search=new JTextField(15);
		n_btn_search=new JButton("�˻�");
		n_tf_search.addActionListener(this);
		n_btn_search.addActionListener(this);
		n_btn_insert=new JButton("���");
		n_btn_insert.addActionListener(this);
		p_north_north_west.add(n_cb_category);
		p_north_north_west.add(n_tf_search);
		p_north_north_west.add(n_btn_search);
		p_north_north_west.add(n_btn_insert);
		
		//�� ������ ���̺� �׳� ����
		JPanel p_north_remain=new JPanel();
		p_north.add(p_north_remain);
		n_tb_subject=new SubjectTableModel();
		n_jtb_subject=new JTable(n_tb_subject);
		n_jtb_subject.setModel(n_tb_subject);
		n_jtb_subject.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row=n_jtb_subject.getSelectedRow();
				int col=2;
				String data=(String) n_jtb_subject.getValueAt(row, col);
				int no=Integer.parseInt(data);
				
				try {
					Subject sj = sModel.selecterByPK(no);
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
		JScrollPane sp=new JScrollPane(n_jtb_subject);
		sp.setPreferredSize(new Dimension(screenSize.width/10*7, screenSize.height/100*28));
		p_north_remain.add(sp,BorderLayout.CENTER);
		
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
//		s_tf_1.setEnabled(false);
		p_south_north.add(s_tf_1);
		s_tf_2=new JTextField();
//		s_tf_2.setEnabled(false);
		p_south_north.add(s_tf_2);
		s_tf_3=new JTextField();
//		s_tf_3.setEnabled(false);
		p_south_north.add(s_tf_3);
		s_tf_4=new JTextField();
//		s_tf_4.setEnabled(false);
		p_south_north.add(s_tf_4);
		s_tf_5=new JTextField();
//		s_tf_5.setEnabled(false);
		p_south_north.add(s_tf_5);
		s_tf_6=new JTextField();
//		s_tf_6.setEnabled(false);
		p_south_north.add(s_tf_6);
		s_tf_7=new JTextField();
//		s_tf_7.setEnabled(false);
		p_south_north.add(s_tf_7);
		s_tf_8=new JTextField();
//		s_tf_8.setEnabled(false);
		p_south_north.add(s_tf_8);
		s_tf_9=new JTextField();
//		s_tf_9.setEnabled(false);
		p_south_north.add(s_tf_9);
		
		//�� ������ ���̺�1, �ؽ�Ʈ����1
		JPanel p_south_remain=new JPanel();
		p_south.add(p_south_remain,BorderLayout.CENTER);
		p_south_remain.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.LIGHT_GRAY,Color.LIGHT_GRAY));
		p_south_remain.setLayout(new BorderLayout());
		p_south_remain.add(new JLabel("���� ��ȹ"),BorderLayout.NORTH);
		s_ta_etc=new JTextArea();
//		s_ta_etc.setEnabled(false);
		p_south_remain.add(s_ta_etc,BorderLayout.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt=e.getSource();
		if (evt==n_btn_search || evt==n_tf_search) {
			searchSubject();
		} else if(evt==n_btn_insert){
			insert();
		}
	}

	private void insert() {
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
			sModel.insert(sj);
			JOptionPane.showMessageDialog(null, s_tf_4.getText()+"��/�� ��Ͽ� �����ϴ�.");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, s_tf_4.getText()+"��/�� ��Ͽ� ���� �� �����ϴ�.\n�ش� ���ÿ� �ش��ϴ� ���ǰ� ��Ͽ� �ֽ��ϴ�.");
		} 
	}

	private void searchSubject() {
		int idx=n_cb_category.getSelectedIndex();
		String str=n_tf_search.getText();
		
		try {
			ArrayList data=sModel.searchSubject(idx,str);
			n_tb_subject.data=data;
			n_jtb_subject.setModel(n_tb_subject);
			n_tb_subject.fireTableDataChanged();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
