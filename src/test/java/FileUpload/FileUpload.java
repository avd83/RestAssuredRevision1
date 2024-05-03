package FileUpload;

import java.io.File;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FileUpload {

	@Test(priority=1)
	void getSingleFileUpload() {

		File myFile = new File("F:\\15dec22_laptop\\POSTMAN\\CREATE OWN API USING JSON-SERVER\\jsonfiles\\Text.txt");

		given().multiPart("File", myFile).contentType("multipart/form-data")

				.when().post("http://localhost:8080//uploadFile")

				.then().statusCode(200).body("fileName", equalTo("Text.txt")).log().all();
	}

	@Test(priority=2)
	void getMultipleFileUpload() {

		File myFile = new File("F:\\15dec22_laptop\\POSTMAN\\CREATE OWN API USING JSON-SERVER\\jsonfiles\\Text.txt");
		File myFile1 = new File("F:\\15dec22_laptop\\POSTMAN\\CREATE OWN API USING JSON-SERVER\\jsonfiles\\Text1.txt");
		
		given()
			.multiPart("Files", myFile)
			.multiPart("Files", myFile1)
			.contentType("multipart/form-data")

		.when()
			.post("http://localhost:8080//uploadMultipleFiles")

		.then()
			.statusCode(200)
			.body("[0].fileName", equalTo("Text.txt"))
			.body("[1].fileName", equalTo("Text1.txt"))
			.log().all();
	}
	@Test(priority=3)
	void getMultipleFileUploadByArray() {

		File myFile = new File("F:\\15dec22_laptop\\POSTMAN\\CREATE OWN API USING JSON-SERVER\\jsonfiles\\Text.txt");
		File myFile1 = new File("F:\\15dec22_laptop\\POSTMAN\\CREATE OWN API USING JSON-SERVER\\jsonfiles\\Text1.txt");
		
		File fileArray[] = {myFile, myFile1}; 
		given()
			.multiPart("Files", fileArray)
			.contentType("multipart/form-data")

		.when()
			.post("http://localhost:8080//uploadMultipleFiles")

		.then()
			.statusCode(200)
			.body("[0].fileName", equalTo("Text.txt"))
			.body("[1].fileName", equalTo("Text1.txt"))
			.log().all();
	}
}