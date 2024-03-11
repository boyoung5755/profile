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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
    
    h2 {
        color: white;
    }
    
 
    
</style>

<div class="center">
    <h2>김보영</h2> <!-- 이 부분에 사용자 이름이 들어갑니다. -->
    <button type="button" class="btn btn-secondary" onclick="window.location.href='/common/menu'">클릭</button>
</div>



