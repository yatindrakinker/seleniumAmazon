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
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;

import com.ami.pageobjects.CreateShopifyProduct;
import com.ami.resources.BaseClass;

import apiWebhook.WebhookAPIHit;
import webhooksAMI.WebHookPage;

public class WebhooksTestVariantsProduct extends BaseClass {
	int initialVariantSize = 0;

	@DataProvider
	public Object[][] getNewProductData() throws IOException {
		List<HashMap<String, String>> data = util.getJsonDataToHashmap("NewVariantProduct");
		return new Object[][] { { data.get(0) } };
	}

	@Test(priority = 1, dataProvider = "getNewProductData")
	public void testCreateNewVariantsActiveShopifyProduct(HashMap<String, String> input) {
		CreateShopifyProduct create = new CreateShopifyProduct(util);
		WebHookPage web = new WebHookPage(util);
		WebhookAPIHit apiObj = new WebhookAPIHit(util);
		CreateShopifyProduct.productUrl = "";
		create.goToCreateNewProductPage(util.getConfigProperty("store"));
		create.createNewVariantProduct(input, "Active");
		util.hold(15);
		web.getShopifyProductDataInJson(CreateShopifyProduct.productId);
		web.shopifyJsonToJavaObjects();
		web.getShopifyValue("id");
		util.hold(30);
		web.amazonJsonToJavaObjects(apiObj.getCreateProductData(), "create");
		util.hold(5);
		web.amazonJsonToJavaObjects(apiObj.getCreateProductData(), "create");
		web.getAMIValue("id");
		web.validateProductIsCreated("positive");
	}

	@Test(priority = 2, dataProvider = "getNewProductData")
	public void testTitleUpdateWebhookForVariantProduct(HashMap<String, String> input) {
		variantsProductUpdate(input, "title", "updated_title_variants", "product");
	}

	@Test(priority = 3, dataProvider = "getNewProductData")
	public void testDescriptionUpdateWebhookForVariantsProduct(HashMap<String, String> input) {
		variantsProductUpdate(input, "description", "updated_description_variants", "product");
	}

	@Test(priority = 4, dataProvider = "getNewProductData")
	public void testVendorUpdateWebhookForVariantsProduct(HashMap<String, String> input) {
		variantsProductUpdate(input, "vendor", "updated_vendor", "variants");
	}

	@Test(priority = 4, dataProvider = "getNewProductData")
	public void testPriceUpdateWebhookForVariantsProduct(HashMap<String, String> input) {
		variantsProductUpdate(input, "price", "updated_price", "variants");
	}

	@Test(priority = 5, dataProvider = "getNewProductData")
	public void testSKUUpdateWebhookForVariantsProduct(HashMap<String, String> input) {
		variantsProductUpdate(input, "sku", "updated_sku", "variants");
	}

	@Test(priority = 6, dataProvider = "getNewProductData")
	public void testBarcodeUpdateWebhookForVariantsProduct(HashMap<String, String> input) {
		variantsProductUpdate(input, "barcode", "updated_barcode", "variants");
	}

	@Test(priority = 8, dataProvider = "getNewProductData")
	public void testWeightUpdateWebhookForVariantsProduct(HashMap<String, String> input) {
		variantsProductUpdate(input, "weight", "updated_weight", "variants");
	}

	@Test(priority = 9, dataProvider = "getNewProductData")
	public void testWeightUnitUpdateWebhookForVariantsProduct(HashMap<String, String> input) {
		variantsProductUpdate(input, "weight_unit", "updated_weight_unit", "variants");
	}

	@Test(priority = 10, dataProvider = "getNewProductData")
	public void testChangeImageOrder(HashMap<String, String> input) {
		boolean isTrue = false;
		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl);
		WebHookPage web = new WebHookPage(util);
		CreateShopifyProduct create = new CreateShopifyProduct(util);
		WebhookAPIHit apiObj = new WebhookAPIHit(util);
		web.amazonJsonToJavaObjects(apiObj.getUpdateProductData(), "update");
		util.hold(5);
		String initialImgVal = WebHookPage.mainImgUrl;
		System.err.println("initialImgVal = " + initialImgVal);
		create.addImage(input);
		create.changeImgByDragging();
		util.hold(30);
		web.amazonJsonToJavaObjects(apiObj.getUpdateProductData(), "update");
		util.hold(5);
		web.amazonJsonToJavaObjects(apiObj.getUpdateProductData(), "update");
		System.err.println("WebHookPage.mainImgUrl = " + WebHookPage.mainImgUrl);

