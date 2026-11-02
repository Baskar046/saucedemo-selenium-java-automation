package Test.AddToCard;

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

public class TC007_CheckAddToCart {
    public static void main(String[] args){

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

            WebElement AddToCart_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='add-to-cart-sauce-labs-backpack']")));
            AddToCart_1.click();

            WebElement AddToCart_2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='add-to-cart-sauce-labs-bike-light']")));
            AddToCart_2.click();

            System.out.println("Add to cart button clicked ");

            WebElement RemoveAddToCart_1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("remove-sauce-labs-backpack")));
            RemoveAddToCart_1.click();

            WebElement ClickChecklist = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='shopping_cart_badge']")));
            ClickChecklist.click();

            WebElement CheckOutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
            CheckOutBtn.click();

            WebElement FirstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
            FirstName.sendKeys("John");

            WebElement LastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("last-name")));
            LastName.sendKeys("Wick");

            WebElement ZIPCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postal-code")));
            ZIPCode.sendKeys("100000");


            WebElement ContinueBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='continue']")));
            ContinueBtn.click();


            WebElement FinishBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("finish")));
            FinishBtn.click();

            boolean IsPaymentCompleted = wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/checkout-complete.html"));

            if(IsPaymentCompleted){
                System.out.println("order placed successfully");
            }

            WebElement CheckPDFOrder = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("generate-pdf-order"))
            );

            CheckPDFOrder.click();

            System.out.println(driver.getWindowHandles());



        }catch (Exception e){
            System.out.println("Reason: " + e);
        }
}   }
