<%@ include file="../common/include.jsp"%>
<%@ page session="false"%>
<c:set var="context" scope="request"
	value="<%=request.getContextPath()%>" />
<html>
<head>

<link rel="stylesheet" type="text/css"
	href="${context}/resources/css/retailBanking.css" />

</head>
<body>
	<%@ include file="./homeNavbar.jsp"%>
	<center>
		<h1>Welcome to Electonic Funds Transfer</h1>
		<img src="${context}/resources/images/Banking.jpg" width="400" height="400"> <br> <br> <br>
	</center>
	</div>
</body>
</html>
