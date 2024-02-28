package com.ami.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ami.pageobjects.reusableclasses.ReusableMethodsOR;

public class OrderPageOR extends ReusableMethodsOR {

	@FindBy(xpath = "//p[text() = 'Source Order Id']/span")
	protected WebElement  sourceOrderId;

	@FindBy(xpath = "//h1[text() = 'Settings']")
	protected WebElement  settingsHeading;

	@FindBy(xpath = "//span[text() = 'Seller Name']/parent::div/following-sibling::div/button")
	protected WebElement  sellerNameSelector;

	@FindBy(xpath = "//li[@role = 'presentation']/button/span/span/div/div[2]/span")
	protected List<WebElement > sellerList;

	@FindBy(xpath = "//span[text() = 'Enable Order Syncing from App']/parent::div/preceding-sibling::div/button")
	protected WebElement  enableOrderSyncingfromAppToggleButton;

	@FindBy(xpath = "//h2[text() = 'Sync Order Settings to Shopify']")
	protected WebElement  syncOrderSettingsToShopifyHeading;

	@FindBy(xpath = "//h2[text() = 'Sync Shipment Settings']")
	protected WebElement  syncShipmentSettingsHeading;

	@FindBy(xpath = "//h2[text() = 'Order Cancelation & Refund Settings']")
	protected WebElement  orderCancelationAndRefundSettingsHeading;

	@FindBy(xpath = "//span[text() = 'Shopify Order source identifier']/parent::label/parent::div/parent::div/parent::div/parent::div/preceding-sibling::div/button")
	protected WebElement  shopifyOrderSourceIdentifierTogglebutton;

	@FindBy(xpath = "//span[text() = 'Shopify Order source identifier']/parent::label/parent::div/parent::div/following-sibling::div/div/div/input")
	protected WebElement  shopifyOrderSourceIdentifierInputField;

	@FindBy(xpath = "//div[text() = 'Some information is missing or wrong, kindly check them before proceeding.']")
	protected WebElement  errorMsgWhenShopifyOrderSourceIdentifierInputFieldIsBlank;

	@FindBy(xpath = "//p[text() = 'There’s no page at this address']")
	protected WebElement  orderNotFoundMsg;

	@FindBy(xpath = "//span[text() = 'Add Item Tax with Orders']/parent::div/parent::div/parent::div/parent::div/parent::div/preceding-sibling::div/button")
	protected WebElement  addItemTaxWithOrdersToggleButton;

	@FindBy(xpath = "//span[text() = 'Sync CPF Number']/parent::div/parent::div/parent::div/parent::div/parent::div/preceding-sibling::div/button")
	protected WebElement  syncCPFNumberToggleButton;

	@FindBy(xpath = "//span[text() = 'Add Tags in Order: ']/parent::div/parent::div/parent::div//parent::div/preceding-sibling::div/button")
	protected WebElement  addTagsInOrderToggleButton;

	@FindBy(xpath = "//span[text() = 'Amazon order ID']/parent::div/preceding-sibling::div/button")
	protected WebElement  amazonOrderIDToggleButton;

	@FindBy(xpath = "//span[text() = 'Seller ID']/parent::div/preceding-sibling::div/button")
	protected WebElement  sellerIDToggleButton;

	@FindBy(xpath = "//span[text() = 'Amazon']/parent::div/preceding-sibling::div/button")
	protected WebElement  amazonToggleButton;

	@FindBy(xpath = "//span[text() = 'Set Custom Tag']/parent::div/preceding-sibling::div/button")
	protected WebElement  setCustomTagToggleButton;

	@FindBy(xpath = "//input[@placeholder = \"Press 'Enter' to add tag\"]")
	protected WebElement  addCustomTagInputField;

	@FindBy(xpath = "//div[text() = 'Maximum two custom tags allowed']/parent::div/parent::span/parent::div/parent::div/following-sibling::div//span[@class = 'Polaris-Tag__TagText']")
	protected List<WebElement > setCustomTagList;

	@FindBy(xpath = "//button[contains(@aria-label , 'Remove')]")
	protected List<WebElement > removeTagsButton;

	@FindBy(xpath = "//span[text() = 'Set Amazon Order ID as Shopify Order Name']/parent::div/parent::div/parent::div/parent::div/parent::div/preceding-sibling::div/button")
	protected WebElement  setAmazonOrderIDAsShopifyOrderNameToggleButton;

