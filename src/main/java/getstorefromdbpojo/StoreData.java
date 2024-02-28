package getstorefromdbpojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreData {

	private List<Stores> stores;

	public List<Stores> getStores() {
		return stores;
	}
	
	
}
