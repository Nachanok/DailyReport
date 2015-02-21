<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

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
                    <li class="active"><a href="#TimeBank" data-toggle="tab">TimeBank</a></li>
                    <li><a href="Report.jsp" data-toggle="tab">Report</a></li>
                    <li><a href="<s:url action="taskpageLink"/>" data-toggle="tab">PrivateTaskList</a></li>
                    <li><a href="TimeStamp.jsp" data-toggle="tab">TimeStamp</a></li>
        </ul>
        <!--Space-->
        <div class="container-fluid">
            <!--Summary zone-->
            <div class="well">
            <h2>Summary</h2>
                <div class="row-fluid">
                    <div class="span4">
                        <p>Total get :</p><span class="label label-default">0.0</span>
                    </div>
                    <div class="span4">
                        <p>Total use :</p><span class="label label-default">0.0</span>
                    </div>
                    <div class="span4">
                        <p>Remaining :</p><span class="label label-default">0.0</span>
                    </div>
                </div>
            </div>
            <!--Timebank Zone-->
            <div class="well">
            <h2>TimeBankHistory</h2>
            <div class="row-fluid">
            <s:form theme="simple">
                <div class="span4">
                    <!-- calendar -->
                    <h3 class="panel-title">Start Date</h3>
                    <div class="input-append" id="datetimepicker4">
                        <sj:datepicker id="startdate" name="startdate" changeYear="true" showOn="focus" format="#yyyy#MM#dd"/><br>
                        
                    </div>
                </div>
                <div class="span4">
                    <!-- calendar -->
                    <h3 class="panel-title">End Date</h3>
                    <div class="input-append" id="datetimepicker4">
                        <sj:datepicker id="enddate" name="enddate" changeYear="true" showOn="focus" format="#yyyy#MM#dd"/>
                    </div>
                </div>
                <div class="span4">
                    <button type="button" class="btn btn-primary">Reload</button>
                </div>
            </s:form>
            </div>
            <!--Summary table-->
                <div class="page-header">
                       <h3 id="tables">ShowTime</h3>
                </div>
                <table class="table table-striped table-bordered table-hover">
                    <thead>
                      <tr>
                        <th>Date</th>
                        <th>Task</th>
                        <th>Time</th>
                        <th>Description</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                      </tr>
                     </tbody>
                </table>
        </div>
        <script type="text/javascript" src="js/draganddrop.js"></script>
        <script src="js/bootstrap-dropdown.js"></script>
		<script src="js/bootstrap-collapse.js"></script> 
    </body>
    
</html>
