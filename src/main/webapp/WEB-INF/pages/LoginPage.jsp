<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<link href="css/bootstrap.css" rel="stylesheet">



<link href="css/sticky-footer-navbar.css" rel="stylesheet"
	media="screen">

<style type="text/css">
.loginForm {
	float: right;
}

input.span4,submit.span4 {
	width: auto;
	height: auto;
}
</style>
</head>
<body>
	<form action="<c:url value='j_spring_security_check' />" name='f'
		method="post">
		<fieldset>
			<div class="loginForm">
				<label>User Name:<input type="text" name="j_username" /></label> <label>Password:<input
					type="password" name="j_password" /></label> <input type="submit"
					value="submit" />
			</div>
		</fieldset>
	</form>



	<form class="form-horizontal">
		<fieldset>

			<!-- Form Name -->
			<legend>Form Name</legend>

			<!-- Text input-->
			<div class="control-group">
				<label class="control-label" for="textinput">Username</label>
				<div class="controls">
					<input id="textinput" name="textinput" type="text" placeholder=""
						class="input-xlarge">

				</div>
			</div>

			<!-- Text input-->
			<div class="control-group">
				<label class="control-label" for="textinput">Password</label>
				<div class="controls">
					<input id="textinput" name="textinput" type="text" placeholder=""
						class="input-xlarge">

				</div>
			</div>

			<!-- Button -->
			<div class="control-group">
				<label class="control-label" for="singlebutton"></label>
				<div class="controls">
					<button id="singlebutton" name="singlebutton"
						class="btn btn-default">Login</button>
				</div>
			</div>

		</fieldset>
	</form>





	<script src="js/jquery.js"></script>
	<script
		src="http://static.scripting.com/github/bootstrap2/js/bootstrap-modal.js"></script>
	<script type="text/javascript">
		var error = "${error}";
		console.log(error);
	</script>
</body>
</html>