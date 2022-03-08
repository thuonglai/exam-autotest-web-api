package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public class TopBarPage extends PageObject {
    private String cssIconCard = "div.top-bar div.cart i";

    @FindBy(css = "div.top-bar div.cart i")
    private WebElementFacade cartBtn;

    private String cssQuantityField = "span.counter";
    @FindBy(css = "span.counter")
    private WebElementFacade numberVolume;

    @FindBy(css = "div.cart span.caption")
    private WebElementFacade orderTotal;

    //    cart popup
    @FindBy(css = "div.cart-popper div.name")
    private WebElementFacade popupProName;

    @FindBy(css = "div.cart-popper div.brand")
    private WebElementFacade popupBrand;

    @FindBy(css = "div.cart-popper div.summary div.description span.case-price")
    private WebElementFacade casePrice;

    @FindBy(css = "div.cart-popper span.quantity")
    private WebElementFacade quantity;

    @FindBy(css = "div.cart-popper div.summary div.total")
    private WebElementFacade productTotalPrice;

    @FindBy(css = "div.cart-popper div.report td.popper-order-value")
    private WebElementFacade orderValue;

    @FindBy(css = "div.cart-popper div.report td.order-value")
    private WebElementFacade itemsSubtotal;

    @FindBy(css = "div.cart-popper div.report td.popper-order-shipping-fee")
    private WebElementFacade smallOrderSurcharge;

    @FindBy(css = "div.cart-popper div.report td.popper-order-logistics-shipping-fee")
    private WebElementFacade logisticsSurcharge;

    @FindBy(css = "div.cart-popper div.report td.popper-order-total")
    private WebElementFacade total;

    public void hoverMouseOnCartButton() {
        moveTo(By.cssSelector(cssIconCard));
    }

    public void clickOnCartButton() {
        cartBtn.click();
    }

    public int getQuantityProductInCart() {
        int volume = 0;
        if (isElementVisible(By.cssSelector(cssQuantityField)) == true) {
            String strVolume = numberVolume.getText();
            volume = Integer.valueOf(strVolume);
        }
        return volume;
    }

    public int getVolumeValue() {
        String volume = numberVolume.getText();
        return Integer.valueOf(volume);
    }

    public String getOrderTotal() {
        return orderTotal.getText();
    }

    public String getPopupProductName() {
        return popupProName.getText();
    }

    public String getPopupBrand() {
        return popupBrand.getText();
    }

    public String getPopupProductPrice() {
        return casePrice.getText();
    }

    public int getPopupQuantity() {
        String volume = quantity.getText();
        return Integer.valueOf(volume);
    }

    public String getPopupProductTotalPrice() {
        return productTotalPrice.getText();
    }

    public String getPopupOrderValue() {
        return orderValue.getText();
    }

    public String getPopupItemsSubtotal() {
        return itemsSubtotal.getText();
    }

    public String getPopupSmallOrderSurcharge() {
        return smallOrderSurcharge.getText();
    }

    public String getPopupLogisticsSurcharge() {
        return logisticsSurcharge.getText();
    }

    public String getPopupTotal() {
        return total.getText();
    }
}
