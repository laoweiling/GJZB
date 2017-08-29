<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>testshowAllUsers</title>
</head>
<body>
	<c:forEach items="${users}" var="item">
		<tr>
		    userId：<td>${item.userId}</td>
		    phone：<td>${item.phone}</td><br />
		    
		</tr>
	</c:forEach> 
	
</body>
</html>