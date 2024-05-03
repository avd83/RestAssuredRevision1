package ParseAndValidateJSONResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestJsonResponse {

	@Test(priority = 1)
	void getValidateResponseBody1() {

		given().contentType(ContentType.JSON)
		.when().get("http://localhost:3000/store").then()
				// .log().all()
			.statusCode(200)
			// .header("Content-Type","application/json")
			.body("books[1].title", equalTo("The lord of the rings"));
	}

	@Test(priority = 2)
	void getValidateResponseByResponseObject() {

		Response res = given().contentType(ContentType.JSON)
				.when().get("http://localhost:3000/store");

		System.out.println(res.getContentType().toString());
		Assert.assertEquals(res.getStatusCode(),200);
		Assert.assertEquals(res.header("Content-Type"),"application/json");
		
		String bookTitle = res.jsonPath().get("books[1].title").toString();
		Assert.assertEquals(bookTitle,"The lord of the rings");
	}
	
	@Test(priority = 3)
	void getValidateResponseByJsonObject() {

		Response res = given().contentType(ContentType.JSON)
				.when().get("http://localhost:3000/store");

		JSONObject jo = new JSONObject(res.asString()); // convert response from  object to String
		
		boolean status = false;
		for(int i=0;i<jo.getJSONArray("books").length();i++) 
		{			
			String bookTitle = jo.getJSONArray("books").getJSONObject(i).get("title").toString();
			System.out.println("The title of the books are:==> " + bookTitle);
			if(bookTitle.equals("The lord of the rings")) {
				status = true;
				break;
			}			
		}		
		Assert.assertEquals(status,true);
		
		double totalPrice=0;
		for(int i=0;i<jo.getJSONArray("books").length();i++) 
		{			
			String price = jo.getJSONArray("books").getJSONObject(i).get("price").toString();			
			totalPrice = totalPrice +Double.parseDouble(price);					
		}	
		System.out.println("The total price of the boks are :=>"+ totalPrice);
		Assert.assertEquals(totalPrice,275.50);
	}
	
	@Test(priority = 4)
	void getValidateResponseByJsonPath() {

		String res = given().contentType(ContentType.JSON)
				.when().get("http://localhost:3000/store")
				.then().log().all().extract().response().asString();
		
		JsonPath jp = new JsonPath(res); // convert response from  object to String using JsonPath class
		
		String title = jp.getString("books[1].title");
		String price = jp.getString("books[1].price") ;
		System.out.println(title);
		System.out.println(price);
		
		List<Object> price1= jp.getList("books.price");
		double totalPrice = 0;
		for(int i=0;i<price1.size();i++) {
			totalPrice = totalPrice + Double.parseDouble((String) price1.get(i));
		}
		System.out.println("The total price of the boks are :=>"+ totalPrice);
	}
}
