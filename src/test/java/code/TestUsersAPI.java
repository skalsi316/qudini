package code;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import java.net.*;
import code.beans.*;

public class TestUsersAPI {

	final String baseUrl = "https://github.com/skalsi316/restapi/";

	@Test
	public void TestAPI() throws URISyntaxException {
		URI uri = new URI(baseUrl);
		
		RestTemplate restTemplate = new RestTemplate();
     		
		UsersOptions user = new UsersOptions(1, "Sunita", "2018-07-03T21:53:42-07:00", "2018-07-03T21:53:42-07:00");
		
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<UsersOptions> entity = new HttpEntity<UsersOptions>(user, headers);

		try
	    {
			ResponseEntity<UsersOptions> result = restTemplate.postForEntity(uri, entity, UsersOptions.class);
	        fail();
	    }
	    catch(HttpClientErrorException ex)
	    {
	    	assertEquals(422,ex.getRawStatusCode());
	    }
	}
}