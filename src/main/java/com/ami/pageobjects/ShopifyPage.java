package com.ami.pageobjects;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ami.resources.Utilities;

public class ShopifyPage extends ShopifyPageOR {

	private static Logger log = LogManager.getLogger(ShopifyPage.class.getName());
	Utilities util;
	public static String pageUrl = "";
	public static String id = "";
	public static String productUrl = "";
	public static String productName = "";

	/**
	 * 
	 * @param util
	 */
	public ShopifyPage(Utilities util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
	}

	/**
	 * This block of code enters values in required field (email and login)
	 * 
	 * @param input
	 */
	public void shopifyLogin() {
		util.hold(5);
		util.enterValue(emailInputField, util.getConfigProperty("user_email"));
		log.info("user-email entered successfully.");
		util.click(loginButton);
		log.info("clicked on login button successfully.");
		util.hold(5);
		util.enterValue(passwordInputField, util.getConfigProperty("user_password"));
		log.info("user-password entered successfully.");
		util.click(loginButton);
		log.info("clicked on login button successfully.");

		util.hold(5);
		util.refreshPage();

		new WebDriverWait(util.getDriver(), Duration.ofSeconds(30))
				.until(ExpectedConditions.visibilityOf(salesChannelButton));

		if (util.isElementVisible(topBar)) {
			log.info("user is successfully logged into the shopify.");
			Assert.assertTrue(true);
		} else {
			log.error("user is not logged into the shopify.");
			Assert.assertTrue(true);
		}
	}

	public void shopifyPartnersLogin() {
		util.hold(5);

		if (!userCard.isEmpty()) {
			for (WebElement email : userCardEmail) {
				if (email.getText().equals(util.getConfigProperty("user_email"))) {
					util.click(email);
				}

			}

		}
//		util.enterValue(emailInputField, util.getProperty("user_email"));
//		log.info("user-email entered successfully.");
//		util.click(loginButton);
//		log.info("clicked on login button successfully.");
//		util.hold(5);
//		util.enterValue(passwordInputField, util.getProperty("user_password"));
//		log.info("user-password entered successfully.");
//		util.click(loginButton);
//		log.info("clicked on login button successfully.");

		util.hold(5);
		util.refreshPage();
	}

	public void select(Map<String, String> input) {
		util.click(salesChannelButton);
		log.info("successfully clicked on sales channel button");

		for (WebElement salesChannel : installedChannelList) {
			util.hold(3);
			if (salesChannel.getText().equals(input.get("sales_channel"))) {
				util.hold(1);
				util.actionClick(salesChannel);
				log.info("clicked on sales " + input.get("sales_channel") + " channel");
				break;
			}
		}
		util.hold(3);
	}

	/**
	 * This block of code selects a store from various store list. The store name is
	 * being fetched from "/src/main/java/com/Onyx/Resources/ShopifyLoginData.json"
	 * 
	 * @param input
	 */
//	public void selectStore(HashMap<String, String> input) {
//		
////		User clicks on Sales channel button
//		util.click(salesChannelButton);
//		log.info("successfully clicked on sales channel button");
//	
//
////		***********	For Future Purpose	**************
////		This block of code selects sales channel mentioned in Json file.
//		for (WebElement salesChannel : installedChannelList) {
//			util.hold(3);
//			if (salesChannel.getText().equals(input.get("sales_channel"))) {
//				util.hold(1);
//				util.actionClick(salesChannel);
//				log.info("clicked on sales " + input.get("sales_channel") + " channel");
//				break;
//			}
//		}
//		util.hold(3);
//		util.clickOnOverview();
//		
//		util.waitUntilElementIsVisible(sellerNameHeading);
//		pageUrl = StringUtils.substringBeforeLast(util.getPageURL(), "/");
//		
//		}

	@FindBy(xpath = "//span[text() = 'Overview']")
	WebElement overview;

	public void selectStore(String store) {
		util.waitUntilElementIsVisible(salesChannelButton, 30);
//		User clicks on Sales channel button
		util.click(salesChannelButton);
		log.info("successfully clicked on sales channel button");

		if (store.equalsIgnoreCase("staging")) {
			util.click(stagingStore);

		} else {
			util.click(liveStore);

		}

		if (util.getConfigProperty("onboarding").equals("false")) {
			util.waitUntilElementIsVisible(overview);
			util.clickOnOverview();
			util.clickOnOverview();
		}

		util.waitUntilElementIsVisible(overviewHeading);
		pageUrl = StringUtils.substringBeforeLast(util.getPageURL(), "/");
	}

	public void goToCreateNewProductPage(HashMap<String, String> input) {
		util.getDriver().get(input.get("store_url") + "/products/new");
		util.click(user);
		util.waitUntilElementIsVisible(titleInputField);
	}

	public void createNewProduct(HashMap<String, String> input) {
		productName = input.get("title");
		util.enterValue(titleInputField, productName);
		util.switchToIFrame(iframe);
		util.enterValue(productDescriptionBox, input.get("description"));
		util.switchToDefaultContent();
		util.enterValue(priceInputField, input.get("price"));
		util.enterValue(compareAtPriceInputField, input.get("compare_price"));
		util.enterValue(skuInpField, input.get("sku"));
		util.enterValue(barcodeInputField, input.get("barcode"));
		util.enterValue(locationsInpFieldList.get(0), input.get("inventory"));
		util.enterValue(weightInpField, input.get("weight"));
		util.selectByValue(selectWtUnit, "g");
		util.click(saveBtn);
		util.hold(2);
		util.waitUntilElementIsVisible(productCreatedMsg);
		productUrl = util.getPageURL();
	}

	public void deleteShopifyProduct() {
		util.getDriver().get(productUrl);
		util.waitUntilElementIsVisible(titleInputField);
		util.click(deleteProductBtn);
	}

}
