package com.ami.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ami.pageobjects.reusableclasses.ReusableMethodsOR;

public class ProductListingPageOR extends ReusableMethodsOR {

	@FindBy(xpath = "//h1[text() = 'Listings']")
	protected WebElement listingsHeading;

	@FindBy(xpath = "//p[text() = 'Seller Name']/following-sibling::button")
	protected WebElement sellerNameBtn;

	@FindBy(xpath = "//p[text() = 'Seller Name']/following-sibling::button//p")
	protected WebElement acntNameOnSwitcher;

	@FindBy(xpath = "//li[@role = 'presentation']/button[@role = 'menuitem']/span/span/div/div[2]/span")
	protected WebElement sellerNameOpenMenu;

	@FindBy(xpath = "//span[text() = 'Link Products']")
	protected WebElement linkProductBannerBtn;

	@FindBy(xpath = "//h1[text() = 'Link Amazon Products']")
	protected WebElement linkAmzProductsPageHeading;

	@FindBy(id = "all")
	protected WebElement statusAll;

	@FindBy(id = "not_listed")
	protected WebElement statusNotListed;

	@FindBy(xpath = "//button[@id = 'not_listed']/span/div/span/span")
	protected WebElement notListedCount;

	@FindBy(id = "inactive")
	protected WebElement statusInactive;

	@FindBy(xpath = "//button[@id = 'inactive']/span/span/span/span[2]")
	protected WebElement inActiveCount;

	@FindBy(id = "Incomplete")
	protected WebElement statusInComplete;

	@FindBy(xpath = "//button[@id = 'Incomplete']/span/span/span")
	protected WebElement incompleteCount;

	@FindBy(id = "active")
	protected WebElement statusActive;

	@FindBy(id = "Error")
	protected WebElement statusError;

	@FindBy(xpath = "//input[@placeholder = 'Search with Title, Vendor, Product type or Barcode']")
	protected WebElement searchInputField;

	@FindBy(xpath = "//p[@style = 'font-size: 13px; margin: 1px 0px;']")
	protected List<WebElement> suggestionList;

	@FindBy(xpath = "//span[text() = 'More filters']/parent::span/parent::button")
	protected WebElement moreFiltersBtn;

	@FindBy(xpath = "//h3[text() = 'More filters']")
	protected List<WebElement> moreFiltersHeading;

	@FindBy(id = "InventoryToggleButton")
	protected WebElement inventoryToggleBtnFilter;

	@FindBy(id = "SKUToggleButton")
	protected WebElement skuToggleButtonFilter;

	@FindBy(id = "TagsToggleButton")
	protected WebElement tagsToggleButtonFilter;

	@FindBy(id = "Product typeToggleButton")
	protected WebElement productTypeToggleBtnFilter;

	@FindBy(id = "VendorToggleButton")
	protected WebElement vendorToggleButtonFilter;

	@FindBy(css = "button[id = 'Template nameToggleButton']")
	protected WebElement templateNameToggleButtonFilter;

	@FindBy(id = "Product StatusToggleButton")
	protected WebElement productStatusToggleButtonFilter;

	@FindBy(id = "ActivityToggleButton")
	protected WebElement activityToggleButtonFilter;

	@FindBy(xpath = "//button[@id = 'TypeToggleButton']")
	protected WebElement typeToggleButtonFilter;

	@FindBy(id = "Variant attributesToggleButton")
	protected WebElement variantAttributesToggleButtonFilter;

	@FindBy(xpath = "//div[@aria-label = 'More filters']//button[contains(@class, 'FilterTrigger')]//span[@class = 'Polaris-VisuallyHidden']/parent::span")
	protected List<WebElement> downArrowFilterList;

	@FindBy(xpath = "//div[@class = 'Polaris-Select']/select")
	protected WebElement selectFilterAttributes;

	@FindBy(xpath = "//input[@placeholder = 'enter value']")
	protected WebElement inputFieldMoreFilters;
	
	@FindBy(css = "span.Polaris-Tag__TagText+button[aria-label='Remove ']")
	protected WebElement removeStatusTagBtn;

	@FindBy(xpath = "//span[text() = 'Done']/parent::span/parent::button")
	protected WebElement doneBtn;

	@FindBy(xpath = "//span[text() = 'Clear all filters']")
	protected WebElement clearAllFiltersBtn;

	@FindBy(xpath = "(//tr[@class = 'ant-table-row ant-table-row-level-0']/td[7])[1]")
	protected WebElement inventoryAt0thPos;

