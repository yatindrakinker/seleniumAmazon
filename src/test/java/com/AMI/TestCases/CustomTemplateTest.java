package com.AMI.TestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ami.pageobjects.CreateShopifyProduct;
import com.ami.pageobjects.CustomTemplatePage;
import com.ami.pageobjects.reusableclasses.ReusableMethods;
import com.ami.resources.BaseClass;

public class CustomTemplateTest extends BaseClass {
	private CreateShopifyProduct create;
	private CustomTemplatePage ctp;
	private ReusableMethods reuse;
	String url = "";

	@DataProvider
	public Object[][] getTemplateData() throws IOException {
		List<HashMap<String, String>> data = util.getJsonDataToHashmap("Template");
		return new Object[][] { { data.get(0) } };
	}

	@Test(priority = 1, dataProvider = "getTemplateData")
	public void test_TC_AM_CT_001_004_005_007_010(Map<String, String> input) {
		ctp = new CustomTemplatePage(util);
		create = new CreateShopifyProduct(util);
		reuse = new ReusableMethods(util);
		ctp.validateAddNewTemplateBtn();
		ctp.validateTemplateNamingConvention(input);
		ctp.validateBarCodeExempCheckbox();
		ctp.validateOptionalFieldAreNotVisibleWhenNoCategoryIsChosen();
		ctp.validateLinkIsNotBroken();
	}

	@Test(dataProvider = "getTemplateData", priority = 2)
	public void testValidateWhenUserOnlySelectsProductCategoryAndSaveTemplate(Map<String, String> input) {
		url = util.getPageURL();
		util.openAndSwitchToNewTab();
		create.createNewShopifyProduct();
		util.getWindoHandleByUrl(url);
		ctp.validateWhenUserOnlySelectsProductCategoryAndSaveTemplate(input);
	}

	@Test(dataProvider = "getTemplateData", priority = 3)
	public void testValidateRequiredAttributesAreDisplayed(Map<String, String> input) {

		ctp.validateRequiredAttributesAreDisplayed(input);
		ctp.validateUserCanSelectCustomAttribute();
		ctp.validateUserCanSelectShopifyAttribute();
		ctp.validateErrorMsgIsDisplayedIfMapsOnlyFewOptions();
		ctp.validateUserCanAddOptionalAttribute();
		ctp.openMapOptionalAttribute();
		ctp.clickSaveBtnErrIsDisplayed();
	}

	@Test(dataProvider = "getTemplateData", priority = 4)
	public void testValidateUserCanCreateTemplateByAddingOptionalAttributes(Map<String, String> input) {

		ctp.validateUserCanCreateTemplateByAddingOptionalAttributes(input);
		ctp.goToTemplates();
		ctp.waitUntilProductIsBeignAssigned();
	}

	@Test(priority = 5)
	public void testValidateAllCheckboxAreWorking() {

		ctp.validateAllCheckboxAreWorking();
		ctp.validateWareHouseCheckBoxesWorking();
	}

	@Test(priority = 6)
	public void testValidateWhenInventorySettingsIsDisabled() {
		ctp.validateWhenInventorySettingsIsDisabled();
	}

	@Test(priority = 7, dataProvider = "getTemplateData")
	public void testValidateTimeHandlingInpFieldAcceptsOnlyWholeNum(Map<String, String> input) {

		url = util.getPageURL();
		util.openAndSwitchToNewTab();
		create.createNewShopifyProduct();
		util.gotoWindowByClosingCurrentOne(url);
		util.switchToIFrame();
		ctp.validateTimeHandlingInpFieldAcceptsOnlyWholeNum(input);
	}

	@Test(priority = 8, dataProvider = "getTemplateData")
	public void testValidateFixedInventorySettingWorking(Map<String, String> input) {

		ctp.validateFixedInventorySettingWorking(input);
	}

	@Test(priority = 9, dataProvider = "getTemplateData")
	public void testValidateSetReserveFunctionality(Map<String, String> input) {

		url = util.getPageURL();
		util.openAndSwitchToNewTab();
		create.createNewShopifyProduct();
		util.gotoWindowByClosingCurrentOne(url);
		util.switchToIFrame();
		ctp.validateSetReserveFunctionality(input);
	}

