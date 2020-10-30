package com.manywho.services.mdm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Base64;
import java.util.logging.Logger;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

import com.manywho.services.mdm.ServiceConfiguration;


public class AtomsphereAPI {
	static Logger logger = Logger.getLogger(AtomsphereAPI.class.getName());
    //TODO queryMore queryToken uses just a text blob, not jsonobject so we need to support passing a string payload
    //APIM https://api.boomi.com/apim/api/rest/v1/{accountID}
    public static Document executeAPIXML(ServiceConfiguration configuration, String entityName, String method, String resource, String payload) 
	{
		if (resource!=null)
			resource="/"+resource;
		else 
			resource="";
		String urlString = "https://api.boomi.com/mdm/api/rest/v1/";
		StringBuffer response= new StringBuffer();
        HttpURLConnection conn=null;
		
        try {
        	if (configuration.getApiSleepTime()>0)
        		Thread.sleep(configuration.getApiSleepTime()); //Boomi Default Rate Limit is 1 call per 200ms   	
    		URL url = new URL(urlString+String.format("%s/%s%s", configuration.getAccount(), entityName, resource));
    		logger.info(method + " " + url.toString());
            conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(method);
	        conn.setDoOutput(true);
	        conn.setRequestProperty("Content-Type", "application/xml");
	        conn.setRequestProperty("Accept", "application/xml");
	    	String userpass = configuration.getUsername() + ":" + configuration.getPassword();
	    	String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
	    	conn.setRequestProperty ("Authorization", basicAuth);
	    	if (payload!=null)
	    	{
		        conn.setDoInput(true);
		        logger.info(payload);
		        byte[] input = payload.getBytes();
		 		conn.setRequestProperty("Content-Length", ""+input.length);
		        OutputStream os = conn.getOutputStream();
		        os.write(input, 0, input.length);  
	    	}  	    		
	 
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())) ;
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
			    response.append(responseLine+"\r\n");
			}
			logger.info(response.toString());
		} catch (ProtocolException e1) {
			throw new RuntimeException(e1);
		} catch (InterruptedException e1) {
			throw new RuntimeException(e1);
		} catch (IOException e) {
			try {
				if (conn!=null)
				{
					response.append("Error code: " + conn.getResponseCode());
					response.append(" " + conn.getResponseMessage() + " ");
					InputStream errorStream = conn.getErrorStream();
					if (errorStream!=null)
					{
						BufferedReader br = new BufferedReader(new InputStreamReader(errorStream)) ;
						String responseLine = null;
						while ((responseLine = br.readLine()) != null) {
						    response.append(responseLine+"\r\n");
						}
					}
//					logger.error(response.toString());
					throw new RuntimeException(response.toString());
				} else throw new RuntimeException(e);
			} catch (IOException e1) {
				throw new RuntimeException(e1);
			}
		}
//	    LOGGER.info(response.toString());
        String responseString = response.toString();
		Document doc=null;

		try {
			if (responseString != null && responseString.length()>0)
				doc = DocumentHelper.parseText(responseString);
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
        return doc;
	}
}
