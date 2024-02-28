package com.ami.pageobjects;

import java.time.Duration;

/**
 * Project Name: Amazon MultiAccount 
 * Author: Yatindra Kinker 
 * Version: 
 * Reviewed By: Abhay Hayaran 
 * Date of Creation: December 20, 2022
 *  Modification History:
 * Date of change: 
 * Version: 
 * changed function:  
 * change description:
 * Modified By:
 */

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ami.pageobjects.reusableclasses.ReusableMethods;
import com.ami.resources.Utilities;

public class OrderPage extends OrderPageOR {

	private static Logger log = LogManager.getLogger(OrderPage.class.getName());

	Utilities util;
	ReusableMethods reuse;
	private String amazonOrderIdString = "";

	public OrderPage(Utilities util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
		reuse = new ReusableMethods(util);
	}

	public void clickOnSaveBtn() {
		util.switchToDefaultContent();
		util.click(saveButton);
		util.switchToIFrame();
	}

	public void enableOrderSettings() {
		if (enableOrdersBtn.getAttribute(ARIAPRESSED).equalsIgnoreCase(FALSEVAL)) {
			util.click(enableOrdersBtn);
			clickOnSaveBtn();
		}
	}

	/**
	 * This block of code verifies that after clicking on settings user redirects on
	 * settings page.
	 * 
	 * @param input
	 */
	public void redirectionToSettings() {

		util.goToSection("settings");
		util.clickOnMultiAcccounSettings();
		util.waitUntilElementIsVisible(orderSettings, 30);
		util.click(orderSettings);
		util.waitUntilElementIsVisible(orderTabBtn, 30);
		util.click(orderTabBtn);
	}

	public void orderSettingToggleBtnIsWorking() {
		String statusToggleBtn = enableOrdersBtn.getAttribute(ARIAPRESSED);

		if (statusToggleBtn.equalsIgnoreCase(TRUEVAL)) {
			util.click(disableOrdersBtn);
			statusToggleBtn = enableOrdersBtn.getAttribute(ARIAPRESSED);
			util.click(enableOrdersBtn);
			Assert.assertEquals(statusToggleBtn, FALSEVAL);

		} else if (statusToggleBtn.equalsIgnoreCase(FALSEVAL)) {
			util.click(enableOrdersBtn);
			statusToggleBtn = enableOrdersBtn.getAttribute(ARIAPRESSED);
			Assert.assertEquals(statusToggleBtn, TRUEVAL);

		}
	}

	public void toggleBtnsOnOrderSettingIsWorking() {
		enableOrderSettings();
		WebElement[] toggleBtns = { createOrdersForNonExistingProductsTogglebutton, amazonItemTaxOnShopifyTogglebutton,
				amazonShippingTaxOnShopifyTogglebutton, syncFBAOrdersOnShopifyTogglebutton, syncCPFNumberTogglebutton,
				addTagsInOrderTogglebutton, modifyShopifyOrderNameTogglebutton };

		for (WebElement toggleBtn : toggleBtns) {
			String statusToggleBtn = toggleBtn.getAttribute(ARIACHECKED);
			util.click(toggleBtn);
			Assert.assertNotEquals(statusToggleBtn, toggleBtn.getAttribute(ARIACHECKED));
		}

		util.click(shipmentTabBtn);
		util.click(orderTabBtn);
	}

	/**
	 * Check that  Shopify order source identifier is alredy eneable
	 */
	public void verifyOrderSourceIdentifierIsCheckedAndDisabled() {
		util.click(orderTabBtn);
		boolean isTrue = false;
		String statusToggleBtn = orderSourceIdentifierTogglebutton.getAttribute(ARIACHECKED);
		boolean toggleBtnIsEnabled = orderSourceIdentifierTogglebutton.isEnabled();
		
		if(!toggleBtnIsEnabled && statusToggleBtn.equalsIgnoreCase(TRUEVAL)) {
			isTrue = true;
		}
		
		Assert.assertTrue(isTrue);
	}
	
	/**
	 * Check that  if Shopify order source identifier textfield is blank and click on save 
	 */
	public void orderSourceIdentifierInputFieldIsBlank() {
		String val = orderSourceIdentifierInputField.getAttribute(VAL);
		util.clear(orderSourceIdentifierInputField);
		clickOnSaveBtn();
		util.waitUntilElementIsVisible(someInfoMissingErrMsg, 15);
		util.enterValue(orderSourceIdentifierInputField, val);
	}
	
	public void updateSrcIdentifierVal(String val) {
		String existingVal = orderSourceIdentifierInputField.getAttribute(VAL);
		
		if(!existingVal.equalsIgnoreCase(val)) {
			util.enterValue(orderSourceIdentifierInputField, val);
			clickOnSaveBtn();
			util.hold(3);
		}
		
	}
	
	public void validateSrcIdentifierVal(String identifierVal) {
		String val = orderSrcIdentifierOrChanelNameShopifyOrderPage.getText().trim();
		Assert.assertEquals(val, identifierVal);
	}
	
	
	
	public void enableDisableModifyShopifyOrderNameBtn(String status, String prefix, String suffix) {
		enableCreateOrderForNonExistingProductsTogglebutton();
		
		if(status.equalsIgnoreCase("enable")) {
			
			if (modifyShopifyOrderNameTogglebutton.getAttribute(ARIACHECKED).equalsIgnoreCase(FALSEVAL)) {
				util.click(modifyShopifyOrderNameTogglebutton);
				
				util.enterValue(modifyShopifyOrderPrefixInputField, prefix);
				util.enterValue(modifyShopifyOrderSuffixInputField, suffix);
				
				clickOnSaveBtn();
			}
		}else {
			if (modifyShopifyOrderNameTogglebutton.getAttribute(ARIACHECKED).equalsIgnoreCase(TRUEVAL)) {
				util.click(modifyShopifyOrderNameTogglebutton);
				clickOnSaveBtn();
			}
		}
	}
	
	public void verifyOrderTitleContainsPrefixSuffixAndOrderId(String orderId, String prefix, String suffix) {
		String orderTitle = util.getTagValue(shopifyOrderTitle);
		
		String orderIdTitle = StringUtils.substringAfter(orderTitle, prefix).trim();
		orderIdTitle = StringUtils.substringBefore(orderIdTitle, suffix).trim();
		
		Assert.assertEquals(orderIdTitle, orderId);
		
		orderTitle = util.getTagValue(shopifyOrderTitle);
		String prefixTitle = StringUtils.substringBefore(orderTitle, orderId).trim();
		Assert.assertEquals(prefixTitle, prefix);
		
		orderTitle = util.getTagValue(shopifyOrderTitle);
		String suffixTitle = StringUtils.substringAfter(orderTitle, orderId).trim();
		Assert.assertEquals(suffixTitle, suffix);
		
	}

