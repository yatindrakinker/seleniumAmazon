package pojosellercentralresponse;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDataSellerCentral {
	private String sku;
	private List<FulfillmentAvailability> fulfillmentAvailability;

	public String getSku() {
		return sku;
	}

	public List<FulfillmentAvailability> getFulfillmentAvailability() {
		return fulfillmentAvailability;
	}

}
