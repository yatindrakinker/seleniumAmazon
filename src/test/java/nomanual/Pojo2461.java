package nomanual;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pojo2461 {
	private boolean success;
	private Data data;
	private List<String> variantTitle;

	public boolean isSuccess() {
		return success;
	}

	public Data getData() {
		return data;
	}

	public List<String> getVariantTitle() {
		return variantTitle;
	}

}

@JsonIgnoreProperties(ignoreUnknown = true)
class Data {
	private Mandatory mandatory;
	private Optional optional;

	public Mandatory getMandatory() {
		return mandatory;
	}

	public Optional getOptional() {
		return optional;
	}

}

@JsonIgnoreProperties(ignoreUnknown = true)
class Mandatory {
	private Item item_sku;
	private Item brand_name;
	private Item product_description;
	private Item item_name;
	private Item standard_price;
	private Item quantity;
	private Item main_image_url;
	private Item other_image_url1;
	private Item barcode_exemption;
	private Item category;
	private Item sub_category;

	public Item getItem_sku() {
		return item_sku;
	}

	public Item getBrand_name() {
		return brand_name;
	}

	public Item getProduct_description() {
		return product_description;
	}

	public Item getItem_name() {
		return item_name;
	}

	public Item getStandard_price() {
		return standard_price;
	}

	public Item getQuantity() {
		return quantity;
	}

	public Item getMain_image_url() {
		return main_image_url;
	}

	public Item getOther_image_url1() {
		return other_image_url1;
	}

	public Item getBarcode_exemption() {
		return barcode_exemption;
	}

	public Item getCategory() {
		return category;
	}

	public Item getSub_category() {
		return sub_category;
	}

}

@JsonIgnoreProperties(ignoreUnknown = true)
class Optional {
	private Item feed_product_type;
	private Item closure_type;
	private Item external_product_id;
	private Item manufacturer;
	private Item part_number;
	private Item recommended_browse_nodes;
	private Item model;
	private Item model_name;
	private Item care_instructions1;
	private Item bullet_point1;
	private Item pattern_name;
	private Item thread_count;
	private Item special_features1;
	private Item color_name;
	private Item color_map;
	private Item size_name;
	private Item included_components1;
	private Item item_type_name;
	private Item material_type1;
	private Item manufacturer_contact_information;
	private Item is_assembly_required;
	private Item number_of_boxes;
	private Item wattage_unit_of_measure;
	private Item included_features1;
	private Item wattage;
	private Item size_map;
	private Item unit_count;
	private Item item_dimensions_unit_of_measure;
	private Item unit_count_type;
	private Item item_width;
	private Item item_height;
	private Item item_length;
	private Item fabric_type1;
	private Item country_of_origin;
	private Item external_product_information;
	private Item condition_type;
	private Item maximum_retail_price;
	private Item number_of_items;
	private Item update_delete;
	private Item fuel_type;
	private Item fulfillment_centre_id;
	private Item fulfillment_latency;
	private Item external_product_id_type;

	public Item getFeed_product_type() {
		return feed_product_type;
	}

	public Item getClosure_type() {
		return closure_type;
	}

	public Item getExternal_product_id() {
		return external_product_id;
	}

	public Item getManufacturer() {
		return manufacturer;
	}

	public Item getPart_number() {
		return part_number;
	}

	public Item getRecommended_browse_nodes() {
		return recommended_browse_nodes;
	}

	public Item getModel() {
		return model;
	}

	public Item getModel_name() {
		return model_name;
	}

	public Item getCare_instructions1() {
		return care_instructions1;
	}

	public Item getBullet_point1() {
		return bullet_point1;
	}

	public Item getPattern_name() {
		return pattern_name;
	}

	public Item getThread_count() {
		return thread_count;
	}

	public Item getSpecial_features1() {
		return special_features1;
	}

	public Item getColor_name() {
		return color_name;
	}

	public Item getColor_map() {
		return color_map;
	}

	public Item getSize_name() {
		return size_name;
	}

	public Item getIncluded_components1() {
		return included_components1;
	}

	public Item getItem_type_name() {
		return item_type_name;
	}

	public Item getMaterial_type1() {
		return material_type1;
	}

	public Item getManufacturer_contact_information() {
		return manufacturer_contact_information;
	}

	public Item getIs_assembly_required() {
		return is_assembly_required;
	}

	public Item getNumber_of_boxes() {
		return number_of_boxes;
	}

	public Item getWattage_unit_of_measure() {
		return wattage_unit_of_measure;
	}

	public Item getIncluded_features1() {
		return included_features1;
	}

	public Item getWattage() {
		return wattage;
	}

	public Item getSize_map() {
		return size_map;
	}

	public Item getUnit_count() {
		return unit_count;
	}

	public Item getItem_dimensions_unit_of_measure() {
		return item_dimensions_unit_of_measure;
	}

	public Item getUnit_count_type() {
		return unit_count_type;
	}

	public Item getItem_width() {
		return item_width;
	}

	public Item getItem_height() {
		return item_height;
	}

	public Item getItem_length() {
		return item_length;
	}

	public Item getFabric_type1() {
		return fabric_type1;
	}

	public Item getCountry_of_origin() {
		return country_of_origin;
	}

	public Item getExternal_product_information() {
		return external_product_information;
	}

	public Item getCondition_type() {
		return condition_type;
	}

	public Item getMaximum_retail_price() {
		return maximum_retail_price;
	}

	public Item getNumber_of_items() {
		return number_of_items;
	}

	public Item getUpdate_delete() {
		return update_delete;
	}

	public Item getFuel_type() {
		return fuel_type;
	}

	public Item getFulfillment_centre_id() {
		return fulfillment_centre_id;
	}

	public Item getFulfillment_latency() {
		return fulfillment_latency;
	}

	public Item getExternal_product_id_type() {
		return external_product_id_type;
	}

}

@JsonIgnoreProperties(ignoreUnknown = true)
class Item {
	private String label;
	private Object value;

	public String getLabel() {
		return label;
	}

	public Object getValue() {
		return value;
	}

}
