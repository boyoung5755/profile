<%--
* [[개정이력(Modification Information)]]
* 수정일       수정자        수정내용
* ----------  ---------  -----------------
* 2024. 3. 8.      boyoung       최초작성
* Copyright (c) 2024 by innoT All right reserved
 --%>

<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<style>
    .center {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
    }
    .btn {
        width: 100%;
        height: 100px;
        font-size: 20px;
        margin: 5px;
    }
    .admin-btn {
        position: absolute;
        top: 10px;
        right: 10px;
    }
     /* 모달창 스타일 */
     .modal {
      display: none; 
      position: fixed; 
      z-index: 1; 
      left: 0;
      top: 0;
      width: 100%; 
      height: 100%; 
      overflow: auto; 
      background-color: rgba(0,0,0,0.4);
    }
    
    .modal-content {
      background-color: #fefefe;
      margin: 15% auto;
      padding: 20px;
      border: 1px solid #888;
      width: 80%;
    }
</style>
</head>
<body>
	<div class="container">
        <button id="adminBtn" class="btn btn-secondary">관리자</button>
        <div class="row justify-content-center align-items-center" style="height: 100vh;">
            <div class="col-6 center">
                <div class="row">
                    <div class="col">
                        <button type="button" class="btn btn-primary">신상정보</button>
                    </div>
                    <div class="col">
                        <button type="button" class="btn btn-secondary">방명록</button>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <button type="button" class="btn btn-success">갤러리</button>
                    </div>
                    <div class="col">
                        <button type="button" class="btn btn-danger">기술스택</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>


    <!-- 모달 창 -->
<div id="modal" class="modal">
  <div class="modal-content">
    <span class="close">&times;</span>
      <label for="password">비밀번호:</label><br>
      <input type="password" id="password" name="password"><br>
      <input type="button" value="확인" onclick="fn_adminCheck()">
  </div>
</div>

<script>
	// 관리자 버튼, 모달창, 닫기 버튼을 가져옵니다.
	var btn = document.getElementById("adminBtn");
	var modal = document.getElementById("modal");
	var span = document.getElementsByClassName("close")[0];

	// 관리자 버튼을 클릭하면 모달이 열립니다.
	btn.onclick = function() {
		modal.style.display = "block";
	}

	// 닫기 버튼을 클릭하면 모달창이 닫힙니다.
	span.onclick = function() {
		modal.style.display = "none";
	}

	// 모달창 외부를 클릭하면 모달이 닫힙니다.
	window.onclick = function(event) {
		if (event.target == modal) {
			modal.style.display = "none";
		}
	}

	function fn_adminCheck(){
		
		var password = document.querySelector("#password");

		$.ajax({
			type : 'put',
			url : '/admin',
			data : {
				'password' : password
			},
			dataType: 'json',
			success: function (data) {
				alert(data.msg);
			},
			error: function () {
				alert("서버오류 잠시후에 시도하세요.");
			}
		})
	}

</script>
