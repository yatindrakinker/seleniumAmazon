package nomanual;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class AMZ1130 {
//	Utilities util;
	public String devBaseUrl = "https://dev-amazon-sales-channel-app-backend.cifapps.com/connector/profile/getCategoryAttributes";
	public String stagingBaseUrl = "https://staging-amazon-sales-channel-app-backend.cifapps.com/connector/profile/getCategoryAttributes";
	public String productionBaseUrl = "https://amazon-sales-channel-app-backend.cifapps.com/connector/profile/getCategoryAttributes";
	String targetSId = "176";
	String srcSId = "119";
	String productType = "SHIRT";
	String category = "clothing";
	String subCateg = "sweatshirt";

	BodyPayloadNoManual payload = new BodyPayloadNoManual();

//	public AMZ1130(Utilities util) {
//		this.util = util;
//		PageFactory.initElements(util.getDriver(), this);
//	}

	@Test
	public void getCategoryAttribute() {
		String response = given().log().all().header("Authorization",
				"eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1c2VyX2lkIjoiNjUzYTRjZjJmMmRiNDdkYTAzMDYwN2Y2Iiwicm9sZSI6ImN1c3RvbWVyIiwiZXhwIjoxNzAzNzc2NDY2LCJpc3MiOiJodHRwczpcL1wvYXBwcy5jZWRjb21tZXJjZS5jb20iLCJ0b2tlbl9pZCI6IjY1OGQ1ODkyMDIwNTFlZGQwZjBiY2M2MiJ9.q9hldd6inlYNOPvcsTAT9RBRwckdwf8bO8R8xUipYwkdaMYuTwSkyr4RMiHn-pAQFUqSpcKQ-BaN2X5NlXp_ji26ue6lWA_nbDLdXLnAjXN1_vNtPJZRdZftSF7Ia9HUv9y3KX8Q7hrZFz1PQtomlvokb5I9KR13KnggZBl17j1JNToJl-KA9OZZ9AStQ_0I1BcjqYvZ73ujANdVlkJoTrmkaK5I-OiIwjtZvVtF-83XyXXGm48qnQRgzZ0c72-NCwuF4ay0YJhI02et7UXNVJA2AKlzNitkeaolZnKGViRaog6b3UXuTb4YfwgiLSB8-EF2Sx_DqZqpmA5XBitJfQ")
				.body(payload.getCategoryAttributePayload2(targetSId, srcSId, productType, category, subCateg)).when()
				.post(devBaseUrl).then().log().all().assertThat().statusCode(200).extract()
				.response().asString();

		System.out.println(response);
	}

}
