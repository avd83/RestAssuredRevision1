package SerializationAndDeSerialization;

import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializationDeserialization {
	
	// convert from POJO Object to JSON data 
	@Test(priority=1)
	void getConvertPojo2Json() throws JsonProcessingException {
		
		StudentPoJo stuPojo = new StudentPoJo();
		
		stuPojo.setName("samir");
		stuPojo.setLocation("usa");
		stuPojo.setPhoneNo("3423212345");			
		String courses[] ={"java","c#"};
		stuPojo.setCourses(courses);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonData = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stuPojo);
		
		System.out.println(jsonData);		
	}

	// convert fromJSON data to POJO Object
	
	@Test(priority=2)
	void getConvertJson2PoJo() throws JsonProcessingException {
		
		String jsonData = "{\r\n"
				+ "        \"location\": \"aus\",\r\n"
				+ "        \"name\": \"maxwel\",\r\n"
				+ "        \"phoneNo\": \"6646612789\",\r\n"
				+ "        \"courses\": [\"c++\",\"C#\"]\r\n"
				+ "}"	;
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		StudentPoJo stuPojo =  objectMapper.readValue(jsonData, StudentPoJo.class);
		System.out.println(stuPojo.getName());
		System.out.println(stuPojo.getLocation());
		System.out.println(stuPojo.getPhoneNo());
		System.out.println(stuPojo.getCourses()[0]);
		System.out.println(stuPojo.getCourses()[1]);		
	}
}