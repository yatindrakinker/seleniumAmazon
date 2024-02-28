package com.ami.pageobjects;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ami.resources.Utilities;

public class ProductEditOfferListingPage extends ProductEditPageOR {

	Utilities util;
	CustomTemplatePage ctp;
	CreateShopifyProduct create;
	private String value = "value";
	private String ariaChecked = "aria-checked";
	private String falseVal = "false";
	private String trueVal = "true";
	public static final String INVENTORY = "50";
	public static final String MAXINVENTORY = "100";
	private String customPrice = "50";
	private String currentPageUrl = "";

	public ProductEditOfferListingPage(Utilities util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
	}

	/**
	 * click on save button
	 */
	public void clickOnSaveBtn() {
		util.switchToDefaultContent();
		util.click(saveBtn);
		util.hold(4);
		util.switchToIFrame();
	}

	public void clickOnEditProduct() {
		util.click(kebabMenuListListing.get(0));
		util.click(editProduct);
	}

	/**
	 * Assign default category to the product
	 * 
	 * @param name
	 */
	public void assignDefaultCategoryToProduct(String name) {
		ctp = new CustomTemplatePage(util);
		util.enterValue(templateNameInputField, name);
		ctp.selectDefaultcategoryAndRequireAttribute();
		ctp.selectAmazonRecommendationValueFromDropDown();
		util.enterValue(handlingTimeInputField, "5");
		manualSelection(name);
		waitToTemplateAssign();
	}
	
	public void assignDefaultCategoryToProductWithSetShopifyAttribute(String name,String key) {
		ctp = new CustomTemplatePage(util);
		util.enterValue(templateNameInputField, name);
		util.hold(15);
		ctp.selectDefaultcategoryAndSetShopifyAttribute();
		ctp.selectSetShopifyAttribute(key);
		util.enterValue(handlingTimeInputField, "5");
		manualSelection(name);
		waitToTemplateAssign();
	}
	
	public void changeShopifyAttribute(String key) {
//		setShopifyAttributeSelectorInput
		util.actionClick(setShopifyAttributeSelectorInput.get(0));
		ctp.selectSetShopifyAttribute(key);
		clickOnSaveBtn();
		util.hold(5);
		waitToTemplateAssign();
	}

	public void waitToTemplateAssign() {
		do {
			goToTemplates();
		} while (!templateAssignProgressBar.isEmpty());
	}
	
	public void goToTemplates() {
		util.hold(5);
		util.refreshPage();
		util.hold(5);
		util.switchToDefaultContent();
		util.hold(1);
		util.switchToIFrame();
		util.hold(1);
		util.click(templatesSectionButton);
		util.hold(1);
		util.waitUntilElementIsVisible(templateNameKebabMenuList.get(0), 30);
	}

	public void clickOnCross() {
		util.click(crossButton);
	}
	
	public void searchTemplate(String name) {
		util.enterValue(searchTemplateInputField, name);
		util.waitUntilElementIsVisible(templateNameKebabMenuList.get(0));
	}
	
	public void searchAndEditTemplate(String name) {
		util.enterValue(searchTemplateInputField, name);
		util.waitUntilElementIsVisible(templateNameKebabMenuList.get(0));
		util.scrollToBottom();
		util.hold(1);
		util.actionClick(templateNameKebabMenuList.get(0));
		util.hold(1);
		util.actionClick(editTemplate);
		util.waitUntilElementIsVisible(searchCategoryLabel, 30);
	}
	
	public void mapLinkIsDisplayed() {
		boolean isTrue = false;
		if (!mapAttributeValuesLink.isEmpty()) {
			isTrue = true;
		}
		
		Assert.assertTrue(isTrue);
	}
	
	public void mapLinkIsNotDisplayed() {
		boolean isTrue = false;
		if (mapAttributeValuesLink.isEmpty()) {
			isTrue = true;
		}
		
		Assert.assertTrue(isTrue);
	}
	
