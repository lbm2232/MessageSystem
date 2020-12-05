package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.MemberDAO;
import com.model.MemberDTO;

@WebServlet("/UpdateServiceCon")
public class UpdateServiceCon extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberDTO info = (MemberDTO)session.getAttribute("info");
		
		request.setCharacterEncoding("EUC-KR");
		String email = info.getEmail();
		String pw = request.getParameter("pw");
		String tel = request.getParameter("tel");
		String addr = request.getParameter("addr");
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO(email,pw,tel,addr);
		int cnt = dao.update(dto);
		
		if(cnt>0) {
			System.out.println("업데이트성공!");
			
			session.setAttribute("info", dto);
		}else {
			System.out.println("업데이트실패!");
		}
		response.sendRedirect("main.jsp");
		
		
	}

}
