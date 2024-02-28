package com.ami.pageobjects;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ami.resources.Utilities;

public class VariantEditPage extends ProductEditPageOR {
	Utilities util;
	String currentPageUrl = "";
	String title = "";
	String description = "";
	String valCustomBarcode = "";

	/**
	 * constructor to initialize driver.
	 * 
	 * @param util
	 */
	public VariantEditPage(Utilities util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
	}

	public void waitUntilPageIsLoading() {
		util.switchToDefaultContent();
		util.waitUntilElementIsInvisible(loadingPageBar);
		util.switchToIFrame();
	}

	public void clickOnSaveBtn() {
		util.switchToDefaultContent();
		util.hold(1);
		util.click(saveBtn);
		util.switchToIFrame();
	}

	public void clickOnDiscardBtn() {
		util.switchToDefaultContent();
		util.waitUntilElementIsVisible(discardBtn);
		util.click(discardBtn);
		util.switchToIFrame();
	}

	public void clickOnContinueEditingBtn() {
		util.switchToDefaultContent();
		util.waitUntilElementIsVisible(discardBtn);
		util.click(continueEditBtn);
		util.switchToIFrame();
	}

	public void variantEditBtnWorking() {
		currentPageUrl = util.getPageURL();
		util.clickOnListings(util.getConfigProperty("store"));
		util.hold(2);
//		validation that user is successfully redirected to listing page
		util.isElementDisplyedAndValidate(listingPageHeading);
		util.enterValue(searchInpField, CreateShopifyProduct.nameOfProduct);
//		util.enterValue(searchInpField, "#M9(Ons)hQ");
		util.hold(10);
		util.pressEscape();
		util.click(searchInpField);
		util.waitUntilElementIsVisible(searchProductList.get(0));
		util.click(searchProductList.get(0));
		util.hold(5);
		util.click(kebabMenuListListing.get(0));
		util.click(editProduct);
		util.click(editVariantsBtnList.get(0));
		util.isElementDisplyedAndValidate(variantTitleHeading);
	}

	public void newListingIsSelectedByDefault() {
		boolean isSelected = false;

		if (newListingCheckbox.isSelected()) {
			isSelected = true;
			Assert.assertTrue(isSelected);
		}
		Assert.assertTrue(isSelected);
	}

	public void offerListingRadioBtnWorking() {
		boolean isSelected = false;
		util.hold(10);
		util.click(offerListingCheckbox);
		util.hold(1);
		util.click(proceedBtn);
		waitUntilPageIsLoading();

		if (offerListingCheckbox.isSelected() && (!newListingCheckbox.isSelected())) {
			isSelected = true;
			Assert.assertTrue(isSelected);
		}
		Assert.assertTrue(isSelected);
	}

	public void newListingRadioBtnWorking() {
		boolean isSelected = false;
		util.click(newListingCheckbox);
		util.hold(1);
		util.click(proceedBtn);
		waitUntilPageIsLoading();

		if (newListingCheckbox.isSelected() && (!offerListingCheckbox.isSelected())) {
			isSelected = true;
			Assert.assertTrue(isSelected);
		}
		Assert.assertTrue(isSelected);
	}

	public void asteriskAreDisplayed() {
		util.isElementDisplyedAndValidate(asteriskVariantTitle);
		util.isElementDisplyedAndValidate(asteriskHandlingTime);
	}

	public void setSameProductTitleForShopifyAmazonIsSelectedByDefault() {
		util.validateIsElementSelected(setSameProductTitleForShopifyAndAmazonCheckBox);
	}

	public void variantTitleSectionRadioBtnAreWorking() {
		util.click(setCustomProductTitleForAmazonRadioBtn);
		util.validateIsElementSelected(setCustomProductTitleForAmazonRadioBtn);
		util.click(setSameProductTitleForShopifyAndAmazonCheckBox);
		util.validateIsElementSelected(setSameProductTitleForShopifyAndAmazonCheckBox);
	}

