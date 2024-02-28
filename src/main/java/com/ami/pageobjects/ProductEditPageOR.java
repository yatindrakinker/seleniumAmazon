package com.ami.pageobjects;

import java.util.List;

import javax.xml.xpath.XPath;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ami.pageobjects.reusableclasses.ReusableMethodsOR;

public class ProductEditPageOR extends ReusableMethodsOR {

	@FindBy(id = "AppFrameLoadingBar")
	WebElement loadingPageBar;

	@FindBy(xpath = "//h1[contains(text() ,'Listing')]")
	WebElement listingPageHeading;

	@FindBy(xpath = "(//span[@class='Polaris-Tag Polaris-Tag--removable'])[2]//button")
	WebElement removeProd;

	@FindBy(xpath = "//div[@class = 'ant-spin ant-spin-spinning']")
	WebElement loader;

	@FindBy(css = "tr[class = 'ant-table-row ant-table-row-level-0']>td:last-of-type button")
	List<WebElement> kebabMenuListListing;

	@FindBy(xpath = "//button[@role = 'menuitem']//span[text()='Edit Product']")
	WebElement editProduct;

	@FindBy(xpath = "//a[text() = 'View On Shopify']")
	List<WebElement> viewOnShopify;

	@FindBy(className = "product-image")
	WebElement productImage;

	@FindBy(xpath = "//span[text() = 'Link Products']")
	WebElement linkProductsBtn;

	@FindBy(xpath = "//div[@class = 'Polaris-Frame-Loading' and @role = 'progressbar']")
	WebElement progressBar;

	@FindBy(xpath = "//button[@role = 'menuitem']//span[text() = 'Amazon Lookup']")
	WebElement amazonLookup;

	@FindBy(xpath = "//h2[text() ='Attention']")
	List<WebElement> attention;

	@FindBy(xpath = "//span[text() ='Close']")
	WebElement closeBtn;

	@FindBy(css = "h1[class='Polaris-Text--root Polaris-Text--headingLg']")
	WebElement productEditHeading;

	@FindBy(css = "img[alt='main image']")
	WebElement productImg;

	@FindBy(css = "a[href*='.myshopify.com/admin/products']")
	WebElement viewOnShopifyLink;

	@FindBy(xpath = "//span[text() = 'Offer Listing']")
	WebElement labelOfferListings;

	@FindBy(id = "offer_listing")
	WebElement offerListingCheckbox;

	@FindBy(xpath = "//span[text() = 'New Listing']")
	WebElement labelNewListing;

	@FindBy(id = "new_listing")
	WebElement newListingCheckbox;

	@FindBy(xpath = "//span[text() = 'Proceed']")
	WebElement proceedBtn;

	@FindBy(xpath = "//span[text() = 'Amazon listings.']")
	WebElement amazonListingLink;

	@FindBy(xpath = "//span[text() = 'Set the same Product Title for Shopify and Amazon']")
	WebElement setSameProductTitleForShopifyAndAmazon;

	@FindBy(xpath = "//span[text() = 'Set the same Product Title for Shopify and Amazon']/parent::span/preceding-sibling::span//input")
	WebElement setSameProductTitleForShopifyAndAmazonCheckBox;

	@FindBy(css = "input[id='tour_choice']")
	WebElement setCustomProductTitleForAmazonRadioBtn;

	@FindBy(xpath = "//span[text() = 'Set a Custom Product Title for Amazon']")
	WebElement setCustomProductTitleForLabel;

	@FindBy(xpath = "//legend[text() = 'Title']/parent::fieldset/ul/li/div/label/following-sibling::div//input")
	WebElement setCustomTitleInpField;

	@FindBy(xpath = "//div[@class = 'Polaris-Frame-ContextualSaveBar_14m7v']")
	List<WebElement> contextualSaveBar;

	@FindBy(xpath = "//span[text() = 'Discard']/parent::span/parent::button")
	WebElement discardBtn;

	@FindBy(xpath = "//div[@class = 'Polaris-Modal-Dialog__Modal_2v9yc']")
	List<WebElement> discardModal;

	@FindBy(xpath = "//button[@aria-label = 'Close']/span")
	WebElement crossButton;

	@FindBy(xpath = "//h2[text() = 'Discard all unsaved changes']")
	WebElement discardModalHeading;

	@FindBy(xpath = "//span[text() = 'Continue editing']")
	WebElement continueEditBtn;

