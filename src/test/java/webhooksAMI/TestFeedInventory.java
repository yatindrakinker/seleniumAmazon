package webhooksAMI;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ami.pageobjects.CreateShopifyProduct;
import com.ami.resources.BaseClass;

import apiWebhook.SellerCentralInventoryAPITest;
import apiWebhook.WebhookAPIHit;

public class TestFeedInventory extends BaseClass {
	private static final String WEBHOOKS = "webhooks";
	private String url = "";
	
	
	@DataProvider
	public Object[][] getNewProductData() throws IOException {
		List<HashMap<String, String>> data = util.getJsonDataToHashmap("NewSimpleProduct");
		return new Object[][] { { data.get(0) } };
	}

	WebHookPage web;
	WebhookAPIHit apiObj;
	CreateShopifyProduct create;
	SellerCentralInventoryAPITest sellerAPI;
	LocationWebhookTest locTest;
	private String responseFromSellerCentral;
	
	public void createNewProduct(String prodNameKey , String prodIDKey, String sku) {

		create.createNewShopifyProduct();
		util.updateWebHooksProperty(WEBHOOKS, prodNameKey, CreateShopifyProduct.nameOfProduct);
		util.updateWebHooksProperty(WEBHOOKS, prodIDKey, CreateShopifyProduct.containerIDOfProduct);
		util.hold(30);
		util.goToSection("linking");
		web.linkNewlyCreatedProduct("webhooks", sku, prodNameKey);
		web.inprogressTagIsActive(util.getProperty("webhooks", sku));
		
		if(util.getConfigProperty("store").equals("live")) {
			url  = util.getConfigProperty("storeUrl") + "/products";
		}else if(util.getConfigProperty("store").equals("staging")) {
			url  = util.getConfigProperty("stroreUrlStaging") + "products/";
		}
		
		util.getWindoHandleByUrl(url);
	}
	
	private void gotoProduct(String prodId) {
//		goto shopify
		
		if(util.getConfigProperty("store").equals("live")) {
			url  = util.getConfigProperty("storeUrl") + "products/";
		}else if(util.getConfigProperty("store").equals("staging")) {
			url  = util.getConfigProperty("stroreUrlStaging") + "products/";
		}
		
		System.out.println(url);
		util.getWindoHandleByUrl(url);
		util.hold(5);
		System.out.println(url + prodId);
		util.openUrl(url + prodId);

	}

	@Test(priority = 1)
	public void initializeInstances() {
		web = new WebHookPage(util);
		apiObj = new WebhookAPIHit(util);
		create = new CreateShopifyProduct(util);
		sellerAPI = new SellerCentralInventoryAPITest(util);
		locTest = new LocationWebhookTest();
		
//		create shopify product
		for(int i = 1; i <= 7; i++) {
			create.createNewShopifyProduct();
			System.out.println("product name "+ i + CreateShopifyProduct.nameOfProduct);
			System.out.println("productId "+ i + CreateShopifyProduct.containerIDOfProduct);
			util.updateWebHooksProperty(WEBHOOKS, "product"+i+"Name", CreateShopifyProduct.nameOfProduct);
			util.updateWebHooksProperty(WEBHOOKS, "productId"+i+"", CreateShopifyProduct.containerIDOfProduct);
			util.hold(30);
		}
		
		
		util.openSectionsInNewTab("settings");
		util.openSectionsInNewTab("listings");
		util.openSectionsInNewTab("linking");

	}
	
	@Test(priority = 2)
	public void testSetFixedInventory() {
//		go to settings and enable fixed inventory settings.
		web.enableInventorySettings();
		web.enableAllWareHouses();
		web.enableFixedInventory();
		
//		go to Shopify
//		gotoProduct(util.getProperty("webhooks", "productId1"));
		
//		link shopify product
		util.goToSection("linking");
		web.linkNewlyCreatedProduct("webhooks", "sku1", "product1Name");
		
//		goto listing and wait for inprogress tag is active.
		web.inprogressTagIsActive(util.getProperty("webhooks", "sku1"));

	}
	
	@Test(priority = 3)
	public void testSetReservedInventory() {
//		go to settings and enable reserved inventory settings.
		web.enableInventorySettings();
		web.enableAllWareHouses();
		web.enableReservedInventory();
		
//		go to Shopify
//		gotoProduct(util.getProperty("webhooks", "productId2"));
		
//		update inventory
//		create.updateShopifyInventory();
		
//		link shopify product
		util.goToSection("linking");
		web.linkNewlyCreatedProduct("webhooks", "sku2", "product2Name");
		
		
//		goto listing and wait for inprogress tag is active.
		web.inprogressTagIsActive(util.getProperty("webhooks", "sku2"));

		}
	
