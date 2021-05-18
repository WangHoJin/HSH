<template>
  <div class="container">
    <!-- 중앙 left contents start -->
    <div style="height: 80px"></div>
    <div id="demo" class="container carousel slide mb-3" style="height: 400px" data-ride="carousel">
      <!-- Indicators -->
      <ul class="carousel-indicators">
        <li data-target="#demo" data-slide-to="0" class="active"></li>
        <li data-target="#demo" data-slide-to="1"></li>
        <li data-target="#demo" data-slide-to="2"></li>
      </ul>

      <!-- The slideshow -->
      <div class="carousel-inner">
        <div class="carousel-caption">
          <h1 style="font-size: 90px; height: 280px; line-height: 240%" align="center">
            HAPPY HOUSE
          </h1>
        </div>
        <div class="carousel-item active">
          <img
            src="../img/house1.jpg"
            style="
              height: 400px;
              width: 100%;
              object-fit: cover;
              filter: brightness(50%);
              opacity: 80%;
            "
          />
        </div>
        <div class="carousel-item">
          <img
            src="../img/house2.jpg"
            style="
              height: 400px;
              width: 100%;
              object-fit: cover;
              filter: brightness(50%);
              opacity: 80%;
            "
          />
        </div>
        <div class="carousel-item">
          <img
            src="../img/house3.jpg"
            style="
              height: 400px;
              width: 100%;
              object-fit: cover;
              filter: brightness(50%);
              opacity: 80%;
            "
          />
        </div>
        <a class="carousel-control-prev" href="#demo" data-slide="prev">
          <span class="carousel-control-prev-icon"></span>
        </a>
        <a class="carousel-control-next" href="#demo" data-slide="next">
          <span class="carousel-control-next-icon"></span>
        </a>
      </div>

      <!-- Left and right controls -->
    </div>
    <!-- 중앙 contents start -->
    <!-- 지역 선택 bar start-->
    <div class="dark-bg section">
      <div class="container-fluid" style="margin-bottom: 10px">
        <!-- filters start -->
        <div class="sorting-filters text-center mb-20 d-flex justify-content-center">
          <form class="form-inline" id="frm" action="searchRecentHouseDeal.html">
            <!-- <input type="hidden" id="code"  name="code" value="code"/> -->
            <div class="form-group md">
              <select class="form-control mr-2" name="city" id="city">
                <option value="all">도/광역시</option>
                <option value="서울특별시">서울시</option>
                <option value="경기도">경기도</option>
                <option value="인천광역시">인천광역시</option>
                <option value="부산광역시">부산광역시</option>
                <option value="대전광역시">대전광역시</option>
                <option value="대구광역시">대구광역시</option>
                <option value="울산광역시">울산광역시</option>
                <option value="세종특별자치시">세종시</option>
                <option value="광주광역시">광주광역시</option>
                <option value="강원도">강원도</option>
                <option value="충청북도">충청북도</option>
                <option value="경상북도">경상북도</option>
                <option value="경상남도">경상남도</option>
                <option value="전라북도">전라북도</option>
                <option value="전라남도">전라남도</option>
                <option value="제주특별자치도">제주도</option>
              </select>
            </div>
            <div class="form-group mr-2 md-1">
              <select class="form-control" name="dongcode" id="gu">
                <option value="all">시/구/군</option>
                <option value="11320">도봉구</option>
                <option value="11380">은평구</option>
                <option value="11230">동대문구</option>
                <option value="11590">동작구</option>
                <option value="11545">금천구</option>
                <option value="11530">구로구</option>
                <option value="11110">종로구</option>
                <option value="11305">강북구</option>
                <option value="11260">중랑구</option>
                <option value="11680">강남구</option>
                <option value="11500">강서구</option>
                <option value="11140">중구</option>
                <option value="11740">강동구</option>
                <option value="11215">광진구</option>
                <option value="11440">마포구</option>
                <option value="11650">서초구</option>
                <option value="11290">성북구</option>
                <option value="11350">노원구</option>
                <option value="11710">송파구</option>
                <option value="11410">서대문구</option>
                <option value="11470">양천구</option>
                <option value="11560">영등포구</option>
                <option value="11620">관악구</option>
                <option value="11200">성동구</option>
                <option value="11170">용산구</option>
              </select>
            </div>
            <div class="form-group md-1">
              <select class="form-control" name="dong" id="dongs">
                <option value="all">동</option>
                <option value="면목동">면목동</option>
                <option value="상봉동">상봉동</option>
                <option value="중화동">중화동</option>
                <option value="묵동">묵동</option>
                <option value="망우동">망우동</option>
                <option value="신내동">신내동</option>
              </select>
            </div>
            <button
              type="button"
              class="ml-3 btn btn-dark btn-sm mb-10"
              onclick="javascript:mvhouse()"
            >
              search
            </button>
          </form>
        </div>
        <!-- filters end -->
      </div>
    </div>
    <div class="text-center mb-3"></div>
    <div id="map" style="width: 100%; height: 300px; margin: auto"></div>
    <div class="row mt-3">
      <!-- 내집마련 Article Start  -->
      <div class="col-md-4">
        <div class="card">
          <div class="card-header bg-dark text-white">
            <h4>지혜롭게 내집 마련하기</h4>
          </div>
          <div class="card-body">
            <ul class="list-unstyled">
              <li class="mb-2">
                <i class="fa fa-krw text-secondary mr-2" aria-hidden="true"></i> 가용 자금 확인 및
                대출 계획
              </li>
              <li class="mb-2">
                <i class="fa fa-home text-secondary mr-2" aria-hidden="true"></i> 집 종류 및 지역
                선택
              </li>
              <li class="mb-2">
                <i class="fa fa-line-chart text-secondary mr-2" aria-hidden="true"></i> 정보 수집 &
                시세파악
              </li>
              <li class="mb-2">
                <i class="fa fa-search-plus text-secondary mr-2" aria-hidden="true"></i>부동산 방문
                & 집구경
              </li>
              <li class="mb-2">
                <i class="fa fa-pencil text-secondary mr-2" aria-hidden="true"></i> 계약 및 잔금
                치르기
              </li>
              <li class="mb-2">
                <i class="fa fa-file-text-o text-secondary mr-2" aria-hidden="true"></i> 소유권
                이전등기
              </li>
              <li class="mb-2">
                <i class="fa fa-cog text-secondary mr-2" aria-hidden="true"></i> 인테리어 공사
              </li>
            </ul>
          </div>
        </div>
      </div>
      <!-- 내집마련 Article End  -->
      <!-- 오늘의 뉴스 Article Start  -->
      <div class="col-md-4">
        <div class="card">
          <div class="card-header bg-dark text-white">
            <h4>오늘의 뉴스</h4>
          </div>
          <div class="card-body">
            <h5>서울 아파트. 매매,전세 다른 양상...상승..아시아경제</h5>
            <ul>
              <li>
                <a href="" class="small margin-clear" title="KB국민은행, ‘디지털헌금바구니’ 출시"
                  >KB국민은행, ‘디지털헌금바구니’ 출시</a
                >
              </li>
              <li>
                <a
                  href=""
                  class="small margin-clear"
                  title="존슨콘트롤즈, OpenBlue 공개 “135년 쌓아온 빌딩 전문성과 최첨단 기술 결합”"
                  >존슨콘트롤즈, OpenBlue 공개 “135년 쌓아온 빌딩 전문성과 최첨단 기술 결합”</a
                >
              </li>
              <li>
                <a
                  href=""
                  class="small margin-clear"
                  title="산업통상자원부, 업종별 비대면·온라인 수출 기반 본격 가동"
                  >산업통상자원부, 업종별 비대면·온라인 수출 기반 본격 가동</a
                >
              </li>
              <li>
                <a
                  href=""
                  class="small margin-clear"
                  title="장상인 대표, 제6회 INAK사회공헌대상 프레스클럽부문 ‘시민봉사대상’ 수상"
                  >장상인 대표, 제6회 INAK사회공헌대상 프레스클럽부문 ‘시민봉사대상’ 수상</a
                >
              </li>
              <li>
                <a
                  href=""
                  class="small margin-clear"
                  title="삼성전자, 한샘과 공동사업 강화 위한 업무협약 체결"
                  >삼성전자, 한샘과 공동사업 강화 위한 업무협약 체결</a
                >
              </li>
            </ul>
          </div>
        </div>
      </div>
      <!--오늘의 뉴스 Article End-->
      <!--주택관련기사 Article Start-->
      <div class="col-md-4">
        <div class="card">
          <div class="card-header bg-dark text-white">
            <h4>주택 관련 기사</h4>
          </div>
          <div class="card-body">
            <ul class="list-unstyled">
              <li class="nav-item">
                <a class="nav-link" href="" title="신동아건설"> 신동아건설 </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="" title="12.16 대책 후 ‘매수·매도자 모두 일단 관망세’"
                  >12.16 대책 후 ‘매수·매도자 모두 일단 관망세’</a
                >
              </li>
              <li class="nav-item">
                <a class="nav-link" href="" title="구미시, 낙동강 변 국가3산단에 민간공원 조성"
                  >구미시, 낙동강 변 국가3산단에 민간공원 조성</a
                >
              </li>
              <li class="nav-item">
                <a
                  class="nav-link"
                  href=""
                  title="‘은행’과 ‘보험사’의 주택담보대출 조건, 어떻게 다를까?"
                  >‘은행’과 ‘보험사’의 주택담보대출 조건, 어떻게 다를까?</a
                >
              </li>
            </ul>
          </div>
        </div>
      </div>
      <!-- 주택관련기사 Article End  -->
    </div>
  </div>