	@FindBy(xpath = "//span[text() = 'Discard changes']")
	WebElement discardChangesBtn;

	@FindBy(xpath = "//span[text() = 'Save']")
	WebElement saveBtn;

	@FindBy(xpath = "//span[text() = 'Set a Custom Product Title for Amazon']")
	WebElement setCustomProductTitleForAmazonLabel;

	@FindBy(xpath = "//span[text() = 'Description']")
	WebElement descriptionSectionHeading;

	@FindBy(xpath = "//span[text() = 'Set the same Product Description for Shopify and Amazon']")
	WebElement setSameProductDescriptionForShopifyAndAmazon;

	@FindBy(xpath = "//span[text() = 'Set the same Product Description for Shopify and Amazon']/parent::span/preceding-sibling::span/span/input")
	WebElement setSameProductDescriptionForShopifyAndAmazonCheckbox;

	@FindBy(xpath = "//span[text() = 'Set a Custom Product Description for Amazon']/parent::span/preceding-sibling::span/span/input")
	WebElement setCustomProductDescriptionForAmazonCheckBox;

	@FindBy(className = "cke_wysiwyg_frame")
	WebElement iframeDescriptionBox;

	@FindBy(id = "cke_editor1")
	WebElement descriptionBox;

	@FindBy(className = "cke_toolbar")
	WebElement descriptionBoxToolBar;

	@FindBy(xpath = "//html[@dir='ltr']//body/p")
	WebElement descriptionBoxInputField;

	@FindBy(xpath = "//span[text() = ' 2000']")
	WebElement maxWordLimit;

	@FindBy(xpath = "//span[contains(text() , ' /') and @style = 'color: black;']")
	WebElement forwardSlash;

	@FindBy(xpath = "//span[text() = 'Handling Time']")
	WebElement handlingTimeHeading;

	@FindBy(xpath = "//span[text() = 'Use the same Handling Time as in the Product Template']")
	WebElement useTheSameHandlingTimeAsInTheProductTemplate;

	@FindBy(xpath = "//span[text() = 'Use the same Handling Time as in the Product Template']/parent::span/preceding-sibling::span/span/input")
	WebElement useTheSameHandlingTimeAsInTheProductTemplateCheckBox;

	@FindBy(xpath = "//div[text() = 'days']/preceding-sibling::input")
	WebElement useTheSameHandlingTimeAsInTheProductTemplateInpField;

	@FindBy(xpath = "//span[text() = 'Set a Custom Handling Time']")
	WebElement setCustomHandlingTimeLabel;

	@FindBy(xpath = "//span[text() = 'Set a Custom Handling Time']/parent::span/preceding-sibling::span/span/input")
	WebElement setCustomHandlingCheckbox;

	@FindBy(xpath = "//div[text() = 'days']/preceding-sibling::input")
	WebElement customHandlingTimeInputField;

	@FindBy(xpath = "//div[contains(@class,'Polaris-Frame-Toast Polaris-Frame-Toast-')]")
	WebElement toastMsg;

	@FindBy(xpath = "//div[text()='Saved successfully']")
	WebElement savedSuccessfullyToastMsg;

	@FindBy(xpath = "//div[text() = 'Inventory fulfillment latency is missing or wrong, kindly check it before proceeding.']")
	WebElement infoIsMissingErrToast;

	@FindBy(xpath = "//div[text() = 'Required Attributes are missing or wrong, kindly check it before proceeding.']")
	WebElement reqAttributesMissingErrToast;

	@FindBy(xpath = "//div[text() = 'Some information is missing or wrong, kindly check them before proceeding.']")
	WebElement someInfoMissingErrToast;

	@FindBy(xpath = "//div[text() = 'Description is missing or wrong, kindly check it before proceeding.']")
	WebElement descriptionMissingErrToast;

	@FindBy(xpath = "//div[text() = 'Title is missing or wrong, kindly check it before proceeding.']")
	WebElement titleIsMissingErrToast;

	@FindBy(xpath = "//div[text() = 'Category Settings are missing or wrong, kindly check them before proceeding.']")
	WebElement categorySettingsIsMissingErrToast;

	@FindBy(xpath = "//span[text() = 'Set a Custom Handling Time']/preceding-sibling::span/span/input")
	WebElement setCustomHandlingTimeCheckBox;

	@FindBy(xpath = "//h2[text() = 'Price']")
	WebElement priceHeading;

