<%@ include file="../common/include.jsp"%>
<html>
<head>
<title>New User Information</title>
<style>
.error {
	font-size: 0.8em;
	color: #ff0000;
}
</style>

</head>
<body>




	<h1>
		<fmt:message key="userDataForm.title" />
	</h1>

	<form:form name="myform" action="./processNewUserProfile" method="POST"
		commandName="user">
		<table>
			<tr>
				<td><fmt:message key="lastnameLabel" /></td>
				<td><form:input path="lastName" /> <form:errors
						path="lastName" cssClass="error" /></td>
			</tr>
			<tr>
				<td><fmt:message key="firstnameLabel" /></td>
				<td><form:input path="firstName" /> <form:errors
						path="firstName" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="age">Age</form:label></td>
				<td><form:input path="age" /> <form:errors path="age"
						cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="gender">Gender</form:label></td>
				</td>
				<td><form:radiobutton path="gender" value="Male" checked="true" />
					Male<br> <form:radiobutton path="gender" value="Female" />
					Female<br></td>
			</tr>
			<tr>
				<td><form:label path="userName">User Name</form:label></td>
				<td><form:input path="userName" autocomplete="off" /> <form:errors
						path="userName" cssClass="error" /></td>
			</tr>

			<tr>
				<td><form:label path="password">Password</form:label></td>
				<td><form:password path="password" autocomplete="off" />
					<form:errors path="password" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="confirmPassword">Confirm Password</form:label></td>
				<td><form:password path="confirmPassword" autocomplete="off" />
					<form:errors path="confirmPassword" cssClass="error" /></td>
			</tr>


			<tr>
				<td><input id="submit" type="submit"
					value="<fmt:message key="submit" />"></td>

			</tr>
		</table>
	</form:form>

</body>
</html>
