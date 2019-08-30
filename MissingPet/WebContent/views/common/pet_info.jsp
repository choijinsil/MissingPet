<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div>
		<table>
			<tbody>
				<tr>
					<th>아이디</th>
					<td>
						<font class="a">${vo.id}</font>
					</td>
				</tr>
				<tr>
					<th>사진</th>
					<td>
						<font class="a"></font>
					</td>
				</tr>
				<tr>
					<th>품종</th>
					<td>
						<font class="a">${vo.missing_type}</font>
					</td>
				</tr>
				<tr>
					<th>코멘트</th>
					<td>
						<font class="a">${vo.missing_comment}</font>
					</td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>
						<font class="a">${vo.tel}</font>
					</td>
				</tr>
				<tr>
					<th>사례금</th>
					<td>
						<font class="a">${vo.tip}</font>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
		