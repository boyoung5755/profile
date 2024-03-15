/**
 * 프로그램 설명
 * @date        : 2024. 3. 10.
 * @author      : boyoung
 * @version	: 1.0
 * <PRE>
 * ----------------------------
 * 개정이력
 * 2024. 3. 10. boyoung : 최초작성
 * </PRE>
 */







//파일 등록
function fn_galleryUpload(){
	
	var formData = new FormData($('#galleryData')[0]);
	$.ajax({
		type: "post",
		url: "/gallery/upload",
		data: formData,
		contentType: false, // contentType 설정 제거
		processData: false,
		cache: false,
		success: function (data) {
			if(data.success == "Y"){
				alert("업로드 성공!");
				location.href="/gallery";
			}else{
				alert("업로드 실패!@!#!")
			}
		},
		error: function () {
			alert("서버오류");
		}
	});
}