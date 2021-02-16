<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@splidejs/splide@latest/dist/css/splide.min.css">
<link rel="stylesheet" href="/res/css/user/profile.css?ver=3">
    
<div class="centerCont"></div>
<div>
	<input type="file" id="inputImg" multiple accept="image/*">						
	<input type="button" value="업로드" onclick="upload()">
</div>
<div class="modalContainer hide">
	<div class="modalContent">
		<span class="pointer" onclick="closeModal()">닫기</span>
		<div class="splide">
			<div class="splide__track">
				<div class="splide__list">					
				</div>
			</div>
		</div>
	</div>
	
</div>
<script src="https://cdn.jsdelivr.net/npm/@splidejs/splide@latest/dist/js/splide.min.js"></script>
<script src="/res/js/user/profile.js"></script>