	@FindBy(xpath = "//tr[@class = 'ant-table-row ant-table-row-level-0']/td[8]/div/div/span/span/span")
	protected List<WebElement> amazonStatusList;

	@FindBy(className = "GridCursorPointer")
	protected List<WebElement> titleOfProductsList;

	@FindBy(xpath = "//span[text() = 'SKU:']/span")
	protected List<WebElement> skuList;

	@FindBy(xpath = "//tr[@class = 'ant-table-row ant-table-row-level-0']")
	protected List<WebElement> productList;

	@FindBy(xpath = "//tr[@class = 'ant-table-row ant-table-row-level-0']//td[contains(@class,'row-expand-icon-cell')]")
	protected List<WebElement> variantExpandBtn;

	@FindBy(xpath = "//tr[@class = 'ant-table-expanded-row ant-table-expanded-row-level-1']")
	protected WebElement variantGrid;

	@FindBy(css = "table[style = 'table-layout: auto;']>thead>tr>th:nth-of-type(2)")
	protected WebElement imageHeadingVariantGrid;

	@FindBy(css = "table[style = 'table-layout: auto;']>thead>tr>th:nth-of-type(3)")
	protected WebElement skuHeadingVariantGrid;

	@FindBy(css = "table[style = 'table-layout: auto;']>thead>tr>th:nth-of-type(4)")
	protected WebElement productDetailsHeadingVariantGrid;

	@FindBy(css = "table[style = 'table-layout: auto;']>thead>tr>th:nth-of-type(5)")
	protected WebElement inventoryHeadingVariantGrid;

	@FindBy(xpath = "//table[@style = 'table-layout: auto;']//th")
	protected List<WebElement> headingVariantGrid;

	@FindBy(xpath = "//table[@style = 'table-layout: auto;']//td[5]")
	protected List<WebElement> inventoryValVariantGrid;

	@FindBy(xpath = "//tr[@class = 'ant-table-row ant-table-row-level-0']/td[7]")
	protected List<WebElement> inventoryParentProduct;

	@FindBy(css = "table[style = 'table-layout: auto;']>thead>tr>th:nth-of-type(5)")
	protected WebElement amazonStatusHeadingVariantGrid;

	@FindBy(xpath = "//table[@style = 'table-layout: auto;']//div[@class = 'product-img-container']/img")
	protected List<WebElement> imageContainerVariantsList;

	@FindBy(xpath = "//table[@style = 'table-layout: auto;']//img[@class = 'product-image']")
	protected List<WebElement> productImgVariantGrid;

	@FindBy(xpath = "//span[@class = 'Polaris-Tag__TagText']")
	protected List<WebElement> activeFilter;

	@FindBy(xpath = "(//button[contains(@aria-label,'Remove')])[2]")
	protected WebElement removeFilter;

	@FindBy(xpath = "//span[text() = 'Sync Status']")
	protected WebElement syncStatusBtn;

	@FindBy(xpath = "//span[text() = 'Amazon Lookup']")
	protected WebElement amazonLookupBtn;

	@FindBy(xpath = "//span[text() = 'Bulk Update']")
	protected WebElement bulkUpdateBtn;

	@FindBy(xpath = "//div[@role='dialog']//span[contains(text(),'Proceed')]")
	protected WebElement proceedBtnSyncStatusModal;

	@FindBy(xpath = "//span[contains(text(),'Import Products')]")
	protected WebElement importProductsBtn;

	@FindBy(xpath = "//span[contains(text(),'Export Products')]")
	protected WebElement exportProductsBtn;

	@FindBy(xpath = "//div[@role='dialog']//h2[normalize-space()='Import Products']")
	protected WebElement importProductModal;

	@FindBy(xpath = "//div[@role='dialog']//span[contains(text(),'Import Products')]/parent::span/parent::button")
	protected List<WebElement> importProductsBtnImportModal;

	@FindBy(css = "a[href = 'https://docs.cedcommerce.com/shopify/multi-account-amazon-cedcommerce/?section=listings-3']")
	protected WebElement needHelpLinkImportModal;

	@FindBy(xpath = "//div[text() = 'Add file']")
	protected WebElement addFileBtn;

	@FindBy(xpath = "//div[@role='dialog']//h2[normalize-space()='Export Products']")
	protected WebElement exportProductModal;

	@FindBy(xpath = "//span[normalize-space()='Current Page']/preceding-sibling::span/span/input")
	protected WebElement currentPageRadioBtnExport;

