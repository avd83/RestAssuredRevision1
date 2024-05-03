package PathAndQueryParameter;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

public class PathAndQueryParameter {

	//https://reqres.in/api/users?page=2&id=9
	
	@Test
	void getTest() {
		
		given().pathParam("myPath","users")// path parameter
				.queryParam("page", "2") // query parameter
				.queryParam("id","9") // query parameter		
		.when().get("https://reqres.in/api/{myPath}") // add only path parameter in url.
		
		.then()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 OK")
		.log().all();			
	}	
}