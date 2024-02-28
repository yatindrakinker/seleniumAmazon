package com.AMI.TestCases;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ami.pageobjects.DefaultTemplatePage;
import com.ami.resources.BaseClass;

public class DefaultTemplateTest extends BaseClass {

	@DataProvider
	public Object[][] getTemplateData() throws IOException {
		List<HashMap<String, String>> data = util.getJsonDataToHashmap("Template");
		return new Object[][] { { data.get(0) } };
	}

	@Test(priority = 1)
	public void run_TC_AM_DT_001_002() {
		DefaultTemplatePage dtp = new DefaultTemplatePage(util);
		dtp.validateRedirectionToDefTemplatePage();
	}

	@Test(priority = 2)
	public void run_TC_AM_DT_005() {
		DefaultTemplatePage dtp = new DefaultTemplatePage(util);
		dtp.validateAllCheckBoxAreWorking();
	}

	@Test(priority = 3)
	public void run_TC_AM_DT_006_007() {
		DefaultTemplatePage dtp = new DefaultTemplatePage(util);
		dtp.validateInventorySettings();
	}

	@Test(priority = 4, dataProvider = "getTemplateData")
	public void run_TC_AM_DT_008_009_10_11(HashMap<String, String> input) {
		DefaultTemplatePage dtp = new DefaultTemplatePage(util);
		dtp.tcAmDt008();
		dtp.tcAmDt010(input);
	}

	@Test(priority = 5, dataProvider = "getTemplateData")
	public void run_TC_AM_DT_012_14_15(HashMap<String, String> input) {
		DefaultTemplatePage dtp = new DefaultTemplatePage(util);
		dtp.tcAmDt012();
		dtp.tcAmDt014015(input);
	}

	@Test(priority = 6, dataProvider = "getTemplateData")
	public void run_validateFixedInventoryFunctionality(HashMap<String, String> input) {
		DefaultTemplatePage dtp = new DefaultTemplatePage(util);
		dtp.validateFixedInventoryFunctionality();
	}

	@Test(priority = 7, dataProvider = "getTemplateData")
	public void run_validateSetReserveFunctionality(HashMap<String, String> input) {
		DefaultTemplatePage dtp = new DefaultTemplatePage(util);
		dtp.validateSetReserveFunctionality(input);
	}

	@Test(priority = 8, dataProvider = "getTemplateData")
	public void run_validateThresoldInventoryFunctionality(HashMap<String, String> input) {
		DefaultTemplatePage dtp = new DefaultTemplatePage(util);
		dtp.validateThresoldInventoryFunctionality(input);
	}

	@Test(priority = 9, dataProvider = "getTemplateData")
	public void run_validatePriceSettingsFunctionality(HashMap<String, String> input) {
		DefaultTemplatePage dtp = new DefaultTemplatePage(util);
		dtp.validatePriceSettingsFunctionality();
		dtp.validateEnableSalePriceFunctionality(input);
		dtp.validateBusinessPriceFunctionality(input);
		dtp.validateMinimumPriceFunctionality(input);
	}

	@Test(priority = 10)
	public void testContextualSaveBarIsDisplayedIfInventorySettingsAreChanged() {
		DefaultTemplatePage dtp = new DefaultTemplatePage(util);
		dtp.contextualSaveBarIsDisplayedIfInventorySettingsAreChanged();
	}

	@Test(priority = 11)
	public void testContextualSaveBarIsDisplayedIfHandlingTimeValueIsChanged() {
		DefaultTemplatePage dtp = new DefaultTemplatePage(util);
		dtp.contextualSaveBarIsDisplayedIfHandlingTimeValueIsChanged();
	}

	@Test(priority = 12)
	public void testContextualSaveBarIsNotDisplayedIfHandlingTimeValueEnteredIsSame() {
		DefaultTemplatePage dtp = new DefaultTemplatePage(util);
		dtp.contextualSaveBarIsNotDisplayedIfHandlingTimeValueEnteredIsSame();
	}

	@Test(priority = 13)
	public void testErrorToastMsgIsDisplayedWhenInvalidHandlingTimeIsEnteredAndSaved() {
		DefaultTemplatePage dtp = new DefaultTemplatePage(util);
		dtp.enterInvalidHandlingTimeAndSave();
	}

	@Test(priority = 14)
	public void testContinueSellingCheckboxCheckedUncheckedContextualSaveBarIsDisplayed() {
		DefaultTemplatePage dtp = new DefaultTemplatePage(util);
		dtp.continueSellingCheckboxCheckedUncheckedContextualSaveBarIsDisplayed();
	}

	@Test(priority = 15)
	public void testFixedInventoryCheckboxCheckedUncheckedContextualSaveBarIsDisplayed() {
		DefaultTemplatePage dtp = new DefaultTemplatePage(util);
		dtp.continueSellingCheckboxCheckedUncheckedContextualSaveBarIsDisplayed();
		dtp.changeDefaultSettingsAndValidateContextualSaveBarIsDisplayed("fixedInventoryCheckbox");
	}

