package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification req;
	
	
	public RequestSpecification requestBuilder() throws IOException {
		if (req==null) {
		PrintStream log = new PrintStream(new FileOutputStream("logs.txt"));
	    req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrI"))
				.setContentType(ContentType.JSON).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log)) // request logging
				.addFilter(ResponseLoggingFilter.logResponseTo(log)) //response logging
				.build();
	 return req;
      }
     return req;
	}
	
	public static String getGlobalValue(String key) throws IOException {
		
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("./src/test/java/resources/global.properties");
		prop.load(fis);
		String value = prop.getProperty(key);
		//System.out.println(value);
		return value ;
		
		
	}
	
	public String getJSONPath(Response response , String key) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		String value = js.getString(key);
		return value;
	}

}
