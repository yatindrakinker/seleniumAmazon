package com.ami.pageobjects;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import com.ami.resources.Utilities;

public class ProductListingPage extends ProductListingPageOR {

	Utilities util;
	
	private static final String INVENTORY = "inventory";
	private static final String  EQUALS = "Equals";
	String title = "";
	CreateShopifyProduct cr;


	@DataProvider
	public Object[][] getTemplateData() throws IOException {
		List<HashMap<String, String>> data = util.getJsonDataToHashmap("Template");
		return new Object[][] { { data.get(0) } };
	}

	/**
	 * Constructor to initialize driver to locators.
	 * 
	 * @param util
	 */
	public ProductListingPage(Utilities util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
	}

	/**
	 * This method validates that listing option is visible is menu-nav-bar
	 */
	public void listingSubMenuIsDisplayed() {
		util.switchToDefaultContent();
		util.isElementDisplyedAndValidate(listingsNavigation);
	}

	/**
	 * This m
	 */
	public void clickOnMoreFiltersBtn() {
		util.click(moreFiltersBtn);
	}

	public void refreshAndClickOnMoreFiltersBtn() {
		util.refreshPage();
		util.switchToIFrame();
		util.waitUntilElementIsVisible(moreFiltersBtn);
		util.click(moreFiltersBtn);
	}

	public void redirectionToListing() {
		util.click(listingsNavigation);
		util.switchToIFrame();
		util.isElementDisplyedAndValidate(listingsHeading);
	}

	public void switcherIsDisplayed() {
		util.isElementDisplyedAndValidate(sellerNameBtn);
	}

