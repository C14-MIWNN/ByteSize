package nl.miwnn.se14.bytesize;

import nl.miwnn.se14.bytesize.dto.ByteSizeUserDTO;
import nl.miwnn.se14.bytesize.model.ByteSizeUser;
import org.junit.jupiter.api.Assertions;
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
	public void adminReferralCodeCheck() {
		//Arrange
		ByteSizeUser user = new ByteSizeUser();
		user.setRole("ADMIN");

		//Act
        int code = user.generateReferralCode() %8;

        //Assert
		Assertions.assertEquals(0, code);

	}
}
