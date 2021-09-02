package kr.co.ant.study.jpa.old;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class A_BasicOldStyle {

	private static final String url = "jdbc:h2:tcp://localhost:9092/~/test";
	private static final String id = "sa";
	private static final String pw = "";
	
	
	/** -- DDL
	 CREATE TABLE PUBLIC.t_member (
	MEMBER_ID VARCHAR(5) NOT NULL,
	NAME VARCHAR(100) NOT NULL,
	CONSTRAINT MEMBER_PK PRIMARY KEY (MEMBER_ID)
	);
	CREATE INDEX T_MEMBER_MEMBER_ID_IDX ON PUBLIC.t_member (MEMBER_ID);
	CREATE UNIQUE INDEX T_PRIMARY_KEY_B ON PUBLIC.t_member (MEMBER_ID);
	
	CREATE TABLE PUBLIC.T_MEMBER_DETAIL (
		ID INTEGER NOT NULL AUTO_INCREMENT,
		MEMBER_ID VARCHAR(5) NOT NULL,
		ADDR VARCHAR(10),
		CONSTRAINT T_MEMBER_DETAIL_PK PRIMARY KEY (ID),
		CONSTRAINT T_MEMBER_DETAIL_FK FOREIGN KEY (MEMBER_ID) REFERENCES PUBLIC.T_MEMBER(MEMBER_ID) ON DELETE RESTRICT ON UPDATE RESTRICT
	);
	CREATE UNIQUE INDEX PRIMARY_KEY_37 ON PUBLIC.T_MEMBER_DETAIL (ID);
	CREATE INDEX T_MEMBER_DETAIL_FK_INDEX_3 ON PUBLIC.T_MEMBER_DETAIL (MEMBER_ID);
 
	 */
	
	public static void main(String[] args) throws SQLException {
		A_BasicOldStyle dao = new A_BasicOldStyle();
		dao.insertMember("choi6", "품팔6");
		dao.selectMember("choi2");
		
	}
	
	/**
	 * 회원정보 저장
	 * @param id
	 * @param name
	 * @throws SQLException
	 */
	public void insertMember(String id, String name) throws SQLException {
		Connection connection = getConnection();
		PreparedStatement insertps = connection.prepareStatement("insert into t_member(member_id, name) values(?, ?)");
		
		insertps.setString(1, id);
		insertps.setString(2, name);
		insertps.executeUpdate();
	}
	
	/**
	 * 회원정보 조회
	 * @param firstName
	 * @throws SQLException
	 */
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
		return DriverManager.getConnection(url, id, pw);
	}
	
}