		if (!initialImgVal.equals(WebHookPage.mainImgUrl)) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);

	}

	@Test(priority = 11)
	public void testDeleteVariant() {
		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl);
		WebHookPage web = new WebHookPage(util);
		WebhookAPIHit apiObj = new WebhookAPIHit(util);
		CreateShopifyProduct create = new CreateShopifyProduct(util);
		initialVariantSize = WebHookPage.updateResponseSize;
		create.deleteVariant();
		util.hold(30);
		web.amazonJsonToJavaObjects(apiObj.getUpdateProductData(), "update");
		util.hold(5);
		web.amazonJsonToJavaObjects(apiObj.getUpdateProductData(), "update");

		if (initialVariantSize != WebHookPage.updateResponseSize) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(true);
		}

	}

	@Test(priority = 12)
	public void testDeleteVariantProduct() {
		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl);
		WebHookPage web = new WebHookPage(util);
		WebhookAPIHit apiObj = new WebhookAPIHit(util);
		CreateShopifyProduct create = new CreateShopifyProduct(util);
		initialVariantSize = WebHookPage.updateResponseSize;
		create.deleteShopifyProduct();
		util.hold(30);
//		web.getProductDeleteDraftArchieveResponse(apiObj.getUpdateProductData());
		web.getProductDeleteDraftArchieveResponse(apiObj.getCreateProductData());
	}

	@Test(priority = 13, dataProvider = "getNewProductData")
	public void testCreateNewVariantsDraftShopifyProduct(HashMap<String, String> input) {
		CreateShopifyProduct create = new CreateShopifyProduct(util);
		WebHookPage web = new WebHookPage(util);
		WebhookAPIHit apiObj = new WebhookAPIHit(util);
		CreateShopifyProduct.productUrl = "";
		create.goToCreateNewProductPage(util.getConfigProperty("store"));
		create.createNewVariantProduct(input, "Draft");
		util.hold(5);
		create.addImage(input);
		util.hold(5);
		web.getShopifyProductDataInJson(CreateShopifyProduct.productId);
		web.shopifyJsonToJavaObjects();
		web.getShopifyValue("id");
		util.hold(30);
//		web.getProductDeleteDraftArchieveResponse(apiObj.getUpdateProductData());
		web.getProductDeleteDraftArchieveResponse(apiObj.getCreateProductData());

	}

	@Test(priority = 14)
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

	public void variantsProductUpdate(HashMap<String, String> input, String attributeName, String key,
			String getValFrom) {
		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl);
		WebHookPage web = new WebHookPage(util);
		WebhookAPIHit apiObj = new WebhookAPIHit(util);
		CreateShopifyProduct create = new CreateShopifyProduct(util);
		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl);
		create.updateVariantsValue(input, attributeName, key); // =>change attribute name and key
		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl + ".json");
		util.hold(2);
		util.refreshPage();
		web.shopifyJsonToJavaObjects();

		util.hold(30);
		web.amazonJsonToJavaObjects(apiObj.getUpdateProductData(), "update");
		util.hold(5);
		web.amazonJsonToJavaObjects(apiObj.getUpdateProductData(), "update");

		for (int i = 1; i < WebHookPage.updateResponseSize; i++) {

			if (getValFrom.equalsIgnoreCase("product")) {
				web.getShopifyValue(attributeName); // =>change attribute name
			} else if (getValFrom.equalsIgnoreCase("variants")) {
				web.getShopifyVariantValue(attributeName, i); // =>change attribute name
			} else if (getValFrom.equalsIgnoreCase("image") || (getValFrom.equalsIgnoreCase("image_src"))) {
				web.getShopifyImageValue(attributeName); // =>change attribute name
			}

			if (getValFrom.equalsIgnoreCase("variants")) {
				web.getAMIVariantValueUpdate(attributeName, i); // =>change attribute name
			} else {
				web.getAMIValueUpdate(attributeName); // =>change attribute name
			}

			web.compareAttributeValues();
		}
	}

}