	public void waitToValueMapLinkIsVisible(String name) {
		do {
			util.hold(5);
			util.refreshPage();
			util.hold(5);
			util.switchToDefaultContent();
			util.hold(1);
			util.switchToIFrame();
			util.hold(1);
			util.waitUntilElementIsVisible(templatesSectionButton, 30);
			util.click(templatesSectionButton);
			util.hold(1);
			util.waitUntilElementIsVisible(templateNameKebabMenuList.get(0), 30);
			searchTemplate(name);
		}while(mapAttributeValuesLink.isEmpty());
	}
	
	/**
	 * Select product manually to assign the template to the product
	 * 
	 * @param name
	 */
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

	/**
	 * Wait for a product until it is created in app.
	 */
	public void waitToDisplayProduct() {
		do {
			util.hold(5);
			util.click(browseManualProductsButton);
		} while (!noProductFound.isEmpty());
	}

	public void editProduct(String name) {
		util.enterValue(searchInpField, name);
		util.hold(10);
		util.pressEscape();
		util.click(searchInpField);
		util.waitUntilElementIsVisible(searchProductList.get(0), 30);
		util.click(searchProductList.get(0));
		util.hold(5);
		util.click(kebabMenuListListing.get(0));
		util.click(editProduct);
		util.waitUntilElementIsVisible(viewOnShopify.get(0), 30);

	}

	public void offerListingRadioButtonIsSelected() {
		util.validateIsElementSelected(offerListingCheckbox);
	}

	public void barCodeExemptionIsNotAvailableInOfferListing() {
		util.listIsNotEmpty(barcodeCheckBoxList);
	}

