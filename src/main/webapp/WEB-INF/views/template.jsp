<%--
* [[개정이력(Modification Information)]]
* 수정일       수정자        수정내용
* ----------  ---------  -----------------
* 2024. 3. 8.      boyoung       최초작성
* Copyright (c) 2024 by innoT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>	
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <tiles:insertAttribute name="preScript" />
    <tiles:insertAttribute name="cssScript" />
    <title>Insert title here</title>
    <script src="/resources/js/app/home.js"></script>
    <style>
    	   /* 모달창 기본 스타일 */
.modal {
  display: none; /* 기본적으로 숨김 */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* 모달 내용 스타일 */
.modal-content {
  background-color: #fefefe;
  margin: 15% auto; /* 15% from the top and centered */
  padding: 20px;
  border: 1px solid #888;
  width: 80%; /* Could be more or less, depending on screen size */
}

/* 닫기 버튼 스타일 */
.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}
    
    
    
    
    </style>
    <script>
//모달 창 가져오기
var modal = document.getElementById("adminModal");

// 관리자 버튼 가져오기
var btn = document.getElementById("adminBtn");

// 닫기 버튼 가져오기
var span = document.getElementsByClassName("close")[0];

$(document).ready(function(){
    // 관리자 버튼 클릭 이벤트
    $("#adminBtn").click(function(){
        $("#adminModal").show();
    });

    // 닫기 버튼 클릭 이벤트
    $(".close").click(function(){
        $("#adminModal").hide();
    });

    // 모달창 바깥 클릭 이벤트
    $(window).click(function(e){
        if(e.target.id === "adminModal"){
            $("#adminModal").hide();
        }
    });
});



</script>
</head>
<body>
	<c:if test="${ empty sessionScope.role }">
		<button id="adminBtn" class="btn btn-secondary">관리자</button>
	</c:if>
	<c:if test="${sessionScope.role eq 'admin'}">
		<span>관리자 로그인중</span>
		<button id="logout" class="btn btn-secondary" onclick="fn_logout()">연결해제</button>
	</c:if>
    <tiles:insertAttribute name="content" />
    
    <!-- 모달 창 -->
<div id="adminModal" class="modal" style="display:none;">
  <!-- 모달 내용 -->
  <div class="modal-content">
    <span class="close">&times;</span>
    <p>관리자 비밀번호를 입력하세요.</p>
    <input type="password" id="adminPassword" />
    <button type="button" onclick="fn_adminCheck()">제출</button>
  </div>
</div>
    
    
    
</body>

</html>
