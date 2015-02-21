<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
        <script src="ckeditor/ckeditor.js"></script>
        
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
    <script type="text/javascript">
       		function genderChange(value){
        		var choiceValue;
        		choiceValue = value;
        		if(value == 1)
        			alert("Future implementation");
        		else
        			alert("Individual Report");
        		document.getElementById("radValue").value= choiceValue;

        	}
    </script>
    <body>
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
                    <li><a href="Index.jsp" data-toggle="tab">TimeSheet</a></li>
                    <li><a href="TimeBank.jsp" data-toggle="tab">TimeBank</a></li>
                    <li class="active"><a href="#Report" data-toggle="tab">Report</a></li>
                    <li><a href="<s:url action="taskpageLink"/>" data-toggle="tab">PrivateTaskList</a></li>
                    <li><a href="TimeStamp.jsp" data-toggle="tab">TimeStamp</a></li>
        </ul>
        <!--Space-->
        <div class="container-fluid">
            <div class="well">
            <h2>Generate report</h2>
            <s:form id="reportGeneral" theme="simple" action="generateReport" method="post">
            <div class="span3">
            	<s:radio name="choice" id= "regularReport" list="#{'1':'General Report','2':'Individual Report'}" onChange="genderChange(this.value)"/>
                <s:hidden id="radValue" value=""/>
                <!-- <label><input type="radio" name="optionsRadios1" id="optionsRadios1" value="option1">Daily</label> -->
            </div>
            <!-- <div class="span3">
                 <label><input type="radio" name="optionsRadios2" id="optionsRadios2" value="option2">Weekly</label>
            </div>
            <div class="span3">
                 <label><input type="radio" name="optionsRadios3" id="optionsRadios3" value="option3">Monthly</label>
            </div>
            <div class="span3">
                 <label><input type="radio" name="optionsRadios4" id="optionsRadios4" value="option4">Yearly</label>
            </div>-->
            
            <div class="row-fluid">
                <div class="span3">
                    <!-- calendar -->
                    <h3 class="panel-title">Start Date</h3>
                    <div class="input-append" id="datetimepicker4">
                    	<sj:datepicker id="date" name="startdate" label="Date" changeYear="true" showOn="focus" format="#yyyy#MM#dd"/>
                        
                    </div>
                    
                </div>
                <div class="span3">
                    <!-- calendar -->
                    <h3 class="panel-title">End Date</h3>
                    <div class="input-append" id="datetimepicker3">
                    	<sj:datepicker id="date2" name="enddate" label="Date2" changeYear="true" showOn="focus" format="#yyyy#MM#dd"/>
                	</div>
                </div>
                <div class="span3" align="center">
                	<br>
                    <s:submit value ="Generate" cssClass="btn btn-primary" />
                </div>
                </div>  
                </s:form>  
            </div>
        </div>
        <div class="container-fluid">
            <div class="well">
            <h2>Weekly report</h2>
            <div class="row-fluid">
            	<s:form id="weeklyReport" theme="simple" action="weekReport" method="post">
                <div class="span3">
                    <!-- calendar -->
                    <h3 class="panel-title">Start Date</h3>
                    <div class="input-append" id="datetimepicker4">
                        <sj:datepicker id="date3" name="startdate" label="Date3" changeYear="true" showOn="focus" format="#yyyy#MM#dd"/>
                    </div>
                </div>
                <div class="span3">
                    <!-- calendar -->
                    <h3 class="panel-title">End Date</h3>
                    <sj:datepicker id="date4" name="enddate" label="Date4" changeYear="true" showOn="focus" format="#yyyy#MM#dd"/>
                </div>
                <div class="span3">
                	<h3 class="panel-title">Additional Information</h3>
                	<s:textarea cssClass="form-control" rows="3" id="nextWeek" name="nextWeek" value=""></s:textarea>
                	<script>
                		// Replace the <textarea id="editor1"> with a CKEditor
                		// instance, using default configuration.
                		CKEDITOR.replace( 'nextWeek' );
           			</script>
                </div>
                <div class="span3" align="center">
                	<s:submit cssClass="btn btn-primary" value="Generate" />
                </div>
                </s:form>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="js/draganddrop.js"></script>
        <script src="js/bootstrap-dropdown.js"></script>
		<script src="js/bootstrap-collapse.js"></script> 
    </body>
    
</html>
