package nl.miwnn.se14.bytesize.unittests;

import nl.miwnn.se14.bytesize.model.ByteSizeUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Heron
 * Purpose for the class
 */

public class GenerateReferralCodeTests {

//    @Test
//    void userReferralCodeIsUneven() {
//        //Arrange
//        //Act
//        //Assert
//    }

    @Test
    void adminReferralCodeIsEven() {
        //Arrange
        ByteSizeUser user = new ByteSizeUser();
        user.setRole("ADMIN");

        //Act
        int code = user.generateReferralCode() %2;

        //Assert
        Assertions.assertEquals(0, code);
    }

    @Test
    void adminReferralCodeIsDivisibleByEight() {
        //Arrange
        ByteSizeUser user = new ByteSizeUser();
        user.setRole("ADMIN");

        //Act
        int code = user.generateReferralCode() %8;

        //Assert
        Assertions.assertEquals(0, code);
    }

}
