package locasellingdisabledorderresponsepojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Disabled {
	@JsonProperty("67865")
	private ShopId _67865;

	public ShopId get_67865() {
		return _67865;
	}

}
