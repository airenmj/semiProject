package client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import client.subject.Subject;

public class MyListModel {
	Connection con;
	
	public MyListModel() throws Exception {
		con=DBcon.getConnection();
	}

	public ArrayList refresh() throws Exception {
		String sql="select time,category,no,name,year,grade,professor,schedule,class,etc from mylist where no>'0'";
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		ArrayList data=new ArrayList();
		while (rs.next()) {
			ArrayList temp=new ArrayList();
			temp.add(rs.getString("time"));
			temp.add(rs.getString("category"));
			temp.add(rs.getString("no"));
			temp.add(rs.getString("name"));
			temp.add(rs.getString("year"));
			temp.add(rs.getString("grade"));
			temp.add(rs.getString("professor"));
			temp.add(rs.getString("schedule"));
			temp.add(rs.getString("class"));
			temp.add(rs.getString("etc"));
			data.add(temp);
		}
		rs.close();
		ps.close();
		return data;
	}

	public ArrayList hakjum() throws Exception {
		String sql="select sum(grade) from mylist";
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		ArrayList data=new ArrayList();
		while(rs.next()){
			data.add(rs.getString("sum(grade)"));
		}
		rs.close();
		ps.close();
		return data;
	}

	public void delete(Subject sj) throws Exception {
		con.setAutoCommit(false);
		String sql="delete mylist where no=?";
		PreparedStatement ps1=con.prepareStatement(sql);
		ps1.setInt(1, sj.getSubNo());
		
		int r=ps1.executeUpdate();
		if (r!=1) {
			con.rollback();
		}
		con.commit();
		ps1.close();
		con.setAutoCommit(true);
	}

	public ArrayList fillTimeTable() throws Exception {
		String sql="select schedule from mylist";
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		ArrayList data=new ArrayList();
		while(rs.next()){
			data.add(rs.getString("schedule"));
		}
		rs.close();
		ps.close();
		return data;
	}
}
