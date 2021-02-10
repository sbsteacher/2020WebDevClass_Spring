var inputImgElem = document.querySelector('#inputImg')
function upload () {
	if(inputImgElem.files.length === 0) {
		alert('이미지를 선택해 주세요')
		return
	}
	
	ajax()
	
	function ajax () {
		var formData = new FormData()
		for(var i=0; i<inputImgElem.files.length; i++) {
			formData.append('imgs', inputImgElem.files[i])	
		}		
		fetch('/user/profileUpload',{
			method: 'post',
			body: formData
		})
	}
}


var centerContElem = document.querySelector('.centerCont')
function getData () {
	fetch('/user/profileData')
	.then(res => res.json())
	.then(myJson => {
		proc(myJson)
	})
	
	function proc (myJson) {
		var div = document.createElement('div')
		div.classList.add('profileBox')
		
		var delProfileHTML = ''
		
		var imgSrc = '/res/img/basic_profile.jpg'
		if(myJson.profile_img) {
			imgSrc = `/res/img/user/${myJson.i_user}/${myJson.profile_img}`
			
			delProfileHTML = `
				<div id="delProfileBtnContainer">
					<button onclick="delProfileImg();">기본이미지 사용</button>
				</div>
			`
		}
		
		var gender = '여'
		if(myJson.gender === 1) {
			gender = '남'
		}
		
		div.innerHTML = `
			<div class="circular--landscape circular--size200">
				<img id="profileImg" src="${imgSrc}" alt="프로필 이미지">
			</div>
			<div>
				<div>아이디 : ${myJson.user_id}</div>
				<div>이름 : ${myJson.nm}</div>
				<div>성별 : ${gender}
				<div>연락처 : ${myJson.ph}
			</div>
			${delProfileHTML}
		`
		
		centerContElem.innerHTML = ''
		centerContElem.append(div)
	}
}
getData()











