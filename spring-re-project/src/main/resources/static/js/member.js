$(function() {

	$("#loginForm").submit(function() {
		var id = $("#userId").val();
		var pass = $("#userPass").val();

		if (id.length <= 0) {
			alert("아이디가 입력되지 않았습니다.\n아이디를 입력해주세요");
			$("#userId").focus();
			return false;
		}
		if (pass.length <= 0) {
			alert("비밀번호가 입력되지 않았습니다.\n비밀번호를 입력해주세요");
			$("#userPass").focus();
			return false;
		}

	});
	$("#modalLoginForm").submit(function() {
		var id = $("#modalUserId").val();
		var pass = $("#modalUserPass").val();

		if (id.length <= 0) {
			alert("아이디가 입력되지 않았습니다.\n아이디를 입력해주세요");
			$("#modalUserId").focus();
			return false;
		}
		if (pass.length <= 0) {
			alert("비밀번호가 입력되지 않았습니다.\n비밀번호를 입력해주세요");
			$("#modalUserPass").focus();
			return false;
		}
	});

	$("#id").on("keyup", function() {
		var regExp = /[^A-Za-z0-9]/gi;
		if (regExp.test($(this).val())) {
			alert("영문 대소문자, 숫자만 입력할 수 있습니다.");
			$(this).val($(this).val().replace(regExp, ""));
		}
	});
	$("#pass1").on("keyup", inputCharReplace);
	$("#pass2").on("keyup", inputCharReplace);
	$("#emailId").on("keyup", inputCharReplace);
	$("#emailDomain").on("keyup", inputEmailDomainReplace);

	$("#btnOverlapId").on("click", function() {
		var id = $("#id").val();
		url = "overlapIdCheck?id=" + id;
		if (id.length == 0) {
			alert("아이디를 입력해주세요");
			return false;
		}
		if (id.length < 5) {
			alert("아이디는 5자 이상 입력해주세요.");
			return false;
		}
		window.open(url, "idCheck", "toolbar=no, scrollbars=no, resizeable=no, " + "status=no, memubar=no, width=500, height=330");
	});

	$("#idCheckForm").on("submit", function() {

		var id = $("#checkId").val();

		if (id.length == 0) {
			alert("아이디를 입력해주세요");
			return false;
		}
		if (id.length < 5) {
			alert("아이디는 5자 이상 입력해주세요.");
			return false;
		}
	});

	$("#btnIdCheckColose").on("click", function() {
		var id = $(this).attr("data-id-value");
		opener.document.joinForm.id.value = id;
		opener.document.joinForm.isIdCheck.value = true;
		wiondow.close();
	});

	$("#btnZipcode").click(findZipcode);

	$("#selectDomain").on("change", function() {
		var str = $(this).val();
		if (str == "직접입력") {
			$("#emailDomain").val("");
			$("#emailDomain").prop("readonly", false);
		} else if (str == "네이버") {
			$("#emailDomain").val("naver.com");
			$("#emailDomain").prop("readonly", true);
		} else if (str == "다음") {
			$("#emailDomain").val("daum.net");
			$("#emailDomain").prop("readonly", true);
		} else if (str == "한메일") {
			$("#emailDomain").val("hanmail.net");
			$("#emailDomain").prop("readonly", true);
		} else if (str == "구글") {
			$("#emailDomain").val("gmail.com");
			$("#emailDomain").prop("readonly", true);
		}
	});

	$("#joinForm").on("submit", function() {
		return joinFormCheck();
	});

	function joinFormCheck() {
		var name = $("#name").val();
		var id = $("#id").val();
		var pass1 = $("#pass1").val();
		var pass2 = $("#pass2").val();
		var zipcode = $("#zipcode").val();
		var address1 = $("#address1").val();
		var emailId = $("#emailId").val();
		var emailDomain = $("#emailDomain").val();
		var mobile2 = $("#mobile2").val();
		var mobile3 = $("#mobile2").val();
		var isIdCheck = $("#isIdCheck").val();
		if (name.length == 0) {
			alert("이름이 입력되지 않았습니다.\n이름을 입력해주세요");
			return false;
		}
		if (id.length == 0) {
			alert("아이디가 입력되지 않았습니다.\n아이디를 입력해주세요");
			return false;
		}
		if (isJoinForm && isIdCheck == 'false') {
			alert("아이디 중복 체크를 하지 않았습니다.\n아이디 중복 체크를 해주세요");
			return false;
		}
		if (pass1.length == 0) {
			alert("비밀번호가 입력되지 않았습니다.\n비밀번호를 입력해주세요");
			return false;
		}
		if (pass2.length == 0) {
			alert("비밀번호 확인이 입력되지 않았습니다.\n비밀번호 확인을 입력해주세요");
			return false;
		}
		if (pass1 != pass2) {
			alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
			return false;
		}
		if (zipcode.length == 0) {
			alert("우편번호가 입력되지 않았습니다.\n우편번호를 입력해주세요");
			return false;
		}
		if (address1.length == 0) {
			alert("주소가 입력되지 않았습니다.\n주소를 입력해주세요");
			return false;
		}
		if (emailId.length == 0) {
			alert("이메일 아이디가 입력되지 않았습니다.\n이메일 아이디를 입력해주세요");
			return false;
		}
		if (emailDomain.length == 0) {
			alert("이메일 도메인이 입력되지 않았습니다.\n이메일 도메인을 입력해주세요");
			return false;
		}
		if (mobile2.length == 0 || mobile3.length == 0) {
			alert("휴대폰 번호가 입력되지 않았습니다.\n휴대폰 번호를 입력해주세요");
			return false;
		}
	}

	function findZipcode() {
		new daum.Postcode({
			oncomplete: function(data) {
				var addr = ''; // 주소 변수
				var extraAddr = ''; // 참고 항목 변수
				addr = data.roadAddress;

				if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
					extraAddr += data.bname;
				}

				if (data.buildingName !== '' && data.apartment === 'Y') {
					extraAddr += (extraAddr !== '' ?
						', ' + data.buildingName : data.buildingName);
				}
				if (extraAddr !== '') {
					extraAddr = ' (' + extraAddr + ')';
				}
				addr += extraAddr;
				$("#zipcode").val(data.zonecode);
				$("#address1").val(addr);
			}
		}).open();
	}

}); // end $(function() {}) DOM이 준비되면 끝


function inputCharReplace() {
	// 아래와 같이 정규표현식을 이용해 영문 대소문자, 숫자만 입력되었는지 체크할 수 있다. var regExp = /[^A-Za-z0-9]/gi;
	if (regExp.test($(this).val())) {
		alert("영문 대소문자, 숫자만 입력할 수 있습니다.");
		$(this).val($(this).val().replace(regExp, ""));
	}
}

function inputEmailDomainReplace() {
	var regExp = /[^a-z0-9\.]/gi;
	if (regExp.test($(this).val())) {
		alert("이메일 도메인은 영문 소문자, 숫자, 점(.)만 입력할 수 있습니다.");
		$(this).val($(this).val().replace(regExp, ""));
	}
}
