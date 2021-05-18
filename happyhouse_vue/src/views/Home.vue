<template>
  <div class="container">
    <!-- 중앙 left contents start -->

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
  </div>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from "@/components/HelloWorld.vue";

export default {
  name: "Home",
  components: {
    // HelloWorld,
  },
};
</script>
