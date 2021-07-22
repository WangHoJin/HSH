<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>HAPPY HOUSE_아파트&주택정보조회페이지</title>
	<!-- 깃아 제발~~~~ -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="shortcut icon" href="img/favicon.ico" />
	<!-- bootstrap을 위한 js & css 등록 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
	<link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
	<!-- Font Awesome icons (free version)-->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
	<link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet"
		type="text/css" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script src="https://use.fontawesome.com/releases/v4.7.0/js/all.js"></script>
	<script type="text/javascript" src="./js/httpRequest.js"></script>
	<script type="text/javascript" src="./js/info.js"></script>
	<script type="text/javascript" src="./js/login.js"></script>
	<script type="text/javascript" src="./js/dongsearch.js"></script>
	<script type="text/javascript" src="./js/show.js"></script>
	<script type="text/javascript" src="./js/housesearch.js"></script>
	<script type="text/javascript">
		$(document).ready(function () {

			var cafeArea = {
				"서울": ["역삼점", "선릉점"],
				"대전": ["학하점", "봉명점"],
				"구미": ["연수원점", "구미사업장"],
				"광주": ["하남산단점", "광주역점"]
			};

			var officePosition = {
				"역삼점": { "lat": 37.500613, "lng": 127.036431 },
				"선릉점": { "lat": 37.504268, "lng": 127.048188 },
				"학하점": { "lat": 36.355360, "lng": 127.298294 },
				"봉명점": { "lat": 36.358843, "lng": 127.344192 },
				"연수원점": { "lat": 36.098594, "lng": 128.389770 },
				"구미사업장": { "lat": 36.109553, "lng": 128.415011 },
				"하남산단점": { "lat": 35.204279, "lng": 126.807198 },
				"광주역점": { "lat": 35.165476, "lng": 126.909216 }
			};

			var officeAddress = {
				"역삼점": "서울특별시+강남구+역삼동+테헤란로+212",
				"선릉점": "서울특별시 강남구 역삼동 테헤란로 334 LG 화재빌딩",
				"학하점": "대전광역시 유성구 덕명동 124",
				"봉명점": "대전광역시 유성구 봉명동 대학로 60 봉명가든 6층",
				"연수원점": "경상북도 구미시 공단동 259",
				"구미사업장": "경상북도 구미시 임수동 94",
				"하남산단점": "107 하남산단6번로 광산구 광주광역시",
				"광주역점": "광주광역시 북구 중흥동 611"
			};

			$(".dropdown-item.cafe_area").click(function () {
				var selArea = $(this).text();
				$("#areaBtn").text(selArea);
				var offices = cafeArea[selArea];
				$("#office_div").empty();
				$.each(offices, function (i, office) {
					$("#office_div").append('<label class="dropdown-item cafe_office">' + office + '</label>');
				});
			});



			//officePosition의 lat, lng를 이용한 marker
			$(document).on("click", ".dropdown-item.cafe_office", function () {
				var selOffice = $(this).text();
				$("#officeBtn").text(selOffice);
				var office = officePosition[selOffice];
				deleteMarkers();
				var officemarker = {
					coords: { lat: office.lat, lng: office.lng },
					iconImage: null,
					content: selOffice
				};
				addMarker(officemarker);
			});

			/*
			//officeAddress의 주소를 이용하여 geocoding으로 lat, lng 얻어온 후 marker
			$(document).on("click", ".dropdown-item.cafe_office", function() {
				var selOffice = $(this).text();
				$("#officeBtn").text(selOffice);
				var officeAddr = officeAddress[selOffice];
				
				$.get("https://maps.googleapis.com/maps/api/geocode/json"
						, {	
							key: '발급받은 key 입력', address: officeAddr 
						} , function(data, status) {
							deleteMarkers();
							var officemarker = {
									coords: { lat: data.results[0].geometry.location.lat, lng: data.results[0].geometry.location.lng },
									iconImage: null,
									content: selOffice
							};
							console.log(officemarker);
							addMarker(officemarker);
						}
						, "json"
				);//get
			});
			*/
		});
		
		
	</script>
</head>

