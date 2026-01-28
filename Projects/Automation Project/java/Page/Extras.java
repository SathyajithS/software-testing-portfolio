package Page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Extras {
	WebDriver driver;
    JavascriptExecutor js;

    // HAMBURGER MENU 
    @FindBy(xpath = "//*[normalize-space()='Menu']")
    WebElement menu;

    //  ADD TO CART
    @FindBy(xpath = "//button[contains(text(),'Add to Cart')]")
    WebElement addToCartBtn;

    public Extras(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }
    
    // MENU → COMPUTERS & TABLETS → SELECT FIRST PRODUCT
    
    public void openComputersAndSelectFirstProduct() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        js.executeScript("window.scrollTo(0,0);");
        wait.until(ExpectedConditions.elementToBeClickable(menu));
        js.executeScript("arguments[0].click();", menu);
        System.out.println("Menu opened");

        // Click Computers & Tablets
        By computers = By.xpath("//a[contains(.,'Computers')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(computers));
        js.executeScript("arguments[0].click();", driver.findElement(computers));
        System.out.println("Clicked Computers & Tablets");

        // Select first product
        By firstProduct = By.xpath("(//a[contains(@href,'/p/')])[1]");
        wait.until(ExpectedConditions.elementToBeClickable(firstProduct));
        js.executeScript("arguments[0].click();", driver.findElement(firstProduct));
        System.out.println("First computer selected");
    }

    
    // ADD SELECTED PRODUCT TO CART
    public void addSelectedProductToCart() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Switch to product tab
        for (String win : driver.getWindowHandles()) {
            driver.switchTo().window(win);
        }
        PageFactory.initElements(driver, this);

        js.executeScript(
            "window.scrollTo(0, document.body.scrollHeight/2);" +
            "window.dispatchEvent(new Event('scroll'));"
        );

        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
        js.executeScript("arguments[0].click();", addToCartBtn);

        System.out.println("Product added to cart");
    }
}