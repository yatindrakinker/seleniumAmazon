package com.ami.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ami.pageobjects.reusableclasses.ReusableMethodsOR;

public class LocalSellingPageOR extends ReusableMethodsOR {

//	--------------<Overview>---------------------------------

	@FindBy(xpath = "//h1[text()='Overview']")
	protected WebElement overViewPage;

	@FindBy(id = "activities")
	protected WebElement activities;

	@FindBy(id = "local_delivery")
	protected WebElement localDeliverySection;

	@FindBy(id = "local_delivery")
	protected List<WebElement> localDeliverySectionList;

	@FindBy(xpath = "//button[text()='Manage']")
	protected WebElement manageBtnLocalSelling;

	@FindBy(xpath = "//h6[text()='Local Selling']/parent::div/following-sibling::div/div[@class = 'custom-badging'][1]//button")
	protected WebElement ordersLocalSelling;

	@FindBy(id = "all-customers-1")
	protected WebElement ordersTabLocalSellingPage;

	@FindBy(css = "button[id = 'all-customers-1'][aria-label = 'All customers']")
	protected WebElement allTabOrders;

	@FindBy(xpath = "//span[text()='Ready For Pickup']/ancestor::button[@id='accepts-marketing-1']")
	protected WebElement readyForPickupTabOrders;

	@FindBy(id = "accepts-marketing-2")
	protected WebElement pendingTabOrders;

	@FindBy(id = "accepts-marketing-3")
	protected WebElement pickedUpTabOrders;



	@FindBy(css = "input[placeholder='Search with order ID']")
	protected WebElement searchOrdersInputFieldLocalSelling;

	@FindBy(css = "tr[class='ant-table-row ant-table-row-level-0']")
	protected List<WebElement> ordersList;
	
	@FindBy(css = ".ant-table-row.ant-table-row-level-0>td>button")
	protected List<WebElement> orderIdList;

	@FindBy(css = "button[class='Polaris-TextField__ClearButton']")
	protected WebElement clearInputFieldBtnOrder;

	@FindBy(id = "accepts-marketing-1")
	protected WebElement storesTabLocalSellingPage;

	@FindBy(xpath = "//span[contains(text(),'More filters')]/parent::span/parent::button")
	protected WebElement moreFiltersButton;

	@FindBy(css = "div[class='Custom__Card--StoreGrid']")
	protected List<WebElement> sellerList;

	@FindBy(xpath = "//h6[text()='Local Selling']/parent::div/following-sibling::div/div[@class = 'custom-badging'][2]//button")
	protected WebElement storesLocalSelling;

	@FindBy(xpath = "//h1[text()='Local Selling']")
	protected WebElement localSellingPageHeading;

	@FindBy(css = "h1[class^='Polaris-Header-Title']")
	protected WebElement pageTitle;

	@FindBy(css = "input[placeholder='Search by Store name or store code']")
	protected WebElement searchStoresInputFieldLocalSelling;

	@FindBy(css = "button[class='Polaris-TextField__ClearButton']")
	protected WebElement clearInputFieldBtnStore;

	@FindBy(css = "img[src='/902e6225075f4dc2f9fe944f578a4ad2.png']")
	protected List<WebElement> notFoundImg;

	@FindBy(css = "button[aria-label='Cancel']")
	protected WebElement crossBtnMoreFilterModal;

	@FindBy(id = "statusToggleButton")
	protected WebElement statusToggleButtonFilterModal;

	@FindBy(id = "statusToggleButton")
	protected List<WebElement> statusToggleButtonFilterModalList;

	@FindBy(id = "StoreToggleButton")
	protected WebElement storeToggleButton;

	@FindBy(tagName = "select")
	protected WebElement selectFilterModal;

	@FindBy(xpath = "//span[text()='Done']/parent::span/parent::button")
	protected WebElement doneBtnFilterModal;

	@FindBy(xpath = "//span[text()='Clear all filters']/parent::span/parent::button")
	protected WebElement clearFiltersBtn;

	@FindBy(xpath = "//span[text()='Fetch Stores']/ancestor::button")
	protected WebElement fetchStoresBtn;

