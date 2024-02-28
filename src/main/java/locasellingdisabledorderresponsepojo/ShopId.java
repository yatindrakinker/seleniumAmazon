package locasellingdisabledorderresponsepojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ShopId {
	private String shop_id;
	private String marketplace;
	private String message;
	private String cif_order_id;

	public String getShop_id() {
		return shop_id;
	}

	public String getMarketplace() {
		return marketplace;
	}

	public String getMessage() {
		return message;
	}

	public String getCif_order_id() {
		return cif_order_id;
	}

}
