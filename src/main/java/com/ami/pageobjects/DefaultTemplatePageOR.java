package com.ami.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ami.pageobjects.reusableclasses.ReusableMethodsOR;

public class DefaultTemplatePageOR extends ReusableMethodsOR {

	@FindBy(id = "AppFrameTopBar")
	protected WebElement contextualSaveBar;

	@FindBy(id = "templates")
	protected WebElement templateButton;

	@FindBy(xpath = "//span[text()='Map attribute values']")
	protected List<WebElement> mapAttributeValuesList;

	@FindBy(xpath = "//span[text() = 'Edit']/parent::div/parent::button")
	protected WebElement editButton;

	@FindBy(xpath = "//nav[@role = 'navigation']")
	protected List<WebElement> backBtnList;

	@FindBy(xpath = "//h1[text() = 'Edit Default Template']")
	protected WebElement headingDefaultTemplatePage;

	@FindBy(xpath = "//input[@placeholder='Search with template name']")
	protected WebElement searchTemplateInputField;

	@FindBy(xpath = "//input[@type = 'checkbox']")
	protected List<WebElement> allCheckboxList;

	@FindBy(xpath = "//div[text() = 'Enter a number between 1 to 30']")
	protected List<WebElement> wrongHandlingTimeMsg;

	@FindBy(xpath = "//label[text() = 'Set Threshold Inventory']/parent::div/parent::div/following-sibling::div//input")
	protected List<WebElement> thresoldInventoryInputFieldList;

	@FindBy(xpath = "//span[text() = 'Delete Out of Stock']/parent::span/preceding-sibling::span/span/input")
	protected WebElement delOutOfStockCheckBox;

	@FindBy(xpath = "//span[text() = 'Delete out of stock']/parent::span/preceding-sibling::span/span/input")
	protected WebElement delOutOfStockCheckBoxDisabled;

	@FindBy(xpath = "//input[@name = 'PolarisChoiceList1[]']")
	protected List<WebElement> warehouseListCheckbox;

	@FindBy(xpath = "//input[contains(@name , 'PolarisChoiceList')]")
	protected List<WebElement> warehouseListCheckboxCustomTemplate;

	@FindBy(xpath = "//span[contains(text() , 'Start date')]/parent::label/parent::div/parent::div/following-sibling::div//input")
	protected WebElement startDateSelector;

	@FindBy(xpath = "//span[contains(text() , 'End date')]/parent::label/parent::div/parent::div/following-sibling::div//input")
	protected WebElement endDateSelector;

	@FindBy(xpath = "//span[contains(text() , 'Enable Price Syncing from the App')]/parent::span/preceding-sibling::span/span/input")
	protected WebElement enablePriceSyncCheckbox;

	@FindBy(xpath = "(//span[contains(text() , 'Choose Trend')]/parent::label/parent::div/parent::div/following-sibling::div/select)[1]")
	protected WebElement standardPriceChoseTrend;

	@FindBy(xpath = "//span[contains(text() , 'Choose trend')]/parent::label/parent::div/parent::div/following-sibling::div/select")
	protected WebElement standardPriceChoseTrendDisabled;

	@FindBy(xpath = "//span[contains(text() , 'Sale Price')]/parent::span/preceding-sibling::span/span/input")
	protected WebElement salePriceCheckbox;

	@FindBy(xpath = "//span[contains(text() , 'Sale price')]/parent::span/parent::span/preceding-sibling::span/span/input")
	protected WebElement salePriceCheckboxDisabled;

	@FindBy(xpath = "//span[contains(text() , 'Business Price')]/parent::span/preceding-sibling::span/span/input")
	protected WebElement businessPriceCheckbox;

	@FindBy(xpath = "//span[contains(text() , 'Business price')]/parent::span/parent::span/preceding-sibling::span/span/input")
	protected WebElement businessPriceCheckboxDisabled;

	@FindBy(xpath = "//span[text() = 'Business Price']/parent::span/parent::label/following-sibling::div/div/div//select")
	protected WebElement choseTrendBusinessPrice;

	@FindBy(xpath = "//span[text() = 'Business Price']/parent::span/parent::label/following-sibling::div/div/div/div/div[2]//input[@placeholder = 'Enter value']")
	protected WebElement choseTrendBusinessValueInputField;

