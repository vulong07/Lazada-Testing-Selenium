package Functions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductCancelPaymentPage {

	private WebDriver driver;

	public ProductCancelPaymentPage(WebDriver driver) {
		this.driver = driver;
	}

	public void navigateToSummary() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			WebElement summaryElement = wait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//*[@id=\"rightContainer_10010\"]/div[1]/div")));
			summaryElement.click();
		} catch (Exception e) {
			System.out.println("Không thể click vào Summary");
		}
	}

	public void viewOrder() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		try {
			WebElement viewOrderButton = wait.until(
					ExpectedConditions.presenceOfElementLocated(By.cssSelector("#package-delivery-view-order-btn")));
			viewOrderButton.click();
		} catch (Exception e) {
			System.out.println("Không tìm thấy element của xem đơn đặt hàng");
		}
	}

	public void selectItemToCancel(int itemIndex) {
		try {
			List<WebElement> items = driver.findElements(By.cssSelector("item-image"));
			if (itemIndex >= 0 && itemIndex < items.size()) {
				items.get(itemIndex).click();
			} else {
				System.out.println("Không tìm thấy element");
			}
		} catch (Exception e) {
			System.out.println("Lỗi khi chọn item");
		}
	}

	public void initiateCancel() {
		try {
			WebElement cancelButton = driver.findElement(By.cssSelector("text.link.bold"));
			if (cancelButton.isDisplayed() && !cancelButton.getAttribute("disabled").equals("true")) {
				cancelButton.click();
			} else {
				System.out.println("Không thể hủy");
			}
		} catch (Exception e) {
			System.out.println("Lỗi khi click nút hủy");
		}
	}

	public void selectCancelReason(int reasonIndex) {
		try {
			List<WebElement> selectBoxes = driver
					.findElements(By.cssSelector(".reason-col .next-select.medium.reason"));
			if (reasonIndex >= 0 && reasonIndex < selectBoxes.size()) {
				new Select(selectBoxes.get(reasonIndex)).selectByIndex(reasonIndex); // Use Select for dropdown
			} else {
				System.out.println("Không thể chọn lý do");
			}
		} catch (Exception e) {
			System.out.println("Lỗi khi chọn lý do hủy");
		}
	}

	public void confirmCancel() {
		try {
			WebElement confirmCheckbox = driver.findElement(By.cssSelector(".next-checkbox checked"));
			if (confirmCheckbox.isDisplayed() && !confirmCheckbox.getAttribute("disabled").equals("true")) {
				confirmCheckbox.click();
			} else {
				System.out.println("Không thể chọn xác nhận hủy đơn hàng");
			}
		} catch (Exception e) {
			System.out.println("Lỗi khi click xác nhận hủy");
		}
	}

	public void completeCancel() {
		try {
			WebElement confirmButton = driver
					.findElement(By.cssSelector(".next-btn next-btn-normal next-btn-large button"));
			if (confirmButton.isDisplayed() && !confirmButton.getAttribute("disabled").equals("true")) {
				confirmButton.click();
			} else {
				System.out.println("Khong the huy");
			}
		} catch (Exception e) {
			System.out.println("Lỗi khi hoan thanh xác nhận hủy");
		}
	}
}
