package com.ami.pageobjects;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ami.resources.Utilities;

public class InventorySettingsPage extends ProductDetailsOR {
	Utilities util;
	CreateShopifyProduct csp;
	boolean isCustomInventoryToggleBtnEnabled;
	boolean isFixedInventoryToggleBtnEnabled;
	boolean isReservedInventoryToggleBtnEnabled;
	boolean isThresoldInventoryToggleBtnEnabled;
	boolean isDeleteOutOfStockInventoryToggleBtnEnabled;
	boolean isManageWareHouseToggleBtnEnabled;
	private String url = "";

	public InventorySettingsPage(Utilities util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
		csp = new CreateShopifyProduct(util);
	}

	public void gotoInventorySettings() {
		util.openSectionsInNewTab(LISTING);
		util.openSectionsInNewTab(LINKING);
		util.openSectionsInNewTab(SETTINGSSECTION);
		util.openNewProduct();
		util.goToSection(SETTINGSSECTION);
		util.click(prodSettings);
		util.click(tabInventorySettings);
		util.isElementDisplyedAndValidate(headingInventorySettings);
	}

	public void disableInventorySettings() {
		if (enabledInventorySettingBtn.getAttribute(ARIAPRESSED).equalsIgnoreCase(TRUEVAL)) {
			util.click(disabledInventorySettingBtn);
			util.switchToDefaultContent();
			util.click(saveBtn);
			util.switchToIFrame();
		}

//		verify other toggle btns are disabled

		isCustomInventoryToggleBtnEnabled = setCustomInventoryToggleBtn.isEnabled();
		isFixedInventoryToggleBtnEnabled = setFixedInventoryToggleBtn.isEnabled();
		isReservedInventoryToggleBtnEnabled = setReservedInventoryToggleBtn.isEnabled();
		isThresoldInventoryToggleBtnEnabled = thresoldInventoryToggleBtn.isEnabled();
		isDeleteOutOfStockInventoryToggleBtnEnabled = deleteOutOfStockToggleBtn.isEnabled();
		isManageWareHouseToggleBtnEnabled = manageWareHouseToggleBtn.isEnabled();

		if (!isCustomInventoryToggleBtnEnabled && isFixedInventoryToggleBtnEnabled
				&& isReservedInventoryToggleBtnEnabled && isThresoldInventoryToggleBtnEnabled
				&& isDeleteOutOfStockInventoryToggleBtnEnabled && isManageWareHouseToggleBtnEnabled) {

		}
	}

	public void enableInventorySettings() {
		if (enabledInventorySettingBtn.getAttribute(ARIAPRESSED).equalsIgnoreCase(FALSEVAL)) {
			util.click(enabledInventorySettingBtn);
			util.switchToDefaultContent();
			util.click(saveBtn);
			util.switchToIFrame();
		}

//		verify other toggle btns are disabled

		isCustomInventoryToggleBtnEnabled = setCustomInventoryToggleBtn.isEnabled();
		isFixedInventoryToggleBtnEnabled = setFixedInventoryToggleBtn.isEnabled();
		isReservedInventoryToggleBtnEnabled = setReservedInventoryToggleBtn.isEnabled();
		isThresoldInventoryToggleBtnEnabled = thresoldInventoryToggleBtn.isEnabled();
		isDeleteOutOfStockInventoryToggleBtnEnabled = deleteOutOfStockToggleBtn.isEnabled();
		isManageWareHouseToggleBtnEnabled = manageWareHouseToggleBtn.isEnabled();

		if (isCustomInventoryToggleBtnEnabled && isFixedInventoryToggleBtnEnabled && isReservedInventoryToggleBtnEnabled
				&& isThresoldInventoryToggleBtnEnabled && isDeleteOutOfStockInventoryToggleBtnEnabled
				&& isManageWareHouseToggleBtnEnabled) {

		}
	}

	public void customInventoryInputIsWorking() {

		if (setCustomInventoryToggleBtn.getAttribute(ARIACHECKED).equalsIgnoreCase(FALSEVAL)) {
			util.click(setCustomInventoryToggleBtn);
			util.switchToDefaultContent();
			util.click(saveBtn);
			util.switchToIFrame();
		}

		if (!setCustomInventoryInputField.isEnabled()) {
			Assert.assertTrue(true, "custom inventory input field is enabled.");
		}

	}

	public void deactivateProductOnAmazonToggleBtnIsWorking() {
		enableInventorySettings();
		String initial = util.extractValueByAttributes(deleteOutOfStockToggleBtn, "aria-checked");

		util.click(deleteOutOfStockToggleBtn);
		util.switchToDefaultContent();
		util.click(saveBtn);
		util.switchToIFrame();
		String finalcondition = util.extractValueByAttributes(deleteOutOfStockToggleBtn, "aria-checked");
		Assert.assertNotEquals(initial, finalcondition, "initial and final are same.");
	}

