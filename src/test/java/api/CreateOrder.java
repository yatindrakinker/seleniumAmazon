package api;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ami.resources.BaseClass;

import apiBodyPayload.JsonBody;
import io.restassured.path.json.JsonPath;

public class CreateOrder extends BaseClass {
	JsonBody jsonBody = new JsonBody();
	private String baseUrlLive;
	private String baseUrlStaging;
	private String resource = "connector/order/testOrderCreate";
	private String baseUrl;
	private String userId;
	private static final String STORE = "store";
	private static final String STAGING = "staging";
	private String url = "";
	private String orderShopId = "";
	private String createOrder = "";

	

	@DataProvider
	public Object[][] getOrderData() throws IOException {
		List<HashMap<String, String>> data = util.getJsonDataToHashmap("OrderDetails");
		return new Object[][] { { data.get(0) } };
	}
	
//	prepaid test
	public void createPrepaidOrders( String serviceCredits, String availableCredits, String usedCredits) {
		
		if(util.getConfigProperty(STORE).equalsIgnoreCase(STAGING)) {
			url = "https://staging-amazon-sales-channel-app-backend.cifapps.com/plan/plan/creditUpdateTest";
			orderShopId = util.getConfigProperty("orderShopIdStaging");
		}else {
			url = "https://staging-amazon-sales-channel-app-backend.cifapps.com/plan/plan/creditUpdateTest";
			orderShopId = util.getConfigProperty("orderShopIdLive");
		}
		
		given().log().all().header("Content-Type", "application/json")
		.header("Authorization", "Bearer " + util.getConfigProperty("token"))
		.body(jsonBody.prepaidJsonBody(serviceCredits, availableCredits, usedCredits )).when()
		.post(url).then().log().all().assertThat().statusCode(200).extract()
		.response().asString();
	}
	
//	postpaid test
	public void createPostpaidOrders(String availableCredits, String usedCredits, String cappedCredits) {
		
		if(util.getConfigProperty(STORE).equalsIgnoreCase(STAGING)) {
			url = "https://staging-amazon-sales-channel-app-backend.cifapps.com/plan/plan/creditUpdateTest";
		}else {
			url = "https://staging-amazon-sales-channel-app-backend.cifapps.com/plan/plan/creditUpdateTest";
		}
		
		given().log().all().header("Content-Type", "application/x-www-form-urlencoded")
		.header("Authorization", util.getConfigProperty("token"))
		.body(jsonBody.postpaidJsonBody(availableCredits, usedCredits, cappedCredits)).when()
		.post(url).then().log().all().assertThat().statusCode(200).extract()
		.response().asString();
	}

//	Create Orders for non-existing Products
	@Test(dataProvider = "getOrderData")
	public String createOrdersForNonExistinProducts(HashMap<String, String> input) {
		
		
		if(util.getConfigProperty(STORE).equalsIgnoreCase(STAGING)) {
			orderShopId = util.getConfigProperty("orderShopIdStaging");
			createOrder = util.getConfigProperty("baseUrlCreateOrderStaging");
		}else {
			orderShopId = util.getConfigProperty("orderShopIdLive");
			createOrder = util.getConfigProperty("baseUrlCreateOrder");
		}
		
		String responseCreateOrder = given().log().all().queryParam("home_shop_id", orderShopId)
				.queryParam("marketplace", input.get("marketplace")).header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + util.getConfigProperty("token"))
				.body(jsonBody.demo(orderShopId)).when()
				.post(createOrder).then().log().all().assertThat().statusCode(200).extract()
				.response().asString();
		System.out.println("responseCreateOrder = " + responseCreateOrder);

		JsonPath jsPath = new JsonPath(responseCreateOrder);
		System.out.println(jsPath.getString("data.order_id"));

		return jsPath.getString("data[0].order_id");
	}
	

	
	
	public void createExcessCharge(String usedCredit, String availCredit, String cappedCredit) {
		String endPoint = "";
		
		if(util.getConfigProperty("store").equalsIgnoreCase("staging")) {
			endPoint = "https://staging-amazon-sales-channel-app-backend.cifapps.com/plan/plan/creditUpdateTest";
		}else if(util.getConfigProperty("store").equalsIgnoreCase("staging")) {
			endPoint = "https://amazon-sales-channel-app-backend.cifapps.com/plan/plan/creditUpdateTest";
		}
		
		given().log().all().header("Content-Type", "application/json")
				.header("Authorization", util.getConfigProperty("token")).body(jsonBody.excessChargePayload(usedCredit,availCredit,cappedCredit)).when()
				.post(endPoint).then().log().all().assertThat().statusCode(200).extract()
				.response().asString();
	}

	@Test(dataProvider = "getOrderData")
	public void cancelOrder(HashMap<String, String> input) {
		 given().log().all().header("Content-Type", "application/json")
				.header("Authorization", input.get("token"))
				.body(jsonBody.cancelOrderForNonExistingOrders(input.get("shop_id"), input.get("quantity_to_cancel")))
				.when().post(input.get("baseUrlCancelOrderLive")).then().log().all().assertThat().statusCode(200)
				.extract().response().asString();
	}

	public String createOrdersForLocalSellingStore(String supplySrcId) {
		baseUrlLive = "https://amazon-sales-channel-app-backend.cifapps.com/";
		baseUrlStaging = "https://staging-amazon-sales-channel-app-backend.cifapps.com/";
		
		if (util.getConfigProperty("store").equalsIgnoreCase("live")) {
			baseUrl = baseUrlLive + resource;
			userId = util.getConfigProperty("userId");
		} else {
			baseUrl = baseUrlStaging + resource;
			userId = util.getConfigProperty("userIdStaging");
		}

		return given().log().all().queryParam("home_shop_id", util.getConfigProperty("target_shop_id"))
				.queryParam("marketplace", "amazon")
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + util.getConfigProperty("token"))
				.body(jsonBody.orderBodyForLocalSelling(supplySrcId)).when().post(baseUrl).then().log()
				.all().assertThat().statusCode(200).extract().response().asPrettyString();
	}

}