	/**
	 * This block of code validates that toggle button before Enable Order Syncing
	 * from App is working properly and when it is disabled "Sync Order Settings to
	 * Shopify", "Sync Shipment Settings", "Order Cancelation & Refund Settings"
	 * section are not visible.
	 * 
	 */
	public void TC_AM_OS_002() {
//		Checks that if toggle button is active,  On clicking it should get inactive.
		if (util.extractValueByAttributes(enableOrderSyncingfromAppToggleButton, "aria-checked").equals("true")) {
			util.click(enableOrderSyncingfromAppToggleButton);
			log.info("clicked on toggle button before Enable Order Syncing");

			try {
				if (util.extractValueByAttributes(enableOrderSyncingfromAppToggleButton, "aria-checked").equals("false")
						&& !util.isElementDisplyed(orderCancelationAndRefundSettingsHeading)
						&& !util.isElementDisplyed(syncOrderSettingsToShopifyHeading)
						&& !util.isElementDisplyed(syncShipmentSettingsHeading)) {
					Assert.assertTrue(true);
					log.info("toggle button is disabled");
				} else {
					log.error("toggle button is enabled");
					Assert.assertTrue(false);
				}
			} catch (NoSuchElementException e) {
				Assert.assertTrue(true);
				log.info("toggle button is disabled");
			}
		}

//		Checks that if toggle button is inactive,  On clicking it should get active.
		else if (util.extractValueByAttributes(enableOrderSyncingfromAppToggleButton, "aria-checked").equals("false")) {
			util.click(enableOrderSyncingfromAppToggleButton);
			log.info("clicked on toggle button before Enable Order Syncing");

			if (util.extractValueByAttributes(enableOrderSyncingfromAppToggleButton, "aria-checked").equals("false")
					&& util.isElementDisplyed(orderCancelationAndRefundSettingsHeading)
					&& util.isElementDisplyed(syncOrderSettingsToShopifyHeading)
					&& util.isElementDisplyed(syncShipmentSettingsHeading)) {
				log.info("toggle button is enabled");
				Assert.assertTrue(true);
			} else {
				log.error("toggle button is disabled");
				Assert.assertTrue(false);
			}
		}
	}

	/**
	 * This block of code validates that toggle button before "Shopify Order source
	 * identifier" is enabled.
	 */
	public void TC_AM_OS_003() {
//		If Enable Order Syncing from App is disabled enable it.
		if (util.extractValueByAttributes(enableOrderSyncingfromAppToggleButton, "aria-checked").equals("false")) {
			util.click(enableOrderSyncingfromAppToggleButton);
		}

		if (util.extractValueByAttributes(shopifyOrderSourceIdentifierTogglebutton, "aria-checked").equals("true")) {
			Assert.assertTrue(true);
			log.info("toggle button is enabled");
		} else {
			log.error("toggle button is disabled");
			Assert.assertTrue(false);
		}
	}

	/**
	 * This block of code validates that if "Shopify Order source identifier" input
	 * field is left blank and if save button is clicked the toast message showing
	 * error is appeared on screen.
	 * 
	 */
	public void TC_AM_OS_004(HashMap<String, String> input) {

		util.enterValue(shopifyOrderSourceIdentifierInputField, input.get("no_value"));
		clickOnSaveBtn();
		util.hold(2);

		if (util.isElementDisplyed(errorMsgWhenShopifyOrderSourceIdentifierInputFieldIsBlank)) {
			Assert.assertTrue(true);
			log.info("Toast error msg is displayed");
		} else {
			log.error("Toast error msg is not displayed");
			Assert.assertTrue(false);
		}

		util.enterValue(shopifyOrderSourceIdentifierInputField, "xyz");
		clickOnSaveBtn();
		util.waitUntilElementIsVisible(orderSettingsSavedSuccessfullyMsg);
		util.hold(5);
		util.enterValue(shopifyOrderSourceIdentifierInputField, input.get("amazon"));
		clickOnSaveBtn();
		util.waitUntilElementIsVisible(orderSettingsSavedSuccessfullyMsg);
	}

	/**
	 * This block of code verifies that after enabling "Create Orders for
	 * non-existing Products" toggle button it should get active.
	 */
	public void TC_AM_OS_006() {

		if (util.extractValueByAttributes(createOrdersForNonExistingProductsTogglebutton, "aria-checked")
				.equals("false")) {
			util.click(createOrdersForNonExistingProductsTogglebutton);

			if (util.extractValueByAttributes(createOrdersForNonExistingProductsTogglebutton, "aria-checked")
					.equals("true")) {
				Assert.assertTrue(true);
				log.info("toggle button is working");
			} else {
				log.error("toggle button is not working");
				Assert.assertTrue(false);
			}
		}

	}

	/**
	 * This block of code validates that if Create Orders for non-existing Products
	 * toggle button is enabled the user can create order or not.
	 * 
	 * @param input
	 */

	public void enableCreateOrderForNonExistingProductsTogglebutton() {
		util.click(orderSettings);

		util.click(orderTabBtn);

		if (enableOrdersBtn.getAttribute(ARIAPRESSED).equalsIgnoreCase(FALSEVAL)) {
			enableOrdersBtn.click();
			clickOnSaveBtn();
		}

		if (createOrdersForNonExistingProductsTogglebutton.getAttribute(ARIACHECKED).equalsIgnoreCase(FALSEVAL)) {
			createOrdersForNonExistingProductsTogglebutton.click();
			clickOnSaveBtn();
		}

	}

	/**
	 * This block of code validates that if Create Orders for non-existing Products
	 * toggle button is disabled the user can create order or not.
	 */
	public void disableCreateOrderForNonExistingProductsTogglebutton() {
		util.click(orderSettings);

		if (enableOrdersBtn.getAttribute(ARIAPRESSED).equalsIgnoreCase(FALSEVAL)) {
			enableOrdersBtn.click();
			clickOnSaveBtn();
		}

		if (createOrdersForNonExistingProductsTogglebutton.getAttribute(ARIACHECKED).equalsIgnoreCase(TRUEVAL)) {
			createOrdersForNonExistingProductsTogglebutton.click();
			clickOnSaveBtn();
		}

	}
	
	public void enableDisableAmazonItemTaxOnShopifyToggleBtn(String status) {
		enableCreateOrderForNonExistingProductsTogglebutton();
		
		if(status.equalsIgnoreCase("enable")) {
			if (amazonItemTaxOnShopifyTogglebutton.getAttribute(ARIACHECKED).equalsIgnoreCase(FALSEVAL)) {
				amazonItemTaxOnShopifyTogglebutton.click();
				clickOnSaveBtn();
			}
		}else {
			if (amazonItemTaxOnShopifyTogglebutton.getAttribute(ARIACHECKED).equalsIgnoreCase(TRUEVAL)) {
				amazonItemTaxOnShopifyTogglebutton.click();
				clickOnSaveBtn();
			}
		}

		
	}
	
