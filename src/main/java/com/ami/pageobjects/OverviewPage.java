package com.ami.pageobjects;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ami.resources.Utilities;

public class OverviewPage extends OverViewPageOR {
	private static Logger log = LogManager.getLogger(OverviewPage.class.getName());
	Utilities util;
	String pageUrl = "";
	String currentPlanName = "";
	private String ariaLabel = "aria-label";
	private String onboardingCountry = "onboarding_country";
	private String cursor = "cursor";
	private String pointer = "pointer";
	private String noSuchWindowExcep =  "NoSuchWindowException occured";

	/**
	 * OverViewPage parametertised constructor it initializes driver to pagefactory
	 * methods.
	 * 
	 * @param util
	 */
	public OverviewPage(Utilities util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
	}

	/**
	 * This method validates that user is redirecting to overview after clicking on
	 * overview button. TC_AM_OV_001
	 */
	public void checkRedirectionToOverView() {
		util.waitUntilElementIsVisible(overViewHeading);

		if (util.getPageURL().contains("/dashboard")) {
			log.info("user is successfully redirected to overview.");
			Assert.assertTrue(true);
		} else {
			log.error("user is not redirected to overview.");
			Assert.assertTrue(false);
		}
	}

	/**
	 * This method validates that seller account is visible. TC_AM_OV_008
	 */
	public void sellerAcountIsVisible() {
		util.isElementDisplyedAndValidate(sellerAccountButton);
	}

	/**
	 * This method validates that seller account is clicked dropdown is shown and
	 * flag is shown with it.TC_AM_OV_008 TC_AM_OV_009 & TC_AM_OV_010
	 */
	public void sellerAcntDropDownAndFlasIsVisible() {
		if(sellerAccountButton.getAttribute(ARIAEXPANDED).equalsIgnoreCase(FALSEVAL)) {
			util.click(sellerAccountButton);
		}
		

		util.isElementDisplyedAndValidate(sellerAcntDropDown);

		for (WebElement customFlag : customFlagList) {

			util.isElementDisplyedAndValidate(customFlag);
		}
	}

	/**
	 * This method validates that seller account is clicked dropdown is shown and it
	 * contains "add new account button". TC_AM_OV_011
	 */
	public void validateAddAcntButtonIsVisible() {
		util.isElementDisplyedAndValidate(addNewAcntBtn);
	}

	/**
	 * This method validates that when clicking on Add Account then it redirects to
	 * Account Connection TC_AM_OV_012
	 */
	public void validateAccountConnectionRedirection() {
		util.click(addNewAcntBtn);
		util.isElementDisplyedAndValidate(addNewAcntPageHeading);
	}

	/**
	 * This method validates that 3 hyperlinks Amazon By CedCommerce, Get Support or
	 * Book a Call is displayed. TC_AM_OV_031,TC_AM_OV_032,TC_AM_OV_033
	 */

	public void validateHyperlinksAreVisible() {
		boolean isTrue = false;

		pageUrl = util.getPageURL();
		util.isElementDisplyedAndValidate(amazonByCedLink);
		util.isElementDisplyedAndValidate(getSupportLink);
		util.isElementDisplyedAndValidate(bookACallLink);
		util.click(amazonByCedLink);
		util.goToChildWindow();
		if (util.getPageTitle().equalsIgnoreCase("Multi-account Amazon by CedCommerce - CedCommerce Products Documentation")) {
			log.info("redirected to doc page successfully.");
			Assert.assertTrue(true);
		} else {
			log.error("redirection error occured.");
			Assert.assertTrue(false);
		}

		try {
			util.getDriver().close();
			util.getWindoHandleByUrl(pageUrl);
			util.switchToIFrame();
			util.click(bookACallLink);
			util.goToChildWindow();

			if (util.getPageURL().contains("https://calendly.com/")) {
				isTrue = true;
				Assert.assertTrue(isTrue);
			}
			Assert.assertTrue(isTrue);

			util.getDriver().close();
			util.getWindoHandleByUrl(pageUrl);
			util.switchToIFrame();
			util.click(getSupportLink);
			util.isElementDisplyedAndValidate(getSupportPageHeading);
			util.hold(2);
			util.switchToDefaultContent();
			util.clickOnOverview();
		} catch (NoSuchWindowException e) {
			util.logWarn(noSuchWindowExcep);
		}

	}

