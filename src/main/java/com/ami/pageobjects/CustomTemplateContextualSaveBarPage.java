package com.ami.pageobjects;

/**
 * Project Name: Amazon MultiAccount 
 * Author: Yatindra Kinker 
 * Version: 
 * Reviewed By: Abhay Hayaran 
 * Date of Creation: March 21, 2023
 *  Modification History:
 * Date of change: 
 * Version: 
 * changed function:  
 * change description:
 * Modified By:
 */

import org.openqa.selenium.support.PageFactory;

import com.ami.pageobjects.reusableclasses.ReusableMethods;
import com.ami.resources.Utilities;

public class CustomTemplateContextualSaveBarPage extends DefaultTemplatePageOR {

	Utilities util;
	ReusableMethods reuse;

	public CustomTemplateContextualSaveBarPage(Utilities util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
		reuse = new ReusableMethods(util);
	}

	public void gotoCustomTemplate() {
		reuse.goToTemplatesInSettings();
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

	public void contextualSaveBarIsDisplayedIfInventorySettingsAreChanged() {
		util.click(backBtn);
		util.hold(5);
		util.click(addNewTemplateBtn);
		util.hold(2);
		util.enableAndDisableElement(enableInventorySettingsCheckbox);
		util.contextualSaveBarIsDisplayed();
		contextualBarContainsDiscardAndSaveBtn();
		util.enableAndDisableElement(enableInventorySettingsCheckbox);
		util.contextualSaveBarIsNotDisplayed();
	}

	public void contextualSaveBarIsDisplayedIfHandlingTimeValueIsChanged() {
		util.enableButton(enableInventorySettingsCheckbox);
		util.enterValue(handlingTimeInputField, "3");
		util.contextualSaveBarIsDisplayed();
		contextualBarContainsDiscardAndSaveBtn();
		util.clickOnSaveBtn();
	}

	public void contextualBarContainsDiscardAndSaveBtn() {
		util.switchToDefaultContent();
		util.isElementDisplyed(discardBtn);
		util.isElementDisplyed(saveBtn);
		util.switchToIFrame();
	}

	public void contextualSaveBarIsNotDisplayedIfHandlingTimeValueEnteredIsSame() {
		util.click(backBtn);

		util.click(addNewTemplateBtn);
		util.hold(2);
		util.enableAndDisableElement(enableInventorySettingsCheckbox);
		String value = util.extractValueByAttributes(handlingTimeInputField, "value");
		util.enableButton(enableInventorySettingsCheckbox);
		util.clear(handlingTimeInputField);
		util.enterValue(handlingTimeInputField, value);
		util.contextualSaveBarIsNotDisplayed();
	}

	public void enterInvalidHandlingTimeAndSave() {
		util.click(backBtn);

		util.click(addNewTemplateBtn);
		util.hold(2);
		util.enableAndDisableElement(enableInventorySettingsCheckbox);
		util.enableButton(enableInventorySettingsCheckbox);
		util.clear(handlingTimeInputField);
		util.enterValue(handlingTimeInputField, "500");
		clickSaveBtnErrIsDisplayed();
	}

	public void continueSellingCheckboxCheckedUncheckedContextualSaveBarIsDisplayed() {
		util.click(backBtn);

		util.click(addNewTemplateBtn);
		util.hold(2);
		util.enableButton(enableInventorySettingsCheckbox);
		util.enableAndDisableElement(continueSellingCheckbox);
		util.contextualSaveBarIsDisplayed();
		contextualBarContainsDiscardAndSaveBtn();
		util.enableAndDisableElement(continueSellingCheckbox);
		util.discardAndSaveBtnAreNotDisplayed();
	}

	public void changeDefaultSettingsAndValidateContextualSaveBarIsDisplayed(String checkboxName) {
		util.click(backBtn);
		util.click(addNewTemplateBtn);
		util.hold(2);
		util.enableButton(enableInventorySettingsCheckbox);
		changeDefaultSettings(checkboxName);
		util.contextualSaveBarIsDisplayed();
		contextualBarContainsDiscardAndSaveBtn();
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
		case "imagesCheckbox":
			util.enableAndDisableElement(imagesCheckBox);
			break;
		case "productDetailsCheckbox":
			util.enableAndDisableElement(productDetailsCheckBox);
			break;
		case "barCodeExemptionCheckbox":
			util.enableAndDisableElement(barCodeExemptionCheckbox);
			break;
		case "delOutOfStockCheckBox":
			util.enableAndDisableElement(delOutOfStockCheckBox);
			break;
		case "overrideExistingProductsCheckBox":
			util.enableAndDisableElement(overrideExistingProductsCheckBox);
			break;
		default:
			break;
		}
	}

