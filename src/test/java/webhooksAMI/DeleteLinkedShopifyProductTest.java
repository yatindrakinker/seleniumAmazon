package webhooksAMI;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ami.pageobjects.CreateShopifyProduct;
import com.ami.resources.BaseClass;

import apiWebhook.WebhookAPIHit;

public class DeleteLinkedShopifyProductTest extends BaseClass {

	@DataProvider
	public Object[][] getNewProductData() throws IOException {
		List<HashMap<String, String>> data = util.getJsonDataToHashmap("NewSimpleProduct");
		return new Object[][] { { data.get(0) } };
	}

	WebHookPage web;
	WebhookAPIHit apiObj;
	CreateShopifyProduct create;

	@Test(priority = 1, dataProvider = "getNewProductData")
	public void testLinkedProdIsUnlinkedWhenSimpleShopifyProdIsDeleted(Map<String, String> input) {
		create = new CreateShopifyProduct(util);
		web = new WebHookPage(util);
		create.createNewShopifyProduct();
		util.hold(30);
		util.openSectionsInNewTab("linking");
		web.linkNewlyCreatedProduct();
		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl);
		create.deleteShopifyProduct();
		util.hold(30);
		util.refreshPage();
		util.goToSection("linking");
		web.productIsUnlinked();
	}

//	@Test(priority = 2, dataProvider = "getNewProductData")
//	public void testLinkedProdIsUnlinkedWhenVariantShopifyProdIsDeleted(Map<String, String> input) {
//		create = new CreateShopifyProduct(util);
//		web = new WebHookPage(util);
//		create.createNewVarinatShopifyProduct();
//		util.hold(30);
//		web.linkNewlyCreatedProduct();
//		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl);
//		create.deleteShopifyProduct();
//		util.hold(30);
//		util.goToSection("overview");
//		web.productIsUnlinked();
//	}

}
