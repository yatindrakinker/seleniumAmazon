package webhooksAMI;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ami.pageobjects.CreateShopifyProduct;
import com.ami.resources.BaseClass;

import apiWebhook.SellerCentralInventoryAPITest;
import apiWebhook.WebhookAPIHit;

public class FeedInventoryVariantTest extends BaseClass {
	private static final String WEBHOOKS = "webhooksvariant";
	private String url = "";
	private String responseFromSellerCentral;

	WebHookPage web;
	WebhookAPIHit apiObj;
	CreateShopifyProduct create;
	SellerCentralInventoryAPITest sellerAPI;
	LocationWebhookTest locTest;

	private void gotoProduct(String prodId) {
//		goto shopify

		if (util.getConfigProperty("store").equals("live")) {
			url = util.getConfigProperty("storeUrl") + "products/";
		} else if (util.getConfigProperty("store").equals("staging")) {
			url = util.getConfigProperty("stroreUrlStaging") + "products/";
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
		for (int i = 1; i <= 6; i++) {
//			create.createNewVariantShopifyProduct();
			create.createNewShopifyProduct();
			create.enableContinueSelling();
			create.makeSimpleProductToVariants();
			System.out.println("product name " + i + CreateShopifyProduct.nameOfProduct);
			System.out.println("productId " + i + CreateShopifyProduct.containerIDOfProduct);
			util.updateWebHooksProperty(WEBHOOKS, "product" + i + "Name", CreateShopifyProduct.nameOfProduct);
			util.updateWebHooksProperty(WEBHOOKS, "productId" + i + "", CreateShopifyProduct.containerIDOfProduct);
			util.hold(30);
		}

//	open all required sections	
		util.openSectionsInNewTab("settings");
		util.openSectionsInNewTab("listings");
		util.openSectionsInNewTab("linking");
	}

	@Test(priority = 2)
	public void testSetFixedInventory() {
//		enable fixed-inventory settings.
		web.enableInventorySettings();
		web.enableAllWareHouses();
		web.enableFixedInventory();
//		go to shopify and update inventory
		gotoProduct(util.getProperty(WEBHOOKS, "productId1"));
		create.updateVariantInventory("150");
//		link shopify product
		util.goToSection("linking");
		web.linkNewlyCreatedProduct(WEBHOOKS, "sku1", "product1Name");
//		verify inprogress tag is active
		web.inprogressTagIsActive(util.getProperty(WEBHOOKS, "sku1"));
	}

	@Test(priority = 3)
	public void testSetReserveInventory() {
//		enable reserved-inventory settings.
		web.enableInventorySettings();
		web.enableAllWareHouses();
		web.enableReservedInventory();
//		go to shopify and update inventory
		gotoProduct(util.getProperty(WEBHOOKS, "productId2"));
		create.updateVariantInventory("150");
//		link shopify product
		util.goToSection("linking");
		web.linkNewlyCreatedProduct(WEBHOOKS, "sku2", "product2Name");
//		verify inprogress tag is active
		web.inprogressTagIsActive(util.getProperty(WEBHOOKS, "sku2"));
	}

	@Test(priority = 4)
	public void testSetContinueSellingInventoryOnBothAppAndShopify() {
//		go to settings and enable custom inventory settings.
		web.enableInventorySettings();
		web.enableAllWareHouses();
		web.enableCustomInventory();
//		go to shopify and update inventory
		gotoProduct(util.getProperty(WEBHOOKS, "productId3"));
//		enable continue selling on shopify
		create.updateVariantInventory("150");
//		create.enableContinueSellingForVariants();
//		link shopify product
		util.goToSection("linking");
		web.linkNewlyCreatedProduct(WEBHOOKS, "sku3", "product3Name");
//		verify inprogress tag is active
		web.inprogressTagIsActive(util.getProperty(WEBHOOKS, "sku3"));
	}

	@Test(priority = 5)
	public void testSetContinueSellingInventoryOnShopify() {
//		go to settings and enable custom inventory settings.
		web.enableInventorySettings();
		web.enableAllWareHouses();
		web.disableMaximumInventorySetting();
//		go to shopify and update inventory
		gotoProduct(util.getProperty(WEBHOOKS, "productId4"));
//		enable continue selling on shopify
//		create.enableContinueSellingForVariants();
		create.updateVariantInventory("150");
//		link shopify product
		util.goToSection("linking");
		web.linkNewlyCreatedProduct(WEBHOOKS, "sku4", "product4Name");
//		verify inprogress tag is active
		web.inprogressTagIsActive(util.getProperty(WEBHOOKS, "sku4"));
	}
	
	@Test(priority = 6)
	public void testNoSettingsAreApplied() {
//		enable fixed-inventory settings.
		web.disableInventorySettings();
		web.enableInventorySettings();
		web.disableMaximumInventorySetting();
//		go to shopify and update inventory
		gotoProduct(util.getProperty(WEBHOOKS, "productId6"));
		create.updateVariantInventory("150");
//		link shopify product
		util.goToSection("linking");
		web.linkNewlyCreatedProduct(WEBHOOKS, "sku6", "product6Name");
//		verify inprogress tag is active
		web.inprogressTagIsActive(util.getProperty(WEBHOOKS, "sku6"));
	}

	@Test(priority = 7)
	public void testContinueSellingInventoryOnApp() {
//		go to settings and enable custom inventory settings.
		web.enableInventorySettings();
		web.enableAllWareHouses();
		web.disableMaximumInventorySetting();
//		go to shopify and update inventory
		gotoProduct(util.getProperty(WEBHOOKS, "productId5"));
		create.updateVariantInventory("150");
//		link shopify product
		util.goToSection("linking");
		web.linkNewlyCreatedProduct(WEBHOOKS, "sku5", "product5Name");
//		verify inprogress tag is active
		web.inprogressTagIsActive(util.getProperty(WEBHOOKS, "sku5"));
//		goto listing and wait for inprogress tag is active.
		web.checkInProgressTagIsInActive(util.getProperty(WEBHOOKS, "sku5"));
//		get inventory from the marketplace using api
		responseFromSellerCentral = sellerAPI.hitSellerCentralInventoryAPI(util.getProperty(WEBHOOKS, "sku5"));
		String marketPlaceInventory = sellerAPI.returnInventory(responseFromSellerCentral);
		String expectedInventory = "150";
		System.out.println("Shopify inventory = " + expectedInventory);
		System.out.println("amazon inventory = " + marketPlaceInventory);
		Assert.assertEquals(marketPlaceInventory, expectedInventory, "Inventory is not updated on marketplace.");

	}

	@Test(priority = 8)
	public void testFixedInventoryIsUpdatedOnMarketPlace() {
//		wait until in progress tag is inactive
		web.checkInProgressTagIsInActive(util.getProperty(WEBHOOKS, "sku1"));

//		get inventory from the marketplace using api
		responseFromSellerCentral = sellerAPI.hitSellerCentralInventoryAPI(util.getProperty(WEBHOOKS, "sku1"));
		String marketPlaceInventory = sellerAPI.returnInventory(responseFromSellerCentral);
		String expectedInventory = WebHookPage.FIXEDINVENTORY;
		System.out.println("FIXED INVENTORY ON APP = " + expectedInventory);
		System.out.println("AMAZON INVENTORY = " + marketPlaceInventory);
		Assert.assertEquals(marketPlaceInventory, expectedInventory, "Inventory is not updated on marketplace.");

	}

	@Test(priority = 9)
	public void testReservedInventoryIsUpdatedOnMarketPlace() {
		int expectedInventory ;

//		wait until in progress tag is inactive
		web.checkInProgressTagIsInActive(util.getProperty(WEBHOOKS, "sku2"));

//		get inventory from the marketplace using api
		responseFromSellerCentral = sellerAPI.hitSellerCentralInventoryAPI(util.getProperty(WEBHOOKS, "sku2"));
		String marketPlaceInventory = sellerAPI.returnInventory(responseFromSellerCentral);
		
		if(Integer.parseInt(WebHookPage.RESERVEDINVENTORY) >= Integer.parseInt("150")) {
			expectedInventory = 0;
		}else {
				expectedInventory = Integer.parseInt("150")
					- Integer.parseInt(WebHookPage.RESERVEDINVENTORY);
		}
		
		
		System.out.println("INVENTORY ON APP = " + expectedInventory);
		System.out.println("AMAZON INVENTORY = " + marketPlaceInventory);

		Assert.assertEquals(marketPlaceInventory, String.valueOf(expectedInventory),
				"Inventory is not updated on marketplace.");
	}

	@Test(priority = 10)
	public void testContinueSellingForShopifyAndAppInventoryIsUpdatedOnMarketPlace() {

//		wait until in progress tag is inactive
		web.checkInProgressTagIsInActive(util.getProperty(WEBHOOKS, "sku3"));

//		get inventory from the marketplace using api
		responseFromSellerCentral = sellerAPI.hitSellerCentralInventoryAPI(util.getProperty(WEBHOOKS, "sku3"));
		String marketPlaceInventory = sellerAPI.returnInventory(responseFromSellerCentral);
		String expectedInventory = WebHookPage.MAXINVENTORY;

		System.out.println("INVENTORY ON APP = " + expectedInventory);
		System.out.println("AMAZON INVENTORY = " + marketPlaceInventory);
		Assert.assertEquals(marketPlaceInventory, expectedInventory, "Inventory is not updated on marketplace.");
	}
	
	@Test(priority = 11)
	public void testContinueSellingForShopify() {

//		wait until in progress tag is inactive
		web.checkInProgressTagIsInActive(util.getProperty(WEBHOOKS, "sku4"));
		
//		get inventory from the marketplace using api
		responseFromSellerCentral = sellerAPI.hitSellerCentralInventoryAPI(util.getProperty(WEBHOOKS, "sku4"));
		String marketPlaceInventory = sellerAPI.returnInventory(responseFromSellerCentral);
		String expectedInventory = "150";
		System.out.println("Shopify inventory = " + expectedInventory);
		System.out.println("amazon inventory = " + marketPlaceInventory);
		Assert.assertEquals(marketPlaceInventory, expectedInventory, "Inventory is not updated on marketplace.");
	}
	
	@Test(priority = 12)
	public void testWhenNoSettingsAreApplied() {
//		wait until in progress tag is inactive
		web.checkInProgressTagIsInActive(util.getProperty(WEBHOOKS, "sku6"));
		
//		get inventory from the marketplace using api
		responseFromSellerCentral = sellerAPI.hitSellerCentralInventoryAPI(util.getProperty(WEBHOOKS, "sku6"));
		String marketPlaceInventory = sellerAPI.returnInventory(responseFromSellerCentral);
		String expectedInventory = "150";
		System.out.println("Shopify inventory = " + expectedInventory);
		System.out.println("amazon inventory = " + marketPlaceInventory);
		Assert.assertEquals(marketPlaceInventory, expectedInventory, "Inventory is not updated on marketplace.");
	}

}
