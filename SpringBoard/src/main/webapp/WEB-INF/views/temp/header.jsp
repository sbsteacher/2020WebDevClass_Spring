<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %> 
<tiles:importAttribute name="menuList"/>
<header>
	<ul>	
		<c:if test="${loginUser == null}">
			<li><a href="/user/login">로그인</a>
		</c:if>
		<c:if test="${loginUser != null}">
			<li>${loginUser.nm}님 환영합니다.</li>
			<li><a href="/user/logout">Logout</a></li>
		</c:if>		
		<li><a href="/board/home">Home</a></li>
		<!-- TODO: 메뉴 뿌리기 -->
		<c:forEach items="${pageScope.menuList}" var="item">
			<li class="${item.typ == param.typ ? 'selectedBoard': ''}">
				<a href="/board/list?typ=${item.typ}">
					${item.nm}
				</a>
			</li>
		</c:forEach>
		<c:if test="${loginUser != null}">
			<li><a href="/user/profile">프로필</a></li>
			<li><a href="/user/changePw">비밀번호변경</a></li>
		</c:if>
	</ul>
</header>