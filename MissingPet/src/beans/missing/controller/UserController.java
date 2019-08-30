package beans.missing.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.missing.dao.UserDAO;
import beans.missing.vo.UserVO;

@WebServlet("/main")
public class UserController extends HttpServlet {
	UserDAO userDao= new UserDAO();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

 
		if (action == null || action.equals("main")) {// main.jsp 접속
			RequestDispatcher rd = request.getRequestDispatcher("/views/common/main.jsp");
			rd.forward(request, response);
			
		}else if(action.equals("joinForm")) {//회원 가입
			RequestDispatcher rd = request.getRequestDispatcher("/views/common/join.jsp");
			rd.forward(request, response);
			
		}else if(action.equals("join")) { //회원가입 버튼 누르면
			UserVO vo= new UserVO(request.getParameter("id")
					, request.getParameter("name")
					, request.getParameter("pass")
					, request.getParameter("email")
					, request.getParameter("tel")
					, request.getParameter("address")
					, "N");
				if(userDao.insert_user(vo)) {//회원 가입 성공시
					response.sendRedirect("/main?action=main");
				}else {
					System.out.println("회원가입 실패");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('회원가입 실패 하였습니다!'); history.back();</script>");
					out.flush();
				}
		}else if(action.equals("loginForm")) {//로그인 창으로 이동
			RequestDispatcher rd = request.getRequestDispatcher("/views/common/login.jsp");
			rd.forward(request, response);
		}else if(action.equals("login")) { // 로그인 시
			String id= request.getParameter("id");
			String pass= request.getParameter("pass");
			
			Map<String, String> map= new HashMap<String, String>(); //sql문에 전달할 값
			map.put("id", id);
			map.put("pass", pass);
			
			if(userDao.select_user(map)) {//로그인 성공시
				System.out.println("로그인 성공!"); 
				request.getSession().setAttribute("loginId",id);
				RequestDispatcher rd = request.getRequestDispatcher("/views/common/main.jsp");
				rd.forward(request, response);
			}else { //로그인 실패시
				System.out.println("로그인 실패!");
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('로그인 실패 했지롱~! 다시해라!'); history.back();</script>");
				out.flush();
			}
			
		}else if(action.equals("loginOut")) {//로그아웃
			request.getSession().invalidate(); 
			response.sendRedirect("/main?action=main");
	}
	}

}
