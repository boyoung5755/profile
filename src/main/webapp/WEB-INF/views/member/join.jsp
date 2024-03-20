<%--
* [[개정이력(Modification Information)]]
* 수정일       수정자        수정내용
* ----------  ---------  -----------------
* 2024. 3. 20.      boyoung       최초작성
* Copyright (c) 2024 by innoT All right reserved
 --%>

 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link
  href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
  rel="stylesheet"
  integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
  crossorigin="anonymous"
/>
<script
  src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
  integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
  crossorigin="anonymous"
></script>
<script src="/resources/js/jquery-3.7.1.min.js"></script>
<script src="/resources/js/jquery.serializejson.js"></script>

 <script>


    var msg="${msg}";

    alert(msg);

 </script>

<style>
    body {
        background-color: #F7E600;
    }
    .login-form {
        background-color: white;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
    }
    .btn-kakao {
        background-color: #FEE500;
        color: black;
        border: none;
    }
    .btn-kakao:hover {
        background-color: #FDD835;
    }
</style>
<div class="container mt-5">
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <div class="login-form">
                <h5 class="text-center">카카오 회원가입</h5>
                <form id="kakaoJoinData">
                    <div class="form-group">
                        <label for="memNickname">닉네임</label>
                        <input type="text" class="form-control" id="memNickname" name="memNickname" 
                        value="${newMember.memNickname}" disabled
                        required>
                    </div>
                    <div class="form-group">
                        <label for="memEmail">이메일</label>
                        <input type="email" class="form-control" id="memEmail" name="memEmail" 
                        value="${newMember.memEmail}" disabled
                        required>
                    </div>
                    <button type="button" class="btn btn-kakao btn-block mt-3" onclick="fn_kakaoJoin()">등록하기</button>
                    <button type="button" onclick="location.href='/common/menu'" class="mt-3 btn btn-danger btn-block">취소하기</button>
                </form>
            </div>
        </div>
    </div>
</div>


<script>
    function fn_kakaoJoin(){

    var formData = new FormData($('#kakaoJoinData')[0]);

    formData.set('memEmail' ,$('#memEmail').val());
    formData.set('memNickname' ,$('#memNickname').val());
    


	console.table(formData);
	$.ajax({
			type: "post",
			url: "/kakao/join",
			data: formData,
			contentType: false, // contentType 설정 제거
			processData: false,
			cache: false,
			success: function (data) {
				if(data.success == "Y"){
					alert("회원등록성공!~~@");
					location.href="/common/menu";
				}else{
                    alert("등록실패 다시 시도하세요");
					location.href="redirect:/member/join";
				}
			},
			error: function () {
				alert("서버오류");
			}
		});



    }


</script>