package EXO2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Testing {
    static WebDriver driver;
    @BeforeEach
    void setUp(){
        //Recuperer le driver.
        System.setProperty("webdriver.chrome.driver","/home/ludmila/Downloads/chromedriver_linux64/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.ebay.com");
    }
    @Test
    void testpage(){
        //Effectuer les operation sur la page ebay
        ebaypage ebaypage = new ebaypage(driver);
        ebaypage.selectcategory("Books");
        ebaypage.search("python in easy steps");
        ebaypage.submit();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ebaypage.livredetails();
        ebaypage.handleSwitchWindow();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        buypage buybook = new buypage(driver);
        buybook.acheter();
        buybook.elempris("1");
        buybook.verifierUrl("https://cart.payments.ebay.com/");
        buybook.verifPrix("US $9.37");
    }
   @AfterEach
    void close(){
        driver.close();
   }
}
