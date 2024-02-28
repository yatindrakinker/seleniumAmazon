package api;

import static io.restassured.RestAssured.given;

import com.ami.resources.Utilities;

import io.restassured.path.json.JsonPath;

public class API {
	Utilities util;
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
	public static String amazonOrderId = "";
	public static String orderItemId = "";
	private String cancelOrderUrl ;

	public API(Utilities util) {
		this.util = util;
	}

	public String createOrders(String sku, String qty) {

		if (util.getConfigProperty(STORE).equalsIgnoreCase(STAGING)) {
			orderShopId = util.getConfigProperty("orderShopIdStaging");
			createOrder = util.getConfigProperty("baseUrlCreateOrderStaging");
		} else {
			orderShopId = util.getConfigProperty("orderShopIdLive");
			createOrder = util.getConfigProperty("baseUrlCreateOrder");
		}

		String responseCreateOrder = given().log().all().queryParam("home_shop_id", orderShopId)
				.queryParam("marketplace", "amazon").header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + util.getConfigProperty("token"))
				.body(orderPayload(sku, orderShopId, qty)).when().post(createOrder).then().log().all().assertThat()
				.statusCode(200).extract().response().asString();
		System.out.println("responseCreateOrder = " + responseCreateOrder);

		JsonPath jsPath = new JsonPath(responseCreateOrder);
		System.out.println(jsPath.getString("success"));
		
		
		
		
		

