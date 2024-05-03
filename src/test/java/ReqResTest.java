import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.Test;

public class ReqResTest {
	
	int id;	
	
	@Test(priority=1)
	void ViewUsers() {
		
		given().log().all()
		.when().log().all().get("https://reqres.in/api/users?page=2")
		.then().log().all()
		.statusCode(200)
		.body("page",equalTo(2));		
	}
	
	@Test(priority=2)
	void CreateUser() {
		
		Map<String,String> data = new HashMap<>();
		data.put("name", "samir");
		data.put("job", "Project leader");
		
		id = given().log().all().header("Content-Type","application/json")
		.body(data)
		.when().log().all().post("https://reqres.in/api/users").jsonPath().getInt("id");
		System.out.println("id");
		//.then().log().all().statusCode(201);
	}
	
	@Test(priority=3,dependsOnMethods= {"CreateUser"})
	void UpdateUser() {
		
		Map<String,String> data = new HashMap<>();
		data.put("name", "kaushik");
		data.put("job", "General manager");
		
		given().log().all().header("Content-Type","application/json")
		.body(data)
		.when().log().all().put("https://reqres.in/api/users/"+id)
		.then().log().all().statusCode(200);
	}
	
	@Test(priority=4)
	void DeleteUser() {
		
		given().log().all()
		.when().log().all().delete("https://reqres.in/api/users/"+id)
		.then().log().all().statusCode(204);
	}
}
