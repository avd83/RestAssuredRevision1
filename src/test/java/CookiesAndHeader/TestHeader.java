package CookiesAndHeader;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TestHeader {

	@Test(priority=1)
	void getSingleHeader() {

		Response res = given()
				       .when().get("https://www.google.com");

		String headerValue = res.getHeader("Content-type");
		System.out.println(headerValue);

	}
	
	@Test(priority=2)
	void getMultipleHeaders() {

		Response res = given()
				       .when().get("https://www.google.com");

		Headers headers = res.getHeaders();
		
		for(Header hd:headers) {
			System.out.println(hd.getName()+"==> " + hd.getValue());
		}

	}

}
