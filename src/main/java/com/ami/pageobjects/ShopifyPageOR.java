package com.ami.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShopifyPageOR {
	
	@FindBy(id = "account_email")
	WebElement emailInputField;
	
	@FindBy(xpath = "//button[@name = 'commit']")
	WebElement loginButton;
	
	@FindBy(className = "user-card")
	WebElement user;
	
	@FindBy(id = "account_password")
	WebElement passwordInputField;
	
	@FindBy(id = "AppFrameTopBar")
	WebElement topBar;
	
	@FindBy(xpath = "//h3[text()='There’s a problem loading this page']")
	List<WebElement> problemInPageLoad;
	
	@FindBy(xpath = "//div[@class = 'FQTSE']//button")
	WebElement shopifyStoreSelector;
	
	@FindBy(xpath = "//div[@class = '_utVO']//li//span")
	List<WebElement> allStore;
	
	@FindBy(xpath = "//div[@class = 'user-card__email']")
	List<WebElement> userEmailList;
	
	@FindBy(xpath = "//span[text() = 'Sales channels']")
	WebElement salesChannelButton;
	
	@FindBy(xpath = "//div[@class='qPq52']/div")
	List<WebElement> installedChannelList;
	
	@FindBy(css = "ul#search-results a[href*='/apps/amazon-by-cedcommerce-staging']")
	protected WebElement stagingStore;
	
	@FindBy(css = "ul#search-results a[href*='/apps/amazon-sales-channel-1']")
	protected WebElement liveStore;
	
	@FindBy(xpath = "//div[text() = 'Amazon Multi Account Demo']/parent::div/parent::a")
	WebElement amazonMultiAcntDemoInSalesChannel;
	
	@FindBy(xpath = "//span[text() = 'Amazon Multi Account Demo']")
	WebElement amazonMultiAcntDemoInNavBar;
	
	@FindBy(xpath = "//span[text() = 'Amazon by CedCommerce']")
	WebElement amazonByCedCommerceNavBar;
	
	@FindBy(xpath = "//span[text() = 'Seller Name']")
	WebElement sellerNameHeading;
	
	@FindBy(xpath = "//h1[text()='Overview']")
	WebElement overviewHeading;
	
//	------------------------------------CREATE NEW PRODUCT-----------------------------------
	
	@FindBy(xpath = "//span[contains(text(),'Add product')]")
	WebElement addProductButton;
	
	@FindBy(xpath = "//input[@placeholder = 'Short sleeve t-shirt']")
	WebElement titleInputField;
	
	@FindBy(id = "product-description_ifr")
	WebElement iframe;
	
	@FindBy(id = "tinymce")
	WebElement productDescriptionBox;
	
	@FindBy(className = "dn_ht")
	WebElement addFilesButton;
	
	@FindBy(xpath = "//input[@placeholder = 'e.g., T-Shirt']")
	WebElement productType;
	
	@FindBy(xpath = "//input[@placeholder='Find or create tags']")
	WebElement productTags;
	
	@FindBy(name = "price")
	WebElement priceInputField;
	
	@FindBy(name = "compareAtPrice")
	WebElement compareAtPriceInputField;
	
	@FindBy(id = "InventoryCardSku")
	WebElement skuInpField;
	
	@FindBy(id = "InventoryCardBarcode")
	WebElement barcodeInputField;
	
	@FindBy(xpath = "//div[@class='NNGPS']//input")
	List<WebElement> locationsInpFieldList;
	
	@FindBy(id = "ShippingCardWeight")
	WebElement weightInpField;
	
	@FindBy(id = "ShippingCardWeightUnit")
	WebElement selectWtUnit;
	
	@FindBy(xpath = "//button[contains(@class,'Polaris-Button--primary_7k9zs')][1]")
	WebElement saveBtn;
	
	@FindBy(xpath = "//span[text() = 'Save as active']")
	WebElement setAsActiveBtn;
	
	@FindBy(xpath = "//span[text() = 'Product created']")
	WebElement productCreatedMsg;
	
	@FindBy(xpath = "//span[text() = 'Delete product']")
	WebElement deleteProductBtn;
	
	@FindBy(xpath = "(//span[text() = 'Delete product'])[2]")
	WebElement confirmDeleteProductBtn;
	
	@FindBy(css = ".user-card")
	List<WebElement> userCard;
	
	@FindBy(xpath = "//div[@class='user-card__email']")
	List<WebElement> userCardEmail;

}
