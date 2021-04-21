package common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO {
	Connection conn;
	Statement stmt;
	ResultSet rs;

	public List<Employee> getempList() {
		// 사원 정보를 가지고 오는 처리.
		String sql = "select * from employees";
		DBCon.getConnect();
		conn = DBCon.getConnect();
		List<Employee> employees = new ArrayList<Employee>();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLasttName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setSalary(rs.getInt("salary"));

				employees.add(emp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return employees;
	}
}
