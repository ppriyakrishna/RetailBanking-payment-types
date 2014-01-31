<%@ include file="../common/include.jsp"%>
<html>
<head>
<title>Funds Transfer</title>
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


	<c:if test="${message!=null}">
		<h1>
			<c:out value="${message}" />
		</h1>
	</c:if>
	<c:if test="${message==null}">

		<h1>
			<fmt:message key="fundsTransferForm.title" />
		</h1>
	</c:if>


	<form:form name="myform" action="./processFundsTransfer" method="POST"
		commandName="fundsTransfer">
		<form:errors path="*" cssClass="error" />
		<table>
			<tr>
				<td><h3>
						<font color="blue">Originator:</font>
					</h3></td>
			</tr>
			<form:form modelAttribute="fundsTransfer.fromAccount">
				<tr>
					<td><form:label path="account_Id" />Account No:</td>
					<td><form:select path="account_Id">
							<form:options items="${accountIdList}" />
						</form:select></td>

					<td><form:label path="account_Name" />Account Name:</td>
					<td><form:input path="account_Name" disabled="true" /></td>

				</tr>
				<tr>
					<td><form:label path="branch_Name" />Branch Name:</td>
					<td><form:input path="branch_Name" disabled="true" /></td>

					<td><form:label path="currency">Currency:</form:label></td>
					<td><form:input path="currency" disabled="true" /></td>

				</tr>
			</form:form>

			<tr>
				<td><form:label path="paymentType.type_Code" />Payment Type:</td>
				<td><form:select path="paymentType.type_Code">
						<form:option value=" " label="--- Select ---" />
						<form:options items="${paymentTypeList}" />
					</form:select></td>

				<td><form:label path="paymentType.description" />Payment Type
					Desc:</td>
				<td><form:input path="paymentType.description" size="50"
						disabled="true" /></td>
			</tr>
			<tr>
				<td><form:label path="transferAmt">Amount</form:label></td>
				<td><form:input path="transferAmt" /></td>
			</tr>


			<tr>
				<td><form:label path="fromBank.bank_Name" />Bank Name:</td>
				<td><form:input path="fromBank.bank_Name" disabled="true" /></td>

				<td><form:label path="fromBank.country_Name" />Country Name:</td>
				<td><form:input path="fromBank.country_Name" disabled="true" /></td>
			</tr>


			<tr>
				<td><h3>
						<font color="blue">Beneficary Details:</font>
					</h3></td>
			</tr>

			<tr>
				<td><h3>
						<font color="blue">Beneficary Account:</font>
					</h3></td>
			</tr>

			<tr>
				<td><form:label path="toAccount.account_Id" />Account No:</td>
				<td><form:input path="toAccount.account_Id" /></td>

				<td><form:label path="toAccount.account_Name" />Account Name</td>
				<td><form:input path="toAccount.account_Name" /></td>
			</tr>
			<tr>
				<td><form:label path="toAccount.branch_Name" />Branch Name:</td>
				<td><form:input path="toAccount.branch_Name" /></td>

			</tr>


			<tr>
				<td><h3>
						<font color="blue">Beneficary Bank:</font>
					</h3></td>
			</tr>

			<tr>
				<td><form:label path="toBank.bank_Name" />Bank Name:</td>
				<td><form:select path="toBank.bank_Name">
						<form:option value=" " label="--- Select ---" />
						<form:options items="${bankNameList}" />
					</form:select></td>

				<td><form:label path="toBank.country_Name" />Country Name</td>
				<td><form:select path="toBank.country_Name">
						<form:option value=" " label="--- Select ---" />
						<form:options items="${countryNameList}" />
					</form:select></td>
			</tr>
			<tr>
				<td><form:label path="toBank.currency">Currency:</form:label></td>
				<td><form:select path="toBank.currency">
						<form:option value=" " label="--- Select ---" />
						<form:options items="${currencyList}" />
					</form:select></td>


			</tr>
			<tr />
			<tr />


			<tr>
				<c:if test="${message==null}">
					<tr>
						<td><input id="submit" type="submit"
							value="<fmt:message key="submit" />"></td>

					</tr>

				</c:if>
				<c:if test="${message!=null}">

					<tr>

						<td><a href="${context}/viewAccountDetails">View Account
								Details</a></td>
						<td />
						<td><a href="${context}/fundsTransfer">Create New
								Transfer</a></td>


					</tr>

				</c:if>
		</table>

	</form:form>

	<a href="${context}/account?accounts">Accounts Page</a> &nbsp &nbsp
	<a href="${context}/home">Home</a>
	
</body>
</html>
