package Functions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class  Product_Payment {

	private WebDriver driver;

	public Product_Payment(WebDriver driver) {
		this.driver = driver;
	}

	public void openCart() {
		try {
			WebElement cartButton = driver.findElement(By.cssSelector(".lzd-nav-cart"));
			if (cartButton.isDisplayed()) {
				cartButton.click();
			} else {
				System.out.println("Không thể Click");
			}
		} catch (Exception e) {
			System.out.println("Không tìm thấy element");
		}
	}

	public void clickItemInCart(int position) {
		try {
			List<WebElement> items = driver.findElements(By.cssSelector(".next-checkbox"));
			if (position > 0 && position < items.size()) {
				items.get(position).click();
			} else {
				System.out.println("Không tìm thấy phần tử");
			}
		} catch (Exception e) {
			System.out.println("Lỗi khi click item");
		}
	}

	public void proceedToPayment() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			WebElement paymentButton = wait.until(
					ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.next-btn.next-btn-primary")));
			if (paymentButton.isDisplayed()) {
				paymentButton.click();
			} else {
				System.out.println("Không thể Click");
			}
		} catch (Exception e) {
			System.out.println("Không tìm thấy Element");
		}
	}

	public void confirmPayment() {
		try {
			WebElement confirmationButton = driver
					.findElement(By.cssSelector(".undefined.checkout-order-total button"));
			if (confirmationButton.isDisplayed() && !confirmationButton.getAttribute("disabled").equals("true")) {
				confirmationButton.click();
			} else {
				System.out.println("Không tìm thể xác nhận");
			}
		} catch (Exception e) {
			System.out.println("Không tìm thấy Element");
		}
	}
}
