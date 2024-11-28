package nl.miwnn.se14.bytesize.unittests;

import nl.miwnn.se14.bytesize.model.ByteSizeUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Heron
 * Purpose for the class
 */

public class generateReferralCodeTests {

    @Test
    void userReferralCodeIsUneven() {
        //Arrange
        ByteSizeUser user = new ByteSizeUser();
        user.setRole("USER");

        //Act
        int code = user.generateReferralCode() %2;

        //Assert
        assertEquals(1, code);
    }

    @Test
    void userReferralCodeIsNotZero() {
        //Arrange
        ByteSizeUser user = new ByteSizeUser();
        user.setRole("USER");

        //Act
        int code = user.generateReferralCode();

        //Assert
        assertNotEquals(0, code);
    }

    @Test
    void userReferralCodeIsNotNegative() {
        //Arrange
        ByteSizeUser user = new ByteSizeUser();
        user.setRole("USER");

        //Act
        int code = user.generateReferralCode();

        //Assert
        assertTrue(code > 0);
    }

    @Test
    void adminReferralCodeIsEven() {
        //Arrange
        ByteSizeUser user = new ByteSizeUser();
        user.setRole("ADMIN");

        //Act
        int code = user.generateReferralCode() % 2;

        //Assert
        assertEquals(0, code);
    }

    @Test
    void adminReferralCodeIsDivisibleByEight() {
        //Arrange
        ByteSizeUser user = new ByteSizeUser();
        user.setRole("ADMIN");

        //Act
        int code = user.generateReferralCode() % 8;

        //Assert
        assertEquals(0, code);
    }

    @Test
    void checkIfReferralCodeIsZero() {
        // Arrange
        ByteSizeUser byteSizeUser = new ByteSizeUser();
        byteSizeUser.setRole("ADMIN");

        // Act
        int code = byteSizeUser.generateReferralCode();

        // Assert
        assertNotEquals(0, code, "code should be 0 if byteSizeUser is Admin");
    }

    @Test
    void checkIfReferralCodeIsPositive() {
        // Arrange
        ByteSizeUser byteSizeUser = new ByteSizeUser();
        byteSizeUser.setRole("USER");

        // Act
        int code = byteSizeUser.generateReferralCode();

        // Assert
        assertTrue(code > 0, "code must be higher than 0");
    }
}
