package beans.missing.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pet")
public class PetController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if(action.equals("register")) {//main페이지에서 동물등록 눌렀을때 페이지 이동
			RequestDispatcher rd = request.getRequestDispatcher("/views/user/register_pet.jsp");
			rd.forward(request, response);
			
		}else if(action.equals("map")) {//register_put.jsp에서 등록버튼 클릭시 이동페이지
			response.sendRedirect("/views/user/map.jsp");
		}
	}
	
}
