<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/article/leftMenu.jsp"></jsp:include>

<script type="text/javascript">
$(document).ready(function() {
	
	
		$("#submit").click(function() {
			var form = $("#writeForm");
			form.attr("method", "post");
			form.attr("action", "<c:url value="/doModifyMember"/>");
			form.submit();
		});	
			
		$("#cancel").click(function() {
			location.href = "<c:url value="/memberManage"/>";
		});

	});
</script>

<div id="members" align="center">
	회원관리 페이지 <br/>
	<br/>
<form id="writeForm" enctype="multipart/form-data">
	<table border="2">
		<tr>
			<th>MEMBER_ID</th>
			<th>NICK_NAME</th>
			<th>PASSWORD</th>
			<th>EMAIL</th>
			<th>IS_ADMIN</th>
		</tr>
		<tr>
			<td align="center">${ member.memberId }</td>
			<td><input type="text" id="nickName" name="nickName" value="${ member.nickName }" style="width: 100px"/></td>
			<td><input type="text" id="password" name="password" value="${ member.password }" style="width: 100px"/></td>
			<td><input type="text" id="email" name="email" value="${ member.email }" style="width: 100px"/></td>
			<td><input type="text" id="isAdmin" name="isAdmin" value="${ member.isAdmin }" style="width: 100px"/></td>
		</tr>
	</table>
	<br />
	<input type="button" id="submit" value="확인"/>
	<input type="button" id="cancel" value="취소"/>
	</form>
</div>