	public void switcherContainsAcntName() {
		boolean isTrue = false;

		if (!util.getTagValue(acntNameOnSwitcher).equals("")) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);

	}

	public void switcherContainsAddAcntBtn() {

		if (util.extractValueByAttributes(sellerNameBtn, ARIAEXPANDED).equals(FALSEVAL)) {
			util.click(sellerNameBtn);
		}
		util.isElementDisplyedAndValidate(addNewAcntBtn);
	}

	public void redirectionAfterClickingAddAcntBtn() {
		util.click(addNewAcntBtn);
		util.isElementDisplyedAndValidate(addNewAcntPageHeading);
		util.click(backBtn);
	}

	public void linkProductBtnIsDisplayed() {
		util.isElementDisplyedAndValidate(linkProductBannerBtn);
		util.click(linkProductBannerBtn);
		util.waitUntilElementIsVisible(linkAmzProductsPageHeading, 20);
		util.isElementDisplyedAndValidate(linkAmzProductsPageHeading);
		util.click(backBtn);
		util.waitUntilElementIsVisible(statusAll, 20);
	}

	public void statusWiseProductsAreDisplayed() {
		WebElement[] statusArr = { statusAll, statusNotListed, statusInactive, statusInComplete, statusActive,
				statusError };

		for (WebElement ele : statusArr) {
			util.isElementDisplyedAndValidate(ele);
		}

	}

	public void mousePropertyChangeWhenHoverAtStatus() {
		WebElement[] statusArr = { statusAll, statusNotListed, statusInactive, statusInComplete, statusActive,
				statusError };

		for (WebElement ele : statusArr) {
			util.validateCursorProperty(ele, POINTER);
		}

	}

	public void statusWiseProductsContainsCount() {
		WebElement[] statusArrCount = { incompleteCount, inActiveCount, notListedCount };

		for (WebElement ele : statusArrCount) {
			boolean isTrue = false;

			if (!util.getTagValue(ele).equals("")) {
				isTrue = true;
			}
			Assert.assertTrue(isTrue);

		}

	}

	public void redirectionToStatusWiseSection() {
		WebElement[] statusArr = { statusNotListed, statusAll, statusInactive, statusInComplete, statusActive,
				statusError };

		for (WebElement ele : statusArr) {
			boolean isTrue = false;
			util.click(ele);

			if (util.extractValueByAttributes(ele, "aria-selected").equals("true")) {
				isTrue = true;
			}
			Assert.assertTrue(isTrue);
		}

		util.click(statusAll);
	}

	public void searchInputFieldContainsPlaceHolder() {
		boolean isTrue = false;

		if (util.extractValueByAttributes(searchInputField, "placeholder")
				.contains("Title, Vendor, Product type or Barcode")) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);

	}

	public void searchInputFieldIsEditable() {
		boolean isTrue = false;
		String beforeVal = util.extractValueByAttributes(searchInputField, "value");
		util.enterValue(searchInputField, "testing");

		if (!beforeVal.equals(util.extractValueByAttributes(searchInputField, "value"))) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);
	}

	public void filterModalIsDisplayed() {
		WebElement[] filterArr = { inventoryToggleBtnFilter, skuToggleButtonFilter, tagsToggleButtonFilter,
				productTypeToggleBtnFilter, vendorToggleButtonFilter, templateNameToggleButtonFilter,
				productStatusToggleButtonFilter, activityToggleButtonFilter, typeToggleButtonFilter };
		util.click(moreFiltersBtn);
		util.hold(1);

		for (WebElement filter : filterArr) {
			util.isElementDisplyedAndValidate(filter);
		}

	}

	public void filterOptionsAreClickable() {
		WebElement[] filterArr = { inventoryToggleBtnFilter, skuToggleButtonFilter, tagsToggleButtonFilter,
				productTypeToggleBtnFilter, vendorToggleButtonFilter, templateNameToggleButtonFilter,
				productStatusToggleButtonFilter, variantAttributesToggleButtonFilter, activityToggleButtonFilter,
				typeToggleButtonFilter };

		for (int i = 0; i < downArrowFilterList.size(); i++) {
			boolean isTrue = false;
			util.click(downArrowFilterList.get(i));

			if (util.extractValueByAttributes(filterArr[i], ARIAEXPANDED).equals("true")) {
				isTrue = true;
			}
			Assert.assertTrue(isTrue);

			util.click(downArrowFilterList.get(i));
		}
	}

	public void applyInventoryFilter(Map<String, String> input) {
		boolean isTrue = false;
		util.click(inventoryToggleBtnFilter);
		util.selectByVisibleText(selectFilterAttributes, EQUALS);
		util.enterValue(inputFieldMoreFilters, input.get(INVENTORY));
		util.click(doneBtn);

		activeFilterAreShown();
		util.hold(5);

		if (noProductsFound.isEmpty()) {
			if (util.getTagValue(inventoryAt0thPos).contains(input.get(INVENTORY))) {
				isTrue = true;
			}
			Assert.assertTrue(isTrue);
		} else if (!noProductsFound.isEmpty()) {
			Assert.assertTrue(true);
		}

		util.click(moreFiltersBtn);
		util.click(inventoryToggleBtnFilter);
		util.click(clearAllFiltersBtn);
		util.hold(2);

		activeFilterAreNotShown();
	}

	public void activeFilterAreShown() {
		boolean isTrue = false;

		if (!activeFilter.isEmpty()) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);
	}

	public void activeFilterAreNotShown() {
		boolean isTrue = false;

		if (activeFilter.isEmpty()) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);
	}

	public void applySKUFilter(Map<String, String> input) {
		boolean isTrue = false;
		util.click(skuToggleButtonFilter);
		util.selectByVisibleText(selectFilterAttributes, "Contains");
		util.enterValue(inputFieldMoreFilters, input.get("sku"));
		util.click(doneBtn);

		if (noProductsFound.isEmpty()) {
			if (!allProductList.isEmpty()) {
				isTrue = true;
			}
			Assert.assertTrue(isTrue);
		} else if (!noProductsFound.isEmpty()) {
			Assert.assertTrue(true);
		}
//		util.listIsNotEmpty(skuList)

		activeFilterAreShown();
		util.click(moreFiltersBtn);
		util.click(skuToggleButtonFilter);
		util.click(removeFilter);
		activeFilterAreNotShown();
	}

	public void inventorySelectContainsCondition() {
		String[] values = { EQUALS, "Not Equals", "Greater Than or Equal to", "Less Than or Equal to" };
		util.click(inventoryToggleBtnFilter);

		for (int i = 0; i < values.length; i++) {
			util.selectContainsValues(util.getOptionList(selectFilterAttributes).get(i), values[i]);
		}

		util.click(inventoryToggleBtnFilter);
	}

	public void skuSelectContainsCondition() {
		String[] values = { EQUALS, "Not Equals", "Contains", "Does not Contains", "Starts With", "Ends With" };
		util.click(skuToggleButtonFilter);

		for (int i = 0; i < values.length; i++) {
			util.selectContainsValues(util.getOptionList(selectFilterAttributes).get(i), values[i]);
		}

		util.click(skuToggleButtonFilter);
	}

	public void productStatusSelectContainsCondition() {
		String[] values = { "Choose...", "Active", "Not Listed", "Inactive", "Incomplete", "Not Listed: Offer" };
		util.click(productStatusToggleButtonFilter);

		for (int i = 0; i < values.length; i++) {
			util.selectContainsValues(util.getOptionList(selectFilterAttributes).get(i), values[i]);
		}

		util.click(productStatusToggleButtonFilter);
	}

	public void activitySelectContainsCondition() {
		String[] values = { "Choose...", "In Progress", "Error" };
		util.click(activityToggleButtonFilter);

		for (int i = 0; i < values.length; i++) {
			util.selectContainsValues(util.getOptionList(selectFilterAttributes).get(i), values[i]);
		}

		util.click(activityToggleButtonFilter);
	}

	public void typeSelectContainsCondition() {
		String[] values = { "Choose...", "simple", "variation" };
		util.click(typeToggleButtonFilter);

		for (int i = 0; i < values.length; i++) {
			util.selectContainsValues(util.getOptionList(selectFilterAttributes).get(i), values[i]);
		}

		util.click(typeToggleButtonFilter);
	}

	public void vendorSelectContainsCondition() {
		String[] values = { EQUALS, "Not Equals" };
		util.click(vendorToggleButtonFilter);

		for (int i = 0; i < values.length; i++) {
			util.selectContainsValues(util.getOptionList(selectFilterAttributes).get(i), values[i]);
		}

		util.click(vendorToggleButtonFilter);
	}

	public void applyTagsFilter(Map<String, String> input) {
		boolean isTrue = false;
		util.click(tagsToggleButtonFilter);
		util.enterValue(inputFieldMoreFilters, input.get("tags"));
		util.click(doneBtn);

		if (noProductsFound.isEmpty()) {
			if (!allProductList.isEmpty()) {
				isTrue = true;
			}
			Assert.assertTrue(isTrue);
		} else if (!noProductsFound.isEmpty()) {
			Assert.assertTrue(true);
		}

		activeFilterAreShown();
		util.click(moreFiltersBtn);
		util.click(tagsToggleButtonFilter);
		util.click(clearAllFiltersBtn);
		activeFilterAreNotShown();
	}

	public void applyProductTypeFilter(Map<String, String> input) {
		boolean isTrue = false;
		util.click(productTypeToggleBtnFilter);
		util.enterValue(inputFieldMoreFilters, input.get("product_type"));
		util.click(doneBtn);

		if (noProductsFound.isEmpty()) {
			if (!allProductList.isEmpty()) {
				isTrue = true;
			}
			Assert.assertTrue(isTrue);
		} else if (!noProductsFound.isEmpty()) {
			Assert.assertTrue(true);
		}
		activeFilterAreShown();
		util.click(moreFiltersBtn);
		util.click(productTypeToggleBtnFilter);
		util.click(clearAllFiltersBtn);
		activeFilterAreNotShown();
		util.click(doneBtn);
		util.hold(5);
	}

	public void buttonsAreDisplayed() {
		WebElement[] eleArr = { syncStatusBtn, amazonLookupBtn, bulkUpdateBtn };

		for (WebElement ele : eleArr) {
			util.isElementDisplyedAndValidate(ele);
			util.validateCursorProperty(ele, POINTER);
		}
	}

	public void modalIsDisplayedWhenSyncBtnIsClicked() {
		util.click(syncStatusBtn);
		util.waitUntilElementIsVisible(proceedBtnSyncStatusModal, 10);
		util.click(proceedBtnSyncStatusModal);
		util.waitUntilElementIsVisible(toastMsg, 10);
		util.isElementDisplyedAndValidate(toastMsg);
	}

	public void bulkUpdateContainsImportExportBtn() {
		WebElement[] eleArr = { importProductsBtn, exportProductsBtn };
		util.click(bulkUpdateBtn);

		for (WebElement ele : eleArr) {
			util.isElementDisplyedAndValidate(ele);
		}

	}

	public void exportBtnOpenModal() {
		WebElement[] eleArr = { allProductsRadioBtnExport, currentPageRadioBtnExport };
		util.click(exportProductsBtn);
		util.waitUntilElementIsVisible(exportProductModal);

		for (WebElement ele : eleArr) {
			util.isElementDisplyedAndValidate(ele);
		}
	}

	public void closeExportModal() {
		util.click(crossBtnModal);
	}

	public void selectedProductCountIsDisplayed() {

		if (!productListingCheckboxList.isEmpty()) {
			util.jsClick(productListingCheckboxList.get(0));

			util.isElementDisplyedAndValidate(selectedProductsCount.get(0));
			boolean isTrue = false;

			if (selectedProductsCountText.getText().contains("1")) {
				isTrue = true;
				Assert.assertTrue(isTrue);
			}
			Assert.assertTrue(isTrue);
		}
	}

	public void exportModalIsDisplayed() {
		util.click(bulkUpdateBtn);
		util.click(exportProductsBtn);
		util.isElementDisplyedAndValidate(exportProductModal);
		util.isElementDisplyedAndValidate(selectedProductsRadioBtnExportModal);
	}

	public void ifNotSelectedFilterExportModalBtnIsDisabled() {
		boolean isTrue = false;

		if (util.extractValueByAttributes(exportBtnExportModal, ARIADISABLED).equals(TRUEVAL)
				|| (!exportBtnExportModal.isEnabled())) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);
	}

	public void exportModalContainsElements() {
		WebElement[] eleArr = { cancelBtnExportModal, crossBtnModal, exportProductToCSVFileExportModal };

		for (WebElement ele : eleArr) {
			util.isElementDisplyedAndValidate(ele);
		}
	}

	public void cursorPropertyChanges() {
		util.validateCursorProperty(exportProductToCSVFileExportModal, POINTER);
	}

	public void crossBtnClosesModal() {
		boolean isTrue = false;
		util.click(crossBtnModal);

		if (checkboxListExportModal.isEmpty()) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);
	}

	public void importModalIsOpened() {
		util.click(bulkUpdateBtn);
		util.click(importProductsBtn);
		util.waitUntilElementIsVisible(importProductModal, 10);
		util.isElementDisplyedAndValidate(importProductModal);
	}

	public void importModalContains() {
		WebElement[] eleArr = { cancelBtnExportModal, crossBtnModal, importProductsBtnImportModal.get(0),
				needHelpLinkImportModal, addFileBtn };

		for (WebElement ele : eleArr) {
			util.isElementDisplyedAndValidate(ele);
		}
	}

	public void needHelpLinkImportModalIsWorking() {
		boolean isTrue = false;
		String currentPageUrl = util.getPageURL();
		util.click(needHelpLinkImportModal);
		util.goToChildWindow();

		if (util.getPageURL().contains("https://docs.cedcommerce.com/")) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);

		try {
			util.getDriver().close();
			util.getWindoHandleByUrl(currentPageUrl);
			util.switchToIFrame();
		} catch (NoSuchWindowException e) {
			util.logError("no such window exception occured.");
		}

	}

	public void closeImportModal() {
		util.jsClick(crossBtnModal);
		util.hold(1);
		util.listIsEmpty(importProductsBtnImportModal);
	}

	public void checkboxesBeforeAllProducts() {
		util.listIsNotEmpty(productListingCheckboxList);
		util.hold(2);
		
	}

	public void selectActionContainsVariousActions() {
		util.click(productListingCheckboxList.get(1));
		util.hold(3);
		util.listIsNotEmpty(selectedProductsCount);
		WebElement[] arrSelectAction = { uploadProductBtnSelectActions, syncProductBtnSelectActions,
				amazonLookupBtnSelectActions, syncInventoryBtnSelectActions, syncPriceBtnSelectActions,
				syncImgBtnSelectActions, deleteProductsBtnSelectActions };
		util.click(selectActionsBtn);

		for (WebElement ele : arrSelectAction) {
			util.isElementDisplyedAndValidate(ele);
		}

	}

	public void onClickingCrossBtnCheckBoxGetsUnchecked() {
		util.click(crossBtnSelectedProductsCount);
		util.listIsEmpty(selectedProductsCount);
	}

	public void selectAllProducts() {

		util.hold(1);
		util.jsClick(productListingCheckboxList.get(0));
		util.hold(1);
		util.isElementDisplyedAndValidate(selectAll50PlusProductsBtn.get(0));
		util.hold(1);
		util.jsClick(selectAll50PlusProductsBtn.get(0));

		for (int i = 0; i < 10; i++) {
			boolean isTrue = false;
			if (productListingCheckboxList.get(i).isSelected()) {
				isTrue = true;
			}
			Assert.assertTrue(isTrue);
		}
	}

	public void headingsAreDisplayed() {

		util.listIsNotEmpty(imageHeading);
		util.listIsNotEmpty(titleHeading);
		util.listIsNotEmpty(productDetailsHeading);
		util.listIsNotEmpty(templateHeading);
		util.listIsNotEmpty(inventoryHeading);
		util.listIsNotEmpty(amazonStatusHeading);
		util.listIsNotEmpty(activityHeading);
		util.listIsNotEmpty(actionsHeading);

	}

	/**
	 * This method applies filters of variants product and verifies that
	 * collapse/expand btn working or not.
	 */
	public void selectVariantsFromFilter() {

		if (moreFiltersHeading.isEmpty()) {
			util.click(moreFiltersBtn);
		}

		util.hold(5);
		util.click(typeToggleButtonFilter);
		util.selectByVisibleText(selectFilterAttributes, "variation");
		util.click(moreFiltersBtn);
		util.hold(2);
		util.waitUntilElementIsVisible(variantExpandBtn.get(0), 10);
		util.click(variantExpandBtn.get(0));
		util.hold(2);
		util.isElementDisplyedAndValidate(imageHeadingVariantGrid);
	}

	/**
	 * This method verifies that headings are displayed in variant grid.
	 */
	public void variantGridContainsHeading() {
		WebElement[] headingsVariantGridArr = { imageHeadingVariantGrid, skuHeadingVariantGrid,
				productDetailsHeadingVariantGrid, inventoryHeadingVariantGrid, amazonStatusHeadingVariantGrid };

		for (WebElement ele : headingsVariantGridArr) {
			util.isElementDisplyedAndValidate(ele);
		}
	}

	public void imagesSectionIsVisible() {
		util.listIsNotEmpty(imageContainerVariantsList);
	}

	public void imgSectionContainsImg() {

		for (WebElement img : imageContainerVariantsList) {
			boolean isTrue = false;

			if (!util.extractValueByAttributes(img, "src").equals("")) {
				isTrue = true;
				Assert.assertTrue(isTrue);
			}
			Assert.assertTrue(isTrue);
		}
	}

	public void onHoveringImgCursorPropertyIsNotChanging() {
		util.validateCursorProperty(imageContainerVariantsList.get(0), "auto");
	}

	public void variantsGridNotContainsTitle() {
		for (int i = 1; i < headingVariantGrid.size(); i++) {

			boolean isTrue = false;

			if (!headingVariantGrid.get(i).getText().equals("title")) {
				isTrue = true;
				Assert.assertTrue(isTrue);
			}
			Assert.assertTrue(isTrue);

		}
	}

	public void inventoryIsShownInVariantGrid() {
		for (WebElement ele : inventoryValVariantGrid) {

			boolean isTrue = false;

			if (!ele.getText().equals("")) {
				isTrue = true;
				Assert.assertTrue(isTrue);
			}
			Assert.assertTrue(isTrue);
		}
	}

	public void statusAreDisplayed() {
		for (WebElement ele : amazonStatusList) {

			boolean isTrue = false;
			boolean isPartialListed = ele.getText().equalsIgnoreCase("Partial Listed");
			boolean isIncomplete = ele.getText().equalsIgnoreCase("incomplete");
			boolean isInactive = ele.getText().equalsIgnoreCase("inactive");
			boolean isListed = ele.getText().equalsIgnoreCase("Listed");
			boolean isNotListed = ele.getText().equalsIgnoreCase("Not Listed");
			boolean isActive = ele.getText().equalsIgnoreCase("active");
			boolean isError = ele.getText().equalsIgnoreCase("error");

			if (isPartialListed || isIncomplete || isInactive || isListed || isNotListed || isActive || isError) {
				isTrue = true;
			}
			Assert.assertTrue(isTrue);
		}
	}

	public void createNewShopifyProd() {
		cr = new CreateShopifyProduct(util);

		String urlApp = util.getPageURL();
		util.openAndSwitchToNewTab();
//		create new shopify product
		cr.goToCreateNewProductPage("store");
		cr.createNewShopifyProduct();
		util.gotoWindowByClosingCurrentOne(urlApp);
	}

	public void createManualTemplate() {
		CustomTemplatePage templatePage = new CustomTemplatePage(util);
//		create a template and assign proudct in this template manually.
		util.goToMultiAccountsSettingsClick();
		util.clickOnTemplates();
		util.waitUntilElementIsVisible(templateNameInputField);
		util.enterValue(templateNameInputField, CreateShopifyProduct.nameOfProduct);
		templatePage.selectDefaultcategoryAndRequireAttribute();
		templatePage.selectAmazonRecommendationValueFromDropDown();
		util.enterValue(handlingTimeInputField, "3");
		util.actionClick(manualRadioBtn);
		util.enterValue(manualSelectionInpField, CreateShopifyProduct.nameOfProduct);
		util.click(browseManualProductsButton);

		if (!noProductsFound.isEmpty()) {
			tryUntilProductIsShown();
		} else {
			util.hold(3);
			util.actionClick(checkBoxProductsManualSelectionDialogBox);
			util.click(continueBtnManualSelectionDialogBox);
		}

		util.hold(1);
		templatePage.clickOnSaveBtn();
		util.hold(2);
		util.waitUntilElementIsVisible(moveToTemplateGridBtn, 30);
		util.click(moveToTemplateGridBtn);
		templateIsAssigning();

	}

	public void templateIsAssigning() {
		while (!progressBarTemplateAssign.isEmpty()) {
			util.hold(30);
			util.refreshPage();
			util.switchToIFrame();
			util.click(productTemplates);
		}
	}

	public void tryUntilProductIsShown() {
		int count = 0;
		while (!noProductsFound.isEmpty()) {
//			click on browse product while dialog box is not showed up.
			if (count == 10) {
				Assert.assertTrue(false);
				break;
			} else {
				util.hold(30);
				util.click(browseManualProductsButton);
			}

			count++;
		}

		if (!crossBtnExportModalList.isEmpty()) {
			util.click(crossBtnExportModalList.get(0));
			util.click(browseManualProductsButton);
		}
	}

	public void redirectionToTemplates() {

		util.waitUntilElementIsVisible(bookACallFooterLink, 30);
		util.hold(5);
		
		do {
			if (templateNameListListing.isEmpty()) {
				util.click(nextPaginationBtn);
			} else {
				util.click(templateNameListListing.get(0));
				break;
			}
		} while (nextPaginationBtn.getAttribute(ARIADISABLED) == null);

		util.waitUntilElementIsVisible(editTemplateHeading, 30);
		util.isElementDisplyedAndValidate(editTemplateHeading);
		util.click(backBtn);
	}

	public void clickOnAddTempBtn() {

		String text = totalPageCountListing.getText();
		text = StringUtils.substringAfter(text, "/ ");
		text = StringUtils.substringBefore(text, " page(s)");
		int counter = Integer.parseInt(text);

		for (int i = 0; i < counter; i++) {

			if (plusBtnList.isEmpty()) {
				util.click(nextPaginationBtn);
			} else {
				title = productTitleList.get(0).getText();
				util.click(plusBtnList.get(0));
				break;
			}

		}

		util.waitUntilElementIsVisible(addTemplateHeadingAddTempModal, 5);
		util.selectByIndex(selectTemplateAddTempModal, 1);
		util.isElementDisplyedAndValidate(addNewTemplateBtnAddTempModal);
		util.click(addNewTemplateBtnAddTempModal);
		util.waitUntilElementIsVisible(dataUpdatedSuccessfullyToastMsg, 10);
		util.hold(3);
	}

	public void changeAndDeleteTemplateBtnIsDisplayed() {
		util.enterValue(searchInputField, title);
		util.click(searchedProductList.get(0));
		util.waitUntilElementIsVisible(changeTemplateBtnList.get(0), 10);
		util.isElementDisplyedAndValidate(changeTemplateBtnList.get(0));
		util.isElementDisplyedAndValidate(deleteTemplateBtnList.get(0));
	}

	public void clickOnFilters(String filterName) {
		switch (filterName) {
		case INVENTORY:
			util.click(inventoryToggleBtnFilter);
			break;

		case "sku":
			util.click(skuToggleButtonFilter);
			break;

		case "tags":
			util.click(tagsToggleButtonFilter);
			break;

		case "productType":
			util.click(productTypeToggleBtnFilter);
			break;

		case "vendor":
			util.click(vendorToggleButtonFilter);
			break;

		case "templateName":
			util.click(templateNameToggleButtonFilter);
			break;

		case "productStatus":
			util.click(productStatusToggleButtonFilter);
			break;

		case "variant":
			util.click(variantAttributesToggleButtonFilter);
			break;

		case "activity":
			util.click(activityToggleButtonFilter);
			break;

		case "type":
			util.click(typeToggleButtonFilter);
			break;

		default:
			break;
		}
	}

	public void applyFilters(Map<String, String> input, String filterName, String conditionName, String key) {
		boolean isTrue = false;
		clickOnFilters(filterName);
		util.selectByVisibleText(selectFilterAttributes, conditionName);
		util.enterValue(inputFieldMoreFilters, input.get(key));
		util.click(doneBtn);

		if (util.getTagValue(inventoryAt0thPos).contains(input.get(key))) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);

		util.click(moreFiltersBtn);
		clickOnFilters(filterName);
		util.click(clearAllFiltersBtn);
	}

	/**
	 * This method creates a new shopify product and creates a new template with
	 * same name of product and assigns this product using manual selection.
	 */
	public void createNewProductAndCreateManualTemplate() {
		CustomTemplatePage ctp = new CustomTemplatePage(util);
		util.switchToDefaultContent();
		util.clickOnSettings();
		util.waitUntilElementIsVisible(productTemplates, 20);
		util.hold(2);
		util.click(productTemplates);
		util.actionClick(productTemplates);
		util.hold(2);
		util.click(addNewTeamplateBtn);
		util.hold(2);
		String currentPageUrl = util.getPageURL();
		CreateShopifyProduct create = new CreateShopifyProduct(util);
		util.openAndSwitchToNewTab();
		create.goToCreateNewProductPage(util.getConfigProperty("store"));
		create.createNewShopifyProduct();
		util.getWindoHandleByUrl(currentPageUrl);
		util.switchToIFrame();
		util.enterValue(templateNameInputField, CreateShopifyProduct.nameOfProduct);
		util.enterValue(handlingTimeInputField, "5");
		ctp.selectDefaultcategoryAndRequireAttribute();
		ctp.selectAmazonRecommendationValueFromDropDown();
		util.actionClick(manualRadioBtn);
		util.enterValue(manualSelectionInpField, CreateShopifyProduct.nameOfProduct);
		util.click(browseManualProductsButton);
		util.hold(1);
		productIsCreatedOnApp();
		util.click(checkBoxProductsManualSelectionDialogBox);
		util.click(continueBtnManualSelectionDialogBox);
		util.hold(1);
		clickOnSaveBtn();
	}

	public void productIsCreatedOnApp() {
		do {
			util.hold(10);
			util.click(browseManualProductsButton);
		} while (!noProductsFound.isEmpty());
	}

	public void clickOnSaveBtn() {
		util.switchToDefaultContent();
		util.click(saveBtn);
		util.switchToIFrame();
	}

	/**
	 * This method validates that template created using
	 * "createNewProductAndCreateManualTemplate()" is reflecting or not .
	 */
	public void validateManualTemplateIsCreated(String nameOfTemplate) {
		searchAndSelectProduct(nameOfTemplate);
		util.listIsNotEmpty(changeTemplateBtnList);
		util.listIsNotEmpty(deleteTemplateBtnList);
	}

	public void searchAndSelectProduct(String nameOfTemplate) {
		util.click(searchInputFieldListing);
		util.enterValue(searchInputFieldListing, nameOfTemplate);
		util.hold(3);
		util.pressEscape();
		util.click(searchInputFieldListing);
		util.enterValue(searchInputFieldListing, nameOfTemplate);
		util.waitUntilElementIsVisible(searchedProductSuggestionList.get(0), 30);

		for (WebElement ele : searchedProductSuggestionList) {
			if (util.extractValueByAttributes(ele, "data-listbox-option-value")
					.equals(CreateShopifyProduct.containerIDOfProduct)) {
				util.click(ele);
				break;
			} else {
				Assert.assertTrue(false, "product is not available in app.");
			}
		}
	}

	/**
	 * This method validates that manual template contains changeTemplate Buttton
	 * and deleteTemplate button
	 * 
	 * @param productName
	 */

	public void manualTemplateContainsChangeAndRemoveTemplateBtn(String productName) {
		searchAndSelectProduct(productName);
		util.isElementDisplyedAndValidate(changeTemplateBtnList.get(0));
		util.isElementDisplyedAndValidate(deleteTemplateBtnList.get(0));
	}

	public void waitToDisplayTemplateActionBtns() {
		do {
			util.refreshPage();
			util.switchToIFrame();
			util.hold(10);
		} while (changeTemplateBtnList.isEmpty());
	}

	/**
	 * This method validates that if we change the template assigned to the product
	 * the apply new template button is enabled.
	 * 
	 * @param nameOfTemplate
	 */
	public void changeTemplate(String nameOfTemplate) {
		boolean isTrue = false;
		util.click(changeTemplateBtnList.get(0));
		String btnIsDisabled = util.extractValueByAttributes(applyNewTeamplateBtn, "aria-disabled");

		if (manualTemplatesInAddTemplateModal.size() <= 2 && (btnIsDisabled.equals("true"))) {
			util.click(closeModalBtn);
		} else {
			for (WebElement option : manualTemplatesInAddTemplateModal) {

				if (!(option.getText().equals(nameOfTemplate)) && (!option.getText().equals("Select Template"))) {
					util.selectByVisibleText(selectTemplateAddTemplateModal, option.getText());
					isTrue = applyNewTeamplateBtn.isEnabled();
					break;
				}
			}
			Assert.assertTrue(isTrue);
		}
	}

	/**
	 * This method validates that if we change the template to old one assigned to
	 * the product the apply new template button is enabled.
	 * 
	 * @param nameOfTemplate
	 */
	public void assignOldTemplateToProduct(String nameOfTemplate) {
		boolean isTrue = false;
		util.selectByVisibleText(selectTemplateAddTemplateModal, nameOfTemplate);
		isTrue = applyNewTeamplateBtn.isEnabled();
		Assert.assertTrue(isTrue);
	}

	public void clickOnApplyNewTemplate() {
		util.click(applyNewTeamplateBtn);
		util.hold(2);
		util.isElementDisplyedAndValidate(productAssignedToTemplateToastMsg);
	}

	public void templateIsChangedSuccessfully(String productName) {
		boolean isTrue = false;
		String newAssignedTemplate = templateNameListListing.get(0).getText();

		if (!productName.equals(newAssignedTemplate)) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);
	}

	public void clearFilters() {
		util.click(moreFiltersBtn);
		util.click(clearAllFiltersBtn);
		util.click(doneBtn);
	}

	public void unassignedTemplateProductContainsAddTemplateBtn() {

		do {
			if (plusBtnList.isEmpty()) {
				util.click(nextPaginationBtn);
			} else {
				Assert.assertTrue(true);
				break;
			}
		} while (nextPaginationBtn.getAttribute("aria-disabled") == null);

	}

	public void syncProductActivityProgressBarIsShown() {
		util.click(moreFiltersBtn);
		util.click(skuToggleButtonFilter);
		util.selectByVisibleText(selectTemplateAddTemplateModal, "Contains");
		util.enterValue(inputFieldMoreFilters, "yktest");
		util.click(doneBtn);
		util.hold(3);
		for (int i = 0; i < 5; i++) {
			util.click(productListingCheckboxList.get(i));
		}

		util.click(selectActionsBtn);
		util.click(syncProductBtnSelectActions);
		util.click(confirmBtnDialogBox);
		util.openAndSwitchToNewTab();
		util.goToMultiAccountOverView();

	}

	public void assignTemplateFromListing() {
		boolean isTrue = false;
		util.click(moreFiltersBtn);
		util.click(templateNameToggleButtonFilter);

		if (manualTemplatesInAddTemplateModal.size() > 1) {
			util.selectByIndex(selectTemplateAddTemplateModal, 1);
		}

		util.click(doneBtn);

		if (!noProductsFound.isEmpty()) {
			for (WebElement deleteTempBtn : deleteTemplateBtnList) {
				util.click(deleteTempBtn);
				util.click(removeTemplateBtn);
			}
		}

		util.click(moreFiltersBtn);
		util.click(clearAllFiltersBtn);
		util.click(doneBtn);
		util.click(plusBtnList.get(0));

		if (manualTemplatesInAddTemplateModal.size() > 1) {
			util.selectByIndex(selectTemplateAddTemplateModal, 1);
			util.click(applyNewTeamplateBtn);
		}

		util.click(moreFiltersBtn);
		
		if(templateNameToggleButtonFilter.getAttribute(ARIAEXPANDED).equals(FALSEVAL)) {	
			util.click(templateNameToggleButtonFilter);
		}

		if (manualTemplatesInAddTemplateModal.size() > 1) {
			util.selectByIndex(selectTemplateAddTemplateModal, 1);
		}

		util.hold(2);
		util.click(doneBtn);
		util.hold(1);

		if (!changeTemplateBtnList.isEmpty()) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);
	}

	public void clickOnEditProductBtn() {
		String isExpanded = util.extractValueByAttributes(kebabMenuListListing.get(0), ARIAEXPANDED);

		if (isExpanded.equals(FALSEVAL)) {
			util.click(kebabMenuListListing.get(0));
		}

		util.click(editProduct);
		util.waitUntilElementIsVisible(offerListingRadioBtn, 30);
		util.isElementDisplyedAndValidate(offerListingRadioBtn);
		util.click(backBtnListing);

	}

	public void clearActiveTag() {
		util.click(removeStatusTagBtn);
		util.hold(5);
	}

	public void paginationBtnIsWorking() {
		util.waitUntilElementIsVisible(nextPaginationBtn);
		if (nextPaginationBtn.getAttribute("ariaDisabled") == null) {
			String pageCountBeforeNext = util.extractValueByAttributes(inputTextFieldCurrentPage, VAL).trim() ;
			util.click(nextPaginationBtn);
			util.hold(5);
			String pageCountAfterNext = util.extractValueByAttributes(inputTextFieldCurrentPage, VAL).trim() ;
			
			Assert.assertNotEquals(pageCountBeforeNext, pageCountAfterNext);

			util.click(prevPaginationBtn);
			util.hold(5);
			String pageCountAfterPrev = util.extractValueByAttributes(inputTextFieldCurrentPage, VAL).trim() ;
			Assert.assertNotEquals(pageCountAfterNext, pageCountAfterPrev);
		} else {
			Assert.assertEquals(util.extractValueByAttributes(inputTextFieldCurrentPage, VAL).trim(), "1");
		}
	}

	public void footerLinksAreWorking() {
		OnboardingPage op = new OnboardingPage(util);
		op.openTermsConditionCheckBoxDoc();
		op.cedLinkFooterIsFunctioning();
		op.bookCallLinkFooterIsFunctioning();
	}
	
//	---------------------------------JULY CASES------------------------------------
	
	public void productUploadTourBtnIsWorking() {
		util.click(productUploadTourBtn);
		util.isElementDisplyedAndValidate(nextBtnProductUpload);
		util.click(closeTourBtn);
	}
}
