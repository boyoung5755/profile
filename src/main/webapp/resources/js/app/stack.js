/**
 * 프로그램 설명
 * @date        : 2024. 3. 11.
 * @author      : boyoung
 * @version	: 1.0
 * <PRE>
 * ----------------------------
 * 개정이력
 * 2024. 3. 11. boyoung : 최초작성
 * </PRE>
 */

 
//스택이름 클릭시 코드제목 출력ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ

function fn_codeTitleList(stackNo){
	
	var stackNo = stackNo;
	
	$.ajax({
		
		type: "get",
		url: "/stack/get/"+stackNo,
		processData: false,
		contentType: false,
		cache: false,
		success: function (data) {
			let liTag = "";
			let codeList = data.cnameList;
			console.table(codeList);
			
			if(codeList[0] != null){
				
				liTag +=`
					<ul>
				`;
				$.each(codeList, function(i,v){
					
					if(v.codeDel == 'N'){
						liTag +=`
							<li onclick="fn_codeDetail('${v.codeNo}')">${v.codeTitle}</li>
						`;
					}
					
				})
				liTag +=`
					</ul>
				`;
			}else{
				liTag +=`<li>내역없음</li></ul>`;
			}
			$("#codeTitleArea").html(liTag);
		},
		error: function () {
			console.log("오류발생");
		}
	})
}	
	
	//스택이름 클릭시 코드상세 출력
	function fn_codeDetail(codeNo){
		
		var codeNo = codeNo;
		$.ajax({
		
		type: "get",
		url: "/stack/detail/"+codeNo,
		processData: false,
		contentType: false,
		cache: false,
		success: function (data) {
			let pTag = "";
			let buTag = "";
			let detail = data.codeDetail;
			console.table(detail);
			
			if(detail.codeContent != ''){
				
				pTag+=`<p>${detail.codeContent}</p>`;
				
				buTag ="";
				buTag+=`	
					<button type="button" onclick="fn_updateForm('${detail.codeNo}')">수정하기</button>
					<button type="button" onclick="fn_deleteCode('${detail.codeNo}')">삭제하기</button>
				`;
				$("#buttonArea").html(buTag);
				
			}else{
				buTag +=`
					<button type="button" onclick="fn_addCode('${detail.codeNo}')">추가하기</button>
				`;
			}
			$("#codeDetailArea").html(pTag);
			$("#buttonArea").html(buTag);
		},
		error: function (data) {
			console.log("오류발생");
		}
	})
}	


function fn_addNewCodeForm(){
	location.href = "/stack/form";
}

function fn_updateForm(codeNo){
	location.href = "/stack/edit/"+codeNo;
}

function fn_deleteCode(codeNo){
	
	$.ajax({
		type: "put",
		url: "/stack/delete/"+codeNo,
		success: function (data) {
			if(data.success == "Y"){
				alert("삭제성공");
				location.href="/stack";
			}else{
				location.href="redirect:/stack";
			}
		},
		error: function () {
			alert("서버오류");
		}
	});
	
}




function fn_updateCode(){
	
	var codeNo = document.querySelector('#codeNo').value;
	var stackNo = document.querySelector('#stackNo').value;
	var codeTitle = document.querySelector('#codeTitle').value;
	var codeContent = CKEDITOR.instances.codeContent.getData();
	var sendData ={
				'stackNo' : stackNo 
				, 'codeTitle' : codeTitle
				, 'codeContent' : codeContent
				, 'codeNo' : codeNo
			};
	
	$.ajax({
			type: "put",
			url: "/stack/update",
			data: JSON.stringify(sendData),
			contentType : 'application/json; charset=utf-8',
			success: function (data) {
				if(data.success == "Y"){
					alert("수정성공");
					location.href="/stack";
				}else{
					location.href="redirect:/stack";
				}
			},
			error: function () {
				alert("서버오류");
			}
		});
	
}



function fn_addNewCode(){
	
	var formData = new FormData($('#stackFormData')[0]);
	var codeContent = CKEDITOR.instances.codeContent.getData();
	formData.set('codeContent', codeContent);
	console.table(formData);
	$.ajax({
			type: "post",
			url: "/stack/codeNew",
			data: formData,
			contentType: false, // contentType 설정 제거
			processData: false,
			cache: false,
			success: function (data) {
				if(data.success == "Y"){
					alert("등록성공");
					location.href="/stack";
				}else{
					location.href="redirect:/stack";
				}
			},
			error: function () {
				alert("서버오류");
			}
		});
	
}




