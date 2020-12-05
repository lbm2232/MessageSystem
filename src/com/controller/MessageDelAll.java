package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.MemberDTO;
import com.model.MessageDAO;

@WebServlet("/MessageDelAll")
public class MessageDelAll extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberDTO info = (MemberDTO)session.getAttribute("info");
		MessageDAO dao = new MessageDAO();
		int cnt = dao.deleteAll(info.getEmail());
		
		if(cnt>0) {
			System.out.println("전체삭제 완료!");
		}
		else {
			System.out.println("전체삭제 실패!");
		}
		response.sendRedirect("main.jsp#contact"); 
	}

}
