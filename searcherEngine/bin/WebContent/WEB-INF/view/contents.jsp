<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>다비치의 CSI</title>
<script type="text/javascript">
	
</script>

</head>
<body>
	<div id="container">
		<c:forEach var="category" items="${model.curPageCategoryList}">
			<div class="subContainer">
				<img src="${category.logImgURL }">
				<h3 style="display: inline-block;">
					<a href="${category.searchURL }"> 검색결과 더보기 >></a>
				</h3>
				<br />
				<c:forEach var="contents" items="${category.contentsList}">
					<div class="alert alert-success"
						style="height: 200px; overflow: auto">
						<h3>
							<a href="${contents.linkURL }">${contents.title}</a>
						</h3>
						<div class="img pull-left"
							style="display: inline-block; width: 110px;">
							<a href="${contents.linkURL }"><img src="${contents.imgURL }"
								width="100px"></a><br/>${contents.playTime}<br /> ${contents.uploadTime}
						</div>
						<div class="sammary pull-left"
							style="display: inline-block; width: 350px;">
							${contents.summary }<br /> ${contents.catagoryTag }<br />
						</div>
					</div>
				</c:forEach>

			</div>
		</c:forEach>
	</div>

</body>

</html>