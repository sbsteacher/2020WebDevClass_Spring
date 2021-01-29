'use strict'

//글 제목 클릭
function clkArticle(i_board) {		
	var url = `/board/detail?i_board=${i_board}`;
	location.href = url; //주소값 이동
}

//삭제 버튼 클릭
function clkDel(i_board, typ) {
	if(confirm('삭제 하시겠습니까?')) {
		fetch(`/board/del/${i_board}`, {
			method: 'delete'
		})
		.then(function(res) {			
			return res.json();
		}).then(function(myJson) {
		    console.log(myJson);
			if(myJson.result === 1) { //삭제 완료
				location.href = `/board/list?typ=${typ}`;
			} else { //삭제 실패
				alert("삭제 실패하였습니다.");
			}
		});
	}
}

//지금은 사용 X, 혹시나 나중에 욕이 있는지 체크하는 용도로 사용
function chk() {
	var frm = document.querySelector('#frm');
	if(chkEmptyEle(frm.title, '제목') || chkEmptyEle(frm.ctnt, '내용')){
		return false;
	}
}

//댓글에서 수정버튼 클릭 > 수정FORM 나타나게 처리
function clkCmtMod(i_cmt) {
	var trForm = document.querySelector('#mod_' + i_cmt);
	trForm.classList.remove('cmd_mod_form');
	console.log(trForm);
}

function clkCmtClose(i_cmt) {
	var trForm = document.querySelector('#mod_' + i_cmt);
	trForm.classList.add('cmd_mod_form');
}

function toggleFavorite (i_board) {
	var fc = document.querySelector('#favoriteContainer');
	var state = fc.getAttribute('is_favorite'); //1: 좋아요, 0:안 좋아요	
	var state = 1 - state;
	
	axios.get('/board/ajaxFavorite.korea', {
		params: {
			'state': state,
			'i_board': i_board
		}	
	}).then(function (res) { //통신 성공
		console.log(res);
		if(res.data.result == 1) {
			var iconClass = state == 1 ? 'fas' : 'far';
			fc.innerHTML = `<i class="${iconClass} fa-heart"></i>`;
			fc.setAttribute('is_favorite', state)
		} else {
			alert('에러가 발생하였습니다.')
		}
	}).catch(function(err) { //통신 실패
		console.err('err 발생 : ' + err)
	});
}

var cmtObj = {
	createCmtTable: function() {
		var tableElem = document.createElement('table')
		tableElem.innerHTML = 
		`<tr>
			<th>내용</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>비고</th>
		</tr>`			
		return tableElem
	},
	
	getCmtList: function(i_board) {
		fetch(`/board/cmtList?i_board=${i_board}`)
			.then(function(res) {
				return res.json()
			})
			.then((list) => {
				this.proc(list)
			})
	},
	
	createRecode: function(item) {
		
	},
	proc: function(list) {
		var table = this.createCmtTable()
		
		console.log(list)
	}	
}

var cmtListElem = document.querySelector('#cmtList')
if(cmtListElem) {
	var i_board = document.querySelector('#i_board').dataset.id
	cmtObj.getCmtList(i_board)
}


//댓글 달기
var cmtFrmElem = document.querySelector('#cmtFrm')
if(cmtFrmElem) {	
	cmtFrmElem.onsubmit = function(e) {
		e.preventDefault()
	}

	var ctntElem = cmtFrmElem.ctnt
	var btnElem = cmtFrmElem.btn	
	var i_board = document.querySelector('#i_board').dataset.id

	ctntElem.onkeyup = function(e) {
		console.log(e.keyCode)
		if(e.keyCode === 13) {
			ajax()
		}
	}	
	btnElem.addEventListener('click', ajax)
		
	function ajax () {		
		if(ctntElem.value === '') {
			return
		}
				
		var param = {
			i_board: i_board,
			ctnt: c
		}
	
		console.log(param)
		fetch('/board/insCmt', {
			method: 'POST',
			headers: {
            	'Content-Type': 'application/json'
			},
			body: JSON.stringify(param)
		}).then(function(res) {
			return res.json()
		}).then(function(data) {
			proc(data)
		})
	}
	
	function proc (data) {
		switch(data.result){
			case 0:
				alert('댓글 작성 실패하였습니다')
			return
			case 1:
				ctntElem.value = ''
			return
		}
	}
}









