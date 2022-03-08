package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;

public class CartPage extends PageObject {
    //    summary & checkout
    @FindBy(css = "table.summary  tr:nth-child(1) td.order-value")
    private WebElementFacade orderValueField;

    @FindBy(css = "table.summary  tr.sub-total td.order-value")
    private WebElementFacade itemSubTotalField;

    @FindBy(css = "table.summary  tr.small_order_surcharge td:nth-child(2)")
    private WebElementFacade smallOrderSurchargeFiled;

    @FindBy(css = "table.summary  tr.logistics_surcharge td:nth-child(2)")
    private WebElementFacade logisticSurchargeField;

    @FindBy(css = "table.summary  tr.total td:nth-child(2)")
    private WebElementFacade totalField;

    @FindBy(css = "table.summary  tr.small_order_surcharge td:nth-child(1) i")
    private WebElementFacade iconTooltipSmallCharge;

    @FindBy(css = "p.pod-express-items-description")
    private WebElementFacade itemDesc;

    @FindBy(css = "div.cart-item-card div.row:nth-child(1) div.brand")
    private WebElementFacade brandFirstItem;

    @FindBy(css = "div.cart-item-card div.row:nth-child(1) a.name")
    private WebElementFacade productNameFirstItem;

    @FindBy(css = "div.cart-item-card div.row:nth-child(1) div.meta > div:nth-of-type(2)")
    private WebElementFacade skuNameFirstItem;

    @FindBy(css = "div.cart-item-card div.row:nth-child(1) div.unit-upc")
    private WebElementFacade unitUPCFirstItem;

    @FindBy(css = "div.cart-item-card div.row:nth-child(1) div.unit-upc span.rb-order-code")
    private WebElementFacade idFirstItem;

    @FindBy(css = "div.cart-item-card div.row:nth-child(1) div.item-price")
    private WebElementFacade unitCaseFirstItem;

    @FindBy(css = "div.cart-item-card div.row:nth-child(1) div.case-price")
    private WebElementFacade productPriceFirstItem;

    @FindBy(css = "div.cart-item-card div.row:nth-child(1) input")
    private WebElementFacade quantityFirstItem;

    @FindBy(css = "div.cart-item-card div.row:nth-child(1) div.total")
    private WebElementFacade totalFirstItem;

    @FindBy(css = "div.shipping-address div.row > div:nth-child(2) div > div:nth-child(1) ")
    private WebElementFacade nameAddress;

    @FindBy(css = "div.shipping-address div.row > div:nth-child(2) div span.address-street1")
    private WebElementFacade addressStreet1;

    @FindBy(css = "div.shipping-address div.row > div:nth-child(2) div span.address-city")
    private WebElementFacade addressCity;

    @FindBy(css = "div.shipping-address div.row > div:nth-child(2) div span.address-state")
    private WebElementFacade addressState;

    @FindBy(css = "div.shipping-address div.row > div:nth-child(2) div span.address-zip")
    private WebElementFacade addressZip;

    @FindBy(css = "div.shipping-address div.row > div:nth-child(2) div span.address-phone_number")
    private WebElementFacade phoneNumber;

    @FindBy(css = "div.checkout-form-small-order-surcharge-warning div.message")
    private WebElementFacade noteSmallOrder;

    @FindBy(css = "span.danger")
    private WebElementFacade deleteButton;

    public void waitCartPageLoad() {
        waitFor(orderValueField);
    }

    public String getOrderValueField() {
        return orderValueField.getText();
    }

    public String getItemSubTotalField() {
        return itemSubTotalField.getText();
    }

    public String getSmallOrderSurchargeFiled() {
        return smallOrderSurchargeFiled.getText();
    }

    public String getLogisticSurchargeField() {
        return logisticSurchargeField.getText();
    }

    public String getTotalField() {
        return totalField.getText();
    }

    public String getDataTipContentOfSmallSurCharge() {
        return iconTooltipSmallCharge.getAttribute("data-tip");
    }

    public void checkPageHasText(String text) {
        shouldContainText(text);
    }

    public String getItemDesc() {
        return itemDesc.getText();
    }

    public String getBrandFirstItem() {
        return brandFirstItem.getText();
    }

    public String getProductNameFirstItem() {
        return productNameFirstItem.getText();
    }

    public String getSkuNameFirstItem() {
        return skuNameFirstItem.getText();
    }

    public String getUnitUPCFirstItem() {
        return unitUPCFirstItem.getText();
    }

    public String getIdFirstItem() {
        return idFirstItem.getText();
    }

    public String getUnitCaseFirstItem() {
        return unitCaseFirstItem.getText();
    }

    public String getProductPriceFirstItem() {
        return productPriceFirstItem.getText();
    }

    public int getQuantityFirstItem() {
        String quantity = quantityFirstItem.getValue();
        return Integer.valueOf(quantity);
    }

    public String getTotalFirstItem() {
        return totalFirstItem.getText();
    }

    public String getNameAddress() {
        return nameAddress.getText();
    }

    public String getAddressStreet1() {
        return addressStreet1.getText();
    }

    public String getAddressCity() {
        return addressCity.getText();
    }

    public String getAddressState() {
        return addressState.getText();
    }

    public String getAddressZip() {
        return addressZip.getText();
    }

    public String getPhoneNumber() {
        return phoneNumber.getText();
    }

    public String getNoteSmallOrder() {
        return noteSmallOrder.getText();
    }

    public void clickDeleteButton() {
        deleteButton.click();
    }
}