	@FindBy(xpath = "//span[text() = 'Set the same Price for Shopify and Amazon']/parent::span/preceding-sibling::span/span/input")
	WebElement setSameProductPriceForAmazonCheckBox;

	@FindBy(xpath = "//span[text() = 'Set a Custom Price for Amazon']/parent::span/preceding-sibling::span/span/input")
	WebElement setCustomPriceForAmazonCheckBox;

	@FindBy(xpath = "//span[text() = 'Set a Custom Price for Amazon']/ancestor::label/following-sibling::div//input")
	WebElement setCustomPriceForAmazonInputField;

	@FindBy(xpath = "//h2[text() = 'Inventory']")
	WebElement inventoryHeading;

	@FindBy(xpath = "//span[text() = 'Set the same Product Inventory for Shopify and Amazon']/parent::span/preceding-sibling::span/span/input")
	WebElement setSameProductInventoryForShopifyAndAmazonCheckBox;

	@FindBy(xpath = "//span[text() = 'Set a Custom Product Inventory for Amazon']/parent::span/preceding-sibling::span/span/input")
	WebElement setCustomProductInventoryForAmazonCheckBox;

	@FindBy(xpath = "//span[text() = 'SKU']")
	WebElement skuHeading;

	@FindBy(xpath = "//span[text() = 'Set same SKU for Shopify and Amazon']/parent::span/preceding-sibling::span/span/input")
	WebElement setSameSKUForShopifyAndAmazonCheckBox;

	@FindBy(xpath = "//legend[text() = 'SKU']/parent::fieldset/ul/li/div/label/following-sibling::div//input")
	WebElement setSameSKUForShopifyAndAmazonInputField;

	@FindBy(xpath = "//span[text() = 'Set custom SKU']/parent::span/preceding-sibling::span/span/input")
	WebElement setCustomSKUCheckBox;

	@FindBy(xpath = "//span[text() = 'Variants']")
	WebElement variants;

	@FindBy(xpath = "//span[text() = 'Variants']")
	List<WebElement> variantsList;

	@FindBy(xpath = "//span[text() = 'Amazon Parent SKU']")
	WebElement amazonParentSKUHeading;

	@FindBy(xpath = "//span[text() = 'Amazon Parent SKU']/parent::div/parent::div/parent::h2/parent::div/parent::div/following-sibling::div//input")
	WebElement amazonParentSKUInputField;

	@FindBy(xpath = "//h2[contains(text(),'Barcode')]")
	WebElement barcodeSectionHeading;

	@FindBy(xpath = "//span[text() = 'Barcode']//preceding-sibling::span/span/input")
	WebElement barcodeCheckBox;

	@FindBy(xpath = "//span[text() = 'Barcode']//preceding-sibling::span/span/input")
	List<WebElement> barcodeCheckBoxList;

	@FindBy(xpath = "//button[text() = 'Click here to apply.']")
	WebElement applyForBarCodeExemptionLink;

	@FindBy(xpath = "//span[contains(text(),'same Barcode for Shopify and Amazon')]/parent::span/preceding-sibling::span/span/input")
	WebElement setSameBarcodeForShopifyAndAmazonCheckBox;

	@FindBy(xpath = "//legend[text() = 'barcode']/parent::fieldset/ul/li/div/label/following-sibling::div//input")
	WebElement setSameBarcodeForShopifyAndAmazonInputField;

	@FindBy(xpath = "//span[text() = 'Set a Custom Barcode for Amazon']/parent::span/preceding-sibling::span/span/input")
	WebElement setCustomBarcodeCheckBox;

	@FindBy(xpath = "//span[text() = 'Set a Custom Barcode for Amazon']/parent::span/preceding-sibling::span/parent::label/following-sibling::div//input")
	WebElement setCustomBarcodeInpField;

	@FindBy(xpath = "//div[contains(text() , 'Barcode is missing or wrong')]")
	WebElement barCodeMissingToastErrMsg;

	@FindBy(xpath = "//span[text() = 'Add Amazon Category']")
	WebElement addAmazonCategoryHeading;

	@FindBy(xpath = "//span[text() = 'Use the same Category Settings as in the Product Template']//preceding-sibling::span/span/input")
	WebElement setSameCategorySettingsAsProductTemplateCheckBox;

