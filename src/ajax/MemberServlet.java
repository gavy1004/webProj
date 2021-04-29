package ajax;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		// 조회sql을 작성해 보세요
		String sql = "select member_id, member_name, member_age from member";
		Connection conn = DBCon.getConnect();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String json = "[";

		try {
			psmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = psmt.executeQuery();
			while (rs.next()) {
				String memId = rs.getString(1);
				String memName = rs.getString(2);
				int memAge = rs.getInt(3);

				json += "{\"id\":\"" + memId + //
						"\",\"name\":\"" + memName + //
						"\",\"age\":" + memAge + "}";

				if (!rs.isLast())
					json += ",";
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
		json += "]";
		// response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(json);

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
	    String json = "{\"id\":" + p1 + ",\"name\":\""+p2+"\",\"age\":"+p3+"}";
		response.getWriter().print(json);

	}

}
