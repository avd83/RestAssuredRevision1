package RequestBodyDiifrentWay;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class DiffrentWayRequestBodySend {	
	PoJo pojo= new PoJo();
	/*
	@Test(priority=1)
	public void testPostUsingHashMap() {
			
		given().log().all().body(getHashMapData())
		.when().log().all().post("http://localhost:3000/students")
		.then()
		.statusCode(201)		
		.body("name", equalTo("samir"))
		.body("location", equalTo("usa"))
		.body("phoneno", equalTo("3423212345"))
		.body("courses[0]", equalTo("java"))
		.body("courses[1]", equalTo("c#"))
		.log().all();
				
	}
	
	@Test(priority=1)
	public void testPostUsingOrgJson() {
			
		given().log().all().body(getJsonOrgData().toString())
		.when().log().all().post("http://localhost:3000/students")
		.then()
		.statusCode(201)		
		.body("name", equalTo("bhatti"))
		.body("location", equalTo("uk"))
		.body("phoneno", equalTo("222222222"))
		.body("courses[0]", equalTo("selenium"))
		.body("courses[1]", equalTo("c++"))
		.log().all();
				
	}
	
	@Test(priority=1)
	public void testPostUsingPoJo() {
		
		given().log().all().contentType("application/json").body(getPoJoData())
		.when().log().all().post("http://localhost:3000/students")
		.then()
		.statusCode(201)		
		.body("name", equalTo(pojo.getName()))
		.body("location", equalTo(pojo.getLocation()))
		.body("courses[0]", equalTo("playwrite"))
		.body("courses[1]", equalTo("appium"))
		.body("phoneNo", equalTo(pojo.getPhoneNo()))
		.log().all();
				
	}*/
	
	@Test(priority=1)
	public void testPostUsingExternalJsonFile() throws FileNotFoundException {
					
		given().log().all().contentType("application/json").body(getJsonFileData())
		.when().log().all().post("http://localhost:3000/students")
		.then()
		.statusCode(201)		
		.body("name", equalTo("danny"))
		.body("location", equalTo("NZ"))
		.body("courses[0]", equalTo("perl"))
		.body("courses[1]", equalTo("python"))
		.body("phoneNo", equalTo("6646612789"))
		.log().all();
				
	}
	@Test(priority=2)
	public void testDeleteUser() {
			
		given().log().all()
		.when().log().all().delete("http://localhost:3000/students/9ca3")
		.then().statusCode(200).log().all();				
	}
	/*
	public Map getHashMapData() {
		String courses[] ={"java","c#"};
		
		Map data = new HashMap();
		data.put("name","samir");
		data.put("location","usa");
		data.put("phoneno","3423212345");		
		data.put("courses",courses);
		
		return data;
	}
	
	public JSONObject getJsonOrgData() {
		String courses[] ={"selenium","c++"};
		
		JSONObject jasonObject = new JSONObject();
		jasonObject.put("name","bhatti");
		jasonObject.put("location","uk");
		jasonObject.put("phoneno","222222222");		
		jasonObject.put("courses",courses);
		
		return jasonObject;
	}
	
	public PoJo getPoJoData() {
		String courses[] ={"playwrite","appium"};		
		pojo.setName("Naresh");
		pojo.setLocation("AMD");
		pojo.setPhoneNo("5555555554");		
		pojo.setCourses(courses);	
		return pojo; 
	}
	*/
	
	public String getJsonFileData() throws FileNotFoundException {
		File f = new File("C:\\Users\\Abhay\\eclipse-workspace-RestAssured\\RestAssuredRevision1\\src\\test\\java\\RequestBodyDiifrentWay\\requestBody.json");
		FileReader fr = new FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject jo = new JSONObject(jt);
		
		return jo.toString();
	}
}
