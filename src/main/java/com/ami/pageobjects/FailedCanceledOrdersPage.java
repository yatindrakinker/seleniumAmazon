package com.ami.pageobjects;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ami.resources.Utilities;

public class FailedCanceledOrdersPage extends OrderPageOR {
	Utilities util;

	public FailedCanceledOrdersPage(Utilities util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
	}

	
	public void validateFailedCanceledOrderFieldsAreVisible() {
		boolean isTrue = false;

		util.click(manageFailedCanceledOrdersOverview);
		util.isElementDisplyedAndValidate(failedCanceledOrdersHeading);
		util.goToMultiAccountOverView();
		util.click(failedCanceledOrdersCountOverview);
		util.isElementDisplyedAndValidate(failedCanceledOrdersHeading);

		util.enterValue(searchFailedCanceledOrdersInputField, "xyz");
		util.hold(3);
		util.listIsNotEmpty(noFoundImgList);
		util.click(clearSearchInputFieldBtn);

		if (util.extractValueByAttributes(searchFailedCanceledOrdersInputField, "value").equals("")) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);

	}
	
	public void searchCancelledFailedOrder(String shopifyOrderId) {
		util.goToMultiAccountOverView();
		util.click(manageFailedCanceledOrdersOverview);
		util.isElementDisplyedAndValidate(failedCanceledOrdersHeading);
		util.enterValue(searchFailedCanceledOrdersInputField, shopifyOrderId);
		util.hold(5);
		util.click(cancelOrderBtnsFailedCanceledOrders.get(0));
		util.waitUntilElementIsVisible(confirmBtnModal, 30);
		util.selectByValue(selectBtnFailedCanceledOrdersModal, "customer");
		util.click(confirmBtnModal);
		util.waitUntilElementIsVisible(confirmBtnModal, 30);
		util.click(confirmBtnModal);
		util.waitUntilElementIsVisible(cancellationDoneToastMsg, 60);
		
	}
	
	public void verifyOrderIsRemovedFromGrid(String shopifyOrderId) {
		util.goToMultiAccountOverView();
		util.click(manageFailedCanceledOrdersOverview);
		util.isElementDisplyedAndValidate(failedCanceledOrdersHeading);
		util.enterValue(searchFailedCanceledOrdersInputField, shopifyOrderId);
		util.hold(5);
		util.listIsNotEmpty(noFoundImgList);
	}
}
