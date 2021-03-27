package zdtestpol51bdd.devto;

import io.cucumber.java.After;
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
import zdtestpol51bdd.devto.pages.MainPage;
import zdtestpol51bdd.devto.pages.PodcastListPage;
import zdtestpol51bdd.devto.pages.SingleBlogPage;
import zdtestpol51bdd.devto.pages.SinglePodcastPage;

import java.util.List;

public class DevToStepsDefinitions {
    WebDriver driver;
    WebDriverWait wait;
    String firstBlogTitle;
    String firstCastTitle;
    String searchingPhrase;

    MainPage mainPage;
    SingleBlogPage singleBlogPage;
    PodcastListPage podcastListPage;
    SinglePodcastPage singlePodcastPage;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Given("I go to devto main page")
    public void i_go_to_devto_main_page() {
        mainPage = new MainPage(driver);
    }

    @When("I click on first blog displayed")
    public void i_click_on_first_blog_displayed() {
        firstBlogTitle = mainPage.firstBlog.getText();
        mainPage.selectFirstBlog();
    }

    @Then("I should be redirected to blog site")
    public void i_should_be_redirected_to_blog_site() {
        wait.until(ExpectedConditions.titleContains(firstBlogTitle));
        singleBlogPage = new SingleBlogPage(driver);
        String blogTitleText = singleBlogPage.blogTitle.getText();
        Assert.assertEquals(firstBlogTitle, blogTitleText);
    }

    @When("I click text podcast in main page")
    public void i_click_text_podcast_in_main_page() {
        mainPage.goToPodcastSection();
    }

    @When("I click on first podcast displayed")
    public void i_click_on_first_podcast_displayed() {
        wait.until(ExpectedConditions.titleContains("Podcasts"));
        podcastListPage = new PodcastListPage(driver);
        firstCastTitle = podcastListPage.firstCast.getText();
        firstCastTitle = firstCastTitle.replace("podcast", "");
        podcastListPage.selectFirstPodcast();
    }

    @When("I play the podcast")
    public void i_play_the_podcast() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("record")));
        singlePodcastPage = new SinglePodcastPage(driver);
        singlePodcastPage.playThePodcast();
    }

    @Then("Podcast should be played")
    public void podcast_should_be_played() {
        wait.until(ExpectedConditions.invisibilityOf(singlePodcastPage.initializing));
        boolean isPauseBtnVisible = singlePodcastPage.pauseBtn.isDisplayed();
        Assert.assertTrue(isPauseBtnVisible);
    }

    @When("I search for {string} phrase")
    public void i_search_for_phrase(String phrase) {
        WebElement searchBar = driver.findElement(By.cssSelector("#header-search > form > input.crayons-header--search-input.crayons-textfield"));
        searchBar.click();
        searchBar.sendKeys(phrase);
        searchingPhrase = phrase;
        searchBar.sendKeys(Keys.ENTER);
    }

    @Then("Top {int} blogs found should have correct phrase in title or snippet")
    public void top_blogs_found_should_have_correct_phrase_in_title_or_snippet(Integer int1) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3.crayons-story__title"))); //h3
        wait.until(ExpectedConditions.attributeContains(By.id("substories"), "class", "search-results-loaded"));
        List<WebElement> allPosts = driver.findElements(By.className("crayons-story__body")); // div - caly wpis
        if (allPosts.size() >= int1) {
            for (int i = 0; i < int1; i++) {
                WebElement singlePost = allPosts.get(i);
                WebElement singlePostTitle = singlePost.findElement(By.cssSelector(".crayons-story__title > a")); //tytul kafelka
                String singlePostTitleText = singlePostTitle.getText().toLowerCase(); // wyciagnij tekst z tytulu
                boolean isPhraseInTitle = singlePostTitleText.contains(searchingPhrase);
                if (isPhraseInTitle) { // isPhraseInTitle == true
                    Assert.assertTrue(isPhraseInTitle);
                } else {
                    WebElement snippet = singlePost.findElement(By.xpath("//div[contains(@class,'crayons-story__snippet')]"));
                    String snippetText = snippet.getText().toLowerCase();
                    boolean isPhraseInSnippet = snippetText.contains(searchingPhrase);
                    Assert.assertTrue(isPhraseInSnippet);
                }
            }
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
