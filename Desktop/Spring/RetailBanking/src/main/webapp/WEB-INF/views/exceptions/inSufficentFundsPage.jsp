<%@ include file="../common/include.jsp"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>insufficentFundsPage</title>
</head>
<body>
	<h1>Insufficent Funds for Originator ${fromAccount.account_Name}  <br>
	holding Account No:${fromAccount.account_Id} </h1>
	
	<a href="${context}/fundsTransfer">Edit Fund Transfer</a>&nbsp;
	<a href="${context}/account?accounts">Accounts Page</a>&nbsp;
	<a href="${context}/home">Home Page </a>&nbsp;
</body>
</html>