package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MyStepdefs {
    private WebDriver driver;

    @Given("I am at https:\\/\\/membership.basketballengland.co.uk\\/NewSupporterAccount")
    public void iAmAtHttpsMembershipBasketballenglandCoUkNewSupporterAccount() {
        driver = new FirefoxDriver();
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }
    @When("I fill in date of birth")
    public void iFillInDateOfBirth() {
    }

    @And("I fill in first name")
    public void iFillInFirstName() {
    }

    @And("I fill in last name")
    public void iFillInLastName() {
    }

    @And("I fill in an email address")
    public void iFillInAnEmailAddress() {
    }

    @And("I confirm the Email Address")
    public void iConfirmTheEmailAddress() {
    }

    @And("I type a password")
    public void iTypeAPassword() {
    }

    @And("I retype the password")
    public void iRetypeThePassword() {
    }

    @And("I describe myself as a fan")
    public void iDescribeMyselfAsAFan() {
    }

    @And("I agree to the terms and conditions")
    public void iAgreeToTheTermsAndConditions() {
    }

    @And("I am over {int} years old")
    public void iAmOverYearsOld(int arg0) {
    }

    @And("I agree to the code of ethics and conduct")
    public void iAgreeToTheCodeOfEthicsAndConduct() {
    }

    @And("I click the button")
    public void iClickTheButton() {
    }

    @Then("an account will be created")
    public void anAccountWillBeCreated() {
    }
}
