<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	<center>
		<h2>Please login</h2>
		<s:form action="AuthenticateAction" method="post">
			<s:textfield name="username" size="10" label="Enter Username" />
			<s:password name="password" size="10" label="Enter Password" />
			<s:submit value="Login" />
		</s:form>
	</center>
</body>
</html>