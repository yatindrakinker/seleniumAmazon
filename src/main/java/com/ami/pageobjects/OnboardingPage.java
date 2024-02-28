package com.ami.pageobjects;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.ami.resources.Utilities;

public class OnboardingPage extends OnboardingPageOR {

	Utilities util;
	public static String storeName = "";
	public static String storeUrl = "";
	private String val = "value";
	private String store = "store";
	private String classVal = "class";
	private String ariaChecked = "aria-checked";
	private String ariaDisabled = "aria-disabled";
	private String falseVal = "false";
	private String trueVal = "true";
	private String docsByCedTitle = "Multi-account Amazon by CedCommerce - CedCommerce Products Documentation";
	private String calendlyTitle = "Select a Date & Time - Calendly";
	private String char32 = "1234567890abcdefghijklmnopqrstuvwxyz";
	private String AcntName = "testAmazonOnboarding";

	/**
	 * Constructor
	 * 
	 * @param util
	 */
	public OnboardingPage(Utilities util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
	}

	/**
	 * Go to Shopify Partners Create new Store
	 * 
	 */

	public void openShopifyPartners() {
		util.openAndSwitchToNewTab();
		util.openUrl(util.getConfigProperty("shopifyPartnersLoginUrl"));
	}

	public void createNewStore() {
		util.click(addStoreButton);
		util.click(createDevStoreBtn);
		util.click(createStoreToTestRadioBtn);
		util.enterValue(storeNameInputField, getStoreName());
		storeUrl = util.extractValueByAttributes(storeUrlInputField, val);
		util.hold(3);
		util.click(createDevStoreBtn);
	}

	public String getStoreName() {
		storeName = "testOnboarding" + util.randString(5);
		return storeName;
	}

	public void openShopifyAppStore() {
		util.switchToDefaultContent();
		openSettings();
		openLocationAndSalesChannel();
		util.click(shopifyAppStore);
		util.getWindoHandleByUrl("https://apps.shopify.com/");
		util.waitUntilElementIsVisible(searchAppInputField, 30);
	}

	public void openSettings() {
		util.click(settingsShopify);
	}

	public void openLocationAndSalesChannel() {
		util.click(appAndSalesChannelBtn);
	}

	/**
	 * search app from shopify app store and install it.
	 */
	public void searchAndInstallApp() {
		util.refreshPage();
		util.enterValue(searchAppInputField, util.getConfigProperty("sales_channel"));
		util.pressEnter();
		util.waitUntilElementIsVisible(cedCossAmazonChannelApp, 30);
		util.click(cedCossAmazonChannelApp);
		util.waitUntilElementIsVisible(installButton, 30);
		util.click(installButton);

		if (!userCard.isEmpty()) {
			for (WebElement email : userCardEmail) {
				if (email.getText().equals(util.getConfigProperty("user_email"))) {
					util.click(email);
				}

			}

		}
		util.waitUntilElementIsVisible(addSalesChannelBtn, 30);
		util.isElementDisplyedAndValidate(addSalesChannelBtn);
		util.click(addSalesChannelBtn);
	}


	/**
	 * After clicking on add sales channel it is redirecting to onboarding step
	 */
	public void redirectionIsProperToOnboarding() {
		util.isElementDisplyedAndValidate(searchCountryInputField);
	}

	public void checklistForSuccessfulAcntConnection() {
		WebElement[] checklistArr = { primaryAccessCheckbox, acntIsNotInactiveCheckbox, professionalAcntCheckbox,
				connectingProfessionalSellerAcntCheckbox };

		for (WebElement checkList : checklistArr) {
			util.click(checkList);
			util.validateIsElementSelectedUsingAttribute(checkList);
		}
	}

	public void paymentMethodTooltipIsDisplayed() {
		util.moveToElement(paymentMethodLabel);
		util.isElementDisplyedAndValidate(tooltipPaymentMethod);
	}
	
	public void addTarget(WebElement ele) {
		if(ele.getTagName().equalsIgnoreCase("a")) {
			util.setAttribute(ele, TARGET, BLANK);
		}
	}

