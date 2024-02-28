package com.ami.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ami.resources.Utilities;

public class ActivitiesOverViewPage extends OverViewPageOR {
	private Utilities util;

	public ActivitiesOverViewPage(Utilities util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
	}

	/**
	 * This method validates that in overview section activities section is
	 * available.TC_AM_OV_148,
	 * TC_AM_OV_149,TC_AM_OV_150,TC_AM_OV_153,TC_AM_OV_154,TC_AM_OV_155,TC_AM_OV_156
	 */
	public void validateActivitiesIsShown() {
//		util.goToMultiAccountOverView();

		util.switchToIFrame();
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
		if(util.extractValueByAttributes(sellerAccountButton, "aria-expanded").equals("false")) {
			
			util.click(sellerAccountButton);
		}
		util.isElementDisplyedAndValidate(sellerAccountsList.get(0));
		util.hold(2);
		
		for(WebElement acnt : sellerAccountsList) {
			if(acnt.getText().equals(util.getConfigProperty("acntNameLive"))) {
				isTrue = true;
				util.click(acnt);
				util.hold(2);
				
			}
		}
		
		Assert.assertTrue(isTrue);
		util.isElementDisplyedAndValidate(activitiesHeading);

		for (WebElement msgActivity : msgActivities) {
			util.isElementDisplyedAndValidate(msgActivity);
		}

		for (WebElement dateTimeActivitiy : dateTimeActivities) {

			if (util.getTagValue(dateTimeActivitiy).contains("GMT")) {
				isTrue = true;
			}
			Assert.assertTrue(isTrue);
		}
		validateHyperlinksAreVisible();
		util.goToMultiAccountOverView();
	}

	public void validateHyperlinksAreVisible() {
		String pageUrl = "";
		boolean isTrue = false;

		pageUrl = util.getPageURL();
		util.isElementDisplyedAndValidate(amazonByCedLink);
		util.isElementDisplyedAndValidate(getSupportLink);
		util.isElementDisplyedAndValidate(bookACallLink);
		util.click(amazonByCedLink);
		util.goToChildWindow();

		if (util.getPageTitle()
				.equalsIgnoreCase("Multi-account Amazon by CedCommerce - CedCommerce Products Documentation")) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);

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
			e.getMessage();
		}

	}
}
