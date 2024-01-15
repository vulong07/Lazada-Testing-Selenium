package Functions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductInformation {

	private WebDriver driver; 

	public ProductInformation(WebDriver driver) { 
		this.driver = driver;
	}

	public void selectProduct(int desiredProductNumber) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		List<WebElement> productElements = wait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".Bm3ON")));

		if (desiredProductNumber >= 0 && desiredProductNumber < productElements.size()) {
			WebElement targetProduct = productElements.get(desiredProductNumber);
			targetProduct.click();
		} else {
			System.out.println("Product not found");
		}
	}

	public void selectSize(int size) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		List<WebElement> sizeOptions = wait.until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(By.cssSelector(".sku-prop-content .sku-variable-size")));

		for (WebElement sizeOption : sizeOptions) {
			String sizeValue = sizeOption.getText();
			if (Integer.parseInt(sizeValue) == size) {
				sizeOption.click();
				break;
			}
		}

		if (!isSizeSelected(size)) {
			System.out.println("Size not found");
		}
	}

	private boolean isSizeSelected(int size) {
		return false;

	}

	public void setQuantity(int quantity) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement quantityInput = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("input[type='text'][step='1'][min='1'][max='4'][autocomplete='off']")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value = '" + quantity + "';", quantityInput);
		js.executeScript("arguments[0].dispatchEvent(new Event('change'));", quantityInput);
	}

	public void addToCart() {
		try {
			WebElement addToCartButton = driver.findElement(By.cssSelector(".pdp-cart-concern > button:nth-child(2)"));
			if (addToCartButton.isDisplayed()) {
				addToCartButton.click();
			} else {
				System.out.println("Cannot click 'Add to Cart' button");
			}
		} catch (Exception e) {
			System.out.println("Element not found");
		}
	}

	public void closePopup() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement popup = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[8]/div/div[2]/a/i")));
		if (popup.isDisplayed()) {
			popup.click();
		} else {
			System.out.println("Cannot close popup");
		}
	}
}
