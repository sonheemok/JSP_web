<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/Movie/resource/js/jquery-1.12.1.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		$("#addNewActor").click(function(){
				
			var form = $("#addNewActorForm");
			form.attr("method", "POST");
			form.attr("action", "/Movie/addNewActorAction");
			form.submit();
		});
	});

</script>
</head>

<body>

	<form id="addNewActorForm">
	
		${ ErrorMessage }
		<input type="text" id = "actorName" name="actorName" />
		<input type="button" id = "addNewActor" value="등록하기" />
	</form>

</body>
</html>