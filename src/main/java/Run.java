
import org.openqa.selenium.WebDriver;

import Functions.Login;
import Functions.ProductCancelPaymentPage;
import Functions.ProductFiltering;
import Functions.ProductInformation;
import Functions.ProductSearch;
import Functions.Product_Payment;

import SetUp.Config;

public class Run {
	public static void main(String[] args) throws Exception {
		String link = "https://member.lazada.vn/user/login?spm=a2o4n.home-vn.header.d5.19053bdc146Tjw&redirect=https%3A%2F%2Fwww.lazada.vn%2F";
		Thread thread = new Thread(() -> {
			try {
				WebDriver driver = Config.getDriver("Edge");
				driver.get(link);
				driver.manage().window().maximize();

				Login login = new Login(driver, "longb3676@gmail.com", "Vulong009@@", 500, 0);
				try {
					login.performLogin();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Thread.sleep(2000);

				String searchTerm = "Giay Nam";

				try {

					ProductSearch productSearch = new ProductSearch(driver, searchTerm);

					productSearch.search();

				} catch (Exception e) {
					System.err.println("Error occured during search: " + e.getMessage());
				}

				ProductFiltering productFiltering = new ProductFiltering(driver);

				productFiltering.filterByBrand("adidas");
				productFiltering.setPriceRange(500, 1000);
				productFiltering.applyPriceFilter();
				productFiltering.selectShippingService("Free Shipping"); // Example shipping option
				Thread.sleep(10000);

				ProductInformation productInfo = new ProductInformation(driver);

				productInfo.selectProduct(4);
				productInfo.selectSize(43);
				productInfo.setQuantity(2);
				productInfo.addToCart();
				productInfo.closePopup();
				Thread.sleep(10000);

				Product_Payment productPaymentPage = new Product_Payment(driver);

				productPaymentPage.openCart();
				productPaymentPage.clickItemInCart(2);
				productPaymentPage.proceedToPayment();
				productPaymentPage.confirmPayment();
				Thread.sleep(10000);

				ProductCancelPaymentPage cancelPaymentPage = new ProductCancelPaymentPage(driver);

				cancelPaymentPage.navigateToSummary();
				cancelPaymentPage.viewOrder();
				cancelPaymentPage.selectItemToCancel(0);
				cancelPaymentPage.initiateCancel();
				cancelPaymentPage.selectCancelReason(2);
				cancelPaymentPage.confirmCancel();
				cancelPaymentPage.completeCancel();
				Thread.sleep(10000);
				driver.quit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		Thread.sleep(500);

		thread.start();

		thread.join();

	}

}
