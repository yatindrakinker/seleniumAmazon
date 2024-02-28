package com.AMI.TestCases;

import org.testng.annotations.Test;

import com.ami.pageobjects.CreateShopifyProduct;
import com.ami.pageobjects.ProductEditOfferListingPage;
import com.ami.resources.BaseClass;

public class ProductEditOfferListingTest extends BaseClass {

	ProductEditOfferListingPage pep;
	CreateShopifyProduct cr;

	@Test(priority = 1)
	public void testOfferListingRadioBtnIsSelected() {
		pep = new ProductEditOfferListingPage(util);
		cr = new CreateShopifyProduct(util);
		String url = util.getPageURL();
		util.openAndSwitchToNewTab();
		cr.createNewShopifyProduct();
		util.gotoWindowByClosingCurrentOne(url);
		util.switchToIFrame();
		util.openAndSwitchToNewTab();
		util.openUrl(url);
		util.clickOnSettings();
		util.clickOnCustomTemplates();
		pep.assignDefaultCategoryToProduct(CreateShopifyProduct.nameOfProduct);
		util.gotoWindowByClosingCurrentOne(url);
		util.switchToIFrame();
		util.clickOnListings(util.getConfigProperty("store"));
		pep.editProduct(CreateShopifyProduct.nameOfProduct);
		pep.offerListingRadioButtonIsSelected();
	}

	@Test(priority = 2)
	public void testSKUInputFieldContainsSameValue() {
		pep.skuInputFieldContainsValue(CreateShopifyProduct.skuOfProudct);
	}

	@Test(priority = 3)
	public void testBarcodeInputFieldContainsSameValue() {
		pep.barcodeInputFieldContainsValue(CreateShopifyProduct.barcodeOfProduct);
	}

	@Test(priority = 4)
	public void testRequiredAttributeSectionIsDisplayed() {
		pep.requiredAttributeSectionIsDisplayed();
	}

	@Test(priority = 5)
	public void testRequiredAttributeSectionAttribute() {
		pep.requiredAttributeSectionAttribute();
	}

	@Test(priority = 6)
	public void testSectionInOfferListing() {
		pep.sectionInOfferListing();
	}

	@Test(priority = 7)
	public void testEachSectionContainsTwoRadioButtons() {
		pep.eachSectionContainsTwoRadioButtons();
	}

	@Test(priority = 8)
	public void testSimpleProductDoesNotContainVariantSectionInOfferListing() {
		pep.simpleProductDoesNotContainVariantSectionInOfferListing();
	}

	@Test(priority = 9)
	public void testSetCustomValuesAndValidate() {
		pep.setCustomValuesAndValidate();
	}
	
	@Test(priority = 10)
	public void testMouseHoverAtInventoryVal() {
		pep.clickOnEditProduct();
		pep.mouseHoverAtInventoryVal();
	}
	
	@Test(priority = 11)
	public void testFixedInventory() {
		pep.setFixedInventory(CreateShopifyProduct.nameOfProduct);
		pep.validateFixedInventory();
	}

	@Test(priority = 12)
	public void testReservedInventory() {
		pep.setReservedInventory(CreateShopifyProduct.nameOfProduct);
		pep.validateReservedInventory();
	}
	
	@Test(priority = 13)
	public void testMaxInventory() {
		String url = util.getPageURL();
		util.openAndSwitchToNewTab();
		util.openUrl(CreateShopifyProduct.productUrl);
		cr.enableContinueSelling();
		util.gotoWindowByClosingCurrentOne(url);
		util.switchToIFrame();
		pep.setMaximumInventory(CreateShopifyProduct.nameOfProduct);
		pep.validateMaximumInventory();
	}

//	@Test(priority = 14)
//	public void testWareHouseInventory() {
//		String url = util.getPageURL();
//		pep.setWarehouseInventory(CreateShopifyProduct.nameOfProduct);
//		util.gotoWindowByClosingCurrentOne(url);
//		util.hold(25);
//		pep.openEditTemplate(CreateShopifyProduct.nameOfProduct);
//		pep.validateInventory();
//	}
//	
//	@Test(priority = 15)
//	public void setCustomInventory() {
//		
//	}
	
	@Test(priority = 16)
	public void testHoverAtPrice() {
		util.openListingsInNewTabUsingUrl();
		pep.editProduct(CreateShopifyProduct.nameOfProduct);
		pep.hoverAtPrice();
	}
	
	@Test(priority = 17)
	public void testCustomPriceInputFieldAcceptsVal() {
		pep.enterCustomPrice();
		pep.customPriceInputFieldAcceptsVal();
	}
	
	@Test(priority = 18)
	public void testPriceBreakupIsNotVisibleWhenCustomPriceIsEnabled() {
		pep.priceBreakupIsNotVisibleWhenCustomPriceIsEnabled();
	}
	
	@Test(priority = 19)
	public void testRequiredAttributesGetsResetWhenListAsIsChanged() {
		pep.requiredAttributesValGetsReset();
	}
	
	@Test(priority = 20)
	public void testSelectAttributes() {
		pep.selectAttributes();
	}
	
	@Test(priority = 21)
	public void testClickOnDifferentListAsButDoNotConfirmIt() {
		pep.clickOnDifferentListAsButDoNotConfirmIt();
		
	}
}
