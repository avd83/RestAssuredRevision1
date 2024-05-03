package JSONAndXMLSchemaValidation;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

public class JsonSchemaValidate {

	@Test(priority = 1)
	void getValidateJSONSchema() {

		given()
					
		.when()
			.get("http://localhost:3000/store")
		
		.then()
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storeShema.json"));
	}
	
}