	@Test(priority = 4)
	public void testSetContinueSellingInventoryOnBothAppAndShopify() {
//		go to settings and enable reserved inventory settings.
		web.enableInventorySettings();
		web.enableAllWareHouses();
		web.enableCustomInventory();
		
//		go to Shopify
//		open product
		gotoProduct(util.getProperty("webhooks", "productId3"));
		
//		enable continue selling on shopify
		create.enableContinueSelling();
		
//		link shopify product
		util.goToSection("linking");
		web.linkNewlyCreatedProduct("webhooks", "sku3", "product3Name");
		
//		goto listing and wait for inprogress tag is active.
		web.inprogressTagIsActive(util.getProperty("webhooks", "sku3"));

		}
	
	@Test(priority = 5)
	public void testSetContinueSellingInventoryOnShopify() {
//		go to settings and disable reserved inventory settings.
		web.enableInventorySettings();
		web.enableAllWareHouses();
		web.disableMaximumInventorySetting();
		
//		go to Shopify
//		open product
		gotoProduct(util.getProperty("webhooks", "productId4"));
		
//		enable continue selling on shopify
		create.enableContinueSelling();
		
//		link shopify product
		util.goToSection("linking");
		web.linkNewlyCreatedProduct("webhooks", "sku4", "product4Name");
		
//		goto listing and wait for inprogress tag is active.
		web.inprogressTagIsActive(util.getProperty("webhooks", "sku4"));


		}
	
	@Test(priority = 6)
	public void testSetContinueSellingInventoryOnApp() {
//		go to settings and disable reserved inventory settings.
		web.enableInventorySettings();
		web.enableAllWareHouses();
		web.disableMaximumInventorySetting();
		
//		go to Shopify
		
//		open product
		gotoProduct(util.getProperty("webhooks", "productId5"));
		
//		disable continue selling on shopify
		create.disableContinueSelling();
		
//		link shopify product
		util.goToSection("linking");
		web.linkNewlyCreatedProduct("webhooks", "sku5", "product5Name");
		
//		goto listing and wait for inprogress tag is active.
		web.inprogressTagIsActive(util.getProperty("webhooks", "sku5"));


		}
	
	@Test(priority = 7)
	public void testNoSettingsAreApplied() {
//		go to settings and disable reserved inventory settings.
		web.disableInventorySettings();
		web.enableInventorySettings();
		web.disableMaximumInventorySetting();
		
		
//		go to Shopify
		
//		open product
		gotoProduct(util.getProperty("webhooks", "productId7"));
		
//		disable continue selling on shopify
		create.disableContinueSelling();
		
//		link shopify product
		util.goToSection("linking");
		web.linkNewlyCreatedProduct("webhooks", "sku7", "product7Name");
		
//		goto listing 
//		wait until in progress tag is inactive
		web.checkInProgressTagIsInActive(util.getProperty("webhooks", "sku7"));
		

		}
	
	@Test(priority = 7)
	public void testSetContinueSellingInventoryIsDisabledOnAppAndShopify() {
//		go to settings and disable reserved inventory settings.
		web.enableInventorySettings();
		web.enableAllWareHouses();
		web.enableCustomInventory();
		
//		go to Shopify
		
//		open product
		gotoProduct(util.getProperty("webhooks", "productId6"));
		
//		disable continue selling on shopify
		create.disableContinueSelling();
		
//		link shopify product
		util.goToSection("linking");
		web.linkNewlyCreatedProduct("webhooks", "sku6", "product6Name");
		
//		goto listing 
//		wait until in progress tag is inactive
		web.checkInProgressTagIsInActive(util.getProperty("webhooks", "sku6"));
		
//		get inventory from the marketplace using api
		responseFromSellerCentral = sellerAPI.hitSellerCentralInventoryAPI(util.getProperty("webhooks", "sku6"));
		String marketPlaceInventory = sellerAPI.returnInventory(responseFromSellerCentral);
		String expectedInventory = "100";

		System.out.println("Shopify inventory = " + expectedInventory);
		System.out.println("amazon inventory = " + marketPlaceInventory);
		Assert.assertEquals(marketPlaceInventory, expectedInventory, "Inventory is not updated on marketplace.");

		}
	
	
	
