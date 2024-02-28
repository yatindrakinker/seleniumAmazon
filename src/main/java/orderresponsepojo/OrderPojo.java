package orderresponsepojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderPojo {
	private boolean success;
	private List<OrderData> data;

	public boolean isSuccess() {
		return success;
	}

	public List<OrderData> getData() {
		return data;
	}

}