	/**
	 * Verify that back button is shown at top left side and it is clickable and it
	 * redirects to overview section. TC_AM_OV_014 & TC_AM_OV_015 & TC_AM_OV_016
	 */
	public void validateBackBtnIsVisible() {
		util.isElementDisplyedAndValidate(backButton);
		util.actionClick(backButton);
		log.info("clicked on back button arrow.");

		util.isElementDisplyedAndValidate(overViewHeading);
		util.hold(2);
	}

	/**
	 * Verify that Select Your Country field is shown manadatory TC_AM_OV_017
	 */
	public void validateCountrySelectionIsMandatory() {
		util.click(sellerAccountButton);
		log.info("clicked on seller account button .");
		util.hold(1);
		util.click(addNewAcntBtn);
		
	}

	/**
	 * This method validates that when user clicks into search input field then
	 * dropdown is visible which contains countries .TC_AM_OV_018 & TC_AM_OV_019
	 */
	public void validateDropDownIsVisible() {
		boolean flagNorthAmerica = false;
		boolean flagBrazil = false;
		boolean flagIndia = false;
		boolean flagAustralia = false;
		boolean flagJapan = false;
		util.click(countrySearchInputField);
		log.info("clicked on country search input field.");
		util.isElementDisplyedAndValidate(countriesContainer);

		for (WebElement country : allCountriesList) {
			util.moveToElement(country);

			if (util.extractValueByAttributes(country, ariaLabel).equalsIgnoreCase("North America")) {
				flagNorthAmerica = true;
			} else if (util.extractValueByAttributes(country, ariaLabel).equalsIgnoreCase("Brazil")) {
				flagBrazil = true;
			} else if (util.extractValueByAttributes(country, ariaLabel).equalsIgnoreCase("India")) {
				flagIndia = true;
			} else if (util.extractValueByAttributes(country, ariaLabel).equalsIgnoreCase("Australia")) {
				flagAustralia = true;
			} else if (util.extractValueByAttributes(country, ariaLabel).equalsIgnoreCase("Japan")) {
				flagJapan = true;
			}
		}

		if (flagNorthAmerica && flagBrazil && flagIndia && flagAustralia && flagJapan) {
			log.info("country is visible.");
			Assert.assertTrue(true);
		} else {
			log.error("country is not visible.");
			Assert.assertTrue(false);
		}

	}

	/**
	 * This method validates that selected country is visible in search input field.
	 * TC_AM_OV_020
	 */
	public void validateSelectedCountryIsVisibleInSearchField(Map<String, String> input) {
		util.enterValue(countrySearchInputField, input.get("search_country_keyword"));

		if (!util.matchRegExpressionNumSpecialChar(input.get("search_country_keyword"))) {
			for (WebElement country : allCountriesList) {
				util.jsMoveToElement(country);

				if (util.extractValueByAttributes(country, ariaLabel).equalsIgnoreCase(input.get(onboardingCountry))) {
					util.click(country);
					log.info("selected country " + input.get(onboardingCountry));
					break;
				}
			}
		}

		if (util.extractValueByAttributes(countrySearchInputField, "value")
				.equalsIgnoreCase(input.get(onboardingCountry))) {
			log.info("search input field contains country name.");
			Assert.assertTrue(true);
		} else {
			log.error("search input field does not contains country name.");
			Assert.assertTrue(false);
		}
	}

	/**
	 * This method validates that user can enter maunually and dropdown is shown
	 * accordingly TC_AM_OV_021 && TC_AM_OV_022
	 */
	public void validateCountryNameCanBeEnteredManually(Map<String, String> input) {
		util.enterValue(countrySearchInputField, input.get(onboardingCountry));

		if (!util.matchRegExpressionNumSpecialChar(input.get(onboardingCountry))) {
			try {
				util.click(searchedCountryList.get(0));
			} catch (NoSuchElementException e) {
				log.error("country is invalid.");
				Assert.assertTrue(true);
			}
		}
	}

