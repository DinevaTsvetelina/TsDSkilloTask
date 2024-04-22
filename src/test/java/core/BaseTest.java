package core;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ChromeBrowser;
import utils.Screenshot;
import utils.UserRegistrationData;

import java.io.File;
import java.net.URL;

public class BaseTest {
    protected final Faker faker;
    protected WebDriver driver;

    public BaseTest() {
        this.faker = new Faker();
    }

    @BeforeMethod
    public void setup() {
        ChromeBrowser.open();
        driver = ChromeBrowser.driver;
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (!result.isSuccess()) {
            Screenshot.capture(driver, "target/failed-tests-screenshots", result.getName());
        }
        ChromeBrowser.quit();
    }

    protected UserRegistrationData createMockedData(){
        var password = this.faker.bothify("?##????");
        return new UserRegistrationData(this.faker.name().username(), this.faker.bothify("????##@my-mail.com"), password, password);
    }

    protected File getFileFromResource(String path){
        try{
            URL fileUrl = ClassLoader.getSystemResource(path);
            return new File(fileUrl.toURI());
        }
        catch(Exception e){
            throw new RuntimeException();
        }
    }
}
