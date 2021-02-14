package com.star.blue.store.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Session은 Application과 다른 점은 사용자별로 개별적으로 다른 공간을 부여받아 저장한다.
// 브라우져가 달라지면 다른 사용자로 인식한다. 그러므로 session도 달라진다.
// 구글 chrome 브라우져는 하나의 프로세서에서 여러개의 Thead라는 개념을 사용하기 때문에 하나의 사용자로(같은 Session으로) 인식한다.
// Session은 SID를 가진다. WAS에 의해서 SID가 발부된다.
// invalidate() - 세션에서 사용되는 객체들을 바로 해제.
// setMaxInactiveInterval() - 세션 타인아웃을 정수(초)로 설정.
//
@WebServlet("/calc_3")
public class Calc3 extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//ServletContext application = request.getServletContext();
		HttpSession session = request.getSession();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
        
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		
		int v = 0;
		/* if(v_ != "" && v_ != null) */ v = Integer.parseInt(v_);
		
		if(op != "" && op != null && op.equals("=")) {
			int x = (Integer) session.getAttribute("value");
			int y = v;
			String operator = (String) session.getAttribute("op");
			
			int result = 0;
			if(operator.equals("+"))
				result = x+y;
			else	result = x-y;

			response.getWriter().printf("result is %d\n", result);
		}
		else {
			session.setAttribute("value", v);
			session.setAttribute("op", op);
			
			response.sendRedirect("calc2.html");
		}
	}
}
