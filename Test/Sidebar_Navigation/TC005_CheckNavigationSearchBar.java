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

public class TC005_CheckNavigationSearchBar {
    public static void main(String[] args){

        ChromeOptions option = new ChromeOptions();

        //disable the password manager and leak detection pop-ups
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service",false);
        prefs.put("profile.password_manager_enabled",false);
        prefs.put("profile.password_manager_leak_detection",false);

        option.setExperimentalOption("prefs",prefs);

        WebDriver driver = new ChromeDriver(option);

        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            driver.get("https://www.saucedemo.com/");

            WebElement UserName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
            UserName.sendKeys("performance_glitch_user");

            WebElement Password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            Password.sendKeys("secret_sauce");

            WebElement LoginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button")));
            LoginBtn.click();

            WebElement SideBarBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
            SideBarBtn.click();

            WebElement DynamicCatalogBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("dynamic_catalog_sidebar_link")));
            DynamicCatalogBtn.click();

            WebElement LazyLoadBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("dynamic_catalog_lazy_load_link")));
            LazyLoadBtn.click();

            boolean IsDynamicPageOpened = wait.until(ExpectedConditions.urlContains("https://www.saucedemo.com/dynamic-catalog-lazy-load.html"));

            if(IsDynamicPageOpened){
                System.out.println("Page was redirected successfully");
            }

            driver.navigate().back();

            boolean IsBackToParentPage =  wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/inventory.html"));

            if(IsBackToParentPage){
                System.out.println("successfully return to the parent page");
            }

        }catch (Exception e){
            System.out.println("Reason: " + e.getMessage());
        }


    }
}
