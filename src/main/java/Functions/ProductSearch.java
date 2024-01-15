package Functions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductSearch { 

    private WebDriver driver;  
    private String searchTerm;  

    public ProductSearch(WebDriver driver, String searchTerm) { 
        this.driver = driver;
        this.searchTerm = searchTerm;
    }

    public void search() { 
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".search-box__bar--29h6 input[type='search']")));
            searchInput.clear();
            searchInput.sendKeys(searchTerm);
            clickSearchButton();
        } catch (Exception e) {
            System.out.println("Cannot find search element"); 
           
        }
    }

    private void clickSearchButton() {  
        WebElement searchButton = driver.findElement(By.cssSelector(".search-box__button--1oH7"));
        searchButton.click();
    }
}
