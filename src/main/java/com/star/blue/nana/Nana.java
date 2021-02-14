package com.star.blue.nana;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@WebServlet("/hello")
public class Nana extends HttpServlet {
	
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        PrintWriter out = response.getWriter();
		out.println("Hello Servlet <br/>");
		
		int cnt = 0;
		String str = request.getParameter("count");
		if(str != "" && str != null)
			cnt = Integer.parseInt(str);

		for(int i=0; i<cnt; i++)
			out.println((i+1) + ": 안녕 Servlet~~ <br/>");
		 
        
		/*
		 * String title = request.getParameter("title"); String content =
		 * request.getParameter("content");
		 * 
		 * out.println(title); out.println(content);
		 */
    }
}