	@FindBy(xpath = "//span[text()='Create new store']/ancestor::button")
	protected WebElement createNewStoreBtn;

	@FindBy(css = "div[class='Custom__Card--StoreGrid']>div>div>div:nth-of-type(2) p+span>span+span")
	protected List<WebElement> statusOfStores;

	@FindBy(css = "button[aria-label='Clear Store Status']")
	protected WebElement clearStatusBtn;

	@FindBy(css = "button[aria-label='Clear Store']")
	protected WebElement clearStoreBtn;

//	--------------<ARCHIEVED ORDERS>---------------------------------

	@FindBy(xpath = "//span[text()='Archived Orders']/ancestor::button")
	protected WebElement archievedOrderBtn;

	@FindBy(css = "input[placeholder='Search with Amazon Order Id']")
	protected WebElement searchArchievedOrderInputField;

	@FindBy(css = "tr[class='ant-table-row ant-table-row-level-0']>td:nth-of-type(3)  button")
	protected List<WebElement> viewArchievedOrderBtns;

//	--------------<IMPORT BUTTON MODAL>---------------------------------

	@FindBy(xpath = "//span[text()='Import Orders']/ancestor::button")
	protected WebElement importOrdersBtn;
	
	@FindBy(xpath = "//div[@role='dialog']//h2[text()='Import Order(s)']")
	List<WebElement> importOrdersModal;

	@FindBy(css = "div[role='dialog'] input[placeholder='Enter Amazon Order Id']")
	protected WebElement inputFieldAmazonOrderId;

	@FindBy(xpath = "//div[@role='dialog']//span[text()='Cancel']/parent::span/parent::button")
	protected WebElement cancelBtnModal;

	@FindBy(xpath = "//div[@role='dialog']//span[text()='Confirm']/parent::span/parent::button")
	protected WebElement confirmBtnModal;
	
	@FindBy(xpath = "//span[text()='Kindly fill Amazon order id to proceed importing of orders.']")
	protected WebElement inputFieldAmazonIsEmptyToastMsg;
	
	@FindBy(xpath = "//span[text()='Order not found']")
	protected WebElement orderNotFoundToastMsg;


//	--------------</IMPORT BUTTON MODAL>---------------------------------

//	--------------<VIEW ORDERS PAGE>---------------------------------

	@FindBy(id = "amazon")
	protected WebElement amazonTab;

	@FindBy(id = "amazon-panel")
	protected WebElement amazonOrderDetails;

	@FindBy(id = "shopify")
	protected WebElement shopifyTab;

	@FindBy(id = "shopify-panel")
	protected WebElement shopifyOrderDetails;

//	--------------</VIEW ORDERS PAGE>---------------------------------

//	--------------</ARCHIEVED ORDERS>---------------------------------
//	--------------<new Store>---------------------------------

	@FindBy(css = "h1[class = 'Polaris-Header-Title Polaris-Header-Title__TitleWithSubtitle']")
	protected WebElement pageHeading;

	@FindBy(css = "input[placeholder='Set a store name']")
	protected WebElement storeNameInputField;

	@FindBy(css = "input[placeholder='Set a store name']+div")
	protected WebElement totalCharacters;

	@FindBy(css = "input[placeholder='Set store code']")
	protected WebElement storeCodeInputField;

	@FindBy(css = "input[placeholder='Street Address']")
	protected WebElement streetAddressInputField;

	@FindBy(xpath = "(//input[@placeholder='Street Address'])[2]")
	protected WebElement streetAddressInputField2;

	@FindBy(css = "input[placeholder='Landmark (optional)']")
	protected WebElement streetAddressInputField3;

	@FindBy(css = "input[placeholder='Add country']")
	protected WebElement addCountryInputField;

	@FindBy(css = "li[aria-label='India']")
	protected WebElement indiaCountry;

	@FindBy(css = "input[placeholder='Add state']")
	protected WebElement addStateInputField;

	@FindBy(css = "li[aria-label='Uttar Pradesh']")
	protected WebElement up;

	@FindBy(css = "input[placeholder='Add city']")
	protected WebElement addCityInputField;

