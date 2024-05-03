package JSONAndXMLSchemaValidation;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;
import io.restassured.matcher.RestAssuredMatchers;
import static org.hamcrest.Matchers.*;

public class XmlSchemaValidate {
	
	@Test(priority = 1)
	void getValidateXMLSchema() {

		given()
					
		.when()
			.get("https://thetestrequest.com/authors.xml")
		
		.then()
			.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("xmlSchema.xsd"));
	}	
}