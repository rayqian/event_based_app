package rpc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class SearchItem
 */
@WebServlet("/search")
public class SearchItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		/*
		PrintWriter out = response.getWriter();
		if(request.getParameter("username")!=null) {
			String username = request.getParameter("username");
			out.println("hi there," + username);
		}
		if(request.getParameter("pw")!=null) {
			out.println("password is " + request.getParameter("pw"));
		}
		out.close();
		*/
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String username = "";
		String pw = "";
		if (request.getParameter("username") != null) {
			username = request.getParameter("username");
		}
		if (request.getParameter("pw") != null) {
			pw = request.getParameter("pw");
		}
		JSONObject obj = new JSONObject();
		try {
			obj.put("username", username);
			obj.put("password", pw);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		out.print(obj);
		out.close();   
  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
