package zdtestpol51bdd.devto;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Locale;

public class DevToStepsDefinitions {
    WebDriver driver;
    WebDriverWait wait;
    String firstBlogTitle;
    String firstCastTitle;
    String searchingPhrase;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Given("I go to devto main page")
    public void i_go_to_devto_main_page() {
        driver.get("https://dev.to");
    }

    @When("I click on first blog displayed")
    public void i_click_on_first_blog_displayed() {
        WebElement firstBlog = driver.findElement(By.cssSelector("h2.crayons-story__title > a"));
        firstBlogTitle = firstBlog.getText();
        firstBlog.click();
    }

    @Then("I should be redirected to blog site")
    public void i_should_be_redirected_to_blog_site() {
        wait.until(ExpectedConditions.titleContains(firstBlogTitle));
        WebElement blogTitle = driver.findElement(By.tagName("h1"));
        String blogTitleText = blogTitle.getText();
        Assert.assertEquals(firstBlogTitle, blogTitleText);
    }

    @When("I click text podcast in main page")
    public void i_click_text_podcast_in_main_page() {
        WebElement podcast = driver.findElement(By.linkText("Podcasts"));
        podcast.click();
    }

    @When("I click on first cast displayed")
    public void i_click_on_first_cast_displayed() {
        wait.until(ExpectedConditions.titleContains("Podcasts"));
        WebElement firstCast = driver.findElement(By.tagName("h3"));
        firstCastTitle = firstCast.getText();
        firstCastTitle = firstCastTitle.replace("podcast", "");
        firstCast.click();
    }

    @Then("I should be redirected to cast site")
    public void i_should_be_redirected_to_cast_site() {
        wait.until(ExpectedConditions.titleContains(firstCastTitle));
        WebElement castTitle = driver.findElement(By.tagName("h1"));
        String castTitleText = castTitle.getText();
        Assert.assertEquals(firstCastTitle, castTitleText);
    }

    @When("I search for {string} phrase")
    public void i_search_for_phrase(String phrase) {
        WebElement searchBar = driver.findElement(By.cssSelector("#header-search > form > input.crayons-header--search-input.crayons-textfield"));
        searchBar.click();
        searchBar.sendKeys(phrase);
        searchingPhrase = phrase;
        searchBar.sendKeys(Keys.ENTER);
    }

    @Then("Top {int} blogs found should have correct phrase in title")
    public void top_blogs_found_should_have_correct_phrase_in_title(Integer int1) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3.crayons-story__title")));
        wait.until(ExpectedConditions.attributeContains(By.id("substories"), "class", "search-results-loaded"));
        List<WebElement> allPosts = driver.findElements(By.className("crayons-story__body"));
        if (allPosts.size() >= int1) {
            for (int i = 0; i < int1; i++) {
                WebElement singlePost = allPosts.get(i);
                WebElement singlePostTitle = singlePost.findElement(By.cssSelector(".crayons-story__title > a"));
                String singlePostTitleText = singlePost.getText().toLowerCase(Locale.ROOT);
                boolean isPhraseInTitle = singlePostTitleText.contains(searchingPhrase);
                if (isPhraseInTitle) {
                    Assert.assertTrue(isPhraseInTitle);
                } else {
                    WebElement snippet = singlePost.findElement(By.xpath("//div[contains(@class, 'crayons-story__snippet')]"));
                    String snippetText = snippet.getText().toLowerCase(Locale.ROOT);
                    boolean isPhraseInSnippet = snippetText.contains(searchingPhrase);
                    Assert.assertTrue(isPhraseInSnippet);
                }
            }
        }
    }
}


