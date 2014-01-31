<%@ include file="../common/include.jsp"%>
<html>
<head>

</head>
<body>
	<h1>
		<fmt:message key="userDataForm.success" />
	</h1>

<form:form name="myform" action="./loginAccount" method="POST"
		commandName="user">
	<table>
		<tr>
			<td>LasName:</td>
			<td>${user.lastName}</td>
		</tr>
		<tr>
			<td>FirstName:</td>
			<td>${user.firstName}</td>
		</tr>
		<tr>
			<td>Age:</td>
			<td> ${user.age}</td>
			
			
		</tr>
			<tr>
				<td>Gender:</td>
				
				<td>${user.gender}</td>
			</tr>
			<tr>
				<td>User Name:</td>
				<td>${user.userName}</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td >******</td>
			</tr>


			<tr>
			
				<td><a href="${context}/loginAccount">Login</a></td>
				
			</tr>
		</table>
	
</form:form>
</body>
</html>
