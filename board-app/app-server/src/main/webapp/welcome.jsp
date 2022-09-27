<%@page import="com.bitcamp.board.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bitcamp</title>
</head>
<body>
<h1>환영합니다!-JSP</h1>
<p>비트캠프 게시판 관리 시스템 프로젝트입니다.</p>
<ul>
  <li><a href='board/list'>게시글</a></li>
  <li><a href='member/list'>회원</a></li>
<c:choose>
  <c:when test="${not empty sessionScope.loginMember}">
  <%-- 세션 스콥 빼도 되지만, 보다 명확한 의미 전달을 위해 놔두겠다.  --%>
    <li><a href="auth/logout">${loginMember.name}(로그아웃)</a></li>
  </c:when>
  <c:otherwise>
  <li><a href='auth/form.jsp'>로그인</a></li>
  </c:otherwise>
</c:choose>  
</ul>
</body>
</html>