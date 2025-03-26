package com.hmkcode;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmkcode.vo.Content;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Sending POST to GCM" );
        
        String apiKey = "AIzaSyAQQUlFwB3P4Q3Aa53CdugcCk-Cni_NXxc";
        Content content = createContent();
        
        POST2GCM.post(apiKey, content);
    }
    
    public static Content createContent(){
		
		Content c = new Content();
		
		c.addRegId("APA91bFGN21zgGHxSG73QUi_oU7eqXqozKlt4hUYlkr9czA1z43mT_AeNlFYIbmDh-6pCbtcE06aQbwCHz4xuYm9weNo3xxr75qRM4cvVAmUYBtoG6ldTks42OnpKayHBIBImsfNt2BJ");
		c.createData("Test Title", "Elije el Premio");
		
		return c;
	}
}
