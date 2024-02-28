package locasellingdisabledorderresponsepojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocalSellingOrderDisabledPojo {

	private boolean success;
	private DataDisabledOrderPojoLocalSellin data;
	private String message;

	public boolean isSuccess() {
		return success;
	}

	public DataDisabledOrderPojoLocalSellin getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}

}
