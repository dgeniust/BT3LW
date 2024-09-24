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
@WebServlet(urlPatterns = "/edit")
public class UpdateContronller extends HttpServlet {
    public UpdateContronller() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("edit.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String fullname = req.getParameter("fullname");
		String email = req.getParameter("email");
		String username = "";
		HttpSession session = req.getSession(false);
			if (session != null && session.getAttribute("usermodel") != null) {
				UserModel user = (UserModel) session.getAttribute("usermodel");
				username = user.getUsername();
			}
		IUserService service = new UserServiceImpl();

		System.out.println(1);
		UserModel user = service.update(username,fullname, email);
		System.out.println(2);
		if (user != null) {
			System.out.println("Update success");
			//set lại dữ liệu mới
			session.setAttribute("usermodel", user);
			if (user.getRole().equals("MEMBER"))
				resp.sendRedirect(req.getContextPath() + "/home");
			else if (user.getRole().equals("MANAGER"))
				resp.sendRedirect(req.getContextPath() + "/home");
		} else {
			System.out.println("Update fail");
			req.setAttribute("type", "Edit"); 
			req.getRequestDispatcher("edit.jsp").forward(req, resp);
		}
	}

}
