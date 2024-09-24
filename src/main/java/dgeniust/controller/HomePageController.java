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
@WebServlet(urlPatterns = "/home")
public class HomePageController extends HttpServlet {
    public HomePageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("a");
		System.out.println("b");
		System.out.println("-");
		resp.setContentType("text/html");

		boolean ok = false;
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("usermodel") != null) {
			UserModel user = (UserModel) session.getAttribute("usermodel");
			session.setAttribute("nameLogin", user.getFullname());

			System.out.println("c");
			System.out.println("d");
			System.out.println("e");
			System.out.println(req.getSession());
			System.out.println(user.getFullname());
			
			/*
			 * if (!user.getRole().equals("MEMBER")) {
			 * resp.sendRedirect(req.getContextPath() + "/admin"); return; }
			 */
				
			ok = true;
		}
		if (!ok) {
			// go back home page
//			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
