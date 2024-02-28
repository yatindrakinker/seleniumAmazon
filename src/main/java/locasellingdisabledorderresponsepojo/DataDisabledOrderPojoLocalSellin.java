package locasellingdisabledorderresponsepojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataDisabledOrderPojoLocalSellin {

	private Disabled disabled;

	public Disabled getDisabled() {
		return disabled;
	}
	
}

