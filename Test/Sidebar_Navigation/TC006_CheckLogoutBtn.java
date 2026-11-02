package Test.Sidebar_Navigation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class TC006_CheckLogoutBtn {
    public static void main(String[] args) {


        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service",false);
        prefs.put("profile.password_manager_enabled",false);
        prefs.put("profile.password_manager_leak_detection",false);

        options.setExperimentalOption("prefs",prefs);

        WebDriver driver = new ChromeDriver(options);

        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            driver.get("https://www.saucedemo.com/");

            WebElement UserName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
            UserName.sendKeys("performance_glitch_user");

            WebElement Password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            Password.sendKeys("secret_sauce");

            WebElement LoginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button")));
            LoginBtn.click();

            System.out.println("Login successfully");

            WebElement SideBarBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
            SideBarBtn.click();

            WebElement LogoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='logout_sidebar_link']")));
            LogoutBtn.click();

            System.out.println("Logout successfully");


        }catch (Exception e){
            System.out.println("Reason: " + e.getMessage());
        }

    }
}
