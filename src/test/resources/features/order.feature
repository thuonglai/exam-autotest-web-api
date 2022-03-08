Feature: Order

  @order
  Scenario Outline: Order product
    Given sign up to app as buyer
    And cart is empty
    When search by keyword <productName>
    And add <volume> item <productName> to cart
    And hover on the cart button
    Then In top bar, volume should equal <volume>, order total should equal <totalOrder>
#    In popup
    And hover on the cart button
    And In popup, check product name = <productName>, brand = <brand>, volume = <volume>, smallOrderSurcharge = <smallOrderSurCharge>, logisticsSurCharge = <logisticsSurCharge>, price = <price>
    When click on cart button
    Then In top bar, volume should equal <volume>, order total should equal <totalOrder>
    And Show Order value: <price>, Items Subtotal: 10.00 $, Small Order surcharge: <smallOrderSurCharge>, Logistics Surcharge: <logisticsSurCharge>, Total: <totalOrder>
    And Page has text: Pod Express Items
    And Show text: These items will be consolidated and delivered to you from our distribution centers.
    And Show Brand name: <brand>, Product name: <productName>, Sku name: <skuName>, Unit UPC: <upc>, Id: <id>, Unit case: <unit>, Price: <price>, Quantity: <volume>, Total: 10.00
    And Check address name= exam store, street= 1250 Waters Place, city= Bronx County, state= NY, zip= 10461, phone= 1232123211
    And Check note = Add another $340.00 to your order to remove the Small Order Surcharge!
    And Check tool tip = Small order surcharge for orders less than $350

    Examples:
      | productName   | brand       | skuName    | volume | smallOrderSurCharge | logisticsSurCharge | totalOrder | price | id     | upc          | unit                           |
      | Product exam1 | exam brand1 | Sku1 exam1 | 1      | 30.00               | 20.00              | 60.00      | 10.00 | 28414 | 123456789012 | $10.00/unit (1 units/case) |