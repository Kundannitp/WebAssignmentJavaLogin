package Servelet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServelet
 */
@WebServlet("/LoginServelet")
public class LoginServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String name=req.getParameter("Username");
		String password=req.getParameter("password");
		String query="SELECT * FROM users";
		try {
			Class.forName(Admin.connector);
			Connection ct=DriverManager.getConnection(Admin.url,Admin.username,Admin.password);
			Statement st=ct.createStatement();
			ResultSet rs= st.executeQuery(query);
			while(rs.next()) {
				if(name.equals(rs.getString("uid"))&&password.equals(rs.getString("psw"))) {
					HttpSession http=req.getSession();
					http.setAttribute("Username", name);
					RequestDispatcher ds = req.getRequestDispatcher("home.html");
					ds.include(req, res);
				}
			}
			PrintWriter out=res.getWriter();
			out.println("Invalid Username or password");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