	@FindBy(xpath = "//span[text() = 'Set a Custom Category Setting']//preceding-sibling::span/span/input")
	WebElement setCustomCategorySettingsCheckBox;

	@FindBy(xpath = "//span[text() = 'Image Selection']")
	WebElement imgSelectionHeading;

	@FindBy(xpath = "//span[text() = 'Set Product Images as shown on Shopify']/ancestor::label//input")
	WebElement setSameProductImgAsShownOnShopifyCheckBox;

	@FindBy(xpath = "//span[text() = 'Set Product Images as shown on Shopify']")
	WebElement setSameProductImgAsShownOnShopifyLabel;

	@FindBy(xpath = "//span[text() = 'Set Custom Images for Amazon']")
	WebElement setCustomImgForAmazonLabel;

	@FindBy(xpath = "//span[text() = 'Set Custom Images for Amazon']/ancestor::label//input")
	WebElement setCustomImgForAmazonCheckBox;

	@FindBy(xpath = "//h2[text() = 'Choose Images by clicking them']")
	WebElement choseImgsHeading;

	@FindBy(xpath = "//img[@alt = 'productThumbnails']")
	List<WebElement> images;

	@FindBy(xpath = "//button[text() = 'Know more on uploading files ']")
	WebElement knowMoreOnUploadingImgs;

	@FindBy(xpath = "//span[text() = 'Add More Images']")
	WebElement addMoreImgsBtn;

	@FindBy(xpath = "//div[@role='dialog']//h2[text()='Add More Images']")
	List<WebElement> addMoreImgModal;

//	@FindBy(xpath = "//div[@role='dialog']//h2[text()='Image Upload Tutorial']")
//	List<WebElement> imageUploadTutorialModal;

	@FindBy(xpath = "//div[@role='dialog']//button[@aria-label='Close']/span")
	WebElement closeBtnModal;

	@FindBy(xpath = "//input[@placeholder='Search with Title, Vendor, Product type or Barcode']")
	WebElement searchInpField;

	@FindBy(xpath = "//div[@class='Polaris-Combobox__Listbox']")
	WebElement suggesstionBox;

	@FindBy(xpath = "//ul[@role='listbox']/div/li")
	List<WebElement> searchProductList;

	@FindBy(xpath = "//td[contains(@class,'ant-table-cell')][8]/div//span[contains(text(),'Not Listed: Offer')]")
	List<WebElement> notListedOffer;

	@FindBy(xpath = "//span[contains(text(),'Not Listed: Offer')]//ancestor::td//following-sibling::td[2]//button")
	List<WebElement> kebabMenuNotListed;

	@FindBy(id = "nextURL")
	WebElement nextPageBtn;

	@FindBy(id = "previousURL")
	WebElement prevPageBtn;

	@FindBy(xpath = "//span[contains(text(),'page')]")
	WebElement totalPages;

	@FindBy(xpath = "//span[text() = 'Edit']/parent::span/parent::button")
	List<WebElement> editVariantsBtnList;

	@FindBy(xpath = "//span[contains(text() , 'Variant Title')]")
	WebElement variantTitleHeading;

//	--------------------------------------PRICE SECTION-------------------------------------------------------

	@FindBy(xpath = "//h2[text() = 'Price']")
	WebElement priceSectionHeading;

	@FindBy(xpath = "//span[text() = 'Set the same Price for Shopify and Amazon']")
	WebElement setSamePriceForShopifyAndAmazonLabel;

	@FindBy(xpath = "//span[text() = 'Set the same Price for Shopify and Amazon']//preceding-sibling::span/span/input")
	WebElement setSamePriceForShopifyAndAmazonCheckbox;

	@FindBy(xpath = "//span[text() = 'Set a Custom Price for Amazon']")
	WebElement setCustomPriceForAmazonLabel;

//	@FindBy(xpath = "//span[text() = 'Set a Custom Price for Amazon']//preceding-sibling::span/span/input")
//	WebElement setCustomPriceForAmazonCheckbox;

	@FindBy(xpath = "//legend[text() = 'price']/parent::fieldset/ul/li/div/label/following-sibling::div//input")
	WebElement setCustomPriceForAmazonInpField;

	@FindBy(xpath = "//legend[text() = 'price']/parent::fieldset/ul//span[@tabindex = '0']/span")
	WebElement priceValue;

	@FindBy(xpath = "//legend[text() = 'price']/parent::fieldset/ul//span[@tabindex = '0']/span")
	List<WebElement> priceValueList;

