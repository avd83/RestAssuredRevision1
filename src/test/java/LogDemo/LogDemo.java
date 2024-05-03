package LogDemo;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

public class LogDemo {
	
	@Test(priority=1)
	void getLogAll() {

		 given()
		 .when().get("https://www.google.com")
		 .then()
		 .log().all();
		 //.headers();
		// .cookies();
		 //.body();
	}
}