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

public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleBiz articleBiz;

	public ListServlet() {
		super();
		articleBiz = new ArticleBiz();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int pageNo = 0;
		ArticleSearchVO searchVO = new ArticleSearchVO();
		HttpSession session = request.getSession();
		try {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
			searchVO.setPageNo(pageNo);
			searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
			searchVO.setSearchCategory(request.getParameter("searchCategory"));
		} catch (NumberFormatException nfe) {
			searchVO = (ArticleSearchVO) session.getAttribute("_SEARCH_");
			if (searchVO == null) {
				searchVO = new ArticleSearchVO();
				searchVO.setPageNo(0);
				searchVO.setSearchKeyword("");
			}
		}
		session.setAttribute("_SEARCH_", searchVO);

		ArticleListVO articles = articleBiz.getArticleList(searchVO);

		request.setAttribute("articles", articles);
		request.setAttribute("searchVO", searchVO);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/article/list.jsp");

		rd.forward(request, response);
	}

}
