/*
 * Project: COMP 3095 Message Board
 * Assignment: Assignment #1
 * Author: Jesse Fogel
 * Student Number: 100893486
 * Date: 17/10/2015
 * Description: This servlet is called when the user presses the register button. It validates the data
 * that the users enters, ensuring all data is entered, and properly formatted. Assuming it is correct,
 * this servlet will save the user data to the database (currently users.txt) 
*/

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ValidateRegister
 */
@WebServlet("/ValidateRegister")
public class ValidateRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateRegister() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		//Error Booleans
		boolean nullFields = false;
		boolean formatError = false;
		boolean userExists = false;
		
		//Regex Strings
		String alpha = "^(?:\\W||\\s)*$";				//These strings are patterns used by the String.matches
		String numeric = "^(?:\\D||\\s)*$";				//function, to determine whether the strings are incorrectly
														//formatted
		//Get form values
		//Name
		String fName = request.getParameter("fname");
		String lName = request.getParameter("lname");
		
		//Email
		String email = request.getParameter("email");
		String email2 = request.getParameter("email2");
		
		//Phone
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		
		//Password
		String pword = request.getParameter("pword");
		String pword2 = request.getParameter("pword2");
		
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
		
		//Email Validation
		//This must be done first, as users are identified by their unique email address
		//Doing Email Validation first allows us to ensure user does not already exist
		//before storing user's registration data
		if(!email.isEmpty())
		{
			while ((temp = line.readLine()) != null)
			{
				if(line.getLineNumber() == j)
				{
					j += 4;
					if(email.equals(temp))
						userExists = true;
				}
				
			}
		}			
		
		//Assuming the user doesn't exist, proceed.
		if(userExists)
			response.sendError(0);
		else
		{
			//Check if any fields are empty
			if(fName.isEmpty() || lName.isEmpty() || email.isEmpty() || email2.isEmpty() || phone1.isEmpty()
					|| phone2.isEmpty() || phone3.isEmpty() || pword.isEmpty() || pword2.isEmpty())
				nullFields = true;
			
			//Check if any fields are incorrectly formatted
			if(fName.matches(alpha) || lName.matches(alpha) || phone1.matches(numeric) || phone2.matches(numeric)
					|| phone3.matches(alpha) || pword.matches(alpha) || pword2.matches(alpha))
				formatError = true;
			
			if (nullFields && !formatError)
				response.sendError(1);
			else if (!nullFields && formatError)
				response.sendError(2);
			else if (nullFields && formatError)
				response.sendError(3);
			else
				writer.write(lName + ", " + fName + System.lineSeparator() + email
						+ System.lineSeparator() + "(" + phone1 + ") " + phone2 + "-" + phone3
						+ System.lineSeparator());
				response.sendRedirect("posts.html");
		}
	}

}
