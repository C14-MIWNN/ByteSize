package nl.miwnn.se14.bytesize;

import nl.miwnn.se14.bytesize.dto.ByteSizeUserDTO;
import nl.miwnn.se14.bytesize.model.ByteSizeUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ByteSizeApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void nonAdminReferralCodeCheck() {
		//Arrange

		//Act

		//Assert
	}

	@Test
	@DisplayName("Generated code is divisible by 7")
	public void adminReferralCodeDivisibleBySeven() {
		//Arrange
		ByteSizeUser user = new ByteSizeUser();
		user.setRole("ADMIN");

		//Act
        int code = user.generateReferralCode() %7;

        //Assert
		Assertions.assertEquals(0, code);
	}

	@Test
	@DisplayName("Generated code is not divisible by 2")
	public void adminReferralCodeNotDivisibleByTwo() {
		//Arrange
		//Act
		//Assert
	}
}
