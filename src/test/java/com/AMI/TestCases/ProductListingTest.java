package com.AMI.TestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ami.pageobjects.CreateShopifyProduct;
import com.ami.pageobjects.ProductListingPage;
import com.ami.resources.BaseClass;

public class ProductListingTest extends BaseClass {

	ProductListingPage plp;

	@DataProvider
	public Object[][] getProductListingData() throws IOException {
		List<HashMap<String, String>> data = util.getJsonDataToHashmap("ProductListing");
		return new Object[][] { { data.get(0) } };
	}

	@Test(priority = 1)
	public void initialize() {
		plp = new ProductListingPage(util);
	}

	@Test(priority = 1)
	public void testListingSubMenuIsDisplayed() {
		plp.listingSubMenuIsDisplayed();
	}

	@Test(priority = 2)
	public void testRedirectionToListing() {
		plp.redirectionToListing();
	}

	@Test(priority = 3)
	public void testSwitcherIsDisplayed() {
		plp.switcherIsDisplayed();
	}

	@Test(priority = 4)
	public void testSwitcherContainsAcntName() {
		plp.switcherContainsAcntName();
	}

	@Test(priority = 5)
	public void testSwitcherContainsAddAcntBtn() {
		plp.switcherContainsAddAcntBtn();
	}

	@Test(priority = 6)
	public void testRedirectionAfterClickingAddAcntBtn() {
		plp.redirectionAfterClickingAddAcntBtn();
	}

	// Functionality removed

	// @Test(priority = 7) // public void testLinkProductBtnIsDisplayed() { // //
//	  plp.linkProductBtnIsDisplayed(); // }

	@Test(priority = 8)
	public void testStatusWiseProductsAreDisplayed() {
		plp.statusWiseProductsAreDisplayed();
	}

	@Test(priority = 9)
	public void testMousePropertyChangeWhenHoverAtStatus() {
		plp.mousePropertyChangeWhenHoverAtStatus();
	}

	@Test(priority = 10)
	public void testStatusWiseProductsContainsCount() {
		plp.statusWiseProductsContainsCount();
	}

	@Test(priority = 11)
	public void testRedirectionToStatusWiseSection() {
		plp.redirectionToStatusWiseSection();
	}

	@Test(priority = 12)
	public void testSearchInputFieldContainsPlaceHolder() {
		plp.searchInputFieldContainsPlaceHolder();
	}

	@Test(priority = 13)
	public void testFilterAreDisplayed() {
		plp.filterModalIsDisplayed();
	}

	@Test(priority = 14)
	public void testFilterOptionsAreClickable() {
		plp.filterOptionsAreClickable();
	}

	@Test(priority = 15, dataProvider = "getProductListingData")
	public void testApplyInventoryFilter(HashMap<String, String> input) {
		plp.applyInventoryFilter(input);
	}

	@Test(priority = 16, dataProvider = "getProductListingData")
	public void testApplyTagsFilter(HashMap<String, String> input) {
		plp.applyTagsFilter(input);
	}

	@Test(priority = 17, dataProvider = "getProductListingData")
	public void testApplySKUFilter(HashMap<String, String> input) {
		plp.applySKUFilter(input);
	}

	@Test(priority = 18, dataProvider = "getProductListingData")
	public void testApplyProductTypeFilter(HashMap<String, String> input) {
		plp.applyProductTypeFilter(input);
	}

	@Test(priority = 19)
	public void testInventorySelectContainsCondition() {
		plp.refreshAndClickOnMoreFiltersBtn();
		plp.inventorySelectContainsCondition();
	}

	@Test(priority = 20)
	public void testSkuSelectContainsCondition() {
		plp.skuSelectContainsCondition();
	}

	@Test(priority = 21)
	public void testProductStatusSelectContainsCondition() {
		plp.productStatusSelectContainsCondition();
	}

	@Test(priority = 22)
	public void testActivitySelectContainsCondition() {
		plp.activitySelectContainsCondition();
	}

	@Test(priority = 23)
	public void testTypeSelectContainsCondition() {
		plp.typeSelectContainsCondition();
	}

	@Test(priority = 24)
	public void testVendorSelectContainsCondition() {
		plp.vendorSelectContainsCondition();
	}

	@Test(priority = 25)
	public void testButtonsAreDisplayed() {
		plp.buttonsAreDisplayed();
	}

	@Test(priority = 26)
	public void testModalIsDisplayedWhenSyncBtnIsClicked() {
		plp.modalIsDisplayedWhenSyncBtnIsClicked();
	}

	@Test(priority = 27)
	public void testBulkUpdateContainsImportExportBtn() {
		plp.bulkUpdateContainsImportExportBtn();
	}

	@Test(priority = 28)
	public void testOnClickingExportBtnOpenModal() {
		plp.exportBtnOpenModal();
		plp.closeExportModal();
	}