	@Test(priority = 10, dataProvider = "getTemplateData")
	public void testValidateThresoldInventoryCheckBoxAndInpFieldFunctionality(Map<String, String> input) {

		ctp.validateThresoldInventoryCheckBoxAndInpFieldFunctionality(input);
	}

	@Test(priority = 11, dataProvider = "getTemplateData")
	public void testValidateAllCheckBoxOfWareHouseSettingsCanNotBeUnchecked(Map<String, String> input) {

		ctp.validateAllCheckBoxOfWareHouseSettingsCanNotBeUnchecked(input);
	}

	@Test(priority = 12, dataProvider = "getTemplateData")
	public void testValidatePriceSettingsFunctionality(Map<String, String> input) {

		ctp.validatePriceSettingsFunctionality(input);
	}

	@Test(priority = 13, dataProvider = "getTemplateData")
	public void testValidateBusinessPriceFunctionality(Map<String, String> input) {

		ctp.validateBusinessPriceFunctionality(input);
	}

	@Test(priority = 14, dataProvider = "getTemplateData")
	public void testValidateMinimumPriceFunctionality(Map<String, String> input) {

		ctp.validateMinimumPriceFunctionality(input);
	}

	@Test(priority = 15, dataProvider = "getTemplateData")
	public void testValidateEnableSalePriceFunctionality(Map<String, String> input) {

		ctp.validateEnableSalePriceFunctionality(input);
	}

	@Test(priority = 16, dataProvider = "getTemplateData")
	public void testValidateManualSelectionFunctionlity(Map<String, String> input) {

		ctp.validateManualSelectionFunctionlity(input);
	}

	@Test(priority = 17, dataProvider = "getTemplateData")
	public void testValidateAdvanceSelectionFunctionlity(Map<String, String> input) {

		ctp.validateAdvanceSelectionFunctionlity(input);
		ctp.addRowEnterValue(input);
		ctp.addGroupsAndEnterValues(input);
		ctp.validateAdvanceSelectionFiltersAreDisplayed();
		ctp.removeGrp();
		ctp.removeRow();
	}

	@Test(priority = 18, dataProvider = "getTemplateData")
	public void testValidateTemplateEditFunctionality(Map<String, String> input) {
		String url = util.getPageURL();
		util.openAndSwitchToNewTab();
		create.createNewShopifyProduct();
		util.getWindoHandleByUrl(url);
		ctp.validateTemplateEditFunctionality(input);
	}

	@Test(priority = 19, dataProvider = "getTemplateData")
	public void testManualSelectionModal() {
		ctp.manualSelectionModal();
	}

	@Test(priority = 20)
	public void testInvalidStartAndEndDatePreventsTemplateCreation() {
		ctp.verifyInvalidStartAndEndDatePreventsTemplateCreation();
	}

	@Test(priority = 21)
	public void testTemplateIsVisibleOnGrid() {

		reuse.verifyTemplateIsCreated();
	}

	@Test(priority = 22)
	public void testProductAlreadyAssignedInTemplateCanBeAssignedInAnotherTemplate() {
		ctp.verifyIfProdIsAlreadyAssignInExistingTemplate();
	}

	@Test(priority = 23)
	public void testTemplateHaveNoProductAfterItsProdIsAssignedToAnotherTemp() {
		ctp.verifyTemplateHaveNoProductAfterItsProdIsAssignedToAnotherTemp();
	}

	@Test(priority = 24, description = "201-209")
	public void testEditProdCategorySearch() {
		util.switchToIFrame();
		ctp.verifyEditProdCategorySearch();
	}

	@Test(priority = 25, description = "210-212")
	public void testFulfillmentTypeCanBeChanged() {
		ctp.verifyFBMIsSelectedByDefaultInAddNewTemplate();
		ctp.selectFulfillmentType("fba"); // As default category already assigned to
		// this product just click on default category reuse.selectDefaultCategory();
		util.hold(1);
		ctp.clickOnSaveBtn();
		reuse.waitForImportantInfoModal();
		reuse.searchAndOpenEditTemplatePage(CreateShopifyProduct.nameOfProduct);
		ctp.verifyFulfillmentTypeIsUpdated("fba");
		reuse.searchAndOpenEditTemplatePage(CreateShopifyProduct.nameOfProduct);
		ctp.selectFulfillmentType("fbm");
		util.hold(1);
		ctp.clickOnSaveBtn();
		reuse.waitForImportantInfoModal();
		reuse.searchAndOpenEditTemplatePage(CreateShopifyProduct.nameOfProduct);
		ctp.verifyFulfillmentTypeIsUpdated("fbm");
	}

