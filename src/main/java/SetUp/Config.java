package SetUp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Config {
	public static WebDriver getDriver(String browser) {
		switch (browser.toLowerCase()) {
		case "chrome":
			return new ChromeDriver();
		case "edge":
			return new EdgeDriver();
		default:
			throw new IllegalArgumentException("Unsupported browser: " + browser);
		}
	}
}
