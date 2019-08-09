<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	출력 방법 : 클라이언트 ===============> 서버
			========				======  요청을 받아서 결과값 응답 (response)
			요청(요청내용 전송 : request)
									서버에서 클라인언트로 전송을 할때 결과값을 전송 하기 위해 (request에 값을 담아서 전송)
 									==============================================================
 									 request.setAttribute(),   session.setAttribute()
 									 =====						======
 									 한번만 사용 (page)				여러 page(jsp) 사용
 --%>
 <%--
		JSP에서 출력 =>
			1. 단일변수 String name="홍길동"
			2. VO에 있는 데이터 출력 => vo.getName()
			3. ArrayList에 있는 데이터 출력
			=> out.ptintln(일반변수) , <%= 변수%>
			${} => 자바 일반 변수를 출력하지 못한다.
				request.setAttribute()
				session.setAttribute()  로 출력
  --%>
 <%
 	String name="홍길동";
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>JSP 출력</h1>
	<%=name %> <br> 
	<%out.println(name); %><br>
	<%
		request.setAttribute("name", "심청이");
		session.setAttribute("name", "홍길동");
		application.setAttribute("name", "춘향이");
	%>
	${name }
</body>
</html>





