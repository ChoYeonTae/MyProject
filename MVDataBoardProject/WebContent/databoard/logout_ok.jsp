<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	session.invalidate();    //서벙에 저장된 session의 모든 데이터 삭제
	response.sendRedirect("list.jsp");
%>
    
    