		return responseCreateOrder;
	}

	public String cancelOrders(String amazonOrderID, String sku, String qty, String orderItemId) {

		if (util.getConfigProperty(STORE).equalsIgnoreCase(STAGING)) {
			orderShopId = util.getConfigProperty("orderShopIdStaging");
			
			cancelOrderUrl = "https://staging-amazon-sales-channel-app-backend.cifapps.com/connector/test/orderCancellation";
		} else {
			orderShopId = util.getConfigProperty("orderShopIdLive");
			cancelOrderUrl = "https://amazon-sales-channel-app-backend.cifapps.com/connector/test/orderCancellation";
		}

		String responseCreateOrder = given().log().all().queryParam("home_shop_id", orderShopId)
				.queryParam("marketplace", "amazon").header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + util.getConfigProperty("token"))
				.body(cancelOrderPayload(amazonOrderID, sku, orderShopId, qty, orderItemId)).when().post(cancelOrderUrl).then().log()
				.all().assertThat().statusCode(200).extract().response().asString();

		return responseCreateOrder;
	}

	public String orderPayload(String sku, String shopId, String quantity) {
		amazonOrderId = "";
		amazonOrderId = Utilities.amazonOrderId();
		orderItemId = Utilities.amazonOrderId();
		return "{\n" + "    \"region\": \"NA\",\n" + "    \"data\": {\n" + "        \"BuyerInfo\": {\n"
				+ "            \"BuyerEmail\": \"pzv63120423gfrgt83ry@marketplace.amazon.com\",\n"
				+ "            \"BuyerName\": \"Test data\"\n" + "        },\n" + "        \"AmazonOrderId\": \""
				+ amazonOrderId + "\",\n" + "        \"EarliestDeliveryDate\": \"2023-01-31T08:00:00Z\",\n"
				+ "        \"EarliestShipDate\": \"2023-01-10T08:00:00Z\",\n"
				+ "        \"SalesChannel\": \"Amazon.com\",\n" + "        \"AutomatedShippingSettings\": {\n"
				+ "            \"HasAutomatedShippingSettings\": false\n" + "        },\n"
				+ "        \"OrderStatus\": \"Unshipped\",\n" + "        \"NumberOfItemsShipped\": 0,\n"
				+ "        \"OrderType\": \"StandardOrder\",\n" + "        \"IsPremiumOrder\": false,\n"
				+ "        \"IsPrime\": false,\n" + "        \"FulfillmentChannel\": \"MFN\",\n"
				+ "        \"NumberOfItemsUnshipped\": 1,\n" + "        \"HasRegulatedItems\": false,\n"
				+ "        \"IsReplacementOrder\": false,\n" + "        \"IsSoldByAB\": false,\n"
				+ "        \"LatestShipDate\": \"2023-01-12T07:59:59Z\",\n"
				+ "        \"ShipServiceLevel\": \"Std US D2D Dom\",\n"
				+ "        \"DefaultShipFromLocationAddress\": {\n"
				+ "            \"AddressLine1\": \"3 cité de l'ameublement\",\n"
				+ "            \"Phone\": \"+1 763-225-9463 ext. 67994\",\n"
				+ "            \"PostalCode\": \"75011\",\n" + "            \"City\": \"Paris\",\n"
				+ "            \"CountryCode\": \"FR\",\n" + "            \"Name\": \"Syos\"\n" + "        },\n"
				+ "        \"IsISPU\": false,\n" + "        \"MarketplaceId\": \"ATVPDKIKX0DER\",\n"
				+ "        \"LatestDeliveryDate\": \"2023-08-05T07:59:59Z\",\n"
				+ "        \"PurchaseDate\": \"2023-08-05T06:31:32Z\",\n" + "        \"ShippingAddress\": {\n"
				+ "            \"StateOrRegion\": \"FL\",\n" + "            \"PostalCode\": \"32720-0940\",\n"
				+ "            \"City\": \"DELAND\",\n" + "            \"CountryCode\": \"US\"\n" + "        },\n"
				+ "        \"IsAccessPointOrder\": false,\n" + "        \"PaymentMethod\": \"Other\",\n"
				+ "        \"IsBusinessOrder\": false,\n" + "        \"OrderTotal\": {\n"
				+ "            \"CurrencyCode\": \"USD\",\n" + "            \"Amount\": \"218.29\"\n" + "        },\n"
				+ "        \"PaymentMethodDetails\": [\n" + "            \"Standard\"\n" + "        ],\n"
				+ "        \"IsGlobalExpressEnabled\": false,\n"
				+ "        \"LastUpdateDate\": \"2023-01-05T06:50:29Z\",\n"
				+ "        \"ShipmentServiceLevelCategory\": \"Standard\"\n" + "    },\n" + "    \"items\": [\n"
				+ "        {\n" + "            \"TaxCollection\": {\n"
				+ "                \"Model\": \"MarketplaceFacilitator\",\n"
				+ "                \"ResponsibleParty\": \"Amazon Services, Inc.\"\n" + "            },\n"
				+ "            \"ProductInfo\": {\n" + "                \"NumberOfItems\": \"1\"\n" + "            },\n"
				+ "            \"BuyerInfo\": {},\n" + "            \"ItemTax\": {\n"
				+ "                \"CurrencyCode\": \"USD\",\n" + "                \"Amount\": \"12.29\"\n"
				+ "            },\n" + "            \"QuantityShipped\": 0,\n"
				+ "            \"BuyerRequestedCancel\": {\n"
				+ "                \"IsBuyerRequestedCancel\": \"false\",\n"
				+ "                \"BuyerCancelReason\": \"\"\n" + "            },\n"
				+ "            \"ItemPrice\": {\n" + "                \"CurrencyCode\": \"USD\",\n"
				+ "                \"Amount\": \"189.00\"\n" + "            },\n"
				+ "            \"ASIN\": \"B09NMLVT49\",\n" + "            \"SellerSKU\": \"" + sku + "\",\n"
				+ "            \"Title\": \"Syos Soprano Saxophone Mouthpiece, Spark Model, 7 Tip Opening, Carmine Red, Improve the Sound of your Soprano Sax with this Easy-To-Play, Bright and Powerful Soprano Mouthpiece\",\n"
				+ "            \"ShippingTax\": {\n" + "                \"CurrencyCode\": \"USD\",\n"
				+ "                \"Amount\": \"10.00\"\n" + "            },\n"
				+ "            \"IsGift\": \"false\",\n" + "            \"ShippingPrice\": {\n"
				+ "                \"CurrencyCode\": \"USD\",\n" + "                \"Amount\": \"7.00\"\n"
				+ "            },\n" + "            \"ConditionSubtypeId\": \"New\",\n"
				+ "            \"ShippingDiscount\": {\n" + "                \"CurrencyCode\": \"USD\",\n"
				+ "                \"Amount\": \"0.00\"\n" + "            },\n"
				+ "            \"ShippingDiscountTax\": {\n" + "                \"CurrencyCode\": \"USD\",\n"
				+ "                \"Amount\": \"0.00\"\n" + "            },\n"
				+ "            \"IsTransparency\": false,\n" + "            \"QuantityOrdered\": " + quantity + ",\n"
				+ "            \"PromotionDiscountTax\": {\n" + "                \"CurrencyCode\": \"USD\",\n"
				+ "                \"Amount\": \"0.00\"\n" + "            },\n"
				+ "            \"ConditionId\": \"New\",\n" + "            \"PromotionDiscount\": {\n"
				+ "                \"CurrencyCode\": \"USD\",\n" + "                \"Amount\": \"0.00\"\n"
				+ "            },\n" + "            \"OrderItemId\": \""+orderItemId+"\"\n" + "        }\n"
				+ "    ],\n" + "    \"shop_id\": \"" + shopId + "\",\n" + "    \"home_shop_id\": \"" + shopId + "\"\n"
				+ "}";
	}

	public String cancelOrderPayload(String amazonOrderID, String sku, String orderShopId, String quantity, String orderItemId) {
		return "{\n" + "            \"success\": true,\n" + "            \"data\": {\n"
				+ "                \"_url\": \"/webapi/rest/v1/order\",\n" + "                \"shop_id\": \""
				+ orderShopId + "\",\n" + "                \"home_shop_id\": \"" + orderShopId + "\",\n"
				+ "                \"amazon_order_id\": \"" + amazonOrderID + "\"\n" + "            },\n"
				+ "            \"orders\": [\n" + "               {\n" + "            \"region\": \"NA\",\n"
				+ "            \"country\": \"US\",\n"
				+ "            \"url\": \"https://sellercentral.amazon.com/orders-v3/order/113-6442627-16354145\",\n"
				+ "            \"data\": {\n" + "                \"BuyerInfo\": {\n"
				+ "                    \"BuyerEmail\": \"abc@zon.com\",\n"
				+ "                    \"BuyerName\": \"Terese Cracroft\"\n" + "                },\n"
				+ "                \"AmazonOrderId\": \"" + amazonOrderID + "\",\n"
				+ "                \"EarliestDeliveryDate\": \"2022-12-24T07:00:00Z\",\n"
				+ "                \"EarliestShipDate\": \"2022-12-23T07:00:00Z\",\n"
				+ "                \"SalesChannel\": \"Amazon.com\",\n"
				+ "                \"AutomatedShippingSettings\": {\n"
				+ "                    \"HasAutomatedShippingSettings\": false\n" + "                },\n"
				+ "                \"OrderStatus\": \"Canceled\",\n" + "                \"NumberOfItemsShipped\": 0,\n"
				+ "                \"OrderType\": \"StandardOrder\",\n" + "                \"IsPremiumOrder\": false,\n"
				+ "                \"IsPrime\": false,\n" + "                \"FulfillmentChannel\": \"MFN\",\n"
				+ "                \"NumberOfItemsUnshipped\": 1,\n" + "                \"HasRegulatedItems\": false,\n"
				+ "                \"IsReplacementOrder\": \"false\",\n" + "                \"IsSoldByAB\": false,\n"
				+ "                \"LatestShipDate\": \"2022-12-23T06:59:59Z\",\n"
				+ "                \"ShipServiceLevel\": \"Econ US Dom\",\n"
				+ "                \"DefaultShipFromLocationAddress\": {\n"
				+ "                    \"StateOrRegion\": \"Michigan\",\n"
				+ "                    \"AddressLine1\": \"1448 E Outer Dr\",\n"
				+ "                    \"Phone\": \"+1 346-307-9643 ext. 80190\",\n"
				+ "                    \"PostalCode\": \"48234-1593\",\n"
				+ "                    \"City\": \"Detroit\",\n" + "                    \"CountryCode\": \"US\",\n"
				+ "                    \"Name\": \"Hair & Beyond 1 L.L.C\"\n" + "                },\n"
				+ "                \"IsISPU\": false,\n" + "                \"MarketplaceId\": \"ATVPDKIKX0DER\",\n"
				+ "                \"LatestDeliveryDate\": \"2022-10-20T06:59:59Z\",\n"
				+ "                \"PurchaseDate\": \"2022-12-22T19:46:02Z\",\n"
				+ "                \"ShippingAddress\": {\n" + "                    \"StateOrRegion\": \"UT\",\n"
				+ "                    \"AddressLine1\": \"1545 E DOWNINGTON AVE\",\n"
				+ "                    \"Phone\": \"+1 346-307-9643 ext. 80190\",\n"
				+ "                    \"PostalCode\": \"84105-3804\",\n"
				+ "                    \"City\": \"SALT LAKE CITY\",\n"
				+ "                    \"CountryCode\": \"US\",\n"
				+ "                    \"AddressType\": \"Residential\",\n"
				+ "                    \"Name\": \"Carole Williams\",\n"
				+ "                    \"AddressLine2\": \"\",\n" + "                    \"AddressLine3\": \"\"\n"
				+ "                },\n" + "                \"IsAccessPointOrder\": false,\n"
				+ "                \"PaymentMethod\": \"Other\",\n" + "                \"IsBusinessOrder\": false,\n"
				+ "                \"OrderTotal\": {\n" + "                    \"CurrencyCode\": \"INR\",\n"
				+ "                    \"Amount\": \"100.00\"\n" + "                },\n"
				+ "                \"PaymentMethodDetails\": [\n" + "                    \"Standard\"\n"
				+ "                ],\n" + "                \"IsGlobalExpressEnabled\": false,\n"
				+ "                \"LastUpdateDate\": \"2022-12-22T20:17:24Z\",\n"
				+ "                \"ShipmentServiceLevelCategory\": \"FreeEconomy\",\n"
				+ "                \"ShipServiceLevelCategory\": \"FreeEconomy\"\n" + "            },\n"
				+ "              \"items\": [\n" + "        {\n" + "            \"TaxCollection\": {\n"
				+ "                \"Model\": \"MarketplaceFacilitator\",\n"
				+ "                \"ResponsibleParty\": \"Amazon Services, Inc.\"\n" + "            },\n"
				+ "            \"ProductInfo\": {\n" + "                \"NumberOfItems\": \"1\"\n" + "            },\n"
				+ "            \"BuyerInfo\": {},\n" + "            \"ItemTax\": {\n"
				+ "                \"CurrencyCode\": \"USD\",\n" + "                \"Amount\": \"0.97\"\n"
				+ "            },\n" + "            \"QuantityShipped\": 0,\n"
				+ "            \"BuyerRequestedCancel\": {\n"
				+ "                \"IsBuyerRequestedCancel\": \"true\",\n"
				+ "                \"BuyerCancelReason\": \"Test Cancel Reason\"\n" + "            },\n"
				+ "            \"ItemPrice\": {\n" + "                \"CurrencyCode\": \"USD\",\n"
				+ "                \"Amount\": \"12.50\"\n" + "            },\n" + "            \"SellerSKU\": \"" + sku
				+ "\",\n" + "            \"Title\": \"Roux Fanci-full Mousse #26 Golden Spell\",\n"
				+ "            \"IsGift\": \"false\",\n" + "            \"ConditionSubtypeId\": \"New\",\n"
				+ "            \"IsTransparency\": false,\n" + "            \"QuantityOrdered\": 2,\n"
				+ "            \"PromotionDiscountTax\": {\n" + "                \"CurrencyCode\": \"USD\",\n"
				+ "                \"Amount\": \"0.00\"\n" + "            },\n"
				+ "            \"ConditionId\": \"New\",\n" + "            \"PromotionDiscount\": {\n"
				+ "                \"CurrencyCode\": \"USD\",\n" + "                \"Amount\": \"0.00\"\n"
				+ "            },\n" + "            \"OrderItemId\": \""+orderItemId+"\",\n"
				+ "            \"QuantityCancelled\": " + quantity + "\n" + "        }\n" + "    ],\n"
				+ "            \"shop_id\": \"" + orderShopId + "\",\n" + "            \"home_shop_id\": \""
				+ orderShopId + "\"\n" + "        }\n" + "            ],\n" + "            \"spapi\": true,\n"
				+ "            \"start_time\": \"11-01-2022 07:54:53\",\n"
				+ "            \"end_time\": \"11-01-2022 07:54:54\",\n"
				+ "            \"execution_time\": 0.5678579807281494,\n" + "            \"ip\": \"103.97.184.106\"\n"
				+ "        }";

	}
	
	private String cancelOrder(String shopId, String amazonOrderId, String sku, String qty) {
		
		return "{\n"
				+ "            \"success\": true,\n"
				+ "            \"data\": {\n"
				+ "                \"_url\": \"/webapi/rest/v1/order\",\n"
				+ "                \"shop_id\": \""+shopId+"\",\n"
				+ "                \"home_shop_id\": \""+shopId+"\",\n"
				+ "                \"amazon_order_id\": \""+amazonOrderId+"\"\n"
				+ "            },\n"
				+ "            \"orders\": [\n"
				+ "               {\n"
				+ "            \"region\": \"NA\",\n"
				+ "            \"country\": \"US\",\n"
				+ "            \"url\": \"https://sellercentral.amazon.com/orders-v3/order/113-6442627-16354145\",\n"
				+ "            \"data\": {\n"
				+ "                \"BuyerInfo\": {\n"
				+ "                    \"BuyerEmail\": \"abc@zon.com\",\n"
				+ "                    \"BuyerName\": \"Terese Cracroft\"\n"
				+ "                },\n"
				+ "                \"AmazonOrderId\": \""+amazonOrderId+"\",\n"
				+ "                \"EarliestDeliveryDate\": \"2022-12-24T07:00:00Z\",\n"
				+ "                \"EarliestShipDate\": \"2022-12-23T07:00:00Z\",\n"
				+ "                \"SalesChannel\": \"Amazon.com\",\n"
				+ "                \"AutomatedShippingSettings\": {\n"
				+ "                    \"HasAutomatedShippingSettings\": false\n"
				+ "                },\n"
				+ "                \"OrderStatus\": \"Canceled\",\n"
				+ "                \"NumberOfItemsShipped\": 0,\n"
				+ "                \"OrderType\": \"StandardOrder\",\n"
				+ "                \"IsPremiumOrder\": false,\n"
				+ "                \"IsPrime\": false,\n"
				+ "                \"FulfillmentChannel\": \"MFN\",\n"
				+ "                \"NumberOfItemsUnshipped\": 1,\n"
				+ "                \"HasRegulatedItems\": false,\n"
				+ "                \"IsReplacementOrder\": \"false\",\n"
				+ "                \"IsSoldByAB\": false,\n"
				+ "                \"LatestShipDate\": \"2022-12-23T06:59:59Z\",\n"
				+ "                \"ShipServiceLevel\": \"Econ US Dom\",\n"
				+ "                \"DefaultShipFromLocationAddress\": {\n"
				+ "                    \"StateOrRegion\": \"Michigan\",\n"
				+ "                    \"AddressLine1\": \"1448 E Outer Dr\",\n"
				+ "                    \"Phone\": \"+1 346-307-9643 ext. 80190\",\n"
				+ "                    \"PostalCode\": \"48234-1593\",\n"
				+ "                    \"City\": \"Detroit\",\n"
				+ "                    \"CountryCode\": \"US\",\n"
				+ "                    \"Name\": \"Hair & Beyond 1 L.L.C\"\n"
				+ "                },\n"
				+ "                \"IsISPU\": false,\n"
				+ "                \"MarketplaceId\": \"ATVPDKIKX0DER\",\n"
				+ "                \"LatestDeliveryDate\": \"2022-10-20T06:59:59Z\",\n"
				+ "                \"PurchaseDate\": \"2022-12-22T19:46:02Z\",\n"
				+ "                \"ShippingAddress\": {\n"
				+ "                    \"StateOrRegion\": \"UT\",\n"
				+ "                    \"AddressLine1\": \"1545 E DOWNINGTON AVE\",\n"
				+ "                    \"Phone\": \"+1 346-307-9643 ext. 80190\",\n"
				+ "                    \"PostalCode\": \"84105-3804\",\n"
				+ "                    \"City\": \"SALT LAKE CITY\",\n"
				+ "                    \"CountryCode\": \"US\",\n"
				+ "                    \"AddressType\": \"Residential\",\n"
				+ "                    \"Name\": \"Carole Williams\",\n"
				+ "                    \"AddressLine2\": \"\",\n"
				+ "                    \"AddressLine3\": \"\"\n"
				+ "                },\n"
				+ "                \"IsAccessPointOrder\": false,\n"
				+ "                \"PaymentMethod\": \"Other\",\n"
				+ "                \"IsBusinessOrder\": false,\n"
				+ "                \"OrderTotal\": {\n"
				+ "                    \"CurrencyCode\": \"INR\",\n"
				+ "                    \"Amount\": \"100.00\"\n"
				+ "                },\n"
				+ "                \"PaymentMethodDetails\": [\n"
				+ "                    \"Standard\"\n"
				+ "                ],\n"
				+ "                \"IsGlobalExpressEnabled\": false,\n"
				+ "                \"LastUpdateDate\": \"2022-12-22T20:17:24Z\",\n"
				+ "                \"ShipmentServiceLevelCategory\": \"FreeEconomy\",\n"
				+ "                \"ShipServiceLevelCategory\": \"FreeEconomy\"\n"
				+ "            },\n"
				+ "              \"items\": [\n"
				+ "        {\n"
				+ "            \"TaxCollection\": {\n"
				+ "                \"Model\": \"MarketplaceFacilitator\",\n"
				+ "                \"ResponsibleParty\": \"Amazon Services, Inc.\"\n"
				+ "            },\n"
				+ "            \"ProductInfo\": {\n"
				+ "                \"NumberOfItems\": \"1\"\n"
				+ "            },\n"
				+ "            \"BuyerInfo\": {},\n"
				+ "            \"ItemTax\": {\n"
				+ "                \"CurrencyCode\": \"USD\",\n"
				+ "                \"Amount\": \"0.97\"\n"
				+ "            },\n"
				+ "            \"QuantityShipped\": 0,\n"
				+ "            \"BuyerRequestedCancel\": {\n"
				+ "                \"IsBuyerRequestedCancel\": \"true\",\n"
				+ "                \"BuyerCancelReason\": \"Test Cancel Reason\"\n"
				+ "            },\n"
				+ "            \"ItemPrice\": {\n"
				+ "                \"CurrencyCode\": \"USD\",\n"
				+ "                \"Amount\": \"12.50\"\n"
				+ "            },\n"
				+ "            \"SellerSKU\": \""+sku+"\",\n"
				+ "            \"Title\": \"Roux Fanci-full Mousse #26 Golden Spell\",\n"
				+ "            \"IsGift\": \"false\",\n"
				+ "            \"ConditionSubtypeId\": \"New\",\n"
				+ "            \"IsTransparency\": false,\n"
				+ "            \"QuantityOrdered\": 2,\n"
				+ "            \"PromotionDiscountTax\": {\n"
				+ "                \"CurrencyCode\": \"USD\",\n"
				+ "                \"Amount\": \"0.00\"\n"
				+ "            },\n"
				+ "            \"ConditionId\": \"New\",\n"
				+ "            \"PromotionDiscount\": {\n"
				+ "                \"CurrencyCode\": \"USD\",\n"
				+ "                \"Amount\": \"0.00\"\n"
				+ "            },\n"
				+ "            \"OrderItemId\": \"6162120670747631\",\n"
				+ "            \"QuantityCancelled\": "+qty+"\n"
				+ "        }\n"
				+ "    ],\n"
				+ "            \"shop_id\": \""+shopId+"\",\n"
				+ "            \"home_shop_id\": \""+shopId+"\"\n"
				+ "        }\n"
				+ "            ],\n"
				+ "            \"spapi\": true,\n"
				+ "            \"start_time\": \"11-01-2022 07:54:53\",\n"
				+ "            \"end_time\": \"11-01-2022 07:54:54\",\n"
				+ "            \"execution_time\": 0.5678579807281494,\n"
				+ "            \"ip\": \"103.97.184.106\"\n"
				+ "        }";
	}

}
