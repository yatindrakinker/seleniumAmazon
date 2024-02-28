package com.ami.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ami.pageobjects.reusableclasses.ReusableMethodsOR;

public class OnboardingPageOR extends ReusableMethodsOR{

	@FindBy(xpath = "//span[text() = 'Sales channels']")
	WebElement salesChannelButton;

	@FindBy(xpath = "//div[@class = 'FSndY']/div")
	List<WebElement> installedChannelList;

	@FindBy(id = "subscription")
	WebElement subscriptionHeading;

//	-------------------<Shopify Partners Page>------------------------

	@FindBy(xpath = "//span[text() = 'Add store']")
	WebElement addStoreButton;

	@FindBy(xpath = "//span[text() = 'Create development store']")
	WebElement createDevStoreBtn;

	@FindBy(xpath = "//input[@value='test_store']")
	WebElement createStoreToTestRadioBtn;

	@FindBy(xpath = "//span[text() = 'Store Name']/parent::label/parent::div/parent::div/following-sibling::div//input")
	WebElement storeNameInputField;

	@FindBy(xpath = "//div[text() = 'myshopify.com']/preceding-sibling::input")
	WebElement storeUrlInputField;

	@FindBy(css = "a[href*='/settings']")
	WebElement settingsShopify;

	@FindBy(css = "a[href*='/settings/apps']")
	WebElement appAndSalesChannelBtn;

	@FindBy(xpath = "//span[text() = 'Shopify App Store']")
	WebElement shopifyAppStore;

	@FindBy(xpath = "//h2[normalize-space()='Get the very best apps for your store']")
	WebElement headingShopifyStore;

	@FindBy(xpath = "(//input[@placeholder='Search apps, guides, and more'])[1]")
	WebElement searchAppInputField;

	@FindBy(xpath = "(//input[@placeholder='Search apps, guides, and more'])[1]")
	List<WebElement> searchAppInputFieldList;

	@FindBy(xpath = "//a[normalize-space()='CedCommerce Amazon Channel']")
	WebElement cedCossAmazonChannelApp;

	@FindBy(css = "button[data-partner-tracking-label='amazon-by-cedcommerce']")
	WebElement installButton;

	@FindBy(xpath = "(//button[normalize-space() = 'Add sales channel'])[1]")
	WebElement addSalesChannelBtn;

	@FindBy(xpath = "//input[@placeholder='Search']")
	WebElement searchCountryInputField;

	@FindBy(xpath = "//li[@aria-label='India']")
	WebElement india;

	@FindBy(xpath = "//p[contains(text(),'By using our services')]/parent::div/parent::span/parent::span/preceding-sibling::span//input")
	WebElement agreeToTermsCheckbox;

	@FindBy(xpath = "//span[text()='Primary access to Amazon Seller Central account.']/parent::span/preceding-sibling::span//input")
	WebElement primaryAccessCheckbox;

	@FindBy(xpath = "//span[text()='A seller account that is not suspended, inactive or in vacation mode.']/parent::span/preceding-sibling::span//input")
	WebElement acntIsNotInactiveCheckbox;

	@FindBy(xpath = "//p[contains(text(),'Amazon Professional account with updated')]/parent::div/parent::span/parent::span/preceding-sibling::span//input")
	WebElement professionalAcntCheckbox;

	@FindBy(css = ".dashed")
	WebElement paymentMethodLabel;

	@FindBy(xpath = "//div[@role='tooltip' and text()='The Credit / Debit Card or other mode of payment associated with your Amazon Seller account must be active & in running state.']")
	WebElement tooltipPaymentMethod;

	@FindBy(xpath = "//span[text()='Connect only a professional seller account, not Amazon Pay and other account types.']/parent::span/preceding-sibling::span//input")
	WebElement connectingProfessionalSellerAcntCheckbox;

	@FindBy(css = "a[href='https://amazon-sales-channel-app-backend.cifapps.com/Cedcommerce_Privacy_Statement.pdf']")
	WebElement termsConditionLink;

	@FindBy(xpath = "//span[contains(text(),'Connect')]/parent::span/parent::button")
	WebElement connectSellerAcntBtn;

