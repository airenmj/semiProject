package client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import client.subject.Subject;

public class ManagerModel {
	Connection con;
	
	public ManagerModel() throws Exception {
		con=DBcon.getConnection();
	}

	public Subject selecterByPK(int no) throws Exception {
		Subject sj=new Subject();
		String sql="select * from subject where no="+no;
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while (rs.next()) {
			sj.setSubTime(rs.getString("time"));
			sj.setSubCategory(rs.getString("category"));
			sj.setSubNo(Integer.parseInt(rs.getString("no")));
			sj.setSubName(rs.getString("name"));
			sj.setSubYear(Integer.parseInt(rs.getString("year")));
			sj.setSubGrade(Integer.parseInt(rs.getString("grade")));
			sj.setSubProfessor(rs.getString("professor"));
			sj.setSubSchedule(rs.getString("schedule"));
			sj.setSubClass(rs.getString("class"));
			sj.setSubEtc(rs.getString("etc"));
		}
		rs.close();
		ps.close();
		return sj;
	}

	public ArrayList searchSubject(String str) throws Exception {
		String sql="select time,category,no,name,year,grade,professor,schedule,class,etc from subject where name like '%"+str+"%'";
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

	public void insert(Subject sj) throws Exception {
		con.setAutoCommit(false);
		String sql="insert into subject values(?,?,seq_sub.nextval,?,?,?,?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, sj.getSubTime());
		ps.setString(2, sj.getSubCategory());
		ps.setString(3, sj.getSubName());
		ps.setInt(4, sj.getSubYear());
		ps.setInt(5, sj.getSubGrade());
		ps.setString(6, sj.getSubProfessor());
		ps.setString(7, sj.getSubSchedule());
		ps.setString(8, sj.getSubClass());
		ps.setString(9, sj.getSubEtc());
		
		int r=ps.executeUpdate();
		if (r!=1) {
			con.rollback();
		}
		con.commit();
		ps.close();
		con.setAutoCommit(true);
	}

	public void modify(Subject sj) throws Exception {
		String sql="update subject set time=?,category=?,name=?,year=?,grade=?,professor=?,schedule=?,class=?,etc=? where no=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, sj.getSubTime());
		ps.setString(2, sj.getSubCategory());
		ps.setString(3, sj.getSubName());
		ps.setInt(4, sj.getSubYear());
		ps.setInt(5, sj.getSubGrade());
		ps.setString(6, sj.getSubProfessor());
		ps.setString(7, sj.getSubSchedule());
		ps.setString(8, sj.getSubClass());
		ps.setString(9, sj.getSubEtc());
		ps.setInt(10, sj.getSubNo());
		
		int r=ps.executeUpdate();
		if (r!=1) {
			con.rollback();
		}
		con.commit();
		ps.close();
	}

	public void delete(Subject sj) throws Exception {
		con.setAutoCommit(false);
		String sql="delete subject where no=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, sj.getSubNo());
		
		int r=ps.executeUpdate();
		if (r!=1) {
			con.rollback();
		}
		con.commit();
		ps.close();
		con.setAutoCommit(true);
	}
}