	public void skuInputFieldContainsValue(String sku) {
		boolean isTrue = false;

		if (util.extractValueByAttributes(setSKUForAmazonInpField, value).equals(sku)) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);
	}

	public void barcodeInputFieldContainsValue(String barcode) {
		boolean isTrue = false;

		if (util.extractValueByAttributes(setSameBarcodeForShopifyAndAmazonInputField, value).equals(barcode)) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);
	}

	public void requiredAttributeSectionIsDisplayed() {
		util.isElementDisplyedAndValidate(requiredAttributesHeading);
	}

	public void requiredAttributeSectionAttribute() {
		util.click(setShopifyAttribute.get(0));
		util.isElementDisplyedAndValidate(setShopifyAttributeOption);
		util.isElementDisplyedAndValidate(setAmazonRecommentdationSelector);
	}

	public void sectionInOfferListing() {
		WebElement[] arrElements = { priceHeading, inventoryHeading, skuHeading, barcodeSectionHeading,
				attributesSectionHeading, optionalAttributeSection };
		for (WebElement ele : arrElements) {
			util.isElementDisplyedAndValidate(ele);
		}
	}

	public void eachSectionContainsTwoRadioButtons() {
		WebElement[] arrElements = { setSameProductPriceForAmazonCheckBox, setCustomPriceForAmazonCheckBox,
				setSameProductInventoryForShopifyAndAmazonCheckBox, setCustomProductInventoryForAmazonCheckBox,
				setSameSKUForShopifyAndAmazonCheckBox, setCustomSKUCheckBox, setSameBarcodeForShopifyAndAmazonCheckBox,
				setCustomBarcodeCheckBox };

		for (WebElement ele : arrElements) {
			util.isElementDisplyedAndValidate(ele);
		}
	}

	public void simpleProductDoesNotContainVariantSectionInOfferListing() {
		util.listIsEmpty(variantsList);
	}

	public void setCustomValuesAndValidate() {

		String newPrice = "50";
		String newInventory = "5";
		String newBarcode = util.extractValueByAttributes(setSameBarcodeForShopifyAndAmazonInputField, value);
		newBarcode = "new" + newBarcode;
		util.click(setCustomPriceForAmazonCheckBox);
		util.enterValue(setCustomPriceForAmazonInpField, newPrice);
		util.click(setCustomQuantityForAmazonCheckbox);
		util.enterValue(setCustomQuantityForAmazonInpField, newInventory);
		util.click(setCustomBarcodeCheckBox);
		util.enterValue(setCustomBarcodeInpField, newBarcode);
		clickOnSaveBtn();
		util.click(backBtn);
		util.waitUntilElementIsVisible(kebabMenuListListing.get(0), 20);
		validateCustomPrice(newPrice);
		validateCustomBarcode(newBarcode);
		validateCustomInventory(newInventory);

	}

	public void validateCustomPrice(String newPrice) {
		boolean isTrue = false;
		if (priceListingGrid.getText().equals(newPrice)) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);
	}

	public void validateCustomBarcode(String newBarcode) {
		boolean isTrue = false;

		if (barcodeListingGrid.getText().equals(newBarcode)) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);

	}

	public void validateCustomInventory(String newInventory) {
		boolean isTrue = false;
		String inventory = inventoryListingGrid.getText();
		inventory = StringUtils.substringBefore(inventory, "in").trim();

		if (inventory.equals(newInventory)) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);
	}

	public void mouseHoverAtInventoryVal() {
		boolean isTrue = false;

		if (!setSameQuantityForShopifyAndAmazonCheckbox.isSelected()) {
			util.click(setSameQuantityForShopifyAndAmazonCheckbox);
			clickOnSaveBtn();
			util.hold(5);
		}
		util.moveToElement(inventoryValue);
		util.isElementDisplyedAndValidate(inventoryBreakup);
		util.isElementDisplyedAndValidate(shopifyInventoryInInventoryBreakup);
		util.isElementDisplyedAndValidate(calculatedInventoryInInventoryBreakup);

		if (shopifyInventoryInInventoryBreakup.getText().equals(CreateShopifyProduct.iventoryOfProduct)) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);
	}

	public void openEditPrdouctInListing() {
		util.waitUntilElementIsVisible(kebabMenuListListing.get(0), 20);
		util.click(kebabMenuListListing.get(0));
		util.waitUntilElementIsVisible(editProduct, 20);
		util.click(editProduct);
	}

	public void openEditTemplate(String templateName) {
		currentPageUrl = util.getPageURL();
		util.openAndSwitchToNewTab();
		util.openUrl(currentPageUrl);
		util.clickOnSettings();
		util.click(templateButton);
		util.enterValue(searchTemplateInputField, templateName);
		util.waitUntilElementIsVisible(kebabMenuTemplates.get(0), 30);
		util.scrollToBottom();
		util.hold(2);
		util.click(kebabMenuTemplates.get(0));
		util.hold(2);
		util.waitUntilElementIsVisible(editTemplate, 30);
		util.click(editTemplate);
	}

	public void saveTemplateAndOpenEditProduct() {
		clickOnSaveBtn();
		util.hold(1);
		util.gotoWindowByClosingCurrentOne(currentPageUrl);
		util.refreshPage();
		util.hold(2);
		util.switchToIFrame();
		openEditPrdouctInListing();
	}

	public void setFixedInventory(String templateName) {
		String isFixedInventoryCheckboxChecked;
		String isReservedInventoryCheckboxChecked;
		openEditTemplate(templateName);
		util.hold(2);
		isFixedInventoryCheckboxChecked = util.extractValueByAttributes(setFixedInventoryCheckbox, ariaChecked);
		isReservedInventoryCheckboxChecked = util.extractValueByAttributes(setReserverInventoryCheckbox, ariaChecked);

		if (isFixedInventoryCheckboxChecked.equals(trueVal)) {
			util.enterValue(setFixedInventoryInputField, INVENTORY);
		} else if (isReservedInventoryCheckboxChecked.equals(trueVal)
				&& isFixedInventoryCheckboxChecked.equals(falseVal)) {
			util.click(setReserverInventoryCheckbox);
			util.hold(1);
			util.click(setFixedInventoryCheckbox);
			util.hold(1);
			util.enterValue(setFixedInventoryInputField, INVENTORY);
		} else if (isReservedInventoryCheckboxChecked.equals(falseVal)
				&& isFixedInventoryCheckboxChecked.equals(falseVal)) {
			util.click(setFixedInventoryCheckbox);
			util.hold(1);
			util.enterValue(setFixedInventoryInputField, INVENTORY);
		}
		saveTemplateAndOpenEditProduct();
	}

	public void validateFixedInventory() {
		boolean isTrue = false;

		if (!setSameProductInventoryForShopifyAndAmazonCheckBox.isSelected()) {
			util.click(setSameProductInventoryForShopifyAndAmazonCheckBox);
			clickOnSaveBtn();
		}
		String val = util.getTagValue(inventoryValue);

		if (val.equals(INVENTORY)) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);

		isTrue = false;
		util.moveToElement(inventoryValue);

		if ((shopifyInventoryInInventoryBreakup.getText().equals(CreateShopifyProduct.iventoryOfProduct))
				&& (calculatedInventoryInInventoryBreakup.getText().equals(INVENTORY))) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);
	}

	public void setReservedInventory(String templateName) {
		String isFixedInventoryCheckboxChecked;
		String isReservedInventoryCheckboxChecked;
		openEditTemplate(templateName);
		isFixedInventoryCheckboxChecked = util.extractValueByAttributes(setFixedInventoryCheckbox, ariaChecked);
		isReservedInventoryCheckboxChecked = util.extractValueByAttributes(setReserverInventoryCheckbox, ariaChecked);
		util.hold(20);
		if (isReservedInventoryCheckboxChecked.equals(trueVal)) {
			util.enterValue(setReserverInventoryInputField, INVENTORY);
		} else if (isReservedInventoryCheckboxChecked.equals(falseVal)
				&& isFixedInventoryCheckboxChecked.equals(trueVal)) {
			util.click(setFixedInventoryCheckbox);
			util.hold(1);
			util.click(setReserverInventoryCheckbox);
			util.hold(1);
			util.enterValue(setReserverInventoryInputField, INVENTORY);
		} else if (isReservedInventoryCheckboxChecked.equals(falseVal)
				&& isFixedInventoryCheckboxChecked.equals(falseVal)) {
			util.click(setReserverInventoryCheckbox);
			util.hold(1);
			util.enterValue(setReserverInventoryInputField, INVENTORY);
		}

		saveTemplateAndOpenEditProduct();
	}

	public void validateReservedInventory() {
		boolean isTrue = false;
		String val = util.getTagValue(inventoryValue);
//		Inventory in product  = Reserved inventory - inventory in shopify
		int calculatedInventory = Integer.parseInt(CreateShopifyProduct.iventoryOfProduct)
				- Integer.parseInt(INVENTORY);

		if (Integer.parseInt(CreateShopifyProduct.iventoryOfProduct) == (Integer.parseInt(INVENTORY))) {
			if (val.equals("0")) {
				isTrue = true;
			}
		} else if (Integer.parseInt(CreateShopifyProduct.iventoryOfProduct) < (Integer.parseInt(INVENTORY))) {
			if (val.equals("0")) {
				isTrue = true;
			}
		} else if (Integer.parseInt(CreateShopifyProduct.iventoryOfProduct) > (Integer.parseInt(INVENTORY))
				&& (val.equals(String.valueOf(calculatedInventory)))) {
			isTrue = true;

		}
		Assert.assertTrue(isTrue);

	}

	public void setMaximumInventory(String templateName) {
		openEditTemplate(templateName);
		util.hold(15);
		String isMaxInventoryCheckboxChecked = util.extractValueByAttributes(continueSellingCheckbox, ariaChecked);

		if (isMaxInventoryCheckboxChecked.equals(falseVal)) {
			util.click(continueSellingCheckbox);
			util.enterValue(setMaxInventoryInputField, MAXINVENTORY);
		} else {
			util.enterValue(setMaxInventoryInputField, MAXINVENTORY);
		}

		saveTemplateAndOpenEditProduct();
	}

	public void validateMaximumInventory() {
		boolean isTrue = false;
		String val = util.getTagValue(inventoryValue);

		if (val.equals(MAXINVENTORY)) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);
	}

	public void setWarehouseInventory(String templateName) {
		String url = util.getPageURL();
		create = new CreateShopifyProduct(util);
		util.openAndSwitchToNewTab();
		util.openUrl(CreateShopifyProduct.productUrl);
		create.updateWareHouseInventory();
		create.getWareHouseName();
		util.gotoWindowByClosingCurrentOne(url);
		openEditTemplate(templateName);
		activeWareHouses();
	}

	public void activeWareHouses() {
		WebElement checkbox;
		List<String> wareHouseToBeRemainActive = create.returnWareHouseName();
		for (WebElement ele : wareHouseNameListInTemplate) {
			if (ele.getText().contains(wareHouseToBeRemainActive.get(0))
					|| ele.getText().contains(wareHouseToBeRemainActive.get(1))) {
				checkbox = ele.findElement(By.xpath("//preceding-sibling::span[1]//input"));

				if (util.extractValueByAttributes(checkbox, ariaChecked).equals(falseVal)) {
					util.click(checkbox);
				}
			}
		}
	}

	public void validateInventory() {
		boolean isTrue = false;
		String val = util.getTagValue(inventoryValue);
		String shopifyInventoryVal = String.valueOf(Integer.parseInt(CreateShopifyProduct.updatedInventory)
				+ Integer.parseInt(CreateShopifyProduct.inventoryInSecondWareHouse));

		if (val.equals(shopifyInventoryVal)) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);
	}

	public void setCustomProductInventory() {
		if (!setCustomProductInventoryForAmazonCheckBox.isSelected()) {
			util.click(setCustomProductInventoryForAmazonCheckBox);
			util.hold(1);
		}

		util.listIsEmpty(inventoryValueList);
	}

	public void customInventoryInputFieldContainsVal() {
		boolean isTrue = false;
		util.enterValue(setCustomQuantityForAmazonInpField, "5");
		String val = util.extractValueByAttributes(setCustomQuantityForAmazonInpField, value);

		if (val.equals("5")) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);
	}

	public void hoverAtPrice() {

		if (setCustomPriceForAmazonCheckBox.isEnabled()) {
			util.click(setSamePriceForShopifyAndAmazonCheckbox);
			util.hold(1);
			clickOnSaveBtn();
		}

		util.moveToElement(priceValue);

		util.isElementDisplyedAndValidate(shopifyPriceAtHover);
		util.isElementDisplyedAndValidate(priceValAtHover);
	}

	public void enterCustomPrice() {

		if (!setCustomPriceForAmazonCheckBox.isEnabled()) {
			util.click(setCustomPriceForAmazonCheckBox);
			util.hold(1);
			clickOnSaveBtn();
		}

		util.enterValue(setCustomPriceForAmazonInpField, customPrice);
	}

	public void customPriceInputFieldAcceptsVal() {
		boolean isTrue = false;

		if (!util.extractValueByAttributes(setCustomPriceForAmazonInpField, value).equals("")) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);
	}

	public void priceBreakupIsNotVisibleWhenCustomPriceIsEnabled() {
		util.listIsEmpty(priceValueList);
	}

	public String getAttributeValue() {
		return attributeVal.get(0).getText();
	}

	public void requiredAttributesValGetsReset() {
		util.click(newListingCheckbox);
		util.hold(1);
		util.click(proceedBtn);
		util.hold(1);
		util.click(offerListingCheckbox);
		util.hold(1);
		util.click(proceedBtn);
		util.hold(1);
		util.listIsEmpty(attributeVal);
	}

	public void selectAttributes() {
		util.click(setShopifyAttribute.get(0));
		util.hold(1);
		util.click(selectAmazonRecommendation);
		util.hold(1);
		util.click(setAmazonRecommentdationSelector);
		util.hold(1);
		util.click(newOption);
		util.hold(1);
		util.listIsNotEmpty(attributeVal);
	}
	
	public void clickOnDifferentListAsButDoNotConfirmIt() {
		util.click(newListingCheckbox);
		util.hold(1);
		util.click(crossButton);
		util.hold(2);
		util.listIsNotEmpty(attributeVal);
	}
}
