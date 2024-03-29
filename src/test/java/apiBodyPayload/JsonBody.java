package apiBodyPayload;

import com.ami.resources.BaseClass;
import com.ami.resources.Utilities;

public class JsonBody extends BaseClass {
	private String pastDate = util.getYesterday().toString();
	private String purchaseDate = util.getToday().toString();
	private String earliestDeliveryDate = util.getTomorrow().toString();
	private String latestDeliveryDate = util.getTomorrow().toString();
	private String earliestShipDate = util.getDayAfterTomorrow().toString();
	private String latestShipDate = util.getDayAfterTomorrow().toString();
	private String lastUpdateDate = util.getTomorrow().toString();
	public static String amazonOrderId = "";
	private float itemPrice = util.floatRandomNumber();
	private float taxAmount = util.itemTax();
	private float totalOrderAmount = itemPrice + taxAmount;
	private String sellerSKU = util.orderItemId();
	private String orderItemId = util.orderItemId();

	public String prepaidJsonBody(String serviceCredits, String availableCredits, String usedCredits) {
		return "{\n"
				+ "    \"type\": \"user_service\",\n"
				+ "    \"prepaid\": {\n"
				+ "        \"service_credits\": "+serviceCredits+",\n"
				+ "        \"available_credits\": "+availableCredits+",\n"
				+ "        \"total_used_credits\": "+usedCredits+"\n"
				+ "    }\n"
				+ "}";
	}

	public String postpaidJsonBody(String availableCredits, String usedCredits, String cappedCredits) {
		return "{\n" + "   \"type\" : \"user_service\",\n" + "   \"postpaid\": {\n" + "       \"total_used_credits\": "
				+ usedCredits + ",\n" + "       \"available_credits\": " + availableCredits + ",\n"
				+ "       \"capped_credit\": " + cappedCredits + "\n" + "   }\n" + "}";
	}

