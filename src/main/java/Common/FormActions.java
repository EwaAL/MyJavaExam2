package Common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

//***************************************************************************
public class FormActions {
    // This class performs the actions at the basketballengland webpage
    //
    // formDriver - contains the browser
    // userData - array with two columns that contains user data.
    //           First column: contains the identifier for each field
    //           in the form at the basketballengland webpage where
    //           data from the second column will be added.
    //           Second column: contains random user data collected from
    //           https://fejk.se/, ex. first name, last name etc.
//***************************************************************************
    private WebDriver formDriver;
    private String[][] userData;
//***************************************************************************
    // Fills in the form for creating a user account at the basketballengland webpage
    //
    // driver - the browser
    // data - the data to be used at the basketballengland webpage
    // error - defines the error to create on the webpage. Empty if account is to
    //         be created successfully.
    public void fillForm(WebDriver driver, String[][] data, String error) {
        formDriver = driver;
        userData = data;
        this.addRandomUserInfo(error);
        this.checkRelevantBoxes(error);
    }
//*****************************************************************************
    // Fills in the textfields in the form at the basketballengland webpage
    //
    // error - if an error is to be created the type of error is defined here
    private void addRandomUserInfo(String error) {

        WebElement textbox;

        for (int i=0;i<7;i++){
            //loop through userData to fill in the values in the form fields
            if(userData[i][0].equals(error)){
                //creates errors in textfields for last name or for password
                switch (error){
                    case "member_lastname":
                        textbox=formDriver.findElement(By.id(userData[i][0]));
                        textbox.sendKeys("");
                        break;
                    case "signupunlicenced_password":
                        textbox = formDriver.findElement(By.id(userData[i][0]));
                        textbox.sendKeys("unequalpassword"); //this is a mismatch password
                        break;
                }
            }else {
                textbox=formDriver.findElement(By.id(userData[i][0]));
                textbox.sendKeys(userData[i][1]);
                if(userData[i][0].equals("dp")){
                    //removes the calender dropdown
                    Actions actions = new Actions(formDriver);actions.click().perform();
                }
            }

        }
    }
//*****************************************************************************
    // Checks the relevant checkboxes
    //
    // error - names a checkbox to leave out, to create an error
    private void checkRelevantBoxes(String error){

        // this is the checkbox for being a fan
        WebElement checkbox = formDriver.findElement(By.cssSelector("#signup_form > " +
                "div:nth-child(11) > div > div > div:nth-child(12) > div > label > span.box"));
        checkbox.click();
        // this is the checkbox for Terms and Conditions. Leaved out if error is activated
        checkbox = formDriver.findElement(By.cssSelector("#signup_form > div:nth-child(12) > " +
                "div > div:nth-child(2) > div:nth-child(1) > label > span.box"));
        if(!error.equals("Terms and Conditions")) {
            checkbox.click();
        }
        // this is the age checkbox
        checkbox = formDriver.findElement(By.cssSelector("#signup_form > div:nth-child(12) > " +
                "div > div:nth-child(2) > div.md-checkbox.margin-top-10 > label > span.box"));
        checkbox.click();
        // this is the Code of Ethics and Conducts checkbox
        checkbox = formDriver.findElement(By.cssSelector("#signup_form > div:nth-child(12) > " +
                "div > div:nth-child(7) > label > span.box"));
        checkbox.click();
    }
//****************************************************************************
    // Collects the value for comparing to see if the wanted result is achieved
    //
    // selector - the selector to use when finding the right element on the webpage
    public String getConfirmation(String selector) {

        WebElement label = formDriver.findElement(By.cssSelector(selector));
        return label.getText();
    }
//****************************************************************************
}
