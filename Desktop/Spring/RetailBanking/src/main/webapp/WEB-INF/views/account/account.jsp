<%@ include file="../common/include.jsp"%>
<%@ page session="false"%>
<c:set var="context" scope="request"
	value="<%=request.getContextPath()%>" />
<html>
<head>
<SCRIPT type="text/javascript">
    window.history.forward();
    function noBack() { window.history.forward(); }
</SCRIPT>
<link rel="stylesheet" type="text/css"
	href="${context}/resources/css/retailBanking.css" />
</head>
<body onload="noBack();"
    onpageshow="if (event.persisted) noBack();" onunload="">
	<%@ include file="./accountNavbar.jsp"%>
	<center>
		<h1>Accounts</h1>
		<img src="${context}/resources/images/account.jpg" width="400" height="400"> <br> <br> <br>	
	</center>
	</div>
</body>
</html>
