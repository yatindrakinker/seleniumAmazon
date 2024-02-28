package pojoUpdateAMI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductUpdate {

	private boolean success;
	private boolean requeue;
	private ProductUpdateData data;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isRequeue() {
		return requeue;
	}

	public void setRequeue(boolean requeue) {
		this.requeue = requeue;
	}

	public ProductUpdateData getData() {
		return data;
	}

	public void setData(ProductUpdateData data) {
		this.data = data;
	}

}