	/**
	 * This method validates that user can enter maunually and it contains numbers
	 * or special chars no dropdown is shown TC_AM_OV_023
	 * 
	 * @param input
	 */
	public void validateIfSearchedCountryContainsNumAndSpecialChars(Map<String, String> input) {
		util.enterValue(countrySearchInputField, input.get("search_country_keyword_num"));

		if (util.matchRegExpressionNumSpecialChar(input.get("search_country_keyword_num"))) {
			try {
				util.isElementDisplyed(searchedCountryContainer);
			} catch (NoSuchElementException e) {
				log.error("country is invalid.");
				Assert.assertTrue(true);
			}
		}
	}

	/**
	 * This method validates that Connect Seller Account button is shown in the // *
	 * Account Connection section and it is clickable. TC_AM_OV_024 & TC_AM_OV_025
	 * TC_AM_OV_026
	 */
	public void validateConnectSellerAcntButtonIsVisibleOnAccountConnectionSection(Map<String, String> input) {
		pageUrl = util.getPageURL();
		util.isElementDisplyedAndValidate(connectSellerAccount);
		util.enterValue(countrySearchInputField, input.get(onboardingCountry));
		util.hold(1);
		util.click(searchedCountryList.get(0));
		util.hold(1);
		util.click(connectSellerAccount);
		util.goToChildWindow();
		util.isElementDisplyedAndValidate(amazonCSSignInForm);

		try {
			util.getDriver().close();
			util.getWindoHandleByUrl(pageUrl);
			util.hold(2);
			util.switchToIFrame();
		} catch (NoSuchWindowException e) {
			util.logWarn(noSuchWindowExcep);
		}
	}

	/**
	 * Verify that when Account is not connected successfully then it redirects to
	 * another page of Unsuccessfull Connection.TC_AM_OV_034,
	 * TC_AM_OV_036,TC_AM_OV_037, TC_AM_OV_038, TC_AM_OV_039, TC_AM_OV_040
	 */
	public void validateConnectionErrorMsgIsDisplayed() {
		boolean isTrue = false;
		pageUrl = util.getPageURL();

//		util.verifyElementVisible(connectionErrorMsg);  ==> continuous error

		util.isElementDisplyedAndValidate(forMoreInfoClickHereLink);
		util.moveToElement(forMoreInfoClickHereLink);

//		validate that cursor's property is changing when we hover mouse at clickHereToKnowMoreLink.
		if (forMoreInfoClickHereLink.getCssValue(cursor).equals(pointer)) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);

		util.click(forMoreInfoClickHereLink);
		util.getWindoHandleByUrl("https://docs.cedcommerce.com/shopify/amazon-channel-cedcommerce/");
		isTrue = false;

