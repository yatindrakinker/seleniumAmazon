package com.ami.pageobjects.reusableclasses;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ami.pageobjects.CreateShopifyProduct;
import com.ami.pageobjects.CustomTemplatePage;
import com.ami.pageobjects.OrderPage;
import com.ami.resources.Utilities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import getstorefromdbpojo.GetAllStoresFromDBPojo;
import getstorefromdbpojo.StoreAddress;
import getstorefromdbpojo.StoreData;
import getstorefromdbpojo.Stores;
import locasellingdisabledorderresponsepojo.LocalSellingOrderDisabledPojo;
import orderresponsepojo.OrderPojo;

public class ReusableMethods extends ReusableMethodsOR {
	Utilities util;
	Random random;
	CustomTemplatePage ctp;
	private String templateName;

	public ReusableMethods(Utilities util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
		ctp = new CustomTemplatePage(util);
	}

	public void goToOverview() {
		util.goToSection(OVERVIEWSECTION);
	}

	public void goToLinking() {
		util.goToSection(LINKINGSECTION);
		util.refreshPage();
		util.switchToIFrame();
		util.waitUntilElementIsVisible(linkingReqTab, 30);
	}

	public void goToTemplatesInSettings() {
		util.goToSection(SETTINGSSECTION);
		util.refreshPage();
		util.switchToIFrame();
		util.waitUntilElementIsVisible(productTemplates, 30);
	}

	public void openNewTemplate() {
		util.click(productTemplates);
		util.click(addNewTemplateBtn);
		util.waitUntilElementIsClickable(searchCategoryInputField, 60);
	}

	public void goToSettings() {
		util.goToSection(SETTINGSSECTION);
	}

	public void clickOnSettings() {
		util.switchToDefaultContent();
		util.click(salesChannel);

		if (util.getConfigProperty(STORE).equalsIgnoreCase("live")) {
			util.click(cedAmzLive);
		} else if (util.getConfigProperty(STORE).equalsIgnoreCase("staging")) {
			util.click(cedAmzStaging);
		}

		util.click(cedAmzSettings);
		util.switchToIFrame();
		util.waitUntilElementIsVisible(templatesTab, 20);
	}

	public void createNewTemplateUsingAdvanceSelection() {
		stepsToFillMandatoryData();
		fetchShopifyProdByAdvanceSelection();
		validateProductIsImportedOnAppAndSave();
		waitToTemplateAssign();

	}

	public void waitToTemplateAssign(String templateName) {
		do {
			goToTemplates(templateName);
		} while (!templateAssignProgressBar.isEmpty());
	}

	public void goToTemplates(String templateName) {
		verifyTemplateIsCreated(templateName);
		util.hold(1);
		util.waitUntilElementIsVisible(templateNameKebabMenuList.get(0), 30);
	}

	public void verifyTemplateIsCreated(String templateName) {
		searchTemplateOnGrid(templateName);
		Assert.assertEquals(templateNameOnGrid.get(0).getText().trim(), templateName, "Template name is not matching.");

	}

	public void createNewTemplateUsingAdvanceSelection(String templateName) {
		System.out.println("template name = " + templateName);
		stepsToFillMandatoryData(templateName);
		fetchShopifyProdByAdvanceSelection(templateName);
		validateProductIsImportedOnAppAndSave();
		waitToTemplateAssign(templateName);

	}

	public void fetchShopifyProdByAdvanceSelection(String templateName) {
		util.click(advancedSelectionRadioBtn);
		util.selectByValue(selectByAttributeDropDown, "title");
		util.selectByValue(selectByConditionropDown, "equals");
		util.enterValue(valueInputFieldAdvancedSelection, templateName);
		util.click(seeQueryResultBtn);
		util.hold(2);
	}

	public void stepsToFillMandatoryData(String templateName) {
		util.switchToIFrame();
		util.click(productTemplates);
		util.click(addNewTemplateBtn);
		util.enterValue(templateNameInputField, templateName);
		selectDefaultcategoryAndRequireAttribute();
		selectAmazonRecommendationValueFromDropDown();
		util.enterValue(handlingTimeInputField, "3");

	}

	public void createNewTemplateUsingManualSelection() {
		stepsToFillMandatoryData();
		fetchShopifyProdByManualSelection();
		validateProductIsImportedOnAppAndSave();
		waitToTemplateAssign();

	}

	public void validateProductIsImportedOnAppAndSave() {
		int maxRetries = 5;

		for (int i = 0; i < maxRetries; i++) {
			if (!productIsImportedOnAppSuccessfully.isEmpty()) {
				clickOnSave();
				util.waitUntilElementIsVisible(mapValuesBtn, 120);
				return; // Exit the loop if successful
			}

			util.hold(30);
			util.click(seeQueryResultBtn);
			util.hold(2);

			if (i > 5) {
				Assert.assertTrue(false, "Product is not imported on the app, cannot proceed further.");
			}
		}
	}

	public void verifyProductIsImported() {
		int maxRetries = 6;

		for (int retryCount = 0; retryCount < maxRetries; retryCount++) {
			// Check if the product is imported successfully
			if (!productIsImportedOnAppSuccessfully.isEmpty()) {
				// Product is imported successfully, no need to retry
				return;
			}

			// Retry logic
			util.hold(30);
			util.click(seeQueryResultBtn);
			util.hold(2);

			if (retryCount > 5) {
				Assert.assertTrue(false, "Product is not imported on the app, cannot proceed further.");
			}
		}

	}

