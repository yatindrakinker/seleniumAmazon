package com.ami.pageobjects;

import org.openqa.selenium.support.PageFactory;

import com.ami.resources.Utilities;

public class ProductEditNewPage extends DefaultTemplatePageOR {
	Utilities util;
	CreateShopifyProduct cr ;
	String url = "";

	public ProductEditNewPage(Utilities util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
	}
	
	/**
	 * Not listed product functionality
	 * 1	create a new Shopify product
	 * 2	go to listing -> not listed -> search product -> open to edit
	 * 3	validate sections
	 * 4	update values from shopify and validate
	 * 5	update values from app and validate
	 */
	public void createProduct() {
		cr = new CreateShopifyProduct(util);
		url = util.getPageURL();
		util.openAndSwitchToNewTab();
		cr.createNewShopifyProduct();
	}
	
	public void goToListing() {
		
	}
}
