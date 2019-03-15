package client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import client.subject.Subject;

public class SubjectModel {
	Connection con;
	
	public SubjectModel() throws Exception {
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

	public ArrayList searchSubject(int idx, String str) throws Exception {
		String[]key={"category","name","professor"};
		String sql="select time,category,no,name,year,grade,professor,schedule,class,etc from subject where "+key[idx]+" like '%"+str+"%'";
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
		String sql="insert into mylist select time,category,no,name,year,grade,professor,schedule,class,etc from subject where no=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, ""+sj.getSubNo());
		int r=ps.executeUpdate();
		if (r!=1) {
			con.rollback();
		}
		con.commit();
		ps.close();
		con.setAutoCommit(true);
	}
}
