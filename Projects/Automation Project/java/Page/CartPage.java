package Page;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	WebDriver driver;
	By cartItems = By.xpath("//a[contains(@href,'/p/') and normalize-space(text())!='']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // OPEN CART
  
    public void openCart() {
        driver.navigate().to("https://www.croma.com/cart");
        System.out.println("Cart page opened");
    }
    // PRINT CART ITEMS 
   
    public int logCartItems() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> d.findElements(cartItems).size() > 0);

        List<WebElement> items = driver.findElements(cartItems);

        System.out.println("------ CART ITEMS ------");
        for (WebElement item : items) {
            System.out.println(item.getText());
        }
        System.out.println("Total visible items in cart: " + items.size());

        return items.size();
    }
}