<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style>
			label{
				width:250px;
				background-color:skyblue;
				display:inline-block;			
			}
			input{
			
			
			}
		
		</style>
		
		<script type="text/javascript" src="../common/js/jquery-3.4.1.min.js"></script>
	</head>

	<body>
		<h1>직원 추가하기</h1>
		
		<form action="empinsert" method="post">
		<label>직원번호 :</label> <input type="number" name="employee_id"><br>
		<label>직원이름 :</label>  <input type="text" name="first_name"><br>
		<label>직원성 :</label>  <input type="text" name="last_name"><br>
		<label>이메일 :</label> 
		<input type="text" name="email" placeholder="aa@bb.cc"><br>
		<label>전화번호 :</label>  <input type="tel" name="phone_number"
		placeholder="010-0000-0000"
		pattern="\d{3}-\d{4}-\d{4}"><br>
		<label>고용일 :</label>  <input type="date" name="hire_date"><br>
		<label>직무 :</label> 
		<select name="job_id">
		<c:forEach items="${joblist }" var="job">
			<option>${job}</option>
		</c:forEach>
		</select>
		
		<br>
		<label>급여 :</label>  <input type="number" name="salary"><br>
		<label>커미션 :</label>  <input type="number" name="commission_pct"><br>
		<label>매니저이름 :</label> 
		<select name="manager_id">
		<c:forEach items="${managerlist }" var="m">
			<option>${m}</option>
		</c:forEach>
		</select>		
		<br>

		<label>부서번호 :</label>
		<select name="department_id">
			<c:forEach items="${deptlist }" var="dept">
				<option value= "${dept.department_id }">${dept.department_name }</option>
			</c:forEach>
		</select><br>
		<input type="submit" value="입력하기">
		
		</form>
	</body>
	
</html>