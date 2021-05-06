package fileBoard;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/fileUploadServlet")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FileUploadServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		System.out.println("doPost call()");
		String path = "C:/tmp";

		ServletContext sc = this.getServletContext();
		path = sc.getRealPath("upload"); // 서버상 경로
											// 저장위피 파일크기(byte단위) 인코딩 RenamePolicy
		MultipartRequest multi = new MultipartRequest(request, path, 5 * 1024 * 1024, "UTF-8",
				new DefaultFileRenamePolicy());

		Enumeration en = multi.getFileNames();
		String author = multi.getParameter("author");
		String title = multi.getParameter("title");
		String fileN = null;
		while (en.hasMoreElements()) {
			String name = (String) en.nextElement();
			String fileName = multi.getFilesystemName(name);
			fileN = fileName;
			System.out.println("name :" + name + ",filename" + fileName);
		}
		// 입력 후 저장된 값 가져오기
		FileDAO dao = new FileDAO();
		FileVO vo = new FileVO();
		vo.setAuthor(author);
		vo.setFilename(fileN);
		vo.setTitle(title);
		
		FileVO rvo = dao.getInsertKeyVal(vo);
		
		// 가져온 값을 json형식으로 생성 {"num" : ?,"author" : ?, "title" : ? }
		JSONObject obj = new JSONObject();
		obj.put("num", rvo.getNum());
		obj.put("title", rvo.getTitle());
		obj.put("author", rvo.getAuthor());
		obj.put("fileName", rvo.getFilename());
		obj.put("day", rvo.getDay());

		response.getWriter().print(obj);
	}

}
