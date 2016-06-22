
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Servlet.Connector"%>
<%@ page import="Devices.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.ArrayList;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Smart House Alarm Control System</title>
<link rel="stylesheet" type="text/css" href="css/page.css" />
<link rel="stylesheet" type="text/css" href="buttons/buttons.css" />
<meta name="description"
	content="BigVideo.js - The jQuery Plugin for Big Background Video" />
<meta name="author" content="GBin1.com" />
<meta name="viewport" content="width=device-width">
<meta name="article" id="articlelink"
	content="/technology/jquerynews/20120831-jquery-plugin-bigvideojs/" />
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/bigvideo.css">
</head>
<body>
	<% 
Connector con = new Connector();
String name =  request.getParameter("buttonName");
session.setAttribute("name", name);	
session.setAttribute("DevicesType", "Alarm.jsp");
ArrayList<String> currentStatus=con.getgetCurrentStatus();
System.out.println("current status"+currentStatus.size());
%>
<div id="top"></div>
	<div class="main">		
		<center>
			<form action="actionServlet" method="POST">
				
				<%
					for (int i = 0; i < currentStatus.size(); i++) {
						if (currentStatus.get(i).contains(name)) {
							if (currentStatus.get(i).contains("ON")) {
								%>
					<img src="images/ala1.png" alt="not availible" width="150"height="150" id="image" />	</br>		
								
								<% 
							} else if (currentStatus.get(i).contains("OFF")) {
								%>
								
				<img src="images/ala2.png" alt="not availible" width="150"height="150" id="image" />	</br>				
								<%
							}
						}
					}
				currentStatus.clear();
				%>
 				<INPUT TYPE="HIDDEN" VALUE="<%=name%>"NAME="<%=name%>">
 				<INPUT TYPE="HIDDEN" VALUE="Alarm"NAME="Alarm">
				<button class="button big gray" id="turnON" name="ON" value="ON">TurnON</button>
				<button class="button big gray" id="turnOFF" name="OFF" value="OFF">Turn OFF</button>
				<button class="button big gray" id="back" name="back" value="back">Turn Back</button>
			</form>
		</center>
	</div>
	
			<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.0/jquery.min.js"></script>
		

	

	<!-- BigVideo Dependencies -->
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
	<script>window.jQuery || document.write('<script src="js/jquery-1.7.2.min.js"><\/script>')</script>
	<script src="js/jquery-ui-1.8.22.custom.min.js"></script>
	<script src="js/jquery.imagesloaded.min.js"></script>
	<script src="js/video.js"></script>

	<!-- BigVideo -->
	<script src="js/bigvideo.js"></script>

	<!-- Demo -->
	<script>
	    $(function() {
            var BV = new $.BigVideo();
			BV.init();
			BV.show('images/re-alarm.mp4',{ambient:true});
	    });
    </script>
</body>
</html>