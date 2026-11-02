package Test.ValidateLinks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TC008_CheckHomePageBrokenLinks {
    public static void main(String[] args){

        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service",false);
        prefs.put("profile.password_manager_enabled",false);
        prefs.put("profile.password_manager_leak_detection",false);

        options.setExperimentalOption("prefs",prefs);

        WebDriver driver = new ChromeDriver(options);

        try{
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));


            driver.get("https://www.saucedemo.com/");


            WebElement UserName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
            UserName.sendKeys("performance_glitch_user");

            WebElement Password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            Password.sendKeys("secret_sauce");

            WebElement LoginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button")));
            LoginBtn.click();

            List<WebElement> Links = driver.findElements(By.tagName("a"));

            for(WebElement Link : Links) {

                String ActualLinks = Link.getAttribute("href");

                if (ActualLinks == null || ActualLinks.isEmpty()) {
                    System.out.println("Missing href attribute: " + ActualLinks);
                    continue;
                }

                try {

                    URL url = new URL(ActualLinks);

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.setConnectTimeout(3000);

                    connection.setRequestMethod("GET");

                    connection.setRequestProperty("User-Agent", "Mozilla/5.0");

                    connection.connect();

                    int ResponseCode = connection.getResponseCode();

                    if (ResponseCode >= 200 && ResponseCode < 300) {
                        System.out.println("Valid Links");
                    } else if (ResponseCode >= 300 && ResponseCode < 400) {
                        System.out.println("Redirected Links: " + ActualLinks + " | Code: " + ResponseCode);

                    } else if (ResponseCode >= 400 && ResponseCode < 500) {
                        System.out.println("Client Error: " + ActualLinks + " | Code: " + ResponseCode);
                    } else if (ResponseCode >= 500) {
                        System.out.println("Server Error: " + ActualLinks + "| Code: " + ResponseCode);
                    }


                }catch(Exception error){
                    System.out.println("Unable to check links: " + ActualLinks);
                    System.out.println(error.getMessage());
                }

            }

        }catch (Exception e){

            System.out.println("Reason: " + e.getMessage());
        }
    }
}