	@FindBy(xpath = "//span[text()='Shopify Price:']/parent::div/following::div/span")
	WebElement shopifyPriceAtHover;

	@FindBy(xpath = "//span[text()='Price:']/parent::div/following::div/span")
	WebElement priceValAtHover;

//	--------------------------------------QUANTITY SECTION-------------------------------------------------------

	@FindBy(xpath = "//h2[contains(text(),  'Inventory')]")
	WebElement quantitySectionHeading;

	@FindBy(xpath = "//span[text() = 'Set the same Product Inventory for Shopify and Amazon']")
	WebElement setSameQuantityForShopifyAndAmazonLabel;

	@FindBy(xpath = "//span[text() = 'Set the same Product Inventory for Shopify and Amazon']/ancestor::label//input")
	WebElement setSameQuantityForShopifyAndAmazonCheckbox;

	@FindBy(xpath = "//span[text() = 'Set a Custom Product Inventory for Amazon']")
	WebElement setCustomQuantityForAmazonLabel;

	@FindBy(xpath = "//span[text() = 'Set a Custom Product Inventory for Amazon']/ancestor::label//input")
	WebElement setCustomQuantityForAmazonCheckbox;

	@FindBy(xpath = "(//legend[text() = 'quantity']/parent::fieldset/ul/li/div)[2]/div/div//input")
	WebElement setCustomQuantityForAmazonInpField;

	@FindBy(xpath = "//legend[text() = 'quantity']/parent::fieldset/ul//span[@tabindex = '0']/span")
	WebElement inventoryValue;

	@FindBy(xpath = "//legend[text() = 'quantity']/parent::fieldset/ul//span[@tabindex = '0']/span")
	List<WebElement> inventoryValueList;

	@FindBy(xpath = "//span[text() = 'Inventory Breakup:']")
	WebElement inventoryBreakup;

	@FindBy(xpath = "//span[text() = 'Shopify Inventory:']/parent::div/following-sibling::div/span")
	WebElement shopifyInventoryInInventoryBreakup;

	@FindBy(xpath = "//span[text() = 'Calculated Inventory']/parent::div/following-sibling::div/span")
	WebElement calculatedInventoryInInventoryBreakup;

//	--------------------------------------SKU SECTION-------------------------------------------------------

	@FindBy(xpath = "//span[contains(text(),'SKU')]")
	WebElement skuSectionHeading;

	@FindBy(xpath = "//span[contains(text(),'SKU for Shopify and Amazon')]")
	WebElement setSameSKUForShopifyAndAmazonLabel;

//	@FindBy(xpath = "//span[contains(text(),'SKU for Shopify and Amazon')]//preceding-sibling::span/span/input")
//	WebElement setSameSKUForShopifyAndAmazonCheckbox;

	@FindBy(xpath = "//span[contains(text(),'Set custom SKU')]")
	WebElement setCustomSKUForAmazonLabel;

	@FindBy(xpath = "//span[contains(text(),'Set custom SKU')]/ancestor::label//input")
	WebElement setCustomSKUForAmazonCheckbox;

	@FindBy(xpath = "(//legend[text() = 'SKU']/parent::fieldset/ul/li/div)[2]/div/div//input")
	WebElement setSKUForAmazonInpField;

	@FindBy(className = "user-card__email")
	WebElement email;

	@FindBy(className = "user-card__email")
	List<WebElement> emailList;

//	--------------------ADD AMAZON CATEGORY-----------------

	@FindBy(xpath = "//span[contains(text(),'Add Amazon Category')]")
	WebElement addAmzCategoryHeading;

	@FindBy(xpath = "//span[contains(text(),'Set category from Product Template')]")
	WebElement useSameCategoryLabel;

	@FindBy(xpath = "//span[contains(text(),'Set category from Product Template')]//preceding-sibling::span/span/input")
	WebElement useSameCategoryRadioBtn;

	@FindBy(xpath = "//span[contains(text(),'Set new product category')]")
	WebElement setCustomCategoryLabel;

	@FindBy(xpath = "//span[contains(text(),'Set new product category')]//preceding-sibling::span/span/input")
	WebElement setCustomCategoryRadioBtn;

	@FindBy(xpath = "//legend[text() = 'selected_category']/parent::fieldset/ul//div[@class='Polaris-Choice__Descriptions']")
	List<WebElement> selectedCategory;

