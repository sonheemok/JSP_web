package com.ktds.jgbaek.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.jgbaek.article.vo.ArticleVO;
import com.ktds.jgbaek.member.vo.MemberVO;
import com.ktds.jgbaek.util.xml.XML;

public class MemberDAO {

	public MemberVO getMemberByIdAndPassword(MemberVO member) {

		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ARTICLE", "ARTICLE");

			String query = XML.getNodeString("//query/member/getMemberByIdAndPassword/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getPassword());

			rs = stmt.executeQuery();

			MemberVO validMember = null;

			if (rs.next()) {
				validMember = new MemberVO();
				validMember.setMemberId(rs.getString("MEMBER_ID"));
				validMember.setNickName(rs.getString("NICK_NAME"));
				validMember.setPassword(rs.getString("PASSWORD"));
				validMember.setEmail(rs.getString("EMAIL"));
			}
			return validMember;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}
	}

	public List<MemberVO> getAllMember() {

		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<MemberVO> members = new ArrayList<MemberVO>();

		try

		{
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ARTICLE", "ARTICLE");
			String query = XML.getNodeString("//query/member/getAllMember/text()");
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

			MemberVO member = null;

			while (rs.next()) {
				member = new MemberVO();
				member.setMemberId(rs.getString("MEMBER_ID"));
				member.setNickName(rs.getString("NICK_NAME"));
				member.setPassword(rs.getString("PASSWORD"));
				member.setEmail(rs.getString("EMAIL"));
				members.add(member);
			}
			return members;

		} catch (

		SQLException e)

		{
			throw new RuntimeException(e.getMessage(), e);
		} finally

		{
			closeDB(conn, stmt, rs);
		}
	}

	public MemberVO addNewMember(MemberVO member) {
		loadOracleDriver();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ARTICLE", "ARTICLE");
			String query = XML.getNodeString("//query/member/addNewMember/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getEmail());
			stmt.setString(3, member.getPassword());
			stmt.setString(4, member.getEmail());

			rs = stmt.executeQuery();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}
		return member;
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