	public void enableDisableAmazonShippingTaxOnShopifyToggleBtn(String status) {
		enableCreateOrderForNonExistingProductsTogglebutton();
		
		if(status.equalsIgnoreCase("enable")) {
			if (amazonShippingTaxOnShopifyTogglebutton.getAttribute(ARIACHECKED).equalsIgnoreCase(FALSEVAL)) {
				amazonShippingTaxOnShopifyTogglebutton.click();
				clickOnSaveBtn();
			}
		}else {
			if (amazonShippingTaxOnShopifyTogglebutton.getAttribute(ARIACHECKED).equalsIgnoreCase(TRUEVAL)) {
				amazonShippingTaxOnShopifyTogglebutton.click();
				clickOnSaveBtn();
			}
		}

		
	}

	/**
	 * This method opens particular order in shopify order page on the basis of
	 * orderId.
	 * 
	 * @param orderId
	 */
	public void openOrder(String orderId) {
//		It redirects to order page for given order Id
		util.getShopifyOrder(orderId);
	}

	/**
	 * This validates order is created or not
	 * 
	 * @param input
	 */
	public void validateOrderIsCreated() {

		util.listIsNotEmpty(editBtnsOrderPage);
	}
	
	/**
	 * verifies tax field is added in shopify orders.
	 * @param tax
	 */
	public void validateAmazonTax(String tax) {
		util.validateListIsNotEmpty(taxLabelShopifyOrderPage);
		String taxCost = itemTaxShopifyOrderPage.getText().trim();
		Assert.assertEquals(tax, taxCost);
	}
	
	/**
	 * verifies tax field is missinf in shopify orders.
	 */
	public void validateAmazonTaxIsNotVisibleInOrder() {
		util.validateListIsEmpty(taxLabelShopifyOrderPage);
	}
	
	/**
	 * verifies tax field is added in shopify orders.
	 * @param tax
	 */
	public void validateAmazonShippingTax(String tax) {
		util.validateListIsNotEmpty(shippingTaxLabelShopifyOrderPage);
		String taxCost = shippingTaxShopifyOrderPage.getText().trim();
		Assert.assertEquals(tax, taxCost);
	}
	
	/**
	 * verifies shipping tax field is missing in shopify orders.
	 */
	public void validateAmazonShiipingTaxIsNotVisibleInOrder() {
		util.validateListIsEmpty(shippingTaxLabelShopifyOrderPage);
	}


	/**
	 * This method searches for failed orders with orderId
	 * 
	 * @param amazonOrderId
	 */
	public void searchFailedOrders(String amazonOrderId) {
		util.goToMultiAccountOverView();
		util.click(manageFailedOrders);
		util.searchFailedOrder(amazonOrderId);
		
		util.listIsEmpty(noFoundImgList);
		
	}

	/**
	 * This block of code validates that if user clicks on "Add Item Tax with
	 * Orders" if it is disabled it should enabled and vice-versa. And when user
	 * enables and creates order the tax should be added in the total amount
	 */

	public void enableAddItemTaxWithOrdersToggleButton() {
		util.enableButton(createOrdersForNonExistingProductsTogglebutton);
		util.enableButton(addItemTaxWithOrdersToggleButton);
		clickOnSaveBtn();
	}

	/**
	 * This method validates that "Tax" is available in order.
	 */
	public void validateTaxFieldIsAvailableInOrder() {

		if (util.isElementDisplyed(tax)) {
			Assert.assertTrue(true);
			log.info("tax field is displayed");
		} else {
			log.error("tax field is not displayed");
			Assert.assertTrue(false);
		}
	}
	

	public void disableAddTagsInOrder() {
		enableCreateOrderForNonExistingProductsTogglebutton();
		if (addTagsInOrderTogglebutton.getAttribute(ARIACHECKED).equalsIgnoreCase(TRUEVAL)) {
			addTagsInOrderTogglebutton.click();
			clickOnSaveBtn();
		}
	}
	
	public void enableAddTagsInOrder() {
		enableCreateOrderForNonExistingProductsTogglebutton();
		if (addTagsInOrderTogglebutton.getAttribute(ARIACHECKED).equalsIgnoreCase(FALSEVAL)) {
			addTagsInOrderTogglebutton.click();
			clickOnSaveBtn();
		}
		
	}
	
	public void selectTagsToShowInOrders() {
		disableAddTagsInOrder();
		util.hold(3);
		enableAddTagsInOrder();
		util.hold(3);
		
		WebElement[] arrCheckbox = {amazonOrderIDTagCheckbox, sellerIDTagCheckbox,
				amazonTagCheckbox};
		
		for(WebElement arr: arrCheckbox) {
			if (arr.getAttribute(ARIACHECKED).equalsIgnoreCase(FALSEVAL)) {
				util.click(arr);
			}
		}
		
		clickOnSaveBtn();
	}
	
	public void addCustomTagsInOrder(String tag1, String tag2) {

		if (setCustomTagCheckbox.getAttribute(ARIACHECKED).equalsIgnoreCase(FALSEVAL)) {
			util.click(setCustomTagCheckbox);
			util.enterValue(setCustomTagInputField, tag1);
			util.pressEnter();
			util.enterValue(setCustomTagInputField, tag2);
			util.pressEnter();
			clickOnSaveBtn();
		}
	}
	
	public String getSellerId() {
		util.goToSection(SETTINGS);
		util.click(accountsTabSettings);
		String sellerId = amazonID.getText();
		sellerId = StringUtils.substringAfter(sellerId, "Amazon ID: ").trim();
		
		return sellerId;
		
	}
	
	public void verifyTagsAreInOrder(String orderId, String sellerId, String tag1, String tag2, String isAvailable) {
		
		List<WebElement> orderIdTags = util.getDriver().findElements(By.cssSelector("span[title='"+orderId+"']"));
		List<WebElement> amazonTags = util.getDriver().findElements(By.cssSelector("span[title='amazon']"));
		List<WebElement> amazonIDs = util.getDriver().findElements(By.cssSelector("span[title='"+sellerId+"']"));
		List<WebElement> customTags1 = util.getDriver().findElements(By.cssSelector("span[title='"+tag1+"']"));
		List<WebElement> customTags2 = util.getDriver().findElements(By.cssSelector("span[title='"+tag2+"']"));
		
		if(isAvailable.equalsIgnoreCase("yes")) {
			util.listIsNotEmpty(orderIdTags);
			util.listIsNotEmpty(amazonTags);
			util.listIsNotEmpty(amazonIDs);
			util.listIsNotEmpty(customTags1);
			util.listIsNotEmpty(customTags2);
		}else {
			util.listIsEmpty(orderIdTags);
			util.listIsEmpty(amazonTags);
			util.listIsEmpty(amazonIDs);
			util.listIsEmpty(customTags1);
			util.listIsEmpty(customTags2);
		}
				
	}
	
	

