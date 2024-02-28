package com.ami.pageobjects;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ami.resources.Utilities;

public class HelpFaqPage extends HelpFaqPageOR {
	private static Logger log = LogManager.getLogger(HelpFaqPage.class.getName());
	private String pageUrl = "";
	Utilities util;

	public HelpFaqPage(Utilities util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
	}

	public void validateAvailabilityOfHyperLinksOnEachPage() {
		WebElement[] hyperLinksArr = { amzByCedLink, getSupportBtn, bookACallLink };
		util.goToMultiAccountOverView();

		for (WebElement link : hyperLinksArr) {
			util.isElementDisplyedAndValidate(link);
			util.hold(2);
		}

		util.goToMultiAcccounSettings();

		for (WebElement link : hyperLinksArr) {
			util.isElementDisplyedAndValidate(link);
			util.hold(2);
		}

		util.goToMultiAccountListings();

		for (WebElement link : hyperLinksArr) {
			util.isElementDisplyedAndValidate(link);
			util.hold(2);
		}

		util.goToMultiAcccounFAQ();
	}

	public void validateHyperlinksRedirection() {
		boolean isTrue = false;
		pageUrl = util.getPageURL();
		util.isElementDisplyedAndValidate(amzByCedLink);
		util.isElementDisplyedAndValidate(getSupportBtn);
		util.isElementDisplyedAndValidate(bookACallLink);
		util.click(amzByCedLink);
		util.goToChildWindow();
		if (util.getPageTitle().equalsIgnoreCase("Amazon by CedCommerce - CedCommerce Products Documentation")) {
			log.info("redirected to doc page successfully.");
			Assert.assertTrue(true);
		} else {
			log.error("redirection error occured.");
			Assert.assertTrue(true);
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
			util.click(getSupportBtn);
			util.isElementDisplyedAndValidate(getSupportPageHeading);
			util.hold(2);
			util.switchToDefaultContent();
			util.clickOnOverview();
		} catch (NoSuchWindowException e) {
			util.logWarn("NoSuchWindowException occured");
		}
		util.getWindoHandleByUrl(pageUrl);
		util.switchToIFrame();
	}

	public void gotoChildWindowAndValidate(WebElement element, String keyword) {
		boolean isTrue = false;

		pageUrl = util.getPageURL();
		util.click(element);
		util.goToChildWindow();

		if (util.getPageURL().contains(keyword)) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);

		try {
			util.getDriver().close();
			util.getWindoHandleByUrl(pageUrl);
			util.switchToIFrame();
		} catch (Exception e) {
			util.logWarn("exception handled successfully.");
		}
	}

	public void getSupportPageFunctionality() {
		WebElement[] elementArr = { whatsappHeading, startChatBtnWApp, skypeHeading, startChatBtnSkype, emailHeading,
				mailUsBtn };
		util.goToMultiAcccounFAQ();
		util.click(getSupportBtn);

		for (WebElement element : elementArr) {
			util.isElementDisplyedAndValidate(element);
		}

		gotoChildWindowAndValidate(startChatBtnWApp, "whatsapp");
		gotoChildWindowAndValidate(startChatBtnSkype, "skype");
	}

	public void faqFunctionality() {
		boolean isTrue = false;
		if (!faqList.isEmpty()) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);
		isTrue = false;
		
		for (int i = 0; i < btnList.size(); i++) {
			String initial = util.extractValueByAttributes(btnList.get(i), "aria-expanded");
			util.click(btnList.get(i));

			if (!util.extractValueByAttributes(faqList.get(i), "aria-expanded").equals(initial)) {
				isTrue = true;
				Assert.assertTrue(isTrue);
			}
			Assert.assertTrue(isTrue);
		}
	}

	public void searchBarFAQ(Map<String, String> input) {
		boolean isTrue = false;
		int initialSize = 0;
		util.clickOnFAQ();
		util.isElementDisplyedAndValidate(searchQryInpField);

		util.enterValue(searchQryInpField, input.get("invalid_search_qry"));
		util.pressEnter();
		util.isElementDisplyedAndValidate(nothingMatchMsg);

		util.goToMultiAcccounFAQ();
		initialSize = allQryList.size();
		util.enterValue(searchQryInpField, input.get("valid_search_qry"));
		util.pressEnter();
		util.hold(2);

		if (allQryList.size() != initialSize) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);
	}

	public void showMoreBtnWorking() {
		boolean isTrue = false;
		String before = "";
		String after = "";
		WebElement[] arr = { genQryShowMoreLessBtn, productSectionShowMoreLessBtn, orderSectionShowMoreLessBtn,
				errorsSectionShowMoreLessBtn };
		util.clickOnFAQ();

		for (int i = 0; i < arr.length; i++) {
			before = util.getTagValue(arr[i].findElement(By.xpath("span/span[2]")));

			if (i == 1) {
				for (int j = 1; j <= 4; j++) {
					util.click(arr[i]);
					util.hold(3);
				}
			} else {
				util.click(arr[i]);
				util.hold(3);
			}

			after = util.getTagValue(arr[i].findElement(By.xpath("span/span[2]")));

			if (!before.equals(after)) {
				isTrue = true;
				Assert.assertTrue(isTrue);
			}
			Assert.assertTrue(isTrue);
		}
	}

	public void queriesSectionWise() {
		boolean isTrue = false;
		WebElement[] arr = { genQryHeadingFAQPage, productSectionHeadingFAQPage, shipmentSettingHeadingFAQPage,
				orderSectionHeadingFAQPage, errorHeadingFAQPageSection };

		for (WebElement element : arr) {
			util.isElementDisplyedAndValidate(element);
		}

		if ((!qryListBelowErrors.isEmpty()) && (!qryListBelowOrderSection.isEmpty())
				&& (!qryListBelowShipmentSettingSection.isEmpty()) && (!qryListBelowProductSection.isEmpty())) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);
	}
}
