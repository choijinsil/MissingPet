package beans.missing.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.missing.dao.UserDAO;

@WebServlet("/main")
public class UserController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		UserDAO dao = new UserDAO();
		
		if (action == null || action.equals("main")) {// main.jsp 접속
			
			request.getSession().setAttribute("list", dao.pet_list());
			
			RequestDispatcher rd = request.getRequestDispatcher("/views/common/main.jsp");
			rd.forward(request, response);
		}
	}

}
