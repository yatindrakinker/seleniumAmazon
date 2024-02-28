package com.ami.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ami.pageobjects.reusableclasses.ReusableMethodsOR;

public class CreateShopifyProductOR extends ReusableMethodsOR{

	@FindBy(xpath = "//span[contains(text(),'Add product')]")
	protected WebElement addProductButton;

	@FindBy(xpath = "//input[@placeholder = 'Short sleeve t-shirt']")
	protected WebElement titleInputField;

	@FindBy(id = "product-description_ifr")
	protected WebElement iframe;

	@FindBy(id = "tinymce")
	protected WebElement productDescriptionBox;

	@FindBy(xpath = "//button[text()='Add from URL']")
	protected WebElement addFromUrl;

	@FindBy(css = "div._Checkbox_is5y9_8 input")
	protected WebElement imgCheckBox;

	@FindBy(css = "div._Checkbox_is5y9_8 input")
	protected List<WebElement> imgCheckBoxList;

	@FindBy(css = "span[class*='_DragHandle']")
	protected List<WebElement> imgList;

	@FindBy(css = "button[aria-label*='Delete file']")
	protected WebElement deleteImgBtn;

	@FindBy(css = "button[aria-label = 'Delete']")
	protected WebElement confirmDeleteImgBtn;

	@FindBy(xpath = "//span[text() = 'File deleted']")
	protected WebElement fileDeletedMsg;

	@FindBy(xpath = "//span[text() = 'Media added']")
	protected WebElement mediaAddedMsg;

	@FindBy(xpath = "//input[@placeholder = 'https://']")
	protected WebElement enterUrlInputField;

	@FindBy(xpath = "//div[@role='dialog']//span[text() = 'Add file']/parent::button")
	protected WebElement addFileBtn;

	@FindBy(xpath = "//h2[text()='Status']/parent::div/following-sibling::div[1]//select")
	protected WebElement productStatus;

	@FindBy(className = "dn_ht")
	protected WebElement addFilesButton;

	@FindBy(xpath = "//label[text()='Vendor']/parent::div/parent::div/following-sibling::div//input")
	protected WebElement vendorInputField;

	@FindBy(id = "ProductOrganizationCustomType")
	protected WebElement productTypeInputField;

	@FindBy(css = "input[name='tags']")
	protected WebElement productTagsInputField;

	@FindBy(xpath = "//button[contains(@aria-label,'Remove')]")
	protected List<WebElement> removeTagsList;

	@FindBy(xpath = "//span[normalize-space()='Add']")
	protected WebElement addTagBtn;

	@FindBy(name = "price")
	protected WebElement priceInputField;

	@FindBy(name = "compareAtPrice")
	protected WebElement compareAtPriceInputField;

	@FindBy(xpath = "//span[contains(text(),'This product has a SKU or barcode')]/parent::span/preceding-sibling::span/span/input")
	protected WebElement openSKUCheckbox;

	@FindBy(id = "InventoryCardSku")
	protected WebElement skuInpField;

	@FindBy(id = "InventoryCardBarcode")
	protected WebElement barcodeInputField;

	@FindBy(css = "div input[name*='inventoryLevels[0]']")
	protected WebElement locationsInpFieldList;

	@FindBy(xpath = "(//tr[@class='RISTz']//td)[3]//button")
	protected WebElement availableInventoryBtn;

	@FindBy(xpath = "(//tr[@class='RISTz']//td)[3]//button")
	protected List<WebElement> availableInventoryBtnList;

	@FindBy(xpath = "//tr[@class = 'RISTz']/th/div/div")
	protected List<WebElement> wareHouseNameList;

	@FindBy(xpath = "//th[@scope='row' ]/span[text()='Total']")
	protected WebElement totalHeading;

//	@FindBy(css = "div.V5v1E>div>div>div:nth-of-type(2) input")
	@FindBy(xpath = "//label[text()='New']/ancestor::div[@class='Polaris-Labelled__LabelWrapper_bf6ys']/following-sibling::div//input")
	protected WebElement newInventoryInputField;
	
	@FindBy(xpath = "//label[text()='New']/ancestor::div[@class='Polaris-Labelled__LabelWrapper_bf6ys']/following-sibling::div//input")
	protected List<WebElement> newInventoryInputFieldList;

	@FindBy(xpath = "//label[text() = 'Reason']/parent::div/parent::div/parent::div/following-sibling::div/button[2]")
	protected WebElement confirmSaveBtnToUpdateInventory;

	@FindBy(id = "ShippingCardWeight")
	protected WebElement weightInpField;

	@FindBy(id = "ShippingCardWeightUnit")
	protected WebElement selectWtUnit;

	@FindBy(xpath = "//span[text()='Country/Region of origin']/parent::label/parent::div/parent::div/following-sibling::div/select")
	protected WebElement selectCountryOfOrigin;

