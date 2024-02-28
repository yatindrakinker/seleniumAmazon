package nomanual;

public class BodyPayloadNoManual {

	public String getCategoryAttributePayload(String targetSId, String srcSId, String productType, String category,
			String subCateg) {
		return "{\n" + "    \"target\": {\n" + "        \"marketplace\": \"amazon\",\n" + "        \"shopId\": \""
				+ targetSId + "\"\n" + "    },\n" + "    \"source\": {\n" + "        \"marketplace\": \"shopify\",\n"
				+ "        \"shopId\": \"" + srcSId + "\"\n" + "    },\n" + "    \"data\": {\n"
				+ "        \"product_type\": [\"" + productType + "\"],\n" + "        \"category\": \"" + category
				+ "\",\n" + "        \"sub_category\": \"" + subCateg + "\",\n"
				+ "        \"browser_node_id\": \"0\",\n" + "        \"barcode_exemption\": false\n" + "    }\n" + "}";
	}
	
	public String getCategoryAttributePayload2(String targetSId, String srcSId, String productType, String category,
			String subCateg) {
		return "{\n"
				+ "    \"target\": {\n"
				+ "        \"marketplace\": \"amazon\",\n"
				+ "        \"shopId\": \"176\"\n"
				+ "    },\n"
				+ "    \"source\": {\n"
				+ "        \"marketplace\": \"shopify\",\n"
				+ "        \"shopId\": \"119\"\n"
				+ "    },\n"
				+ "    \"data\": {\n"
				+ "        \"product_type\": [\"LUGGAGE\"],\n"
				+ "        \"category\": \"Clothing\",\n"
				+ "        \"sub_category\": \"SHIRT\",\n"
				+ "        \"browser_node_id\": \"0\",\n"
				+ "        \"barcode_exemption\": false\n"
				+ "    }\n"
				+ "}";
	}

}


