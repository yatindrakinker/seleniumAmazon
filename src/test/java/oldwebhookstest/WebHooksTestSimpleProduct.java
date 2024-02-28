package oldwebhookstest;

/**
 * Project Name: Amazon MultiAccount 
 * Author: Yatindra Kinker 
 * Version: 
 * Reviewed By: Abhay Hayaran 
 * Date of Creation: Feb 10, 2022
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

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ami.pageobjects.CreateShopifyProduct;
import com.ami.resources.BaseClass;

import apiWebhook.WebhookAPIHit;
import webhooksAMI.WebHookPage;

public class WebHooksTestSimpleProduct extends BaseClass {
	WebHookPage web;
	WebhookAPIHit apiObj;
	CreateShopifyProduct create;

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

	@Test(priority = 3, dataProvider = "getNewProductData")
	public void testTitleUpdateWebhookForSimpleProduct(HashMap<String, String> input) {
		simpleProductUpdate(input, "title", "updated_title_simple", "product");
	}

	@Test(priority = 4, dataProvider = "getNewProductData")
	public void testDescriptionUpdateWebhookForSimpleProduct(HashMap<String, String> input) {
		simpleProductUpdate(input, "description", "updated_description_simple", "product");
	}

	@Test(priority = 5, dataProvider = "getNewProductData")
	public void testPriceUpdateWebhookForSimpleProduct(HashMap<String, String> input) {
		simpleProductUpdate(input, "price", "updated_price", "variants");
	}

	@Test(priority = 6, dataProvider = "getNewProductData")
	public void testSKUUpdateWebhookForSimpleProduct(HashMap<String, String> input) {
		simpleProductUpdate(input, "sku", "updated_sku", "variants");
	}

	@Test(priority = 7, dataProvider = "getNewProductData")
	public void testBarcodeUpdateWebhookForSimpleProduct(HashMap<String, String> input) {
		simpleProductUpdate(input, "barcode", "updated_barcode", "variants");
	}

	@Test(priority = 8, dataProvider = "getNewProductData")
	public void testVendorUpdateWebhookForSimpleProduct(HashMap<String, String> input) {
		simpleProductUpdate(input, "vendor", "updated_vendor", "variants");
	}

	@Test(priority = 9, dataProvider = "getNewProductData")
	public void testWeightUpdateWebhookForSimpleProduct(HashMap<String, String> input) {
		simpleProductUpdate(input, "weight", "updated_weight", "variants");
	}

	@Test(priority = 9, dataProvider = "getNewProductData")
	public void testWeightUnitUpdateWebhookForSimpleProduct(HashMap<String, String> input) {
		simpleProductUpdate(input, "weight_unit", "updated_weight_unit", "variants");
	}

	@Test(priority = 10, dataProvider = "getNewProductData")
	public void testInventoryUpdateWebhookForSimpleProduct(HashMap<String, String> input) {
		simpleProductUpdate(input, "inventory_quantity", "updated_inventory", "variants");
	}

	@Test(priority = 11, dataProvider = "getNewProductData")
	public void testCompareAtPriceUpdateWebhookForSimpleProduct(HashMap<String, String> input) {
		simpleProductUpdate(input, "compare_at_price", "updated_inventory", "variants");
	}

	@Test(priority = 12, dataProvider = "getNewProductData")
	public void testImageUpdateWebhookForSimpleProduct(HashMap<String, String> input) {
		simpleProductUpdate(input, "image_src", "updated_image_url", "image");
	}

	@Test(priority = 13, dataProvider = "getNewProductData")
	public void testTagsUpdateWebhook(HashMap<String, String> input) {
		simpleProductUpdate(input, "tags", "updated_tags", "product");
	}

	@Test(priority = 14, dataProvider = "getNewProductData")
	public void testProductTypeUpdateWebhook(HashMap<String, String> input) {
		simpleProductUpdate(input, "productType", "updated_product_type", "product");
	}

	@Test(priority = 15, dataProvider = "getNewProductData")
	public void testAllImgDeleteWebhook(HashMap<String, String> input) {
		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl);
		WebhookAPIHit apiObj = new WebhookAPIHit(util);
		WebHookPage web = new WebHookPage(util);
		CreateShopifyProduct create = new CreateShopifyProduct(util);
		create.deleteAllImg();
		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl + ".json");
		util.refreshPage();
		util.hold(5);
		util.refreshPage();
		web.shopifyJsonToJavaObjects();
		web.getShopifyImgNull();
		util.hold(30);
		web.amazonJsonToJavaObjects(apiObj.getUpdateProductData(), "update");
		web.amazonJsonToJavaObjects(apiObj.getUpdateProductData(), "update");
		web.getAMIImgNull();
		web.compareValuesWhenNoImgIsAssigned();
	}

	@Test(priority = 16)
	public void testDeleteProduct() {
		WebHookPage web = new WebHookPage(util);
		WebhookAPIHit apiObj = new WebhookAPIHit(util);
		CreateShopifyProduct create = new CreateShopifyProduct(util);
		create.deleteShopifyProduct();
		util.hold(30);
//		web.getProductDeleteDraftArchieveResponse(apiObj.getUpdateProductData());
		web.getProductDeleteDraftArchieveResponse(apiObj.getCreateProductData());
	}

	@Test(priority = 17, dataProvider = "getNewProductData")
	public void testCreateNewSimpleDraftShopifyProduct(HashMap<String, String> input) {
		CreateShopifyProduct create = new CreateShopifyProduct(util);
		WebHookPage web = new WebHookPage(util);
		create.goToCreateNewProductPage(util.getConfigProperty("store"));
		create.createNewProduct(input, "Draft");
		web.getShopifyProductDataInJson(CreateShopifyProduct.productId);
		web.shopifyJsonToJavaObjects();
		WebhookAPIHit apiObj = new WebhookAPIHit(util);
		util.hold(30);
//		web.getProductDeleteDraftArchieveResponse(apiObj.getUpdateProductData());
		web.getProductDeleteDraftArchieveResponse(apiObj.getCreateProductData());
		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl);
		create.deleteShopifyProduct();
	}

	@Test(priority = 18, dataProvider = "getNewProductData")
	public void testArchiveShopifyProduct(HashMap<String, String> input) {
		CreateShopifyProduct create = new CreateShopifyProduct(util);
		WebHookPage web = new WebHookPage(util);
		WebhookAPIHit apiObj = new WebhookAPIHit(util);
		create.goToCreateNewProductPage(util.getConfigProperty("store"));
		create.createNewProduct(input, "Active");
		web.getShopifyProductDataInJson(CreateShopifyProduct.productId);
		web.shopifyJsonToJavaObjects();
		web.getShopifyValue("id");
		util.hold(45);
		web.amazonJsonToJavaObjects(apiObj.getCreateProductData(), "create");
		web.getAMIValue("id");
		web.validateProductIsCreated("positive");
		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl);
		create.archieveProduct();
		util.hold(30);
//		web.getProductDeleteDraftArchieveResponse(apiObj.getUpdateProductData());
		web.getProductDeleteDraftArchieveResponse(apiObj.getCreateProductData());
		create.deleteShopifyProduct();
	}

	@Test(priority = 19, dataProvider = "getNewProductData")
	public void testSalesChannelInactiveProductActive(HashMap<String, String> input) {
		CreateShopifyProduct create = new CreateShopifyProduct(util);
		WebHookPage web = new WebHookPage(util);
		WebhookAPIHit apiObj = new WebhookAPIHit(util);
		create.goToCreateNewProductPage(util.getConfigProperty("store"));
		create.createNewProduct(input, "Active");
		create.makeSalesChannelInactive();
		web.getShopifyProductDataInJson(CreateShopifyProduct.productId);
		web.shopifyJsonToJavaObjects();
		web.getShopifyValue("id");
		util.hold(45);
		web.amazonJsonToJavaObjects(apiObj.getCreateProductData(), "create");
		web.getAMIValue("id");
//		web.getProductDeleteDraftArchieveResponse(apiObj.getUpdateProductData());
		web.getProductDeleteDraftArchieveResponse(apiObj.getCreateProductData());
		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl);
		create.archieveProduct();
		util.hold(30);
//		web.getProductDeleteDraftArchieveResponse(apiObj.getUpdateProductData());
		web.getProductDeleteDraftArchieveResponse(apiObj.getCreateProductData());
		create.deleteShopifyProduct();
	}

	@Test(priority = 20, dataProvider = "getNewProductData")
	public void testSalesChannelInactiveProductDraft(HashMap<String, String> input) {
		CreateShopifyProduct create = new CreateShopifyProduct(util);
		WebHookPage web = new WebHookPage(util);
		WebhookAPIHit apiObj = new WebhookAPIHit(util);
		create.goToCreateNewProductPage(util.getConfigProperty("store"));
		create.createNewProduct(input, "Draft");
		create.makeSalesChannelInactive();
		web.getShopifyProductDataInJson(CreateShopifyProduct.productId);
		web.shopifyJsonToJavaObjects();
		web.getShopifyValue("id");
		util.hold(45);
		web.amazonJsonToJavaObjects(apiObj.getCreateProductData(), "create");
		web.getAMIValue("id");
//		web.getProductDeleteDraftArchieveResponse(apiObj.getUpdateProductData());
		web.getProductDeleteDraftArchieveResponse(apiObj.getCreateProductData());
		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl);
		create.archieveProduct();
		util.hold(30);
//		web.getProductDeleteDraftArchieveResponse(apiObj.getUpdateProductData());
		web.getProductDeleteDraftArchieveResponse(apiObj.getCreateProductData());
		create.deleteShopifyProduct();
		util.hold(10);
//		deleteAll();
	}

	@Test(priority = 21)
	public void deleteAll() {
		util.openAndSwitchToNewTab();
		if (util.getConfigProperty("store").equals("live")) {
			util.openUrl(util.getConfigProperty("storeUrl") + "products?selectedView=all&query=Automation%20Test");
		} else if (util.getConfigProperty("store").equals("stroreUrlStaging")) {
			util.openUrl(util.getConfigProperty("storeUrl") + "products?selectedView=all&query=Automation%20Test");
		}

		if (util.getDriver().findElements(By.xpath("//p[text() = 'No products found']")).isEmpty()) {
			util.click(util.getDriver().findElement(By.xpath("//th[1]//input")));
			util.click(util.getDriver().findElement(By.xpath("//button[@aria-label = 'More actions']")));
			util.click(util.getDriver().findElement(By.xpath("//span[text() = 'Delete products']")));
			util.click(util.getDriver().findElement(By.xpath("//span[text() = 'Delete']")));
		}

	}


	public void simpleProductUpdate(HashMap<String, String> input, String attributeName, String key,
			String getValFrom) {
		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl);
		WebHookPage web = new WebHookPage(util);
		WebhookAPIHit apiObj = new WebhookAPIHit(util);
		CreateShopifyProduct create = new CreateShopifyProduct(util);
		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl);
		create.updateValue(input, attributeName, key); // =>change attribute name and key
		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl + ".json");
		util.hold(2);
		util.refreshPage();
		web.shopifyJsonToJavaObjects();

		if (getValFrom.equalsIgnoreCase("product")) {
			web.getShopifyValue(attributeName); // =>change attribute name
		} else if (getValFrom.equalsIgnoreCase("variants")) {
			web.getShopifyVariantValue(attributeName, 1); // =>change attribute name
		} else if (getValFrom.equalsIgnoreCase("image") || (getValFrom.equalsIgnoreCase("image_src"))) {
			web.getShopifyImageValue(attributeName); // =>change attribute name
		}

		util.hold(30);
		web.amazonJsonToJavaObjects(apiObj.getUpdateProductData(), "update");
		util.hold(10);
		web.amazonJsonToJavaObjects(apiObj.getUpdateProductData(), "update");
		web.getAMIValueUpdate(attributeName); // =>change attribute name
		web.compareAttributeValues();
	}

}