	public void onChangeSettings() {
		util.click(deleteOutOfStockToggleBtn);
		util.switchToDefaultContent();
		util.click(discardBtn);
		util.hold(1);
		util.click(discardChangesBtn);
		util.hold(1);
		util.listIsEmpty(discardChangesBtnList);
		util.switchToIFrame();
	}

	public void disableDeleteOutOfStockToggleBtn() {
		if (deleteOutOfStockToggleBtn.getAttribute(ARIACHECKED).equalsIgnoreCase(TRUEVAL)) {
			util.click(deleteOutOfStockToggleBtn);
			util.switchToDefaultContent();
			util.click(saveBtn);
			util.switchToIFrame();
		}
	}

	public void enableDeleteOutOfStockToggleBtn() {
		if (deleteOutOfStockToggleBtn.getAttribute(ARIACHECKED).equalsIgnoreCase(FALSEVAL)) {
			util.click(deleteOutOfStockToggleBtn);
			util.switchToDefaultContent();
			util.click(saveBtn);
			util.switchToIFrame();
		}
	}

	public void linkProduct(String fileName, String sku, String prodName) {

		util.hold(30);
		util.goToSection(LINKINGSECTION);
		util.refreshPage();
		util.switchToIFrame();
		util.waitUntilElementIsVisible(linkedTab, 30);
		util.click(linkedTab);
		util.waitUntilElementIsVisible(searchInpFieldLinking);
//		enter sku in search input field
		util.enterValue(searchInpFieldLinking, util.getProperty(fileName, sku));
		util.hold(5);
//		clear search input field
		searchInpFieldLinking.clear();
		util.hold(2);
//		enter sku in search input field
		util.enterValue(searchInpFieldLinking, util.getProperty(fileName, sku));
		util.hold(5);

//		if no data is displayed the switch to linking required tab
		if (!noDataList.isEmpty()) {
			util.click(linkingReqBtnLinking);
			util.hold(1);
			util.enterValue(searchInpFieldLinking, util.getProperty(fileName, sku));
			util.hold(5);
			util.pressBackSpace();
			util.hold(5);
			util.enterValue(searchInpFieldLinking, util.getProperty(fileName, sku));
			util.hold(5);

			for (int i = 0; i < skuOnLinkingRequiredTab.size(); i++) {
				if (skuOnLinkingRequiredTab.get(i).getText().equals(util.getProperty(fileName, sku))) {
					util.click(linkBtnListLinkingReqTab.get(i));
					break;
				}
			}
		} else {
//			if no data is not displayed then unlink the linked product
			util.click(unlinkBtn.get(0));
			util.hold(1);
			util.click(yesBtn);
			util.hold(5);
			util.click(linkingReqBtnLinking);
			util.enterValue(searchInpFieldLinking, util.getProperty(fileName, sku));
			util.hold(5);
			util.enterValue(searchInpFieldLinking, util.getProperty(fileName, sku));
			util.hold(5);
		}

		if (!linkBtns.isEmpty()) {

			for (int i = 0; i < skuOnLinkingRequiredTab.size(); i++) {
				if (skuOnLinkingRequiredTab.get(i).getText().equals(util.getProperty(fileName, sku))) {
					util.click(linkBtnListLinkingReqTab.get(i));
					break;
				}
			}

			util.waitUntilElementIsVisible(searchProduct, 30);
			util.enterValue(searchProduct, util.getProperty(fileName, prodName));
			util.hold(5);
			util.pressBackSpace();
			util.hold(5);
			util.enterValue(searchProduct, util.getProperty(fileName, prodName));
			util.hold(5);

			while (productNameListLinkModal.isEmpty()) {
				util.enterValue(searchProduct, util.getProperty(fileName, prodName));
				util.hold(5);
			}

			util.waitUntilElementIsVisible(linkBtnsLinkProductsModal);
			util.click(linkBtnsLinkProductsModal);
			util.hold(5);
			util.jsClick(confirmBtn);
			util.hold(3);
		}
	}

	public void checkInProgressTagIsInActive(String sku) {
		int count = 0;
		util.goToSection("listings");
		util.refreshPage();
		util.switchToIFrame();
		util.waitUntilElementIsVisible(moreFiltersBtn, 30);

		if (moreFiltersModal.isEmpty()) {

			util.click(moreFiltersBtn);
		}

		util.waitUntilElementIsVisible(moreFiltersBtn, 10);

		if (skuCollapsible.getAttribute("aria-hidden").equals(TRUEVAL)) {
			util.click(skuToggleButton);
			util.hold(1);
		}

		util.selectByVisibleText(selectSKU, "Equals");
		util.enterValue(skuInputField, sku);
		util.click(doneBtnMoreFiltersModal);
		util.hold(1);

		if (!inprogressTagIsAcive.isEmpty()) {

			do {
				if (count == 31) {
					util.logError("inprogress tag is still active afer 30 minutes");
					Assert.assertTrue(false);
				}
				checkForInProgressTag();
				count++;
			} while (!inprogressTagIsAcive.isEmpty());

		}
	}
	
	