	@Test(priority = 8)
	public void testFixedInventory() {
//		wait until in progress tag is inactive
		web.checkInProgressTagIsInActive(util.getProperty("webhooks", "sku1"));
		
//		get inventory from the marketplace using api
		responseFromSellerCentral = sellerAPI.hitSellerCentralInventoryAPI(util.getProperty("webhooks", "sku1"));
		String marketPlaceInventory = sellerAPI.returnInventory(responseFromSellerCentral);
		String expectedInventory = WebHookPage.FIXEDINVENTORY;
		System.out.println("FIXED INVENTORY ON APP = " + expectedInventory);
		System.out.println("AMAZON INVENTORY = " + marketPlaceInventory);
		Assert.assertEquals(marketPlaceInventory, expectedInventory, "Inventory is not updated on marketplace.");
	}
	
	@Test(priority = 9)
	public void testReservedInventory() {
		int expectedInventory;
		
//		wait until in progress tag is inactive
		web.checkInProgressTagIsInActive(util.getProperty("webhooks", "sku2"));
		
//		get inventory from the marketplace using api
		responseFromSellerCentral = sellerAPI.hitSellerCentralInventoryAPI(util.getProperty("webhooks", "sku2"));
		String marketPlaceInventory = sellerAPI.returnInventory(responseFromSellerCentral);
		
		if(Integer.parseInt(WebHookPage.RESERVEDINVENTORY) >= Integer.parseInt("100")) {
			expectedInventory = 0;
		}else {
			expectedInventory = Integer.parseInt("100")
					- Integer.parseInt(WebHookPage.RESERVEDINVENTORY);
		}
		
		System.out.println("INVENTORY ON APP = " + expectedInventory);
		System.out.println("AMAZON INVENTORY = " + marketPlaceInventory);

		Assert.assertEquals(marketPlaceInventory, String.valueOf(expectedInventory),
				"Inventory is not updated on marketplace.");
	}
	
	@Test(priority = 10)
	public void testContinueSellingForShopifyAndApp() {
		
//		wait until in progress tag is inactive
		web.checkInProgressTagIsInActive(util.getProperty("webhooks", "sku3"));
		
//		get inventory from the marketplace using api
		responseFromSellerCentral = sellerAPI.hitSellerCentralInventoryAPI(util.getProperty("webhooks", "sku3"));
		String marketPlaceInventory = sellerAPI.returnInventory(responseFromSellerCentral);
		String expectedInventory = WebHookPage.MAXINVENTORY;

		System.out.println("INVENTORY ON APP = " + expectedInventory);
		System.out.println("AMAZON INVENTORY = " + marketPlaceInventory);
		Assert.assertEquals(marketPlaceInventory, expectedInventory, "Inventory is not updated on marketplace.");
	}
	
	@Test(priority = 11)
	public void testContinueSellingForShopify() {

//		wait until in progress tag is inactive
		web.checkInProgressTagIsInActive(util.getProperty("webhooks", "sku4"));
		
//		get inventory from the marketplace using api
		responseFromSellerCentral = sellerAPI.hitSellerCentralInventoryAPI(util.getProperty("webhooks", "sku4"));
		String marketPlaceInventory = sellerAPI.returnInventory(responseFromSellerCentral);
		String expectedInventory = "100";
		System.out.println("Shopify inventory = " + expectedInventory);
		System.out.println("amazon inventory = " + marketPlaceInventory);
		Assert.assertEquals(marketPlaceInventory, expectedInventory, "Inventory is not updated on marketplace.");
	}
	
	@Test(priority = 12)
	public void testContinueSellingForApp() {

//		wait until in progress tag is inactive
		web.checkInProgressTagIsInActive(util.getProperty("webhooks", "sku5"));
		
//		get inventory from the marketplace using api
		responseFromSellerCentral = sellerAPI.hitSellerCentralInventoryAPI(util.getProperty("webhooks", "sku5"));
		String marketPlaceInventory = sellerAPI.returnInventory(responseFromSellerCentral);
		String expectedInventory = "100";
		System.out.println("Shopify inventory = " + expectedInventory);
		System.out.println("amazon inventory = " + marketPlaceInventory);
		Assert.assertEquals(marketPlaceInventory, expectedInventory, "Inventory is not updated on marketplace.");
	}
	
	@Test(priority = 13)
	public void testWhenNoSettingsAreApplied() {
//		wait until in progress tag is inactive
		web.checkInProgressTagIsInActive(util.getProperty("webhooks", "sku7"));
		
//		get inventory from the marketplace using api
		responseFromSellerCentral = sellerAPI.hitSellerCentralInventoryAPI(util.getProperty("webhooks", "sku7"));
		String marketPlaceInventory = sellerAPI.returnInventory(responseFromSellerCentral);
		String expectedInventory = "100";
		System.out.println("INVENTORY ON APP = " + expectedInventory);
		System.out.println("AMAZON INVENTORY = " + marketPlaceInventory);
		Assert.assertEquals(marketPlaceInventory, expectedInventory, "Inventory is not updated on marketplace.");
	}
}
