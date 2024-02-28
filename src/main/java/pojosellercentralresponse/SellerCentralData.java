package pojosellercentralresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SellerCentralData {
	String shop_id;

	public String getShop_id() {
		return shop_id;
	}

}
