package dgeniust.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import dgeniust.models.UserModel;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/admin")
public class AdminController extends HttpServlet {

    public AdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean ok = false;

		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("usermodel") != null) {
			UserModel user = (UserModel) session.getAttribute("usermodel");
			req.setAttribute("name", user.getFullname());
			if (!user.getRole().equals("MANAGER")) {
				resp.sendRedirect(req.getContextPath() + "/waiting");
				return;
			}
			ok = true;
		}
		if (!ok) {
			// go back home page
			System.out.println("back");
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		req.getRequestDispatcher("admin.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
