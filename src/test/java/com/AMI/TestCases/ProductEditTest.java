package com.AMI.TestCases;

import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.ami.pageobjects.CreateShopifyProduct;
import com.ami.pageobjects.ProductEditPage;
import com.ami.pageobjects.ProductListingPage;
import com.ami.pageobjects.reusableclasses.ReusableMethods;
import com.ami.resources.BaseClass;

public class ProductEditTest extends BaseClass {
	ProductEditPage pep;
	ReusableMethods reuse;
	ProductListingPage plp;
	CreateShopifyProduct cr;

	@DataProvider
	public Object[][] getProductEdit() throws IOException {
		List<HashMap<String, String>> data = util.getJsonDataToHashmap("ProductEdit");
		return new Object[][] { { data.get(0) } };
	}

//	Not Listed Products cases starts here
	@Test(priority = 1, dataProvider = "getProductEdit")
	public void runValidateRedirectionToEditPage(HashMap<String, String> input) {
		plp = new ProductListingPage(util);
		pep = new ProductEditPage(util);
		reuse = new ReusableMethods(util);
		cr = new CreateShopifyProduct(util);
		String url = util.getPageURL();
		util.openAndSwitchToNewTab();
		cr.createNewShopifyProduct();
		util.gotoWindowByClosingCurrentOne(url);
		util.switchToIFrame();
		pep.validateRedirectionToEditPage(input);
		pep.clickOnEditProduct();

	}

	@Test(priority = 2, dataProvider = "getProductEdit")
	public void testFieldsAreVisible(HashMap<String, String> input) {
		String url = util.getPageURL();
		util.openAndSwitchToNewTab();
		util.getDriver().get(url);
		reuse.clickOnSettings();
		reuse.createNewTemplateUsingAdvanceSelection();
		util.goToSection("listings");
		util.refreshPage();
		util.switchToIFrame();
		plp.searchAndSelectProduct(CreateShopifyProduct.nameOfProduct);
		pep.clickOnEditProduct();
//		pep.validateFieldsAreVisible();
	}

	@Test(priority = 3, dataProvider = "getProductEdit")
	public void testRadioBtnsAreClickable(HashMap<String, String> input) {
//		need to  update this test case as some functionality is changed.
//		pep.validateRadioBtnsAreClickable();
	} // Not Listed Products cases ends here

	// New Listing cases are start from here

	@Test(priority = 4)
	public void testNewListingRadioBtnIsSelectedByDefault() {
		pep.validateNewListingRadioBtnIsSelectedByDefault();
		pep.validateNewListingFieldsAreVisible();
	}

	// This test case includes all the cases of Title section

	@Test(priority = 5, dataProvider = "getProductEdit")
	public void testNewListingTitleSection(HashMap<String, String> input) {
		pep.newListingTitleSection(input);
	}

	@Test(priority = 6, dataProvider = "getProductEdit")
	public void testNewListingDescriptionSectionFunctionality(HashMap<String, String> input) {
		pep.newListingDescriptionSectionFunctionality();
	}

	@Test(priority = 7, dataProvider = "getProductEdit")
	public void runValidateTimeHandlingFunctionality(Map<String, String> input) {
		pep.validateTimeHandlingFunctionality(input);
	}

	@Test(priority = 8, dataProvider = "getProductEdit")
	public void runValidateBarcodeSection(HashMap<String, String> input) {
		pep.validateBarcodeSection();
	}

	@Test(priority = 9, dataProvider = "getProductEdit")
	public void runValidateImageSectionFunctionality(HashMap<String, String> input) {
		pep.validateImageSectionFunctionality();
	}

	@Test(priority = 10, dataProvider = "getProductEdit")
	public void runValidatePriceSectionFunctionality(HashMap<String, String> input) {
		pep.validatePriceSectionFunctionality();
	}

	@Test(priority = 11, dataProvider = "getProductEdit")
	public void runValidateQuantitySectionFunctionality(HashMap<String, String> input) {
		pep.validateQuantitySectionFunctionality();
	}

	@Test(priority = 12, dataProvider = "getProductEdit")
	public void runValidateSKUSectionFunctionality(HashMap<String, String> input) {
		pep.validateSKUSectionFunctionality();
	}

	@Test(priority = 13)
	public void testAmazonCategorySection() {
		pep.addAmazonCategoryIsDisplayed();
	}

	@Test(priority = 14)
	public void testAmazonCategorySameCategoryIsDisabled() {
		pep.sameCategorySettingsIsDisabledIfNotTemplateIsAssign();
	}

	@Test(priority = 15)
	public void testAmazonCategoryAreDisplayed() {
		pep.addAmazonCategoryAreDisplayed();
	}

	@Test(priority = 16)
	public void testRequiredAttributesAreVisible() {
		pep.requiredAttributesAreVisible();
	}

	@Test(priority = 17)
	public void testOptionalAttributesSectionDisplayed() {
		pep.optionalAttributesSectionDisplayed();
		pep.addOptionalAttribute();
	}

	@Test(priority = 18)
	public void testSetCustomOption() {
		pep.setCustomOption();
	}

	@Test(priority = 19)
	public void testSetShopifyAttributeOption() {
		pep.setShopifyAttributeOption();
	}

	@Test(priority = 20)
	public void testClickingOnBackBtnRemovesCategory() {
		pep.clickingOnBackBtnRemovesCategory();
	}

	@Test(priority = 21)
	public void testAmazonSearchCategory() {
		pep.searchCategory();
	}

	// @Test(priority = 22) // public void testVariantThemeSection() {
	// pep.variantThemeSection();
	// }

	// // @Test(priority = 23)
	// public void testofferListingEditPage() { 
  // pep.offerListingEditPage(); 
  // }

}