package Functions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductFiltering {

	private WebDriver driver;

	public ProductFiltering(WebDriver driver) {
		this.driver = driver;
	}

	public void filterByBrand(String brandName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
		List<WebElement> brandCheckboxes = wait.until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(By.cssSelector(".ant-checkbox-input[businesstype='brand']")));

		for (WebElement checkbox : brandCheckboxes) {
			String businessValue = checkbox.getAttribute("businessvalue");
			if (businessValue.equals(brandName)) {
				checkbox.click();
				break;
			}
		}
	}

	public void setPriceRange(double minPrice, double maxPrice) {
		List<WebElement> priceInputs = driver.findElements(By.cssSelector(".q9tZB"));
		WebElement minPriceInput = findPriceInputByPlaceholder("Tối thiểu", priceInputs);
		WebElement maxPriceInput = findPriceInputByPlaceholder("Tối đa", priceInputs);

		if (minPriceInput != null && maxPriceInput != null) {
			minPriceInput.clear();
			minPriceInput.sendKeys(Double.toString(minPrice));
			maxPriceInput.clear();
			maxPriceInput.sendKeys(Double.toString(maxPrice));
		} else {
			System.out.println("Price input elements not found");
		}
	}

	private WebElement findPriceInputByPlaceholder(String placeholder, List<WebElement> priceInputs) {
		for (WebElement priceInput : priceInputs) {
			if (priceInput.getAttribute("placeholder").equals(placeholder)) {
				return priceInput;
			}
		}
		return null;
	}

	public void applyPriceFilter() {
		WebElement applyButton = driver.findElement(By.cssSelector(".gJ98q .y9-OE ._1lPeN button"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(applyButton)).click();
	}

	public void selectShippingService(String serviceName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(350));
		List<WebElement> shippingOptions = wait.until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(By.cssSelector(".ant-checkbox-input[businesstype='multiple']")));

		for (WebElement shippingOption : shippingOptions) {
			String shippingValue = shippingOption.getAttribute("businessvalue");
			if (shippingValue.equals(serviceName)) {
				shippingOption.click();
				break;
			}
		}
	}
}
