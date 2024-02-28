package com.ami.pageobjects;

/**
 * Project Name: Amazon MultiAccount 
 * Author: Yatindra Kinker 
 * Version: 
 * Reviewed By: Abhay Hayaran 
 * Date of Creation: Jan 16, 2022
 *  Modification History:
 * Date of change: 
 * Version: 
 * changed function:  
 * change description:
 * Modified By:
 */

import java.util.Map;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ami.resources.Utilities;

public class DefaultTemplatePage extends DefaultTemplatePageOR {
	private CustomTemplateContextualSaveBarPage ctp;
	String falseVal = "false";
	String ariaChecked = "aria-checked";
	String percentDecrease = "percent_decrease";
	String percentIncrease = "percentage_increase";
	String invalidVal = "invalid_value";
	String val = "value";

	Utilities util;

	public DefaultTemplatePage(Utilities util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
		ctp = new CustomTemplateContextualSaveBarPage(util);
	}

	/**
	 * This method validates that when user clicks on edit button it should redirect
	 * him to default template page tcAmDt001, tcAmDt002
	 */
	public void validateRedirectionToDefTemplatePage() {
		util.switchToDefaultContent();
		util.goToMultiAccountsSettingsClick();
		util.hold(10);
		util.click(productTemplates);
		util.isElementDisplyedAndValidate(headingDefaultTemplatePage);
		util.hold(5);
	}

	public void clickSaveBtnErrIsDisplayed() {
		util.hold(1);
		clickOnSaveBtn();
		util.hold(2);
		util.isElementDisplyedAndValidate(someInfoMissingErrMsg);
		util.hold(2);
	}

	public void clickOnSaveBtn() {
		util.switchToDefaultContent();
		util.click(saveBtn);
		util.switchToIFrame();
	}

	/**
	 * This method validates that all checkbox are working properly and clickable.
	 * tcAmDt005
	 */
	public void validateAllCheckBoxAreWorking() {
		for (WebElement checkbox : allCheckboxList) {
			try {
				if (util.extractValueByAttributes(checkbox, "disabled")==null) {

				} else if (util.extractValueByAttributes(checkbox, "disabled").equals(falseVal)) {

					if (util.extractValueByAttributes(checkbox, ariaChecked).equals("true")) {
						util.click(checkbox);

						if (util.extractValueByAttributes(checkbox, ariaChecked).equals(falseVal)) {
							Assert.assertTrue(true);
						} else {
							Assert.assertTrue(false);
						}
					} else if (util.extractValueByAttributes(checkbox, ariaChecked).equals(falseVal)) {
						util.click(checkbox);

						if (util.extractValueByAttributes(checkbox, ariaChecked).equals("true")) {
							Assert.assertTrue(true);
						} else {
							Assert.assertTrue(false);
						}
					}

				}
			} catch (NullPointerException e) {
				util.logWarn("null pointer exception occured.");
			}
		}
	}

