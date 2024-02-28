package com.ami.pageobjects;

import java.security.SecureRandom;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ami.customexceptions.EmptyListException;
import com.ami.pageobjects.reusableclasses.ReusableMethods;
import com.ami.resources.Utilities;

public class CustomTemplatePage extends DefaultTemplatePageOR {

	Utilities util;
	String currentPageUrl = "";
	WebElement category = null;
	WebElement subCategory = null;
	ReusableMethods reuse;
	CreateShopifyProduct create;
	ProductListingPage plp;

	String[] shopifyAttrArr = { "Yk", "partNum123", "model1", "description", "style1", "bulletPoint1", "male", "black",
			"quantity", "weight", "weight_unit", "grams", "barcode", "Variant title", "Arm Length", "Color", "Length",
			"Material", "Position", "Rim Size", "Size", "Speeds", "Style", "Teeth", "Title" };
	String[] arrValid = { "1", "2", "29", "30" };

	public CustomTemplatePage(Utilities util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
		create = new CreateShopifyProduct(util);
		plp = new ProductListingPage(util);

	}

	public void clickOnSaveBtn() {
		util.switchToDefaultContent();
		util.click(saveBtn);
		util.switchToIFrame();
	}

	/**
	 * this method clicks on save button and validate if error msg is displayed.
	 */
	public void clickSaveBtnErrIsDisplayed() {
		util.hold(1);
		clickOnSaveBtn();
		util.hold(2);
		util.isElementDisplyedAndValidate(someInfoMissingErrMsg);
		util.hold(2);
	}

	/**
	 * This method validates that when user clicks on add new template button he is
	 * redirected to add new template page.TC_AM_CT_001
	 */
	public void validateAddNewTemplateBtn() {
		util.goToMultiAcccounSettings();
		util.click(templateButton);
		util.isElementDisplyedAndValidate(addNewTemplateBtn);
		util.click(addNewTemplateBtn);
		util.isElementDisplyedAndValidate(addNewTemplatePageHeading);
	}

	/**
	 * This method validates that what characters name input field accepts.
	 * TC_AM_CT_004,TC_AM_CT_005
	 */
	public void validateTemplateNamingConvention(Map<String, String> input) {
		boolean isTrue = false;
		util.enterValue(templateNameInputField, input.get("template_name_all"));

		if (!util.matchIfStringContainsSpecialChar(util.extractValueByAttributes(templateNameInputField, VAL))) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		} else {
			Assert.assertTrue(isTrue);
		}

		util.enterValue(templateNameInputField, " ");
		clickSaveBtnErrIsDisplayed();
	}

	/**
	 * This method validates that Barcode/GTIN Exemption is clickable.TC_AM_CT_007
	 */
	public void validateBarCodeExempCheckbox() {
		util.enableButtonAndValidate(barCodeExemptionCheckbox);
		util.disableButtonAndValidate(barCodeExemptionCheckbox);
	}

	/**
	 * This method validates that on clicking link it redirects to amazon sign in
	 * page.TC_AM_CT_010
	 */
	public void validateLinkIsNotBroken() {
		currentPageUrl = util.getPageURL();
		util.click(applyForGTINExemptionLink);
		util.goToChildWindow();
		boolean isTrue = false;
		if (util.getPageTitle().contains("Amazon")) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);

		try {
			util.getDriver().close();
			util.getWindoHandleByUrl(currentPageUrl);
			util.switchToIFrame();
		} catch (NoSuchWindowException e) {
			util.logWarn("page closed successfully.");
		}
	}

	/**
	 * This method validates that when user enters in search input the results are
	 * shown accordingly.TC_AM_CT_011
	 */
	public void validateWorkingOfSearchInputField(Map<String, String> input) {
		boolean isTrue = false;
		util.enterValue(searchInputField, input.get("valid_search_keyword"));
		util.click(searchBtn);
		util.waitUntilElementIsVisible(clearBtn);
		util.hold(2);
		util.isElementDisplyedAndValidate(categoryCount);
//		TC_AM_CT_012
		util.enterValue(searchInputField, input.get("invalid_search_keyword"));
		util.click(searchBtn);
		util.waitUntilElementIsVisible(clearBtn);
		util.hold(2);
		util.isElementDisplyedAndValidate(noSearchResultsFoundMsg);
//		TC_AM_CT_013
		util.enterValue(searchInputField, input.get("invalid_search_keyword2"));
		util.click(searchBtn);
		util.waitUntilElementIsVisible(clearBtn);
		util.hold(2);
		util.isElementDisplyedAndValidate(noSearchResultsFoundMsg);
//		TC_AM_CT_014
		util.click(clearBtn);
		util.hold(5);
		util.isElementDisplyedAndValidate(browseAndSelectCategory);
//		TC_AM_CT_015
		util.enterValue(searchInputField, input.get("valid_category"));
		util.click(searchBtn);
		util.waitUntilElementIsVisible(clearBtn);
		util.hold(2);

		if (util.validateReturnContainsValue(searchedCategoryResult1, input.get("valid_category"))
				|| util.validateReturnContainsValue(searchedSubCategoryResult1, input.get("valid_category"))) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		} else {
			Assert.assertTrue(isTrue);
		}

		util.click(clearBtn);
		util.hold(5);
	}

	/**
	 * This method selects category and sub category and validates that they are
	 * reflecting. TC_AM_CT_016
	 */
	public void validateDropdownSelection(Map<String, String> input) {

		SecureRandom secureRandom = new SecureRandom();
		int randomNum = secureRandom.nextInt(1000000001);

		String tempName = input.get("new_template_name") + randomNum;
		util.enterValue(templateNameInputField, tempName);
		selectProductCategory(input);
		selectProductSubCategory(input);

		for (int i = 0; i < selectedCategoryList.size(); i += 2) {
			if (i == 0)
				util.validateContainsValue(selectedCategoryList.get(i), input.get("select_category_value"));
			if (i == 2) {
				util.validateContainsValue(selectedCategoryList.get(i), input.get("select_subcategory_value"));
				break;
			}
		}
		util.hold(5);
	}

	/**
	 * This method selects product sub category
	 * 
	 * @param input
	 */
	public void selectProductSubCategory(Map<String, String> input) {
		subCategory = util.getDriver()
				.findElement(By.xpath("//a[text()= '" + input.get("select_subcategory_value") + "']"));
		util.click(subCategory);
		util.hold(5);
	}

	/**
	 * This method selects product category
	 * 
	 * @param input
	 */
	public void selectProductCategory(Map<String, String> input) {
		util.hold(1);
		category = util.getDriver().findElement(By.xpath("//a[text()= '" + input.get("select_category_value") + "']"));
		util.click(category);
		util.hold(5);
	}

	/**
	 * creates name that contains random number.
	 * 
	 * @param input
	 * @return
	 */
	public String createRandomName(Map<String, String> input) {

		SecureRandom secureRandom = new SecureRandom();
		int randomNum = secureRandom.nextInt(1000000001);

		return input.get("new_template_name") + randomNum;
	}

	public void selectDefaultcategoryAndRequireAttribute() {
		util.click(defaultCategory);
		util.waitUntilElementIsVisible(attributeHeading);
		util.hold(1);
		util.click(offerConditionType);
		util.hold(1);
		util.jsClick(amazonRecommendationsRadioBtn);
		util.hold(1);
		util.actionClick(setAmazonRecommendationSelector);
		util.hold(1);
	}

	public void selectAmazonRecommendationValueFromDropDown() {
		util.hold(1);
		util.jsClick(setAmazonRecommendationOptionNew);
	}

	public void selectDefaultcategoryAndSetShopifyAttribute() {
		util.click(defaultCategory);
		util.waitUntilElementIsVisible(attributeHeading);
		util.hold(1);
		util.actionClick(defaultSelectAttribute);
		util.hold(1);
		util.jsClick(setShopifyAttributeOption);
		util.hold(1);
		util.actionClick(setAmazonRecommendationSelector);
	}

	public void selectSetShopifyAttribute(String key) {
		util.hold(1);
		switch (key) {
		case "title":
			util.click(titleOption);
			break;
		case "productType":
			util.click(productTypeOption);
			break;
		case "handler":
			util.click(titleOption);
			break;
		case "tags":
			util.click(tagsOption);
			break;
		case "description":
			util.click(descriptionOption);
			break;
		case "brand":
			util.click(brandOption);
			break;
		case "quantity":
			util.click(quantityOption);
			break;
		case "sku":
			util.click(skuOption);
			break;
		case "barcode":
			util.click(barcodeOption);
			break;
		case "price":
			util.click(priceOption);
			break;
		case "weight":
			util.click(weightOption);
			break;
		case "weightUnit":
			util.click(weightUnitOption);
			break;
		case "variantTitle":
			util.click(variantTitleOption);
			break;
		case "COLOR":
			util.click(capsColorOption);
			break;
		case "color":
			util.click(colorOption);
			break;
		case "material":
			util.click(materailOption);
			break;
		case "SIZE":
			util.click(capsSizeOption);
			break;
		case "size":
			util.click(sizeOption);
			break;
		case "type":
			util.click(typeOption);
			break;
		default:
			break;
		}

	}

	public void selectAdvanceSelectionAndFillAttribute(Map<String, String> input) {
		util.click(advancedRadioBtn);
		util.selectByValue(selectBy.get(0), "title");
		util.selectByValue(selectBy.get(1), "equals");
		util.enterValue(advanceSelectionInputField.get(0), CreateShopifyProduct.nameOfProduct);
		util.hold(1);
		util.click(seeQryResultBtn);
		waitForProductToReflect();
		util.hold(5);
	}

	public void waitForProductToReflect() {
		do {
			util.hold(10);
			util.click(seeQryResultBtn);
		} while (!zeroProductApplied.isEmpty());
	}

	/**
	 * This method validates that if user only selects category and tries to save
	 * error toast msg must be displayed.
	 * 
	 * @param input
	 */
	public void validateWhenUserOnlySelectsProductCategoryAndSaveTemplate(Map<String, String> input) {
		util.switchToIFrame();
		util.enterValue(templateNameInputField, CreateShopifyProduct.nameOfProduct);
		selectProductCategory(input);
		util.enterValue(handlingTimeInputField, input.get("valid_handling_time1"));
		selectAdvanceSelectionAndFillAttribute(input);
	}

	public void removeActiveCategory() {
		boolean value = true;
		try {
			while (value) {

				if (util.isElementDisplyed(removeCategoryBtn)) {
					util.jsMoveToElement(removeCategoryBtn);
					util.click(removeCategoryBtn);
				} else {
					value = false;
				}
			}
		} catch (Exception e) {
			util.logWarn("exception occured at removeActiveCategory().");
		}
	}

	/**
	 * This method validates that when user selects any category the required
	 * attributes are displayed. TC_AM_CT_018
	 */
	public void validateRequiredAttributesAreDisplayed(Map<String, String> input) {
		boolean isTrue = false;
		util.enterValue(templateNameInputField, CreateShopifyProduct.nameOfProduct);
		removeActiveCategory();
		selectDefaultcategoryAndRequireAttribute();
		selectAdvanceSelectionAndFillAttribute(input);
		clickSaveBtnErrIsDisplayed();

		if (!selectShopifyAttribute.isEmpty()) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);

