function chkPw() {
	var frm = document.querySelector('#frm');
	if(frm.current_pw.value == '') {
		alert('기존 비밀번호를 작성해 주세요.');
		frm.current_pw.focus();
		return false;
	} else if(frm.user_pw.value == '') {
		alert('변경 비밀번호를 작성해 주세요.');
		frm.user_pw.focus();
		return false;
	} else if(frm.user_pw.value != frm.chk_user_pw.value) {
		alert('변경/확인 비밀번호를 확인해 주세요.');
		frm.user_pw.focus();
		return false;
	}
}

//인증메일 받기
function clkFindPwBtn () {
	var user_id = document.querySelector('#findPwUserId').value

	ajax()
	
	function ajax () {
		fetch(`/user/findPwProc?user_id=${user_id}`)
		.then(res => res.json())
		.then(res => {
			if(res.result === 1) {
				//타이머 시작!
				startTimer()
			} else {
				alert('인증 메일 발송을 실패하였습니다')
			}
		})
	}
	
	//타이머 작동
	function startTimer () {
		var countDownTimeElem = document.querySelector('#countDownTime')
		var totalSec = 300
		
		var interval = setInterval(function() {
			var min = parseInt(totalSec / 60)
			var sec = totalSec - (min * 60)
			var result = `남은시간 ${create2Seat(min)}:${create2Seat(sec)}`
			countDownTimeElem.innerText = result
			
			if(totalSec == 0) {
				clearInterval(interval)
			}
			totalSec--			
		}, 1000)
	}
	
	//무조건 2자리 숫자 만들기  
	function create2Seat(p) {
		var val = '0' + p
		return val.substr(val.length - 2, 2)
		//return p.length === 1 ? `0${p}` : `${p}`
	}
}

//비밀번호 찾기(변경)
var findPwAuthFrmElem = document.querySelector('#findPwAuthFrm')
if(findPwAuthFrmElem) {
	var btnSendElem = findPwAuthFrmElem.btnSend
	var userPwElem = findPwAuthFrmElem.user_pw
	var userChkPwElem = findPwAuthFrmElem.chk_user_pw
	
	var userIdVal = findPwAuthFrmElem.user_id.value
	var cdVal = findPwAuthFrmElem.cd.value

	btnSendElem.addEventListener('click', function() {
		if(userPwElem.value !== userChkPwElem.value) {
			alert('비밀번호를 확인해 주세요')
			return
		}
		ajax()
	})	 

	function ajax () {
		var param = {
			user_id: userIdVal,
			cd: cdVal,
			user_pw: userPwElem.value		
		}
		
		fetch('/user/findPwAuth', { 
			method: 'post',
			headers: {
            	'Content-Type': 'application/json'
			},
			body: JSON.stringify(param)
		}).then(res => res.json())
		.then(myJson => {
			proc(myJson)
		})
	}
	
	function proc (res) {
		switch(res.result) {
			case 0:
				alert('비밀번호 변경에 실패하였습니다.')
			return
			case 1:
				alert('비밀번호를 변경하였습니다.')
				location.href='/user/login'
			return
			case 2:
				alert('인증시간이 초과하였습니다.')	
		}
	}
}







