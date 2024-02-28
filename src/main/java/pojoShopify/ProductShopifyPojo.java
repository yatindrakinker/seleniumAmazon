package pojoShopify;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductShopifyPojo {
	private ProductDataShopifyPojo product;

	public ProductDataShopifyPojo getProduct() {
		return product;
	}

	public void setProduct(ProductDataShopifyPojo product) {
		this.product = product;
	}

}