	public void setCustomTitleInputFieldBlank() {
		util.click(setCustomProductTitleForAmazonRadioBtn);
		title = util.extractValueByAttributes(setCustomTitleInpField, "value");
		util.clear(setCustomTitleInpField);
		clickOnSaveBtn();
		util.hold(1);
//		util.isElementDisplyedAndValidate(categorySettingsIsMissingErrToast);
		util.isElementDisplyedAndValidate(toastMsg);
	}

	public void setCustomTitle() {
		String randomName = util.randomString(7);
		util.enterValue(setCustomTitleInpField, randomName);
	}

	public void setCustomTitleWithSpace() {
		String randomName = " " + util.randomString(7) + " ";
		util.enterValue(setCustomTitleInpField, randomName);
	}

	public void setDefaultTitleSetting() {
		util.enterValue(setCustomTitleInpField, title);
		util.click(setSameProductTitleForShopifyAndAmazon);
	}

	public void setTheSameProductDescriptionForShopifyAndAmazon() {
		util.validateIsElementSelected(setSameProductDescriptionForShopifyAndAmazonCheckbox);
	}

	public void variantDescriptionSectionRadioBtnAreWorking() {
		util.click(setCustomProductDescriptionForAmazonCheckBox);
		util.validateIsElementSelected(setCustomProductDescriptionForAmazonCheckBox);
		util.click(setSameProductDescriptionForShopifyAndAmazonCheckbox);
		util.validateIsElementSelected(setSameProductDescriptionForShopifyAndAmazonCheckbox);
	}

	public void setCustomDescriptionInputFieldBlank() {
		util.click(setCustomProductDescriptionForAmazonCheckBox);
		util.switchToIFrame(iframeDescriptionBox);
		description = util.extractValueByAttributes(descriptionBoxInputField, "value");
		util.clear(descriptionBoxInputField);
		util.switchToDefaultContent();
		clickOnSaveBtn();
		util.hold(1);
//		util.isElementDisplyedAndValidate(categorySettingsIsMissingErrToast);
		util.isElementDisplyedAndValidate(toastMsg);
	}

	public void setCustomDescription() {
		String randomDescription = util.randomString(20);
		util.switchToIFrame(iframeDescriptionBox);
		util.enterValue(descriptionBoxInputField, randomDescription);
		util.switchToDefaultContent();
		clickOnSaveBtn();
		util.hold(1);
	}

	public void setCustomDescriptionWithSpaces() {
		String randomDescription = "  " + util.randomString(20) + "  ";
		util.switchToIFrame(iframeDescriptionBox);
		util.enterValue(descriptionBoxInputField, randomDescription);
		util.switchToDefaultContent();
		clickOnSaveBtn();
		util.hold(1);
	}

	public void handlingTimeSectionRadioBtnAreWorking() {
		util.click(setCustomHandlingCheckbox);
		util.validateIsElementSelected(setCustomHandlingCheckbox);
		util.click(useTheSameHandlingTimeAsInTheProductTemplateCheckBox);
		util.validateIsElementSelected(useTheSameHandlingTimeAsInTheProductTemplateCheckBox);
	}

	public void handlingInputFieldContainsTime() {
		boolean containsVal = false;

		if (!util.extractValueByAttributes(useTheSameHandlingTimeAsInTheProductTemplateInpField, "value").equals("")) {
			containsVal = true;
			Assert.assertTrue(containsVal);
		}
		Assert.assertTrue(containsVal);
	}

	public void byDefaultCustomInputFieldContainsTwo() {
		boolean defaultValIsOne = false;
		util.click(setCustomHandlingCheckbox);
		waitUntilPageIsLoading();
		String customHandlingInputFieldVal = util.extractValueByAttributes(customHandlingTimeInputField, "value");

		if (customHandlingInputFieldVal.equals("2")) {
			defaultValIsOne = true;
			Assert.assertTrue(defaultValIsOne);
		}
		Assert.assertTrue(defaultValIsOne);
	}

