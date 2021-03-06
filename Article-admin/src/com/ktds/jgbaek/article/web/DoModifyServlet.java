package com.ktds.jgbaek.article.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.jgbaek.article.biz.ArticleBiz;
import com.ktds.jgbaek.article.vo.ArticleVO;
import com.ktds.jgbaek.util.Root;

public class DoModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArticleBiz articleBiz;

	public DoModifyServlet() {
		super();
		articleBiz = new ArticleBiz();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "잘못된 요청입니다.");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int articleId = Integer.parseInt(request.getParameter("articleId"));

		try {
			articleBiz.modifyArticle(request);
			response.sendRedirect(Root.get(this)+"/list");
			
		} catch (RuntimeException re) {
			System.out.println(re.getMessage());
			response.sendRedirect(Root.get(this)+"/detial?articleId=" + articleId);
		}

	}

}
