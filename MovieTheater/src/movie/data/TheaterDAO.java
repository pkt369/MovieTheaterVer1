package movie.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbcp2.BasicDataSource;

public class TheaterDAO {
	BasicDataSource ds;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String userid = "scott";
	private String passwd = "tiger";
	
	public TheaterDAO(){
		ds = new BasicDataSource(); //Connection Pool 기능과 Thread-safe 기능을 갖춤.
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(userid);
		ds.setPassword(passwd);
		
		ds.setInitialSize(5);
	}
	
	public void insertTheater(String city, String TheaterName, String movie, String day,
			String startTime, String auditorium) {
		Connection conn = null;
		String sql = "insert into theater values(?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, city);
			pstmt.setString(2, TheaterName);
			pstmt.setString(3, movie);
			pstmt.setString(4, day);
			pstmt.setString(5, startTime);
			pstmt.setString(6, auditorium);
			pstmt.setString(7, null);
			
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
	
	public ArrayList<theater> selectTheater() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select * from theater";
		ArrayList<theater> list = new ArrayList<theater>();
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				theater mov = new theater();
				
				mov.setCity(rs.getString("city"));
				mov.setTheaterName(rs.getString("TheaterName"));
				mov.setMovie(rs.getString("movie"));
				mov.setDay(rs.getString("day"));
				mov.setStartTime(rs.getString("startTime"));
				mov.setAuditorium(rs.getString("auditorium"));
				mov.setSellSeats(rs.getString("sellSeats"));
				
				list.add(mov);
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteTheater(String movie, String day, String time) {
		//영화이름이 있는지 확인
		ArrayList<theater> list = selectTheater();
		int count = 0;
		for(theater e : list) {
			if(e.getMovie().equals(movie) && 
					e.getDay().equals(day) &&
					e.getStartTime().equals(time)) {
//				System.out.println("같은이름있음");
				count++;
				break;
			}
		}
		if(count == 0) {
			return;
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from theater where movie = ? AND day = ? AND startTime = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, movie);
			pstmt.setString(2, day);
			pstmt.setString(3, time);
			
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
