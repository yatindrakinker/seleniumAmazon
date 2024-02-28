package com.ami.pageobjects;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ami.pageobjects.reusableclasses.ReusableMethods;
import com.ami.resources.Utilities;

public class PricingPlanPage extends PricingPlanOR {

	Utilities util;
	String activePlanName;
	String ordersLimitInCurrentPlan;
	ReusableMethods reuse;

	public PricingPlanPage(Utilities util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
		reuse = new ReusableMethods(util);
	}

	public void clickOnViewAllPlan() {
		util.click(viewAllPlanBtn);
		util.waitUntilElementIsVisible(regularPlansBtn);
	}

	public void monthlyAnuallyPlansAreWorking() {
		boolean isTrue = false;

		WebElement[] plans = { regularPlansBtn, marketingPlansBtn };

		for (WebElement plan : plans) {

			if (util.extractValueByAttributes(plan, ARIASELECTED).equals(FALSEVAL)) {
				util.click(plan);
			}
			if (util.extractValueByAttributes(plan, ARIASELECTED).equals(TRUEVAL)) {
				isTrue = true;
			}
			Assert.assertTrue(isTrue);
			isTrue = false;
		}

	}

	public void monthlyAnuallyPlansContainsEightPlan() {
		boolean isTrue = false;

		WebElement[] plans = { regularPlansBtn, marketingPlansBtn };

		for (WebElement plan : plans) {
			util.click(plan);

			if (pricingPlansNameList.size() == 8) {
				isTrue = true;
			}
			Assert.assertTrue(isTrue);
			isTrue = false;
		}
	}

	public void eachMonthlyAnuallyPlansContainsViewAllFeatures() {

		WebElement[] plans = { regularPlansBtn, marketingPlansBtn };

		for (int k = 0; k < plans.length; k++) {
			util.click(plans[k]);

			for (int i = 0; i < viewAllFeaturesBtn.size(); i++) {

				if (i > 2) {
					util.click(nextPricingPlanBtn);
				}

				util.click(viewAllFeaturesBtn.get(i));
				util.isElementDisplyedAndValidate(allFeaturesModal);
				util.click(closeBtnModal);
			}
		}
	}

	public void getOneMonthFreePlan() {
		util.isElementDisplyedAndValidate(get1MonthFreeText);
	}

	public void mouseHoverAtPlan() {
		WebElement[] plans = { regularPlansBtn, marketingPlansBtn };

		for (WebElement plan : plans) {
			util.moveToElement(plan);
			util.validateCursorProperty(plan, "pointer");
		}
	}

	public void onClickPricingPlanCardsMoves() {
		WebElement[] plans = { regularPlansBtn, marketingPlansBtn };

		for (WebElement plan : plans) {
			util.click(plan);
			cardsMoveWhenClickAtForwardBackwardBtn();
		}
	}

	public void cardsMoveWhenClickAtForwardBackwardBtn() {
		util.waitUntilElementIsVisible(prevPricingPlanBtn);
		boolean isTrue = false;
		String classAtCardZeroBeforeClickingAtNextBtn = util.extractValueByAttributes(pricingPlanCards.get(0),
				CLASSVAL);
		util.click(nextPricingPlanBtn);
		String classAtCardZeroAfterClickingAtNextBtn = util.extractValueByAttributes(pricingPlanCards.get(0), CLASSVAL);

		if (!classAtCardZeroBeforeClickingAtNextBtn.equals(classAtCardZeroAfterClickingAtNextBtn)) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);

		String classAtCardZeroBeforeClickingAtPreviousBtn = util.extractValueByAttributes(pricingPlanCards.get(0),
				"class");
		util.click(prevPricingPlanBtn);
		String classAtCardZeroAfterClickingAtPreviousBtn = util.extractValueByAttributes(pricingPlanCards.get(0),
				"class");

