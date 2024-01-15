package Functions;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductFilteringPage {

    private WebDriver driver;

    public ProductFilteringPage(WebDriver driver) {
        this.driver = driver;
    }

    public void filterByBrand(String brandName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
        List<WebElement> checkboxes = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector(".ant-checkbox-input[businesstype='brand']")));
        for (WebElement checkbox : checkboxes) {
            if (checkbox.getAttribute("businessvalue").equals(brandName)) {
                checkbox.click();
                break;
            }
        }
    }

    public void setPriceRange(double minPrice, double maxPrice) {
        List<WebElement> priceInputs = driver.findElements(By.cssSelector(".q9tZB"));
        WebElement minPriceInput = findInputByPlaceholder("Tối thiểu", priceInputs);
        WebElement maxPriceInput = findInputByPlaceholder("Tối đa", priceInputs);
        if (minPriceInput != null && maxPriceInput != null) {
            minPriceInput.clear();
            minPriceInput.sendKeys(Double.toString(minPrice));
            maxPriceInput.clear();
            maxPriceInput.sendKeys(Double.toString(maxPrice));
        } else {
            System.out.println("Không tìm thấy element input min hoặc max");
        }
    }

    private WebElement findInputByPlaceholder(String placeholder, List<WebElement> inputs) {
        for (WebElement input : inputs) {
            if (input.getAttribute("placeholder").equals(placeholder)) {
                return input;
            }
        }
        return null;
    }

    public void applyPriceFilter() {
        WebElement button = driver.findElement(By.cssSelector(".gJ98q .y9-OE ._1lPeN button"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }

    public void selectShippingService(String serviceName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(350));
        List<WebElement> shippingOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector(".ant-checkbox-input[businesstype='multiple']")));
        for (WebElement shippingOption : shippingOptions) {
            if (shippingOption.getAttribute("businessvalue").equals(serviceName)) {
                shippingOption.click();
                break;
            }
        }
    }
}