	@FindBy(xpath = "//span[normalize-space()='All Product']/preceding-sibling::span/span/input")
	protected WebElement allProductsRadioBtnExport;

	

	@FindBy(xpath = "//span[text() = 'Select all 50+ products']/parent::span/parent::button")
	protected List<WebElement> selectAll50PlusProductsBtn;

	@FindBy(xpath = "//span[@class = 'ant-checkbox ant-checkbox-checked']")
	protected List<WebElement> selectedCheckBoxList;

	@FindBy(css = "div[class = 'Polaris-ButtonGroup Polaris-ButtonGroup--segmented']")
	protected List<WebElement> selectedProductsCount;

	@FindBy(xpath = "//div[@class = 'Polaris-ButtonGroup Polaris-ButtonGroup--segmented']/div[2]")
	protected WebElement crossBtnSelectedProductsCount;

	@FindBy(xpath = "//div[@class = 'Polaris-ButtonGroup Polaris-ButtonGroup--segmented']//span[@class = 'Polaris-Button__Text']")
	protected WebElement selectedProductsCountText;

	@FindBy(xpath = "//span[contains(text() , 'Selected Products: ')]")
	protected WebElement selectedProductsRadioBtnExportModal;

	@FindBy(xpath = "//div[@role='dialog']//span[contains(text(),'Export Products')]/parent::span/parent::button")
	protected WebElement exportBtnExportModal;

	@FindBy(xpath = "//div[@role='dialog']//span[contains(text(),'cancel')]/parent::span/parent::button")
	protected WebElement cancelBtnExportModal;

	@FindBy(xpath = "//div[@role='dialog']//input[@type = 'checkbox']")
	protected List<WebElement> checkboxListExportModal;

	@FindBy(css = "a[href='https://docs.cedcommerce.com/shopify/multi-account-amazon-cedcommerce/?section=listings-3']")
	protected WebElement exportProductToCSVFileExportModal;

	@FindBy(css = "a[href='https://docs.cedcommerce.com/shopify/amazon-channel-cedcommerce/?section=csv-bulk-action']")
	protected WebElement bulkEditorExportModal;

	@FindBy(xpath = "//span[contains(.,'Sync Product') and contains(@class,'Button--removeUnderline')]")
	protected WebElement syncProductBtnSelectActions;

	@FindBy(css = "#amazonLookup")
	protected WebElement amazonLookupBtnSelectActions;

	@FindBy(xpath = "//span[text() = 'Sync Inventory']/parent::div/parent::button")
	protected WebElement syncInventoryBtnSelectActions;

	@FindBy(xpath = "//span[text() = 'Sync Price']/parent::div/parent::button")
	protected WebElement syncPriceBtnSelectActions;

	@FindBy(xpath = "//span[text() = 'Sync Image']/parent::div/parent::button")
	protected WebElement syncImgBtnSelectActions;

	@FindBy(xpath = "//span[text() = 'Delete Products']/parent::span/parent::button")
	protected WebElement deleteProductsBtnSelectActions;

	@FindBy(xpath = "//span[text()='Confirm']/parent::span/parent::button")
	protected WebElement confirmBtnDialogBox;

	@FindBy(xpath = "//th[text() = 'Image']")
	protected List<WebElement> imageHeading;

	@FindBy(xpath = "//th[text() = ' Title']")
	protected List<WebElement> titleHeading;

	@FindBy(xpath = "//th[text() = ' Product Details']")
	protected List<WebElement> productDetailsHeading;

	@FindBy(xpath = "//th[text() = 'Template']")
	protected List<WebElement> templateHeading;

	@FindBy(xpath = "//th[text() = 'Inventory']")
	protected List<WebElement> inventoryHeading;

	@FindBy(xpath = "//th[text() = 'Amazon Status']")
	protected List<WebElement> amazonStatusHeading;

	@FindBy(xpath = "//th[text() = 'Activity']")
	protected List<WebElement> activityHeading;

	@FindBy(xpath = "//th[text() = 'Actions']")
	protected List<WebElement> actionsHeading;

	@FindBy(css = "div[class='Custom__Template--Name'] div[class='Custom__Button--Add']")
	protected List<WebElement> templateNameListListing;

	@FindBy(xpath = "//span[text() = 'Product assigned to the selected template.']")
	protected WebElement productAssignedToTemplateToastMsg;

	@FindBy(xpath = "//h1[text() = 'Edit Product Template']")
	protected WebElement editTemplateHeading;