	@FindBy(xpath = "//span[contains(text() , 'Minimum Price')]/parent::span/preceding-sibling::span/span/input")
	protected WebElement minimumPriceCheckbox;

	@FindBy(xpath = "//span[contains(text() , 'Minimum Price')]/parent::span/parent::span/preceding-sibling::span/span/input")
	protected WebElement minimumPriceCheckboxDisabled;

	@FindBy(xpath = "//span[text() = 'Minimum Price']/parent::span/parent::label/following-sibling::div/div/div//select")
	protected WebElement selectMinPriceTrend;

	@FindBy(xpath = "//span[text() = 'Minimum Price']/parent::span/parent::label/following-sibling::div/div/div/div/div[2]//input[@placeholder = 'Enter value']")
	protected WebElement minimumPriceTrendValueInputField;

	@FindBy(xpath = "//span[contains(text() , 'Product Details')]/parent::span/preceding-sibling::span/span/input")
	protected WebElement productDetailsCheckBox;

	@FindBy(xpath = "//span[contains(text() , 'Images')]/parent::span/preceding-sibling::span/span/input")
	protected WebElement imagesCheckBox;

	@FindBy(xpath = "//span[contains(text() , 'SKU')]/parent::div/preceding-sibling::div/label/span/span/input")
	protected WebElement skuCheckbox;

	@FindBy(xpath = "//span[contains(text() , 'Barcode')]/parent::div/preceding-sibling::div/label/span/span/input")
	protected WebElement barcodeCheckbox;

	

	@FindBy(xpath = "//span[contains(text() , 'Profile saved successfully')]")
	protected WebElement profileSaveMsg;

//	**************** CUSTOM TEMPLATE PAGE OBJECTS ************************

	@FindBy(xpath = "//h1[text() = 'Add New Template']")
	protected WebElement addNewTemplatePageHeading;

	@FindBy(xpath = "//span[text() = 'Barcode/GTIN Exemption']/parent::span/preceding-sibling::span/span/input")
	protected WebElement barCodeExemptionCheckbox;

	@FindBy(xpath = "//a[text() = 'here.']")
	protected WebElement applyForGTINExemptionLink;

	@FindBy(xpath = "//div[text() = 'Search a Category for this Product.']/preceding-sibling::div//input")
	protected WebElement searchInputField;

	@FindBy(xpath = "//span[@class = 'Polaris-Button__Text' and text() = 'Search']")
	protected WebElement searchBtn;

	@FindBy(xpath = "//span[text() = 'Clear']")
	protected WebElement clearBtn;

	@FindBy(xpath = "//span[@class = 'icon-button']")
	protected WebElement removeCategoryBtn;

	@FindBy(xpath = "//a[@class = 'menu-item back-nav']")
	protected WebElement browseAndSelectCategory;

	@FindBy(xpath = "//a[text()= 'Apps for Android']")
	protected WebElement appsForAndroidCategory;

	@FindBy(xpath = "//a[text()= 'Communication']")
	protected WebElement appsForAndroidSubCategory;

	@FindBy(xpath = "//div[@style= 'margin-top: 20px;'][1]//div[@class = 'Polaris-Stack__Item']/span")
	protected List<WebElement> selectedCategoryList;

	@FindBy(xpath = "(//ul[@aria-live = 'polite']/div[2]/li/div/div/div/div/span/div/div//span)[1]")
	protected WebElement searchedCategoryResult1;

	@FindBy(xpath = "(//ul[@aria-live = 'polite']/div[2]/li/div/div/div/div/span/div/div//span)[4]")
	protected WebElement searchedSubCategoryResult1;

	@FindBy(xpath = "//button[@aria-label = 'View details for ']")
	protected WebElement totalProductFoundMsg;

	@FindBy(xpath = "//div[@class = 'categoryCount']")
	protected WebElement categoryCount;

	@FindBy(xpath = "//p[text() = 'No Search Results Found']")
	protected WebElement noSearchResultsFoundMsg;

	@FindBy(xpath = "//p[text() = 'Shopify Attribute']/parent::div/div//div[@class = ' css-b62m3t-container']")
	protected List<WebElement> selectShopifyAttribute;

	@FindBy(xpath = "//span[text() = 'Set Shopify Attribute']/parent::div/div/div[contains(@class,'css-b62m3t-container')]")
	protected List<WebElement> setShopifyAttributeSelectorInput;

