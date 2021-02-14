package com.star.blue.spag;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.metamodel.SetAttribute;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// model1.jsp의 Controller 부분을 모두 여기로 옮겨온다.
// 여기서 view 단으로 데이터를 전달한다.
@WebServlet("/spag")
public class Spag extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = 0;
		String num_ = request.getParameter("n");
		if(num_ != null && !num_.equals(""))
			num = Integer.parseInt(num_);
		String result = (num%2 != 0) ? "홀수" : "짝수";
		
		// 페이지 내에서만 사용하는 것이 pageContext이다.
		
		request.setAttribute("result", result);
		
		String[] names = {"newlec", "wilson"};
		request.setAttribute("names", names);
		
		Map<String, Object> notice = new HashMap<String, Object>();
		notice.put("id", 1);
		notice.put("title", "EL은 좋아요");
		request.setAttribute("notice", notice);
		
		// redirect
		// forward
		RequestDispatcher dispatcher = request.getRequestDispatcher("spag.jsp");
		dispatcher.forward(request, response);
		// request, response는 spag.jsp에서 사용하던 request, response와 같은 것이다.
		// 이렇게 할 수 있는 것이 forward 기능이다. 이와는 달리 redirect는 전혀 별개로 된다.
	}
}
