/**
 * 프로그램 설명
 * @date        : 2024. 3. 15.
 * @author      : boyoung
 * @version	: 1.0
 * <PRE>
 * ----------------------------
 * 개정이력
 * 2024. 3. 15. boyoung : 최초작성
 * </PRE>
 */

 
 //1.댓글 엔터키 이벤트
/*
	keydown:키보드를 눌렀을때
	keyCode == 13 - > 엔터키
	event.preventDefault(); ->form제출방지
*/
$(document).ready(function() {
    $('#commCn').keydown(function(event) {
        if (event.keyCode == 13) {
            event.preventDefault();
            fn_insertComm();
        }
    });
});



//상세보기페이지 댓글 목록
$(function(){

  fn_commList();

});

//1.댓글목록
function fn_commList(){
	
	let settings = {
		url : '/gb/retrieveGB',
		contentType : 'application/json',
		method : 'get',
	};
	
	let divTag='';
	$.ajax(settings).done(function (resp){
		var commList = resp.commList;
		if(commList.length != 0){
			$.each(commList , function(i, v){
				if(v.gbNo != null){
					divTag += `
						<div class="card mt-3">
					      <div class="card-body">
					        <div class="d-flex justify-content-between mb-2 ">
					          <h8 class="fw-normal">${v.gbRdate}</h8>
					        </div>
					        <div class="d-flex justify-content-between align-items-end">
					          <div class="role-heading" >
					            <h5 class="mb-1">${v.gbContent}</h5>
					          </div>
					        </div>
					      </div>
					    </div>
					`;
				}
			})
		}else{
			divTag += `
				<div class="card mt-3">
			      <div class="card-body">
			        <div class="d-flex justify-content-between align-items-end">
			          <div class="role-heading" >
			            <h5 class="mb-1">작성된 댓글이 없습니다.</h5>
			          </div>
			        </div>
			      </div>
			    </div>
			`;
		}
		$("#gbArea").html(divTag);
	})
}




//2.댓글 등록

function fn_insertComm(){
	
	fn_swalConfirm(" 등록하시겠습니까?", function(){
		//비동기
		var insertComm = new FormData($("#insertComm")[0]);
		
		$.ajax({
			type: "POST",
			enctype: 'multipart/form-data',
			url: "/gb/createGB",
			data: insertComm,
			processData: false,
			contentType: false,
			cache: false,
			success: function (data) {
				var text;
				var icon;
				if (data.success == "Y") {
					text = "댓글 등록이 완료되었습니다.";
					icon = "success";
				} else {
					text = "댓글 등록이 실패되었습니다.";
					icon = "warning";
				}
				fn_swalComplete(text, icon, "/guestbook"+data.boNo, data.success);
			},
			error: function () {
				fn_swalError();
			}
		});
	});
}

