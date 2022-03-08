package pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;

import static java.time.temporal.ChronoUnit.SECONDS;

public class ProductPage extends PageObject {
    @FindBy(css = ".close-btn")
    private WebElementFacade closePopupBtn;

    @FindBy(css = "input[name='query']")
    private WebElementFacade searchBarField;

    @FindBy(css = "div.search-input button")
    private WebElementFacade searchBtn;

    @FindBy(name = "cart_items[0][quantity]")
    private WebElementFacade firstVolumeProduct;

    @FindBy(css = "button[form='ProductQuickViewForm']")
    private WebElementFacade addToCartPopupButton;

    @FindBy(css = "div.rb-notification  span[aria-label=\"close\"]")
    private WebElementFacade closeNotificationBtn;

    public void closePopup() {
        waitFor(closePopupBtn);
        clickOn(closePopupBtn);
        closePopupBtn.waitUntilNotVisible();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void searchProduct(String productName) {
        searchBarField.waitUntilClickable();
        enter(productName).into(searchBarField);
        clickOn(searchBtn);
    }

    public void addToCard(String productName, int volume) {
        withTimeoutOf(5, SECONDS).waitFor(By.partialLinkText(productName));
        String xpathIconAdd= String.format("//a[@data-tip='%s']/parent::div/following-sibling::div[contains(@class,'actions')]",productName);
        WebElementFacade iconAdd = find(By.xpath(xpathIconAdd));
        iconAdd.click();
        waitForRenderedElements(By.xpath("//div[@class=\"modal-header\"]/h4[contains(.,\"Add to cart\")]"));
        enter(String.valueOf(volume)).into(firstVolumeProduct);
        clickOn(addToCartPopupButton);
        waitFor(closeNotificationBtn);
        clickOn(closeNotificationBtn);
    }
}
