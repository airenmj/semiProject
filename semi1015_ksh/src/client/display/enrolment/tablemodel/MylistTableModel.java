package client.display.enrolment.tablemodel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MylistTableModel extends AbstractTableModel{
	public ArrayList data=new ArrayList();
	String[]colNames={"조직구분","이수구분","강의번호","강의명","학년","학점","담당교수","교시","강의실","강의 계획"};
	
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