	public void openTermsConditionCheckBoxDoc() {
		boolean isTrue = false;
		String url = util.getPageURL();
		util.waitUntilElementIsVisible(termsConditionLink, 30);
		
		addTarget(termsConditionLink);
		
		util.click(termsConditionLink);
		util.getWindoHandleByUrl("https://amazon-sales-channel-app-backend.cifapps.com/");

		if (util.getPageURL().contains("Cedcommerce_Privacy_Statement.pdf")) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);

		util.gotoWindowByClosingCurrentOne(url);
		util.switchToIFrame();
	}

	public void cedLinkFooterIsFunctioning() {
		boolean isTrue = false;
		String url = util.getPageURL();
		util.waitUntilElementIsVisible(cedLinkFooter, 30);
		addTarget(cedLinkFooter);
		util.click(cedLinkFooter);
		util.getWindoHandleByUrl("https://docs.cedcommerce.com/shopify/multi-account-amazon-cedcommerce/?section=");
		if (util.getPageTitle().equals(docsByCedTitle)) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);

		util.gotoWindowByClosingCurrentOne(url);
		util.switchToIFrame();
	}

	public void bookCallLinkFooterIsFunctioning() {
		boolean isTrue = false;
		String url = util.getPageURL();
		util.waitUntilElementIsVisible(bookCallLinkFooter, 30);
		addTarget(bookCallLinkFooter);
		util.actionClick(bookCallLinkFooter);
		util.getWindoHandleByUrl("https://calendly.com/scale-business-with-cedcommerce/shopify-amazon-integration");
		util.waitUntilElementIsVisible(calendlyHeading, 30);
		if (util.getPageTitle().equals(calendlyTitle)) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);

		util.gotoWindowByClosingCurrentOne(url);
		util.switchToIFrame();
	}
	
	public void dataPrivacyLinkFooterIsFunctioning() {
		String url = util.getPageURL();
		util.waitUntilElementIsVisible(dataPrivacyRightsLinkFooter, 30);
		addTarget(dataPrivacyRightsLinkFooter);
		util.actionClick(dataPrivacyRightsLinkFooter);
		util.getWindoHandleByUrl("https://amazon-sales-channel-app-backend.cifapps.com/Data_Privacy_Rights.pdf");

		util.gotoWindowByClosingCurrentOne(url);
		util.switchToIFrame();
	}

	public void getSupportLinkFooterIsFunctioning() {
		util.click(getSupportLinkFooter);

		util.isElementDisplyedAndValidate(whatsAppGetSupportPage);

		util.getDriver().navigate().back();

		util.switchToIFrame();
//		util.waitUntilElementIsVisible(acntNameInputField, 30);
		util.waitUntilElementIsVisible(getSupportLinkFooter, 30);
	}

	/**
	 * While onboarding is not completed on clicking every section onboarding page
	 * is displayed.
	 */
	public void onboardingPageIsDisplayedOnEverySection() {
		WebElement[] sectionArr = { listingsApp, productLinkingApp, settingsApp, faqApp, overviewApp };
		WebElement[] sectionArrStaging = { listingsApp, settingsApp, faqApp, overviewApp };

		if (util.getConfigProperty(store).equals("staging")) {
			for (WebElement section : sectionArrStaging) {
				util.switchToDefaultContent();
				util.click(section);
				util.switchToIFrame();
				util.waitUntilElementIsVisible(searchCountryInputField, 30);
				util.isElementDisplyedAndValidate(searchCountryInputField);
			}

		} else if (util.getConfigProperty(store).equals("live")) {
			for (WebElement section : sectionArr) {
				util.switchToDefaultContent();
				util.click(section);
				util.switchToIFrame();
				util.waitUntilElementIsVisible(searchCountryInputField, 30);
				util.isElementDisplyedAndValidate(searchCountryInputField);
			}

		}

	}

	public void onboardingStepsAreIsDisplayed(int stepNum) {
		util.switchToDefaultContent();
		util.hold(1);
		util.switchToIFrame();
		boolean isTrue = false;
		util.waitUntilElementIsVisible(imgCC, 20);
		String text = onboardingStep.getText();
		text = StringUtils.substringBefore(text, ":").trim();

		if (text.contains(stepNum + "/3")) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);

	}

	public void countryListIsDisplayed() {
		util.waitUntilElementIsVisible(searchCountryInputField, 30);
		util.click(searchCountryInputField);
		util.listIsNotEmpty(countryList);
	}

	public void searchInputFieldAcceptsOnlyCharacters() {
		util.enterValue(searchCountryInputField, "XYz@123");
		util.hold(1);
		util.matchCharacterRegExpression(util.extractValueByAttributes(searchCountryInputField, val));
	}

	public void errMsgIsDisplayedWhenCountryIsInvalid() {
		util.click(connectSellerAcntBtn);
		util.hold(2);
		util.isElementDisplyedAndValidate(someInfoMissingErrMsg);
		util.isElementDisplyedAndValidate(kindlySelectCountry);
	}

	public void choseCountryIsMandatory() {
		util.isElementDisplyedAndValidate(choseCountryMandatoryField);
	}

	public void chooseCountry() {
		util.enterValue(searchCountryInputField, "India");
		util.hold(1);
		util.click(india);
		util.hold(1);
	}

	public void connectSellerAcntBtnFunctioning() {
		String url = util.getPageURL();
//		WebElement[] checklistArr = { primaryAccessCheckbox, acntIsNotInactiveCheckbox, professionalAcntCheckbox,
//				connectingProfessionalSellerAcntCheckbox };
//
//		for (WebElement checkList : checklistArr) {
//			util.enableButton(checkList);
//		}

		chooseCountry();
		util.click(connectSellerAcntBtn);
		util.getWindoHandleByUrl("https://sellercentral.amazon.in/ap/signin");
		util.isElementDisplyedAndValidate(userInpFieldAmz);
		util.gotoWindowByClosingCurrentOne(url);
		util.switchToIFrame();
	}

	public void connectionUnsuccessfulReasonAreDisplayed() {
		WebElement[] unsuccessReasonsArr = { reason1, reason2, reason3, reason4, connectionUnsuccessfulBanner,
				retryConnectingBtn };

		for (WebElement reason : unsuccessReasonsArr) {
			util.isElementDisplyedAndValidate(reason);
		}
	}

	public void clickOnConnectSellerAcnt() {
		util.click(connectSellerAcntBtn);
	}

	public void clickHereToKnowMoreLinkIsFunctioning() {
		boolean isTrue = false;
		String url = util.getPageURL();
		util.validateCursorProperty(clickHereLink, "pointer");
		addTarget(clickHereLink);
		util.click(clickHereLink);
		util.getWindoHandleByUrl(
				"https://docs.cedcommerce.com/shopify/multi-account-amazon-cedcommerce/?section=onboarding-the-app-4");

		if (util.getPageTitle().equals(docsByCedTitle)) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);

		util.gotoWindowByClosingCurrentOne(url);
		util.switchToIFrame();
	}

	public void retryConnectingBtnIsWorking() {
		util.click(retryConnectingBtn);
		util.waitUntilElementIsVisible(searchCountryInputField, 30);
		util.isElementDisplyedAndValidate(searchCountryInputField);
	}

	public void allCheckListCheckBoxAreChecked() {
		WebElement[] checklistArr = { primaryAccessCheckbox, acntIsNotInactiveCheckbox, professionalAcntCheckbox,
				connectingProfessionalSellerAcntCheckbox };

		for (WebElement checkList : checklistArr) {
			util.validateIsElementSelectedUsingAttribute(checkList);
		}
	}

	public void checkSearchCountryInputFieldIsEmpty() {
		boolean isTrue = false;

		if (util.extractValueByAttributes(searchCountryInputField, val).equals("")) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);
	}

	public void connectSellerBtnIsDisabled() {
		boolean isTrue = false;
		if (util.extractValueByAttributes(connectSellerAcntBtn, ariaDisabled).equals("true")) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);
