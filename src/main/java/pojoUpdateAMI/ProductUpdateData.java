package pojoUpdateAMI;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductUpdateData {
	private List<ProductAMIPojo> db_data;

	public List<ProductAMIPojo> getDb_data() {
		return db_data;
	}

	public void setDb_data(List<ProductAMIPojo> db_data) {
		this.db_data = db_data;
	}

}