	public String orderForNonExistingShopifyProduct(String shopId) {

		if (amazonOrderId.equals("")) {
			amazonOrderId = Utilities.amazonOrderId();
		}

		return "{\n" + "  \"region\": \"NA\",\n" + "  \"country\": \"India\",\n"
				+ "  \"url\": \"https://sellercentral.amazon.com/orders-v3/order/113-6442627-1635448\",\n"
				+ "  \"data\": {\n" + "    \"BuyerInfo\": {\n"
				+ "      \"BuyerEmail\": \"vyzpgdnxwnt32h5@marketplace.amazon.com\",\n"
				+ "      \"BuyerName\": \"Terese Cracroft\"\n" + "    },\n" + "    \"AmazonOrderId\": \""
				+ amazonOrderId + "\",\n" + "    \"EarliestDeliveryDate\": \"" + earliestDeliveryDate + "\",\n"
				+ "    \"EarliestShipDate\": \"" + earliestShipDate + "\",\n"
				+ "    \"SalesChannel\": \"Amazon.com\",\n" + "    \"AutomatedShippingSettings\": {\n"
				+ "      \"HasAutomatedShippingSettings\": false\n" + "    },\n"
				+ "    \"OrderStatus\": \"Unshipped\",\n" + "    \"NumberOfItemsShipped\": 0,\n"
				+ "    \"OrderType\": \"StandardOrder\",\n" + "    \"IsPremiumOrder\": false,\n"
				+ "    \"IsPrime\": false,\n" + "    \"FulfillmentChannel\": \"MFN\",\n"
				+ "    \"NumberOfItemsUnshipped\": 1,\n" + "    \"HasRegulatedItems\": false,\n"
				+ "    \"IsReplacementOrder\": \"false\",\n" + "    \"IsSoldByAB\": false,\n"
				+ "    \"LatestShipDate\": \"" + latestShipDate + "\",\n"
				+ "    \"ShipServiceLevel\": \"Econ US Dom\",\n" + "    \"DefaultShipFromLocationAddress\": {\n"
				+ "      \"StateOrRegion\": \"Michigan\",\n" + "      \"AddressLine1\": \"1448 E Outer Dr\",\n"
				+ "      \"Phone\": \"+1 346-307-9643 ext. 80190\",\n" + "      \"PostalCode\": \"48234-1593\",\n"
				+ "      \"City\": \"Detroit\",\n" + "      \"CountryCode\": \"US\",\n"
				+ "      \"Name\": \"Hair & Beyond 1 L.L.C\"\n" + "    },\n" + "    \"IsISPU\": false,\n"
				+ "    \"MarketplaceId\": \"ATVPDKIKX0DER\",\n" + "    \"LatestDeliveryDate\": \"" + latestDeliveryDate
				+ "\",\n" + "    \"PurchaseDate\": \"" + purchaseDate + "\",\n" + "    \"ShippingAddress\": {\n"
				+ "      \"StateOrRegion\": \"UT\",\n" + "      \"AddressLine1\": \"1545 E DOWNINGTON AVE\",\n"
				+ "      \"Phone\": \"+1 346-307-9643 ext. 80190\",\n" + "      \"PostalCode\": \"84105-3804\",\n"
				+ "      \"City\": \"SALT LAKE CITY\",\n" + "      \"CountryCode\": \"US\",\n"
				+ "      \"AddressType\": \"Residential\",\n" + "      \"Name\": \"Carole Williams\",\n"
				+ "      \"AddressLine2\": \"\",\n" + "      \"AddressLine3\": \"\"\n" + "    },\n"
				+ "    \"IsAccessPointOrder\": false,\n" + "    \"PaymentMethod\": \"Other\",\n"
				+ "    \"IsBusinessOrder\": false,\n" + "    \"OrderTotal\": {\n" + "      \"CurrencyCode\": \"USD\",\n"
				+ "      \"Amount\": \"" + totalOrderAmount + "\"\n" + "    },\n" + "    \"PaymentMethodDetails\": [\n"
				+ "      \"Standard\"\n" + "    ],\n" + "    \"IsGlobalExpressEnabled\": false,\n"
				+ "    \"LastUpdateDate\": \"" + lastUpdateDate + "\",\n"
				+ "    \"ShipmentServiceLevelCategory\": \"FreeEconomy\",\n"
				+ "    \"ShipServiceLevelCategory\": \"FreeEconomy\"\n" + "  },\n" + "  \"items\": [\n" + "    {\n"
				+ "      \"TaxCollection\": {\n" + "        \"Model\": \"MarketplaceFacilitator\",\n"
				+ "        \"ResponsibleParty\": \"Amazon Services, Inc.\"\n" + "      },\n"
				+ "      \"ProductInfo\": {\n" + "        \"NumberOfItems\": \"1\"\n" + "      },\n"
				+ "      \"BuyerInfo\": {\n" + "\n" + "      },\n" + "      \"ItemTax\": {\n"
				+ "        \"CurrencyCode\": \"USD\",\n" + "        \"Amount\": \"" + taxAmount + "\"\n" + "      },\n"
				+ "      \"QuantityShipped\": 0,\n" + "      \"BuyerRequestedCancel\": {\n"
				+ "        \"IsBuyerRequestedCancel\": \"false\",\n" + "        \"BuyerCancelReason\": \"\"\n"
				+ "      },\n" + "      \"ItemPrice\": {\n" + "        \"CurrencyCode\": \"USD\",\n"
				+ "        \"Amount\": \"" + itemPrice + "\"\n" + "      },\n" + "      \"ASIN\": \"B0149K6J9W\",\n"
				+ "      \"SellerSKU\":" + sellerSKU + ",\n"
				+ "      \"Title\": \"Roux Fanci-full Mousse #26 Golden Spell\",\n" + "      \"IsGift\": \"false\",\n"
				+ "      \"ConditionSubtypeId\": \"New\",\n" + "      \"IsTransparency\": false,\n"
				+ "      \"QuantityOrdered\": 1,\n" + "      \"PromotionDiscountTax\": {\n"
				+ "        \"CurrencyCode\": \"USD\",\n" + "        \"Amount\": \"0.00\"\n" + "      },\n"
				+ "      \"ConditionId\": \"New\",\n" + "      \"PromotionDiscount\": {\n"
				+ "        \"CurrencyCode\": \"USD\",\n" + "        \"Amount\": \"0.00\"\n" + "      },\n"
				+ "      \"OrderItemId\": \"" + orderItemId + "\"\n" + "    }\n" + "  ],\n" + "  \"shop_id\": \""
				+ shopId + "\",\n" + "  \"home_shop_id\": \"" + shopId + "\"\n" + "}";
	}