	@FindBy(css = "a[href*='https://docs.cedcommerce.com/shopify/multi-account-amazon-cedcommerce/?section']")
	WebElement cedLinkFooter;

	@FindBy(xpath = "//div[@class = 'Polaris-FooterHelp']//button[text()='Get Support']")
	WebElement getSupportLinkFooter;

	@FindBy(xpath = "//h3[text()='WhatsApp']")
	WebElement whatsAppGetSupportPage;

	@FindBy(css = "a[href='https://calendly.com/scale-business-with-cedcommerce/shopify-amazon-integration']")
	WebElement bookCallLinkFooter;
	
	@FindBy(css = "div[class='Polaris-FooterHelp'] a[href='https://amazon-sales-channel-app-backend.cifapps.com/Data_Privacy_Rights.pdf']")
	WebElement dataPrivacyRightsLinkFooter;

	@FindBy(xpath = "//h1[normalize-space()='Shopify Amazon Integration']")
	WebElement calendlyHeading;

	@FindBy(xpath = "//p[text()='Your account connection was unsuccessful.']")
	WebElement connectionUnsuccessfulBanner;

	@FindBy(xpath = "//p[contains(text(),'do not have Primary Access to the Amazon Seller Central Account.')]")
	WebElement reason1;

	@FindBy(xpath = "//p[contains(text(),'You are trying to log in using an Amazon Pay account.')]")
	WebElement reason2;

	@FindBy(xpath = "//p[contains(text(),'Your Seller Account is either Suspended, Inactive, or in Vacation mode.')]")
	WebElement reason3;

	@FindBy(xpath = "//p[contains(text(),\"You don't have an Amazon Professional Account\")]")
	WebElement reason4;

	@FindBy(css = "a[href*='https://docs.cedcommerce.com/shopify/multi-account-amazon-cedcommerce/?section=']")
	WebElement clickHereLink;

	@FindBy(xpath = "//span[text()='Retry Connecting']/parent::span/parent::button")
	WebElement retryConnectingBtn;

//	-------------------</Shopify Partners Page>------------------------
//	-------------------<App Navigation Pannel>-------------------------
	@FindBy(css = "a[href*='/panel/user/dashboard']")
	WebElement overviewApp;

	@FindBy(css = "a[href$='/panel/user/product']")
	WebElement listingsApp;

	@FindBy(css = "a[href$='/panel/user/productlinkings']")
	WebElement productLinkingApp;

	@FindBy(css = "a[href$='/panel/user/config']")
	WebElement settingsApp;
	
	@FindBy(css = "a[href$='/panel/user/allSales']")
	WebElement ordersApp;

	@FindBy(css = "a[href$='/panel/user/faq']")
	WebElement faqApp;

	@FindBy(xpath = "//h2[@class='Polaris-Text--root Polaris-Text--headingMd']")
	WebElement onboardingStep;

	@FindBy(css = ".user-card")
	List<WebElement> userCard;

	@FindBy(xpath = "//div[@class='user-card__email']")
	List<WebElement> userCardEmail;

//	-------------------</App Navigation Pannel>------------------------

//	----------------------------<App page>-----------------------------

//	@FindBy(xpath = "//p[text() = 'Choose a suitable country']/span[contains(normalize-space(),'*')]")		// live
	@FindBy(xpath = "//label[contains(text(),'Country')]")
	WebElement choseCountryMandatoryField;

	@FindBy(xpath = "//div[@class='Polaris-Combobox__Listbox']//ul/li")
	List<WebElement> countryList;

	@FindBy(xpath = "//span[text()='Some information is missing or wrong, kindly check them before proceeding.']")
	WebElement someInfoMissingErrMsg;

	@FindBy(xpath = "//div[text()='Kindly select your country']")
	WebElement kindlySelectCountry;

	@FindBy(xpath = "//label[text()='Country']/parent::div/parent::div/following-sibling::div//input")
	WebElement countryInputField;

//	-----------------------------</App page>----------------------------
//	------------------------<Amazon>------------------

	@FindBy(id = "ap_email")
	WebElement userInpFieldAmz;

