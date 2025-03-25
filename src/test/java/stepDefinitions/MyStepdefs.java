package stepDefinitions;

import Common.UserInfo;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;
//**************************************************************************
public class MyStepdefs {
// This class contains the step definitions as described in the feature file
// driver - the Webdriver for https://membership.basketballengland.co.uk/
//                            NewSupporterAccount
// fillData - array with two columns; contains user data.
//            First column contains the identifier for each field
//            in the form at the basketballengland website where
//            data from the second column will be added.
//            Second column contains random user data collected from https://fejk.se/,
//            ex. first name, last name etc.
// user - is the class UserInfo (where most of the logic is located)
//***************************************************************************
    private WebDriver driver;
    private String[][] fillData;
    private UserInfo user;
//***************************************************************************
    // Collects the user data to be used when filling in the webpage form
    // at the basketball website
    @Given("I have the user info I need")
    public void iHaveTheUserInfoINeed() {
        user = new UserInfo();
        fillData = user.collectRandomUserInfo();
    }
//***************************************************************************
    // Opens the basketball webpage for creating an account
    // path - the address to the webpage
    @And("I am at the website {string}")
    public void iAmAtTheWebsite(String path) {
        driver = new FirefoxDriver();
        driver.get(path);
    }
//***************************************************************************
    // Fills in the form for creating an account at the website
    @And("I have filled in the form")
    public void iHaveFilledInTheForm() {
        user.fillForm(driver, fillData, "");
    }
//***************************************************************************
    // Identifies the button to create the account, and clicks on it
    @When("I click the button")
    public void iClickTheButton() {
        driver.findElement(By.cssSelector("#signup_form > div.form-actions.noborder > " +
                "input")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
    }
//****************************************************************************
    // Controls that the account has been created by comparing a label
    // from a button on the "welcome" page (actual) with a specified text (expected).
    // If assertEquals return true the test has passed.
    // Closing the webpage.
    @Then("an account will be created")
    public void anAccountWillBeCreated() {
        String actual = user.getConfirmation("body > div > " +
                "div.page-content-wrapper > div > div > div.btn-group > a");
        String expected = "GO TO MY LOCKER";
        assertEquals(expected, actual);
        driver.close();
    }
//***************************************************************************
    // Fills in the form with missing data or wrong data.
    // error - specifies which error to make
    @And("I have filled in the form with an error with {}")
    public void iHaveFilledInTheFormWithAnErrorWith(String error) {
        user.fillForm(driver, fillData, error);
    }
//**************************************************************************
    // Confirms that the correct error message becomes visible on the form after
    // filling in the data with an error
    // expected - the expected error message
    // number - identifier for the type of error
    // actual - the error message as collected from the webpage
    // If assertEquals return true the test has passed.
    // Closing the webpage.
    @Then("a message with the text {} for number {} will show")
    public void aMessageWithTheTextForNumberWillShow(String expected, @NotNull String number) {
        String actual = "";
        switch (number) {
            case "1": actual = user.getConfirmation("#signup_form > " +
                        "div:nth-child(6) > div:nth-child(2) > div > span > span");
                break;
            case "2": actual = user.getConfirmation("#signup_form > " +
                        "div:nth-child(9) > div > div.row > div:nth-child(2) > div " +
                        "> span > span");
                break;
            case "3": actual = user.getConfirmation("#signup_form > " +
                    "div:nth-child(12) > div > div:nth-child(2) > div:nth-child(1) " +
                    "> span > span");
                break;
        }
        assertEquals(expected, actual);
        driver.close();
    }
//*********************************************************************************
}
