<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.vo.*,java.util.*"%>
<%--
	내부 크래스
	추상클래스
	종단클래스
	일반 클래스
 --%>
 
 <%
 	MemberVO vo = new MemberVO();
 	vo.setName("홍길동");
 	vo.setSex("남자");
 	vo.setAddr("서울");
 	vo.setEmail(true);
 	
 	request.setAttribute("vo", vo);
 	
 	List<String> list = new ArrayList<String>();
 	list.add("홍길동");
 	list.add("심청이");
 	list.add("춘향이");
 	
 	request.setAttribute("list", list);
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
이름 : ${vo.name}<br>
	 ${vo.getName() }<br>
성별 : ${vo.sex }<br>
주소 : ${vo.addr }<br>
이메일 : ${vo.email } 

<p>
이름 1 : <%=list.get(0) %><br>
이름 2 : <%=list.get(1) %><br>
이름 3 : <%=list.get(2) %><br>
<p>
이름 1 :${list[0] }<br>
이름 2 :${list[1] }<br>
이름 3 :${list[2] }<br>

</body>
</html>


