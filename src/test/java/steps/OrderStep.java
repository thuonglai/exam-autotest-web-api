package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;
import pages.*;
import utils.Role;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderStep {
    private EnvironmentVariables env;
    private float total;
    StartPage startPage;
    SignInPage signInPage;
    ProductPage productPage;
    TopBarPage topBarPage;
    CartPage cartPage;

    @Given("sign up to app as buyer")
    public void signUpToApp() {
        startPage.openPage();
        startPage.clickSignInButton();
        String email = EnvironmentSpecificConfiguration.from(env).getProperty("emailBuyer");
        String password = EnvironmentSpecificConfiguration.from(env).getProperty("passwordBuyer");
        signInPage.signIn(email, password, Role.BUYER.getValue());
        productPage.closePopup();
    }

    @And("cart is empty")
    public void makeCartEmpty() {
        int countProductInCart = topBarPage.getQuantityProductInCart();
        if (countProductInCart > 0) {
            topBarPage.clickOnCartButton();
            cartPage.clickDeleteButton();
            getDriver().switchTo().alert().accept();
        }
    }

    @When("search by keyword {}")
    public void searchByKeyword(String productName) {
        productPage.searchProduct(productName);
    }

    @And("add {} item {} to cart")
    public void addToCard(int volume, String productName) {
        productPage.addToCard(productName, volume);
    }

    @And("hover on the cart button")
    public void hoverToCartButton() {
        topBarPage.hoverMouseOnCartButton();
    }

    @Then("In top bar, volume should equal {}, order total should equal {}")
    public void checkInfoCartInTopBar(int volume, float orderTotal) {
        this.total = orderTotal;
        int realVolume = topBarPage.getVolumeValue();
        assertThat(realVolume).isEqualTo(volume);
        String realTotal = topBarPage.getOrderTotal();
        String strOrderTotal = String.format("$%.2f", orderTotal);
        assertThat(realTotal).isEqualTo(strOrderTotal);
    }

    @And("In popup, check product name = {}, brand = {}, volume = {}, smallOrderSurcharge = {}, logisticsSurCharge = {}, price = {}")
    public void checkInfoCartPopup(String productName, String brand, int volume, float smallOrderSurcharge, float logisSurcharge, float price) {
        String realProductName = topBarPage.getPopupProductName();
        assertThat(realProductName).isEqualTo(productName);
        String realBrand = topBarPage.getPopupBrand();
        assertThat(realBrand).isEqualTo(brand);
        String realProPrice = topBarPage.getPopupProductPrice();
        assertThat(realProPrice).isEqualTo(String.format("$%.2f", price));
        int realQuantity = topBarPage.getPopupQuantity();
        assertThat(realQuantity).isEqualTo(volume);
        String realProTotalPrice = topBarPage.getPopupProductTotalPrice();
        float proTotalPrice = price * volume;
        assertThat(realProTotalPrice).isEqualTo(String.format("$%.2f", proTotalPrice));
        String realOrderValue = topBarPage.getPopupOrderValue();
        assertThat(realOrderValue).isEqualTo(String.format("$%.2f", proTotalPrice));
        String realItemSubTotal = topBarPage.getPopupItemsSubtotal();
        assertThat(realItemSubTotal).isEqualTo(String.format("$%.2f", proTotalPrice));
        String realSmallOrderSurcharge = topBarPage.getPopupSmallOrderSurcharge();
        assertThat(realSmallOrderSurcharge).isEqualTo(String.format("$%.2f", smallOrderSurcharge));
        String realLogisticSurcharge = topBarPage.getPopupLogisticsSurcharge();
        assertThat(realLogisticSurcharge).isEqualTo(String.format("$%.2f", logisSurcharge));
        String realTotal = topBarPage.getPopupTotal();
        assertThat(realTotal).isEqualTo(String.format("$%.2f", this.total));
    }

    @When("click on cart button")
    public void clickOnCartButton() {
        topBarPage.clickOnCartButton();
    }

    @Then("Show Order value: {}, Items Subtotal: {}, Small Order surcharge: {}, Logistics Surcharge: {}, Total: {}")
    public void checkSummary(float price, float itemSubtotal, float smallOrderSurcharge, float logisticSurcharge, float total) {
        cartPage.waitCartPageLoad();
        String realOrderValue = cartPage.getOrderValueField();
        assertThat(realOrderValue).isEqualTo(String.format("$%.2f", itemSubtotal));
        String realItemSubtotal = cartPage.getItemSubTotalField();
        assertThat(realItemSubtotal).isEqualTo(String.format("$%.2f", itemSubtotal));
        String realSmallOrderSurcharge = cartPage.getSmallOrderSurchargeFiled();
        assertThat(realSmallOrderSurcharge).isEqualTo(String.format("$%.2f", smallOrderSurcharge));
        String realLogisticSurcharge = cartPage.getLogisticSurchargeField();
        assertThat(realLogisticSurcharge).isEqualTo(String.format("$%.2f", logisticSurcharge));
        String realTotal = cartPage.getTotalField();
        assertThat(realTotal).isEqualTo(String.format("$%.2f", total));
    }

    @And("Page has text: {}")
    public void checkPageHasText(String text) {
        cartPage.checkPageHasText(text);
    }

    @And("Show text: {}")
    public void checkShowText(String text) {
        String realItemDesc = cartPage.getItemDesc();
        assertThat(realItemDesc).isEqualTo(text);
    }

    @And("Show Brand name: {}, Product name: {}, Sku name: {}, Unit UPC: {}, Id: {}, Unit case: {}, Price: {}, Quantity: {}, Total: {}")
    public void checkShowText(String brand, String productName, String skuName, String upc, String id, String unitCase, float price, int quantity, float total) {
        String realBrand = cartPage.getBrandFirstItem();
        assertThat(realBrand).isEqualTo(brand);
        String realProductName = cartPage.getProductNameFirstItem();
        assertThat(realProductName).isEqualTo(productName);
        String realSkuName = cartPage.getSkuNameFirstItem();
        assertThat(realSkuName).isEqualTo(skuName);
        String realUPC = cartPage.getUnitUPCFirstItem();
        assertThat(realUPC).contains(upc);
        String realID = cartPage.getIdFirstItem();
        assertThat(realID).contains(id);
        String realUnitCase = cartPage.getUnitCaseFirstItem();
        assertThat(realUnitCase).isEqualTo(unitCase);
        String realProductPrice = cartPage.getProductPriceFirstItem();
        assertThat(realProductPrice).isEqualTo(String.format("$%.2f", price));
        int realQuantity = cartPage.getQuantityFirstItem();
        assertThat(realQuantity).isEqualTo(quantity);
        String realTotal = cartPage.getTotalFirstItem();
        assertThat(realTotal).isEqualTo(String.format("$%.2f", total));
    }

    @And("Check address name= {}, street= {}, city= {}, state= {}, zip= {}, phone= {}")
    public void checkAddress(String name, String street, String city, String state, String zip, String phone) {
        String realNameAddress = cartPage.getNameAddress();
        assertThat(realNameAddress).isEqualTo(name);
        String realStreet = cartPage.getAddressStreet1();
        assertThat(realStreet).contains(street);
        String realCity = cartPage.getAddressCity();
        assertThat(realCity).contains(city);
        String realState = cartPage.getAddressState();
        assertThat(realState).contains(state);
        String realZip = cartPage.getAddressZip();
        assertThat(realZip).isEqualTo(zip);
        String realPhone = cartPage.getPhoneNumber();
        assertThat(realPhone).isEqualTo(phone);
    }

    @And("Check note = {}")
    public void checkNote(String note) {
        String realNote = cartPage.getNoteSmallOrder();
        assertThat(realNote).isEqualTo(note);
    }

    @And("Check tool tip = {}")
    public void checkToolTip(String tooltip) {
        String realToolTip = cartPage.getDataTipContentOfSmallSurCharge();
        assertThat(realToolTip).isEqualTo(tooltip);
    }
}