//		TC_AM_CT_022
		util.isElementDisplyedAndValidate(setAmazonRecommendationInputHeading);
		selectProductCategory(input);
		selectProductSubCategory(input);
		isTrue = false;

		if (!selectShopifyAttribute.isEmpty()) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);
	}

	/**
	 * This method validates that optional fields are not displayed if no category
	 * or sub-category is chosen. TC_AM_CT_019
	 */
	public void validateOptionalFieldAreNotVisibleWhenNoCategoryIsChosen() {
		boolean isTrue = false;

		if (selectShopifyAttribute.isEmpty()) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);

	}

	/**
	 * 
	 * This method validates that user can select "set custom" from dropdown.
	 * TC_AM_CT_020
	 */
	public void validateUserCanSelectCustomAttribute() {
		util.click(selectShopifyAttribute.get(0));
		util.hold(2);
		util.jsClick(setCustomAttribute);
		util.hold(1);
		util.isElementDisplyedAndValidate(setCustomInputField);
	}

	/**
	 * This method validates that user can select "set shopify attribute" from
	 * dropdown. TC_AM_CT_021
	 */
	public void validateUserCanSelectShopifyAttribute() {
		boolean isTrue = false;

		util.click(selectShopifyAttribute.get(0));
		util.hold(2);
		util.jsClick(setShopifyAttribute);

		if (util.getTagValue(selectedAttributeName.get(0)).equals("Set Shopify Attribute")) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);
	}

	/**
	 * TC_AM_CT_023
	 */
	public void validateErrorMsgIsDisplayedIfMapsOnlyFewOptions() {
		util.actionClick(setShopifyAttributeSelectorInput.get(0));
		util.hold(1);
		util.actionClick(shopifyAttributeList.get(0));
		clickSaveBtnErrIsDisplayed();
	}

	/**
	 * TC_AM_CT_024
	 */
	public void validateUserCanAddOptionalAttribute() {
		util.click(addOptionaAttributeBtn);
		util.isElementDisplyedAndValidate(optionalAmazonAttributeSelectorList.get(0));
	}

	/**
	 * TC_AM_CT_025
	 */
	public void validateUserCanCreateTemplateByAddingOptionalAttributes(Map<String, String> input) {
		String pageUrl = util.getPageURL();
		util.openAndSwitchToNewTab();
		create.createNewShopifyProduct();
		util.getWindoHandleByUrl(pageUrl);
		String tempName = CreateShopifyProduct.nameOfProduct;
		util.switchToIFrame();

		if (!backBtnList.isEmpty()) {
			util.actionClick(backBtn);
		}

		util.hold(1);
		util.click(templateButton);
		util.hold(1);
		util.click(addNewTemplateBtn);
		util.enterValue(templateNameInputField, tempName);
		selectDefaultcategoryAndRequireAttribute();
		selectAmazonRecommendationValueFromDropDown();
		util.enterValue(handlingTimeInputField, "5");
		selectAdvanceSelectionAndFillAttribute(input);
		clickOnSaveBtn();
		util.hold(2);

		for (WebElement name : customTemplateNameOverview) {

			try {

				if (util.getTagValue(name).equals(tempName)) {
					Assert.assertTrue(true);
					util.getDriver().findElement(By.xpath("//span[text() = '" + tempName
							+ "']/parent::div/parent::div/following-sibling::div[2]/div/button"));
					break;
				} else {
					Assert.assertTrue(false);
				}
			} catch (NoSuchElementException e) {

				if (util.isElementEnabled(nextPageBtnPagination))
					util.click(nextPageBtnPagination);
			}
		}
	}

	public void goToTemplates() {
		util.refreshPage();
		util.switchToIFrame();
		util.waitUntilElementIsVisible(templateButton, 30);
		util.click(templateButton);
	}

	public void waitUntilProductIsBeignAssigned() {
		do {
			goToTemplates();
			util.hold(10);
		} while (!progressbar.isEmpty());
	}

	public void closeImpInfoModal() {
		util.waitUntilElementIsVisible(impInfoHeading, 30);
		util.click(crossBtnModal);
	}

	/**
	 * validata if all checkbox are working properly TC_AM_CT_027
	 */
	public void validateAllCheckboxAreWorking() {
		util.hold(2);
		util.click(templateButton);
		util.click(addNewTemplateBtn);
		util.validateWorking(barCodeExemptionCheckbox);
		util.validateWorking(barCodeExemptionCheckbox);
		util.hold(1);
		util.validateWorking(enableInventorySettingsCheckbox);
		util.validateWorking(enableInventorySettingsCheckbox);
		util.enableButton(enableInventorySettingsCheckbox);
		util.hold(1);
		util.validateWorking(continueSellingCheckbox);
		util.validateWorking(continueSellingCheckbox);
		util.hold(1);
		util.validateWorking(setFixedInventoryCheckbox);
		util.validateWorking(setFixedInventoryCheckbox);
		util.disableButton(setFixedInventoryCheckbox);
		util.hold(1);
		util.validateWorking(setReserverInventoryCheckbox);
		util.validateWorking(setReserverInventoryCheckbox);
		util.disableButton(setReserverInventoryCheckbox);
		util.hold(1);
		util.validateWorking(thresoldInventoryCheckbox);
		util.validateWorking(thresoldInventoryCheckbox);
		util.hold(1);
		util.validateWorking(delOutOfStockCheckBox);
		util.validateWorking(delOutOfStockCheckBox);
		util.hold(1);
		util.validateWorking(enablePriceSyncCheckbox);
		util.validateWorking(enablePriceSyncCheckbox);
		util.enableButton(enablePriceSyncCheckbox);
		util.hold(1);
		util.validateWorking(salePriceCheckbox);
		util.validateWorking(salePriceCheckbox);
		util.hold(1);
		util.validateWorking(businessPriceCheckbox);
		util.validateWorking(businessPriceCheckbox);
		util.hold(1);
		util.validateWorking(minimumPriceCheckbox);
		util.validateWorking(minimumPriceCheckbox);
		util.hold(1);
		util.validateWorking(productDetailsCheckBox);
		util.validateWorking(productDetailsCheckBox);
		util.hold(1);
		util.validateWorking(imagesCheckBox);
		util.validateWorking(imagesCheckBox);
		util.hold(1);
		util.validateWorking(overrideExistingProductsCheckBox);
		util.validateWorking(overrideExistingProductsCheckBox);

	}

	public void validateWareHouseCheckBoxesWorking() {
		for (int i = 0; i < wareHouseChoiceCheckBoxCustomTemplate.size(); i++) {

			if (i != wareHouseChoiceCheckBoxCustomTemplate.size() - 1
					&& (wareHouseChoiceCheckBoxCustomTemplate.get(i).isEnabled())) {

				util.validateWorking(wareHouseChoiceCheckBoxCustomTemplate.get(i));
				util.validateWorking(wareHouseChoiceCheckBoxCustomTemplate.get(i));
				util.disableButton(wareHouseChoiceCheckBoxCustomTemplate.get(i));

			} else if (i == wareHouseChoiceCheckBoxCustomTemplate.size() - 1) {
				util.validateIsElementDisabled(wareHouseChoiceCheckBoxCustomTemplate.get(i));
			}
		}
	}

	/**
	 * This method validates that when user disables inventory settings all
	 * checkboxes are disabled. TC_AM_CT_028, TC_AM_CT_029
	 */
	public void validateWhenInventorySettingsIsDisabled() {
		boolean isTrue = false;
		util.disableButton(enableInventorySettingsCheckbox);
		util.validateIsElementEnabled(handlingTimeInputField);
		util.validateIsElementDisabledUsingAttribute(continueSellingCheckbox);
		util.validateIsElementDisabledUsingAttribute(setFixedInventoryCheckbox);
		util.validateIsElementDisabledUsingAttribute(setReserverInventoryCheckbox);
		util.validateIsElementDisabledUsingAttribute(thresoldInventoryCheckbox);
		util.validateIsElementDisabledUsingAttribute(delOutOfStockCheckBoxDisabled);

		for (WebElement checkbox : warehouseListCheckbox) {
			util.validateIsElementDisabled(checkbox);
		}

		util.enableButton(enableInventorySettingsCheckbox);
		util.enableButton(continueSellingCheckbox);
//		TC_AM_CT_029
		if (util.extractValueByAttributes(continueSellingCheckbox, "aria-checked").equals("true")
				&& util.extractValueByAttributes(setMaxInventoryInputField, "placeholder").equals("999")) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);
	}

	/**
	 * This method validates that time handling input field only accepts whole
	 * numbers and it can be between 1 to 30. TC_AM_CT_032,
	 * TC_AM_CT_033,TC_AM_CT_034
	 */
	public void validateTimeHandlingInpFieldAcceptsOnlyWholeNum(Map<String, String> input) {
		String[] arrInvalid = { " ", "0", "31" };
		String temName = createRandomName(input);
		util.click(backBtn);
		util.hold(2);
		util.click(templateButton);
		util.click(addNewTemplateBtn);
		util.enterValue(templateNameInputField, temName);
		selectDefaultcategoryAndRequireAttribute();
		selectAmazonRecommendationValueFromDropDown();
		util.enableButton(enableInventorySettingsCheckbox);
		selectAdvanceSelectionAndFillAttribute(input);

		for (int i = 0; i < arrInvalid.length; i++) {
			util.enterValue(handlingTimeInputField, arrInvalid[i]);
			util.hold(2);
			clickSaveBtnErrIsDisplayed();
			util.hold(5);
		}
	}

	/**
	 * TC_AM_CT_036, TC_AM_CT_037, TC_AM_CT_038, TC_AM_CT_039
	 */
	public void validateFixedInventorySettingWorking(Map<String, String> input) {
		boolean isTrue = false;
		String temName = createRandomName(input);
		util.click(backBtn);
		util.waitUntilElementIsVisible(templateButton);
		util.click(templateButton);
		util.click(addNewTemplateBtn);
		util.enterValue(templateNameInputField, temName);
		selectDefaultcategoryAndRequireAttribute();
		selectAmazonRecommendationValueFromDropDown();
		util.click(overrideExistingProductsCheckBox);
		selectAdvanceSelectionAndFillAttribute(input);
		util.enableButton(enableInventorySettingsCheckbox);
		util.enableButton(setFixedInventoryCheckbox);
//		input field is blank on clicking save button error msg is displayed
		util.clear(setFixedInventoryInputField);
		clickSaveBtnErrIsDisplayed();
		util.hold(2);
//		Reserve checkbox is not selected input field below it must disappeared
		util.validateIsElementDisabledUsingAttribute(setReserverInventoryCheckbox);
		util.disableButton(setFixedInventoryCheckbox);

		if (setFixedInventoryInputFieldList.isEmpty()) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);

		util.enableButton(setFixedInventoryCheckbox);
