package RequestAndResponseSpecificationBuilder;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ReqSpec {
	
	String studentId;
	String json;
	
	@Test(priority=1)
	void normalPostStudent() {
	 
		  	given()
		  		.contentType(ContentType.JSON) .body(getData()) 
		  	.when()
		  		.post("http://localhost:3000/students")
		  	.then()
		  		.log().all()
		  		.assertThat().statusCode(201); }
	  
	@Test(priority=2)
	void postReqSpec() {
	  
	  RequestSpecification reqSpec = new RequestSpecBuilder()
	  .setBaseUri("http://localhost:3000/students")
	  .setContentType(ContentType.JSON).build();
	  
	  ResponseSpecification resSpec = new ResponseSpecBuilder()
	  .expectStatusCode(201) .expectContentType(ContentType.JSON).build();
	  
	  String res= given() .spec(reqSpec) .body(getData()) .when() .post() .then()
	  .spec(resSpec)
	  .log().all().assertThat().extract().response().asPrettyString();
	  //.body("name",equalTo("Aueel"));
	 
	 JsonPath jp = new JsonPath(res); 
	 String name = jp.getString("name");
	  System.out.println(name);
	  
	  }
	 

	@Test(priority=3)
	void postPoJoReqSpec() throws JsonProcessingException {
		

		RequestSpecification reqSpec = new RequestSpecBuilder()
				.setBaseUri("http://localhost:3000/students")
				.setContentType(ContentType.JSON).build();

		ResponseSpecification resSpec = new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.expectStatusCode(201).build();

		Response res = given().spec(reqSpec).body(setPoJoData())
				.when().post().then().spec(resSpec).log().all().extract().response();

		JsonPath jp = new JsonPath(res.asString());
		String courseList = jp.getString("courses");
		System.out.println(courseList);
		studentId = jp.getString("id");
		System.out.println(studentId);
	}
	
	@Test(priority=4)
	void getPoJoResSpec() {
		
		RequestSpecification reqSpec = new RequestSpecBuilder()
				.setBaseUri("http://localhost:3000/students/"+studentId)
				.setContentType(ContentType.JSON)
				.build();
		
		ResponseSpecification resSpec = new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.expectStatusCode(200)
				.build();
		
		RequestPoJo res = given()
							.spec(reqSpec)			
						  .when()
						  	.get()			
						  .then()
						  	.spec(resSpec)
						  	.log().all()
						  	.assertThat().extract().response().as(RequestPoJo.class);	
		String name = res.getName();
		System.out.println(name);		
	}
	
	String getData() {
		String data = "{\r\n"
				+ "    \"id\": \"3\",\r\n"
				+ "    \"location\": \"Hungary\",\r\n"
				+ "    \"name\": \"Jerrold Wolf\",\r\n"
				+ "    \"phneno\": \"015-313-3305\",\r\n"
				+ "    \"courses\": [\r\n"
				+ "      \"postman\",\r\n"
				+ "      \"RestAssured\"\r\n"
				+ "    ]\r\n"
				+ "  }";	
		return data;
	}
	
	String setPoJoData() throws JsonProcessingException {
		
		Faker faker = new Faker();
		RequestPoJo reqPoJo = new RequestPoJo();
		int id = faker.number().numberBetween(1, 99);

		reqPoJo.setId(String.valueOf(id));
		reqPoJo.setLocation(faker.address().country());
		reqPoJo.setName(faker.name().fullName());
		reqPoJo.setPhneno(faker.phoneNumber().cellPhone());
		String[] courses = { "postman", "RestAssured" };
		reqPoJo.setCourses(courses);

		ObjectMapper obj = new ObjectMapper();
		json = obj.writeValueAsString(reqPoJo);
		return json;
	}
}
