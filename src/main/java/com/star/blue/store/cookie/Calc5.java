package com.star.blue.store.cookie;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 쿠키는 SID를 서버와 클라이언트에서 같이 사용한다.
// Cookie는 기본적으로 SID를 클라이언트에서 저장한다.
// 쿠키는 기본적으로 브라우져의 생존기간과 같다.
// 설정으로 MaxAge를 설정할 수 있다. (초단위)
@WebServlet("/calc_5")
public class Calc5 extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// ServletContext application = request.getServletContext();
		Cookie[] cookies = request.getCookies();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
        
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		
		int v = 0;
		 if(v_ != "" && v_ != null)  v = Integer.parseInt(v_);
		
		if(op != "" && op != null && op.equals("=")) {
			int x = 0;
			for(Cookie c : cookies) {
				if(c.getName().equals("value")) {
					x = Integer.parseInt(c.getValue());
					break;
				}
			}

			int y = v;
			String operator = "";
			for(Cookie c : cookies) {
				if(c.getName().equals("op")) {
					operator = c.getValue();
					break;
				}
			}
			
			int result = 0;
			if(operator.equals("+"))
				result = x+y;
			else	result = x-y;

			response.getWriter().printf("result is %d\n", result);
		}
		else {
			Cookie valueCookie = new Cookie("value", String.valueOf(v));
			Cookie opCookie = new Cookie("op", op);
			valueCookie.setPath("/calc5");	// URL에 여기서 설정한 PATH가 설정될때만 전달된다.
			valueCookie.setMaxAge(24*60*60);	// 초단위 만료 시간. (이 시간동안은 브라우져가 닫혀도 살아 있다)
			opCookie.setPath("/calc5");
			response.addCookie(valueCookie);
			response.addCookie(opCookie);
			
			response.sendRedirect("calc3.html");
		}
	}
}
