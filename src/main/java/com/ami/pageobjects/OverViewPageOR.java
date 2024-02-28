package com.ami.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ami.pageobjects.reusableclasses.ReusableMethodsOR;

public class OverViewPageOR extends ReusableMethodsOR {

	@FindBy(xpath = "//h2[text() = 'Pricing']")
	WebElement pricingHeading;

	@FindBy(xpath = "//h2[text() = 'Pricing']/parent::div/following-sibling::div//h2")
	WebElement activePricingPlan;

	@FindBy(xpath = "//h2[text() = 'Pricing']/parent::div/following-sibling::div//h2/parent::div/following-sibling::div//span[contains(@class, 'variationSubdued')]")
	WebElement paymentDetails;

	@FindBy(xpath = "//div[contains(@class, 'Polaris-ProgressBar Polaris-ProgressBar--')]")
	WebElement progressBar;

	@FindBy(xpath = "//div[contains(@class, 'Polaris-ProgressBar Polaris-ProgressBar--')]/following-sibling::span")
	WebElement orderProcessedBar;

	@FindBy(xpath = "//div[contains(@class, 'Polaris-ProgressBar Polaris-ProgressBar--')]/following-sibling::span")
	List<WebElement> orderProcessedBarList;

	@FindBy(xpath = "//span[text() = 'Change Plan']")
	WebElement changePricingPlanButton;

	@FindBy(xpath = "//h1[normalize-space()='Subscription Plan']")
	WebElement subscriptionPlanHeading;

	@FindBy(xpath = "//div[@class = 'hoverPlan']")
	List<WebElement> subscriptionPlanList;

	@FindBy(xpath = "//h1[text() = 'Approve subscription']")
	WebElement approveSubscriptionPageHeading;

	@FindBy(id = "approve-charges-button")
	WebElement approveButton;

	@FindBy(xpath = "//span[contains(@class, 'Polaris-Badge--statusWarning')]")
	WebElement currentPlanBanner;

	@FindBy(xpath = "//button[contains(@class , 'slick-arrow slick-prev')]")
	WebElement prevArrowButton;

	@FindBy(xpath = "//button[contains(@class , 'slick-arrow slick-next')]")
	WebElement nextArrowButton;

	@FindBy(xpath = "//progress[@class = 'Polaris-ProgressBar__Progress']")
	WebElement progressActivityBar;

	@FindBy(xpath = "//p[text() = 'Seller Name']/following-sibling::button")
	WebElement sellerAccountButton;

	@FindBy(xpath = "//button[@class = 'Polaris-ActionList__Item']")
	WebElement sellerAcntDropDown;

	@FindBy(xpath = "//button[@role='menuitem']//p")
	List<WebElement> sellerAccountsList;

	@FindBy(css = "li div[class = 'custom-flag']")
	List<WebElement> customFlagList;

	@FindBy(xpath = "//nav[@role = 'navigation']")
	WebElement backButton;

	@FindBy(xpath = "//span[@style= 'color: red;']")
	WebElement mandatoryStar;

	@FindBy(xpath = "//span[text() = 'Choose a suitable country']")
	WebElement selectCountry;

	@FindBy(xpath = "//input[@placeholder = 'Search']")
	WebElement countrySearchInputField;

	@FindBy(xpath = "//div[@class = 'Polaris-Popover__Content']")
	WebElement countriesContainer;

	@FindBy(xpath = "//div[@class = 'Polaris-Combobox__Listbox']//ul/li")
	List<WebElement> allCountriesList;

	@FindBy(xpath = "//div[@class = 'Polaris-Popover__Wrapper']//div[contains(@class , 'Popover__Content')]")
	WebElement searchedCountryContainer;

	@FindBy(xpath = "//div[@class = 'Polaris-Popover__Content']//div[contains(@class , 'Combobox__Listbox')]//li")
	List<WebElement> searchedCountryList;

	@FindBy(xpath = "//span[text() = 'By using our services, you agree to our ']")
	WebElement agreeToTermsCheckBox;

	@FindBy(xpath = "//span[text() = 'Connect Amazon account']/parent::span/parent::button")
	WebElement connectSellerAccount;

	@FindBy(xpath = " //form[@name = 'signIn']")
	WebElement amazonCSSignInForm;

	@FindBy(xpath = "//a[text() = 'CedCommerce Amazon Channel']")
	WebElement amazonByCedLink;