	/**
	 * This method validates that "Add Item Tax with Orders" toggle button is
	 * working properly.
	 */
	public void TC_AM_OS_008() {

		if (util.extractValueByAttributes(addItemTaxWithOrdersToggleButton, "aria-checked").equals("false")) {
			util.click(addItemTaxWithOrdersToggleButton);

			if (util.extractValueByAttributes(addItemTaxWithOrdersToggleButton, "aria-checked").equals("true")) {
				Assert.assertTrue(true);
				log.info("toggle button is enabled");
			} else {
				log.error("toggle button is disabled");
				Assert.assertTrue(false);
			}

		} else if (util.extractValueByAttributes(addItemTaxWithOrdersToggleButton, "aria-checked").equals("true")) {
			util.click(addItemTaxWithOrdersToggleButton);

			if (util.extractValueByAttributes(addItemTaxWithOrdersToggleButton, "aria-checked").equals("false")) {
				Assert.assertTrue(true);
				log.info("toggle button is disabled");
			} else {
				log.error("toggle button is enabled");
				Assert.assertTrue(false);
			}
		}
	}

	/**
	 * This block of code validates that if user clicks on "Add Item Tax with
	 * Orders" if it is disabled it should enabled and vice-versa. And when user
	 * disables and creates order the tax should not be added in the total amount.
	 */
	public void TC_AM_OS_009() {
		util.goToMultiAcccounSettings();

		if (util.extractValueByAttributes(addItemTaxWithOrdersToggleButton, "aria-checked").equals("true")) {
			util.click(addItemTaxWithOrdersToggleButton);
			log.info("toggle button is disabled");
		}

//		Verify tax should not be added in bill.
	}

	public void disableAddItemTaxWithOrdersToggleButton() {
		util.goToMultiAcccounSettings();
		util.enableButton(enableOrderSyncingfromAppToggleButton);
		util.enableButton(createOrdersForNonExistingProductsTogglebutton);
		util.disableButton(addItemTaxWithOrdersToggleButton);
		clickOnSaveBtn();
	}

	public void validateTaxFieldIsNotAvailableInOrder() {

		try {
			if (!util.isElementDisplyed(tax)) {
				Assert.assertTrue(true);
				log.info("tax field is not displayed");
			} else {
				log.error("tax field is displayed");
				Assert.assertTrue(false);
			}
		} catch (NoSuchElementException e) {
			Assert.assertTrue(true);
			log.info("tax field is not displayed");
		}
	}

	/**
	 * This block of code validates that if user clicks on "Sync CPF Number", if it
	 * is disabled it should enabled and vice-versa. If it is enabled then it should
	 * sync => only used for Brazil customers.
	 */
	public void TC_AM_OS_010() {
		util.goToMultiAcccounSettings();
		new WebDriverWait(util.getDriver(), Duration.ofSeconds(30))
				.until(ExpectedConditions.visibilityOf(sellerNameSelector));
		util.enableButton(enableOrderSyncingfromAppToggleButton);

//		If toggle button is enabled , click on it if it get disabled => test case pass else fail
		if (util.extractValueByAttributes(syncCPFNumberToggleButton, "aria-checked").equals("true")) {
			util.click(syncCPFNumberToggleButton);
			log.info("toggle button is clicked");

			if (util.extractValueByAttributes(syncCPFNumberToggleButton, "aria-checked").equals("false")) {
				Assert.assertTrue(true);
				log.info("syncCPFNumber toggle button is disabled");
			} else {
				log.error("syncCPFNumber toggle button is enabled");
				Assert.assertTrue(false);
			}
		}

//		If toggle button is disabled , click on it if it get enabled => test case pass else fail
		else if (util.extractValueByAttributes(syncCPFNumberToggleButton, "aria-checked").equals("false")) {
			util.click(syncCPFNumberToggleButton);
			log.info("toggle button is clicked");

			if (util.extractValueByAttributes(syncCPFNumberToggleButton, "aria-checked").equals("true")) {
				Assert.assertTrue(true);
				log.info("syncCPFNumber toggle button is enabled");
			} else {
				log.error("syncCPFNumber toggle button is disabled");
				Assert.assertTrue(false);
			}
		}
	}

	/**
	 * If user disables "Sync CPF Number" then it should not sync.
	 */
	public void TC_AM_OS_011() {

		if (util.extractValueByAttributes(syncCPFNumberToggleButton, "aria-checked").equals("true")) {
			util.click(syncCPFNumberToggleButton);
		}
	}

	/**
	 * This block of code validates that if user clicks on "Add Tags in Order:"
	 * toggle button. "The Amazon order ID", "Seller ID", "Amazon", "Set Custom Tag"
	 * toggle button are displayed.
	 */
	public void TC_AM_OS_012() {
		util.clickOnMultiAcccounSettings();
		new WebDriverWait(util.getDriver(), Duration.ofSeconds(30))
				.until(ExpectedConditions.visibilityOf(sellerNameSelector));
		util.enableButton(enableOrderSyncingfromAppToggleButton);

		if (util.extractValueByAttributes(addTagsInOrderToggleButton, "aria-checked").equals("false")) {
			util.click(addTagsInOrderToggleButton);

			if (util.isElementDisplyed(amazonOrderIDToggleButton) && (util.isElementDisplyed(sellerIDToggleButton))
					&& (util.isElementDisplyed(amazonToggleButton))
					&& (util.isElementDisplyed(setCustomTagToggleButton))) {
				Assert.assertTrue(true);
				log.info("All options are visible.");
			} else {
				log.error("All options are not visible.");
				Assert.assertTrue(false);
			}
		}
	}

	/**
	 * This block of code validates that when "The Amazon order ID", "Seller ID",
	 * "Amazon", "Set Custom Tag" toggle button are working properly.
	 */
	public void TC_AM_OS_013() {
		util.goToMultiAcccounSettings();
		util.enableButton(enableOrderSyncingfromAppToggleButton);
		util.enableButton(addTagsInOrderToggleButton);
		util.enableButtonAndValidate(amazonOrderIDToggleButton);
		util.disableButtonAndValidate(amazonOrderIDToggleButton);
		util.enableButtonAndValidate(sellerIDToggleButton);
		util.disableButtonAndValidate(sellerIDToggleButton);
		util.enableButtonAndValidate(amazonToggleButton);
		util.disableButtonAndValidate(amazonToggleButton);
		util.enableButtonAndValidate(setCustomTagToggleButton);
		util.disableButtonAndValidate(setCustomTagToggleButton);
	}

	/**
	 * This block of code validates when "Amazon OrderID" toggle button is enabled
	 * the orderID should be visible in order.
	 */
	public void TC_AM_OS_014() {
		util.clickOnMultiAcccounSettings();
		util.enableButton(enableOrderSyncingfromAppToggleButton);
		util.enableButton(addTagsInOrderToggleButton);
		util.enableButton(amazonOrderIDToggleButton);
	}

