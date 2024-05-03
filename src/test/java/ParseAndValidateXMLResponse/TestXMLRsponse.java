package ParseAndValidateXMLResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class TestXMLRsponse {
	
	@Test(priority = 1)
	void getValidateResponseBody1() {

		given()
					
		.when()
			.get("https://thetestrequest.com/authors.xml")
		
		.then()
			.statusCode(200)
			//.header("Content-Type", "application/xml; charset=utf-8")
			.body("objects.object[0].name", equalTo("Karl Zboncak"))
			.body("objects.object[0].email", equalTo("viva@keebler.biz"));
	}
	
	@Test(priority = 2)
	void getValidateResponseBody2() {

		Response res = 
					given()
					
					.when()
						.get("https://thetestrequest.com/authors.xml");
		
		Assert.assertEquals(res.statusCode(),200);
		Assert.assertEquals(res.header("Content-Type"), "application/xml; charset=utf-8");
		
		String name = res.xmlPath().get("objects.object[0].name").toString();
		Assert.assertEquals(name, "Karl Zboncak");
		
	}
	
	@Test(priority = 2)
	void getValidateResponseBody3() {

		Response res = 
					given()
					
					.when()
						.get("https://thetestrequest.com/authors.xml");
		
		XmlPath xp = new XmlPath(res.asString());
		
		List<String> names = xp.getList("objects.object.name");
		Assert.assertEquals(names.size(), 5);
		
		boolean status =false;
		for(String s :names) {	
			System.out.println(s);
			if(s.equals("Karl Zboncak")) {
			status=true;
			break;
			}
		}
		Assert.assertEquals(status,true );
	}
}
