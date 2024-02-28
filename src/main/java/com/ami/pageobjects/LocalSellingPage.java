package com.ami.pageobjects;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ami.resources.Utilities;

public class LocalSellingPage extends LocalSellingPageOR {
	Utilities util;
	private static final String COUNTRY = "India";
	private static final String STATE = "Uttar";
	private static final String CITY = "Lucknow";
	private static final String ZIPCODE = "226024";
	private static final String CONTACTNUM = "9876543210";
	private static final String ADDRESSLINE1 = "3/460 Vishwas Khand";
	private static final String EMAIL = "abc@jmail.com";
	private static final String TIMEZONE = "Asia/Kolkata";
	private static final String STATUSXPATH = "//div[@class='Custom__Card--StoreGrid']//h6[text()='";
	private String selectedCity;
	private String storeName;
	private String storeCode;
	private String enteredAddressLine1;
	private String selectedLocationStatus;
	private String enteredZipCode;
	private String orderId;

	public LocalSellingPage(Utilities util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
	}

	public void disableLocalSelling() {
		util.click(localSelling);
		util.waitUntilElementIsVisible(lastSyncBOPIS, 10);
		String currentTime = lastSyncBOPIS.getText().trim();

		if (util.extractValueByAttributes(disableBOPISBtn, ARIAPRESSED).equals(FALSEVAL)) {
			util.click(disableBOPISBtn);
			util.clickOnSaveBtn();

			Assert.assertNotEquals(currentTime, lastSyncBOPIS.getText().trim());
		}
	}

	public void enableLocalSelling() {
		util.click(localSelling);
		util.waitUntilElementIsVisible(lastSyncBOPIS, 10);
		String currentTime = lastSyncBOPIS.getText().trim();

		if (util.extractValueByAttributes(enableBOPISBtn, ARIAPRESSED).equals(FALSEVAL)) {
			util.click(enableBOPISBtn);
			if(bopisOrderSyncToggleBtn.getAttribute(ARIACHECKED).equalsIgnoreCase(FALSEVAL)) {			
				util.click(bopisOrderSyncToggleBtn);
			}
			util.clickOnSaveBtn();

			Assert.assertNotEquals(currentTime, lastSyncBOPIS.getText().trim());
		}
	}

	public void localSellingIsDisabledFromSetting() {
		util.openSectionsInNewTab(SETTINGSSECTION);
		util.switchToDefaultContent();
		util.getDriver().findElement(By.cssSelector("a[href*='apps/amazon-sales-channel-1/panel/user/dashboard']")).click();
		util.hold(5);
		util.refreshPage();
		util.clickOnSettings();
		disableLocalSelling();
		util.goToSection("overview");
		util.waitUntilElementIsVisible(activities, 30);
		util.refreshPage();
		util.switchToIFrame();
		util.moveToElement(activities);
		util.listIsEmpty(localDeliverySectionList);
	}

	public void localSellingIsEnabledFromSetting() {
		util.goToSection(SETTINGSSECTION);
		util.click(localSelling);

		if (util.extractValueByAttributes(disableBOPISBtn, ARIAPRESSED).equals(TRUEVAL)) {
			util.click(enableBOPISBtn);
			util.clickOnSaveBtn();
		}

		util.goToSection("overview");
		util.waitUntilElementIsVisible(activities, 30);
		util.refreshPage();
		util.switchToIFrame();
		util.moveToElement(activities);
		util.moveToElement(localDeliverySectionList.get(0));
		util.listIsNotEmpty(localDeliverySectionList);
	}

	public void manageBtnLocalSellingIsClickable() {
		util.click(manageBtnLocalSelling);
		util.isElementDisplyedAndValidate(localSellingPageHeading);
		util.click(backBtn);
		util.isElementDisplyedAndValidate(localDeliverySection);
	}

	public void orderCountLocalSellingIsClickable() {
		boolean isTrue = false;
		util.click(ordersLocalSelling);
		util.isElementDisplyedAndValidate(localSellingPageHeading);
		String isOrderTabIsSelected = util.extractValueByAttributes(ordersTabLocalSellingPage, ARIASELECTED);

		if (isOrderTabIsSelected.equals(TRUEVAL)) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);

