<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
$(document).ready(function() {
	$("#mainLogo").attr("src","img/main_logo_"+parseInt(Math.random()*6)+".png");
});
</script>

</head>
<body>
	<div id="main" style="text-align: center;">
		<img id="mainLogo" src="" style="width: 35%; margin-bottom: 20px;">
		<form action="searchRequest.ap" method="post" class="form-inline"
			role="form">
			<div class="form-group" style="width: 30%">
				<input type="text" name="param" class="form-control"
					placeholder="Search">
			</div>
			<button type="submit" class="btn btn-default" style="width: 5%;display: inline-block;">
				<i class="glyphicon glyphicon-search"></i>
			</button>
		</form>
	</div>
</body>
</html>