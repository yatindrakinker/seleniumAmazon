package pojosellercentralresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FulfillmentAvailability {
	private String fulfillmentChannelCode;
	private String quantity;

	public String getFulfillmentChannelCode() {
		return fulfillmentChannelCode;
	}

	public String getQuantity() {
		return quantity;
	}

}
