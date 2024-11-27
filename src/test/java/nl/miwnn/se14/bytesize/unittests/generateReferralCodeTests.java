package nl.miwnn.se14.bytesize.unittests;

import nl.miwnn.se14.bytesize.model.ByteSizeUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        boolean isCodeZero = true;

        //Act
        int code = user.generateReferralCode();
        if (code == 0) {
            isCodeZero = false;
        }

        //Assert
        assertEquals(true, isCodeZero);
    }

    @Test
    void userReferralCodeIsNotNegative() {
        //Arrange
        ByteSizeUser user = new ByteSizeUser();
        user.setRole("USER");
        boolean isCodeNegative = true;

        //Act
        int code = user.generateReferralCode();
        if (code > 0) {
            isCodeNegative = false;
        }

        //Assert
        assertEquals(false, isCodeNegative);
    }

    @Test
    void adminReferralCodeIsEven() {
        //Arrange
        ByteSizeUser user = new ByteSizeUser();
        user.setRole("ADMIN");

        //Act
        int code = user.generateReferralCode() %2;

        //Assert
        assertEquals(0, code);
    }

    @Test
    void adminReferralCodeIsDivisibleByEight() {
        //Arrange
        ByteSizeUser user = new ByteSizeUser();
        user.setRole("ADMIN");

        //Act
        int code = user.generateReferralCode() %8;

        //Assert
        assertEquals(0, code);
    }

}
