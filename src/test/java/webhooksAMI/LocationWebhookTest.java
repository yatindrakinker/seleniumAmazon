package webhooksAMI;
/**
 * Project Name: Amazon MultiAccount 
 * Author: Yatindra Kinker 
 * Version: 
 * Reviewed By: Abhay Hayaran 
 * Date of Creation: March 14, 2022
 *  Modification History:
 * Date of change: 
 * Version: 
 * changed function:  
 * change description:
 * Modified By:
 */

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ami.pageobjects.CreateShopifyProduct;
import com.ami.resources.BaseClass;

import apiWebhook.WebhookAPIHit;

public class LocationWebhookTest extends BaseClass {

	@DataProvider
	public Object[][] getNewProductData() throws IOException {
		List<HashMap<String, String>> data = util.getJsonDataToHashmap("NewSimpleProduct");
		return new Object[][] { { data.get(0) } };
	}

	@Test(priority = 1, dataProvider = "getNewProductData")
	public void testCreateNewSimpleActiveShopifyProduct(HashMap<String, String> input) {
		CreateShopifyProduct create = new CreateShopifyProduct(util);
		WebHookPage web = new WebHookPage(util);
		WebhookAPIHit apiObj = new WebhookAPIHit(util);
		CreateShopifyProduct.productUrl = "";
		create.goToCreateNewProductPage(util.getConfigProperty("store"));
		create.createNewProduct(input, "Active");
		web.getShopifyProductDataInJson(CreateShopifyProduct.productId);
		web.shopifyJsonToJavaObjects();
		web.getShopifyValue("id");
		util.hold(45);
		web.amazonJsonToJavaObjects(apiObj.getCreateProductData(), "create");
		web.getAMIValue("id");
		web.validateProductIsCreated("positive");
	}

	@Test(priority = 2, dataProvider = "getNewProductData")
	public void testAddNewLocation(HashMap<String, String> input) {
		WebHookPage web = new WebHookPage(util);
		CreateShopifyProduct create = new CreateShopifyProduct(util);
		create.goToLocationsShopify();
		create.addNewLocation(input);
		util.closeShopifySettings();
		util.hold(2);
		util.goToMultiAccountsSettingsClick();
		util.hold(60);
		util.clickOnTemplates();
		util.refreshPage();
		util.switchToIFrame();
		util.clickOnTemplates();
		web.validateLocationIsAddedSuccessfully();
	}

	@Test(priority = 3, dataProvider = "getNewProductData")
	public void testUpdateLocation(HashMap<String, String> input) {
		WebHookPage web = new WebHookPage(util);
		CreateShopifyProduct create = new CreateShopifyProduct(util);
		create.updateLocation(input);
		util.closeShopifySettings();
		util.hold(2);
		util.goToMultiAccountsSettingsClick();
		util.hold(60);
		util.clickOnTemplates();
		util.refreshPage();
		util.switchToIFrame();
		util.clickOnTemplates();
		web.updateLocationUpdatedSuccessfully(input);
	}

	@Test(priority = 4, dataProvider = "getNewProductData")
	public void testDeactivateLocation(HashMap<String, String> input) {
		WebHookPage web = new WebHookPage(util);
		CreateShopifyProduct create = new CreateShopifyProduct(util);
		create.deactivateLocation();
		util.closeShopifySettings();
		util.hold(2);
		util.goToMultiAccountsSettingsClick();
		util.hold(60);
		util.clickOnTemplates();
		util.refreshPage();
		util.switchToIFrame();
		util.clickOnTemplates();
		web.validateLocationIsDeactivatedSuccessfully();
	}

	@Test(priority = 5, dataProvider = "getNewProductData")
	public void testDeleteLocation(HashMap<String, String> input) {
		WebHookPage web = new WebHookPage(util);
		WebhookAPIHit apiObj = new WebhookAPIHit(util);
		CreateShopifyProduct create = new CreateShopifyProduct(util);
		create.deleteLocation();
		util.hold(45);
		util.closeShopifySettings();
		web.validateNewLocationIsDeleted(apiObj.getCreateProductData());
	}

	@Test(priority = 6)
	public void testDeleteProduct() {
		WebHookPage web = new WebHookPage(util);
		WebhookAPIHit apiObj = new WebhookAPIHit(util);
		CreateShopifyProduct create = new CreateShopifyProduct(util);
		create.deleteShopifyProduct();
		util.hold(30);
		web.getProductDeleteDraftArchieveResponse(apiObj.getCreateProductData());
	}
}
