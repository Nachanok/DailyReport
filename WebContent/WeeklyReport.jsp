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
<ul>
	<li><b>Weekly Report:</b>&nbsp;<s:property value="account.getFullname()"/>&nbsp;&lt;<s:property value="account.getEmail()"/>&gt;</li>
	<li><b>Role:</b>&nbsp;<s:property value="account.getRole()"/></li>
	<li><b>Location:</b>&nbsp; TH</li>
	<li><b>Date:</b><s:property value="enddate"/></li>
	<li>
		<b>This Week:</b>
		<ul>
			<s:iterator status="loop" value="reports">
				<li>	
					<s:property value="projectName"/>&nbsp;<s:property value="projectVersion"/>(<s:property value="totalHour()" />)
					<ul>			
					<s:iterator status="inner" value="task">
						<li>
							<s:property/>
							<ul>
								<s:iterator status="last" value="descriptionLists.get(#inner.index)">
									<li><s:property/></li>
								</s:iterator>
							</ul>
						</li>			
					</s:iterator>
					</ul>
				</li>
			</s:iterator>
		</ul>
	</li>
	<s:property value="nextWeek" escapeHtml="false"/>	
</ul>
</body>
</html>