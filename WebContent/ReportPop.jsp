<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table border="2">
	<tr>
	<s:iterator status="out" value="dates">
		<td>
			<s:property />
		</td>
	</s:iterator>
	</tr>
	<tr>
	<s:iterator status="out" value="hours">
		<td>
			<s:property />
		</td>
	</s:iterator>
	</tr>
</table>
</body>
</html>