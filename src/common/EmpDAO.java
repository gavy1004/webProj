package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpDAO {
	Connection conn;
	Statement stmt;
	ResultSet rs;
	PreparedStatement psmt;
	
	//resource해제
	public void close() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (psmt != null) {
			try {
				psmt.close();
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
	public Employee insertEmpBySeq(Employee emp) {
		conn = DBCon.getConnect();
		Employee empl = new Employee();

		String sql1 = "select employees_seq.nextval from dual";
		String sql2 = "insert into emp_temp(employee_id, first_name,last_name, email, hire_date, job_id, DEPARTMENT_ID,salary)"
				+ "values(?,?,?,?,?,?,50,?)";
		try {
			int empId = 0;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql1);
			if (rs.next()) {
				empId = rs.getInt(1);
			}
			psmt = conn.prepareStatement(sql2);
			psmt.setInt(1, empId);
			psmt.setString(2, emp.getFirstName());
			psmt.setString(3, emp.getLastName());
			psmt.setString(4, emp.getEmail());
			psmt.setString(5, emp.getHireDate());
			psmt.setString(6, emp.getJobId());
			psmt.setInt(7, emp.getSalary());

			int r = psmt.executeUpdate(); // 쿼리 하는 건수 업데이트
			System.out.println(r + "건 입력됨");
			empl.setEmployeeId(empId);
			empl.setFirstName(emp.getFirstName());
			empl.setLastName(emp.getLastName());
			empl.setEmail(emp.getEmail());
			empl.setSalary(emp.getSalary());
			empl.setHireDate(emp.getHireDate());
			empl.setJobId(emp.getJobId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return empl; // 여기서 시작
	}

	public void insertEmp(Employee emp) {
		String sql = "insert into emp_temp(employee_id,last_name,email,hire_date,job_id)"
				+ "values((select max(employee_id)+1 from emp_temp),?,?,?,?)";
		conn = DBCon.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, emp.getLastName());
			psmt.setString(2, emp.getEmail());
			psmt.setString(3, emp.getHireDate());
			psmt.setString(4, emp.getJobId());

			int r = psmt.executeUpdate(); // 쿼리 하는 건수 업데이트
			System.out.println(r + "건 입력됨");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

	}

	public List<Employee> getEmpByDept(String dept) {
		// 사원 정보를 가지고 오는 처리.
		String sql = "select * from emp_temp where department_id = " + dept + "order by employee_id";
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
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setSalary(rs.getInt("salary"));
				emp.setHireDate(rs.getString("hire_date"));
				emp.setJobId(rs.getString("job_id"));

				employees.add(emp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return employees;
	}

	public List<Employee> getempList() {
		// 사원 정보를 가지고 오는 처리.
		String sql = "select * from emp_temp order by employee_id";
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
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setSalary(rs.getInt("salary"));
				emp.setHireDate(rs.getString("hire_date"));
				emp.setJobId(rs.getString("job_id"));

				employees.add(emp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return employees;
	}

	// empl_demo
	public List<Employee> getEmployeeList() {
		String sql = "select * from empl_demo order by employee_id";
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
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setSalary(rs.getInt("salary"));
				emp.setHireDate(rs.getString("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				emp.setPhoneNumber(rs.getString("phone_number"));

				employees.add(emp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return employees;
	}

	public Map<String, Integer> getEmployeeByDept() {
		// 부서명, 사원수
		Map<String, Integer> map = new HashMap<>();
		// map.put("부서",20);
		// 부서별 사원수를 차트 sql
		String sql = "select d.department_name, count(1)\r\n" 
				+ "from empl_demo e , departments d\r\n"
				+ "where e.department_id = d.department_id\r\n" 
				+ "group by department_name";
		conn = DBCon.getConnect();

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				map.put(rs.getString(1), rs.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return map;
	}
	
	// google calendar
	// 스케쥴 정보를 가지고 오는 메소드.. 5.3 
	public List<ScheduleVO> getScheduleList() {
		conn = DBCon.getConnect();
		
		String sql = "select * from schedule";
		List<ScheduleVO> list = new ArrayList<>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				ScheduleVO vo = new ScheduleVO();
				vo.setTitle(rs.getString("title"));
				vo.setStartDay(rs.getString("start_day"));
				vo.setEndDay(rs.getString("end_day"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}
	
	// 스케쥴정보를 db에 입력하는 작업
	public void insertSchedule(ScheduleVO vo) {
		String sql = "insert into schedule values(?,?,?)";
		conn = DBCon.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getStartDay());
			psmt.setString(3, vo.getEndDay());
			
			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력되었습니다");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
	}
	

}
