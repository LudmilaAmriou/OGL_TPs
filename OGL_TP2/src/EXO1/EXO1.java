package EXO1;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class EXO1 {
    private WebDriver driver;
    @BeforeEach
    public void init(){}
    @Test
    void testEbay(){
        System.setProperty("webdriver.chrome.driver","/home/ludmila/Downloads/chromedriver_linux64/chromedriver");

        //aller vers le site
        driver = new ChromeDriver();
        driver.get("https://www.ebay.com/");

        //Selectionner la categorie
        Select select = new Select(driver.findElement(By.id("gh-cat")));
        select.selectByVisibleText("Books");

        //Rechercher "python in easy steps"
        WebElement search = driver.findElement(By.id("gh-ac"));
        search.sendKeys("python in easy steps");
        search.submit();

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        //Choisir le livre correct avec le chemain vers ce dernier.

        WebElement book= driver.findElement(By.partialLinkText("Python in easy steps by Mike McGrath 1840785969 The Fast Free Shipping"));
        book.click();

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        //Changer de fenetre
        String winHandleBefore = driver.getWindowHandle();
        for(String winHandle : driver.getWindowHandles()){
           driver.switchTo().window(winHandle);
        }

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        //Appuyer sur le lien qui permet d'ajouter le livre au panier (chemain)
       String url = String.format("//div[@id = 'mainContent']/form/div[%d]/div/div[%d]/ul/li[%d]/div/a",2,2,2);
        driver.findElement(By.xpath(url)).click();

        //Recuperer combien d'elements dans le panier
        WebElement panier = driver.findElement(By.id("gh-cart-n"));

        //Recuperer le prix total
        url = "//div[@data-test-id='SUBTOTAL']/span/span/span";
        WebElement prixtotal = driver.findElement(By.xpath(url));


        //Effectuer les 3 tests: prixtotal, lien de la page et la quantite prise
        assertEquals(driver.getCurrentUrl(),"https://cart.payments.ebay.com/" );
        assertEquals(panier.getText(),"1");
        assertEquals(prixtotal.getText(),"US $9.37");



        //Fermer les fenetres

        driver.switchTo().window(winHandleBefore);
    }
    @AfterEach
    void closeBrowser() {
        driver.close();
    }
}