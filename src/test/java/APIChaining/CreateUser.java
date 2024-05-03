package APIChaining;

import static io.restassured.RestAssured.given;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

public class CreateUser {
	
	@Test
	void test_createUser(ITestContext context) {
				
		String bearerToken = "c5e83b4dc99bd239341cc4148ff43677c371afd14d975b76745c74cfb2c83acc";
		
		Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		
		data.put("name",faker.name().fullName());
		data.put("gender","Male");
		data.put("email",faker.internet().emailAddress());
		data.put("status","inactive");		
		
	int id = given()
				.contentType("application/json")
				.headers("Authorization","Bearer "+bearerToken)
				.body(data.toString())			
			.when()
				.post("https://gorest.co.in/public/v2/users")
				.jsonPath().getInt("id");
	
	System.out.println("Id is :" + id);
	
	//context.setAttribute("user_id",id);
	context.getSuite().setAttribute("user_id",id);	
	}
}