	@FindBy(css = "li[aria-label='Lucknow']")
	protected WebElement lko;

	@FindBy(xpath = "//label[text()='UTC Time zone']/parent::div/parent::div/following-sibling::div/select")
	protected WebElement utcTimeZone;

	@FindBy(css = "input[placeholder='Add Zip Code']")
	protected WebElement addZipCode;

	@FindBy(css = "input[placeholder='1234567890']")
	protected WebElement contactNumEle;

	@FindBy(css = "input[placeholder='Add e-mail id']")
	protected WebElement emailId;

	@FindBy(xpath = "//span[text()='Days']")
	protected WebElement choseOnTheBasisOfDays;

	@FindBy(xpath = "//span[text()='Hours']")
	protected WebElement choseOnTheBasisOfHours;

	@FindBy(xpath = "//span[text()='Minutes']")
	protected WebElement choseOnTheBasisOfMinutes;

	@FindBy(xpath = "//label[text()='Store Status']/parent::div/parent::div/following-sibling::div/select")
	protected WebElement storeStatus;

	@FindBy(xpath = "//span[text()='Save and create store']/parent::span/parent::button")
	protected WebElement saveAndCreateStoreBtn;

	@FindBy(xpath = "//span[text()='Store has not been created']")
	protected WebElement storeNotCreatedMsg;

	@FindBy(xpath = "//span[text()='Store created successfully']")
	protected WebElement storeCreatedSuccessfullyMsg;

	@FindBy(xpath = "//span[text()='Delete Store Successfully']")
	protected WebElement storeDeleatedSuccessfullyMsg;

	@FindBy(xpath = "//span[text()='Status Updates Successfully']")
	protected WebElement statusUpdatedSuccessfullyMsg;

	@FindBy(css = "div.Custom__Card--StoreGrid h6")
	protected List<WebElement> storeNameOnLocalSellingPage;

	@FindBy(xpath = "//span[text()='Kindly fill all the fields correctly.']")
	protected WebElement errToastMsgToFillMandatoryField;

	@FindBy(xpath = "//div[text()='Store name is a mandatory field and must be unique.']")
	protected WebElement storeNameIsMandatoryErrMsg;

	@FindBy(xpath = "//div[text()='Store name does not contain <> symbols']")
	protected WebElement storeNameDoesNotContains;

	@FindBy(xpath = "//div[text()='Store code must be in correct format.']")
	protected WebElement storeCodeIsNotInCorrectFormErrMsg;

	@FindBy(xpath = "//div[text()='Store code is a mandatory field and must be unique.']")
	protected WebElement storeCodeIsMandatoryErrMsg;

	@FindBy(xpath = "//div[text()='Address line 1 must contain only 180 characters']")
	protected WebElement addressLine1CanHave180CharsErrMsg;

	@FindBy(xpath = "//div[text()='Address line 2 must contain only 60 characters']")
	protected WebElement addressLine2CanHave60CharsErrMsg;

	@FindBy(xpath = "//div[text()='Address line 3 must contain only 60 characters.']")
	protected WebElement addressLine3CanHave60CharsErrMsg;

	@FindBy(xpath = "//div[text()='Address line 1 is a mandatory field.']")
	protected WebElement addressLine1IsMandatoryErrMsg;

	@FindBy(xpath = "//div[text()='Country is a mandatory field.']")
	protected WebElement countryIsMandatoryErrMsg;

	@FindBy(xpath = "//div[text()='State is a mandatory field.']")
	protected WebElement stateIsMandatoryErrMsg;

	@FindBy(xpath = "//div[text()='City is a mandatory field.']")
	protected WebElement cityIsMandatoryErrMsg;

	@FindBy(xpath = "//div[text()='UTC is a mandatory field.']")
	protected WebElement utcIsMandatoryErrMsg;

	@FindBy(xpath = "//div[text()='Zip code is a mandatory field.']")
	protected WebElement zipCodeIsMandatoryErrMsg;

	@FindBy(xpath = "//div[text()='Zip code must contain only numbers and must be of 6 digits.']")
	protected WebElement zipCodeMustContains6DigitsErrMsg;

