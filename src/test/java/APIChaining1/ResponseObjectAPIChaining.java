package APIChaining1;

import static io.restassured.RestAssured.given;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ResponseObjectAPIChaining {
	
	String bearerToken = "c5e83b4dc99bd239341cc4148ff43677c371afd14d975b76745c74cfb2c83acc";
	int id;
	
	@Test(priority=1)
	void test_createUser() {
		
		Response res = given()
					.contentType(ContentType.JSON)
					.headers("Authorization", "Bearer " + bearerToken)
					.body(getGenerateData().toString())
				.when()
					.log().all()
					.post("https://gorest.co.in/public/v2/users");
		Assert.assertEquals(res.statusCode(),201);
				 id = res.jsonPath().get("id");
				System.out.println("User id is :" + id);
	}

	@Test(priority=2)
	void test_getUser() {
		
		Response res1 = given()
							.contentType(ContentType.JSON)
							.headers("Authoriztaion","Bearer "+bearerToken)
							.pathParam("id",id)				
						.when()
			  				.get("https://gorest.co.in/public/v2/users/{id}");
		
		String name = res1.jsonPath().get("name");
		String email= res1.jsonPath().get("email");
		System.out.println("User Details is :"+name +" , " + email);
		Assert.assertEquals(res1.statusCode(),200);
					
	}
	
	@Test(priority=3)
	void test_updateUser() {
		 
		Response res2 = given()
				.contentType(ContentType.JSON)
				.headers("Authorization", "Bearer " + bearerToken)
				.body(getGenerateData().toString())
				.pathParam("id",id)
			.when()
				.put("https://gorest.co.in/public/v2/users/{id}");
		
		Assert.assertEquals(res2.statusCode(),200);
		String name = res2.jsonPath().get("name");
		String email = res2.jsonPath().get("email");
		
		System.out.println("Change data :" + name +" , " + email);				
	}
	
	@Test(priority=4)
	void test_deleteUser() {
		
			given()
				.headers("Authorization", "Bearer " + bearerToken)
				.pathParam("id",id)
				.contentType("application/json")
			.when()
				.delete("https://gorest.co.in/public/v2/users/{id}")
			.then()
				.log().all()
				.statusCode(204);				
	}
	
	public JSONObject getGenerateData() {
		 		 
		Faker faker = new Faker();
		JSONObject data = new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "inactive");
		return data;
	}

}
