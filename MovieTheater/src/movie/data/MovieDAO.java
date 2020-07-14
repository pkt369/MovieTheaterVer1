package movie.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbcp2.BasicDataSource;

public class MovieDAO {
	BasicDataSource ds;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String userid = "scott";
	private String passwd = "tiger";
	
	public MovieDAO(){
		ds = new BasicDataSource(); //Connection Pool 기능과 Thread-safe 기능을 갖춤.
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(userid);
		ds.setPassword(passwd);
		
		ds.setInitialSize(5);
	}
	
	public void insertMovie(String name, int num, String url) {
		Connection conn = null;
		String sql = "insert into movies values(?, ?, ?)";
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, num);
			pstmt.setString(3, url);
			
			int result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("성공");
			}else {
				System.out.println("실패");
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}
	
	public ArrayList<Movie> selectMovie() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select * from movies";
		ArrayList<Movie> list = new ArrayList<Movie>();
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Movie mov = new Movie();
				
				mov.setName(rs.getString("name"));
				mov.setAge(rs.getInt("age"));
				mov.setUrl(rs.getURL("url"));
				
				list.add(mov);
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteMovie(String name) {
		//영화이름이 있는지 확인
		ArrayList<Movie> list = selectMovie();
		int count = 0;
		for(Movie e : list) {
			if(e.getName().equals(name)) {
//				System.out.println("같은이름있음");
				count++;
				break;
			}
		}
		if(count == 0) {
//			System.out.println("같은 이름 없음");
			return;
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from movies where name = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
