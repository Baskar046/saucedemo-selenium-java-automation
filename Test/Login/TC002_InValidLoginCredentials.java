package Test.Login;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

public class TC002_InValidLoginCredentials {
    public static void main(String[] args){

        WebDriver driver = new ChromeDriver();

        try {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            driver.get("https://www.saucedemo.com/");

            WebElement UserName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
            UserName.sendKeys("1234");

            WebElement Password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            Password.sendKeys("secret_sauce");

            WebElement LoginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button")));
            LoginBtn.click();

            TakesScreenshot LoginPageScreenshot = (TakesScreenshot) driver;
            File Image = LoginPageScreenshot.getScreenshotAs(OutputType.FILE);

            File Designation = new File("C:\\Users\\Selva Baskar\\IdeaProjects\\SeleniumMiniProject\\src\\ScreenShotImages\\TC002_InvalidLoginTestImage.png");

            Files.copy(Image.toPath(), Designation.toPath(), StandardCopyOption.REPLACE_EXISTING);

            boolean IsLoginSuccessful = wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/inventory.html"));

            if(IsLoginSuccessful){
                System.out.println("Login Successful");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            driver.quit();
        }
    }
}