	@FindBy(xpath = "//span[text() = 'Edit Shopify Order Name (Optional)']")
	protected WebElement  editShopifyOrderName;

	@FindBy(xpath = "//input[@placeholder = \"Enter order's prefix\"]")
	protected WebElement  orderPrefixInputField;

	@FindBy(xpath = "//input[@placeholder = \"Enter order's suffix\"]")
	protected WebElement  orderSuffixInputField;

	@FindBy(xpath = "//span[text() = 'Receive Email alerts for Failed Orders']/parent::div/parent::div/parent::div/preceding-sibling::div/button")
	protected WebElement  receiveEmailAlertsForFailedOrdersToggleButton;

	@FindBy(xpath = "//span[text() = 'Sync Shipment Status']/parent::div/parent::div/parent::div/parent::div/parent::div/preceding-sibling::div/button")
	protected WebElement  syncShipmentStatusToggleButton;

	@FindBy(xpath = "//span[text() = 'Tracking Details Required. ']/parent::div/parent::div/parent::div/parent::div/parent::div/preceding-sibling::div/button")
	protected WebElement  trackingDetailsRequiredToggleButton;

	@FindBy(xpath = "//a[@href = 'https://sellercentral.amazon.com/help/hub/reference/external/G202014610']")
	protected WebElement  linkVTRPage;

	@FindBy(xpath = "//span[text() = 'Map carrier codes between Shopify and Amazon ']/parent::div/preceding-sibling::div/button")
	protected WebElement  mapCarrierCodesBetweenShopifyAndAmazonToggleButton;

	@FindBy(xpath = "//span[text() = 'Shopify Carrier Code :']")
	protected WebElement  shopifyCarrierCode;

	@FindBy(xpath = "//input[@placeholder = 'Ex- Amazon Logistics']/parent::div/parent::div/preceding-sibling::div")
	protected WebElement  shopifyCarrierCodeSearchButton;

	@FindBy(xpath = "//input[@placeholder = 'Search or type shopify carrier code']")
	protected WebElement  searchOrTypeShopifyCarrierCodeInputField;

	@FindBy(xpath = "//div[text() = 'AGS']")
	protected WebElement  AGSOption;

	@FindBy(xpath = "//div[text() = 'AMZN']")
	protected WebElement  AMZNOption;

	@FindBy(xpath = "//input[@placeholder = 'Ex- Amazon Logistics']")
	protected WebElement  shopifyCarrierCodeInputField;

	@FindBy(xpath = "//span[text() = '//span[text() = 'Amazon Carrier Code :']]")
	protected WebElement  amazonCarrierCode;

	@FindBy(xpath = "//input[@placeholder = 'Ex- Amazon Shipping']/parent::div/parent::div/preceding-sibling::div")
	protected WebElement  amazonCarrierCodeSearchButton;

	@FindBy(xpath = "//input[@placeholder = 'Search or type amazon carrier code']")
	protected WebElement  searchOrTypeAmazonCarrierCodeInputField;

	@FindBy(xpath = "//div[text() = '4PX']")
	protected WebElement  px4Option;

	@FindBy(xpath = "//div[text() = 'A-1']")
	protected WebElement  A1Option;

	@FindBy(xpath = "//input[@placeholder = 'Ex- Amazon Shipping']")
	protected List<WebElement > amazonCarrierCodeInputField;

	@FindBy(xpath = "//input[@placeholder = 'Ex- Amazon Shipping']")
	protected WebElement  amzCarrierCodeInputField;

	@FindBy(xpath = "//span[text() = 'Add New Carrier Code']")
	protected WebElement  addNewCarrierCodeButton;

	@FindBy(xpath = "//input[@placeholder = 'Ex- Amazon Shipping']/parent::div/parent::div/parent::div/parent::div/parent::div/parent::div/parent::div/parent::div/parent::div/following-sibling::div/button")
	protected List<WebElement > deleteCarrierCodeButton;

	@FindBy(xpath = "//span[text() = 'Sync Order Cancelation status']/parent::div/parent::div/parent::div/parent::div/parent::div/preceding-sibling::div/button")
	protected WebElement  syncOrderCancellationStatusToggleButton;

	@FindBy(xpath = "//span[text() = 'Sync Order Refund']/parent::div/parent::div/parent::div/parent::div/parent::div/preceding-sibling::div/button")
	protected WebElement  syncOrderRefundToggleButton;

