package libraryapp.com;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.dConnection.*;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("register.html").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user=request.getParameter("user");
		String pwd=request.getParameter("user_pwd");
		int number = Integer.parseInt(request.getParameter("user_phone"));
		String email=request.getParameter("user_email");
		String bookrented=request.getParameter("book_rented");
		String bookbought=request.getParameter("book_bought");
		String db_email=null,db_name=null,db_pwd=null;
		int db_phone=0;
		PostgresJDBC obj=new PostgresJDBC();
		try {
			//Class.forName("org.postgresql.Driver");
			//Connection con=obj.getConnection();
			Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","root");
			Statement  st=con.createStatement();
			
			PreparedStatement psmt=con.prepareStatement("INSERT INTO public.user_table(user_name,user_email,user_phone,user_pwd,book_rented,book_bought) VALUES (?,?,?,?,?,?);");
			psmt.setString(2, email);
			psmt.setInt(3, number);
			psmt.setString(1, user);
			psmt.setString(4, pwd);
			psmt.setString(5,bookrented);
			psmt.setString(6, bookbought);
			
			ResultSet rs=st.executeQuery("SELECT * FROM public.user_table");
			while(rs.next()) {
				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(4)+" "+rs.getInt(3));
				db_email=rs.getString(2);
				db_name=rs.getString(1);
				db_phone=rs.getInt(3);
				db_pwd=rs.getString(4);
			}
			psmt.executeQuery();
			System.out.println("Record inserted successfully"+user);
			
			con.close();
			//st.close();
			//psmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("index.html").forward(request,response);
		
			}
		
	}