	@Test(priority = 26, description = "213")
	public void testFulfillmentTypeCanBeChangedForVariantProduct() {
		ctp.gotoCreateNewVariantProduct();
		reuse.createNewTemplateUsingAdvanceSelection();
		reuse.searchAndOpenEditTemplatePage(CreateShopifyProduct.nameOfProduct);
		ctp.verifyFBMIsSelectedByDefaultInAddNewTemplate();
		ctp.selectFulfillmentType("fba");
		util.hold(1);
		ctp.clickOnSaveBtn();
		reuse.waitForImportantInfoModal();
		reuse.searchAndOpenEditTemplatePage(CreateShopifyProduct.nameOfProduct);
		ctp.verifyFulfillmentTypeIsUpdated("fba");
		reuse.searchAndOpenEditTemplatePage(CreateShopifyProduct.nameOfProduct);
		ctp.selectFulfillmentType("fbm");
		util.hold(1);
		ctp.clickOnSaveBtn();
		reuse.waitForImportantInfoModal();
		reuse.searchAndOpenEditTemplatePage(CreateShopifyProduct.nameOfProduct);
		ctp.verifyFulfillmentTypeIsUpdated("fbm");
	}

	@Test(priority = 27, description = "246-252")
	public void testCreateAndUpdateTemplateDateIsVisibleOnGrid() {
		ctp.verifyCreateAndUpdatedTimeIsVisibleOnGrid(CreateShopifyProduct.nameOfProduct);
		ctp.verifyCloneTemplateContainsCreateAndUpdatedTimeOnGrid(CreateShopifyProduct.nameOfProduct);
		reuse.searchAndOpenEditTemplatePage(CreateShopifyProduct.nameOfProduct);
	}

	@Test(priority = 28, description = "246-252")
	public void testRoundOff() {
		url = util.getPageURL();
		util.openAndSwitchToNewTab();
		create.createNewShopifyProduct();
		util.gotoWindowByClosingCurrentOne(url);
		util.refreshPage();
		util.switchToIFrame();
		reuse.createNewTemplateUsingAdvanceSelection();
		reuse.searchAndOpenEditTemplatePage(CreateShopifyProduct.nameOfProduct);
		ctp.verifyRoundOffFunctionality();
	}

	@Test(priority = 29)
	public void applyHigherWholeNum() {
		ctp.verifyRoundOff(CreateShopifyProduct.nameOfProduct, "higherWholeNumber");
		ctp.verifyForWholeNum();
	}

	@Test(priority = 30)
	public void applyNearestHigher10() {
		reuse.goToSettings();
		reuse.searchAndOpenEditTemplatePage(CreateShopifyProduct.nameOfProduct);
		ctp.verifyRoundOff(CreateShopifyProduct.nameOfProduct, "higherEndWith10");
		String roundOffVal = StringUtils.substringAfter(ctp.priceAfterRoundOff(), "₹").trim();
		String nearestVal = ctp
				.findHighestNearestNumEndingWithZero(Integer.parseInt(CreateShopifyProduct.priceOfProduct));
		Assert.assertEquals(roundOffVal, nearestVal);

	}

	@Test(priority = 31)
	public void applyNearestHigher9() {
		reuse.goToSettings();
		reuse.searchAndOpenEditTemplatePage(CreateShopifyProduct.nameOfProduct);
		ctp.verifyRoundOff(CreateShopifyProduct.nameOfProduct, "higherEndWith9");
		String roundOffVal = StringUtils.substringAfter(ctp.priceAfterRoundOff(), "₹").trim();
		String nearestVal = ctp.findNearestNumEndingWith(Integer.parseInt(CreateShopifyProduct.priceOfProduct), 9);
		Assert.assertEquals(roundOffVal, nearestVal);

	}

