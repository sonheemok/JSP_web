package com.ktds.jgbaek.article.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.jgbaek.article.biz.ArticleBiz;
import com.ktds.jgbaek.article.vo.ArticleListVO;
import com.ktds.jgbaek.article.vo.ArticleSearchVO;
import com.ktds.jgbaek.history.biz.OperationHistoryBiz;
import com.ktds.jgbaek.history.vo.ActionCode;
import com.ktds.jgbaek.history.vo.BuildDescription;
import com.ktds.jgbaek.history.vo.Description;
import com.ktds.jgbaek.history.vo.OperationHistoryVO;
import com.ktds.jgbaek.member.vo.MemberVO;

public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleBiz articleBiz;
	private OperationHistoryBiz historyBiz;

	public ListServlet() {
		super();
		articleBiz = new ArticleBiz();
		historyBiz = new OperationHistoryBiz();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int pageNo = 0;

		OperationHistoryVO historyVO = new OperationHistoryVO();
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		historyVO.setIp(request.getRemoteHost());
		historyVO.setMemberId(member.getMemberId());
		historyVO.setUrl(request.getRequestURI());
		historyVO.setActionCode(ActionCode.ARTICLE);
		try {
			
			pageNo = Integer.parseInt(request.getParameter("pageNo"));			
			historyVO.setDescription(BuildDescription.get(Description.LIST_PAGIN, member.getMemberId(), pageNo+""));
			
		} catch (NumberFormatException nfe) {		
			
			historyVO.setDescription(BuildDescription.get(Description.LIST, member.getMemberId()));
		}

		historyBiz.addHistory(historyVO);
		ArticleSearchVO searchVO = new ArticleSearchVO();
		searchVO.setPageNo(pageNo);

		ArticleListVO articles = articleBiz.getArticleList(searchVO);

		request.setAttribute("articles", articles);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/article/list.jsp");

		rd.forward(request, response);
	}

}
