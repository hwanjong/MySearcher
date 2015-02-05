<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title><decorator:title default="searcher" /></title>
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/bootstrap-theme.min.css" rel="stylesheet">
<link href="css/bootstrap-theme.min.css" rel="stylesheet">
<link href="css/common.css" rel="stylesheet">

<!-- JavaScript -->
<script src="js/jquery-2.1.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<c:set var="pageId" value="<%=request.getServletPath()%>" />
<decorator:head />
<script type="text/javascript">
	$(document).ready(function() {
		/* $("form").submit(function(e) {
			e.preventDefault(); //폼점송이 두번되지않게 막음
			$.post("searchRequest.ap", {
				param : $("#param").val(),
			}, function(data) {
				alert("결과수신");
				var result = '${model.result}';
				alert(data);
			});
		});
		 */
		/* $(".popover-content").append('dfdf'); */
		$("#ajaxLoading").hide();
		$("#loginModalBtn").click(function() {
			$("#loginModal").modal("show");
		});
		$("#signupModalBtn").click(function() {
			$("#signupModal").modal("show");
		});
		$("#signupBtn").click(function() {
			$("#signup").submit();
		});

		$("#info").popover().click(function(e) {
			e.preventDefault();
		});

		$("#logout").click(function() {
			window.location.replace("logout.ap");
		});
		$("#loginBtn").click(function() {
			$("#login").submit();
		});
		$("#category").click(function() {
			$("#categoryBox").show();
			$('#myTab a:first').tab('show');
		});
		$(".addCategory").click(function(e) {
			if ('${user}' == '') {
				alert("설정저장은 로그인후이용가능");
			} else {
				var id = e.target.id;
				var url = e.target.src;
				$("#container").append('<img src="'+url+'">');
				$("#ajaxLoading").show();

				$.post("/searcherEngine/addCategory.ap", {
					category : id,
				}, function(data) {
					alert("결과수신");
					var result = '${model.result}';
					$("#ajaxLoading").hide();
				});
			}

		});
	});