	@Test(priority = 32)
	public void testApplyNearestHigherPoint49() {
		reuse.goToSettings();
		reuse.searchAndOpenEditTemplatePage(CreateShopifyProduct.nameOfProduct);
		ctp.verifyRoundOff(CreateShopifyProduct.nameOfProduct, "higherEndWith0.49");
		String roundOffVal = StringUtils.substringAfter(ctp.priceAfterRoundOff(), "₹").trim();
		String nearestVal = ctp
				.findNearestNumEndingWithPoint49(Double.parseDouble(CreateShopifyProduct.priceOfProduct));
		Assert.assertEquals(roundOffVal, nearestVal);

	}

	@Test(priority = 33)
	public void testApplyNearestHigherPoint99() {
		reuse.goToSettings();
		reuse.searchAndOpenEditTemplatePage(CreateShopifyProduct.nameOfProduct);
		ctp.verifyRoundOff(CreateShopifyProduct.nameOfProduct, "higherEndWith0.99");
		String roundOffVal = StringUtils.substringAfter(ctp.priceAfterRoundOff(), "₹").trim();
		String nearestVal = ctp
				.findNearestNumEndingWithPoint99(Double.parseDouble(CreateShopifyProduct.priceOfProduct));
		Assert.assertEquals(roundOffVal, nearestVal);

	}

	@Test(priority = 34)
	public void applyNearestLower10() {
		reuse.goToSettings();
		reuse.searchAndOpenEditTemplatePage(CreateShopifyProduct.nameOfProduct);
		ctp.verifyRoundOff(CreateShopifyProduct.nameOfProduct, "lowerEndWith10");
		String roundOffVal = StringUtils.substringAfter(ctp.priceAfterRoundOff(), "₹").trim();
		String nearestVal = ctp.nearestLowerEndingWithZero(Integer.parseInt(CreateShopifyProduct.priceOfProduct));
		Assert.assertEquals(roundOffVal, nearestVal);
	}

	@Test(priority = 35)
	public void applyNearestLowerWhole() {
		reuse.goToSettings();
		reuse.searchAndOpenEditTemplatePage(CreateShopifyProduct.nameOfProduct);
		ctp.verifyRoundOff(CreateShopifyProduct.nameOfProduct, "lowerWholeNumber");
		String roundOffVal = StringUtils.substringAfter(ctp.priceAfterRoundOff(), "₹").trim();
		String nearestVal = ctp.immediateLowerWholeNum(Double.parseDouble(CreateShopifyProduct.priceOfProduct));
		Assert.assertEquals(roundOffVal, nearestVal);
	}

	@Test(priority = 36)
	public void applyNearestLowerEndingWith9() {
		reuse.goToSettings();
		reuse.searchAndOpenEditTemplatePage(CreateShopifyProduct.nameOfProduct);
		ctp.verifyRoundOff(CreateShopifyProduct.nameOfProduct, "lowerEndWith9");
		String roundOffVal = StringUtils.substringAfter(ctp.priceAfterRoundOff(), "₹").trim();
		String nearestVal = ctp.immediateLowerNumberEndingWith9(Integer.parseInt(CreateShopifyProduct.priceOfProduct));
		Assert.assertEquals(roundOffVal, nearestVal);
	}

	@Test(priority = 37)
	public void applyNearestLowerEndingWith99() {
		reuse.goToSettings();
		reuse.searchAndOpenEditTemplatePage(CreateShopifyProduct.nameOfProduct);
		ctp.verifyRoundOff(CreateShopifyProduct.nameOfProduct, "lowerEndWith0.99");
		String roundOffVal = StringUtils.substringAfter(ctp.priceAfterRoundOff(), "₹").trim();
		String nearestVal = ctp.lowerNumEndingWithPoint99(Integer.parseInt(CreateShopifyProduct.priceOfProduct));
		Assert.assertEquals(roundOffVal, nearestVal);
	}

	@Test(priority = 38)
	public void applyNearestLowerEndingWith49() {
		reuse.goToSettings();
		reuse.searchAndOpenEditTemplatePage(CreateShopifyProduct.nameOfProduct);
		ctp.verifyRoundOff(CreateShopifyProduct.nameOfProduct, "lowerEndWith0.49");
		String roundOffVal = StringUtils.substringAfter(ctp.priceAfterRoundOff(), "₹").trim();
		String nearestVal = ctp.lowerNumEndingWithPoint49(Integer.parseInt(CreateShopifyProduct.priceOfProduct));
		Assert.assertEquals(roundOffVal, nearestVal);
	}

