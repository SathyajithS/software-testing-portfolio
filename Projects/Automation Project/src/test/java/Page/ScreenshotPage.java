package Page;

import java.io.File;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotPage {

    WebDriver driver;

    public ScreenshotPage(WebDriver driver) {
        this.driver = driver;
    }

    public void captureFinalCart() throws Exception {

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        File dest = new File(
            System.getProperty("user.dir") +
            "/screenshots/final_cart.png"
        );
        dest.getParentFile().mkdirs();
        FileHandler.copy(src, dest);

        System.out.println("Screenshot saved at: " + dest.getAbsolutePath());
    }
}