	@FindBy(xpath = "//div[text()='Contact is a mandatory field.']")
	protected WebElement contactIsMandatoryErrMsg;

	@FindBy(xpath = "//div[text()='Contact must be in correct format.']")
	protected WebElement contactMustBeInCorrectFormatErrMsg;

	@FindBy(xpath = "//div[text()='E-mail id is a mandatory field.']")
	protected WebElement eMailIsMandatoryErrMsg;

	@FindBy(xpath = "//div[text()='E-mail id must be in correct format.']")
	protected WebElement eMailMustBeInCorrectFormatErrMsg;

	@FindBy(xpath = "//span[text()='Sun']/ancestor::div[@id = 'workday']")
	protected WebElement sunday;

	@FindBy(xpath = "(//p[contains(text(),'Sunday')]/parent::div/following-sibling::div//h6[text()='STORE OPEN TIME']/parent::div//select)[1]")
	protected WebElement sundayStoreOpenTimeInHours;

	@FindBy(xpath = "(//p[contains(text(),'Sunday')]/parent::div/following-sibling::div//h6[text()='STORE OPEN TIME']/parent::div//select)[2]")
	protected WebElement sundayStoreOpenTimeInMin;

	@FindBy(xpath = "(//p[contains(text(),'Sunday')]/parent::div/following-sibling::div//h6[text()='STORE CLOSE TIME']/parent::div//select)[1]")
	protected WebElement sundayStoreCloseTimeInHours;

	@FindBy(xpath = "(//p[contains(text(),'Sunday')]/parent::div/following-sibling::div//h6[text()='STORE CLOSE TIME']/parent::div//select)[2]")
	protected WebElement sundayStoreCloseTimeInMin;

	@FindBy(xpath = "//p[contains(text(),'Sunday')]/parent::div/following-sibling::div//h6[text()='STORE CLOSE TIME']/parent::div/div[@id='myFieldIDError']")
	protected WebElement sundayStoreErrWhenClosingAndOpeningTimeIsSame;

//	@FindBy(xpath = "//div[text()='Store closing time must be ahead of store opening time']")
//	protected WebElement errWhenClosingAndOpeningTimeIsSame

	@FindBy(xpath = "//span[text()='Mon']/ancestor::div[@id = 'workday']")
	protected WebElement monday;

	@FindBy(xpath = "(//p[contains(text(),'Monday')]/parent::div/following-sibling::div//h6[text()='STORE OPEN TIME']/parent::div//select)[1]")
	protected WebElement mondayStoreOpenTimeInHours;

	@FindBy(xpath = "(//p[contains(text(),'Monday')]/parent::div/following-sibling::div//h6[text()='STORE OPEN TIME']/parent::div//select)[2]")
	protected WebElement mondayStoreOpenTimeInMin;

	@FindBy(xpath = "(//p[contains(text(),'Monday')]/parent::div/following-sibling::div//h6[text()='STORE CLOSE TIME']/parent::div//select)[1]")
	protected WebElement mondayStoreCloseTimeInHours;

	@FindBy(xpath = "(//p[contains(text(),'Monday')]/parent::div/following-sibling::div//h6[text()='STORE CLOSE TIME']/parent::div//select)[2]")
	protected WebElement mondayStoreCloseTimeInMin;

	@FindBy(xpath = "//span[text()='Tue']/ancestor::div[@id = 'workday']")
	protected WebElement tuesday;

	@FindBy(xpath = "(//p[contains(text(),'Tuesday')]/parent::div/following-sibling::div//h6[text()='STORE OPEN TIME']/parent::div//select)[1]")
	protected WebElement tuesdayStoreOpenTimeInHours;

	@FindBy(xpath = "(//p[contains(text(),'Tuesday')]/parent::div/following-sibling::div//h6[text()='STORE OPEN TIME']/parent::div//select)[2]")
	protected WebElement tuesdayStoreOpenTimeInMin;

	@FindBy(xpath = "(//p[contains(text(),'Tuesday')]/parent::div/following-sibling::div//h6[text()='STORE CLOSE TIME']/parent::div//select)[1]")
	protected WebElement tuesdayStoreCloseTimeInHours;