	public void customHandlingTimeCanBeAssigned() {
		util.click(setCustomHandlingCheckbox);
		util.enterValue(customHandlingTimeInputField, "5");
	}

	public void inputFieldAcceptsOnlyWholeNum() {
		boolean acceptsOnlyWholeNum = false;
		String customHandlingInputFieldVal = util.extractValueByAttributes(customHandlingTimeInputField, "value");

		if (util.matchRegExpressionNumbersOnly(customHandlingInputFieldVal)) {
			acceptsOnlyWholeNum = true;
			Assert.assertTrue(acceptsOnlyWholeNum);
		}
		Assert.assertTrue(acceptsOnlyWholeNum);
	}

	public void inputFieldAcceptsValueBetweenZeroToThirtyValid() {
		String[] validVal = { "29", "1" };

		for (String val : validVal) {
			util.enterValue(customHandlingTimeInputField, val);
			clickOnSaveBtn();
			util.hold(1);
//			util.isElementDisplyedAndValidate(savedSuccessfullyToastMsg);
			util.isElementDisplyedAndValidate(toastMsg);
			util.hold(5);
		}
	}

	public void inputFieldAcceptsValueBetweenZeroToThirtyInvalid() {
		String[] invalidVal = { "-1", "0", "31" };

		for (String val : invalidVal) {
			util.enterValue(customHandlingTimeInputField, val);
			clickOnSaveBtn();
			util.hold(1);
//			util.isElementDisplyedAndValidate(reqAttributesMissingErrToast);
			util.isElementDisplyedAndValidate(toastMsg);
			util.hold(5);
		}
	}

	public void samePriceForShopifyAndAmazonRadioBtnIsSelectedByDefault() {
		util.validateIsElementSelected(setSameProductPriceForAmazonCheckBox);
	}

	public void variantPriceSectionRadioBtnAreWorking() {
		util.click(setCustomPriceForAmazonCheckBox);
		util.validateIsElementSelected(setCustomPriceForAmazonCheckBox);
		util.click(setSameProductPriceForAmazonCheckBox);
		util.validateIsElementSelected(setSameProductPriceForAmazonCheckBox);
	}

	public void customPricAcceptsDecimalValues() {
		util.click(setCustomPriceForAmazonCheckBox);
		util.enterValue(setCustomPriceForAmazonInputField, "599.99");
		boolean acceptsOnlyDecimalNum = false;
		String customPriceInputFieldVal = util.extractValueByAttributes(setCustomPriceForAmazonInputField, "value");

		if (util.matchRegExpressionDecimalNumbers(customPriceInputFieldVal)) {
			acceptsOnlyDecimalNum = true;
			Assert.assertTrue(acceptsOnlyDecimalNum);
		}
		Assert.assertTrue(acceptsOnlyDecimalNum);
	}

	public void barcodeSectionDefaultSetting() {
		util.moveToElement(setSameBarcodeForShopifyAndAmazonCheckBox);
		util.validateIsElementSelected(setSameBarcodeForShopifyAndAmazonCheckBox);
	}

	public void barcodeSectionRadioBtnWorking() {
		util.click(setCustomBarcodeCheckBox);
		util.validateIsElementSelected(setCustomBarcodeCheckBox);
		util.click(setSameBarcodeForShopifyAndAmazonCheckBox);
		util.validateIsElementSelected(setSameBarcodeForShopifyAndAmazonCheckBox);
	}

	public void customBarcodeInputFieldIsBlank() {
		util.click(setCustomBarcodeCheckBox);
		valCustomBarcode = util.extractValueByAttributes(setCustomBarcodeInpField, "value");
		util.clear(setCustomBarcodeInpField);
		clickOnSaveBtn();
//		util.isElementDisplyedAndValidate(barCodeMissingToastErrMsg);
		util.isElementDisplyedAndValidate(toastMsg);
	}

