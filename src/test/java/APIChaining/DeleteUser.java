package APIChaining;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteUser {
	
	@Test
	void test_deleteUser(ITestContext context) {
		
		//int id=(int) context.getAttribute("user_id");// run test in single test refer testng.xml file
		int id=(int) context.getSuite().getAttribute("user_id");// run test in multiple test refer testng1.xml file
		
		String bearerToken = "c5e83b4dc99bd239341cc4148ff43677c371afd14d975b76745c74cfb2c83acc";
		
		given()
			.headers("Authorization","Bearer "+bearerToken)
			.pathParam("id",id)
			.contentType("application/json")
		
		.when()
			.delete("https://gorest.co.in/public/v2/users/{id}")
		
		.then()
			.statusCode(204)
			.log().all();	
	}
}