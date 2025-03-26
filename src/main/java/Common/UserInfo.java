package Common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserInfo {

    public String[][] collectRandomUserInfo(WebDriver driver) {

        String [][] userData = new String [7][2];
        String[] dividedName;
        String fullName;
        String tempDate;
        String[] date;

        driver.get("https://fejk.se/");

        tempDate = driver.findElement(By.cssSelector("#ident > table > tbody > " +
                "tr:nth-child(7) > td:nth-child(2)")).getText();
        date = tempDate.split("-");
        userData[0][0]="dp";
        userData[0][1]=date[2] + "/" + date[1] + "/" + date[0];

        fullName = driver.findElement(By.cssSelector("#ident > table > tbody >" +
                " tr:nth-child(1) > td:nth-child(2)")).getText();
        dividedName = fullName.split(" ");
        userData[1][0]="member_firstname";
        userData[1][1]= dividedName[0];
        userData[2][0]="member_lastname";
        userData[2][1] = dividedName [1];

        userData[3][0]="member_emailaddress";
        userData[3][1]= driver.findElement(By.cssSelector("#ident > table > " +
                "tbody > tr:nth-child(5) > td:nth-child(2) > a")).getText();

        userData[4][0]="member_confirmemailaddress";
        userData[4][1]= driver.findElement(By.cssSelector("#ident > table > " +
                "tbody > tr:nth-child(5) > td:nth-child(2) > a")).getText();

        userData[5][0]="signupunlicenced_password";
        userData[5][1]= driver.findElement(By.cssSelector("#ident > table > " +
                "tbody > tr:nth-child(11) > td:nth-child(2)")).getText();

        userData[6][0]="signupunlicenced_confirmpassword";
        userData[6][1]= driver.findElement(By.cssSelector("#ident > table > " +
                "tbody > tr:nth-child(11) > td:nth-child(2)")).getText();

        return userData;
    }
}