	@FindBy(className = "dropdown")
	WebElement addAmazonCategoryDropdown;

	@FindBy(xpath = "//span[@class='Polaris-Icon Polaris-Icon--colorPrimary Polaris-Icon--applyColor']")
	List<WebElement> greenArrowBtn;

	@FindBy(xpath = "//a[@class='menu-item undefined']")
	List<WebElement> selectCategoryList;

	@FindBy(xpath = "//span[text() = 'Set a Custom Category Setting']/parent::label/following-sibling::div//input")
	WebElement customCategoryInpField;

	@FindBy(xpath = "//span[text() = 'Apply']/parent::span/parent::button")
	WebElement applyBtn;

	@FindBy(xpath = "//div[@id = '[object Object]']")
	List<WebElement> foundCategoryList;

	@FindBy(xpath = "//span[text() = 'Clear']/parent::span/parent::button")
	WebElement clearBtn;

	@FindBy(xpath = "//span[contains(text() , 'matching categories found')]")
	WebElement categoriesFound;

	@FindBy(xpath = "//p[contains(text() , 'No matching category(s) found!')]")
	WebElement noCategoryFound;

	@FindBy(xpath = "//div[@class = 'wrapper']/following-sibling::div")
	List<WebElement> selectedCategoryFromNodeToLeaf;

	@FindBy(xpath = "//div[@class = 'menu transition-enter-done']//span[@class = 'icon-button']")
	WebElement removeAmzCategoryBackBtn;

	@FindBy(xpath = "//h2[text() = 'Attributes']")
	WebElement attributesSectionHeading;

	@FindBy(xpath = "//h2[text() = 'Required Attributes']")
	WebElement requiredAttributesHeading;

	@FindBy(xpath = "//span[text() = 'Shopify Attribute']/parent::div/following-sibling::div//div/div[@class = ' css-b62m3t-container']")
	List<WebElement> setShopifyAttribute;

	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'Set Custom']")
	WebElement setCustomOption;

	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'Set Shopify Attribute']")
	WebElement setShopifyAttributeOption;

	@FindBy(xpath = "//h2[text() = 'Optional Attributes']/parent::div/parent::div/following-sibling::div/div//div[@role='group']//div[@class=' css-qc6sy-singleValue']")
	List<WebElement> amazonAttributeOptionalAttribute;

	@FindBy(xpath = "//h2[text() = 'Optional Attributes']/parent::div/parent::div/following-sibling::div/div//div[@role='group']/div/div[2]/div/div[2]//div[@class=' css-1s2u09g-control']")
	List<WebElement> shopifyAttributeOptionalAttribute;

	@FindBy(xpath = "//h2[text() = 'Optional Attributes']/parent::div/parent::div/following-sibling::div/div//div[@role='group']/div/div[3]/div/div[2]//div[@class=' css-1s2u09g-control']")
	List<WebElement> setShopifyAttributesOptionalAttribute;

	@FindBy(xpath = "//div[@class=' css-yt9ioa-option']")
	List<WebElement> setShopifyAttributesOptionalAttributeOptions;

	@FindBy(xpath = "//div[@class=' css-yt9ioa-option']")
	List<WebElement> amazonAttributeOptionalAttributeOption;

	@FindBy(xpath = "//div[@class = 'z-set']//input")
	WebElement customValueInputField;

	@FindBy(xpath = "//h2[text() = 'Optional Attributes']/parent::div/parent::div/parent::div//div[@role='group']//div[@class = 'z-set']//input")
	List<WebElement> optionalAttributeCustomInputField;

	@FindBy(xpath = "//span[text() = 'Set Shopify Attribute']/parent::div/following-sibling::div//input")
	List<WebElement> setShopifyAttributeDropDown;

	@FindBy(xpath = "//div[@class = ' css-yt9ioa-option']")
	List<WebElement> setShopifyAttributeDropDownVal;

	@FindBy(xpath = "(//h2[text()='Optional Attributes'])[2]")
	WebElement optionalAttributeSection;

	@FindBy(xpath = "//span[text()='Add Attributes']/parent::span/parent::button")
	WebElement addOptionalAttributesBtn;

	@FindBy(xpath = "//span[text() = 'Variation Theme']/parent::h2")
	WebElement variationThemeSectionHeading;

	@FindBy(xpath = "//span[text() = 'Amazon Recommendation']/parent::div/following-sibling::div//div//div[@class = ' css-1s2u09g-control']")
	WebElement variationThemeAmazonRecommendationSelect;

	@FindBy(xpath = "//span[contains(text(),'Variant Title')]/span")
	WebElement asteriskVariantTitle;

	@FindBy(xpath = "//span[contains(text(),'Handling Time')]/span")
	WebElement asteriskHandlingTime;

