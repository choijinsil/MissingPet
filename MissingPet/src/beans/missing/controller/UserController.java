package beans.missing.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.missing.dao.UserDAO;
import beans.missing.vo.PetVO;
import beans.missing.vo.UserVO;


@WebServlet("/main")
public class UserController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action == null || action.equals("main")) {// main.jsp 접속
			RequestDispatcher rd = request.getRequestDispatcher("/views/common/main.jsp");
			rd.forward(request, response);
		}else if(action.equals("user_mypage")) {
			/*MYPAGE이동 1. 회원정보조회 2. 회원MISSING정보조회*/
			
			//ID파라미터 SESSION영역에 저장 -> REDIRECT이동시 공유
			String id = request.getParameter("id");
			HttpSession session = request.getSession();
			session.setAttribute("id", id);	
			

			//회원정보,회원MISSING정보 REQUEST객체 영역에 저장
			UserDAO dao = new UserDAO();
			List<UserVO> userlist = dao.select_myinfo(id);
			request.setAttribute("userlist", userlist);
			List<PetVO> missinglist = dao.select_mymissing(id);
			request.setAttribute("missinglist", missinglist);
			
			
			
			//FORWARD이동		
			RequestDispatcher rd = request.getRequestDispatcher("/views/user/mypage.jsp");
			rd.forward(request, response);
			
		}else if(action.equals("update_myinfo")) {
			 /*MYPAGE이동 1. 회원정보수정*/
			
			 //회원정보얻어오기
			 UserVO user = new UserVO();
			 user.setId(request.getParameter("id"));
			 user.setPass(request.getParameter("pass"));
			 user.setName(request.getParameter("name"));
			 user.setEmail(request.getParameter("email"));
			 user.setTel(request.getParameter("tel"));
			 user.setAddress(request.getParameter("address"));
			 user.setBlack(request.getParameter("black"));
			 
			 //회원정보수정 
			 UserDAO dao = new UserDAO();
			 dao.update_myinfo(user);
			 
			 //회원정보,회원MISSING정보 SESSION객체 영역에 저장
			 String id = request.getParameter("id");
			 List<UserVO> userlist = dao.select_myinfo(id);
			 HttpSession session = request.getSession();
			 session.setAttribute("userlist", userlist);
			 List<PetVO> missinglist = dao.select_mymissing(id);
			 session.setAttribute("missinglist", missinglist);
				
		
		    //리다이렉트이동	 
			response.sendRedirect("/views/user/mypage.jsp"); 
	    	
     	}else if(action.equals("update_mymissing")) {
     		/*회원MISSING정보업데이트-> 인계날짜 SYSDATE입력*/
     		
     		//ID와 MISSING_NO(공고번호) 얻기
			String id = request.getParameter("id");
			int missing_no = Integer.parseInt(request.getParameter("missing_no"));
			
			
		 	//특정MISSING_NO(공고번호)의 인계날짜 SYSDATE입력
			UserDAO dao = new UserDAO();
			dao.update_mymissing(missing_no);
			 
			//회원정보,회원MISSING정보 SESSION객체 영역에 저장
			 List<UserVO> userlist = dao.select_myinfo(id);
			 HttpSession session = request.getSession();
			 session.setAttribute("userlist", userlist);	 
			 List<PetVO> missinglist = dao.select_mymissing(id);
			 session.setAttribute("missinglist", missinglist);

			 //리다이렉트이동
			 response.sendRedirect("/views/user/mypage.jsp");     	
     	}

}}
