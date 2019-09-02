<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 사진을 올린 서버의 정보 -->
<c:set var="path" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		
	</head>

	<body>
		<h1>부서의 상세보기</h1>
		<form action="deptdetail" method="post">
		부서번호 : <input type="number" name="department_id" value="${dept.department_id}"><br> 
		부서이름 : <input type="text" name="department_name" value="${dept.department_name}"> <br>
		DB에 저장된 이미지 : <img alt="이미지" src="${path}/resources/${dept.fileName}" width="300" height="200"><br>
		직접 이미지 접근:
		<img alt="이미지" src="${path}/resources/나는 무민이다.jpeg" width="300" height="200"><br>
		<a href="deptdownload?folder=resources&file=${dept.fileName}">이미지 다운받기</a>
		<input type="submit" value="수정하기"> 
		
		
		</form> 
	</body>
	
</html>