//	---------------------Add Variant Attributes----------------------
	@FindBy(xpath = "//h2[text() = 'Add Attributes in Variants']")
	WebElement addVariantAttributesSectionHeading;

	@FindBy(xpath = "//div[contains(text(),'Select...')]")
	WebElement attributesDropDown;

	@FindBy(xpath = "//div[text() = 'Please Select']/parent::div")
	WebElement shopifyAttribute;

//	---------------------------------<templates>-----------------------------
	@FindBy(xpath = "//h1[text()='Settings']")
	List<WebElement> settingsHeadingList;

	@FindBy(xpath = "//span[text()='Product Templates']")
	WebElement templateButton;

	@FindBy(xpath = "//span[text()='Product Templates']")
	List<WebElement> templateButtonList;

	@FindBy(xpath = "//div[@class = 'Polaris-Grid']/div[2]//button")
	List<WebElement> templateNameKebabMenuList;

	@FindBy(id = "templates")
	WebElement templatesSectionButton;

	@FindBy(id = "templates")
	List<WebElement> templatesSectionButtonList;

	@FindBy(xpath = "//input[@placeholder='Search with template name']")
	WebElement searchTemplateInputField;

	@FindBy(xpath = "//span[text() = 'Add New Template']")
	WebElement addNewTemplateButton;

	@FindBy(xpath = "//input[@placeholder = 'Enter Template Name']")
	WebElement templateNameInputField;

	@FindBy(xpath = "//a[@class ='menu-item undefined' and text()='Default']")
	WebElement defaultCategory;

	@FindBy(xpath = "//a[@class='menu-item undefined' and text() = 'Apps for Android']")
	WebElement appsForAndriodCategory;

	@FindBy(className = "card_dull")
	List<WebElement> inactiveCategoryCard;

	@FindBy(xpath = "//a[@class='menu-item undefined' and text() = 'Communication']")
	WebElement communicationCategory;

	@FindBy(xpath = "//span[text() = 'Shopify Attribute']/parent::div/following-sibling::div//div[@class = ' css-b62m3t-container']")
	List<WebElement> selectShopifyAttribute;

	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'Set Custom']")
	WebElement setCustomShopifyAttribute;

	@FindBy(xpath = "//div[@aria-disabled='false' and text()='Set Shopify Attribute']")
	WebElement setShopifyAttributeInShopifyAttribute;

	@FindBy(xpath = "//div[@aria-disabled='false' and text()='Set Amazon Recommendation']")
	WebElement setAmazonRecommendationInShopifyAttribute;

	@FindBy(xpath = "//span[text() = 'Set Shopify Attribute']/parent::div/div/div/div/div")
	List<WebElement> setShopifyAttributeSelectorInput;

	@FindBy(xpath = "//span[text() = 'Set Amazon Recommendation']/parent::div/following-sibling::div//div[@class = ' css-1d8n9bt']//div[@class = ' css-ackcql']/input")
	List<WebElement> setAmazonRecommendationInput;

	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'Product Type']")
	WebElement productTypeOption;

	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'Handle']")
	WebElement handleOption;

	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'Color']")
	WebElement colorOption;

	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'brand']")
	WebElement materialOption;

	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'weight_unit']")
	WebElement weightUnitOption;

	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'quantity']")
	WebElement qtyOption;

	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'cubic_centimeters']")
	WebElement cubicCentimeters;

	@FindBy(xpath = "//div[@aria-disabled='false' and text()='India']")
	WebElement indiaOption;

	@FindBy(xpath = "//div[@aria-disabled='false' and text()='Manufacturer']")
	WebElement manufacturer;

	@FindBy(xpath = "//span[text() = 'Set Custom']/parent::div/following-sibling::div//div[@class = 'z-set']//input")
	List<WebElement> setCustomInputField;

	@FindBy(xpath = "//input[@placeholder = 'Enter value...']")
	WebElement handlingTimeInputField;

	@FindBy(id = "manual")
	WebElement manualRadioBtn;

	@FindBy(xpath = "//input[@placeholder = 'Search products with Title']")
	WebElement manualSelectionInpField;

	@FindBy(xpath = "//span[text() = 'Browse products']")
	WebElement browseManualProductsButton;

	@FindBy(xpath = "//div[@role='dialog']//input[@type = 'checkbox']")
	WebElement checkBoxProductsManualSelectionDialogBox;

	@FindBy(xpath = "//div[@role='dialog']//span[text() = 'Continue']")
	WebElement continueBtnManualSelectionDialogBox;

	@FindBy(className = "progress-height-6")
	List<WebElement> templateAssignProgressBar;

	@FindBy(xpath = "//p[text() = 'No Products Found']")
	List<WebElement> noProductFound;

	@FindBy(xpath = "//p[text() = 'Saving your Template']")
	List<WebElement> savingYourTemplateProgressBar;

	@FindBy(xpath = "//span[text() ='Search a Category for this Product.']")
	WebElement searchCategoryLabel;

