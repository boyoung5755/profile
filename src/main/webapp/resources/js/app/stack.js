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

 
//스택이름 클릭시 코드제목 출력

function fn_codeTitleList(stackNo){
	
	var stackNo = stackNo;
	
	$.ajax({
		
		type: "get",
		url: "/stack/"+stackNo,
		processData: false,
		contentType: false,
		cache: false,
		success: function (data) {
			var text;
			var icon;
			var jobSn = data.jobSn;
			if (data.success == "Y") {
				text = "일감 등록이 완료되었습니다.";
				icon = "success";
			} else {
				text = "일감 등록이 실패되었습니다.";
				icon = "warning";
			}
			//모달창열기
			fn_swalComplete(text, icon, "/job/"+proSn+"/"+jobSn+"/detail", data.success);
		},
		error: function () {
			fn_swalError();
		}
		
	})
	
	
}