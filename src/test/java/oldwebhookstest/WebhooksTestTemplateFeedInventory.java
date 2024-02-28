package oldwebhookstest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ami.pageobjects.CreateShopifyProduct;
import com.ami.pageobjects.reusableclasses.ReusableMethods;
import com.ami.resources.BaseClass;

import apiWebhook.SellerCentralInventoryAPITest;
import apiWebhook.WebhookAPIHit;
import webhooksAMI.LocationWebhookTest;
import webhooksAMI.WebHookPage;

public class WebhooksTestTemplateFeedInventory extends BaseClass {

	public static final String RESERVEDINVENTORY = "50";
	public static final String FIXEDINVENTORY = "50";

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
	ReusableMethods reuse;
	private String responseFromSellerCentral;

	@Test(priority = 1)
	public void initializeInstances() {
		web = new WebHookPage(util);
		apiObj = new WebhookAPIHit(util);
		create = new CreateShopifyProduct(util);
		sellerAPI = new SellerCentralInventoryAPITest(util);
		locTest = new LocationWebhookTest();
		reuse = new ReusableMethods(util);

		create.createNewShopifyProduct();
		util.hold(30);
		util.openSectionsInNewTab("settings");
		util.openSectionsInNewTab("listings");
		util.openSectionsInNewTab("linking");
		web.linkNewlyCreatedProduct();
	}

	@Test(priority = 2)
	public void testInventoryAssignedOnTemplate() {
		util.goToSection("settings");
		reuse.createNewTemplateUsingAdvanceSelection();
		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl);
		create.updateInventory();
		web.checkInProgressTagIsInActiveAndCheckInventoryFeed();
		responseFromSellerCentral = sellerAPI.hitSellerCentralInventoryAPI();

		String marketPlaceInventory = sellerAPI.returnInventory(responseFromSellerCentral);
		String expectedInventory = CreateShopifyProduct.updatedInventory;
		System.out.println("SHOPIFY INVENTORY = " + expectedInventory);
		System.out.println("AMAZON INVENTORY = " + marketPlaceInventory);

		Assert.assertEquals(marketPlaceInventory, expectedInventory,
				"Inventory is not updated on marketplace.");

	}

	@Test(priority = 3)
	public void testFixedInventorySetInTemplate() {
		reuse.openTemplate();
		reuse.enableAndSetFixedInventory(FIXEDINVENTORY);
		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl);
		create.updateInventory();
		web.checkInProgressTagIsInActiveAndCheckInventoryFeed();
		responseFromSellerCentral = sellerAPI.hitSellerCentralInventoryAPI();
		String marketPlaceInventory = sellerAPI.returnInventory(responseFromSellerCentral);
		String expectedInventory = FIXEDINVENTORY;
		System.out.println("SHOPIFY INVENTORY = " + expectedInventory);
		System.out.println("AMAZON INVENTORY = " + marketPlaceInventory);

		Assert.assertEquals(marketPlaceInventory, expectedInventory,
				"Inventory is not updated on marketplace.");
	}

	@Test(priority = 4)
	public void testReservedInventorySetInTemplate() {
		reuse.openTemplate();
		reuse.enableAndSetReservedInventory(RESERVEDINVENTORY);
		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl);
		create.updateInventory();
		web.checkInProgressTagIsInActiveAndCheckInventoryFeed();
		responseFromSellerCentral = sellerAPI.hitSellerCentralInventoryAPI();
		
		String marketPlaceInventory = sellerAPI.returnInventory(responseFromSellerCentral);
		int expectedInventory = Integer.parseInt(CreateShopifyProduct.updatedInventory)
				- Integer.parseInt(RESERVEDINVENTORY);

		System.out.println("INVENTORY ON APP = " + expectedInventory);
		System.out.println("AMAZON INVENTORY = " + marketPlaceInventory);

		Assert.assertEquals(marketPlaceInventory, String.valueOf(expectedInventory),
				"Inventory is not updated on marketplace.");

	}

	@Test(priority = 5)
	public void testContinueSellingInventorySetInTemplateAndOnShopify() {
		reuse.openTemplate();
		reuse.enableAndSetContinueSellingWhenOutOfStockInventory();
		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl);
		create.enableContinueSelling();
		create.updateInventory();
		web.checkInProgressTagIsInActiveAndCheckInventoryFeed();
		responseFromSellerCentral = sellerAPI.hitSellerCentralInventoryAPI();
		
		String marketPlaceInventory = sellerAPI.returnInventory(responseFromSellerCentral);
		String expectedInventory = "999";

		System.out.println("INVENTORY ON APP = " + expectedInventory);
		System.out.println("AMAZON INVENTORY = " + marketPlaceInventory);

		Assert.assertEquals(marketPlaceInventory, expectedInventory,
				"Inventory is not updated on marketplace.");
	}

//	@Test(priority = 6)
//	public void testContinueSellingInventorySetInTemplate() {
//		reuse.openTemplate();
//		reuse.enableAndSetContinueSellingWhenOutOfStockInventory();
//		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl);
//		create.disableContinueSelling();
//		create.updateInventory();
//		web.checkInProgressTagIsInActiveAndCheckInventoryFeed();
//		responseFromSellerCentral = sellerAPI.hitSellerCentralInventoryAPI();
//		
//		String marketPlaceInventory = sellerAPI.returnInventory(responseFromSellerCentral);
//		String expectedInventory = CreateShopifyProduct.updatedInventory;
//		
//		System.out.println("Shopify inventory = " + expectedInventory);
//		System.out.println("amazon inventory = " + marketPlaceInventory);
//		Assert.assertEquals(marketPlaceInventory, expectedInventory,
//				"Inventory is not updated on marketplace.");
//	}

}