	@FindBy(id = "ap_email")
	List<WebElement> userInpFieldAmzList;

	@FindBy(id = "ap_password")
	WebElement userPasswordInpFieldAmz;

	@FindBy(id = "cvf-input-code")
	WebElement otpInpFieldAmz;

	@FindBy(xpath = "//div[normalize-space()='pankaj@cedcommerce.com']")
	WebElement amzUser;

	@FindBy(id = "auth-signin-button")
	WebElement twoFASigninBtn;

	@FindBy(xpath = "(//div[text() = 'sellernext'])[1]")
	WebElement sellerNextBtnAmz;

	@FindBy(xpath = "(//div[text() = 'sellernext'])[1]")
	List<WebElement> sellerNextBtnAmzList;

	@FindBy(className = "picker-switch-accounts-button")
	WebElement selectAcntBtnAmz;

	@FindBy(className = "picker-switch-accounts-button")
	List<WebElement> selectAcntBtnAmzList;

	@FindBy(id = "sc-search-field")
	WebElement searchInpFieldAmz;

	@FindBy(xpath = "//div[@aria-labelledby='katal-id-0']")
	WebElement checkbox;

	@FindBy(className = "primary")
	WebElement confirmBtnAmz;

	@FindBy(id = "ap_password")
	WebElement passwordInpFieldAmz;

	@FindBy(id = "signInSubmit")
	WebElement signInBtnAmz;

	@FindBy(id = "continue")
	List<WebElement> sendOTPBtn;

	@FindBy(id = "cvf-submit-otp-button-announce")
	WebElement submitOTPBtn;

//	------------------------</Amazon>------------------

//	--------------------<SecondStep>---------------------

	@FindBy(xpath = "//h2[text() = 'Account Connection Successful']")
	WebElement acntConnectionSuccessfulMsg;

	@FindBy(xpath = "//h2[text()='Create an Account Name']/span")
	WebElement createAnAcntNameMandatory;

	@FindBy(xpath = "//input[@placeholder='Ex: storename_country']")
	WebElement acntNameInputField;
	
	@FindBy(css = "img[src='/60f6af5f9f0a263ebe076f58b3c697bc.png']")
	WebElement imgCC;

	@FindBy(xpath = "//div[text()='Account name is missing, Please enter account name.']")
	WebElement acntNameMissingErrMsg;

	@FindBy(xpath = "//div[text() = '0/32']")
	WebElement nameLimit;

	@FindBy(xpath = "//div[text() = 'Account name cannot exceed 32 characters.']")
	List<WebElement> exceedLimitErrMsg;

	@FindBy(xpath = "//span[contains(text(),'Next')]/parent::span/parent::button")
	WebElement saveAndNextBtn;

//	--------------------</SecondStep>-----------------------------

//	--------------------<ThirdStep>---------------------------------

	@FindBy(id = "new_seller")
	WebElement newAmzSellerRadioBtn;

	@FindBy(id = "new_seller")
	List<WebElement> newAmzSellerRadioBtnList;

	@FindBy(id = "existing_seller")
	WebElement existingSellerRadioBtn;

	@FindBy(xpath = "//p[text()='Sync Status Preference']")
	WebElement syncStatusPreferenceHeading;

	@FindBy(id = "existing_seller")
	WebElement alreadyHaveListingOnAmzCheckbox;

	@FindBy(id = "product_Switch_FBM_SKU")
	WebElement skuCheckBox;

	@FindBy(id = "product_Switch_FBM_Barcode")
	WebElement barcodeCheckBox;

	@FindBy(id="sync_status_withError")
	WebElement errMsgWhenSKUAndBarcodeChecboxInNotSelected;

	@FindBy(xpath = "//span[contains(text(),'FBM')]/parent::span/preceding-sibling::span/span/input")
	WebElement fbmCheckBox;

	@FindBy(xpath = "//span[contains(text(),'FBA')]/parent::span/preceding-sibling::span/span/input")
	WebElement fbaCheckBox;

	@FindBy(xpath = "//p[text()='Configure Sync Preferences']")
	WebElement configSyncPreferences;

