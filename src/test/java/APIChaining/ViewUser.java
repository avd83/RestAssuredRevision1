package APIChaining;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class ViewUser {
	
	@Test
	void test_viewUser(ITestContext context) {
		
		//int id=(int) context.getAttribute("user_id");
		int id=(int) context.getSuite().getAttribute("user_id");	
		
		String bearerToken = "c5e83b4dc99bd239341cc4148ff43677c371afd14d975b76745c74cfb2c83acc";		
		
		given()
			.headers("Authorization","Bearer "+bearerToken )
			.pathParam("id",id)
		
		.when()
			.get("https://gorest.co.in/public/v2/users/{id}")
		
		.then()
			.statusCode(200)
			.log().all();			
	}
}
