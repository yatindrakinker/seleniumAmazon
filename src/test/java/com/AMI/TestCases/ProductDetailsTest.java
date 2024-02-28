package com.AMI.TestCases;

import org.testng.annotations.Test;

import com.ami.pageobjects.CreateShopifyProduct;
import com.ami.pageobjects.ProductDetailsPage;
import com.ami.resources.BaseClass;

public class ProductDetailsTest extends BaseClass{

	private ProductDetailsPage pdp;
	
	@Test(priority = 1)
	public void initialize() {
		pdp = new ProductDetailsPage(util);
	}
	
	@Test(priority = 2)
	public void testRedirectionToProductDetails() {
		pdp.gotoProductDetails();
	}
	
	@Test(priority = 3)
	public void testToggleButtonsAreWorking() {
		pdp.verifyToggleBtns();
	}
	
	@Test(priority = 4)
	public void testErrorTagIsVisibleWhenImageSyncingIsDisabled() {
		pdp.disableProductImages();
//		pdp.updateImage();
		CreateShopifyProduct.skuOfProudct = "The Micro Juliet";
		pdp.verifySyncing(CreateShopifyProduct.skuOfProudct, "img");
		pdp.verifyErrorTagIsVisible();
		pdp.verifyErrForInfoSync("img");
	}
	
	@Test(priority = 5)
	public void testErrorTagIsVisibleWhenProductInfoSyncIsDisabled() {
		util.goToSection("settings");
		pdp.disableProductInformation();
		pdp.verifySyncing(CreateShopifyProduct.skuOfProudct, "info");
		pdp.verifyErrorTagIsVisible();
		pdp.verifyErrForInfoSync("info");
	}
	
}