	@Test(priority = 16)
	public void testSetReserveInventoryCheckboxCheckedUncheckedContextualSaveBarIsDisplayed() {
		DefaultTemplatePage dtp = new DefaultTemplatePage(util);
		dtp.continueSellingCheckboxCheckedUncheckedContextualSaveBarIsDisplayed();
		dtp.changeDefaultSettingsAndValidateContextualSaveBarIsDisplayed("setReserveCheckbox");
	}

	@Test(priority = 17)
	public void testThresoldInventoryCheckboxCheckedUncheckedContextualSaveBarIsDisplayed() {
		DefaultTemplatePage dtp = new DefaultTemplatePage(util);
		dtp.continueSellingCheckboxCheckedUncheckedContextualSaveBarIsDisplayed();
		dtp.changeDefaultSettingsAndValidateContextualSaveBarIsDisplayed("thresoldInventoryCheckbox");
	}

	@Test(priority = 18)
	public void testWareHouseCheckboxCheckedUncheckedContextualSaveBarIsDisplayed() {
		DefaultTemplatePage dtp = new DefaultTemplatePage(util);
		dtp.selectDeSelelctInventoryCheckBox();
	}

	@Test(priority = 19)
	public void testPriceSyncingFromAppCheckboxCheckedUncheckedContextualSaveBarIsDisplayed() {
		DefaultTemplatePage dtp = new DefaultTemplatePage(util);
		dtp.continueSellingCheckboxCheckedUncheckedContextualSaveBarIsDisplayed();
		dtp.changeDefaultSettingsAndValidateContextualSaveBarIsDisplayed("enablePriceSyncCheckbox");
	}

	@Test(priority = 20)
	public void testSalePriceCheckboxCheckedUncheckedContextualSaveBarIsDisplayed() {
		DefaultTemplatePage dtp = new DefaultTemplatePage(util);
		dtp.enablePriceSyncingAndSave();
		dtp.changeDefaultSettingsAndValidateContextualSaveBarIsDisplayed("salePriceCheckbox");
	}

	@Test(priority = 21)
	public void testBusinessPriceCheckboxCheckedUncheckedContextualSaveBarIsDisplayed() {
		DefaultTemplatePage dtp = new DefaultTemplatePage(util);
		dtp.enablePriceSyncingAndSave();
		dtp.changeDefaultSettingsAndValidateContextualSaveBarIsDisplayed("businessPriceCheckbox");
	}

	@Test(priority = 22)
	public void testMinimumPriceCheckboxCheckedUncheckedContextualSaveBarIsDisplayed() {
		DefaultTemplatePage dtp = new DefaultTemplatePage(util);
		dtp.enablePriceSyncingAndSave();
		dtp.changeDefaultSettingsAndValidateContextualSaveBarIsDisplayed("minimumPriceCheckbox");
	}

	@Test(priority = 23)
	public void testBarcodeCheckboxCheckedUncheckedContextualSaveBarIsDisplayed() {
		DefaultTemplatePage dtp = new DefaultTemplatePage(util);
		dtp.enablePriceSyncingAndSave();
		dtp.changeDefaultSettingsAndValidateContextualSaveBarIsDisplayed("barcodeCheckbox");
	}

	@Test(priority = 24)
	public void testSKUCheckboxCheckedUncheckedContextualSaveBarIsDisplayed() {
		DefaultTemplatePage dtp = new DefaultTemplatePage(util);
		dtp.enablePriceSyncingAndSave();
		dtp.changeDefaultSettingsAndValidateContextualSaveBarIsDisplayed("skuCheckbox");
	}

	@Test(priority = 25)
	public void testChangeThresoldInventoryUnitContextualSaveBarIsDisplayed() {
		DefaultTemplatePage dtp = new DefaultTemplatePage(util);
		dtp.enableThresoldInventoryAndSave();
		dtp.changeThresoldInvenventory();
	}

	@Test(priority = 26)
	public void testChangeFixedInventoryUnitContextualSaveBarIsDisplayed() {
		DefaultTemplatePage dtp = new DefaultTemplatePage(util);
		dtp.enableFixedInventoryAndSave();
		dtp.changeFixedInvenventory();
	}

	@Test(priority = 27)
	public void testChangeReserveInventoryUnitContextualSaveBarIsDisplayed() {
		DefaultTemplatePage dtp = new DefaultTemplatePage(util);
		dtp.enableReserveInventoryAndSave();
		dtp.changeReserveInvenventory();
	}

	@Test(priority = 28)
	public void testChangeStandardPriceChoseTrendContextualSaveBarIsDisplayed() {
		DefaultTemplatePage dtp = new DefaultTemplatePage(util);
		dtp.changeStandardPriceChoseTrend();
	}
}
