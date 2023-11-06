package EXO2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class EXO2 {
        protected WebDriver driver;

        public EXO2(WebDriver driver){
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }
    }
