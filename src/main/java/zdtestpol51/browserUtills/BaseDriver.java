package zdtestpol51.browserUtills;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseDriver {
    public static WebDriver setLocalDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    public static WebDriver setHeadlessDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        opt.setHeadless(true);
        WebDriver driver = new ChromeDriver(opt);
        return driver;
    }
}
