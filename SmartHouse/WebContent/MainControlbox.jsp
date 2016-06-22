<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Servlet.Connector"%>
<%@ page import="Devices.*"%>
<%@ page import="java.util.*;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Welcome to the SmartHouse Menu Page</title>
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
 
	<div id="weather"></div>
	<div class="main" id="buttonContainer">
		<center>
			<% 
Connector con = new Connector();
con.devicename();
con.getAllDevicesType();
ArrayList<String> AllNameList = con.getAllNameList();
ArrayList<String> DevicesType=con.getDevicesTypelist();
ArrayList<String> currentStatus=con.getgetCurrentStatus();
session.setAttribute("currentStatus", currentStatus);
%>
			<% 
	
	for (int i=0;i<AllNameList.size();i++) {
		
		%>

			<a 
				onclick="javascript:location.href='<%=DevicesType.get(i)%>.jsp?buttonName=<%=AllNameList.get(i)%>'"
				class="button small gray"><%=AllNameList.get(i)%>  </a> 
			<%
	}
	AllNameList.clear();
	DevicesType.clear();
%>


			
			<a class="button small gray"
				onclick="javascript:location.href='end.jsp'">End</a>
		</center>
	</div>

	<div class="main2">
		
		<h1 id="logo2"></h1>
	</div>
	<!-- BigVideo Dependencies -->
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
	<script>window.jQuery || document.write('<script src="js/jquery-1.7.2.min.js"><\/script>')</script>
	<script src="js/jquery-ui-1.8.22.custom.min.js"></script>
	<script src="js/jquery.imagesloaded.min.js"></script>
	<script src="js/video.js"></script>

		 <script src="js/weather.js"></script>   
      <link rel="stylesheet" href="css/Copy of style.css">
	

	<!-- BigVideo -->
	<script src="js/bigvideo.js"></script>

	<!-- Demo -->
	<script>
	    $(function() {
            var BV = new $.BigVideo();
			BV.init();
			BV.show('images/menu.mp4',{ambient:true});
	    });
    </script>  
        <script type="text/javascript">
     
$(document).ready(function() {
	  $.simpleWeather({
	    zipcode: '',
	    woeid: '12884400',
	    location: '',
	    unit: 'f',
	    success: function(weather) {
	      html = '<h3>'+weather.temp+'&deg;'+weather.units.temp+'</h3>';
	      html += '<div id="image" style="top:-160px" ><img border="0" src='+weather.tomorrow.image+' width="210" height="150"></div>';
	      html += '<ul><li>'+weather.city+', '+weather.region+'</li>';

	      
	      html += '<li class="currently">'+weather.forecast+'</li>';
	      html += '<li>'+weather.tempAlt+'&deg;C</li></ul>';
	     
	  
	      $("#weather").html(html);
	    },
	    error: function(error) {
	      $("#weather").html('<p>'+error+'</p>');
	    }
	  });
	});

</script>
</body>
</html>