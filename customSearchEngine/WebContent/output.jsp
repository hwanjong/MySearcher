<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�˷���</title>
<img src="http://14.36.206.18:7777/loading.jpg" id="webcam1" width="100%" height="100%" alt="Live Stream" /> 

</head>
<body>

</body>
<script type="text/javascript"> 
var per_frame=10; // 40 �����ϴ°� ���幫���ѵ� 10�������ָ� ȭ�����ڿ�������� pc������ 
DoIt1(); 
document.images.webcam1.onload = DoIt1(); 

function LoadImage1() { 
uniq1 = Math.random(); 
document.images.webcam1.src = "http://211.189.127.217:7777/cam_1.jpg?uniq=1" 
+ uniq1; 
document.images.webcam1.onload = DoIt1; 
} 

function DoIt1() { 
window.setTimeout("LoadImage1();", per_frame); 
} 
</script> 

</html>