package pojoUpdateAMI;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductAMIPojo {
	private String _id;
	private String container_id;
	private String shop_id;
	private String source_product_id;
	private String type;
	private String user_id;
	private List<Object> additional_images;
	private List<String> app_codes;
	private String barcode;
	private String brand;
	private String compare_at_price;
	private Object created_at;
	private String description;
	private String fulfillment_service;
	private double grams;
	private String handle;
	private String id;
	private String inventory_item_id;
	private String inventory_management;
	private String inventory_policy;
	private boolean inventory_tracked;
	private String low_sku;
	private String main_image;
	private int position;
	private String product_type;
	private boolean requires_shipping;
	private String sku;
	private String source_marketplace;
	private String source_sku;
	private String source_status;
	private List<String> tags;
	private boolean taxable;
	private String template_suffix;
	private String title;
	private List<Object> variant_attributes;
	private List<VariantAttributesValueAMIPojo> variant_attributes_values;
	private String variant_title;
	private double weight;
	private String weight_unit;
	private List<Marketplace> marketplace;
	private List<Object> collection;
	private List<Location> locations;
	private double price;
	private int quantity;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getContainer_id() {
		return container_id;
	}

	public void setContainer_id(String container_id) {
		this.container_id = container_id;
	}

	public String getShop_id() {
		return shop_id;
	}

	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}

	public String getSource_product_id() {
		return source_product_id;
	}

	public void setSource_product_id(String source_product_id) {
		this.source_product_id = source_product_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public List<Object> getAdditional_images() {
		return additional_images;
	}

	public void setAdditional_images(List<Object> additional_images) {
		this.additional_images = additional_images;
	}

	public List<String> getApp_codes() {
		return app_codes;
	}

	public void setApp_codes(List<String> app_codes) {
		this.app_codes = app_codes;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCompare_at_price() {
		return compare_at_price;
	}

	public void setCompare_at_price(String compare_at_price) {
		this.compare_at_price = compare_at_price;
	}

	public Object getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Object created_at) {
		this.created_at = created_at;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFulfillment_service() {
		return fulfillment_service;
	}

	public void setFulfillment_service(String fulfillment_service) {
		this.fulfillment_service = fulfillment_service;
	}

	public double getGrams() {
		return grams;
	}

	public void setGrams(double grams) {
		this.grams = grams;
	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInventory_item_id() {
		return inventory_item_id;
	}

	public void setInventory_item_id(String inventory_item_id) {
		this.inventory_item_id = inventory_item_id;
	}

	public String getInventory_management() {
		return inventory_management;
	}

	public void setInventory_management(String inventory_management) {
		this.inventory_management = inventory_management;
	}

	public String getInventory_policy() {
		return inventory_policy;
	}

	public void setInventory_policy(String inventory_policy) {
		this.inventory_policy = inventory_policy;
	}

	public boolean isInventory_tracked() {
		return inventory_tracked;
	}

	public void setInventory_tracked(boolean inventory_tracked) {
		this.inventory_tracked = inventory_tracked;
	}

	public String getLow_sku() {
		return low_sku;
	}

	public void setLow_sku(String low_sku) {
		this.low_sku = low_sku;
	}

	public String getMain_image() {
		return main_image;
	}

	public void setMain_image(String main_image) {
		this.main_image = main_image;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getProduct_type() {
		return product_type;
	}

	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}

	public boolean isRequires_shipping() {
		return requires_shipping;
	}

	public void setRequires_shipping(boolean requires_shipping) {
		this.requires_shipping = requires_shipping;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getSource_marketplace() {
		return source_marketplace;
	}

	public void setSource_marketplace(String source_marketplace) {
		this.source_marketplace = source_marketplace;
	}

	public String getSource_sku() {
		return source_sku;
	}

	public void setSource_sku(String source_sku) {
		this.source_sku = source_sku;
	}

	public String getSource_status() {
		return source_status;
	}

	public void setSource_status(String source_status) {
		this.source_status = source_status;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public boolean isTaxable() {
		return taxable;
	}

	public void setTaxable(boolean taxable) {
		this.taxable = taxable;
	}

	public String getTemplate_suffix() {
		return template_suffix;
	}

	public void setTemplate_suffix(String template_suffix) {
		this.template_suffix = template_suffix;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Object> getVariant_attributes() {
		return variant_attributes;
	}

	public void setVariant_attributes(List<Object> variant_attributes) {
		this.variant_attributes = variant_attributes;
	}

	public List<VariantAttributesValueAMIPojo> getVariant_attributes_values() {
		return variant_attributes_values;
	}

	public void setVariant_attributes_values(List<VariantAttributesValueAMIPojo> variant_attributes_values) {
		this.variant_attributes_values = variant_attributes_values;
	}

	public String getVariant_title() {
		return variant_title;
	}

	public void setVariant_title(String variant_title) {
		this.variant_title = variant_title;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getWeight_unit() {
		return weight_unit;
	}

	public void setWeight_unit(String weight_unit) {
		this.weight_unit = weight_unit;
	}

	public List<Marketplace> getMarketplace() {
		return marketplace;
	}

	public void setMarketplace(List<Marketplace> marketplace) {
		this.marketplace = marketplace;
	}

	public List<Object> getCollection() {
		return collection;
	}

	public void setCollection(List<Object> collection) {
		this.collection = collection;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
