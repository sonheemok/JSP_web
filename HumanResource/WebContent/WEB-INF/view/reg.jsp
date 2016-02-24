<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<table>
	<tr>
		<th>REGION_ID</th>
		<th>REGION_NAME</th>
	</tr>
<c:forEach items="${ allRegions }" var ="reg">
	<tr>
		<td>${ reg.regionId }</td>
		<td>${ reg.regionName }</td>
	</tr>
</c:forEach>
</table>

</body>
</html>