package kr.co.ant.study.jpa.old;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

public class B_BasicOldStyleTransaction {

	private static final String url = "jdbc:h2:tcp://localhost:9092/~/test";
	private static final String id = "sa";
	private static final String pw = "";
	
	public static void main(String[] args) throws SQLException {
		B_BasicOldStyleTransaction dao = new B_BasicOldStyleTransaction();
		
		String id = "c2";
		String name="품2";
		
		Connection connection = dao.getConnection();
		dao.insertMember(connection, id, name);

		//오류 나면 여기 부터  rollback 지점 정의
		Savepoint save = connection.setSavepoint();
		dao.updateMember(connection, id);
		try {
			dao.insertAddress(connection, id, "adress");
			connection.commit();
		}catch(Exception e) {
			//롤백지점 까지만 롤백 하고
			connection.rollback(save);
			//나머지 커밋
			connection.commit();;
			e.printStackTrace();
		}
		
		
		/*Connection connection = dao.getConnection();
		dao.insertMember(connection, id, name);
		//저장하고 커밋
		connection.commit();
		
		dao.updateMember(connection, id);
		try {
			dao.insertAddress(connection, id, "adress1111111111");
			connection.commit();
		}catch(Exception e) {
			//오류나면 롤백
			connection.rollback();
			e.printStackTrace();
		}*/
		
		
	}
	
	public void insertMember(Connection connection, String id, String name) throws SQLException {
		PreparedStatement insertps = connection.prepareStatement("insert into t_member(member_id, name) values(?, ?)");
		
		insertps.setString(1, id);
		insertps.setString(2, name);
		insertps.executeUpdate();
		
	}
	
	public void insertAddress(Connection connection, String id, String addr) throws Exception {
		if(addr.length() > 10) {
			throw new Exception();
		}
		PreparedStatement detail = connection.prepareStatement("insert into t_member_detail(member_id, addr) values(?, ?)");
		detail.setString(1, id);
		detail.setString(2, addr);
		detail.executeUpdate();
	}
	
	public void updateMember(Connection connection, String id) throws SQLException {
		PreparedStatement update = connection.prepareStatement("update t_member set addr_yn = ? where member_id = ?");
		update.setString(1, "Y");
		update.setString(2, id);
		update.executeUpdate();
	}
	
	public void selectMember(String firstName) throws SQLException {
		Connection connection = getConnection();
		PreparedStatement ps =  connection.prepareStatement("select member_id, name from t_member where member_id=?");
		ps.setString(1, firstName);
		if(ps.execute()) {
			ResultSet rs = ps.getResultSet();
			while(rs.next()) {
				System.out.println(rs.getString("member_id")+"_"+rs.getString("name"));
			}
		}
	}
	
	public Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(url, id, pw);
		connection.setAutoCommit(false);
		return connection;
	}
	
}
