<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Picker</title>
</head>
<body>
	<div id="main" style="text-align: center;">
		<img src="img/main_logo.jpg" style="width: 40%; margin-bottom: 20px;">
		<form action="searchRequest.ap" method="post" class="form-inline"
			role="form">
			<div class="form-group" style="width: 33%">
				<input type="text" name="param" class="form-control"
					placeholder="Search">
			</div>
			<button type="submit" class="btn btn-default" style="width: 7%">
				<i class="glyphicon glyphicon-search"></i>
			</button>
		</form>

	</div>

</body>
</html>