	/**
	 * This block of code validates when "Amazon SellerID" toggle button is enabled
	 * the SellerID should be visible in order.
	 */
	public void TC_AM_OS_015() {
		util.enableButton(enableOrderSyncingfromAppToggleButton);
		util.enableButton(addTagsInOrderToggleButton);
		util.enableButton(sellerIDToggleButton);
	}

	/**
	 * This block of code validates when "Amazon" toggle button is enabled the
	 * Amazon should be visible in order.
	 */
	public void TC_AM_OS_016() {
		util.enableButton(enableOrderSyncingfromAppToggleButton);
		util.enableButton(addTagsInOrderToggleButton);
		util.enableButton(amazonToggleButton);
	}

	/**
	 * This block of code validates when "set Custom Tag" toggle button is enabled
	 * the Custom Tag should be visible in order.
	 */
	public void TC_AM_OS_017() {
		util.enableButton(enableOrderSyncingfromAppToggleButton);
		util.enableButton(addTagsInOrderToggleButton);
		util.hold(1);
		util.enableButton(setCustomTagToggleButton);
		util.hold(1);
	}

	/**
	 * This block of code validates that user can add maximum two custom tags.
	 */
	public void TC_AM_OS_018_019(HashMap<String, String> input) {

		try {

			if (util.extractValueByAttributes(addCustomTagInputField, "disabled").equals("true")) {

				for (int i = 0; i < removeTagsButton.size(); i++) {

					if (removeTagsButton.size() == 2) {
						removeTagsButton.get(0).click();
						removeTagsButton.get(0).click();
					} else if (removeTagsButton.size() == 1) {
						removeTagsButton.get(0).click();
					}
				}

				util.hold(1);
				util.enterValue(addCustomTagInputField, input.get("tagName1"));
				util.pressEnter();
				util.hold(1);
				util.enterValue(addCustomTagInputField, input.get("tagName2"));
				util.pressEnter();
			}
		} catch (NullPointerException e) {
			System.out.println("null pointer exception handled in 018_019");
		}

//		util.hold(1);
//		util.enterValue(addCustomTagInputField, input.get("tagName1"));
//		util.pressEnter();
//		util.hold(1);
//		util.enterValue(addCustomTagInputField, input.get("tagName2"));
//		util.pressEnter();

		for (WebElement tag : setCustomTagList) {
			if (util.isElementDisplyed(tag)) {
				log.info(tag + " is visible");
				Assert.assertTrue(true);
			} else {
				log.error(tag + " is not visible");
				Assert.assertTrue(false);
			}
		}

		if (util.extractValueByAttributes(addCustomTagInputField, "disabled").equals("true")) {
			log.info(addCustomTagInputField + " is disabled");
			Assert.assertTrue(true);
		} else {
			log.error(addCustomTagInputField + " is disabled");
			Assert.assertTrue(false);
		}
	}

	/**
	 * This block of code validates that "Set Amazon Order ID as Shopify Order Name"
	 * toggle button is working or not.
	 */
	public void TC_AM_OS_020() {
		util.enableButton(setAmazonOrderIDAsShopifyOrderNameToggleButton);
		util.disableButton(setAmazonOrderIDAsShopifyOrderNameToggleButton);
	}

	/**
	 * This block of code verifies that when user clicks on "Set Amazon Order ID as
	 * Shopify Order Name", "Edit Shopify Order Name (Optional)" is displayed.
	 */
	public void TC_AM_OS_021() {
		util.enableButton(setAmazonOrderIDAsShopifyOrderNameToggleButton);

		if (util.isElementDisplyed(editShopifyOrderName) && (util.isElementDisplyed(orderPrefixInputField))
				&& (util.isElementDisplyed(orderSuffixInputField)
						&& (util.isElementDisplyed(receiveEmailAlertsForFailedOrdersToggleButton)))) {
			log.info(editShopifyOrderName + " is displayed");
			Assert.assertTrue(true);
		} else {
			log.error(editShopifyOrderName + " is not displayed");
			Assert.assertTrue(false);
		}
	}

	/**
	 * This method verifies that prefix and suffix input field is editable.
	 */
	public void TC_AM_OS_022(HashMap<String, String> input) {

		String prefixInitialValue = util.extractValueByAttributes(orderPrefixInputField, "value");

		util.enterValue(orderPrefixInputField, input.get("new_prefix_value"));

		if (!prefixInitialValue.equals(util.extractValueByAttributes(orderPrefixInputField, "value"))) {
			Assert.assertTrue(true);
			log.info("orderPrefixInputField is editable.");
		} else {
			Assert.assertTrue(false);
			log.error("orderPrefixInputField is not editable.");
		}

		String suffixInitialValue = util.extractValueByAttributes(orderSuffixInputField, "value");

		util.enterValue(orderSuffixInputField, input.get("new_suffix_value"));

		if (!suffixInitialValue.equals(util.extractValueByAttributes(orderSuffixInputField, "value"))) {
			Assert.assertTrue(true);
			log.info("orderSuffixInputField is editable.");
		} else {
			Assert.assertTrue(false);
			log.error("orderSuffixInputField is not editable.");
		}
	}

	/**
	 * This method validates that user can successfully save settings if both prefix
	 * and suffix input field are empty.
	 */
	public void TC_AM_OS_023() {
		util.enterValue(orderPrefixInputField, "");
		util.enterValue(orderSuffixInputField, "");
		clickOnSaveBtn();
		util.hold(1);
		new WebDriverWait(util.getDriver(), Duration.ofSeconds(30))
				.until(ExpectedConditions.elementToBeClickable(saveButton));

		if (util.isElementDisplyed(orderSettingsSavedSuccessfullyMsg)) {
			Assert.assertTrue(true);
			log.info("settings saved successfully");
		} else {
			log.error("settings not saved successfully");
			Assert.assertTrue(false);
		}
	}

	/**
	 * * This method validates that user can successfully save settings if both
	 * prefix and suffix input field are filled and these values and displayed in
	 * input fields.
	 */
	public void TC_AM_OS_024(HashMap<String, String> input) {
		util.enterValue(orderPrefixInputField, input.get("prefix_value"));
		util.enterValue(orderSuffixInputField, input.get("suffix_value"));
		clickOnSaveBtn();
		util.hold(1);
		new WebDriverWait(util.getDriver(), Duration.ofSeconds(30))
				.until(ExpectedConditions.elementToBeClickable(saveButton));

		if (util.isElementDisplyed(orderSettingsSavedSuccessfullyMsg)) {
			Assert.assertTrue(true);
			log.info("settings saved successfully");
		} else {
			log.error("settings not saved successfully");
			Assert.assertTrue(false);
		}

		if ((util.extractValueByAttributes(orderPrefixInputField, "value").equals(input.get("prefix_value")))
				&& (util.extractValueByAttributes(orderSuffixInputField, "value").equals(input.get("suffix_value")))) {
			log.info("prefix value is same as filled");
			Assert.assertTrue(true);
		} else {
			log.error("prefix value is not same as filled");
			Assert.assertTrue(false);
		}
	}

