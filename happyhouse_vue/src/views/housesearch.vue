<template>
  <div id="search" class="container">
    <GmapMap ref="mapRef" :center="center" :zoom="16" style="width: 100%; height: 300px"></GmapMap>
    <!-- 아파트 거래정보 검색 시작 -->
    <div class="d-flex justify-content-center mb-3 mt-3">
      <div class="dark-bg section">
        <div class="container-fluid" style="margin-bottom: 10px">
          <!-- filters start -->
          <div class="sorting-filters text-center mb-20 d-flex justify-content-center">
            <button type="button" class="btn btn-dark mr-3" id="searchall">전체 조회</button>

            <form class="form-inline ml-3 mr-3" id="frm" method="post" action="housesearch">
              <input type="hidden" name="act" id="act" value="search" />
              <label for="dongsearch" style="display: inline-block; margin-right: 10px"
                >동별 검색</label
              >

              <div class="form-group md">
                <select class="form-control mr-2" id="sido">
                  <option value="0">선택</option>
                </select>
              </div>
              <div class="form-group mr-2 md-1">
                <select class="form-control" id="gugun">
                  <option value="0">선택</option>
                </select>
              </div>
              <div class="form-group md-1">
                <select class="form-control" id="dong">
                  <option value="0">선택</option>
                </select>
              </div>
            </form>

            <form class="form-inline ml-3" id="frm" method="post" action="housesearch">
              <div>
                <input type="hidden" name="act" value="aptsearch" />
                <label for="aptname" style="display: inline-block; margin-right: 10px"
                  >아파트별 검색</label
                >
                <input type="text" id="apttext" />
                <input type="button" id="aptsearch" class="btn btn-dark" value="검색" />
              </div>
            </form>

            <button type="button" class="form-inline ml-3 btn btn-warning" id="searchinterest">
              관심지역
            </button>
          </div>
          <!-- filters end -->
        </div>
      </div>
    </div>
    <!-- 아파트 거래정보 끝 -->

    <!-- 중앙 콘텐츠 시작 -->
    <div data-spy="scroll" data-offset="50">
      <!-- 모든집의정보 받아오기 시작 -->
      <div class="row">
        <div class="col-lg-6">
          <div class="card" style="height: 400px; overflow: auto">
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
                <tbody id="searchResult"></tbody>
              </table>
            </div>
          </div>
        </div>

        <div class="col-lg-6">
          <div class="card" style="height: 400px; overflow: auto">
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
                <tbody id="detailResult"></tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
      <!-- 모든집의정보 받아오기 끝 -->
    </div>
    <!-- 컨테이너 끝 -->
  </div>
</template>

<script>
export default {
  name: "search",
  data() {
    return {
      center: {
        lat: 37.5,
        lng: 127.0324,
      },
    };
  },
};
</script>

<style></style>
