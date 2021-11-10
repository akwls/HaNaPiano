package pack;
import java.sql.*;

public class MySQLConnect {
	public MySQLConnect() {
		
	}
	public void connect() {
		try {
			// Class.forName("org.git.mm.mysql.Driver");// 1. jdbc 드라이브 연결
			Class.forName("com.mysql.cj.jdbc.Driver"); // 로 변경이 됨
			System.out.println("드라이브 연결이 잘됨");
		} catch(ClassNotFoundException ee) {
			System.out.println("DB 연결 드라이브가 없음");
		}
		
		Connection conn = null;
		String url = "jdbc:mysql: //127.0.0.1:3306/hanapiano_db";
		String id = "root";
		String pw = "mirim";
		
		try {
			conn = DriverManager.getConnection(url, id, pw);// 2. 데이터베이스 연결
		} catch(SQLException ee) {
			System.out.println("DB 연결실패");
		}
		
		Statement stmt = null; // 3. 명령어 처리 객체 생성
		try {
			stmt = conn.createStatement();
		} catch(SQLException ee) {
			System.out.println("작업 처리 객체 생성 실패");
		}
		
		
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery("select * from user");// 4. 결과보기
			while(rs.next()) {
				System.out.println(rs.getString("id"));
			}
		} catch(SQLException ee) {
			System.out.println("명령어 전송 실패"+ee.toString());
		}
		
		
		try {
			// rs.close();
			stmt.close();
			conn.close();
		} catch(SQLException ee) {
			System.out.println("접속 종료 실패"+ee.toString());
		}
	}
}