	@FindBy(xpath = "//p[text() = 'Shopify Attribute']/parent::div/div/div/div/div[contains(@class,'css-hlgwow')]/div[1]")
	protected List<WebElement> selectedAttributeName;

	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'Set Shopify Attribute']")
	protected WebElement setShopifyAttributeOption;

	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'Set Amazon Recommendation']")
	protected WebElement setAmazonRecommendationOption;

	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'Set Amazon Recommendation']")
	protected List<WebElement> setAmazonRecommendationOptionList;

	@FindBy(xpath = "//span[text() = 'Set Amazon Recommendation']")
	protected WebElement setAmazonRecommendationInputHeading;

	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'brand']")
	protected WebElement brandOption;

	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'Product Type']")
	protected WebElement productTypeOption;

	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'title']")
	protected WebElement titleOption;

	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'description']")
	protected WebElement descriptionOption;

	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'quantity']")
	protected WebElement quantityOption;

	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'sku']")
	protected WebElement skuOption;

	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'barcode']")
	protected WebElement barcodeOption;

	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'price']")
	protected WebElement priceOption;

	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'tags']")
	protected WebElement tagsOption;

	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'Handler']")
	protected WebElement handlerOption;

	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'weight']")
	protected WebElement weightOption;

	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'weight_unit']")
	protected WebElement weightUnitOption;

	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'grams']")
	protected WebElement gramsOption;
	
	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'Variant title']")
	protected WebElement variantTitleOption;
	
	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'COLOR']")
	protected WebElement capsColorOption;
	
	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'Color']")
	protected WebElement colorOption;
	
	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'Material']")
	protected WebElement materailOption;
	
	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'SIZE']")
	protected WebElement capsSizeOption;
	
	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'Size']")
	protected WebElement sizeOption;
	
	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'type']")
	protected WebElement typeOption;
	
	
	@FindBy(xpath = "//div[contains(@class,'css-d7l1ni-option')]")
	protected WebElement setCustomShopifyAttribute;

	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'Set Custom']")
	protected WebElement setCustomAttribute;

	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'Set Custom']")
	protected List<WebElement> setCustomAttributeList;

	@FindBy(xpath = "//div[@aria-disabled='false' and text() = 'Set Shopify Attribute']")
	protected WebElement setShopifyAttribute;

	@FindBy(xpath = "//div[@class = ' css-14el2xx-placeholder']")
	protected List<WebElement> listShopifyAttribute;

	@FindBy(xpath = "//div[@class = ' css-14el2xx-placeholder']/parent::div/parent::div")
	protected WebElement shopifyAttributeContainer;

	@FindBy(xpath = "//span[text() = 'Set Custom']/parent::div//div[@class = 'z-set']//input")
	protected WebElement setCustomInputField;

	@FindBy(xpath = "//span[text() = 'Set Custom']/parent::div//div[@class = 'z-set']//input")
	protected List<WebElement> setCustomInputFieldList;

	@FindBy(xpath = "//div[contains(@class , 'css-10wo9uf-option') and @aria-disabled='false']")
	protected List<WebElement> shopifyAttributeList;

	@FindBy(xpath = "//span[text() = 'Add Attribute']")
	protected WebElement addOptionaAttributeBtn;

	@FindBy(xpath = "//span[text() = 'Delete']")
	protected WebElement deleteOptionaAttributeBtn;

	@FindBy(xpath = "//h2[text() = 'Optional Attributes']/parent::div/following-sibling::div//p[.='Amazon Attribute *']/following-sibling::div/div")
	protected List<WebElement> optionalAmazonAttributeSelectorList;

	@FindBy(xpath = "//h2[text() = 'Optional Attributes']/parent::div/following-sibling::div/div//p[text() = 'Shopify Attribute']/following-sibling::div/div[contains(@class,'css-b62m3t-container')]")
	protected List<WebElement> optionalShopifyAttributeSelectorList;

	@FindBy(xpath = "//div[text() = 'Variation Theme (variation_theme)']")
	protected WebElement variationThemeOptionalAttribute;

	@FindBy(xpath = "(//h2[text() = 'Optional Attributes']/parent::div/parent::div/parent::div/following-sibling::div//div[@role = 'group']/div/div[3]/div/div[2]/div)")
	protected WebElement setShopifyAttributeOptionalAttributeSelector;

	@FindBy(xpath = "//div[text() = 'Product Type']")
	protected WebElement productType;

	@FindBy(id = "advanced")
	protected WebElement advancedRadioBtn;

	@FindBy(xpath = "(//label[text() = 'Select By'])/parent::div/parent::div/following-sibling::div/select")
	protected List<WebElement> selectBy;

	@FindBy(xpath = "//span[text() = 'Add group']/parent::span/parent::button")
	protected WebElement addGrpBtn;

	@FindBy(xpath = "//span[text() = 'Remove group']/parent::span/parent::button")
	protected List<WebElement> removeGrpBtn;

	@FindBy(xpath = "(//label[text() = 'Value'])/parent::div/parent::div/following-sibling::div//input")
	protected List<WebElement> advanceSelectionInputField;

	@FindBy(xpath = "//div[contains(@class,'Polaris-Banner--statusSuccess') and @role = 'status']")
	protected WebElement productsMatchAdvanceSelectionBanner;

	@FindBy(xpath = "//div[contains(@class,'Polaris-Banner--statusWarning') and @role = 'alert']")
	protected WebElement zeroProductsFoundAdvanceSelectionBanner;

	@FindBy(xpath = "//span[text() = 'Add row']")
	protected WebElement addRowBtn;

	@FindBy(xpath = "//button[contains(@class , '-Button--iconOnly')]")
	protected List<WebElement> delRowBtn;

	@FindBy(xpath = "//span[text() = 'See Query Result']")
	protected WebElement seeQryResultBtn;

	@FindBy(xpath = "//h2[text() = 'Advanced Selection']/parent::div/parent::div/following-sibling::span/span")
	protected List<WebElement> activeFilters;

	@FindBy(xpath = "//div[@class = 'custom-product--templateName']/span")
	protected List<WebElement> customTemplateNameOverview;

	@FindBy(id = "nextURL")
	protected WebElement nextPageBtnPagination;

	@FindBy(id = "previousURL")
	protected WebElement prevPageBtnPagination;

	@FindBy(xpath = "//span[text()='Warehouse Settings']/parent::legend/following-sibling::ul//input")
	protected List<WebElement> wareHouseChoiceCheckBoxCustomTemplate;

	@FindBy(xpath = "//div[contains(text(),'Total')]")
	protected List<WebElement> zeroProductApplied;
	
	@FindBy(xpath = "//span[text()='Standard Price']/ancestor::div[@class='Polaris-HorizontalStack']/following-sibling::div[@class='customFlex']//span[text()='Choose Trend']/ancestor::div[@class='Polaris-Labelled__LabelWrapper']/following-sibling::div/select")
	protected WebElement choseTrendSelect;
	
	@FindBy(xpath = "//span[text()='Standard Price']/ancestor::div[@class='Polaris-HorizontalStack']/following-sibling::div[@class='customFlex']//span[text()='Value']/ancestor::div[@class='Polaris-Labelled__LabelWrapper']/following-sibling::div//input")
	protected WebElement standardPriceValueInputTextField;
	
	@FindBy(xpath = "//span[text()='Standard Price']/ancestor::div[@class='Polaris-HorizontalStack']/following-sibling::div[@class='customFlex']//span[text()='Rounding Off']")
	protected WebElement standardPriceRoundingOffLabel;

	@FindBy(xpath = "//span[text()='Standard Price']/ancestor::div[@class='Polaris-HorizontalStack']/following-sibling::div[@class='customFlex']//span[text()='Rounding Off']/ancestor::div[@class='Polaris-Labelled__LabelWrapper']/following-sibling::div/select")
	protected WebElement standardPriceRoundingOffSelect;
	
	@FindBy(xpath = "//span[text()='Sale Price']/ancestor::label[@class='Polaris-Choice']/following-sibling::div[@class='customFlex']//span[text()='Rounding Off']")
	protected WebElement salePriceRoundingOffLabel;

	@FindBy(xpath = "//span[text()='Sale Price']/ancestor::label[@class='Polaris-Choice']/following-sibling::div[@class='customFlex']//span[text()='Rounding Off']/ancestor::div[@class='Polaris-Labelled__LabelWrapper']/following-sibling::div/select")
	protected WebElement salePriceRoundingOffSelect;
	
	@FindBy(xpath = "//span[text()='Business Price']/ancestor::label[@class='Polaris-Choice']/following-sibling::div[@class='customFlex']//span[text()='Rounding Off']")
	protected WebElement businessPriceRoundingOffLabel;

	@FindBy(xpath = "//span[text()='Business Price']/ancestor::label[@class='Polaris-Choice']/following-sibling::div[@class='customFlex']//span[text()='Rounding Off']/ancestor::div[@class='Polaris-Labelled__LabelWrapper']/following-sibling::div/select")
	protected WebElement businessPriceRoundingOffSelect;
	
	@FindBy(xpath = "//span[text()='Minimum Price']/ancestor::label[@class='Polaris-Choice']/following-sibling::div[@class='customFlex']//span[text()='Rounding Off']")
	protected WebElement minimumPriceRoundingOffLabel;

	@FindBy(xpath = "//span[text()='Minimum Price']/ancestor::label[@class='Polaris-Choice']/following-sibling::div[@class='customFlex']//span[text()='Rounding Off']/ancestor::div[@class='Polaris-Labelled__LabelWrapper']/following-sibling::div/select")
	protected WebElement minimumPriceRoundingOffSelect;

