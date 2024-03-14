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
	    			width="300px" height="300px" class="draggable" draggable="true">
    		</c:if>
    		<c:if test="${! fn:startsWith(gallery.fileMime,'image/')}">
	    		<img alt="" src="/resources/images/document.png" width="300px" height="300px" 
	    		class="draggable" draggable="true">
    		</c:if>
    		<c:if test="${fn:startsWith(gallery.fileMime,'video/')}">
    			<video src="/video?fileNo=${gallery.fileNo}" width="300px" height="300px"
    				autoplay="autoplay" muted="muted" loop="loop" class="draggable" draggable="true"
    			></video>
    		</c:if>	
    	</c:forEach>
    </div>
    
</div>


<script>
//ㅁㅁㅁㅁ드래그앤드랍ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ
const draggables = document.querySelectorAll(".draggable");
const galleryArea = document.querySelectorAll("#galleryArea");


draggables.forEach(draggable => {
	  draggable.addEventListener("dragstart", () => {
	    draggable.classList.add("dragging");
	  });

	  draggable.addEventListener("dragend", () => {
	    draggable.classList.remove("dragging");
	  });
	});

galleryArea.forEach(container => {
	  container.addEventListener("dragover", e => {
	    e.preventDefault();
	    const afterElement = getDragAfterElement(container, e.clientX);
	    const draggable = document.querySelector(".dragging");
	    if (afterElement === undefined) {
	      container.appendChild(draggable);
	    } else {
	      container.insertBefore(draggable, afterElement);
	    }
	  });
	});

	function getDragAfterElement(container, x) {
	  const draggableElements = [
	    ...container.querySelectorAll(".draggable:not(.dragging)"),
	  ];

	  return draggableElements.reduce(
	    (closest, child) => {
	      const box = child.getBoundingClientRect();
	      const offset = x - box.left - box.width / 2;
	      // console.log(offset);
	      if (offset < 0 && offset > closest.offset) {
	        return { offset: offset, element: child };
	      } else {
	        return closest;
	      }
	    },
	    { offset: Number.NEGATIVE_INFINITY },
	  ).element;
	}

</script>