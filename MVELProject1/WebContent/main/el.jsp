<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	EL : 표현식
		=>${출력할 내용} =>  out.println(변수명) , <%=변수 %>
		=============
		=>${}는 자바가 아니다
			1) 연산자
				1.산술연산자 ( + , - , * , / , % )
					/ => 5/2 => 2.5(실수) /=> div
					&{ 5/2 } == ${ 5 div 2 }
					% => 왼쪽편 부호를 따라 간다 -5%-2= -1 , 5%-2 => 1
					${ 5%2 } => 1 , ${5 mod 2 }
					=> ${ null + 1 } => (null 산술연산 ) ==> null은 0으로 인식
					=> + 연산자 문자열 결합을 할 수 없다
						${"abc"+1} => (자바: "abc1") => EL => Error
						${"10"+"20"} => 30
						*** 문자열 결합 ${"abc"+=3} => "abc3" (문자열 결합은 +=)
				2.비교 연산자 ( == , != , < , > , <= , >= )  ==>문자열 비교 (equals x => ==)
					== : eq		=> ${7==7} , ${7eq7} ==>true
					!= : ne		=> ${7!=7} , ${7ne7} ==>false
					<  : lt
					>  : gt
					<= : le
					>= : ge
				3. 논리연산자 ( && (and) , || (or) , ! (not))
				===============================
				
				4.삼항연산자 조건 ? 값1 : 값2 ==> 조건 true => 값1 , 조건 false =>값2
								if~else
				5. null,  공백 => empty => ${empty name}
			2) 내장 객체
				param	===========> ${param.id} ==> requset.getParameter("id")
											====
											key
				paramValues	=======> ${paramValues.hobby} ==> request.getParameterValues("hobby")
			*****requestScope =====> ${requestScope.id} ==> request.getAttribute("id")
															==========================
															request.setAttribute("id","name")
								===> ${id}
								String id = "name" ==> ${id} (출력이 안된다)
													==> <%= id%>
								request.setAttribute("id",id)  => ${id}
			*****sessionScope ===> ${sessionScope.id}  ==> session.getAttribute("id")
								=======================
								session.setAttribute("id","admin")
				=> 우선 순위 : request , session
					request.setAttribute("id","hong")
 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>연산자 (산술연산자)</h1>
	${ 10+10 }<br>
	${ 10-10 }<br>
	${ 10*10 }<br>
	${ 5/2 }<br>
	${ 5%2 }<br>
	${ null+10 } : null은 0으로 인식 <br>
	${ "10"+"10" } : 문자열 결합이 아니다, parseInt가 자동으로 들어감 <br>
	${ "abc" += 10 } :문자열 겹합은 +=<br>
	<h1>EL에서 지원하는 데이터형</h1>
	boolean , byte , short , int , long (정수형) , float , double(실수형), 문자나 문자열 (string),null<br>
	${ "hello" },${ 'java' }   ==>스크립스형식
	<h1>비교연산자 (결과값 :  true/false)</h1>
	${ 10==10 }, ${ "Hello" == "hello" } ,<%--  ${10 eq 10 } --%><br>
	${ 10!=10 }, <%-- ${ 10 ne 10 } --%><br>
	${ 10>10 }, <%-- ${ 10 gt 10 } --%><br>
	${ 10<10 }, <%-- ${ 10 lt 10 } --%><br>
	${ 10<=10 }, <%-- ${ 10 le 10 } --%><br>
	${ 10>=10 }, <%-- ${ 10 ge 10 } --%><br>
	<h1>논리연산자 (결과값 : true/false)</h1>
<%-- 	${ 10==10 && 10!=10 }<br>
	${ 10==10 and 10!=10 }<br>
	${ 10==10 || 10!=10 }<br>
	${ 10==10 or 10!=10 }<br> --%>
	<h1>삼항연산자</h1>
	<%
		int sex =1;   // 일반 변수는 값을 가져오지 못한다.
		request.setAttribute("sex", 1);	
	%>
	${ sex == 1? "남자":"여자" }<br>
	<h1>Empty연산자</h1>
	${ empty "" }<br>
	<%--
		if(page==null) ${empty page} ==> if (page.equals("")) ==>${empty page}
	 --%>
	${ empty null }<br>
</body>
</html>













