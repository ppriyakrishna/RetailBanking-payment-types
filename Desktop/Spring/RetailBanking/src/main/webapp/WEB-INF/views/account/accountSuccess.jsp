<%@ include file="../common/include.jsp"%>
<html>
<head>
<title>New Account Information</title>

</head>
<body>




	<h1>
		<fmt:message key="accountForm.success" />
	</h1>


	<table>

		<tr>
			<td>Account Name:</td>
			<td>${account.account_Name}</td>
		</tr>
		<tr>
			<td>Branch Name</td>
			<td>${account.branch_Name}</td>
		</tr>
		<tr>
			<td>Amount:</td>
			<td>${account.amount}</td>
		</tr>
		<tr>
			<td>currency:</td>
			<td>${account.currency}</td>
		</tr>



		<tr>
			<td><a href="${context}/processNewAccount">Create Another
					Account </a></td><td/> &nbsp; &nbsp;
			<td><a href="${context}/account?accounts">Accounts Page</a></td><td/> 
			 &nbsp; &nbsp;
			<td><a href="${context}/home">Home Page </a></td>
		</tr>
	</table>


</body>
</html>
