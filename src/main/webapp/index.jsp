<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>


<section id="main-view-container">
	<div class="inner main">
		

			<% if(loginMember == null) { %>	
			<h1>안녕하세요, 카페 자와 입니다.</h1>
			<% } else { %>
			<h1>안녕하세요, 카페 자와 입니다.</h1>
			<p align="center"> <%= loginMember.getMemberName() %> 님의 회원등급은 <%= (loginMember.getOrderCount() > 30) ? "VIP" : (loginMember.getOrderCount() > 10) ? "Gold" : "Silver" %> 입니다 </p>
			<div id ="map" style="width:60%; height:400px; display: none;"></div>
			<% } %>



	</div>		
</section>


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
	// 주소로 좌표를 검색합니다
	geocoder.addressSearch('서울시 강남구 도곡로 457', function(result, status) {
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
				content: '<div style="width:150px;text-align:center;padding:6px 0;">CAFE JAWA 잠실점</div>'
			});
			infowindow.open(map, marker);
			// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			map.setCenter(coords);
		} 
	});
	geocoder.addressSearch('인천 남동구 인하로 556', function(result, status) {
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
				content: '<div style="width:150px;text-align:center;padding:6px 0;">CAFE JAWA 인천점</div>'
			});
			infowindow.open(map, marker);
			// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			map.setCenter(coords);
		} 
	});	
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