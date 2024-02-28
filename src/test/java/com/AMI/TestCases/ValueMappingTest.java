package com.AMI.TestCases;

import org.testng.annotations.Test;

import com.ami.pageobjects.CreateShopifyProduct;
import com.ami.pageobjects.ProductEditOfferListingPage;
import com.ami.pageobjects.ProductListingPage;
import com.ami.pageobjects.ValueMappingPage;
import com.ami.pageobjects.reusableclasses.ReusableMethods;
import com.ami.resources.BaseClass;

public class ValueMappingTest extends BaseClass {

	ValueMappingPage map;
	CreateShopifyProduct create;
	ProductEditOfferListingPage pep;
	ReusableMethods reuse;
	ProductListingPage plp;

	@Test(priority = 1)
	public void initialize() {
		map = new ValueMappingPage(util);
		create = new CreateShopifyProduct(util);
		reuse = new ReusableMethods(util);
		plp = new ProductListingPage(util);;
	}

	@Test(priority = 2)
	public void testValueMappingLinkIsGeneratedForProductType() {
		map.createTemplateForValueMapping("gift cards", "productType");
		map.mapAttributeLinkIsDisplayed(CreateShopifyProduct.nameOfProduct);
		map.verifyAttributeIsMapped();
		map.verifyFeedIsGeneratedOnListing();
		map.validateCountryInFeeds("India");
	}
	
	private void testValueMappingLinkIsGenerated(String attribute) {
		map.editShopifyAttributeValueMapping(CreateShopifyProduct.nameOfProduct, "gift cards", attribute);
		reuse.closeModal();
		map.mapAttributeLinkIsDisplayed(CreateShopifyProduct.nameOfProduct);
		map.verifyAttributeIsMapped();
		util.goToSection("listings");
		map.uploadProduct();
		reuse.clickOnActivityFeedBtn();
		map.validateCountryInFeeds("India");
	}

	@Test(priority = 3)
	public void testValueMappingLinkIsGeneratedForWeight() {
		testValueMappingLinkIsGenerated("weight");
	}

	@Test(priority = 4)
	public void testValueMappingLinkIsGeneratedForWeightUnit() {
		testValueMappingLinkIsGenerated("weightUnit");
		
	}

	@Test(priority = 5)
	public void testValueMappingLinkIsGeneratedForGrams() {
		testValueMappingLinkIsGenerated("grams");
	}

	@Test(priority = 6)
	public void testValueMappingLinkIsNotGeneratedForTitle() {
		map.verifyValueMappingLinkIsNotGenerated(CreateShopifyProduct.nameOfProduct, "gift cards", "title");
	}
	
	@Test(priority = 7)
	public void testValueMappingLinkIsNotGeneratedForBrand() {
		map.verifyValueMappingLinkIsNotGenerated(CreateShopifyProduct.nameOfProduct, "gift cards", "brand");
	}
	
	@Test(priority = 8)
	public void testValueMappingLinkIsNotGeneratedForDescription() {
		map.verifyValueMappingLinkIsNotGenerated(CreateShopifyProduct.nameOfProduct, "gift cards", "description");
	}
	
	@Test(priority = 9)
	public void testValueMappingLinkIsNotGeneratedForTags() {
		map.verifyValueMappingLinkIsNotGenerated(CreateShopifyProduct.nameOfProduct, "gift cards", "tags");
	}
	
	@Test(priority = 10)
	public void testValueMappingLinkIsNotGeneratedForQuantity() {
		map.verifyValueMappingLinkIsNotGenerated(CreateShopifyProduct.nameOfProduct, "gift cards", "quantity");
	}
	
	@Test(priority = 11)
	public void testValueMappingLinkIsNotGeneratedForPrice() {
		map.verifyValueMappingLinkIsNotGenerated(CreateShopifyProduct.nameOfProduct, "gift cards", "price");
		map.editShopifyAttributeValueMapping(CreateShopifyProduct.nameOfProduct, "gift cards", "grams");
		reuse.closeModal();
		map.mapAttributeLinkIsDisplayed(CreateShopifyProduct.nameOfProduct);
	}
	
