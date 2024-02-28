package com.ami.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ami.pageobjects.reusableclasses.ReusableMethodsOR;

public class ProductLinkingPageOR extends ReusableMethodsOR {

//	-----------------------------------Overview Page Objects Product Linking -----------------------------------------------

	@FindBy(xpath = "//span[text() = 'Linked']/parent::div/following-sibling::div/button/span[@class =  'Polaris-Spinner Polaris-Spinner--sizeSmall']")
	protected WebElement loaderLinked;

	@FindBy(xpath = "//span[text() = 'Close Match']/parent::div/following-sibling::div/button/span[@class =  'Polaris-Spinner Polaris-Spinner--sizeSmall']")
	protected WebElement loaderCloseMatch;

//	-----------------------------------Overview Page Objects Product Linking-----------------------------------------------

//	----------------------------------Link Amazon Product Page Objects------------------------------------------------------

	@FindBy(xpath = "//p[text()='Fulfilment Type: ']/following-sibling::div/div/p")
	protected WebElement fbmfbaSelector;

	@FindBy(xpath = "(//button[@role='menuitem']//div[@class='custom-flag']/following-sibling::p)[1]")
	protected WebElement activeAcnt;

	@FindBy(xpath = "//h1[text() = 'Link Amazon Products']")
	protected WebElement headingLinkAmzProduct;

	@FindBy(id = "close-match")
	protected WebElement closeMatchTab;

	@FindBy(css = "button[id='close-match']>div>span>div>span[class*='--tone']>span+span")
	protected WebElement closeMatchProductCountLinkAmazon;

	@FindBy(id = "linking-required")
	protected WebElement linkingRequired;

	@FindBy(css = "button[id='linking-required']>div>span>div>span[class*='--tone']>span+span")
	protected WebElement linkingRequiredProductCountLinkAmazon;

	@FindBy(id = "linked")
	protected WebElement linked;

	@FindBy(id = "deleted")
	protected WebElement deletedTab;

	@FindBy(css = "button[id='deleted']>span>div>span+span>span+span")
	protected WebElement countDeltedTab;

	@FindBy(css = "button[class$='Polaris-Button--destructive']")
	protected List<WebElement> delBtnsDeletedTab;

	@FindBy(xpath = "//th[normalize-space()='Amazon']")
	protected WebElement amazonHeading;

	@FindBy(xpath = "//th[normalize-space()='Shopify']")
	protected WebElement shopifyHeading;

	@FindBy(css = "a[href *= 'myshopify.com/admin/products/']")
	protected List<WebElement> viewOnShopifyLinkListLinked;

	@FindBy(className = "user-card")
	protected WebElement user;

	@FindBy(className = "user-card")
	protected List<WebElement> userList;

	@FindBy(css = "button[id='linked']>div>span>div>span[class*='--tone']>span+span")
	protected WebElement linkedProductCountLinkAmazon;

	@FindBy(xpath = "//input[@placeholder = 'Search by Amazon Title, Barcode, or SKU']")
	protected WebElement searchInputField;

	@FindBy(xpath = "//button[@class = 'Polaris-TextField__ClearButton']/span")
	protected WebElement clearInputFieldBtn;

	@FindBy(xpath = "//th[normalize-space()='Image']")
	protected WebElement imageHeading;

	@FindBy(xpath = "//th[normalize-space()='Product Title']")
	protected WebElement productTitleHeading;

	@FindBy(xpath = "//th[normalize-space()='Amazon SKU']")
	protected WebElement sellerCentralSKUHeading;

	@FindBy(xpath = "//th[normalize-space()='Barcode']")
	protected WebElement barcodeHeading;

	@FindBy(xpath = "//th[normalize-space()='Amazon Status']")
	protected WebElement amazonStatusHeading;

	@FindBy(xpath = "//th[normalize-space()='Action']")
	protected WebElement actionHeading;

	@FindBy(css = "tr[class = 'ant-table-row ant-table-row-level-0']")
	protected List<WebElement> productList;

