package com.AMI.TestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ami.pageobjects.CreateShopifyProduct;
import com.ami.pageobjects.FailedOrdersOverViewPage;
import com.ami.pageobjects.OrderPage;
import com.ami.pageobjects.reusableclasses.ReusableMethods;
import com.ami.resources.BaseClass;

import api.API;
import api.CreateOrder;
import apiBodyPayload.JsonBody;
import io.restassured.path.json.JsonPath;
import webhooksAMI.WebHookPage;

public class OrderTest extends BaseClass {
	private final String ITEMTAX = "$12.29";
	private final String SHIPPINGTAX = "$10.00";
	private final String DEFAULTSRCIDENTIFIER = "Amazon";
	private final String CUSTOMSRCIDENTIFIER = "yatindra";
	private final String TAG1 = "yatindra";
	private final String TAG2 = "kinker";

	public static String orderId;
	private static final String SKUNUM = "sku2";
//	18058680342298042385580325
	private String sku = util.getProperty("sku", SKUNUM);
	private static final String SKUFILENAME = "sku";
	private String nonExistingSKU = "sku_not_available";

	private static final String LINKING = "linking";
	private static final String LISTING = "listings";
	private static final String SETTINGS = "settings";

	CreateOrder createOrder;
	CreateShopifyProduct create;
	OrderPage op;
	API api;
	WebHookPage web;
	ReusableMethods reuse;
	FailedOrdersOverViewPage fop;

	@DataProvider
	public Object[][] getOrderData() throws IOException {
		List<HashMap<String, String>> data = util.getJsonDataToHashmap("OrderDetails");
		return new Object[][] { { data.get(0) } };
	}

	@Test(priority = 1)
	public void initialize() {
		op = new OrderPage(util);
		createOrder = new CreateOrder();
		api = new API(util);
		create = new CreateShopifyProduct(util);
		web = new WebHookPage(util);
		reuse = new ReusableMethods(util);
		fop = new FailedOrdersOverViewPage(util);
		util.openSectionsInNewTab(LISTING);
	}

	@Test(priority = 1, description = "Verify that user is able to click on order settings")
	public void testRedirectionToSettings() {
		op.redirectionToSettings();
	}

	@Test(priority = 2, description = "Verify that order settings toggle button is working. ")
	public void testOrderSettingToggleBtnIsWorking() {
		op.orderSettingToggleBtnIsWorking();
	}

	@Test(priority = 3, description = "Verify that toggle buttons are working. ")
	public void testToggleBtnsOnOrderSettingIsWorking() {

		op.toggleBtnsOnOrderSettingIsWorking();
	}

	@Test(priority = 4, description = "Check that  Shopify order source identifier is alredy eneable")
	public void testOrderSourceIdentifierIsCheckedAndDisabled() {

		op.verifyOrderSourceIdentifierIsCheckedAndDisabled();
	}

	@Test(priority = 5, description = "Check that  if Shopify order source identifier textfield is blank and click on save")
	public void testorderSourceIdentifierInputFieldIsLeftBlank() {

		op.orderSourceIdentifierInputFieldIsBlank();
	}

	@Test(priority = 6, description = "Verify that user is able to Create order for non existing product, Src Identifier(default) ")
	public void testOrderIsCreatedSuccessfullyForNonExistingProduct() {
//		go to settings and enable settings
		util.goToSection(SETTINGS);
		String settingsPageUrl = util.getDriver().getCurrentUrl();
		op.enableCreateOrderForNonExistingProductsTogglebutton();
		op.updateSrcIdentifierVal(DEFAULTSRCIDENTIFIER);

//		create order
		String response = api.createOrders(nonExistingSKU, "1");
		JsonPath jsPath = new JsonPath(response);
		String shopifyOrderId = jsPath.getString("data.order_id");
		shopifyOrderId = StringUtils.substringAfter(shopifyOrderId, "[");
		shopifyOrderId = StringUtils.substringBefore(shopifyOrderId, "]");
		System.out.println(shopifyOrderId);
		util.hold(10);
//		validate order is available on shopify
		util.openOrdersInNewTab();
		util.getShopifyOrder(shopifyOrderId);
		op.validateOrderIsCreated();
		op.validateSrcIdentifierVal(DEFAULTSRCIDENTIFIER);
		util.gotoWindowByClosingCurrentOne(settingsPageUrl);
	}

