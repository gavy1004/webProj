package ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ajax.html")
public class AjaxServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String p1 = req.getParameter("p1");
		String p2 = req.getParameter("p2");
		resp.getWriter().print("<h1>Ajax Get Page</h1>");
		resp.getWriter().print("<h3>" + p1 + "," + p2 + "</h3>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		resp.getWriter().print("<h1>Ajax Post Page</h1>");
	}

}
