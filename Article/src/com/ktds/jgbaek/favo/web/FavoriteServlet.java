package com.ktds.jgbaek.favo.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.jgbaek.favo.biz.FavoriteBiz;
import com.ktds.jgbaek.favo.vo.FavoVO;
import com.ktds.jgbaek.history.biz.OperationHistoryBiz;
import com.ktds.jgbaek.history.vo.OperationHistoryVO;
import com.ktds.jgbaek.member.vo.MemberVO;

/**
 * Servlet implementation class FavoriteServlet
 */
public class FavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FavoriteBiz favoriteBiz;
	private OperationHistoryBiz operationHistoryBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavoriteServlet() {
        super();
        favoriteBiz = new FavoriteBiz();
        operationHistoryBiz = new OperationHistoryBiz();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN,"잘못된 요청입니다.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int articleId = Integer.parseInt(request.getParameter("articleId"));
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		FavoVO favoriteVO = new FavoVO();
		favoriteVO.setArticleId(articleId);
		favoriteVO.setMemberId(member.getMemberId());
		favoriteBiz.insertFavoriteData(favoriteVO);
		
		OperationHistoryVO historyVO = (OperationHistoryVO) request.getAttribute("OperationHistoryVO");
		historyVO.setActionCode("AR_F");
		String message = "[%s]가[%s]글을 즐겨찾기에 등록했습니다.";
		
		boolean isExistFavoriteData = favoriteBiz.isExistFavoriteData(favoriteVO);
		if(isExistFavoriteData){
			message="[%s]가 [%s] 글을 삭제했습니다.";
		}
		
		historyVO.setDescription(String.format(message, member.getMemberId(), articleId+""));
		operationHistoryBiz.addHistory(historyVO);
		
		StringBuffer json = new StringBuffer();
		json.append("{");
		json.append("\"result\" : true");
		json.append(", \"isFavorite\" : " +isExistFavoriteData);
		json.append("}");
		
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
		
	}

}
