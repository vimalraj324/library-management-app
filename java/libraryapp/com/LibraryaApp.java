package libraryapp.com;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.dConnection.PostgresJDBC;

/**
 * Servlet implementation class LibraryaApp
 */
@WebServlet("/libraryapp")
public class LibraryaApp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibraryaApp() {
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
		HttpSession s=request.getSession(false);
		String bookrented=request.getParameter("book");
		String email=(String)s.getAttribute("session_email");
		
		try {
			PostgresJDBC jdbc=new PostgresJDBC();
			Connection con= jdbc.getConnection();
			PreparedStatement psmt=con.prepareStatement("UPDATE public.user_table set book_rented=? WHERE user_email=?");
			psmt.setString(1, bookrented);
			psmt.setString(2, email);
			psmt.executeUpdate();
			 
			psmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		response.getWriter().append("selected book were added under your name:").append(email);
		}
	}

