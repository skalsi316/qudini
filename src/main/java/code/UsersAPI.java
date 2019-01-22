package code;

import static java.util.stream.Collectors.toList; 

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.nio.charset.Charset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import code.beans.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.codec.binary.Base64;

import java.util.Comparator;
import java.util.stream.Collectors;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class UsersAPI {

    private static final Logger log = LoggerFactory.getLogger(UsersAPI.class);

    static final String URL = "https://github.com/skalsi316/restapi/";
    
    public static void main(String[] args) {
    
    	String s="", name="", duetime="", jointime="";
    	long id=0;

    	//import the path of the json file
    	Scanner sc = new Scanner(System. in);
    	System.out.println("Enter the path of the filename: ");
    	s = sc.nextLine();	//Example is "src/main/java/code/customers.json"

		RestTemplate restTemplate = new RestTemplate();

    	try {
    		JSONParser parser = new JSONParser();
    		JSONArray a = (JSONArray) parser.parse(new FileReader(s));

			List<UsersOptions> list = new ArrayList<UsersOptions>();
					
			UsersOptions usersOptions = new UsersOptions(id, name, duetime, jointime);
    		for (Object o : a) {
    			JSONObject users = (JSONObject) o;
    			duetime = (String) users.get("duetime");    		    
    		    id = (long) users.get("id");
    			name = (String) users.get("name");
    			jointime = (String) users.get("jointime");
    			
    			list.add(new UsersOptions(id, name, duetime, jointime));
    			

    		}

    		//sort the list by duetime using Instream API
    		List<UsersOptions> slist = list.stream().sorted(Comparator.comparing(UsersOptions::getDuetime)).collect(Collectors.toList());

    		/**
			slist.forEach(e -> 
				log.info("Id:"+ e.getId()+", Name: "+e.getName()+", DueTime:"+e.getDuetime())
				);
    		 */
    		for(UsersOptions oList : slist) { 
    		
    			log.info("UsersOptions: " + oList.toString());
    			
    			//Data sorted by date is attached to the request.
    			HttpEntity<UsersOptions> requestBody = new HttpEntity<>(oList);
   	 
    			// Send request with POST method.
    			ResponseEntity<UsersOptions> result = restTemplate.postForEntity(URL, requestBody, UsersOptions.class);
   	 
    			log.info("Status code:" + result.getStatusCode());
   	 
    			// Code = 200.
    			if (result.getStatusCode() == HttpStatus.OK) {
    				UsersOptions e = result.getBody();
    				System.out.println("User Created: "+ e.getId());
    			}
    		}
    	}
    	
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
}