	@FindBy(xpath = "//span[text()='Price']/parent::span/preceding-sibling::span/span/input")
	WebElement priceCheckBox;

	@FindBy(xpath = "//span[text()='Inventory']/parent::span/preceding-sibling::span/span/input")
	WebElement inventoryCheckBox;

	@FindBy(xpath = "//span[text()='Product Details']/parent::span/preceding-sibling::span/span/input")
	WebElement productDetailsCheckBox;

	@FindBy(xpath = "//span[text()='Images']/parent::span/preceding-sibling::span/span/input")
	WebElement imagesCheckBox;

	@FindBy(xpath = "//div[contains(@class,'describe__field ')][1]")
	WebElement describeFieldManufacturerBtn;

	@FindBy(xpath = "//span[text()='Manufacturer']/ancestor::div[contains(@class,'describe__field ')]")
	WebElement manufacturerBtn;

	@FindBy(css = "input[placeholder='enter brand name']")
	WebElement brandNameInputField;
	
	@FindBy(css = "input[placeholder='Type your Brand name']")
	WebElement typeBrandNameInputField;

	@FindBy(xpath = "//div[text()='Kindly enter your brand name']")
	WebElement errWhenBrandInputFieldIsEmpty;

//	@FindBy(xpath = "//span[text()='Dropshipper']/parent::span/preceding-sibling::span/span/input")
	@FindBy(xpath = "//span[text()='Drop shipper']/ancestor::div[contains(@class,'describe__field ')]")
	WebElement dropShipperCheckBox;

	@FindBy(xpath = "//span[text()='Retailer']/ancestor::div[contains(@class,'describe__field ')]")
	WebElement retailerCheckBox;

	@FindBy(xpath = "//span[text()='Reseller']/ancestor::div[contains(@class,'describe__field ')]")
	WebElement resellerDetailsCheckBox;

	@FindBy(css = "div.describe__field:last-of-type")
	WebElement describeOtherBtn;

	@FindBy(xpath = "//span[text()='Others']/ancestor::div[contains(@class,'describe__field ')]")
	WebElement otherBtn;
	
	@FindBy(css = "div.describe__field:nth-of-type(2)")
	WebElement dropshipperBtn;

	@FindBy(xpath = "//label[text()='Specify your business type']/parent::div/parent::div/following-sibling::div//input")
	WebElement otherBusinessInputField;

	@FindBy(xpath = "//div[text()='Kindly enter your business type']")
	WebElement errMsgWhenOtherInputFieldIsEmpty;

	@FindBy(xpath = "//span[text()='Complete Account Setup']/parent::span/parent::button")
	WebElement completeAcntSetupBtn;

	@FindBy(xpath = "//p[normalize-space()='Select atleast any one to proceed.']")
	WebElement selectAtleastOneToProceedErrMsg;

	@FindBy(xpath = "//div[@role='dialog']//h2[normalize-space()='Attention']")
	List<WebElement> attentionModal;

	@FindBy(xpath = "//div[@role='dialog']//span[normalize-space()='If you are a New Amazon Seller, make sure:']")
	WebElement infoForNewSellers;

	@FindBy(xpath = "//a[@href = 'https://brandservices.amazon.com/brandregistry']")
	WebElement manageItHereLink;

	@FindBy(xpath = "//a[@href = 'https://sellercentral.amazon.com/gtinx']")
	WebElement referenceLink;

	@FindBy(xpath = "//span[text() = 'Ok, I understand']/parent::span/parent::button")
	WebElement okIUnderstandBtn;
	
	@FindBy(css = "input[placeholder='Type your Brand name']")
	List<WebElement> brandNameInputFieldList;
	
	@FindBy(tagName = "select")
	WebElement brandRegisterSelect;
	
	@FindBy(css = "input[value ='not_applied']")
	WebElement notAppliedYetRadioBtn;
	
	@FindBy(css = "input[value ='pending_approval']")
	WebElement pendingApprovalRadioBtn;
	
	@FindBy(css = "input[value ='willing_to_sell_generic']")
	WebElement willingToSellGenericRadioBtn;
	
