package com.AMI.TestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ami.pageobjects.CreateShopifyProduct;
import com.ami.pageobjects.ProductLinkingPage;
import com.ami.pageobjects.reusableclasses.ReusableMethods;
import com.ami.resources.BaseClass;

import webhooksAMI.WebHookPage;

public class ProductLinkingTest extends BaseClass {
	CreateShopifyProduct create;
	WebHookPage web;
	ProductLinkingPage plt;
	ReusableMethods reuse;

	@DataProvider
	public Object[][] getProductLinkingData() throws IOException {
		List<HashMap<String, String>> data = util.getJsonDataToHashmap("ProductLinkingData");
		return new Object[][] { { data.get(0) } };
	}
	
	@Test(priority = 1)
	public void initialize() {
		plt = new ProductLinkingPage(util);
		create = new CreateShopifyProduct(util);
		web = new WebHookPage(util);
		reuse = new ReusableMethods(util);
	}

	@Test(priority = 1)
	public void testVisiblityOfElementsOnOverView() {
		
		plt.validateVisiblityOnOverView();
	}

	@Test(priority = 2)
	public void testCountIsDisplayed() {
		plt.validateCountIsDisplayed();
	}

	@Test(priority = 3)
	public void testCursorPropertyChanges() {
		plt.validateCursorPropertyChanges();
	}

	@Test(priority = 4)
	public void testValidateRedirectFromOverview() {
		plt.validateRedirectFromOverview();
	}

	@Test(priority = 5)
	public void testLinkedProducts() {
		plt.validateVisibilityOfLinkedProductsElement();
		plt.validateWorkingOfLinkedElements();
		plt.validateAddNewSellerWorking();

	}

	@Test(priority = 6)
	public void testRdirectionFromOneTabToAnother() {
		plt.validateRedirectionToAmazon();
		plt.validateRedirectionToShopifyProduct();
		plt.validateHyperlinksAreVisible();
		plt.clickOnBackBtn();
		plt.redirectionFromOneTabToAnother();
	}

	@Test(priority = 7, dataProvider = "getProductLinkingData")
	public void testSearchInputFieldFunctionality(HashMap<String, String> input) {
		plt.validateRedirectionToAmazon();
	}

	@Test(priority = 8)
	public void testVisibilityOfLinkingRequiredProductsElement() {
		plt.validateVisibilityOfLinkingRequiredElementOnLinkAmazonProducts();
		plt.validateSortFunctionality();
		plt.redirectionToViewOnAmazonLinkingReq();
		plt.linkProductFunctionality();
		plt.viewOnAmzLinkFunctionalityModal();
		plt.viewOnShopifyFunctionalityModal();
		plt.linkBtnFunctionality();
	}

	@Test(priority = 9)
	public void testLinkProduct() {
		reuse = new ReusableMethods(util);
		util.openAndSwitchToNewTab();
		create = new CreateShopifyProduct(util);
		web = new WebHookPage(util);
		create.createNewShopifyProduct();
		util.hold(30);
		reuse.goToLinking();
		web.linkNewlyCreatedProduct();
	}

	@Test(priority = 10)
	public void testUnlinkBtn() {
		plt.unlinkBtnFunctionalityLinked();
	}

	@Test(priority = 11)
	public void testVisibilityOfCloseMatchProduct() {
		plt.validateVisibilityOfCloseMatchProduct();
		plt.validateRedirectionToShopifyProduct();
		plt.validateRedirectionToAmazon();
	}

	@Test(priority = 12, dataProvider = "getProductLinkingData")
	public void testMatchFoundFunctionalityLinkModalCloseMatch(HashMap<String, String> input) {
		plt.matchFoundFunctionality(input);
		plt.validateCancelAndCrossBtnWorking();
	}

	@Test(priority = 13, dataProvider = "getProductLinkingData")
	public void testCloseMatchFunctionalityLinkModal(HashMap<String, String> input) {
		plt.closeMatchSection();
	}

	@Test(priority = 14, dataProvider = "getProductLinkingData")
	public void testSelectManuallyFunctionality(HashMap<String, String> input) {
		plt.selectManually(input);
	}

	@Test(priority = 15, description = "117-121")
	public void testDeleteAmzonProductBtnIsWorkingInLinkingReq() {

		plt.validateDeleteBtnInLinkingRequired();
		plt.viewOnAmzLinkFunctionalityModal();
		plt.closeDeleteModal();
	}

	@Test(priority = 16, description = "129-132")
	public void testLinkingRequiredCount() {
		plt.getCountOnOverview();
		plt.validateLinkingRequiredCount();
	}

	@Test(priority = 17, description = "129-132")
	public void testClosedMatchCount() {
		util.switchToIFrame();
		plt.validateClosedMatchCount();
	}

	@Test(priority = 18, description = "129-132")
	public void testLinkedCount() {
		plt.validateLinkedCount();

	}

	/**
	 * can not run this code as it deletes the listed product from the marketplace.
	 */
//	@Test(priority = 19, description = "134-137")
//	public void testVerifyDeleteTabOnLinkAmazon() {
//		plt.verifyDeleteTabOnLinkAmazon();
//	}

	/**
	 * This case is not feasible for the automation because it leads to error as the
	 * product is available on amazon.
	 */
//	@Test(priority = 20, description = "138,139")
//	public void testDeleteProductInDeleteListing() {
//
//		plt.deleteProductInDeleteListing();
//	}

}