	/**
	 * This method validates that when user disables inventory settings all
	 * checkboxes are disabled. tcAmDt006, tcAmDt007
	 */
	public void validateInventorySettings() {
		boolean isTrue = false;
		util.disableButton(enableInventorySettingsCheckbox);
		util.validateIsElementDisabled(handlingTimeInputField);
		util.validateIsElementDisabledUsingAttribute(continueSellingCheckbox);
		util.validateIsElementDisabledUsingAttribute(setFixedInventoryCheckbox);
		util.validateIsElementDisabledUsingAttribute(setReserverInventoryCheckbox);
		util.validateIsElementDisabledUsingAttribute(thresoldInventoryCheckbox);
		util.hold(1);
		util.validateIsElementDisabledUsingAttribute(delOutOfStockCheckBoxDisabled);

		for (WebElement checkbox : warehouseListCheckbox) {
			util.validateIsElementDisabled(checkbox);
		}

		util.enableButton(enableInventorySettingsCheckbox);
		util.enableButton(continueSellingCheckbox);
//		tcAmDt007
		if (util.extractValueByAttributes(continueSellingCheckbox, ariaChecked).equals("true")
				&& util.extractValueByAttributes(setMaxInventoryInputField, val).equals("999")) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		} 
			Assert.assertTrue(isTrue);

	}

	/**
	 * This method validate that when user disables "Continue selling when out of
	 * stock" checkbox the input field below it is not visible.
	 */
	public void tcAmDt008() {
		util.enableButton(enableInventorySettingsCheckbox);
		util.disableButton(continueSellingCheckbox);
		try {
			util.isElementNotDisplyedAndValidate(setMaxInventoryInputField);
		} catch (NoSuchElementException e) {
			Assert.assertTrue(true);
		}
	}

	/**
	 * This method validates that time handling input field only accepts whole
	 * numbers.tcAmDt010
	 * 
	 * @param input
	 */
	public void tcAmDt010(Map<String, String> input) {
		boolean containsDot = false;
		util.enableButton(enableInventorySettingsCheckbox);
		util.enterValue(handlingTimeInputField, input.get("handling_time_decimal_num"));

		if (!util.extractValueByAttributes(handlingTimeInputField, val).contains(".")) {
			containsDot = true;
			Assert.assertTrue(containsDot);
		}
			Assert.assertTrue(containsDot);
	}

	/**
	 * This method validates that when user lefts time handling input field blank
	 * and clicks on save button error toast message is displayed.tcAmDt012
	 * 
	 * @param input
	 */
	public void tcAmDt012() {
		util.enableButton(enableInventorySettingsCheckbox);
		util.clear(handlingTimeInputField);
		clickSaveBtnErrIsDisplayed();
	}

	/**
	 * This method validates that when set fixed inventory is enabled an input field
	 * below it is displayed and it accepts only whole numbers.tcAmDt014,
	 * tcAmDt015
	 */
	public void tcAmDt014015(Map<String, String> input) {
		boolean acceptsNumsOnly = false;
		util.enableButton(enableInventorySettingsCheckbox);
		util.disableButton(setReserverInventoryCheckbox);
		util.enableButton(setFixedInventoryCheckbox);
		util.isElementDisplyedAndValidate(setFixedInventoryInputField);
		util.enterValue(setFixedInventoryInputField, input.get(invalidVal));
//		 tcAmDt015

		if (util.matchRegExpressionNumbersOnly(util.extractValueByAttributes(setFixedInventoryInputField, val))) {
			acceptsNumsOnly = true;
			Assert.assertTrue(acceptsNumsOnly);
		}
			Assert.assertTrue(acceptsNumsOnly);
	}

	/**
	 * This method validates that when user enters no value in set fixed inventory
	 * input field and clicks on save btn an error toast msg is displayed.
	 * tcAmDt016,tcAmDt017, tcAmDt019
	 */
	public void validateFixedInventoryFunctionality() {
		boolean inputFieldDisplayed = false;
		util.enableButton(enableInventorySettingsCheckbox);
		util.enableButton(setFixedInventoryCheckbox);
		util.clear(setFixedInventoryInputField);
		clickSaveBtnErrIsDisplayed();
		util.hold(2);
		util.validateIsElementDisabledUsingAttribute(setReserverInventoryCheckbox);
		util.disableButton(setFixedInventoryCheckbox);

		try {
			if (!setFixedInventoryInputField.isDisplayed()) {
				inputFieldDisplayed = true;
				Assert.assertTrue(inputFieldDisplayed);
			} else {
				Assert.assertTrue(inputFieldDisplayed);
			}
		} catch (NoSuchElementException e) {
			Assert.assertTrue(true);
		}
	}

	/**
	 * This method validates funcationality of set reserve checkbox.
	 * tcAmDt020,tcAmDt021,tcAmDt022,tcAmDt023,tcAmDt025
	 */
	public void validateSetReserveFunctionality(Map<String, String> input) {
		boolean acceptsNumsOnly = false;
		boolean inputFieldDisplayed = false;
		util.enableButton(enableInventorySettingsCheckbox);
		util.disableButton(setFixedInventoryCheckbox);
		util.enableButton(setReserverInventoryCheckbox);
		util.isElementDisplyedAndValidate(setReserverInventoryInputField);
		util.clear(setReserverInventoryInputField);
		clickSaveBtnErrIsDisplayed();
		util.hold(2);
//		validation regarding setReserverInventoryInputField only accepts whole numbers.
		util.enterValue(setReserverInventoryInputField, input.get(invalidVal));

		if (util.matchRegExpressionNumbersOnly(
				util.extractValueByAttributes(setReserverInventoryInputField, val))) {
			acceptsNumsOnly = true;
			Assert.assertTrue(acceptsNumsOnly);
		}
			Assert.assertTrue(acceptsNumsOnly);

//		validates that if setReserverInventory checkbox is checked then set fixed Inventory is getting disabled.
		util.validateIsElementDisabledUsingAttribute(setFixedInventoryCheckbox);
//		validates that if setReserverInventory checkbox is unchecked then input field below it is not displayed.
		util.disableButton(setReserverInventoryCheckbox);

		try {
			if (!setReserverInventoryInputField.isDisplayed()) {
				inputFieldDisplayed = true;
				Assert.assertTrue(inputFieldDisplayed);
			} else {
				Assert.assertTrue(inputFieldDisplayed);
			}
		} catch (NoSuchElementException e) {
			Assert.assertTrue(true);
		}
	}

	/**
	 * This method validates funcationality of thresold inventory checkbox.
	 * tcAmDt026,tcAmDt027,tcAmDt028,tcAmDt029
	 */
	public void validateThresoldInventoryFunctionality(Map<String, String> input) {
		boolean isTrue = false;
//		enables "Enable Inventory Checkbox".
		util.enableButton(enableInventorySettingsCheckbox);
//		enables "Thresold Inventory Checkbox".
		util.enableButton(thresoldInventoryCheckbox);
//		validates input field below thresold invetory checkbox is displayed.
		util.isElementDisplyedAndValidate(thresoldInventoryInputField);
//		validation regarding thresoldInventoryInputField only accepts whole numbers.
		util.enterValue(thresoldInventoryInputField, input.get(invalidVal));

		if (util.matchRegExpressionNumbersOnly(util.extractValueByAttributes(thresoldInventoryInputField, val))) {
			 isTrue = true;
			Assert.assertTrue(isTrue);
		}
			Assert.assertTrue(isTrue);

//		validates if input field is blank and when user clicks on save button toast error msg is displayed.
		util.enterValue(thresoldInventoryInputField, "");
		clickSaveBtnErrIsDisplayed();
		util.hold(2);
	}

	/**
	 * This method validates working of price settings. If "Enable Price Syncing
	 * from the App" checkbox is disabled all fields under this section will be
	 * disabled.tcAmDt042,tcAmDt043
	 */
	public void validatePriceSettingsFunctionality() {
		util.enableButton(enableInventorySettingsCheckbox);
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
		util.isElementDisplyedAndValidate(standardPriceChoseTrend);
		util.isElementDisplyedAndValidate(salePriceCheckbox);
		util.isElementDisplyedAndValidate(businessPriceCheckbox);
		util.isElementDisplyedAndValidate(minimumPriceCheckbox);
	}

	/**
	 * Enable sales price setting and standard price is chosen as Map to shopify
	 * price and start date and end date is not chosen on clicking save button toast
	 * error message is displayed. tcAmDt050
	 */
	public void validateEnableSalePriceFunctionality(Map<String, String> input) {
		util.enableButton(enableInventorySettingsCheckbox);
		util.hold(1);
		util.enableButton(salePriceCheckbox);
		util.selectByValue(standardPriceChoseTrend, input.get("map_to_shopify_price"));
		clickSaveBtnErrIsDisplayed();
	}

	/**
	 * tcAmDt060, tcAmDt062,tcAmDt064,tcAmDt067
	 */
	public void validateBusinessPriceFunctionality(Map<String, String> input) {
		util.enableButton(enableInventorySettingsCheckbox);
//		enable "businessPriceCheckbox"
		util.enableButton(businessPriceCheckbox);
		util.selectByValue(choseTrendBusinessPrice, input.get(percentDecrease));
		util.enterValue(choseTrendBusinessValueInputField, "");
		clickSaveBtnErrIsDisplayed();
//		select percent increase from dropdown and save
		util.selectByValue(choseTrendBusinessPrice, input.get(percentIncrease));
		util.enterValue(choseTrendBusinessValueInputField, "");
		clickSaveBtnErrIsDisplayed();
//		select value increase from dropdown and save
		util.selectByValue(choseTrendBusinessPrice, input.get("value_increase"));
		util.enterValue(choseTrendBusinessValueInputField, "");
		clickSaveBtnErrIsDisplayed();
//		select value decrease from dropdown and save
		util.selectByValue(choseTrendBusinessPrice, input.get("value_decrease"));
		util.enterValue(choseTrendBusinessValueInputField, "");
		clickSaveBtnErrIsDisplayed();
	}

	public void validateMinimumPriceFunctionality(Map<String, String> input) {
		util.enableButton(enableInventorySettingsCheckbox);
//		enable "minimumPriceCheckbox"
		util.enableButton(minimumPriceCheckbox);
		util.selectByValue(selectMinPriceTrend, input.get(percentDecrease));
		util.enterValue(minimumPriceTrendValueInputField, "");
		clickSaveBtnErrIsDisplayed();
//		select percent increase from dropdown and save
		util.selectByValue(selectMinPriceTrend, input.get(percentIncrease));
		util.enterValue(minimumPriceTrendValueInputField, "");
		clickSaveBtnErrIsDisplayed();
//		select value increase from dropdown and save
		util.selectByValue(selectMinPriceTrend, input.get("value_increase"));
		util.enterValue(minimumPriceTrendValueInputField, "");
		clickSaveBtnErrIsDisplayed();
//		select value decrease from dropdown and save
		util.selectByValue(selectMinPriceTrend, input.get("value_decrease"));
		util.enterValue(minimumPriceTrendValueInputField, "");
		clickSaveBtnErrIsDisplayed();
	}

	public void contextualSaveBarIsDisplayedIfInventorySettingsAreChanged() {
		util.click(backBtn);
		util.hold(5);
		util.click(editButton);
		util.hold(2);
		util.enableAndDisableElement(enableInventorySettingsCheckbox);
		util.contextualSaveBarIsDisplayed();
		ctp.contextualBarContainsDiscardAndSaveBtn();
	}

	public void contextualSaveBarIsDisplayedIfHandlingTimeValueIsChanged() {
		util.enableButton(enableInventorySettingsCheckbox);
		util.clear(handlingTimeInputField);
		util.contextualSaveBarIsDisplayed();
		ctp.contextualBarContainsDiscardAndSaveBtn();
	}

	public void contextualSaveBarIsNotDisplayedIfHandlingTimeValueEnteredIsSame() {
		util.click(backBtn);
		util.waitUntilElementIsVisible(editButton);
		util.click(editButton);
		util.hold(2);
		util.enableAndDisableElement(enableInventorySettingsCheckbox);
		String value = util.extractValueByAttributes(handlingTimeInputField, val);
		util.enableButton(enableInventorySettingsCheckbox);
		util.clear(handlingTimeInputField);
		util.enterValue(handlingTimeInputField, value);
		util.contextualSaveBarIsNotDisplayed();
	}

	public void enterInvalidHandlingTimeAndSave() {
		util.click(backBtn);
		util.waitUntilElementIsVisible(editButton);
		util.click(editButton);
		util.hold(2);
		util.enableAndDisableElement(enableInventorySettingsCheckbox);
		util.enableButton(enableInventorySettingsCheckbox);
		util.clear(handlingTimeInputField);
		util.enterValue(handlingTimeInputField, "500");
		clickSaveBtnErrIsDisplayed();
	}

	public void continueSellingCheckboxCheckedUncheckedContextualSaveBarIsDisplayed() {
		util.click(backBtn);
		util.waitUntilElementIsVisible(editButton);
		util.click(editButton);
		util.hold(2);
		util.enableButton(enableInventorySettingsCheckbox);
		util.enableAndDisableElement(continueSellingCheckbox);
		util.contextualSaveBarIsDisplayed();
		ctp.contextualBarContainsDiscardAndSaveBtn();
		util.enableAndDisableElement(continueSellingCheckbox);
		util.discardAndSaveBtnAreNotDisplayed();
	}

	public void changeDefaultSettingsAndValidateContextualSaveBarIsDisplayed(String checkboxName) {
		util.click(backBtn);
		util.waitUntilElementIsVisible(editButton);
		util.click(editButton);
		util.hold(2);
		util.enableButton(enableInventorySettingsCheckbox);
		changeDefaultSettings(checkboxName);
		util.contextualSaveBarIsDisplayed();
		ctp.contextualBarContainsDiscardAndSaveBtn();
		changeDefaultSettings(checkboxName);
		util.contextualSaveBarIsNotDisplayed();
		util.discardAndSaveBtnAreNotDisplayed();
	}

	public void changeDefaultSettings(String checkboxName) {
		switch (checkboxName) {
		case "enablePriceSyncCheckbox":
			util.enableAndDisableElement(enablePriceSyncCheckbox);
			break;
		case "continueSellingCheckbox":
			util.enableAndDisableElement(continueSellingCheckbox);
			break;
		case "fixedInventoryCheckbox":
			util.enableAndDisableElement(setFixedInventoryCheckbox);
			break;
		case "setReserveCheckbox":
			util.enableAndDisableElement(setReserverInventoryCheckbox);
			break;
		case "thresoldInventoryCheckbox":
			util.enableAndDisableElement(thresoldInventoryCheckbox);
			break;
		case "priceSynx":
			util.enableAndDisableElement(enablePriceSyncCheckbox);
			break;
		case "salePriceCheckbox":
			util.enableAndDisableElement(salePriceCheckbox);
			break;
		case "businessPriceCheckbox":
			util.enableAndDisableElement(businessPriceCheckbox);
			break;
		case "minimumPriceCheckbox":
			util.enableAndDisableElement(minimumPriceCheckbox);
			break;
		case "barcodeCheckbox":
			util.enableAndDisableElement(barcodeCheckbox);
			break;
		case "skuCheckbox":
			util.enableAndDisableElement(skuCheckbox);
			break;
		default:
			break;
		}
	}

	public void selectDeSelelctInventoryCheckBox() {

		if (warehouseListCheckbox.isEmpty()) {
			util.contextualSaveBarIsNotDisplayed();
			util.discardAndSaveBtnAreNotDisplayed();
		} else {
			util.click(warehouseListCheckbox.get(0));
			util.contextualSaveBarIsDisplayed();
			ctp.contextualBarContainsDiscardAndSaveBtn();
		}
	}

	public void enablePriceSyncingAndSave() {

		if (util.extractValueByAttributes(enablePriceSyncCheckbox, ariaChecked).equals(falseVal)) {
			util.click(enablePriceSyncCheckbox);
			clickOnSaveBtn();
		}
	}

	public void enableThresoldInventoryAndSave() {

		if (util.extractValueByAttributes(thresoldInventoryCheckbox, ariaChecked).equals(falseVal)) {
			util.click(thresoldInventoryCheckbox);
			clickOnSaveBtn();
		}
	}

	public void enableFixedInventoryAndSave() {

		if (util.extractValueByAttributes(setFixedInventoryCheckbox, ariaChecked).equals(falseVal)) {
			util.click(setFixedInventoryCheckbox);
			clickOnSaveBtn();
		}
	}

	public void enableReserveInventoryAndSave() {

		util.disableButton(setFixedInventoryCheckbox);

		if (util.extractValueByAttributes(setReserverInventoryCheckbox, ariaChecked).equals(falseVal)) {
			util.click(setReserverInventoryCheckbox);
			clickOnSaveBtn();
		}
	}

	public void changeThresoldInvenventory() {
		String value = util.extractValueByAttributes(thresoldInventoryInputField, val);

		if (value.equals("")) {
			util.enterValue(thresoldInventoryInputField, "1");
			clickOnSaveBtn();
		} else {
			value = util.extractValueByAttributes(thresoldInventoryInputField, val);
			int thresoldLimit = Integer.parseInt(value) + 1;
			util.enterValue(thresoldInventoryInputField, String.valueOf(thresoldLimit));
			util.contextualSaveBarIsDisplayed();
			ctp.contextualBarContainsDiscardAndSaveBtn();
			util.enterValue(thresoldInventoryInputField, value);
			util.contextualSaveBarIsNotDisplayed();
			util.discardAndSaveBtnAreNotDisplayed();
		}
	}

	public void changeFixedInvenventory() {
		String value = util.extractValueByAttributes(setFixedInventoryInputField, val);

		if (value.equals("")) {
			util.enterValue(setFixedInventoryInputField, "1");
			clickOnSaveBtn();
		} else {
			value = util.extractValueByAttributes(setFixedInventoryInputField, val);
			int thresoldLimit = Integer.parseInt(value) + 1;
			util.enterValue(setFixedInventoryInputField, String.valueOf(thresoldLimit));
			util.contextualSaveBarIsDisplayed();
			ctp.contextualBarContainsDiscardAndSaveBtn();
			util.enterValue(setFixedInventoryInputField, value);
			util.contextualSaveBarIsNotDisplayed();
			util.discardAndSaveBtnAreNotDisplayed();
		}
	}

	public void changeReserveInvenventory() {
		String value = util.extractValueByAttributes(setReserverInventoryInputField, val);

		if (value.equals("")) {
			util.enterValue(setReserverInventoryInputField, "1");
			clickOnSaveBtn();
		} else {
			value = util.extractValueByAttributes(setReserverInventoryInputField, val);
			int thresoldLimit = Integer.parseInt(value) + 1;
			util.enterValue(setReserverInventoryInputField, String.valueOf(thresoldLimit));
			util.contextualSaveBarIsDisplayed();
			ctp.contextualBarContainsDiscardAndSaveBtn();
			util.enterValue(setReserverInventoryInputField, value);
			util.contextualSaveBarIsNotDisplayed();
			util.discardAndSaveBtnAreNotDisplayed();
		}
	}
	
	public void changeStandardPriceChoseTrend() {
		util.enableButton(enablePriceSyncCheckbox);
		util.selectByValue(standardPriceChoseTrend, percentIncrease);
		util.contextualSaveBarIsDisplayed();
		ctp.contextualBarContainsDiscardAndSaveBtn();
		util.selectByValue(standardPriceChoseTrend, "default");
		util.contextualSaveBarIsNotDisplayed();
		util.discardAndSaveBtnAreNotDisplayed();
		util.selectByValue(standardPriceChoseTrend, percentDecrease);
		util.contextualSaveBarIsDisplayed();
		ctp.contextualBarContainsDiscardAndSaveBtn();
	}

}