	@Test(priority = 7, description = "Verify that user tries to Create order for sku which does not exist in shopify and "
			+ "Create order for non existing product settings is disabled on App.")
	public void testOrderIsCreatedSuccessfullyForExistingProduct() {
//		go to settings and disable settings
		util.goToSection(SETTINGS);
		op.disableCreateOrderForNonExistingProductsTogglebutton();

//		create order
		String response = api.createOrders(CreateShopifyProduct.skuOfProudct, "1");
		JsonPath jsPath = new JsonPath(response);

		if (jsPath.getString("success").equalsIgnoreCase("false")) {
			util.goToSection("overview");
			op.searchFailedOrders(API.amazonOrderId);
		}

	}

	@Test(priority = 8, description = "enable => Tax, Shipping Tax, Src Identifier(custom), Add Tags, Modify Shopify order name")
	public void testTaxIsAddedWhenAmazonItemTaxOnShopifySettingIsOn() {
		util.switchToIFrame();
//		go to settings and disable settings
		util.goToSection(SETTINGS);
		String settingsPageUrl = util.getDriver().getCurrentUrl();
		
		op.enableDisableAmazonItemTaxOnShopifyToggleBtn("enable");
		
		op.enableDisableAmazonShippingTaxOnShopifyToggleBtn("enable");
		
		op.updateSrcIdentifierVal(CUSTOMSRCIDENTIFIER);
		
		op.enableDisableModifyShopifyOrderNameBtn("enable", TAG1, TAG2);
		
		op.selectTagsToShowInOrders();
		op.addCustomTagsInOrder(TAG1, TAG2);
		String sellerId = op.getSellerId();

//		create order
		String response = api.createOrders(nonExistingSKU, "1");
		JsonPath jsPath = new JsonPath(response);
		String shopifyOrderId = jsPath.getString("data.order_id");
		shopifyOrderId = StringUtils.substringAfter(shopifyOrderId, "[");
		shopifyOrderId = StringUtils.substringBefore(shopifyOrderId, "]");
		System.out.println(shopifyOrderId);
		util.hold(10);
//		validate order is available on shopify
		util.openOrdersInNewTab();
		util.getShopifyOrder(shopifyOrderId);
		op.validateOrderIsCreated();
		
//		prefix and suffix
		op.verifyOrderTitleContainsPrefixSuffixAndOrderId(API.amazonOrderId, TAG1, TAG2);
		
//		Item tax
		op.validateAmazonTax(ITEMTAX);
//		Shipping tax
		op.validateAmazonShippingTax(SHIPPINGTAX);
//		source identifier val
		op.validateSrcIdentifierVal(CUSTOMSRCIDENTIFIER);
//		verify tags in shopify orders
		op.verifyTagsAreInOrder(API.amazonOrderId, sellerId, TAG1, TAG2, "yes");
		
//		Verify prefix and suffix
		
		
		util.gotoWindowByClosingCurrentOne(settingsPageUrl);

	}
	
