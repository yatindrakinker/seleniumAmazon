package com.AMI.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ami.pageobjects.CreateShopifyProduct;
import com.ami.pageobjects.InventorySettingsPage;
import com.ami.resources.BaseClass;

import apiWebhook.SellerCentralInventoryAPITest;
import webhooksAMI.WebHookPage;

public class TestInventorySettings extends BaseClass {
	protected static final String WEBHOOKS = "webhooks";
	protected static final String SKUSFILE = "sku";

	private InventorySettingsPage isp;
	private CreateShopifyProduct csp;
	private SellerCentralInventoryAPITest api;
	private String responseFromSellerCentral;
	private WebHookPage web;

	@Test(priority = 1)
	public void initialize() {
		isp = new InventorySettingsPage(util);
		csp = new CreateShopifyProduct(util);
		api = new SellerCentralInventoryAPITest(util);
		web = new WebHookPage(util);
	}

	@Test(priority = 2)
	public void testRedirectionToProductDetails() {
		isp.gotoInventorySettings();
	}

	@Test(priority = 3)
	public void testWhenInventorySettingsIsDisabled() {
		isp.disableInventorySettings();
	}

	@Test(priority = 4)
	public void testWhenInventorySettingsIsEnabled() {
		isp.enableInventorySettings();
	}

	@Test(priority = 5)
	public void testCustomInventoryInputIsWorking() {
		isp.customInventoryInputIsWorking();
	}

	@Test(priority = 6)
	public void testDeactivateProductOnAmazonToggleBtnIsWorking() {
		isp.deactivateProductOnAmazonToggleBtnIsWorking();
	}

	@Test(priority = 7)
	public void testOnChangeSettings() {
		isp.onChangeSettings();
	}

	@Test(priority = 8)
	public void testProductIsDeletedItBecomesOutOfStockOnMarketPlace() {
		util.switchToIFrame();
		isp.enableInventorySettings();
		isp.enableDeleteOutOfStockToggleBtn();
		util.goToNewProduct();
//		create shopify product
		for (int i = 1; i <= 5; i++) {
			csp.createNewShopifyProduct();
			System.out.println("product name " + i + CreateShopifyProduct.nameOfProduct);
			System.out.println("productId " + i + CreateShopifyProduct.containerIDOfProduct);
			util.updateWebHooksProperty(SKUSFILE, "product" + i + "Name", CreateShopifyProduct.nameOfProduct);
			util.updateWebHooksProperty(SKUSFILE, "productId" + i + "", CreateShopifyProduct.containerIDOfProduct);
			util.hold(30);
		}
		
//		link product
		for(int i = 1; i <= 5; i++) {
			isp.linkProduct(SKUSFILE, "sku"+i, "product"+i+"Name");
		}
		
//		checking in progress tag is active
		for(int i = 1; i <= 5; i++) {
			web.inprogressTagIsActive(util.getProperty(SKUSFILE, "sku"+i));
		}
		
//		wait until in progress tag is inactive
		
		web.checkInProgressTagIsInActive(util.getProperty(SKUSFILE, "sku5"));
		
//		change settings
		util.goToSection("settings");
		isp.enableInventorySettings();
		isp.enableDeleteOutOfStockToggleBtn();
//		delete shopify product
		isp.deleteShopifyProduct(util.getProperty(SKUSFILE, "productId1"));
		util.hold(30);
//		initiate sync call
		isp.startSyncProduct();
		isp.waitUntilSyncIsComplete();
		responseFromSellerCentral = api.hitSellerCentralInventoryAPI(util.getProperty(SKUSFILE, "sku1"));
		String marketPlaceInventory = api.returnInventory(responseFromSellerCentral);
		Assert.assertEquals(marketPlaceInventory, "0");
	}
	
	@Test(priority = 9)
	public void testProductIsDeletedStatusOfLinkedProductIsUpdatedAsStatusOnMarketPlace() {
		isp.verifyProductStatusIsSameAsMarketPlace("sku", "sku1");
		
	}
	
