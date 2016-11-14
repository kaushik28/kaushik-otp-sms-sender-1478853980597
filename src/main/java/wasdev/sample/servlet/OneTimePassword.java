package wasdev.sample.servlet;

import java.io.IOException;
import java.lang.Math;
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
    public static Map<String, String> ONE_TIME_PASSWORDS = new HashMap<String, String>();
    	
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String phoneNumber = request.getParameter("phone");
    	
    	// Get the sent OTP from map
    	String otp = ONE_TIME_PASSWORDS.get(phoneNumber);
    	
    	// Send the OTP as response for validation
        response.setContentType("text/html");
        response.getWriter().print(otp);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Sms message = null;
    	String phoneNumber = request.getParameter("phone");
    	System.out.println("**** XXXX **** The phone number provided is : " + phoneNumber);
    	
    	// Generate a random 4 digit numberic OTP
    	int randomPIN = (int)(Math.random()*9000)+1000;
    	String otp = String.valueOf(randomPIN);
    	
    	// Send an sms to the phone number provided
    	TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
		Map<String, String> params = new HashMap<String, String>();
		
		// Update with your Twilio number 
		params.put("From", "+16305998910");
		params.put("Body", "For loging into OpenBanking your One Time Password (OTP) is:"+otp);
		params.put("To", phoneNumber);
    	
		SmsFactory messageFactory = client.getAccount().getSmsFactory();
		try {
		message = messageFactory.create(params);
		}
		catch (TwilioRestException e) {
			throw new ServletException(e);
		}
    	
    	String sid = message.getSid();
    	System.out.println("**** XXXX **** sid returned is : " + sid);

        // Store the sent otp in map
        ONE_TIME_PASSWORDS.put(phoneNumber, otp);
        
        response.setContentType("text/html");
        response.getWriter().print("Sent, sid="+sid);
        
    }

}
