package com.star.blue.calc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class Calculator extends HttpServlet {
	
	/* 이 부분을 생략하더라도 자동적으로 아래의 doGet(), doPost()를 HttpService가 자동적으로 맵핑해 준다.
	 * 생략하지 않으면 있은 그대로 두 번 호출된다.
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getMethod().equals("GET")) {	// "GET"은 반드시 대문자여야 한다. 요청할때 소문자여도 여기서는 대문자로...
			System.out.println("GET 요청이 왔습니다.");
		}
		else if(req.getMethod().equals("POST")) {
			System.out.println("POST 요청이 왔습니다.");
		}
			
		super.service(req, resp);
	}
	*/
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGTE 메소드가 호출되었습니다.");
		
		Cookie[] cookies = request.getCookies();
		String exp = "0";
		if(cookies != null) {
			for(Cookie c : cookies) {
				if(c.getName().equals("exp")) {
					exp = c.getValue();
					break;
				}
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		//out.write("<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>");
		out.write("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>");
		out.write("<html>");
		out.write("<head>");
		out.write("<meta charset='UTF-8'>");
		out.write("<title>Insert title here</title>");
		out.write("<style>");
		out.write("input{");
		out.write("width:50px;");
		out.write("height:50px;");
		out.write("}");
		out.write(".output{");
		out.write("height: 50px;");
		out.write("background: #e9e9e9;");
		out.write("font-size: 24px;");
		out.write("font-weight: bold;");
		out.write("text-align: right;");
		out.write("padding: 0px 5px;");
		out.write("}");
		out.write("</style>");
		out.write("</head>");
		out.write("<body>");
		//out.write("<form action='calc3' method='post'>");
		out.write("<form method='post'>");
		out.write("<table>");
		out.write("<tr>");
		out.printf("<td class='output' colspan='4'>%s</td>", exp);
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td><input type='submit' name='operator' value='CE' /></td>");
		out.write("<td><input type='submit' name='operator' value='C' /></td>");
		out.write("<td><input type='submit' name='operator' value='BS' /></td>");
		out.write("<td><input type='submit' name='operator' value='/' /></td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td><input type='submit' name='value' value='7' /></td>");
		out.write("<td><input type='submit' name='value' value='8' /></td>");
		out.write("<td><input type='submit' name='value' value='9' /></td>");
		out.write("<td><input type='submit' name='operator' value='*' /></td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td><input type='submit' name='value' value='4' /></td>");
		out.write("<td><input type='submit' name='value' value='5' /></td>");
		out.write("<td><input type='submit' name='value' value='6' /></td>");
		out.write("<td><input type='submit' name='operator' value='-' /></td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td><input type='submit' name='value' value='1' /></td>");
		out.write("<td><input type='submit' name='value' value='2' /></td>");
		out.write("<td><input type='submit' name='value' value='3' /></td>");
		out.write("<td><input type='submit' name='operator' value='+' /></td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td></td>");
		out.write("<td><input type='submit' name='value' value='0' /></td>");
		out.write("<td><input type='submit' name='dot' value='.' /></td>");
		out.write("<td><input type='submit' name='operator' value='=' /></td>");
		out.write("</tr>");
		out.write("</table>");
		out.write("</form>");
		out.write("</body>");
		out.write("</html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPOST 메소드가 호출되었습니다.");
		
		Cookie[] cookies = request.getCookies();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
        
		String value = request.getParameter("value");
		String operator = request.getParameter("operator");
		String dot = request.getParameter("dot");
		
		String exp = "";
		if(cookies != null) {
			for(Cookie c : cookies) {
				if(c.getName().equals("exp")) {
					exp = c.getValue();
					break;
				}
			}
		}
		if(operator != null && operator.equals("=")) {
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
			try {
				exp = String.valueOf(engine.eval(exp));
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(operator != null && operator.equals("C")) {
			exp = "";
		}
		else {
			exp += (value == null)?"" : value;
			exp += (operator == null)?"" : operator;
			exp += (dot == null)?"" : dot;
		}
		
		Cookie expCookie = new Cookie("exp", exp);
		if(operator != null && operator.equals("C"))
			expCookie.setMaxAge(0);
		expCookie.setPath("/calculator");	// default가 "/" 이다. Cookie가 요기로만 전달된다.
		response.addCookie(expCookie);
		response.sendRedirect("calculator");	// 자기 자신을 호출한다. 이것은 GET 요청이다.
	}
}
