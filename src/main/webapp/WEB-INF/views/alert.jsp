<%--
* [[개정이력(Modification Information)]]
* 수정일       수정자        수정내용
* ----------  ---------  -----------------
* 2024. 3. 20.      boyoung       최초작성
* Copyright (c) 2024 by innoT All right reserved
 --%>

 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>	
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <script>

    var url="${url}";
    var msg="${msg}";
    if(msg != "" && url != ""){
        alert(msg);
        location.href = url;
    }
 </script>