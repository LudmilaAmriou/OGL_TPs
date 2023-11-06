package EXO2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static EXO2.Testing.driver;
import static org.junit.jupiter.api.Assertions.assertEquals;

//La page contenant la phase de verification et de l'ajout

public class buypage extends EXO2{


    //Recuperer elements html
    @FindBy(id = "gh-cart-n") public WebElement panier;
    @FindBy(xpath = "//div[@id = 'mainContent']/form/div[2]/div/div[2]/ul/li[2]/div/a") public WebElement ajouterpanier;
    @FindBy(xpath = "//div[@data-test-id='SUBTOTAL']/span/span/span") public WebElement prixtotal;

    public buypage(WebDriver driver) {
        super(driver);
    }

    //AJouter au panier
    public void acheter()
    {
        ajouterpanier.click();
    }

    //Comparer les liens
    public void verifierUrl(String url)
    {

        assertEquals(url,driver.getCurrentUrl());
    }

    //Voir si le nombre d elements achete == 1
    public void elempris(String count)
    {

        assertEquals(count,panier.getText());
    }

    //Verifier prix total
    public void verifPrix(String price){
        assertEquals(price,prixtotal.getText());
    }


}
