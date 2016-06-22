<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Welcome to the Smart House</title>
        <link rel="stylesheet" href="assets/css/styles.css" />
        
    </head>
    <body>
		<div id="formContainer">
		
			<form id="login" method="post" action="LoginServlet">
				<a href="#" id="flipToRecover" class="flipLink">Forgot?</a>
				<input type="text" name="username" id=username placeholder="Username" />
				<input type="password" name="password" id="loginPass" placeholder="Password" />
				<%
				String display = (String) session.getAttribute("Result");

				if (display == null) {
			%>

			<%
				} else {
			%>

					<script>alert("Sorry please try again.")</script>
			<%
				}
			%>
				<input type="submit" name="submit" value="Login" />
			</form>
			<form>
				<a href="#" id="flipToLogin" class="flipLink">Forgot?</a>
				<input type="text" name="recoverEmail" id="recoverEmail" value="Email" />
				<input type="submit" name="submit" value="Recover" />
			</form>
		</div>
		<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
		<script src="assets/js/script.js"></script>

    </body>
</html>