	@Test(priority = 39, description = "392")
	public void applyPercentIncreaseEndingWithWholeNum() {
		Map<String, String> map = reuse.verifyRoundOffIncrease(CreateShopifyProduct.nameOfProduct,
				"percentage_increase", "higherWholeNumber", "whole");
		Assert.assertEquals(map.get("roundOffVal"), map.get("nearestVal"));
	}

	@Test(priority = 40, description = "393")
	public void applyPercentIncreaseEndingWith9() {
		Map<String, String> map = reuse.verifyRoundOffIncrease(CreateShopifyProduct.nameOfProduct,
				"percentage_increase", "higherEndWith9", "endWith9");
		Assert.assertEquals(map.get("roundOffVal"), map.get("nearestVal"));
	}

	@Test(priority = 41, description = "393")
	public void applyPercentIncreaseEndingWith10() {

		Map<String, String> map = reuse.verifyRoundOffIncrease(CreateShopifyProduct.nameOfProduct,
				"percentage_increase", "higherEndWith10", "endWith10");
		Assert.assertEquals(map.get("roundOffVal"), map.get("nearestVal"));
	}

	@Test(priority = 42, description = "394")
	public void applyPercentIncreaseEndingWithPoint49() {
		Map<String, String> map = reuse.verifyRoundOffIncrease(CreateShopifyProduct.nameOfProduct,
				"percentage_increase", "higherEndWith0.49", "endWithPoint49");
		Assert.assertEquals(map.get("roundOffVal"), map.get("nearestVal"));

	}

	@Test(priority = 43, description = "395")
	public void applyPercentIncreaseEndingWithPoint99() {
		Map<String, String> map = reuse.verifyRoundOffIncrease(CreateShopifyProduct.nameOfProduct,
				"percentage_increase", "higherEndWith0.99", "endWithPoint99");
		Assert.assertEquals(map.get("roundOffVal"), map.get("nearestVal"));
	}

	@Test(priority = 44)
	public void applyPercentDecreaseEndingWithWholeNum() {
		Map<String, String> map = reuse.verifyRoundOffDecreaseAtHigher(CreateShopifyProduct.nameOfProduct,
				"percentage_decrease", "higherWholeNumber", "whole");
		Assert.assertEquals(map.get("roundOffVal"), map.get("nearestVal"));
	}

	@Test(priority = 45)
	public void applyPercentDecreaseEndingWith9() {
		Map<String, String> map = reuse.verifyRoundOffDecreaseAtHigher(CreateShopifyProduct.nameOfProduct,
				"percentage_decrease", "higherEndWith9", "endWith9");
		Assert.assertEquals(map.get("roundOffVal"), map.get("nearestVal"));
	}

	@Test(priority = 46)
	public void applyPercentDecreaseEndingWith10() {
		Map<String, String> map = reuse.verifyRoundOffDecreaseAtHigher(CreateShopifyProduct.nameOfProduct,
				"percentage_decrease", "higherEndWith10", "endWith10");
		Assert.assertEquals(map.get("roundOffVal"), map.get("nearestVal"));
	}

	@Test(priority = 47)
	public void applyPercentDecreaseEndingWithPoint49() {
		Map<String, String> map = reuse.verifyRoundOffDecreaseAtHigher(CreateShopifyProduct.nameOfProduct,
				"percentage_decrease", "higherEndWith0.49", "endWithPoint49");
		Assert.assertEquals(map.get("roundOffVal"), map.get("nearestVal"));
	}

	@Test(priority = 48)
	public void applyPercentDecreaseEndingWithPoint99() {
		Map<String, String> map = reuse.verifyRoundOffDecreaseAtHigher(CreateShopifyProduct.nameOfProduct,
				"percentage_decrease", "higherEndWith0.99", "endWithPoint99");
		Assert.assertEquals(map.get("roundOffVal"), map.get("nearestVal"));
	}

