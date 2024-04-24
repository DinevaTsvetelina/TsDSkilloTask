package userarea.negative;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Header;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
import utils.UserRegistrationData;

public class RegisterUserTests extends BaseTest {
    @Test
    public void cannotCreateNewUserWithDuplicateUsername() {

        var home = new HomePage(this.driver);
        home.navigateTo();

        var header = new Header(this.driver);
        header.clickLogin();

        var login = new LoginPage(this.driver);
        login.clickRegister();

        var register = new RegisterPage(this.driver);
        String sameUsername = this.faker.name().username();

        UserRegistrationData userDataFirst = createMockedData();
        userDataFirst.setUsername(sameUsername);
        register.addUser(userDataFirst);
        register.waitUntilToastInvisible();

        header.logoutUser();
        header.clickLogin();
        login.clickRegister();

        UserRegistrationData userDataSecond = createMockedData();
        userDataSecond.setUsername(sameUsername);
        register.addUser(userDataSecond);

        String actualErrorToastText = register.getErrorToastMessage();

        Assert.assertEquals(actualErrorToastText, "Username taken", "Error message is not as expected.");
    }

    @Test
    public void cannotCreateNewUserWithUsernameShorterThanMinimumAllowedSize() {

        var home = new HomePage(this.driver);
        home.navigateTo();

        var header = new Header(this.driver);
        header.clickLogin();

        var login = new LoginPage(this.driver);
        login.clickRegister();

        var register = new RegisterPage(this.driver);
        UserRegistrationData userData = createMockedData();
        userData.setUsername("u");
        register.addUser(userData);

        String actualErrorToastText = register.getErrorToastMessage();

        Assert.assertEquals(actualErrorToastText, "Registration failed!", "Error message is not as expected.");
    }

    @Test
    public void cannotCreateNewUserWithUsernameLongerThanMaximumAllowedSize() {

        var home = new HomePage(this.driver);
        home.navigateTo();

        var header = new Header(this.driver);
        header.clickLogin();

        var login = new LoginPage(this.driver);
        login.clickRegister();

        var register = new RegisterPage(this.driver);
        UserRegistrationData userData = createMockedData();
        userData.setUsername("longerThanMaxUsername");
        register.addUser(userData);

        String actualErrorToastText = register.getErrorToastMessage();

        Assert.assertEquals(actualErrorToastText, "Registration failed!", "Error message is not as expected.");
    }

    @Test
    public void cannotCreateNewUserWithEmailLongerThanMaximumAllowedSize() {

        var home = new HomePage(this.driver);
        home.navigateTo();

        var header = new Header(this.driver);
        header.clickLogin();

        var login = new LoginPage(this.driver);
        login.clickRegister();

        var register = new RegisterPage(this.driver);
        UserRegistrationData userData = createMockedData();
        userData.setEmail("longerThanMaximumAllowedSizeEmaillongerThanMaximumAllowedSizeEmai@test.com");
        register.addUser(userData);

        Assert.assertTrue(register.isErrorToastDisplayed(), "Error toast message is not displayed.");
    }

    @Test
    public void cannotCreateNewUserWithEmailNotContainingAtSymbol() {

        var home = new HomePage(this.driver);
        home.navigateTo();

        var header = new Header(this.driver);
        header.clickLogin();

        var login = new LoginPage(this.driver);
        login.clickRegister();

        var register = new RegisterPage(this.driver);
        UserRegistrationData userData = createMockedData();
        userData.setEmail("tbdfhsg");
        register.addUser(userData);

        Assert.assertTrue(register.isErrorToastDisplayed(), "Error toast message is not displayed.");
    }

    @Test
    public void cannotCreateNewUserWithPasswordShorterThanMinimumAllowedSize() {
        var home = new HomePage(this.driver);
        home.navigateTo();

        var header = new Header(this.driver);
        header.clickLogin();

        var login = new LoginPage(this.driver);
        login.clickRegister();

        var register = new RegisterPage(this.driver);

        UserRegistrationData userData = createMockedData();
        userData.setPassword("123");
        register.addUserInformation(userData);

        String actualShortPasswordErrorMessage = register.getMinMaxPasswordErrorMessage();
        Assert.assertEquals(actualShortPasswordErrorMessage, "Minimum 6 characters !", "Error message is not as expected.");

        register.clickSignIn();
        String actualErrorToastText = register.getErrorToastMessage();
        Assert.assertEquals(actualErrorToastText, "Registration failed!", "Error message is not as expected.");
    }

    @Test
    public void cannotCreateNewUserWithPasswordLongerThanMaximumAllowedSize() {
        var home = new HomePage(this.driver);
        home.navigateTo();

        var header = new Header(this.driver);
        header.clickLogin();

        var login = new LoginPage(this.driver);
        login.clickRegister();

        var register = new RegisterPage(this.driver);

        UserRegistrationData userData = createMockedData();
        userData.setPassword("longerThanMaxSizePass");
        userData.setConfirmPassword("longerThanMaxSizePass");
        register.addUserInformation(userData);

        String actualShortPasswordErrorMessage = register.getMinMaxPasswordErrorMessage();
        Assert.assertEquals(actualShortPasswordErrorMessage, "Maximum 20 characters!", "Error message is not as expected.");

        register.clickSignIn();
        String actualErrorToastText = register.getErrorToastMessage();
        Assert.assertEquals(actualErrorToastText, "Registration failed!", "Error message is not as expected.");
    }

    @Test
    public void cannotCreateNewUserWithMismatchedPassword() {
        var home = new HomePage(this.driver);
        home.navigateTo();

        var header = new Header(this.driver);
        header.clickLogin();

        var login = new LoginPage(this.driver);
        login.clickRegister();

        var register = new RegisterPage(this.driver);
        UserRegistrationData userData = createMockedData();
        userData.setConfirmPassword("something_else");
        register.addUser(userData);

        Assert.assertTrue(register.isErrorToastDisplayed(), "Error toast message is not displayed.");
    }
}