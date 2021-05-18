<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- bootstrap을 위한 js & css 등록 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	// 회원 탈퇴.
	$("#delete").on("click", function() {
		if(confirm("정말 삭제?")) {
			$(location).attr('href', "<c:url value='/member/delete' />");
		}
	});
})
</script>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-light fixed-top shadow">
		<div class="container">
			<a class="navbar-brand" href="${root}/">
				<img src="${root}/img/logo.jpg" alt="Logo" style="width:100px;">
			</a>
			<button class="navbar-toggler navbar-toggler-right bg-secondary text-white" type="button"
				data-toggle="collapse" data-target="#navb">
				<span class="navbar-toggler-icon">-</span>
			</button>
			<div id="navb" class="collapse navbar-collapse">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item">
						<a class="nav-link text-secondary" href="#">공지사항</a>
					</li>
					<li class="nav-item ">
						<a class="nav-link text-secondary" href="#">오늘의 뉴스</a>
					</li>
					<!-- 로그인 후 -->
					<c:if test="${userinfo ne null}">
					<li class="nav-item menu_confirm_on" >
						<a class="nav-link text-secondary" href="#InterestArea" data-toggle="modal" id="interest">관심 지역 추가</a>
					</li>
					
					</c:if>
				</ul>


				<ul id="header_nav_confirm_off" class="navbar-nav justify-content-end">
				<!-- 로그인 전 -->
				<c:if test="${userinfo eq null}">	
					<li class="nav-item">
						<a class="nav-link text-secondary" href="#" id="login" data-toggle="modal"
							data-target="#loginModal">로그인</a>
					</li>
					<li class="nav-item">
						<a class="nav-link text-secondary" href="#voteModal" data-toggle="modal">회원가입</a>
					</li>
				</c:if>	
				</ul>
					<!-- 로그인 후 -->
					<c:if test="${userinfo ne null}">
				<ul id="header_nav_confirm_on" class="navbar-nav justify-content-end" ">
					<li class="nav-item">
						<a class="nav-link text-secondary">${userinfo.username}님 환영합니다!</a>
					</li>
					<li class="nav-item">
						<a class="nav-link text-secondary" href="${root}/member/logout" id="logout">로그아웃</a>
					</li>
					<li class="nav-item">
						<a class="nav-link text-secondary" href="#searchModal" data-toggle="modal" id="view">회원정보</a>
					</li>
				</ul>
					</c:if>

			</div>
		</div>
	</nav>
	
	<!-- 회원가입 modal 시작 -->

		<div id="voteModal" class="modal fade">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">

					<div class="modal-header">
						<h4 class="modal-title">회원 가입</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<div class="modal-body">
						<form method="post" action="${root}/member/register">
							<input type="hidden" name="act" id="act" value="join">
							<div class="form-group ">
								<label for="usr">아이디*</label> <input type="text"
									class="form-control" id="id" name="userid">
							</div>
							<div class="form-group">
								<label for="pwd">비밀번호*</label> <input type="password"
									class="form-control" id="pwd" placeholder="영문 숫자 포함 6자리 이상"
									name="userpwd">
							</div>
							<div class="form-group ">
								<label for="usr">이름*</label> <input type="text"
									class="form-control" id="name" placeholder="User Name"
									name="username">
							</div>
							<div class="form-group ">
								<label for="usr">주소*</label> <input type="text"
									class="form-control" id="addr" placeholder="address"
									name="zipcode">
							</div>
							<div class="form-group ">
								<label for="usr">전화번호*</label> <input type="tel"
									class="form-control" id="tel" placeholder="010-xxxx-xxxx"
									name="usertel">
							</div>
							<input type="submit" class="btn btn-warning" value="등록">
						</form>
					</div>
					<div class="modal-footer justify-content-center">

						<!--  <button type="button" class="btn btn-warning" data-dismiss="modal"
							onclick="javascript:sign();">등록</button>-->
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
						<div class="row">
			            	  <div  class="col-3 d-inline text-right">아이디*</div>
			            	  <div class="col-9 d-inline ">${userinfo.userid}</div>
			              </div>
			               <div class="row">
			            	  <div  class="col-3 d-inline text-right">이름*</div>
			            	  <div class="col-9 d-inline ">${userinfo.username}</div>
			              </div>
			               <div class="row">
			            	  <div  class="col-3 d-inline text-right">주소*</div>
			            	  <div class="col-9 d-inline ">${userinfo.address}</div>
			              </div>
			              <div class="row">
			            	  <div  class="col-3 d-inline text-right">전화번호*</div>
			            	  <div class="col-9 d-inline ">${userinfo.phone}</div>
			              </div>

					</div>
					<div class="modal-footer justify-content-center">
						<button type="button" class="btn btn-info" data-dismiss="modal"
							onclick="">확인</button>
						<button type="button" class="btn btn-info" data-dismiss="modal"
							id="modify" data-toggle="modal" data-target="#modifyModal">수정</button>
						<button type="button" class="btn btn-danger" data-dismiss="modal"
							id="delete" onclick="">탈퇴</button>
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
						<form method="post" action="${root}/member/login"
							class="needs-validation border rounded p-3">
							<input type="hidden" name="act" id="act" value="login">
							<div class="form-group">
								<label for="uname">ID:</label> <input type="text"
									class="form-control" id="mID" placeholder="Enter ID"
									name="userid" required>
								<div class="valid-feedback">Valid.</div>
								<div class="invalid-feedback">Please fill out this field.</div>
							</div>
							<div class="form-group">
								<label for="pwd">Password:</label> <input type="password"
									class="form-control" id="mpwd" placeholder="Enter Password"
									name="userpwd" required>
								<div class="valid-feedback">Valid.</div>
								<div class="invalid-feedback">Please fill out this field.</div>
							</div>
							<!-- Modal footer -->
							<div class="modal-footer">

								<input type="submit" class="btn btn-primary" value="Login">
								<button type="button" class="btn btn-danger"
									data-dismiss="modal">Close</button>
							</div>
						</form>
					</div>


				</div>
			</div>
		</div>
		<!-- 로그인 Modal 끝-->
		

		
		<!-- 회원 정보 수정 modal 시작 -->

    <div id="modifyModal" class="modal fade">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">

                <div class="modal-header">
                    <h4 class="modal-title">회원 정보 수정</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <div class="modal-body">
                    <form method="post" action="${root }/member/modify">
                        <input type="hidden" name="act" id="act" value="modify">
                        <div class="form-group ">
                            <label for="usr">아이디* (아이디는 변경할 수 없습니다)</label> <input type="text"
                                class="form-control" id="id" name="modify_userid" value="${userinfo.userid}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="pwd">비밀번호* (영문 숫자 포함 6자리 이상)</label> <input type="password"
                                class="form-control" id="pwd" value="${userinfo.userpwd }"
                                name="modify_userpwd">
                        </div>
                        <div class="form-group ">
                            <label for="usr">이름*</label> <input type="text"
                                class="form-control" id="name" value="${userinfo.username}"
                                name="modify_username">
                        </div>
                        <div class="form-group ">
                            <label for="usr">주소*</label> <input type="text"
                                class="form-control" id="addr" value="${userinfo.address}"
                                name="modify_zipcode">
                        </div>
                        <div class="form-group ">
                            <label for="usr">전화번호*</label> <input type="tel"
                                class="form-control" id="tel" value="${userinfo.phone}"
                                name="modify_usertel">
                        </div>
                        <input type="submit" class="btn btn-warning" value="수정">
                    </form>
                </div>
                <div class="modal-footer justify-content-center">

                    <!--  <button type="button" class="btn btn-warning" data-dismiss="modal"
                        onclick="javascript:sign();">등록</button>-->
                </div>
            </div>
        </div>
    </div>
    <!-- 회원 정보 수정 modal 끝 -->
</body>
</html>