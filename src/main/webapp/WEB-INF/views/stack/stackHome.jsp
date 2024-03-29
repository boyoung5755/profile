<%--
* [[개정이력(Modification Information)]]
* 수정일       수정자        수정내용
* ----------  ---------  -----------------
* 2024. 3. 10.  김보영       최초작성
* Copyright (c) 2024 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="/resources/js/app/stack.js"></script>

<div class="container">
	<c:if test="${ sessionScope.role eq 'admin'}">
		<button type="button" class="btn btn-secondary" onclick="fn_addNewCodeForm()">등록하기</button>
		<hr class="mb-3">
	</c:if>
    <div class="row">
        <div class="col-3">
            기술목록
            <hr>
            <ul>
                <c:forEach items="${stackList}" var="sList">
                    <c:if test="${not empty sList}">
                        <li onclick="fn_codeTitleList('${sList.stackNo}')">${sList.stackName}</li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>  
        <div class="col-3">
            코드제목
            <hr>
            <div id="codeTitleArea">
            </div>
        </div> 
        <div class="col-6">
            코드상세
            <hr>
            <div id="codeDetailArea">
            </div>
            <c:if test="${ sessionScope.role eq 'admin'}">
            <div id="buttonArea"></div>
            </c:if>
        </div>  
    </div>
</div>