	@FindBy(xpath = "(//p[contains(text(),'Tuesday')]/parent::div/following-sibling::div//h6[text()='STORE CLOSE TIME']/parent::div//select)[2]")
	protected WebElement tuesdayStoreCloseTimeInMin;

	@FindBy(xpath = "//span[text()='Wed']/ancestor::div[@id = 'workday']")
	protected WebElement wednesday;

	@FindBy(xpath = "(//p[contains(text(),'Wednesday')]/parent::div/following-sibling::div//h6[text()='STORE OPEN TIME']/parent::div//select)[1]")
	protected WebElement wednesdayStoreOpenTimeInHours;

	@FindBy(xpath = "(//p[contains(text(),'Wednesday')]/parent::div/following-sibling::div//h6[text()='STORE OPEN TIME']/parent::div//select)[2]")
	protected WebElement wednesdayStoreOpenTimeInMin;

	@FindBy(xpath = "(//p[contains(text(),'Wednesday')]/parent::div/following-sibling::div//h6[text()='STORE CLOSE TIME']/parent::div//select)[1]")
	protected WebElement wednesdayStoreCloseTimeInHours;

	@FindBy(xpath = "(//p[contains(text(),'Wednesday')]/parent::div/following-sibling::div//h6[text()='STORE CLOSE TIME']/parent::div//select)[2]")
	protected WebElement wednesdayStoreCloseTimeInMin;

	@FindBy(xpath = "//span[text()='Thu']/ancestor::div[@id = 'workday']")
	protected WebElement thursday;

	@FindBy(xpath = "(//p[contains(text(),'Thursday')]/parent::div/following-sibling::div//h6[text()='STORE OPEN TIME']/parent::div//select)[1]")
	protected WebElement thursdayStoreOpenTimeInHours;

	@FindBy(xpath = "(//p[contains(text(),'Thursday')]/parent::div/following-sibling::div//h6[text()='STORE OPEN TIME']/parent::div//select)[2]")
	protected WebElement thursdayStoreOpenTimeInMin;

	@FindBy(xpath = "(//p[contains(text(),'Thursday')]/parent::div/following-sibling::div//h6[text()='STORE CLOSE TIME']/parent::div//select)[1]")
	protected WebElement thursdayStoreCloseTimeInHours;

	@FindBy(xpath = "(//p[contains(text(),'Thursday')]/parent::div/following-sibling::div//h6[text()='STORE CLOSE TIME']/parent::div//select)[2]")
	protected WebElement thursdayStoreCloseTimeInMin;

	@FindBy(xpath = "//span[text()='Fri']/ancestor::div[@id = 'workday']")
	protected WebElement friday;

	@FindBy(xpath = "(//p[contains(text(),'Friday')]/parent::div/following-sibling::div//h6[text()='STORE OPEN TIME']/parent::div//select)[1]")
	protected WebElement fridayStoreOpenTimeInHours;

	@FindBy(xpath = "(//p[contains(text(),'Friday')]/parent::div/following-sibling::div//h6[text()='STORE OPEN TIME']/parent::div//select)[2]")
	protected WebElement fridayStoreOpenTimeInMin;

	@FindBy(xpath = "(//p[contains(text(),'Friday')]/parent::div/following-sibling::div//h6[text()='STORE CLOSE TIME']/parent::div//select)[1]")
	protected WebElement fridayStoreCloseTimeInHours;

	@FindBy(xpath = "(//p[contains(text(),'Friday')]/parent::div/following-sibling::div//h6[text()='STORE CLOSE TIME']/parent::div//select)[2]")
	protected WebElement fridayStoreCloseTimeInMin;

	@FindBy(xpath = "//span[text()='Sat']/ancestor::div[@id = 'workday']")
	protected WebElement saturday;

	@FindBy(xpath = "(//p[contains(text(),'Saturday')]/parent::div/following-sibling::div//h6[text()='STORE OPEN TIME']/parent::div//select)[1]")
	protected WebElement saturdayStoreOpenTimeInHours;

