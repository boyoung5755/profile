<%--
* [[개정이력(Modification Information)]]
* 수정일       수정자        수정내용
* ----------  ---------  -----------------
* 2024. 3. 11.     boyoung        최초작성
* Copyright (c) 2024 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="/resources/js/ckeditor/ckeditor.js"></script>
<script src="/resources/js/app/stack.js"></script>
 
 
<div class="container">
		<h1>등록하기 폼</h1>
        <form id="stackFormData" enctype="multipart/form-data">
			<input type="hidden" value="${rndStr}" id="fileCode" name="fileCode">    
            <div class="form-group">
                <label for="stackName">기술스택</label>
                <select id="stackNo" name="stackNo" class="form-select" >
                	<option value="">선택</option>
                	<c:forEach items="${stackList}" var="sList">
                		<c:if test="${not empty sList}">
	                	<option value="${sList.stackNo}">${sList.stackName}</option>
                		</c:if>
                	</c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="codeTitle">코드 제목</label>
                <input type="text" class="form-control" id="codeTitle" name="codeTitle"value="">
            </div>
            <div class="form-group">
                <label for="codeContent">코드 상세 정보</label>
                <textarea class="form-control" id="codeContent" name="codeContent" rows="5"></textarea>
            </div>
            <div>
                <label for="codeContent">파일</label>
                <input type="file"  multiple="multiple" id="fileList" name="profileFile"/>
            </div>
            <button type="button" class="btn btn-primary" onclick="fn_addNewCode()">등록</button>
        </form>
    </div>
    
    
   <script>
	   CKEDITOR.replace('codeContent',{
		    filebrowserImageUploadUrl:`<c:url value='/image?type=image'/>`
		});
   </script>