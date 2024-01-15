package Functions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {
    private WebDriver driver;
    private String name;
    private String password;
    private int xOffset;
    private int yOffset;

    public Login(WebDriver driver, String name, String password, int xOffset, int yOffset) {
        this.driver = driver;
        this.name = name;
        this.password = password;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void performLogin() throws InterruptedException {
        WebElement usernameField = driver.findElement(By.cssSelector("input[data-meta='Field']"));
        usernameField.clear();
        usernameField.sendKeys(name);

        WebElement passwordField = driver.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);

        try {
            WebElement loginButton = driver.findElement(By.xpath("//button[@class='next-btn next-btn-primary next-btn-large']"));
            loginButton.click();
        } catch (NoSuchElementException e) {
            System.out.println("Login button not found.");
        }

        try {
        	
            WebElement slideButton = driver.findElement(By.cssSelector(".nc_iconfont.btn_slide"));
            Actions action = new Actions(driver);
            action.dragAndDropBy(slideButton, xOffset, yOffset).build().perform();
        } catch (NoSuchElementException e) {
            System.out.println("Slide button not found.");
        }
    }
}
