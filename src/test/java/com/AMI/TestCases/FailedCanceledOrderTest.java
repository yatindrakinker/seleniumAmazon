package com.AMI.TestCases;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import com.ami.pageobjects.FailedCanceledOrdersPage;
import com.ami.pageobjects.FailedOrdersOverViewPage;
import com.ami.pageobjects.FailedRefundOrdersPage;
import com.ami.pageobjects.OrderPage;
import com.ami.pageobjects.reusableclasses.ReusableMethods;
import com.ami.resources.BaseClass;

import api.API;
import api.CreateOrder;
import io.restassured.path.json.JsonPath;

public class FailedCanceledOrderTest extends BaseClass {
	public String orderId;
	private String sku = "Paraffin cartridge";
	FailedRefundOrdersPage fop;
	CreateOrder createOrder;
	FailedCanceledOrdersPage fcop;
	FailedOrdersOverViewPage failedOvr;
	ReusableMethods reuse;
	API api;
	OrderPage order;

	@Test(priority = 1)
	public void initialize() {
		order = new OrderPage(util);
		api = new API(util);
		fop = new FailedRefundOrdersPage(util);
		createOrder = new CreateOrder();
		fcop = new FailedCanceledOrdersPage(util);
		failedOvr = new FailedOrdersOverViewPage(util);
		reuse = new ReusableMethods(util);
		util.openSectionsInNewTab("settings");
		util.openSectionsInNewTab("linking");
	}

	@Test(priority = 2)
	public void testFailedPartialCanceledOrderIsAvailableOnApp() {
//		go to settings 
//		enable create order for non existing product
//		go to cancel and refund -> enable Sync cancelled orders on Shopify settings
		reuse.enableCancelledOrdersSyncOnShopify();

//		create order (for non existing product) -> get amazon orderID, sku, shopify orderID from response.
		String response = api.createOrders(sku, "2");
		JsonPath jsPath = new JsonPath(response);

		String shopifyOrderId = jsPath.getString("data.order_id");
		shopifyOrderId = StringUtils.substringAfter(shopifyOrderId, "[");
		shopifyOrderId = StringUtils.substringBefore(shopifyOrderId, "]");
		System.out.println(shopifyOrderId);

//		hit api -> cancel order for same amazon order ID, same sku, same orderId
		api.cancelOrders(API.amazonOrderId, sku, "1", API.orderItemId);

//		go to overview -> manage failed canceled orders -> search with shopify orderID
		util.goToSection("overview");
		fcop.searchCancelledFailedOrder(shopifyOrderId);
		fcop.verifyOrderIsRemovedFromGrid(shopifyOrderId);

	}
	
	@Test(priority = 3)
	public void testFailedCanceledOrderIsAvailableOnApp() {
//		go to settings 
//		enable create order for non existing product
//		go to cancel and refund -> enable Sync cancelled orders on Shopify settings
		reuse.enableCancelledOrdersSyncOnShopify();

//		create order (for non existing product) -> get amazon orderID, sku, shopify orderID from response.
		String response = api.createOrders(sku, "1");
		JsonPath jsPath = new JsonPath(response);

		String shopifyOrderId = jsPath.getString("data.order_id");
		shopifyOrderId = StringUtils.substringAfter(shopifyOrderId, "[");
		shopifyOrderId = StringUtils.substringBefore(shopifyOrderId, "]");
		System.out.println(shopifyOrderId);

//		hit api -> cancel order for same amazon order ID, same sku, same orderId
		api.cancelOrders(API.amazonOrderId, sku, "1", API.orderItemId);

//		go to overview -> manage failed canceled orders -> search with shopify orderID
		util.goToSection("overview");
		fcop.searchCancelledFailedOrder(shopifyOrderId);
		fcop.verifyOrderIsRemovedFromGrid(shopifyOrderId);

	}

}
