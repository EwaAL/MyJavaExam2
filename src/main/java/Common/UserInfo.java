package Common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//***************************************************************************
public class UserInfo {
    // This class collects the user data from fejk.se, to be used for filling in
    // the Create Account-form at the basketballengland webpage
//***************************************************************************
    // Collects data from fejk.se and stores it in an array with two columns,
    // one for the selector to the wanted field, and one for the actual data collected
    // from the field.
    //
    // driver - the active browser
    // user data - the array holding the data, first column stores the selectors to
    //             use at the basketballengland webpage, the second column stores
    //             the actual value to use
    // divided name - array keeping first name and last name separately
    // full name - holds the first and last name together as it comes from fejk.se
    // tempDate - holds the date with the date format it has when it comes from fejk.se
    // date - array keeping day, month and year separately
    public String[][] collectRandomUserInfo(WebDriver driver) {

        String [][] userData = new String [7][2];
        String[] dividedName;
        String fullName;
        String tempDate;
        String[] date;

        driver.get("https://fejk.se/"); // opens the webpage

        // collects the date from fejk.se and rebuilds it to the format requested at
        // the basketballengland webpage
        tempDate = driver.findElement(By.cssSelector("#ident > table > tbody > " +
                "tr:nth-child(7) > td:nth-child(2)")).getText();
        date = tempDate.split("-");
        userData[0][0]="dp"; // selector for the form field "Date of Birth of the member (DD/MM/YYY)"
        userData[0][1]=date[2] + "/" + date[1] + "/" + date[0]; // date with correct format

        // collects the name from fejk.se
        fullName = driver.findElement(By.cssSelector("#ident > table > tbody >" +
                " tr:nth-child(1) > td:nth-child(2)")).getText();
        dividedName = fullName.split(" "); // separates first name and last name
        userData[1][0]="member_firstname"; // selector for the form field "First Name"
        userData[1][1]= dividedName[0]; // first name
        userData[2][0]="member_lastname"; // selector for the form field "Last Name"
        userData[2][1] = dividedName [1]; // last name

        userData[3][0]="member_emailaddress"; // selector for the form field "Email Address"
        // collects the email address from fejk.se
        userData[3][1]= driver.findElement(By.cssSelector("#ident > table > " +
                "tbody > tr:nth-child(5) > td:nth-child(2) > a")).getText();

        // saves the e-mail address again, for the confirmation field
        userData[4][0]="member_confirmemailaddress"; // selector for the form field "Confirm Email Address"
        userData[4][1]= userData[3][1]; // reusing the email address

        userData[5][0]="signupunlicenced_password"; // selector for the form field "Password"
        // collects the password from fejk.se
        userData[5][1]= driver.findElement(By.cssSelector("#ident > table > " +
                "tbody > tr:nth-child(11) > td:nth-child(2)")).getText();

        userData[6][0]="signupunlicenced_confirmpassword"; // selector for the form field "Retype your password"
        userData[6][1]= userData[5][1]; //reusing the password

        return userData; // returns the array with the collected data
    }
//********************************************************************************
}
