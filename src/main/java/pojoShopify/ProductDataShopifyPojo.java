package pojoShopify;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDataShopifyPojo {
	private long id;
	private String title;
	private String body_html;
	private String vendor;
	private String product_type;
	private String handle;
	private String status;
	private String tags;
	private List<VariantsShopifyPojo> variants;
	private List<OptionsShopifyPojo> options;
	private List<ImagesShopifyPojo> images;
	private ImageShopifyPojo image;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody_html() {
		return body_html;
	}

	public void setBody_html(String body_html) {
		this.body_html = body_html;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public List<VariantsShopifyPojo> getVariants() {
		return variants;
	}

	public void setVariants(List<VariantsShopifyPojo> variants) {
		this.variants = variants;
	}

	public String getProduct_type() {
		return product_type;
	}

	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public List<OptionsShopifyPojo> getOptions() {
		return options;
	}

	public void setOptions(List<OptionsShopifyPojo> options) {
		this.options = options;
	}

	public List<ImagesShopifyPojo> getImages() {
		return images;
	}

	public void setImages(List<ImagesShopifyPojo> images) {
		this.images = images;
	}

	public ImageShopifyPojo getImage() {
		return image;
	}

	public void setImage(ImageShopifyPojo image) {
		this.image = image;
	}

}
