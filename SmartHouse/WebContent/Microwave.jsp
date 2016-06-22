
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
<title>Microwave Control</title>
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
<script type="text/javascript" src="clock/action_clock.js" ></script>
<script type="text/javascript" src="clock/clock.js" ></script>
<script type="text/javascript">
yui.Event.addListener(window, "load",showMeThisCoolDHTMLWidgetStuffOK);
function showMeThisCoolDHTMLWidgetStuffOK()
{
	var wowClock1 = new ygClock(100,100,"clock/clock.png","clock/clock_mask.png","clock/hourhand.png","clock/minhand.png","clock/sechand.png",-7,-7);
};
</script>
</head>
<body>
	<% 
String name =  request.getParameter("buttonName");
session.setAttribute("name", name);	
session.setAttribute("DevicesType", "Microwave.jsp");
ArrayList<String> currentList=(ArrayList)session.getAttribute("currentStatus");; 
%>

	<div class="main">
		<center>
			<form action="actionServlet" method="POST" name="actionServlet">

				<%
					for (int i = 0; i < currentList.size(); i++) {
						if (currentList.get(i).contains(name)) {
							
							if (currentList.get(i).contains("ON")) {
								%>
					<img src="images/mi1.png" alt="not availible" width="220"height="150" id="image" /></br>							
								<% 
								//out.println("Current Status is: ON");
							} else if (currentList.get(i).contains("OFF")) {
								
								%>
								
				<img  src="images/mi2.png" alt="not availible" width="220"height="150" id="image" /></br>	
					
								<%
							}		
							
						}
					}
					currentList.clear();
				%>
				<INPUT TYPE="HIDDEN" VALUE="<%=name%>" NAME="<%=name%>"> <br>input
				a number between 0 to 90: <input type="text"
					onkeyup="javascript:this.value=this.value.replace(/[^\d]/g,'');if(this.value<0){
this.value=0;};if(this.value>90){this.value=90;}"
					onchange="javascript:this.value=this.value.replace(/[^\d]/g,'');if(this.value<0){
this.value=0;};if(this.value>90){this.value=90;}"
					name="Microwave" /> <br>
				<button class="button big gray" id="turnOFF" name="ON" value="ON">Submit</button>
				<button class="button big gray" id="back" name="back" value="back">Turn
					Back</button>
			</form>
		</center>
	</div>

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
			BV.show('images/Microwave.mp4',{ambient:true});
	    });
    </script>
    
</body>
</html>