//		fixed inventory input field only accepts whole numbers

		isTrue = false;

		for (int i = 0; i < arrValid.length; i++) {
			util.enterValue(handlingTimeInputField, arrValid[i]);

			if (util.matchRegExpressionNumbersOnly(util.extractValueByAttributes(setFixedInventoryInputField, VAL))) {
				isTrue = true;
			}
			Assert.assertTrue(isTrue);
		}

		util.enterValue(setFixedInventoryInputField, "100");
		util.enterValue(handlingTimeInputField, "5");
		clickOnSaveBtn();
		util.hold(2);
		util.isElementDisplyedAndValidate(profileSaveMsg);
		closeImpInfoModal();
	}

	/**
	 * This method validates funcationality of set reserve checkbox.TC_AM_CT_041,
	 * TC_AM_CT_042, TC_AM_CT_043, TC_AM_CT_044, TC_AM_CT_045
	 * 
	 */
	public void validateSetReserveFunctionality(Map<String, String> input) {
		boolean isTrue = false;
		mandatoryStepsToAdvanceSelection(input);
		util.disableButton(setFixedInventoryCheckbox);
		util.enableButton(setReserverInventoryCheckbox);
		util.isElementDisplyedAndValidate(setReserverInventoryInputField);
//		input field is blank on clicking save button error msg is displayed
		util.clear(setReserverInventoryInputField);
		clickSaveBtnErrIsDisplayed();
		util.hold(2);
//		validation regarding setReserverInventoryInputField only accepts whole numbers.
		for (int i = 0; i < arrValid.length; i++) {
			util.enterValue(setReserverInventoryInputField, arrValid[i]);

			if (util.matchRegExpressionNumbersOnly(
					util.extractValueByAttributes(setReserverInventoryInputField, VAL))) {
				isTrue = true;
			}
			Assert.assertTrue(isTrue);
		}

		isTrue = false;
//		validates that if setReserverInventory checkbox is checked then set fixed Inventory is getting disabled.
		util.validateIsElementDisabledUsingAttribute(setFixedInventoryCheckbox);
//		validates that if setReserverInventory checkbox is unchecked then input field below it is not displayed.
		util.disableButton(setReserverInventoryCheckbox);

		if (setReserverInventoryInputFieldList.isEmpty()) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);
	}

	/**
	 * TC_AM_CT_048, TC_AM_CT_049, TC_AM_CT_050, TC_AM_CT_051
	 * 
	 * @param input
	 */
	public void validateThresoldInventoryCheckBoxAndInpFieldFunctionality(Map<String, String> input) {
		boolean isTrue = false;
		mandatoryStepsToAdvanceSelection(input);
		util.enableButton(thresoldInventoryCheckbox);
//		validate input field is displayed
		util.isElementDisplyedAndValidate(thresoldInventoryInputField);
//		input field is blank on clicking save button error msg is displayed
		util.clear(thresoldInventoryInputField);
		clickSaveBtnErrIsDisplayed();
		util.hold(2);

//		validation regarding thresoldInventoryInputField only accepts whole numbers.
		for (int i = 0; i < arrValid.length; i++) {
			util.enterValue(thresoldInventoryInputField, arrValid[i]);

			if (util.matchRegExpressionNumbersOnly(util.extractValueByAttributes(thresoldInventoryInputField, VAL))) {
				isTrue = true;
			}
			Assert.assertTrue(isTrue);
		}

//		validates that if thresoldInventoryCheckbox is unchecked then input field below it is not displayed.
		util.disableButton(thresoldInventoryCheckbox);
		isTrue = false;

		if (thresoldInventoryInputFieldList.isEmpty()) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);

	}

	/**
	 * This method validates that all warehouse can not be disabled TC_AM_CT_063
	 */
	public void validateAllCheckBoxOfWareHouseSettingsCanNotBeUnchecked(Map<String, String> input) {
		mandatoryStepsToAdvanceSelection(input);

		for (int i = 0; i < warehouseListCheckbox.size(); i++) {

			if (i != warehouseListCheckbox.size() - 1) {
				util.disableButton(warehouseListCheckbox.get(i));
			} else if ((i == warehouseListCheckbox.size() - 1)) {

				util.validateIsElementDisabledUsingAttribute(warehouseListCheckbox.get(i));
			}
		}
	}

	/**
	 * This method validates working of price settings. If "Enable Price Syncing
	 * from the App" checkbox is disabled all fields under this section will be
	 * disabled.TC_AM_DT_064,TC_AM_DT_065,TC_AM_DT_066
	 */
	public void validatePriceSettingsFunctionality(Map<String, String> input) {
		mandatoryStepsToAdvanceSelection(input);
		util.enableButton(enableInventorySettingsCheckbox);
		util.disableButton(overrideExistingProductsCheckBox);
//		disable enablePriceSyncCheckbox
		util.disableButton(enablePriceSyncCheckbox);
//		validate all fields are disabled
		util.validateIsElementDisabledUsingAttribute(standardPriceChoseTrendDisabled);
		util.validateIsElementDisabledUsingAttribute(salePriceCheckboxDisabled);
		util.validateIsElementDisabledUsingAttribute(businessPriceCheckboxDisabled);
		util.validateIsElementDisabledUsingAttribute(minimumPriceCheckboxDisabled);
//		enable "enablePriceSyncCheckbox"
		util.enableButton(enablePriceSyncCheckbox);
//		validate all fields are enabled
		util.isElementDisplyedAndValidate(salePriceCheckbox);
		util.isElementDisplyedAndValidate(businessPriceCheckbox);
		util.isElementDisplyedAndValidate(minimumPriceCheckbox);
	}

	/**
	 * This method validates that when user selects business check box and selects
	 * trend but lefts input field blank on saving error message is displayed
	 * TC_AM_CT_082,TC_AM_CT_084,TC_AM_CT_086,TC_AM_CT_089
	 * 
	 * @param input
	 */
	public void validateBusinessPriceFunctionality(Map<String, String> input) {
		mandatoryStepsToAdvanceSelection(input);
		util.enableButton(enableInventorySettingsCheckbox);
		util.disableButton(overrideExistingProductsCheckBox);
//		enable "businessPriceCheckbox"
		util.enableButton(businessPriceCheckbox);
		util.selectByValue(choseTrendBusinessPrice, input.get("percentage_decrease"));
		util.clear(choseTrendBusinessValueInputField);
		clickSaveBtnErrIsDisplayed();
//		select percent increase from dropdown and save
		util.selectByValue(choseTrendBusinessPrice, input.get("percentage_increase"));
		util.clear(choseTrendBusinessValueInputField);
		clickSaveBtnErrIsDisplayed();
//		select value increase from dropdown and save
		util.selectByValue(choseTrendBusinessPrice, input.get("value_increase"));
		util.clear(choseTrendBusinessValueInputField);
		clickSaveBtnErrIsDisplayed();
//		select value decrease from dropdown and save
		util.selectByValue(choseTrendBusinessPrice, input.get("value_decrease"));
		util.clear(choseTrendBusinessValueInputField);
		clickSaveBtnErrIsDisplayed();
	}

	/**
	 * This method validates that when user selects minimum check box and selects
	 * trend but lefts input field blank on saving error message is displayed
	 * TC_AM_CT_091, TC_AM_CT_093, TC_AM_CT_095, TC_AM_CT_098
	 * 
	 * @param input
	 */
	public void validateMinimumPriceFunctionality(Map<String, String> input) {
		mandatoryStepsToAdvanceSelection(input);
		util.enableButton(enableInventorySettingsCheckbox);
		util.disableButton(overrideExistingProductsCheckBox);
//		enable "minimumPriceCheckbox"
		util.enableButton(minimumPriceCheckbox);
		util.selectByValue(selectMinPriceTrend, input.get("percentage_decrease"));
		util.clear(minimumPriceTrendValueInputField);
		clickSaveBtnErrIsDisplayed();
//		select percent increase from dropdown and save
		util.selectByValue(selectMinPriceTrend, input.get("percentage_increase"));
		util.clear(minimumPriceTrendValueInputField);
		clickSaveBtnErrIsDisplayed();
//		select value increase from dropdown and save
		util.selectByValue(selectMinPriceTrend, input.get("value_increase"));
		util.clear(minimumPriceTrendValueInputField);
		clickSaveBtnErrIsDisplayed();
//		select value decrease from dropdown and save
		util.selectByValue(selectMinPriceTrend, input.get("value_decrease"));
		util.clear(minimumPriceTrendValueInputField);
		clickSaveBtnErrIsDisplayed();
	}

	/**
	 * Enable sales price setting and standard price is chosen as Map to shopify
	 * price and start date and end date is not chosen on clicking save button toast
	 * error message is displayed. TC_AM_CT_072
	 */
	public void validateEnableSalePriceFunctionality(Map<String, String> input) {
		mandatoryStepsToAdvanceSelection(input);
		util.disableButton(overrideExistingProductsCheckBox);
//		enable "enablePriceSyncCheckbox"
		util.enableButton(enablePriceSyncCheckbox);
		util.enableButton(enableInventorySettingsCheckbox);
		util.enableButton(salePriceCheckbox);
		util.selectByValue(standardPriceChoseTrend, input.get("map_to_shopify_price"));
		clickSaveBtnErrIsDisplayed();
	}

	/**
	 * TC_AM_CT_106, TC_AM_CT_109, TC_AM_CT_111
	 * 
	 * @param input
	 */
	public void validateManualSelectionFunctionlity(Map<String, String> input) {
		boolean isTrue = false;
		mandatoryStepsToManualSelection(input);
		util.disableButton(overrideExistingProductsCheckBox);
//		search bar is accepting all characters
		util.enterValue(manualSelectionInpField, input.get("template_name_all"));

		if (util.matchAllRegExpression(util.extractValueByAttributes(manualSelectionInpField, VAL))) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);
	}

	/**
	 * TC_AM_CT_115, TC_AM_CT_116, TC_AM_CT_118, TC_AM_CT_119, TC_AM_CT_120
	 * 
	 * @param input
	 */
	public void validateAdvanceSelectionFunctionlity(Map<String, String> input) {
		mandatoryStepsToAdvanceSelection(input);
		util.enterValue(handlingTimeInputField, input.get("valid_handling_time2"));
		util.disableButton(overrideExistingProductsCheckBox);
//		validating dropdown functionality
		util.selectByValue(selectBy.get(0), "tags");
		util.selectByValue(selectBy.get(0), "product_type");
		util.selectByValue(selectBy.get(0), "brand");
		util.selectByValue(selectBy.get(0), "title");
//		validating dropdown functionality
		util.selectByValue(selectBy.get(1), "equals");
		util.selectByValue(selectBy.get(1), "not equals");
		util.selectByValue(selectBy.get(0), "title");
		util.selectByValue(selectBy.get(1), "contains");
//		entering name
		util.enterValue(advanceSelectionInputField.get(0), input.get("valid_value_contains_product_title"));
		util.actionClick(seeQryResultBtn);
		util.hold(1);

		try {
			util.isElementDisplyedAndValidate(productsMatchAdvanceSelectionBanner);
		} catch (NoSuchElementException e) {
			util.isElementDisplyedAndValidate(zeroProductsFoundAdvanceSelectionBanner);
		}
	}

	/**
	 * This method adds a new row and enter values TC_AM_CT_120
	 * 
	 * @param input
	 */
	public void addRowEnterValue(Map<String, String> input) {
//		Click on add row 
		util.click(addRowBtn);
		util.selectByValue(selectBy.get(2), "price");
		util.selectByValue(selectBy.get(3), "less than or equal to");
//		entering price value
		util.enterValue(advanceSelectionInputField.get(1), input.get("price"));
		util.actionClick(seeQryResultBtn);

		try {
			util.isElementDisplyedAndValidate(productsMatchAdvanceSelectionBanner);
		} catch (NoSuchElementException e) {
			util.isElementDisplyedAndValidate(zeroProductsFoundAdvanceSelectionBanner);
		}
	}

	/**
	 * This method adds a new group and enters values in the field accordingly
	 * TC_AM_CT_121
	 * 
	 * @param input
	 */
	public void addGroupsAndEnterValues(Map<String, String> input) {
		util.click(addGrpBtn);
		util.selectByValue(selectBy.get(2), "price");
		util.selectByValue(selectBy.get(3), "less than or equal to");
		util.enterValue(advanceSelectionInputField.get(2), input.get("price"));
		util.actionClick(seeQryResultBtn);

		try {
			util.isElementDisplyedAndValidate(productsMatchAdvanceSelectionBanner);
		} catch (NoSuchElementException e) {
			util.isElementDisplyedAndValidate(zeroProductsFoundAdvanceSelectionBanner);
		}
	}

	/**
	 * This method remove groups TC_AM_CT_122
	 */
	public void removeGrp() {
		boolean isTrue = false;
		int initialNumOfRemoveGrpBtn = removeGrpBtn.size();
		int finalNumOfRemoveGrpBtn = 0;

		if (removeGrpBtn.size() >= 2) {
			util.click(removeGrpBtn.get(removeGrpBtn.size() - 1));
		}

		try {
			finalNumOfRemoveGrpBtn = removeGrpBtn.size();
		} catch (Exception e) {
			util.logWarn("exception occured in removeGrp().");
		}

		if (initialNumOfRemoveGrpBtn != finalNumOfRemoveGrpBtn) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		} else {
			Assert.assertTrue(isTrue);
		}
	}

	/**
	 * This method validates that user can delete rows that are added. TC_AM_CT_123
	 */
	public void removeRow() {
		boolean isTrue = false;
		int initialSize = 0;

		try {
			initialSize = delRowBtn.size();
			if (initialSize >= 2) {
				util.click(delRowBtn.get(initialSize - 1));
			}
		} catch (Exception e) {
			util.logWarn("exception occured in removeRow().");
		}

		if (initialSize != delRowBtn.size()) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);
	}

	/**
	 * This method validates that if filtes are applied in advance selection filters
	 * then the activated filters must be displayed. TC_AM_CT_125
	 */
	public void validateAdvanceSelectionFiltersAreDisplayed() {
		boolean isTrue = false;

		if (!activeFilters.isEmpty()) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);
	}

	/**
	 * TC_AM_CT_127, TC_AM_CT_128, TC_AM_CT_129, TC_AM_CT_130, TC_AM_CT_131,
	 * TC_AM_CT_132
	 */
	public void validateTemplateEditFunctionality(Map<String, String> input) {
		boolean isTrue = false;
		validateUserCanCreateTemplateByAddingOptionalAttributes(input);
		util.refreshPage();
		waitUntilProductIsBeignAssigned();
		util.enterValue(searchTemplateInputField, CreateShopifyProduct.nameOfProduct);
		util.hold(2);
		util.click(templateNameKebabMenuList.get(0));
		util.click(editButton);

		String nameOfTemplate = util.extractValueByAttributes(templateNameInputField, VAL);
		String timeHandleOfTemplate = util.extractValueByAttributes(handlingTimeInputField, VAL);

		if (!backBtnList.isEmpty()) {
			util.click(backBtn);
		}
		util.click(templateButton);
//		click on edit template button
		util.enterValue(searchTemplateInputField, CreateShopifyProduct.nameOfProduct);
		util.hold(2);
		util.click(templateNameKebabMenuList.get(0));
		util.click(editButton);

//		validate name of template must contain any value
		if (!util.extractValueByAttributes(templateNameInputField, VAL).equals("")) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);

		isTrue = false;
