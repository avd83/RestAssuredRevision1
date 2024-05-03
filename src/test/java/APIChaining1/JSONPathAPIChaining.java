package APIChaining1;

import static io.restassured.RestAssured.given;
import org.json.JSONObject;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class JSONPathAPIChaining {
	
	String bearerToken = "c5e83b4dc99bd239341cc4148ff43677c371afd14d975b76745c74cfb2c83acc";
	String id;
	
	@Test(priority=1)
	void createUser() {
		
		String res = given()
					.contentType(ContentType.JSON)
					.headers("Authorization", "Bearer " + bearerToken)
					.body(getGenerateData().toString())
				.when()
					.log().all()
					.post("https://gorest.co.in/public/v2/users")
				.then()
				    .statusCode(201)
					.assertThat().extract().response().asString();

		JsonPath jp = new JsonPath(res);
		id = jp.getString("id");
		System.out.println("New User Id is :" + id);
	}

	@Test(priority=2)
	void getUser() {
		
		String res1 = given()
			.contentType(ContentType.JSON)
			.headers("Authoriztaion","Bearer "+bearerToken)
			.pathParam("id",id)						
		.when()
			.log().all()
			.get("https://gorest.co.in/public/v2/users/{id}")				
		.then()
			.assertThat()
			//.statusCode(200)
			.extract().response().body().asString();
		
		System.out.println("response of GEt post is:" + res1);
		
		
		JsonPath jp = new JsonPath(res1.toString());
		
		String name1 = jp.getString("name");
		String email1 = jp.getString("email");	
		System.out.println("View User Details is :"+name1 +" , " + email1);		
	}
	
	@Test(priority=3)
	void updateUser() {
		
		String res2 = given()
				.contentType(ContentType.JSON)
				.headers("Authorization", "Bearer " + bearerToken)
				.body(getGenerateData().toString())
				.pathParam("id",id)
			.when()
				.log().all()
				.put("https://gorest.co.in/public/v2/users/{id}")				
			.then()
				.statusCode(200)
				.assertThat().extract().response().asString();
		
		JsonPath jp = new JsonPath(res2);
		String name = jp.getString("name");
		String email = jp.getString("email");
		
		System.out.println("Updated detials : " + name +" , " + email);		
	}
	
	@Test(priority=4)
	void deleteUser() {
		
			given()
				.headers("Authorization", "Bearer " + bearerToken)
				.pathParam("id",id)
				.contentType("application/json")
			.when()
				.log().all()
				.delete("https://gorest.co.in/public/v2/users/{id}")				
			.then()
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