//	------------------Edit Template Page ---------------------

	@FindBy(xpath = "//h1[text() = 'Edit Product Template']")
	protected WebElement editProductTemplateHeading;

	@FindBy(xpath = "//span[@class = 'Polaris-Badge Polaris-Badge--statusSuccess']")
	protected WebElement numOfProductAssignedBanner;

	

//----------------------------------------------------<Manual Selection Modal>-----------------------------------------

	// ----------------------------------------------------</Manual Selection Modal>-----------------------------------------

	@FindBy(xpath = "//span[text() = 'Map attribute values']")
	protected List<WebElement> mapAttributeValuesLink;

	@FindBy(xpath = "//h2[text()='Important information']")
	protected WebElement impInfoHeading;

	@FindBy(xpath = "//span[text()='Do it later']")
	protected WebElement doItLaterBtn;

	@FindBy(xpath = "//span[text()='Do it later']")
	protected List<WebElement> doItLaterBtnList;

	@FindBy(xpath = "//span[text()='Map values']")
	protected WebElement mapValuesBtn;
	
	@FindBy(xpath = "//span[text()='Not Eligible for value mapping']")
	protected WebElement notEligible;

	@FindBy(xpath = "//span[contains(@class,'progress-height-')]")
	protected List<WebElement> progressbar;