	public String demo(String shopId) {
		if (amazonOrderId.equals("")) {
			amazonOrderId = Utilities.amazonOrderId();
		}
		return "{\n" + "    \"region\": \"NA\",\n" + "    \"data\": {\n" + "        \"BuyerInfo\": {\n"
				+ "            \"BuyerEmail\": \"pzv63120423gfrgt83ry@marketplace.amazon.com\",\n"
				+ "            \"BuyerName\": \"Test data\"\n" + "        },\n" + "        \"AmazonOrderId\": \""
				+ amazonOrderId + "\",\n" + "        \"EarliestDeliveryDate\": \"" + earliestDeliveryDate + "\",\n"
				+ "        \"EarliestShipDate\": \"" + earliestShipDate + "\",\n"
				+ "        \"SalesChannel\": \"Amazon.com\",\n" + "        \"AutomatedShippingSettings\": {\n"
				+ "            \"HasAutomatedShippingSettings\": false\n" + "        },\n"
				+ "        \"OrderStatus\": \"Unshipped\",\n" + "        \"NumberOfItemsShipped\": 0,\n"
				+ "        \"OrderType\": \"StandardOrder\",\n" + "        \"IsPremiumOrder\": false,\n"
				+ "        \"IsPrime\": false,\n" + "        \"FulfillmentChannel\": \"MFN\",\n"
				+ "        \"NumberOfItemsUnshipped\": 1,\n" + "        \"HasRegulatedItems\": false,\n"
				+ "        \"IsReplacementOrder\": false,\n" + "        \"IsSoldByAB\": false,\n"
				+ "        \"LatestShipDate\": \"" + latestShipDate + "\",\n"
				+ "        \"ShipServiceLevel\": \"Std US D2D Dom\",\n"
				+ "        \"DefaultShipFromLocationAddress\": {\n"
				+ "            \"AddressLine1\": \"3 cité de l'ameublement\",\n"
				+ "            \"Phone\": \"+1 763-225-9463 ext. 67994\",\n"
				+ "            \"PostalCode\": \"75011\",\n" + "            \"City\": \"Paris\",\n"
				+ "            \"CountryCode\": \"FR\",\n" + "            \"Name\": \"Syos\"\n" + "        },\n"
				+ "        \"IsISPU\": false,\n" + "        \"MarketplaceId\": \"ATVPDKIKX0DER\",\n"
				+ "        \"LatestDeliveryDate\": \"" + latestDeliveryDate + "\",\n" + "        \"PurchaseDate\": \""
				+ purchaseDate + "\",\n" + "        \"ShippingAddress\": {\n"
				+ "            \"StateOrRegion\": \"FL\",\n" + "            \"PostalCode\": \"32720-0940\",\n"
				+ "            \"City\": \"DELAND\",\n" + "            \"CountryCode\": \"US\"\n" + "        },\n"
				+ "        \"IsAccessPointOrder\": false,\n" + "        \"PaymentMethod\": \"Other\",\n"
				+ "        \"IsBusinessOrder\": false,\n" + "        \"OrderTotal\": {\n"
				+ "            \"CurrencyCode\": \"USD\",\n" + "            \"Amount\": \"218.29\"\n" + "        },\n"
				+ "        \"PaymentMethodDetails\": [\n" + "            \"Standard\"\n" + "        ],\n"
				+ "        \"IsGlobalExpressEnabled\": false,\n" + "        \"LastUpdateDate\": \"" + lastUpdateDate
				+ "\",\n" + "        \"ShipmentServiceLevelCategory\": \"Standard\"\n" + "    },\n"
				+ "    \"items\": [\n" + "        {\n" + "            \"TaxCollection\": {\n"
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
				+ "            \"ASIN\": \"B09NMLVT49\",\n" + "            \"SellerSKU\": \"7516581757142\",\n"
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
				+ "            \"IsTransparency\": false,\n" + "            \"QuantityOrdered\": 1,\n"
				+ "            \"PromotionDiscountTax\": {\n" + "                \"CurrencyCode\": \"USD\",\n"
				+ "                \"Amount\": \"0.00\"\n" + "            },\n"
				+ "            \"ConditionId\": \"New\",\n" + "            \"PromotionDiscount\": {\n"
				+ "                \"CurrencyCode\": \"USD\",\n" + "                \"Amount\": \"0.00\"\n"
				+ "            },\n" + "            \"OrderItemId\": \"" + orderItemId + "\"\n" + "        }\n"
				+ "    ],\n" + "    \"shop_id\": \"" + shopId + "\",\n" + "    \"home_shop_id\": \"" + shopId + "\"\n"
				+ "}";
	}