	@Test(priority = 29)
	public void testSelectedProductCountIsDisplayed() {
		plp.clickOnMoreFiltersBtn();
		util.hold(2);
		plp.selectedProductCountIsDisplayed();
	}

	@Test(priority = 30)
	public void testIfNotSelectedFilterExportModalBtnIsDisabled() {
		plp.exportModalIsDisplayed();
		plp.ifNotSelectedFilterExportModalBtnIsDisabled();
	}

	@Test(priority = 31)
	public void testExportModalContainsElements() {
		plp.exportModalContainsElements();
	}

	@Test(priority = 32)
	public void testCursorPropertyChanges() {
		plp.cursorPropertyChanges();
	}

	@Test(priority = 33)
	public void testCrossBtnClosesModal() {
		plp.crossBtnClosesModal();
	}

	@Test(priority = 34)
	public void testImportModalIsOpened() {
		plp.importModalIsOpened();
	}

	@Test(priority = 35)
	public void testImportModalContains() {
		plp.importModalContains();
	}

	@Test(priority = 36)
	public void testNeedHelpLinkImportModalIsWorking() {
		plp.needHelpLinkImportModalIsWorking();
	}

	@Test(priority = 37)
	public void testCloseImportModal() {
		plp.closeImportModal();
	}

	@Test(priority = 38)
	public void testCheckboxesBeforeAllProducts() {
		plp.checkboxesBeforeAllProducts();
	}

	@Test(priority = 39)
	public void testSelectActionContainsVariousActions() {

		plp.selectActionContainsVariousActions();
	}

	@Test(priority = 40)
	public void testOnClickingCrossBtnCheckBoxGetsUnchecked() {
		plp.onClickingCrossBtnCheckBoxGetsUnchecked();
	}

	@Test(priority = 41)
	public void testSelectAllProducts() {
		plp.selectAllProducts();
	}

	@Test(priority = 42)
	public void testHeadingsAreDisplayed() {
		plp.headingsAreDisplayed();
	}

	@Test(priority = 43)
	public void testSelectVariantsFilter() {
		plp.selectVariantsFromFilter();
	}

	@Test(priority = 44)
	public void testVariantGridContainsHeading() {
		plp.variantGridContainsHeading();
	}

	@Test(priority = 45)
	public void testImgSectionContainsImg() {
		plp.imagesSectionIsVisible();
		plp.imgSectionContainsImg();
		plp.onHoveringImgCursorPropertyIsNotChanging();
	}

	@Test(priority = 46)
	public void testVariantsGridNotContainsTitle() {
		plp.variantsGridNotContainsTitle();
	}

	@Test(priority = 47)
	public void testInventoryIsShownInVariantGrid() {
		plp.inventoryIsShownInVariantGrid();
		plp.clearFilters();
	}

	@Test(priority = 48)
	public void testRedirectionToTemplates() {
		plp.redirectionToTemplates();
		plp.createNewShopifyProd();
		plp.createManualTemplate();
		util.goToListingsClick();
	}

	@Test(priority = 49)
	public void testManualTemplateIsCreated() {
		plp.validateManualTemplateIsCreated(CreateShopifyProduct.nameOfProduct);

	}

	@Test(priority = 50)
	public void testManualTemplateContainsChangeAndRemoveTemplateBtn() {
		plp.manualTemplateContainsChangeAndRemoveTemplateBtn(CreateShopifyProduct.nameOfProduct);
	}

	@Test(priority = 51)
	public void testChangeTemplate() {
		plp.changeTemplate(CreateShopifyProduct.nameOfProduct);
	}

	@Test(priority = 52)
	public void testAssignOldTemplateToProduct() {
		plp.assignOldTemplateToProduct(CreateShopifyProduct.nameOfProduct);
	}

	@Test(priority = 53)
	public void testChangeTemplateConfirm() {
		plp.changeTemplate(CreateShopifyProduct.nameOfProduct);
		plp.clickOnApplyNewTemplate();
		plp.templateIsChangedSuccessfully(CreateShopifyProduct.nameOfProduct);
	}

	@Test(priority = 54, description = "If templated is not assigned to any product and if atleast one template is already created then '+' and 'N/A' is displayed ")
	public void testAddTemplateBtnIsDisplayed() {

		plp.unassignedTemplateProductContainsAddTemplateBtn();
	}

	@Test(priority = 55, description = "assign template to the product from listing .")
	public void testTemplateCanBeAssignToProductFromListing() {
		plp.assignTemplateFromListing();
	}

	@Test(priority = 56, description = "test edit btn is working")
	public void testClickOnEditProductBtn() {

		util.switchToIFrame();
		plp.clickOnEditProductBtn();
	}

	@Test(priority = 57, description = "test next and previous pagination btn is working")
	public void testPaginationBtnIsWorking() {
		plp.clearActiveTag();

		plp.paginationBtnIsWorking();
		plp.footerLinksAreWorking();
	}

}
