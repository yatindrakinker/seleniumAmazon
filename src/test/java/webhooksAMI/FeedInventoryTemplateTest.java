package webhooksAMI;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ami.pageobjects.CreateShopifyProduct;
import com.ami.pageobjects.reusableclasses.ReusableMethods;
import com.ami.resources.BaseClass;

import apiWebhook.SellerCentralInventoryAPITest;
import apiWebhook.WebhookAPIHit;

public class FeedInventoryTemplateTest extends BaseClass{
	private static final String WEBHOOKS = "webhookstemplate";
	private String url = "";
	private String responseFromSellerCentral;

	WebHookPage web;
	WebhookAPIHit apiObj;
	CreateShopifyProduct create;
	SellerCentralInventoryAPITest sellerAPI;
	LocationWebhookTest locTest;
	ReusableMethods reuse;

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
		reuse = new ReusableMethods(util);

//		create shopify product
		for (int i = 1; i <= 5; i++) {
			create.createNewShopifyProduct();
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
//		go to settings
		reuse.goToTemplatesInSettings();
//		open new template page
//		fill all details
//		search product using advance search
//		save template
		reuse.createNewTemplateUsingAdvanceSelection(util.getProperty(WEBHOOKS, "product1Name"));
//		open template, update inventory settings and save
		reuse.searchTemplateOnGrid(util.getProperty(WEBHOOKS, "product1Name"));
		reuse.openEditTemplatePage(util.getProperty(WEBHOOKS, "product1Name"));
		reuse.enableAndSetFixedInventory(WebHookPage.FIXEDINVENTORY);
//		go to linking required section
//		link shopify product
		util.goToSection("linking");
		web.linkNewlyCreatedProduct(WEBHOOKS, "sku1", "product1Name");
//		go to listing
//		search product using sku
//		check for inprogress tag
		web.inprogressTagIsActive(util.getProperty(WEBHOOKS, "sku1"));
	}
	
	@Test(priority = 3)
	public void testSetReserveInventory() {
//		go to settings
		reuse.goToTemplatesInSettings();
//		open new template page
//		fill all details
//		search product using advance search
//		save template
		reuse.createNewTemplateUsingAdvanceSelection(util.getProperty(WEBHOOKS, "product2Name"));
//		open template, update inventory settings and save
		reuse.searchTemplateOnGrid(util.getProperty(WEBHOOKS, "product2Name"));
		reuse.openEditTemplatePage(util.getProperty(WEBHOOKS, "product2Name"));
		reuse.enableAndSetReservedInventory(WebHookPage.RESERVEDINVENTORY);
//		go to linking required section
//		link shopify product
		util.goToSection("linking");
		web.linkNewlyCreatedProduct(WEBHOOKS, "sku2", "product2Name");
//		go to listing
//		search product using sku
//		check for inprogress tag
		web.inprogressTagIsActive(util.getProperty(WEBHOOKS, "sku2"));
	}
	
	@Test(priority = 4)
	public void testSetContinueSellingInventoryOnBothAppAndShopify() {
//		go to settings
		reuse.goToTemplatesInSettings();
//		open new template page
//		fill all details
//		search product using advance search
//		save template
		reuse.createNewTemplateUsingAdvanceSelection(util.getProperty(WEBHOOKS, "product3Name"));
//		open template, update inventory settings and save
//		reuse.searchTemplateOnGrid(util.getProperty(WEBHOOKS, "product3Name"));
//		reuse.openEditTemplatePage(util.getProperty(WEBHOOKS, "product3Name"));
//		reuse.enableAndSetReservedInventory(WebHookPage.RESERVEDINVENTORY);
//		go to linking required section
		
//		go to Shopify
		gotoProduct(util.getProperty(WEBHOOKS, "productId3"));
//		enable continue selling on shopify
		create.enableContinueSelling();
		
//		link shopify product
		util.goToSection("linking");
		web.linkNewlyCreatedProduct(WEBHOOKS, "sku3", "product3Name");
//		go to listing
//		search product using sku
//		check for inprogress tag
		web.inprogressTagIsActive(util.getProperty(WEBHOOKS, "sku3"));
	}
	
