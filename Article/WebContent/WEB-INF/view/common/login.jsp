<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Cookie[] cookies = request.getCookies();

	String userId = "";
	String userPassword = "";
	String autoLogin = "";

	if (cookies != null) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("userId")) {
				userId = cookie.getValue();
			} else if (cookie.getName().equals("userPassword")) {
				userPassword = cookie.getValue();
			} else if (cookie.getName().equals("autoLogin")) {
				autoLogin = cookie.getValue();
			}
		}
	}
%>

<script type="text/javascript">

	FB.init({
	    appId : '437795256418001', // App ID
	    cookie : true,
	    status : true,
	    xfbml : true
	});
	function checkLoginState() {
	    FB.getLoginStatus(function(response) {
	        statusChangeCallback(response);
	    });
	}
	function statusChangeCallback(response) {
	    console.log('statusChangeCallback');
	    console.log(response);
	    
	    if (response.status === 'connected') {
	        // Logged into your app and Facebook.
	       login();
	    } else if (response.status === 'not_authorized') {
	        document.getElementById('status').innerHTML = 'Please log '
	                + 'into this app.';
	    } else {
	        document.getElementById('status').innerHTML = 'Please log '
	                + 'into Facebook.';
	    }
	}
	function login() {
        console.log('Welcome!  Fetching your information.... ');
        FB.api(
            '/me',
            function(response) {
               $("#userId").val(response.id);
               $("#userPwd").val(response.name);
               $("#facebookLogin").val("Y");
               
               var form = $("#loginForm");
               form.attr("method", "post");
               form.attr("action", "/doLogin");
               form.submit();
                }
        );
    }

	$(document).ready(function() {
		$("#btnLogin").click(function() {
			
			var form = $("#loginForm");
			form.attr("method", "post");
			form.attr("action", "/doLogin");
			form.submit();
		});
		
		var autoLogin = "<%=autoLogin%>"; 
		if ( autoLogin == "true") {
			$("#userPw").val("<%=userPassword%>");
			$("#userId").val("<%=userId%>");
			$("#btnLogin").click();
		}

		$("#btnJoin").click(function() {
			location.href = "/registerMember";
		});

	});
</script>

<div id="login">
	<fb:login-button scope="public_profile,email"
		onlogin="checkLoginState();"></fb:login-button>

	<img tabindex="3" src="/resource/img/join.png" id="btnJoin" />
	<form id="loginForm">
		<img tabindex="3" src="/resource/img/login.png" id="btnLogin" /> <input
			type="password" tabindex="2" id="userPw" name="userPw"
			placeholder="Password" /> <input type="text" tabindex="1"
			id="userId" name="userId" placeholder="ID" />
			<input type="hidden" id="facebookLogin" name="facebookLogin"/>
	</form>

</div>
<div class="clear"></div>
</div>