package Test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Page.CartPage;
import Page.Extras;
import Page.Pageclass;
import Page.ScreenshotPage;
public class Testclass extends Base.Baseclass {

    Pageclass page;
    Extras extras;
    CartPage cart;
    ScreenshotPage ss;

    @BeforeClass
    public void setupPages() {
        page = new Pageclass(driver);
        extras = new Extras(driver);
        cart = new CartPage(driver);
        ss = new ScreenshotPage(driver);
    }

    // ADD 3 PRODUCTS USING SEARCH
    @Test(dataProvider = "products", priority = 1)
    public void addProductsToCart(String product) {

        page.searchProduct(product);
        page.selectFirstProduct();
        page.switchToProductTab();
        page.waitForProductPage();
        page.addToCart();
        page.proceedToCheckout();
        page.goToHomePage();
    }

    // ADD 4TH PRODUCT FROM MENU
    @Test(priority = 2)
    public void addFourthProductFromMenu() {

        extras.openComputersAndSelectFirstProduct();
        extras.addSelectedProductToCart();
    }

    // FINAL CART VALIDATION + SCREENSHOT
    @Test(priority = 3)
    public void openVerifyAndCaptureFinalCart() throws Exception {

    	cart.openCart();

        int count = cart.logCartItems(); // log only

        System.out.println("Final cart item count observed: " + count);

        ss.captureFinalCart(); // ALWAYS after cart opens

        System.out.println("Final cart screenshot captured successfully");
    }

    @DataProvider(name = "products")
    public Object[][] getProducts() {
        return new Object[][] {
            {"ipad"},
            {"airpods"},
            {"laptop"}
        };
    }
}