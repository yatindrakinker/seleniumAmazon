package orderresponsepojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderData {

	private String shop_id;
	private String marketplace;
	private String order_id;
	public String getShop_id() {
		return shop_id;
	}
	public String getMarketplace() {
		return marketplace;
	}
	public String getOrder_id() {
		return order_id;
	}
	
	
}
