package wasdev.sample.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OneTimePassword
 */
@WebServlet("/OneTimePassword")
public class OneTimePassword extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// Get the sent OTP from user session
    	String otp = "1234";
    	
    	// Send the OTP as response for validation
        response.setContentType("text/html");
        response.getWriter().print(otp);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String phoneNumber = request.getParameter("phone");
    	System.out.println("**** XXXX **** The phone number provided is : " + phoneNumber);
    	
    	// Generate a random 4 digit numberic OTP
    	String otp = "1234";
    	
    	// Send an sms to the phone number provided
    	
    	
        // Store the sent otp in user session
        

        
        response.setContentType("text/html");
        response.getWriter().print("Sent");
        
    }

}
