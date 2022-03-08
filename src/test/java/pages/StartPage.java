package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("https://beta.podfoods.co/")
public class StartPage extends PageObject {
    @FindBy(className = "login")
    private WebElementFacade  signInBtn;

    public void openPage(){
        open();
    }

    public void clickSignInButton(){
        waitFor(signInBtn);
        clickOn(signInBtn);
    }
}
