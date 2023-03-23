package libraryapp.com;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.dConnection.PostgresJDBC;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user=request.getParameter("user");
		String pwd=request.getParameter("password");
		PostgresJDBC jdbc = new PostgresJDBC();
		String db_email=null,db_name=null,db_pwd=null;
		int db_phone=0;
		
		try(Connection con = jdbc.getConnection()){
			PreparedStatement psmt=con.prepareStatement("select *  from  public.user_table where user_name=? and user_pwd=?");
			psmt.setString(1,user);
			psmt.setString(2,pwd);
			ResultSet rs=psmt.executeQuery();
			//int i=psmt.executeUpdate();
			while(rs.next()) {
				db_email=rs.getString(2);
				db_name=rs.getString(1);
				db_phone=rs.getInt(3);
				db_pwd=rs.getString(4);
			}
			jdbc.closeConnection(psmt, con);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		if(Objects.nonNull(db_email)) {
			HttpSession s=request.getSession(true);
			s.setAttribute("session_email", db_email);
			//s.setAttribute("Books", bookdatalist);
			request.getRequestDispatcher("library.html").forward(request, response);
		}else {
			response.getWriter().append("login failed");
			
			}
				}
	}
