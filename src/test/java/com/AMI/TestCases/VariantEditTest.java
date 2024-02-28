package com.AMI.TestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WindowType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ami.pageobjects.CreateShopifyProduct;
import com.ami.pageobjects.ProductEditPage;
import com.ami.pageobjects.VariantEditPage;
import com.ami.resources.BaseClass;

public class VariantEditTest extends BaseClass {
	VariantEditPage vep;
	ProductEditPage pep;
	CreateShopifyProduct cr;

	@DataProvider
	public Object[][] getProductEdit() throws IOException {
		List<HashMap<String, String>> data = util.getJsonDataToHashmap("ProductEdit");
		return new Object[][] { { data.get(0) } };
	}
	
	@Test(priority = 1)
	public void initialize() {
		cr = new CreateShopifyProduct(util);
		pep = new ProductEditPage(util);
		vep = new VariantEditPage(util);
	}

	@Test(priority = 1, dataProvider = "getProductEdit")
	public void testEditBtn(Map<String, String> input) {
		String url = util.getPageURL();
		CreateShopifyProduct.productUrl = "";
		util.getDriver().switchTo().newWindow(WindowType.TAB);
		cr.goToCreateNewProductPage(util.getConfigProperty("store"));
		cr.createNewShopifyProduct();
		util.gotoWindowByClosingCurrentOne(url);
		util.switchToIFrame();
		pep.validateRedirectionToEditPage(input);
		pep.clickOnEditProduct();

	}

	@Test(priority = 2)
	public void testNewListingIsSelectedByDefault() {
		vep.newListingIsSelectedByDefault();
	}

	
	/*
	 * functionality updated need to update it after testcases are updated.
	 */
//	@Test(priority = 3)
//	public void testRadioBtnAreWorking() {
//		vep.offerListingRadioBtnWorking();
//		vep.newListingRadioBtnWorking();
//	}

	@Test(priority = 4)
	public void testRedirectionToShopifyProductPage() {
		ProductEditPage pep = new ProductEditPage(util);
		pep.redirectionToShopifyProductPage();
	}

	@Test(priority = 5)
	public void testRedirectionToAmazonListingPage() {
		ProductEditPage pep = new ProductEditPage(util);
		pep.redirectionToShopifyLisingDoc();
	}

	/**
	 * removed
	 */
//	@Test(priority = 6)
//	public void testAsteriskAreDisplayed() {
//		vep.asteriskAreDisplayed();
//	}

	@Test(priority = 7)
	public void testSetSameProductTitleForShopifyAmazonIsSelectedByDefault() {
		vep.setSameProductTitleForShopifyAmazonIsSelectedByDefault();
	}

	@Test(priority = 8)
	public void testVariantTitleSectionRadioBtnAreWorking() {
		vep.variantTitleSectionRadioBtnAreWorking();
	}

	@Test(priority = 9)
	public void testSetCustomTitleInputFieldBlank() {
		vep.setCustomTitleInputFieldBlank();
	}

	@Test(priority = 10)
	public void testSetCustomTitle() {
		vep.setCustomTitle();
	}

	@Test(priority = 11)
	public void testSetCustomTitleWithSpace() {
		vep.setCustomTitleWithSpace();
		vep.setDefaultTitleSetting();
	}

	@Test(priority = 12)
	public void testSetTheSameProductDescriptionForShopifyAndAmazon() {
		vep.setTheSameProductDescriptionForShopifyAndAmazon();
	}

	@Test(priority = 13)
	public void testVariantDescriptionSectionRadioBtnAreWorking() {
		vep.variantDescriptionSectionRadioBtnAreWorking();
	}

	@Test(priority = 14)
	public void testSetCustomDescriptionInputFieldBlank() {
		vep.setCustomDescriptionInputFieldBlank();
	}

	@Test(priority = 15)
	public void testSetCustomDescription() {
		vep.setCustomDescription();
	}

	@Test(priority = 16)
	public void testSetCustomDescriptionWithSpaces() {
		vep.setCustomDescriptionWithSpaces();
	}

	@Test(priority = 17)
	public void testHandlingTimeSectionRadioBtnAreWorking() {
		vep.handlingTimeSectionRadioBtnAreWorking();
	}

	@Test(priority = 18)
	public void testHandlingInputFieldContainsTime() {
		vep.handlingInputFieldContainsTime();
	}

	@Test(priority = 19)
	public void testByDefaultCustomInputFieldContainsOne() {
		vep.byDefaultCustomInputFieldContainsTwo();
	}

	@Test(priority = 20)
	public void testCustomHandlingTimeCanBeAssigned() {
		vep.customHandlingTimeCanBeAssigned();
	}

	@Test(priority = 21)
	public void testInputFieldAcceptsOnlyWholeNum() {
		vep.inputFieldAcceptsOnlyWholeNum();
	}

	@Test(priority = 22)
	public void testInputFieldAcceptsValueBetweenZeroToThirtyValid() {
		vep.inputFieldAcceptsValueBetweenZeroToThirtyValid();
	}

	@Test(priority = 23)
	public void testInputFieldAcceptsValueBetweenZeroToThirtyInvalid() {
		vep.inputFieldAcceptsValueBetweenZeroToThirtyInvalid();
	}

	@Test(priority = 24)
	public void testVariantPriceSectionRadioBtnAreWorking() {
		vep.samePriceForShopifyAndAmazonRadioBtnIsSelectedByDefault();
		vep.variantPriceSectionRadioBtnAreWorking();
	}

