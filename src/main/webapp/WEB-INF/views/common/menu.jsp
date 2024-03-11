<%--
* [[개정이력(Modification Information)]]
* 수정일       수정자        수정내용
* ----------  ---------  -----------------
* 2024. 3. 8.      boyoung       최초작성
* Copyright (c) 2024 by innoT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/resources/css/menu.css" />


<div class="container">
        <div class="row justify-content-center align-items-center" style="height: 100vh;">
            <div class="col-6 center">
                <div class="row">
                    <div class="col">
                        <button type="button" class="btn btn-primary menu"
                        	onclick="window.location.href='/common/profile'">신상정보</button>
                    </div>
                    <div class="col">
                        <button type="button" class="btn btn-secondary menu"
                        	onclick="window.location.href='/guestbook'">방명록</button>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <button type="button" class="btn btn-success menu"
                        	onclick="window.location.href='/gallery'">갤러리</button>
                    </div>
                    <div class="col">
                        <button type="button" class="btn btn-danger menu"
                        	onclick="window.location.href='/myStack'">기술스택</button>
                    </div>
                </div>
            </div>
        </div>
    </div>