	@Test(priority = 12)
	public void testWhenProdIsDeletedOnShopifyLinkIsNotDisplayed() {
		reuse.openExistingShopifyProduct(CreateShopifyProduct.productId);
		create.deleteShopifyProduct();
		reuse.goToSettings();
		reuse.searchTemplateOnGrid();
		map.mapAttributeLinkIsNotDisplayed(CreateShopifyProduct.nameOfProduct);
		map.zeroProdAssignIsDisplayed();
	}
	
	@Test(priority = 13)
	public void testWhenProductIsAssignedOnAnotherTemplateMapLinkIsNotDisplayed() {
		String oldTemplateName = CreateShopifyProduct.nameOfProduct;
		map.createTemplateForValueMapping("gift cards", "productType");
		map.mapAttributeLinkIsDisplayed(CreateShopifyProduct.nameOfProduct);
		reuse.searchTemplateOnGrid(oldTemplateName);
		reuse.openEditTemplatePage(oldTemplateName);
		reuse.addNewProductInTemplate(CreateShopifyProduct.nameOfProduct);
		reuse.clickOnSave();
		map.waitUntilMapValueBtnIsDisplayed();
		reuse.closeModal();
		map.mapAttributeLinkIsNotDisplayed(CreateShopifyProduct.nameOfProduct);
		map.zeroProdAssignIsDisplayed();
		map.mapAttributeLinkIsDisplayed(CreateShopifyProduct.nameOfProduct);
	}

	@Test(priority = 14)
	public void testValueMappingLinkIsGeneratedForVariantTitle() {
		map.createTemplateForVariantProductValueMapping("gift bags", "variantTitle");
		map.mapAttributeLinkIsDisplayed(CreateShopifyProduct.nameOfProduct);
		map.mapAttributeLinkIsDisplayed(CreateShopifyProduct.nameOfProduct);
		map.openValueMapping();
		map.mapValuesForVariantProduct();
		util.goToSection("listings");
		plp.searchAndSelectProduct(CreateShopifyProduct.nameOfProduct);
		util.hold(2);
		map.uploadProduct();
		reuse.clickOnActivityFeedBtn();
		map.validateMappedValuesInFeedsForVariant("width", "CM");
		map.validateMappedValuesInFeedsForVariant("height", "CM");
		map.validateMappedValuesInFeedsForVariant("length", "CM");
		map.validateMappedValuesInFeedsForVariant("country", "India");
	}
	
	@Test(priority = 15)
	public void testValueMappingLinkIsGeneratedForCOLOR() {
		reuse.goToProductFromTemplateAndOpenEdit();
		reuse.enterCustomOptionName("COLOR", "0");
		util.pressEscape();
		reuse.enterCustomValForVariants("red", "0", "0");
		reuse.enterCustomValForVariants("black", "0", "1");
		reuse.clickOnVariantsDoneBtn();
		reuse.clickOnShopifySaveBtn();
		util.closeCurrentWindow();
		reuse.goToSettings();
		reuse.searchAndOpenEditTemplatePage(CreateShopifyProduct.nameOfProduct);;
		util.switchToIFrame();
		map.searchAndSelectCategory("gift bags", "COLOR");
		reuse.clickOnSave();
		reuse.waitForImportantInfoModal();
		reuse.waitForMapValBtn();
		reuse.searchTemplateOnGrid(CreateShopifyProduct.nameOfProduct);
		map.openValueMapping();
		map.mapValuesForVariantProduct();
	}
	
	@Test(priority = 16)
	public void testValueMappingLinkIsGeneratedForSIZE() {
		reuse.goToProductFromTemplateAndOpenEdit();
		reuse.enterCustomOptionName("SIZE", "0");
		util.pressEscape();
		reuse.enterCustomValForVariants("LARGE", "0", "0");
		reuse.enterCustomValForVariants("SMALL", "0", "1");
		reuse.clickOnVariantsDoneBtn();
		reuse.clickOnShopifySaveBtn();
		util.closeCurrentWindow();
		reuse.goToSettings();
		reuse.searchAndOpenEditTemplatePage(CreateShopifyProduct.nameOfProduct);;
		util.switchToIFrame();
		map.searchAndSelectCategory("gift bags", "SIZE");
		reuse.clickOnSave();
		reuse.waitForImportantInfoModal();
		reuse.waitForMapValBtn();
		reuse.searchTemplateOnGrid(CreateShopifyProduct.nameOfProduct);
		map.openValueMapping();
		map.mapValuesForVariantProduct();
	}

