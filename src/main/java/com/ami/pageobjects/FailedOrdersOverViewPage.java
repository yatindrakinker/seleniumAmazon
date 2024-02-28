package com.ami.pageobjects;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ami.resources.Utilities;

public class FailedOrdersOverViewPage extends OverViewPageOR {

	Utilities util;
	OrderPage order;

	public FailedOrdersOverViewPage(Utilities util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
		order = new OrderPage(util);
	}

	public void failedOrderSectionIsAvaialbleOnOverview() {
		util.isElementDisplyedAndValidate(failedOrdersSectionOverview);
		util.isElementDisplyedAndValidate(failedOrdersCountOverview);
	}

	public void cursorPropertyChanges() {
		util.validateCursorProperty(failedOrdersCountOverview, "pointer");
	}

	public void inFailedOrderSectionOnOverviewManageFailedOrdersLinkIsAvailable() {
		util.isElementDisplyedAndValidate(manageFailedOrdersButton);
		util.click(manageFailedOrdersButton);
		util.waitUntilElementIsVisible(failedOrdersPageHeading, 30);
		util.click(backBtn);
		util.waitUntilElementIsVisible(manageFailedOrdersButton, 30);
	}

	public void validateFailedOrderCounts() {
		String failedCountOverview = failedOrdersCountOverview.getText();
		util.click(failedOrdersCountOverview);
		util.waitUntilElementIsVisible(failedOrdersPageHeading, 30);
		int countFailedOrders = 0;

		if (noFoundImgList.isEmpty()) {
			do {
				countFailedOrders += amazonOrderIDFailedOrdersPage.size();
				util.click(nextPaginationBtn);
				util.hold(5);
			} while (nextPaginationBtn.getAttribute(ARIADISABLED) == null);

//			countFailedOrders += amazonOrderIDFailedOrdersPage.size();
			System.err.println("countFailedOrders \t" + countFailedOrders);
		}

		Assert.assertEquals(failedCountOverview, countFailedOrders + "");

	}

	public void searchInputFieldIsWorking() {
		util.enterValue(searchInputFailedOrdersPage, "1-1-1-1-1-1-1-1-1-1-1-1-1-1");
		util.hold(5);
		util.validateListIsNotEmpty(noFoundImgList);
		util.click(searchInputFailedOrdersPage);
		util.click(clearSearchInputFieldBtn);
	}

	public void failedOrdersAreVisible() {
		if (!noFoundImgList.isEmpty()) {
			Assert.assertTrue(true);
		} else {
			util.validateListIsNotEmpty(failedOrderAmzId);
		}
	}

	public void achievedOrderBtnIsWorking() {
		util.click(archivedOrdersFailedOrdersPage);
		util.waitUntilElementIsVisible(archivedOrdersHeading, 30);
		util.isElementDisplyedAndValidate(orderIdArchivedOrdersColHeading);
		util.isElementDisplyedAndValidate(dateHeadingArchivedCol);
		util.isElementDisplyedAndValidate(actionsHeadingArchivedOrders);
	}

	public void viewBtnIsWorkingOnArchievedOrder() {
		LocalSellingPage lsp = new LocalSellingPage(util);
		lsp.paginationBtnsAreWorking();

		if (noFoundImgList.isEmpty()) {
			util.click(viewButtonArchivedOrder.get(0));
			util.isElementDisplyedAndValidate(viewYourOrderPageHeading);
			util.waitUntilElementIsVisible(customerName, 45);
			util.isElementDisplyedAndValidate(customerName);
			util.isElementDisplyedAndValidate(customerAddress);
			util.isElementDisplyedAndValidate(earliestDeliveryDate);
			util.isElementDisplyedAndValidate(latestShipDate);
			util.isElementDisplyedAndValidate(earliestShipDate);
			util.isElementDisplyedAndValidate(latestDeliveryDate);
		} else {
			Assert.assertTrue(true);
		}

	}

	public void gotoFailedOrdersPage() {
		util.goToMultiAccountOverView();
		util.isElementDisplyedAndValidate(manageFailedOrdersButton);
		util.click(manageFailedOrdersButton);
		util.isElementDisplyedAndValidate(failedOrdersPageHeading);
	}

	public void importOrdersBtnIsAvailable() {
		gotoFailedOrdersPage();
		util.click(importOrdersFailedOrdersPage);
	}

	public void verifyWhenOrderIdIsNotGiven() {
		util.enterValue(importOrderInputField, "");
		util.click(confirmBtnModal);
		util.waitUntilElementIsVisible(fillAmazonOrderIdToProceedImportingToastMsg, 30);
	}