	public void setCustomBarcode() {
		String customBarcode = util.randomString(5);
		util.enterValue(setCustomBarcodeInpField, customBarcode);
		clickOnSaveBtn();
		util.hold(1);
//		util.isElementDisplyedAndValidate(savedSuccessfullyToastMsg);
		util.isElementDisplyedAndValidate(toastMsg);
		waitUntilPageIsLoading();
	}

	public void setCustomBarcodeDecimalValue() {

		util.enterValue(setCustomBarcodeInpField, "123.52");
		clickOnSaveBtn();
		util.hold(1);
//		util.isElementDisplyedAndValidate(savedSuccessfullyToastMsg);
		util.isElementDisplyedAndValidate(toastMsg);
		waitUntilPageIsLoading();
	}

	public void inventorySectionDefaultSetting() {
		util.validateIsElementSelected(setSameQuantityForShopifyAndAmazonCheckbox);
	}

	public void inventorySectionRadioBtnAreWorking() {
		util.click(setCustomQuantityForAmazonCheckbox);
		util.validateIsElementSelected(setCustomQuantityForAmazonCheckbox);
		util.click(setSameQuantityForShopifyAndAmazonCheckbox);
		util.validateIsElementSelected(setSameQuantityForShopifyAndAmazonCheckbox);
	}

	public void customInventoryAcceptsWholeNum() {
		util.click(setCustomQuantityForAmazonCheckbox);
		util.enterValue(setCustomQuantityForAmazonInpField, "10.25");
		boolean acceptsOnlyWholeNum = false;
		String customInventoryInputFieldVal = util.extractValueByAttributes(setCustomQuantityForAmazonInpField,
				"value");

		if (util.matchRegExpressionNumbersOnly(customInventoryInputFieldVal)) {
			acceptsOnlyWholeNum = true;
			Assert.assertTrue(acceptsOnlyWholeNum);
		}
		Assert.assertTrue(acceptsOnlyWholeNum);
	}

	public void variantSKUSectionDefaultSetting() {
		util.validateIsElementSelected(setSameSKUForShopifyAndAmazonCheckBox);
	}

	public void variantSKUSectionRadioBtnWorking() {
		util.click(setCustomSKUForAmazonCheckbox);
		util.validateIsElementSelected(setCustomSKUForAmazonCheckbox);
		util.click(setSameSKUForShopifyAndAmazonCheckBox);
		util.validateIsElementSelected(setSameSKUForShopifyAndAmazonCheckBox);
	}

	public void setCustomSKU() {
		util.click(setCustomSKUForAmazonCheckbox);
		boolean acceptsAllVal = false;
		String customSKUInputFieldVal = util.extractValueByAttributes(setSKUForAmazonInpField, "value");

		if (util.matchAllRegExpression(customSKUInputFieldVal)) {
			acceptsAllVal = true;
			Assert.assertTrue(acceptsAllVal);
		}
		Assert.assertTrue(acceptsAllVal);
	}

	/**
	 * By default "Set Product Images as shown on Shopify" radio button is selected.
	 */
	public void variantImgSelectionDefaultSetting() {
		util.validateIsElementSelected(setSameProductImgAsShownOnShopifyCheckBox);
	}

	/**
	 * Both radio buttons are working properly.
	 */
	public void variantImgRadioBtnWorking() {
		util.click(setCustomImgForAmazonCheckBox);
		util.validateIsElementSelected(setCustomImgForAmazonCheckBox);
		util.click(setSameProductImgAsShownOnShopifyCheckBox);
		util.validateIsElementSelected(setSameProductImgAsShownOnShopifyCheckBox);
	}

	public void offerListing() {
		util.click(offerListingCheckbox);
		util.hold(1);
		util.click(proceedBtn);
		util.hold(10);
		util.click(shopifyAttribute);
		util.hold(1);
		util.click(setShopifyAttributeOption);
		util.click(setShopifyAttributeDropDown.get(0));
		util.click(setShopifyAttributeDropDownVal.get(0));
	}
	
	public void offerListingVariantTitleDefaultSetting() {
		setSameProductTitleForShopifyAmazonIsSelectedByDefault();
	}
	