//		util.validateIsElementDisabled(connectSellerAcntBtn);
	}

	public void enterCredentials() {
		util.getWindoHandleByUrl("https://sellercentral.amazon.in/ap/signin");
		util.enterValue(userInpFieldAmz, util.getConfigProperty("emailSC"));
		util.hold(1);
		util.enterValue(userPasswordInpFieldAmz, util.getConfigProperty("passwordSC"));
		util.hold(1);
		util.click(signInBtnAmz);

		if (!sendOTPBtn.isEmpty()) {
			util.click(sendOTPBtn.get(0));
		}

		util.click(submitOTPBtn);
//		util.hold(1);
//		util.click(sellerNextBtnAmz);
//		util.hold(1);
//		util.click(selectAcntBtnAmz);
//		util.hold(1);
//		util.enableButton(checkbox);
//		util.click(confirmBtnAmz);
	}

//	---------------------Onboarding 2nd Step--------------------------

	public void successAcntBannerIsDisplayed() {
		util.isElementDisplyedAndValidate(acntConnectionSuccessfulMsg);
	}

	public void createAcntNameIsMandatory() {
		util.enterValue(acntNameInputField, "");
		util.click(saveAndNextBtn);
		util.waitUntilElementIsVisible(someInfoMissingErrMsg, 10);
		util.isElementDisplyedAndValidate(acntNameMissingErrMsg);
	}

	public void acntNameInputFieldAcceptsVals() {
		boolean isTrue = false;
		util.enterValue(acntNameInputField, "Test@Amz.Automation");

		if (util.matchAllRegExpression(util.extractValueByAttributes(acntNameInputField, val))) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);
	}

	public void nameLimitIsDisplyed() {
		util.isElementDisplyedAndValidate(nameLimit);
	}

	public void nameLimitExceeds() {
		util.enterValue(acntNameInputField, char32);
		util.listIsNotEmpty(exceedLimitErrMsg);
		util.click(acntNameInputField);
		util.pressBackSpace();
		util.listIsEmpty(exceedLimitErrMsg);
	}

	public void saveAndNextBtnIsWorking() {
		util.clear(acntNameInputField);
		util.click(saveAndNextBtn);
		util.hold(2);
		util.isElementDisplyedAndValidate(someInfoMissingErrMsg);
	}

	public void footerLinksCursorProperty() {
		WebElement[] linksArr = { cedLinkFooter, bookCallLinkFooter, getSupportLinkFooter };
		for (WebElement arr : linksArr) {
			util.validateCursorProperty(arr, "pointer");
		}
	}

	public void setAcntNameAndGoTo3rdStep() {
		util.enterValue(acntNameInputField, AcntName);
		util.click(saveAndNextBtn);

		util.waitUntilElementIsVisible(completeAcntSetupBtn, 30);
	}

