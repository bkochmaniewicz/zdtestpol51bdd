package zdtestpol51bdd.devto;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DevToStepsDefinitions {

    WebDriver driver;

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    }

    @Given("Chrome browser is open")
    public void chrome_browser_is_open() {
        driver = new ChromeDriver();
    }
    @When("I go to devto main page")
    public void i_go_to_devto_main_page() {
        driver.get("https://dev.to");
    }
    @When("I click on first blog displayed")
    public void i_click_on_first_blog_displayed() {
        driver.findElement(By.className("crayons-story__body")).click();
    }
    @Then("I should be redirected to blog page")
    public void i_should_be_redirected_to_blog_page() {
        System.out.println("Then");
    }
}
