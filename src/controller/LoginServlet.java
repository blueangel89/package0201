package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.LoginService;
import vo.Member;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Request 내장객체
		//out (JspWriter)
		//loginForm.jsp에서 입력받은 아이디 & 비밀번호 값을 가져옴
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		LoginService loginService = new LoginService();
		Member loginMember = loginService.getLoginMember(id,passwd);
		
		//정상적으로 로그인처리가 되었으면 
		if(loginMember != null) {
			HttpSession session = request.getSession();
			//id 속성을 지정
			session.setAttribute("id",id);
			response.sendRedirect("index.jsp");
		}else {//로그인 실패
			response.setContentType("text/html;charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("<alert('로그인실패')>");
			out.println("history.back()");
			out.println("</script>");
		}
	}

}