//	---------------------Onboarding 3rd Step--------------------------

	public void atleastOneRBMustBeSelected() {
		util.click(completeAcntSetupBtn);
		util.isElementDisplyedAndValidate(selectAtleastOneToProceedErrMsg);
	}

	public void radioBtnsAreWorking() {
		boolean isTrue = false;
		util.click(newAmzSellerRadioBtn);

		if (newAmzSellerRadioBtn.isSelected() && !existingSellerRadioBtn.isSelected()) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);

		isTrue = false;

		util.click(existingSellerRadioBtn);

		if (!newAmzSellerRadioBtn.isSelected() && existingSellerRadioBtn.isSelected()) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);
	}

	public void selectExistingAmzSellerRadioBtn() {
		util.enableButton(existingSellerRadioBtn);

	}

	public void disableExistingAmzSeller() {
		util.disableButton(existingSellerRadioBtn);

	}

	public void configSyncPrefSection() {
		multipleCheckboxesCanBeSelected();
	}

	public void multipleCheckboxesCanBeSelected() {
		WebElement[] checkBoxArr = { priceCheckBox, inventoryCheckBox, productDetailsCheckBox, imagesCheckBox };

		for (WebElement checkBox : checkBoxArr) {
			util.enableButton(checkBox);
			util.hold(1);
		}

		for (WebElement checkBox : checkBoxArr) {
			util.validateIsElementSelectedUsingAttribute(checkBox);
		}

		util.disableButton(priceCheckBox);
	}

	public void byDefaultSKUAndFBMAreChecked() {
		util.enableButton(alreadyHaveListingOnAmzCheckbox);

		WebElement[] checkBoxArr = { skuCheckBox, fbmCheckBox };

		for (WebElement checkBox : checkBoxArr) {
			util.validateIsElementSelectedUsingAttribute(checkBox);
			util.click(checkBox);
			util.hold(1);
			util.validateIsElementNotSelected(checkBox);
		}

		WebElement[] checkBoxArr2 = { fbmCheckBox, skuCheckBox };

		for (WebElement checkBox : checkBoxArr2) {
			util.click(checkBox);
			util.hold(1);
			util.validateIsElementSelectedUsingAttribute(checkBox);
		}

	}
	
	/**
	 * SKU and inventoryCheckBox, productDetailsCheckBox, imagesCheckBox, priceCheckBox is enable for linking.
	 */
	public void selectFbmCheckboxAndProductDetails() {
		util.enableButton(alreadyHaveListingOnAmzCheckbox);
		WebElement[] checkBoxArr = {fbmCheckBox, inventoryCheckBox, productDetailsCheckBox, imagesCheckBox, priceCheckBox };
		
		for(WebElement checkbox : checkBoxArr) {
			if(checkbox.getAttribute(ARIACHECKED).equalsIgnoreCase(FALSEVAL)) {
				util.click(checkbox);
				util.hold(1);
			}
		}
		
		util.enterValue(typeBrandNameInputField, "ced amz");
	}

	public void bothSKUAndBarCodeCanNotBeUnchecked() {
		WebElement[] checkBoxArr = { skuCheckBox, barcodeCheckBox };

		for (WebElement checkBox : checkBoxArr) {
			if (util.extractValueByAttributes(checkBox, ariaChecked).equals(trueVal)) {
				util.click(checkBox);
			}
		}

		util.click(completeAcntSetupBtn);
		util.hold(1);
		util.isElementDisplyedAndValidate(someInfoMissingErrMsg);
		util.isElementDisplyedAndValidate(errMsgWhenSKUAndBarcodeChecboxInNotSelected);

		if (util.extractValueByAttributes(skuCheckBox, ariaChecked).equals(falseVal)) {
			util.click(skuCheckBox);
		}

	}

	public void allCheckBoxAreClickable() {
		WebElement[] checkBoxArr = { barcodeCheckBox, fbaCheckBox, priceCheckBox, inventoryCheckBox,
				productDetailsCheckBox, imagesCheckBox };

		for (WebElement checkBox : checkBoxArr) {
			util.enableButton(checkBox);
			util.hold(1);
			util.validateIsElementSelectedUsingAttribute(checkBox);
		}

		for (WebElement checkBox : checkBoxArr) {
			util.click(checkBox);
			util.hold(1);
			util.validateIsElementNotSelected(checkBox);
		}
	}

	public void checkManufacturerCheckBoxIsWorking() {

		if (!util.extractValueByAttributes(describeFieldManufacturerBtn, "class").contains("active")) {
			util.click(manufacturerBtn);
		}

		util.isElementDisplyedAndValidate(brandNameInputField);

	}

	public void brandNameInputFieldIsEmpty() {
		util.click(completeAcntSetupBtn);
		util.hold(1);
		util.isElementDisplyedAndValidate(someInfoMissingErrMsg);
		util.isElementDisplyedAndValidate(errWhenBrandInputFieldIsEmpty);
	}

	public void brandNameInputFieldAcceptsAnyValue() {
		boolean isTrue = false;
		util.enterValue(brandNameInputField, "1aA@().");

		if (util.matchAllRegExpression(util.extractValueByAttributes(brandNameInputField, val))) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);
	}

	public void uncheckManufacturerCheckBox() {

		if (util.extractValueByAttributes(describeFieldManufacturerBtn, "class").contains("active")) {
			util.click(manufacturerBtn);
		}
	}

	public void checkOtherBtnIsWorking() {

		if (!util.extractValueByAttributes(describeOtherBtn, "class").contains("active")) {
			util.click(otherBtn);
		}

		util.isElementDisplyedAndValidate(otherBusinessInputField);

	}

	public void otherBusinessNameInputFieldIsEmpty() {
		util.click(completeAcntSetupBtn);
		util.hold(1);
		util.isElementDisplyedAndValidate(someInfoMissingErrMsg);
		util.isElementDisplyedAndValidate(errMsgWhenOtherInputFieldIsEmpty);

		util.click(completeAcntSetupBtn);
		util.hold(1);
		util.isElementDisplyedAndValidate(someInfoMissingErrMsg);
		util.isElementDisplyedAndValidate(errMsgWhenOtherInputFieldIsEmpty);

	}

	public void businessNameInputFieldAcceptsAnyValue() {
		boolean isTrue = false;
		util.enterValue(otherBusinessInputField, "1aA@().");

		if (util.matchAllRegExpression(util.extractValueByAttributes(otherBusinessInputField, val))) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);
	}

	public void uncheckOtherCheckBox() {

		if (util.extractValueByAttributes(describeOtherBtn, "class").contains("active")) {
			util.click(otherBtn);
		}
	}

	public void enableDropshipperBtn() {
		if (!util.extractValueByAttributes(dropshipperBtn, "class").contains("active")) {
			util.click(dropshipperBtn);
		}

	}

	public void allCheckBoxAreClickableNewSeller() {
		WebElement[] checkBoxArr = { manufacturerBtn, dropShipperCheckBox, retailerCheckBox, resellerDetailsCheckBox,
				otherBtn };

		for (WebElement checkBox : checkBoxArr) {
			util.click(checkBox);
			util.hold(1);
			util.validateIsElementSelectedUsingAttribute(checkBox);
		}

		for (WebElement checkBox : checkBoxArr) {
			util.click(checkBox);
			util.hold(1);
			util.validateIsElementNotSelected(checkBox);
		}
	}
	
	public void verifyCanSelectMultipleRoles() {
		util.refreshPage();
		util.switchToIFrame();
		util.waitUntilElementIsVisible(manufacturerBtn, 30);
		
		boolean isTrue = false;
		WebElement[] checkBoxArr = { retailerCheckBox, dropShipperCheckBox};
		WebElement[] checkBoxArr1 = { manufacturerBtn, dropShipperCheckBox, retailerCheckBox, resellerDetailsCheckBox,
				otherBtn };
		
		for (WebElement checkBox : checkBoxArr1) {
			
			if(checkBox.getAttribute("class").contains("active"))
				util.click(checkBox);
		}
		
		util.click(completeAcntSetupBtn);
		
		util.isElementDisplyedAndValidate(selectAtleastOneToProceedErrMsg);
		
		for (WebElement checkBox : checkBoxArr) {
			
			if(!checkBox.getAttribute("class").contains("active"))
				util.click(checkBox);
		}
		
		for (WebElement checkBox : checkBoxArr) {
			isTrue = false;
			if(checkBox.getAttribute("class").contains("active"))
				isTrue = true;
		}
		
		Assert.assertTrue(isTrue);
	}
	
	public void verifySettingsSelectedInOnbordingAreSameInSettings() {
		boolean isTrue = false;
		util.openSectionsInNewTab(SETTINGSSECTION);
		util.click(prodSettings);
		util.click(productDetailsBtn);
		util.isElementDisplyedAndValidate(productHeadingDetails);
		
		WebElement[] checkBoxArr = { fbmToggleBtn, fbmSKUToggleBtn, productImagesToggleBtn, productInfoToggleBtn};
		
		for (WebElement checkBox : checkBoxArr) {
			isTrue = false;
			
			if(checkBox.getAttribute(ARIACHECKED).contains(TRUEVAL))
				isTrue = true;
		}
		Assert.assertTrue(isTrue);
		
	}

	public void newSellerModal() {

		util.click(completeAcntSetupBtn);
		util.listIsNotEmpty(attentionModal);
	}

	public void manageItHereLinkIsWorking() {
		boolean isTrue = false;
		String url = util.getPageURL();
		util.click(manageItHereLink);
		util.getWindoHandleByUrl(amazonBrandRegister);

		if (util.getPageTitle().equals(amazonBrandTitle)) {
			isTrue = true;
		}
		

		util.gotoWindowByClosingCurrentOne(url);
		util.switchToIFrame();
		Assert.assertTrue(isTrue);
	}

	public void referenceLinkIsWorking() {
		boolean isTrue = false;
		String url = util.getPageURL();
		util.click(referenceLink);
		util.getWindoHandleByUrl(amazonSignInUrl);

		if (util.getPageTitle().equals("Amazon Sign-In")) {
			isTrue = true;
		}

		util.gotoWindowByClosingCurrentOne(url);
		util.switchToIFrame();
		closeModal();
		Assert.assertTrue(isTrue);
	}

	public void okIUnderstandBtnIsWorking() {
		util.click(crossBtn);
		util.hold(1);
		util.listIsEmpty(attentionModal);
	}
	
	public void closeModal() {
		util.click(crossBtnModal);
	}

	public void enableExistingSellerCheckbox() {
		util.click(existingSellerRadioBtn);

	}

	public void validateSelectedOption(String selected) {
		enableExistingSellerCheckbox();
		Select select = new Select(brandRegisterSelect);
		WebElement element = select.getFirstSelectedOption();
		Assert.assertEquals(element.getText(), selected);
	}

	public void selectForBrandRegister(String value) {
		boolean isTrue = false;
		util.selectByValue(brandRegisterSelect, value);
		util.hold(1);
		util.listIsEmpty(brandNameInputFieldList);
		util.isElementDisplyedAndValidate(notAppliedYetRadioBtn);
		util.isElementDisplyedAndValidate(pendingApprovalRadioBtn);
		util.isElementDisplyedAndValidate(willingToSellGenericRadioBtn);

		if (notAppliedYetRadioBtn.isSelected()) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);

		util.click(pendingApprovalRadioBtn);
		isTrue = false;

		if (pendingApprovalRadioBtn.isSelected()) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);

		util.click(willingToSellGenericRadioBtn);
		isTrue = false;

		if (willingToSellGenericRadioBtn.isSelected()) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);

		util.click(notAppliedYetRadioBtn);

	}

	public void complete3rdStep() {
		util.click(completeAcntSetupBtn);
		util.waitUntilElementIsVisible(overviewPage, 20);
	}

