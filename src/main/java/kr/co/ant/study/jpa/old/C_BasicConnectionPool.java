package kr.co.ant.study.jpa.old;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class C_BasicConnectionPool {

	private static final String url = "jdbc:h2:tcp://localhost:9092/~/test";
	private static final String id = "sa";
	private static final String pw = "";
	
	public static List<Connection> pool = new ArrayList<Connection>();
	public static int max = 10;
	
	public static void main(String[] args) throws SQLException {
		
		for(int i = 0; i < max; i++) {
			pool.add(getConnection());
		}
		
		new C_BasicConnectionPool().insertMember("sss", "bbbb");
	}
	
	
	/**
	 * 회원정보 저장
	 * @param id
	 * @param name
	 * @throws SQLException
	 */
	public void insertMember(String id, String name) throws SQLException {
		//Pool에 있는 Connection 가져오기
		Connection connection = pool.remove(0);
		
		PreparedStatement insertps = connection.prepareStatement("insert into t_member(member_id, name) values(?, ?)");
		insertps.setString(1, id);
		insertps.setString(2, name);
		insertps.executeUpdate();
		
		//다쓴거 다시 집어 넣기
		pool.add(connection);
	}
	
	
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, id, pw);
	}
	
}