	@FindBy(xpath = "//div[@class = 'Polaris-FooterHelp']//button[text()='Get Support']")
	WebElement getSupportLink;

	@FindBy(xpath = "//h1[text() = 'How can we help you ?']")
	WebElement getSupportPageHeading;

	@FindBy(xpath = "//button[contains(@class , 'Polaris-Button--iconOnly')]")
	List<WebElement> allButtons;

	@FindBy(xpath = "//div[@id = 'basic-collapsible']")
	List<WebElement> faqList;

	@FindBy(css = "a[href='https://calendly.com/scale-business-with-cedcommerce/shopify-amazon-integration']")
	WebElement bookACallLink;

	@FindBy(xpath = "//div[@role = 'alert']//span[text() = 'Your account connection was unsuccessful.']")
	WebElement connectionErrorMsg;

	@FindBy(xpath = "//h2[text() = 'What could have possibly went wrong!!']")
	WebElement infoRegardingError;

	@FindBy(css = "a[href='https://docs.cedcommerce.com/shopify/multi-account-amazon-cedcommerce/?section=onboarding-the-app-4']")
	WebElement forMoreInfoClickHereLink;

	@FindBy(xpath = "//span[text() = 'Retry Connecting']/parent::span/parent::button")
	WebElement retryConnecting;

	@FindBy(xpath = "//button[text() = 'Get Support']")
	WebElement getSuportButton;

	@FindBy(id = "activities")
	WebElement activitiesOverviewHeading;

	@FindBy(xpath = "//span[text() = 'All Activities']")
	WebElement allActivitiesButton;

	@FindBy(xpath = "(//div[@class='Polaris-VerticalStack'])[3]/div")
	List<WebElement> msgActivities;

	@FindBy(xpath = "(//div[@class='Polaris-VerticalStack'])[3]/div/div")
	List<WebElement> dateTimeActivities;

	@FindBy(xpath = "(//h2[text() = 'Product Status'])[2]")
	WebElement productStatusHeading;

	@FindBy(xpath = "//span[text() = 'Manage Listings']/parent::span/parent::button")
	WebElement manageListingsButton;

	@FindBy(xpath = "//span[text() = 'Not Listed']")
	WebElement notListedStatus;

	@FindBy(xpath = "//span[text() = 'Not Listed']/parent::span/following-sibling::button")
	WebElement notListedStatusCount;

	@FindBy(xpath = "//span[text() = 'Inactive']")
	WebElement inActiveStatus;

	@FindBy(xpath = "//span[text() = 'Inactive']/parent::span/following-sibling::button")
	WebElement inActiveStatusCount;

	@FindBy(xpath = "//span[text() = 'Active']")
	WebElement activeStatus;

	@FindBy(xpath = "//span[text() = 'Active']/parent::span/following-sibling::button")
	WebElement activeStatusCount;

	@FindBy(xpath = "//span[text() = 'Incomplete']")
	WebElement incompleteStatus;

	@FindBy(xpath = "//span[text() = 'Incomplete']/parent::span/following-sibling::button")
	WebElement incompleteStatusCount;

	@FindBy(xpath = "//h1[text() = 'Listings']")
	WebElement listingPageHeading;

	@FindBy(id = "all")
	WebElement allStatusListingPage;

	@FindBy(id = "not_listed")
	WebElement notListedStatusListingPage;

	@FindBy(xpath = "//button[@id = 'not_listed']//div[@class = 'custom-notlisted-badge']//span[@class = 'Polaris-Badge']")
	WebElement notListedStatusCountListingPage;

	@FindBy(id = "inactive")
	WebElement inactiveStatusListingPage;

	@FindBy(xpath = "//button[@id = 'inactive']//span[@class = 'Polaris-Tabs__Title']//span[@class = 'Polaris-VisuallyHidden']")
	WebElement inactiveStatusCountListingPage;

	@FindBy(id = "Incomplete")
	WebElement incompleteStatusListingPage;

	@FindBy(xpath = "//button[@id = 'Incomplete']//span[@class = 'Polaris-Tabs__Title']//span[@class = 'Polaris-VisuallyHidden']")
	WebElement incompleteStatusCountListingPage;

	@FindBy(id = "active")
	WebElement activeStatusListingPage;