		if (!classAtCardZeroBeforeClickingAtPreviousBtn.equals(classAtCardZeroAfterClickingAtPreviousBtn)) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);
	}

	public void customSolForHighVolMerchantsBannerIsDisplayed() {

		while (nextCorouselDisabledBtn.isEmpty()) {
			util.click(nextCorouselBtn);
		}

		util.isElementDisplyedAndValidate(customSolForHighVolMerchants);
		util.isElementDisplyedAndValidate(customSolForHighVolMerchants2);
	}

	public void currentPlanIsShownOnOverview() {
		boolean isTrue = false;

		if (!currentPlanBtnList.isEmpty()) {
			activePlanName = currentActivePlanName.getText().trim();
			activePlanName = StringUtils.substringBefore(activePlanName, ".00").trim();
			activePlanName = StringUtils.substringAfter(activePlanName, "(").trim();
			util.click(backBtn);

			String planNameAtOverview = StringUtils.substringBefore(planNameOverview.getText(), "/").trim();

			if (activePlanName.equals(planNameAtOverview)) {
				isTrue = true;
			}

			Assert.assertTrue(isTrue);
		}

	}

	public void progressBarIsDisplayed() {
		util.isElementDisplyedAndValidate(orderProcessed);
		util.isElementDisplyedAndValidate(orderProgressBar);
	}

	public void onClickViewAllPlans() {
		util.click(viewAllPlanBtn);
		util.isElementDisplyedAndValidate(pricingPlanPageHeading);

	}

	public void currentActivePlanIsAtFirstPosition() {
		WebElement[] plans = { regularPlansBtn, marketingPlansBtn };

		for (WebElement plan : plans) {
			util.click(plan);
			if (!currentPlanBtnList.isEmpty()) {
				util.isElementDisplyedAndValidate(currentPlanAtFirstPlace);
			}
		}
		util.click(backBtn);
	}

	public void upgradePlanSectionIsDisplayed() {
		util.isElementDisplyedAndValidate(anuallybtn);
		util.isElementDisplyedAndValidate(monthlyBtn);
	}

	public void sectionIsDisplayedAccordinglyMonthlyAnually() {
		boolean isTrue = false;
		util.click(anuallybtn);
		util.isElementDisplyedAndValidate(getOneMonthFreeOnSelectingAnualPlan);
		util.click(monthlyBtn);

		if (util.extractValueByAttributes(monthlyBtn, ARIAPRESSED).equals(TRUEVAL)) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);
	}

	public void onClickUpgradeNowBtn() {
		util.click(upgradeNowBtn);
		util.switchToDefaultContent();
		util.waitUntilElementIsVisible(approveSubscriptionHeading, 30);
		util.isElementDisplyedAndValidate(approveSubscriptionHeading);
		util.isElementDisplyedAndValidate(approveBtn);
		util.getDriver().navigate().back();
		util.switchToIFrame();
		util.waitUntilElementIsVisible(monthlyBtn, 30);
	}

	public void enableOrderSettings() {
		util.click(orderSettings);
		if (util.extractValueByAttributes(enableOrderSettingsBtn, ARIAPRESSED).equals(FALSEVAL)) {
			util.click(enableOrderSettingsBtn);
			util.switchToDefaultContent();
			util.click(saveBtn);
			util.switchToIFrame();
		}

	}

	public void enableCreateOrdersForNonExistingProductsToggleBtn() {
		util.click(orderSettings);
		if (util.extractValueByAttributes(createOrdersForNonExistingProductsToggleBtn, "aria-checked")
				.equals(FALSEVAL)) {
			util.click(createOrdersForNonExistingProductsToggleBtn);
			util.switchToDefaultContent();
			util.click(saveBtn);
			util.switchToIFrame();
		}

	}

	public void checkProgressBarWhenFilled50Percent() {
		util.openSectionsInNewTab("settings");
		enableOrderSettings();
		enableCreateOrdersForNonExistingProductsToggleBtn();
		util.goToSection("overview");
	}

	public void refreshOverview() {
		util.refreshPage();
		util.switchToDefaultContent();
		util.switchToIFrame();
		util.waitUntilElementIsVisible(subscriptionSectionHeading, 30);
	}

	public void ordersBarIsFilled(double expextedPercent) {
		boolean isTrue = false;
		util.hold(5);
		Double percentage = Double.parseDouble(util.extractValueByAttributes(orderProgressBar, VAL));

		if (percentage >= expextedPercent) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);
	}

	public void loginEmail(String sub) {
		reuse.loginToMail(sub);
	}

	public void emailForOrderLimitIsReceived(String subject) {
		String msg = reuse.searchEMail(subject);
		Assert.assertEquals(msg, subject);
	}
	
	private void overview() {
		util.goToSection(OVERVIEW);
		util.refreshPage();
		util.switchToIFrame();
		util.waitUntilElementIsVisible(overViewHeading, 30);
		util.hold(5);
	}

	public void excessUsageChargesIsDisplayed() {
		overview();
		util.isElementDisplyedAndValidate(excessUsageChargesCard);
		util.click(payExcessChargesBtn);
		util.waitUntilElementIsVisible(approveChargePageHeading, 30);
		util.clickOnOverview();
		util.click(contactUsBtnExcessChargesCard);
		util.waitUntilElementIsVisible(modeOfComm);
		overview();
		util.click(faqBtnExcessChargesCard);
		util.isElementDisplyedAndValidate(faqSearchInputField);
		overview();
	}
	
	public void excessUsageChargeIsDisplayed() {
		util.isElementDisplyedAndValidate(excessUsageChargesDetails);
		util.click(payExcessUsageChargesDetails);
		util.waitUntilElementIsVisible(approveChargePageHeading, 30);
		util.getDriver().navigate().back();
		util.refreshPage();
		util.switchToIFrame();
	}
	
	public void payExcessCharges() {
		util.click(payExcessChargesBtn);
		util.waitUntilElementIsVisible(approveChargeBtn);
		util.click(approveChargeBtn);
		util.waitUntilElementIsVisible(excessChargesPaidSuccessfullyToastMsg, 30);
	}
	
	/**
	 * When excess order limit is exhausted on clicking every section pricing plan page
	 * is displayed.
	 */
	public void pricingPlanPageIsDisplayedOnEverySection() {
		WebElement[] sectionArr = { listingsApp, productLinkingApp, settingsApp, faqApp, overviewApp };
		WebElement[] sectionArrStaging = { listingsApp, settingsApp, faqApp, overviewApp };

		if (util.getConfigProperty(STORE).equals(STAGING)) {
			for (WebElement section : sectionArrStaging) {
				util.switchToDefaultContent();
				util.click(section);
				util.switchToIFrame();
				util.waitUntilElementIsVisible(pricingPlanPageHeading, 30);
				util.isElementDisplyedAndValidate(pricingPlanPageHeading);
			}

		} else if (util.getConfigProperty(STORE).equals(LIVE)) {
			for (WebElement section : sectionArr) {
				util.switchToDefaultContent();
				util.click(section);
				util.switchToIFrame();
				util.waitUntilElementIsVisible(pricingPlanPageHeading, 30);
				util.isElementDisplyedAndValidate(pricingPlanPageHeading);
			}

		}

	}
	
	public void excessOrdersText() {
		util.switchToIFrame();
		String text = ordersText.getText();
		text = StringUtils.substringBefore(text, "orders (").trim();
		
		Assert.assertNotEquals("", text);
	}
	
	public void choseSubscriptionPlanWhenExcessPaymentIsPending() {
		util.switchToIFrame();
		for(WebElement btn : buyNowBtn) {
			if(btn.getAttribute(ARIADISABLED).equals(FALSEVAL)) {
				btn.click();
				util.listIsNotEmpty(modalForPayExcessChargesList);
				break;
			}
		}
	}
	
	public void linksAreWorkingInAttentionModal() {
		util.click(contactUsBtnAttentionModal);
		util.waitUntilElementIsVisible(modeOfComm);
		util.goToSection(OVERVIEW);
		util.refreshPage();
		util.switchToIFrame();
		util.waitUntilElementIsVisible(regularPlansTab, 30);
		choseSubscriptionPlanWhenExcessPaymentIsPending();
		util.click(faqBtnAttentionModal);
		util.isElementDisplyedAndValidate(faqSearchInputField);
		util.goToSection(OVERVIEW);
		util.refreshPage();
		util.switchToIFrame();
		util.waitUntilElementIsVisible(regularPlansTab, 30);
		
	}
	
	public void payBtnWorkingInAttentionModal() {
		linksAreWorkingInAttentionModal();
		choseSubscriptionPlanWhenExcessPaymentIsPending();
		util.click(payExcessChargesBtnAttentionModal);
		util.waitUntilElementIsVisible(approveChargeBtn, 30);
		util.click(approveChargeBtn);
//		util.waitUntilElementIsVisible(excessChargesPaidSuccessfullyToastMsg, 30);
		
	}
	
	public void waitForPricingPlanOnEachSection() {
		util.hold(60);
		util.refreshPage();
		util.switchToIFrame();
		util.waitUntilElementIsVisible(regularPlansTab, 60);
	}
	
	public String getPlanPrice() {
		for(int i = 0; i < buyNowBtn.size(); i++) {
			if(buyNowBtn.get(i).getAttribute(ARIADISABLED).equals(FALSEVAL)) {
				return pricingPlanPriceList.get(i).getText();
			}
		}
		return "";
	}
	
	
	public void choseSubscriptionPlan() {
		util.switchToIFrame();
		for(WebElement btn : buyNowBtn) {
			if(btn.getAttribute(ARIADISABLED).equals(FALSEVAL)) {
				btn.click();
				break;
			}
		}
	}
	
	public void selectPricingPlan() {
		String price = getPlanPrice();
		choseSubscriptionPlan();
		util.click(confirmAndProceedBtn);
		util.switchToDefaultContent();
		util.waitUntilElementIsVisible(approveChargeBtn, 30);
		util.click(approveChargeBtn);
		util.switchToIFrame();
		util.waitUntilElementIsVisible(subscriptionHeadingOverview, 60);
		String priceDetailsOverview = currentPlanDetailsOverview.getText();
		priceDetailsOverview = StringUtils.substringBefore(priceDetailsOverview, "/").trim();
		Assert.assertEquals(price, priceDetailsOverview);
		
	}

}
