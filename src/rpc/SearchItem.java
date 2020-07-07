package rpc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.Item;
import external.TicketMasterAPI;

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
		try {
			double lat = Double.parseDouble(request.getParameter("lat"));
			double lon = Double.parseDouble(request.getParameter("lon"));
			String keyword = request.getParameter("term");
			
			TicketMasterAPI tmAPI = new TicketMasterAPI(); 
			List<Item> items = tmAPI.search(lat, lon, keyword);
			
			JSONArray array = new JSONArray();
			
			for(Item item : items) {
				JSONObject obj = item.toJSONObject();
				array.put(obj);
			}
			
			RpcHelper.writeJsonArray(response, array);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