	@Test(priority = 10)
	public void testFeedIsGenerated() {
		isp.openAdminPanelFeeds();
		isp.openJsonInventory("0");
		
	}
	
//	@Test(priority = 11)
//	public void testProductIsDeletedWhenDeactivateProductOnAmazonIsDisabled() {
////		change settings
//		util.goToSection("settings");
//		isp.enableInventorySettings();
//		isp.disableDeleteOutOfStockToggleBtn();
////		delete shopify product
//		isp.deleteShopifyProduct(util.getProperty(SKUSFILE, "productId2"));
//		util.hold(30);
////		initiate sync call
//		isp.startSyncProduct();
//		isp.waitUntilSyncIsComplete();	
//		responseFromSellerCentral = api.hitSellerCentralInventoryAPI(util.getProperty(SKUSFILE, "sku2"));
//		String marketPlaceInventory = api.returnInventory(responseFromSellerCentral);
//		Assert.assertEquals(marketPlaceInventory, "100");
//	}
//	
//	@Test(priority = 12)
//	public void testProductIsDeletedStatusOfLinkedProductIsUpdatedAsStatusOnMarketPlace2() {
//		isp.verifyProductStatusIsSameAsMarketPlace("sku", "sku2");
//		
//	}
//	
//	@Test(priority = 13)
//	public void testFeedIsGenerated2() {
//		isp.goToAdminPanel();
//		util.refreshPage();
//		isp.openJsonInventory("100");
//		
//	}
//	
//	@Test(priority = 14)
//	public void testWhenProductIsDraft() {
//		util.goToSection("settings");
//		isp.enableInventorySettings();
//		isp.disableDeleteOutOfStockToggleBtn();
//		
////		draft shopify product
//		isp.draftShopifyProduct(util.getProperty(SKUSFILE, "productId3"));
//		util.hold(30);
////		initiate sync call
//		isp.startSyncProduct();
//		isp.waitUntilSyncIsComplete();	
//		responseFromSellerCentral = api.hitSellerCentralInventoryAPI(util.getProperty(SKUSFILE, "sku3"));
//		String marketPlaceInventory = api.returnInventory(responseFromSellerCentral);
//		Assert.assertEquals(marketPlaceInventory, "100");
//		
//	}
//	
//	@Test(priority = 15)
//	public void testInventoryWhenProductIsDraftOnShopify() {
//		isp.verifyProductStatusIsSameAsMarketPlace("sku", "sku3");
//		
//	}
//	
//	@Test(priority = 16)
//	public void testFeedWhenProductIsDraftOnShopify() {
//		isp.goToAdminPanel();
//		util.refreshPage();
//		isp.openJsonInventory("100");
//		
//	}
//	
//	@Test(priority = 17)
//	public void testWhenProductIsDraftAndSettingIsEnabled() {
//		util.goToSection("settings");
//		isp.enableInventorySettings();
//		isp.enableDeleteOutOfStockToggleBtn();
//		
////		draft shopify product
//		isp.draftShopifyProduct(util.getProperty(SKUSFILE, "productId4"));
//		util.hold(30);
////		initiate sync call
//		isp.startSyncProduct();
//		isp.waitUntilSyncIsComplete();	
//		responseFromSellerCentral = api.hitSellerCentralInventoryAPI(util.getProperty(SKUSFILE, "sku4"));
//		String marketPlaceInventory = api.returnInventory(responseFromSellerCentral);
//		Assert.assertEquals(marketPlaceInventory, "0");
//	}
//	
//	@Test(priority = 18)
//	public void testInventoryWhenProductIsDraftOnShopifyAndSettingIsEnabled() {
//		isp.verifyProductStatusIsSameAsMarketPlace("sku", "sku4");
//		
//	}
//	
//	@Test(priority = 19)
//	public void testFeedWhenProductIsDraftOnShopifyAndSettingIsEnabled() {
//		isp.goToAdminPanel();
//		util.refreshPage();
//		isp.openJsonInventory("0");
//		
//	}
//	
//	@Test(priority = 20)
//	public void testInventoryWhenSalesChannelIsInactiveAndSettingIsEnabled() {
//		util.goToSection("settings");
//		isp.enableInventorySettings();
//		isp.enableDeleteOutOfStockToggleBtn();
//		
////		inactive sales channel shopify product
//		isp.makeSalesChannelInactive(util.getProperty(SKUSFILE, "productId5"));
//		util.hold(30);
////		initiate sync call
//		isp.startSyncProduct();
//		isp.waitUntilSyncIsComplete();	
//		responseFromSellerCentral = api.hitSellerCentralInventoryAPI(util.getProperty(SKUSFILE, "sku5"));
//		String marketPlaceInventory = api.returnInventory(responseFromSellerCentral);
//		Assert.assertEquals(marketPlaceInventory, "0");
//	}
//	
//	@Test(priority = 21)
//	public void testWhenSalesChannelIsInactiveAndSettingIsEnabled() {
//		isp.verifyProductStatusIsSameAsMarketPlace("sku", "sku5");
//		
//	}
//	
//	@Test(priority = 22)
//	public void testFeedWhenSalesChannelIsInactiveAndSettingIsEnabled() {
//		isp.goToAdminPanel();
//		util.refreshPage();
//		isp.openJsonInventory("0");
//		
//	}

//	@Test(priority = 8)
//	public void testProductIsDeletedItBecomesOutOfStockOnMarketPlace() {
//		util.switchToIFrame();
//		isp.enableInventorySettings();
//		isp.enableDeleteOutOfStockToggleBtn();
//		util.goToNewProduct();
//		csp.createNewShopifyProduct();
//		isp.linkProduct("sku", "sku1", CreateShopifyProduct.nameOfProduct);
//		String sku = util.getProperty("sku", "sku1");
//		isp.checkInProgressTagIsInActive(sku);
//		isp.deleteShopifyProduct();
//		util.hold(30);
//		util.hitNewProductUrl();
//		isp.startSyncProduct();
//		isp.waitUntilSyncIsComplete();	
//		responseFromSellerCentral = api.hitSellerCentralInventoryAPI(sku);
//		String marketPlaceInventory = api.returnInventory(responseFromSellerCentral);
//		Assert.assertEquals(marketPlaceInventory, "0");
//	}
//	
//	@Test(priority = 9)
//	public void testProductIsDeletedStatusOfLinkedProductIsUpdatedAsStatusOnMarketPlace() {
//		isp.verifyProductStatusIsSameAsMarketPlace("sku", "sku1");
//		
//	}
//	
//	@Test(priority = 10)
//	public void testFeedIsGenerated() {
//		isp.openAdminPanelFeeds();
//		isp.openJsonInventory("0");
//		
//	}
//	
//	@Test(priority = 11)
//	public void testProductIsDeletedWhenDeactivateProductOnAmazonIsDisabled() {
//		util.goToNewProduct();
//		csp.createNewShopifyProduct();
//		util.goToSection("settings");
//		isp.enableInventorySettings();
//		isp.disableDeleteOutOfStockToggleBtn();
//		isp.linkProduct("sku", "sku2", CreateShopifyProduct.nameOfProduct);
//		String sku = util.getProperty("sku", "sku2");
//		isp.checkInProgressTagIsInActive(sku);
//		isp.deleteShopifyProduct();
//		util.hold(30);
//		util.hitNewProductUrl();
//		isp.startSyncProduct();
//		isp.waitUntilSyncIsComplete();	
//		responseFromSellerCentral = api.hitSellerCentralInventoryAPI(sku);
//		String marketPlaceInventory = api.returnInventory(responseFromSellerCentral);
//		Assert.assertEquals(marketPlaceInventory, "100");
//	}
//	
//	@Test(priority = 12)
//	public void testProductIsDeletedStatusOfLinkedProductIsUpdatedAsStatusOnMarketPlace2() {
//		isp.verifyProductStatusIsSameAsMarketPlace("sku", "sku2");
//		
//	}
//	
//	@Test(priority = 13)
//	public void testFeedIsGenerated2() {
//		isp.goToAdminPanel();
//		util.refreshPage();
//		isp.openJsonInventory("100");
//		
//	}
//	
//	@Test(priority = 14)
//	public void testWhenProductIsDraft() {
//		util.goToNewProduct();
//		csp.createNewShopifyProduct();
//		util.goToSection("settings");
//		isp.enableInventorySettings();
//		isp.disableDeleteOutOfStockToggleBtn();
//		isp.linkProduct("sku", "sku3", CreateShopifyProduct.nameOfProduct);
//		String sku = util.getProperty("sku", "sku3");
//		isp.checkInProgressTagIsInActive(sku);
//		isp.draftShopifyProduct();
//		util.hold(30);
//		util.hitNewProductUrl();
//		isp.startSyncProduct();
//		isp.waitUntilSyncIsComplete();	
//		responseFromSellerCentral = api.hitSellerCentralInventoryAPI(sku);
//		String marketPlaceInventory = api.returnInventory(responseFromSellerCentral);
//		Assert.assertEquals(marketPlaceInventory, "100");
//	}
//	
//	@Test(priority = 15)
//	public void testInventoryWhenProductIsDraftOnShopify() {
//		isp.verifyProductStatusIsSameAsMarketPlace("sku", "sku3");
//		
//	}
//	
//	@Test(priority = 16)
//	public void testFeedWhenProductIsDraftOnShopify() {
//		isp.goToAdminPanel();
//		util.refreshPage();
//		isp.openJsonInventory("100");
//		
//	}
//	
//	@Test(priority = 17)
//	public void testWhenProductIsDraftAndSettingIsEnabled() {
//		util.goToNewProduct();
//		csp.createNewShopifyProduct();
//		util.goToSection("settings");
//		isp.enableInventorySettings();
//		isp.enableDeleteOutOfStockToggleBtn();
//		isp.linkProduct("sku", "sku3", CreateShopifyProduct.nameOfProduct);
//		String sku = util.getProperty("sku", "sku4");
//		isp.checkInProgressTagIsInActive(sku);
//		isp.draftShopifyProduct();
//		util.hold(30);
//		util.hitNewProductUrl();
//		isp.startSyncProduct();
//		isp.waitUntilSyncIsComplete();	
//		responseFromSellerCentral = api.hitSellerCentralInventoryAPI(sku);
//		String marketPlaceInventory = api.returnInventory(responseFromSellerCentral);
//		Assert.assertEquals(marketPlaceInventory, "0");
//	}
//	
//	@Test(priority = 18)
//	public void testInventoryWhenProductIsDraftOnShopifyAndSettingIsEnabled() {
//		isp.verifyProductStatusIsSameAsMarketPlace("sku", "sku4");
//		
//	}
//	
//	@Test(priority = 19)
//	public void testFeedWhenProductIsDraftOnShopifyAndSettingIsEnabled() {
//		isp.goToAdminPanel();
//		util.refreshPage();
//		isp.openJsonInventory("0");
//		
//	}
//	
//	@Test(priority = 20)
//	public void testInventoryWhenSalesChannelIsInactiveAndSettingIsEnabled() {
//		util.goToNewProduct();
//		csp.createNewShopifyProduct();
//		util.goToSection("settings");
//		isp.enableInventorySettings();
//		isp.enableDeleteOutOfStockToggleBtn();
//		isp.linkProduct("sku", "sku5", CreateShopifyProduct.nameOfProduct);
//		String sku = util.getProperty("sku", "sku5");
//		isp.checkInProgressTagIsInActive(sku);
//		util.getWindoHandleByUrl(CreateShopifyProduct.containerIDOfProduct);
//		csp.makeSalesChannelInactive();
//		util.hold(30);
//		isp.startSyncProduct();
//		isp.waitUntilSyncIsComplete();	
//		responseFromSellerCentral = api.hitSellerCentralInventoryAPI(sku);
//		String marketPlaceInventory = api.returnInventory(responseFromSellerCentral);
//		Assert.assertEquals(marketPlaceInventory, "0");
//	}
//	
//	@Test(priority = 21)
//	public void testWhenSalesChannelIsInactiveAndSettingIsEnabled() {
//		isp.verifyProductStatusIsSameAsMarketPlace("sku", "sku5");
//		
//	}
//	
//	@Test(priority = 22)
//	public void testFeedWhenSalesChannelIsInactiveAndSettingIsEnabled() {
//		isp.goToAdminPanel();
//		util.refreshPage();
//		isp.openJsonInventory("0");
//		
//	}
}
