<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동물 등록</title>
</head>
	<style>
		h3{text-align: center;}
		hr{width: 600px;}
		table{margin: auto; border: 1px solid #BDBDBD; border-radius: 5px; margin-top: 40px; padding: 30px;}
		th{text-align: left;}
		td{padding: 20px;}
		div{margin-left: 650px; margin-top: 20px;}
		textarea{width: 500px; height: 400px;}
	</style>
	<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script>
		$(function(){
			$("input[name=type]:checkbox").change(function() {
		        var cnt = 1;
		        if( cnt == $("input[name=type]:checkbox:checked").length ) {
		            $(":checkbox:not(:checked)").attr("disabled", "disabled");
		        } else {
		            $("input[name=type]:checkbox").removeAttr("disabled");
		        }
		    });
		});
	</script>
<body>
	<h3>실종동물 등록</h3><hr>
	<form action="pet?action=map" method="post" enctype="multipart/form-data">
		<table>
			<tbody>
				<tr>
					<th>사진</th>
					<td>
						<input type="file" name="missing_pic">
					</td>
				</tr>
				<tr>
					<th>실종장소</th>
					<td>
						<input name="missing_place">
					</td>
				</tr>
				<tr>
					<th>실종날짜</th>
					<td>
						<input name="missing_date">
					</td>
				</tr>
				<tr>
					<th>종류</th>
					<td>
						<input type="checkbox" name="" value="강아지"> 강아지
						<input type="checkbox" name="type" value="고양이"> 고양이
						<input type="checkbox" name="type" value="기타"> 기타
					</td>
				</tr>
				<tr>
					<th>보상금</th>
					<td>
						<input name="tip">
					</td>
				</tr>
				<tr>
					<th>코멘트</th>
					<td>
						<textarea name="comment"></textarea>
					</td>
				</tr>
			</tbody>
		</table>
		<div>
			<button type="submit">등록</button>
		</div>
	</form>
</body>
</html>