//	----------------------------<template grid>-----------------------------------------

	@FindBy(css = "p[id^='template_Text_CreatedOn_']")
	protected List<WebElement> createdOnList;

	@FindBy(css = "div[class='Polaris-Grid']>div[class^='Polaris-Grid-Cell']:nth-of-type(2)>div>p")
	protected List<WebElement> updatedOnList;

//	----------------------------<Value Mapping>-----------------------------------------

	@FindBy(xpath = "//h1[text()='Value Mapping']")
	protected WebElement valueMappingHeading;

	@FindBy(className = "custom__section--collapse")
	protected WebElement correspondingAmzAttributeValueMapping;
	
	@FindBy(className = "custom__section--collapse")
	protected List<WebElement> correspondingAmzAttributeValueMappingList;
	
	@FindBy(css = "div.custom__section--collapse>div>div>span+span")
	protected List<WebElement> correspondingAmzAttributeNameList;

	@FindBy(css = ".custom__section--collapse+div#basic-collapsible input")
	protected WebElement searchWithInputFieldValueMapping;

	@FindBy(css = "div#basic-collapsible div.Polaris-Select>select")
	protected WebElement selectAmzValueSelectValueMapping;
	
	@FindBy(css = "div#basic-collapsible div.Polaris-Select")
	protected List<WebElement> selectAmzValue;
	
	@FindBy(css = "div.custom__value--heading+div>div>select")
	protected List<WebElement> selectAmzValueSelectValueMappingList;

	@FindBy(css = "div#basic-collapsible>div div.Polaris-HorizontalStack>button")
	protected List<WebElement> saveValuesBtnValueMapping;
	
	@FindBy(xpath = "//span[text() = '0 product(s)']")
	protected List<WebElement> zeroProdBadge;

//	----------------------------<Value Mapping/>-----------------------------------------
}
