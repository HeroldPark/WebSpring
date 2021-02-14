<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- model1.jsp를 Model 2 방식으로 수정한다. -->
<!-- controller 부분을 Spag.java로 모두 옮기도 여기는 view만 남겨둔다. -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	pageContext.setAttribute("abc", "Hello");
	pageContext.setAttribute("result", "Hello");
%>
<body>
	[ EL ] <br>
	<%=request.getAttribute("result") %>입니다.
	<!-- 위와 같은 결과를 출력한다. 이것이 EL이다. -->
	<!--EL을 통해서 간단히 뽑아낼 수 있는 방법은 page, request, session, application 객체가 있다. -->
	<!-- 여러 저장소에 중복으로 저장되어 있다면 위의 순서대로 뒤져서 꺼내오게 된다. -->
	<!-- 하나의 저장소로 한정시킬려면 위의 저장소 이름에 Scope를 붙이면 된다. pageScope 이렇게.. ${sessionScope.abc} -->
	<br/>
	result: ${result} 입니다. <br>
	${names[0]}...	<br>
	ID: ${notice.id} <br>
	Title: ${notice.title}	<br>
	pageContext: ${abc}	<br>
	result: ${result}, ${requestScope.result}	<br>
	param: ${param.n}	<br>
	header: ${header.accept}	<br>
	pageContext: ${pageContext.request.method}	<br>	<br>
	
	[ EL 연산자 ] <br>
	
	param 1: ${param.n > 3}	<br>
	param 2: ${param.n gt 3}	<br>	<!-- javascript에서 <, >를 사용하는 입장에서 사용할 수 없는 경우가 발생할때 사용한다. -->
	
	param 3: ${empty param.n} <br>
	param 4: ${param.n == null || param.n == ''} <br> <!-- 위와 같은 의미임.  -->
	param 5: ${not empty param.n} <br>	<!-- 위와 같은 반대.  -->
	param 6: ${param.n/2}	<br>
</body>
</html>