	public String cancelOrderForNonExistingOrders(String shopId, String quantity) {
		return "{\n" + "  \"success\": true,\n" + "  \"data\": {\n" + "    \"_url\": \"/webapi/rest/v1/order\",\n"
				+ "    \"shop_id\": \"" + shopId + "\",\n" + "    \"home_shop_id\": \"" + shopId + "\",\n"
				+ "    \"amazon_order_id\": \"" + amazonOrderId + "\"\n" + "  },\n" + "  \"orders\": [\n" + "    {\n"
				+ "      \"data\": {\n" + "        \"BuyerInfo\": [],\n" + "        \"AmazonOrderId\": \""
				+ amazonOrderId + "\",\n" + "        \"EarliestShipDate\": \"2023-02-06T08:00:00Z\",\n"
				+ "        \"SalesChannel\": \"Amazon.com\",\n" + "        \"AutomatedShippingSettings\": {\n"
				+ "          \"HasAutomatedShippingSettings\": false\n" + "        },\n"
				+ "        \"OrderStatus\": \"Canceled\",\n" + "        \"NumberOfItemsShipped\": 0,\n"
				+ "        \"OrderType\": \"StandardOrder\",\n" + "        \"IsPremiumOrder\": false,\n"
				+ "        \"IsPrime\": false,\n" + "        \"FulfillmentChannel\": \"MFN\",\n"
				+ "        \"NumberOfItemsUnshipped\": 0,\n" + "        \"HasRegulatedItems\": false,\n"
				+ "        \"IsReplacementOrder\": false,\n" + "        \"IsSoldByAB\": false,\n"
				+ "        \"LatestShipDate\": \"2023-02-07T07:59:59Z\",\n"
				+ "        \"ShipServiceLevel\": \"Std US D2D Dom\",\n" + "        \"IsISPU\": false,\n"
				+ "        \"MarketplaceId\": \"ATVPDKIKX0DER\",\n"
				+ "        \"PurchaseDate\": \"2023-02-05T02:35:38Z\",\n" + "        \"IsAccessPointOrder\": false,\n"
				+ "        \"IsBusinessOrder\": false,\n" + "        \"OrderTotal\": {\n"
				+ "          \"CurrencyCode\": \"USD\",\n" + "          \"Amount\": \"45.30\"\n" + "        },\n"
				+ "        \"PaymentMethodDetails\": [\n" + "          \"Standard\"\n" + "        ],\n"
				+ "        \"IsGlobalExpressEnabled\": false,\n"
				+ "        \"LastUpdateDate\": \"2023-02-16T08:46:17Z\",\n"
				+ "        \"ShipmentServiceLevelCategory\": \"Standard\"\n" + "      },\n" + "      \"items\": [\n"
				+ "        {\n" + "          \"TaxCollection\": {\n"
				+ "            \"Model\": \"MarketplaceFacilitator\",\n"
				+ "            \"ResponsibleParty\": \"Amazon Services, Inc.\"\n" + "          },\n"
				+ "          \"ProductInfo\": {\n" + "            \"NumberOfItems\": \"1\"\n" + "          },\n"
				+ "          \"BuyerInfo\": [],\n" + "          \"ItemTax\": {\n"
				+ "            \"CurrencyCode\": \"USD\",\n" + "            \"Amount\": \"0.93\"\n" + "          },\n"
				+ "          \"QuantityShipped\": 0,\n" + "          \"BuyerRequestedCancel\": {\n"
				+ "            \"IsBuyerRequestedCancel\": \"true\",\n"
				+ "            \"BuyerCancelReason\": \"dgyfgy\"\n" + "          },\n" + "          \"ItemPrice\": {\n"
				+ "            \"CurrencyCode\": \"USD\",\n" + "            \"Amount\": \"37.38\"\n" + "          },\n"
				+ "          \"ASIN\": \"B000VYGH2A\",\n" + "          \"SellerSKU\": \"" + sellerSKU + "\",\n"
				+ "          \"Title\": \"Simpson Strong Tie IS24-R100 24-Inch OC Insulation Supports, 100-Pack\",\n"
				+ "          \"ShippingTax\": {\n" + "            \"CurrencyCode\": \"USD\",\n"
				+ "            \"Amount\": \"0.17\"\n" + "          },\n" + "          \"IsGift\": \"false\",\n"
				+ "          \"ShippingPrice\": {\n" + "            \"CurrencyCode\": \"USD\",\n"
				+ "            \"Amount\": \"6.82\"\n" + "          },\n"
				+ "          \"ConditionSubtypeId\": \"New\",\n" + "          \"ShippingDiscount\": {\n"
				+ "            \"CurrencyCode\": \"USD\",\n" + "            \"Amount\": \"0.00\"\n" + "          },\n"
				+ "          \"ShippingDiscountTax\": {\n" + "            \"CurrencyCode\": \"USD\",\n"
				+ "            \"Amount\": \"0.00\"\n" + "          },\n" + "          \"IsTransparency\": false,\n"
				+ "          \"QuantityOrdered\": 0,\n" + "          \"PromotionDiscountTax\": {\n"
				+ "            \"CurrencyCode\": \"USD\",\n" + "            \"Amount\": \"0.00\"\n" + "          },\n"
				+ "          \"ConditionId\": \"New\",\n" + "          \"PromotionDiscount\": {\n"
				+ "            \"CurrencyCode\": \"USD\",\n" + "            \"Amount\": \"0.00\"\n" + "          },\n"
				+ "          \"OrderItemId\": \"" + orderItemId + "\",\n" + "          \"QuantityCancelled\": "
				+ quantity + "\n" + "        }\n" + "      ],\n" + "      \"region\": null,\n"
				+ "      \"country\": \"US\",\n" + "      \"url\": \"https://sellercentral.amazon.com/orders-v3/order/"
				+ amazonOrderId + "\",\n" + "      \"shop_id\": \"" + shopId + "\"\n" + "    }\n" + "  ]}";
	}

