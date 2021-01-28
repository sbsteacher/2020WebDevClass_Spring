<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div>
	<a href="/board/list?typ=${requestScope.data.typ}">돌아가기</a>
	
	<c:if test="${data.i_user == loginUser.i_user}">
		<button onclick="clkDel(${requestScope.data.i_board}, ${requestScope.data.typ});">삭제</button>
		<a href="/board/mod?typ=${requestScope.data.typ}&i_board=${requestScope.data.i_board}">
			<button>수정</button>
		</a>
	</c:if>
	<div>
		<div>번호 : ${requestScope.data.seq}</div>
		<div>조회수 : ${requestScope.data.hits}</div>
		<div>
			이름 :
			<c:if test="${data.profile_img == null}">
				<div class="circular--landscape circular--size40">
					<img id="profileImg" src="/res/img/basic_profile.jpg">
				</div>				
			</c:if>
			<c:if test="${data.profile_img != null}">
				<div class="circular--landscape circular--size40">
					<img id="profileImg" src="/res/img/${data.i_user}/${data.profile_img}">
				</div>
			</c:if>
			<span class="profile-td-nm">${data.writer_nm}</span>
		</div>
		<div>제목 : ${data.title}</div>
		<div>작성일 : ${data.r_dt}</div>
		<div>
			${data.ctnt}
		</div>
	</div>
	<div style="margin-top: 20px;">
		<c:if test="${loginUser != null}">
		<div>
			<form id="cmtFrm">
				댓글: <input type="text" name="ctnt" data-id="${requestScope.data.i_board}">
				<input type="button" name="btn" value="댓글쓰기">
			</form>
		</div>	
		</c:if>
		<div>
			<c:if test="${fn:length(requestScope.cmtList) > 0}">
			<table>
				<tr>					
					<th>내용</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>비고</th>					
				</tr>
				<c:forEach items="${cmtList}" var="item">
					<tr>
						<td>${item.ctnt}</td>
						<td>${item.user_nm}</td>
						<td>${item.r_dt}</td>
						<td>
							<c:if test="${item.i_user == loginUser.i_user}">
								<a href="cmt/del?i_cmt=${item.i_cmt}&i_board=${data.i_board}">
									<button>삭제</button>
								</a>
								<button onclick="clkCmtMod(${item.i_cmt});">수정</button>
							</c:if>
						</td>
					</tr>
					<c:if test="${item.i_user == loginUser.i_user}">
						<tr id="mod_${item.i_cmt}" class="cmd_mod_form">
							<td colspan="4">
								<form action="cmt/mod" method="post">
									<input type="hidden" name="i_board" value="${data.i_board}">
									<input type="hidden" name="i_cmt" value="${item.i_cmt}">
									<input type="text" name="ctnt" value="${item.ctnt}">
									<input type="submit" value="수정">
									<!--input type="button" value="닫기" onclick="clkCmtClose(${item.i_cmt});"-->
									<button type="button" onclick="clkCmtClose(${item.i_cmt});">닫기</button>
								</form>
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</table>
			</c:if>
		</div>
	</div>
	
	<c:if test="${loginUser != null}">
	<div id="favoriteContainer" is_favorite="${data.is_favorite}"
		 onclick="toggleFavorite(${data.i_board});">	
		<c:choose>
			<c:when test="${data.is_favorite == 1}">
				<i class="fas fa-heart"></i>
			</c:when>
			<c:otherwise>
				<i class="far fa-heart"></i>
			</c:otherwise>
		</c:choose>		
	</div>
	</c:if>
</div>
