package dgeniust.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import dgeniust.dao.UserServiceImpl;
import dgeniust.models.UserModel;
import dgeniust.service.IUserService;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/signup")
public class SignupController extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = (HttpSession) req.getSession(false);
		if (session != null && session.getAttribute("usermodel") != null) {
			UserModel user = (UserModel) session.getAttribute("usermodel");
			if (user.getRole().equals("MEMBER"))
				resp.sendRedirect(req.getContextPath() + "/waiting");
			else if (user.getRole().equals("MANAGER"))
				resp.sendRedirect(req.getContextPath() + "/manager");
			return;
		}
		 
		req.removeAttribute("error2");
		req.setAttribute("type", "Signup");
		req.getRequestDispatcher("signup.jsp").forward(req, resp);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		String password = req.getParameter("pword");
		String email = req.getParameter("email");
		String fullname = req.getParameter("fullname");
		IUserService service = new UserServiceImpl();
		System.out.println(password);
		boolean isSuccess = service.register(username, email, password, fullname);
		if (isSuccess) {
			
			System.out.print("Đăng kí thành công: "+username);
			req.setAttribute("color", "green");
			req.setAttribute("error", "Đăng ký thành công, bạn vui lòng đăng nhập lại!");
			req.setAttribute("type", "Login");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			resp.sendRedirect(req.getContextPath()+"/login");
			
		} else {
			System.out.println("Đăng kí thất bại: "+username);
			req.setAttribute("color", "red");
			req.setAttribute("error2", "Email hoặc username đã tồn tại");
			req.setAttribute("type", "Signup");
			req.getRequestDispatcher("signup.jsp").forward(req, resp);
		}
	}

}
