<%@page import="java.io.FileInputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지도 띄우기</title>
<title>map(바탕은 카카오맵, ajax로 실종동물,목격동물 정보 보이는 창 띄우기)</title>
<style type="text/css">
	.map2{ display:inline; float:left; width: 80%; height: 500px; margin-top: 30px;}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	
 
 $(document).ready(function() {
	
	 
	 
	 
	 $.ajax({
			url:"wit_info.jsp",
			dataType:"html",
			
			type:"post",
			success:function(result){
				$("#d2").html(result);
			}		
		});
	 
	
	 $('#img').on("click", function() {
		 var visi=$('#d2').attr("style").split(";")[4];
		 var state=visi.split(":")[1];
		 if(state=="hidden"){
			 	$('#d2').attr("style", "width:20%; height:100%; float:right; background:blue; visibility:visibility;");
				$('#d1').attr("style", "width:80%; height:100%; float:left; background:red;");
			return true;
		 }else
			 $('#d2').attr("style", "width:20%; height:100%; float:right; background:blue; visibility:hidden;");
			$('#d1').attr("style", "width:100%; height:100%; float:left; background:red;");
			 
	 });
	 
 });
	
</script>

<style type="text/css">
	html,body{
		width:100%;
		height:100%;
	}
</style>
</head>
<body>
	<a href="/main?action=main">메인으로</a>	
  <div id="map2"></div>
	<div style="height:100%; width:100%; overflow: hidden; margin: 0; padding: 0;">
	<div id="d1" style="width:100%; height:100%; float:left; background:red;"><img id="img" src="../../images/dog_icon1.png" style="position:relative; left:100px; top:150px;"></div> 
	<div id="d2" style="width:20%; height:100%; float:right; background:blue; visibility:hidden;">2</div>
	</div>
  	

	<div id="map" style="width:500px;height:400px;"></div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7b34394e2d8b59d2f6ccd7212da74043"></script>
	<script type="text/javascript">
		var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
		var options = { //지도를 생성할 때 필요한 기본 옵션
			center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
			level: 3 //지도의 레벨(확대, 축소 정도)
		};
		
		var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
	</script>
	
</body>

</html>