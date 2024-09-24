package dgeniust.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dgeniust.config.DBConnectSQL;
@WebServlet("/dbtest")

public class Test extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = DBConnectSQL.getDatabaseConnection()) {
            if (connection != null) {
                response.getWriter().println("Database connection established successfully.");
            } else {
                response.getWriter().println("Failed to establish database connection.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Database connection failed", e);
        }
    }
}
