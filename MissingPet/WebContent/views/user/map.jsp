<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Map</title>
	<style>
		.wrap{ width: 80%; margin: 130px 2.0% 20px;  float: left;}
		.map{ width: 600px; height: 500px; position: relative; background-color: orange; }
		.list{ margin-left: 700px; position: absolute; width: 300px; height: 500px;}
		.list td{padding: 30px;}
		.list th{text-align: left;}
		.list .a{color:#0B3861; font-size: 12px bold;}
		a{margin-left: 790px; font-size: 30px; color: red;}
		.bt_item{ margin-left: 780px; margin-top: 20px;}
		
		#table{margin: auto; border: 1px solid #BDBDBD; border-radius: 5px; margin-top: 40px;}
		textarea{width: 180px; height: 100px;}
		h3{text-align: center;}
		.bt_div{margin-left: 500px;}
	</style>
	<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script>
		$(function(){
			$('button').click(function(){
				$.ajax(
					{
						url:'/views/user/pet_info.jsp',
						data:{
							dvd_no: '${param.missing_no }'
						},
						success:function(result){
							$('#list').html(result);
						}
					}		
				);
			});
		});
	</script>
</head>
<body>
	<div class="wrap">
		<div id="list" class="list">
		</div>
		<div class="map">
		</div>
		<button>보기</button>
	</div>
</body>
</html>