	@FindBy(xpath = "//span[text() = 'Map possible refund reasons between Shopify and Amazon ']/parent::div/preceding-sibling::div/button")
	protected WebElement  mapPossibleRefundReasonsBetweenShopifyAndAmazonToggleButton;

	@FindBy(xpath = "//input[@placeholder = 'Ex- Customer Return']")
	protected List<WebElement > shopifyRefundReasonInputField;

	@FindBy(xpath = "//input[@placeholder = 'Ex- Customer Return']/parent::div/parent::div/parent::div/div/div/span")
	protected WebElement  shopifyRefundReasonSearchButton;

	@FindBy(xpath = "//input[@placeholder = 'Search or type shopify refund reason']")
	protected WebElement  searchShopifyRefundReasonInputField;

	@FindBy(xpath = "//div[text() = 'Add custom']")
	protected WebElement  addCustomOption;

	@FindBy(xpath = "//div[text() = 'Customer Return']")
	protected WebElement  customerReturnOption;

	@FindBy(xpath = "//div[text() = 'Seller Refund']")
	protected WebElement  sellerRefundOption;

	@FindBy(xpath = "//input[@placeholder = 'Ex- No Return']")
	protected List<WebElement > amazonRefundReasonInputField;

	@FindBy(xpath = "//span[text() = 'Add New Refund Reason']")
	protected WebElement  addNewRefundReasonButton;

	@FindBy(xpath = "//input[@placeholder = 'Ex- No Return']/parent::div/parent::div/parent::div/div/div/span")
	protected WebElement  amazonRefundReasonSearchButton;

	@FindBy(xpath = "//input[@placeholder = 'Search or type amazon refund reason']")
	protected WebElement  searchOrTypeAmazonRefundReasonInputField;

	@FindBy(xpath = "//div[contains(text(),'ProductOutofStock')]")
	protected WebElement  productOutOfStockOptionAmazon;

	@FindBy(xpath = "//div[text() = 'CustomerReturn']")
	protected WebElement  customerReturnAmazon;

	@FindBy(xpath = "//input[@placeholder = 'Ex- No Return']/parent::div/parent::div/parent::div/parent::div/parent::div/parent::div/parent::div/parent::div/parent::div/following-sibling::div/button")
	protected List<WebElement > deleteNewRefundReasonButton;

	@FindBy(xpath = "//span[text() = 'Save']/parent::button")
	protected WebElement  saveButton;

	@FindBy(xpath = "//div[text() = 'Order settings saved successfully.']")
	protected WebElement  orderSettingsSavedSuccessfullyMsg;

	@FindBy(xpath = "//input[@placeholder = 'Search with Amazon Order Id']")
	protected WebElement  searchWithAmazonOrderIdInputField;

	@FindBy(xpath = "//span[text() = 'Search']/parent::span")
	protected WebElement  searchButton;

	@FindBy(xpath = "//td[text() = 'Refund reason cannot be empty']")
	protected WebElement  refundReasonCanNotBeEmpty;

	@FindBy(xpath = "//nav[@role = 'navigation']//span[@class = 'Polaris-Breadcrumbs__Icon']")
	protected WebElement  backButton;

	@FindBy(xpath = "//span[text() = 'Manage Failed Orders']")
	protected WebElement  manageFailedOrders;

	@FindBy(xpath = "(//h2[text() = 'Failed Orders'])[2]")
	protected WebElement  manageFailedOrdersHeadingOverview;

	@FindBy(xpath = "//p[text() = 'No Orders Found']")
	protected WebElement  noOrdersFound;

	@FindBy(xpath = "//span[text() = 'Tax']")
	protected WebElement  tax;

//	@FindBy(xpath = "(//button[@id='refund-restock'])[2]")
	@FindBy(xpath = "(//button[@id='RefundRestock'])[2]")
	
	protected WebElement  refundButtonShopify;

	@FindBy(xpath = "//h1[text() = 'Refund']")
	protected WebElement  refundPageHeading;
	
	@FindBy(name="shippingRefundAmount")
	protected WebElement  refundAmtInputField;
	
	@FindBy(css = "input[name='transactions[0].amount']")
	protected WebElement  refundAmtAmazonInputField;

	@FindBy(name="refundReason")
	protected WebElement  reasonForRefundInputField;
	
	@FindBy(xpath = "//span[contains(text(),'Refund')]/parent::button")
	protected WebElement  refundBtn;
	