	@FindBy(css = "h3.Polaris-Text--root.Polaris-Text--headingSm")
	protected List<WebElement> productTitle;

	@FindBy(css = "a[href^='http://Amazon.in']")
	protected List<WebElement> viewOnAmzLink;

	@FindBy(xpath = "//td[@class = 'ant-table-cell'][3]/span")
	protected List<WebElement> sellerCentralSKUValue;

	@FindBy(xpath = "//td[@class = 'ant-table-cell'][4]/span")
	protected List<WebElement> barcodeValue;

	@FindBy(css = "td[class = 'ant-table-cell']:nth-of-type(6)>span>span+span")
	protected List<WebElement> statusValue;

	@FindBy(id = "failedOrder_tour_7")
	protected List<WebElement> linkBtnList;

	@FindBy(xpath = "//h1[text() = 'How can we help you ?']")
	protected WebElement getSupportPageHeading;

	@FindBy(xpath = "//tr[contains(@class,'ant-table-row ant-table-row-level')]")
	protected List<WebElement> searchedProductList;

	@FindBy(xpath = "//span[@class='ant-spin-dot ant-spin-dot-spin']")
	protected WebElement nestedLodingIcon;

	@FindBy(xpath = "(//span[@class='Polaris-Spinner Polaris-Spinner--sizeSmall'])[last()]")
	protected WebElement loader;

	@FindBy(xpath = "(//span[@class='Polaris-Spinner Polaris-Spinner--sizeSmall'])[last()]")
	protected List<WebElement> loaderList;

//	------------------linking required-----------------------

	@FindBy(xpath = "//td[@class='ant-table-cell'][2]/div/div[1]//h3")
	protected List<WebElement> productTitleLinkingReq;

//	---------------------------Link product Modal Page Objects-----------------------

	@FindBy(xpath = "//h2[text() = 'Link Products']")
	protected WebElement linkProductModalHeading;

	@FindBy(xpath = "//h2[text() = 'Link Products']")
	protected List<WebElement> linkProductModalHeadingList;

	@FindBy(xpath = "//td[@class='ant-table-cell'][2]/div/div[2]//h3")
	protected List<WebElement> shopifyLinkedProductNameList;

	@FindBy(xpath = "//td[@class='ant-table-cell'][1]/div/div[2]//h3")
	protected List<WebElement> amazonLinkedProductNameList;

	@FindBy(css = "tr[class='ant-table-row ant-table-row-level-0'] button")
	protected List<WebElement> unlinkBtnList;

	@FindBy(xpath = "//span[text() = 'Yes']")
	protected WebElement confirmYesBtn;

	@FindBy(css = "div[role='dialog'] a[href^='http://Amazon.in/']")
	protected WebElement viewOnAmzLinkModal;

	@FindBy(xpath = "//div[@role='dialog']//a[text()='View on Shopify']")
	protected List<WebElement> viewOnShopifyLinkModal;

	@FindBy(css = "input[placeholder='Search by Shopify Title or SKU']")
	protected WebElement searchShopifyInputFieldModal;

	@FindBy(xpath = "//td[@class = 'ant-table-cell'][5]/button")
	protected List<WebElement> linkBtnListModal;

//	-------------------------confirm link product modal page objects--------------------

	@FindBy(xpath = "//h2[text() = 'Link']")
	protected WebElement confirmLinkProductModalHeading;

	@FindBy(xpath = "//span[text() = 'Back']")
	protected WebElement backBtnModal;

	@FindBy(xpath = "//span[contains(text(),'Amazon product has been successfully linked ')]")
	protected WebElement productLinkedSuccessfullyToast;

//	-------------------closed match-------------------------------

	@FindBy(xpath = "//th[text()='Amazon']")
	protected WebElement amazonSectionCloseMatch;

	@FindBy(xpath = "//th[text()='Shopify']")
	protected WebElement shopifySectionCloseMatch;

