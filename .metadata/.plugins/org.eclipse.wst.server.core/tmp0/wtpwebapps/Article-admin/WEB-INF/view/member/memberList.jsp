<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/article/leftMenu.jsp"></jsp:include>

<script type="text/javascript">
	$(document).ready(function(){
		$("#massiveSelectCheckBox").click(function(){
			 var isChecked = $(this).prop("checked");
			 $(".deleteMemberIds").prop("checked",isChecked);			 
		});
		
		
		$("#deleteBtn").click(function(){
			var isChecked = false;

			$(".deleteMemberIds").each(function(index,data){
				
				if (data.checked ) {
					isChecked = data.checked
				}
			});
			
			 if (!isChecked){
			alert("삭제할 대상을 선택하세요");
			return;
		} 
			if(confirm("정말 삭제하시겠습니까?")){
				var form=$("#massiveDeleteForm");
				form.attr("method", "POST");
				form.attr("action", "<c:url value="/memberDelete"/>");
				form.submit();
			}
		});
	});
</script>

<body>

<div id="members" align="center">
	회원관리 페이지
	<br/><br/>
	
	<table border="2" >
		<tr>
			<th><input type="checkbox" id="massiveSelectCheckBox"/></th>
			<th>MEMBER_ID</th>
			<th>NICK_NAME</th>
			<th>PASSWORD</th>
			<th>EMAIL</th>
			<th>IS_ADMIN</th>
		</tr>	
	
	<form id="massiveDeleteForm">
	<c:forEach items="${ members }" var="members">
		<tr>
			<td>
				<input type="checkbox" class="deleteMemberIds"
						name = "deleteMemberIds"
						value ="${members.memberId }"/>
			</td>
			<td>
			<a href="<c:url value="/detailMember?memberId=${ members.memberId }"/>">
			${ members.memberId }
			</a>
			</td>
			<td>${ members.nickName }</td>
			<td>${ members.password }</td>
			<td>${ members.email}</td>
			<td>${ members.isAdmin}</td>
	</tr>
	</c:forEach>
	</form>	
	</table>
	<br/>
	
	<a href="<c:url value="/write"/>"><img src="<c:url value="/resource/img/write.png"/>" id="writeBtn" /></a>
	<img src="<c:url value="/resource/img/trashcan.png"/>" id="deleteBtn" width=125px height=125px; />
</div>
</body>