	@Test(priority = 49)
	public void applyPercentDecreaseLowerEndingWithWholeNum() {
		Map<String, String> map = reuse.verifyRoundOffDecreaseAtLower(CreateShopifyProduct.nameOfProduct,
				"percentage_decrease", "lowerWholeNumber", "whole");
		Assert.assertEquals(map.get("roundOffVal"), map.get("nearestVal"));
	}

	@Test(priority = 50)
	public void applyPercentDecreaseLowerEndingWith9() {
		Map<String, String> map = reuse.verifyRoundOffDecreaseAtLower(CreateShopifyProduct.nameOfProduct,
				"percentage_decrease", "lowerEndWith9", "endWith9");
		Assert.assertEquals(map.get("roundOffVal"), map.get("nearestVal"));
	}

	@Test(priority = 51)
	public void applyPercentDecreaseLowerEndingWith10() {
		Map<String, String> map = reuse.verifyRoundOffDecreaseAtLower(CreateShopifyProduct.nameOfProduct,
				"percentage_decrease", "lowerEndWith10", "endWith10");
		Assert.assertEquals(map.get("roundOffVal"), map.get("nearestVal"));
	}

	@Test(priority = 52)
	public void applyPercentDecreaseLowerEndingWithPoint49() {
		Map<String, String> map = reuse.verifyRoundOffDecreaseAtLower(CreateShopifyProduct.nameOfProduct,
				"percentage_decrease", "lowerEndWith0.49", "endWithPoint49");
		Assert.assertEquals(map.get("roundOffVal"), map.get("nearestVal"));
	}

	@Test(priority = 53)
	public void applyPercentDecreaseLowerEndingWithPoint99() {
		Map<String, String> map = reuse.verifyRoundOffDecreaseAtLower(CreateShopifyProduct.nameOfProduct,
				"percentage_decrease", "lowerEndWith0.99", "endWithPoint99");
		Assert.assertEquals(map.get("roundOffVal"), map.get("nearestVal"));
	}

	@Test(priority = 54)
	public void testIfStartAndEndDateIsNotSelected() {
		ctp.verifyTemplateCanNotBeSavedIfStartAndEndDateIsNotSelected(CreateShopifyProduct.nameOfProduct);
	}

	@Test(priority = 55, dataProvider = "getTemplateData")
	public void testFulFillmentTypeIsSame(Map<String, String> input) {
		url = util.getPageURL();
		util.openAndSwitchToNewTab();
		create.createNewShopifyProduct();
		util.getWindoHandleByUrl(url);
		reuse.createNewTemplateUsingAdvanceSelection();
		ctp.verifyFulfilllmentTypeIsSameOnListing(CreateShopifyProduct.nameOfProduct);
		util.goToSection("settings");
		reuse.searchAndOpenEditTemplatePage(CreateShopifyProduct.nameOfProduct);
		reuse.selectFulfillmentType("fba");
		reuse.waitForMapValBtn();
		ctp.verifyFulfilllmentTypeIsSameOnListing(CreateShopifyProduct.nameOfProduct);
	}

	@Test(priority = 56, dataProvider = "getTemplateData")
	public void testFulFillmentTypeIsSameForClonedTemplate(Map<String, String> input) {
		util.goToSection("settings");
		reuse.searchAndCloneTemplate(CreateShopifyProduct.nameOfProduct);
		reuse.verifyFulfillmentTypeInTemplates("fba");
		reuse.clickOnSave();
		reuse.waitForMapValBtn();
		reuse.searchAndDeleteTemplate(CreateShopifyProduct.nameOfProduct);
		reuse.searchAndOpenEditTemplatePage("Copy of " + CreateShopifyProduct.nameOfProduct);
		reuse.addNewTemplateName(CreateShopifyProduct.nameOfProduct);
		reuse.clickOnSave();
		ctp.verifyFulfilllmentTypeIsSameOnListing(CreateShopifyProduct.nameOfProduct);
		util.goToSection("settings");
		reuse.searchAndOpenEditTemplatePage(CreateShopifyProduct.nameOfProduct);
		reuse.selectFulfillmentType("fbm");
		reuse.waitForMapValBtn();
		ctp.verifyFulfilllmentTypeIsSameOnListing(CreateShopifyProduct.nameOfProduct);
	}

}
