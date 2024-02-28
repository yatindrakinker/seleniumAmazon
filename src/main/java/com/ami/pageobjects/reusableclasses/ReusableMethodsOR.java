package com.ami.pageobjects.reusableclasses;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReusableMethodsOR {

	protected static final String ARIASELECTED = "aria-selected";
	protected static final String ARIADISABLED = "aria-disabled";
	protected static final String ARIACHECKED = "aria-checked";
	protected static final String ARIAEXPANDED = "aria-expanded";
	protected static final String ARIAHIDDEN = "aria-hidden";
	protected static final String ARIAPRESSED = "aria-pressed";
	protected static final String SETTINGSSECTION = "settings";
	protected static final String OVERVIEWSECTION = "overview";
	protected static final String LINKINGSECTION = "linking";
	protected static final String TRUEVAL = "true";
	protected static final String FALSEVAL = "false";
	protected static final String VAL = "value";
	protected static final String ACTIVE = "Active";
	protected static final String INACTIVE = "Inactive";
	protected static final String CLASSVAL = "class";
	protected static final String POINTER = "pointer";
	protected static final String STORE = "store";
	protected static final String SIZE = "Size";
	protected static final String COLOR = "Color";
	protected static final String MATERIAL = "Material";
	protected static final String STYLE = "Style";
	protected static final String LISTING = "listings";
	protected static final String SETTINGS = "settings";
	protected static final String LINKING = "linking";
	protected static final String OVERVIEW = "overview";
	protected static final String LIVE = "live";
	protected static final String STAGING = "staging";
	protected static final String LISTINGURL = "apps/amazon-sales-channel-1/panel/user/product";
	protected static final String OVERVIEWURL = "apps/amazon-sales-channel-1/panel/user/dashboard";
	protected static final String SETTINGSURL = "apps/amazon-sales-channel-1/panel/user/config";
	protected static final String LIINKINGURL = "apps/amazon-sales-channel-1/panel/user/productlinkings";
	protected static final String SUPPORTLINK = "https://amazon-by-cedcommerce.cifapps.com/panel/support";
	protected static final String APPROVECHARGESPAGEURL = "https://admin.shopify.com/store/testing-staging-store/charges";
	protected static final String SKU = "47269740020010";
	protected static final String STAGINGADMIN = "https://staging-amazon-by-cedcommerce.cifapps.com/auth/login?admin_user_token=";
	protected static final String LIVEADMIN = "https://amazon-by-cedcommerce.cifapps.com/auth/login?admin_user_token=";
	protected static final String TOKEN = "token";
	protected static final String WEBHOOKS = "webhooks";
	protected static final String SKUSFILE = "sku";
	protected static final String TARGET = "target";
	protected static final String BLANK = "_blank";

	protected static final String AMAZONURL = "https://sellercentral.amazon.in/";
	


	@FindBy(id = "subscription")
	protected WebElement subscriptionHeadingOverview;

	@FindBy(css = "div.Custom--Activity-Wrapper div.Polaris-ProgressBar--colorPrimary")
	protected WebElement progressBarActivitiesOverview;

	@FindBy(css = "div[role='dialog'] button[aria-label='Close']")
	protected WebElement crossBtnModal;

	@FindBy(css = "div[role='dialog'] button[aria-label='Close']")
	protected List<WebElement> crossBtnModalList;

	@FindBy(css = "ul#search-results a[href*='/apps/amazon-by-cedcommerce-staging']")
	protected WebElement stagingStore;

	@FindBy(css = "ul#search-results a[href*='/apps/amazon-sales-channel-1']")
	protected WebElement liveStore;

	@FindBy(css = "a[href$='panel/user/dashboard']")
	protected WebElement overViewNavigation;

	@FindBy(xpath = "//span[text()='Synchronization with Amazon Started']/parent::div/following-sibling::div/div")
	protected List<WebElement> syncStatusProgressbarList;

	@FindBy(xpath = "//span[text()=\"Synchronization of Amazon Listing's images with Amazon Started\"]/parent::div/following-sibling::div")
	protected List<WebElement> syncAmazonListingImgList;

	@FindBy(xpath = "//span[text() = 'Sales channels']")
	protected WebElement salesChannel;

	@FindBy(xpath = "//div[text() = 'CedCommerce Amazon Channel']")
	protected WebElement cedAmzLive;

	@FindBy(xpath = "//div[text() = 'Amazon by Ced - Staging']")
	protected WebElement cedAmzStaging;

	@FindBy(css = "a[href$='/panel/user/config']")
	protected WebElement cedAmzSettings;

	@FindBy(xpath = "//h1[text() = 'Overview']")
	protected WebElement overViewHeading;

	@FindBy(id = "productlinking")
	protected WebElement productLinkingHeadingOverview;

	@FindBy(xpath = "//h2[text() = 'Linked Product Status']")
	protected WebElement linkedProductStatus;

	@FindBy(id = "sales")
	protected WebElement ordersOverview;

	@FindBy(xpath = "//span[text() = 'Manage Linking']/parent::span/parent::button")
	protected WebElement manageLinkingBtn;

	@FindBy(xpath = "//span[text() = 'Linked']")
	protected WebElement statusLinkedProductLabel;

//	@FindBy(css = "button[id='linked']>div>span>div>span[class*='--tone']>span+span")
//	protected WebElement linkedProductCount;

	@FindBy(xpath = "//span[text() = 'Linked']/parent::span/following-sibling::button")
	protected WebElement linkedProductCount;

	@FindBy(css = "button[id='linked']>span>div>span+span>span+span")
	protected WebElement linkedMatchCountLinkAmzProd;

	@FindBy(xpath = "//span[text() = 'Linking Required']")
	protected WebElement statusLinkingRequiredProductLabel;

	@FindBy(xpath = "//span[text() = 'Linking Required']/parent::span/following-sibling::button")
	protected WebElement linkingRequiredProductCount;

	@FindBy(css = "button[id='linking-required']>span>div>span+span>span+span")
	protected WebElement linkingRequiredMatchCountLinkAmzProd;

	@FindBy(xpath = "//span[text() = 'Close Match']")
	protected WebElement statusCloseMatchProductLabel;

	@FindBy(xpath = "//span[text() = 'Close Match']/parent::span/following-sibling::button")
	protected WebElement closeMatchProductCount;

	@FindBy(css = "button[id='close-match']>span>div>span+span>span+span")
	protected WebElement closeMatchCountLinkAmzProd;

	@FindBy(xpath = "//p[text()='Fulfilment Type: ']")
	protected WebElement fulfillmentTypeLabel;

	@FindBy(name = "app-iframe")
	protected WebElement iframe;

	@FindBy(css = "nav[role='navigation']>button")
	protected WebElement backBtn;

	@FindBy(xpath = "(//span[@class='Polaris-Button__Icon']/parent::span/parent::button)[1]")
	protected WebElement backBtnListing;

	@FindBy(xpath = "//span[text()='Save']//parent::button")
	protected WebElement saveBtn;

	@FindBy(xpath = "//p[text() = 'Seller Name']/following-sibling::button")
	protected WebElement selectStoreBtn;

	@FindBy(xpath = "//p[text() = 'Seller Name']")
	protected WebElement sellerNameLabel;

	@FindBy(xpath = "//p[normalize-space()='Seller Name']/following-sibling::button")
	protected WebElement addNewSellerBtn;

	@FindBy(css = "ul[role='menu']>li:last-of-type button")
	protected WebElement addNewAcntBtn;

	@FindBy(css = "ul[role='menu']>li:last-of-type button")
	protected List<WebElement> addNewAcntList;

	@FindBy(xpath = "//h1[text() = 'Add New Account']")
	protected WebElement addNewAcntPageHeading;

	@FindBy(id = "nextURL")
	protected WebElement nextPaginationBtn;

	@FindBy(id = "previousURL")
	protected WebElement prevPaginationBtn;

	@FindBy(css = "div[class='ant-empty-description']")
	protected WebElement noData;

	@FindBy(css = "div[class='ant-empty-description']")
	protected List<WebElement> noDataList;

	@FindBy(xpath = "//p[text() = 'No Products Found']")
	protected List<WebElement> noProductsFound;

	@FindBy(xpath = "//p[text() = 'No Feeds Found']")
	protected List<WebElement> noFeedsFound;

	@FindBy(css = "div[role='dialog'] button[class = 'Polaris-Modal-CloseButton']>span")
	protected List<WebElement> crossBtnExportModalList;

	@FindBy(css = "nav[aria-label='Pagination'] div[aria-live='polite']>span")
	protected WebElement totalPages;

	@FindBy(css = "span[class='Polaris-Tag__TagText']>span")
	protected WebElement statusTagOrders;

	@FindBy(css = "div[role='dialog'] h2")
	protected List<WebElement> headingOfModal;

	@FindBy(xpath = "//div[@role='dialog']//div[@style='width: 234px; display: inline-block; word-break: break-word;']/span")
	protected List<WebElement> productsInManualSelectionModal;

	@FindBy(xpath = "//div[@role='dialog']//span[contains(text(),'Selected products : ')]")
	protected WebElement selectedProductsManualSelectionDialogBox;

	@FindBy(css = "div[role='dialog'] input[type='checkbox']")
	protected WebElement checkBoxInManualSelectionModal;

	@FindBy(xpath = "//div[@role='dialog']//span[@class='Polaris-Badge']")
	protected WebElement productsStatusManualSelectionDialogBox;

	@FindBy(xpath = "//div[@role='dialog']//span[text()='Cancel']")
	protected WebElement cancelBtnManualSelectionDialogBox;

	@FindBy(xpath = "//div[@role='dialog']//button[@aria-label='Close']")
	protected WebElement crossBtnManualSelectionDialogBox;

	// ----------- <GLOBAL PAGEOBJECTS NAVIGATION MENU>----------------

	@FindBy(css = "a[href$='/panel/user/product']")
	protected WebElement listingsNavigation;

	@FindBy(css = "a[href$='/panel/user/productlinkings']")
	protected WebElement productLinkingNavigation;

	@FindBy(css = "a[href$='/panel/user/config']")
	protected WebElement settingsNavigation;

	@FindBy(css = "a[href$='/panel/user/faq']")
	protected WebElement faqNavigation;

//	------------------------ </GLOBAL PAGEOBJECTS NAVIGATION MENU> ------------------------------------

//	------------------------ <GLOBAL PAGEOBJECTS  FOOTER> ------------------------------------

	@FindBy(css = "a[href='https://amazon-sales-channel-app-backend.cifapps.com/Cedcommerce_Privacy_Statement.pdf']")
	protected WebElement termsAndConditionFooterLink;

	@FindBy(css = "a[href*='https://docs.cedcommerce.com/shopify/multi-account-amazon-cedcommerce/?section=']")
	protected WebElement cedAmzChannelFooterLink;

	@FindBy(css = "a[href='https://amazon-sales-channel-app-backend.cifapps.com/Data_Privacy_Rights.pdf']")
	protected WebElement dataPrivacyRightsFooterLink;

	@FindBy(css = "a[href='https://calendly.com/scale-business-with-cedcommerce/shopify-amazon-integration']")
	protected WebElement bookACallFooterLink;

	@FindBy(xpath = "//button[text()='Get Support']")
	protected WebElement getSupportFooterBtn;

//	------------------------ </GLOBAL PAGEOBJECTS  FOOTER> ------------------------------------

//	------------------------ <GLOBAL PAGEOBJECTS  OVERVIEW> ------------------------------------

	@FindBy(xpath = "//span[text()='All Activities']")
	protected WebElement allActivitiesBtn;

//	------------------------ </GLOBAL PAGEOBJECTS  OVERVIEW> ------------------------------------

//	------------------------ <GLOBAL PAGEOBJECTS TEMPLATE> ------------------------------------
	@FindBy(id = "templates")
	protected WebElement templatesTab;

	@FindBy(id = "template_TextField_SearchBar")
	protected WebElement templateSearchInputField;

	@FindBy(css = "button[id^='template_Btn_Menu_']")
	protected List<WebElement> kebabMenuList;

	@FindBy(xpath = "//span[text()='Edit']")
	protected WebElement editBtn;

	@FindBy(xpath = "//span[text()='Clone']")
	protected WebElement cloneBtn;

	@FindBy(xpath = "//span[text()='Delete']")
	protected WebElement deleteBtn;

	@FindBy(id = "templateSettings")
	protected WebElement productTemplates;

//	@FindBy(xpath = "//div[@class='custom-product--templateName']/span/span")
	@FindBy(xpath = "//p[contains(@id ,'template_Text_CreatedOn')]/preceding-sibling::h6")
	protected List<WebElement> templateNameList;

	@FindBy(id = "template_TextField_SearchCategory")
	protected WebElement searchCategoryInputField;

	@FindBy(id = "FBM")
	protected WebElement radioBtnFBM;

	@FindBy(id = "FBA")
	protected WebElement radioBtnFBA;

	@FindBy(className = "Polaris-TextField__ClearButton")
	protected WebElement crossBtnSearchCategoryInputField;

	@FindBy(id = "template_Btn_Search")
	protected WebElement searchCategoryBtn;

	@FindBy(id = "template_Btn_Clear")
	protected List<WebElement> clearSearchBtn;

	@FindBy(css = "div[id*='template_ResourceItem_'] span[id*='template_Text_PathMarkup_']>span>div>div:first-of-type>p")
	protected List<WebElement> searchedCategoriesList;

	@FindBy(xpath = "//input[@placeholder='999']")
	protected WebElement setMaxInventory;

	@FindBy(xpath = "//span[contains(text(),'Continue selling when out of stock')]/preceding-sibling::span/span/input")
	protected WebElement contSellingCheckBox;

	@FindBy(css = "div[class*= 'Polaris-Frame-Toast']")
	protected WebElement toastMsg;

	@FindBy(xpath = "//span[text() = 'Add New Template']/parent::span/parent::button")
	protected WebElement addNewTemplateBtn;

	@FindBy(css = "input[placeholder = 'Enter Template Name']")
	protected WebElement templateNameInputField;

	@FindBy(xpath = "//div[text() = 'New']")
	protected WebElement setAmazonRecommendationOptionNew;

	@FindBy(xpath = "//div[text()= 'Default']")
	protected WebElement defaultCategory;

	@FindBy(id = "template_Text_Attributes")
	protected WebElement attributeHeading;

	@FindBy(xpath = "//p[text() = 'Amazon Recommendations']//ancestor::label[contains(@class,'Polaris-Choice')]//input[@type='radio']")
	protected WebElement amazonRecommendationsRadioBtn;

	@FindBy(id = "offer_condition_type")
	protected WebElement offerConditionType;

	@FindBy(xpath = "//div[text() = 'Please Select' and @class = ' css-1dimb5e-singleValue']/parent::div/parent::div")
	protected WebElement defaultSelectAttribute;

	@FindBy(css = "div[class*='css-1jqq78o-placeholder']")
	protected WebElement setAmazonRecommendationSelector;

	@FindBy(id = "manual")
	protected WebElement manualRadioBtn;

	@FindBy(css = "input[placeholder = 'Search products with Title']")
	protected WebElement manualSelectionInpField;

	@FindBy(xpath = "//span[text() = 'Browse products']/parent::span/parent::button")
	protected WebElement browseManualProductsButton;

	@FindBy(css = "div[role='dialog'] input[type = 'checkbox']")
	protected WebElement checkBoxProductsManualSelectionDialogBox;

	@FindBy(css = "div[role='dialog'] button.Polaris-Button.Polaris-Button--primary")
	protected WebElement continueBtnManualSelectionDialogBox;

	@FindBy(css = "div[role='dialog'] button.Polaris-Button.Polaris-Button--primary")
	protected List<WebElement> continueBtnManualSelectionDialogBoxList;

	@FindBy(xpath = "//span[text()='See Query Result']/parent::span/parent::button")
	protected WebElement seeQueryResultBtn;

	@FindBy(xpath = "//div[@class='Polaris-Frame-Loading__Level']")
	protected WebElement loadingPageBar;

	@FindBy(xpath = "//span[text()='Move on to template grid']/parent::span/parent::button")
	protected WebElement moveToTemplateGridBtn;

	@FindBy(css = ".progress-height-6")
	protected List<WebElement> progressBarTemplateAssign;

	@FindBy(xpath = "//div[@class='custom__progress--section']")
	protected List<WebElement> customProgressBar;

	@FindBy(xpath = "//div[text() = 'Kindly wait, some products are still assigning.']")
	protected WebElement productsAreStillAssigningErrorMsg;

	@FindBy(id = "productTemplates")
	protected WebElement productTemplatesHeading;

	@FindBy(xpath = "//span[text() = 'Override Existing Products']/parent::span/preceding-sibling::span/span/input")
	protected WebElement overrideExistingProductsCheckBox;

	@FindBy(id = "advanced")
	protected WebElement advancedSelectionRadioBtn;

	@FindBy(xpath = "(//div[@class='custom-selection']//select)[1]")
	protected WebElement selectByAttributeDropDown;

	@FindBy(xpath = "(//div[@class='custom-selection']//select)[2]")
	protected WebElement selectByConditionropDown;

	@FindBy(xpath = "//div[@class='custom-selection']//input[(@placeholder='Enter value')]")
	protected WebElement valueInputFieldAdvancedSelection;

	@FindBy(css = "span[class*='Polaris-Banner__text--success']")
	protected List<WebElement> productIsImportedOnAppSuccessfully;

	@FindBy(className = "progress-height-6")
	protected List<WebElement> templateAssignProgressBar;

	@FindBy(xpath = "//div[@class = 'Polaris-Grid']/div[2]//button")
	protected List<WebElement> templateNameKebabMenuList;

	@FindBy(css = "div[class='Polaris-Grid']>div h6")
	protected List<WebElement> templateNameOnGrid;

	@FindBy(xpath = "//span[text()='Warehouse Settings']/parent::legend/following-sibling::ul/li//input")
	protected List<WebElement> wareHouseCheckBox;

	@FindBy(xpath = "//span[text() = 'Enable Inventory Settings for the App']/parent::span/preceding-sibling::span/span/input")
	protected WebElement enableInventorySettingsCheckbox;

	@FindBy(xpath = "//span[contains(text(),'Handling Time is the')]/parent::div/preceding-sibling::div[@class='Polaris-Connected']/div//input")
	protected WebElement handlingTimeInputField;

	@FindBy(xpath = "//span[contains(text() , 'Continue selling when out of stock')]/parent::span/preceding-sibling::span/span/input")
	protected WebElement continueSellingCheckbox;

	@FindBy(css = "input[placeholder = '999']")
	protected WebElement setMaxInventoryInputField;

	@FindBy(xpath = "//span[contains(text() , 'Set Fixed Inventory')]/parent::span/preceding-sibling::span/span/input")
	protected WebElement setFixedInventoryCheckbox;

	@FindBy(xpath = "(//span[text() = 'Set Fixed Inventory'])[2]/parent::label/parent::div/parent::div/following-sibling::div/div/div/input")
	protected WebElement setFixedInventoryInputField;

	@FindBy(xpath = "(//span[text() = 'Set Fixed Inventory'])[2]/parent::label/parent::div/parent::div/following-sibling::div/div/div/input")
	protected List<WebElement> setFixedInventoryInputFieldList;

	@FindBy(xpath = "//span[contains(text() , 'Set Reserve Inventory')]/parent::span/preceding-sibling::span/span/input")
	protected WebElement setReserverInventoryCheckbox;

	@FindBy(xpath = "//label[text() = 'Set Reserved Inventory']/parent::div/parent::div/following-sibling::div/div/div/input")
	protected WebElement setReserverInventoryInputField;

	@FindBy(xpath = "//label[text() = 'Set Reserved Inventory']/parent::div/parent::div/following-sibling::div/div/div/input")
	protected List<WebElement> setReserverInventoryInputFieldList;

	@FindBy(xpath = "//span[contains(text() , 'Threshold Inventory')]/parent::span/preceding-sibling::span/span/input")
	protected WebElement thresoldInventoryCheckbox;

	@FindBy(xpath = "//label[text() = 'Set Threshold Inventory']/parent::div/parent::div/following-sibling::div//input")
	protected WebElement thresoldInventoryInputField;

	@FindBy(xpath = "//h2[text()='Important information']")
	protected WebElement importantInfoModal;

	@FindBy(css = "button[aria-label='Close']>span")
	protected WebElement crossBtn;

	@FindBy(css = "#template_TextField_SearchBar")
	protected WebElement searchTemplateNameInputField;

	@FindBy(xpath = "//span[text()='View products']/parent::span/parent::button")
	protected WebElement viewProductsBtnEditTempPage;

	@FindBy(css = "div[class*='Polaris-Banner--statusWarning'] div.Polaris-Banner__ContentWrapper>div")
	protected WebElement infoBannerRegardingAssignedProduct;

	@FindBy(xpath = "//span[text() = 'Discard']")
	protected WebElement discardBtn;

	@FindBy(id = "AppFrameTopBar")
	protected List<WebElement> saveBar;

	@FindBy(xpath = "//span[text() = 'Continue editing']")
	protected WebElement continueEditBtn;

	@FindBy(xpath = "//span[text() = 'Discard changes']")
	protected WebElement discardChangesBtn;

	@FindBy(xpath = "//span[text() = 'Discard changes']")
	protected List<WebElement> discardChangesBtnList;

//	-------------------------------------------<LINKING REQUIRED>---------------------------------------------------

	@FindBy(xpath = "//h1[text()='Link Amazon Products']")
	protected WebElement productLinkingPageHeading;

	@FindBy(id = "linking-required")
	protected WebElement linkingReqTab;

	@FindBy(xpath = "//div[@role = 'dialog'] //span[text()='Delete']")
	protected WebElement deleteBtnModal;

	@FindBy(xpath = "//span[text()='Successfully deleted']")
	protected WebElement deletedSuccessfullyMsg;

	@FindBy(xpath = "//div[@role = 'dialog'] //span[text()='Cancel']")
	protected List<WebElement> cancelBtnModalList;

	@FindBy(xpath = "//div[@role = 'dialog'] //span[text()='Cancel']")
	protected WebElement cancelBtnModal;

	@FindBy(xpath = "//div[@role = 'dialog'] //span[text()='Confirm']")
	protected WebElement confirmBtnModal;

	@FindBy(xpath = "//span[text()='Product upload initiated']")
	protected WebElement prodUpdateInitiatedMsg;

	@FindBy(xpath = "//span[text() = 'Confirm']")
	protected List<WebElement> confirmBtnModalList;

	@FindBy(xpath = "//span[text() = 'Product upload initiated']")
	protected WebElement prodUploadInitiatedMsg;

	@FindBy(xpath = "//span[text()='Map values']")
	protected WebElement mapValuesBtn;

	@FindBy(xpath = "//span[text()='Do it later']")
	protected WebElement doItLaterBtn;

	@FindBy(css = "div[class = 'custom-sort'] button")
	protected WebElement sortByBtn;

	@FindBy(xpath = "//ul[@class = 'Polaris-ActionList']")
	protected WebElement sortActionList;

	@FindBy(xpath = "//span[text() = 'Alphabetically (A-Z)']")
	protected WebElement a2zSortEle;

	@FindBy(xpath = "//span[text() = 'Alphabetically (Z-A)']")
	protected WebElement z2aSortEle;

	@FindBy(xpath = "//li[@role = 'presentation']//span[text() = 'Inactive']")
	protected WebElement inActiveStatusEle;

	@FindBy(xpath = "//li[@role = 'presentation']//span[text() = 'Active']")
	protected WebElement activeStatusEle;

	@FindBy(xpath = "//li[@role = 'presentation']//span[text() = 'Incomplete']")
	protected WebElement inCompleteStatusEle;

	@FindBy(xpath = "//li[@role = 'presentation']//span[text() = 'Deleted']")
	protected WebElement deleteStatusEle;

	@FindBy(xpath = "//div[@class = 'custom-sort']/div/button/span/span[1]")
	protected WebElement customSortText;

	@FindBy(xpath = "//div[@class = 'custom-sort']//span[text() = 'Incomplete']")
	protected WebElement incompleteStatusProductList;

	@FindBy(xpath = "//div[@class = 'custom-sort']//span[text() = 'Inactive']")
	protected WebElement inActiveStatusProductList;

	@FindBy(xpath = "//input[@placeholder='Search by Amazon Title, Barcode, or SKU']")
	protected WebElement searchInpFieldLinking;

	@FindBy(id = "linking-required")
	protected WebElement linkingReqBtnLinking;

	@FindBy(css = "td[class='ant-table-cell']:nth-of-type(3)>p")
	protected List<WebElement> skuOnLinkingRequiredTab;

	@FindBy(css = "button[id*='failedOrder_tour']")
	protected List<WebElement> linkBtnListLinkingReqTab;

	@FindBy(xpath = "//span[text() = 'Unlink']")
	protected List<WebElement> unlinkBtn;

	@FindBy(xpath = "//span[text() = 'Yes']")
	protected WebElement yesBtn;

	@FindBy(xpath = "//span[text() = 'Link']/parent::span/parent::button")
	protected List<WebElement> linkBtns;

	@FindBy(xpath = "//input[@placeholder='Search by Shopify Title or SKU']")
	protected WebElement searchProduct;

	@FindBy(xpath = "//a[contains(@href,'/admin/products/')]/preceding-sibling::p")
	protected List<WebElement> productNameListLinkModal;

	@FindBy(xpath = "//div[@role='dialog']//span[text() = 'Link']/parent::span/parent::button")
	protected WebElement linkBtnsLinkProductsModal;

	@FindBy(xpath = "//span[text() = 'Confirm']/parent::span/parent::button")
	protected WebElement confirmBtn;

	@FindBy(css = "div.custom-productlinking td[class*='ant-table-cell']:nth-of-type(5)>p")
	protected List<WebElement> inventoryLinkingReq;

	@FindBy(css = "div[class$='custom-productlinking'] tr[class*='ant-table-row ant-table-row-level-0']>td[class='ant-table-cell']:nth-of-type(3) p")
	protected List<WebElement> skuLinkingReq;

	@FindBy(xpath = "//span[@class = 'gridInProgressBadge']")
	protected List<WebElement> inprogressTagIsAcive;

//	-------------------------------------------</LINKING REQUIRED>---------------------------------------------------

//	-------------------------------------------<LINKED>---------------------------------------------------

	@FindBy(id = "linked")
	protected WebElement linkedTab;

//	-------------------------------------------</LINKED>---------------------------------------------------

//	------------------------ </GLOBAL PAGEOBJECTS TEMPLATE> ------------------------------------

//	-----------------------------<SHOPIFY PAGEOBJECTS>-----------------------------------------

	@FindBy(css = "div[class='Polaris-Header-Title__TitleMetadata_19y17']>div>span:nth-of-type(2)>span+span")
	protected WebElement orderStatusOnShopify;

	@FindBy(id = "appguide")
	protected WebElement appGuide;

	@FindBy(xpath = "(//button[starts-with(@aria-controls,'option-gid://shopify/ProductOption/')])[1]")
	protected WebElement editOrDoneVariantsBtnShopify;

	@FindBy(name = "optionName[0]")
	protected WebElement optionNameInput;

	@FindBy(name = "optionName[0][0]")
	protected WebElement optionValuesFirstInput;

	@FindBy(name = "optionName[0][1]")
	protected WebElement optionValuesSecondInput;

	@FindBy(css = "div[id=':r7p:'] ul li>div>div>div")
	protected List<WebElement> variantsNameList;

//	-----------------------------<SHOPIFY PAGEOBJECTS/>-----------------------------------------

//	-----------------------------<LISTING>-----------------------------------------

	@FindBy(css = "tr.ant-table-row>td:nth-of-type(5)>div>p:nth-of-type(5)>span")
	protected WebElement fulfillmentTypeVal;

	@FindBy(className = "product_title")
	protected List<WebElement> productTitleList;

	@FindBy(css = "h1[class$='Polaris-Text--headingLg']")
	protected WebElement productNameHeading;

	@FindBy(css = "div#tour_prices div.Polaris-Choice__Descriptions span[tabindex = '0']")
	protected WebElement productPriceListing;

	@FindBy(xpath = "//p[text()='Price:']/following-sibling::p")
	protected WebElement pricePopupListing;

	@FindBy(css = "tr.ant-table-row.ant-table-row-level-0>td:nth-of-type(10)>div")
	protected List<WebElement> activityFeedBtnList;

	@FindBy(css = "div[role='dialog'] tr[class='ant-table-row ant-table-row-level-0']>td:nth-of-type(14)")
	protected List<WebElement> countryListInFeedsModal;

	@FindBy(id = "InventoryCardBarcode")
	protected WebElement barcodeInputField;

	@FindBy(xpath = "(//button[contains(@class,'Polaris-Button--primary_7k9zs')])[1]")
	protected WebElement saveBtnShopify;

	@FindBy(xpath = "[text() = 'Product saved']")
	protected WebElement productSavedMsg;

	@FindBy(css = "tr[class *= 'ant-table-row ant-table-row-level-0'] input[type='checkbox']")
	protected List<WebElement> productListingCheckboxList;

	@FindBy(css = "tr[class *= 'ant-table-row ant-table-row-level-0'] td:nth-of-type(4)>div")
	protected WebElement productListingNameListing;

	@FindBy(id = "popoverActivator")
	protected WebElement selectActionsBtn;

	@FindBy(xpath = "//span[contains(.,'Upload Product') and contains(@class,'Button--removeUnderline')]")
	protected WebElement uploadProductBtnSelectActions;

	@FindBy(css = "div[role='dialog'] tr[class='ant-table-row ant-table-row-level-0']>td:nth-of-type(14)")
	protected List<WebElement> itemWidthUnitOfMeasureListInFeedsModal;

	@FindBy(css = "div[role='dialog'] tr[class='ant-table-row ant-table-row-level-0']>td:nth-of-type(15)")
	protected List<WebElement> itemHeightUnitOfMeasureListInFeedsModal;

	@FindBy(css = "div[role='dialog'] tr[class='ant-table-row ant-table-row-level-0']>td:nth-of-type(18)")
	protected List<WebElement> itemLengthUnitOfMeasureListInFeedsModal;

	@FindBy(css = "div[role='dialog'] tr[class='ant-table-row ant-table-row-level-0']>td:nth-of-type(20)")
	protected List<WebElement> variantCountryListInFeedsModal;

	@FindBy(xpath = "//span[text() = 'Edit Filters']")
	protected WebElement editFiltersBtn;

	@FindBy(css = "a[href*='myshopify.com/admin/products/']")
	protected WebElement viewOnShopifyBtn;

	@FindBy(id = "activities")
	protected WebElement activitiesHeading;

	@FindBy(xpath = "//span[text()='Sync Status']/ancestor::button")
	protected WebElement syncStatusBtn;

	@FindBy(xpath = "//div[@role='dialog']//span[text()='Proceed']/ancestor::button")
	protected WebElement proceedBtn;
	
	@FindBy(className = "product_title")
	protected WebElement nameOfProduct;

//	-----------------------------<LISTING/>-----------------------------------------

//	-----------------------------<EMAIL>-----------------------------------------

	@FindBy(css = "a[data-action='sign in']")
	protected List<WebElement> signInBtnGmail;

	@FindBy(id = "identifierId")
	protected WebElement emailInputField;

	@FindBy(xpath = "//span[text() = 'Next']")
	protected WebElement nextButton;

	@FindBy(name = "Passwd")
	protected WebElement pwdInputField;

	@FindBy(css = "input[placeholder='Search in mail']")
	protected WebElement searchInMail;

	@FindBy(css = "input[placeholder='Search in mail']")
	protected List<WebElement> searchInMailList;

	@FindBy(css = "div[data-default-label='From']")
	protected WebElement from;

	@FindBy(css = "input[placeholder='Name or email']")
	protected WebElement nameOrEmailInput;

	@FindBy(xpath = "//div[text()='Inbox']/ancestor::div[@class='yi']/following-sibling::div[@class = 'y6']/span/span")
	protected List<WebElement> emailSubject;

	@FindBy(xpath = "//span[text()='Recommended']")
	protected WebElement recommendedPlanTag;

	@FindBy(css = "a[href='https://amazon-by-cedcommerce.cifapps.com/panel/support']")
	protected WebElement hereLink;

	@FindBy(xpath = "//h6[text()='Please choose your preferred mode of communication']")
	protected WebElement modeOfComm;

	@FindBy(xpath = "//div[text()='Inbox']/ancestor::td/preceding-sibling::td[@data-tooltip='Select']/div[@role='checkbox']")
	protected List<WebElement> checkBox;

	@FindBy(xpath = "(//div[@aria-label='Delete'])[2]")
	protected WebElement delIcon;

	@FindBy(css = "input[placeholder='Search your query here...']")
	protected WebElement faqSearchInputField;

//	-----------------------------<EMAIL/>-----------------------------------------

//	-------------------<PRODUCT SETTINGS>----------------------------

	@FindBy(id = "inventory_Text_InventorySettings")
	protected WebElement headingInventorySettings;

	@FindBy(css = "div[data-node-key='inventory']")
	protected WebElement tabInventorySettings;

	@FindBy(id = "productsettings")
	protected WebElement prodSettings;

	@FindBy(id = "ordersettings")
	protected WebElement orderSettings;

	@FindBy(id = "inventory")
	protected WebElement inventorySettings;

	@FindBy(id = "inventory_Btn_Enable")
	protected WebElement enabledInventorySettingBtn;

	@FindBy(id = "inventory_Btn_Disable")
	protected WebElement disabledInventorySettingBtn;

	@FindBy(id = "inventory_Switch_FixedInventory")
	protected WebElement setFixedInventoryToggleBtn;

	@FindBy(id = "inventory_TextField_FixedInventory")
	protected WebElement setFixedInventoryInputFieldProdSettings;

	@FindBy(id = "inventory_Switch_CustomInventory")
	protected WebElement setCustomInventoryToggleBtn;

	@FindBy(id = "inventory_TextField_customInventory")
	protected WebElement setCustomInventoryInputField;

	@FindBy(id = "inventory_Switch_ReservedInventory")
	protected WebElement setReservedInventoryToggleBtn;

	@FindBy(id = "inventory_TextField_ReservedInventory")
	protected WebElement setReservedInventoryInputField;

	@FindBy(id = "inventory_Switch_ThresholdInventory")
	protected WebElement thresoldInventoryToggleBtn;

	@FindBy(id = "inventory_TextField_ThresholdInventory")
	protected WebElement thresoldInventoryInputFieldProdSettings;

	@FindBy(id = "inventory_Switch_OutStockAmazonProduct")
	protected WebElement deleteOutOfStockToggleBtn;

	@FindBy(id = "inventory_Switch_Warehouses")
	protected WebElement manageWareHouseToggleBtn;

	@FindBy(css = "button[id*='inventory_Switch_Warehouse_']")
	protected List<WebElement> wareHousesListToggleBtn;

	@FindBy(css = "div[data-node-key='product']")
	protected WebElement productDetailsBtn;

	@FindBy(id = "product_Text_ProductHeadingDetails")
	protected WebElement productHeadingDetails;

	@FindBy(xpath = "(//h6[@id='product_Text_ProductDetails']/ancestor::div[@class='Polaris-BlockStack'])[3]/preceding-sibling::button")
	protected WebElement productInfoToggleBtn;

	@FindBy(xpath = "(//h6[@id='product_Text_ProductImages']/ancestor::div[@class='Polaris-BlockStack'])[3]/preceding-sibling::button")
	protected WebElement productImagesToggleBtn;

	@FindBy(xpath = "//h6[text()='Set Handling Time']/ancestor::div[@class='Polaris-HorizontalStack']/button")
	protected WebElement setHandlingTimeToggleBtn;

	@FindBy(css = "input[placeholder='Ex: 10']")
	protected WebElement setHandlingTimeInput;

	@FindBy(xpath = "//h6[text()='Sync Product By fulfillment-type']/ancestor::div[@class='Polaris-HorizontalStack']/button")
	protected WebElement syncProdByFulfillmentTypeToggleBtn;

	@FindBy(xpath = "//p[text()='FBA (Fulfillment by Amazon)']/parent::div/preceding-sibling::button")
	protected WebElement fbaToggleBtn;

	@FindBy(id = "product_Switch_FBA_SKU")
	protected WebElement fbaSKUToggleBtn;

	@FindBy(id = "product_Switch_FBA_Barcode")
	protected WebElement fbaBarcodeToggleBtn;

	@FindBy(xpath = "//p[text()='FBM (Fulfillment by Merchant)']/parent::div/preceding-sibling::button")
	protected WebElement fbmToggleBtn;

	@FindBy(id = "product_Switch_FBM_SKU")
	protected WebElement fbmSKUToggleBtn;

	@FindBy(id = "product_Switch_FBM_Barcode")
	protected WebElement fbmBarcodeToggleBtn;

//	-------------------<PRODUCT SETTINGS/>---------------------------

	@FindBy(css = "div[aria-label='More filters']")
	protected List<WebElement> moreFiltersModal;

	@FindBy(xpath = "//span[text()='More filters']/ancestor::button")
	protected WebElement moreFiltersBtn;

	@FindBy(xpath = "//div[@aria-label = 'More filters']//span[text()='Clear all filters']/parent::span/parent::button")
	protected WebElement clearAllFilters;

	@FindBy(id = "SKUToggleButton")
	protected WebElement skuToggleButton;

	@FindBy(id = "SKUCollapsible")
	protected WebElement skuCollapsible;

	@FindBy(css = "div[id='SKUCollapsible'] select")
	protected WebElement selectSKU;

	@FindBy(css = "div[id='SKUCollapsible'] input[placeholder = 'enter value']")
	protected WebElement skuInputField;

	@FindBy(css = "div[aria-label='More filters'] button[class='Polaris-Button Polaris-Button--primary']")
	protected WebElement doneBtnMoreFiltersModal;

	@FindBy(css = "tr[class='ant-table-row ant-table-row-level-0'] input[type='checkbox']")
	protected WebElement checkboxListing;

	@FindBy(xpath = "//span[text()='Sync Image']/ancestor::button")
	protected WebElement syncImgBtn;

	@FindBy(xpath = "//span[text()='Image sync initiated']")
	protected WebElement imgSyncInitiatedMsg;

	@FindBy(xpath = "//span[text()='Sync Product(s)']/ancestor::button")
	protected WebElement syncProductBtn;

	@FindBy(xpath = "//span[text()='Product update initiated']")
	protected WebElement prodUpdateSyncInitiatedMsg;

	@FindBy(xpath = "Please wait while the Sync Status is being initiated. The App will update the Product status once these Products match with your Amazon Seller Central products.")
	protected WebElement syncStatusInitiatedMsg;

	@FindBy(xpath = "//span[text()='Synchronization with Amazon Started']/parent::div/following-sibling::div/div[contains(@class,'Polaris-ProgressBar')]")
	protected WebElement syncWithAmazonProgressBarOverview;

	@FindBy(id = "product-errors")
	protected WebElement productErrorTabInModal;

	@FindBy(id = "variant-errors")
	protected WebElement productVariantErrorTabInModal;

	@FindBy(xpath = "//div[@role='dialog']//p[text()='Product Syncing is Disabled. Please Enable it from the Settings and Try Again']")
	protected WebElement prodSyncDisabledErrMsgInModal;

	@FindBy(xpath = "//div[@role='dialog']//p[text()='Image Syncing is Disabled. Please Enable it from Settings and Try Again.']")
	protected WebElement prodImgDisabledErrMsgInModal;

	@FindBy(css = "tr[class='ant-table-row ant-table-row-level-0'] div.activity span[class$='Polaris-Text--root Polaris-Text--bodySm']")
	protected WebElement activity;

//	---------------------feed--------------------------

	@FindBy(id = "PolarisSelect1")
	protected WebElement selectStore;

	@FindBy(xpath = "//span[normalize-space()='Feeds']")
	protected WebElement feeds;

	@FindBy(css = "span[title='Feed Id / Equals']")
	protected WebElement feedIdEquals;

	@FindBy(xpath = "//span[contains(text(),'Reset')]")
	protected WebElement resetBtn;

	@FindBy(css = ".ant-select-selector")
	protected WebElement attribute;

	@FindBy(xpath = "//li[@title='Feed Id']")
	protected WebElement feedID;

	@FindBy(xpath = "//li[@title='Status']")
	protected WebElement status;

	@FindBy(xpath = "//li[@title='Type']")
	protected WebElement type;

	@FindBy(xpath = "//li[@title='Equals']")
	protected WebElement equals;

	@FindBy(css = "li[title='JSON Inventory']")
	protected WebElement jsonInventory;

	@FindBy(xpath = "//li[@title='Done']")
	protected WebElement done;

	@FindBy(xpath = "//li[@title='Product Inventory']")
	protected WebElement productInventory;

	@FindBy(xpath = "//tr[@class = 'ant-table-row ant-table-row-level-0']/td[7]/button")
	protected List<WebElement> viewBtnFeedsList;

	@FindBy(xpath = "//tr[@class = 'ant-table-row ant-table-row-level-0']/td[8]/button")
	protected List<WebElement> responseBtnFeedsList;

	@FindBy(xpath = "//div[@role='dialog']//td[contains(text(),'\"quantity\"')]")
	protected WebElement qunatityFeeds;

	@FindBy(xpath = "//tr//td[contains(@class , 'ant-table-cell')][2]")
	protected List<WebElement> feedIdList;

	@FindBy(xpath = "//tr/th[contains(text(),'SKU')]")
	protected WebElement skuFeed;

	@FindBy(xpath = "//tr/th[contains(text(),'Quantity')]")
	protected WebElement inventoryFeed;

	@FindBy(xpath = "//tr/th[contains(text(),'StatusCode')]")
	protected WebElement statusFeed;

	@FindBy(xpath = "//span[text() = 'Apply Filters']")
	protected WebElement applyFiltersBtn;

	@FindBy(xpath = "//tr[@class='ant-table-row ant-table-row-level-0']//span[@class = 'ant-checkbox']")
	protected WebElement checkbox;

//	-------------------<App Navigation Pannel>-------------------------

	@FindBy(css = "a[href*='/panel/user/dashboard']")
	protected WebElement overviewApp;

	@FindBy(css = "a[href$='/panel/user/product']")
	protected WebElement listingsApp;

	@FindBy(css = "a[href$='/panel/user/productlinkings']")
	protected WebElement productLinkingApp;

	@FindBy(css = "a[href$='/panel/user/config']")
	protected WebElement settingsApp;

	@FindBy(css = "a[href$='/panel/user/allSales']")
	protected WebElement ordersApp;

	@FindBy(css = "a[href$='/panel/user/faq']")
	protected WebElement faqApp;

//	-------------------<App Navigation Pannel/>-------------------------

//	-------------------<Failed Orders Overview>-------------------------

	@FindBy(id = "failedorders")
	protected WebElement failedOrdersSectionOverview;

	@FindBy(id = "failedOrder_tour_1")
	protected WebElement manageFailedOrdersBtnOverview;

	@FindBy(xpath = "//button[@id='failedOrder_tour_1']/parent::div/following-sibling::div/button/span/span")
	protected WebElement failedOrdersCountOverview;

//  -------------------</Failed Orders Overview>-------------------------

//	-------------------<Failed Orders PAge>-------------------------

	@FindBy(xpath = "//h1[text()='Failed Orders']")
	protected WebElement failedOrdersPageHeading;

	@FindBy(css = "img[src='/902e6225075f4dc2f9fe944f578a4ad2.png']")
	protected List<WebElement> noFoundImgList;

	@FindBy(css = "input[placeholder='Search with Amazon Order Id']")
	protected WebElement searchInputFailedOrdersPage;

	@FindBy(css = "button[class='Polaris-TextField__ClearButton']")
	protected WebElement clearSearchInputFieldBtn;

	@FindBy(xpath = "//span[text()='Archived Orders']/ancestor::button")
	protected WebElement archivedOrdersFailedOrdersPage;

	@FindBy(xpath = "//span[text()='Import Orders']/ancestor::button")
	protected WebElement importOrdersFailedOrdersPage;

	@FindBy(css = "div[id='failedOrder_tour_2'] tr[class='ant-table-row ant-table-row-level-0']>td:nth-of-type(1) input")
	protected List<WebElement> checkBoxFailedOrdersPage;

	@FindBy(xpath = "//span[text()='Select Actions']/ancestor::button")
	protected WebElement selectActionsBtnFailedOrderPage;

	@FindBy(xpath = "//span[text()='Archive Order']/ancestor::button")
	protected WebElement archiveOrderBtnSelectActionsFailedOrderPage;

	@FindBy(css = "div[id='failedOrder_tour_2'] tr[class='ant-table-row ant-table-row-level-0']>td:nth-of-type(2)")
	protected List<WebElement> amazonOrderIDFailedOrdersPage;

	@FindBy(css = "div[id='failedOrder_tour_2'] tr[class='ant-table-row ant-table-row-level-0']>td:nth-of-type(3)")
	protected List<WebElement> appStatusFailedOrdersPage;

	@FindBy(css = "div[id='failedOrder_tour_2'] tr[class='ant-table-row ant-table-row-level-0']>td:nth-of-type(4) p")
	protected List<WebElement> failureReasonFailedOrdersPage;

	@FindBy(css = "div[id='failedOrder_tour_2'] tr[class='ant-table-row ant-table-row-level-0']>td:nth-of-type(5)")
	protected List<WebElement> createdAtFailedOrdersPage;

	@FindBy(css = "div[id='failedOrder_tour_2'] tr[class='ant-table-row ant-table-row-level-0']>td:nth-of-type(6)>div>div:nth-of-type(1) span>span")
	protected List<WebElement> viewBtnOrdersPage;

	@FindBy(css = "div[id='failedOrder_tour_2'] tr[class='ant-table-row ant-table-row-level-0']>td:nth-of-type(6)>div>div:nth-of-type(2) span>span")
	protected List<WebElement> createBtnOrdersPage;

	@FindBy(css = "div[id='failedOrder_tour_2'] tr[class='ant-table-row ant-table-row-level-0']>td:nth-of-type(6)>div>div:nth-of-type(3) span>span")
	protected List<WebElement> archieveBtnOrdersPage;

//	-------------------</Failed Orders Page>-------------------------

//	-----------------------------<Archieved Orders>-------------------

	@FindBy(xpath = "//h1[text() = 'Archived Orders']")
	protected WebElement archivedOrdersHeading;

	@FindBy(xpath = "//p[text() = 'No Archived Orders Found']")
	protected List<WebElement> noArchiveOrdersFoundMsg;

	@FindBy(xpath = "//th[text() = 'Order Id']")
	protected WebElement orderIdArchivedOrdersColHeading;

	@FindBy(xpath = "//th[text() = 'Date']")
	protected WebElement dateHeadingArchivedCol;

	@FindBy(xpath = "//th[text() = 'Actions']")
	protected WebElement actionsHeadingArchivedOrders;

	@FindBy(xpath = "//span[text() = 'View']/parent::span/parent::button")
	protected List<WebElement> viewButtonArchivedOrder;

	@FindBy(xpath = "//span[text() = 'Create']/parent::span/parent::button")
	protected List<WebElement> createButtonArchivedOrder;

	@FindBy(xpath = "//span[text() = 'Archive']/parent::span/parent::button")
	protected List<WebElement> archiveButtonArchivedOrder;

//	-----------------------------</Archieved Orders>-------------------

//	-----------------------------<View Your Orders Page>-------------------------

	@FindBy(xpath = "//p[text() = 'View your order']")
	protected WebElement viewYourOrderPageHeading;

	@FindBy(xpath = "//h2[text()='Customer']")
	protected WebElement customerName;

	@FindBy(xpath = "//h2[text()='SHIPPING ADDRESS']")
	protected WebElement customerAddress;

	@FindBy(xpath = "//p[text() = 'Amazon EarliestDeliveryDate']/following-sibling::p")
	protected WebElement earliestDeliveryDate;

	@FindBy(xpath = "//p[text() = 'Amazon LatestShipDate']/following-sibling::p")
	protected WebElement latestShipDate;

	@FindBy(xpath = "//p[text() = 'Amazon EarliestShipDate']/following-sibling::p")
	protected WebElement earliestShipDate;

	@FindBy(xpath = "//p[text() = 'Amazon LatestDeliveryDate']/following-sibling::p")
	protected WebElement latestDeliveryDate;

//	-----------------------------</View Your Orders Page>-------------------------

//	----------------------------<Import Orders Modal>----------------------------

	@FindBy(xpath = "//div[@role='dialog']//h2[contains(text(),'Import Order')]")
	protected WebElement importOrdersModal;

	@FindBy(css = "div[role='dialog'] input[placeholder='Enter Amazon Order Id']")
	protected WebElement importOrderInputField;

	@FindBy(xpath = "//span[text()='Kindly fill Amazon order id to proceed importing of orders.']")
	protected WebElement fillAmazonOrderIdToProceedImportingToastMsg;

	@FindBy(xpath = "//span[text()='Order not found']")
	protected WebElement orderNotFoundToastMsg;

//	----------------------------</Import Orders Modal>----------------------------

	@FindBy(xpath = "//h1[text() = 'Create Order']")
	protected WebElement createOrderPageHeading;

	@FindBy(css = "a[href*='https://sellercentral.amazon.in/orders-v3/order']")
	protected WebElement viewOnAmazonLinkCreateOrder;

	@FindBy(id = "failedOrder_tour_5")
	protected WebElement linkBtnCreateOrdersPage;

	@FindBy(xpath = "//span[text()='Create Order']/ancestor::button")
	protected WebElement createOrderBtnCreateOrdersPage;

	@FindBy(xpath = "//h2[text()='Order created successfully']")
	protected WebElement orderCreatedSuccessfully;

//	----------------------------</Order Settings>----------------------------	

	@FindBy(css = "div[id$='tab-order']")
	protected WebElement orderTabBtn;

	@FindBy(css = "div[id$='tab-shipment']")
	protected WebElement shipmentTabBtn;

	@FindBy(id = "order_Btn_Enable")
	protected WebElement enableOrdersBtn;

	@FindBy(id = "order_Btn_Disable")
	protected WebElement disableOrdersBtn;

	@FindBy(id = "order_Switch_CreateOrderForNonExistingProducts")
	protected WebElement createOrdersForNonExistingProductsTogglebutton;

	@FindBy(id = "order_Switch_AmazonItemTax")
	protected WebElement amazonItemTaxOnShopifyTogglebutton;

	@FindBy(id = "order_Switch_AmazonShippingTax")
	protected WebElement amazonShippingTaxOnShopifyTogglebutton;

	@FindBy(xpath = "(//h6[text()='Sync FBA orders on Shopify']/ancestor::div[@class='Polaris-BlockStack'])[5]/preceding-sibling::button")
	protected WebElement syncFBAOrdersOnShopifyTogglebutton;

	@FindBy(id = "order_Switch_SyncCPFNumber")
	protected WebElement syncCPFNumberTogglebutton;
	
	@FindBy(id="order_Switch_OrderSourceIdentifier")
	protected WebElement orderSourceIdentifierTogglebutton;
	
	@FindBy(id="order_TextField_OrderSourceIdentifier")
	protected WebElement orderSourceIdentifierInputField;

	@FindBy(id = "order_Switch_AddTagsInOrder")
	protected WebElement addTagsInOrderTogglebutton;
	
	@FindBy(id = "order_Checkbox_AmazonOrderID")
	protected WebElement amazonOrderIDTagCheckbox;
	
	@FindBy(id = "order_Checkbox_SellerID")
	protected WebElement sellerIDTagCheckbox;
	
	@FindBy(id = "order_Checkbox_Amazon")
	protected WebElement amazonTagCheckbox;
	
	@FindBy(id = "order_Checkbox_SetCustomTag")
	protected WebElement setCustomTagCheckbox;
	
	@FindBy(css = "input[placeholder = \"Press 'Enter' to add tag\"]")
	protected WebElement setCustomTagInputField;

	@FindBy(id = "order_Switch_ModifyShopifyOrderName")
	protected WebElement modifyShopifyOrderNameTogglebutton;
	
	@FindBy(id="order_TextField_Prefix")
	protected WebElement modifyShopifyOrderPrefixInputField;
	
	@FindBy(id="order_TextField_Suffix")
	protected WebElement modifyShopifyOrderSuffixInputField;

	@FindBy(css = "div[id$='tab-cancelRefund']")
	protected WebElement cancelRefundTabBtn;

	@FindBy(css = "div[id$='tab-cancelRefund']")
	protected List<WebElement> cancelRefundTabBtnList;

	@FindBy(id = "cancellationAndRefund_Switch_SyncCancelledOrdersOnShopify")
	protected WebElement syncCancelledOrdersOnShopifyToggleBtn;

	@FindBy(id = "cancellationAndRefund_Switch_SyncRefundedOrdersOnShopify")
	protected WebElement syncRefundedOrdersOnShopifyToggleBtn;

	@FindBy(id = "cancellationAndRefund_Switch_RefundReasons")
	protected WebElement mapRefundReasonsToggleBtn;

	@FindBy(id = "cancellationAndRefund_Btn_Shopify_0")
	protected WebElement shopifySellerRefundSearchBtn;

	@FindBy(css = "input[placeholder='Search or type shopify refund reason']")
	protected WebElement shopifySellerRefundInputField;

	@FindBy(css = "li[aria-label='Customer Return']")
	protected WebElement customerReturnOption;

	@FindBy(css = "li[aria-label='Add custom']")
	protected WebElement addCustomOption;

	@FindBy(id = "cancellationAndRefund_Btn_Amazon_0")
	protected WebElement amazonCustomerCancelSearchBtn;

	@FindBy(css = "input[placeholder='Search or type amazon refund reason']")
	protected WebElement amazonCustomerCancelInputField;

	@FindBy(css = "li[aria-label='CustomerCancel']")
	protected WebElement customerCancelOption;
	
	@FindBy(id="accounts")
	protected WebElement accountsTabSettings;
	
	@FindBy(xpath = "//p[contains(text(),'Amazon ID')]")
	protected WebElement amazonID;

//	------------------------<Failed Cancel Order/>---------------------------------

	@FindBy(id = "failedorders")
	protected WebElement failedCanceledOrdersSectionOverview;

	@FindBy(xpath = "//span[text()='Manage Failed Canceled Orders']/ancestor::button")
	protected WebElement manageFailedCanceledOrdersOverview;

	@FindBy(css = "div.custom-error-badge:nth-of-type(2)>div>button>span>span")
	protected WebElement failedCanceledOrdersCountOverview;

	@FindBy(xpath = "//h1[text()='Failed Canceled Orders']")
	protected WebElement failedCanceledOrdersHeading;

	@FindBy(css = "input[placeholder='Search with Shopify Order Id']")
	protected WebElement searchFailedCanceledOrdersInputField;

	@FindBy(xpath = "//span[text() = 'Cancel Order']/ancestor::button")
	protected List<WebElement> cancelOrderBtnsFailedCanceledOrders;

	@FindBy(css = "div[role='dialog'] select")
	protected WebElement selectBtnFailedCanceledOrdersModal;

	@FindBy(xpath = "//span[text()='Cancellation done successfully']")
	protected WebElement cancellationDoneToastMsg;
	
	@FindBy(xpath = "//span[text() = 'Some information is missing or wrong, kindly check them before proceeding.']")
	protected WebElement someInfoMissingErrMsg;

//	------------------------<Failed Cancel Order/>---------------------------------
	
//	------------------------<Shopify Orders page />---------------------------------
	
	@FindBy(css = "button[aria-label = 'Edit']")
	protected List<WebElement> editBtnsOrderPage;
	
	@FindBy(xpath = "//span[text() = 'Taxes']/parent::div")
	protected List<WebElement> taxLabelShopifyOrderPage;
	
	@FindBy(xpath = "//span[contains(text() , 'ItemTax')]/parent::div/following-sibling::div/span[2]")
	protected WebElement itemTaxShopifyOrderPage;
	
	@FindBy(xpath = "//span[contains(text() , 'ShippingTax')]")
	protected List<WebElement> shippingTaxLabelShopifyOrderPage;
	
	@FindBy(xpath = "//span[contains(text() , 'ShippingTax')]/parent::div/following-sibling::div/span[2]")
	protected WebElement shippingTaxShopifyOrderPage;
	
	@FindBy(xpath = "//p[text()='channel']/span")
	protected WebElement orderSrcIdentifierOrChanelNameShopifyOrderPage;
	
	@FindBy(css = "h1.Polaris-Header-Title")
	protected WebElement shopifyOrderTitle;
	
	
//	------------------------<Shopify Orders page/>---------------------------------
	
//	------------------------<EDIT Product page/>---------------------------------
	
	@FindBy(css = "div[id='sku'] input[value='custom'][type='radio']")
	protected WebElement setCustomSKURadioBtnEditPage;
	
	@FindBy(css = "div[id='sku'] [type='text']")
	protected WebElement setCustomSKURadioInputFieldEditPage;
	

}
