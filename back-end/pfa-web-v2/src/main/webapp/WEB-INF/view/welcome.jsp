<!DOCTYPE html>
<html>
<head>
<title>My app</title>
</head>
<body>

<h2>Welcome to my app</h2>

<form action="${pageContext.request.contextPath}/user/performLogout" method="POST">
	<input type="submit" value="Logout"/>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
</form> 

</body>

</html>