	public void selectDeSelelctInventoryCheckBox() {

		if (warehouseListCheckboxCustomTemplate.isEmpty()) {
			util.contextualSaveBarIsNotDisplayed();
			util.discardAndSaveBtnAreNotDisplayed();
		} else {
			util.click(warehouseListCheckboxCustomTemplate.get(0));
			util.contextualSaveBarIsDisplayed();
			contextualBarContainsDiscardAndSaveBtn();
		}
	}

	public void enablePriceSyncingAndSave() {

		if (util.extractValueByAttributes(enablePriceSyncCheckbox, ARIACHECKED).equals(FALSEVAL)) {
			util.click(enablePriceSyncCheckbox);
			clickOnSaveBtn();
		}
	}

	public void enableThresoldInventoryAndSave() {

		if (util.extractValueByAttributes(thresoldInventoryCheckbox, ARIACHECKED).equals(FALSEVAL)) {
			util.click(thresoldInventoryCheckbox);
			clickOnSaveBtn();
		}
	}

	public void enableFixedInventoryAndSave() {

		if (util.extractValueByAttributes(setFixedInventoryCheckbox, ARIACHECKED).equals(FALSEVAL)) {
			util.click(setFixedInventoryCheckbox);
			clickOnSaveBtn();
		}
	}

	public void enableReserveInventoryAndSave() {

		util.disableButton(setFixedInventoryCheckbox);

		if (util.extractValueByAttributes(setReserverInventoryCheckbox, ARIACHECKED).equals(FALSEVAL)) {
			util.click(setReserverInventoryCheckbox);
			clickOnSaveBtn();
		}
	}

	public void changeThresoldInvenventory() {
		String value = util.extractValueByAttributes(thresoldInventoryInputField, VAL);

		if (value.equals("")) {
			util.enterValue(thresoldInventoryInputField, "1");
			clickOnSaveBtn();
		} else {
			value = util.extractValueByAttributes(thresoldInventoryInputField, VAL);
			int thresoldLimit = Integer.parseInt(value) + 1;
			util.enterValue(thresoldInventoryInputField, String.valueOf(thresoldLimit));
			util.contextualSaveBarIsDisplayed();
			contextualBarContainsDiscardAndSaveBtn();
			util.enterValue(thresoldInventoryInputField, value);
			util.contextualSaveBarIsNotDisplayed();
			util.discardAndSaveBtnAreNotDisplayed();
		}
	}

	public void changeFixedInvenventory() {
		String value = util.extractValueByAttributes(setFixedInventoryInputField, VAL);

		if (value.equals("")) {
			util.enterValue(setFixedInventoryInputField, "1");
			clickOnSaveBtn();
		} else {
			value = util.extractValueByAttributes(setFixedInventoryInputField, VAL);
			int thresoldLimit = Integer.parseInt(value) + 1;
			util.enterValue(setFixedInventoryInputField, String.valueOf(thresoldLimit));
			util.contextualSaveBarIsDisplayed();
			contextualBarContainsDiscardAndSaveBtn();
			util.enterValue(setFixedInventoryInputField, value);
			util.contextualSaveBarIsNotDisplayed();
			util.discardAndSaveBtnAreNotDisplayed();
		}
	}

	public void changeReserveInvenventory() {
		String value = util.extractValueByAttributes(setReserverInventoryInputField, VAL);

		if (value.equals("")) {
			util.enterValue(setReserverInventoryInputField, "1");
			clickOnSaveBtn();
		} else {
			value = util.extractValueByAttributes(setReserverInventoryInputField, VAL);
			int thresoldLimit = Integer.parseInt(value) + 1;
			util.enterValue(setReserverInventoryInputField, String.valueOf(thresoldLimit));
			util.contextualSaveBarIsDisplayed();
			contextualBarContainsDiscardAndSaveBtn();
			util.enterValue(setReserverInventoryInputField, value);
			util.contextualSaveBarIsNotDisplayed();
			util.discardAndSaveBtnAreNotDisplayed();
		}
	}

	public void changeAdvanceSelection() {
		util.click(editFiltersBtn);
		util.selectByValue(selectBy.get(0), "sku");
		util.contextualSaveBarIsDisplayed();
		contextualBarContainsDiscardAndSaveBtn();
	}

}
