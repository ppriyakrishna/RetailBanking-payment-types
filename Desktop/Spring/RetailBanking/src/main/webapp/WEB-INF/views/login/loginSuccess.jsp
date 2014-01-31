<%@ include file="../common/include.jsp"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Success</title>
</head>
<body>


	

		<h1>
			<fmt:message key="login.success" />
		</h1>



	<form:form name="myform" action="./account" method="GET">
		<table>
			<tr>
				<td>User Name:</td>
				<td>${user.userName}</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td>******</td>
			</tr>

			<tr>
				
				<td><input type="submit" name="accounts" value="Accounts" /></td>
				
				<td><input type="submit" name="home" value="Home" /></td>
			</tr>
		</table>

	</form:form>


</body>
</html>