	@FindBy(id="welcome")
	WebElement overviewPage;

	protected String amazonBrandRegister = "https://brandservices.amazon.com/brandregistry";

	protected String amazonBrandTitle = "Amazon Brand Registry: Help Protect Your Brand on Amazon";

	protected String amazonSignInUrl = "https://sellercentral.amazon.com/ap/signin";

	// --------------------</ThirdStep>---------------------------------

//	-------------------------------<Pricing Plan>-------------------------------

	@FindBy(xpath = "//h1[text()='Pricing Plans']")
	WebElement pricingPlansSectionHeading;

	@FindBy(xpath = "//div[@class='Custom-Pricing--Card']//div[@class='Custom-Alpha-Card__Section']/div/div[3]/p")
	List<WebElement> pricingPlansNameList;
	
	@FindBy(css = "div[class='slick-slide slick-active']")
	protected WebElement customPlanCard;
	
	@FindBy(xpath = "//span[text()='Contact Now']/ancestor::button")
	protected WebElement contactNowBtn;
	
	@FindBy(css = ".Custom__plan-switcher>button")
	protected WebElement planSwitcherBtn;
	
	@FindBy(xpath = "//div[@role='dialog']//h2[text()='Selected plan details']")
	protected WebElement selectedPlanDetails;
	
	@FindBy(xpath = "//div[@role='dialog']//span[text()='Confirm and proceed']")
	protected WebElement confirmAndProceedBtn;

	@FindBy(className = "Custom__Button--FreePlan")
	WebElement continueWithFreeBtn;

	@FindBy(xpath = "//span[text() = 'Buy Now']/parent::span/parent::button")
	List<WebElement> buyNowBtn;

	@FindBy(xpath = "//button[text()='Next']")
	WebElement nextPricingPlanBtn;

	@FindBy(xpath = "//button[text()='Previous']")
	WebElement prevPricingPlanBtn;

	@FindBy(xpath = "//div[@class='slick-track']/div")
	List<WebElement> pricingPlanCards;

	@FindBy(xpath = "//ul[@class='slick-dots slick-dots-bottom']")
	WebElement carouselIndicator;

	@FindBy(xpath = "//h1[text()='Approve subscription']")
	WebElement headingApproveSubscriptionPage;

	@FindBy(xpath = "//button[text()='Get Support']")
	WebElement getSupportBtn;

	@FindBy(xpath = "//p[text()='Frequently Asked Questions']")
	WebElement faqHeadingPricingPlan;

	@FindBy(xpath = "//div[@class='Custom-Spacing']/following-sibling::div/div[@class='Polaris-Box']//div[@id='basic-collapsible']")
	List<WebElement> faqs;

	@FindBy(xpath = "//span[text()='View all questions']/parent::span/parent::button")
	WebElement viewAllQuestions;

	@FindBy(xpath = "//h1[text()='FAQ & Troubleshoot']")
	WebElement faqAndTroubleShootPageHeading;

	@FindBy(xpath = "(//h2[text()='Recent Activities']/parent::div/following-sibling::div/div[2]/div[2]/div[2]/span)[1]")
	WebElement accountConnectionCountry;

//	-------------------------------</Pricing Plan>-------------------------------

//	-------------------------------<Overview>-------------------------------

	@FindBy(xpath = "//span[text()='Current Plan:']/following-sibling::span//u")
	WebElement activePlan;

	@FindBy(xpath = "//h1[text() = 'Overview']")
	WebElement overViewPageHeading;

	@FindBy(xpath = "//span[text() = 'View all plans']")
	WebElement viewAllPlansButton;

	@FindBy(xpath = "//h1[text() = 'Pricing Plans']")
	WebElement pricingPlanPageHeading;

	@FindBy(xpath = "//p[text() = 'Free']/following-sibling::span/span[text()='Current Plan']")
	WebElement freePlanIsActiveOnPricingPlan;

	@FindBy(xpath = "//div[@data-index = '0']//span[text()='Current Plan']/parent::span/parent::button")
	WebElement freePlanContainsCurrentPlanBtn;

//	-------------------------------</Overview>-------------------------------
}
