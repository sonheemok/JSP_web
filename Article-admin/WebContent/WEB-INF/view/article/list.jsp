<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/article/leftMenu.jsp"></jsp:include>

<script type="text/javascript">
	$(document).ready(function(){
		$("#initSearchBtn").click(function(){
			location.href = "<c:url value="/list/init"/>";
		});
		
		$("#searchBtn").click(function(){
			if( $("#searchKeyword").val()=="" ) {
				alert("검색어를 입력하세요!");
				return;
			}
			movePage('0');
		});
		
		$("#massiveSelectCheckBox").click(function(){
			 var isChecked = $(this).prop("checked");
			 $(".deleteArticleId").prop("checked",isChecked);			 
		});
		
		
		$("#massiveDeleteBtn").click(function(){
			var isChecked = false;

			$(".deleteArticleId").each(function(index,data){
				
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
				form.attr("action", "<c:url value="/massiveDelete"/>");
				form.submit();
			}
		});
	});
</script>

<body>
<div id="listcss" align="center">
	<br/>
	 총 ${articles.paging.totalArticleCount} 건의 게시물이 있습니다.
	<br/><br/>
	
	<table border="2" >
		<tr>
			<th><input type="checkbox" id="massiveSelectCheckBox"/></th>
			<th>ARTICLE_ID</th>
			<th>TITLE</th>
			<th>NICK_NAME</th>
			<th>HITS</th>
			<th>RECOMMENDS</th>
		</tr>	
	
	<form id="massiveDeleteForm">
	<c:forEach items="${ articles.articleList }" var="articles">
		<tr>
			<td>
				<input type="checkbox" class="deleteArticleId"
						name = "deleteArticleId"
						value ="${articles.articleId }"/>
			</td>
			<td>${ articles.articleId }</td>
			<td>
				<a href="<c:url value="/detail?articleId=${articles.articleId }"/>">
					${ articles.title }
				</a>
				<c:if test="${articles.fileCount gt 0 }">
					[${articles.fileCount}개의 첨부파일 있음]
				</c:if>
			</td>
			<td>${ articles.nickName }</td>
			<td>${ articles.hits }</td>
			<td>${ articles.recommends }</td>
	</tr>
	</c:forEach>
	</form>
	
	<tr>
		<td colspan="6" text-align="center">
			<form id="searchForm">
				<div style="text-align:center;">
				${articles.paging.getPagingList("pageNo","[@]","[이전]", "[다음]", "searchForm")}
				</div>
				<div style="text-align:right;">
					<select id="searchCategory" name="searchCategory">
						<option value="content">제목+내용</option>
						<option value="memberId">멤버ID</option>
						<option value="nickName">닉네임</option>
					</select>
					
					
					<input type="text" id="searchKeyword" name="searchKeyword" value="${searchVO.searchKeyword}"/>
					<input type="button" id="searchBtn" value="검색 "/>
					<input type="button" id="initSearchBtn" value="검색 초기화 "/>
				</div>
			</form>
			
		</td>
	</tr>
	</table>
	<br/>
	
	
	<a href="<c:url value="/write"/>"><img src="<c:url value="/resource/img/write.png"/>" id="btnWrite" /></a>
	<a href="<c:url value="/logout"/>"><img src="<c:url value="/resource/img/exit.png"/>" id="btnExit" /></a>
	<img src="<c:url value="/resource/img/trashcan.png"/>" id="massiveDeleteBtn" width=125px height=125px; />
	
	
	</br>
</div>
</body>