	@FindBy(xpath = "//th[text()='Action']")
	protected WebElement actionSectionCloseMatch;

	@FindBy(xpath = "//span[text() = 'Select Manually']/ancestor::button")
	protected List<WebElement> selectManuallyBtnList;

	@FindBy(xpath = "//span[text() = 'Select Manually']")
	protected List<WebElement> selectManuallyBtnListCloseMatch;

	@FindBy(xpath = "//input[@placeholder='Search by Shopify Title or SKU']/parent::div/parent::div/parent::div/parent::div/following-sibling::div//tr[@class = 'ant-table-row ant-table-row-level-0']/td[2]")
	protected List<WebElement> productListCloseMatchLinkModal;

	@FindBy(xpath = "//div[@role='dialog']//tr[@class = 'ant-table-row ant-table-row-level-0']/td[5]/button")
	protected List<WebElement> linkBtnCloseMatchLinkModal;

	@FindBy(xpath = "//td[1]//p[text() = 'Seller-sku:']/parent::div/following-sibling::div/span")
	protected List<WebElement> amzSellerSkuListCloseMatch;

	@FindBy(xpath = "//input[@placeholder='Search by Shopify Title or SKU']/parent::div/parent::div/parent::div/parent::div/following-sibling::div//tr[@class = 'ant-table-row ant-table-row-level-0']/td[5]/button")
	protected List<WebElement> btnListCloseMatchLinkProductModal;

	@FindBy(xpath = "//h3[@class='Polaris-Text--root Polaris-Text--headingSm']")
	protected List<WebElement> productTitleCloseMatch;

	@FindBy(xpath = "//p[text()='Seller-sku:']")
	protected List<WebElement> sellerSKUCloseMatch;

	@FindBy(xpath = "//p[text()='Barcode:']")
	protected List<WebElement> barcodeCloseMatch;

	@FindBy(xpath = "//a[text() = 'View on Amazon']")
	protected List<WebElement> viewOnAmazonLinkCloseMatch;

	@FindBy(xpath = "//span[text() = 'Link']/parent::span/parent::button")
	protected List<WebElement> linkBtnCloseMatch;

	@FindBy(xpath = "//span[text() = 'Select Manually']/parent::span/parent::button")
	protected List<WebElement> selectManuallyBtn;

//	----------------------link products modal close match -----------------------

	@FindBy(xpath = "//h2[text() = 'Link Products']")
	protected List<WebElement> linkProductsHeadingModal;

	@FindBy(xpath = "//a[text() = 'View On Amazon']")
	protected List<WebElement> viewOnAmzLinkListLinkModalCloseMatch;

	@FindBy(xpath = "//h2[text() = 'Link']")
	protected WebElement linkHeadingConfirmLinkModalCloseMatch;

	@FindBy(xpath = "(//div[@role='dialog']//div[@class = 'custom-productlinking']/div//p)[1]")
	protected WebElement productName;

	@FindBy(xpath = "//div[@role = 'dialog']//input[@placeholder='Search by Shopify Title or SKU']")
	protected WebElement searchInputProductLinkProductsModal;

	@FindBy(xpath = "//div[@role = 'dialog']//a[text() = 'View On Amazon']")
	protected List<WebElement> viewOnAmazonLinkLinkProductsModal;

	@FindBy(xpath = "//div[@role='dialog']//span[text() = 'Link']")
	protected List<WebElement> linkBtnLinkProductsModal;

	@FindBy(xpath = "//div[@role='dialog']//a[text()='View on Shopify']/preceding-sibling::p")
	protected List<WebElement> shopifyProductsLinkProductsModal;

	@FindBy(xpath = "//div[@role='dialog']//a[text()='View on Shopify']")
	protected List<WebElement> viewOnShopifyLinkLinkProductsModal;

	@FindBy(name = "title")
	protected WebElement shopifyTitleInputField;

	@FindBy(css = "#productTitle")
	protected WebElement productTitleOnAmazon;

//	---------------------unlink modal page objects----------------------------