	/**
	 * This method validates that user can successfully save settings if both prefix
	 * and suffix input field are filled and these values and displayed in orders.
	 */
	public void TC_AM_OS_025(HashMap<String, String> input) {

//	Need to create order to validate it.

	}

	/**
	 * This method validates that "Receive Email alerts for Failed Orders" is
	 * working properly or not.
	 */
	public void TC_AM_OS_026() {

		util.enableButtonAndValidate(receiveEmailAlertsForFailedOrdersToggleButton);
		util.disableButtonAndValidate(receiveEmailAlertsForFailedOrdersToggleButton);
	}

	/**
	 * This method validates "Sync Shipment Status" toggle button is working
	 * properly.
	 */
	public void TC_AM_OS_029() {

		util.enableButtonAndValidate(syncShipmentStatusToggleButton);
		util.disableButtonAndValidate(syncShipmentStatusToggleButton);
	}

	/**
	 * This method verifies that when user clicks on "Sync Shipment Status" toggle
	 * button the "Tracking Details Required" and "Map carrier codes between Shopify
	 * and Amazon" is displayed.
	 */
	public void TC_AM_OS_030() {
		util.enableButton(syncShipmentStatusToggleButton);

		if ((util.isElementDisplyed(trackingDetailsRequiredToggleButton)
				&& (util.isElementDisplyed(mapCarrierCodesBetweenShopifyAndAmazonToggleButton)))) {
			log.info("Sync Shipment Status and Tracking Details Required is displayed.");
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
			log.error("Sync Shipment Status and Tracking Details Required is not displayed.");
		}
	}

	public void TC_AM_OS_031() {

	}

	/**
	 * 
	 */
	public void TC_AM_OS_033_34() {
		util.clickOnMultiAcccounSettings();
		new WebDriverWait(util.getDriver(), Duration.ofSeconds(30))
				.until(ExpectedConditions.visibilityOf(sellerNameSelector));
		util.enableButton(enableOrderSyncingfromAppToggleButton);
		log.info("Enabled order syncing from app toggle button");
		util.enableButton(syncShipmentStatusToggleButton);
		util.hold(1);
		util.enableButton(mapCarrierCodesBetweenShopifyAndAmazonToggleButton);
		util.hold(1);
		util.click(shopifyCarrierCodeSearchButton);
		util.hold(1);
		util.click(searchOrTypeShopifyCarrierCodeInputField);
		util.hold(1);
		util.click(addCustomOption);
		util.hold(1);
		util.click(shopifyCarrierCodeSearchButton);
		util.hold(1);
		util.click(searchOrTypeShopifyCarrierCodeInputField);
		util.hold(1);
		util.click(px4Option);
		util.hold(1);

		util.click(amazonCarrierCodeSearchButton);
		util.hold(1);
		util.click(searchOrTypeAmazonCarrierCodeInputField);
		util.hold(1);
		util.click(addCustomOption);
		util.hold(1);
		util.click(amazonCarrierCodeSearchButton);
		util.hold(1);
		util.click(searchOrTypeAmazonCarrierCodeInputField);
		util.hold(1);
		util.click(A1Option);
		util.hold(1);
//		if (!util.extractValueByAttributes(searchOrTypeShopifyCarrierCodeInputField, "value").equals("")) {
//			util.click(AGSOption);
//			log.info("clicked on AGS .");
//
//			if (util.extractValueByAttributes(shopifyCarrierCodeInputField, "value").equals("AGS")) {
//				Assert.assertTrue(true);
//				log.info("shopify search bar is working properly.");
//			} else {
//				Assert.assertTrue(false);
//				log.error("shopify search bar is not working properly.");
//			}
//		}
//		util.jsClick(px4Option);
//		util.click(amazonCarrierCodeSearchButton);
//
//		if (!util.extractValueByAttributes(searchOrTypeAmazonCarrierCodeInputField, "value").equals("")) {
//			util.click(A1Option);
//
//			if (util.extractValueByAttributes(amzCarrierCodeInputField, "value").equals("A-1")) {
//				Assert.assertTrue(true);
//				log.info("amazon search bar is working properly.");
//			} else {
//				Assert.assertTrue(false);
//				log.error("amazon search bar is not working properly.");
//			}
//		}
//		util.jsClick(px4Option);
	}

	/**
	 * This method validates that "Add New Carrier Code Button" is working properly.
	 */
	public void TC_AM_OS_035() {
		util.enableButton(syncShipmentStatusToggleButton);
		util.click(addNewCarrierCodeButton);
		if (amazonCarrierCodeInputField.size() > 1) {
			log.info("Add New Carrier Code Button is working");
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
			log.error("Add New Carrier Code Button is not working");
		}
	}

	/**
	 * This method validates that "Delete New Carrier Code Button" is working
	 * properly.
	 */
	public void TC_AM_OS_036() {
		util.enableButton(syncShipmentStatusToggleButton);
		util.click(addNewCarrierCodeButton);

		if (amazonCarrierCodeInputField.size() > 1) {
			int initialSize = amazonCarrierCodeInputField.size();
			util.click(deleteCarrierCodeButton.get(initialSize - 1));

			if (initialSize != amazonCarrierCodeInputField.size()) {
				log.info("delete New Carrier Code Button is working");
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
				log.error("delete New Carrier Code Button is not working");
			}
		}
	}

	/**
	 * When user adds a new carrier but lefts it blank and clicks on save button
	 * toast error msg is displayed.
	 */
	public void TC_AM_OS_037() {
		util.enableButton(syncShipmentStatusToggleButton);
		util.click(addNewCarrierCodeButton);
		clickOnSaveBtn();
		util.hold(1);

		if (util.isElementDisplyed(errorMsgWhenShopifyOrderSourceIdentifierInputFieldIsBlank)) {
			Assert.assertTrue(true);
			log.info("Toast error msg is displayed");
		} else {
			log.error("Toast error msg is not displayed");
			Assert.assertTrue(false);
		}
	}

	/**
	 * This method validates that "Sync Order Cancelation status", "Sync Order
	 * Refund", "Map possible refund reasons between Shopify and Amazon" toggle
	 * button , "Add New Refund Reason", "delete New Refund Reason" is working or
	 * not.
	 * 
	 */
	public void TC_AM_OS_052() {
		util.clickOnMultiAcccounSettings();
		new WebDriverWait(util.getDriver(), Duration.ofSeconds(30))
				.until(ExpectedConditions.visibilityOf(sellerNameSelector));
		util.enableButton(enableOrderSyncingfromAppToggleButton);
		util.enableButtonAndValidate(syncOrderCancellationStatusToggleButton);
		util.disableButtonAndValidate(syncOrderCancellationStatusToggleButton);
		util.enableButton(syncOrderCancellationStatusToggleButton);
		util.enableButtonAndValidate(syncOrderRefundToggleButton);
		util.enableButtonAndValidate(mapPossibleRefundReasonsBetweenShopifyAndAmazonToggleButton);
		util.disableButtonAndValidate(mapPossibleRefundReasonsBetweenShopifyAndAmazonToggleButton);
		util.enableButton(mapPossibleRefundReasonsBetweenShopifyAndAmazonToggleButton);

		util.enableButton(syncOrderRefundToggleButton);

		if (amazonRefundReasonInputField.size() > 1) {
			int initialSize = amazonRefundReasonInputField.size();
			util.click(deleteNewRefundReasonButton.get(initialSize - 1));

			if (initialSize != amazonRefundReasonInputField.size()) {
				log.info("delete New Refund Reason Button is working");
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
				log.error("delete New Refund Reason Button is not working");
			}
		}
	}