	public String orderBodyForLocalSelling(String supplySrcId) {
		amazonOrderId = Utilities.amazonOrderId();
		orderItemId = util.orderItemId();
		util.updateProperty("orderIdAmazon", amazonOrderId);

		return "{\n" + "    \"region\": \"NA\",\n" + "    \"data\": {\n" + "        \"BuyerInfo\": {\n"
				+ "            \"BuyerEmail\": \"pzv63120423gfrgt83ry@marketplace.test.com\",\n"
				+ "            \"BuyerName\": \"Test data\"\n" + "        },\n" + "        \"AmazonOrderId\": \""
				+ amazonOrderId + "\",\n" + "        \"EarliestDeliveryDate\": \"" + earliestDeliveryDate + "\",\n"
				+ "        \"EarliestShipDate\": \"" + earliestShipDate + "\",\n"
				+ "        \"SalesChannel\": \"Amazon.com\",\n" + "        \"AutomatedShippingSettings\": {\n"
				+ "            \"HasAutomatedShippingSettings\": false\n" + "        },\n"
				+ "        \"OrderStatus\": \"Unshipped\",\n" + "        \"NumberOfItemsShipped\": 0,\n"
				+ "        \"OrderType\": \"StandardOrder\",\n" + "        \"IsPremiumOrder\": false,\n"
				+ "        \"IsPrime\": false,\n" + "        \"FulfillmentChannel\": \"MFN\",\n"
				+ "        \"NumberOfItemsUnshipped\": 2,\n" + "        \"HasRegulatedItems\": false,\n"
				+ "        \"IsReplacementOrder\": false,\n" + "        \"IsSoldByAB\": false,\n"
				+ "        \"LatestShipDate\": \"" + latestShipDate + "\",\n"
				+ "        \"ShipServiceLevel\": \"Std US D2D Dom\",\n"
				+ "        \"DefaultShipFromLocationAddress\": {\n" + "            \"AddressLine1\": \"Lucknow 1\",\n"
				+ "            \"Phone\": \"+1 763-225-9463\",\n" + "            \"PostalCode\": \"75011\",\n"
				+ "            \"City\": \"Paris\",\n" + "            \"CountryCode\": \"FR\",\n"
				+ "            \"Name\": \"Syos\"\n" + "        },\n" + "        \"IsISPU\": true,\n"
				+ "        \"MarketplaceId\": \"ATVPDKIKX0DER\",\n" + "        \"LatestDeliveryDate\": \""
				+ latestDeliveryDate + "\",\n" + "        \"PurchaseDate\": \"" + purchaseDate + "\",\n"
				+ "        \"ShippingAddress\": {\n" + "            \"StateOrRegion\": \"FL\",\n"
				+ "            \"PostalCode\": \"32720-0940\",\n" + "            \"City\": \"DELAND\",\n"
				+ "            \"CountryCode\": \"US\",\n" + "            \"AddressLine1\": \"3 cité de\"\n"
				+ "        },\n" + "        \"IsAccessPointOrder\": false,\n"
				+ "        \"PaymentMethod\": \"Other\",\n" + "        \"IsBusinessOrder\": false,\n"
				+ "        \"OrderTotal\": {\n" + "            \"CurrencyCode\": \"USD\",\n"
				+ "            \"Amount\": \"218.29\"\n" + "        },\n" + "        \"PaymentMethodDetails\": [\n"
				+ "            \"Standard\"\n" + "        ],\n" + "        \"IsGlobalExpressEnabled\": false,\n"
				+ "        \"LastUpdateDate\": \"2023-01-05T06:50:29Z\",\n"
				+ "        \"ShipmentServiceLevelCategory\": \"Standard\"\n" + "    },\n" + "    \"items\": [\n"
				+ "        {\n" + "            \"TaxCollection\": {\n"
				+ "                \"Model\": \"MarketplaceFacilitator\",\n"
				+ "                \"ResponsibleParty\": \"Amazon Services, Inc.\"\n" + "            },\n"
				+ "            \"ProductInfo\": {\n" + "                \"NumberOfItems\": \"1\"\n" + "            },\n"
				+ "            \"BuyerInfo\": {},\n" + "            \"ItemTax\": {\n"
				+ "                \"CurrencyCode\": \"USD\",\n" + "                \"Amount\": \"12.29\"\n"
				+ "            },\n" + "            \"QuantityShipped\": 0,\n" + "            \"StoreChainStoreId\": \""
				+ supplySrcId + "\",\n" + "            \"BuyerRequestedCancel\": {\n"
				+ "                \"IsBuyerRequestedCancel\": \"false\",\n"
				+ "                \"BuyerCancelReason\": \"\"\n" + "            },\n"
				+ "            \"ItemPrice\": {\n" + "                \"CurrencyCode\": \"USD\",\n"
				+ "                \"Amount\": \"189.00\"\n" + "            },\n"
				+ "            \"SellerSKU\": \"18059259377021145\",\n"
				+ "            \"Title\": \"Syos Soprano Saxophone Mouthpiece, Spark Modele\",\n"
				+ "            \"ShippingTax\": {\n" + "                \"CurrencyCode\": \"USD\",\n"
				+ "                \"Amount\": \"10.00\"\n" + "            },\n"
				+ "            \"IsGift\": \"false\",\n" + "            \"ShippingPrice\": {\n"
				+ "                \"CurrencyCode\": \"USD\",\n" + "                \"Amount\": \"7.00\"\n"
				+ "            },\n" + "            \"ConditionSubtypeId\": \"New\",\n"
				+ "            \"ShippingDiscount\": {\n" + "                \"CurrencyCode\": \"USD\",\n"
				+ "                \"Amount\": \"0.00\"\n" + "            },\n"
				+ "            \"ShippingDiscountTax\": {\n" + "                \"CurrencyCode\": \"USD\",\n"
				+ "                \"Amount\": \"0.00\"\n" + "            },\n"
				+ "            \"IsTransparency\": false,\n" + "            \"QuantityOrdered\": 2,\n"
				+ "            \"PromotionDiscountTax\": {\n" + "                \"CurrencyCode\": \"USD\",\n"
				+ "                \"Amount\": \"0.00\"\n" + "            },\n"
				+ "            \"ConditionId\": \"New\",\n" + "            \"PromotionDiscount\": {\n"
				+ "                \"CurrencyCode\": \"USD\",\n" + "                \"Amount\": \"0.00\"\n"
				+ "            },\n" + "            \"OrderItemId\": \"" + orderItemId + "\"\n" + "        }\n"
				+ "    ],\n" + "    \"shop_id\": \"" + util.getConfigProperty("target_shop_id") + "\",\n"
				+ "    \"home_shop_id\": \"" + util.getConfigProperty("target_shop_id") + "\"\n" + "}";
	}