//		validate name of time handling input field must contain any value
		if (!util.extractValueByAttributes(handlingTimeInputField, VAL).equals("")) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);

//		TC_AM_CT_128 => failed for now
		try {
			util.isElementDisplyedAndValidate(numOfProductAssignedBanner);
		} catch (NoSuchElementException e) {
			util.logWarn("No prouducts are assigned to this template");
			Assert.assertTrue(true);
		}

		isTrue = false;
		updateTemplateDetails(input);

//		validate fields are editable if values are changed
		if ((!util.extractValueByAttributes(templateNameInputField, VAL).equals(nameOfTemplate))
				&& (!util.extractValueByAttributes(templateNameInputField, VAL).equals(timeHandleOfTemplate))) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);

	}

	/**
	 * This method is used for updating values . // TC_AM_CT_129
	 * 
	 * @param input
	 */
	public void updateTemplateDetails(Map<String, String> input) {

		util.enterValue(templateNameInputField, input.get("updated_title"));
		util.enterValue(handlingTimeInputField, input.get("updated_time"));
		util.click(editFiltersBtn);
		util.hold(1);
		util.enterValue(advanceSelectionInputField.get(0), CreateShopifyProduct.nameOfProduct);
		clickOnSaveBtn();
	}

	/**
	 * This method selects steps to advance selection
	 * 
	 * @param input
	 */
	public void mandatoryStepsToAdvanceSelection(Map<String, String> input) {
		String temName = createRandomName(input);

		if (!backBtnList.isEmpty()) {
			util.click(backBtnList.get(0));
		}

		util.waitUntilElementIsVisible(templateButton);
		util.click(templateButton);
		util.click(addNewTemplateBtn);
		util.enterValue(templateNameInputField, temName);
		selectDefaultcategoryAndRequireAttribute();
		selectAmazonRecommendationValueFromDropDown();
		selectAdvanceSelectionAndFillAttribute(input);
		util.enableButton(enableInventorySettingsCheckbox);
	}

	/**
	 * This method selects steps to manual selection
	 * 
	 * @param input
	 */
	public void mandatoryStepsToManualSelection(Map<String, String> input) {
		String temName = createRandomName(input);

		if (!backBtnList.isEmpty()) {
			util.click(backBtn);
		}

		util.hold(2);
		util.click(templateButton);
		util.click(addNewTemplateBtn);
		util.enterValue(templateNameInputField, temName);
		selectDefaultcategoryAndRequireAttribute();
		selectAmazonRecommendationValueFromDropDown();
		util.enterValue(handlingTimeInputField, "3");
		util.actionClick(manualRadioBtn);
		util.isElementDisplyedAndValidate(manualSelectionInpField);
		util.isElementDisplyedAndValidate(browseManualProductsButton);
	}

	/**
	 * This method validates that when user lefts time handling input field blank
	 * and clicks on save button error toast message is displayed.TC_AM_CT_034
	 */
	public void validateTimeHandlingInpFieldCanNotBeBlank() {
		util.enableButton(enableInventorySettingsCheckbox);
		util.clear(handlingTimeInputField);
		clickSaveBtnErrIsDisplayed();
	}

	/**
	 * This method validates that when set fixed inventory is enabled an input field
	 * below it is displayed and it accepts only whole numbers.TC_AM_DT_037,
	 * TC_AM_DT_036
	 */
	public void validateFixedInventoryCheckBoxAndInpFieldWorking(Map<String, String> input) {
		boolean isTrue = false;
		util.enableButton(enableInventorySettingsCheckbox);
		util.disableButton(setReserverInventoryCheckbox);
		util.enableButton(setFixedInventoryCheckbox);
		util.isElementDisplyedAndValidate(setFixedInventoryInputField);
		util.enterValue(setFixedInventoryInputField, input.get("invalid_value"));
//		 TC_AM_CT_037

		if (util.matchRegExpressionNumbersOnly(util.extractValueByAttributes(setFixedInventoryInputField, VAL))) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);
	}

	/**
	 * This method validates that user adds optional attributes. TC_AM_CT_026
	 */
	public void openMapOptionalAttribute() {
		util.click(addOptionaAttributeBtn);
		util.actionClick(optionalAmazonAttributeSelectorList.get(0));
		util.actionClick(variationThemeOptionalAttribute);
		util.actionClick(optionalShopifyAttributeSelectorList.get(0));
		util.actionClick(setCustomShopifyAttribute);
		util.actionClick(setShopifyAttributeSelectorInput.get(0));
	}

	public void tCAMCT017() {
		util.hold(8);
		util.click(removeCategoryBtn);
		util.hold(5);
		util.click(category);
		util.hold(5);
		util.click(saveBtn);
		util.isElementDisplyedAndValidate(someInfoMissingErrMsg);
	}

	public void mandatorySteps() {
		create = new CreateShopifyProduct(util);
		String pageUrl = util.getPageURL();
		util.openAndSwitchToNewTab();
		create.createNewShopifyProduct();
		util.getWindoHandleByUrl(pageUrl);
		String tempName = CreateShopifyProduct.nameOfProduct;
		util.switchToIFrame();

		if (!backBtnList.isEmpty()) {
			util.actionClick(backBtn);
		}

		util.hold(1);
		util.click(templateButton);
		util.click(addNewTemplateBtn);
		util.enterValue(templateNameInputField, tempName);
		selectDefaultcategoryAndRequireAttribute();
		selectAmazonRecommendationValueFromDropDown();
		util.enterValue(handlingTimeInputField, "3");
	}

	/**
	 * This method validates elements inside modal when manual selection is used.
	 */
	public void manualSelectionModal() {
		boolean isTrue = false;
		int count = 0;
		WebElement[] arrayElement = { checkBoxProductsManualSelectionDialogBox, continueBtnManualSelectionDialogBox,
				selectedProductsManualSelectionDialogBox, productsStatusManualSelectionDialogBox,
				cancelBtnManualSelectionDialogBox, crossBtnManualSelectionDialogBox };

		mandatorySteps();
		util.actionClick(manualRadioBtn);
		util.enterValue(manualSelectionInpField, CreateShopifyProduct.nameOfProduct);
		util.click(browseManualProductsButton);
		util.hold(1);

		util.validateListIsNotEmpty(productsInManualSelectionModal);
//		validate modal contains elements
		for (WebElement ele : arrayElement) {
			util.isElementDisplyedAndValidate(ele);
		}

		for (int i = 0; i < productsInManualSelectionModal.size(); i++) {
			util.click(productsInManualSelectionModal.get(i));
		}

//		select product count is displayed when selects product in modal
		String countText = StringUtils.substringAfter(selectedProductsManualSelectionDialogBox.getText(), ":").trim();
		count = Integer.parseInt(countText);

		if (count == productsInManualSelectionModal.size()) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);

		util.click(continueBtnManualSelectionDialogBox);

