package nomanual;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.ami.resources.BaseClass;

import io.restassured.response.Response;

public class TestAMZ2757 extends BaseClass {

	private String devBaseUrl = "https://dev-amazon-sales-channel-app-backend.cifapps.com/amazon/product/getVariationThemeAttribute";
	private String stagingBaseUrl = "https://staging-amazon-sales-channel-app-backend.cifapps.com/amazon/product/getVariationThemeAttribute";
	private String liveBaseUrl = "https://amazon-sales-channel-app-backend.cifapps.com/amazon/product/getVariationThemeAttribute";
	private String url = devBaseUrl;

	private String devShopId = "176";
	private String stagingShopId = "";
	private String liveShopId = util.getConfigProperty("target_shop_id");
	private String targetShopId = devShopId;

	private String devSrcShopId = "119";
	private String stagingSrcShopId = "";
	private String liveSrcShopId = util.getConfigProperty("src_shop_id");
	private String srcShopId = devSrcShopId;

	@Test
	public void testThrottleLimit() {
		setBaseUrl();
		setShopId();
		setSrcShopId();

		// Define the number of requests to make
		int numRequests = 10;

		// Define the interval between requests in milliseconds
		int requestInterval = 10; // 100 milliseconds


		for (int i = 0; i < numRequests; i++) {
			// Make a GET request to the API
			Response response = given().log().all().header("Authorization",
					"Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1c2VyX2lkIjoiNjUzYTRjZjJmMmRiNDdkYTAzMDYwN2Y2Iiwicm9sZSI6ImN1c3RvbWVyIiwiZXhwIjoxNzA3NDkwNTk4LCJpc3MiOiJodHRwczpcL1wvYXBwcy5jZWRjb21tZXJjZS5jb20iLCJ0b2tlbl9pZCI6IjY1YzYwNGU2OGY5MTE5MmE0MTBlYzU2MiJ9.pgKiPtHzGd4HMIAPzKF4Tuw_aUMlEuptTg7sm0y4KNz-U056Lm47HHfvTh3f5PPXioIbatO2GMFXGGTUblncFsHy7NFL1P0KTpmFSfaGM9WLQoMUQXyYeGGqqdOnIYzkOXbL0VpA3K9fAvXRTyVowUGVbQlfFSnvDTBbzNet7mAIQRl3YweDkXcj85TEBFhXT9HJUr5hn-SMlG0c2ctMwdHIirS_0EEs6bRDBQmp_ShwdmDbyOpWQbAc9sjWdpn0RmyIuxR2uYoTbHGKhMqAZcR5tALTgb0iKuI3IttcUwL3FRFTsZE4IWRcN81Y5F2A5RG343yigM7ntCf-MUaDsA")
					.header("appcode", "eyJzaG9waWZ5IjoiYW1hem9uX3NhbGVzX2NoYW5uZWwiLCJhbWF6b24iOiJhbWF6b24ifQ==")
					.header("Apptag", "amazon_sales_channel").header("content-type", "application/json").body(bodyPayload()).when()
					.post(url);

			// Validate the response status code
			response.then().assertThat().statusCode(200); // Assuming 200 is the expected status code

			System.out.println(response.asString());

			// Optionally, validate other aspects of the response

			// Sleep for the specified interval before making the next request
			try {
				Thread.sleep(requestInterval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private String bodyPayload() {
		return "{\n"
				+ "    \"target\": {\n"
				+ "        \"marketplace\": \"amazon\",\n"
				+ "        \"shopId\": \""+targetShopId+"\"\n"
				+ "    },\n"
				+ "    \"source\": {\n"
				+ "        \"marketplace\": \"shopify\",\n"
				+ "        \"shopId\": \""+srcShopId+"\"\n"
				+ "    },\n"
				+ "    \"product_type\": \"SKIRT\",\n"
				+ "    \"variation_theme\": \"STYLE/SIZE\"\n"
				+ "}";
	}
	
	private void setBaseUrl() {
		if (util.getConfigProperty("store").equalsIgnoreCase("staging")) {
			url = stagingBaseUrl;
		} else if (util.getConfigProperty("store").equalsIgnoreCase("live")) {
			url = liveBaseUrl;
		}
	}

	private void setShopId() {
		if (util.getConfigProperty("store").equalsIgnoreCase("staging")) {
			srcShopId = stagingSrcShopId;
		} else if (util.getConfigProperty("store").equalsIgnoreCase("live")) {
			srcShopId = liveSrcShopId;
		}
	}

	private void setSrcShopId() {
		if (util.getConfigProperty("store").equalsIgnoreCase("staging")) {
			targetShopId = stagingShopId;
		} else if (util.getConfigProperty("store").equalsIgnoreCase("live")) {
			targetShopId = liveShopId;
		}
	}
}