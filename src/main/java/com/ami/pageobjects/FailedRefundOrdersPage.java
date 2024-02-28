package com.ami.pageobjects;

import java.time.Duration;

/**
 * Project Name: Amazon MultiAccount 
 * Author: Yatindra Kinker 
 * Version: Reviewed By: Abhay Hayaran 
 * Date of Creation: January 12, 2022
 *  Modification History:
 * Date of change: 
 * Version: changed function: 
 * change description:
 *  Modified By:
 */

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ami.pageobjects.reusableclasses.ReusableMethods;
import com.ami.resources.Utilities;

public class FailedRefundOrdersPage extends OrderPageOR {

	private static Logger log = LogManager.getLogger(FailedRefundOrdersPage.class.getName());

	Utilities util;

	public FailedRefundOrdersPage(Utilities util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
	}

	/**
	 * This method validates fields are visible. TC_AM_OS_001,
	 * TC_AM_OS_002,TC_AM_OS_003,TC_AM_OS_004,TC_AM_OS_005
	 */
	public void validateFieldsAreVisible() {
		util.isElementDisplyedAndValidate(manageFailedOrdersHeadingOverview);
		util.isElementDisplyedAndValidate(failedOrdersCountOverview);
		util.isElementDisplyedAndValidate(manageFailedOrders);
		util.click(manageFailedOrders);
		util.isElementDisplyedAndValidate(failedOrdersPageHeading);
		util.click(backButton);
		util.isElementDisplyedAndValidate(failedRefundOrdersCountOverview);
	}

	/**
	 * This method enables toggle button to recieve orders that does not exist in
	 * shopify.
	 */
	public void disableOrderForNonExistingProduct() {
		util.goToSection("settings");
		util.enableButton(enableOrderSyncingfromAppToggleButton);
		util.disableButton(createOrdersForNonExistingProductsTogglebutton);
		util.disableButton(receiveEmailAlertsForFailedOrdersToggleButton);
		util.click(saveButton);
	}

	public void goToFailedOrders() {
		util.goToSection("overview");
		util.refreshPage();
		util.switchToIFrame();
		util.waitUntilElementIsVisible(manageFailedOrders, 30);
		util.click(manageFailedOrders);
		util.hold(3);
	}

	/**
	 * This method search orders for both valid and invalid order id.
	 * TC_AM_OS_006,TC_AM_OS_007
	 * 
	 * @param amazonOrderId
	 */
	public void searchOrder(String amazonOrderId) {
		boolean isTrue = false;
		util.waitUntilElementIsVisible(searchWithAmazonOrderIdInputField, 20);
		util.click(searchWithAmazonOrderIdInputField);
		util.enterValue(searchWithAmazonOrderIdInputField, amazonOrderId);
		util.hold(5);

		if (util.getTagValue(failedOrderAmzId.get(0)).equalsIgnoreCase(amazonOrderId)) {
			isTrue = true;
		}

		util.enterValue(searchWithAmazonOrderIdInputField, "xyz");
		util.hold(5);
		util.isElementDisplyedAndValidate(noOrdersFoundMsg);
		Assert.assertTrue(isTrue);
	}

	/**
	 * This method enables sync orders button and verifies that if refund is
	 * initiated from shopify without any reasons it go to the failed refund order
	 * section. TC_AM_OS_009 to TC_AM_OS_016
	 */
	public void failedRefundOrder() {
		OrderPage op = new OrderPage(util);
		ReusableMethods reuse = new ReusableMethods(util);
		util.goToSection("settings");
		op.enableCreateOrderForNonExistingProductsTogglebutton();
		util.hold(2);
		util.click(cancelRefundTabBtn);

		if (syncRefundedOrdersOnShopifyToggleBtn.getAttribute(ARIACHECKED).equals(TRUEVAL)) {
			util.click(syncRefundedOrdersOnShopifyToggleBtn);
			util.hold(2);
			reuse.clickOnSave();
		}

		util.enableButton(syncRefundedOrdersOnShopifyToggleBtn);
		util.enableButton(mapRefundReasonsToggleBtn);
		util.click(shopifySellerRefundSearchBtn);
		util.click(shopifySellerRefundInputField);
		util.hold(1);
		util.click(customerReturnOption);
		util.hold(2);

		util.click(amazonCustomerCancelSearchBtn);
		util.click(amazonCustomerCancelInputField);
		util.hold(1);
		util.click(customerCancelOption);
		util.hold(2);
		reuse.clickOnSave();
	}

	private void gotoRefundOrdersAndSearch(String amazonOrderId) {
		util.goToSection("overview");
		util.refreshPage();
		util.switchToIFrame();
		util.scrollToBottom();
		util.waitUntilElementIsVisible(manageFailedRefundOrders, 30);
		util.click(manageFailedRefundOrders);
		util.waitUntilElementIsVisible(searchWithAmazonOrderIdInputField, 30);
		util.enterValue(searchWithAmazonOrderIdInputField, amazonOrderId);
		util.hold(5);

	}

	/**
	 * 
	 * @param qty
	 * @param amazonOrderId
	 */
	public void refundWithoutReason(String qty, String amazonOrderId) {
		boolean isTrue = false;
		util.waitUntilElementIsVisible(refundButtonShopify, 30);
		util.click(refundButtonShopify);
		util.enterValue(refundOrderQty, qty);
		util.clear(reasonForRefundInputField);
		util.hold(3);
		util.click(refundBtn);
		util.waitUntilElementIsVisible(refundedLabel, 20);
		util.click(refundButtonShopify);

		util.waitUntilElementIsVisible(refundPageHeading, 20);
		util.enterValue(refundAmtInputField, "7");
		util.click(refundAmtAmazonInputField);
		util.waitUntilElementIsClickable(refundBtn, 15);
		util.click(refundBtn);
		util.hold(30);

		gotoRefundOrdersAndSearch(amazonOrderId);

		while (!noFoundImgList.isEmpty()) {
			util.enterValue(searchWithAmazonOrderIdInputField, amazonOrderId);
			util.hold(5);
		}

		if (!noFoundImgList.isEmpty()) {
			Assert.assertTrue(false);
		} else if (util.getTagValue(failedRefundOrderAmzId.get(0)).equals(amazonOrderId)) {
			isTrue = true;
			util.click(createRefundBtnAmz);
			util.waitUntilElementIsVisible(createRefundModalAmz, 120);
			util.actionClick(selectProductToRefundCheckboxModal);
			util.hold(1);
			util.selectByVisibleText(selectRefundReasonAmz, "Customer Return");
			util.hold(1);

			double amtToRefund = Double.parseDouble(totalItemPrice.getText());
			amtToRefund -= 17;

			if (amtToRefund <= 0) {
				amtToRefund += 20.0;
			}

			util.enterValue(enterRefundAmtRefundModalAmz, amtToRefund + "");
			util.hold(1);
			util.click(confirmBtnModal);
			util.hold(1);
			util.click(confirmYesBtnRefundModal);
			new WebDriverWait(util.getDriver(), Duration.ofSeconds(30))
					.until(ExpectedConditions.visibilityOf(successfullyRefundedToastMsg));
		}

		Assert.assertTrue(isTrue);
	}

	public void verifyOrderIsRefunded(String amazonOrderId) {
		gotoRefundOrdersAndSearch(amazonOrderId);
		util.listIsNotEmpty(noFoundImgList);
	}
}