</script>
</head>
<body>
	<div id="headDiv">
		<h1>My Searcher Test Page</h1>
		<form action="searchRequest.ap" method="post"
			class="navbar-form navbar-left" role="search">
			<div class="form-group">
				<input type="text" name="param" class="form-control"
					placeholder="Search">
			</div>
			<button type="submit" class="btn btn-default">
				<i class="glyphicon glyphicon-search"
					style="padding: 3px; width: 40px;"></i>
			</button>
		</form>
		<c:choose>
			<c:when test="${empty user}">
				<button type="button" id="loginModalBtn" class="btn btn-default">
					<i class="glyphicon glyphicon-user" style="padding: 3px;"></i>
				</button>
			</c:when>
			<c:otherwise>
				'${user.name }' 님
				<button type="button" id="info" title="내정보"
					data-content="'${user.name }'님 환영합니다. &nbsp(현재페이지 :${model.pageNum }/5)"
					data-toggle="popover" data-placement="bottom"
					class="btn btn-default">
					<i class="glyphicon glyphicon-info-sign" style="padding: 3px;"></i>
				</button>
				<button type="button" id="logout" class="btn btn-default">
					<i class="glyphicon glyphicon-log-out" style="padding: 3px;"></i>
				</button>
			</c:otherwise>
		</c:choose>

		<button type="button" id="category" class="btn btn-default">
			<i class="glyphicon glyphicon-plus" style="padding: 3px;"></i>
		</button>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">로그인</h4>
				</div>
				<div class="modal-body">
					<form id="login" action="login.ap" method="post" role="form">
						<div class="form-group">
							<label for="exampleInputEmail1">Email address or ID</label> <input
								type="email" class="form-control" id="exampleInputEmail1"
								name="userId" placeholder="Enter email">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Password</label> <input
								type="password" class="form-control" id="exampleInputPassword1"
								name="pw" placeholder="Password">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" id="loginBtn" class="btn btn-primary">Sign
						In</button>
					<button type="button" id="signupModalBtn" class="btn btn-default"
						data-dismiss="modal">Sign Up</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<!-- Modal -->
	<div class="modal fade" id="signupModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">회원가입</h4>
				</div>
				<div class="modal-body">
					<form id="signup" action="signup.ap" method="post" role="form">
						<div class="form-group">
							<label for="exampleInputEmail1">Email address</label> <input
								type="email" class="form-control" name="userId"
								placeholder="Enter email">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Password</label> <input
								type="password" class="form-control" id="pw1"
								placeholder="Password">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Confirm Password </label> <input
								type="password" class="form-control" id="pw2" name="pw"
								placeholder="Password">
						</div>
						<div class="form-group">
							<label for="userName">Name</label> <input type="text"
								class="form-control" id="userName" name="name"
								placeholder="Name">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" id="signupBtn" class="btn btn-primary">Sign
						Up</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<div id="categoryBox">
		임시 Category Box
		<button type="button" class="close pull-right"
			onclick='$("#categoryBox").hide()'>×</button>
		<ul class="nav nav-tabs" id="myTab" style="text-align: center;">
			<li style="width: 20%"><a href="#vidio" data-toggle="pill">동영상</a></li>
			<li style="width: 20%"><a href="#news" data-toggle="pill">뉴스</a></li>
			<li style="width: 20%"><a href="#blog" data-toggle="pill">블로그</a></li>
			<li style="width: 20%"><a href="#image" data-toggle="pill">이미지</a></li>
			<li style="width: 20%"><a href="#shopping" data-toggle="pill">쇼핑</a></li>
			<li style="width: 20%"><a href="#develop" data-toggle="pill">개발</a></li>
			<li style="width: 20%"><a href="#community" data-toggle="pill">커뮤니티</a></li>
			<li style="width: 20%"><a href="#dictionary" data-toggle="pill">사전</a></li>
		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade" id="vidio">
				<img class="addCategory" id="NaverTVcast"
					src="img/logo_icon/NaverTVcast.png"> <img class="addCategory"
					id="YouTube" src="img/logo_icon/YouTube.png"> <img
					class="addCategory" id="PandoraTV" src="img/logo_icon/Pandora.png">
			</div>
			<div class="tab-pane fade" id="news">
				<img class="addCategory" id="NaverNews"
					src="img/logo_icon/naverNews.png">
			</div>
			<div class="tab-pane fade" id="blog">
				<img class="addCategory" id="NaverBlog"
					src="img/logo_icon/naverBlog.png">
			</div>
			<div class="tab-pane fade" id="image">
				<img class="addCategory" id="GoogleImage"
					src="img/logo_icon/googleImage.png">
			</div>
			<div class="tab-pane fade" id="shopping">
				<img class="addCategory" id="ElevenST"
					src="img/logo_icon/ElevenST.png"> <img class="addCategory"
					id="Aution" src="img/logo_icon/Auction.png"><img
					class="addCategory" id="Timon" src="img/logo_icon/Tmon.png">
				<img class="addCategory" id="Coupang"
					src="img/logo_icon/Coupang.png">
			</div>
			<div class="tab-pane fade" id="develop">
				<img class="addCategory" id="GitHub" src="img/logo_icon/Github.png"><img
					class="addCategory" id="StackOverFlow"
					src="img/logo_icon/Stackoverflow.png">
			</div>
			<div class="tab-pane fade" id="community">
				<img class="addCategory" id="NatePann"
					src="img/logo_icon/NatePann.png"> <img class="addCategory"
					id="NaverKin" src="img/logo_icon/naverKin.png">
			</div>
			<div class="tab-pane fade" id="dictionary">
				<img class="addCategory" id="Wikipedia"
					src="img/logo_icon/Wikipedia.png">
			</div>
		</div>

	</div>
	<decorator:body />

</body>

<div id="ajaxLoading" style="position: absolute; top: 60%; left: 50%;">
	<img id="loadingImg" src="img/ajax-loader.gif">
</div>
</html>