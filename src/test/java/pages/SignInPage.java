package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends PageObject {
    @FindBy(name = "email")
    private WebElementFacade emailField;

    @FindBy(name = "password")
    private WebElementFacade passwordField;

    @FindBy(css = "div#signIn button[type='submit']")
    private WebElementFacade signInBtn;

    String radioRole = "user_role";

    public void signIn(String email, String password, String role) {
        waitFor(emailField);
        enter(email).into(emailField);
        enter(password).into(passwordField);
        inRadioButtonGroup(radioRole).selectByValue(role);
        clickOn(signInBtn);
    }
}
