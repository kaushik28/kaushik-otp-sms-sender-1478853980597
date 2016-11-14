package wasdev.sample.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
*/
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Sms;

/*
import java.util.*;
import com.twilio.sdk.*;
import com.twilio.sdk.resource.factory.*;
import com.twilio.sdk.resource.instance.*;
import com.twilio.sdk.resource.list.*;
*/
/**
 * Servlet implementation class OneTimePassword
 */
@WebServlet("/OneTimePassword")
public class OneTimePassword extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String ACCOUNT_SID = "AC8554f7c6b06d1279cb1d50e2bc29c832";
    public static final String AUTH_TOKEN = "7a5a9ed173757e3258098d97e11f331f";
    	
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
    	TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
    	
	    // Build a filter for the SmsList
		Map<String, String> params = new HashMap<String, String>();
		
		// Update with your Twilio number 
		params.put("From", "+16305998910");
		params.put("Body", "1234");
		params.put("To", "+919886034438");
    	
		SmsFactory messageFactory = client.getAccount().getSmsFactory();
		try {
		message = messageFactory.create(params);
		}
		catch (TwilioRestException e) {
			throw new ServletException(e);
		}
    	
/*
    	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    	Message message = Message.creator(new PhoneNumber("+919886034438"), new PhoneNumber("+16305998910"), otp).create();
*/

    	String sid = message.getSid();
    	System.out.println("**** XXXX **** sid returned is : " + sid);


        // Store the sent otp in user session
        

        
        response.setContentType("text/html");
        response.getWriter().print("Sent, sid="+sid);
        
    }

}
