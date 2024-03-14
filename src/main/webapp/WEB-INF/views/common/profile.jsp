<%--
* [[개정이력(Modification Information)]]
* 수정일       수정자        수정내용
* ----------  ---------  -----------------
* 2024. 3. 10. boyoung        최초작성
* Copyright (c) 2024 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://cdn.jsdelivr.net/npm/echarts@5.5.0/dist/echarts.min.js"></script>
<script src="/resources/js/app/profile.js"></script>

<style>
	body {
	    font-family: 'Noto Sans KR', sans-serif;
	    text-align: center;
	    margin: 0;
	    padding: 0;
	    background-color: #f0f2f5;
	}    

    .profile-container {
        width: 100%;
        max-width: 400px;
        margin: 20px auto;
        background-color: #fff;
        padding: 20px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    .profile-photo {
        width: 300px;
        height: 300px;
        border-radius: 50%;
        margin: 0 auto;
        background-size: cover;
        background-position: center;
    }
    .info {
        margin-top: 20px;
        text-align: left;
    }
    .info p {
        margin: 5px 0;
        color: #333;
    }
</style>
    
    <div class="profile-container">
	    <div class="profile-photo" style="background-image: url('/image/profileShow');">
	        <!-- 프로필 사진 -->
	    </div>
	    <c:if test="${ sessionScope.role eq 'admin'}">
	    	<form id="profileImgData" enctype="multipart/form-data">
	    		<input type="hidden" value="${rndStr}" id="fileCode" name="fileCode" /> 
	    		<input type="file" name="profileFile"accept="image/gif,image/jpeg,image/png" />
				<button type="button" class="btn btn-success mt-3" onclick="fn_addProfileImage()">이미지등록</button>
	    	</form>
			<hr class="mb-3">
		</c:if>
	    <div class="info">
	        <p><strong>이름:</strong> 김보영</p>
	        <p><strong>이메일:</strong> qhdud0110@gmail.com</p>
	        <p><strong>소개:</strong> 안녕하세요, 저는 김보영입니다.</p>
	        <!-- 추가 정보 -->
			<hr class="mb-3">
	    </div>
	    <div class="info">
	    	<p><strong>나의 기술</strong></p>
	    	
	    	<c:if test="${ sessionScope.role eq 'admin'}">
				<button type="button" class="btn btn-success mt-3 " onclick="window.location.href='/stack/manage'">기술관리</button>
				<hr class="mb-3">
			</c:if>
			<ul>
                <c:forEach items="${stackList}" var="sList">
                    <c:if test="${not empty sList}">
                        <li onclick="window.location.href='/stack'">${sList.stackName}</li>
                    </c:if>
                </c:forEach>
            </ul>
	    </div>
		<div>
			 <p><strong>기술분포</strong></p>
			<div id="main" style="width: 600px;height:400px;"></div>
		</div>
		
	</div>
	
	<script type="text/javascript">
	
	
	function fn_getChartData(){
	      
	      $.ajax({
	         type: "post",
	         url: "/info/getChart",
	         contentType : 'application/json; charset=utf-8',
	         dataType:"JSON",
	         success: function (data) {
	         let dataList = [];
	         
	         for(let row of data.chart){
	            let obj = {};
	            obj.name = row.name;
	            obj.value = row.value;
	            dataList.push(obj);
	         }
	         
	            fn_initChart(dataList)
	         },
	         error: function () {
	            alert("서버오류");
	         }
	      })
	   }
	
	
	$(function(){
		fn_getChartData();
	})
	
   function fn_initChart(dataList){
     // Initialize the echarts instance based on the prepared dom
      var myChart = echarts.init(document.getElementById('main'));

      // Specify the configuration items and data for the chart
      option = {
        legend: {
          orient: 'vertical',
          x: 'left',
        },
        series: [
          {
            type: 'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
              show: false,
              position: 'center'
            },
            labelLine: {
              show: false
            },
            emphasis: {
              label: {
                show: true,
                fontSize: '30',
                fontWeight: 'bold'
              }
            },
            data: dataList
          }
        ]
      };

      // Display the chart using the configuration items and data just specified.
      myChart.setOption(option);
   }

    </script>
	

	
    
    