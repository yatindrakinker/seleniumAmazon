package getstorefromdbpojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stores {

	private StoreAddress store_address;
	private String status;
	private String supplySourceId;
	private String supplySourceCode;
	
	public StoreAddress getStore_address() {
		return store_address;
	}
	public String getStatus() {
		return status;
	}
	public String getSupplySourceId() {
		return supplySourceId;
	}
	public String getSupplySourceCode() {
		return supplySourceCode;
	}
	
	
}
