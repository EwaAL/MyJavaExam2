package Common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//********************************************************************
public class CommonSettings {
    // This class contains settings
//********************************************************************
    public WebDriver setBrowser(String useBrowser) {
        // Sets the browser type
        //
        // useBrowser - contains the name of the browser to use
        return switch (useBrowser) {
            case "Firefox" -> new FirefoxDriver();
            case "Edge" -> new EdgeDriver();
            case "Chrome" -> new ChromeDriver();
            default -> null;
        };
    }
//*******************************************************************
}
