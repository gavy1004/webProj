package plugin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/JsonServlet")
public class JsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public JsonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject outObj = new JSONObject();
		JSONArray fAry = new JSONArray();
		fAry.add("Reading");
		fAry.add("Sleeping");
		
		JSONObject fObj = new JSONObject();
		fObj.put("cat", "mew");
		fObj.put("dogt", "bow");
		
		outObj.put("name", "hong");
		outObj.put("hobby", fAry);	//배열
		outObj.put("pet", fObj);	//오브젝트
		
		response.getWriter().print(outObj);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
