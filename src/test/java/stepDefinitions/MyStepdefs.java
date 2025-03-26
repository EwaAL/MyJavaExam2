package stepDefinitions;

import Common.CommonSettings;
import Common.FormActions;
import Common.UserInfo;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

//**************************************************************************
public class MyStepdefs {
// This class contains the step definitions as written in the feature file
//
// driver - the browser for the fejk- and the basketballengland websites
// fillData - array with two columns that contains user data.
//            First column: contains the identifier for each field
//            in the form at the basketballengland website where
//            data from the second column will be added.
//            Second column: contains random user data collected from https://fejk.se/,
//            ex. first name, last name etc.
// doIt - the instance of the class FormActions (where the webpage https://membership.
//        basketballengland.co.uk/NewSupporterAccount is handled)
//***************************************************************************
    private WebDriver driver;
    private String[][] fillData;
    private FormActions doIt;
//***************************************************************************
    // Decides which browser to use
    @Given("I am using {}")
    public void iAmUsing(@NotNull String browser) {

        CommonSettings useSetting = new CommonSettings();
        driver = useSetting.setBrowser(browser);
    }
 //***************************************************************************
    // Collects the user data to be used when filling in the webpage form
    // at the basketball website
    @Given("I have the user info I need")
    public void iHaveTheUserInfoINeed() throws InterruptedException {

        UserInfo user = new UserInfo();
        fillData = user.collectRandomUserInfo(driver);
        Thread.sleep(1000);
    }
//***************************************************************************
    // Navigates to the basketball webpage for creating an account
    //
    // path - the address to the webpage
    @And("I am at the website {string}")
    public void iAmAtTheWebsite(String path) {

        driver.get(path);
    }
//***************************************************************************
    // Fills in the form for creating an account at the website
    @And("I have filled in the form")
    public void iHaveFilledInTheForm() throws InterruptedException {

        doIt = new FormActions();
        doIt.fillForm(driver, fillData, "");
    }
//***************************************************************************
    // Identifies the button to click on for creating the account, and clicks on it
    @When("I click the button")
    public void iClickTheButton() {

        driver.findElement(By.cssSelector("#signup_form > div.form-" +
                "actions.noborder > input")).click();
    }
//****************************************************************************
    // Controls that an account has been created, by comparing a label (used as actual)
    // located at a button on the "welcome" page with a specified text (used as expected).
    // If assertEquals return true the test has passed.
    // Before collecting the label the program wait for the button to load properly.
    // Closing the browser at the end.
    @Then("an account will be created")
    public void anAccountWillBeCreated() throws InterruptedException {

        String selector = "body > div > div.page-content-wrapper > div > div " +
                "> div.btn-group > a";

        // the program  waits until the element is properly loaded
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector(selector)));

        String actual = doIt.getConfirmation(selector);
        String expected = "GO TO MY LOCKER";
        Thread.sleep(1000);

        assertEquals(expected, actual);
        driver.close();
    }
//***************************************************************************
    // Fills in the form with missing data or wrong data.
    //
    // error - specifies which error to create
    @And("I have filled in the form with an error with {}")
    public void iHaveFilledInTheFormWithAnErrorWith(String error) throws InterruptedException {
        doIt = new FormActions();
        doIt.fillForm(driver, fillData, error);
    }
//**************************************************************************
    // Confirms that the correct error message becomes visible on the form after
    // filling in the data with an error
    //
    // expected - the expected error message
    // number - identifier for the type of error
    // actual - the error message collected from the webpage
    // If assertEquals returns true the test has passed.
    // Closing the browser.
    @Then("a message with the text {} for number {} will show")
    public void aMessageWithTheTextForNumberWillShow(String expected,
                                                     @NotNull String number)
                                                    throws InterruptedException {
        String actual = switch (number) {
            case "1" -> doIt.getConfirmation("#signup_form > " +
                    "div:nth-child(6) > div:nth-child(2) > div > span > span");
            case "2" -> doIt.getConfirmation("#signup_form > " +
                    "div:nth-child(9) > div > div.row > div:nth-child(2) > div " +
                    "> span > span");
            case "3" -> doIt.getConfirmation("#signup_form > " +
                    "div:nth-child(12) > div > div:nth-child(2) > div:nth-child(1) " +
                    "> span > span");
            default -> "";
        };

        Thread.sleep(2000);

        assertEquals(expected, actual);
        driver.close();
    }
//*********************************************************************************
}