	@FindBy(xpath = "//input[@placeholder='Size']")
	protected WebElement optionNameInputField;

	@FindBy(xpath = "//input[@placeholder='Classic']")
	protected WebElement optionValueInputField;

	@FindBy(xpath = "//input[@placeholder='Add another value']")
	protected WebElement addAnotherValueInputField;

	@FindBy(className = "ysDjQ")
	protected WebElement addAnotherOptionBtn;

	@FindBy(xpath = "//div[@aria-describedby='ProductStatusPickerHelpText']//select")
	protected WebElement selectProductStatus;

	@FindBy(xpath = "//input[@placeholder='Search product categories']")
	protected WebElement searchProductCategoriesInputField;

	@FindBy(css = "button[aria-label='Save']")
	protected WebElement saveBtn;

	@FindBy(xpath = "//span[text()='Save product']")
	protected WebElement saveProductBtn;

	@FindBy(xpath = "//div[contains(@class, '375yx gk5A5')]")
	protected List<WebElement> variantsList;

	@FindBy(id = "VariantEdit-SKU")
	protected WebElement variantSKUEditInputField;

	@FindBy(xpath = "(//div[contains(@id,'gid://shopify/ProductVariant/')]//label/span[1])[last()]")
	protected WebElement lastVariant;

	@FindBy(xpath = "//span[contains(text(),'Delete variant')]")
	protected WebElement deleteVariantBtn;

	@FindBy(xpath = "//div[@role='dialog']//span[contains(text(),'Delete variant')]")
	protected WebElement confirmDeleteVariantBtn;

	@FindBy(id = "VariantEdit-Barcode")
	protected WebElement variantBarcodeEditInputField;

	@FindBy(xpath = "//span[text() = 'Save as active']")
	protected WebElement setAsActiveBtn;

	@FindBy(xpath = "//span[text() = 'Product created']")
	protected WebElement productCreatedMsg;

	@FindBy(xpath = "//span[text() = 'Product saved']")
	protected WebElement productSaveMsg;

	@FindBy(xpath = "//span[text() = 'Quantity updated']")
	protected WebElement qtyUpdatedMsg;

	@FindBy(xpath = "//span[text() = 'Delete product']")
	protected WebElement deleteProductBtn;

	@FindBy(xpath = "(//span[text() = 'Delete product'])[2]")
	protected WebElement confirmDeleteProductBtn;

	@FindBy(xpath = "(//span[normalize-space()='Add options like size or color'])[2]")
	protected WebElement addOptionsBtn;

	@FindBy(xpath = "//input[@placeholder='Size']")
	protected WebElement sizeOptionInputField;

	@FindBy(xpath = "//input[@placeholder='Add another value']")
	protected WebElement addAnotherValInputField;

	@FindBy(xpath = "//div[text() = 'Size']")
	protected WebElement sizeOptionValue;

	@FindBy(xpath = "//input[@placeholder='Medium']")
	protected WebElement sizeOptionValueInputField;

	@FindBy(css = "button[aria-controls*='option-']")
	protected WebElement doneBtn;

	@FindBy(xpath = "//input[@placeholder='Color']")
	protected WebElement addColorInputField;

	@FindBy(xpath = "//div[text()='Color']")
	protected WebElement colorOptionValue;

	@FindBy(xpath = "//input[@placeholder='Black']")
	protected WebElement colorOptionValueINputField;

	@FindBy(xpath = "//div[contains(text(),'Showing')]/following-sibling::div[2]//span[text()='Edit']")
	protected WebElement editVariantsAttributeBtn;

	@FindBy(xpath = "//span[normalize-space()='Edit prices']")
	protected WebElement editPrices;

	@FindBy(xpath = "//legend[text()='Apply a price to all variants']/parent::fieldset//input")
	protected WebElement editPricesInputFieldForAll;

	@FindBy(id = "EditPriceModalBulkCurrencyField")
	protected WebElement editPriceModalBulkCurrencyInputField;

	@FindBy(id = "EditPriceModalApplyAllButton")
	protected WebElement editPriceModalApplyAllButton;

	@FindBy(xpath = "//div[@role= 'dialog']//span[text() = 'Done']")
	protected WebElement doneBtnModal;

	@FindBy(xpath = "//span[normalize-space()='Edit quantities']")
	protected WebElement editQuantities;

	@FindBy(xpath = "//div[contains(@id,'EditQuantitiesModalgid')]")
	protected List<WebElement> wareHousesList;

	@FindBy(id = "EditQuantitiesModalApplyToAllField")
	protected WebElement editQuantitiesModalApplyToAllFieldInputField;

	@FindBy(id = "EditQuantitiesModalApplyToAllButton")
	protected WebElement editQuantitiesModalApplyToAllButton;

