

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.mysql.jdbc.Connection;

//import jdk.internal.org.xml.sax.InputSource;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
@MultipartConfig
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String name=request.getParameter("user_name");
		String pwd=request.getParameter("user_password");
		String mail=request.getParameter("user_mail");
		response.setContentType("text/html");
		Part part=request.getPart("image");
		PrintWriter out=response.getWriter();
		String filename = part.getSubmittedFileName();
		//out.println(filename);
	//	out.println(name);
		//out.println(pwd);
	//	out.println(mail);
		try {
			Thread.sleep(3000);
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/register","root","vaibhav");
		String g="Insert into user(name,password,mail,image) values(?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(g);
		pstmt.setString(1, name);
		pstmt.setString(2, pwd);
		pstmt.setString(3,mail);
		pstmt.setString(4,filename );
		pstmt.executeUpdate();
		InputStream fis=part.getInputStream();
		byte data[]=new byte[fis.available()];
		fis.read(data);
		String path="D:\\eclipse\\registration\\images"+File.separator+filename;
	//	out.println(path);
		FileOutputStream fos=new FileOutputStream(path);
		
		fos.write(data);
		fos.close();
		out.println("done");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			out.println("errrrrrrrrrrorrrrrrrrrrrrrrrrr");
			
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