	@FindBy(xpath = "//span[contains(text(),'Refunded')]")
	protected WebElement refundedLabel;

	@FindBy(xpath = "//p[text() = 'Source Order Id']/span")
	protected WebElement  amazonOrderId;

	@FindBy(xpath = "//input[@type = 'number']")
	protected WebElement  refundOrderQty;

	@FindBy(xpath = "//span[text() = 'Refund $0.00 USD']/parent::span/parent::button")
	protected WebElement  confirmRefundButton;

	@FindBy(xpath = "//span[text() = 'Manage Failed Refund Orders']")
	protected WebElement  manageFailedRefundOrders;

	@FindBy(xpath = "//p[text() = 'No Failed Refund Orders Found!']")
	protected WebElement  noFailedRefundOrdersFound;

	@FindBy(xpath = "//h1[contains(@class , 'Header-Title')]")
	protected WebElement  titleOfOrder;

	@FindBy(xpath = "//span[text() = 'Shopify Refund Reason :']")
	protected WebElement  shopifyRefundReasonsTitle;

	@FindBy(xpath = "//span[text() = 'Shopify Refund Reason :']/parent::div/parent::div/parent::div/following-sibling::div//button")
	protected WebElement  shopifyRefundReasonsSearchIcon;

	@FindBy(xpath = "(//td[@class = 'ant-table-cell'])[2]")
	protected List<WebElement > failedRefundOrderAmzId;

	@FindBy(xpath = "//span[text() = 'Amazon Refund Reason :']")
	protected WebElement  amazonRefundReasonsTitle;

	@FindBy(xpath = "//span[text() = 'Amazon Refund Reason :']/parent::div/parent::div/parent::div/following-sibling::div//div[@class = 'Polaris-FormLayout__Item'][2]/div//button")
	protected WebElement  amazonRefundReasonsSearchIcon;

	@FindBy(xpath = "//ul[@role = 'listbox']/li")
	protected List<WebElement > carrierCodes;

	@FindBy(id = "failedorders")
	protected WebElement  failedOrdersHeadingOverview;

	@FindBy(xpath = "//span[text() = 'Failed Orders']")
	protected WebElement  failedOrders;

	@FindBy(xpath = "//span[text() = 'Failed Orders']/parent::div/following-sibling::div/button/span/span")
	protected WebElement  failedOrdersCountOverview;

	@FindBy(xpath = "//span[text() = 'Failed Refund Orders']/parent::div/following-sibling::div/button/span/span")
	protected WebElement  failedRefundOrdersCountOverview;

	@FindBy(xpath = "//h1[text() = 'Failed Orders']")
	protected WebElement  failedOrdersPageHeading;

	@FindBy(xpath = "(//td[@class = 'ant-table-cell'])[1]")
	protected List<WebElement > failedOrderAmzId;

	@FindBy(xpath = "//span[text() = 'Archived Orders']/parent::span/parent::button")
	protected WebElement  archivedOrderButtonFailedOrders;

	@FindBy(xpath = "//p[text() = 'No Orders Found']")
	protected WebElement  noOrdersFoundMsg;

	@FindBy(xpath = "//h1[text() = 'Archived Orders']")
	protected WebElement  archivedOrdersHeading;

	@FindBy(xpath = "//th[text() = 'Order Id']")
	protected WebElement  orderIdArchivedOrdersColHeading;

	@FindBy(xpath = "//th[text() = 'Date']")
	protected WebElement  dateHeadingArchivedCol;

	@FindBy(xpath = "//th[text() = 'Actions']")
	protected WebElement  actionsHeadingArchivedOrders;

	@FindBy(xpath = "//span[text() = 'View']/parent::span/parent::button")
	protected List<WebElement > viewButtonArchivedOrder;

	@FindBy(xpath = "//div[@aria-live = 'polite']/span")
	protected WebElement  paginationCount;

	@FindBy(id = "nextURL")
	protected WebElement  nextPageArwButtonArchieved;

	@FindBy(id = "previousURL")
	protected WebElement  prevPageArwButtonArchieved;

	@FindBy(xpath = "//p[text() = 'View your order']")
	protected WebElement  viewYourOrderPageHeading;

	@FindBy(xpath = "//h3[@aria-label = 'Customer']/parent::div/following-sibling::div")
	protected WebElement  customerName;

	@FindBy(xpath = "//h3[@aria-label = 'SHIPPING ADDRESS']/parent::div/following-sibling::div[4]")
	protected WebElement  customerAddress;

