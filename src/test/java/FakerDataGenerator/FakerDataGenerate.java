package FakerDataGenerator;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerDataGenerate {
	
	@Test
	void getFakerData() {
		
		Faker faker = new Faker();
		
		System.out.println(faker.name().fullName());
		
		System.out.println(faker.name().username());
		System.out.println(faker.internet().password());
		
		System.out.println(faker.phoneNumber().cellPhone());
		System.out.println(faker.internet().emailAddress());
		
		System.out.println(faker.aviation().aircraft());
		System.out.println(faker.aviation().airport());
		System.out.println(faker.avatar().toString());
		System.out.println(faker.internet().ipV4Cidr());
	}
}