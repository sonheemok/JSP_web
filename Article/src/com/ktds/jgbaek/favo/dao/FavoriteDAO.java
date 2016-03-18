package com.ktds.jgbaek.favo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ktds.jgbaek.article.vo.ArticleVO;
import com.ktds.jgbaek.favo.vo.FavoVO;
import com.ktds.jgbaek.util.xml.XML;

public class FavoriteDAO {
	public int selectFavoriteCount(FavoVO favoriteVO) {

		loadOracleDriver();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ARTICLE", "ARTICLE");
			String query = XML.getNodeString("//query/favorite/selectFavoriteCount/text()");
			stmt = conn.prepareStatement(query);

			stmt.setInt(1, favoriteVO.getArticleId());
			stmt.setString(2, favoriteVO.getMemberId());

			rs = stmt.executeQuery();
			rs.next();

			return rs.getInt(1);

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}
	}
	
	public void insertFavorite(FavoVO favoriteVO){
		loadOracleDriver();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ARTICLE", "ARTICLE");
			String query = XML.getNodeString("//query/favorite/insertFavorite/text()");
			stmt = conn.prepareStatement(query);

			stmt.setInt(1, favoriteVO.getArticleId());
			stmt.setString(2, favoriteVO.getMemberId());

			stmt.executeUpdate();


		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}
	}
	
	public void deleteFavorite(FavoVO favoriteVO){
		loadOracleDriver();
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ARTICLE", "ARTICLE");
			String query = XML.getNodeString("//query/favorite/deleteFavorite/text()");
			stmt = conn.prepareStatement(query);

			stmt.setInt(1, favoriteVO.getArticleId());
			stmt.setString(2, favoriteVO.getMemberId());

			stmt.executeUpdate();


		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}
	}

	private void closeDB(Connection conn, PreparedStatement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}

	private void loadOracleDriver() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

}
