/**
 * 프로그램 설명
 * @date        : 2024. 3. 13.
 * @author      : boyoung
 * @version	: 1.0
 * <PRE>
 * ----------------------------
 * 개정이력
 * 2024. 3. 13. boyoung : 최초작성
 * </PRE>
 */

 
 function fn_addProfileImage(){
	 
	 var formData = new FormData($('#profileImgData')[0]);
	 
	 $.ajax({
			type: "post",
			url: "/info/addImg",
			data: formData,
			contentType: false, // contentType 설정 제거
			processData: false,
			cache: false,
			success: function (data) {
				if(data.success == "Y"){
					alert("프로필이미지등록성공");
					location.href="/common/profile";
				}else{
					alert("등록실패");
				}
			},
			error: function () {
				alert("서버오류");
			}
		});
 }