</template>

<script>
// defer
// src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCGl1i-Ux9jScPt5LxRSkDtkEN0legZ67E&callback=initMap"

export default {
  name: "Home",
  components: {
    // HelloWorld,
  },
};

// var map = null;
// var latitude;
// var longitude;
// var lat = 37.606991;
// var lng = 127.0232185;
// var zoom = 11;

///////////////////////////////////////////////////맵 설정
// function initMap() {
//   //getLocation();
//   var opt = {
//     // google map에 중앙으로 표시할 좌표 설정
//     center: {
//       lat: lat,
//       lng: lng,
//     },
//     zoom: zoom, //0~ 21  큰 값일 수록  zooming
//   };
//   //google.maps.Map(map을 그릴 영역, 옵션정보 );
//   map = new google.maps.Map(document.getElementById("map"), opt);

//   var infowindow = new google.maps.InfoWindow();

//   var marker, i;
//   for (i = 0; i < locations.length; i++) {
//     marker = new google.maps.Marker({
//       id: i,
//       title: locations[i][0],
//       label: locations[i][0],
//       position: new google.maps.LatLng(locations[i][1], locations[i][2]),
//       map: map,
//     });

//     google.maps.event.addListener(
//       marker,
//       "click",
//       (function (marker, i) {
//         return function () {
//           infowindow.setContent(locations[i][0]);
//           infowindow.open(map, marker);
//         };
//       })(marker, i)
//     );
//     if (marker) {
//       marker.addListener("click", function () {
//         console.log(this.title);
//         map.setZoom(15);
//         map.panTo(this.getPosition());
//       });
//     }
//   }
// }

// /// 현재 위치를 최초위치로.
// function showLocation(position) {
//   latitude = position.coords.latitude;
//   longitude = position.coords.longitude;
//   //alert("Latitude : " + latitude + " Longitude: " + longitude);
//   //현재 위치 정보를 center로 지정하기 위해 객체 생성
//   var hear = new google.maps.LatLng(latitude, longitude);
//   map.panTo(hear); //위치 정보를 통해 맵에 표시
// }

// function errorHandler(err) {
//   if (err.code == 1) {
//     alert("Error: Access is denied!");
//   } else if (err.code == 2) {
//     alert("Error: Position is unavailable!");
//   } else if (err.code == 3) {
//     alert("Erro : Time out");
//   }
// }
// function getLocation() {
//   if (navigator.geolocation) {
//     var options = {
//       timeout: 60000,
//       enabledHighAccuracy: true,
//     };
//     navigator.geolocation.getCurrentPosition(showLocation, errorHandler, options);
//   } else {
//     alert("Sorry, browser does not support geolocation!");
//   }
// }
</script>
