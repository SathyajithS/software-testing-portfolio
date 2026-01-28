package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

import Utils.JSUtils;

public class Baseclass {
	protected WebDriver driver;
	@BeforeTest
	public void setUp() {
		driver=new ChromeDriver();
		driver.get("https://www.croma.com/");
		driver.manage().window().maximize();
		 JSUtils.removeLoginOverlay(driver);
	}
}