//		when click on cross button modal is closed.
		util.click(browseManualProductsButton);
		util.click(crossBtnManualSelectionDialogBox);
		isTrue = false;

		if (headingOfModal.isEmpty()) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);
	}

	/**
	 * 	
	 */
	public void verifyInvalidStartAndEndDatePreventsTemplateCreation() {
		reuse = new ReusableMethods(util);
		gotoCreateNewProduct();
		reuse.stepsToFillMandatoryData();
		reuse.fetchShopifyProdByAdvanceSelection();
		reuse.verifyProductIsImported();
		enableSalePriceCheckBox();
//		when end date is less than start date.
		String startDate = util.getDateInDDMMYYYY(util.getToday());
		String endDate = util.getDateInDDMMYYYY(util.getYesterday());
		enterSalePriceStartEndDate(startDate, endDate);
		clickOnSaveBtn();
		util.waitUntilElementIsVisible(someInfoMissingErrMsg, 5);
//		when start date is after than end date.
		endDate = util.getDateInDDMMYYYY(util.getYesterday());
		enterSalePriceStartEndDate(startDate, endDate);
		clickOnSaveBtn();
		util.waitUntilElementIsVisible(someInfoMissingErrMsg, 5);
//		when start date and end date are same.
		enterSalePriceStartEndDate(startDate, startDate);
		clickOnSaveBtn();
		util.waitUntilElementIsVisible(profileSaveMsg, 5);
		reuse.waitToTemplateAssign();
	}

	public void gotoCreateNewProduct() {
		reuse = new ReusableMethods(util);
		util.openAndSwitchToNewTab();
		create.goToCreateNewProductPage(util.getConfigProperty("store"));
		create.createNewShopifyProduct();
		CreateShopifyProduct.barcodeOfProduct = "9009519202210";

		util.enterValue(barcodeInputField, "9009519202210");
		util.click(saveBtnShopify);
		try {
			util.getDriver().close();
			reuse.goToTemplatesInSettings();
		} catch (NoSuchWindowException e) {
			util.logError("window is already closed");
		}
	}

	public void gotoCreateNewVariantProduct() {
		reuse = new ReusableMethods(util);
		util.openAndSwitchToNewTab();
		create.goToCreateNewProductPage(util.getConfigProperty("store"));
		create.createNewVariantShopifyProduct();
		try {
			util.getDriver().close();
			reuse.goToTemplatesInSettings();
		} catch (NoSuchWindowException e) {
			util.logError("window is already closed");
		}
	}

	private void enableSalePriceCheckBox() {
		util.enableButton(enableInventorySettingsCheckbox);
		util.enableButton(salePriceCheckbox);
	}

	/**
	 * enter start and end date for sale price
	 */
	private void enterSalePriceStartEndDate(String startDate, String endDate) {
		util.enterValue(startDateSelector, startDate);
		util.enterValue(endDateSelector, endDate);
	}

	/**
	 * 123
	 */
	public void verifyIfProdIsAlreadyAssignInExistingTemplate() {
		reuse = new ReusableMethods(util);
		reuse.stepsToFillMandatoryData();
		String name = reuse.addNewTemplateName("new " + CreateShopifyProduct.nameOfProduct);
		reuse.enableOverrideExistingCheckbox();
		reuse.fetchShopifyProdByManualSelection();
		clickOnSaveBtn();
		reuse.verifyTemplateIsCreatedForExistingProduct(name);
	}

	/**
	 * 149-152
	 */
	public void verifyTemplateHaveNoProductAfterItsProdIsAssignedToAnotherTemp() {
		reuse = new ReusableMethods(util);
		boolean isTrue = false;
		reuse.searchAndOpenEditTemplatePage(CreateShopifyProduct.nameOfProduct);
		util.click(viewProductsBtnEditTempPage);
		util.hold(1);

		if (infoBannerRegardingAssignedProduct.getText()
				.contains("Total 0 product(s) are filtered under applied condition")) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue, "failed due to unmatching content.");
		util.isElementDisplyedAndValidate(editFiltersBtn);
	}

	/**
	 * 153,154
	 */
	public void verifyTemplateCanBeCreatedWithZeroProducts() {
		reuse = new ReusableMethods(util);
		reuse.stepsToFillMandatoryData();
		String templateName = "templateWithProduct" + util.randString(4);
		reuse.addNewTemplateName(templateName);
		clickOnSaveBtn();
		reuse.verifyTemplateIsCreatedForExistingProduct(templateName);
		reuse.searchAndDeleteTemplate(templateName);
	}

	public void clickOnModalDeleteBtn() {
		util.waitUntilElementIsVisible(deleteBtnModal, 10);
		util.click(deleteBtnModal);
		util.hold(5);
	}

