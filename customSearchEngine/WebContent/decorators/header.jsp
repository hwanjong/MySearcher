<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title><decorator:title default="flossom" /></title>
<link href="/flossum/css/bootstrap.min.css" rel="stylesheet">
<link href="/flossum/css/header.css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="http://fonts.googleapis.com/earlyaccess/hanna.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="//code.jquery.com/jquery-1.9.1.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="http://maps.googleapis.com/maps/api/js?key=AIzaSyBMuk4J8j5JNE1PC0UdEWpIMmze8UKMG_U&sensor=true">
	
</script>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi" />
<c:set var="pageId" value="<%=request.getServletPath()%>" />
<decorator:head />
<script type="text/javascript">
	function goBack() {
		Android.back();
	}
	function hideLoading() {
		$("#ajaxLoading").hide();
		$('body').css('opacity', '1');
	};

	function showLoading() {
		$("#ajaxLoading").show();
		$("#loadingImg").css('opacity', '1');
		// 		$('body').css('opacity', '0.5');
	};

	function success() {
		if ('${user}') {
			if ('${user.shopNum}' == 0) {
				if ($("#input[name=optionsRadios]:checked").val() == "option2"
						&& $("#targetAddress").val() == '') {
					alert("배송지를 입력하셔야합니다.");
				} else {
					alert("역경매신청완료");
 					$("#auctionForm").submit();
				}
			} else {
				alert("꽃집회원은 신청불가!");
			}
		} else {
			alert("로그인후 이용하세요");
		}
	}
	function checkDelivery() {
		if ($("input[name=optionsRadios]:checked").val() == "option1") {
			$("#address").hide();
		} else {
			$("#address").show();
		}
	}

	function myAuction() {
		showLoading();
		$("#auction").modal("show");
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(function(position) {
				var lat = position.coords.latitude;
				var lng = position.coords.longitude;
				$("input[name=auctionLat]").val(lat);
				$("input[name=auctionLng]").val(lng);
				hideLoading();
			});
		} else {
			// Browser doesn't support Geolocation
			alert("위치찾기실패");
		}
	}

	function myLocation() {
		showLoading();
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(function(position) {
				var lat = position.coords.latitude;
				var lng = position.coords.longitude;
				$("input[name=lat]").val(lat);
				$("input[name=lng]").val(lng);
				$("#locateForm").submit();
			});
		} else {
			// Browser doesn't support Geolocation
			alert("위치찾기실패");
		}
	}
	$(document).ready(function() {
		hideLoading();
		$("#datepicker").datepicker();
	});
</script>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-ex1-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/flossum/main.ap">FLOSSUM</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">이용안내</a></li>
				<li><a href="#" onclick="myAuction()">역경매신청</a></li>
				<li><a href="#">대량구매</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">꽃집찾기 <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#" onclick="myLocation()">내주변꽃집</a></li>
						<li><a href="#"
							onclick="location.replace('/flossum/check/searchAddress.ap');">다른지역꽃집</a></li>
					</ul></li>
			</ul>
			<hr>
			<ul class="nav navbar-nav navbar-right">
				<c:choose>
					<c:when test="${empty user}">
						<li><a href="/flossum/login.ap">로그인</a></li>
						<li><a href="/flossum/join.ap">회원가입</a></li>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${user.shopNum==0}">
								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown">Mypage <b class="caret"></b></a>
									<ul class="dropdown-menu">
										<li class="${pageId == '/user/orderInfo.ap'?'active':''}"><a
											href="/flossum/user/orderInfo.ap">주문상태조회</a></li>
										<li class="${pageId == '/user/adminAuction.ap'?'active':''}"><a
											href="/flossum/user/adminAuction.ap">역경매조회/낙찰</a></li>
										<li class="${pageId == '/user/like.ap'?'active':''}"><a
											href="/flossum/user/like.ap">Like꽃집관리</a></li>
										<li class="${pageId == '/user/info.ap'?'active':''}"><a
											href="/flossum/user/info.ap">회원정보수정</a></li>
									</ul></li>
							</c:when>
							<c:otherwise>
								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown">MyShop <b class="caret"></b></a>
									<ul class="dropdown-menu">
										<li class="${pageId == '/shop/adminSale.ap'?'active':''}"><a
											href="/flossum/shop/adminSale.ap">판매관리</a></li>
										<li class="${pageId == '/shop/adminShop.ap'?'active':''}"><a
											href="/flossum/shop/adminShop.ap">매장관리</a></li>
										<li class="${pageId == '/shop/adminEvent.ap'?'active':''}"><a
											href="/flossum/shop/adminEvent.ap">이벤트관리</a></li>
										<li class="${pageId == '/shop/saleAuction.ap'?'active':''}"><a
											href="/flossum/shop/saleAuction.ap">주변역경매조회/신청</a></li>
										<li class="${pageId == '/user/info.ap'?'active':''}"><a
											href="/flossum/order.ap?id=${user.shopNum}">내꽃집정보</a></li>
									</ul></li>
							</c:otherwise>
						</c:choose>
						<li><a href="/flossum/logout.ap">로그아웃</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</nav>
	<div id="ajaxLoading" style="position: absolute; top: 60%; left: 50%;">
		<img id="loadingImg" src="/flossum/img/ajax-loader.gif">
	</div>
	<!-- 모달그룹 -->
	<div id="auction" class="modal fade" id="myModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<form id ="auctionForm" action="/flossum/auction.ap" method="post" class="form-inline"
			role="form">
			<div class="modal-dialog" style="padding: 10px 0px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 class="modal-title">역경매신청하기</h4>
					</div>
					<div class="modal-body">
						<span class="w100 ib">요청상품</span><input type="text"
							name="auctionName"><br /> <label class="radio">
							<input type="radio" name="optionsRadios" id="optionsRadios1"
							value="option1" onclick="checkDelivery()"> 방문
						</label> <label class="radio"> <input type="radio"
							name="optionsRadios" id="optionsRadios2" value="option2" checked
							onclick="checkDelivery()"> 배송
						</label> <span class="w100 ib">수 령 자 </span><input type="text"
							class="input-mini" id="targetName" name="targetName"><br />
						<span class="w100 ib"> 희망날짜 </span> <input name="requestTime"
							type="text" class="form-control" id="datepicker" readonly><select
							name="requestHour" class="form-control ib">
							<%
								for (int i = 0; i < 24; i++) {
							%><option><%=i%></option>
							<%
								}
							%>
						</select>
						<p id="address">
							<span class="w100 ib">수령자연락처</span><input type="text"
								name="targetPhone" placeholder="ex) 010-1234-1234"><br />
							<span class="w100 ib">배송지주소</span><input type="text"
								class="input-xxlarge" name="targetAddress"><br />
						</p>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
						<button type="button" class="btn btn-primary" onclick="success()">역경매등록</button>
						<input name="auctionLat" value='${model.info.lat}'
							style="display: none"> <input name="auctionLng"
							value='${model.info.lng}' style="display: none">
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</form>
		<form id="locateForm" action="/flossum/findshop.ap" method="post"
			style="display: none;">
			<input type="text" name="lat"> <input type="text" name="lng">
		</form>
	</div>
	<decorator:body />
</body>
</html>