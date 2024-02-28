package com.ami.pageobjects;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;

/**
 * Project Name: Amazon MultiAccount 
 * Author: Yatindra Kinker 
 * Version: 
 * Reviewed By: Abhay Hayaran 
 * Date of Creation: December 30, 2022
 *  Modification History:
 * Date of change: 
 * Version: 
 * changed function:  
 * change description:
 * Modified By:
 */

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ami.resources.Utilities;

public class ProductEditPage extends ProductEditPageOR {
	Utilities util;
	String currentPageUrl = "";
	String val = "value";

	public ProductEditPage(Utilities util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
	}

	/**
	 * This method validates that on clicking "view on shopify link" it redirects to
	 * shopify page.
	 */
	public void redirectionToShopifyProductPage() {
		boolean isTrue = false;
		currentPageUrl = util.getPageURL();
		util.click(viewOnShopifyLink);
		util.goToChildWindow();

		if (!emailList.isEmpty() && (email.getText().equals(util.getConfigProperty("user_email")))) {
			util.click(email);

		}

		if (util.getPageURL().contains("products/")) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);

		try {
			util.getDriver().close();
			util.getWindoHandleByUrl(currentPageUrl);
		} catch (NoSuchWindowException e) {
			util.getWindoHandleByUrl(currentPageUrl);
		}
		util.switchToIFrame();
	}

	/**
	 * This method validates that on clicking "amazon listing" it redirects to
	 * shopify page.
	 */
	public void redirectionToShopifyLisingDoc() {
		boolean isTrue = false;
		util.click(amazonListingLink);
		util.goToChildWindow();

		if (util.getPageURL().contains("section=listings")) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);

		try {
			util.getDriver().close();
			util.getWindoHandleByUrl(currentPageUrl);
		} catch (NoSuchWindowException e) {
			util.getWindoHandleByUrl(currentPageUrl);
		}

		util.switchToIFrame();
	}

	/**
	 * This method clicks on back btn
	 */
	public void clickOnBackBtn() {
//		validating working of back button
		util.click(backBtnListing);
		util.hold(1);
	}

	public void closeAttentionModal() {
		if (!attention.isEmpty()) {
			util.click(closeBtn);
		}
	}

	public void searchProduct() {
		util.enterValue(searchInpField, CreateShopifyProduct.nameOfProduct);
		util.hold(10);
		util.pressEscape();
		util.click(searchInpField);
		util.waitUntilElementIsVisible(searchProductList.get(0));
		util.click(searchProductList.get(0));
		util.hold(5);
	}

	/**
	 * This method validates on clicking edit button the edit product page is open
	 * or not. TC_AM_PE_001 to TC_AM_PE_009
	 */
	public void validateRedirectionToEditPage(Map<String, String> input) {
		String totPages = "";
		currentPageUrl = util.getPageURL();
		util.clickOnListings(util.getConfigProperty("store"));
		util.hold(2);
//		validation that user is successfully redirected to listing page
		util.isElementDisplyedAndValidate(listingPageHeading);

		util.enterValue(searchInpField, CreateShopifyProduct.nameOfProduct);

		util.hold(10);
		util.pressEscape();
		util.click(searchInpField);
		util.waitUntilElementIsVisible(searchProductList.get(0));
		util.click(searchProductList.get(0));
		util.hold(5);
		util.click(kebabMenuListListing.get(0));
		util.click(editProduct);

//		validate back btn is visible
		util.isElementDisplyedAndValidate(backBtnListing);
//		validating user is redirected to product edit page
		util.isElementDisplyedAndValidate(productEditHeading);
//		product img section is displayed
		util.isElementDisplyedAndValidate(productImg);
//		view on shopify link is displayed
		util.isElementDisplyedAndValidate(viewOnShopifyLink);
//		validating on hovering mouse on view on shopiify link property is changing
		util.validateCursorProperty(viewOnShopifyLink, "pointer");
//		validating redirection to shopify product page
		redirectionToShopifyProductPage();
//		validating redirection to cedcommerce doc. listing page
		redirectionToShopifyLisingDoc();
		clickOnBackBtn();
		util.waitUntilElementIsVisible(listingPageHeading);
		util.isElementDisplyedAndValidate(listingPageHeading);
		util.hold(10);
	}

	/**
	 * This method validates that offerlisting and new listing radio btns are
	 * clickable. TC_AM_PE_014
	 */
	public void validateRadioBtnsAreClickable() {
		boolean isTrue = false;

		if (offerListingCheckbox.isSelected()) {
			util.click(newListingCheckbox);
			util.hold(2);
			util.click(proceedBtn);
			util.hold(1);
			util.waitUntilElementIsInvisible(progressBar);

			if (newListingCheckbox.isSelected()) {
				isTrue = true;
				Assert.assertTrue(isTrue);
			} else {
				Assert.assertTrue(isTrue);
			}

			util.click(offerListingCheckbox);
			util.hold(2);
			util.click(proceedBtn);
		} else if (newListingCheckbox.isSelected()) {
			isTrue = false;
			util.click(offerListingCheckbox);
			util.hold(2);
			util.click(proceedBtn);
			util.hold(1);
			util.waitUntilElementIsInvisible(progressBar);

			if (offerListingCheckbox.isSelected()) {
				isTrue = true;
				Assert.assertTrue(isTrue);
			} else {
				Assert.assertTrue(isTrue);
			}
			util.click(newListingCheckbox);
			util.hold(2);
			util.click(proceedBtn);
			util.hold(1);
			util.waitUntilElementIsInvisible(progressBar);
		}
	}

	public void redirectionToListing() {
		boolean isTrue = false;

		if (!newListingCheckbox.isSelected()) {
			util.click(newListingCheckbox);
			util.click(proceedBtn);
			util.hold(1);
			util.waitUntilElementIsInvisible(progressBar);

			if (newListingCheckbox.isSelected()) {
				isTrue = true;
				Assert.assertTrue(isTrue);
			} else {
				Assert.assertTrue(isTrue);
			}
		}
	}

	public void clickOnNewListing() {

		if (!newListingCheckbox.isSelected()) {
			util.click(newListingCheckbox);
			util.hold(1);
			util.click(proceedBtn);
			util.hold(1);
			util.waitUntilElementIsInvisible(progressBar);
		}
	}

	public void clickOnEditProduct() {
		util.waitUntilElementIsVisible(kebabMenuListListing.get(0), 30);

		if (kebabMenuListListing.get(0).getAttribute(ARIAEXPANDED).equals(FALSEVAL)) {
			util.click(kebabMenuListListing.get(0));
		}
		util.click(editProduct);
		util.waitUntilElementIsVisible(viewOnShopifyLink, 30);
	}

	public void clickOnSaveBtn() {
		util.switchToDefaultContent();
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

	public void contextualSaveBarIsDisplayed() {
		util.switchToDefaultContent();
		util.waitUntilElementIsVisible(discardBtn);
		util.hold(2);
		util.validateListIsNotEmpty(contextualSaveBar);
		util.isElementDisplyedAndValidate(discardBtn);
		util.isElementDisplyedAndValidate(saveBtn);
		util.switchToIFrame();
	}

	public void validateContextBarIsNotDisplayed() {
		util.validateListIsEmpty(contextualSaveBar);
	}

	public void discardChanges() {
		try {
			util.switchToDefaultContent();
			util.click(discardBtn);
			util.hold(1);
			util.click(discardChangesBtn);
		} catch (Exception e) {
			util.logWarn("Exception occured in discard changes.");
		}
	}

	public void validateDiscardBtnFunctionality() {
		clickOnDiscardBtn();
//		Verify that when click to Discard option then it shows confirmation modal
		util.switchToDefaultContent();
		util.waitUntilElementIsVisible(discardModal.get(0));
		util.validateListIsNotEmpty(discardModal);
//		Verify that in the confirmation dialog box two buttons are shown Continue Editing and Discard Changes
		util.isElementDisplyedAndValidate(discardChangesBtn);
		util.isElementDisplyedAndValidate(crossButton);
		util.isElementDisplyedAndValidate(continueEditBtn);
		util.hold(2);
		util.actionClick(crossButton);

		util.validateListIsNotEmpty(discardModal);

		util.click(discardBtn);
		util.click(discardChangesBtn);
		util.switchToIFrame();
		util.waitUntilElementIsVisible(backBtnListing);
		util.hold(2);
		util.switchToDefaultContent();
		util.validateListIsEmpty(contextualSaveBar);
		util.switchToIFrame();

	}

	/**
	 * This method validates that fields are visible on product edit page
	 */
	public void validateFieldsAreVisible() {

		WebElement[] elementArr = { offerListingCheckbox, newListingCheckbox, productEditHeading,
				setSameProductTitleForShopifyAndAmazon, setSameProductTitleForShopifyAndAmazonCheckBox,
				setCustomProductTitleForAmazonLabel, setCustomProductTitleForAmazonRadioBtn, descriptionSectionHeading,
				setSameProductDescriptionForShopifyAndAmazonCheckbox, setCustomProductDescriptionForAmazonCheckBox,
				handlingTimeHeading, useTheSameHandlingTimeAsInTheProductTemplate,
				useTheSameHandlingTimeAsInTheProductTemplateCheckBox,
				useTheSameHandlingTimeAsInTheProductTemplateInpField, setCustomHandlingTimeLabel,
				setCustomHandlingCheckbox, priceHeading, setSameProductPriceForAmazonCheckBox,
				setCustomPriceForAmazonCheckBox, inventoryHeading, setSameProductInventoryForShopifyAndAmazonCheckBox,
				setCustomProductInventoryForAmazonCheckBox, skuHeading, setSameSKUForShopifyAndAmazonCheckBox,
				setSameSKUForShopifyAndAmazonInputField, setCustomSKUCheckBox };

		for (WebElement ele : elementArr) {
			util.isElementDisplyedAndValidate(ele);
		}
	}

	/**
	 * This method validates that fields are visible on product edit page
	 */
	public void validateNewListingFieldsAreVisible() {

		WebElement[] elementArr = { offerListingCheckbox, newListingCheckbox, productEditHeading,
				setSameProductTitleForShopifyAndAmazon, setSameProductTitleForShopifyAndAmazonCheckBox,
				setCustomProductTitleForAmazonLabel, setCustomProductTitleForAmazonRadioBtn, descriptionSectionHeading,
				setSameProductDescriptionForShopifyAndAmazonCheckbox, setCustomProductDescriptionForAmazonCheckBox,
				handlingTimeHeading, useTheSameHandlingTimeAsInTheProductTemplate,
				useTheSameHandlingTimeAsInTheProductTemplateCheckBox,
				useTheSameHandlingTimeAsInTheProductTemplateInpField, setCustomHandlingTimeLabel,
				setCustomHandlingCheckbox, priceHeading, setSameProductPriceForAmazonCheckBox,
				setCustomPriceForAmazonCheckBox, inventoryHeading, setSameProductInventoryForShopifyAndAmazonCheckBox,
				setCustomProductInventoryForAmazonCheckBox, skuHeading, setSameSKUForShopifyAndAmazonCheckBox,
				setSameSKUForShopifyAndAmazonInputField, setCustomSKUCheckBox, barcodeSectionHeading, barcodeCheckBox,
				applyForBarCodeExemptionLink, imgSelectionHeading, setSameProductImgAsShownOnShopifyCheckBox,
				setCustomImgForAmazonCheckBox };

		for (WebElement ele : elementArr) {
			util.isElementDisplyedAndValidate(ele);
		}
	}

	public void validateNewListingRadioBtnIsSelectedByDefault() {
//		util.validateIsElementSelectedUsingAttribute(newListingCheckbox);
		util.validateIsElementSelected(newListingCheckbox);
	}

	/**
	 * This method validates the working of the listing section
	 * 
	 * @param input
	 */
	public void newListingTitleSection(Map<String, String> input) {
		boolean isTrue = false;
		String prevValue = "";
//		Verify that by default Set Shopify Title as Amazon Tilte option radio button is shown selected
		util.validateIsElementSelected(setSameProductTitleForShopifyAndAmazonCheckBox);
//		Verify that when click at the radio button Set custom Amazon title then this option is shown selected
		util.click(setCustomProductTitleForAmazonRadioBtn);
		util.isElementDisplyedAndValidate(setCustomTitleInpField);
		prevValue = util.extractValueByAttributes(setCustomTitleInpField, val);
		util.switchToDefaultContent();
//		Verify that when click at the custom option of radio button then Contextual Save Bar is shown => TC_AM_PE_026
		util.isElementDisplyedAndValidate(contextualSaveBar.get(0));
//		Verify that in contextual save bar two buttons are shown Discard and Save => TC_AM_PE_027
		util.isElementDisplyedAndValidate(saveBtn);
		util.isElementDisplyedAndValidate(discardBtn);
		util.switchToIFrame();
//		Verify that when enter the value in the textbox it shows in the textbox =>TC_AM_PE_025
		util.enterValue(setCustomTitleInpField, input.get("new_name"));
		util.hold(3);

		if (!util.extractValueByAttributes(setCustomTitleInpField, val).equals(prevValue)) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		} else {
			Assert.assertTrue(isTrue);
		}

		util.enterValue(setCustomTitleInpField, prevValue);
		validateDiscardBtnFunctionality();
		util.hold(2);
		util.click(setCustomProductTitleForLabel);
		clickOnDiscardBtn();
//		Verify that when click to the Continue editing then it shows the Custom option with the given value in the textbox =>TC_AM_PE_031
		clickOnContinueEditingBtn();
		isTrue = false;

		if (util.extractValueByAttributes(setCustomTitleInpField, val).equals(prevValue)) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		} else {
			Assert.assertTrue(isTrue);
		}

		util.switchToDefaultContent();
		util.waitUntilElementIsVisible(discardBtn);
		util.click(discardBtn);
		util.click(discardChangesBtn);
		util.switchToIFrame();
		util.validateIsElementSelected(setSameProductTitleForShopifyAndAmazonCheckBox);
	}

	/**
	 * This method validates the working of Description section
	 */
	public void newListingDescriptionSectionFunctionality() {
		util.validateIsElementSelected(setSameProductDescriptionForShopifyAndAmazonCheckbox);
		util.click(setCustomProductDescriptionForAmazonCheckBox);
		util.hold(1);
		validateDiscardBtnFunctionality();
		util.click(setCustomProductDescriptionForAmazonCheckBox);
		util.isElementDisplyedAndValidate(descriptionBoxToolBar);
		util.getDriver().switchTo().frame(iframeDescriptionBox);
		util.enterValue(descriptionBoxInputField, "description is entered");
		util.switchToDefaultContent();
		util.switchToIFrame();
		util.isElementDisplyedAndValidate(maxWordLimit);
//		Verify that when again click to the Set Shopify option through radio button without saving the Custom option then Contextual Save bar is not shown in edit section =>TC_AM_PE_049
		util.click(setSameProductDescriptionForShopifyAndAmazonCheckbox);
		validateContextBarIsNotDisplayed();
//		Verify that when given the text and click on Save button then the given value is shown and Set Custom option radio button is shown selected => TC_AM_PE_048
		util.click(setCustomProductDescriptionForAmazonCheckBox);
		clickOnSaveBtn();
		util.hold(5);
		util.validateIsElementSelected(setCustomProductDescriptionForAmazonCheckBox);
		util.click(setSameProductDescriptionForShopifyAndAmazonCheckbox);
	}

	/**
	 * This method validates the working of Time Handling Section TC_AM_PE_050 to
	 * TC_AM_PE_057
	 */
	public void validateTimeHandlingFunctionality(Map<String, String> input) {
		boolean isTrue = false;
//		Verify that Handling Time section is shown in edit section TC50
		util.isElementDisplyedAndValidate(handlingTimeHeading);
//		Verify that two options are shown under Handling time Set Assigned Product Template Value and Set Custom TC51
		util.isElementDisplyedAndValidate(useTheSameHandlingTimeAsInTheProductTemplate);
		util.isElementDisplyedAndValidate(useTheSameHandlingTimeAsInTheProductTemplateCheckBox);
		util.isElementDisplyedAndValidate(setCustomHandlingTimeLabel);
		util.isElementDisplyedAndValidate(setCustomHandlingTimeCheckBox);
//		Verify that by default Set Assigned Product Template Value option is shown selected TC52
		util.validateIsElementSelected(useTheSameHandlingTimeAsInTheProductTemplateCheckBox);
//		TC_AM_PE_053 => need a fresh product for validation
//		Verify that when click at any radio button then contextual save bar is shown TC_AM_PE_054
		util.click(setCustomHandlingTimeCheckBox);
		contextualSaveBarIsDisplayed();
//		Verify that by selecting set custom and not given the value and click on Save then the error validation message is shown TC_AM_PE_055
		util.enterValue(useTheSameHandlingTimeAsInTheProductTemplateInpField, "");
		clickOnSaveBtn();
		util.isElementDisplyedAndValidate(toastMsg);
		util.waitUntilElementIsInvisible(toastMsg);
//		Verify that in Handling Time Field at both the option if value is given above 30 then it shows the error validation TC_AM_PE_056
		util.enterValue(useTheSameHandlingTimeAsInTheProductTemplateInpField, input.get("value_above_30"));
		clickOnSaveBtn();
		util.isElementDisplyedAndValidate(toastMsg);
		util.waitUntilElementIsInvisible(toastMsg);
//		Verify that in handling time field at both the textbox only numeric value is accepted => TC_AM_PE_057
		util.enterValue(useTheSameHandlingTimeAsInTheProductTemplateInpField, input.get("mixed_value"));

		if (util.matchRegExpressionNumbersOnly(
				util.extractValueByAttributes(useTheSameHandlingTimeAsInTheProductTemplateInpField, val))) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);
	}

	/**
	 * This method validate working of barcode section.
	 */
	public void validateBarcodeSection() {
		boolean isTrue = false;
		util.click(backBtnListing);
		util.waitUntilElementIsVisible(kebabMenuListListing.get(0), 20);
		util.click(kebabMenuListListing.get(0));
		util.click(editProduct);
		util.hold(2);
		String preSkuVal = "";
		String postSKUVal = Utilities.amazonOrderId();
//		TC_AM_PE_058 => Verify that Barcode/GTIN Exemption field is shown
		util.isElementDisplyedAndValidate(barcodeSectionHeading);
//		TC_AM_PE_061 => Verify that Checkbox is shown under Barcode Exempt field
		util.isElementDisplyedAndValidate(barcodeCheckBox);
//		TC_AM_PE_062 => Verify that when click to the checkbox then it is shown as selected or barcode exempt enable
		util.validateElementIsClickable(barcodeCheckBox);
		util.enableButton(barcodeCheckBox);
//		TC_AM_PE_063,64,65 => Verify that Amazon Parent SKU field is shown only for the Variants Products

		try {
			if (util.isElementDisplyed(variants) && (util.isElementDisplyed(amazonParentSKUHeading)
					&& (util.isElementDisplyed(amazonParentSKUInputField)))) {
				Assert.assertTrue(true);

				preSkuVal = util.extractValueByAttributes(amazonParentSKUInputField, val);

//				TC_AM_PE_067 => Verify that when not given any value in the Amazon Parent SKU  then by default Product ID is shown in the textbox
				if (!preSkuVal.equals("")) {
					isTrue = true;
					Assert.assertTrue(isTrue);
				}
				Assert.assertTrue(isTrue);

				util.enterValue(amazonParentSKUInputField, postSKUVal);
				isTrue = false;
//				TC_AM_PE_066 => Verify that when given the value in the textbox then it shows in the textbox
				if (!preSkuVal.equals(postSKUVal)) {
					isTrue = true;
					Assert.assertTrue(isTrue);
				}
				Assert.assertTrue(isTrue);

			} else {
				Assert.assertTrue(false);
			}
		} catch (NoSuchElementException e) {
			Assert.assertTrue(true);
		}
	}

	/**
	 * This method validate working of Image Selection section. From TC_AM_PE_068 to
	 * TC_AM_PE_072
	 */
	public void validateImageSectionFunctionality() {
		util.click(backBtnListing);
		util.waitUntilElementIsVisible(kebabMenuListListing.get(0), 20);
		util.click(kebabMenuListListing.get(0));
		util.click(editProduct);
		util.waitUntilElementIsInvisible(progressBar);
		clickOnNewListing();
//		TC_AM_PE_068 => Verify that Image Selection field is shown 
		util.isElementDisplyedAndValidate(imgSelectionHeading);
//		TC_AM_PE_069, TC_AM_PE_070 => Verify that the two options are shown with the radio button
		util.isElementDisplyedAndValidate(setSameProductImgAsShownOnShopifyCheckBox);
		util.isElementDisplyedAndValidate(setSameProductImgAsShownOnShopifyLabel);
		util.isElementDisplyedAndValidate(setCustomImgForAmazonLabel);
		util.isElementDisplyedAndValidate(setCustomImgForAmazonCheckBox);
//		TC_AM_PE_071 => Verify that by default Set Shopify Images option are shown selected
		util.validateIsElementSelected(setSameProductImgAsShownOnShopifyCheckBox);
//		TC_AM_PE_072 => Verify that when click to the set custom then Choose Images by Clicking them field is shown
		util.click(setCustomImgForAmazonCheckBox);
		util.listIsNotEmpty(images);
		util.isElementDisplyedAndValidate(choseImgsHeading);
		util.isElementDisplyedAndValidate(knowMoreOnUploadingImgs);
		util.isElementDisplyedAndValidate(addMoreImgsBtn);
		util.click(knowMoreOnUploadingImgs);
//		util.listIsNotEmpty(imageUploadTutorialModal);
		util.click(closeBtnModal);
		util.click(addMoreImgsBtn);
		util.listIsNotEmpty(addMoreImgModal);
		util.click(closeBtnModal);
	}

	/**
	 * This method validates that price section functionality is working.
	 */
	public void validatePriceSectionFunctionality() {
		boolean isTrue = false;
//		validating fields are displayed
		util.click(backBtnListing);
		util.waitUntilElementIsVisible(kebabMenuListListing.get(0), 20);
		util.click(kebabMenuListListing.get(0));
		util.click(editProduct);
		util.waitUntilElementIsVisible(backBtnListing, 10);
		util.isElementDisplyedAndValidate(priceSectionHeading);
		util.isElementDisplyedAndValidate(setSamePriceForShopifyAndAmazonLabel);
		util.isElementDisplyedAndValidate(setSamePriceForShopifyAndAmazonCheckbox);
		util.isElementDisplyedAndValidate(setCustomPriceForAmazonLabel);
		util.isElementDisplyedAndValidate(setCustomPriceForAmazonCheckBox);

//		validating when set custom checkbox is checked if not selected then context bar is displayed and input field is displayed which accepts only whole numbers
		if (!util.isElementSelected(setCustomPriceForAmazonCheckBox)) {
			util.click(setCustomPriceForAmazonCheckBox);
			util.hold(2);
			util.isElementDisplyedAndValidate(setCustomPriceForAmazonInpField);
			util.enterValue(setCustomPriceForAmazonInpField, "abc125");

			if (util.matchRegExpressionNumbersOnly(
					util.extractValueByAttributes(setCustomPriceForAmazonInpField, val))) {
				isTrue = true;
				Assert.assertTrue(isTrue);
			}
			Assert.assertTrue(isTrue);

			util.switchToDefaultContent();
			util.listIsNotEmpty(contextualSaveBar);
			validateDiscardBtnFunctionality();
		}

	}

	/**
	 * This method validates that quantity section functionality is working.
	 */
	public void validateQuantitySectionFunctionality() {
		boolean isTrue = false;
//		validating fields are displayed
		util.isElementDisplyedAndValidate(quantitySectionHeading);
		util.isElementDisplyedAndValidate(setSameQuantityForShopifyAndAmazonLabel);
		util.isElementDisplyedAndValidate(setSameQuantityForShopifyAndAmazonCheckbox);
		util.isElementDisplyedAndValidate(setCustomQuantityForAmazonLabel);
		util.isElementDisplyedAndValidate(setCustomQuantityForAmazonCheckbox);

//		validating when set custom checkbox is checked if not selected then context bar is displayed and input field is displayed which accepts only whole numbers
		if (!util.isElementSelected(setCustomQuantityForAmazonCheckbox)) {
			util.click(setCustomQuantityForAmazonCheckbox);
			util.hold(2);
			util.isElementDisplyedAndValidate(setCustomQuantityForAmazonInpField);
			util.hold(2);
			JavascriptExecutor jsExecutor = (JavascriptExecutor) util.getDriver();
			jsExecutor.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
			util.click(setCustomPriceForAmazonCheckBox);
			util.enterValue(setCustomPriceForAmazonInpField, "abc125");

			if (util.matchRegExpressionNumbersOnly(
					util.extractValueByAttributes(setCustomQuantityForAmazonInpField, val))) {
				isTrue = true;
				Assert.assertTrue(isTrue);
			}
			Assert.assertTrue(isTrue);

			util.switchToDefaultContent();
			util.validateListIsNotEmpty(contextualSaveBar);
			validateDiscardBtnFunctionality();
		}
	}

	/**
	 * This method validates that sku section functionality is working.
	 */
	public void validateSKUSectionFunctionality() {
		boolean isTrue = false;
//		validating fields are displayed
		util.isElementDisplyedAndValidate(skuSectionHeading);
		util.isElementDisplyedAndValidate(setSameSKUForShopifyAndAmazonLabel);
		util.isElementDisplyedAndValidate(setSameSKUForShopifyAndAmazonCheckBox);
		util.isElementDisplyedAndValidate(setCustomSKUForAmazonLabel);
		util.isElementDisplyedAndValidate(setCustomSKUForAmazonCheckbox);

//		validating when set custom checkbox is checked if not selected then context bar is displayed and input field is displayed which accepts only whole numbers
		if (!util.isElementSelected(setCustomSKUForAmazonCheckbox)) {
			util.click(setCustomSKUForAmazonCheckbox);
			util.hold(2);
			util.isElementDisplyedAndValidate(setSKUForAmazonInpField);
			util.enterValue(setSKUForAmazonInpField, "1234567890!@#DSDF");

			if (util.matchAllRegExpression(util.extractValueByAttributes(setSKUForAmazonInpField, val))) {
				isTrue = true;
				Assert.assertTrue(isTrue);
			}
			Assert.assertTrue(isTrue);

			util.switchToDefaultContent();
			util.validateListIsNotEmpty(contextualSaveBar);
			validateDiscardBtnFunctionality();
		}
	}

	public void addAmazonCategoryIsDisplayed() {
		WebElement[] arr = { addAmzCategoryHeading, useSameCategoryLabel, useSameCategoryRadioBtn,
				setCustomCategoryLabel, setCustomCategoryRadioBtn };

		for (WebElement ele : arr) {
			util.isElementDisplyedAndValidate(ele);
		}

		if (!selectedCategory.isEmpty()) {
			Assert.assertTrue(true);
		}

	}

	/**
	 * 
	 */
	public void sameCategorySettingsIsDisabledIfNotTemplateIsAssign() {
		if (!selectedCategory.isEmpty()) {
			Assert.assertTrue(true);
		} else {
			util.validateIsElementDisabled(useSameCategoryRadioBtn);
		}

	}

	public void addAmazonCategoryAreDisplayed() {
		util.click(setCustomCategoryRadioBtn);
		util.isElementDisplyedAndValidate(addAmazonCategoryDropdown);

		do {
			util.click(selectCategoryList.get(0));
		} while (greenArrowBtn.isEmpty());
	}

	public void clickingOnBackBtnRemovesCategory() {

		do {
			util.click(removeAmzCategoryBackBtn);
		} while (!selectedCategoryFromNodeToLeaf.isEmpty());

		util.listIsEmpty(selectedCategoryFromNodeToLeaf);
	}

	public void searchCategory() {
		util.moveToElement(customCategoryInpField);
		util.click(customCategoryInpField);
		util.enterValue(customCategoryInpField, "baby");
		util.click(applyBtn);
		util.hold(3);
		util.isElementDisplyedAndValidate(categoriesFound);
		util.click(foundCategoryList.get(0));
		util.isElementDisplyedAndValidate(selectedCategoryFromNodeToLeaf.get(0));
		util.click(clearBtn);
		util.hold(2);
	}

	public void requiredAttributesAreVisible() {
		util.isElementDisplyedAndValidate(attributesSectionHeading);
		util.isElementDisplyedAndValidate(requiredAttributesHeading);
		util.click(setShopifyAttribute.get(0));
		util.hold(1);
		util.isElementDisplyedAndValidate(setCustomOption);
		util.isElementDisplyedAndValidate(setShopifyAttributeOption);
	}

	public void optionalAttributesSectionDisplayed() {
		util.isElementDisplyedAndValidate(optionalAttributeSection);
	}

	public void addOptionalAttribute() {
		util.click(addOptionalAttributesBtn);
		util.actionClick(amazonAttributeOptionalAttribute.get(0));
		util.hold(0);
		util.click(amazonAttributeOptionalAttributeOption.get(0));
		util.click(shopifyAttributeOptionalAttribute.get(0));
		util.click(setShopifyAttributeOption);
		util.click(setShopifyAttributesOptionalAttribute.get(0));
		util.click(setShopifyAttributesOptionalAttributeOptions.get(0));

	}

	public void setCustomOption() {
		util.click(setCustomOption);
		util.listIsNotEmpty(optionalAttributeCustomInputField);
	}

	public void setShopifyAttributeOption() {
		util.click(setShopifyAttribute.get(0));
		util.click(setShopifyAttributeOption);
		util.click(setShopifyAttributeDropDown.get(0));
		util.click(setShopifyAttributeDropDownVal.get(0));
	}

	public void variantThemeSection() {
		util.isElementDisplyedAndValidate(variationThemeSectionHeading);
		util.click(variationThemeAmazonRecommendationSelect);
		util.click(amazonAttributeOptionalAttributeOption.get(0));
	}

	/**
	 * Offer Listing starts here Offer listed product edit page
	 */

	public void offerListingEditPage() {
		util.click(backBtnListing);
		util.click(removeProd);

		do {
			util.click(nextPageBtn);
		} while (kebabMenuNotListed.isEmpty());

		util.click(kebabMenuNotListed.get(0));
		util.click(editProduct);
	}

	public void assignCategoryToProduct(String name) {
		String[] values = { "brand", "partnum123", "model123", "description", };
		util.enterValue(templateNameInputField, name);
		util.hold(2);
		util.click(appsForAndriodCategory);
		util.hold(5);
		util.click(communicationCategory);

		for (int i = 0; i < selectShopifyAttribute.size(); i++) {
			if (i == 0) {
				util.click(selectShopifyAttribute.get(i));
				util.hold(1);
				util.jsClick(setCustomShopifyAttribute);
				util.enterValue(setCustomInputField.get(i), values[0]);
//				Style(style_name)
			} else if (i == 1) {
				util.click(selectShopifyAttribute.get(i));
				util.hold(1);
				util.click(setCustomShopifyAttribute);
				util.enterValue(setCustomInputField.get(i), values[1]);
//				Bullet Point(bullet_point1)
			} else if (i == 2) {
				util.click(selectShopifyAttribute.get(i));
				util.hold(1);
				util.click(setCustomShopifyAttribute);
				util.enterValue(setCustomInputField.get(i), values[2]);
//				Bullet Point(bullet_point1)
			} else if (i == 3) {
				util.click(selectShopifyAttribute.get(i));
				util.hold(1);
				util.click(setCustomShopifyAttribute);
				util.enterValue(setCustomInputField.get(i), values[3]);
//				Bullet Point(bullet_point1)
			} else if (i == 4) {
				util.click(selectShopifyAttribute.get(i));
				util.hold(1);
				util.click(setShopifyAttributeInShopifyAttribute);
				util.click(setShopifyAttributeSelectorInput.get(0));
				util.click(productTypeOption);
//				Bullet Point(bullet_point1)
			} else if (i == 5) {
				util.click(selectShopifyAttribute.get(i));
				util.hold(1);
				util.click(setCustomShopifyAttribute);
				util.enterValue(setCustomInputField.get(4), "bullet point 1");
//				Gender(department_name)
			} else if (i == 6) {
				util.click(selectShopifyAttribute.get(i));
				util.hold(1);
				util.click(setShopifyAttributeInShopifyAttribute);
				util.click(setShopifyAttributeSelectorInput.get(1));
				util.click(handleOption);
//				Color (color_name)
			} else if (i == 7) {
				util.click(selectShopifyAttribute.get(i));
				util.hold(1);
				util.click(setCustomShopifyAttribute);
				util.enterValue(setCustomInputField.get(5), "black");
//				Color Map(color_map)
			} else if (i == 8) {
				util.click(selectShopifyAttribute.get(i));
				util.hold(1);
				util.click(setShopifyAttributeInShopifyAttribute);
				util.click(setShopifyAttributeSelectorInput.get(2));
				util.click(colorOption);
//				 Material Type(material_type1)
			} else if (i == 9) {
				util.click(selectShopifyAttribute.get(i));
				util.hold(1);
				util.click(setShopifyAttributeInShopifyAttribute);
				util.click(setShopifyAttributeSelectorInput.get(3));
				util.click(materialOption);
//				no. of wheels
			} else if (i == 10) {
				util.click(selectShopifyAttribute.get(i));
				util.hold(1);
				util.click(setCustomShopifyAttribute);
				util.enterValue(setCustomInputField.get(6), "4");
//				Manufactuter Contact
			} else if (i == 11) {
				util.click(selectShopifyAttribute.get(i));
				util.hold(1);
				util.click(setCustomShopifyAttribute);
				util.enterValue(setCustomInputField.get(7), "9876543120");
//				Item Length
			} else if (i == 12) {
				util.click(selectShopifyAttribute.get(i));
				util.hold(1);
				util.click(setCustomShopifyAttribute);
				util.enterValue(setCustomInputField.get(8), "1cm");
//				Item Width
			} else if (i == 13) {
				util.click(selectShopifyAttribute.get(i));
				util.hold(1);
				util.click(setCustomShopifyAttribute);
				util.enterValue(setCustomInputField.get(9), "1cm");
//				Item Height
			} else if (i == 14) {
				util.click(selectShopifyAttribute.get(i));
				util.hold(1);
				util.click(setCustomShopifyAttribute);
				util.enterValue(setCustomInputField.get(10), "1cm");
//				Volume capacity
			} else if (i == 15) {
				util.click(selectShopifyAttribute.get(i));
				util.hold(1);
				util.click(setCustomShopifyAttribute);
				util.enterValue(setCustomInputField.get(11), "1cm");
//				volume capacity unit of 
			} else if (i == 16) {
				util.click(selectShopifyAttribute.get(i));
				util.hold(1);
				util.click(setShopifyAttributeInShopifyAttribute);
				util.click(setShopifyAttributeSelectorInput.get(4));
				util.click(weightUnitOption);
//				unit count
			} else if (i == 17) {
				util.click(selectShopifyAttribute.get(i));
				util.hold(1);
				util.click(setCustomShopifyAttribute);
				util.enterValue(setCustomInputField.get(12), "1");
//				unit count type
			} else if (i == 18) {
				util.click(selectShopifyAttribute.get(i));
				util.hold(1);
				util.click(setShopifyAttributeInShopifyAttribute);
				util.click(setShopifyAttributeSelectorInput.get(5));
				util.click(qtyOption);
//				country of origin
			} else if (i == 19) {
				util.click(selectShopifyAttribute.get(i));
				util.hold(1);
				util.click(setAmazonRecommendationInShopifyAttribute);
				util.click(setAmazonRecommendationInput.get(0));
				util.click(indiaOption);
//				Warranty Type
			} else if (i == 20) {
				util.click(selectShopifyAttribute.get(i));
				util.hold(1);
				util.click(setAmazonRecommendationInShopifyAttribute);
				util.click(setAmazonRecommendationInput.get(1));
				util.click(manufacturer);
//				warranty description
			} else if (i == 21) {
				util.click(selectShopifyAttribute.get(i));
				util.hold(1);
				util.click(setCustomShopifyAttribute);
				util.enterValue(setCustomInputField.get(13), "6 months");
//				HSN code
			} else if (i == 22) {
				util.click(selectShopifyAttribute.get(i));
				util.hold(1);
				util.click(setCustomShopifyAttribute);
				util.enterValue(setCustomInputField.get(14), "HSN code");
//				Maximum retail price
			} else if (i == 23) {
				util.click(selectShopifyAttribute.get(i));
				util.hold(1);
				util.click(setCustomShopifyAttribute);
				util.enterValue(setCustomInputField.get(15), "100");
//				List price
			} else if (i == 24) {
				util.click(selectShopifyAttribute.get(i));
				util.hold(1);
				util.click(setCustomShopifyAttribute);
				util.enterValue(setCustomInputField.get(16), "118");
			}

		}
	}

	/**
	 * need to run this code to test
	 * @param name
	 */
	public void assignCategoryToProduct1(String name) {
		String[] values = { "brand", "partnum123", "model123", "description" };
		String[] customInputValues = { "brand", "bullet point 1", "black", "4", "9876543120", "1cm", "1cm", "1cm",
				"1cm", "1cm", "1", "1", "HSN code", "100", "118" };
		WebElement[] shopifyAttributeSelectors = { setCustomShopifyAttribute, setCustomShopifyAttribute,
				setCustomShopifyAttribute, setShopifyAttributeInShopifyAttribute, setCustomShopifyAttribute,
				setShopifyAttributeInShopifyAttribute, setCustomShopifyAttribute, setShopifyAttributeInShopifyAttribute,
				setShopifyAttributeInShopifyAttribute, setShopifyAttributeInShopifyAttribute, setCustomShopifyAttribute,
				setCustomShopifyAttribute, setCustomShopifyAttribute, setCustomShopifyAttribute,
				setCustomShopifyAttribute, setCustomShopifyAttribute, setShopifyAttributeInShopifyAttribute,
				setCustomShopifyAttribute, setShopifyAttributeInShopifyAttribute,
				setAmazonRecommendationInShopifyAttribute, setAmazonRecommendationInShopifyAttribute,
				setCustomShopifyAttribute, setCustomShopifyAttribute, setCustomShopifyAttribute,
				setCustomShopifyAttribute };
		WebElement[][] attributeSelectorInputs = { {}, {}, {}, { setShopifyAttributeSelectorInput.get(0) }, {},
				{ setShopifyAttributeSelectorInput.get(1) }, { setShopifyAttributeSelectorInput.get(2) }, {},
				{ setShopifyAttributeSelectorInput.get(3) }, {}, {}, {}, {}, {}, {},
				{ setShopifyAttributeSelectorInput.get(4) }, {}, {}, { setAmazonRecommendationInput.get(0) },
				{ setAmazonRecommendationInput.get(1) }, {}, {}, {}, {}, {} };

		util.enterValue(templateNameInputField, name);
		util.hold(2);
		util.click(appsForAndriodCategory);
		util.hold(5);
		util.click(communicationCategory);

		for (int i = 0; i < selectShopifyAttribute.size(); i++) {
			util.click(selectShopifyAttribute.get(i));
			util.hold(1);
			if (i < shopifyAttributeSelectors.length) {
				util.click(shopifyAttributeSelectors[i]);
				if (i < attributeSelectorInputs.length && attributeSelectorInputs[i].length > 0) {
					util.click(attributeSelectorInputs[i][0]);
				}
			}
			if (i < customInputValues.length) {
				util.enterValue(setCustomInputField.get(i), customInputValues[i]);
			}
		}
	}

	public void assignTemplate(String name) {
		assignCategoryToProduct(name);
		enterHandlingTime();
		manualSelection(name);
		waitToTemplateAssign();
	}

	public void enterHandlingTime() {
		util.enterValue(handlingTimeInputField, "5");
	}

	public void manualSelection(String name) {
		util.actionClick(manualRadioBtn);
		util.enterValue(manualSelectionInpField, name);
		util.click(browseManualProductsButton);
		waitToDisplayProduct();
		util.hold(1);
		util.click(checkBoxProductsManualSelectionDialogBox);
		util.click(continueBtnManualSelectionDialogBox);
		clickOnSaveBtn();
		util.hold(5);
	}

	public void waitToDisplayProduct() {
		do {
			util.hold(5);
			util.click(browseManualProductsButton);
		} while (!noProductFound.isEmpty());
	}

	public void waitToTemplateAssign() {
		do {
			util.hold(5);
			util.refreshPage();
			WebElement iframe = util.getDriver().findElement(By.name("app-iframe"));
			util.getDriver().switchTo().frame(iframe);
			util.hold(10);
			util.click(templateButton);
			util.actionClick(templateButton);
		} while (!templateAssignProgressBar.isEmpty());
	}
}
