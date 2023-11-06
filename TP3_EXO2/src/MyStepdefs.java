import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyStepdefs {
    WebDriver driver;


    @Given("I am at the official website of ebay {string}")
    public void iAmAtTheOfficialWebsiteOfEbay(String arg0) {
        arg0 = "https://www.ebay.com/";
        System.setProperty("webdriver.chrome.driver","/home/ludmila/Downloads/chromedriver_linux64/chromedriver");
        driver = new ChromeDriver();
        driver.get(arg0);
    }

    @When("I select the {string} category")
    public void iSelectTheCategory(String arg0) {
        arg0 = "Books";
        Select select = new Select(driver.findElement(By.id("gh-cat")));
        select.selectByVisibleText(arg0);
    }

    @And("Search {string}")
    public void search(String arg0) {
        arg0 = "python in easy steps";
        WebElement search = driver.findElement(By.id("gh-ac"));
        search.sendKeys(arg0);
        search.submit();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @And("Select the book {string}")
    public void selectTheBook(String arg0) {
        arg0 = "Python in easy steps by Mike McGrath 1840785969 The Fast Free Shipping";
        WebElement book= driver.findElement(By.partialLinkText(arg0));
        book.click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }

    @And("Handle the page switching")
    public void handleThePageSwitching() {
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }

    @And("Add the book to the cart")
    public void addTheBookToTheCart() {
        String url = String.format("//div[@id = 'mainContent']/form/div[%d]/div/div[%d]/ul/li[%d]/div/a",2,2,2);
        driver.findElement(By.xpath(url)).click();
    }

    @Then("Verify the URL of the page {string}")
    public void verifyTheURLOfThePage(String arg0) {
        arg0 = "https://cart.payments.ebay.com/";
        assertEquals(driver.getCurrentUrl(),arg0);
    }

    @And("The number of products in the cart is {string}")
    public void theNumberOfProductsInTheCartIs(String arg0) {
        arg0 = "1";
        WebElement panier = driver.findElement(By.id("gh-cart-n"));
        assertEquals(panier.getText(),"1");

    }

    @And("Verify the price is {string}")
    public void verifyThePriceIs(String arg0) {
        arg0 = "US $9.37";
        String url = "//div[@data-test-id='SUBTOTAL']/span/span/span";
        WebElement prixtotal = driver.findElement(By.xpath(url));
        assertEquals(prixtotal.getText(),arg0);
    }
}
