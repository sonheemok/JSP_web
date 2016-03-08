<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/resource/js/jquery-1.12.1.js"></script>
<script type="text/javascript">


	$(document).ready( function() {
		
		// 목록으로 가기
		$("#list").click( function() {
			location.href = "/list";
		} );
		
		$("#recommend").click( function() {
			
			location.href="/recommend?articleId=${article.articleId }";
		} );
		
		
	} );



</script>
</head>
</head>

<body>
<table border="1">
	<tr>
		<th>ARTICLE_ID</th>
		<th>TITLE</th>
		<th>MEMBER_ID</th>
		<th>NICK_NAME</th>
		<th>DESCRIPT</th>
		<th>HITS</th>
		<th>RECOMMENDS</th>
	</tr>	
	<tr>
		<td>${ article.articleId }</td>
		<td>${ article.title }</td>
		<td>${ article.memberId }</td>
		<td>${ article.nickName }</td>
		<td>${ article.descript }</td>
		<td>${ article.hits }</td>
		<td>${ article.recommends }</td>
	</tr>
</table>

<input type = "button" id="list" value= "목록" />
<input type = "button" id="recommend" value= "추천하기" />
</body>

</html>