	public void offerListingVariantTitleSectionRadioBtnAreWorking() {
		variantTitleSectionRadioBtnAreWorking();
	}
	
	public void offerListingVariantTitleSetCustomTitleInputFieldBlank() {
		setCustomTitleInputFieldBlank();
	}

	public void offerListingVariantSetCustomTitle() {
		setCustomTitle();
	}
	
	public void offerListingSetCustomTitleWithSpace() {
		setCustomTitleWithSpace();
		setDefaultTitleSetting();
	}
	
	public void offerListingSameProductDescriptionForShopifyAndAmazon() {
		setTheSameProductDescriptionForShopifyAndAmazon();
	}
	
	public void offerListingVariantDescriptionSectionRadioBtnAreWorking() {
		variantDescriptionSectionRadioBtnAreWorking();
	}
	
	public void offerListingSetCustomDescriptionInputFieldBlank() {
		setCustomDescriptionInputFieldBlank();
	}
	
	public void offerListingSetCustomDescription() {
		setCustomDescription();
	}
	
	public void offerListingSetCustomDescriptionWithSpaces() {
		setCustomDescriptionWithSpaces();
	}
	
	public void offerListingHandlingTimeSectionRadioBtnAreWorking() {
		handlingTimeSectionRadioBtnAreWorking();
	}

	public void offerListingHandlingInputFieldContainsTime() {
		handlingInputFieldContainsTime();
	}
	
	public void offerListingByDefaultCustomInputFieldContainsOne() {
		byDefaultCustomInputFieldContainsTwo();
	}
	
	public void offerListingCustomHandlingTimeCanBeAssigned() {
		customHandlingTimeCanBeAssigned();
	}
	
	public void offerListingInputFieldAcceptsOnlyWholeNum() {
		inputFieldAcceptsOnlyWholeNum();
	}
	
	public void offerListingInputFieldAcceptsValueBetweenZeroToThirtyValid() {
		inputFieldAcceptsValueBetweenZeroToThirtyValid();
	}
	
	public void offerListingInputFieldAcceptsValueBetweenZeroToThirtyInvalid() {
		inputFieldAcceptsValueBetweenZeroToThirtyInvalid();
	}
	
	public void offerListingVariantPriceSectionRadioBtnAreWorking() {
		samePriceForShopifyAndAmazonRadioBtnIsSelectedByDefault();
		variantPriceSectionRadioBtnAreWorking();
	}
	
	public void offerListingCustomPricAcceptsDecimalValues() {
		customPricAcceptsDecimalValues();
	}
	
	public void offerListingBarcodeSectionDefaultSetting() {
		barcodeSectionDefaultSetting();
	}
	
	public void offerListingBarcodeSectionRadioBtnWorking() {
		barcodeSectionRadioBtnWorking();
	}
	
	public void offerListingCustomBarcodeInputFieldIsBlank() {
		customBarcodeInputFieldIsBlank();
	}
	
	public void offerListingSetCustomBarcode() {
		setCustomBarcode();
	}
	
	public void offerListingSetCustomBarcodeDecimalValue() {
		setCustomBarcodeDecimalValue();
	}
	
	public void offerListingInventorySectionDefaultSetting() {
		inventorySectionDefaultSetting();
	}
	
	public void offerListingInventorySectionRadioBtnAreWorking() {
		inventorySectionRadioBtnAreWorking();
	}
	
	public void offerListingCustomInventoryAcceptsWholeNum() {
		customInventoryAcceptsWholeNum();
	}
	
	public void offerListingVariantSKUSectionDefaultSetting() {
		variantSKUSectionDefaultSetting();
	}
	
	public void offerListingVariantSKUSectionRadioBtnWorking() {
		variantSKUSectionRadioBtnWorking();
	}
	
	public void offerListingSetCustomSKU() {
		setCustomSKU();
	}
	
	public void offerListingVariantImgRadioBtnWorking() {
		variantImgSelectionDefaultSetting();
		variantImgRadioBtnWorking();
	}

}
