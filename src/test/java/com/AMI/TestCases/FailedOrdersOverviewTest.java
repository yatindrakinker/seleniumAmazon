package com.AMI.TestCases;

import org.testng.annotations.Test;

import com.ami.pageobjects.FailedOrdersOverViewPage;
import com.ami.pageobjects.OnboardingPage;
import com.ami.pageobjects.reusableclasses.ReusableMethods;
import com.ami.resources.BaseClass;

import api.API;

public class FailedOrdersOverviewTest extends BaseClass{
	FailedOrdersOverViewPage ovr ;
	API api;
	OnboardingPage op;
	ReusableMethods reuse;
	private String sku = "Test16170";
	
	@Test(priority = 1)
	public void initialize() {
		ovr = new FailedOrdersOverViewPage(util);
		reuse = new ReusableMethods(util);
		op = new OnboardingPage(util);
		api = new API(util);
	}
	
	@Test(priority = 2, description = "Verify that at failed order section Failed Orders option are shown with its count")
	public void TC_AM_OV_343() {
		util.goToMultiAccountOverView();
		ovr.failedOrderSectionIsAvaialbleOnOverview();
		
	}
	
	@Test(priority = 3, description = "Verify that when hover at the count of failed order then hand cursor is shown")
	public void TC_AM_OV_344() {
		ovr.cursorPropertyChanges();
		
	}
	
	@Test(priority = 4, description = "Verify that in Failed Order section Manage Failed Order link is shown")
	public void TC_AM_OV_346_347() {
		ovr.inFailedOrderSectionOnOverviewManageFailedOrdersLinkIsAvailable();
		
	}
	
	@Test(priority = 5, description = "Verify that when clicking at the count of failed orders then it is redirect to Failed Order section and showing the same order count of failed orders")
	public void TC_AM_OV_345() {
		ovr.validateFailedOrderCounts();
		
	}
	
	@Test(priority = 6, description = "Verify that at the grid search Order ID bar is shown")
	public void TC_AM_OV_351() {
		ovr.searchInputFieldIsWorking();
		
	}
	
	
	@Test(priority = 7, description = "Verify that at the grid search Order ID bar is shown")
	public void TC_AM_OV_350() {
		ovr.failedOrdersAreVisible();
		
	}
	
	@Test(priority = 8, description = "Verify that when clcik at Archived Order button then it is redirected to Archived Order section")
	public void TC_AM_OV_353_To_363() {
		ovr.achievedOrderBtnIsWorking();
		ovr.searchInputFieldIsWorking();
	}
	
	@Test(priority = 9, description = "Verify that when clcik at Archived Order button then it is redirected to Archived Order section")
	public void TC_AM_OV_364_To_370() {
		ovr.viewBtnIsWorkingOnArchievedOrder();
	}
	
	@Test(priority = 10, description = "Verify that at bottom three links are shown Amazon By CedCommerce, Get Support and Book A Call are shown")
	public void TC_AM_OV_371_374() {
		op.getSupportLinkFooterIsFunctioning();
		op.dataPrivacyLinkFooterIsFunctioning();
		op.bookCallLinkFooterIsFunctioning();
		op.cedLinkFooterIsFunctioning();
		op.openTermsConditionCheckBoxDoc();
	}
	
	@Test(priority = 11, description = "Verify that when click at Import Order and Order ID is not entered")
	public void TC_AM_OV_375_381() {
		ovr.importOrdersBtnIsAvailable();
		ovr.verifyWhenOrderIdIsNotGiven();
	}
	
	@Test(priority = 12, description = "Verify that when click at Import Order and given the invalid Order ID ")
	public void TC_AM_OV_372_389() {
		ovr.verifyImportOrderModalIsWorking();
	}
	
	@Test(priority = 13, description = "Verify that when clcik at Create button then modal is shown")
	public void TC_AM_OV_393_397() {
		ovr.verifyCreateBtnIsWorking();
		ovr.verifyViewOnAmazonLinkIsWorking();
	}
	
	@Test(priority = 14, description = "")
	public void TC_AM_397_398() {
		ovr.createOrder(sku);
		api.createOrders(sku, "1");
		ovr.createFailedOrder(API.amazonOrderId);
	}
	
	@Test(priority = 15, description = "Verify that after clciking at proceed button then it is redirect to Archived Order section")
	public void TC_AM_400_404() {
		api.createOrders(sku, "1");
		ovr.verifyOrdersCanBeArchieved(API.amazonOrderId);
		ovr.selectActionIsDisplayed(API.amazonOrderId);
	}

}
