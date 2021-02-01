<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//세션으로 지정된 id값을 가져온다.
		String id = (String)session.getAttribute("id");
		
		if(id == null){
	%>
			<script>
				location.href='loginForm.jsp';			
			</script>	
				
	<%		
		}else{
	%>
			<a href="logout">로그아웃</a>
	<%
		}
	%>		
</body>
</html>