	/**
	 * Verify that when Sync Order Refund is enable than other settings should open
	 * that is shopify refund reason and amazon refund reason
	 * TC_AM_OS_054,TC_AM_OS_055,TC_AM_OS_056
	 */
	public void TC_AM_OS_054_55_56() {
		util.clickOnMultiAcccounSettings();
		new WebDriverWait(util.getDriver(), Duration.ofSeconds(30))
				.until(ExpectedConditions.visibilityOf(sellerNameSelector));
		util.enableButton(enableOrderSyncingfromAppToggleButton);
		util.enableButton(syncOrderRefundToggleButton);
		util.isElementDisplyedAndValidate(mapPossibleRefundReasonsBetweenShopifyAndAmazonToggleButton);
		util.enableButton(mapPossibleRefundReasonsBetweenShopifyAndAmazonToggleButton);
		util.isElementDisplyedAndValidate(shopifyRefundReasonsTitle);
		util.isElementDisplyedAndValidate(amazonRefundReasonsTitle);
	}

	/**
	 * This method validates that when refund order field are blank and when save
	 * the changes the error msg is shown.
	 */
	public void TC_AM_OS_057() {
		util.goToMultiAcccounSettings();
		util.enableButton(enableOrderSyncingfromAppToggleButton);
		util.enableButton(syncOrderRefundToggleButton);
		util.click(shopifyRefundReasonsSearchIcon);
		util.hold(1);
		util.click(searchShopifyRefundReasonInputField);
		util.hold(1);
		util.clear(searchShopifyRefundReasonInputField);
		util.hold(2);
		util.click(amazonRefundReasonsSearchIcon);
		util.hold(1);
		util.click(searchOrTypeAmazonRefundReasonInputField);
		util.hold(1);
		util.clear(searchOrTypeAmazonRefundReasonInputField);
		clickOnSaveBtn();
		util.hold(2);
		util.isElementDisplyedAndValidate(errorMsgWhenShopifyOrderSourceIdentifierInputFieldIsBlank);

	}

	public void validateOrderHasPrefixAndSuffix(HashMap<String, String> input) {

		if (util.getTagValue(titleOfOrder).startsWith(input.get("prefix_value"))
				&& (util.getTagValue(titleOfOrder).endsWith(input.get("suffix_value")))) {
			log.info("Order title contains prefix and suffix value.");
			Assert.assertTrue(true);
		} else {
			log.error("Order title contains prefix and suffix value.");
			Assert.assertTrue(false);
		}
	}

	/**
	 * This method enables "Sync Order Refund" toggle button
	 */

	public void enableSyncOrderRefundToggleButton() {
		util.goToMultiAcccounSettings();
		log.info("go to MultiAccount Settings");
		util.enableButton(enableOrderSyncingfromAppToggleButton);
		log.info("enable 'Order Syncing from App' Toggle Button");
		util.enableButton(createOrdersForNonExistingProductsTogglebutton);
		log.info("enable 'create Orders For Non Existing Products' Toggle Button");
		util.enableButton(syncOrderRefundToggleButton);
		log.info("enable 'sync Order Refund' Toggle Button");
		util.enableButton(mapPossibleRefundReasonsBetweenShopifyAndAmazonToggleButton);
		log.info("enable 'map Possible Refund Reasons Between Shopify And Amazon' Toggle Button");
		clickOnSaveBtn();
		log.info("clicks on save button.");
		util.hold(6);
	}

	/**
	 * This method disables "Sync Order Refund" toggle button
	 */
	public void disableSyncOrderRefundToggleButton() {
		util.goToMultiAcccounSettings();
		log.info("go to MultiAccount Settings");
		util.enableButton(enableOrderSyncingfromAppToggleButton);
		log.info("enable 'Order Syncing from App' Toggle Button");
		util.enableButton(createOrdersForNonExistingProductsTogglebutton);
		log.info("enable 'create Orders For Non Existing Products' Toggle Button");
		util.disableButton(syncOrderRefundToggleButton);
		log.info("disable 'sync Order Refund' Toggle Button");
		clickOnSaveBtn();
		log.info("clicks on save button.");
		util.hold(6);
	}

	/**
	 * This method enables "Sync Order Refund" toggle button and add shopify refund
	 * and Amazon refund reason.TC_AM_OS_054,TC_AM_OS_055,TC_AM_OS_056
	 */
	public void enableSyncOrderRefundToggleButtonAndEnterRefundReason() {
		enableSyncOrderRefundToggleButton();
		util.click(shopifyRefundReasonSearchButton);
		log.info("clicks on Shopify Refund Reason button.");
		util.hold(2);
		util.click(searchShopifyRefundReasonInputField);
		log.info("clicks on search Or Type Shopify Refund Reason Input Field button.");
		util.hold(2);

		if (util.extractValueByAttributes(searchShopifyRefundReasonInputField, "value").equals("Customer Return")) {
			util.click(sellerRefundOption);
			log.info("clicks on seller refund shopify option.");
		} else {
			util.click(customerReturnOption);
			log.info("clicks on Customer Return Shopify option.");
		}

		util.hold(2);
		util.click(amazonRefundReasonSearchButton);
		log.info("clicks on Amazon Refund Reason button.");
		util.hold(2);
		util.click(searchOrTypeAmazonRefundReasonInputField);
		log.info("clicks on search Or Type Amazon Refund Reason Input Field button.");

		util.hold(2);

		if (util.extractValueByAttributes(searchOrTypeAmazonRefundReasonInputField, "value").equals("CustomerReturn")) {
			util.click(productOutOfStockOptionAmazon);
			log.info("clicks on Product Out Of Stock Option .");
		} else {
			util.click(customerReturnAmazon);
			log.info("clicks on Customer Return Amazon option.");
		}

		clickOnSaveBtn();
		log.info("clicks on save button.");
	}

	/**
	 * This method clicks on refund button on the order page of a particular product
	 * in shopify orders,
	 */
	public void openRefund() {
//		util.waitUntilElementIsVisible(refundButtonShopify);
		util.hold(10);
		util.click(refundButtonShopify);
		log.info("clicks on Refund button.");
	}

