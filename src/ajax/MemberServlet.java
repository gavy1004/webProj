package ajax;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.DBCon;

@WebServlet("/jquery/MemberServlet.do")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 조회작업.
		String sql = "select * from member";
		Connection conn = DBCon.getConnect();
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print("<h2>정상적으로 조회되었습니다.</h2>");

		// 조회sq;을 작성해 보세요
		// 조회결과를 json형식으로 작성해 보세요
		// [{"id":1, "name":"hong", "age":20},
		// {"id":1, "name":"hong", "age":20},
		// {"id":1, "name":"hong", "age":20}]

		// 결과를 response.getWriter().print();로 출력해 보세요
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 입력작업.
		// 사용자에게 입력받은 값 (받아오는 값)
		String p1 = request.getParameter("m_id");
		String p2 = request.getParameter("m_name");
		String p3 = request.getParameter("m_age");

		String sql = "insert into member values(?,?,?)";
		Connection conn = DBCon.getConnect();
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(sql);
			// db입력되는 값
			psmt.setString(1, p1);
			psmt.setString(2, p2);
			psmt.setString(3, p3);
			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력.");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
		response.getWriter().print("<h2>정상적으로 입력되었습니다.</h2>");

	}

}
