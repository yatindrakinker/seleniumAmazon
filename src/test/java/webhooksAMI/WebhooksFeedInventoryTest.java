package webhooksAMI;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ami.pageobjects.CreateShopifyProduct;
import com.ami.resources.BaseClass;

import apiWebhook.SellerCentralInventoryAPITest;
import apiWebhook.WebhookAPIHit;

public class WebhooksFeedInventoryTest extends BaseClass {
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

	@Test(priority = 1)
	public void initializeInstances() {
		web = new WebHookPage(util);
		apiObj = new WebhookAPIHit(util);
		create = new CreateShopifyProduct(util);
		sellerAPI = new SellerCentralInventoryAPITest(util);
		locTest = new LocationWebhookTest();
		create.createNewShopifyProduct();
		util.hold(30);
		util.openSectionsInNewTab("settings");
		util.openSectionsInNewTab("listings");
		util.openSectionsInNewTab("linking");
		web.linkNewlyCreatedProduct();
		util.goToSection("settings");
	}

	@Test(priority = 2, dataProvider = "getNewProductData")
	public void testProductInventoryUsingFeedsWhenSettingIsDisabled(Map<String, String> input) {
		web.disableInventorySettings();
		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl);
		create.updateInventory();
		web.checkErrorTagIsActive();

	}

	@Test(priority = 3, dataProvider = "getNewProductData")
	public void testProductInventoryUsingFeeds(Map<String, String> input) {
		web.enableInventorySettings();
		web.enableAllWareHouses();
		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl);
		create.updateInventory();
		web.checkInProgressTagIsInActiveAndCheckInventoryFeed();
		responseFromSellerCentral = sellerAPI.hitSellerCentralInventoryAPI();

		String marketPlaceInventory = sellerAPI.returnInventory(responseFromSellerCentral);
		String expectedInventory = CreateShopifyProduct.updatedInventory;
		System.out.println("SHOPIFY INVENTORY = " + expectedInventory);
		System.out.println("AMAZON INVENTORY = " + marketPlaceInventory);

		Assert.assertEquals(marketPlaceInventory, expectedInventory, "Inventory is not updated on marketplace.");

	}

	@Test(priority = 4)
	public void testFixedInventory() {
		web.enableInventorySettings();
		web.enableAllWareHouses();
		web.enableFixedInventory();
		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl);
		create.updateInventory();
		web.checkInProgressTagIsInActiveAndCheckInventoryFeed();
		responseFromSellerCentral = sellerAPI.hitSellerCentralInventoryAPI();
		String marketPlaceInventory = sellerAPI.returnInventory(responseFromSellerCentral);
		String expectedInventory = WebHookPage.FIXEDINVENTORY;
		System.out.println("FIXED INVENTORY ON APP = " + expectedInventory);
		System.out.println("AMAZON INVENTORY = " + marketPlaceInventory);
		Assert.assertEquals(marketPlaceInventory, expectedInventory, "Inventory is not updated on marketplace.");

	}

	@Test(priority = 5)
	public void testReservedInventory() {
		web.enableInventorySettings();
		web.enableAllWareHouses();
		web.enableReservedInventory();
		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl);
		create.updateInventory();
		web.checkInProgressTagIsInActiveAndCheckInventoryFeed();
		responseFromSellerCentral = sellerAPI.hitSellerCentralInventoryAPI();

		String marketPlaceInventory = sellerAPI.returnInventory(responseFromSellerCentral);
		int expectedInventory = Integer.parseInt(CreateShopifyProduct.updatedInventory)
				- Integer.parseInt(WebHookPage.RESERVEDINVENTORY);

		System.out.println("INVENTORY ON APP = " + expectedInventory);
		System.out.println("AMAZON INVENTORY = " + marketPlaceInventory);

		Assert.assertEquals(marketPlaceInventory, String.valueOf(expectedInventory),
				"Inventory is not updated on marketplace.");

	}

	@Test(priority = 6)
	public void testMaximumInventoryIsAppliedOnBothShopifyAndApp() {
		web.enableInventorySettings();
		web.enableAllWareHouses();
		web.enableCustomInventory();
		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl);
		create.enableContinueSelling();
		create.updateInventory();
		web.checkInProgressTagIsInActiveAndCheckInventoryFeed();
		responseFromSellerCentral = sellerAPI.hitSellerCentralInventoryAPI();

		String marketPlaceInventory = sellerAPI.returnInventory(responseFromSellerCentral);
		String expectedInventory = WebHookPage.MAXINVENTORY;

		System.out.println("INVENTORY ON APP = " + expectedInventory);
		System.out.println("AMAZON INVENTORY = " + marketPlaceInventory);

		Assert.assertEquals(marketPlaceInventory, expectedInventory, "Inventory is not updated on marketplace.");

	}

	@Test(priority = 7)
	public void testMaximumInventoryIsAppliedOnShopify() {
		web.enableInventorySettings();
		web.enableAllWareHouses();
		web.disableMaximumInventorySetting();
		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl);
		create.enableContinueSelling();
		create.updateInventory();
		web.checkInProgressTagIsInActiveAndCheckInventoryFeed();
		responseFromSellerCentral = sellerAPI.hitSellerCentralInventoryAPI();

		String marketPlaceInventory = sellerAPI.returnInventory(responseFromSellerCentral);
		String expectedInventory = CreateShopifyProduct.updatedInventory;

		System.out.println("Shopify inventory = " + expectedInventory);
		System.out.println("amazon inventory = " + marketPlaceInventory);
		Assert.assertEquals(marketPlaceInventory, expectedInventory, "Inventory is not updated on marketplace.");
	}

	@Test(priority = 8)
	public void testMaximumInventoryIsAppliedOnApp() {
		web.enableInventorySettings();
		web.enableAllWareHouses();
		web.enableCustomInventory();
		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl);
		create.disableContinueSelling();
		create.updateInventory();
		web.checkInProgressTagIsInActiveAndCheckInventoryFeed();
		responseFromSellerCentral = sellerAPI.hitSellerCentralInventoryAPI();
		String marketPlaceInventory = sellerAPI.returnInventory(responseFromSellerCentral);
		String expectedInventory = CreateShopifyProduct.updatedInventory;

		System.out.println("Shopify inventory = " + expectedInventory);
		System.out.println("amazon inventory = " + marketPlaceInventory);
		Assert.assertEquals(marketPlaceInventory, expectedInventory, "Inventory is not updated on marketplace.");
	}

	@Test(priority = 9, dataProvider = "getNewProductData")
	public void testVariantInventoryOnSellerCentral(HashMap<String, String> input) {
		create = new CreateShopifyProduct(util);
		web = new WebHookPage(util);
		String url = util.getPageURL();
		create.createNewShopifyProduct();
		util.openAndSwitchToNewTab();
		util.openUrl(url);
		util.goToMultiAccountsSettingsClick();
		web.enableInventorySettings();
		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl);
		create.makeSimpleProductToVariants();
		web.linkNewlyCreatedProduct(); //
		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl);
		create.addInventory();
		web.checkInProgressTagIsInActiveAndCheckInventoryFeed();
		web.validateSkuIsPresent();
	}

}
