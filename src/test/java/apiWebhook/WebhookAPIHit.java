package apiWebhook;

import static io.restassured.RestAssured.given;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ami.pageobjects.CreateShopifyProduct;
import com.ami.resources.Utilities;

public class WebhookAPIHit {
	Utilities util;
	public static String createResponse = "";
	public static String updateResponse = "";
	public static String deleteResponse = "";
	private String str = "product create/update manually";

	public WebhookAPIHit(Utilities util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
	}

	@Test
	public String getCreateProductData() {
		createResponse = given().log().all().queryParam("container_id", CreateShopifyProduct.productId)
//			go to config.properties file to update userId
				.queryParam("user_id", util.getConfigProperty("userId"))
//			go to config.properties file to update token
				.queryParam("bearer", util.getConfigProperty("token")).when().get(util.getConfigProperty("createWebhook")).then()
				.log().all().assertThat().statusCode(200).extract().response().asString();

		createResponse = StringUtils.substringAfter(createResponse, "{");
		createResponse = StringUtils.substringBeforeLast(createResponse, "}");
		createResponse = "{"+createResponse+"}";
		System.err.println(createResponse);
		return createResponse;
	}

	@Test
	public String getUpdateProductData() {
		updateResponse = given().log().all().queryParam("container_id", CreateShopifyProduct.productId)
//			go to config.properties file to update userId
				.queryParam("user_id", util.getConfigProperty("userId"))
//			go to config.properties file to update token
				.queryParam("bearer", util.getConfigProperty("token")).when().get(util.getConfigProperty("updateWebhook")).then()
				.log().all().assertThat().statusCode(200).extract().response().asString();
		updateResponse = StringUtils.substringAfter(updateResponse, "{");
		updateResponse = StringUtils.substringBeforeLast(updateResponse, "}");
		updateResponse = "{"+updateResponse+"}";
		System.err.println(updateResponse);

		return updateResponse;
	}

	@Test
	public String getDeleteProductData() {
		deleteResponse = given().log().all().queryParam("container_id", CreateShopifyProduct.productId)
//			go to config.properties file to update userId
				.queryParam("user_id", util.getConfigProperty("userId"))
//			go to config.properties file to update token
				.queryParam("bearer", util.getConfigProperty("token")).when().get(util.getConfigProperty("deleteWebhook")).then()
				.log().all().assertThat().statusCode(200).extract().response().asString();

		return deleteResponse;
	}

}
