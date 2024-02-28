package api;

import static io.restassured.RestAssured.*;

import org.openqa.selenium.WebDriver;

import com.ami.resources.BaseClass;
import com.ami.resources.Utilities;

public class GetStoreFromDBAPI extends BaseClass{
	private String baseUrlLive;
	private String baseUrlStaging;
	private String resource = "amazon/localselling/getAllStoreFromDB";
	private String baseUrl;
	private String userId;

	public String hitGetStoreFromDBAPI() {
		baseUrlLive = "https://amazon-sales-channel-app-backend.cifapps.com/";
		baseUrlStaging = "https://staging-amazon-sales-channel-app-backend.cifapps.com/";
		if (util.getConfigProperty("store").equalsIgnoreCase("live")) {
			baseUrl = baseUrlLive + resource;
			userId = util.getConfigProperty("userId");
		} else {
			baseUrl = baseUrlStaging + resource;
			userId = util.getConfigProperty("userIdStaging");
		}

		return given().log().all().queryParam("target_shop_id", util.getConfigProperty("target_shop_id"))
				.queryParam("target_marketplace", "amazon").queryParam("count", "1").queryParam("activePage", "1")
				.queryParam("user_id", userId).header("Authorization", util.getConfigProperty("token")).when().get(baseUrl)
				.then().log().all().assertThat().statusCode(200).extract().response().asPrettyString();
	}

}