	public void verifyImportOrderModalIsWorking() {
		util.enterValue(importOrderInputField, "01-001-0019");
		util.click(confirmBtnModal);
		util.waitUntilElementIsVisible(orderNotFoundToastMsg, 30);
	}

	public void verifyCreateBtnIsWorking() {
		gotoFailedOrdersPage();
		
		if(noFoundImgList.isEmpty()) {
			util.click(createButtonArchivedOrder.get(0));
			util.waitUntilElementIsVisible(createOrderPageHeading, 30);
		}
		

	}

	public void verifyViewOnAmazonLinkIsWorking() {
		String url = util.getDriver().getTitle();
		
		if(noFoundImgList.isEmpty()) {
			util.click(viewOnAmazonLinkCreateOrder);
			util.getWindoHandleByUrl(AMAZONURL);
			util.gotoWindowByClosingCurrentOne(url);
			util.switchToIFrame();
		}
		
	}
	

	public void createOrder(String sku) {
		util.openSectionsInNewTab("settings");
		
		order.disableCreateOrderForNonExistingProductsTogglebutton();
//		goto product linking and unlink the sku
		util.openSectionsInNewTab("linking");
		util.waitUntilElementIsVisible(linkedTab, 30);
		util.click(linkedTab);
		util.waitUntilElementIsVisible(searchInpFieldLinking);
//		enter sku in search input field
		util.enterValue(searchInpFieldLinking, sku);
		util.hold(5);
//		clear search input field
		searchInpFieldLinking.clear();
		util.hold(2);
//		enter sku in search input field
		util.enterValue(searchInpFieldLinking, sku);
		util.hold(5);

		if (noDataList.isEmpty()) {
			util.click(unlinkBtn.get(0));
			util.hold(1);
			util.click(yesBtn);
			util.hold(5);
		}

	}
	
	public void gotoFailedOrdersAndSearch(String orderId) {
		util.goToSection("overview");
		gotoFailedOrdersPage();
		util.enterValue(searchInputFailedOrdersPage, orderId);
		util.waitUntilElementIsVisible(archiveButtonArchivedOrder.get(0), 30);
	}
	
	public void createFailedOrder(String orderId) {
		gotoFailedOrdersAndSearch(orderId);
		util.click(createButtonArchivedOrder.get(0));
		util.waitUntilElementIsVisible(createOrderBtnCreateOrdersPage, 30);
		util.click(linkBtnCreateOrdersPage);
		util.switchToIFrame();
		util.waitUntilElementIsVisible(linkBtnListLinkingReqTab.get(0), 30);
		util.click(linkBtnListLinkingReqTab.get(0));
		util.waitUntilElementIsVisible(linkBtnsLinkProductsModal);
		util.click(linkBtnsLinkProductsModal);
		util.hold(5);
		util.jsClick(confirmBtn);
		util.hold(3);
		gotoFailedOrdersAndSearch(orderId);
		util.click(createButtonArchivedOrder.get(0));
		util.waitUntilElementIsVisible(createOrderBtnCreateOrdersPage, 30);
		util.click(createOrderBtnCreateOrdersPage);
		util.waitUntilElementIsVisible(orderCreatedSuccessfully, 90);
		gotoFailedOrdersPage();
		util.enterValue(searchInputFailedOrdersPage, orderId);
		util.hold(5);
		util.switchToIFrame();
		util.listIsNotEmpty(noFoundImgList);
	}
	
	public void verifyOrdersCanBeArchieved(String orderId) {
		gotoFailedOrdersAndSearch(orderId);
		util.click(archiveButtonArchivedOrder.get(0));
		
	}
	
	public void selectActionIsDisplayed(String orderId) {
		gotoFailedOrdersAndSearch(orderId);
		util.click(checkBoxFailedOrdersPage.get(0));
		util.waitUntilElementIsClickable(selectActionsBtnFailedOrderPage, 30);
		
		if(selectActionsBtnFailedOrderPage.getAttribute(ARIAEXPANDED).equalsIgnoreCase(FALSEVAL)) {
			util.click(selectActionsBtnFailedOrderPage);
		}
		
		util.click(archiveOrderBtnSelectActionsFailedOrderPage);
		
		util.waitUntilElementIsVisible(confirmBtnModal, 30);
		util.click(confirmBtnModal);
		gotoFailedOrdersPage();
		util.enterValue(searchInputFailedOrdersPage, orderId);
		util.hold(5);
		util.switchToIFrame();
		util.listIsNotEmpty(noFoundImgList);
	}

}
