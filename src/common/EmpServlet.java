package common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/empList.do")
public class EmpServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		EmpDAO dao = new EmpDAO();
		List<Employee> list = dao.getempList();
		String jsonData = "[";
		// [{"empId":"?", "fname":"?" ,"lname":"?"} ... ]
		int cnt = 0;
		for (Employee emp : list) {
			jsonData += ("{\"empId\":\"" + emp.getEmployeeId()//
					+ "\",\"fname\":\"" + emp.getFirstName() //
					+ "\", \"lname\":\""+ emp.getLasttName() //
					+ "\", \"email\":\""+ emp.getEmail() //
					+ "\", \"salsry\":\""+ emp.getSalary() //
					+ "}");
			if(++cnt == list.size()) {
				continue;
			}
			jsonData +=", \n";
		}
		jsonData += "]";
		
		out.println(jsonData);
	}

}
