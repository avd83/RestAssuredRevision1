package CookiesAndHeader;

import static io.restassured.RestAssured.given;
import java.util.Map;
import org.testng.annotations.Test;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TestCookies {
	
	@Test(priority=1)
	void getSingleCookie() {

		Response res = given()
				       .when().get("https://www.google.com");

		String cookieValue = res.cookie("AEC");
		System.out.println(cookieValue);

	}
	
	@Test(priority=2)
	void getMultipleCookies() {

		Response res = given()
				       .when().get("https://www.google.com");

		Map<String, String> cookies = res.getCookies();
		
		for(Map.Entry<String, String > c :cookies.entrySet()) {
			
			System.out.println(c.getKey()+"==>"+c.getValue());			
		}
	}
	

}
