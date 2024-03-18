<%--
* [[개정이력(Modification Information)]]
* 수정일       수정자        수정내용
* ----------  ---------  -----------------
* 2024. 3. 13.     boyoung        최초작성
* Copyright (c) 2024 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript" src="https://uicdn.toast.com/tui.code-snippet/v1.5.0/tui-code-snippet.js"></script>
<script type="text/javascript" src="https://uicdn.toast.com/tui.pagination/v3.3.0/tui-pagination.js"></script>
<script src="https://uicdn.toast.com/tui-grid/latest/tui-grid.js"></script>
<script src="/resources/js/app/stack.js"></script>


<div class="container">
	<div id="gridArea">
	</div>

	
	<h4 class="mt-4">파일현황</h4>

	<table class="table table-bordered">
		<thead>
			<tr>
				<th>파일이름</th>
				<th>파일크기</th>
				<th>파일등록일</th>
				<th>파일타입</th>
				<th>파일삭제</th>
			</tr>
		</thead>
		<tbody id="fileListArea">
		</tbody>
		<tfoot id="filePaging">
		</tfoot>
		
	</table>
	
</div>


<script>


// 토스트 그리드 초기화
let createGrid; // 전역 변수로 변경


const initGrid = () => {
	const Grid = tui.Grid;
	
	createGrid = new Grid({ // 여기서 grid를 초기화
		el : document.getElementById('gridArea'),
		scrollX : false,
		scrollY : true,
		pageOptions: {
	        useClient: true,
	        perPage: 5
		},
		columns : [ {
			header : '기술이름',
			name : 'stackName',
			ellipsis: true,
			height : 300,
			width: 80
		}, {
			header : '코드제목',
			name : 'codeTitle',
			ellipsis: true,
			height : 300,
			width: 300
		}, {
			header : '코드내용',
			name : 'codeContent',
			ellipsis: true,
			height : 300,
			width: 600
		}]
	});
	
	Grid.applyTheme('striped', {
	    grid: {
	        border: '#aaa',
	        text: '#333'
	    },
	    cell: {
	        disabled: {
	            text: '#999'
	        }
	    }
	});
	
}

window.onload = () => {
	initGrid();
	fn_stackList();
}

function fn_stackList(){    
    $.ajax({
        url: "/stack/stackList",
        method: "POST",
        dataType: "JSON",
        contentType: "application/json; charset=UTF-8",
        success: function (data) {
            console.dir(data);
            createGrid.resetData(data.stackList);
        },
        error: function () {
            alert("서버오류");
        }
    });
}

</script>
