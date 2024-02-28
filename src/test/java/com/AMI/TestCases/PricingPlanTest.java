package com.AMI.TestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ami.pageobjects.OnboardingPage;
import com.ami.pageobjects.PricingPlanPage;
import com.ami.pageobjects.reusableclasses.ReusableMethods;
import com.ami.resources.BaseClass;

import api.CreateOrder;
import apiBodyPayload.JsonBody;

public class PricingPlanTest extends BaseClass {

	PricingPlanPage pricingPlan;
	OnboardingPage onboarding;
	CreateOrder createOrder;
	ReusableMethods reuse;
	String userName = util.getConfigProperty("user_email");
	String password = util.getConfigProperty("email_pass");

	private String from = "Cedcommerce <no-reply@cedcommerce.com>";
	private String fiftyPercentEmail = "You have used 50% of order credits!";
	private String ninetyPercentEmail = "You have used 90% of order credits!";
	private String hundredPercentEmail = "Critical: 100% Order Credit Exhausted!";
	private String planRevoked = "Important Update: Panel Access is Revoked due to plan expiry - Amazon By Cedcommerce";
	private String paidExcessCharges = "Excess Usage Charge Paid Successfully! - Amazon By Cedcommerce ";

	@DataProvider
	public Object[][] getOrderData() throws IOException {
		List<HashMap<String, String>> data = util.getJsonDataToHashmap("OrderDetails");
		return new Object[][] { { data.get(0) } };
	}

	@Test(priority = 1)
	public void testGoToPricingPlan() {
		pricingPlan = new PricingPlanPage(util);
		onboarding = new OnboardingPage(util);
		reuse = new ReusableMethods(util);
		createOrder = new CreateOrder();
		pricingPlan.clickOnViewAllPlan();
//		createOrder.createExcessCharge("0", "10", "0");
		
//		for free plan
//		createOrder.createPrepaidOrders("50", "50", "0");
		
//		for 100 orders plan
//		createOrder.createPrepaidOrders("100", "100", "0");
	}

	@Test(priority = 2)
	public void testPricingPlanBtnIsWorking() {

		pricingPlan.monthlyAnuallyPlansAreWorking();
	}


	@Test(priority = 4)
	public void testEachMonthlyAnuallyPlansContainsViewAllFeatures() {
		pricingPlan.eachMonthlyAnuallyPlansContainsViewAllFeatures();
	}

	@Test(priority = 5)
	public void testGetOneMonthFreePlanBannerIsDisplayed() {
		pricingPlan.getOneMonthFreePlan();
	}

	@Test(priority = 6)
	public void testMouseHoverAtPlan() {
		pricingPlan.mouseHoverAtPlan();
	}

	@Test(priority = 7)
	public void testOnClickPricingPlanCardsMoves() {
		pricingPlan.onClickPricingPlanCardsMoves();
	}

	@Test(priority = 8)
	public void testCustomSolForHighVolMerchantsBannerIsDisplayed() {
		pricingPlan.customSolForHighVolMerchantsBannerIsDisplayed();
	}

	@Test(priority = 9)
	public void testContactUsBtnIsWorking() {
		onboarding.contactUsBtnWorking();
	}

	@Test(priority = 10)
	public void testFAQsAreDisplayed() {
		onboarding.faqsAreDisplayed();
	}

	@Test(priority = 11)
	public void testViewAllQuestionsBtnIsWorking() {
		onboarding.viewAllQuestionsBtnIsWorking();
	}

	@Test(priority = 12)
	public void testCurrentPlanIsShownOnOverview() {
		pricingPlan.currentPlanIsShownOnOverview();
	}

	@Test(priority = 13)
	public void testProgressBarIsDisplayed() {
		pricingPlan.progressBarIsDisplayed();
	}

	@Test(priority = 14)
	public void testOnClickViewAllPlans() {
		pricingPlan.onClickViewAllPlans();
	}

//	removed	

//	@Test(priority = 15)
//	public void testCurrentActivePlanIsAtFirstPosition() {
//		pricingPlan.currentActivePlanIsAtFirstPosition();
//	}

	@Test(priority = 18, dataProvider = "getOrderData")
	public void testCheckProgressBarWhenFilled50Percent(HashMap<String, String> input) {

		util.switchToIFrame();
		pricingPlan.checkProgressBarWhenFilled50Percent();
		createOrder = new CreateOrder();
//		for free plan
//		createOrder.createPrepaidOrders("50", "26", "24");
		
		for (int i = 1; i < 26; i++) {
			JsonBody.amazonOrderId = "";
			createOrder.createOrdersForNonExistinProducts(input);
		}

		pricingPlan.refreshOverview();
		pricingPlan.ordersBarIsFilled(50.0);

	}

