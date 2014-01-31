<%@ include file="../common/include.jsp"%>

<html>
<head>
<title>Account Details</title>
<SCRIPT type="text/javascript">
    window.history.forward();
    function noBack() { window.history.forward(); }
</SCRIPT>
</head>
<body onload="noBack();"
    onpageshow="if (event.persisted) noBack();" onunload="">



	<h1>
		<fmt:message key="accountForm.view" />
	</h1>


	<table table BORDER="8" bgcolor="#FAEBD7" cellspacing="10"
		cellpadding="15">

		<tr>

			<th>Account Name</th>
			<th>Branch Name</th>
			<th>Amount</th>
			<th>currency</th>		

		</tr>
		<c:forEach var="account" items="${accountList}">
			<tr>

				<td>${account.account_Name}</td>
				<td>${account.branch_Name}</td>
				<td>${account.amount}</td>
				<td>${account.currency}</td>
			</tr>
		</c:forEach>
	</table>

	<br>
	<br>
	
		<a href="${context}/account?accounts">Accounts Page</a> &nbsp &nbsp
		<a href="${context}/home">Home</a>

	
	



</body>
</html>
