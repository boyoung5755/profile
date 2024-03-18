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

//파일리스트

$(function(){
	fn_fileList();
	
})


//1.페이징 버튼 눌렀을때
function fn_paging(page){
	searchForm.page.value = page ;
	fn_fileList();

}

$(document).on('click','#searchBtn', function(){
  	fn_fileList();
})

function fn_fileList(){
	
	let formData = $('#searchForm').serialize();
	
	let settings = {
		url : '/file/list',
		contentType : 'application/json',
		method : 'get',
		data : formData,
		dataType: 'json'
	};
	
	let trTag='';
	$.ajax(settings).done(function (resp){
		
		let pagingFileList = resp.paging.dataList;
		let simpleCondition = resp.paging.simpleCondition;
		
		if(pagingFileList[0] != null){
			
			$.each(pagingFileList, function(i,v){

		        if(v.fileNo != null){
		          trTag +=`
		            <tr  style="cursor: pointer;">
		              <td onclick="fn_fileDownload('${v.fileNo}')">${v.fileName}</td>
		              <td>${v.fileFancysize}</td>
		              <td>${v.fileRdate}</td>
		              <td>${v.fileMime}</td>
		              <td><button type="button" class="btn btn-danger" onclick="fn_removeFile('${v.fileNo}')">삭제</button></td>
		            </tr> 
		          `;
		        }else{
		          trTag +=`
		            <tr>
		              <td colspan='5'>내역 없음</td>
		            </tr>  
		          `;
		        }

        	$('#fileListArea').html(trTag);
      	});

      	trTag = `
				<tr>
					<td colspan="6">
							<hr class="my-0 mb-3 mt-2">
						${resp.paging.pagingHTML}
						<form id="searchForm">
							<div id ="searchUI" class="row g-3 d-flex justify-content-center">
								<input type="hidden" name="page" readonly="readonly"/>
								<input type="hidden" name="listNum" readonly="readonly" value="${resp.paging.listNum}"/>
								<input type="hidden" name="option" readonly="readonly" value="${resp.paging.option}" id="option"/>
								<div class="col-auto">
									<select name="searchType" class="form-select"> 
										<option value="" >전체</option>
										<option value="title" ${simpleCondition.searchType == "title" ? 'selected' : ''} >파일이름</option>
									</select>
								</div>
								<div class="col-auto">
									<input name="searchWord" placeholder="입력하세요" class="form-control" 
										value="${simpleCondition.searchWord != null ? simpleCondition.searchWord :''}"/>
								</div>
								<div class="col-auto">
									<input type="button" value="검색" id="searchBtn" class="btn btn-primary" />
								</div>
							</div>
						</form>
					</td>
				</tr>
				`;
        $('#filePaging').html(trTag);
		}
		
	});
}



 
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