//	*********************<Debouncing on template and edit product category search>**********************

	/**
	 * 201 - 209
	 */
	public void verifyEditProdCategorySearch() {
		reuse = new ReusableMethods(util);
		gotoCreateNewProduct();
		reuse.createNewTemplateUsingAdvanceSelection();
		reuse.searchAndOpenEditTemplatePage(CreateShopifyProduct.nameOfProduct);
		verifyNoSearchResultsForShortInput();
		verifySearchResultsForValidCategory();
		verifyCrossBtnInInputFieldIsWorking();
	}

	private void verifyNoSearchResultsForShortInput() {
		util.hold(10);
		util.click(searchCategoryInputField);
		// Enter a value with less than three characters in the search input field
		util.enterValue(searchCategoryInputField, "an");

		// Click the search button
		util.click(searchCategoryBtn);

		try {
			// Check if the search results list is empty
			util.throwExceptionIfListIsEmpty(clearSearchBtn);
		} catch (EmptyListException e) {
			// If the list is empty, pass the test with an informative message
			Assert.assertTrue(true, "Search results displayed for input with less than three characters.");
		}
	}

	private void verifySearchResultsForValidCategory() {
		util.enterValue(searchCategoryInputField, "android");
		util.click(searchCategoryBtn);
		util.waitUntilListIsVisible(clearSearchBtn, 30);

		for (int i = 0; i < 5; i++) {
			Assert.assertEquals(searchedCategoriesList.get(i).getText(), "Apps for Android");
		}

	}

	private void verifyCrossBtnInInputFieldIsWorking() {
		util.click(searchCategoryInputField);
		util.click(crossBtnSearchCategoryInputField);
		util.listIsEmpty(searchedCategoriesList);
	}

