package client.display.enrolment.tablemodel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MylistTableModel extends AbstractTableModel{
	public ArrayList data=new ArrayList();
	String[]colNames={"��������","�̼�����","���ǹ�ȣ","���Ǹ�","�г�","����","��米��","����","���ǽ�","���� ��ȹ"};
	
	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		ArrayList temp=(ArrayList) data.get(row);
		return temp.get(col);
	}

	@Override
	public String getColumnName(int col) {
		return colNames[col];
	}
}
