<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<section class="JAWA_jamsil">
<h1>CAFE JAWA 잠실점</h1>
<div id ="map" style="width:600px; height:400px;" ></div>
<div class ="address">주소 : 서울시 강남구 도곡로 457</div>
<div class ="number">전화번호 : 1522-3232</div>
<div class ="road">
<p>
오시는길 : 대치역 2번 출구에서 한티근린공원방면으로 좌회전 후 직진(546m). 은마아파트입구사거리 좌측에 위치.<br>
　　　　　수인분분당선 한티역 2번 출구에서 직진하여 도보로 5분 거리에 위치.<br>
　　　　　*버스 이용 시 간선버스: 333,340,420,461 은마아파트입구사거리역 하차 지선버스: 4412 은마아파트입구사거리역 하차
</p>
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
			
			    // 정상적으로 검색이 완료됐으면 					ㅈ
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
			</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>