	@FindBy(xpath = "//div[@role='dialog']//button[contains(@class,'Polaris-Button--primary_7k9zs')]")
	protected WebElement saveBtnModal;

	@FindBy(xpath = "//span[normalize-space()='Edit SKUs']")
	protected WebElement editSKUs;

	@FindBy(css = "div[role = 'dialog'] div[class*='Polaris-TextField'] input")
	protected List<WebElement> skuInputFieldList;

	@FindBy(xpath = "//span[normalize-space()='Edit barcodes']")
	protected WebElement editBarcodes;

	@FindBy(xpath = "//span[normalize-space()='Edit weight']")
	protected WebElement editWeight;

	@FindBy(xpath = "(//input[@placeholder='0.0'])[1]")
	protected WebElement editWeightModalBulkCurrencyInputField;

	@FindBy(xpath = "(//div[@role= 'dialog']//select)[1]")
	protected WebElement editWeightUnitModalBulkCurrencySelect;

	@FindBy(xpath = "//span[contains(text(),'Apply to all')]")
	protected WebElement editModalApplyToAllBtn;

	@FindBy(xpath = "//h1[normalize-space()='Locations']")
	protected WebElement locationsHeading;

	@FindBy(xpath = "//span[contains(text(),'Add location')]")
	protected WebElement addLocationBtn;

	@FindBy(xpath = "//input[@placeholder='Paris Warehouse']")
	protected WebElement locationNameInputField;

	@FindBy(name = "address1")
	protected WebElement addressInputField;

	@FindBy(name = "address2")
	protected WebElement apartmentNameInputField;

	@FindBy(name = "city")
	protected WebElement cityInputField;

	@FindBy(name = "province")
	protected WebElement selectCountry;

	@FindBy(name = "zip")
	protected WebElement pinCode;

	@FindBy(name = "phone")
	protected WebElement phoneInputField;

	@FindBy(xpath = "//nav[@role='navigation']")
	protected WebElement backBtn;

	@FindBy(xpath = "//div[@class = 'MteWP']//a")
	protected WebElement allLocationList;

	@FindBy(xpath = "//span[contains(text(),'Deactivate location')]")
	protected WebElement deactivateLocationBtn;

	@FindBy(xpath = "//div[contains(@class, 'Dialog__Modal_2v9yc')]//span[text() = 'Deactivate location']")
	protected WebElement confirmDeactivateLocationBtn;

	@FindBy(xpath = "//span[text() = 'Delete location']")
	protected WebElement deleteLocationBtn;

	@FindBy(xpath = "//div[contains(@class, 'Dialog__Modal_2v9yc')]//span[text() = 'Delete location']")
	protected WebElement confirmDeleteLocationBtn;

	@FindBy(xpath = "//input[contains(@id , 'gid://shopify/Product/')]")
	protected WebElement selectProductCheckbox;

	@FindBy(xpath = "//button[@aria-label='More actions']")
	protected WebElement moreActionsBtn;

	@FindBy(xpath = "//span[normalize-space()='Archive product']/parent::button")
	protected WebElement achieveProductBtn;

	@FindBy(xpath = "//div[@role = 'dialog']//span[normalize-space()='Archive product']/parent::button")
	protected WebElement confirmArchieveProductBtn;

	@FindBy(css = "button[aria-label='Manage']")
	protected WebElement manageBtn;

	@FindBy(xpath = "//span[text() = 'Manage sales channels']/ancestor::button")
	protected WebElement manageSalesChannelAndAppBtn;

	@FindBy(xpath = "//div[@role='dialog']//span[text() = 'Deselect all']")
	protected WebElement deSelectAllBtn;

	@FindBy(xpath = "//div[@class='s9eE8']//span[text() = 'CedCommerce Amazon Channel']")
	protected WebElement manageCedCommerceAmazonChannel;

	@FindBy(id = "InventoryCardAllowOutOfStockPurchases")
	protected WebElement continueSellingCheckBox;

	@FindBy(xpath = "(//span[normalize-space()='Continue selling when out of stock'])[1]")
	protected WebElement continueSellingCheckBoxLabel;
	
	@FindBy(css = "th[data-index-table-heading='true'] input[type='checkbox']")
	protected WebElement selectAllVariantsCheckBox;
	
	@FindBy(xpath = "//div[@role = 'dialog']//span[text() = 'Save']/parent::button")
	WebElement saveBtnShopify;
	
	@FindBy(xpath = "//span[text()='Bulk edit']/ancestor::div[contains(@class,'BulkActions__BulkActionButton')]/following-sibling::div//button")
	protected WebElement moreActionsBulkEdit;
	
	@FindBy(xpath = "//span[text()='Continue selling when out of stock']/ancestor::button")
	protected WebElement continueSellingButtonVariants;
	
	@FindBy(name="status")
	protected WebElement statusDropDown;
}