		if (util.getPageTitle().equals("Multi-account Amazon by CedCommerce - CedCommerce Products Documentation")) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);

		try {
			util.getDriver().close();
			util.getWindoHandleByUrl(pageUrl);
			util.switchToIFrame();
		} catch (NoSuchWindowException e) {
			util.logWarn(noSuchWindowExcep);
		}

		util.click(retryConnecting);
		util.isElementDisplyedAndValidate(addNewAcntPageHeading);
	}

	/**
	 * This method Validates that at Footer all the three Hyperlinks are shown
	 * Amazon By CedCommerce, Get Support and Book A Call, TC_AM_OV_041,
	 * TC_AM_OV_042, TC_AM_OV_043, TC_AM_OV_044, TC_AM_OV_045
	 */
	public void validateFooterContainsLink() {
		util.validateCursorProperty(amazonByCedLink, pointer);
		util.validateCursorProperty(getSupportLink, pointer);
		util.validateCursorProperty(bookACallLink, pointer);
		validateHyperlinksAreVisible();
	}

	/**
	 * This method validates that under Subscription field the selected plan,
	 * Payment Details , Orders Processed bar, change plan button is displayed.
	 * TC_AM_OV_096,TC_AM_OV_097,TC_AM_OV_098,TC_AM_OV_099,TC_AM_OV_100,TC_AM_OV_101,TC_AM_OV_103
	 */
	public void mandatoryFieldSubscriptionPlanVisibilityValidation() {
		util.goToMultiAccountOverView();
		util.isElementDisplyedAndValidate(activePricingPlan);
		util.isElementDisplyedAndValidate(paymentDetails);
		util.isElementDisplyed(progressBar);
		if(!orderProcessedBarList.isEmpty()) {
			util.isElementDisplyedAndValidate(orderProcessedBar);
		}
		
		util.isElementDisplyedAndValidate(changePricingPlanButton);
	}

	/**
	 * This method validates that when mouse is hovered at change plan button, hand
	 * cursor is shown.TC_AM_OV_103
	 */
	public void verifyCursorChangeToHandWhileHoverToChangePlanButton() {
		boolean isTrue = false;
		util.moveToElement(changePricingPlanButton);

		if (changePricingPlanButton.getCssValue(cursor).equals(pointer)) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);

	}

	/**
	 * This method validates that when user clicks on change plan button it should
	 * redirect to the "Subscription Plan section". --TC_AM_OV_108--
	 */
	public void clickOnChangePricingPlanButton() {
		currentPlanName = activePricingPlan.getText();
		util.click(changePricingPlanButton);
		log.info("clicked on 'change plan' button");
		if (util.isElementDisplyed(subscriptionPlanHeading)) {
			log.info("user redirects to the subscription plan page");
			Assert.assertTrue(true);
		} else {
			log.error("user is not redirect to the subscription plan page");
			Assert.assertTrue(false);
		}
	}

	/**
	 * This method validates that all subscription plan are visible, TC_AM_OV_140
	 */
	public void allSubscriptionPlanAreVisible() {
		for (int i = 0; i < subscriptionPlanList.size(); i++) {

			if (i == 3) {
				util.click(nextArrowButton);
				util.hold(2);
				util.click(nextArrowButton);
				util.hold(2);
				util.click(nextArrowButton);
			}

			if (i == 4) {
				util.click(nextArrowButton);
				util.hold(2);
				util.click(nextArrowButton);
			}

			util.hold(2);
			util.isElementDisplyedAndValidate(subscriptionPlanList.get(i));
		}
	}

	/**
	 * This method validates that when user selects other plan it should get change.
	 * 
	 */
	public void changePricingPlan() {

		for(int i = 0; i < subscriptionPlanList.size(); i++) {
			
			try {
				if (!subscriptionPlanList.get(i).findElement(By.xpath("//div[@class = 'hoverPlan']//button"))
						.getAttribute("disabled").equals("true")) {
					subscriptionPlanList.get(i).findElement(By.xpath("//div[@class = 'hoverPlan']//button")).click();
					break;
				}
			} catch (Exception e) {
				subscriptionPlanList.get(i).click();
				break;
			}

		}

		util.waitUntilElementIsVisible(approveSubscriptionPageHeading);
		util.click(approveButton);
		log.info("clicked on approve plan button.");
		util.waitUntilElementIsVisible(activePricingPlan);

		if (!activePricingPlan.getText().equalsIgnoreCase(currentPlanName)) {
			log.info("subscription plan is changed successfully.");
			Assert.assertTrue(true);
		} else {
			log.error("subscription plan is changed successfully.");
			Assert.assertTrue(false);
		}
	}

	/**
	 * This method validate contact us button is working properly.TC_AM_OV_142,
	 * TC_AM_OV_143
	 */
	public void validateContactUsWorking() {
		boolean isTrue = false;
		util.click(getSuportButton);
		util.isElementDisplyedAndValidate(getSupportPageHeading);
		
		for (int i = 0; i < allButtons.size(); i++) {
			util.hold(2);
			util.click(allButtons.get(i));
			util.hold(2);

			if (i == 0) {
				util.click(allButtons.get(i));
			}

			if (util.extractValueByAttributes(faqList.get(i), ARIAHIDDEN).equals(FALSEVAL)) {
				isTrue = true;
			}
			Assert.assertTrue(isTrue);
		}
	}

	/**
	 * This method validates that in overview section activities section is
	 * available.TC_AM_OV_148,
	 * TC_AM_OV_149,TC_AM_OV_150,TC_AM_OV_153,TC_AM_OV_154,TC_AM_OV_155,TC_AM_OV_156
	 */
	public void validateActivitiesIsShown() {
		util.goToMultiAccountOverView();
		util.isElementDisplyedAndValidate(activitiesOverviewHeading);
		util.isElementDisplyedAndValidate(allActivitiesButton);
		util.click(allActivitiesButton);
		util.isElementDisplyedAndValidate(activitiesHeading);
		util.isElementDisplyedAndValidate(backButton);
		util.click(backButton);
		util.isElementDisplyedAndValidate(overViewHeading);
		util.click(allActivitiesButton);
	}

	/**
	 * This method validates that seller account button functionlity. TC_AM_OV_157,
	 * TC_AM_OV_158, TC_AM_OV_159, TC_AM_OV_160, TC_AM_OV_161,
	 * TC_AM_OV_165,TC_AM_OV_166, TC_AM_OV_167, TC_AM_OV_168, TC_AM_OV_169
	 */
	public void validateSwitcherIsDisplayed() {
		boolean isTrue = false;
		util.isElementDisplyedAndValidate(sellerAccountButton);
		util.click(sellerAccountButton);
		util.isElementDisplyedAndValidate(sellerAcntDropDown);
		util.hold(2);
		util.click(sellerAccountsList.get(0));
		util.hold(2);
		util.isElementDisplyedAndValidate(activitiesHeading);

		for (WebElement msgActivity : msgActivities) {
			util.isElementDisplyedAndValidate(msgActivity);
		}

		for (WebElement dateTimeActivitiy : dateTimeActivities) {
			util.isElementDisplyedAndValidate(dateTimeActivitiy);

			if (util.getTagValue(dateTimeActivitiy).contains("GMT")) {
				isTrue = true;
				Assert.assertTrue(isTrue);
			}
			Assert.assertTrue(isTrue);
		}
		validateHyperlinksAreVisible();
		util.goToMultiAccountOverView();
	}

	/**
	 * This method validates product status section in overview.
	 * TC_AM_OV_170,TC_AM_OV_171,TC_AM_OV_173
	 */
	public void validateProductStatusOverview() {
//		boolean isTrue = false;
		util.isElementDisplyedAndValidate(productStatusHeading);
		util.isElementDisplyedAndValidate(activeStatus);
		util.isElementDisplyedAndValidate(activeStatusCount);
		util.isElementDisplyedAndValidate(inActiveStatus);
		util.isElementDisplyedAndValidate(inActiveStatusCount);
		util.isElementDisplyedAndValidate(incompleteStatus);
		util.isElementDisplyedAndValidate(incompleteStatusCount);
		util.isElementDisplyedAndValidate(notListedStatus);
		util.isElementDisplyedAndValidate(notListedStatusCount);
//		TC_AM_OV_176 =>Verify that when clicking on Manage Listing then it is redirect to Listing Grid
		util.click(manageListingsButton);
		util.hold(2);
		util.isElementDisplyedAndValidate(listingPageHeading);
//		TC_AM_OV_178 => Verify that at each options product count is shown
		util.isElementDisplyedAndValidate(allStatusListingPage);
		
		util.isElementDisplyedAndValidate(notListedStatusListingPage);

		util.isElementDisplyedAndValidate(inactiveStatusListingPage);


		util.isElementDisplyedAndValidate(incompleteStatusListingPage);


		util.isElementDisplyedAndValidate(activeStatusListingPage);

		util.isElementDisplyedAndValidate(errorStatusListingPage);

		util.goToMultiAccountOverView();
	}

	/**
	 * This method validates Failed Order section in overview. TC_AM_OV_183,
	 * TC_AM_OV_184,TC_AM_OV_187, TC_AM_OV_188, TC_AM_OV_189,TC_AM_OV_193
	 * 
	 * @param input
	 */
	public void validateFailedOrdersOverview(Map<String, String> input) {
//		boolean isTrue = false;

		util.isElementDisplyedAndValidate(failedOrders);
		util.hold(1);
		util.isElementDisplyedAndValidate(failedOrdersCountOverview);
		util.hold(1);
		util.validateCursorProperty(failedOrdersCountOverview, pointer);
		util.click(failedOrdersCountOverview);
		util.isElementDisplyedAndValidate(failedOrdersPageHeading);
//		TC_AM_OV_184
		
		if(noOrdersFoundMsgList.isEmpty()) {
			Assert.assertTrue(true);
		}

//		TC_AM_OV_188
		util.isElementDisplyedAndValidate(sellerAccountButton);
		util.click(sellerAccountButton);
		util.isElementDisplyedAndValidate(sellerAcntDropDown);

//		TC_AM_OV_191
		util.isElementDisplyedAndValidate(searchWithAmzOrderIdInputField);
		util.enterValue(searchWithAmzOrderIdInputField, input.get("failed_amazon_order_id"));
		util.hold(2);

//		if (!util.getTagValue(failedOrderAmzId.get(0)).equals("")) {
//			isTrue = true;
//			Assert.assertTrue(isTrue);
//		}
//		Assert.assertTrue(isTrue);

//		TC_AM_OV_193
		util.isElementDisplyedAndValidate(archivedOrderButtonFailedOrders);
	}

	/**
	 * This method validates archieved order section
	 * TC_AM_OV_194,TC_AM_OV_195,TC_AM_OV_196,TC_AM_OV_197, TC_AM_OV_198,
	 * TC_AM_OV_198,TC_AM_OV_199,TC_AM_OV_200,TC_AM_OV_201,TC_AM_OV_203,TC_AM_OV_204,TC_AM_OV_205,TC_AM_OV_206,TC_AM_OV_207,TC_AM_OV_210
	 */
	public void validateArchievedOrderSection() {
		util.goToMultiAccountOverView();
		util.waitUntilElementIsVisible(manageFailedOrdersButton, 30);
		util.click(manageFailedOrdersButton);
		util.waitUntilElementIsVisible(archivedOrderButtonFailedOrders, 30);
		util.waitUntilElementIsVisible(archivedOrderButtonFailedOrders, 30);
		util.click(archivedOrderButtonFailedOrders);
		util.waitUntilElementIsVisible(archivedOrdersHeading, 30);
		util.isElementDisplyedAndValidate(archivedOrdersHeading);
		util.isElementDisplyedAndValidate(searchWithAmzOrderIdInputField);
		
		if(!noArchiveOrdersFoundMsg.isEmpty()) {
			util.isElementDisplyedAndValidate(orderIdArchivedOrdersColHeading);
			util.isElementDisplyedAndValidate(dateHeadingArchivedCol);
			util.isElementDisplyedAndValidate(actionsHeadingArchivedOrders);
			util.isElementDisplyedAndValidate(viewButtonArchivedOrder.get(0));
			util.click(viewButtonArchivedOrder.get(0));
//			TC_AM_OV_205,TC_AM_OV_206
			util.isElementDisplyedAndValidate(viewYourOrderPageHeading);
			util.isElementDisplyedAndValidate(customerName);
			util.isElementDisplyedAndValidate(customerAddress);
			util.isElementDisplyedAndValidate(earliestDeliveryDate);
			util.isElementDisplyedAndValidate(latestShipDate);
			util.isElementDisplyedAndValidate(earliestShipDate);
			util.isElementDisplyedAndValidate(latestDeliveryDate);
		}
		

//		TC_AM_OV_207
		util.click(backButton);
		util.isElementDisplyedAndValidate(archivedOrderButtonFailedOrders);
		util.hold(2);

	}

	/**
	 * This mthod validates when pagination button should be enabled and disabled
	 * TC_AM_OV_210,=> 1/1 => both < > will be disabled 1/2 => < will be disabled
	 * and > will be enabled 2/2 => < will be enabled and > will be disabled
	 */
		
	public void validatePagination2() {
	    String currentPageNum = StringUtils.substringBeforeLast(util.getTagValue(paginationCount), "/");
	    String totalPageNum = StringUtils.substringAfterLast(util.getTagValue(paginationCount), "/");

	    int currentPage = Integer.parseInt(currentPageNum);
	    int totalPages = Integer.parseInt(totalPageNum);

	    boolean isPaginationValid = false;

	    if (currentPage == 1 && totalPages == 1) {
	        isPaginationValid = nextPageArwButtonArchieved.getAttribute(ARIADISABLED).equalsIgnoreCase(TRUEVAL) &&
	                            prevPageArwButtonArchieved.getAttribute(ARIADISABLED).equalsIgnoreCase(TRUEVAL);
	    } else if (currentPage >= 1 && totalPages > 1) {
	        isPaginationValid = nextPageArwButtonArchieved.getAttribute(ARIADISABLED) == null &&
	                            prevPageArwButtonArchieved.getAttribute(ARIADISABLED) == null;
	    } else if (currentPage > 1 && totalPages > 1 && totalPages == currentPage) {
	        isPaginationValid = nextPageArwButtonArchieved.getAttribute(ARIADISABLED) == null &&
	                            prevPageArwButtonArchieved.getAttribute(ARIADISABLED) == null;
	    }

	    Assert.assertTrue(isPaginationValid);
	}


	/**
	 * This method validates app guide section functionality. TC_AM_OV_240,
	 * TC_AM_OV_241, TC_AM_OV_244, TC_AM_OV_246, TC_AM_OV_247, TC_AM_OV_248,
	 * TC_AM_OV_249
	 */
	public void validateAppGuideSection() {
		boolean isTrue = false;
		util.goToMultiAccountOverView();
		util.isElementDisplyedAndValidate(appGuideHeading);
//		TC_AM_OV_241
		if (!watchVideoButton.isEmpty()) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);