	@FindBy(xpath = "//button[@id = 'active']//span[@class = 'Polaris-Tabs__Title']//span[@class = 'Polaris-VisuallyHidden']")
	WebElement activeStatusCountListingPage;

	@FindBy(id = "Error")
	WebElement errorStatusListingPage;

	@FindBy(id = "failedorders")
	WebElement failedOrdersHeadingOverview;

	@FindBy(xpath = "//span[text() = 'Failed Orders']")
	WebElement failedOrders;

	@FindBy(xpath = "(//td[contains(@class , 'ant-table-cell')][2])")
	List<WebElement> failedOrderAmzId;

	@FindBy(xpath = "(//td[contains(@class , 'ant-table-cell')][3])")
	List<WebElement> amazonStatusFailedOrders;

	@FindBy(xpath = "(//td[contains(@class , 'ant-table-cell')][4])/div/div[2]/span")
	List<WebElement> failureReasonFailedOrders;

	@FindBy(xpath = "(//td[contains(@class , 'ant-table-cell')][5])/div//span[text() = 'View']")
	List<WebElement> viewBtn;

	@FindBy(xpath = "(//td[contains(@class , 'ant-table-cell')][5])/div//span[text() = 'Create Order']")
	List<WebElement> createOrderBtn;

	@FindBy(xpath = "(//td[contains(@class , 'ant-table-cell')][5])/div//span[text() = 'Archive']")
	List<WebElement> archieveBtn;

	@FindBy(xpath = "//span[text() = 'Archived Orders']/parent::span/parent::button")
	WebElement archivedOrderButtonFailedOrders;

	@FindBy(xpath = "//p[text() = 'No Orders Found']")
	WebElement noOrdersFoundMsg;

	@FindBy(xpath = "//p[text() = 'No Orders Found']")
	List<WebElement> noOrdersFoundMsgList;

	@FindBy(xpath = "//div[@aria-live = 'polite']/span")
	WebElement paginationCount;

	@FindBy(id = "nextURL")
	WebElement nextPageArwButtonArchieved;

	@FindBy(id = "previousURL")
	WebElement prevPageArwButtonArchieved;

	@FindBy(xpath = "//p[text() = 'Do you want to archive this order?']")
	WebElement archieveOrderModalMsg;

	@FindBy(xpath = "//input[@placeholder = 'Search with Amazon Order Id']")
	WebElement searchWithAmzOrderIdInputField;

	@FindBy(id = "appguide")
	WebElement appGuideHeading;

	@FindBy(xpath = "//h2[text() = 'More Tips on getting started']")
	WebElement moreTipsHeading;

	@FindBy(xpath = "//span[text() = 'Watch Video']/parent::span/parent::button")
	List<WebElement> watchVideoButton;

	@FindBy(xpath = "//span[text() = 'Manage Failed Orders']")
	WebElement manageFailedOrdersButton;

	@FindBy(xpath = "//th[@class = 'ant-table-cell' and text() = 'Amazon Order Id']")
	WebElement amazonOrderIdHeadingFailedOrders;

	@FindBy(xpath = "//th[@class = 'ant-table-cell' and text() = 'Amazon Status']")
	WebElement amazonStatusHeadingFailedOrders;

	@FindBy(xpath = "//th[@class = 'ant-table-cell' and text() = 'Failure Reason']")
	WebElement amazonFailureReasonHeadingFailedOrders;

	@FindBy(xpath = "//th[@class = 'ant-table-cell' and text() = 'Actions']")
	WebElement amazonActionsHeadingFailedOrders;

	@FindBy(xpath = "//span[text() = 'Import Orders']/parent::span/parent::button")
	WebElement importOrderButton;

	@FindBy(xpath = "//h2[text() = 'Import Order(s)']")
	WebElement importOrderModalHeading;

	@FindBy(xpath = "//button[@aria-label = 'Close']/span")
	WebElement crossButton;

	@FindBy(xpath = "//input[@placeholder = 'Enter Amazon Order Id']")
	WebElement inputFieldImportOrderModal;

	@FindBy(xpath = "//span[text() = 'Close']/parent::span/parent::button")
	WebElement closeBtnImportOrderModal;

	@FindBy(xpath = "//span[text() = 'Proceed']/parent::span/parent::button")
	WebElement proceedBtnImportOrderModal;

	@FindBy(xpath = "//span[text() = 'Archive Order']/parent::span/parent::button")
	WebElement archieveOrderButton;
}
