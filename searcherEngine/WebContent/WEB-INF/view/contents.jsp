<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script type="text/javascript">
var dragStatus ="none";
	$(document).ready(function() {
		$("#prev").click(function() {
			var curPage = '${model.pageNum}';
			curPage--;
			if (curPage == 0)
				curPage = 5;
			$("#changePage").val(curPage);
			$("#headSearch").submit();
			showLoading();
		});
		$("#next").click(function() {
			var curPage = '${model.pageNum}';
			curPage++;
			if (curPage == 6)
				curPage = 1;
			$("#changePage").val(curPage);
			$("#headSearch").submit();
			showLoading();
		});

		$("#prev").hover(function() {
			$(this).attr("src", "img/prev2.png");
		}, function() {
			$(this).attr("src", "img/prev.png");
		});
		$("#next").hover(function() {
			$(this).attr("src", "img/next2.png");
		}, function() {
			$(this).attr("src", "img/next.png");
		});
		
		/* $("#tresh").hover(function() {
			$(this).attr("src", "img/bin2.png");
		}, function() {
			$(this).attr("src", "img/bin.png");
		}); */
	});
	
	function changePage(page){
		$("#changePage").val(page);
		$("#headSearch").submit();
		showLoading();
	}
	function dragDel(e)
	{
		dragStatus="del";
		e.dataTransfer.setData("id",e.target.id);
	}
	function allowDropDel(e){
		if(dragStatus!="del")return;
		e.preventDefault();
		$("#tresh").attr("src", "img/bin2.png");
	}	
	function leaveDropDel(e){
		e.preventDefault();
		$("#tresh").attr("src", "img/bin.png");
	}
	function dropDel(e){
		e.preventDefault();
		if(dragStatus!="del") return;
		if ('${user}' == '') {
			alert("설정변경은 로그인후이용가능");
			return;
		}
		var category = e.dataTransfer.getData("id")+'Div';
		showLoading();
 		$.post("delCategory.ap", {
			category : e.dataTransfer.getData("id"),
		}, function(data) {
			alert("test:비동기수신성공");
			hideLoading();
			$("#container").find("#"+category).remove();
		});
	}
	function dragAdd(e){
		dragStatus="add";
		e.dataTransfer.setData("id",e.target.id);
		e.dataTransfer.setData("url",e.target.src);
	}
	function allowDropAdd(e){
		if(dragStatus!="add") return;
		$("#container").css("opacity","0.4");
		$("#container").css("background-color","#BDBDBD");
		$("#addState").show();
		e.preventDefault();
	}
	function leaveDropAdd(e){
		e.preventDefault();
		$("#container").css("opacity","1");
		$("#container").css("background-color","white");
		$("#addState").hide();
	}
	
	function dropAdd(e){
		e.preventDefault();
		leaveDropAdd(e);
		if(dragStatus!="add") return;
		if ('${user}' == '') {
			alert("설정변경은 로그인후이용가능");
			return;
		}
		//추가할때 찾는작업.
		var addChoice = e.dataTransfer.getData("id");
		if($("#container").find(".subContainer").length==4){
			alert("우리 4개까지만 추가합시다;;");
			return;
		}
		if($("#container").find("#"+addChoice).val()!=null) {
			alert("중복카테고리 추가는안되요");
			return;
		}
	
		showLoading();
		var url = e.dataTransfer.getData("url");
 		$.post("addCategory.ap", {
			category : e.dataTransfer.getData("id"),
		}, function(data) {
			alert("test:비동기수신성공");
			$("#container").append('<div class="subContainer" style="vertical-align: top"><img id="'+addChoice+'" src="'+url+'"></div>');
			hideLoading();
		});
	}
</script>
</head>
<body>
	<a href="#"><img id="prev" class="pull-left" src="img/prev.png"
		style="position: fixed; top: 50%; margin-left: 10px; width: 50px;"></a>
	<div id="container" ondrop="dropAdd(event)" ondragover="allowDropAdd(event)" ondragleave="leaveDropAdd(event)">
		<div id="pageNav" style="text-align: center;">
		<a href="#" onclick="changePage(1)"><img src="${model.pageNum == 1?'img/circle_f.png':'img/circle_e.png'}"></a>
		<a href="#" onclick="changePage(2)"><img src="${model.pageNum == 2?'img/circle_f.png':'img/circle_e.png'}"></a>
		<a href="#" onclick="changePage(3)"><img src="${model.pageNum == 3?'img/circle_f.png':'img/circle_e.png'}"></a>
		<a href="#" onclick="changePage(4)"><img src="${model.pageNum == 4?'img/circle_f.png':'img/circle_e.png'}"></a>
		<a href="#" onclick="changePage(5)"><img src="${model.pageNum == 5?'img/circle_f.png':'img/circle_e.png'}"></a>
		</div>
		<c:forEach var="category" items="${model.curPageCategoryList}">
			<div id="${category.categoryName}Div" class="subContainer">
				<div class="logoContainer">
					<img id="${category.categoryName}" src="${category.logImgURL }" style="width: 140px;" draggable="true" ondragstart="dragDel(event)">
				</div>
				<div class="contentsContainer">
				<c:if test="${category.contentsList.size()==0}">검색결과가 없습니다.</c:if>
					<c:forEach var="contents" items="${category.contentsList}">
						<c:choose>
							<c:when test="${contents.widthSize!=null }">
								<a target="_blank" href="${contents.linkURL }"><img
									src="${contents.imgURL }" height="100px;" style="margin: 1px;"></a>
							</c:when>
							<c:otherwise>
								<div class="content-box">
									<h5>
										<a target="_blank" href="${contents.linkURL }"><b>${contents.title}</b></a>
									</h5>
									<div class="img"
										style="display: inline-block; width: ${contents.imgURL == null?'2%':'31%'};">
										<a target="_blank" href="${contents.linkURL }"><img
											src="${contents.imgURL }" width="95%"></a>
										<c:if test="${contents.playTime!=null}">
											<span class="pull-right"
												style="padding: 1px; font-size: 8px; background-color: black; color: white; margin-top: -17px; margin-right: 10px; position: relative; z-index: 1;">${contents.playTime}</span>
										</c:if>
									</div>
									<div class="sammary"
										style="display: inline-block; width: ${contents.imgURL == null?'98%':'65%'}; vertical-align: top;">
										${contents.summary }<br /> <br />
										<p>${contents.catagoryTag } </p>
										<p class="pull-right">${contents.uploadTime} </p>

									</div>
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
				<div class="moreContainer">
					<h4 class="pull-right">
						<a target="_blank" href="${category.searchURL }"
							style="font-size: 14px"> 검색결과 더보기 >></a>
					</h4>
				</div>
			</div>
		</c:forEach>
	</div>
	<a href="#"><img id="next" src="img/next.png"
		style="position: fixed; top: 50%; right: 10px; margin-right: 10px; width: 50px;"></a>
		<img id="tresh" src="img/bin.png" ondragover="allowDropDel(event)" ondragleave="leaveDropDel(event)" ondrop="dropDel(event)"
		style="position: fixed; bottom:2%; right: 3%; width: 70px;">
		<img id="addState" src="img/add_state.png" style="display:none; position: fixed; top: 40%; left: 50%; width: 60px;">
</body>
</html>