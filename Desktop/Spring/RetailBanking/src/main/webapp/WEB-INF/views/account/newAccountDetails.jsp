<%@ include file="../common/include.jsp"%>
<html>
<head>
<title>New Account Information</title>
<style>
.error {
	font-size: 0.8em;
	color: #ff0000;
}
</style>
<SCRIPT type="text/javascript">
    window.history.forward();
    function noBack() { window.history.forward(); }
</SCRIPT>

</head>
<body onload="noBack();"
    onpageshow="if (event.persisted) noBack();" onunload="">




	<h1>
		<fmt:message key="accountForm.title" />
	</h1>

	<form:form name="myform" action="./processNewAccountProfile"
		method="POST" commandName="account">
		<table>

			<tr>
				<td><form:label path="account_Name" />Account Name</td>
				<td><form:input path="account_Name" autocomplete="off" /> <form:errors
						path="account_Name" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="branch_Name" />Branch Name</td>
				<td><form:select path="branch_Name">
						<form:option value=" " label="--- Select ---" />
						<form:options items="${branchNameList}" />
					</form:select> <form:errors path="branch_Name" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="amount">Amount</form:label></td>
				<td><form:input path="amount" /> <form:errors path="amount"
						cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="currency">Currency</form:label></td>
				<td><form:select path="currency">
						<form:option value=" " label="--- Select ---" />
						<form:options items="${currencyList}" />
					</form:select> <form:errors path="currency" cssClass="error" /></td>
			</tr>


			<tr>
				<td><input id="submit" type="submit"
					value="<fmt:message key="submit" />"></td>

			</tr>
		</table>
	</form:form>

	<a href="${context}/account?accounts">Accounts Page</a> &nbsp &nbsp
	<a href="${context}/home">Home</a>

</body>
</html>
