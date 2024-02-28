package com.AMI.TestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.DataProvider;
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

public class FailedRefundOrdersTest extends BaseClass {
	public String orderId;
	private String sku = "Paraffin cartridge";
	FailedRefundOrdersPage fop;
	CreateOrder createOrder;
	FailedCanceledOrdersPage fcop;
	FailedOrdersOverViewPage failedOvr;
	ReusableMethods reuse ;
	API api;
	OrderPage order;

	@DataProvider
	public Object[][] getOrderData() throws IOException {
		List<HashMap<String, String>> data = util.getJsonDataToHashmap("OrderDetails");
		return new Object[][] { { data.get(0) } };
	}

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
		reuse.unlinkProduct(sku);
	}

	@Test(priority = 2)
	public void run_TC_AM_OS_001To007() {
		util.goToMultiAccountOrders();
		util.goToSection("settings");
		order.disableCreateOrderForNonExistingProductsTogglebutton();	
		api.createOrders(sku, "1");
		util.hold(10);
		fop.goToFailedOrders();
		fop.searchOrder(API.amazonOrderId);
	}

	@Test(priority = 3)
	public void runTC_AM_OS_009_16() {
				
		fop.failedRefundOrder();
		String response = api.createOrders(sku, "1");
		JsonPath jsPath = new JsonPath(response);
		
		String shopifyOrderId = jsPath.getString("data.order_id");
		shopifyOrderId = StringUtils.substringAfter(shopifyOrderId, "[");
		shopifyOrderId = StringUtils.substringBefore(shopifyOrderId, "]");
		System.out.println(shopifyOrderId);
		util.hold(10);
		util.openOrdersInNewTab();
		util.getShopifyOrder(shopifyOrderId);
		fop.refundWithoutReason("1", API.amazonOrderId);
		
		fop.verifyOrderIsRefunded(API.amazonOrderId);
	}
	
	@Test(priority = 4)
	public void testFailedCanceledOrdersOverview() {
		util.goToMultiAccountOverView();
		fcop.validateFailedCanceledOrderFieldsAreVisible();
	}

}
