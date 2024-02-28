package apiWebhook;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import com.ami.resources.Utilities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pojosellercentralresponse.FulfillmentAvailability;
import pojosellercentralresponse.ResponseDataSellerCentral;
import pojosellercentralresponse.SellerCentralResponse;

public class SellerCentralInventoryAPITest {

	Utilities util;
	public static String getResponse = "";
	ObjectMapper mapper;
	SellerCentralResponse sellerCentralResponse;

	public SellerCentralInventoryAPITest(Utilities util) {
		this.util = util;
	}

	public String hitSellerCentralInventoryAPI() {
		getResponse = given().log().all().queryParam("shop_id", util.getConfigProperty("shopId"))
				.queryParam("includedData", "fulfillmentAvailability").queryParam("sku", util.getConfigProperty("sku"))
				.header("Authorization", util.getConfigProperty("remoteToken")).when()
				.get(util.getConfigProperty("getInventoryAPI")).then().log().all().assertThat().statusCode(200)
				.extract().response().asString();

		return getResponse;

	}
	
	public String hitSellerCentralInventoryAPI(String sku) {
		getResponse = given().log().all().queryParam("shop_id", util.getConfigProperty("shopId"))
				.queryParam("includedData", "fulfillmentAvailability").queryParam("sku", sku)
				.header("Authorization", util.getConfigProperty("remoteToken")).when()
				.get(util.getConfigProperty("getInventoryAPI")).then().log().all().assertThat().statusCode(200)
				.extract().response().asString();

		return getResponse;

	}

	public String returnSKU(String response) {
		mapper = new ObjectMapper();
		try {
			sellerCentralResponse = mapper.readValue(response, SellerCentralResponse.class);
			ResponseDataSellerCentral responseDataSellerCentral = sellerCentralResponse.getResponse();

			return responseDataSellerCentral.getSku();

		} catch (JsonProcessingException e) {
			util.logWarn("exception in validateInventory().");
		}
		return response;
	}

	public String returnInventory(String response) {
		mapper = new ObjectMapper();

		try {
			sellerCentralResponse = mapper.readValue(response, SellerCentralResponse.class);
			ResponseDataSellerCentral responseDataSellerCentral = sellerCentralResponse.getResponse();

			List<FulfillmentAvailability> fullfillmentAvailabilty = responseDataSellerCentral
					.getFulfillmentAvailability();

			return (fullfillmentAvailabilty.get(0).getQuantity());

		} catch (JsonProcessingException e) {
			util.logWarn("exception in validateInventory().");
		}
		return "";
	}

}
