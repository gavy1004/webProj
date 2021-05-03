package plugin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.EmpDAO;
import common.ScheduleVO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/scheduleServlet")
public class ScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ScheduleServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// [ {"title":"Meeting","startDay":"2021-05-01"..}, { }, { }.... { }]
		EmpDAO dao = new EmpDAO();
		List<ScheduleVO> list =dao.getScheduleList();
		
		JSONArray ary = new JSONArray();
		for (ScheduleVO vo : list) {
			JSONObject obj = new JSONObject();
			obj.put("title", vo.getTitle());
			obj.put("start", vo.getStartDay());
			obj.put("end", vo.getEndDay());
			ary.add(obj);
		}
		response.getWriter().print(ary);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한건 입력... title, start, end
		String title = request.getParameter("title");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		
		ScheduleVO vo = new ScheduleVO();
		vo.setTitle(title);
		vo.setStartDay(start);
		vo.setEndDay(end);
		
		EmpDAO dao = new EmpDAO();
		dao.insertSchedule(vo);
	}

}
