<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="kr.or.dw.vo.LectureVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수강 강의</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.5.1.js"></script>
<script>
	$(function(){ 
		$('select[name=year]').change(function(){ //년도 선택하면 해당 년도의 강의만 보이는 함수
		  for(let i = 0; i < $('tr[type=var]').length; i++){
			  if($(this).val() == 'all'){
				  $('tr[type=var]').css('display', '');
			  }else if($(this).val() == $($('tr[type=var]')[i]).attr('name')){
				  $($('tr[type=var]')[i]).css('display', '');
			  }else{
				  $($('tr[type=var]')[i]).css('display', 'none');
			  }
		  }
		  
		});
	})
</script>
</head>
<body>
	<h3>수강 강의</h3>
	<select id="year" name="year"> 
		<option name="year" value="all" seleted>전체보기</option>
	<% List<LectureVO> lecList = (List)request.getAttribute("lecList"); //학생이 수강한 년도만 보이게
		Set<Integer> yearList = new HashSet<>();
		for(LectureVO vo : lecList){
			yearList.add(vo.getLec_year());
		};
		for(int year : yearList){
	%>
			<option name="year" value="<%=year%>"><%=year%>년</option>
	<%	} %>

	</select>
	<br>
	<br>
	<div style="width:1000px; height:50%; overflow:auto">
		<table border="1" width="100%" cellspacing="0" cellpadding="0">
			<tr style="height:50px;">
				<th>강의코드</th>
				<th>강의명</th>
				<th>수강년도</th>
				<th>수강학기</th>
				<th>교수명</th>
				<th>강의실</th>
				<th>시간</th>
				<th>구분</th>
				<th>학점</th>
				<th>강의계획서</th>
			</tr>
			<%
			for(LectureVO vo : lecList){
				String lec_time = null;
				double timecre = (double) vo.getLec_time() / vo.getLec_credit();
				if(timecre == 1.5){
					lec_time = "1, 2";
				}else if(timecre == 2){
					lec_time = "1, 2, 3";
				}else if(timecre == 2.5){
					lec_time = "2, 3";
				}else if(timecre == 3){
					lec_time = "2, 3, 4";
				}else if(timecre == 3.5){
					lec_time = "3, 4";
				}else if(timecre == 4){
					lec_time = "3, 4, 5";
				}else if(timecre == 4.5){
					lec_time = "4, 5";
				}else if(timecre == 5){
					lec_time = "4, 5, 6";
				}else if(timecre == 5.5){
					lec_time = "5, 6";
				}else if(timecre == 6){
					lec_time = "5, 6, 7";
				}else if(timecre == 6.5){
					lec_time = "6, 7";
				}else if(timecre == 7){
					lec_time = "6, 7, 8";
				}else if(timecre == 7.5){
					lec_time = "7, 8";
				}else if(timecre == 8){
					lec_time = "7, 8, 9";
				}else if(timecre == 8.5){
					lec_time = "8, 9";
				}
			
			%>
				<tr type="var" name="<%=vo.getLec_year() %>" style="text-align:center; height:30px;">
					<td><%=vo.getLec_code() %></td>
					<td><%=vo.getLec_name() %></td>
					<td><%=vo.getLec_year() %></td>
					<td><%=vo.getLec_term() %>학기</td>
					<td><%=vo.getStaff_name() %></td>
					<td><%=vo.getLec_loc() %></td>
					<td><%=vo.getLec_day() %> <%=lec_time %>교시</td>
					<td><%=vo.getLec_div() %></td>
					<td><%=vo.getLec_credit() %></td>
					<td><input type="button" value="강의계획서 보기"></td>
				</tr>
			
			<%}%>
		
		
		</table>
	</div>
</body>
</html>