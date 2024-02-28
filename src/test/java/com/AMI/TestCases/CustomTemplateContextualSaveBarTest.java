package com.AMI.TestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ami.pageobjects.CreateShopifyProduct;
import com.ami.pageobjects.CustomTemplateContextualSaveBarPage;
import com.ami.pageobjects.CustomTemplatePage;
import com.ami.pageobjects.reusableclasses.ReusableMethods;
import com.ami.resources.BaseClass;

public class CustomTemplateContextualSaveBarTest extends BaseClass {
	private CustomTemplateContextualSaveBarPage dtp;
	private ReusableMethods reuse;
	private CreateShopifyProduct create;

	@DataProvider
	public Object[][] getTemplateData() throws IOException {
		List<HashMap<String, String>> data = util.getJsonDataToHashmap("Template");
		return new Object[][] { { data.get(0) } };
	}
	
	@Test(priority = 1)
	public void initialize() {
		reuse = new ReusableMethods(util);
		dtp = new CustomTemplateContextualSaveBarPage(util);
		create = new CreateShopifyProduct(util);
	}

	@Test(priority = 1)
	public void testContextualSaveBarIsDisplayedIfInventorySettingsAreChanged() {
		util.openSectionsInNewTab("settings");
		reuse.openProductTemplatesPage();
		dtp.contextualSaveBarIsDisplayedIfInventorySettingsAreChanged();
	}

	@Test(priority = 2)
	public void testBarcodeExemptionCheckboxCheckedUncheckedContextualSaveBarIsDisplayed() {
		dtp.enablePriceSyncingAndSave();
		dtp.changeDefaultSettingsAndValidateContextualSaveBarIsDisplayed("barCodeExemptionCheckbox");
	}

	@Test(priority = 2)
	public void testContextualSaveBarIsDisplayedIfHandlingTimeValueIsChanged() {
		dtp.contextualSaveBarIsDisplayedIfHandlingTimeValueIsChanged();
	}

	@Test(priority = 3)
	public void testContextualSaveBarIsNotDisplayedIfHandlingTimeValueEnteredIsSame() {
		dtp.contextualSaveBarIsNotDisplayedIfHandlingTimeValueEnteredIsSame();
	}

	@Test(priority = 4)
	public void testContinueSellingCheckboxCheckedUncheckedContextualSaveBarIsDisplayed() {
		dtp.continueSellingCheckboxCheckedUncheckedContextualSaveBarIsDisplayed();
	}

	@Test(priority = 5)
	public void testFixedInventoryCheckboxCheckedUncheckedContextualSaveBarIsDisplayed() {
		dtp.changeDefaultSettingsAndValidateContextualSaveBarIsDisplayed("fixedInventoryCheckbox");
	}

	@Test(priority = 6)
	public void testSetReserveInventoryCheckboxCheckedUncheckedContextualSaveBarIsDisplayed() {
		dtp.changeDefaultSettingsAndValidateContextualSaveBarIsDisplayed("setReserveCheckbox");
	}

	@Test(priority = 7)
	public void testThresoldInventoryCheckboxCheckedUncheckedContextualSaveBarIsDisplayed() {
		dtp.changeDefaultSettingsAndValidateContextualSaveBarIsDisplayed("thresoldInventoryCheckbox");
	}

	@Test(priority = 8)
	public void testWareHouseCheckboxCheckedUncheckedContextualSaveBarIsDisplayed() {
		dtp.selectDeSelelctInventoryCheckBox();
	}

	@Test(priority = 9)
	public void testPriceSyncingFromAppCheckboxCheckedUncheckedContextualSaveBarIsDisplayed() {
		dtp.continueSellingCheckboxCheckedUncheckedContextualSaveBarIsDisplayed();
		dtp.changeDefaultSettingsAndValidateContextualSaveBarIsDisplayed("enablePriceSyncCheckbox");
	}

	@Test(priority = 10)
	public void testSalePriceCheckboxCheckedUncheckedContextualSaveBarIsDisplayed() {
		dtp.enablePriceSyncingAndSave();
		dtp.changeDefaultSettingsAndValidateContextualSaveBarIsDisplayed("salePriceCheckbox");
	}

	@Test(priority = 11)
	public void testBusinessPriceCheckboxCheckedUncheckedContextualSaveBarIsDisplayed() {
		dtp.enablePriceSyncingAndSave();
		dtp.changeDefaultSettingsAndValidateContextualSaveBarIsDisplayed("businessPriceCheckbox");
	}

	@Test(priority = 12)
	public void testMinimumPriceCheckboxCheckedUncheckedContextualSaveBarIsDisplayed() {
		dtp.enablePriceSyncingAndSave();
		dtp.changeDefaultSettingsAndValidateContextualSaveBarIsDisplayed("minimumPriceCheckbox");
	}

	@Test(priority = 13)
	public void testDeleteOutOfStockCheckboxCheckedUncheckedContextualSaveBarIsDisplayed() {
		dtp.enablePriceSyncingAndSave();
		dtp.changeDefaultSettingsAndValidateContextualSaveBarIsDisplayed("delOutOfStockCheckBox");
	}

	@Test(priority = 14)
	public void testOverrideExistingProductsCheckboxCheckedUncheckedContextualSaveBarIsDisplayed() {
		dtp.enablePriceSyncingAndSave();
		dtp.changeDefaultSettingsAndValidateContextualSaveBarIsDisplayed("overrideExistingProductsCheckBox");
	}

	@Test(priority = 15)
	public void testChangeThresoldInventoryUnitContextualSaveBarIsDisplayed() {
		dtp.enableThresoldInventoryAndSave();
		dtp.changeThresoldInvenventory();
	}

	@Test(priority = 16)
	public void testChangeFixedInventoryUnitContextualSaveBarIsDisplayed() {
		dtp.enableFixedInventoryAndSave();
		dtp.changeFixedInvenventory();
	}

	@Test(priority = 17)
	public void testChangeReserveInventoryUnitContextualSaveBarIsDisplayed() {
		dtp.enableReserveInventoryAndSave();
		dtp.changeReserveInvenventory();
	}

	@Test(priority = 18)
	public void testProductDetailsCheckboxCheckedUncheckedContextualSaveBarIsDisplayed() {
		dtp.enablePriceSyncingAndSave();
		dtp.changeDefaultSettingsAndValidateContextualSaveBarIsDisplayed("productDetailsCheckbox");
	}

	@Test(priority = 19)
	public void testImagesCheckboxCheckedUncheckedContextualSaveBarIsDisplayed() {
		dtp.enablePriceSyncingAndSave();
		dtp.changeDefaultSettingsAndValidateContextualSaveBarIsDisplayed("imagesCheckbox");
	}

	@Test(priority = 20, dataProvider = "getTemplateData")
	public void runValidateTemplateEditFunctionality(HashMap<String, String> input) {
		String url = util.getPageURL();
		util.openAndSwitchToNewTab();
		create.createNewShopifyProduct();
		util.gotoWindowByClosingCurrentOne(url);
		util.refreshPage();
		util.switchToIFrame();
		reuse.createNewTemplateUsingAdvanceSelection();
		reuse.searchAndOpenEditTemplatePage(CreateShopifyProduct.nameOfProduct);
		dtp.changeAdvanceSelection();
	}

}