	@FindBy(xpath = "//span[text() = 'Amazon EarliestDeliveryDate']/parent::div/following-sibling::div/span")
	protected WebElement  earliestDeliveryDate;

	@FindBy(xpath = "//span[text() = 'Amazon LatestShipDate']/parent::div/following-sibling::div/span")
	protected WebElement  latestShipDate;

	@FindBy(xpath = "//span[text() = 'Amazon EarliestShipDate']/parent::div/following-sibling::div/span")
	protected WebElement  earliestShipDate;

	@FindBy(xpath = "//span[text() = 'Amazon LatestDeliveryDate']/parent::div/following-sibling::div/span")
	protected WebElement  latestDeliveryDate;

	@FindBy(xpath = "//input[@placeholder = 'Search with Amazon Order Id']")
	protected WebElement  searchWithAmzOrderIdInputField;

	@FindBy(xpath = "//span[text() = 'Manage Failed Canceled Orders']")
	protected WebElement  manageFailedCanceledOrders;

	@FindBy(xpath = "//span[text() = 'Failed Canceled Orders']/parent::div/following-sibling::div/button/span/span")
	protected WebElement  failedCanceledOrderCounts;

	@FindBy(xpath = "//h1[text() = 'Failed Canceled Orders']")
	protected WebElement  failedCanceledOrdersPageHeading;

	@FindBy(xpath = "//p[text() = 'No Failed Canceled Orders Found!']")
	protected WebElement  noFailedCanceledOrdersFound;

	@FindBy(xpath = "//span[text() = 'Clear']/parent::span/parent::button")
	protected WebElement  clearBtn;

	@FindBy(xpath = "//input[@placeholder = 'Search with Shopify Order Id']")
	protected WebElement  searchWithShopifyOrderIdInputField;

	@FindBy(xpath = "//span[text() = 'Create Refund']")
	protected WebElement  createRefundBtnAmz;

	@FindBy(xpath = "//span[@class='ant-spin-dot ant-spin-dot-spin']")
	protected WebElement  nestedLodingIcon;

	@FindBy(xpath = "//h2[contains(text(), 'Refund Order')]")
	protected WebElement  createRefundModalAmz;

	@FindBy(css = "div[role='dialog'] tbody input[type='checkbox']")
	protected WebElement  selectProductToRefundCheckboxModal;

	@FindBy(xpath = "(//div[@role='dialog']//select)[2]")
	protected WebElement  selectRefundReasonAmz;
	
	@FindBy(css = "div[role='dialog'] tr[class*='ant-table-row ant-table-row-level-0']>td:nth-of-type(5) p")
	protected WebElement  totalItemPrice;

	@FindBy(xpath = "//input[@placeholder = 'Enter refund amount']")
	protected WebElement  enterRefundAmtRefundModalAmz;

	@FindBy(xpath = "//span[contains(text(),'Confirm')]")
	protected WebElement  confirmBtnRefundModal;

	@FindBy(xpath = "//span[contains(text(),'Yes')]")
	protected WebElement  confirmYesBtnRefundModal;

	@FindBy(xpath = "//span[text() = 'Order refund process initiated.']")
	protected WebElement  successfullyRefundedToastMsg;

	@FindBy(xpath = "//span[text() = 'More actions']")
	protected WebElement  moreActionsBtnShopify;

	@FindBy(xpath = "//span[normalize-space()='Cancel order']")
	protected WebElement  cancelOrderBtnShopify;

	@FindBy(xpath = "(//h2[normalize-space()='Cancel order'])[1]")
	protected WebElement  cancelOrderHeadingShopifyModal;

	@FindBy(xpath = "//span[contains(text(),'Keep order')]")
	protected WebElement  keepOrderBtnShopifyModal;

	@FindBy(xpath = "//span[contains(text(),'Cancel order')]")
	protected WebElement  cancelOrderBtnShopifyModal;

	@FindBy(xpath = "//span[text() = 'Canceled']")
	protected WebElement  canceledOrder;
	
// -----------------------------<orders page>---------------------------------
	
	@FindBy (id="all")
	protected WebElement allBtnOrders;
	
	@FindBy(id="errors")
	protected WebElement errsBtnOrders;
	
	@FindBy(css = "input[placeholder='Search using the Amazon order ID']")
	protected WebElement  searchOrderInputField;
	
