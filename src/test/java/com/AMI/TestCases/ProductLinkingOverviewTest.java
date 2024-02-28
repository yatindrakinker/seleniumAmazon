package com.AMI.TestCases;

import org.testng.annotations.Test;

import com.ami.pageobjects.CreateShopifyProduct;
import com.ami.pageobjects.ProductLinkingOverviewPage;
import com.ami.pageobjects.ProductLinkingPage;
import com.ami.resources.BaseClass;

public class ProductLinkingOverviewTest extends BaseClass {
	ProductLinkingPage plp;
	ProductLinkingOverviewPage prodOver;
	CreateShopifyProduct create;
	

	@Test(priority = 1)
	public void testThreeOptionsAreDisplayed() {
		plp = new ProductLinkingPage(util);
		prodOver = new ProductLinkingOverviewPage(util);
		plp.validateVisiblityOnOverView();
		create = new CreateShopifyProduct(util);
	}

	@Test(priority = 2)
	public void testCountIsDisplayed() {
		plp.validateCountIsDisplayed();
	}

	@Test(priority = 3)
	public void testCursorPropertyChanges() {
		plp.validateCursorPropertyChanges();
	}

	@Test(priority = 4)
	public void testValidateRedirectFromOverview() {
		plp.validateRedirectFromOverview();
	}

	@Test(priority = 5)
	public void testManageLinkingButton() {
		prodOver.onClickingManageLinking();
		plp.selectFBM();
		plp.selectFBA();
	}

	@Test(priority = 6)
	public void testAddNewSellerBtnIsWorking() {
		prodOver.addNewAcntIsWorking();
	}

	@Test(priority = 7)
	public void testEachSectionContainsSearchField() {
		prodOver.eachSectionContainsSearchField();
	}

	@Test(priority = 8)
	public void testSearchInputFieldIsWorkingInLinkingReq() {
		prodOver.searchInputFieldIsWorkingForSKUInLinkingReq();
		prodOver.searchInputFieldIsWorkingForBarcodeInLinkingReq();
		prodOver.searchInputFieldIsWorkingForTitleInLinkingReq();
	}

	@Test(priority = 9)
	public void testCloseMatchSection() {
		prodOver.closeMatchSection();
	}
	

	@Test(priority = 10)
	public void testProductInCloseMatchContains() {
		prodOver.productInCloseMatchContains();
	}
	
	@Test(priority = 11)
	public void testViewOnAmazonLinkIsWorking() {
		prodOver.viewOnAmazonLinkIsWorking();
	}
	
	@Test(priority = 12)
	public void testActionColContainsTwoBtns() {
		prodOver.actionColContainsTwoBtns();
	}
	
	@Test(priority = 13)
	public void testLinkingProdAreShown() {
		prodOver.linkingReqProducts();
		prodOver.linkingProdAreShown();
//		plp.validateSortFunctionality();
	}
	
	@Test(priority = 14)
	public void testLinkProductFunctionality() {
		prodOver.linkProductFunctionality();
	}
	
	@Test(priority = 15)
	public void testLinkProductModalContains() {
		prodOver.searchLinkProductModalContainsSearchInputField();
		prodOver.viewOnShopifyLinkIsWorking();
		prodOver.viewOnAmazonInLinkProductModalIsWorking();
		
	}
	
	@Test(priority = 16)
	public void testLinkAShopifyProduct() {
		prodOver.linkAShopifyProduct();
		
	}
	
	@Test(priority = 17)
	public void testAutoSyncOffBtnIsDisplayed() {
		plp.autoSyncOffBtnIsDisplayed();
	}
	
	@Test(priority = 18)
	public void testAutoSyncOffIsEnabled() {
		util.openAndSwitchToNewTab();
		create.createNewShopifyProduct();
		util.goToSection("linking");
		util.switchToIFrame();
		plp.verifyCheckboxStatusIsSameBeforeLinkingAndAfterUnlinking(CreateShopifyProduct.nameOfProduct);
		
	}
	
	
}
