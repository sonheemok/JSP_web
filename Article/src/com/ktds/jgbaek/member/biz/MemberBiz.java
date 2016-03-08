package com.ktds.jgbaek.member.biz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ktds.jgbaek.member.dao.MemberDAO;
import com.ktds.jgbaek.member.vo.MemberVO;

public class MemberBiz {
	
	private MemberDAO memberDAO;
	
	public MemberBiz() {
		memberDAO = new MemberDAO();
		
	}
	public boolean login ( MemberVO member, HttpServletRequest request ) {
		
		// 1. 회원 정보를 가져온다.
		MemberVO loginMember = memberDAO.getMemberByIdAndPassword(member);
		
		// 2. 회원 정보가 존재한다면, 세션에 집어 넣는다.
		// 세션은 request가 가지고 있따.
		if ( loginMember != null) {
			HttpSession session = request.getSession();
			session.setAttribute("_MEMBER_", loginMember); // _MEMBER_ 세션 명명 규칙
		}
		
		// 3. 결과를 보고한다.		
		return loginMember != null;
		
	}
}