//	---------------------------------</templates>-----------------------------
//	---------------------------------<Offer Listing>---------------------------------------
	@FindBy(xpath = "//span[normalize-space()='Shopify Attribute']/parent::div/following-sibling::div/div")
	WebElement shopifyAttribueSelector;

	@FindBy(xpath = "//div[@aria-disabled='false' and text()='Select Amazon Recommendation']")
	WebElement selectAmazonRecommendation;

	@FindBy(xpath = "//span[text() = 'Set Amazon Recommendataion']/parent::div/following-sibling::div//input")
	WebElement setAmazonRecommentdationSelector;

	@FindBy(xpath = "//div[text()='New']")
	WebElement newOption;

//	---------------------------------</Offer Listing>---------------------------------------

	@FindBy(xpath = "//span[contains(text(),'Price')]/span")
	WebElement priceListingGrid;

	@FindBy(xpath = "//span[contains(text(),'Barcode: ')]/span")
	WebElement barcodeListingGrid;

	@FindBy(xpath = "//td[@class='ant-table-cell'][5]")
	WebElement inventoryListingGrid;

	@FindBy(xpath = "//div[@class = 'Polaris-Grid']/div[2]//button")
	List<WebElement> kebabMenuTemplates;

	@FindBy(xpath = "//button[@role = 'menuitem']//span[text() = 'Edit']")
	WebElement editTemplate;

	@FindBy(xpath = "//span[contains(text() , 'Continue selling when out of stock')]/parent::label/span/span/input")
	WebElement continueSellingCheckbox;

	@FindBy(xpath = "//input[@placeholder = '999']")
	WebElement setMaxInventoryInputField;

	@FindBy(xpath = "//span[contains(text() , 'Set Fixed Inventory')]/parent::label/span/span/input")
	WebElement setFixedInventoryCheckbox;

	@FindBy(xpath = "(//span[text() = 'Set Fixed Inventory'])[2]/parent::label/parent::div/parent::div/following-sibling::div/div/div/input")
	WebElement setFixedInventoryInputField;

	@FindBy(xpath = "//span[contains(text() , 'Set Reserve Inventory')]/parent::label/span/span/input")
	WebElement setReserverInventoryCheckbox;

	@FindBy(xpath = "//label[text() = 'Set Reserved Inventory']/parent::div/parent::div/following-sibling::div/div/div/input")
	WebElement setReserverInventoryInputField;

	@FindBy(xpath = "//span[contains(text() , 'Threshold Inventory')]/parent::label/span/span/input")
	WebElement thresoldInventoryCheckbox;

	@FindBy(xpath = "//span[text() = 'Threshold Inventory']/parent::label/parent::div/parent::div/following-sibling::div//input[@placeholder='Enter value...']")
	WebElement thresoldInventoryInputField;

	@FindBy(xpath = "//legend[text() = 'Warehouse Settings']/following-sibling::ul//label/span/following-sibling::span")
	List<WebElement> wareHouseNameListInTemplate;

	@FindBy(xpath = "//legend[text() = 'Warehouse Settings']/following-sibling::ul//input")
	List<WebElement> wareHouseCheckboxListInTemplate;

	@FindBy(xpath = "(//div[contains(@class,'css-qc6sy-singleValue')])[2]")
	List<WebElement> attributeVal;

	@FindBy(xpath = "//span[text() = 'Map attribute values']")
	List<WebElement> mapAttributeValuesLink;

}
