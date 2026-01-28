package Utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JSUtils {
	public static void removeLoginOverlay(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
            "let modal = document.querySelector('div._2Sn47c');" +
            "if(modal){modal.remove();}"
        );

        js.executeScript(
            "document.body.style.overflow='auto';"
        );
    }
}

