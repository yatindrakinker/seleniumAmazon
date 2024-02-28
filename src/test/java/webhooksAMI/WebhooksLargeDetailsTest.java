package webhooksAMI;

import org.testng.annotations.Test;

import com.ami.pageobjects.CreateShopifyProduct;
import com.ami.resources.BaseClass;

import apiWebhook.WebhookAPIHit;

public class WebhooksLargeDetailsTest extends BaseClass {
	WebHookPage web;
	WebhookAPIHit apiObj;
	CreateShopifyProduct create;
	
	public void createLargeVariantsProduct(int variantsCount) {
		create.createNewShopifyProductWithLargeDetails();
		create.addVariants(variantsCount);
		util.hold(30);
		util.openSectionsInNewTab("listings");
		web.searchBySkuOnListing(CreateShopifyProduct.skuOfProudct);
		web.waitForProductToImportOnApp();
	}
	
	@Test(priority = 1)
	public void initializeInstances() {
		web = new WebHookPage(util);
		apiObj = new WebhookAPIHit(util);
		create = new CreateShopifyProduct(util);
	}

	@Test(priority = 2)
	public void createNewProduct() {
		create.createNewShopifyProductWithLargeDetails();
		util.hold(30);
		util.openSectionsInNewTab("listings");
		web.searchBySkuOnListing(CreateShopifyProduct.skuOfProudct);
		web.waitForProductToImportOnApp();
	}

	@Test(priority = 3)
	public void createNew50VariantProduct() {
		createLargeVariantsProduct(50);
	}
	
	@Test(priority = 3)
	public void createNew90VariantProduct() {
		createLargeVariantsProduct(90);
	}
	
	@Test(priority = 3)
	public void createNew99VariantProduct() {
		createLargeVariantsProduct(99);
	}
}