	public String excessChargePayload(String usedCredit, String availCredit, String cappedCredit) {
		return "{\n" + "   \"type\" : \"user_service\",\n" + "   \"postpaid\": { \n" + "       \"total_used_credits\": "
				+ usedCredit + ", \n" + "       \"available_credits\": " + availCredit + ", \n"
				+ "       \"capped_credit\": " + cappedCredit + " \n" + "   },\n" + "   \"expired_at\": \"\" \n" + "}\n"
				+ "";
	}

	public String orderBodyWithPastDeleiveryDate(String supplySrcId) {

		if (amazonOrderId.equals("")) {
			amazonOrderId = Utilities.amazonOrderId();
			util.updateProperty("orderIdAmazon", amazonOrderId);
		}

		return "{\n" + "    \"region\": \"NA\",\n" + "    \"data\": {\n" + "        \"BuyerInfo\": {\n"
				+ "            \"BuyerEmail\": \"pzv63120423gfrgt83ry@marketplace.test.com\",\n"
				+ "            \"BuyerName\": \"Test data\"\n" + "        },\n" + "        \"AmazonOrderId\": \""
				+ amazonOrderId + "\",\n" + "        \"EarliestDeliveryDate\": \"" + earliestDeliveryDate + "\",\n"
				+ "        \"EarliestShipDate\": \"" + earliestShipDate + "\",\n"
				+ "        \"SalesChannel\": \"Amazon.com\",\n" + "        \"AutomatedShippingSettings\": {\n"
				+ "            \"HasAutomatedShippingSettings\": false\n" + "        },\n"
				+ "        \"OrderStatus\": \"Unshipped\",\n" + "        \"NumberOfItemsShipped\": 0,\n"
				+ "        \"OrderType\": \"StandardOrder\",\n" + "        \"IsPremiumOrder\": false,\n"
				+ "        \"IsPrime\": false,\n" + "        \"FulfillmentChannel\": \"MFN\",\n"
				+ "        \"NumberOfItemsUnshipped\": 2,\n" + "        \"HasRegulatedItems\": false,\n"
				+ "        \"IsReplacementOrder\": false,\n" + "        \"IsSoldByAB\": false,\n"
				+ "        \"LatestShipDate\": \"" + latestShipDate + "\",\n"
				+ "        \"ShipServiceLevel\": \"Std US D2D Dom\",\n"
				+ "        \"DefaultShipFromLocationAddress\": {\n" + "            \"AddressLine1\": \"Lucknow 1\",\n"
				+ "            \"Phone\": \"+1 763-225-9463\",\n" + "            \"PostalCode\": \"75011\",\n"
				+ "            \"City\": \"Paris\",\n" + "            \"CountryCode\": \"FR\",\n"
				+ "            \"Name\": \"Syos\"\n" + "        },\n" + "        \"IsISPU\": true,\n"
				+ "        \"MarketplaceId\": \"ATVPDKIKX0DER\",\n" + "        \"LatestDeliveryDate\": \"" + pastDate
				+ "\",\n" + "        \"PurchaseDate\": \"" + purchaseDate + "\",\n" + "        \"ShippingAddress\": {\n"
				+ "            \"StateOrRegion\": \"FL\",\n" + "            \"PostalCode\": \"32720-0940\",\n"
				+ "            \"City\": \"DELAND\",\n" + "            \"CountryCode\": \"US\",\n"
				+ "            \"AddressLine1\": \"3 cité de\"\n" + "        },\n"
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
				+ "            },\n" + "            \"QuantityShipped\": 0,\n" + "            \"StoreChainStoreId\": \""
				+ supplySrcId + "\",\n" + "            \"BuyerRequestedCancel\": {\n"
				+ "                \"IsBuyerRequestedCancel\": \"false\",\n"
				+ "                \"BuyerCancelReason\": \"\"\n" + "            },\n"
				+ "            \"ItemPrice\": {\n" + "                \"CurrencyCode\": \"USD\",\n"
				+ "                \"Amount\": \"189.00\"\n" + "            },\n"
				+ "            \"SellerSKU\": \"18059259377021145\",\n"
				+ "            \"Title\": \"Syos Soprano Saxophone Mouthpiece, Spark Modele\",\n"
				+ "            \"ShippingTax\": {\n" + "                \"CurrencyCode\": \"USD\",\n"
				+ "                \"Amount\": \"10.00\"\n" + "            },\n"
				+ "            \"IsGift\": \"false\",\n" + "            \"ShippingPrice\": {\n"
				+ "                \"CurrencyCode\": \"USD\",\n" + "                \"Amount\": \"7.00\"\n"
				+ "            },\n" + "            \"ConditionSubtypeId\": \"New\",\n"
				+ "            \"ShippingDiscount\": {\n" + "                \"CurrencyCode\": \"USD\",\n"
				+ "                \"Amount\": \"0.00\"\n" + "            },\n"
				+ "            \"ShippingDiscountTax\": {\n" + "                \"CurrencyCode\": \"USD\",\n"
				+ "                \"Amount\": \"0.00\"\n" + "            },\n"
				+ "            \"IsTransparency\": false,\n" + "            \"QuantityOrdered\": 2,\n"
				+ "            \"PromotionDiscountTax\": {\n" + "                \"CurrencyCode\": \"USD\",\n"
				+ "                \"Amount\": \"0.00\"\n" + "            },\n"
				+ "            \"ConditionId\": \"New\",\n" + "            \"PromotionDiscount\": {\n"
				+ "                \"CurrencyCode\": \"USD\",\n" + "                \"Amount\": \"0.00\"\n"
				+ "            },\n" + "            \"OrderItemId\": \"" + orderItemId + "\"\n" + "        }\n"
				+ "    ],\n" + "    \"shop_id\": \"" + util.getConfigProperty("target_shop_id") + "\",\n"
				+ "    \"home_shop_id\": \"" + util.getConfigProperty("target_shop_id") + "\"\n" + "}";
	}
	
