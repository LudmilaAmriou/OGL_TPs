package EXO2;
//La page contenant la phase de recherche du livre.
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ebaypage extends EXO2{
        // Les éléments HTML de la page ebay principale
        public ebaypage(WebDriver driver) {
                super(driver);
        }
        @FindBy(id="gh-ac") public WebElement search;
        @FindBy(id="gh-cat") public WebElement select;
        @FindBy(partialLinkText = "Python in easy steps by Mike McGrath 1840785969 The Fast Free Shipping") public WebElement book;



        // Le constructeur de la classe ebaypage


        //Selectionner la categorie livre
        public void selectcategory(String value) {
                Select category = new Select(select);
                category.selectByVisibleText(value);
        }

        //Rechercher le livre
        public void search(String value){

                search.sendKeys(value);
        }

        //Soummettre recherche
        public void submit(){

                search.submit();
        }

        //Clicker sur le livre qu'on cherche
        public void livredetails(){
                book.click();
        }


        //Changer de fenetre
        public void handleSwitchWindow(){
                String targetWindow = driver.getWindowHandle();
                for (String myWindow : driver.getWindowHandles())
                {
                        if(!targetWindow.contentEquals(myWindow))
                        {
                                driver.switchTo().window(myWindow);
                                break;
                        }
                }
        }
}