	@Test(priority = 9, description = "disable => Tax, Shipping Tax, Src Identifier, Add Tags")
	public void testTaxIsNotAddedWhenAmazonItemTaxOnShopifySettingIsOff() {
//		go to settings and disable settings
		util.goToSection(SETTINGS);
		String settingsPageUrl = util.getDriver().getCurrentUrl();
		op.enableDisableAmazonItemTaxOnShopifyToggleBtn("disable");
		op.enableDisableAmazonShippingTaxOnShopifyToggleBtn("disable");
		
		op.disableAddTagsInOrder();
		String sellerId = op.getSellerId();

//		create order
		String response = api.createOrders(nonExistingSKU, "1");
		JsonPath jsPath = new JsonPath(response);
		String shopifyOrderId = jsPath.getString("data.order_id");
		shopifyOrderId = StringUtils.substringAfter(shopifyOrderId, "[");
		shopifyOrderId = StringUtils.substringBefore(shopifyOrderId, "]");
		System.out.println(shopifyOrderId);
		util.hold(10);
//		validate order is available on shopify
		util.openOrdersInNewTab();
		util.getShopifyOrder(shopifyOrderId);
		op.validateOrderIsCreated();
		op.validateAmazonTaxIsNotVisibleInOrder();
		op.validateAmazonShiipingTaxIsNotVisibleInOrder();
//		verify tags in shopify orders
		op.verifyTagsAreInOrder(shopifyOrderId, sellerId, TAG1, TAG2, "no");
		util.gotoWindowByClosingCurrentOne(settingsPageUrl);

	}
	
//	@Test(priority = 10, description = "enable Amazon Shipping Tax on Shopify settings and verify that order on shopify does not contains tax")
//	public void testShiippingTaxIsAddedWhenAmazonShippingTaxOnShopifySettingIsOn() {
////		go to settings and disable settings
//		util.goToSection(SETTINGS);
//		String settingsPageUrl = util.getDriver().getCurrentUrl();
//		op.enableDisableAmazonShippingTaxOnShopifyToggleBtn("enable");
//
////		create order
//		String response = api.createOrders(nonExistingSKU, "1");
//		JsonPath jsPath = new JsonPath(response);
//		String shopifyOrderId = jsPath.getString("data.order_id");
//		shopifyOrderId = StringUtils.substringAfter(shopifyOrderId, "[");
//		shopifyOrderId = StringUtils.substringBefore(shopifyOrderId, "]");
//		System.out.println(shopifyOrderId);
//		util.hold(10);
////		validate order is available on shopify
//		util.openOrdersInNewTab();
//		util.getShopifyOrder(shopifyOrderId);
//		op.validateOrderIsCreated();
//		op.validateAmazonTax(SHIPPINGTAX);
//		util.gotoWindowByClosingCurrentOne(settingsPageUrl);
//
//	}
//
//	@Test(dataProvider = "getOrderData", priority = 4)
//	public void run_TC_AM_OS_004(HashMap<String, String> input) {
//		
//		op.TC_AM_OS_004(input);
//	}
//
//	@Test(priority = 5)
//	public void run_TC_AM_OS_006() {
//		
//		op.TC_AM_OS_006();
//	}
//
//	@Test(dataProvider = "getOrderData", priority = 6)
//	public void TC_AM_OS_005(HashMap<String, String> input) {
//		JsonBody.amazonOrderId = "";
//		
//		
//		op.enableCreateOrderForNonExistingProductsTogglebutton();
//		util.hold(5);
//		orderId = createOrder.createOrdersForNonExistinProducts(input);
//		util.hold(10);
//		op.openOrder(orderId);
//		op.validateOrderIsCreated();
//	}
//
//	@Test(dataProvider = "getOrderData", priority = 7)
//	public void run_TC_AM_OS_007(HashMap<String, String> input) {
//		JsonBody.amazonOrderId = "";
//		
//		
//		op.disableCreateOrderForNonExistingProductsTogglebutton();
//		util.hold(5);
//		createOrder.createOrdersForNonExistinProducts(input);
//		util.hold(10);
//		op.searchFailedOrders(JsonBody.amazonOrderId);
//	}
//
//	@Test(dataProvider = "getOrderData", priority = 8)
//	public void run_TC_AM_OS_008(HashMap<String, String> input) {
//		JsonBody.amazonOrderId = "";
//		
//		
//		op.TC_AM_OS_008();
//		op.enableAddItemTaxWithOrdersToggleButton();
//		util.hold(5);
//		orderId = createOrder.createOrdersForNonExistinProducts(input);
//		util.hold(10);
//		op.openOrder(orderId);
//		op.validateOrderIsCreated();
//		op.validateTaxFieldIsAvailableInOrder();
//	}
//
//	@Test(dataProvider = "getOrderData", priority = 9)
//	public void run_TC_AM_OS_009(HashMap<String, String> input) {
//		JsonBody.amazonOrderId = "";
//		
//		
//		op.TC_AM_OS_009();
//		op.disableAddItemTaxWithOrdersToggleButton();
//		util.hold(5);
//		orderId = createOrder.createOrdersForNonExistinProducts(input);
//		util.hold(10);
//		op.openOrder(orderId);
//		op.validateTaxFieldIsNotAvailableInOrder();
//	}
//
//	@Test(priority = 10)
//	public void run_TC_AM_OS_010() {
//		
//		op.TC_AM_OS_010();
//	}
//
//	@Test(priority = 11)
//	public void run_TC_AM_OS_011() {
//		
//		op.TC_AM_OS_011();
//	}
//
//	@Test(priority = 12)
//	public void run_TC_AM_OS_012() {
//		
//		op.TC_AM_OS_012();
//	}
//
//	@Test(priority = 13)
//	public void run_TC_AM_OS_013() {
//		
//		op.TC_AM_OS_013();
//	}
//
//	@Test(priority = 14)
//	public void run_TC_AM_OS_014() {
//		
//		op.TC_AM_OS_014();
//	}
//
//	@Test(priority = 15)
//	public void run_TC_AM_OS_015() {
//		
//		op.TC_AM_OS_015();
//	}
//
//	@Test(priority = 16)
//	public void run_TC_AM_OS_016() {
//		
//		op.TC_AM_OS_016();
//	}
//
//	@Test(priority = 17)
//	public void run_TC_AM_OS_017() {
//		
//		op.TC_AM_OS_017();
//	}
//
//	@Test(dataProvider = "getOrderData", priority = 18)
//	public void run_TC_AM_OS_018_019(HashMap<String, String> input) {
//		
//		op.TC_AM_OS_018_019(input);
//	}
//
//	@Test(priority = 19)
//	public void run_TC_AM_OS_020() {
//		
//		op.TC_AM_OS_020();
//	}
//
//	@Test(priority = 20)
//	public void run_TC_AM_OS_021() {
//		
//		op.TC_AM_OS_021();
//	}
//
//	@Test(dataProvider = "getOrderData", priority = 21)
//	public void run_TC_AM_OS_022(HashMap<String, String> input) {
//		
//		op.TC_AM_OS_022(input);
//	}
//
//	@Test(priority = 22)
//	public void run_TC_AM_OS_023() {
//		
//		op.TC_AM_OS_023();
//	}
//
//	@Test(dataProvider = "getOrderData", priority = 23)
//	public void run_TC_AM_OS_024(HashMap<String, String> input) {
//		
//		op.TC_AM_OS_024(input);
//	}
//
//	@Test(dataProvider = "getOrderData", priority = 24)
//	public void run_TC_AM_OS_025(HashMap<String, String> input) {
//		
//		op.TC_AM_OS_025(input);
//	}
//
//	@Test(priority = 25)
//	public void run_TC_AM_OS_026() {
//		
//		op.TC_AM_OS_026();
//	}
//
//	@Test(priority = 28)
//	public void run_TC_AM_OS_029() {
//		
//		op.TC_AM_OS_029();
//	}
//
//	@Test(priority = 29)
//	public void run_TC_AM_OS_030() {
//		
//		op.TC_AM_OS_030();
//	}
//
//	@Test(priority = 33)
//	public void run_TC_AM_OS_033_34() {
//		
//		op.TC_AM_OS_033_34();
//	}
//
//	@Test(priority = 35)
//	public void run_TC_AM_OS_035() {
//		
//		op.TC_AM_OS_035();
//	}
//
//	@Test(priority = 36)
//	public void run_TC_AM_OS_036() {
//		
//		op.TC_AM_OS_036();
//	}
//
//	@Test(priority = 37)
//	public void run_TC_AM_OS_037() {
//		
//		op.TC_AM_OS_037();
//	}
//
//	@Test(priority = 52)
//	public void run_TC_AM_OS_052() {
//		
//		op.TC_AM_OS_052();
//	}
//
//	@Test(priority = 54)
//	public void run_TC_AM_OS_054_55_56() {
//		
//		op.TC_AM_OS_054_55_56();
//	}
//
//	@Test(priority = 57)
//	public void run_TC_AM_OS_057() {
//		
//		op.TC_AM_OS_057();
//	}
//
//	@Test(dataProvider = "getOrderData", priority = 62)
//	public void TC_AM_OS_062_25(HashMap<String, String> input) {
//		JsonBody.amazonOrderId = "";
//		
//		
//		op.enableSyncOrderRefundToggleButton();
//		util.hold(5);
//		orderId = createOrder.createOrdersForNonExistinProducts(input);
//		util.hold(10);
//		op.openOrder(orderId);
//		op.validateOrderHasPrefixAndSuffix(input);
//		op.openRefund();
//		op.refundWithoutAnyRefundReason(input);
//	}
//
////	NEED TO ADD ONE MORE API TO VALIDATE THIS TEST CASE
////	@Test(dataProvider = "getOrderData", priority = 63)
////	public void TC_AM_OS_063(HashMap<String, String> input) {
////		JsonBody.amazonOrderId = "";
////		
////		
////		op.enableSyncOrderRefundToggleButtonAndEnterRefundReason();
////		util.hold(5);
////		orderId = createOrder.createOrdersForNonExistinProducts(input);
////		util.hold(10);
////		op.openOrder(orderId);
////		op.openRefund();
////		op.refundWithRefundReason(input);
////		op.verifyRefundOrderIsInManageFailedOrders();
////	}
//
//	@Test(dataProvider = "getOrderData", priority = 64)
//	public void TC_AM_OS_064(HashMap<String, String> input) {
//		JsonBody.amazonOrderId = "";
//		
//		
//		op.disableSyncOrderRefundToggleButton();
//		util.hold(5);
//		orderId = createOrder.createOrdersForNonExistinProducts(input);
//		util.hold(10);
//		op.openOrder(orderId);
//		op.openRefund();
//		op.refundWithRefundReason(input);
//		op.verifyRefundOrderIsNotInManageFailedOrders();
//	}
//
//	@Test(dataProvider = "getOrderData", priority = 65)
//	public void TC_AM_OS_065(HashMap<String, String> input) {
//		JsonBody.amazonOrderId = "";
//		
//		
//		op.disableSyncOrderRefundToggleButton();
//		util.hold(5);
//		orderId = createOrder.createOrdersForNonExistinProducts(input);
//		util.hold(10);
//		op.openOrder(orderId);
//		op.openRefund();
//		op.refundWithoutAnyRefundReason(input);
//	}
//
//	@Test(dataProvider = "getOrderData", priority = 68)
//	public void TC_AM_OS_068(HashMap<String, String> input) {
//		
//		op.TC_AM_OS_068(input);
//	}
//
//	@Test(dataProvider = "getOrderData", priority = 69)
//	public void TC_AM_OS_069(HashMap<String, String> input) {
//		
//		op.TC_AM_OS_069(input);
//	}
//
//	@Test(dataProvider = "getOrderData", priority = 3)
//	public void testSyncOrderCancellationFunctionality(HashMap<String, String> input) {
//		
//		
//		JsonBody.amazonOrderId = "";
//		op.syncOrderCancellationStatusBtnFunctionality();
//		util.hold(5);
//		orderId = createOrder.createOrdersForNonExistinProducts(input);
//		util.hold(10);
//		op.openOrder(orderId);
//		createOrder.cancelOrder(input);
//		util.hold(20);
//		util.refreshPage();
//		util.hold(5);
//		op.validateOrderIsFullyCanceled();
//	}

}