	@FindBy(xpath = "//span[text()='More filters']/ancestor::button")
	protected WebElement  moreFiltersBtnOrders;
	
	@FindBy(css = "div.FilterSheet")
	protected WebElement  moreFiltersModalOrders;
	
	@FindBy(xpath = "//h2[text()='Status']/ancestor::div[@class='FilterSheet__BodyAccordian']")
	protected WebElement  statusMoreFiltersModalOrders;
	
	@FindBy(xpath = "((//div[@id='basic-collapsible'])[1]//select)[1]")
	protected WebElement  selectMarketplaceStatusModalOrders;
	
	@FindBy(xpath = "((//div[@id='basic-collapsible'])[1]//select)[2]")
	protected WebElement  selectOrderStatusModalOrders;
	
	@FindBy(xpath = "//h2[text()='Sync Status']/ancestor::div[@class='FilterSheet__BodyAccordian']")
	protected WebElement  syncStatusMoreFiltersModalOrders;
	
	@FindBy(xpath = "(//div[@id='basic-collapsible'])[2]//select")
	protected WebElement  selectSyncStatusMoreFiltersModalOrders;
	
	@FindBy(xpath = "//h2[text()='Price']/ancestor::div[@class='FilterSheet__BodyAccordian']")
	protected WebElement  priceMoreFiltersModalOrders;

	@FindBy(xpath = "(//div[@id='basic-collapsible'])[3]//select")
	protected WebElement  selectPriceMoreFiltersModalOrders;
	
	@FindBy(css = "input[placeholder='enter value'][type='number']")
	protected WebElement  priceInputFieldMoreFiltersModalOrders;
	
	@FindBy(xpath = "//h2[text()='Tags']/ancestor::div[@class='FilterSheet__BodyAccordian']")
	protected WebElement  tagsMoreFiltersModalOrders;
	
	@FindBy(xpath = "(//div[@id='basic-collapsible'])[4]//select")
	protected WebElement  selectTagsMoreFiltersModalOrders;
	
	@FindBy(xpath = "//h2[text()='Order Date']/ancestor::div[@class='FilterSheet__BodyAccordian']")
	protected WebElement  ordersDateMoreFiltersModalOrders;
	
	@FindBy(xpath = "//span[text()='Select Date']/ancestor::button")
	protected WebElement  selectDateBtnMoreFiltersModalOrders;
	
	@FindBy(css = "input[value='today']")
	protected WebElement  todaySelectDateMoreFiltersModalOrders;
	
	@FindBy(css = "input[value='last7']")
	protected WebElement  last7Days;
	
	@FindBy(css = "input[value='last30']")
	protected WebElement  last30Days;
	
	@FindBy(css = "input[value='last90']")
	protected WebElement  last90Days;
	
	@FindBy(css = "input[value='last180']")
	protected WebElement  last180Days;
	
	@FindBy(css = "input[value='last12Mon']")
	protected WebElement  last12Months;
	
	@FindBy(css = "input[value='custom']")
	protected WebElement  custom;
	
	@FindBy(css = "div[class='Polaris-Popover__Section'] button[class='Polaris-Button']")
	protected WebElement  cancelBtn;
	
	@FindBy(css = "div[class='Polaris-Popover__Section'] button[class='Polaris-Button Polaris-Button--primary']")
	protected WebElement  applyBtn;
	
	@FindBy(css = "div[class='FilterSheet__footer'] button[class='Polaris-Button']")
	protected WebElement  clearAllFiltersBtn;
	
	@FindBy(css = "div[class='FilterSheet__footer'] button[class='Polaris-Button Polaris-Button--primary']")
	protected WebElement  doneBtn;
	
	@FindBy(xpath = "//span[text()='Archived Orders']/ancestor::button")
	protected WebElement  archievedOrdersBtnOrders;
	
	@FindBy(xpath = "//span[text()='Import Orders']/ancestor::button")
	protected WebElement  importOrdersBtnOrders;
	
	@FindBy(css = "div[class='Custom__Button--BuyShipment']")
	protected List<WebElement>  buyShippingBtnsOrders;
	
	@FindBy(css = "button[class='Polaris-Button Polaris-Button--sizeSlim Polaris-Button--iconOnly']")
	protected List<WebElement>  kebabBtnsOrders;
	
	@FindBy(xpath = "//span[text()='Resolve']/ancestor::button")
	protected List<WebElement>  resolveBtnsOrders;
	
	// -----------------------------<orders page>---------------------------------
}
