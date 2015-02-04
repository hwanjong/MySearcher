<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
	
</script>

</head>
<body>
	<div id="container">
		<c:forEach var="category" items="${model.curPageCategoryList}">
			<div class="subContainer">
				<div class="logoContainer">
					<img src="${category.logImgURL }" style="width: 60px;">
					<h3 style="display: inline-block;">
						<a href="${category.searchURL }"> 검색결과 더보기 >></a>
					</h3>
				</div>
				<div class="contentsContainer">
					<c:forEach var="contents" items="${category.contentsList}">
						<c:choose>
							<c:when test="${contents.widthSize!=null }">
								<a href="${contents.linkURL }"><img
									src="${contents.imgURL }" width="${contents.widthSize}"></a>
							</c:when>
							<c:otherwise>

								<div class="alert alert-success">
									<h3>
										<a href="${contents.linkURL }">${contents.title}</a>
									</h3>
									<div class="img" style="display: inline-block; width: 30%;">
										<a href="${contents.linkURL }"><img
											src="${contents.imgURL }" width="95%"></a><br />${contents.playTime}
									</div>
									<div class="sammary"
										style="display: inline-block; width: 68%; vertical-align: top;">
										${contents.summary }<br /> ${contents.catagoryTag }<br />${contents.uploadTime}
									</div>
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>

			</div>
		</c:forEach>
	</div>

</body>

</html>