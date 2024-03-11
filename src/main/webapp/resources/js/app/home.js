
function fn_adminCheck(){
		
		var password = document.querySelector("#adminPassword").value;

		$.ajax({
			type : 'post',
			url : '/admin',
			data : {
				'password' : password
			},
			dataType: 'json',
			success: function (data) {
				alert(data.msg);
				location.reload(true);
			},
			error: function () {
				alert("서버오류 잠시후에 시도하세요.");
			}
		})
	}
	
function fn_logout(){
	$.ajax({
			type : 'post',
			url : '/logout',
			success: function (data) {
				alert(data.msg);
				location.reload(true);
			},
			error: function () {
				alert("서버오류 잠시후에 시도하세요.");
			}
		})
}	
	