	@Test(priority = 5)
	public void testSetContinueSellingInventoryOnShopify() {
//		go to settings
		reuse.goToTemplatesInSettings();
//		open new template page
//		fill all details
//		search product using advance search
//		save template
		reuse.createNewTemplateUsingAdvanceSelection(util.getProperty(WEBHOOKS, "product4Name"));
//		open template, disable continue selling settings and save
		reuse.searchTemplateOnGrid(util.getProperty(WEBHOOKS, "product4Name"));
		reuse.openEditTemplatePage(util.getProperty(WEBHOOKS, "product4Name"));
		reuse.disableContinueSellingCheckBox();
		reuse.saveTemplate();
//		go to linking required section
		
//		go to Shopify
		gotoProduct(util.getProperty(WEBHOOKS, "productId4"));
//		enable continue selling on shopify
		create.enableContinueSelling();
		
//		link shopify product
		util.goToSection("linking");
		web.linkNewlyCreatedProduct(WEBHOOKS, "sku4", "product4Name");
//		go to listing
//		search product using sku
//		check for inprogress tag
		web.inprogressTagIsActive(util.getProperty(WEBHOOKS, "sku4"));
	}
	
	@Test(priority = 6)
	public void testSetContinueSellingInventoryIsDisabledOnAppAndShopify() {
//		go to settings
		reuse.goToTemplatesInSettings();
//		open new template page
//		fill all details
//		search product using advance search
//		save template
		reuse.createNewTemplateUsingAdvanceSelection(util.getProperty(WEBHOOKS, "product5Name"));
//		open template, disable continue selling settings and save
		reuse.searchTemplateOnGrid(util.getProperty(WEBHOOKS, "product5Name"));
		reuse.openEditTemplatePage(util.getProperty(WEBHOOKS, "product5Name"));
		reuse.disableContinueSellingCheckBox();
		reuse.saveTemplate();
//		go to linking required section
		
//		go to Shopify
		gotoProduct(util.getProperty(WEBHOOKS, "productId5"));
//		enable continue selling on shopify
		create.disableContinueSelling();
		
//		link shopify product
		util.goToSection("linking");
		web.linkNewlyCreatedProduct(WEBHOOKS, "sku5", "product5Name");
//		go to listing
//		search product using sku
//		check for inprogress tag
		web.inprogressTagIsActive(util.getProperty(WEBHOOKS, "sku5"));
		web.checkInProgressTagIsInActive(util.getProperty(WEBHOOKS, "sku5"));
//		get inventory from the marketplace using api
		responseFromSellerCentral = sellerAPI.hitSellerCentralInventoryAPI(util.getProperty(WEBHOOKS, "sku5"));
		String marketPlaceInventory = sellerAPI.returnInventory(responseFromSellerCentral);
		String expectedInventory = "100";
		System.out.println("Shopify inventory = " + expectedInventory);
		System.out.println("amazon inventory = " + marketPlaceInventory);
		Assert.assertEquals(marketPlaceInventory, expectedInventory, "Inventory is not updated on marketplace.");

	}
	
	@Test(priority = 7)
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
	
	@Test(priority = 8)
	public void testReservedInventoryIsUpdatedOnMarketPlace() {
		
		int expectedInventory;

//		wait until in progress tag is inactive
		web.checkInProgressTagIsInActive(util.getProperty(WEBHOOKS, "sku2"));

//		get inventory from the marketplace using api
		responseFromSellerCentral = sellerAPI.hitSellerCentralInventoryAPI(util.getProperty(WEBHOOKS, "sku2"));
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
	
	@Test(priority = 9)
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
	
	@Test(priority = 10)
	public void testContinueSellingForShopify() {

//		wait until in progress tag is inactive
		web.checkInProgressTagIsInActive(util.getProperty(WEBHOOKS, "sku4"));
		
//		get inventory from the marketplace using api
		responseFromSellerCentral = sellerAPI.hitSellerCentralInventoryAPI(util.getProperty(WEBHOOKS, "sku4"));
		String marketPlaceInventory = sellerAPI.returnInventory(responseFromSellerCentral);
		String expectedInventory = "100";
		System.out.println("Shopify inventory = " + expectedInventory);
		System.out.println("amazon inventory = " + marketPlaceInventory);
		Assert.assertEquals(marketPlaceInventory, expectedInventory, "Inventory is not updated on marketplace.");
	}

}
