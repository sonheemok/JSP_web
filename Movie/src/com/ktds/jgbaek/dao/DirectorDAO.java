package com.ktds.jgbaek.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.jgbaek.util.xml.XML;
import com.ktds.jgbaek.vo.ActorVO;
import com.ktds.jgbaek.vo.DirectorVO;
import com.ktds.jgbaek.vo.MovieVO;

public class DirectorDAO {
	
	public List<DirectorVO> getAllDirector() {
		
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<DirectorVO> directors = new ArrayList<DirectorVO>();
		
		try {
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "MOVIE", "MOVIE");
			//String query = "SELECT M.*, G.GRADE_TITLE, AC.ACTOR_COUNT FROM MOVIE M, GRADE G, (SELECT MOVIE_ID, COUNT ( ACTOR_LIST_ID) ACTOR_COUNT FROM ACTOR_LIST GROUP BY MOVIE_ID) AC WHERE M.GRADE_ID = G.GRADE_ID AND AC.MOVIE_ID(+) = M.MOVIE_ID";
			String query = XML.getNodeString("//query/director/getAllDirector/text()");
			System.out.println(query);
			
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
			
			DirectorVO director = null;
			
			while ( rs.next() ) {
				director = new DirectorVO();
				director.setDirectorId(rs.getInt("DIRECTOR_ID"));
				director.setDirectorName(rs.getString("DIRECTOR_NAME"));				
				directors.add(director);
				
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			closeDB(conn, stmt, rs);
		}
		return directors;
	}

	
	public List<DirectorVO> getDirectorsInfoByDirectorId( int movieId ) {
		
		// 1. DriverLoading
		loadOracleDriver();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<DirectorVO> directors = new ArrayList<DirectorVO>();
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "MOVIE", "MOVIE");
			String query = XML.getNodeString("//query/director/getDirectorsInfoByMovieId/text()");
			stmt = conn.prepareStatement(query);
			
			//SQL Parameter Mapping
			//몇번째 물음표를 어디파라미터에 넣을 것인가?
			stmt.setInt(1, movieId);
			DirectorVO director = null;
			rs = stmt.executeQuery();
			while ( rs.next() ) {
				director = new DirectorVO();

				director.setDirectorName(rs.getString("DIRECTOR_NAME"));
				directors.add(director);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			closeDB(conn, stmt, rs);
		}
		
		return directors;
	}
	
	public int insertNewDirectorOfNewMovie ( DirectorVO director ){
		
		int insertCount = 0;
		
		loadOracleDriver();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "MOVIE", "MOVIE");
			String query = XML.getNodeString("//query/director/insertNewDirectorOfNewMovie/text()");
			stmt = conn.prepareStatement(query);
			
			//SQL Parameter Mapping
			//몇번째 물음표를 어디파라미터에 넣을 것인가?
			stmt.setInt(1, director.getDirectorId());
			stmt.setInt(2, director.getMovieId());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			closeDB(conn, stmt, null);
		}
		
		return 0;
	}
	
	private void loadOracleDriver() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
	}
	
	private void closeDB(Connection conn, PreparedStatement stmt, ResultSet rs) {
		if ( rs != null ) {
			try {
				rs.close();
			} catch (SQLException e) {}
		}
		if ( stmt != null ) {
			try {
				stmt.close();
			} catch (SQLException e) {}
		}
		if ( conn != null ) {
			try {
				conn.close();
			} catch (SQLException e) {}
		}
	}
}