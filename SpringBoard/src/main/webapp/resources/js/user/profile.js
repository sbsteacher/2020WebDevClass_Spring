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

var splide = null
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
		var imgOption = ''
		if(myJson.profile_img) {
			imgSrc = `/res/img/user/${myJson.i_user}/${myJson.profile_img}`
			imgOption = ` onclick="clkProfile()" class="pointer" `
			
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
				<img id="profileImg" src="${imgSrc}" ${imgOption} alt="프로필 이미지">
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


var modalContainerElem = document.querySelector('.modalContainer')

function clkProfile () {
	openModal()
	getProfileImgList()
}

//프로필 이미지 리스트 가져오기
function getProfileImgList () {
	fetch('/user/profileImgList')
	.then(res => res.json())
	.then(myJson => {		
		profileImgCarouselProc(myJson)
	})
}

//프로필 이미지 삭제
function delProfileImg({i_img, img}) {
	return new Promise(function(resolve) {
		fetch(`/user/profileImg?i_img=${i_img}&img=${img}`, {
			method: 'delete'
		})
		.then(res => res.json())
		.then(myJson => {
			resolve(myJson)
		})	
	})
}

function profileImgCarouselProc(myJson) {	
	var splideList = document.querySelector('.splide__list')
	splideList.innerHTML = null
	myJson.forEach(function(item) {
		const div = document.createElement('div')
		div.classList.add('splide__slide')
		
		const img = document.createElement('img')		
		img.src = `/res/img/user/${item.i_user}/${item.img}`
		
		const span = document.createElement('span')
		span.classList.add('pointer')
		span.append('X')
		span.addEventListener('click', function() {
			delProfileImg(item).then(myJson => {
				if(myJson === 1) {
					div.remove()
					splide.refresh()
				} else {
					alert('삭제를 실패하였습니다')
				}
			})
		})
		
		div.append(img)
		div.append(span)
		splideList.append(div)
	})
	
	if(splide != null) {		
		splide.destroy(true)
	}
	
	splide = new Splide('.splide').mount()
	
}

function openModal () {	
	modalContainerElem.classList.remove('hide')
}

function closeModal () {
	modalContainerElem.classList.add('hide')
}