	@FindBy(xpath = "//h2[text() = 'Unlink Product']")
	protected WebElement unlinkModalHeading;

	@FindBy(xpath = "//span[text() = 'No']")
	protected WebElement noBtn;

//	-----------------------------------------<link amazon product page>--------------------------------

	@FindBy(xpath = "(//span[text()='Close Match']/following-sibling::span/span)[2]")
	protected WebElement countClosedMatchProduct;

	@FindBy(xpath = "//button[@id='linking-requiredMeasurer']/span/div/span[2]/span[2]")
	protected WebElement countLinkReqProduct;

	@FindBy(xpath = "(//span[text()='Linked']/following-sibling::span/span)[2]")
	protected WebElement countLinkedProduct;

	@FindBy(xpath = "//img[@height = '60px']")
	protected List<WebElement> imgsOfProduct;

	@FindBy(xpath = "//tr[contains(@class,'ant-table-row ant-table-row-level')]/td[3]/p")
	protected List<WebElement> skuOfProduct;

	@FindBy(xpath = "//tr[contains(@class,'ant-table-row ant-table-row-level')]/td[4]/p")
	protected List<WebElement> barcodeOfProduct;

	@FindBy(xpath = "//tr[contains(@class,'ant-table-row ant-table-row-level')]/td[5]/p")
	protected List<WebElement> inventoryOfProduct;

	@FindBy(xpath = "//tr[contains(@class,'ant-table-row ant-table-row-level')]/td[6]/span/span[2]")
	protected List<WebElement> amazonStatusOfProduct;

	@FindBy(xpath = "//tr[contains(@class,'ant-table-row ant-table-row-level')]/td[7]//button")
	protected List<WebElement> actionOfProduct;

//	-----------------------------------------</link amazon product page>--------------------------------

//	---------------------------------<LINKING REQUIRED>-----------------------------------------------

	@FindBy(css = "button[id^='failedOrder_tour_7']+div>button")
	protected List<WebElement> kebabListLinkingReq;

	@FindBy(xpath = "//span[text()='Auto Sync off']")
	protected WebElement autoSyncOffBtnLinkingReq;
	
	@FindBy(xpath = "//div[@role='dialog']//h2[text()='Auto Sync Settings']")
	protected List<WebElement> autoSyncSettingsModal;
	
	@FindBy(xpath = "//div[@role='dialog']//input[@type='checkbox']")
	protected WebElement autoSyncSettingsModalCheckbox;
	
	@FindBy(xpath = "//div[@role='dialog']//span[text()='No']/ancestor::button")
	protected WebElement autoSyncSettingsModalNoBtn;
	
	@FindBy(xpath = "//div[@role='dialog']//span[text()='Yes']/ancestor::button")
	protected WebElement autoSyncSettingsModalYesBtn;
	
	@FindBy(xpath = "//span[text()='Product Sync Enabled: Changes to this product will not be automatically synchronized.']")
	protected WebElement productSyncEnabled;
	
	@FindBy(xpath = "//span[text()='Product Sync Disabled: Changes to this product will not be automatically synchronized.']")
	protected WebElement productSyncDisabled;
	
	@FindBy(xpath = "//span[text()='Delete Product']")
	protected WebElement deleteProductBtnLinkingReq;

	@FindBy(xpath = "//div[@role='dialog'] //h2[contains(text(),'Delete Product')]")
	protected List<WebElement> deleteAmazonProductModalLinkingReq;

	@FindBy(css = "section[class='Polaris-Box']>div>div+div>p[class$='Polaris-Text--semibold']")
	protected WebElement availabilityOnAmazonMsg;

	@FindBy(xpath = "//p[text()='Seller-Sku:']/following-sibling::p")
	protected WebElement skuOfDeleteListing;

	@FindBy(xpath = "//span[text()='Product successfully deleted from Amazon.']")
	protected WebElement prodSuccessfullyDeletedFromAmzMsg;

}