//	--------------------------Subscription Plan--------------------------------------

	public void pricingPlansAreShown() {
		util.isElementDisplyedAndValidate(pricingPlansSectionHeading);
	}

	public void plansAreDisplyed() {
		boolean isTrue = false;

		String[] planKey = { "Beginner", "Startup", "Scale", "Growth", "Advanced", "Professional", "Premium",
				"Enterprise" };

		for (int i = 0; i < pricingPlansNameList.size(); i++) {

			if (pricingPlansNameList.get(i).getText().contains(planKey[i])) {

				isTrue = true;
			}

			Assert.assertTrue(isTrue);
		}
	}

	public void eachPlanContainsBuyBtn() {
		boolean isTrue = false;
		util.isElementDisplyedAndValidate(continueWithFreeBtn);

		for (int i = 0; i < buyNowBtn.size(); i++) {
			isTrue = false;
			util.click(nextPricingPlanBtn);
			util.isElementDisplyedAndValidate(buyNowBtn.get(i));

			if (util.extractValueByAttributes(buyNowBtn.get(i), ariaDisabled).equals(falseVal)) {
				isTrue = true;
			}
			Assert.assertTrue(isTrue);
		}
	}

	public void cardsMoveWhenClickAtForwardBackwardBtn() {
		util.refreshPage();
		util.switchToIFrame();
		util.waitUntilElementIsVisible(prevPricingPlanBtn);
		boolean isTrue = false;
		String classAtCardZeroBeforeClickingAtNextBtn = util.extractValueByAttributes(pricingPlanCards.get(0),
				classVal);
		util.click(nextPricingPlanBtn);
		String classAtCardZeroAfterClickingAtNextBtn = util.extractValueByAttributes(pricingPlanCards.get(0), classVal);

		if (!classAtCardZeroBeforeClickingAtNextBtn.equals(classAtCardZeroAfterClickingAtNextBtn)) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);

		String classAtCardZeroBeforeClickingAtPreviousBtn = util.extractValueByAttributes(pricingPlanCards.get(0),
				classVal);
		util.click(prevPricingPlanBtn);
		String classAtCardZeroAfterClickingAtPreviousBtn = util.extractValueByAttributes(pricingPlanCards.get(0),
				classVal);

		if (!classAtCardZeroBeforeClickingAtPreviousBtn.equals(classAtCardZeroAfterClickingAtPreviousBtn)) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);
	}

	public void carouselIndicatorIsDisplayedUnderPricingPlan() {
		util.isElementDisplyedAndValidate(carouselIndicator);
	}

	public void onClickingBuyNowBtnRedirectsToPaymentPage() {
		util.refreshPage();
		util.switchToIFrame();
		util.waitUntilElementIsVisible(prevPricingPlanBtn);

		for (int i = 0; i < buyNowBtn.size(); i++) {

			for (int j = 0; j < i; j++) {
				util.click(nextPricingPlanBtn);
			}

			util.click(buyNowBtn.get(i));
			util.switchToDefaultContent();
			util.waitUntilElementIsVisible(headingApproveSubscriptionPage, 30);
			util.isElementDisplyedAndValidate(headingApproveSubscriptionPage);
			util.getDriver().navigate().back();
			util.switchToIFrame();
			util.waitUntilElementIsVisible(prevPricingPlanBtn, 30);

		}
	}

	public void contactUsBtnWorking() {
		util.click(getSupportBtn);
		util.isElementDisplyedAndValidate(whatsAppGetSupportPage);
		util.getDriver().navigate().back();
		util.switchToDefaultContent();
		util.switchToIFrame();
		util.waitUntilElementIsVisible(prevPricingPlanBtn, 30);
	}

	public void faqsAreDisplayed() {
		util.isElementDisplyedAndValidate(faqHeadingPricingPlan);
	}

	public void viewAllQuestionsBtnIsWorking() {
		util.waitUntilElementIsVisible(viewAllQuestions, 30);
		util.click(viewAllQuestions);
		util.waitUntilElementIsVisible(faqAndTroubleShootPageHeading, 30);
		util.isElementDisplyedAndValidate(faqAndTroubleShootPageHeading);
		util.getDriver().navigate().back();
		util.switchToDefaultContent();
		util.switchToIFrame();
		util.waitUntilElementIsVisible(prevPricingPlanBtn, 30);
	}

	public void getSupportLinkOnPricingPlan() {
		util.click(getSupportLinkFooter);

		util.isElementDisplyedAndValidate(whatsAppGetSupportPage);

		util.getDriver().navigate().back();
		util.switchToDefaultContent();
		util.switchToIFrame();
		util.waitUntilElementIsVisible(prevPricingPlanBtn, 30);
	}

	public void selectAppStore() {
		util.click(salesChannelButton);

		if (util.getConfigProperty("store").equals("live")) {
			util.click(liveStore);
		} else {
			util.click(stagingStore);
		}
//
//		util.click(util.getDriver()
//				.findElement(By.cssSelector("ul#search-results a[href*='/apps/amazon-sales-channel-1']")));

		util.switchToIFrame();
	}

	public void selectFreePlan() {
		util.click(continueWithFreeBtn);
		util.waitUntilElementIsVisible(selectedPlanDetails, 15);
		util.click(confirmAndProceedBtn);
		util.waitUntilElementIsVisible(overViewPageHeading, 30);
		util.isElementDisplyedAndValidate(overViewPageHeading);
	}

	public void afterSelectingFreePricingPlanCurrentPlanIsShownOnOverView() {
		boolean isTrue = false;

		if (activePlan.getText().contains("Free Plan")) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);

		isTrue = false;
		util.click(viewAllPlansButton);
		util.waitUntilElementIsVisible(pricingPlanPageHeading, 30);

		if (util.extractValueByAttributes(freePlanContainsCurrentPlanBtn, ariaDisabled).equals("true")) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);
	}

	public void accountNameContainsCountryName() {
		boolean isTrue = false;
		util.refreshPage();
		util.switchToIFrame();
		util.waitUntilElementIsVisible(subscriptionHeading, 30);
		String text = accountConnectionCountry.getText();
		text = StringUtils.substringAfter(text, "(");
		text = StringUtils.substringBefore(text, ")");
		text = StringUtils.substringBefore(text, "-");

		if (text.equals("India")) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);
	}

	public void customPlanCardIsDisplayed() {

		util.click(planSwitcherBtn);
		util.isElementDisplyedAndValidate(customPlanCard);
		util.isElementDisplyedAndValidate(contactNowBtn);

		util.click(planSwitcherBtn);
		util.isElementDisplyedAndValidate(customPlanCard);
		util.isElementDisplyedAndValidate(contactNowBtn);

	}
}