	/**
	 * This method selects number of items to return and without adding any refund
	 * reason, refunds order.
	 * 
	 * @param input
	 */
	public void refundWithoutAnyRefundReason(HashMap<String, String> input) {
		WebElement failedRefundOrder;
		util.enterValue(refundOrderQty, input.get("num_of_items_to_return"));
		log.info("enters " + input.get("num_of_items_to_return") + "in return quantity input field. ");
		util.clear(reasonForRefundInputField);
		util.hold(3);
		util.pressEnter();
		log.info("press enter to refund order.");
		util.waitUntilElementIsVisible(refundPageHeading);
		String amazonOrderIdString = amazonOrderId.getText();
//		opens mutliaccount overview page.
		util.goToMultiAccountOverView();
		log.info("opens mutliaccount overview page.");
		util.click(manageFailedRefundOrders);
		log.info("clicks on Manage Failed Refund Orders");
		util.enterValue(searchWithAmazonOrderIdInputField, amazonOrderIdString);
		log.info("enters " + amazonOrderIdString + "in search input field. ");
		util.click(searchButton);
		log.info("clicks on search button.");

		try {
			failedRefundOrder = util.getDriver().findElement(By.xpath("//td[text() = '" + amazonOrderIdString + "']"));
		} catch (NoSuchElementException e) {
			if (util.isElementDisplyed(noFailedRefundOrdersFound)) {
				log.info("failed refund order is not displayed.");
				Assert.assertTrue(true);
			} else {
				log.error("failed refund order is  displayed.");
				Assert.assertTrue(false);
			}
		}
	}

	/**
	 * This method selects number of items to return and adds refund reason, refunds
	 * order.
	 * 
	 * @param input
	 */
	public void refundWithRefundReason(HashMap<String, String> input) {
		util.enterValue(refundOrderQty, input.get("num_of_items_to_return"));
		log.info("enters " + input.get("num_of_items_to_return") + "in return quantity input field. ");
		util.enterValue(reasonForRefundInputField, input.get("invalid_cancelation_reason"));
		log.info("enters " + input.get("invalid_cancelation_reason") + "in refund reason input field. ");
		util.hold(3);
		util.pressEnter();
		log.info("press enter to refund order.");
		util.waitUntilElementIsVisible(refundPageHeading);
		amazonOrderIdString = amazonOrderId.getText();
//		opens mutliaccount overview page.
		util.goToMultiAccountOverView();
		log.info("opens mutliaccount overview page.");
		util.click(manageFailedRefundOrders);
		log.info("clicks on Manage Failed Refund Orders");
		util.enterValue(searchWithAmazonOrderIdInputField, amazonOrderIdString);
		log.info("enters " + amazonOrderIdString + "in search input field. ");
		util.click(searchButton);
		log.info("clicks on search button.");
	}

	/**
	 * This method validates that refund order is available in Manage Failed Orders.
	 */
	public void verifyRefundOrderIsInManageFailedOrders() {
		WebElement failedRefundOrder = null;
		try {
			failedRefundOrder = util.getDriver().findElement(By.xpath("//td[text() = '" + amazonOrderIdString + "']"));
		} catch (NoSuchElementException e) {
			try {
				if (util.isElementDisplyed(failedRefundOrder) && util.isElementDisplyed(refundReasonCanNotBeEmpty)) {
					log.info("failed refund order is displayed.");
					Assert.assertTrue(true);
				} else {
					log.error("failed refund order is not displayed.");
					Assert.assertTrue(false);
				}
			} catch (NoSuchElementException ne) {
				log.error("failed refund order is not displayed.");
				Assert.assertTrue(false);
			}
		}
	}

	/**
	 * This method validates that refund order is not available in Manage Failed
	 * Orders.
	 */
	public void verifyRefundOrderIsNotInManageFailedOrders() {
		WebElement failedRefundOrder;
		try {
			failedRefundOrder = util.getDriver().findElement(By.xpath("//td[text() = '" + amazonOrderIdString + "']"));
		} catch (NoSuchElementException e) {
			if (util.isElementDisplyed(noFailedRefundOrdersFound)) {
				log.info("failed refund order is not displayed.");
				Assert.assertTrue(true);
			} else {
				log.error("failed refund order is displayed.");
				Assert.assertTrue(false);
			}
		}
	}

	/**
	 * This method verifies that when all mandatory fields are filled, and save
	 * button is clicked the toast message "Order settings saved successfully." is
	 * displayed.
	 */
	public void TC_AM_OS_068(HashMap<String, String> input) {
		util.goToMultiAcccounSettings();
		util.enableButton(enableOrderSyncingfromAppToggleButton);

		if (util.extractValueByAttributes(shopifyOrderSourceIdentifierInputField, "value").equals("")) {
			util.enterValue(shopifyOrderSourceIdentifierInputField, input.get("amazon"));
		}

		clickOnSaveBtn();
		new WebDriverWait(util.getDriver(), Duration.ofSeconds(30))
				.until(ExpectedConditions.elementToBeClickable(saveButton));

		if (util.isElementDisplyed(orderSettingsSavedSuccessfullyMsg)) {
			log.info("settings are saved successfully.");
			Assert.assertTrue(true);
		} else {
			log.error("settings are not saved successfully.");
			Assert.assertTrue(false);
		}

	}

	/**
	 * This method verifies that when all mandatory fields are not filled, and save
	 * button is clicked the toast message "Some information is missing or wrong,
	 * kindly check them before proceeding." is displayed.
	 */
	public void TC_AM_OS_069(HashMap<String, String> input) {
		util.goToMultiAcccounSettings();
		util.enableButton(enableOrderSyncingfromAppToggleButton);

		if (!util.extractValueByAttributes(shopifyOrderSourceIdentifierInputField, "value").equals("")) {
			util.clear(shopifyOrderSourceIdentifierInputField);
		}

		clickOnSaveBtn();
		util.hold(2);

		if (util.isElementDisplyed(errorMsgWhenShopifyOrderSourceIdentifierInputFieldIsBlank)) {
			log.info("error msg is displayed.");
			Assert.assertTrue(true);
		} else {
			log.error("error msg is not displayed.");
			Assert.assertTrue(false);
		}
	}

	/**
	 * validate Sync order cancellation status toggle btn is working or not.
	 */
	public void syncOrderCancellationStatusBtnFunctionality() {
		util.goToMultiAcccounSettings();
		util.enableButton(enableOrderSyncingfromAppToggleButton);
		util.enableButton(createOrdersForNonExistingProductsTogglebutton);
		util.validateWorking(syncOrderCancellationStatusToggleButton);
		util.validateWorking(syncOrderCancellationStatusToggleButton);
		util.enableButton(syncOrderCancellationStatusToggleButton);
		clickOnSaveBtn();
	}

	/**
	 * validate sync order cancellation functionality.
	 */
	public void validateOrderIsFullyCanceled() {
		try {
			util.isElementDisplyedAndValidate(canceledOrder);
		} catch (NoSuchElementException e) {
			Assert.assertTrue(false);
		}

	}

}
