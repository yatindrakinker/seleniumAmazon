package pojosellercentralresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SellerCentralResponse {
	private SellerCentralData data;
	private ResponseDataSellerCentral response;

	public SellerCentralData getData() {
		return data;
	}

	public ResponseDataSellerCentral getResponse() {
		return response;
	}

}