	public String orderPayload(String sku, String shopId) {
		amazonOrderId = "";
		amazonOrderId = Utilities.amazonOrderId();
		return "{\n"
				+ "    \"region\": \"NA\",\n"
				+ "    \"data\": {\n"
				+ "        \"BuyerInfo\": {\n"
				+ "            \"BuyerEmail\": \"pzv63120423gfrgt83ry@marketplace.amazon.com\",\n"
				+ "            \"BuyerName\": \"Test data\"\n"
				+ "        },\n"
				+ "        \"AmazonOrderId\": \""+amazonOrderId+"\",\n"
				+ "        \"EarliestDeliveryDate\": \"2023-01-31T08:00:00Z\",\n"
				+ "        \"EarliestShipDate\": \"2023-01-10T08:00:00Z\",\n"
				+ "        \"SalesChannel\": \"Amazon.com\",\n"
				+ "        \"AutomatedShippingSettings\": {\n"
				+ "            \"HasAutomatedShippingSettings\": false\n"
				+ "        },\n"
				+ "        \"OrderStatus\": \"Unshipped\",\n"
				+ "        \"NumberOfItemsShipped\": 0,\n"
				+ "        \"OrderType\": \"StandardOrder\",\n"
				+ "        \"IsPremiumOrder\": false,\n"
				+ "        \"IsPrime\": false,\n"
				+ "        \"FulfillmentChannel\": \"MFN\",\n"
				+ "        \"NumberOfItemsUnshipped\": 1,\n"
				+ "        \"HasRegulatedItems\": false,\n"
				+ "        \"IsReplacementOrder\": false,\n"
				+ "        \"IsSoldByAB\": false,\n"
				+ "        \"LatestShipDate\": \"2023-01-12T07:59:59Z\",\n"
				+ "        \"ShipServiceLevel\": \"Std US D2D Dom\",\n"
				+ "        \"DefaultShipFromLocationAddress\": {\n"
				+ "            \"AddressLine1\": \"3 cité de l'ameublement\",\n"
				+ "            \"Phone\": \"+1 763-225-9463 ext. 67994\",\n"
				+ "            \"PostalCode\": \"75011\",\n"
				+ "            \"City\": \"Paris\",\n"
				+ "            \"CountryCode\": \"FR\",\n"
				+ "            \"Name\": \"Syos\"\n"
				+ "        },\n"
				+ "        \"IsISPU\": false,\n"
				+ "        \"MarketplaceId\": \"ATVPDKIKX0DER\",\n"
				+ "        \"LatestDeliveryDate\": \"2023-08-05T07:59:59Z\",\n"
				+ "        \"PurchaseDate\": \"2023-08-05T06:31:32Z\",\n"
				+ "        \"ShippingAddress\": {\n"
				+ "            \"StateOrRegion\": \"FL\",\n"
				+ "            \"PostalCode\": \"32720-0940\",\n"
				+ "            \"City\": \"DELAND\",\n"
				+ "            \"CountryCode\": \"US\"\n"
				+ "        },\n"
				+ "        \"IsAccessPointOrder\": false,\n"
				+ "        \"PaymentMethod\": \"Other\",\n"
				+ "        \"IsBusinessOrder\": false,\n"
				+ "        \"OrderTotal\": {\n"
				+ "            \"CurrencyCode\": \"USD\",\n"
				+ "            \"Amount\": \"218.29\"\n"
				+ "        },\n"
				+ "        \"PaymentMethodDetails\": [\n"
				+ "            \"Standard\"\n"
				+ "        ],\n"
				+ "        \"IsGlobalExpressEnabled\": false,\n"
				+ "        \"LastUpdateDate\": \"2023-01-05T06:50:29Z\",\n"
				+ "        \"ShipmentServiceLevelCategory\": \"Standard\"\n"
				+ "    },\n"
				+ "    \"items\": [\n"
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
				+ "                \"Amount\": \"12.29\"\n"
				+ "            },\n"
				+ "            \"QuantityShipped\": 0,\n"
				+ "            \"BuyerRequestedCancel\": {\n"
				+ "                \"IsBuyerRequestedCancel\": \"false\",\n"
				+ "                \"BuyerCancelReason\": \"\"\n"
				+ "            },\n"
				+ "            \"ItemPrice\": {\n"
				+ "                \"CurrencyCode\": \"USD\",\n"
				+ "                \"Amount\": \"189.00\"\n"
				+ "            },\n"
				+ "            \"ASIN\": \"B09NMLVT49\",\n"
				+ "            \"SellerSKU\": \""+sku+"\",\n"
				+ "            \"Title\": \"Syos Soprano Saxophone Mouthpiece, Spark Model, 7 Tip Opening, Carmine Red, Improve the Sound of your Soprano Sax with this Easy-To-Play, Bright and Powerful Soprano Mouthpiece\",\n"
				+ "            \"ShippingTax\": {\n"
				+ "                \"CurrencyCode\": \"USD\",\n"
				+ "                \"Amount\": \"10.00\"\n"
				+ "            },\n"
				+ "            \"IsGift\": \"false\",\n"
				+ "            \"ShippingPrice\": {\n"
				+ "                \"CurrencyCode\": \"USD\",\n"
				+ "                \"Amount\": \"7.00\"\n"
				+ "            },\n"
				+ "            \"ConditionSubtypeId\": \"New\",\n"
				+ "            \"ShippingDiscount\": {\n"
				+ "                \"CurrencyCode\": \"USD\",\n"
				+ "                \"Amount\": \"0.00\"\n"
				+ "            },\n"
				+ "            \"ShippingDiscountTax\": {\n"
				+ "                \"CurrencyCode\": \"USD\",\n"
				+ "                \"Amount\": \"0.00\"\n"
				+ "            },\n"
				+ "            \"IsTransparency\": false,\n"
				+ "            \"QuantityOrdered\": 1,\n"
				+ "            \"PromotionDiscountTax\": {\n"
				+ "                \"CurrencyCode\": \"USD\",\n"
				+ "                \"Amount\": \"0.00\"\n"
				+ "            },\n"
				+ "            \"ConditionId\": \"New\",\n"
				+ "            \"PromotionDiscount\": {\n"
				+ "                \"CurrencyCode\": \"USD\",\n"
				+ "                \"Amount\": \"0.00\"\n"
				+ "            },\n"
				+ "            \"OrderItemId\": \"6162120670747631\"\n"
				+ "        }\n"
				+ "    ],\n"
				+ "    \"shop_id\": \""+shopId+"\",\n"
				+ "    \"home_shop_id\": \""+shopId+"\"\n"
				+ "}";
	}

}
