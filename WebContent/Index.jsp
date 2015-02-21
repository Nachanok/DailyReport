<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<a href="about.html"></a>
<html>
    <head>
    	
    	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src="js/loadWelcome.js"></script>
        <script type="text/javascript">window.onload = getWelcome;</script>
		<script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/libs/jqueryui-1.8.16/jquery-ui.js"></script>
        <script type="text/javascript" src="js/about.js"></script>
        <script type="text/javascript" src="js/libs/jtable2.4.0/jquery.jtable.jsabout.js"></script>
        
        <!-- Le styles -->
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" media="screen">
        <link rel="stylesheet" href="css/bootstrap.css" type="text/css"/>
        <link rel="stylesheet" href="css/bootstrap-responsive.css" type="text/css"/>
        <link rel="stylesheet" href="css/style.css" type="text/css"/>
        <style type="text/css">
            body {
                padding-top: 60px;
                padding-bottom: 40px;
            }
        </style>

        <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

        <title>Project TimeSheet Demo</title>
        <sj:head />
        <sx:head />
    </head>
    <!--Javascript part-->
    <SCRIPT LANGUAGE="JavaScript">
		function openChild(file,window) {
    		childWindow=open(file,window,'resizable=no,width=700,height=400');
    		if (childWindow.opener == null) childWindow.opener = self;
    	}
		
		function init()
		{
			if(sessionStorage.getItem('selectDate') != null)
			{
				document.getElementById('date').setAttribute('value', sessionStorage.getItem('selectDate'));
				document.getElementById('taskcode').setAttribute('value', sessionStorage.getItem('task'));
				document.getElementById('amount').setAttribute('value', sessionStorage.getItem('amount'));
				document.getElementById('description').innerHTML = sessionStorage.getItem('description');
			}
		}
	</SCRIPT>
	<!-- End Javascript -->
    <body onload="init()">
        <div class="navbar navbar-fixed-top">
             <div class="navbar-inner">
                <div class="container-fluid">
                    
                    <a class="brand" href="#">Nomagic : TimeSheet and weekly report online</a>
                    <div class="btn-group pull-right">
                        <h4>Welcome,</h4> 
                        <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="icon-user"></i> <s:property value="#session.account.getUsername()"/>
                            <span class="caret"></span>
                        </a>
                    
                        <ul class="dropdown-menu">
                            <li><a href="#">Profile</a></li>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
                            <li class="divider"></li>
                            <li><a href="<s:url action="logOut"/>">Sign Out</a></li>
                        </ul>
                        
                    </div>
                </div>
            </div>
        </div>
        <!-- TabMenu -->
        <ul class="nav nav-tabs" style="margin-bottom: 15px;">
                    <li class="active"><a href="#home" data-toggle="tab">TimeSheet</a></li>
                    <li><a href="TimeBank.jsp" data-toggle="tab">TimeBank</a></li>
                    <li><a href="Report.jsp" data-toggle="tab">Report</a></li>
                    <li><a href="<s:url action="taskpageLink"/>" data-toggle="tab">PrivateTaskList</a></li>
                    <li><a href="TimeStamp.jsp" data-toggle="tab">TimeStamp</a></li>
        </ul>
        <!--Space-->
        <div class="container-fluid">
        <!--TimeSheet table-->
         <div class="col-lg-12">
            <div class="page-header">
              <h1 id="tables">TimeSheet</h1>
            </div>
            <div class="bs-example">
            <div id="timesheet">
            <s:set var="all" value="0"/>
              <table class="table table-striped table-bordered table-hover">
                <thead>
                  <tr>
                    <th>#</th>
                    <th>Task</th>
                    <s:iterator value="weekdate">
                    <th><s:property/></th>
                    </s:iterator>
                    <th>Total</th>
                  </tr>
                </thead>
                <tbody>
                  	<s:iterator status="week" value="listsbydate">
                  		<tr>
                  		<s:set name="id" value="%{#week.index}" />
                  			<td>
                  			<s:property value="#id + 1"/>
                  			</td>
                  			<s:set var="total" value="0" />
                  			<s:iterator status="inner" value="top">
                  				<s:if test="#inner.index == 0"><td><s:property value="getId().regularFormat()"/></td></s:if>                  				
                  				<td><s:property value="usageTime" /><s:set var="total" value="%{#total+usageTime}" /></td>
                  			</s:iterator>
                  			<td>
                  				<s:property value="%{#total}"/><s:set var="all" value="%{#all+#total}"/>
                  			</td>
                  	</s:iterator>
                </tbody>
              </table>
              </div>
            <!--Summary table-->
            <table class="table table-striped table-bordered table-hover">
                <thead>
                  <tr>
                    <th>Task</th>
                    <th>Sun</th>
                    <th>Mon</th>
                    <th>Tue</th>
                    <th>Wed</th>
                    <th>Thu</th>
                    <th>Fri</th>
                    <th>Sat</th>
                    <th>Total</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>Summary</td>
                    <s:iterator status="down" value="totalday"><td><s:property/></td></s:iterator>
                    <td><s:property value="%{#all}"/></td>
                  </tr>
                  </tbody>
            </table>
            </div>
            </div>
            <!-- /example -->
            <!-- calendar -->
            
            <s:form id="timesheetform" theme="simple" action="AddTimeSheet" method="post">
            <div class="row-fluid">
            <div class="span4">
            <div class="input-append" id="datetimepicker">
            <h3 class="panel-title">Date</h3>
            <sj:datepicker id="date" name="date" label="Date" changeYear="true" showOn="focus" format="#yyyy#MM#dd"/><s:submit value="Show" action="Showtimesheet" cssClass="btn btn-primary"/><br>
            <input type="button" value="Select Task" onClick="openChild('SelectTask.jsp','win2')" class="btn btn-primary">
            <s:textfield cssClass="form-control" id="taskcode" name="taskcode" />
            </div>
            </div>
            <div class="span4">
            	<div class="panel-heading">
                    <h3 class="panel-title">Amount</h3>
                </div>
                <s:textfield cssClass="form-control" id="amount" name="amount" label="amount" />
            </div>
			<div class="span4">
                 <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Description</h3>
                    </div>
                    	<s:textarea cssClass="form-control" rows="3" id="description" name="description" value=""></s:textarea>
                        <span class="help-block">Fill your description here.</span>
                    
                 </div>
             </div>
			
			</div>
			<s:submit value="Save" action="Addtimesheet" cssClass="btn btn-primary" />
            </s:form>
          </div>  
      
         <script type="text/javascript" src="js/draganddrop.js"></script>
        <script src="js/bootstrap-dropdown.js"></script>
		<script src="js/bootstrap-collapse.js"></script> 
    </body>
    
</html>
