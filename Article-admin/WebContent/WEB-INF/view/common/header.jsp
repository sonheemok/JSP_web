<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value="/resource/css/header.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resource/css/login.css"/>"/>
<script type="text/javascript" src="<c:url value="/resource/js/jquery-1.12.1.js"/>"></script>


<title>관리자페이지</title>
</head>
<body>

	<div id="wrapper">
	
		<div id="header">
		
		
			<a href="<c:url value="/list"/>"><img src="<c:url value= "/resource/img/icon.png"/>"></a> <div>안녕하세요.</div>
		
		</div>
		<div class="clear"></div>
		<jsp:include page="/WEB-INF/view/common/topMenu.jsp"></jsp:include>
		<div class="clear"></div>