	@Test(priority = 17)
	public void testValueMappingLinkIsGeneratedForMaterial() {
		reuse.goToProductFromTemplateAndOpenEdit();
		reuse.selectPreDefinedVariants("Material");
		util.pressEscape();
		reuse.enterCustomValForVariants("Cotton", "0", "0");
		reuse.enterCustomValForVariants("Rubber", "0", "1");
		reuse.clickOnVariantsDoneBtn();
		reuse.clickOnShopifySaveBtn();
		util.closeCurrentWindow();
		reuse.goToSettings();
		reuse.searchAndOpenEditTemplatePage(CreateShopifyProduct.nameOfProduct);;
		util.switchToIFrame();
		map.searchAndSelectCategory("gift bags", "Material");
		reuse.clickOnSave();
		reuse.waitForImportantInfoModal();
		reuse.waitForMapValBtn();
		reuse.searchTemplateOnGrid(CreateShopifyProduct.nameOfProduct);
		map.openValueMapping();
		map.mapValuesForVariantProduct();
	}
	
	@Test(priority = 18)
	public void testValueMappingLinkIsGeneratedForSize() {
		reuse.goToProductFromTemplateAndOpenEdit();
		reuse.selectPreDefinedVariants("Size");
		util.pressEscape();
		reuse.enterCustomValForVariants("LARGE", "0", "0");
		reuse.enterCustomValForVariants("SMALL", "0", "1");
		reuse.clickOnVariantsDoneBtn();
		reuse.clickOnShopifySaveBtn();
		util.closeCurrentWindow();
		reuse.goToSettings();
		reuse.searchAndOpenEditTemplatePage(CreateShopifyProduct.nameOfProduct);;
		util.switchToIFrame();
		map.searchAndSelectCategory("gift bags", "Material");
		reuse.clickOnSave();
		reuse.waitForImportantInfoModal();
		reuse.waitForMapValBtn();
		reuse.searchTemplateOnGrid(CreateShopifyProduct.nameOfProduct);
		map.openValueMapping();
		map.mapValuesForVariantProduct();
	}
	
	@Test(priority = 19)
	public void testValueMappingLinkIsGeneratedForStyle() {
		reuse.goToProductFromTemplateAndOpenEdit();
		reuse.selectPreDefinedVariants("Style");
		util.pressEscape();
		reuse.enterCustomValForVariants("Indian", "0", "0");
		reuse.enterCustomValForVariants("Western", "0", "1");
		reuse.clickOnVariantsDoneBtn();
		reuse.clickOnShopifySaveBtn();
		util.closeCurrentWindow();
		reuse.goToSettings();
		reuse.searchAndOpenEditTemplatePage(CreateShopifyProduct.nameOfProduct);;
		util.switchToIFrame();
		map.searchAndSelectCategory("gift bags", "Style");
		reuse.clickOnSave();
		reuse.waitForImportantInfoModal();
		reuse.waitForMapValBtn();
		reuse.searchTemplateOnGrid(CreateShopifyProduct.nameOfProduct);
		map.openValueMapping();
		map.mapValuesForVariantProduct();
	}
	@Test(priority = 20)
	public void testValueMappingLinkIsGeneratedForMultipleProducts() {
//		reuse.searchAndOpenEditTemplatePage(CreateShopifyProduct.nameOfProduct);;
//		reuse.clickOnEditFilters();
//		reuse.addNewProductInTemplate("title", "not equals", "abc");
//		reuse.clickOnSave();
//		reuse.waitForMapValBtn();
//		reuse.searchTemplateOnGrid(CreateShopifyProduct.nameOfProduct);
//		map.openValueMapping();
//		reuse.searchAndDeleteTemplate(CreateShopifyProduct.nameOfProduct);
	}
	
	@Test(priority = 21)
	public void testSearchInputIsWorking() {
		reuse.gotoTemplateGrid();
		reuse.validateSearchInputDoesNotAcceptsSpecialChars("@#$%^&*()");
	}

}