<body>
	<!-- 상단 Header Start  -->
	<%@ include file="/WEB-INF/views/header.jsp" %>
	<!-- 상단 Header End  -->

	<div class="container">
		<div style="height: 110px;"></div>


		<div id="map" style="width: 100%; height: 300px; margin: auto;"></div>
		<script defer
			src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCGl1i-Ux9jScPt5LxRSkDtkEN0legZ67E&callback=initMap"></script>
		<script>
			var multi = { lat: 37.5012743, lng: 127.039585 };
			var map;
			var markers = [];
			var infoWindow;

			function initMap() {
				map = new google.maps.Map(document.getElementById('map'), {
					center: multi, zoom: 15
				});

				infoWindow = new google.maps.InfoWindow;

				const myposition = "img/my_position.png";


				//멀티캠퍼스
				//const multimarker = new google.maps.Marker({coords: multi, content: '멀티캠퍼스', iconImage: myposition});

				var multimarker = {
					coords: multi,
					iconImage: myposition,
					content: '멀티캠퍼스(역삼)'
				};
				addMarker(multimarker);
				infoWindow.setPosition(multi);
				infoWindow.setContent('멀티캠퍼스.');
				infoWindow.open(map);

				/*
				// Geolocation
				infoWindow = new google.maps.InfoWindow;
				if (navigator.geolocation) {
					navigator.geolocation.getCurrentPosition(function(position) {
						var pos = {
							lat: position.coords.latitude,
							lng: position.coords.longitude
						};
						
						var mymarker = {
								coords: pos,
								iconImage: myposition,
								content: '현재위치'
						};
						addMarker(mymarker);
						
						infoWindow.setPosition(pos);
						infoWindow.setContent('현재 나의 위치.');
						infoWindow.open(map);
						map.setCenter(pos);
					}, function() {
						handleLocationError(true, infoWindow, map.getCenter());
					});
				} else {
					handleLocationError(false, infoWindow, map.getCenter());
				}
				*/
			}


			function handleLocationError(browserHasGeolocation, infoWindow, pos) {
				infoWindow.setPosition(pos);
				infoWindow.setContent(browserHasGeolocation ?
					'Error: Geolocation 서비스 실패.' :
					'Error: Geolocation을 지원하지 않는 브라우저.');
				infoWindow.open(map);
			}

			function addMarker(props) {
				const marker = new google.maps.Marker({
					position: new google.maps.LatLng(parseFloat(props.coords.lat), parseFloat(props.coords.lng)),
					map: map
				});
				map.setCenter(marker.getPosition());
				map.setZoom(15);

				if (props.iconImage) {
					marker.setIcon(props.iconImage);
				}

				if (props.content) {
					infoWindow = new google.maps.InfoWindow({
						content: props.content
					});

				}

				marker.addListener('click', function () {
					map.setZoom(17);
					map.setCenter(marker.getPosition());
					bounceMarker(marker);
				});
				markers.push(marker);
				setMapOnAll(map);
			}

			function bounceMarker(marker) {
				if (marker.getAnimation() !== null) {
					marker.setAnimation(null);
				} else {
					marker.setAnimation(google.maps.Animation.BOUNCE);
				}
			}

			function deleteMarkers() {
				clearMarkers();
				markers = [];
			}

			function clearMarkers() {
				setMapOnAll(null);
			}

			function setMapOnAll(map) {
				for (let i = 0; i < markers.length; i++) {
					markers[i].setMap(map);
				}
			}
		</script>
		<!-- Google Map end -->

		<!-- 아파트 거래정보 검색 시작 -->
		<div class="d-flex justify-content-center mb-3 mt-3">
			
			 <div class="dark-bg section">
            <div class="container-fluid" style="margin-bottom:10px">
                 <!-- filters start -->
            <div class="sorting-filters text-center mb-20 d-flex justify-content-center">
            <button type="button" class="btn btn-dark mr-3" id="searchall">전체 조회</button>
           
            <form class="form-inline ml-3 mr-3" id="frm" method="post" action="housesearch">
                     <input type="hidden" name="act" id="act" value="search">
                   	<label for="dongsearch" style="display:inline-block; margin-right:10px">동별 검색</label>
                     
                     <div class="form-group md">
                       <select class="form-control mr-2" id="sido">
                         <option value="0">선택</option>
                       </select>
                     </div>
                     <div class="form-group mr-2 md-1">
                       <select class="form-control"  id="gugun">
                       <option value="0">선택</option>
                       </select>
                     </div>
                     <div class="form-group md-1">
                       <select class="form-control"  id="dong">
                           <option value="0">선택</option>
                        </select>
                     </div>
                   </form>
                   
                   
                   <form class="form-inline ml-3" id="frm" method="post" action="housesearch">
                   	<div >
                   		<input type="hidden" name="act" value="aptsearch">
                   		<label for="aptname" style="display:inline-block; margin-right:10px">아파트별 검색</label>
                   		<input type="text" id="apttext">
	                    <input type="button" id="aptsearch" class="btn btn-dark" value="검색">           	
                   	</div>
                   </form>

                  <button type="button" class="form-inline ml-3 btn btn-warning" id="searchinterest">관심지역</button>
                 </div>
                 <!-- filters end -->
               </div>
             </div>
			
		</div>
		<!-- 아파트 거래정보 끝 -->
		<!-- 중앙 콘텐츠 시작 -->
		<div data-spy="scroll" data-offset="50">

			<!-- 모든집의정보 받아오기 시작 -->
			<div class="row" >
				<div class="col-lg-6">
					<div class="card" style="height:400px; overflow:auto">
						<div class="card-header bg-dark text-white">
							<h4>아파트 거래 정보</h4>
						</div>
						<div class="card-body" style="padding:0;'">
							<table id="houseinfo-list" class="table table-striped">
								<thead>
									<tr>
										<th>법정동</th>
										<th>아파트명</th>
										<th>건축년도</th>
									</tr>
								</thead>
								<tbody id="searchResult">
								</tbody>
							</table>
						</div>
					</div>
				</div>
				
				<div class="col-lg-6">
					<div class="card" style="height:400px; overflow:auto">
						<div class="card-header bg-dark text-white">
							<h4>아파트 거래 상세 정보</h4>
						</div>
						<div class="card-body" style="padding:0;'">
							<table id="houseinfo-list" class="table table-striped">
								<thead>
									<tr>
										<th>법정동</th>
										<th>아파트명</th>
										<th>거래가격</th>
										<th>거래날짜</th>
										<th>전용면적</th>
									</tr>
								</thead>
								<tbody id="detailResult">
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<!-- 모든집의정보 받아오기 끝 -->


		</div>


		<!-- 컨테이너 끝 -->
	</div>
	<!-- 중앙 contents end -->

	<!-- 회원가입 modal 시작 -->

	<div id="voteModal" class="modal fade">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">

				<div class="modal-header">
					<h4 class="modal-title">회원 가입</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<div class="modal-body">
					<form action="">

						<div class="form-group ">
							<label for="usr">아이디*</label>
							<input type="text" class="form-control" id="id" name="answer">
						</div>
						<div class="form-group">
							<label for="pwd">비밀번호*</label>
							<input type="password" class="form-control" id="pwd" placeholder="영문 숫자 포함 6자리 이상"
								name="answer">
						</div>
						<div class="form-group ">
							<label for="usr">이름*</label>
							<input type="text" class="form-control" id="name" placeholder="User Name" name="answer">
						</div>
						<div class="form-group ">
							<label for="usr">주소*</label>
							<input type="text" class="form-control" id="addr" placeholder="address" name="answer">
						</div>
						<div class="form-group ">
							<label for="usr">전화번호*</label>
							<input type="tel" class="form-control" id="tel" placeholder="010-xxxx-xxxx" name="answer">
						</div>
					</form>
				</div>
				<div class="modal-footer justify-content-center">
					<button type="button" class="btn btn-warning" data-dismiss="modal"
						onclick="javascript:sign();">등록</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 회원가입 modal 끝 -->
	<!-- 회원정보 조회 modal 시작-->
	<div id="searchModal" class="modal fade">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">

				<div class="modal-header">
					<h4 class="modal-title">회원 정보 확인</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>

				</div>

				<div class="modal-body">
					<div id="search-list"> </div>
				</div>
				<div class="modal-footer justify-content-center">
					<button type="button" class="btn btn-info" data-dismiss="modal" onclick="">확인</button>
					<button type="button" class="btn btn-info" data-dismiss="modal" id="modify" data-toggle="modal"
						data-target="#modifyModal">수정</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal" id="delete">삭제</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 회원정보 조회 modal 끝 -->

	<!-- 로그인 Modal -->
	<div class="modal" id="loginModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Login</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<form action="/action_page.php" class="needs-validation border rounded p-3" novalidate>
						<div class="form-group">
							<label for="uname">ID:</label>
							<input type="text" class="form-control" id="mID" placeholder="Enter ID" name="uID" required>
							<div class="valid-feedback">Valid.</div>
							<div class="invalid-feedback">Please fill out this field.</div>
						</div>
						<div class="form-group">
							<label for="pwd">Password:</label>
							<input type="password" class="form-control" id="mpwd" placeholder="Enter Password"
								name="pswd" required>
							<div class="valid-feedback">Valid.</div>
							<div class="invalid-feedback">Please fill out this field.</div>
						</div>
					</form>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" id="mlogin" class="btn btn-primary" data-dismiss="modal">Login</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>
	

	<footer id="footer" class="clearfix ">

		<footer class="footer text-center pt-5 pb-2">
			<div class="container">
				<div class="row">
					<!-- Footer Location-->
					<div class="col-lg-4 mb-5 mb-lg-0">
						<h4 class="text-uppercase mb-4">Location</h4>
						<p class="lead mb-0">
							(SSAFY)
							<br />
							서울시 강남구 테헤란로 멀티스퀘어
						</p>
					</div>
					<!-- Footer Social Icons-->
					<div class="col-lg-4 mb-5 mb-lg-0">
						<h4 class="text-uppercase mb-4">Around the Web</h4>
						<a class="btn btn-outline-dark btn-social mx-1" href="#!"><i class="fa fa-facebook"
								aria-hidden="true"></i></a>
						<a class="btn btn-outline-dark btn-social mx-1" href="#!"><i class="fa fa-twitter"
								aria-hidden="true"></i></a>
						<a class="btn btn-outline-dark btn-social mx-1" href="#!"><i class="fa fa-instagram"
								aria-hidden="true"></i></a>
						<a class="btn btn-outline-dark btn-social mx-1" href="#!"><i class="fa fa-dribbble"
								aria-hidden="true"></i></i></a>
					</div>
					<!-- Footer About Text-->
					<div class="col-lg-4">
						<h4 class="text-uppercase mb-4">Contact Us</h4>
						<p class="lead mb-0">
							1544-9001</br>
							admin@ssafy.com
						</p>
					</div>
				</div>
			</div>
		</footer>
		<!-- Copyright Section-->
		<div class="copyright py-4 text-center text-secondary ">
			<div class="container"><small>Copyright © Your Website 2021</small></div>
		</div>
	</footer>