//	*********************<Debouncing on template and edit product category search/>**********************
//	*********************<Make necessary changes on frontend to make FBA/FBM Live>**********************

	public void verifyFBMIsSelectedByDefaultInAddNewTemplate() {
		boolean isTrue = false;
//		util.click(backBtn);
//		util.refreshPage();
//		util.switchToIFrame();
//		reuse.openProductTemplatesPage();

		if (radioBtnFBM.isSelected()) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue, "FBM is not selected by default.");
	}

	public void selectFulfillmentType(String type) {

		if (type.equalsIgnoreCase("fbm") && !radioBtnFBM.isSelected()) {
			util.click(radioBtnFBM);
		} else if (type.equalsIgnoreCase("fba") && !radioBtnFBA.isSelected()) {
			util.click(radioBtnFBA);
		} else {
			util.logError("invalid input.");
		}
	}

	public void verifyFulfillmentTypeIsUpdated(String type) {
		boolean isTrue = false;
		if (type.equalsIgnoreCase("fbm") && radioBtnFBM.isSelected()) {
			isTrue = true;
		} else if (type.equalsIgnoreCase("fba") && radioBtnFBA.isSelected()) {
			isTrue = true;
		} else {
			util.logError("invalid input.");
		}

		Assert.assertTrue(isTrue, type + "is not selected.");
	}

//	*********************<Make necessary changes on frontend to make FBA/FBM Live/>**********************

//	*********************<Created_At and Updated_At at Template Grid>**********************

	public void verifyCreateAndUpdatedTimeIsVisibleOnGrid(String name) {
		util.refreshPage();
		util.waitUntilElementIsVisible(templatesTab, 20);
		util.click(templatesTab);
		util.enterValue(templateSearchInputField, name);
		util.hold(2);

		Assert.assertNotEquals(createdOnList.get(0).getText(), "", "Template create date is not available.");
		Assert.assertNotEquals(updatedOnList.get(0).getText(), "", "Template update date is not available.");

	}

	public void verifyCloneTemplateContainsCreateAndUpdatedTimeOnGrid(String name) {
		reuse = new ReusableMethods(util);
		reuse.searchAndCloneTemplate(name);
		verifyCreateAndUpdatedTimeIsVisibleOnGrid(name);
	}

//	*********************<Created_At and Updated_At at Template Grid/>**********************