	public void stepsToFillMandatoryData() {
		templateName = CreateShopifyProduct.nameOfProduct;
		util.switchToIFrame();
		util.click(productTemplates);
		util.click(addNewTemplateBtn);
		util.enterValue(templateNameInputField, templateName);
		selectDefaultcategoryAndRequireAttribute();
		selectAmazonRecommendationValueFromDropDown();
		util.enterValue(handlingTimeInputField, "3");

	}

	public void openProductTemplatesPage() {
		util.click(productTemplates);
		util.click(addNewTemplateBtn);
	}

	public void gotoTemplateGrid() {
		util.refreshPage();
		util.switchToIFrame();
		util.click(productTemplates);
		util.waitUntilElementIsVisible(searchTemplateNameInputField, 30);
	}

	public void validateSearchInputDoesNotAcceptsSpecialChars(String inputVal) {
		boolean isTrue = false;
		util.enterValue(searchTemplateNameInputField, inputVal);
		boolean acceptsSpecialChar = util
				.matchIfStringContainsSpecialChar(searchTemplateNameInputField.getAttribute(VAL));
		boolean containsUnderScore = searchTemplateNameInputField.getAttribute(VAL).contains("_");

		if (!acceptsSpecialChar && !containsUnderScore) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);
	}

	public String addNewTemplateName(String name) {
		templateName = name;
		util.enterValue(templateNameInputField, templateName);
		return templateName;
	}

	public void fetchShopifyProdByAdvanceSelection() {
		templateName = CreateShopifyProduct.nameOfProduct;
		util.click(advancedSelectionRadioBtn);
		util.selectByValue(selectByAttributeDropDown, "title");
		util.selectByValue(selectByConditionropDown, "equals");
		util.enterValue(valueInputFieldAdvancedSelection, templateName);
		util.click(seeQueryResultBtn);
		util.hold(2);
	}

	public void addNewProductInTemplate(String name) {
		util.click(editFiltersBtn);
		util.click(overrideExistingProductsCheckBox);
		util.selectByValue(selectByAttributeDropDown, "title");
		util.selectByValue(selectByConditionropDown, "equals");
		util.enterValue(valueInputFieldAdvancedSelection, name);
		util.click(seeQueryResultBtn);
	}

	public void addNewProductInTemplate(String selectBy1, String selectBy2, String name) {
//		util.click(editFiltersBtn);
		util.click(overrideExistingProductsCheckBox);
		util.selectByValue(selectByAttributeDropDown, selectBy1);
		util.selectByValue(selectByConditionropDown, selectBy2);
		util.enterValue(valueInputFieldAdvancedSelection, name);
		util.click(seeQueryResultBtn);
	}

	public void fetchShopifyProdByManualSelection() {
		templateName = CreateShopifyProduct.nameOfProduct;
		util.click(manualRadioBtn);
		util.enterValue(manualSelectionInpField, templateName);

		util.click(browseManualProductsButton);
		util.hold(2);
		boolean isModalDisplayed = headingOfModal.isEmpty(); // headingModal isNotDisplayed()

		while (isModalDisplayed) {
			util.hold(10);
			util.click(browseManualProductsButton);
			isModalDisplayed = headingOfModal.isEmpty();
		}

		util.click(checkBoxInManualSelectionModal);
		util.click(continueBtnManualSelectionDialogBox);

		util.hold(2);
	}

	public void enableOverrideExistingCheckbox() {
		if (overrideExistingProductsCheckBox.getAttribute(ARIACHECKED).equals(FALSEVAL)) {
			util.click(overrideExistingProductsCheckBox);
		}
	}

	public void waitToTemplateAssign() {
		util.hold(5);
		do {
			goToTemplates();
		} while (!templateAssignProgressBar.isEmpty());
	}

	public void searchTemplateOnGrid() {
		templateName = CreateShopifyProduct.nameOfProduct;
		util.refreshPage();
		util.switchToIFrame();
		util.waitUntilElementIsVisible(productTemplates, 30);
		util.click(productTemplates);
		util.enterValue(templateSearchInputField, templateName);
		util.hold(2);
		util.waitUntilElementIsVisible(templateNameOnGrid.get(0), 30);
	}

	public void searchTemplateOnGrid(String templateName) {
		util.refreshPage();
		util.switchToIFrame();
		util.waitUntilElementIsVisible(productTemplates, 30);
		util.click(productTemplates);
		util.enterValue(templateSearchInputField, templateName);
		util.hold(2);
		util.waitUntilElementIsVisible(templateNameOnGrid.get(0), 30);
	}

	public void verifyTemplateIsCreated() {
		searchTemplateOnGrid();
		Assert.assertEquals(templateNameOnGrid.get(0).getText().trim(), templateName, "Template name is not matching.");

	}

	public void verifyTemplateIsCreatedForExistingProduct(String name) {
		boolean isTrue = false;
		searchTemplateOnGrid();

		for (WebElement nameTemp : templateNameOnGrid) {
			if (nameTemp.getText().trim().equals(name)) {
				isTrue = true;
				break;
			}

		}
		Assert.assertTrue(isTrue, "Template is not created");

	}

	public void searchAndOpenEditTemplatePage(String name) {
		searchTemplateOnGrid();

		for (int i = 0; i < templateNameOnGrid.size(); i++) {

			if (templateNameOnGrid.get(i).getText().trim().equals(name)) {
				clickOnEditTempalteBtn(templateNameKebabMenuList.get(i));
				break;
			}
		}
		util.waitUntilElementIsClickable(searchCategoryInputField, 45);
	}

	public void openEditTemplatePage(String name) {
		for (int i = 0; i < templateNameOnGrid.size(); i++) {

			if (templateNameOnGrid.get(i).getText().trim().equals(name)) {
				clickOnEditTempalteBtn(templateNameKebabMenuList.get(i));
				break;
			}
		}

		util.waitUntilElementIsClickable(searchCategoryInputField, 45);
	}

	public void searchAndCloneTemplate(String name) {
		searchTemplateOnGrid();

		for (int i = 0; i < templateNameOnGrid.size(); i++) {

			if (templateNameOnGrid.get(i).getText().trim().equals(name)) {
				clickOnCloneTempalteBtn(templateNameKebabMenuList.get(i));
				break;
			}
		}

		enableOverrideExistingCheckbox();
		fetchShopifyProdByAdvanceSelection();

	}

	public void searchAndDeleteTemplate(String name) {
		searchTemplateOnGrid();

		for (int i = 0; i < templateNameOnGrid.size(); i++) {

			if (templateNameOnGrid.get(i).getText().trim().equals(name)) {
				clickOnDeleteTempalteBtn(templateNameKebabMenuList.get(i));
				break;
			}
		}

	}

	private void clickOnEditTempalteBtn(WebElement kebabMenu) {

		if (kebabMenu.getAttribute(ARIAEXPANDED).equals(FALSEVAL)) {
			util.scrollToBottom();
			util.hold(1);
			util.click(kebabMenu);
			util.hold(1);
			editBtn.click();
		}
	}

	public void clickOnCloneTempalteBtn(WebElement kebabMenu) {

		if (kebabMenu.getAttribute(ARIAEXPANDED).equals(FALSEVAL)) {
			util.scrollToBottom();
			util.hold(1);
			util.click(kebabMenu);
			util.hold(1);
			util.click(cloneBtn);
		}
	}

	private void clickOnDeleteTempalteBtn(WebElement kebabMenu) {
		if (kebabMenu.getAttribute(ARIAEXPANDED).equals(FALSEVAL)) {
			util.click(kebabMenu);
			util.hold(1);
			util.click(deleteBtn);
			util.hold(1);
			util.click(deleteBtnModal);
			util.waitUntilElementIsVisible(deletedSuccessfullyMsg, 10);
		}
	}

	public void goToTemplates() {
		verifyTemplateIsCreated();
		util.hold(1);
		util.waitUntilElementIsVisible(templateNameKebabMenuList.get(0), 30);
	}

	public void selectDefaultcategoryAndRequireAttribute() {
		selectDefaultCategory();

		util.click(offerConditionType);

		util.hold(1);
		util.jsClick(amazonRecommendationsRadioBtn);
		util.hold(1);
		util.actionClick(setAmazonRecommendationSelector);
		util.hold(1);
	}

	public void selectDefaultCategory() {
		util.click(defaultCategory);
		util.waitUntilElementIsVisible(attributeHeading);
	}

	public void selectAmazonRecommendationValueFromDropDown() {
		util.hold(1);
		util.jsClick(setAmazonRecommendationOptionNew);
	}

	public void clickOnSave() {
		util.switchToDefaultContent();
		util.click(saveBtn);
		util.switchToIFrame();
	}

	public void closeModal() {
		util.click(crossBtnModal);
	}

	/**
	 * clicks on view product btn in edit templates.
	 */
	public void clickOnViewProductsBtn() {
		util.click(viewProductsBtnEditTempPage);
	}

	public void clickOnEditFilters() {
		util.click(editFiltersBtn);
	}

	/**
	 * This method open product on shopify.
	 */
	public void clickOnTemplateToOpenProductInShopify() {
		util.click(productListingNameListing);
		util.waitUntilElementIsVisible(viewOnShopifyBtn, 30);
		util.click(viewOnShopifyBtn);
		util.getWindoHandleByUrl("/products");
	}

	/**
	 * open variants by clicking on edit button
	 */
	public void editVariantOnShopify() {
		util.waitUntilElementIsVisible(editOrDoneVariantsBtnShopify, 20);

		if (editOrDoneVariantsBtnShopify.getAttribute(ARIAEXPANDED).equals(FALSEVAL)) {
			util.click(editOrDoneVariantsBtnShopify);
		}

	}

	/**
	 * select value of Shopify i.e. size, color, material, style
	 * 
	 * @param key
	 */
	public void selectPreDefinedVariants(String key) {
		switch (key) {
		case SIZE:
			for (WebElement name : variantsNameList) {
				if (name.getText().equals(SIZE) && (optionNameInput.getAttribute(ARIAEXPANDED).equals(FALSEVAL))) {
					util.click(optionNameInput);
					util.click(name);
					break;
				}
			}
			break;
		case COLOR:
			for (WebElement name : variantsNameList) {
				if (name.getText().equals(COLOR) && (optionNameInput.getAttribute(ARIAEXPANDED).equals(FALSEVAL))) {
					util.click(name);
					break;
				}
			}
			break;
		case MATERIAL:
			for (WebElement name : variantsNameList) {
				if (name.getText().equals(MATERIAL) && (optionNameInput.getAttribute(ARIAEXPANDED).equals(FALSEVAL))) {
					util.click(name);
					break;
				}
			}
			break;

		case STYLE:
			for (WebElement name : variantsNameList) {
				if (name.getText().equals(STYLE) && (optionNameInput.getAttribute(ARIAEXPANDED).equals(FALSEVAL))) {
					util.click(name);
					break;
				}
			}
			break;
		default:
			break;
		}
	}

	/**
	 * Enter Custom Option Name Input
	 * 
	 * @param key
	 * @param row
	 */
	public void enterCustomOptionName(String key, String row) {
		WebElement optionNameInput = util.getDriver().findElement(By.name("optionName[" + row + "]"));
		util.enterValue(optionNameInput, key);
	}

	/**
	 * Enter Custom Option Values Input
	 * 
	 * @param key
	 * @param row
	 * @param column
	 */
	public void enterCustomValForVariants(String key, String row, String column) {
		WebElement optionValuesInput = util.getDriver()
				.findElement(By.name("optionValue[" + row + "][" + column + "]"));
		util.enterValue(optionValuesInput, key);
	}

	/**
	 * this method clicks on done btn
	 */
	public void clickOnVariantsDoneBtn() {
		if (editOrDoneVariantsBtnShopify.getAttribute(ARIAEXPANDED).equals(TRUEVAL)) {
			util.click(editOrDoneVariantsBtnShopify);
		}
	}

	/**
	 * this method clicks on save btn
	 */
	public void clickOnShopifySaveBtn() {
		util.click(saveBtnShopify);
		util.waitUntilElementIsVisible(saveBtnShopify, 15);
	}

	/**
	 * this method opens the Shopify product assigned in template.
	 */
	public void goToProductFromTemplateAndOpenEdit() {
		util.goToSection("settings");
		searchTemplateOnGrid(CreateShopifyProduct.nameOfProduct);
		openEditTemplatePage(CreateShopifyProduct.nameOfProduct);
		clickOnViewProductsBtn();
		clickOnTemplateToOpenProductInShopify();
		editVariantOnShopify();
	}

	public void navigate(int count) {
		for (int i = 1; i <= count; i++) {
			util.getDriver().navigate();
		}
	}

	/**
	 * creates name that contains random number.
	 * 
	 * @param input
	 * @return
	 */
	public String createRandomName(Map<String, String> input) {
		int randomNum = random.nextInt();
		return input.get("new_template_name") + randomNum;
	}

	public void openTemplate() {
		templateName = CreateShopifyProduct.nameOfProduct;
		util.goToSection("settings");
		util.switchToIFrame();
		util.waitUntilElementIsVisible(templatesTab, 30);
		util.click(templatesTab);
		util.enterValue(templateSearchInputField, templateName);
		util.hold(3);
		util.waitUntilElementIsVisible(kebabMenuList.get(0), 30);

		if (kebabMenuList.get(0).getAttribute("aria-expanded").equals("false")) {
			util.click(kebabMenuList.get(0));
		}

		util.click(editBtn);
	}

	/**
	 * enable inventory settings in template
	 */
	public void enableInventorySettingsInTemplate() {
		String isInventorySettingEnabled = util.extractValueByAttributes(enableInventorySettingsCheckbox, ARIACHECKED);

		if (isInventorySettingEnabled.equals(FALSEVAL)) {
			util.click(enableInventorySettingsCheckbox);
		}
	}

	/**
	 * enable all warehouses in template
	 */
	public void enableAllWareHouses() {
		for (WebElement wareHouse : wareHouseCheckBox) {
			if (util.extractValueByAttributes(wareHouse, ARIACHECKED).equals(FALSEVAL)) {
				util.click(wareHouse);
			}

		}
	}

	/**
	 * enable continue selling checkbox settings in template
	 */
	public void enableContinueSellingCheckBox() {
		String isContinueSellingCheckBoxEnabled = util.extractValueByAttributes(continueSellingCheckbox, ARIACHECKED);

		if (isContinueSellingCheckBoxEnabled.equals(FALSEVAL)) {
			util.click(continueSellingCheckbox);
		}
	}

	/**
	 * disable continue selling check box settings in template
	 */
	public void disableContinueSellingCheckBox() {
		String isContinueSellingCheckBoxEnabled = util.extractValueByAttributes(continueSellingCheckbox, ARIACHECKED);

		if (isContinueSellingCheckBoxEnabled.equals(TRUEVAL)) {
			util.click(continueSellingCheckbox);
		}
	}

	/**
	 * disable fixed inventory check box settings in template
	 */
	public void disableFixedInventoryCheckBox() {
		String isFixedInventoryCheckBoxEnabled = util.extractValueByAttributes(setFixedInventoryCheckbox, ARIACHECKED);

		if (isFixedInventoryCheckBoxEnabled.equals(TRUEVAL)) {
			util.click(continueSellingCheckbox);
		}
	}

	/**
	 * enable reserve inventory check box settings in template
	 */
	public void enableFixedInventoryCheckBox(String fixedInventory) {
		String isReserveInventoryCheckBoxEnabled = util.extractValueByAttributes(setFixedInventoryCheckbox,
				ARIACHECKED);

		String isFixedInventoryCheckBoxEnabled = util.extractValueByAttributes(setFixedInventoryCheckbox, ARIACHECKED);

		if (isReserveInventoryCheckBoxEnabled.equals(FALSEVAL) && isFixedInventoryCheckBoxEnabled.equals(FALSEVAL)) {
			util.click(continueSellingCheckbox);
			util.hold(1);
			util.enableButton(setFixedInventoryCheckbox);
			util.hold(1);
			util.enterValue(setFixedInventoryInputField, fixedInventory);
		} else if (isReserveInventoryCheckBoxEnabled.equals(TRUEVAL)
				&& isFixedInventoryCheckBoxEnabled.equals(FALSEVAL)) {
			util.disableButton(continueSellingCheckbox);
			util.hold(1);
			util.disableButton(setReserverInventoryCheckbox);
			util.hold(1);
			util.enableButton(setFixedInventoryCheckbox);
			util.hold(1);
			util.enterValue(setFixedInventoryInputField, fixedInventory);
		} else if (isFixedInventoryCheckBoxEnabled.equals(TRUEVAL)) {
			util.disableButton(continueSellingCheckbox);
			util.hold(1);
			util.enterValue(setFixedInventoryInputField, fixedInventory);
		}
	}

	/**
	 * disable reserve inventory check box settings in template
	 */
	public void disableReserveInventoryCheckBox() {
		String isReserveInventoryCheckBoxEnabled = util.extractValueByAttributes(setReserverInventoryCheckbox,
				ARIACHECKED);

		if (isReserveInventoryCheckBoxEnabled.equals(TRUEVAL)) {
			util.click(continueSellingCheckbox);
		}
	}

	/**
	 * enable reserve inventory check box settings in template
	 */
	public void enableReserveInventoryCheckBox(String reserveInventory) {
		String isReserveInventoryCheckBoxEnabled = util.extractValueByAttributes(setReserverInventoryCheckbox,
				ARIACHECKED);

		String isFixedInventoryCheckBoxEnabled = util.extractValueByAttributes(setFixedInventoryCheckbox, ARIACHECKED);

		if (isReserveInventoryCheckBoxEnabled.equals(FALSEVAL) && isFixedInventoryCheckBoxEnabled.equals(TRUEVAL)) {
			util.disableButton(continueSellingCheckbox);
			util.disableButton(setFixedInventoryCheckbox);
			util.hold(1);
			util.enableButton(setReserverInventoryCheckbox);
			util.hold(1);
			util.enterValue(setReserverInventoryInputField, reserveInventory);

		} else if (isReserveInventoryCheckBoxEnabled.equals(FALSEVAL)
				&& isFixedInventoryCheckBoxEnabled.equals(FALSEVAL)) {
			util.disableButton(continueSellingCheckbox);
			util.hold(1);
			util.enableButton(setReserverInventoryCheckbox);
			util.hold(1);
			util.enterValue(setReserverInventoryInputField, reserveInventory);
		} else if (isReserveInventoryCheckBoxEnabled.equals(TRUEVAL)) {
			util.enterValue(setReserverInventoryInputField, reserveInventory);
		}
	}

	public void enableAndSetFixedInventory(String fixedInventory) {
		enableInventorySettingsInTemplate();
		enableAllWareHouses();
		disableContinueSellingCheckBox();
		disableReserveInventoryCheckBox();
		enableFixedInventoryCheckBox(fixedInventory);
		saveTemplate();
	}

	public void enableAndSetReservedInventory(String reservedInventory) {
		enableInventorySettingsInTemplate();
		enableAllWareHouses();
		disableContinueSellingCheckBox();
		disableFixedInventoryCheckBox();
		enableReserveInventoryCheckBox(reservedInventory);
		saveTemplate();
	}

	public void enableAndSetContinueSellingWhenOutOfStockInventory() {
		enableInventorySettingsInTemplate();
		enableAllWareHouses();
		enableContinueSellingCheckBox();
		saveTemplate();
	}

	public void waitForImportantInfoModal() {
		util.waitUntilElementIsVisible(importantInfoModal, 60);
	}

	public void waitForMapValBtn() {
		util.waitUntilElementIsVisible(mapValuesBtn, 300);
		util.click(crossBtnModal);
	}

	public void saveTemplate() {
		clickOnSave();
		waitForMapValBtn();
	}

	ObjectMapper objMapper;
	private GetAllStoresFromDBPojo mainPojoObj;
	private StoreData pojoData;
	private List<Stores> allStores;
	private StoreAddress storeAddress;
	public static String supplySrcId;
	public static String storeName;
	Map<String, String> storeInfo = new HashMap<>();

	public Map<String, String> getNewlyCreatedStoreFromDB(String apiResponse) {
		objMapper = new ObjectMapper();
		try {
			mainPojoObj = objMapper.readValue(apiResponse, GetAllStoresFromDBPojo.class);
			pojoData = mainPojoObj.getData();
			allStores = pojoData.getStores();
			storeAddress = allStores.get(0).getStore_address();
			storeName = storeAddress.getName();
			supplySrcId = allStores.get(0).getSupplySourceId();

		} catch (JsonProcessingException e) {
			util.logError("Exception occured in getNewlyCreatedStoreFromDB() in ReusableMethods Class.");
		}
		storeInfo.put("storeName", storeName);
		storeInfo.put("supplySrcId", supplySrcId);

		return storeInfo;

	}

	private OrderPojo orderPojo = null;
	private String shopifyOrderId;

	public String getOrderId(String apiResponse) {
		objMapper = new ObjectMapper();

		try {
			orderPojo = objMapper.readValue(apiResponse, OrderPojo.class);

			if (orderPojo.isSuccess()) {
				shopifyOrderId = orderPojo.getData().get(0).getOrder_id();
				util.updateProperty("orderIdShopify", shopifyOrderId);
				return shopifyOrderId;
			} else {
				Assert.assertTrue(false, "FAILED DUE TO ORDER IS NOT CREATE.");
			}

		} catch (JsonProcessingException e) {
			util.logError("Exception occured in getOrderId() in ReusableMethods Class.");
		}

		return shopifyOrderId;
	}

	public void validateLocalSellingOrderIsNotCreated(String apiResponse) {
		boolean isTrue = false;
		LocalSellingOrderDisabledPojo mainObj;
		objMapper = new ObjectMapper();
		try {
			mainObj = objMapper.readValue(apiResponse, LocalSellingOrderDisabledPojo.class);

			Assert.assertTrue(!mainObj.isSuccess(), "order creation is failed ==-> TRUE.");

			if (mainObj.getMessage().equalsIgnoreCase("order sync settings disabled or user uninstalled")) {
				isTrue = true;

			}
			Assert.assertTrue(isTrue);

		} catch (JsonProcessingException e) {
			util.logError("Exception occured in validateLocalSellingOrderIsNotCreated() in ReusableMethods Class.");
		}
	}

	public void openOrderOnShopify(String shopifyOrderId) {
		String storeUrl;

		String resource = "orders/" + shopifyOrderId;

		if (util.getConfigProperty("store").equalsIgnoreCase("live")) {
			storeUrl = util.getConfigProperty("storeUrl") + resource;
		} else {
			storeUrl = util.getConfigProperty("stroreUrlStaging") + resource;
		}

		util.openAndSwitchToNewTab();
		util.openUrl(storeUrl);
		util.waitUntilElementIsVisible(orderStatusOnShopify, 30);

	}

	public void validateOrderStatusOnShopify(String status) {

		if (orderStatusOnShopify.getText().equalsIgnoreCase(status)) {
			Assert.assertTrue(true, "orders' status is equal to " + status);
		} else {
			Assert.assertTrue(false, "orders' status is not equal to" + status);
		}

		try {
			util.getDriver().close();
			goToOverview();
		} catch (WebDriverException e) {
			util.logError("no such window is available.");
		}

	}

	public void clickOnActivityFeedBtn() {
		util.click(activityFeedBtnList.get(0));
		util.hold(1);
	}

	public void openExistingShopifyProduct(String productId) {
		util.openAndSwitchToNewTab();

		if (util.getConfigProperty(STORE).equals("live")) {
			util.openUrl(util.getConfigProperty("storeUrl") + productId);
		} else if (util.getConfigProperty(STORE).equals("staging")) {
			util.openUrl(util.getConfigProperty("stroreUrlStaging") + productId);
		}
	}

	/**
	 * open overview by control+click
	 * 
	 * @param currentEnvironment
	 * @param ele
	 */
	public void openOverViewInNewTab(String currentEnvironment, WebElement ele, String resourceUrl) {
		util.switchToDefaultContent();
		util.controlClickToOpenNewTab(ele);
		util.getWindoHandleByUrl(currentEnvironment + resourceUrl);
	}

	/**
	 * open and move to section in new tab
	 * 
	 * @param ele
	 * @param sectionName
	 */
	public void openSectionInNewTab(WebElement ele, String sectionName) {
		String currentEnvironment = util.getConfigProperty("store");
		WebElement welcome = util.getDriver().findElement(By.id("welcome"));

		switch (sectionName) {

		case OVERVIEW:
			openOverViewInNewTab(currentEnvironment, ele, OVERVIEWURL);
			util.switchToIFrame();
			util.waitUntilElementIsVisible(welcome, 30);
			break;

		case LISTING:
			openOverViewInNewTab(currentEnvironment, ele, LISTINGURL);
			util.switchToIFrame();
			WebElement allListing = util.getDriver().findElement(By.id("all"));
			util.waitUntilElementIsVisible(allListing, 30);
			break;

		case LINKING:
			openOverViewInNewTab(currentEnvironment, ele, LIINKINGURL);
			util.switchToIFrame();
			WebElement linkingReq = util.getDriver().findElement(By.id("linking-required"));
			util.waitUntilElementIsVisible(linkingReq, 30);
			break;

		case SETTINGS:
			openOverViewInNewTab(currentEnvironment, ele, SETTINGSURL);
			util.switchToIFrame();
			WebElement accounts = util.getDriver().findElement(By.id("accounts"));
			util.waitUntilElementIsVisible(accounts, 30);
			break;

		default:
			break;
		}

		if (currentEnvironment.equals(STAGING) && sectionName.equals(LINKING)) {
			openOverViewInNewTab(currentEnvironment, ele, OVERVIEWURL);
			util.switchToIFrame();
			util.waitUntilElementIsVisible(welcome, 30);
			util.getDriver().findElement(By.xpath("//span[text()='Manage Linking']")).click();
			WebElement linkingReq = util.getDriver().findElement(By.id("linking-required"));
			util.waitUntilElementIsVisible(linkingReq, 30);
		}

	}

	public Map<String, String> verifyRoundOffIncrease(String prodTempName, String choseTrendSelectVal,
			String roundOffSelectVal, String condition) {
		goToSettings();
		searchAndOpenEditTemplatePage(prodTempName);
		ctp.verifyRoundOff(prodTempName, choseTrendSelectVal, roundOffSelectVal);
		String roundOffVal = StringUtils.substringAfter(ctp.priceAfterRoundOff(), "₹").trim();
		String nearestVal = ctp.percentageIncreaseAtHigher(Double.parseDouble(CreateShopifyProduct.priceOfProduct),
				condition);
		Map<String, String> map = new HashMap<>();
		map.putIfAbsent("roundOffVal", roundOffVal);
		map.putIfAbsent("nearestVal", nearestVal);
		return map;
	}

	public Map<String, String> verifyRoundOffDecreaseAtHigher(String prodTempName, String choseTrendSelectVal,
			String roundOffSelectVal, String condition) {
		goToSettings();
		searchAndOpenEditTemplatePage(prodTempName);
		ctp.verifyRoundOff(prodTempName, choseTrendSelectVal, roundOffSelectVal);
		String roundOffVal = StringUtils.substringAfter(ctp.priceAfterRoundOff(), "₹").trim();
		String nearestVal = ctp.percentageDecreaseAtHigher(condition);
		Map<String, String> map = new HashMap<>();
		map.putIfAbsent("roundOffVal", roundOffVal);
		map.putIfAbsent("nearestVal", nearestVal);
		return map;
	}

	public Map<String, String> verifyRoundOffDecreaseAtLower(String prodTempName, String choseTrendSelectVal,
			String roundOffSelectVal, String condition) {
		goToSettings();
		searchAndOpenEditTemplatePage(prodTempName);
		ctp.verifyRoundOff(prodTempName, choseTrendSelectVal, roundOffSelectVal);
		String roundOffVal = StringUtils.substringAfter(ctp.priceAfterRoundOff(), "₹").trim();
		String nearestVal = ctp.percentageDecreaseAtLower(condition);
		Map<String, String> map = new HashMap<>();
		map.putIfAbsent("roundOffVal", roundOffVal);
		map.putIfAbsent("nearestVal", nearestVal);
		return map;
	}

	public void selectFulfillmentType(String name) {

		if (name.equalsIgnoreCase("fba")) {
			if (!radioBtnFBA.isSelected()) {
				util.click(radioBtnFBA);
				clickOnSave();
			}
		} else {
			if (!radioBtnFBM.isSelected()) {
				util.click(radioBtnFBM);
				clickOnSave();
			}
		}
	}

	public void verifyFulfillmentTypeInTemplates(String name) {
		if (name.equalsIgnoreCase("fba")) {
			if (radioBtnFBA.isSelected()) {
				Assert.assertTrue(true, "FBA is not selected");
			}
		} else {
			if (radioBtnFBM.isSelected()) {
				Assert.assertTrue(true, "FBM is not selected");
			}
		}
	}

	public void readOrderEmail() {
//		util.openAndSwitchToNewTab();
//		util.openUrl("https://mail.google.com");

		if (!signInBtnGmail.isEmpty()) {
			util.click(signInBtnGmail.get(0));
			util.enterValue(emailInputField, util.getConfigProperty("user_email"));
			util.click(nextButton);
			util.waitUntilElementIsVisible(pwdInputField, 30);
			util.enterValue(pwdInputField, util.getConfigProperty("email_pass"));
			util.pressEnter();
		} else {
			util.enterValue(emailInputField, util.getConfigProperty("user_email"));
			util.click(nextButton);
			util.waitUntilElementIsVisible(pwdInputField, 30);
			util.enterValue(pwdInputField, util.getConfigProperty("email_pass"));
			util.pressEnter();
		}

		util.waitUntilElementIsVisible(searchInMail, 30);

	}

	public void loginToMail(String subject) {
		util.openAndSwitchToNewTab();
		util.openUrl("https://mail.google.com");

		if (!searchInMailList.isEmpty()) {
			searchEMail(subject);
		} else {
			readOrderEmail();
			searchEMail(subject);
		}
	}

	public String searchEMail(String subject) {
		String text = "";
		util.enterValue(searchInMail, subject);
		util.pressEnter();

		util.click(from);
		util.waitUntilElementIsVisible(nameOrEmailInput, 30);
		util.enterValue(nameOrEmailInput, "no-reply@cedcommerce.com");
		util.pressEnter();

		SimpleDateFormat dateFormat = new SimpleDateFormat("E, MMM dd, yyyy, h");

		// Get the current date and time
		Date currentDate = new Date();

		// Format the date and time
		String formattedDate = dateFormat.format(currentDate);

		List<WebElement> emailDateList = util.getDriver()
				.findElements(By.cssSelector("span[title*='" + formattedDate + "']"));

		for (int i = 0; i < emailDateList.size(); i++) {
			System.out.println(emailSubject.get(i).getText());
			if (emailSubject.get(i).getText().equals(subject)) {
				text = emailSubject.get(i).getText();
				util.click(emailSubject.get(i));
				break;
			}
		}

		String currentPage = util.getDriver().getCurrentUrl();
		util.click(hereLink);
		util.getWindoHandleByUrl(SUPPORTLINK);
		util.waitUntilElementIsVisible(modeOfComm);
		util.gotoWindowByClosingCurrentOne(currentPage);

		return text;
	}

	public void deleteEmail() {
		util.hold(1);
		util.actionClick(delIcon);

	}

	public void recommendedPlanIsVisible() {
		util.isElementDisplyedAndValidate(recommendedPlanTag);
	}

	public void searchBySKUOnListing(String sku) {
		util.goToSection("listings");
		util.waitUntilElementIsVisible(moreFiltersBtn, 30);

		if (moreFiltersModal.isEmpty()) {

			util.click(moreFiltersBtn);
		}

		util.waitUntilElementIsVisible(moreFiltersBtn, 10);

		if (clearAllFilters.isEnabled()) {
			util.click(clearAllFilters);
		}

		if (skuCollapsible.getAttribute("aria-hidden").equals(TRUEVAL)) {
			util.click(skuToggleButton);
			util.hold(1);
		}

		util.selectByVisibleText(selectSKU, "Equals");
		System.out.println(sku);
		util.enterValue(skuInputField, sku);
		util.click(doneBtnMoreFiltersModal);
		util.hold(1);
		util.refreshPage();
		util.switchToIFrame();
	}

	public void closeModalIfDisplayed() {
		if (!crossBtnModalList.isEmpty()) {
			util.click(crossBtnModal);
		}
	}

	public void addTarget(WebElement ele) {
		if (termsAndConditionFooterLink.getTagName().equalsIgnoreCase("a")) {
			util.setAttribute(ele, TARGET, BLANK);
		}
	}
	
	public void unlinkProduct(String sku) {
		util.goToSection(LINKINGSECTION);
		util.waitUntilElementIsVisible(linkedTab, 30);
		util.click(linkedTab);
		util.waitUntilElementIsVisible(searchInpFieldLinking);
//		enter sku in search input field
		util.enterValue(searchInpFieldLinking, sku);
		util.hold(5);
//		clear search input field
		searchInpFieldLinking.clear();
		util.hold(2);
//		enter sku in search input field
		util.enterValue(searchInpFieldLinking, sku);
		util.hold(5);

		if (noDataList.isEmpty()) {
			util.click(unlinkBtn.get(0));
			util.hold(1);
			util.click(yesBtn);
			util.hold(5);
		}
	}
	
	public void enableCancelledOrdersSyncOnShopify() {
		OrderPage op = new OrderPage(util);
		util.goToSection("settings");
		op.enableCreateOrderForNonExistingProductsTogglebutton();
		util.hold(2);
		util.click(cancelRefundTabBtn);

		if (syncCancelledOrdersOnShopifyToggleBtn.getAttribute(ARIACHECKED).equals(FALSEVAL)) {
			util.click(syncCancelledOrdersOnShopifyToggleBtn);
			util.hold(2);
			clickOnSave();
		}
	}
	
	public void searchProductOnListingUsingSKU(String sku) {

		util.goToSection("listings");
		util.waitUntilElementIsVisible(moreFiltersBtn, 30);
		if (!moreFiltersModal.get(0).isDisplayed()) {

			util.click(moreFiltersBtn);
		}

		util.waitUntilElementIsVisible(clearAllFilters, 10);

		if (clearAllFilters.isEnabled()) {
			util.click(clearAllFilters);
		}

		if (skuCollapsible.getAttribute(ARIAHIDDEN).equals(TRUEVAL)) {
			util.click(skuToggleButton);
			util.hold(1);
		}

		util.selectByVisibleText(selectSKU, "Contains");
		util.hold(1);
		util.click(skuInputField);
		for (char c : sku.toCharArray()) {
			skuInputField.sendKeys(String.valueOf(c));
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		util.hold(5);
		util.click(doneBtnMoreFiltersModal);
		
		util.refreshPage();
		util.switchToIFrame();
		util.waitUntilElementIsVisible(nameOfProduct, 30);

	}
	
	public void openProductEditPage() {
		util.waitUntilElementIsVisible(nameOfProduct, 30);
		util.click(nameOfProduct);
		util.waitUntilElementIsVisible(setCustomSKURadioBtnEditPage, 30);
		util.click(setCustomSKURadioBtnEditPage);
		util.click(setCustomSKURadioInputFieldEditPage);
		util.pressBackSpace();
		clickOnSave();
	}
	

}
