package dgeniust.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dgeniust.dao.UserServiceImpl;
import dgeniust.service.IUserService;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/forgetpw")
public class FPController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FPController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("forgetpassword.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String email = req.getParameter("email");
		String newpassword = req.getParameter("pword");
		IUserService service = new UserServiceImpl();
		
		if(service.checkExistEmail(email) != false){
			if(service.updatePassword(email, newpassword)) {
				req.setAttribute("msg", "Mật khẩu đã được đổi thành công");
			}
			else {
				req.setAttribute("msg", "Đã có lỗi xảy ra");
			}
		}
		else {
			req.setAttribute("msg", "Email không tồn tại");
			
		}
		req.getRequestDispatcher("forgetpassword.jsp").forward(req, resp);
	}

}
