package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	
	public static RequestSpecification request;
	
	public RequestSpecification requestSpecification() throws IOException {
		
		
			
		if(request == null) {
			
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			
			 request = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL")).
		    		addQueryParam("key", "qaclick123").
		    		addFilter(RequestLoggingFilter.logRequestTo(log)).
		    		addFilter(ResponseLoggingFilter.logResponseTo(log)).
		    		setContentType(ContentType.JSON).build();
			
			return request;
		}
		
		return request;
		
	}
	
	public ResponseSpecification responseSpecification() {
		
		ResponseSpecification responseSpecification =  new ResponseSpecBuilder().expectStatusCode(200).
	    		expectContentType(ContentType.JSON).build();
		
		return responseSpecification;
	}
	
	public static String getGlobalValue(String key) throws IOException {
		
		Properties prop = new Properties();
		
		FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/resources/global.properties");
		
		prop.load(fileInputStream);
		
		return prop.getProperty(key);
		
	}
	
	public String getJsonPath(Response response, String key) 
	{
		String resp = response.asString();
		
		JsonPath js = new JsonPath(resp);
		
		return js.get(key).toString();
	}

}