	@Test(priority = 25)
	public void testCustomPricAcceptsDecimalValues() {
		util.switchToIFrame();
		vep.customPricAcceptsDecimalValues();
	}

	@Test(priority = 26)
	public void testBarcodeSectionDefaultSetting() {
		vep.barcodeSectionDefaultSetting();
	}

	@Test(priority = 27)
	public void testBarcodeSectionRadioBtnWorking() {
		vep.barcodeSectionRadioBtnWorking();
	}

	@Test(priority = 28)
	public void testCustomBarcodeInputFieldIsBlank() {
		util.switchToIFrame();
		vep.customBarcodeInputFieldIsBlank();
	}

	@Test(priority = 29)
	public void testSetCustomBarcode() {
		vep.setCustomBarcode();
	}

	@Test(priority = 30)
	public void testSetCustomBarcodeDecimalValue() {
		vep.setCustomBarcodeDecimalValue();
	}

	@Test(priority = 31)
	public void testInventorySectionDefaultSetting() {
		vep.inventorySectionDefaultSetting();
	}

	@Test(priority = 32)
	public void testInventorySectionRadioBtnAreWorking() {
		vep.inventorySectionRadioBtnAreWorking();
	}

	@Test(priority = 33)
	public void testCustomInventoryAcceptsWholeNum() {
		vep.customInventoryAcceptsWholeNum();
	}

	@Test(priority = 34)
	public void testVariantSKUSectionDefaultSetting() {
		vep.variantSKUSectionDefaultSetting();
	}

	@Test(priority = 35)
	public void testVariantSKUSectionRadioBtnWorking() {
		vep.variantSKUSectionRadioBtnWorking();
	}

	@Test(priority = 36)
	public void testSetCustomSKU() {
		vep.setCustomSKU();
	}
	
	@Test(priority = 37)
	public void testVariantImgRadioBtnWorking() {
		vep.variantImgSelectionDefaultSetting();
		vep.variantImgRadioBtnWorking();
	}

	/**
	 * functionaity updated need to go through conditions to validate.
	 */
//	@Test(priority = 38)
//	public void testOfferListing() {
//		vep.offerListing();
//	}
	
	@Test(priority = 39)
	public void testOfferListingVariantTitleDefaultSetting() {
		vep.offerListingVariantTitleDefaultSetting();
	}
	
	@Test(priority = 40)
	public void testOfferListingVariantTitleSectionRadioBtnAreWorking() {
		vep.offerListingVariantTitleSectionRadioBtnAreWorking();
	}
	
	@Test(priority = 41)
	public void testOfferListingVariantTitleSetCustomTitleInputFieldBlank() {
		vep.offerListingVariantTitleSetCustomTitleInputFieldBlank();
	}
	
	@Test(priority = 42)
	public void testOfferListingVariantSetCustomTitle() {
		vep.offerListingVariantSetCustomTitle();
	}
	
	@Test(priority = 43)
	public void testOfferListingSetCustomTitleWithSpace() {
//		vep.offerListingSetCustomTitleWithSpace();
	}
	
	@Test(priority = 44)
	public void testOfferListingSameProductDescriptionForShopifyAndAmazon() {
		vep.offerListingSameProductDescriptionForShopifyAndAmazon();
	}
	
	@Test(priority = 45)
	public void testOfferListingVariantDescriptionSectionRadioBtnAreWorking() {
		vep.offerListingVariantDescriptionSectionRadioBtnAreWorking();
	}
	
	@Test(priority = 46)
	public void testOfferListingSetCustomDescriptionInputFieldBlank() {
		vep.offerListingSetCustomDescriptionInputFieldBlank();
	}
	
	@Test(priority = 47)
	public void testOfferListingSetCustomDescription() {
		vep.offerListingSetCustomDescription();
	}
	
	@Test(priority = 48)
	public void testOfferListingSetCustomDescriptionWithSpaces() {
		vep.offerListingSetCustomDescriptionWithSpaces();
	}
	
	@Test(priority = 49)
	public void testOfferListingHandlingTimeSectionRadioBtnAreWorking() {
		vep.offerListingHandlingTimeSectionRadioBtnAreWorking();
	}
	
	@Test(priority = 50)
	public void testOfferListingHandlingInputFieldContainsTime() {
		vep.offerListingHandlingInputFieldContainsTime();
	}	

	@Test(priority = 51)
	public void testOfferListingByDefaultCustomInputFieldContainsOne() {
		vep.offerListingByDefaultCustomInputFieldContainsOne();
	}
	
	@Test(priority = 52)
	public void testOfferListingCustomHandlingTimeCanBeAssigned() {
		vep.offerListingCustomHandlingTimeCanBeAssigned();
	}
	
	@Test(priority = 53)
	public void testOfferListingInputFieldAcceptsOnlyWholeNum() {
		vep.offerListingInputFieldAcceptsOnlyWholeNum();
	}
	
	@Test(priority = 54)
	public void testOfferListingInputFieldAcceptsValueBetweenZeroToThirtyValid() {
		vep.offerListingInputFieldAcceptsValueBetweenZeroToThirtyValid();
	}
	
	@Test(priority = 55)
	public void testOfferListingInputFieldAcceptsValueBetweenZeroToThirtyInvalid() {
		vep.offerListingInputFieldAcceptsValueBetweenZeroToThirtyInvalid();
	}

}
