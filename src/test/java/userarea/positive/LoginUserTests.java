package userarea.positive;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Header;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
import utils.UserRegistrationData;


public class LoginUserTests extends BaseTest {
    @Test
    public void loginUserSuccessfullyWithUsername() {

        UserRegistrationData userData = createAndLogoutUser();

        var login = new LoginPage(this.driver);
        login.navigateTo();
        login.signInUserWithUsernameOrEmail(userData.getUsername(), userData.getPassword(), false);

        var header = new Header(this.driver);

        Assert.assertTrue(header.verifyProfileMenuElementExists(), "User is not signed in as expected");
    }

    @Test
    public void loginUserSuccessfullyWithEmail() {

        UserRegistrationData userData = createAndLogoutUser();

        var login = new LoginPage(this.driver);
        login.navigateTo();
        login.signInUserWithUsernameOrEmail(userData.getEmail(), userData.getPassword(), false);

        var header = new Header(this.driver);

        Assert.assertTrue(header.verifyProfileMenuElementExists(), "User is not signed in as expected");
    }

    @Test
    public void loginUserSuccessfullyWithUsernameAndRememberMeOptionSelected() {

        UserRegistrationData userData = createAndLogoutUser();

        var login = new LoginPage(this.driver);

        login.signInUserWithUsernameOrEmail(userData.getUsername(), userData.getPassword(), true);
        var header = new Header(this.driver);
        header.logoutUser();
        Assert.assertEquals(login.getUsernameOrEmailText(), userData.getUsername(), "Username is not remembered correctly.");
        Assert.assertEquals(login.getPasswordText(), userData.getPassword(), "Password is not remembered correctly.");
    }

    @Test
    public void loginUserSuccessfullyWithEmailAndRememberMeOptionSelected() {

        UserRegistrationData userData = createAndLogoutUser();

        var login = new LoginPage(this.driver);

        login.signInUserWithUsernameOrEmail(userData.getEmail(), userData.getPassword(), true);
        var header = new Header(this.driver);
        header.logoutUser();
        Assert.assertEquals(login.getUsernameOrEmailText(), userData.getEmail(), "Email is not remembered correctly.");
        Assert.assertEquals(login.getPasswordText(), userData.getPassword(), "Password is not remembered correctly.");
    }

    @Test
    public void verifyUserNotFoundErrorMessageIsDisplayed() {

        UserRegistrationData userData = createAndLogoutUser();

        var login = new LoginPage(this.driver);
        login.navigateTo();
        login.signInUserWithUsernameOrEmail("anotherUsername", userData.getPassword(), false);

        String actualUserNotFoundErrorMessage = login.getErrorToast();

        Assert.assertEquals(actualUserNotFoundErrorMessage, "User not found", "Error message is not as expected.");
    }

    @Test
    public void verifyInvalidPasswordErrorMessageIsDisplayed() {

        UserRegistrationData userData = createAndLogoutUser();

        var login = new LoginPage(this.driver);
        login.navigateTo();
        login.signInUserWithUsernameOrEmail(userData.getUsername(), "111", false);

        var header = new Header(this.driver);

        String actualInvalidPasswordErrorMessage = login.getErrorToast();

        Assert.assertEquals(actualInvalidPasswordErrorMessage, "Invalid password", "Error message is not as expected.");
    }

    private UserRegistrationData createAndLogoutUser() {
        var home = new HomePage(this.driver);
        home.navigateTo();

        var header = new Header(this.driver);
        header.clickLogin();

        var login = new LoginPage(this.driver);
        login.clickRegister();

        var register = new RegisterPage(this.driver);
        UserRegistrationData userData = createMockedData();
        register.addUser(userData);
        header.logoutUser();
        return userData;
    }
}
