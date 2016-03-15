package com.ktds.jgbaek.member.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.jgbaek.member.biz.MemberBiz;
import com.ktds.jgbaek.member.vo.MemberVO;
import com.ktds.jgbaek.util.Root;

/**
 * Servlet implementation class DoLoginServlet
 */
public class DoLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberBiz memberBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoLoginServlet() {
        super();
        memberBiz = new MemberBiz();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "로그인 할 수 없습니다.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("userId");
		String memberPassword = request.getParameter("userPw");
		
		MemberVO loginMember = new MemberVO();
		loginMember.setMemberId(memberId);
		loginMember.setPassword(memberPassword);
		
		boolean isLoginSuccess = memberBiz.login(loginMember, request);		
		 if ( isLoginSuccess ) {
			 
			 Cookie autoLogin = new Cookie("autoLogin", "true");
			 autoLogin.setMaxAge(0);
			 response.addCookie(autoLogin);
			 
			 Cookie userId = new Cookie("userId",memberId);
			 userId.setMaxAge(999);
			 response.addCookie(userId);
			 
			 Cookie userPassword = new Cookie("userPassword", memberPassword);
			 userPassword.setMaxAge(999);
			 response.addCookie(userPassword);
			 
			 response.sendRedirect(Root.get(this)+"/list");
			 return;
		 }
		 else {
			 response.sendRedirect(Root.get(this)+"/?errorCode=1");
			 return;
		 }
		
	}

}
