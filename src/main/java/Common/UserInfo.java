package Common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserInfo {
    private String [][] userData = new String [7][2];
    private WebDriver formDriver;

    public String[][] collectRandomUserInfo(){
        WebDriver userDataDriver = new FirefoxDriver();
        userDataDriver.get("https://fejk.se/");
        String[] dividedName;
        String fullName;
        String tempDate;
        String[] date;

        tempDate = userDataDriver.findElement(By.cssSelector("#ident > table > tbody > tr:nth-child(7) > td:nth-child(2)")).getText();
        date = tempDate.split("-");
        userData[0][0]="dp";
        userData[0][1]=date[2] + "/" + date[1] + "/" + date[0];
        fullName = userDataDriver.findElement(By.cssSelector("#ident > table > tbody > tr:nth-child(1) > td:nth-child(2)")).getText();
        dividedName = fullName.split(" ");
        userData[1][0]="member_firstname";
        userData[1][1]= dividedName[0];
        userData[2][0]="member_lastname";
        userData[2][1] = dividedName [1];
        userData[3][0]="member_emailaddress";
        userData[3][1]=userDataDriver.findElement(By.cssSelector("#ident > table > " +
                "tbody > tr:nth-child(5) > td:nth-child(2) > a")).getText();
        userData[4][0]="member_confirmemailaddress";
        userData[4][1]=userDataDriver.findElement(By.cssSelector("#ident > table > " +
                "tbody > tr:nth-child(5) > td:nth-child(2) > a")).getText();
        userData[5][0]="signupunlicenced_password";
        userData[5][1]=userDataDriver.findElement(By.cssSelector("#ident > table > " +
                "tbody > tr:nth-child(11) > td:nth-child(2)")).getText();
        userData[6][0]="signupunlicenced_confirmpassword";
        userData[6][1]=userDataDriver.findElement(By.cssSelector("#ident > table > " +
                "tbody > tr:nth-child(11) > td:nth-child(2)")).getText();
        WebDriverWait wait = new WebDriverWait(userDataDriver, Duration.ofSeconds(5000));
        userDataDriver.close();
        return userData;
    }

    public void fillForm(WebDriver driver, String[][] data, String error) {
        formDriver = driver;
        userData = data;
        this.addRandomUserInfo(error);
        this.checkRelevantBoxes(error);
    }

    private void addRandomUserInfo(String error) {
        WebElement textbox;

        for (int i=0;i<7;i++){
            //loopa genom userData
            if(userData[i][0].equals(error)){
                switch (error){
                    case "member_lastname":
                        textbox=formDriver.findElement(By.id(userData[i][0]));
                        textbox.sendKeys("");
                        break;
                    case "signupunlicenced_password":
                        textbox = formDriver.findElement(By.id(userData[i][0]));
                        textbox.sendKeys("unequalpassword");
                        break;
                }
            }else {
                textbox=formDriver.findElement(By.id(userData[i][0]));
                textbox.sendKeys(userData[i][1]);
                if(userData[i][0].equals("dp")){
                    Actions actions = new Actions(formDriver);actions.click().perform();
                }
            }
            WebDriverWait wait = new WebDriverWait(formDriver, Duration.ofSeconds(5000));
        }

    }

    private void checkRelevantBoxes(String error){

        WebElement checkbox = formDriver.findElement(By.cssSelector("#signup_form > " +
                "div:nth-child(11) > div > div > div:nth-child(12) > div > label > span.box"));
        checkbox.click();
        checkbox = formDriver.findElement(By.cssSelector("#signup_form > div:nth-child(12) > " +
                "div > div:nth-child(2) > div:nth-child(1) > label > span.box"));
        if(!error.equals("Terms and Conditions")) {
            checkbox.click();
        }
        checkbox = formDriver.findElement(By.cssSelector("#signup_form > div:nth-child(12) > " +
                "div > div:nth-child(2) > div.md-checkbox.margin-top-10 > label > span.box"));
        checkbox.click();
        checkbox = formDriver.findElement(By.cssSelector("#signup_form > div:nth-child(12) > " +
                "div > div:nth-child(7) > label > span.box"));
        checkbox.click();
    }


    public String getConfirmation(String selector) {
        WebElement label = formDriver.findElement(By.cssSelector(selector));

        return label.getText();
    }


}
