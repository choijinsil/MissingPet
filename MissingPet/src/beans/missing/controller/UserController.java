package beans.missing.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
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

	UserDAO userDao = new UserDAO();
	String loginId; //로그인 아이디 : 로그인 성공시 세션에 저장, 로그아웃 탈퇴시 null

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();//세션 객체 생성
		
		String action = request.getParameter("action");

		UserDAO dao = new UserDAO();
		
		if (action == null || action.equals("main")) {// main.jsp 접속
			
			session.setAttribute("list", dao.pet_list());
			
			request.getRequestDispatcher("/views/common/main.jsp").forward(request, response);
		} else if (action.equals("joinForm")) {// 회원 가입
			request.getRequestDispatcher("/views/common/join.jsp").forward(request, response);
		} else if (action.equals("join")) { // 회원가입 버튼 누르면
			UserVO vo = new UserVO(request.getParameter("id"), request.getParameter("name"),
					request.getParameter("pass"), request.getParameter("email"), request.getParameter("tel"),
					request.getParameter("address"), "N");
			
			if (userDao.insert_user(vo)) {// 회원 가입 성공시
				response.sendRedirect("/main?action=main");
			} else {
				PrintWriter out = response.getWriter();
				out.println("<script>alert('회원가입 실패 하였습니다!'); history.back();</script>");
				out.flush();
			}
		} else if (action.equals("loginForm")) {// 로그인 창으로 이동
			request.getRequestDispatcher("/views/common/login.jsp").forward(request, response);
		} else if (action.equals("login")) { // 로그인 시
			loginId = request.getParameter("id");
			String pass = request.getParameter("pass");

			Map<String, String> map = new HashMap<String, String>(); // sql문에 전달할 값
			map.put("id", loginId);
			map.put("pass", pass);

			if (userDao.select_user(map)&& "N".equals(userDao.select_black_user(loginId))) {
				// id, pass가 맞고 블랙리스트값이 N인 경우 --로그인 성공!
				session.setAttribute("loginId", loginId);
				request.getRequestDispatcher("/views/common/main.jsp").forward(request, response);
			} else { // 로그인 실패시
				
				if ("Y".equals(userDao.select_black_user(loginId))) {// 블랙리스트가 맞으면 로그인 실패
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('회원님은 현재 블랙리스트 상태 입니다.'); history.back();</script>");
					out.flush();
					return;
				} 
				//아이디가 틀렸을 경우
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('로그인 실패 했지롱~! 다시해라!'); history.back();</script>");
				out.flush();
				return;
			}

		} else if (action.equals("loginOut")) {// 로그아웃
			session.invalidate();
			response.sendRedirect("/main?action=main");

		} else if (action.equals("user_mypage")) {
			/* MYPAGE이동 1. 회원정보조회 2. 회원MISSING정보조회 */

			// 회원정보,회원MISSING정보 REQUEST객체 영역에 저장
			request.setAttribute("userlist", userDao.select_myinfo(loginId));
			request.setAttribute("missinglist", userDao.select_mymissing(loginId));

			// FORWARD이동
			request.getRequestDispatcher("/views/user/mypage.jsp").forward(request, response);

		} else if (action.equals("update_myinfo")) {
			/* MYPAGE이동 1. 회원정보수정 */

			// 회원정보얻어오기
			UserVO user = new UserVO();
			user.setId(request.getParameter("id"));
			user.setPass(request.getParameter("pass"));
			user.setName(request.getParameter("name"));
			user.setEmail(request.getParameter("email"));
			user.setTel(request.getParameter("tel"));
			user.setAddress(request.getParameter("address"));
			user.setBlack(request.getParameter("black"));

			// 회원정보수정
			userDao.update_myinfo(user);
			
			// 회원정보,회원MISSING정보 SESSION객체 영역에 저장
			
			session.setAttribute("userlist", userDao.select_myinfo(loginId));
			session.setAttribute("missinglist", userDao.select_mymissing(loginId));

			// 리다이렉트이동
			response.sendRedirect("/views/user/mypage.jsp");

		} else if (action.equals("update_mymissing")) {
			/* 회원MISSING정보업데이트-> 인계날짜 SYSDATE입력 */

			// ID와 MISSING_NO(공고번호) 얻기
			int missing_no = Integer.parseInt(request.getParameter("missing_no"));

			// 특정MISSING_NO(공고번호)의 인계날짜 SYSDATE입력
			if (userDao.update_mymissing(missing_no)) {
				System.out.println("인계정보 수정 완료");
			} else {
				System.out.println("인계정보 수정 실패");
			}

			// 회원정보,회원MISSING정보 SESSION객체 영역에 저장
			session.setAttribute("userlist", userDao.select_myinfo(loginId));
			session.setAttribute("missinglist", userDao.select_mymissing(loginId));

			// 리다이렉트이동
			response.sendRedirect("/views/user/mypage.jsp");
		}else if(action.equals("withdraw")) {
			
			if(userDao.delete_user(loginId)) { // 회원 탈퇴
				session.invalidate();
				response.sendRedirect("/main");
			}
		}
	}
}
