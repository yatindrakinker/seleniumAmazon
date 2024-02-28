package getstorefromdbpojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetAllStoresFromDBPojo {

	private boolean success;
	private String message;
	private StoreData data;

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public StoreData getData() {
		return data;
	}

}
