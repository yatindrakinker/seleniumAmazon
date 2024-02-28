package nomanual;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.ami.resources.BaseClass;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestAMZ2461 extends BaseClass {

	private String devBaseUrl = "https://dev-amazon-sales-channel-app-backend.cifapps.com/amazon/request/preview";
	private String stagingBaseUrl = "https://staging-amazon-sales-channel-app-backend.cifapps.com/amazon/request/preview";
	private String liveBaseUrl = "https://amazon-sales-channel-app-backend.cifapps.com/amazon/request/preview";
	private String url = stagingBaseUrl;

	private String devShopId = "176";
	private String stagingShopId = "221";
	private String liveShopId = util.getConfigProperty("target_shop_id");
	private String targetShopId = stagingShopId;

	private String devSrcShopId = "119";
	private String stagingSrcShopId = "14";
	private String liveSrcShopId = util.getConfigProperty("src_shop_id");
	private String srcShopId = stagingSrcShopId;
	
	ObjectMapper dataMapper;
	
	Pojo2461 pojo = null;

	private void setBaseUrl() {
		if (util.getConfigProperty("store").equalsIgnoreCase("dev")) {
			url = devBaseUrl;
		} else if (util.getConfigProperty("store").equalsIgnoreCase("live")) {
			url = liveBaseUrl;
		}
	}

	private void setShopId() {
		if (util.getConfigProperty("store").equalsIgnoreCase("dev")) {
			srcShopId = devSrcShopId;
		} else if (util.getConfigProperty("store").equalsIgnoreCase("live")) {
			srcShopId = liveSrcShopId;
		}
	}

	private void setSrcShopId() {
		if (util.getConfigProperty("store").equalsIgnoreCase("dev")) {
			targetShopId = devShopId;
		} else if (util.getConfigProperty("store").equalsIgnoreCase("live")) {
			targetShopId = liveShopId;
		}
	}

	@Test
	public void testEachAttributeInDataObjectHasKeyValuePair() throws JsonMappingException, JsonProcessingException {
		setBaseUrl();
		setShopId();
		setSrcShopId();

		String response = given().log().all().
				header("Authorization", "Bearer " + util.getConfigProperty("token"))
				.header("appcode", "eyJzaG9waWZ5IjoiYW1hem9uX3NhbGVzX2NoYW5uZWwiLCJhbWF6b24iOiJhbWF6b24ifQ==")
				.header("ced-source-id", srcShopId)
				.header("ced-source-name", "shopify")
				.header("ced-target-id", targetShopId)
				.header("ced-target-name", "amazon")
				.header("content-type", "application/json")
				.body(payload())
				.when().post(url).then().log().all().assertThat().statusCode(200).extract().response().asString();

		System.out.println(response);
		 JSONObject jsonObject = new JSONObject(response);
		JSONObject data = jsonObject.getJSONObject("data");

        JSONObject mandatory = data.getJSONObject("Mandatory");
        JSONObject optional = data.getJSONObject("Optional");
        
     // Validate "label" and "value" keys in Mandatory
        validateKeys(mandatory);

        // Validate "label" and "value" keys in Optional
        validateKeys(optional);
		
	}
	
	 private static void validateKeys(JSONObject jsonObject) {
	        for (String key : jsonObject.keySet()) {
	            JSONObject innerObject = jsonObject.getJSONObject(key);
	            if (!innerObject.has("label")) {
	                System.out.println("Error: 'label' key is missing in " + key);
	            }
	            if (!innerObject.has("value")) {
	                System.out.println("Error: 'value' key is missing in " + key);
	            }
	        }
	    }

	private String payload() {
		return "{\n" + "    \"target\": {\n" + "        \"marketplace\": \"amazon\",\n" + "        \"shopId\": \""
				+ targetShopId + "\"\n" + "    },\n" + "    \"source\": {\n" + "        \"marketplace\": \"shopify\",\n"
				+ "        \"shopId\": \"" + srcShopId + "\"\n" + "    },\n" + "    \"source_product_ids\": [\n"
				+ "        \"44960424198441\"\n" + "    ],\n" + "    \"limit\": 1,\n" + "    \"activePage\": 1\n" + "}";

	}

}
