<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script type="text/javascript">
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
	});
	
	function changePage(page){
		$("#changePage").val(page);
		$("#headSearch").submit();
		showLoading();
	}
</script>

</head>
<body>
	<a href="#"><img id="prev" class="pull-left" src="img/prev.png"
		style="position: fixed; top: 50%; margin-left: 10px; width: 50px;"></a>
	<div id="container">
	<div id="pageNav" style="text-align: center;">
	<a href="#" onclick="changePage(1)"><img src="${model.pageNum == 1?'img/circle_f.png':'img/circle_e.png'}"></a>
	<a href="#" onclick="changePage(2)"><img src="${model.pageNum == 2?'img/circle_f.png':'img/circle_e.png'}"></a>
	<a href="#" onclick="changePage(3)"><img src="${model.pageNum == 3?'img/circle_f.png':'img/circle_e.png'}"></a>
	<a href="#" onclick="changePage(4)"><img src="${model.pageNum == 4?'img/circle_f.png':'img/circle_e.png'}"></a>
	<a href="#" onclick="changePage(5)"><img src="${model.pageNum == 5?'img/circle_f.png':'img/circle_e.png'}"></a>
	</div>
		<c:forEach var="category" items="${model.curPageCategoryList}">
			<div class="subContainer">
				<div class="logoContainer">
					<img src="${category.logImgURL }" style="width: 140px;">
				</div>
				<div class="contentsContainer">
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
</body>

</html>