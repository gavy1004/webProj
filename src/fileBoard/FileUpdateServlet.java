package fileBoard;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/FileUpdateServlet")
public class FileUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FileUpdateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("num");	//num를 읽어와 id변수에 담음
		int filenum = Integer.parseInt(num);
		
		FileDAO dao = new FileDAO();
		//dao.updateFile(filenum);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
