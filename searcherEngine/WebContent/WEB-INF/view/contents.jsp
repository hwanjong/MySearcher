<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>

<script type="text/javascript">
	var dragStatus = "none";
	$(document).ready(function() {
		$(".subContainer").each(function(){
			var totalWidth=$(this).css("width");
			totalWidth=totalWidth.split("px")[0];
			var eachWidth = totalWidth / parseInt(totalWidth/320) -50;
			$(this).find(".content-box").css("width",eachWidth);
		});
		
		 $(".subContainer" ).resizable({
			 maxHeight: 800,
		     maxWidth: 1500,
		     minHeight: 277,
		     minWidth: 350,
		     disabled: true,
		     stop: function( event, ui ) {
		    	 
		    	 var category =ui.element.context.id;
		    	 var width = ui.size.width;
		    	 var height = ui.size.height;
		    	 
		    	 if ('${user}' == '') return;
		    	 
		    	 var totalWidth=$("#"+category).css("width");
		    	 totalWidth=totalWidth.split("px")[0];
		    	 var eachWidth = totalWidth / parseInt(totalWidth/320) -50;
		    	 
		    	 $("#"+category).find(".content-box").css("width",eachWidth);
		    	 
		    	 showLoading();
		    	 $.post("saveResize.ap", {
		    		 	categoryDiv : category,
		    			width : width,
		    			height : height
		    		}, function(data) {
		    			hideLoading();
		    		});
		     }
		 });
		 $(".ui-resizable-handle").hide();
		 
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

	function changePage(page) {
		$("#changePage").val(page);
		$("#headSearch").submit();
		showLoading();
	}
	function dragScrap(e) {
		console.log("dragScrap"+e.target.id);
		dragStatus = "scrap";
		e.dataTransfer.setData("id", e.target.id);
	}
	function allowDropScrap(e) {
		console.log("allowDropScrap");
		if (dragStatus != "scrap")
			return;
		e.preventDefault();
		$("#scrapBox").css("background-color", "#BDBDBD");
	}
	function leaveDropScrap(e) {
		console.log("leaveDropScrap");
		e.preventDefault();
		$("#scrapBox").css("background-color", "rgb(226, 228, 230)");
	}
	
	function dropScrap(e) {
		console.log("dropScrap");
		e.preventDefault();
		leaveDropScrap(e);
		var divId = e.dataTransfer.getData("id");
		
		if(divId=="") return;
		
		if (dragStatus != "scrap")
			return;
		if ('${user}' == '') {
			alert("설정변경은 로그인후이용가능");
			return;
		}
		var newDivId = $("#scrapBox").find(".content-box").last().attr("id");
		if(newDivId==null){
			newDivId=1;
		}
		else newDivId= parseInt(newDivId)+1;
		var closeBtnDiv = '<button type="button" class="close pull-right" onclick="deleteScrap(parentNode)" style="margin-bottom: -10px;">×</button>';
		var divHtml = '<div id="'+newDivId+'" class="content-box">'+closeBtnDiv+$("#container").find("#"+divId).html()+'</div>';
		$("#scrapBox").append(divHtml);
		
		showLoading();
		$.post("addScrap.ap", {
			divId : newDivId,
			divHtml : divHtml
		}, function(data) {
			hideLoading();
		}); 
	}
	
	//델리트 Drag부분
	function dragDel(e) {
		console.log("dragDel");
		dragStatus = "del";
		e.dataTransfer.setData("id", e.target.id);
	}
	function allowDropDel(e) {
		console.log("allowDropDel");
		if (dragStatus != "del")
			return;
		e.preventDefault();
		$("#tresh").attr("src", "img/bin2.png");
	}
	function leaveDropDel(e) {
		console.log("leaveDropDel");
		e.preventDefault();
		$("#tresh").attr("src", "img/bin.png");
	}
	function dropDel(e) {
		console.log("dropDel");
		e.preventDefault();
		leaveDropDel(e);
		if (dragStatus != "del")
			return;
		if ('${user}' == '') {
			alert("설정변경은 로그인후이용가능");
			return;
		}
		var category = e.dataTransfer.getData("id") + 'Div';
		showLoading();
		$.post("delCategory.ap", {
			category : e.dataTransfer.getData("id"),
		}, function(data) {
			hideLoading();
			$("#container").find("#" + category).remove();
		});
	}
	
	function dragAdd(e) {
		dragStatus = "add";
		e.dataTransfer.setData("id", e.target.id);
		e.dataTransfer.setData("url", e.target.src);
	}
	function allowDropAdd(e) {
		if (dragStatus != "add")
			return;
		$("#container").css("opacity", "0.4");
		if ($("#container").find(".subContainer").length == 4) {
			$("#container").css("background-color", "#FFA7A7");
			$("#banState").show();
		}else{
			$("#container").css("background-color", "#BDBDBD");
			$("#addState").show();
		}
		e.preventDefault();
	}
	function leaveDropAdd(e) {
		e.preventDefault();
		$("#container").css("opacity", "1");
		$("#container").css("background-color", "white");
		$("#addState").hide();
		$("#banState").hide();
	}

	function dropAdd(e) {
		e.preventDefault();
		leaveDropAdd(e);
		if (dragStatus != "add")
			return;
		if ('${user}' == '') {
			alert("설정변경은 로그인후이용가능");
			return;
		}
		//추가할때 찾는작업.
		var addChoice = e.dataTransfer.getData("id");
		if ($("#container").find(".subContainer").length == 4) {
			return;
		}
		if ($("#container").find("#" + addChoice).val() != null) {
			return;
		}

		showLoading();
		var url = e.dataTransfer.getData("url");
		$.post("addCategory.ap",{
			category : e.dataTransfer.getData("id"),
			},
		function(data) {
			$("#container").append('<div id="'+addChoice+'Div" class="subContainer" style="vertical-align: top; position:static;"><img id="'+ addChoice+ '" src="'+ url+ '" ondragstart="dragDel(event)"></div>');
			hideLoading();
		});
	}
	
	function upIndex(id){
		if ('${user}' == '') {
			alert("설정변경은 로그인후이용가능");
			return;
		}
		var curIndex=$("#"+id).css("z-index");
		if(curIndex==4) return;
		
		var targetIndex = parseInt(curIndex)+1;
		var targetClass = "subContainer "+curIndex;
		var curClass = "subContainer "+targetIndex;
		//1. 대상의 클래스명과 css zindex 낮추기
		 $("#container").find("."+targetIndex).css("z-index",curIndex);
		 $("#container").find("."+targetIndex).attr("class",targetClass);
		//2. 자신의 클래스명과 zindex올리기
		$("#"+id).css("z-index",targetIndex);
		$("#"+id).attr("class",curClass);
		
		//DB처리
		showLoading();
		$.post("changeIndex.ap",{
			curIndex : curIndex,
			state : "up",
			},
		function(data) {
			hideLoading();
		});
		
	}
	function downIndex(id){
		if ('${user}' == '') {
			alert("설정변경은 로그인후이용가능");
			return;
		}
		var curIndex=$("#"+id).css("z-index");
		if(curIndex==1) return;
		
		var targetIndex = parseInt(curIndex)-1;
		var targetClass = "subContainer "+curIndex;
		var curClass = "subContainer "+targetIndex;
		//1. 대상의 클래스명과 css zindex 낮추기
		 $("#container").find("."+targetIndex).css("z-index",curIndex);
		 $("#container").find("."+targetIndex).attr("class",targetClass);
		//2. 자신의 클래스명과 zindex올리기
		$("#"+id).css("z-index",targetIndex);
		$("#"+id).attr("class",curClass);
		
		//DB처리
		showLoading();
		$.post("changeIndex.ap",{
			curIndex : curIndex,
			state : "down",
			},
		function(data) {
			hideLoading();
		});
	}
</script>
</head>
<body>
<%int contentsNum=0; %>
	<a href="#"><img id="prev" class="pull-left" src="img/prev.png"
		style="position: fixed; top: 50%; margin-left: 10px; width: 50px; z-index: 5;"></a>
	<div id="container" ondrop="dropAdd(event)"
		ondragover="allowDropAdd(event)" ondragleave="leaveDropAdd(event)">
		<div id="pageNav" style="text-align: center;">
			<a href="#" onclick="changePage(1)"><img
				src="${model.pageNum == 1?'img/circle_f.png':'img/circle_e.png'}"></a>
			<a href="#" onclick="changePage(2)"><img
				src="${model.pageNum == 2?'img/circle_f.png':'img/circle_e.png'}"></a>
			<a href="#" onclick="changePage(3)"><img
				src="${model.pageNum == 3?'img/circle_f.png':'img/circle_e.png'}"></a>
			<a href="#" onclick="changePage(4)"><img
				src="${model.pageNum == 4?'img/circle_f.png':'img/circle_e.png'}"></a>
			<a href="#" onclick="changePage(5)"><img
				src="${model.pageNum == 5?'img/circle_f.png':'img/circle_e.png'}"></a>
		</div>
		<c:forEach var="category" items="${model.curPageCategoryList}">
			<div class="subContainer ${category.zIndex}" id="${category.categoryName}Div" style="position:absolute; width:${category.width }px; height:${category.height }px; left: ${category.left}; top: ${category.top }; z-index: ${category.zIndex};">
				<div class="logoContainer" onmousedown="" >
					<img id="${category.categoryName}" class="logoImg" src="${category.logImgURL }"
						style="width: 140px;" draggable="true"
						ondragstart="dragDel(event)">
						<span class="zIndex pull-right" style="display: none;"><img src="img/c_front.png" width="30px;" style="margin: 5px;" onclick="upIndex('${category.categoryName}Div')"><img src="img/c_back.png" width="30px;" style="margin: 5px;" onclick="downIndex('${category.categoryName}Div')"></span>
				</div>
				<div class="contentsContainer">
					<c:if test="${category.contentsList.size()==0}">검색결과가 없습니다.</c:if>
					<c:forEach var="contents" items="${category.contentsList}">
						<c:choose>
							<c:when test="${contents.widthSize!=null }">
							<div class="imgContentsDiv" id="<%=contentsNum++ %>" style="display: inline-block;  float: left;" draggable="true" ondragstart="dragScrap(event)">
								<a target="_blank" href="${contents.linkURL }" draggable="false"><img  
									src="${contents.imgURL }" height="100px;" style="margin: 1px;" draggable="false"></a>
							</div>
							</c:when>
							<c:otherwise>
								<div class="content-box" id="<%=contentsNum++ %>" draggable="true" ondragstart="dragScrap(event)">
									<h5>
										<a target="_blank" href="${contents.linkURL }"><b>${contents.title}</b></a>
									</h5>
									<div class="img"
										style="display: inline-block; width: ${contents.imgURL == null?'2%':'31%'};">
										<a target="_blank" href="${contents.linkURL }"><img
											src="${contents.imgURL }" width="95%"></a>
										<c:if test="${contents.playTime!=null}">
											<span class="pull-right"
												style="padding: 1px; font-size: 8px; background-color: black; color: white; margin-top: -17px; margin-right: 10px; position: relative;">${contents.playTime}</span>
										</c:if>
									</div>
									<div class="sammary"
										style="display: inline-block; width: ${contents.imgURL == null?'98%':'65%'}; vertical-align: top;">
										${contents.summary }<br /> <br />
										<p>${contents.catagoryTag }</p>
										<p class="pull-right">${contents.uploadTime}</p>

									</div>
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
				<div class="moreContainer" style="text-align: right; vertical-align: bottom;">
					<h4 >
						<a target="_blank" href="${category.searchURL }"
							style="font-size: 14px"> 검색결과 더보기 >></a>
					</h4>
				</div>
			</div>
		</c:forEach>
	</div>
	<a href="#"><img id="next" src="img/next.png"
		style="position: fixed; top: 50%; right: 10px; margin-right: 10px; width: 50px; z-index: 5;"></a>
	<img id="tresh" src="img/bin.png" ondragover="allowDropDel(event)"
		ondragleave="leaveDropDel(event)" ondrop="dropDel(event)"
		style="position: fixed; bottom: 2%; right: 3%; width: 70px; z-index: 5">
	<img id="addState" src="img/add_state.png"
		style="display: none; position: fixed; top: 40%; left: 50%; width: 60px;">
	<img id="banState" src="img/ban_state.png"
		style="display: none; position: fixed; top: 40%; left: 50%; width: 60px;">
</body>
</html>