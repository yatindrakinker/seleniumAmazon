package getstorefromdbpojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreAddress {

	private String name;

	public String getName() {
		return name;
	}
	
	
}
