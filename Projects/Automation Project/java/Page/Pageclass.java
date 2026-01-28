package Page;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Pageclass {
	WebDriver driver;
    Actions act;

    // HOME PAGE
    @FindBy(id = "searchV2")
    WebElement searchBox;
    // SEARCH RESULT 
    @FindBy(xpath = "(//a[contains(@href,'/p/')])[1]")
    WebElement firstProduct;

    // PRODUCT PAGE 
    @FindBy(xpath = "//button[contains(text(),'Add to Cart')]")
    WebElement addToCartBtn;

   // CART / CHECKOUT
    @FindBy(xpath = "//button[contains(text(),'Proceed') or contains(text(),'Checkout')]")
    WebElement proceedToCheckoutBtn;

    public Pageclass(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        act = new Actions(driver);
    }

    public void searchProduct(String product) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(searchBox));

        searchBox.click();
        searchBox.sendKeys(Keys.CONTROL, "a");
        searchBox.sendKeys(Keys.DELETE);

        //
        for (char c : product.toCharArray()) {
            searchBox.sendKeys(String.valueOf(c));
            try {
                Thread.sleep(120);
            } catch (InterruptedException e) {
            }
        }
        searchBox.sendKeys(Keys.ENTER);

        System.out.println("Search triggered for: " + product);
    }

   
    // SELECT FIRST PRODUCT
    public void selectFirstProduct() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(firstProduct));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", firstProduct);
        js.executeScript("arguments[0].click();", firstProduct);

        System.out.println("First product selected");
    }

   
    // SWITCH TO PRODUCT TAB
    public void switchToProductTab() {

    	 Set<String> windows = driver.getWindowHandles();
    	    for (String win : windows) {
    	        driver.switchTo().window(win);
    	    }
    	    PageFactory.initElements(driver, this);

    	    System.out.println("Switched to product tab and reinitialized elements");
    	}
    public void waitForProductPage() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.urlContains("/p/"));

        System.out.println("Product page loaded: " + driver.getCurrentUrl());
    }  
    // ADD TO CART
   
    public void addToCart() {

    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    	    JavascriptExecutor js = (JavascriptExecutor) driver;
    	    js.executeScript("window.scrollTo(0, document.body.scrollHeight / 2);");
    	    try { Thread.sleep(1000); } catch (InterruptedException e) {}

    	    js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    	    try { Thread.sleep(1000); } catch (InterruptedException e) {}    	   
    	    wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));

    	    js.executeScript("arguments[0].scrollIntoView({block:'center'});", addToCartBtn);
    	    js.executeScript("arguments[0].click();", addToCartBtn);

    	    System.out.println("Product added to cart");
    	}

 
    // GO TO CART
  
    public void proceedToCheckout() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutBtn));
        js.executeScript("arguments[0].click();", proceedToCheckoutBtn);

        System.out.println("Clicked Proceed to Checkout");
    }
    public void goToHomePage() {
        driver.navigate().to("https://www.croma.com");
        System.out.println("Navigated to Croma home page");
    }

}