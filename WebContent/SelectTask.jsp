<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select Task</title>
<!-- Javascript -->
<SCRIPT LANGUAGE="JavaScript">
function updateParent(code) {
    opener.document.timesheetform.taskcode.value = code;
    self.close();
    return false;
}
</SCRIPT>
<!-- End Javascript -->
</head>
<body>
<table>

<s:iterator value="#session.account.getPrivatetasklists()">
<s:set var="enable" value="enable" />
<s:if test="%{#enable==0}">
	<tr>
		<td>	
			<s:set var="id1" value="%{id.getTaskCode()}" scope="request" />		
			<s:property value="id.getTaskCode()"/>
		</td>
		<td>
				<input type="button" name="#code" value="Select" onclick="updateParent('<%=request.getAttribute("id1")%>')"/>
			
		</td>
	</tr>
</s:if>
</s:iterator>

</table>
</body>
</html>