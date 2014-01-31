<%@ include file="../common/include.jsp"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Login</title>
</head>
<body>



	<div align="center" id="login-error">
		<font color="red"> ${error} </font>
	</div>
	
	<c:if test="${message!=null}">
		<h1><c:out value="${message}" /></h1>
	</c:if>
	<c:if test="${message==null}">
	
	<h1>
		<fmt:message key="login.title" />
	</h1>
	</c:if>

	 
	
	<form:form name="myform" action="./loginSuccess" method="POST"
		commandName="user">
	<table>
		<tr>
				<td><form:label path="userName">User Name</form:label></td>
				<td><form:input path="userName"  autocomplete="off"/> </td>
			</tr>
			<tr>
				<td><form:label path="password">Password</form:label></td>
				<td><form:password path="password" showPassword="true" autocomplete="off"/> </td>
			</tr>
			
			<c:if test="${message!=null}">

				<tr>
					<td />
					<td><input type="submit" value="RE-Enter" /></td>
				</tr>          
			</c:if>
			
			<c:if test="${message==null}">

				<tr>
					<td />
					<td><input type="submit" value="Submit" /></td>
				</tr>          
			</c:if>
		  </table>
		
		 </form:form> 
		
	
	</body>
</html>