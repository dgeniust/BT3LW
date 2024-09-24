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
@WebServlet(urlPatterns = "/waiting")
public class WaitingController extends HttpServlet {

    public WaitingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");

		boolean ok = false;

		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("usermodel") != null) {
			UserModel user = (UserModel) session.getAttribute("usermodel");
			req.setAttribute("name", user.getFullname());
			if (!user.getRole().equals("MEMBER")) {
				resp.sendRedirect(req.getContextPath() + "/admin");
				return;
			}
				
			ok = true;
		}
		if (!ok) {
			// go back home page
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		req.getRequestDispatcher("waiting.jsp").forward(req, resp);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