		util.click(backBtn);
		util.isElementDisplyedAndValidate(localDeliverySection);
	}

	public void validateOrderCount() {
		boolean isTrue = false;
		int orderSize = 0;
		util.hold(10);
		int orderCount = Integer.parseInt(ordersLocalSelling.getText());
		util.click(ordersLocalSelling);

		if (orderCount == 0) {
			util.listIsNotEmpty(notFoundImg);
		}

		util.hold(5);

		orderSize = ordersList.size();

		while (util.extractValueByAttributes(nextPaginationBtn, ARIADISABLED) == null) {
			util.click(nextPaginationBtn);
			util.hold(5);
			orderSize += ordersList.size();
		}

		System.out.println("OrderSize: " + orderSize + "\t" + "OrderCount: " + orderCount);

		if (orderSize == orderCount) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);

		util.click(backBtn);
		util.isElementDisplyedAndValidate(localDeliverySection);
	}

	public void storesCountLocalSellingIsClickable() {
		boolean isTrue = false;
		util.waitUntilElementIsVisible(storesLocalSelling, 30);
		util.click(storesLocalSelling);
		util.waitUntilElementIsVisible(localSellingPageHeading, 30);
		util.isElementDisplyedAndValidate(localSellingPageHeading);
		String isStoresTabIsSelected = util.extractValueByAttributes(storesTabLocalSellingPage, ARIASELECTED);

		if (isStoresTabIsSelected.equals(TRUEVAL)) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);
		util.click(backBtn);
		util.isElementDisplyedAndValidate(localDeliverySection);
	}

	public void validateStoresCount() {
		boolean isTrue = false;
		int storesSize = 0;
		int storesCount = Integer.parseInt(storesLocalSelling.getText());
		util.click(storesLocalSelling);

		if (storesCount == 0) {
			return;
		}

		util.hold(2);
		storesSize = sellerList.size();
		util.hold(2);

		while (util.extractValueByAttributes(nextPaginationBtn, ARIADISABLED) == null) {
			util.click(nextPaginationBtn);
			util.hold(5);
			storesSize = storesSize + sellerList.size();
			util.hold(2);
		}

		if (storesSize == storesCount) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);

	}

	public void searchInputFieldIsWorking() {
//		for stores search input field
		boolean isTrue = false;
		util.click(storesTabLocalSellingPage);
		util.enterValue(searchStoresInputFieldLocalSelling, "123!@#$%ASDFGHJ");
		util.hold(5);
		util.listIsNotEmpty(notFoundImg);
		String valEnteredInInputField = util.extractValueByAttributes(searchStoresInputFieldLocalSelling, VAL);

		if (util.matchAllRegExpression(valEnteredInInputField)) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);
		isTrue = false;

		util.click(searchStoresInputFieldLocalSelling);
		util.click(clearInputFieldBtnStore);
		valEnteredInInputField = util.extractValueByAttributes(searchStoresInputFieldLocalSelling, VAL);

		if (valEnteredInInputField.equals("")) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);
		isTrue = false;

		util.click(ordersTabLocalSellingPage);
		util.enterValue(searchOrdersInputFieldLocalSelling, "000000000000000XYZ!@#$%^&*");
		util.hold(5);
		util.listIsNotEmpty(notFoundImg);
		valEnteredInInputField = util.extractValueByAttributes(searchOrdersInputFieldLocalSelling, VAL);

		if (util.matchRegExpressionNumbersOnly(valEnteredInInputField)) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);
		isTrue = false;

		util.click(searchOrdersInputFieldLocalSelling);
		util.click(clearInputFieldBtnOrder);
		valEnteredInInputField = util.extractValueByAttributes(searchOrdersInputFieldLocalSelling, VAL);

		if (valEnteredInInputField.equals("")) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);
	}

	public void selectStoresTab() {
		util.click(storesTabLocalSellingPage);
	}

	public void clickOnMoreFilterButton() {
		util.click(moreFiltersButton);
		util.hold(1);
	}

	public void moreFilterWorking() {
		clickOnMoreFilterButton();
		if (util.extractValueByAttributes(statusToggleButtonFilterModal, ARIAEXPANDED).equals(FALSEVAL)) {

			util.click(statusToggleButtonFilterModal);
			util.hold(1);
		}
		util.click(crossBtnMoreFilterModal);
		util.hold(1);
		util.listIsEmpty(statusToggleButtonFilterModalList);

	}

	public void selectStoreStatus() {
		verifyStoreStatus(ACTIVE);
		verifyStoreStatus(INACTIVE);
	}

	private void verifyStoreStatus(String statusToVerify) {
		boolean isTrue = false;

		util.click(moreFiltersButton);
		util.hold(1);

		if (util.extractValueByAttributes(statusToggleButtonFilterModal, ARIAEXPANDED).equals(FALSEVAL)) {
			util.click(statusToggleButtonFilterModal);
			util.hold(1);
		}

		util.selectByValue(selectFilterModal, statusToVerify);
		util.hold(2);
		util.click(doneBtnFilterModal);

		for (WebElement status : statusOfStores) {
			if (status.getText().equals(statusToVerify)) {
				isTrue = true;
			}
		}

		Assert.assertTrue(isTrue);
	}

	public void clearBtnInMoreFiltersModalIsWorking() {
		boolean isTrue = false;
		util.click(moreFiltersButton);
		util.hold(1);
		System.out.println(util.extractValueByAttributes(statusToggleButtonFilterModal, ARIAEXPANDED));
		if (util.extractValueByAttributes(statusToggleButtonFilterModal, ARIAEXPANDED).equals(FALSEVAL)) {

			util.click(statusToggleButtonFilterModal);
			util.hold(1);
		}
		util.click(clearStatusBtn);
		util.hold(2);
		util.click(doneBtnFilterModal);

		System.out.println(util.extractValueByAttributes(nextPaginationBtn, ARIADISABLED));
		do {

			for (WebElement status : statusOfStores) {
				if (status.getText().equals(INACTIVE)) {
					isTrue = true;
				} else if (status.getText().equals(ACTIVE)) {
					isTrue = true;
					break;
				}
			}

			util.click(nextPaginationBtn);
		}while (util.extractValueByAttributes(nextPaginationBtn, ARIADISABLED) == null);

		Assert.assertTrue(isTrue);
	}

	public void selectInactiveStatus() {
		util.click(moreFiltersButton);
		util.hold(1);
		util.hold(1);
		if (util.extractValueByAttributes(statusToggleButtonFilterModal, ARIAEXPANDED).equals(FALSEVAL)) {

			util.click(statusToggleButtonFilterModal);
			util.hold(1);
		}
		util.selectByValue(selectFilterModal, INACTIVE);
		util.hold(2);
		util.click(doneBtnFilterModal);
	}

	public void clickOnClearAllFiltersBtn() {
		boolean isTrue = false;
		util.click(moreFiltersButton);
		util.hold(1);
		if (util.extractValueByAttributes(statusToggleButtonFilterModal, ARIAEXPANDED).equals(FALSEVAL)) {

			util.click(statusToggleButtonFilterModal);
			util.hold(1);
		}
		util.click(clearFiltersBtn);
		util.hold(2);
		util.click(doneBtnFilterModal);

		do {

			for (WebElement status : statusOfStores) {
				if (status.getText().equals(INACTIVE)) {
					isTrue = true;
				} else if (status.getText().equals(ACTIVE)) {
					isTrue = true;
					break;
				}
			}

			util.click(nextPaginationBtn);
		}while (util.extractValueByAttributes(nextPaginationBtn, ARIADISABLED) == null);

		Assert.assertTrue(isTrue);
	}

	public void createNewStoreBtnIsWorking() {
		boolean isTrue = false;
		util.click(storesTabLocalSellingPage);
		util.click(createNewStoreBtn);

		if (pageTitle.getText().equals("New Store")) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);

	}

	public void backBtnIsWorking() {
		util.click(backBtn);
		util.isElementDisplyedAndValidate(storesTabLocalSellingPage);
	}

	public void storeNameInputFieldAcceptsAllCharacters() {
		boolean isTrue = false;
		util.enterValue(storeNameInputField, "123Abc!@#<>");
		util.hold(1);
		util.click(saveAndCreateStoreBtn);
		util.hold(2);

		if (util.extractValueByAttributes(storeNameInputField, VAL).equals("123Abc!@#<>")
				&& (storeNameDoesNotContains.isDisplayed())) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);
		util.clear(storeNameInputField);
	}

	public void storeNameInputFieldAcceptsOnly40Words() {
		String sentence = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.";
		boolean isTrue = false;
		util.enterValue(storeNameInputField, sentence);
		String totalWordsInStoreNameInputField = util.extractValueByAttributes(storeNameInputField, VAL);
		char[] charArr = totalWordsInStoreNameInputField.toCharArray();

		if (charArr.length == Integer.parseInt(StringUtils.substringBefore(totalCharacters.getText(), "/"))) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);
		util.clear(storeNameInputField);
	}

	public void validateStoreNameIsMandatoryField() {
		storeCode = String.valueOf(util.randomNum());
		storeName = "Store_" + storeCode;
		util.click(createNewStoreBtn);
		storeNameInputFieldAcceptsAllCharacters();
		storeNameInputFieldAcceptsOnly40Words();
		util.enterValue(storeCodeInputField, storeCode);
		util.enterValue(streetAddressInputField, ADDRESSLINE1);
		util.enterValue(addCountryInputField, COUNTRY);
		util.click(indiaCountry);
		util.hold(5);
		util.click(addStateInputField);
		util.enterValue(addStateInputField, STATE);
		util.click(up);
		util.hold(5);
		util.click(addCityInputField);
		util.enterValue(addCityInputField, CITY);
		util.click(lko);
		util.selectByValue(utcTimeZone, TIMEZONE);
		util.enterValue(addZipCode, ZIPCODE);
		util.enterValue(contactNumEle, CONTACTNUM);
		util.enterValue(emailId, EMAIL);
		util.selectByValue(storeStatus, ACTIVE);
		util.click(saveAndCreateStoreBtn);
		util.hold(2);
		util.isElementDisplyedAndValidate(errToastMsgToFillMandatoryField);
		util.isElementDisplyedAndValidate(storeNameIsMandatoryErrMsg);
	}

	public void storeCodeDoesNotAcceptsSpecialChars() {
		String invalidStoreCode = "~!@#$%^&*()_+`{}[]|\';:?/><.,dkjhksdah121345";
		util.enterValue(storeCodeInputField, invalidStoreCode);
		util.click(saveAndCreateStoreBtn);
		util.hold(2);
		util.isElementDisplyedAndValidate(storeCodeIsNotInCorrectFormErrMsg);
	}

	public void validateStoreCodeIsMandatoryField() {
		util.enterValue(storeNameInputField, storeName);
		storeCodeDoesNotAcceptsSpecialChars();
		util.clear(storeCodeInputField);
		util.enterValue(streetAddressInputField, ADDRESSLINE1);
		util.enterValue(addCountryInputField, COUNTRY);
		util.click(indiaCountry);
		util.hold(5);
		util.click(addStateInputField);
		util.enterValue(addStateInputField, STATE);
		util.click(up);
		util.hold(5);
		util.click(addCityInputField);
		util.enterValue(addCityInputField, CITY);
		util.click(lko);
		util.selectByValue(utcTimeZone, TIMEZONE);
		util.enterValue(addZipCode, ZIPCODE);
		util.enterValue(contactNumEle, CONTACTNUM);
		util.enterValue(emailId, EMAIL);
		util.selectByValue(storeStatus, ACTIVE);
		util.click(saveAndCreateStoreBtn);
		util.hold(2);
		util.isElementDisplyedAndValidate(errToastMsgToFillMandatoryField);
		util.isElementDisplyedAndValidate(storeCodeIsMandatoryErrMsg);
	}

	public void addressLine1CanHaveOnly180Chars() {
		String address = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took.";
		util.enterValue(streetAddressInputField, address);
		util.enterValue(streetAddressInputField2, address);
		util.enterValue(streetAddressInputField3, address);
		util.click(saveAndCreateStoreBtn);
		util.hold(2);
		util.isElementDisplyedAndValidate(addressLine1CanHave180CharsErrMsg);
		util.isElementDisplyedAndValidate(addressLine2CanHave60CharsErrMsg);
		util.isElementDisplyedAndValidate(addressLine3CanHave60CharsErrMsg);
		util.clear(streetAddressInputField2);
		util.clear(streetAddressInputField3);
	}

	public void validateAddressLine1IsMandatoryField() {
		util.enterValue(storeNameInputField, storeName);
		util.enterValue(storeCodeInputField, storeCode);
		addressLine1CanHaveOnly180Chars();
		util.click(streetAddressInputField);
		util.clear(streetAddressInputField);
		util.enterValue(addCountryInputField, COUNTRY);
		util.click(indiaCountry);
		util.hold(5);
		util.click(addStateInputField);
		util.enterValue(addStateInputField, STATE);
		util.click(up);
		util.hold(5);
		util.click(addCityInputField);
		util.enterValue(addCityInputField, CITY);
		util.click(lko);
		util.selectByValue(utcTimeZone, TIMEZONE);
		util.enterValue(addZipCode, ZIPCODE);
		util.enterValue(contactNumEle, CONTACTNUM);
		util.enterValue(emailId, EMAIL);
		util.selectByValue(storeStatus, ACTIVE);
		util.click(saveAndCreateStoreBtn);
		util.hold(2);
		util.isElementDisplyedAndValidate(errToastMsgToFillMandatoryField);
		util.isElementDisplyedAndValidate(addressLine1IsMandatoryErrMsg);
	}

	public void validateCountryIsMandatoryField() {
		util.enterValue(storeNameInputField, storeName);
		util.enterValue(storeCodeInputField, storeCode);
		util.enterValue(streetAddressInputField, ADDRESSLINE1);
		util.clear(addCountryInputField);
		util.selectByValue(utcTimeZone, TIMEZONE);
		util.enterValue(addZipCode, ZIPCODE);
		util.enterValue(contactNumEle, CONTACTNUM);
		util.enterValue(emailId, EMAIL);
		util.selectByValue(storeStatus, ACTIVE);
		util.click(saveAndCreateStoreBtn);
		util.hold(2);
		util.isElementDisplyedAndValidate(errToastMsgToFillMandatoryField);
		util.isElementDisplyedAndValidate(countryIsMandatoryErrMsg);
	}

	public void validateStateIsMandatoryField() {
		util.enterValue(storeNameInputField, storeName);
		util.enterValue(storeCodeInputField, storeCode);
		util.enterValue(streetAddressInputField, ADDRESSLINE1);
		util.enterValue(addCountryInputField, COUNTRY);
		util.click(indiaCountry);
		util.hold(5);
		util.clear(addStateInputField);
		util.selectByValue(utcTimeZone, TIMEZONE);
		util.enterValue(addZipCode, ZIPCODE);
		util.enterValue(contactNumEle, CONTACTNUM);
		util.enterValue(emailId, EMAIL);
		util.selectByValue(storeStatus, ACTIVE);
		util.click(saveAndCreateStoreBtn);
		util.hold(2);
		util.isElementDisplyedAndValidate(errToastMsgToFillMandatoryField);
		util.isElementDisplyedAndValidate(stateIsMandatoryErrMsg);
	}

	public void validateCityIsMandatoryField() {
		util.enterValue(storeNameInputField, storeName);
		util.enterValue(storeCodeInputField, storeCode);
		util.enterValue(streetAddressInputField, ADDRESSLINE1);
		util.enterValue(addCountryInputField, COUNTRY);
		util.click(indiaCountry);
		util.hold(5);
		util.click(addStateInputField);
		util.enterValue(addStateInputField, STATE);
		util.click(up);
		util.hold(5);
		util.clear(addCityInputField);
		util.selectByValue(utcTimeZone, TIMEZONE);
		util.enterValue(addZipCode, ZIPCODE);
		util.enterValue(contactNumEle, CONTACTNUM);
		util.enterValue(emailId, EMAIL);
		util.selectByValue(storeStatus, ACTIVE);
		util.click(saveAndCreateStoreBtn);
		util.hold(2);
		util.isElementDisplyedAndValidate(errToastMsgToFillMandatoryField);
		util.isElementDisplyedAndValidate(cityIsMandatoryErrMsg);
	}

	public void validateUTCIsMandatoryField() {
		util.refreshPage();
		util.switchToIFrame();
		util.click(manageBtnLocalSelling);
		util.waitUntilElementIsVisible(storesTabLocalSellingPage, 30);
		util.click(storesTabLocalSellingPage);
		util.click(createNewStoreBtn);
		util.enterValue(storeNameInputField, storeName);
		util.enterValue(storeCodeInputField, storeCode);
		util.enterValue(streetAddressInputField, ADDRESSLINE1);
		util.enterValue(addCountryInputField, COUNTRY);
		util.click(addCountryInputField);
		util.hold(1);
		util.click(indiaCountry);
		util.hold(5);
		util.click(addStateInputField);
		util.enterValue(addStateInputField, STATE);
		util.click(up);
		util.hold(5);
		util.click(addCityInputField);
		util.enterValue(addCityInputField, CITY);
		util.click(lko);
		util.enterValue(addZipCode, ZIPCODE);
		util.enterValue(contactNumEle, CONTACTNUM);
		util.enterValue(emailId, EMAIL);
		util.selectByValue(storeStatus, ACTIVE);
		util.click(saveAndCreateStoreBtn);
		util.hold(2);
		util.isElementDisplyedAndValidate(errToastMsgToFillMandatoryField);
		util.isElementDisplyedAndValidate(utcIsMandatoryErrMsg);

	}

	public void zipCodeCanBeOnlyOfSixDigits() {
		boolean isTrue = false;
		util.enterValue(addZipCode, "22602");
		util.click(saveAndCreateStoreBtn);
		util.hold(2);
		util.isElementDisplyedAndValidate(zipCodeMustContains6DigitsErrMsg);

		util.enterValue(addZipCode, "2!@#$%^&*(SDFGHJ");
		util.click(saveAndCreateStoreBtn);
		util.hold(2);
		util.isElementDisplyedAndValidate(zipCodeMustContains6DigitsErrMsg);

		if (util.extractValueByAttributes(addZipCode, VAL).equals("2")) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);
		isTrue = false;

		if (util.extractValueByAttributes(addZipCode, "maxlength").equals("6")) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);
	}

	public void validateZipCodeIsMandatoryField() {
		util.enterValue(storeNameInputField, storeName);
		util.enterValue(storeCodeInputField, storeCode);
		util.enterValue(streetAddressInputField, ADDRESSLINE1);
		util.enterValue(addCountryInputField, COUNTRY);
		util.click(indiaCountry);
		util.hold(5);
		util.click(addStateInputField);
		util.enterValue(addStateInputField, STATE);
		util.click(up);
		util.hold(5);
		util.click(addCityInputField);
		util.enterValue(addCityInputField, CITY);
		util.click(lko);
		util.selectByVisibleText(utcTimeZone, TIMEZONE);
		zipCodeCanBeOnlyOfSixDigits();
		util.clear(addZipCode);
		util.enterValue(contactNumEle, CONTACTNUM);
		util.enterValue(emailId, EMAIL);
		util.selectByValue(storeStatus, ACTIVE);
		util.click(saveAndCreateStoreBtn);
		util.hold(2);
		util.isElementDisplyedAndValidate(errToastMsgToFillMandatoryField);
		util.isElementDisplyedAndValidate(zipCodeIsMandatoryErrMsg);
	}

	public void contactNumberCanBeOnlyOfTenDigits() {
		boolean isTrue = false;
		String contactNumber = "987654321";
		util.enterValue(contactNumEle, contactNumber);
		util.click(saveAndCreateStoreBtn);
		util.hold(2);
		util.isElementDisplyedAndValidate(contactMustBeInCorrectFormatErrMsg);

		util.enterValue(contactNumEle, "987654321!@#$%^&*(SDFGHJ");

		if (util.extractValueByAttributes(contactNumEle, VAL).equals(contactNumber)) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);
		isTrue = false;

		if (util.extractValueByAttributes(contactNumEle, "maxlength").equals("10")) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);
	}

	public void validateContactNumberIsMandatoryField() {
		util.enterValue(storeNameInputField, storeName);
		util.enterValue(storeCodeInputField, storeCode);
		util.enterValue(streetAddressInputField, ADDRESSLINE1);
		util.enterValue(addCountryInputField, COUNTRY);
		util.click(indiaCountry);
		util.hold(5);
		util.click(addStateInputField);
		util.enterValue(addStateInputField, STATE);
		util.click(up);
		util.hold(5);
		util.click(addCityInputField);
		util.enterValue(addCityInputField, CITY);
		util.click(lko);
		util.selectByVisibleText(utcTimeZone, TIMEZONE);

		util.enterValue(addZipCode, ZIPCODE);
		util.clear(contactNumEle);
		util.enterValue(emailId, EMAIL);
		util.selectByValue(storeStatus, ACTIVE);
		util.click(saveAndCreateStoreBtn);
		util.hold(2);
		util.isElementDisplyedAndValidate(errToastMsgToFillMandatoryField);
		util.isElementDisplyedAndValidate(contactIsMandatoryErrMsg);
	}

	public void emailCorrectFormat() {
		String wrongEmail = "abc@def";
		util.enterValue(emailId, wrongEmail);
		util.click(saveAndCreateStoreBtn);
		util.hold(2);
		util.isElementDisplyedAndValidate(eMailMustBeInCorrectFormatErrMsg);
	}

	public void validateEmailIsMandatoryField() {
		util.enterValue(storeNameInputField, storeName);
		util.enterValue(storeCodeInputField, storeCode);
		util.enterValue(streetAddressInputField, ADDRESSLINE1);
		util.enterValue(addCountryInputField, COUNTRY);
		util.click(indiaCountry);
		util.hold(5);
		util.click(addStateInputField);
		util.enterValue(addStateInputField, STATE);
		util.click(up);
		util.hold(5);
		util.click(addCityInputField);
		util.hold(1);
		util.enterValue(addCityInputField, CITY);
		util.click(lko);
		util.selectByVisibleText(utcTimeZone, TIMEZONE);

		util.enterValue(addZipCode, ZIPCODE);
		util.enterValue(contactNumEle, CONTACTNUM);
		emailCorrectFormat();
		util.clear(emailId);
		util.selectByValue(storeStatus, ACTIVE);
		util.click(saveAndCreateStoreBtn);
		util.hold(2);
		util.isElementDisplyedAndValidate(errToastMsgToFillMandatoryField);
		util.isElementDisplyedAndValidate(eMailIsMandatoryErrMsg);
	}

	public void operationalDaysCanBeSelected() {
		boolean isTrue = false;
		WebElement[] daysList = { sunday, monday, tuesday, wednesday, thursday, friday, saturday };

		for (WebElement days : daysList) {
			isTrue = false;

			if (util.extractValueByAttributes(days, CLASSVAL).equals("Custom__Block--Button")) {
				util.click(days);
			}

			if (util.extractValueByAttributes(days, CLASSVAL).equals("Custom__Block--Button active")) {
				isTrue = true;
			}
		}

		Assert.assertTrue(isTrue);
	}

	public void openingAndClosingTimeIsShown() {
		WebElement[] timeList = { sundayStoreOpenTimeInHours, sundayStoreOpenTimeInMin, sundayStoreCloseTimeInHours,
				sundayStoreCloseTimeInMin, mondayStoreOpenTimeInHours, mondayStoreOpenTimeInMin,
				mondayStoreCloseTimeInHours, mondayStoreCloseTimeInMin, tuesdayStoreOpenTimeInHours,
				tuesdayStoreOpenTimeInMin, tuesdayStoreCloseTimeInHours, tuesdayStoreCloseTimeInMin,
				wednesdayStoreOpenTimeInHours, wednesdayStoreOpenTimeInMin, wednesdayStoreCloseTimeInHours,
				wednesdayStoreCloseTimeInMin, thursdayStoreOpenTimeInHours, thursdayStoreOpenTimeInMin,
				thursdayStoreCloseTimeInHours, thursdayStoreCloseTimeInMin, fridayStoreOpenTimeInHours,
				fridayStoreOpenTimeInMin, fridayStoreCloseTimeInHours, fridayStoreCloseTimeInMin,
				saturdayStoreOpenTimeInHours, saturdayStoreOpenTimeInMin, saturdayStoreCloseTimeInHours,
				saturdayStoreCloseTimeInMin };

		for (WebElement time : timeList) {
			util.isElementDisplyedAndValidate(time);
		}

	}

	public void openingAndClosingTimeCanNotBeEqual() {
		WebElement[] daysList = { monday, tuesday, wednesday, thursday, friday };

		for (WebElement days : daysList) {

			if (util.extractValueByAttributes(days, CLASSVAL).equals("Custom__Block--Button active")) {
				util.click(days);
			}
		}

		util.selectByValue(sundayStoreOpenTimeInHours, "01");
		util.selectByValue(sundayStoreOpenTimeInMin, "01");
		util.selectByValue(sundayStoreCloseTimeInHours, "01");
		util.selectByValue(sundayStoreCloseTimeInMin, "01");

		util.selectByValue(saturdayStoreOpenTimeInHours, "01");
		util.selectByValue(saturdayStoreOpenTimeInMin, "01");
		util.selectByValue(saturdayStoreCloseTimeInHours, "01");
		util.selectByValue(saturdayStoreCloseTimeInMin, "01");

		util.isElementDisplyedAndValidate(sundayStoreErrWhenClosingAndOpeningTimeIsSame);

		util.selectByValue(sundayStoreOpenTimeInHours, "01");
		util.selectByValue(sundayStoreOpenTimeInMin, "01");
		util.selectByValue(sundayStoreCloseTimeInHours, "01");
		util.selectByValue(sundayStoreCloseTimeInMin, "01");

		util.selectByValue(saturdayStoreOpenTimeInHours, "01");
		util.selectByValue(saturdayStoreOpenTimeInMin, "01");
		util.selectByValue(saturdayStoreCloseTimeInHours, "01");
		util.selectByValue(saturdayStoreCloseTimeInMin, "01");

		util.isElementDisplyedAndValidate(saturdayStoreErrWhenClosingAndOpeningTimeIsSame);

	}

	public void openingTimeShouldBeLessThanClosingTime() {
		util.selectByValue(sundayStoreOpenTimeInHours, "02");
		util.isElementDisplyedAndValidate(saturdayStoreErrWhenClosingAndOpeningTimeIsSame);

	}

	public void handlingTimeDropdown() {
		boolean isTrue = false;
		util.click(daysBtnHandlingTime);
		int size = handlingTimeSelectOption.size();

		if (size == 2) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);

		util.selectByValue(handlingTimeSelect, "01");
		util.validateSelectedOption(handlingTimeSelect, "01");

		util.selectByValue(handlingTimeSelect, "02");
		util.validateSelectedOption(handlingTimeSelect, "02");

		isTrue = false;
		util.click(hoursBtnHandlingTime);
		size = handlingTimeSelectOption.size();

		if (size == 24) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);

		util.selectByValue(handlingTimeSelect, "01");
		util.validateSelectedOption(handlingTimeSelect, "01");

		util.selectByValue(handlingTimeSelect, "23");
		util.validateSelectedOption(handlingTimeSelect, "23");

		isTrue = false;
		util.click(minutesBtnHandlingTime);
		size = handlingTimeSelectOption.size();

		if (size == 60) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);

		util.selectByValue(handlingTimeSelect, "01");
		util.validateSelectedOption(handlingTimeSelect, "01");

		util.selectByValue(handlingTimeSelect, "59");
		util.validateSelectedOption(handlingTimeSelect, "59");
	}

	public void storeStatusDropdown() {
		util.selectByValue(storeStatus, ACTIVE);
		util.validateSelectedOption(storeStatus, ACTIVE);
		util.selectByValue(storeStatus, INACTIVE);
		util.validateSelectedOption(storeStatus, INACTIVE);

	}

	public void fillAllMandatoryFields() {
		storeCode = String.valueOf(util.randomNum());
		storeName = "Store_" + storeCode;
		util.updateProperty("storeName", storeName);
		util.enterValue(storeNameInputField, storeName);
		util.enterValue(storeCodeInputField, storeCode);
		util.enterValue(streetAddressInputField, ADDRESSLINE1);
		enteredAddressLine1 = util.extractValueByAttributes(streetAddressInputField, VAL);
		util.extractValueByAttributes(streetAddressInputField2, VAL);
		util.extractValueByAttributes(streetAddressInputField3, VAL);
		util.enterValue(addCountryInputField, COUNTRY);
		util.click(indiaCountry);
		util.hold(5);
		util.click(addStateInputField);
		util.enterValue(addStateInputField, STATE);
		util.click(up);
		util.hold(5);
		util.click(addCityInputField);
		util.enterValue(addCityInputField, CITY);
		util.click(lko);
		selectedCity = util.extractValueByAttributes(addCityInputField, VAL);
		util.selectByValue(utcTimeZone, TIMEZONE);
		util.enterValue(addZipCode, ZIPCODE);
		enteredZipCode = util.extractValueByAttributes(addZipCode, VAL);
		util.enterValue(contactNumEle, CONTACTNUM);
		util.enterValue(emailId, util.getConfigProperty("user_email"));
		util.selectByValue(storeStatus, INACTIVE);
		selectedLocationStatus = util.getSelectedOptionInDropDown(storeStatus);
	}

	public void pressBackBtnWhileFillingFields() {
		util.click(backBtn);
		util.waitUntilElementIsVisible(discardStoreBtn, 5);
		util.click(discardStoreBtn);
		util.hold(2);
		util.isElementDisplyedAndValidate(storeNotCreatedMsg);
	}

	public void clickOnCreateStoreBtn() {
		util.click(saveAndCreateStoreBtn);
		util.hold(2);
		util.waitUntilElementIsVisible(storeCreatedSuccessfullyMsg, 15);
	}

	public void createNewStore() {
		fillAllMandatoryFields();
		clickOnCreateStoreBtn();
	}

	public void searchCreatedStore() {
		util.click(storesTabLocalSellingPage);
		util.enterValue(searchStoresInputFieldLocalSelling, util.getValueFromVariableProperties("storeName"));
		util.hold(5);
	}

	public void changeStoreStatus() {

		if (moreActionsBtn.getAttribute(ARIAEXPANDED).equals(FALSEVAL)) {
			util.click(moreActionsBtn);
			util.click(storeStatusBtnMoreAction);
			util.waitUntilElementIsVisible(statusUpdatedSuccessfullyMsg, 10);
		}

	}

	public void storeIsCreatedSuccessFully() {
		boolean isTrue = false;
		util.click(storesTabLocalSellingPage);
		util.hold(1);
		searchCreatedStore();

		do {
			if (doesStoreExist(storeName)) {
				isTrue = true;
			} else {
				util.click(nextPaginationBtn);
			}
		} while (util.extractValueByAttributes(nextPaginationBtn, ARIADISABLED) == null);

		Assert.assertTrue(isTrue);

	}

	private boolean doesStoreExist(String targetStoreName) {
		for (WebElement store : storeNameOnLocalSellingPage) {
			if (store.getText().equals(targetStoreName)) {
				return true;
			}
		}
		return false;
	}

	WebElement statusOfStore;
	WebElement locationOfStore;

	public void validateStatusAndLocation() {
		statusOfStore = util.getDriver().findElement(By.xpath(getStatusXPath(storeName)));
		locationOfStore = util.getDriver().findElement(By.xpath(getLocationXPath(storeName)));
		Assert.assertTrue(statusOfStore.getText().equalsIgnoreCase(selectedLocationStatus),
				"Status is not as expected");

		String completeAddress = enteredAddressLine1 + ", " + selectedCity + "- " + enteredZipCode;
		Assert.assertEquals(completeAddress, locationOfStore.getText(), "Location does not match the expected address");
	}

	private String getStatusXPath(String storeName) {
		return STATUSXPATH + storeName
				+ "']/ancestor::div[@class='Polaris-Grid']/div[2]//p[text()='Status:']/following-sibling::span/span[2]";
	}

	private String getLocationXPath(String storeName) {
		return STATUSXPATH + storeName + "']/ancestor::div[@class='Polaris-Grid']/div[2]/div/div[2]/div[2]/p[2]";
	}

	public void editStoreBtnIsWorking() {
		searchCreatedStore();
		util.hold(2);
		util.click(editStoreBtn);
		util.isElementDisplyedAndValidate(storeCodeInputField);

	}

	public void validateDisableFields() {
		boolean fieldIsDisabled = false;
		WebElement[] disablefields = { storeNameInputField, storeCodeInputField, streetAddressInputField,
				streetAddressInputField2, streetAddressInputField3, addCountryInputField, addStateInputField,
				addCityInputField, addZipCode };

		for (WebElement fields : disablefields) {
			fieldIsDisabled = false;
			if (!fields.isEnabled()) {
				fieldIsDisabled = true;
			}
		}
		Assert.assertTrue(fieldIsDisabled);
		util.click(backBtn);
	}

	public void clickOnActionBtn() {
		if (util.extractValueByAttributes(moreActionsBtn, ARIAEXPANDED).equals(FALSEVAL)) {
			util.click(moreActionsBtn);
		}
	}

	public void moreActionBtnIsWorking() {
		searchCreatedStore();

		statusOfStore = util.getDriver().findElement(By.xpath(getStatusXPath(storeName)));

		locationOfStore = util.getDriver().findElement(By.xpath(getLocationXPath(storeName)));

		clickOnActionBtn();

		if (statusOfStore.getText().equals(selectedLocationStatus)) {
			performStatusCheck(ACTIVE, "Activate Store");
		} else if (!statusOfStore.getText().equals(selectedLocationStatus)) {
			performStatusCheck(INACTIVE, "Inactivate Store");
		}
	}

	private void performStatusCheck(String expectedStatus, String expectedButtonLabel) {
		boolean isTrue = false;

		if (storeStatusBtnMoreAction.getText().equals(expectedButtonLabel)) {
			isTrue = true;
			Assert.assertTrue(isTrue);
			util.click(storeStatusBtnMoreAction);
			util.waitUntilElementIsVisible(statusUpdatedSuccessfullyMsg, 10);
			util.hold(2);
			isTrue = false;

			statusOfStore = util.getDriver().findElement(By.xpath(STATUSXPATH + storeName
					+ "']/ancestor::div[@class='Polaris-Grid']/div[2]//p[text()='Status:']/following-sibling::span/span[2]"));

			if (statusOfStore.getText().equals(expectedStatus)) {
				isTrue = true;
			}
			Assert.assertTrue(isTrue);
		}
	}

	public void clickOnDeleteStoreBtn() {
		clickOnActionBtn();
		util.click(deleteStoreBtnMoreAction);
	}

	public void onClickingDiscardBtn() {
		boolean modalIsNotDisplayed = false;
		clickOnDeleteStoreBtn();

		util.click(discardBtnDeleteStoreModal);
		util.hold(1);
		if (discardBtnDeleteStoreModalList.isEmpty()) {
			modalIsNotDisplayed = true;
		}

		Assert.assertTrue(modalIsNotDisplayed);
	}

	public void onClickingDeleteBtn() {
		clickOnDeleteStoreBtn();
		util.click(deleteBtnDeleteStoreModal);
		util.waitUntilElementIsVisible(storeDeleatedSuccessfullyMsg, 15);
		util.selectAll(searchStoresInputFieldLocalSelling);
		util.pressBackSpace();
		util.hold(5);
	}

	public void paginationBtnsAreWorking() {
		if (nextPaginationBtn.getAttribute(ARIADISABLED) == null) {
			String pageCountBeforeNext = StringUtils.substringBefore(totalPages.getText(), "/");
			util.click(nextPaginationBtn);
			String pageCountAfterNext = StringUtils.substringBefore(totalPages.getText(), "/");
			Assert.assertNotEquals(pageCountBeforeNext, pageCountAfterNext);

			util.click(prevPaginationBtn);
			String pageCountAfterPrev = StringUtils.substringBefore(totalPages.getText(), "/");
			Assert.assertNotEquals(pageCountAfterNext, pageCountAfterPrev);
		} else if (nextPaginationBtn.getAttribute(ARIADISABLED).equalsIgnoreCase(TRUEVAL)) {
			Assert.assertEquals(StringUtils.substringAfter(totalPages.getText(), "/").trim(), "1");
		}
	}

	public void clickOnOrdersTab() {
		util.click(ordersTabLocalSellingPage);
	}

	/**
	 * Local Selling Order Section test cases start from here
	 */

	public void verifyTabRedirectionIsProper() {
		WebElement[] tabsInOrder = { readyForPickupTabOrders, pendingTabOrders, pickedUpTabOrders, allTabOrders };
		String[] activeTabContents = { "Order status equals ReadyForPickup", "Order status equals Pending",
				"Order status equals PickedUp" };

		clickOnOrdersTab();

		for (int i = 0; i < tabsInOrder.length; i++) {
			util.click(tabsInOrder[i]);

			if (i == tabsInOrder.length - 1) {
				// If we're on the last tab, check if it's selected
				Assert.assertTrue(tabsInOrder[i].getAttribute(ARIASELECTED).equals("true"));
			} else {
				// For other tabs, check if the tab is selected and the status tag matches
				if (tabsInOrder[i].getAttribute(ARIASELECTED).equals("true")
						&& statusTagOrders.getText().equals(activeTabContents[i])) {
					Assert.assertTrue(true);
				} else {
					Assert.fail("Tab redirection is not proper for tab: " + activeTabContents[i]);
				}
			}
		}
	}

	public void openStores() {
		if (util.extractValueByAttributes(storeToggleButton, ARIAEXPANDED).equals(FALSEVAL)) {
			util.click(storeToggleButton);
			util.hold(1);
		}
	}

	public void getOrderByStoreName() {
		boolean isStoreInStatusTag = verifyOrdersStoreWise(1);
		Assert.assertTrue(isStoreInStatusTag);
	}

	private void clearBtnIsDisabled() {
		boolean btnIsDisabled = clearStoreBtn.getAttribute(ARIADISABLED).equals(TRUEVAL);
		Assert.assertTrue(btnIsDisabled, "clear button is disabled.");
	}

	private void clearBtnIsEnabled() {
		boolean btnIsEnabled = clearStoreBtn.getAttribute(ARIADISABLED) == null;
		Assert.assertTrue(btnIsEnabled, "clear button is enabled.");
	}

	private boolean verifyOrdersStoreWise(int index) {
		util.click(moreFiltersButton);
		util.hold(1);

		if (util.extractValueByAttributes(storeToggleButton, ARIAEXPANDED).equals(FALSEVAL)) {
			util.click(storeToggleButton);
			util.hold(1);
		}

		clearBtnIsDisabled();

		util.selectByIndex(selectFilterModal, index);
		util.hold(2);

		clearBtnIsEnabled();

		String selectedStore = util.getSelectedOptionInDropDown(selectFilterModal);
		util.click(doneBtnFilterModal);

		return (ordersList.isEmpty() || statusTagOrders.getText().contains(selectedStore));
	}

	private void archievedOrderSection() {
		util.click(archievedOrderBtn);
		util.enterValue(searchArchievedOrderInputField, "!@^&*(ASDFGHJN");
		util.hold(2);
		util.listIsNotEmpty(notFoundImg);
		util.click(searchArchievedOrderInputField);
		util.click(clearInputFieldBtnStore);

	}

	private void openViewOrdersPage() {
		if (!viewArchievedOrderBtns.isEmpty()) {
			util.waitUntilElementIsVisible(viewArchievedOrderBtns.get(0), 30);
			util.click(viewArchievedOrderBtns.get(0));
			util.click(shopifyTab);
			util.isElementDisplyedAndValidate(shopifyOrderDetails);
			util.click(amazonTab);
			util.isElementDisplyedAndValidate(amazonOrderDetails);
		}

	}

	private void validatePagination() {
		util.click(backBtn);
		util.isElementDisplyedAndValidate(searchArchievedOrderInputField);
		paginationBtnsAreWorking();
		util.click(backBtn);
	}

	public void openArchievedOrders() {
		archievedOrderSection();
		openViewOrdersPage();
		validatePagination();
	}

	private void clickOnImportModal() {
		util.click(importOrdersBtn);
		util.isElementDisplyedAndValidate(inputFieldAmazonOrderId);
	}

	private void enterOrderId(String orderId) {
		util.enterValue(inputFieldAmazonOrderId, orderId);
		util.click(confirmBtnModal);
	}

	private void searchWithoutEnteringOrder() {
		enterOrderId("");
		util.waitUntilElementIsVisible(inputFieldAmazonIsEmptyToastMsg, 30);
	}

	private void closeModalBtnIsWorking() {
		util.click(importOrdersBtn);
		util.click(crossBtn);
		util.listIsEmpty(importOrdersModal);
	}

	private void searchInvalidOrderToImport() {
		enterOrderId("ZZZZZZZZZZZ");
		util.waitUntilElementIsVisible(orderNotFoundToastMsg, 30);
	}

	public void importOrdersFunctionality() {
		clickOnImportModal();
		searchWithoutEnteringOrder();
		searchInvalidOrderToImport();
		closeModalBtnIsWorking();
	}

	public void searchOrders() {
		util.click(ordersTabLocalSellingPage);
		util.enterValue(searchOrdersInputFieldLocalSelling, orderId);
	}

	public void validateOrderIsNotAvailableOnGrid() {
		util.goToSection(OVERVIEWSECTION);
		String amazonOrderId = util.getValueFromVariableProperties("orderIdAmazon");
		util.click(ordersTabLocalSellingPage);
		util.enterValue(searchOrdersInputFieldLocalSelling, amazonOrderId);
		util.listIsNotEmpty(notFoundImg);

	}

	public void validateOrderIsCreatedSuccessfully() {
		util.goToSection(OVERVIEWSECTION);
		String amazonOrderId = util.getValueFromVariableProperties("orderIdAmazon");
		util.click(ordersTabLocalSellingPage);
		util.enterValue(searchOrdersInputFieldLocalSelling, amazonOrderId);
		util.waitUntilElementIsVisible(orderIdList.get(0), 30);

		Assert.assertEquals(amazonOrderId, orderIdList.get(0).getText().trim());
	}

	public void goToSettingsAndDisableLocalSelling() {
		util.goToSection(SETTINGSSECTION);
		disableLocalSelling();
	}

	public void goToSettingsAndEnableLocalSelling() {
		util.goToSection(SETTINGSSECTION);
		util.switchToIFrame();
		enableLocalSelling();
	}

	/**
	 * Enable BOPIS Order Syncing Setting as Fulfilled or Unfulfilled
	 * 
	 * @param status
	 */
	public void selectOrderStatus(String status) {
		goToSettingsAndEnableLocalSelling();

		if (status.equalsIgnoreCase("fulfilled") && (!setFulfilledOrderStatusRadioBtn.isSelected())) {
			util.click(setFulfilledOrderStatusRadioBtn);
			util.clickOnSaveBtn();
		} else if (status.equalsIgnoreCase("unfulfilled") && (!setUnfulfilledOrderStatusRadioBtn.isSelected())) {
			util.click(setUnfulfilledOrderStatusRadioBtn);
			util.clickOnSaveBtn();

		}
	}
}
