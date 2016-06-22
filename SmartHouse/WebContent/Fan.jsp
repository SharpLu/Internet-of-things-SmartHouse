
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Servlet.Connector"%>
<%@ page import="Devices.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.ArrayList;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Smart house Fan Control System</title>
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
		String name =  request.getParameter("buttonName");
		session.setAttribute("name", name);	
		session.setAttribute("DevicesType", "Fan.jsp");			
		ArrayList<String> currentList=(ArrayList)session.getAttribute("currentStatus");;
	%>

	<div class="main">
		<center>
			<form action="actionServlet" method="POST">

				<%
					for (int i = 0; i < currentList.size(); i++) {
										if (currentList.get(i).contains(name)) {
											if (currentList.get(i).contains("OFF")) {
				%>
				<img src="images/fan_static.png" alt="not availible" width="160"
					height="160" id="image" /><br></br>

				<%
					} else if (currentList.get(i).contains("FAST")) {
				%>

				<img src="images/fanFast.gif" alt="not availible" width="160"
					height="160" id="image" /><br></br>
				<%
					}else if (currentList.get(i).contains("SLOW")) {
				%>

				<img src="images/fanSlow.gif" alt="not availible" width="160"
					height="160" id="image" /><br></br>
				<%
					}
										}
									}
					currentList.clear();
				%>
				<INPUT TYPE="HIDDEN" VALUE="<%=name%>" NAME="<%=name%>"> <select
					type="select" name="Fan_Item" id="type">
					<option value="FAST">FAST</option>
					<option value="SLOW">SLOW</option>
					<option value="OFF">OFF</option>
				</select> 
				<input type="submit" class="button big gray" />
				<button class="button big gray" id="back" name="back" value="back">Turn
					Back</button>
			</form>
		</center>
	</div>

	<!-- BigVideo Dependencies -->
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"
		type="text/javascript"></script>
	<script type="text/javascript">
		window.jQuery
				|| document
						.write('<script src="js/jquery-1.7.2.min.js"><\/script>')
	</script>
	<script src="js/jquery-ui-1.8.22.custom.min.js" type="text/javascript"></script>
	<script src="js/jquery.imagesloaded.min.js" type="text/javascript"></script>
	<script src="js/video.js" type="text/javascript"></script>

	<!-- BigVideo -->
	<script src="js/bigvideo.js" type="text/javascript"></script>

	<!-- Demo -->
	<script type="text/javascript">
		$(function() {
			var BV = new $.BigVideo();
			BV.init();
			BV.show('images/fan.mp4', {
				ambient : true
			});
		});
	</script>
</body>
</html>