package Servelet;

import java.io.IOException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis=request.getRequestDispatcher("Signup.html");
		dis.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("Fname")+request.getParameter("Lname");
		String roll=request.getParameter("roll");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String uid=request.getParameter("username");
		String psw=request.getParameter("password");
		String query="INSERT INTO users VALUES(?,?,?,?,?,?)";
		try {
			Class.forName(Admin.connector);
			Connection ct=(Connection) DriverManager.getConnection(Admin.url,Admin.username,Admin.password);
			PreparedStatement st=(PreparedStatement) ct.prepareStatement(query);
			st.setString(1, name);
			st.setString(2, roll);
			st.setString(3, email);
			st.setString(4, phone);
			st.setString(5, uid);
			st.setString(6, psw);
			st.executeUpdate();
			System.out.println("Updated");
			HttpSession http=request.getSession();
			http.setAttribute("Username", uid);
			RequestDispatcher dis=request.getRequestDispatcher("home.html");
			dis.include(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
