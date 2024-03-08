<%--
* [[개정이력(Modification Information)]]
* 수정일       수정자        수정내용
* ----------  ---------  -----------------
* 2024. 3. 7.      김보영        최초작성
* Copyright (c) 2024 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<style>
	    body {
	        background-color: #008000;
	    }
	    .center {
	        position: absolute;
	        top: 50%;
	        left: 50%;
	        transform: translate(-50%, -50%);
	        text-align: center;
	    }
	    .admin-btn {
	        position: absolute;
	        top: 10px;
	        right: 10px;
	    }
	    h2 {
	        color: white;
	    }
	</style>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="center">
        <h2>김보영</h2> <!-- 이 부분에 사용자 이름이 들어갑니다. -->
        <button type="button" class="btn btn-secondary" onclick="<c:url value='/menu' />">클릭</button>
    </div>
    <button class="btn btn-secondary admin-btn" type="button" onclick="alert('관리자 버튼이 클릭되었습니다.')">관리자</button>
    
</body>
</html>