<script type="text/javascript">
let colorArr = ['table-muted','table-light'];
$(document).ready(function() {

	$("#searchall").click(function() {
	 	// 모든 목록 출력하기
		$.get("${root}/housesearch2/houseall"
			,function(data, status){
				$("#searchResult").empty();
				$.each(data, function(index, apt) {
					let str = "<tr class="+colorArr[index%2]+">"
					+ "<td>" + apt.dong + "</td>"
					+ "<td>" + apt.aptName + "</td>"
					+ "<td>" + apt.buildYear + "</td>"
					$("#searchResult").append(str);
					});//each
			}//function
			, "json"
	);//get
	});
	 	
	//---------------------------------------------------------------------
		$("#searchinterest").click(function() {
	 	// 관심지역 목록 출력하기
	 	let userid = '${userinfo.userid}';
			$.get("${root}/housesearch2/interest/"+userid
					,function(data, status){
						$("#searchResult").empty();
						$.each(data, function(index, apt) {
							let str = "<tr class="+colorArr[index%2]+">"
							+ "<td>" + apt.dong + "</td>"
							+ "<td>" + apt.aptName + "</td>"
							+ "<td>" + apt.buildYear + "</td>"
							$("#searchResult").append(str);
							});//each
					}//function
					, "json"
			);//get
			
			$.get("${root}/housesearch2/dealinterest/"+userid
					, function(data, status) {
						$("#detailResult").empty();
						$.each(data, function(index, deal) {
							let str = "<tr class="+colorArr[index%2]+">"
							+ "<td>" + deal.dong + "</td>"
							+ "<td>" + deal.aptName + "</td>"
							+ "<td>" + deal.dealAmount + "</td>"
							+ "<td>" + deal.dealYear + "." + deal.dealMonth + "." + deal.dealDay + "</td>"
							+ "<td>" + deal.area + "</td>"
							+ "</tr>";
							$("#detailResult").append(str);
						});//each
					} // function
					, "json"
				); // get
			});
	//---------------------------------------------------------------------

	// 시/도 채워넣기
	$.get("${root}/housesearch2/house/"
		,function(data, status){
			$.each(data, function(index, vo) {
				console.log(vo.sido_code);
				$("#sido").append("<option value='"+vo.sido_code+"'>"+vo.sido_name+"</option>");
			});//each
		}//function
		, "json"
	);//get

	// 구/군 채워넣기
	$("#sido").change(function() {
		let sido = $("#sido option:selected").val();
		$.get("${root}/housesearch2/house/"+sido
				,function(data, status){
					$("#gugun").empty();
					$("#gugun").append('<option value="0">선택</option>');
					$.each(data, function(index, vo) {
						$("#gugun").append("<option value='"+vo.gugun_code+"'>"+vo.gugun_name+"</option>");
					});//each
				}//function
				, "json"
		);//get
	});//change
	
	// 동 채워넣기
	$("#gugun").change(function() {
		let sido = $("#sido option:selected").val();
		let gugun = $("#gugun option:selected").val();
		$.get("${root}/housesearch2/house/"+sido+"/"+gugun
				,function(data, status){
					$("#dong").empty();
					$("#dong").append('<option value="0">선택</option>');
					$.each(data, function(index, vo) {
						$("#dong").append("<option value='"+vo.dong+"'>"+vo.dong+"</option>");
					});//each
				}//function
				, "json"
		);//get
	});//change
	
	// 테이블에 반영하기
	$("#dong").change(function() {
		let sido = $("#sido option:selected").val();
		let gugun = $("#gugun option:selected").val();
		let dong = $("#dong option:selected").val();
		/* 기본 검색 */
		$.get("${root}/housesearch2/house/"+sido+"/"+gugun+"/"+dong
			,function(data, status){
				$("#searchResult").empty();
				$.each(data, function(index, apt) {
					let str = "<tr class="+colorArr[index%2]+">"
					+ "<td>" + apt.dong + "</td>"
					+ "<td>" + apt.aptName + "</td>"
					+ "<td>" + apt.buildYear + "</td>"
					+ "</tr>";
					$("#searchResult").append(str);
				});//each
			}//function
			, "json"
		);//get
		
		/* 상세 검색 */			
		$.get("${root}/housesearch2/housedeal/"+sido+"/"+gugun+"/"+dong
			, function(data, status) {
				$("#detailResult").empty();
				$.each(data, function(index, deal) {
					let str = "<tr class="+colorArr[index%2]+">"
					+ "<td>" + deal.dong + "</td>"
					+ "<td>" + deal.aptName + "</td>"
					+ "<td>" + deal.dealAmount + "</td>"
					+ "<td>" + deal.dealYear + "." + deal.dealMonth + "." + deal.dealDay + "</td>"
					+ "<td>" + deal.area + "</td>"
					+ "</tr>";
					$("#detailResult").append(str);
				});//each
			} // function
			, "json"
		); // get
	});//change
	
	$("#aptsearch").click(function() {
		/* 기본 검색 */
		let aptname=$("#apttext").val();
		console.log("아파트검색");
		console.log(aptname);
		$.get("${root}/housesearch/aptofname/"+aptname
			, function(data, status) {
				$("#searchResult").empty();
				$.each(data, function(index, apt) {
					let str = "<tr class="+colorArr[index%2]+">"
					+ "<td>" + apt.dong + "</td>"
					+ "<td>" + apt.aptName + "</td>"
					+ "<td>" + apt.buildYear + "</td>"
					+ "</tr>";
					$("#searchResult").append(str);
				});//each
			} // function
			, "json"
		); // get
		
		/* 상세 검색 */			
		$.get("${root}/housesearch/dealofaptname/"+aptname
			, function(data, status) {
				$("#apttext").val("");
				$("#detailResult").empty();
				$.each(data, function(index, deal) {
					let str = "<tr class="+colorArr[index%2]+">"
					+ "<td>" + deal.dong + "</td>"
					+ "<td>" + deal.aptName + "</td>"
					+ "<td>" + deal.dealAmount + "</td>"
					+ "<td>" + deal.dealYear + "." + deal.dealMonth + "." + deal.dealDay + "</td>"
					+ "<td>" + deal.area + "</td>"
					+ "</tr>";
					$("#detailResult").append(str);
				});//each
			} // function
			, "json"
		); // get
	});
	
});
</script>
</body>
</html>