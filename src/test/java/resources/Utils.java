package resources;


import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

//import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	//multiple values(parameterizing) in the feature examples will be displayed by making Request Specification static and by if statement
	
	public static RequestSpecification req;
	public RequestSpecification requestSpecfication() throws IOException {
		if (req==null) {
		PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));

		//RestAssured.baseURI="https://rahulshettyacademy.com";
		
			 req=new RequestSpecBuilder().setBaseUri(getGlobalvalue("baseURI")).setContentType(ContentType.JSON)
		.addFilter(RequestLoggingFilter.logRequestTo(log))
		.addFilter(ResponseLoggingFilter.logResponseTo(log))
		.addQueryParam("key", "qaclick123").build();
		 return req;
		}
		return req;
	}
	//anytime the BaseUrl will change so better to make getGlobalvalue(String key)
	
	public static String getGlobalvalue(String key) throws IOException {
	 Properties prop= new Properties();
	 FileInputStream fis= new FileInputStream("C:\\Users\\nithy\\eclipse-workspace\\ENUM\\src\\test\\java\\resources\\global.properties");
	 prop.load(fis);
	 return prop.getProperty(key);
	 
	}
	
	
	//Response response is the result extracted from the post method
	public String getJsonPath(Response response,String key) {
		String responseString= response.asString();
		JsonPath js=new JsonPath(responseString);
		return js.get(key).toString();
		
	}
}
