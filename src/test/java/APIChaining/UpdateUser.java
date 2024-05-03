package APIChaining;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUser {
	
	@Test
	void test_updateUser(ITestContext context) {
		
		//int id=(int) context.getAttribute("user_id");
		int id=(int) context.getSuite().getAttribute("user_id");
		String bearerToken = "c5e83b4dc99bd239341cc4148ff43677c371afd14d975b76745c74cfb2c83acc";		
		
		Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		data.put("name",faker.name().fullName());
		data.put("gender","Male");
		data.put("email",faker.internet().emailAddress());
		data.put("status","active");	
		
		given()
			.headers("Authorization","Bearer "+bearerToken)
			.contentType("application/json")
			.body(data.toString())	
			.pathParam("id",id)
		.when()
			.put("https://gorest.co.in/public/v2/users/{id}")		
		.then()
			.statusCode(200)
			.log().all();		
	}

}
