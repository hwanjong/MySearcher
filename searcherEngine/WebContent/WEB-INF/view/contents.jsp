<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script type="text/javascript">
$(document).ready(function() {
	$("#prev").hover(
		function(){
			$(this).attr("src","img/prev2.png");
		},
	function(){
		$(this).attr("src","img/prev.png");
	});
});
</script>

</head>
<body>
	<a href="#"><img id="prev" class="pull-left" src="img/prev.png"
		style="position: fixed; top: 50%; margin-left: 10px; width: 50px;"></a>
	<div id="container">
		<c:forEach var="category" items="${model.curPageCategoryList}">
			<div class="subContainer">
				<div class="logoContainer">
					<img src="${category.logImgURL }" style="width: 150px;">

					<%-- <h3 style="display: inline-block;">
						<a href="${category.searchURL }"> 검색결과 더보기 >></a>
					</h3> --%>
				</div>
				<div class="contentsContainer">
					<c:forEach var="contents" items="${category.contentsList}">
						<c:choose>
							<c:when test="${contents.widthSize!=null }">
								<a target="_blank" href="${contents.linkURL }"><img
									src="${contents.imgURL }" height="100px;" style="margin: 1px;"></a>
							</c:when>
							<c:otherwise>

								<div class="alert alert-success">
									<h4>
										<a target="_blank" href="${contents.linkURL }">${contents.title}</a>
									</h4>
									<div class="img"
										style="display: inline-block; width: ${contents.imgURL == null?'2%':'32%'};">
										<a target="_blank" href="${contents.linkURL }"><img
											src="${contents.imgURL }" width="95%"></a>
										<c:if test="${contents.playTime!=null}">
											<span class="pull-right"
												style="padding: 1px; font-size: 8px; background-color: black; color: white; margin-top: -17px; margin-right: 10px; position: relative; z-index: 1;">${contents.playTime}</span>
										</c:if>
									</div>
									<div class="sammary"
										style="display: inline-block; width: ${contents.imgURL == null?'98%':'66%'}; vertical-align: top;">
										${contents.summary }<br /> ${contents.catagoryTag }
										${contents.uploadTime}
									</div>
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
				<div class="moreContainer">
					<h4 class="pull-right">
						<a target="_blank" href="${category.searchURL }"> 검색결과 더보기 >></a>
					</h4>
				</div>

			</div>
		</c:forEach>
	</div>
	<a href="#"><img src="img/next.png"
		style="position: fixed; top: 50%; right: 10px; margin-right: 10px; width: 50px;"></a>
</body>

</html>