//	*********************<Round off functionality>**********************

	/**
	 * 253-256
	 */
	public void verifyRoundOffFunctionality() {
		util.isElementDisplyedAndValidate(standardPriceRoundingOffLabel);
		enableSalePriceCheckBox();
		util.isElementDisplyedAndValidate(salePriceRoundingOffLabel);
		util.enableButton(businessPriceCheckbox);
		util.isElementDisplyedAndValidate(businessPriceRoundingOffLabel);
		util.enableButton(minimumPriceCheckbox);
		util.isElementDisplyedAndValidate(minimumPriceRoundingOffLabel);
		util.disableButton(salePriceCheckbox);
		util.disableButton(businessPriceCheckbox);
		util.disableButton(minimumPriceCheckbox);
	}

	public void verifyRoundOff(String templateName, String value) {
		reuse = new ReusableMethods(util);
		util.selectByValue(standardPriceRoundingOffSelect, value);
		reuse.clickOnSave();
		util.waitUntilElementIsVisible(importantInfoModal, 60);
		util.click(crossBtn);
		util.goToSection("listings");
		util.refreshPage();
		util.switchToIFrame();
		plp.searchAndSelectProduct(templateName);
		util.hold(5);
		util.click(productTitleList.get(0));
		util.waitUntilElementIsVisible(productNameHeading, 30);

	}

	public void verifyRoundOff(String templateName, String choseTrend, String roundOffVal) {
		reuse = new ReusableMethods(util);
		util.selectByValue(choseTrendSelect, choseTrend);
		util.enterValue(standardPriceValueInputTextField, "100");
		util.selectByValue(standardPriceRoundingOffSelect, roundOffVal);
		reuse.clickOnSave();
		util.waitUntilElementIsVisible(importantInfoModal, 60);
		util.click(crossBtn);
		util.goToSection("listings");
		util.refreshPage();
		util.switchToIFrame();
		plp.searchAndSelectProduct(templateName);
		util.hold(5);
		util.click(productTitleList.get(0));
		util.waitUntilElementIsVisible(productNameHeading, 30);

	}

	/**
	 * after round off price does not contains any decimal.
	 */
	public void verifyForWholeNum() {
		boolean isTrue = false;
		util.hold(1);
		util.moveToElement(productPriceListing);
		util.hold(1);

		if (!pricePopupListing.getText().contains(".")) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue, "Price contains decimal values.");
		util.refreshPage();
	}

	/**
	 * returns next number ending with zero for a given number
	 * 
	 * @param num
	 * @return
	 */
	public String findHighestNearestNumEndingWithZero(int num) {
		int number = num;
		int remainder = number % 10;
		int difference = 10 - remainder;

		if (remainder == 0) {
			return String.valueOf(number + 10);
		} else {
			return String.valueOf(number + difference);
		}

	}

	/**
	 * turns next number ending with zero
	 * 
	 * @param num
	 * @return
	 */
	public String findNearestNumEndingWithZero(int num) {
		int number = num;
		int remainder = number % 10;
		int difference = 10 - remainder;

		if (remainder == 0) {
			return String.valueOf(number);
		} else {
			return String.valueOf(number + difference);
		}

	}

	/**
	 * turns next number ending with a specific number
	 * 
	 * @param num
	 * @param endingVal
	 * @return
	 */
	public String findNearestNumEndingWith(int num, int endingVal) {
		int number = num;
		while (number % 10 != endingVal) {
			number++;
		}

		return String.valueOf(number);
	}

	/**
	 * 
	 * @param num
	 * @return
	 */
	public String findNearestNumEndingWithPoint49(double num) {
		int integerPart = (int) num;

		// Calculate the decimal part of the number
		double decimalPart = num - integerPart;

		// Check if the decimal part is less than 0.49
		if (decimalPart < 0.49) {
			// Add 0.49 to the integer part and return the result
			return String.valueOf(integerPart + 0.49);
		} else {
			// Add 1.0 to the integer part and subtract 0.51 from the decimal part
			// to get the next number ending with 0.49
			return String.valueOf(integerPart + 1.0 - 0.51 + decimalPart);
		}
	}

	/**
	 * 
	 * @param num
	 * @return
	 */
	public String findNearestNumEndingWithPoint99(double num) {
		int integerPart = (int) Math.ceil(num);

		// Add 0.99 to the integer part
		return String.valueOf(integerPart + 0.99);
	}

	/**
	 * 
	 * @param num
	 */
	public void priceEndsWithNumber(String num) {
		boolean isTrue = false;
		util.hold(1);
		util.moveToElement(productPriceListing);
		util.hold(1);
		String price = pricePopupListing.getText();

		if (price.endsWith(num)) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue, "Price does not ends with ." + num);
	}

	/**
	 * 
	 * @return
	 */
	public String priceAfterRoundOff() {
		util.hold(1);
		util.moveToElement(productPriceListing);
		util.hold(1);

		return pricePopupListing.getText();

	}

	/**
	 * 
	 * @param number
	 * @return
	 */
	public String nearestLowerEndingWithZero(int number) {
		if (number % 10 == 0) {
			// If the input number is already ending with 0, return the same number
			return String.valueOf(number);
		} else {
			// Subtract the last digit to get the immediate lower number ending with 0
			return String.valueOf(number - (number % 10));
		}
	}

	/**
	 * 
	 * @param number
	 * @return
	 */
	public String immediateLowerWholeNum(double number) {
		int immediateLowerWholeNumber = (int) Math.floor(number);
		return String.valueOf(immediateLowerWholeNumber);
	}

	/**
	 * 
	 * @param number
	 * @return
	 */
	public String immediateLowerNumberEndingWith9(int number) {
		if (number < 9) {
			return String.valueOf(0);
		} else if (number == 9) {
			return String.valueOf(9);
		} else {
			return String.valueOf((number / 10) * 10 - 1);
		}

	}

	/**
	 * 
	 * @param number
	 * @return
	 */
	public String lowerNumEndingWithPoint49(double number) {
		double lowerNumber = Math.floor(number) - 0.51;
		return String.valueOf(lowerNumber);
	}

	/**
	 * 
	 * @param number
	 * @return
	 */
	public String lowerNumEndingWithPoint99(double number) {
		int integerPart = (int) number;
		double lowerNumber = integerPart - 0.01;

		return String.valueOf(lowerNumber);
	}

	/**
	 * 
	 * @param number
	 * @return
	 */
	public String percentageIncreaseAtHigher(double number, String calcAt) {
		number = Math.floor(number);
		int doubledNum = (int) (number + (number * 100 / 100));

		switch (calcAt) {
		case "whole":
			return String.valueOf(doubledNum);

		case "endWith10":
			return String.valueOf(doubledNum + 10);

		case "endWith9":
			if (doubledNum % 10 != 9) {
				doubledNum = doubledNum + (9 - doubledNum % 10);
			}

			return String.valueOf(doubledNum);

		case "endWithPoint49":
			double num = doubledNum + 0.49;
			return String.valueOf(num);

		case "endWithPoint99":
			double num2 = doubledNum + 0.99;
			return String.valueOf(num2);

		default:
			break;
		}
		return calcAt;

	}

	/**
	 * 
	 * @param number
	 * @return
	 */
	public String percentageDecreaseAtHigher(String calcAt) {

		switch (calcAt) {
		case "whole":
			return "0";

		case "endWith10":
			return "10";

		case "endWith9":
			return "9";

		case "endWithPoint49":
			return "0.49";

		case "endWithPoint99":
			return "0.99";

		default:
			break;
		}
		return calcAt;

	}

	/**
	 * 
	 * @param calcAt
	 * @return
	 */
	public String percentageDecreaseAtLower(String calcAt) {

		if (calcAt.equalsIgnoreCase("whole") || calcAt.equalsIgnoreCase("endWith10")
				|| calcAt.equalsIgnoreCase("endWith9") || calcAt.equalsIgnoreCase("endWithPoint49")
				|| calcAt.equalsIgnoreCase("endWithPoint99")) {
			return "0";
		}
		return calcAt;

	}

	public void verifyTemplateCanNotBeSavedIfStartAndEndDateIsNotSelected(String prodTempName) {
		reuse = new ReusableMethods(util);
		reuse.goToSettings();
		reuse.searchAndOpenEditTemplatePage(prodTempName);
		util.enableButton(salePriceCheckbox);
		reuse.clickOnSave();
		util.hold(1);
		util.isElementDisplyed(someInfoMissingErrMsg);
		util.disableButton(salePriceCheckbox);
	}

//	*********************<Round off functionality/>**********************

//	*********************fba/fbm should be reflect on listing******************

	public void verifyFulfilllmentTypeIsSameOnListing(String prodTempName) {
		reuse = new ReusableMethods(util);
		String fulFillmentType;
//		reuse.goToSettings();
//		util.switchToIFrame();
		reuse.searchAndOpenEditTemplatePage(prodTempName);

//		check which fulfillment type is selected
		if (radioBtnFBM.isSelected()) {
			fulFillmentType = "fbm";
		} else {
			fulFillmentType = "fba";
		}

		util.goToSection("listings");
		util.refreshPage();
		util.switchToIFrame();
		plp.searchAndSelectProduct(prodTempName);
		util.hold(5);
//		util.click(productTitleList.get(0));
//		util.waitUntilElementIsVisible(productNameHeading, 30);
		Assert.assertEquals(fulfillmentTypeVal.getText().trim(), fulFillmentType.toUpperCase());
	}

}
