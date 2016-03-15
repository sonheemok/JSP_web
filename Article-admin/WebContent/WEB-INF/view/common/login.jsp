<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	Cookie[] cookies = request.getCookies();

	String userId = "";
	String userPassword = "";
	String autoLogin = "";

	if (cookies != null) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("userId")) {
				userId = cookie.getValue();
			}
			else if (cookie.getName().equals("userPassword")) {
				userPassword = cookie.getValue();
			} 
			else if (cookie.getName().equals("autoLogin")) {
				autoLogin = cookie.getValue();
			}
		}
	}
%>
<script type="text/javascript">

	$(document).ready(function() {
		
	
		$("#btnLogin").click(function() {
			
			var form = $("#loginForm");
			form.attr("method", "post");
			form.attr("action", "<c:url value="/doLogin"/>");
			form.submit();
		});
		
		var autoLogin = "<%=autoLogin%>"; 
		if ( autoLogin == "true") {
			$("#userPw").val("<%=userPassword%>");
			$("#userId").val("<%=userId%>");
			$("#btnLogin").click();
		}

		$("#btnJoin").click(function() {
			location.href = "<c:url value="/registerMember"/>";
		});
		$("#userPw").keyup(function(){
			if ( e.keCode ==13) {
				//Enter입력했다면
				$("#btnLogin").click();
			}
		});

	});
</script>

<div id="login">
	<img  src="<c:url value="/resource/img/join.png"/>" id="btnJoin" />
	<form id="loginForm">
		<img tabindex="3" src="<c:url value="/resource/img/login.png"/>" id="btnLogin" />
		<input type="password" tabindex="2" id="userPw" name="userPw"
			placeholder="Password" />
		<input type="text" tabindex="1"
			id="userId" name="userId" placeholder="ID" />
	</form>

</div>
<div class="clear"></div>
</div>