	@Test(priority = 19)
	public void testEmailFor50PercentIsRecieved() {
		
		util.hold(10);
		Map<String, String> email = util.readMail(userName, password);
		String subject = email.get("mailSubject");
		
		boolean isTrue = false;
		
		if(subject.contains(fiftyPercentEmail.trim())) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);

	}

	@Test(priority = 19, dataProvider = "getOrderData")
	public void testCheckProgressBarWhenFilled70Percent(HashMap<String, String> input) {
//		for free plan
//		createOrder.createPrepaidOrders("50", "16", "34");

		for (int i = 1; i < 11; i++) {
			JsonBody.amazonOrderId = "";
			createOrder.createOrdersForNonExistinProducts(input);
		}

		pricingPlan.refreshOverview();
		pricingPlan.ordersBarIsFilled(70.0);

	}

	@Test(priority = 20, dataProvider = "getOrderData")
	public void testCheckProgressBarWhenFilled90Percent(HashMap<String, String> input) {
//		for free plan
//		createOrder.createPrepaidOrders("50", "44", "6");

		for (int i = 1; i < 11; i++) {
			JsonBody.amazonOrderId = "";
			createOrder.createOrdersForNonExistinProducts(input);
		}

		pricingPlan.refreshOverview();
		pricingPlan.ordersBarIsFilled(90.0);

	}

	@Test(priority = 21)
	public void testEmailFor90PercentIsRecieved() {

//		pricingPlan.emailForOrderLimitIsReceived("90% Of Your App Credits Have Been Used Up! - Amazon By Cedcommerce");
		util.hold(10);
		Map<String, String> email = util.readMail(userName, password);
		String subject = email.get("mailSubject");
		
		boolean isTrue = false;
		
		if(subject.contains(ninetyPercentEmail.trim())) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);
		reuse.recommendedPlanIsVisible();
	}

	@Test(priority = 22, dataProvider = "getOrderData")
	public void testCheckProgressBarWhenFilled100Percent(HashMap<String, String> input) {
		
//		for free plan
//		createOrder.createPrepaidOrders("50", "1", "49");

		for (int i = 1; i < 6; i++) {
			JsonBody.amazonOrderId = "";
			createOrder.createOrdersForNonExistinProducts(input);
		}

		pricingPlan.refreshOverview();
		reuse.closeModalIfDisplayed();
		pricingPlan.ordersBarIsFilled(100.0);

	}

	@Test(priority = 23)
	public void testEmailFor100PercentIsRecieved() {
//		pricingPlan.emailForOrderLimitIsReceived("Critical: 100% Order Credit Exhausted! - Amazon By Cedcommerce");
		util.hold(10);
		Map<String, String> email = util.readMail(userName, password);
		String subject = email.get("mailSubject");
		boolean isTrue = false;
		
		if(subject.contains(hundredPercentEmail.trim())) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);
	}

	@Test(priority = 24, description = "483-493", dataProvider = "getOrderData")
	public void testExcessChargeCard(HashMap<String, String> input) {
		
		for (int i = 0; i < 2; i++) {
			JsonBody.amazonOrderId = "";
			createOrder.createOrdersForNonExistinProducts(input);
		}
		util.refreshPage();
		util.switchToIFrame();

		pricingPlan.excessUsageChargesIsDisplayed();
	}

	/**
	 * These 2 cases can be tested only for few conditions.
	 */
//	@Test(priority = 25)
//	public void testUpgradePlanSectionIsDisplayed() {
//		pricingPlan.upgradePlanSectionIsDisplayed();
//		pricingPlan.sectionIsDisplayedAccordinglyMonthlyAnually();
//	}
//
//	@Test(priority = 26)
//	public void testOnClickUpgradeNowBtn() {
//		pricingPlan.onClickUpgradeNowBtn();
//	}

	
	
	@Test(priority = 27, description = "483-493", dataProvider = "getOrderData")
	public void testWhenExcessOrderLimitIsExhausted(HashMap<String, String> input) {
		
		for (int i = 1; i < 9; i++) {
			JsonBody.amazonOrderId = "";
			createOrder.createOrdersForNonExistinProducts(input);
		}
		
//		createOrder.createExcessCharge("10", "0", "0");
		
		
	}

	@Test(priority = 28, description = "483-493")
	public void testIfPlanExhaustOnEachSectionPricingPlanIsDisplayed() {
		pricingPlan.waitForPricingPlanOnEachSection();
		pricingPlan.pricingPlanPageIsDisplayedOnEverySection();
	}
	
	@Test(priority = 29, description = "483-493")
	public void testExcessOrdersText() {
		pricingPlan.excessOrdersText();
	}
	
	@Test(priority = 29, description = "483-493")
	public void testUpgradeSubscriptionPlanBeforePayingExtraCharges() {
		pricingPlan.choseSubscriptionPlanWhenExcessPaymentIsPending();
	}
	
	@Test(priority = 35, description = "483-493")
	public void testPayExcessCharges() {
		pricingPlan.payBtnWorkingInAttentionModal();
		util.hold(10);
	}
	
	@Test(priority = 36, description = "email is recieved when excess charges are paid.")
	public void testEmailReceivedForExcessPaymentIsPaid() {
		Map<String, String> email = util.readMail(userName, password);
		String subject = email.get("mailSubject");
		Assert.assertEquals(paidExcessCharges.trim(), subject.trim());
	}
	
	@Test(priority = 36, description = "purchase new plan")
	public void testSelectPricingPlan() {
		util.switchToIFrame();
		pricingPlan.selectPricingPlan();
//		
	}
	
	@Test(priority = 37, description = "purchase new plan")
	public void testEmailReceivedForPricingPlan() {
		Map<String, String> email = util.readMail(userName, password);
		String subject = email.get("mailSubject");

		Assert.assertEquals("Payment Done Successfully! - Amazon By Cedcommerce", subject.trim());
//		Payment Done Successfully! - Amazon By Cedcommerce
	}
	
}
