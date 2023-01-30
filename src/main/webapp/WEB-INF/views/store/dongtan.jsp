<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<h1>CAFE JAWA 동탄점</h1>
<div id ="map" style="width:600px; height:400px; left:525px;" ></div>
<div id ="address">주소 : 경기 화성시 메타폴리스로 54</div>
<div id ="number">전화번호 : 1522-3232</div>			
		<script type"text/javascrpit" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b73e3975751cfe6ba538745c6cf799e0&libraries=services"></script>
			
			<script>
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			    mapOption = {
			        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
			        level: 3 // 지도의 확대 레벨
			    };  
			
			// 지도를 생성합니다    
			var map = new kakao.maps.Map(mapContainer, mapOption); 
			
			// 주소-좌표 변환 객체를 생성합니다
			var geocoder = new kakao.maps.services.Geocoder();

			geocoder.addressSearch('경기 화성시 메타폴리스로 54', function(result, status) {
				
			    // 정상적으로 검색이 완료됐으면 
			     if (status === kakao.maps.services.Status.OK) {
			
			        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
			
			        // 결과값으로 받은 위치를 마커로 표시합니다
			        var marker = new kakao.maps.Marker({
			            map: map,
			            position: coords
			        });
			
			        // 인포윈도우로 장소에 대한 설명을 표시합니다
			        var infowindow = new kakao.maps.InfoWindow({
			            content: '<div style="width:150px;text-align:center;padding:6px 0;">CAFE JAWA 동탄점</div>'
			        });
			        infowindow.open(map, marker);
			
			        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			        map.setCenter(coords);
			    } 
			});	
			</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>