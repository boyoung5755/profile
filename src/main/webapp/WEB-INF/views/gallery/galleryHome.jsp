<%--
* [[개정이력(Modification Information)]]
* 수정일       수정자        수정내용
* ----------  ---------  -----------------
* 2024. 3. 10.      boyoung        최초작성
* Copyright (c) 2024 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script src="/resources/js/app/gallery.js"></script>
<style>
  .preview-img {
    width: 100px; /* 미리보기 이미지의 너비 */
    height: auto; /* 미리보기 이미지의 높이를 비율에 맞게 조정 */
    margin-right: 10px; /* 이미지 간 간격 */
  }
</style>



<div class="container">
	<h1>갤러리</h1>
	
	<form id="galleryData" enctype="multipart/form-data" >
        <input type="hidden" value="${rndStr}" id="fileCode" name="fileCode"> 
        <input type="file" name="profileFile" multiple="multiple">
        <button type="button" onclick="fn_galleryUpload()">Upload</button>
    </form>
    <hr class="mb-3">
	
	
	    
    <div id="galleryArea">
    	<c:forEach items="${galleryFileList}" var="gallery">
    		<c:if test="${fn:startsWith(gallery.fileMime,'image/')}">
	    		<img alt="" src="/image/show?fileNo=${gallery.fileNo}"
	    			width="300px" height="300px">
    		</c:if>
    		<c:if test="${! fn:startsWith(gallery.fileMime,'image/')}">
	    		<img alt="" src="/resources/images/document.png" width="300px" height="300px">
    		</c:if>
    		<c:if test="${fn:startsWith(gallery.fileMime,'video/')}">
    			<video src="/video?fileNo=${gallery.fileNo}" width="300px" height="300px"
    				autoplay="autoplay" muted="muted" loop="loop"
    			></video>
    		</c:if>	
    	</c:forEach>
    </div>
    
</div>