	@FindBy(xpath = "(//p[contains(text(),'Saturday')]/parent::div/following-sibling::div//h6[text()='STORE OPEN TIME']/parent::div//select)[2]")
	protected WebElement saturdayStoreOpenTimeInMin;

	@FindBy(xpath = "(//p[contains(text(),'Saturday')]/parent::div/following-sibling::div//h6[text()='STORE CLOSE TIME']/parent::div//select)[1]")
	protected WebElement saturdayStoreCloseTimeInHours;

	@FindBy(xpath = "(//p[contains(text(),'Saturday')]/parent::div/following-sibling::div//h6[text()='STORE CLOSE TIME']/parent::div//select)[2]")
	protected WebElement saturdayStoreCloseTimeInMin;

	@FindBy(xpath = "//p[contains(text(),'Saturday')]/parent::div/following-sibling::div//h6[text()='STORE CLOSE TIME']/parent::div/div[@id='myFieldIDError']")
	protected WebElement saturdayStoreErrWhenClosingAndOpeningTimeIsSame;

	@FindBy(xpath = "//span[text()='Days']/parent::span/parent::button")
	protected WebElement daysBtnHandlingTime;

	@FindBy(xpath = "//span[text()='Hours']/parent::span/parent::button")
	protected WebElement hoursBtnHandlingTime;

	@FindBy(xpath = "//p[text()='Choose on the basis of']/parent::div/parent::div/following-sibling::div//div[@class='Polaris-Select']/select")
	protected WebElement handlingTimeSelect;

	@FindBy(xpath = "//p[text()='Choose on the basis of']/parent::div/parent::div/following-sibling::div//div[@class='Polaris-Select']/select/option")
	protected List<WebElement> handlingTimeSelectOption;

	@FindBy(xpath = "//span[text()='Minutes']/parent::span/parent::button")
	protected WebElement minutesBtnHandlingTime;

	@FindBy(xpath = "//span[text()='Discard Store']/ancestor::button")
	protected WebElement discardStoreBtn;

	@FindBy(xpath = "//div[@role='dialog'] //span[text()='Save']/ancestor::button")
	protected WebElement saveBtnModal;

	@FindBy(xpath = "//span[text()='Edit']/ancestor::button")
	protected WebElement editStoreBtn;

	@FindBy(xpath = "//span[text()='More actions']/ancestor::button")
	protected WebElement moreActionsBtn;

	@FindBy(xpath = "(//button[@role='menuitem']/div/span)[1]")
	protected WebElement storeStatusBtnMoreAction;

	@FindBy(xpath = "//button[@role='menuitem']/div/span[text()='Delete Store']")
	protected WebElement deleteStoreBtnMoreAction;

	@FindBy(xpath = "//span[text()='Discard']/ancestor::button")
	protected WebElement discardBtnDeleteStoreModal;

	@FindBy(xpath = "//span[text()='Discard']/ancestor::button")
	protected List<WebElement> discardBtnDeleteStoreModalList;

	@FindBy(xpath = "//span[text()='Delete']/ancestor::button")
	protected WebElement deleteBtnDeleteStoreModal;

	

//	--------------</new Store>---------------------------------

//	--------------</Overview>---------------------------------

//	--------------<Settings>---------------------------------

	@FindBy(id = "localSelling")
	protected WebElement localSelling;

	@FindBy(id = "bopis_Btn_Enable")
	protected WebElement enableBOPISBtn;
	
	@FindBy(css = "button[class ^= 'ant-switch ant-switch-small']")
	protected WebElement bopisOrderSyncToggleBtn;

	@FindBy(id = "bopis_Btn_Disable")
	protected WebElement disableBOPISBtn;
	
	@FindBy(id="bopis_Text_LastSyncUpdate")
	protected WebElement lastSyncBOPIS;
	
	@FindBy(id="bopis_ChoiceList_SetFulfilledOrderStatus")
	protected WebElement setFulfilledOrderStatusRadioBtn;
	
	@FindBy(id="bopis_ChoiceList_SetUnfulfilledOrderStatus")
	protected WebElement setUnfulfilledOrderStatusRadioBtn;
	

//	--------------</Settings>---------------------------------
}
