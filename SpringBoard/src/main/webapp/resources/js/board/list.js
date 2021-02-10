var dataElem = document.querySelector('#data')
var listFrmElem = document.querySelector('#listFrm')
var typElem = listFrmElem.typ
var searchTypeElem = document.querySelector('#searchType')
var searchTextElem = document.querySelector('#searchText')	

//글 제목 클릭
function clkArticle(i_board, searchType, searchText) {		
	var url = `/board/detail?i_board=${i_board}&searchType=${searchType}&searchText=${searchText}`;
	location.href = url; //주소값 이동
}

//검색텍스트에서 엔터치면 검색되게 처리
function doSearch(e) {
	if(e.keyCode === 13) {
		getBoardList()
	}
}

function getBoardList(page) {
	
	var searchTextValue = searchTextElem.value
	if(searchTextValue !== '') {
		var searchTypeValue = searchTypeElem.value
		
		listFrmElem.searchType.value = searchTypeValue
		listFrmElem.searchText.value = searchTextValue
	}
	var {typ} = dataElem.dataset
	//var typ = dataElem.dataset.typ

	typElem.value = typ
	
	if(page) {
		listFrmElem.page.value = page
	}
	
	listFrmElem.submit()
}