	@FindBy(css = "div[class='Custom__Template--Name']>div>p")
	protected List<WebElement> naList;

	@FindBy(css = "div[class='Custom__Template--Name']>div>span>button")
	protected List<WebElement> plusBtnList;

	@FindBy(xpath = "//tr[@class = 'ant-table-row ant-table-row-level-0']/td[6]/div/div/div[2]/span[1]/button[1]")
	protected List<WebElement> changeTemplateBtnList;

	@FindBy(xpath = "//tr[@class = 'ant-table-row ant-table-row-level-0']/td[6]/div/div/div[2]/span[2]/button")
	protected List<WebElement> deleteTemplateBtnList;

	@FindBy(xpath = "//span[text()='Remove Template']")
	protected WebElement removeTemplateBtn;

	@FindBy(xpath = "//tr[@class = 'ant-table-row ant-table-row-level-0']/td[6]/div//span[text() = 'N/A']/parent::div/parent::div/parent::td/preceding-sibling::td[2]//span[@class='GridCursorPointer']/span")
	protected List<WebElement> productTitleList;

//	Add template modal

	@FindBy(xpath = "//div[@role='dialog']//h2[text()='Add Template']")
	protected WebElement addTemplateHeadingAddTempModal;

	@FindBy(xpath = "//div[@role='dialog']//span[text() = 'Apply New Template']")
	protected WebElement addNewTemplateBtnAddTempModal;

	@FindBy(xpath = "//div[@class = 'Polaris-Frame-Toast' and text() = 'profile deleted successfully']")
	protected WebElement profileDeletedSuccessfullyToastMsg;

	@FindBy(xpath = "//div[@class = 'Polaris-Frame-Toast' and text() = 'data updated successfully']")
	protected WebElement dataUpdatedSuccessfullyToastMsg;

	@FindBy(xpath = "//div[@role='dialog']//select")
	protected WebElement selectTemplateAddTempModal;

	@FindBy(css = "nav[aria-label= 'Pagination']>div>div+div>div>span>div>div+div>span")
	protected WebElement totalPageCountListing;

	@FindBy(xpath = "//ul[@role = 'listbox']//li")
	protected List<WebElement> searchedProductList;

	@FindBy(xpath = "//tr//button[@class = 'Polaris-Link']")
	protected WebElement currentAssignedTemplateName;

	@FindBy(css = "div[role='dialog'] select")
	protected WebElement selectTemplateAddTemplateModal;

	@FindBy(css = "div[role='dialog'] select>option")
	protected List<WebElement> manualTemplatesInAddTemplateModal;

	@FindBy(xpath = "//span[text() = 'Add New Template']")
	protected WebElement addNewTeamplateBtn;

	@FindBy(xpath = "//div[@role = 'dialog']//button[@aria-label = 'Close']/span")
	protected WebElement closeModalBtn;

	@FindBy(xpath = "//span[text() = 'Apply New Template']/parent::span/parent::button")
	protected WebElement applyNewTeamplateBtn;

	@FindBy(css = ".ant-table-row.ant-table-row-level-0")
	protected List<WebElement> allProductList;
	
	@FindBy(css = "input[placeholder='placeholder']")
	protected WebElement inputTextFieldCurrentPage;

//	---------------------------------------------------------------------------------

	@FindBy(css = "input[placeholder='Search with Title, Vendor, Product type or Barcode']")
	protected WebElement searchInputFieldListing;

	@FindBy(css = "li[data-listbox-option-action='false']")
	protected List<WebElement> searchedProductSuggestionList;

	@FindBy(css = "button[aria-label='Remove ']")
	protected WebElement crossBtnOnSelectedProduct;

	@FindBy(css = "tr[class = 'ant-table-row ant-table-row-level-0']>td:last-of-type button")
	protected List<WebElement> kebabMenuListListing;

	@FindBy(xpath = "//span[text()='Edit Product']")
	protected WebElement editProduct;

	@FindBy(id = "offer_listing")
	protected WebElement offerListingRadioBtn;

	@FindBy(xpath = "//span[text() = 'Start Product Upload Tour']/parent::span/parent::button")
	protected WebElement productUploadTourBtn;
	
	@FindBy(css = "button[aria-label='Go to next step']")
	protected WebElement nextBtnProductUpload;
	
	@FindBy(css = "button[aria-label='Close Tour']")
	protected WebElement closeTourBtn;
	
	
	
	
}
