package userarea.negative;

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
    public void cannotLoginWithInvalidUsernameForValidPassword() {

        UserRegistrationData userData = createAndLogoutUser();

        var login = new LoginPage(this.driver);
        login.navigateTo();
        login.signInUserWithUsernameOrEmail("anotherUsername", userData.getPassword(), false);

        var header = new Header(this.driver);

        Assert.assertFalse(header.verifyProfileMenuElementExists(), "User is not signed in as expected");
    }

    @Test
    public void cannotLoginWithInvalidPasswordForValidUsername() {

        UserRegistrationData userData = createAndLogoutUser();

        var login = new LoginPage(this.driver);
        login.navigateTo();
        login.signInUserWithUsernameOrEmail(userData.getUsername(), "invalidPassword", false);

        var header = new Header(this.driver);

        Assert.assertFalse(header.verifyProfileMenuElementExists(), "User is not signed in as expected");
    }

    @Test
    public void cannotLoginWithInvalidUsernameInvalidPassword() {

        createAndLogoutUser();

        var login = new LoginPage(this.driver);
        login.navigateTo();
        login.signInUserWithUsernameOrEmail("anotherUsername", "invalidPassword", false);

        var header = new Header(this.driver);

        Assert.assertFalse(header.verifyProfileMenuElementExists(), "User is not signed in as expected");
    }

    @Test
    public void cannotLoginWithEmptyUsername() {

        UserRegistrationData userData = createAndLogoutUser();

        var login = new LoginPage(this.driver);
        login.navigateTo();
        login.signInUserWithUsernameOrEmail("", userData.getPassword(), false);

        var header = new Header(this.driver);

        Assert.assertFalse(header.verifyProfileMenuElementExists(), "User is not signed in as expected");
    }

    @Test
    public void cannotLoginWithEmptyPassword() {

        UserRegistrationData userData = createAndLogoutUser();

        var login = new LoginPage(this.driver);
        login.navigateTo();
        login.signInUserWithUsernameOrEmail(userData.getUsername(), "", false);

        var header = new Header(this.driver);

        Assert.assertFalse(header.verifyProfileMenuElementExists(), "User is not signed in as expected");
    }

    @Test
    public void cannotLoginWithEmptyUsernameEmptyPassword() {

        createAndLogoutUser();

        var login = new LoginPage(this.driver);
        login.navigateTo();
        login.signInUserWithUsernameOrEmail("", "", false);

        var header = new Header(this.driver);

        Assert.assertFalse(header.verifyProfileMenuElementExists(), "User is not signed in as expected");
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