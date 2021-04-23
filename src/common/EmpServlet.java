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

		String dept = req.getParameter("dept"); // dept선언????????

		EmpDAO dao = new EmpDAO();

		List<Employee> list = null;

		if (dept == null) {
			list = dao.getempList();
		} else {
			list = dao.getEmpByDept(dept);
		}

		String jsonData = "[";
		// [{"empId":"?", "fname":"?" ,"lname":"?"} ... ]
		int cnt = 0;
		for (Employee emp : list) {
			jsonData += ("{\"empId\":\"" + emp.getEmployeeId()//
					+ "\", \"fname\":\"" + emp.getFirstName() //
					+ "\", \"lname\":\"" + emp.getLastName() //
					+ "\", \"email\":\"" + emp.getEmail() //
					+ "\", \"salary\":\"" + emp.getSalary() //
					+ "\", \"hireDate\":\"" + emp.getHireDate() //
					+ "\", \"jobId\":\"" + emp.getJobId() //
					+ "\"}");
			if (++cnt == list.size()) {
				continue;
			}
			jsonData += ", \n";
		}
		jsonData += "]";
		out.println(jsonData);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstName = req.getParameter("first_name");
		String lastName = req.getParameter("last_name");
		String email = req.getParameter("email");
		String salary = req.getParameter("salary");
		String hireDate = req.getParameter("hire_date");
		String jodId = req.getParameter("job_id");
		
		int sal = Integer.parseInt(salary);
		
		Employee emp = new Employee();
		emp.setFirstName(firstName);
		emp.setLastName(lastName);
		emp.setEmail(email);
		emp.setSalary(sal);
		emp.setHireDate(hireDate);
		emp.setJobId(jodId);

		EmpDAO dao = new EmpDAO();
		Employee empl = dao.insertEmpBySeq(emp);
		// {"eid":"?","fname":"?","lname":"?"...........}

		PrintWriter out = resp.getWriter();
		out.println("{\"employee_id\":\"" + empl.getEmployeeId() + "\","//
				+ "\"first_name\":\"" + empl.getFirstName() + "\","//
				+ "\"last_name\":\"" + empl.getLastName() + "\","//
				+ "\"email\":\"" + empl.getEmail() + "\","//
				+ "\"hire_date\":\"" + empl.getHireDate() + "\","//
				+ "\"salary\":\"" + empl.getSalary() + "\","//
				+ "\"job_id\":\"" + empl.getJobId() + "\""//
				+ "}");

	}

}