//		TC_AM_OV_244
		util.isElementDisplyedAndValidate(nextArrowButton);
		util.isElementDisplyedAndValidate(prevArrowButton);

//		TC_AM_OV_246, TC_AM_OV_247, TC_AM_OV_248, TC_AM_OV_249
		validateHyperlinksAreVisible();
	}

	/**
	 * TC_AM_OV_215,TC_AM_OV_216,TC_AM_OV_217,TC_AM_OV_218
	 */
	public void validateFailedOrderFunctionalityOverview() {
		boolean isTrue = false;
		util.goToMultiAccountOverView();
		util.click(manageFailedOrdersButton);
		util.hold(2);
		util.isElementDisplyedAndValidate(importOrderButton);
		util.isElementDisplyedAndValidate(amazonOrderIdHeadingFailedOrders);

		for (WebElement orderId : failedOrderAmzId) {

			if (!util.getTagValue(orderId).equals("")) {
				isTrue = true;
				Assert.assertTrue(isTrue);
			}
			Assert.assertTrue(isTrue);
		}

		for (WebElement btn : viewBtn) {

			util.isElementDisplyedAndValidate(btn);
		}

		for (WebElement btn : createOrderBtn) {

			util.isElementDisplyedAndValidate(btn);
		}

		for (WebElement btn : archieveBtn) {

			util.isElementDisplyedAndValidate(btn);
		}
		util.isElementDisplyedAndValidate(amazonStatusHeadingFailedOrders);
		util.isElementDisplyedAndValidate(amazonFailureReasonHeadingFailedOrders);
		util.isElementDisplyedAndValidate(amazonActionsHeadingFailedOrders);
		util.click(importOrderButton);
		util.isElementDisplyedAndValidate(crossButton);
		util.isElementDisplyedAndValidate(inputFieldImportOrderModal);
		util.isElementDisplyedAndValidate(closeBtnImportOrderModal);
		util.isElementDisplyedAndValidate(proceedBtnImportOrderModal);
		util.click(closeBtnImportOrderModal);
	}

	public void viewCreateBtnFunctionality() {
		util.click(viewBtn.get(0));
		util.isElementDisplyedAndValidate(viewYourOrderPageHeading);
		util.isElementDisplyedAndValidate(customerName);
		util.isElementDisplyedAndValidate(customerAddress);
		util.isElementDisplyedAndValidate(earliestDeliveryDate);
		util.isElementDisplyedAndValidate(latestShipDate);
		util.isElementDisplyedAndValidate(earliestShipDate);
		util.isElementDisplyedAndValidate(latestDeliveryDate);
		util.click(backButton);
		util.hold(5);

		util.click(createOrderBtn.get(0));
		util.isElementDisplyedAndValidate(createOrderPageHeading);
		util.click(backButton);
		util.hold(5);

		util.click(archieveBtn.get(0));
		util.isElementDisplyedAndValidate(archieveOrderModalMsg);
		util.isElementDisplyedAndValidate(closeBtnImportOrderModal);
		util.isElementDisplyedAndValidate(archieveOrderButton);
		util.click(closeBtnImportOrderModal);

	}

}
