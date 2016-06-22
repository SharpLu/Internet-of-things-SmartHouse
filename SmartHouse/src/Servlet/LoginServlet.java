package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @author: FENG LU QIUQI HU
 * Date: 2013-11-16
 */

@WebServlet("/RegServlet")
public class LoginServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	PrintWriter out = null;
	Connector connect;
	Connector con = new Connector();
// instance the connector
	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String LoginResult = con.authenticateUser(username, password);
			response.setContentType("text/html");
			if (LoginResult.contains("Try")) {
				request.getSession().setAttribute("Result", "Try");
				response.sendRedirect("index.jsp");
			} else {
				response.sendRedirect("MainControlbox.jsp");
			}

		} catch (Exception e) {
			PrintWriter out = response.getWriter();
			out.println("<body>" + "Sorry can not found servlet" + "</body>");
		}

	}
}