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
<h1 align="center">
	Timesheet Individual Report
</h1>
<table width="50%">
	<tr>
		<td><b>Username:</b> <s:property value="account.getUsername()"/></td>
		<td><b>User ID: </b></td>
	</tr>
	<tr>
		<td><b>Date: </b> <s:property value="startdate"/></td>
		<td><b>To</b> <s:property value="enddate" /></td>
	</tr>
</table>
<hr>
<s:iterator value="listsForIndividual" status="outer">
	<b><s:property value="dates[#outer.index]"/></b>
	<table width="75%">
		<thead>
			<tr>
				<th align="left">Tasks</th>
				<th>Usage Time</th>
				<th>Description</th>
			</tr>
		</thead>
		<tbody>
			<s:set var="total" value="0" />
			<s:property value=""/>
			<s:iterator value="top" status="in">
				<tr>
					<td width="45%"><s:property value="getId().getProjectCode()" />.<s:property value="getId().getVersionCode()" />.<s:property value="getId().getTaskGroupCode()"/>.<s:property value="getId().getSubtaskCode()" /></td>
					<td align="right" width="10%"><s:property value="usageTime"/><s:set var="total" value="%{#total+usageTime}" /></td>
					<td align="right" width="45%"><s:property value="descriptions"/></td>
				</tr>
			</s:iterator>
			<tr>
				<td>Total</td>
				<td align="right"><s:property value="%{#total}"/></td>
				<td></td>
			</tr>
		</tbody>
	</table>
<hr>
</s:iterator>

<table>
</table>

</body>
</html>