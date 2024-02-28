package com.AMI.TestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ami.pageobjects.OverviewPage;
import com.ami.resources.BaseClass;

public class OverViewTest extends BaseClass {
	public static String orderId;

	@DataProvider
	public Object[][] getOverViewData() throws IOException {
		List<HashMap<String, String>> data = util.getJsonDataToHashmap("OverView");
		return new Object[][] { { data.get(0) } };
	}

	@Test(priority = 1)
	public void TC_AM_OV_001() {
		OverviewPage op = new OverviewPage(util);
		op.checkRedirectionToOverView();
	}

	@Test(priority = 2)
	public void TC_AM_OV_031_32_33() {
		OverviewPage op = new OverviewPage(util);
		op.validateHyperlinksAreVisible();
	}

	@Test(priority = 2)
	public void TC_AM_OV_008() {
		OverviewPage op = new OverviewPage(util);
		op.sellerAcountIsVisible();
	}

	@Test(priority = 3)
	public void TC_AM_OV_009_10() {
		OverviewPage op = new OverviewPage(util);
		op.sellerAcntDropDownAndFlasIsVisible();
	}

	@Test(priority = 4)
	public void TC_AM_OV_011() {
		OverviewPage op = new OverviewPage(util);
		op.validateAddAcntButtonIsVisible();
	}

	@Test(priority = 5)
	public void TC_AM_OV_012() {
		OverviewPage op = new OverviewPage(util);
		op.validateAccountConnectionRedirection();
	}

	@Test(priority = 6)
	public void TC_AM_OV_014_15_16() {
		OverviewPage op = new OverviewPage(util);
		op.validateBackBtnIsVisible();
	}

	@Test(priority = 7)
	public void TC_AM_OV_017() {
		OverviewPage op = new OverviewPage(util);
		op.validateCountrySelectionIsMandatory();
	}

	@Test(priority = 8)
	public void TC_AM_OV_018_19() {
		OverviewPage op = new OverviewPage(util);
		op.validateDropDownIsVisible();
	}

	@Test(priority = 9, dataProvider = "getOverViewData")
	public void TC_AM_OV_020(HashMap<String, String> input) {
		OverviewPage op = new OverviewPage(util);
		op.validateSelectedCountryIsVisibleInSearchField(input);
	}

	@Test(priority = 10, dataProvider = "getOverViewData")
	public void TC_AM_OV_021_22(HashMap<String, String> input) {
		OverviewPage op = new OverviewPage(util);
		op.validateCountryNameCanBeEnteredManually(input);
	}

	@Test(priority = 11, dataProvider = "getOverViewData")
	public void TC_AM_OV_023(HashMap<String, String> input) {
		OverviewPage op = new OverviewPage(util);
		op.validateIfSearchedCountryContainsNumAndSpecialChars(input);
	}

//	new functionality added must agree on terms and conditions (checkbox click functionality)
	@Test(priority = 12, dataProvider = "getOverViewData")
	public void TC_AM_OV_024_25_26(HashMap<String, String> input) {
		OverviewPage op = new OverviewPage(util);
		op.validateConnectSellerAcntButtonIsVisibleOnAccountConnectionSection(input);
	}

	@Test(priority = 13)
	public void TC_AM_OV_036To40() {
		OverviewPage op = new OverviewPage(util);
		op.validateConnectionErrorMsgIsDisplayed();
	}

	@Test(priority = 14)
	public void TC_AM_OV_041To45() {
		OverviewPage op = new OverviewPage(util);
		op.validateFooterContainsLink();
	}
/*
 * 
 * price functionality updated need to update cases
	@Test(priority = 96)
	public void TC_AM_OV_096To100() {
		OverviewPage op = new OverviewPage(util);
		op.mandatoryFieldSubscriptionPlanVisibilityValidation();
		op.verifyCursorChangeToHandWhileHoverToChangePlanButton();
	}

	@Test(priority = 108)
	public void TC_AM_OV_108() {
		OverviewPage op = new OverviewPage(util);
		op.clickOnChangePricingPlanButton();
	}
*/
	@Test(priority = 109)
	public void TC_AM_OV_140() {
		OverviewPage op = new OverviewPage(util);
		op.allSubscriptionPlanAreVisible();
	}

	@Test(priority = 110)
	public void TC_AM_OV_142_143() {
		OverviewPage op = new OverviewPage(util);
		op.validateContactUsWorking();
	}

	@Test(priority = 111)
	public void TC_AM_OV_148To156() {
		OverviewPage op = new OverviewPage(util);
		op.validateActivitiesIsShown();
	}

	@Test(priority = 112)
	public void TC_AM_OV_158_61_65_69() {
		OverviewPage op = new OverviewPage(util);
		op.validateSwitcherIsDisplayed();
	}

	@Test(priority = 113)
	public void TC_AM_OV_170_71_73_76_78() {
		OverviewPage op = new OverviewPage(util);
		op.validateProductStatusOverview();
	}

	@Test(priority = 114, dataProvider = "getOverViewData")
	public void TC_AM_OV_183_84_87_88_89_93(HashMap<String, String> input) {
		OverviewPage op = new OverviewPage(util);
		op.validateFailedOrdersOverview(input);
	}

	@Test(priority = 115)
	public void TC_AM_OV_194_95_96_97() {
		OverviewPage op = new OverviewPage(util);
		op.validateArchievedOrderSection();
		op.validatePagination2();
	}

	@Test(priority = 116)
	public void TC_AM_OV_240_41_44_49() {
		OverviewPage op = new OverviewPage(util);
		op.validateAppGuideSection();
	}
/*
	@Test(priority = 117, dataProvider = "getOrderData")
	public void runvalidateFailedOrderFunctionalityOverview(HashMap<String, String> input) {
		OverviewPage op = new OverviewPage(util);
		JsonBody.amazonOrderId = "";
		FailedRefundOrdersPage fop = new FailedRefundOrdersPage(util);
		CreateOrder createOrder = new CreateOrder();
		orderId = createOrder.createOrdersForNonExistinProducts(input);
		util.hold(10);
		util.getShopifyOrder(orderId);
		fop.refundWithoutReason(input, JsonBody.amazonOrderId);
		op.validateFailedOrderFunctionalityOverview();
		op.viewCreateBtnFunctionality();
	}
*/
//	@Test(priority = 110)
//	public void TC_AM_OV_110() {
//		OverviewPage op = new OverviewPage(util);
//		op.changePricingPlan();
//	}

}
