package zdtestpol51bdd.devto.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SingleBlogPage {
    WebDriver driver;

    @FindBy(tagName = "h1")
    public WebElement blogTitle;

    public SingleBlogPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }
}
