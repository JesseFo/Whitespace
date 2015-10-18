

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ValidateLogin
 */
@WebServlet("/ValidateLogin")
public class ValidateLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//Validation booleans
		boolean userExists = false;
		boolean validated = false;
		boolean nullFields = false;
		
		//Get form values		
		String email = request.getParameter("loginemail");
		String pword = request.getParameter("loginpword");
		
		//File Streams
		Path path = Paths.get("users.txt");
		//This line, though it may seem redundant, ensures the file exists, or creates it if it doesn't
		FileOutputStream oUsers = new FileOutputStream(path.toFile(), true);
		BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString()));
		InputStream iUsers = Files.newInputStream(path);
		BufferedReader reader = new BufferedReader(new InputStreamReader(iUsers));
		LineNumberReader line = new LineNumberReader(reader);
		
		//File checking string
		String temp = "";
		
		//Line counting integer
		int j = 1;
		
		//Session
		HttpSession session = request.getSession();
		
		//Email Validation
		if(!email.isEmpty())
		{
			while ((temp = line.readLine()) != null && validated)
			{
				if(line.getLineNumber() == j)
				{
					if(!userExists)
					{
						if(email.equals(temp))
						{
							userExists = true;
							j += 2;
						}
						else
							j += 4;
					}
					else
					{
						if(pword.equals(temp))
							validated = true;
					}
				}			
			}
		}
		else
			nullFields = true;
		
		if(validated)
		{
			session.setAttribute("email", email);
			response.sendRedirect("posts.html");
		}
		else
		{
			if(!nullFields)
				response.sendError(0);
			else
				response.sendError(1);
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
