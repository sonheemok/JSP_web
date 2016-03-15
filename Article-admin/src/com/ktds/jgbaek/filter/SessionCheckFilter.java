package com.ktds.jgbaek.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.jgbaek.member.vo.MemberVO;

/**
 * Servlet Filter implementation class SessionCheckFilter
 */
public class SessionCheckFilter implements Filter {

	private List<String> whiteList;

	private List<String> staticResourceList;

	/**
	 * Default constructor.
	 */
	public SessionCheckFilter() {

		whiteList = new ArrayList<String>();
		whiteList.add("/admin/");
		whiteList.add("/admin/doLogin");
		whiteList.add("/favicon.ico");

		staticResourceList = new ArrayList<String>();
		staticResourceList.add("/admin/resource/");

	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		String uri = req.getRequestURI();
		System.out.println(uri);

		if (!whiteList.contains(uri)) {

			boolean isURIResourceFile = false;

			for (String staticResource : staticResourceList) {
				if (uri.startsWith(staticResource)) {// 사용자가 요청한 uri가 /로 시작되면
														// 지나가라
					isURIResourceFile = true;
					break;

				}
			}

			if (!isURIResourceFile) {

				HttpSession session = req.getSession();

				MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
				if (member == null) {
					HttpServletResponse res = (HttpServletResponse) response;
					res.sendRedirect("/");
					return;
				}
			}
		}
		// pass the request along the filter chain.
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