	public void checkForInProgressTag() {
		util.refreshPage();
		util.hold(60);
		util.switchToIFrame();

		if (!inprogressTagIsAcive.isEmpty()) {
			util.hold(10);

		}
	}

	public void deleteShopifyProduct(String prodId) {
		gotoProduct(prodId);
		csp.deleteShopifyProduct();
	}
	
	public void draftShopifyProduct(String prodId) {
		gotoProduct(prodId);
		csp.draftShopifyProduct();
	}
	
	public void makeSalesChannelInactive(String prodId) {
		gotoProduct(prodId);
		util.click(manageBtn);
		util.hold(1);
		util.click(manageSalesChannelAndAppBtn);
		util.hold(1);
		util.click(deSelectAllBtn);
		util.hold(1);
		util.click(doneBtnModal);
		util.hold(2);
		util.click(saveBtn);
		util.hold(3);
	}

	public void startSyncProduct() {
		util.goToSection(LISTING);
		util.refreshPage();
		util.switchToIFrame();
		util.waitUntilElementIsVisible(syncStatusBtn, 60);
		util.waitUntilElementIsClickable(syncStatusBtn, 30);
		util.click(syncStatusBtn);
		util.waitUntilElementIsVisible(proceedBtn, 30);
		util.click(proceedBtn);
	}
	
	public void waitUntilSyncIsComplete() {
		util.goToSection(OVERVIEWSECTION);
		util.refreshPage();
		util.switchToIFrame();
		util.waitUntilElementIsVisible(overViewHeading, 30);
		int count = 0;
		
		while(!syncStatusProgressbarList.isEmpty()) {
			if(count > 20) {
				Assert.assertTrue(false);
			}
			util.hold(60);
			util.refreshPage();
			util.switchToIFrame();
			util.waitUntilElementIsVisible(activitiesHeading, 30);
			count += 1;
		}
		
		util.hold(5);
		
		while(!syncAmazonListingImgList.isEmpty()) {
			if(count > 20) {
				Assert.assertTrue(false);
			}
			util.hold(60);
			util.refreshPage();
			util.switchToIFrame();
			util.waitUntilElementIsVisible(activitiesHeading, 30);
			count += 1;
		}
		
	}
	
	public void verifyProductStatusIsSameAsMarketPlace(String fileName, String sku) {
		util.goToSection(LINKINGSECTION);
		util.refreshPage();
		util.switchToIFrame();
		util.click(linkedTab);
		util.hold(3);
		util.click(linkingReqTab);
		util.hold(1);
		util.enterValue(searchInpFieldLinking, util.getProperty(fileName, sku));
		util.hold(5);
		util.pressBackSpace();
		util.hold(5);
		util.enterValue(searchInpFieldLinking, util.getProperty(fileName, sku));
		util.hold(5);
		
		String inventory = util.getTagValue(inventoryLinkingReq.get(0));
		Assert.assertEquals(inventory, "0");
	}
	
	public void openAdminPanelFeeds() {
		util.openAndSwitchToNewTab();
		String url = "";
		
		if(util.getConfigProperty(STORE).equalsIgnoreCase(LIVE)) {
			url = LIVEADMIN;
		}else {
			url = STAGINGADMIN;
		}
		System.out.println(url);
		url = url + util.getConfigProperty(TOKEN);
		util.openUrl(url);
		util.hold(5);
		util.selectByIndex(selectStore, 1);
		util.waitUntilElementIsVisible(feeds, 30);
		util.click(feeds);
		util.waitUntilElementIsVisible(feedIdEquals, 10);
		
	}
	
	public void openJsonInventory(String inventory) {
		util.click(feedIdEquals);
		util.hold(1);
		util.click(status);
		util.click(equals);
		util.click(done);
		util.click(feedIdEquals);
		util.hold(1);
		util.click(type);
		util.click(equals);
		util.click(jsonInventory);
		util.hold(1);
		util.click(viewBtnFeedsList.get(0));
		String feedInventory = qunatityFeeds.getText();
		System.out.println(feedInventory);
		feedInventory = StringUtils.substringAfterLast(feedInventory, ":").trim();
		Assert.assertEquals(feedInventory, inventory);
		
	}
	
	public void goToAdminPanel() {
		
		if(util.getConfigProperty(STORE).equalsIgnoreCase(STAGING)){
			util.getWindoHandleByUrl(STAGINGADMIN);
		}else if(util.getConfigProperty(STORE).equalsIgnoreCase(LIVE)){
			util.getWindoHandleByUrl(LIVEADMIN);
		}
	}
	
	public void gotoProduct(String prodId) {
//		goto shopify
		
		if(util.getConfigProperty("store").equals("live")) {
			url  = util.getConfigProperty("storeUrl") + "products/";
		}else if(util.getConfigProperty("store").equals("staging")) {
			url  = util.getConfigProperty("stroreUrlStaging") + "products/";
		}
		
		System.out.println(url);
		util.getWindoHandleByUrl(url);
		util.hold(5);
		System.out.println(url + prodId);
		util.openUrl(url + prodId);

	}

}
