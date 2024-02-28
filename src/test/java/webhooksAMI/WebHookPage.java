package webhooksAMI;

/**
 * Project Name: Amazon MultiAccount 
 * Author: Yatindra Kinker 
 * Version: 
 * Reviewed By: Abhay Hayaran 
 * Date of Creation: Feb 10, 2022
 *  Modification History:
 * Date of change: 
 * Version: 
 * changed function:  
 * change description:
 * Modified By:
 */

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.ami.pageobjects.CreateShopifyProduct;
import com.ami.pageobjects.ProductListingPage;
import com.ami.resources.Utilities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pojoCreateAMI.Data;
import pojoCreateAMI.DbData;
import pojoCreateAMI.Locations;
import pojoCreateAMI.Product;
import pojoShopify.ImagesShopifyPojo;
import pojoShopify.ProductDataShopifyPojo;
import pojoShopify.ProductShopifyPojo;
import pojoShopify.VariantsShopifyPojo;
import pojoUpdateAMI.ProductAMIPojo;
import pojoUpdateAMI.ProductUpdate;
import pojoUpdateAMI.ProductUpdateData;

public class WebHookPage {
	private String feedId = "";
	int shopifyImgSize = 0;
	public static int updateResponseSize = 0;
	public static int variantSizeShopify = 0;
	public static String mainImgUrl = "";
	DecimalFormat df = new DecimalFormat("0.00");
	String shopifyJsonValue = "";
	ObjectMapper shopifyMapper;
	ObjectMapper amazonMapper;
	private String falseVal = "false";
	private String trueVal = "true";
	public static final String RESERVEDINVENTORY = "50";
	public static final String FIXEDINVENTORY = "70";
	public static final String MAXINVENTORY = "999";
	public static ProductShopifyPojo productPojo = null;
	public static ProductDataShopifyPojo productDataShopifyPojo = null;

	public static Product product = null;
	public static Data dataCreatePojo = null;
	public static DbData dbDataCreatePojo = null;
	public static List<DbData> dbDataPjo = null;

	HashMap<String, String> skuFromFeed = new HashMap<>();
	ProductAMIPojo[] productPojoUpdate = null;
	public static List<ProductAMIPojo> productAMIPojo;

	Object shopifyVal = null;
	Object AMIVal = null;
	List<Object> AMIVariantVal = null;
	Utilities util;
	ProductListingPage plp;
	SoftAssert sAssert;

	/**
	 * constructor
	 * 
	 * @param util
	 */
	public WebHookPage(Utilities util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
		plp = new ProductListingPage(util);
		sAssert = new SoftAssert();
	}

	/**
	 * This method opens a new tab and open Shopify product data in json format.
	 * 
	 * @param productId
	 */
	public void getShopifyProductDataInJson(String productId) {
		util.getDriver().switchTo().newWindow(WindowType.TAB);
		util.getDriver().get(CreateShopifyProduct.productUrl + ".json");
		getShopifyJsonValue();
	}

	public String getShopifyJsonValue() {
		return util.getDriver().findElement(By.tagName("pre")).getText();
	}

	/**
	 * This method converts json data to java objects.
	 * 
	 * @return
	 */
	public void shopifyJsonToJavaObjects() {
		shopifyMapper = new ObjectMapper();
		shopifyJsonValue = getShopifyJsonValue();

		try {
			productPojo = shopifyMapper.readValue(shopifyJsonValue, ProductShopifyPojo.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		variantSizeShopify = productPojo.getProduct().getVariants().size();
	}

	/**
	 * This method converts json data to java objects.
	 * 
	 * @return
	 */
	public void amazonJsonToJavaObjects(String jsonValueAMI, String testName) {
		amazonMapper = new ObjectMapper();

		if (testName.equals("create")) {

			try {

				System.out.println("jsonValueAMI = " + jsonValueAMI);
				product = amazonMapper.readValue(jsonValueAMI, Product.class);
				dataCreatePojo = product.getData();
				if (product.isSuccess()) {
					dbDataPjo = dataCreatePojo.getDb_data();
//					mainImgUrl = productAMIPojo.get(0).getMain_image();
					mainImgUrl = dbDataPjo.get(0).getMain_image();
				} else {

				}

			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}

		} else if (testName.equals("update")) {

			try {
				System.out.println("jsonValueAMI = " + jsonValueAMI);
				ProductUpdate productUpdate = amazonMapper.readValue(jsonValueAMI, ProductUpdate.class);
				ProductUpdateData productUpdateData = productUpdate.getData();
				productAMIPojo = productUpdateData.getDb_data();
				mainImgUrl = productAMIPojo.get(0).getMain_image();
				System.out.println(productAMIPojo.get(0).get_id());

			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

		}
	}

	public String getMainImgSrc() {

		mainImgUrl = productPojoUpdate[0].getMain_image();
		return mainImgUrl;
	}

	public void getProductDeleteDraftArchieveResponse(String jsonValueAMI) {
		amazonMapper = new ObjectMapper();

		try {
			product = amazonMapper.readValue(jsonValueAMI, Product.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (product.getMessage().equals("something went wrong, product_listing or product_id key not found")) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

	}

	public void validateNewLocationIsDeleted(String jsonValueAMI) {
		amazonMapper = new ObjectMapper();

		try {
			product = amazonMapper.readValue(jsonValueAMI, Product.class);
			productPojoUpdate = amazonMapper.readValue(jsonValueAMI, ProductAMIPojo[].class);
		} catch (Exception e) {
			util.logWarn("exception occured");
		}

		List<Locations> allLocations = product.getData().getDb_data().get(0).getLocations();

		for (Locations location : allLocations) {
			System.err.println("active location: " + location.getLocation_id());

			if (!CreateShopifyProduct.locationId.equals(location.getLocation_id())) {
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		}

	}

	/**
	 * get shopify value
	 * 
	 * @param attributeName
	 */
	public void getShopifyValue(String attributeName) {
		shopifyVal = getValueShopify(attributeName);
	}

	/**
	 * get shopify value from variants object
	 * 
	 * @param attributeName
	 */
	public void getShopifyVariantValue(String attributeName, int i) {
		shopifyVal = getVariantsValueShopify(attributeName, i);
	}

	/**
	 * get shopify value from variants object
	 * 
	 * @param attributeName
	 */
	public void getShopifyImageValue(String attributeName) {
		shopifyVal = getImagesValueShopify(attributeName);
	}

	public int getShopifyImgNull() {
		shopifyImgSize = productPojo.getProduct().getImages().size();

		return shopifyImgSize;
	}

	public Object getAMIImgNull() {
		Object obj = null;

		try {
			obj = productPojoUpdate[0].getMain_image();
		} catch (NullPointerException e) {
			obj = "0";
		}

		return obj;

	}

	public void compareValuesWhenNoImgIsAssigned() {
		System.out.println("img shopify " + getShopifyImgNull());
		System.out.println("img ami " + getAMIImgNull());
//		if (getShopifyImgNull() == 0 && getAMIImgNull().equals("")) {
		if (getShopifyImgNull() == 0 && Integer.parseInt(getAMIImgNull().toString()) == 0) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

	}

	/**
	 * get amazon value
	 * 
	 * @param attributeName
	 */
	public void getAMIValue(String attributeName) {
		AMIVal = getValueAMICreate(attributeName);
	}

	/**
	 * get amazon value
	 * 
	 * @param attributeName
	 */
	public void getAMIValueUpdate(String attributeName) {
		AMIVal = getValueAMIUpdate(attributeName);
	}

	/**
	 * get amazon value from switch case
	 * 
	 * @param attributeName
	 * @param i
	 */
	public void getAMIVariantValueUpdate(String attributeName, int i) {
		AMIVal = getVariantValueAMIUpdate(attributeName, i);
	}

	/**
	 * This method compare and validates shopify and multiaccount product
	 */
	public void validateProductIsCreated(String test) {
		try {

			if (test.equals("positive")) {

				if (shopifyVal.toString().equals(AMIVal.toString())) {
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			} else if (test.equals("negative")) {
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

	}

	public void validateProductIsCreatedWhenSalesChannelIsInActive(String test, String jsonValueAMI) {
		amazonMapper = new ObjectMapper();

		try {
			product = amazonMapper.readValue(jsonValueAMI, Product.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (product.getMessage().equals("something went wrong, product_listing or product_id key not found")) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

	/**
	 * This method compares values fetched from amazon multichannel and shopify
	 */
	public void compareAttributeValues() {
		try {
			System.err.println("344 AMIVal = " + AMIVal);
			System.err.println("345 shopifyVal = " + shopifyVal.toString());

			if (AMIVal.equals("GRAMS")) {
				AMIVal = "g";

				if (shopifyVal.toString().equalsIgnoreCase(AMIVal.toString())) {
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}

			} else if (AMIVal.equals("POUNDS")) {
				AMIVal = "lb";

				if (shopifyVal.toString().equalsIgnoreCase(AMIVal.toString())) {
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}

			} else if (shopifyVal.toString().equalsIgnoreCase(AMIVal.toString())) {
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}

		} catch (NullPointerException e) {
			Assert.assertTrue(false);
		}

	}

	/**
	 * This method compares values fetched from amazon multichannel and shopify
	 */
	public void compareVariantAttributeValues() {
		try {

			for (int i = 0; i < AMIVariantVal.size(); i++) {

				if (AMIVariantVal.get(i).equals("GRAMS")) {
					AMIVal = AMIVariantVal.get(i);
					AMIVal = "g";

					if (shopifyVal.toString().equalsIgnoreCase(AMIVal.toString())) {
						Assert.assertTrue(true);
					} else {
						Assert.assertTrue(false);
					}

				} else if (AMIVariantVal.get(i).equals("POUNDS")) {
					AMIVal = AMIVariantVal.get(i);
					AMIVal = "lb";

					if (shopifyVal.toString().equalsIgnoreCase(AMIVariantVal.get(i).toString())) {
						Assert.assertTrue(true);
					} else {
						Assert.assertTrue(false);
					}

				} else if (shopifyVal.toString().equalsIgnoreCase(AMIVariantVal.get(i).toString())) {
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			}

		} catch (NullPointerException e) {
			System.err.println("null pointer exception occured.");
			Assert.assertTrue(false);
		}

	}

	/**
	 * extract value according to the key
	 * 
	 * @param attributeName
	 * @return
	 */
	public Object getValueShopify(String attributeName) {
		String caseName = attributeName;
		Object obj = null;
		productDataShopifyPojo = productPojo.getProduct();

		switch (caseName) {
		case "id":
			obj = productPojo.getProduct().getId();
			return obj;
		case "title":
			obj = productPojo.getProduct().getTitle();
			return obj;
		case "description":
			obj = productPojo.getProduct().getBody_html();
			return obj;
		case "status":
			obj = productPojo.getProduct().getStatus();
			return obj;
		case "vendor":
			obj = productPojo.getProduct().getVendor();
			return obj;
		case "productType":
			obj = productPojo.getProduct().getProduct_type();
			return obj;
		case "tags":
			obj = productPojo.getProduct().getTags();
			return obj;
		default:
			System.err.println("no case found with given attribute " + attributeName + " please enter valid case.");
		}
		return obj;
	}

	public Object getVariantsValueShopify(String attributeName, int i) {
		productDataShopifyPojo = productPojo.getProduct();
		List<VariantsShopifyPojo> variantsData = productDataShopifyPojo.getVariants();
		String caseName = attributeName;
		Object obj = null;

		switch (caseName) {
		case "price":
			obj = variantsData.get(i - 1).getPrice();
			return df.format(Double.parseDouble(obj.toString()));
		case "compare_at_price":
			obj = variantsData.get(i - 1).getCompare_at_price();
			return df.format(obj);
		case "sku":
			obj = variantsData.get(i - 1).getSku();
			return obj;
		case "barcode":
			obj = variantsData.get(i - 1).getBarcode();
			return obj;
		case "weight":
			obj = variantsData.get(i - 1).getWeight();
			return obj;
		case "weight_unit":
			obj = variantsData.get(i - 1).getWeight_unit();
			return obj;
		case "inventory_quantity":
			obj = variantsData.get(i - 1).getInventory_quantity();
			return obj;
		case "vendor":
			obj = productPojo.getProduct().getVendor();

			return obj;

		}

		return obj;

	}

	public Object getImagesValueShopify(String attributeName) {
		productDataShopifyPojo = productPojo.getProduct();
		List<ImagesShopifyPojo> imgShopifyPojo = productPojo.getProduct().getImages();
		String caseName = attributeName;
		Object obj = null;

		switch (caseName) {

		case "image_id":
			obj = imgShopifyPojo.get(0).getId();
			return obj;
		case "image_src":

			try {
				obj = imgShopifyPojo.get(0).getSrc();
			} catch (NullPointerException e) {
				Assert.assertTrue(true);
			}

			return obj;
		}

		return obj;
	}

	/**
	 * extract value according to the key
	 * 
	 * @param attributeName
	 * @return
	 */
	public Object getValueAMICreate(String attributeName) {
		String caseName = attributeName;
		Object obj = null;

		try {
			switch (caseName) {
			case "id":
				obj = dbDataPjo.get(0).getContainer_id();
				System.out.println(obj);
				return obj;
			case "title":
				obj = dbDataPjo.get(0).getTitle();
				return obj;
			case "description":
				obj = dbDataPjo.get(0).getDescription();
				return obj;
			case "barcode":
				obj = dbDataPjo.get(0).getBarcode();
				return obj;
			case "productType":
				obj = dbDataPjo.get(0).getProduct_type();
				return obj;
			case "vendor":
				obj = dbDataPjo.get(0).getBrand();
				return obj;
			case "price":
				obj = dbDataPjo.get(0).getPrice();
				return df.format(Double.parseDouble(obj.toString()));
			case "compare_at_price":
				obj = dbDataPjo.get(0).getCompare_at_price();
				return df.format(Double.parseDouble(obj.toString()));
			case "weight":
				obj = dbDataPjo.get(0).getWeight();
				return obj;
			case "weight_unit":
				obj = dbDataPjo.get(0).getWeight_unit();
				return obj;
			case "sku":
				obj = dbDataPjo.get(0).getSku();
				obj = StringUtils.substringBefore(String.valueOf(obj), "_");
				return obj;
			case "status":
				obj = dbDataPjo.get(0).getSource_status();
				return obj;
			case "inventory_quantity":
				obj = dbDataPjo.get(0).getQuantity();
				return obj;
			default:
				System.err.println("attribute not present in switch case");
				break;
			}

		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		return obj;
	}

	/**
	 * 
	 * @param attributeName
	 * @return
	 */
	public Object getValueAMIUpdate(String attributeName) {
		Object obj = null;

		switch (attributeName) {
		case "title":
			obj = productAMIPojo.get(0).getTitle();
			return obj;
		case "description":
			obj = productAMIPojo.get(0).getDescription();
			return obj;
		case "barcode":
			obj = productAMIPojo.get(0).getBarcode();
			return obj;
		case "productType":
			obj = productAMIPojo.get(0).getProduct_type();
			return obj;
		case "vendor":
			obj = productAMIPojo.get(0).getBrand();
			return obj;
		case "price":
			obj = productAMIPojo.get(0).getPrice();
			return df.format(Double.parseDouble(obj.toString()));
		case "compare_at_price":
			obj = productAMIPojo.get(0).getCompare_at_price();
			return df.format(Double.parseDouble(obj.toString()));
		case "weight":
			obj = productAMIPojo.get(0).getWeight();
			return obj;
		case "weight_unit":
			obj = productAMIPojo.get(0).getWeight_unit();
			return obj;
		case "sku":
			obj = productAMIPojo.get(0).getSku();
			return obj;
		case "status":
			obj = productAMIPojo.get(0).getSource_status();
			return obj;
		case "inventory_quantity":
			obj = productAMIPojo.get(0).getQuantity();
			return obj;
		case "tags":
			obj = productAMIPojo.get(0).getTags().get(0);
			return obj;
		case "image_src":
			obj = productAMIPojo.get(0).getMain_image();
			return obj;
		default:
			System.err.println("attribute not present in switch case");
			break;
		}

		return obj;

	}

	/**
	 * Get values from json response of amazon
	 * 
	 * @param attributeName
	 * @param i
	 * @return
	 */
	public Object getVariantValueAMIUpdate(String attributeName, int i) {
		Object obj = null;

		switch (attributeName) {
		case "title":
			obj = productPojoUpdate[i].getTitle();
			return obj;
		case "description":
			obj = productPojoUpdate[i].getDescription();
			return obj;
		case "barcode":
			obj = productPojoUpdate[i].getBarcode();
			return obj;
		case "productType":
			obj = productPojoUpdate[i].getProduct_type();
			return obj;
		case "vendor":
			obj = productPojoUpdate[i].getBrand();
			return obj;
		case "price":
			obj = productPojoUpdate[i].getPrice();
			return df.format(Double.parseDouble(obj.toString()));
		case "compare_at_price":
			obj = productPojoUpdate[i].getCompare_at_price();
			return df.format(Double.parseDouble(obj.toString()));
		case "weight":
			obj = productPojoUpdate[i].getWeight();
			return obj;
		case "weight_unit":
			obj = productPojoUpdate[i].getWeight_unit();
			return obj;
		case "sku":
			obj = productPojoUpdate[i].getSku();
			return obj;
		case "status":
			obj = productPojoUpdate[i].getSource_status();
			return obj;
		case "inventory_quantity":
			obj = productPojoUpdate[i].getQuantity();
			return obj;
		case "image_src":
			obj = productPojoUpdate[i].getMain_image();
			return obj;
		default:
			System.err.println("attribute not present in switch case");
			break;
		}

		return obj;

	}

	@FindBy(name = "PolarisChoiceList1[]")
	List<WebElement> locationList;

	/**
	 * This method validates the new location is added in the app or not.
	 */
	public void validateLocationIsAddedSuccessfully() {
		List<WebElement> newLocation = util.getDriver()
				.findElements(By.xpath("//input[@value = '" + CreateShopifyProduct.locationId + "']"));
		boolean locationAdded = false;

		if (newLocation.size() == 1) {
			locationAdded = true;
			Assert.assertTrue(locationAdded);
		} else {
			System.err.println("location are added: " + newLocation.size());
			Assert.assertTrue(locationAdded);
		}

	}

	@FindBy(xpath = "//input[@name = 'PolarisChoiceList1[]']/parent::span/parent::span/following-sibling::span/span")
	List<WebElement> locationNameList;

	/**
	 * This method validates the changes are reflected of not if made in shopify.
	 */
	public void updateLocationUpdatedSuccessfully(HashMap<String, String> input) {
		boolean locationUpdated = false;

		for (int i = 0; i < locationNameList.size(); i++) {
			System.out.println(util.getTagValue(locationNameList.get(i)));

			if (util.extractValueByAttributes(locationList.get(i), "value").equals(CreateShopifyProduct.locationId)
					&& (util.getTagValue(locationNameList.get(i)).contains(CreateShopifyProduct.updatedLocationName))) {
				System.err.println(util.extractValueByAttributes(locationList.get(i), "value")
						+ util.getTagValue(locationNameList.get(i)));
				locationUpdated = true;
				break;
			}
		}

		if (locationUpdated) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

	}

	/**
	 * This method validates the new location is deactivated in the app if
	 * deactivated from shopify.
	 */
	public void validateLocationIsDeactivatedSuccessfully() {
		boolean locationDeactivated = false;
		List<WebElement> newLocation = util.getDriver()
				.findElements(By.xpath("//input[@value = '" + CreateShopifyProduct.locationId + "']"));

		for (int i = 0; i < locationNameList.size(); i++) {

			try {
				if (util.extractValueByAttributes(locationList.get(i), "value").equals(CreateShopifyProduct.locationId)
						&& (util.extractValueByAttributes(locationList.get(i), "disabled").equals("true"))) {
					System.err.println(util.extractValueByAttributes(locationList.get(i), "value")
							+ util.extractValueByAttributes(locationList.get(i), "disabled"));
					locationDeactivated = true;
					break;
				}
			} catch (NullPointerException e) {
				util.logWarn("null pointer exception");
			}
		}

		if (locationDeactivated) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

	}

	/**
	 * This method validates the new location is removed in the app if deleted from
	 * shopify.
	 */
	public void validateLocationIsDeletedSuccessfully() {
		boolean locationDeleted = false;

		for (WebElement location : locationList) {

			if (util.extractValueByAttributes(location, "value").equals(CreateShopifyProduct.locationId)) {
				locationDeleted = true;
				break;
			}
		}

		if (!locationDeleted) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

	}

	@FindBy(xpath = "//span[@class = 'gridInProgressBadge']")
	List<WebElement> inprogressTagIsAcive;

	@FindBy(className = "gridErrorBadge")
	List<WebElement> errorTag;

	@FindBy(xpath = "//i/parent::span")
	List<WebElement> taskInProgress;

	@FindBy(xpath = "//i/parent::span")
	WebElement taskInProgressEle;

	@FindBy(xpath = "//input[@placeholder='Search with Title, Vendor, Product type or Barcode']")
	WebElement searchInputField;

	@FindBy(css = "div[class='Polaris-Combobox__Listbox'] li")
	List<WebElement> searchedProductList;

	@FindBy(className = "product_title")
	protected WebElement nameOfProduct;

	@FindBy(id = "PolarisSelect1")
	WebElement selectStore;

	@FindBy(xpath = "//span[normalize-space()='Feeds']")
	WebElement feeds;

	@FindBy(xpath = "//span[contains(text(),'Reset')]")
	WebElement resetBtn;

	@FindBy(css = ".ant-select-selector")
	WebElement attribute;

	@FindBy(xpath = "//li[@title='Feed Id']")
	WebElement feedID;

	@FindBy(xpath = "//li[@title='Status']")
	WebElement status;

	@FindBy(xpath = "//li[@title='Equals']")
	WebElement equals;

	@FindBy(xpath = "//li[@title='Done']")
	WebElement done;

	@FindBy(xpath = "//li[@title='Type']")
	WebElement type;

	@FindBy(xpath = "//li[@title='Product Inventory']")
	WebElement productInventory;

	@FindBy(xpath = "//tr[@class = 'ant-table-row ant-table-row-level-0']/td[7]/button")
	List<WebElement> viewBtnFeedsList;

	@FindBy(xpath = "//tr[@class = 'ant-table-row ant-table-row-level-0']/td[8]/button")
	List<WebElement> responseBtnFeedsList;

	@FindBy(xpath = "//tr//td[contains(@class , 'ant-table-cell')][2]")
	List<WebElement> feedIdList;

	@FindBy(xpath = "//tr/th[contains(text(),'SKU')]")
	WebElement skuFeed;

	@FindBy(xpath = "//tr/th[contains(text(),'Quantity')]")
	WebElement inventoryFeed;

	@FindBy(xpath = "//tr/th[contains(text(),'StatusCode')]")
	WebElement statusFeed;

	@FindBy(xpath = "//span[text() = 'Apply Filters']")
	WebElement applyFiltersBtn;

	@FindBy(xpath = "//tr[@class='ant-table-row ant-table-row-level-0']//span[@class = 'ant-checkbox']")
	WebElement checkbox;

	@FindBy(xpath = "//tr[contains(@class , 'ant-table-row ant-table-row-level-0')]/td[11]//button")
	WebElement kebabMenu;

	@FindBy(xpath = "//span[text() = 'Sync Inventory']")
	WebElement syncInventory;

	@FindBy(xpath = "//span[text() = 'Start']")
	WebElement startBtn;

	@FindBy(xpath = "//h1[text() = 'Listings']")
	WebElement listingPageHeading;

	public void checkInProgressTagIsInActiveAndCheckInventoryFeed() {
		int count = 0;
		util.goToSection("listings");
		util.enterValue(searchInputField, CreateShopifyProduct.nameOfProduct);
		util.hold(2);
		util.pressEscape();
		util.enterValue(searchInputField, CreateShopifyProduct.nameOfProduct);
		util.hold(2);
		util.waitUntilListIsVisible(searchedProductList, 30);
		util.hold(1);
		util.retryingFindClick(searchedProductList.get(0));
		util.hold(10);
		util.refreshPage();
		util.switchToIFrame();
		util.waitUntilElementIsVisible(kebabMenu, 20);

		if (!inprogressTagIsAcive.isEmpty()) {

			do {
				if (count == 31) {
					util.logError("inprogress tag is still active afer 30 minutes");
					Assert.assertTrue(false);
				}
				checkForInProgressTag();
				count++;
			} while (!inprogressTagIsAcive.isEmpty());

//			util.openAndSwitchToNewTab();
//
//			if (util.getProperty("store").equals("live")) {
//				util.getDriver().get(util.getProperty("adminPannelLive") + util.getProperty("token"));
//			} else {
//				util.getDriver().get(util.getProperty("adminPannelStagin") + util.getProperty("token"));
//			}
//
//			util.selectByIndex(selectStore, 1);
//			util.waitUntilElementIsVisible(feeds);
//			util.click(feeds);
//			selectProductInventory();
//			openFeed();

		}
	}

	@FindBy(css = "div[class='FilterSheet']")
	protected List<WebElement> moreFiltersModal;

	@FindBy(xpath = "//span[contains(text(),'More filters')]/ancestor::button")
	protected WebElement moreFiltersBtn;

	@FindBy(css = "div[class='FilterSheet__BodyAccordian']:nth-of-type(2)")
	protected WebElement skuToggleButton;

	@FindBy(css = "div[class='FilterSheet__BodyAccordian']:nth-of-type(2) div#basic-collapsible")
	protected WebElement skuCollapsible;
	

	@FindBy(css = "div[class='FilterSheet__BodyAccordian']:nth-of-type(2) select")
	protected WebElement selectSKU;

	@FindBy(css = "div[class='FilterSheet__BodyAccordian']:nth-of-type(2) input[placeholder = 'enter value']")
	protected WebElement skuInputField;

	@FindBy(css = "div[class='FilterSheet__footer'] button[class='Polaris-Button Polaris-Button--primary']")
	protected WebElement doneBtnMoreFiltersModal;
	
	
	public void searchBySkuOnListing(String sku) {
		util.goToSection("listings");
		util.waitUntilElementIsVisible(moreFiltersBtn, 30);

		if (!moreFiltersModal.get(0).isDisplayed()) {

			util.click(moreFiltersBtn);
		}

		util.waitUntilElementIsVisible(clearAllFilters, 10);
		util.click(clearAllFilters);
		util.hold(2);

		if (skuCollapsible.getAttribute("aria-hidden").equals(trueVal)) {
			util.click(skuToggleButton);
			util.hold(1);
		}

		util.selectByVisibleText(selectSKU, "Equals");
//		util.enterValue(skuInputField, sku);
//		util.enterUsingJS(skuInputField, sku);
//		util.click(doneBtnMoreFiltersModal);
//		util.hold(10);
		
		for (char c : sku.toCharArray()) {
			skuInputField.sendKeys(String.valueOf(c));
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		util.hold(5);
		util.click(doneBtnMoreFiltersModal);
	}
	
	
	public void waitForProductToImportOnApp() {
		int count = 0;
		
		if (!noProductsFound.isEmpty()) {

			do {
				if (count == 10) {
					util.logError("Product is not imported on app.");
					Assert.assertTrue(false);
				}
				checkForInProgressTag(noProductsFound);
				count++;
			} while (!inprogressTagIsAcive.isEmpty());

		}else {
			Assert.assertTrue(true);
		}
	}
	
	public void checkForInProgressTag(List<WebElement> eleList) {
		util.refreshPage();
		util.hold(60);
		util.switchToIFrame();

		if (!eleList.isEmpty()) {
			util.hold(10);

		}
	}

	public void checkInProgressTagIsInActive(String sku) {
		int count = 0;
		searchBySkuOnListing(sku);

		if (!inprogressTagIsAcive.isEmpty()) {

			do {
				if (count == 31) {
					util.logError("inprogress tag is still active afer 30 minutes");
					Assert.assertTrue(false);
				}
				checkForInProgressTag();
				count++;
			} while (!inprogressTagIsAcive.isEmpty());

		}
	}

	public void checkErrorTagIsActive() {
		boolean isTrue = false;
		util.goToSection("listings");
		util.enterValue(searchInputField, CreateShopifyProduct.nameOfProduct);
		util.hold(2);
		util.pressEscape();
		util.click(searchInputField);
		util.enterValue(searchInputField, CreateShopifyProduct.nameOfProduct);
		util.hold(2);

		for (WebElement product : searchedProductList) {
			if (product.getAttribute("data-listbox-option-value").equals(CreateShopifyProduct.containerIDOfProduct)) {
				util.retryingFindClick(product);
				util.hold(5);
				break;
			}
		}

		do {
			util.refreshPage();
			util.hold(5);
			util.switchToIFrame();
		} while (errorTag.isEmpty());

		if (!errorTag.isEmpty()) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);
	}

	@FindBy(xpath = "//span[text() = 'Edit Product']")
	WebElement editProduct;

	@FindBy(xpath = "//span[text() = 'Set the same Product Inventory for Shopify and Amazon']/parent::label/following-sibling::div//span/span")
	WebElement inventoryInEditProduct;

	@FindBy(xpath = "//span[text() = 'Inventory Breakup:']/following-sibling::div/div[2]/span")
	WebElement inventoryOnShopify;

	@FindBy(xpath = "//span[text() = 'Inventory Breakup:']/following-sibling::div[2]/div[2]/span")
	WebElement inventoryOnApp;

	private String shopifyInventoryOnEditProduct = "";
	private String appInventoryOnEditProduct = "";

	public void openProduct() {
		util.openAndSwitchToNewTab();
		util.goToMultiAccountListings();
		util.enterValue(searchInputField, CreateShopifyProduct.nameOfProduct);
		util.hold(2);
		util.pressEscape();
		util.click(searchInputField);
		util.click(searchedProductList.get(0));
		util.hold(10);
		util.click(kebabMenu);
		util.hold(1);
		util.click(editProduct);
		util.moveToElement(inventoryInEditProduct);
		shopifyInventoryOnEditProduct = inventoryOnShopify.getText();
		appInventoryOnEditProduct = inventoryOnApp.getText();
	}

	public String getShopifyInventoryInEditProduct() {
		return shopifyInventoryOnEditProduct;
	}

	public String getInventoryInEditProduct() {
		return appInventoryOnEditProduct;
	}

	public void checkForInProgressTag() {
		util.refreshPage();
		util.hold(60);
		util.switchToIFrame();

		if (!inprogressTagIsAcive.isEmpty()) {
			util.hold(10);

		}
	}

	public void selectProductInventory() {
		util.waitUntilElementIsVisible(resetBtn);
		util.click(resetBtn);
		util.click(attribute);
		util.click(status);
		util.click(equals);
		util.click(done);
		util.hold(2);
		util.click(attribute);
		util.click(type);
		util.click(equals);
		util.click(productInventory);
		util.click(applyFiltersBtn);
	}

	public void openFeed() {
		String skuData = "";
		for (int i = 0; i < 5; i++) {
			feedId = feedIdList.get(i).getText();
			util.click(viewBtnFeedsList.get(i));
			skuData = skuFeed.getText();
			skuData = StringUtils.substringAfter(skuData, "<SKU>");
			skuData = StringUtils.substringBefore(skuData, "</SKU>");
			skuFromFeed.put(feedId, skuData);
			util.hold(2);
			util.click(closeBtn);
			System.out.println(skuData);
		}
	}

	public void validateSkuIsPresent() {
		boolean isTrue = false;
		for (Map.Entry<String, String> e : skuFromFeed.entrySet()) {
			System.out.println(e.getKey() + "= " + e.getValue());
			if (e.getValue().equals(WebHookPage.sku)) {
				isTrue = true;
				break;
			}
		}
		Assert.assertTrue(isTrue);
	}

	public void validateSKU() {

		boolean isTrue = false;
		String skuData = skuFeed.getText();
		skuData = StringUtils.substringAfter(skuData, "<SKU>");
		System.out.println("skuData = " + skuData);
		skuData = StringUtils.substringBefore(skuData, "</SKU>");
		System.out.println("skuData = " + skuData);

		if (skuData.equals(WebHookPage.sku)) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);
	}

	@FindBy(xpath = "//div[@role='dialog']//button[@aria-label = 'Close']/span")
	WebElement closeBtn;

	public void validateInventory() {
		boolean isTrue = false;
		String inventoryData = inventoryFeed.getText();
		inventoryData = StringUtils.substringAfter(inventoryData, "<Quantity>");
		System.out.println("inventoryData = " + inventoryData);
		inventoryData = StringUtils.substringBefore(inventoryData, "</Quantity>");
		System.out.println("inventoryData = " + inventoryData);

		if (inventoryData.equals(CreateShopifyProduct.updatedInventory)) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);

		util.click(closeBtn);
	}

	public void validateInventoryWhenFixedInventoryIsEnable() {
		boolean isTrue = false;
		String inventoryData = inventoryFeed.getText();
		inventoryData = StringUtils.substringAfter(inventoryData, "<Quantity>");
		System.out.println("inventoryData = " + inventoryData);
		inventoryData = StringUtils.substringBefore(inventoryData, "</Quantity>");
		System.out.println("inventoryData = " + inventoryData);

		if (inventoryData.equals("100")) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);

		util.click(closeBtn);
	}

	public void validateInventoryWhenReservedInventoryIsEnable() {
		boolean isTrue = false;
		String inventoryData = inventoryFeed.getText();
		inventoryData = StringUtils.substringAfter(inventoryData, "<Quantity>");
		System.out.println("inventoryData = " + inventoryData);
		inventoryData = StringUtils.substringBefore(inventoryData, "</Quantity>");
		System.out.println("inventoryData = " + inventoryData);

		int shopifyInventory = Integer.parseInt(CreateShopifyProduct.updatedInventory);
		int calculatedInventory = 0;
		String expectedInventory = "";
		if (shopifyInventory <= 100) {
			expectedInventory = "0";
		} else {
			calculatedInventory = (Integer.parseInt(CreateShopifyProduct.updatedInventory) - 100);
			expectedInventory = String.valueOf(calculatedInventory);
		}

		if (inventoryData.equals(expectedInventory)) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);

		util.click(closeBtn);
	}

	public void validateFeedResponse() {
		util.click(responseBtnFeedsList.get(0));
		boolean isTrue = false;
		String statusData = statusFeed.getText();
		statusData = StringUtils.substringAfter(statusData, "<StatusCode>");
		System.out.println("statusData = " + statusData);
		statusData = StringUtils.substringBefore(statusData, "</StatusCode>");
		System.out.println("statusData = " + statusData);

		if (statusData.equals("Complete")) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);
	}

	@FindBy(id = "linking-required")
	WebElement linkingRequried;

	@FindBy(xpath = "//span[text() = 'Link']/parent::span/parent::button")
	List<WebElement> linkBtns;

	@FindBy(css = "button[id*='failedOrder_tour']")
	List<WebElement> linkBtnListLinkingReqTab;

	@FindBy(xpath = "//input[@placeholder='Search by Shopify Title or SKU']")
	WebElement searchProduct;

	@FindBy(xpath = "//a[contains(@href,'/admin/products/')]/preceding-sibling::p")
	protected List<WebElement> productNameListLinkModal;

	@FindBy(xpath = "//div[@role='dialog']//span[text() = 'Link']/parent::span/parent::button")
	WebElement linkBtnsLinkProductsModal;

	@FindBy(xpath = "//span[text() = 'Confirm']/parent::span/parent::button")
	WebElement confirmBtn;

	@FindBy(xpath = "(//tr[@class = 'ant-table-row ant-table-row-level-0']/td[3]/span)[1]")
	WebElement sellerCentralSku;

	@FindBy(className = "ant-empty-description")
	List<WebElement> noData;
	
	@FindBy(css = "img[src='/902e6225075f4dc2f9fe944f578a4ad2.png']")
	List<WebElement> noProductsFound;

	@FindBy(className = "custom-sort")
	WebElement customSort;

	@FindBy(xpath = "//button[@role='menuitem']//span[text()='Inactive']")
	WebElement inactiveStatus;

	@FindBy(xpath = "//input[@placeholder='Search by Amazon Title, Barcode, or SKU']")
	WebElement searchInpFieldLinking;

	@FindBy(id = "linking-required")
	WebElement linkingReqBtnLinking;

	@FindBy(css = "td[class='ant-table-cell']:nth-of-type(3)>p")
	List<WebElement> skuOnLinkingRequiredTab;

	public static String sku = "";

	public void linkNewlyCreatedProduct() {
		util.hold(1);
		util.click(linkedTab);
		util.waitUntilElementIsVisible(searchInpFieldLinking);
//		enter sku in search input field
		util.enterValue(searchInpFieldLinking, util.getProperty("sku", "sku1"));
		util.pressBackSpace();
		util.hold(2);
//		clear search input field
		util.selectAll(searchInpFieldLinking);
		util.pressBackSpace();
		util.hold(2);
//		enter sku in search input field
		util.enterValue(searchInpFieldLinking, util.getProperty("sku", "sku1"));
		util.hold(5);

//		if no data is displayed the switch to linking required tab
		if (!noData.isEmpty()) {
			util.click(linkingReqBtnLinking);
			util.hold(1);
			util.enterValue(searchInpFieldLinking, util.getProperty("sku", "sku1"));
			util.hold(5);
			util.pressBackSpace();
			util.hold(5);
			util.enterValue(searchInpFieldLinking, util.getProperty("sku", "sku1"));
			util.hold(5);

			for (int i = 0; i < skuOnLinkingRequiredTab.size(); i++) {
				if (skuOnLinkingRequiredTab.get(i).getText().equals(util.getProperty("sku", "sku1"))) {
					util.click(linkBtnListLinkingReqTab.get(i));
					break;
				}
			}
		} else {
//			if no data is not displayed then unlink the linked product
			util.click(unlinkBtn.get(0));
			util.hold(1);
			util.click(yesBtn);
			util.hold(5);
			util.click(linkingReqBtnLinking);
			util.enterValue(searchInpFieldLinking,util.getProperty("sku", "sku1"));
			util.hold(5);
			util.enterValue(searchInpFieldLinking, util.getProperty("sku", "sku1"));
			util.hold(5);
		}

		if (!linkBtns.isEmpty()) {

			for (int i = 0; i < skuOnLinkingRequiredTab.size(); i++) {
				if (skuOnLinkingRequiredTab.get(i).getText().equals(util.getProperty("sku", "sku1"))) {
					util.click(linkBtnListLinkingReqTab.get(i));
					break;
				}
			}

			util.waitUntilElementIsVisible(searchProduct, 30);
//			util.enterValue(searchProduct, CreateShopifyProduct.nameOfProduct);
//			util.hold(5);
//			util.enterValue(searchProduct, CreateShopifyProduct.nameOfProduct);
//			util.hold(5);
			
			waitUntilProductIsImported();

			util.waitUntilElementIsVisible(linkBtnsLinkProductsModal);
			util.click(linkBtnsLinkProductsModal);
			util.hold(5);
			util.jsClick(confirmBtn);
			util.hold(3);
		}
	}
	
	
	@FindBy(css = "div[class='ant-empty-description']")
	protected List<WebElement> noDataList;
	
	
	public void checkProductIsImportedOnAPP() {
		util.refreshPage();
		util.hold(60);
		util.switchToIFrame();

		if (!noDataList.isEmpty()) {
			util.hold(10);

		}
	}
	
	public void waitUntilProductIsImported() {
		int count = 0;
		util.enterValue(searchProduct, CreateShopifyProduct.nameOfProduct);
		util.hold(5);
		
		if (!noDataList.isEmpty()) {
			
			do {
				if (count == 11) {
					Assert.assertTrue(false);
				}
				checkProductIsImportedOnAPP();
				count++;
			} while (!noDataList.isEmpty());
			
		}
		
	}

	/**
	 * new method
	 */

	public void linkNewlyCreatedProduct(String fileName, String sku, String prodName) {
		util.hold(1);
		util.click(linkedTab);
		util.waitUntilElementIsVisible(searchInpFieldLinking);
//		enter sku in search input field
		util.enterValue(searchInpFieldLinking, util.getProperty(fileName, sku));
		util.hold(5);
//		clear search input field
		searchInpFieldLinking.clear();
		util.hold(2);
//		enter sku in search input field
		util.enterValue(searchInpFieldLinking, util.getProperty(fileName, sku));
		util.hold(5);

//		if no data is displayed the switch to linking required tab
		if (!noData.isEmpty()) {
			util.click(linkingReqBtnLinking);
			util.hold(1);
			util.enterValue(searchInpFieldLinking, util.getProperty(fileName, sku));
			util.hold(5);
			util.pressBackSpace();
			util.hold(5);
			util.enterValue(searchInpFieldLinking, util.getProperty(fileName, sku));
			util.hold(5);

			for (int i = 0; i < skuOnLinkingRequiredTab.size(); i++) {
				if (skuOnLinkingRequiredTab.get(i).getText().equals(sku)) {
					util.click(linkBtnListLinkingReqTab.get(i));
					break;
				}
			}
		} else {
//			if no data is not displayed then unlink the linked product
			util.click(unlinkBtn.get(0));
			util.hold(1);
			util.click(yesBtn);
			util.hold(5);
			util.click(linkingReqBtnLinking);
			util.enterValue(searchInpFieldLinking, util.getProperty(fileName, sku));
			util.hold(5);
			util.enterValue(searchInpFieldLinking, util.getProperty(fileName, sku));
			util.hold(5);
		}

		if (!linkBtns.isEmpty()) {

			for (int i = 0; i < skuOnLinkingRequiredTab.size(); i++) {
				if (skuOnLinkingRequiredTab.get(i).getText().equals(util.getProperty(fileName, sku))) {
					util.click(linkBtnListLinkingReqTab.get(i));
					break;
				}
			}

			util.waitUntilElementIsVisible(searchProduct, 30);
			util.enterValue(searchProduct, util.getProperty(fileName, prodName));
			util.hold(5);
			util.pressBackSpace();
			util.hold(5);
			util.enterValue(searchProduct, util.getProperty(fileName, prodName));
			util.hold(5);

			while (productNameListLinkModal.isEmpty()) {
				util.enterValue(searchProduct, util.getProperty(fileName, prodName));
				util.hold(5);
			}

			util.waitUntilElementIsVisible(linkBtnsLinkProductsModal);
			util.click(linkBtnsLinkProductsModal);
			util.hold(5);
			util.jsClick(confirmBtn);
			util.hold(3);
		}
	}

//	public void inprogressTagIsActive(String prodName) {
//		util.goToSection("listings");
//		plp.searchAndSelectProduct(prodName);
//		util.refreshPage();
//		util.switchToIFrame();
//		sAssert.assertTrue(!inprogressTagIsAcive.isEmpty());
//	}

	@FindBy(xpath = "//div[@class = 'FilterSheet__footer']//span[text()='Clear all filters']/parent::span/parent::button")
	protected WebElement clearAllFilters;

	/**
	 * new mehtod
	 * 
	 * @param prodName
	 */

	public void inprogressTagIsActive(String sku) {

		util.goToSection("listings");
		util.waitUntilElementIsVisible(moreFiltersBtn, 30);

//		previously working code
//		if (moreFiltersModal.isEmpty()) {
//
//			util.click(moreFiltersBtn);
//		}
		
		if (!moreFiltersModal.get(0).isDisplayed()) {

			util.click(moreFiltersBtn);
		}

		util.waitUntilElementIsVisible(clearAllFilters, 10);

		if (clearAllFilters.isEnabled()) {
			util.click(clearAllFilters);
		}

		if (skuCollapsible.getAttribute("aria-hidden").equals(trueVal)) {
			util.click(skuToggleButton);
			util.hold(1);
		}

		util.selectByVisibleText(selectSKU, "Equals");
		util.hold(1);
//		util.enterValue(skuInputField, sku);
//		skuInputField.sendKeys(sku);
		util.click(skuInputField);
//		util.enterUsingJS(skuInputField, sku);
		
		for (char c : sku.toCharArray()) {
			skuInputField.sendKeys(String.valueOf(c));
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		util.hold(5);
		util.click(doneBtnMoreFiltersModal);
		
		util.refreshPage();
		util.switchToIFrame();
		util.waitUntilElementIsVisible(nameOfProduct, 30);

		if (inprogressTagIsAcive.isEmpty()) {
			for (int i = 0; i < 5; i++) {
				util.refreshPage();
				util.switchToIFrame();
				util.waitUntilElementIsVisible(nameOfProduct, 30);
				util.hold(20);
			}
		}
	}

	public void waitUntilInprogressTagIsActive(String prodName) {
		int count = 0;
		util.goToSection("listings");
		plp.searchAndSelectProduct(prodName);
		util.refreshPage();
		util.switchToIFrame();

		while (inprogressTagIsAcive.isEmpty()) {

			if (count == 10) {
				break;
			} else {
				util.refreshPage();
				util.switchToIFrame();
				util.hold(25);
			}

			count++;

		}
		sAssert.assertTrue(!inprogressTagIsAcive.isEmpty());
	}

	@FindBy(id = "linked")
	WebElement linkedTab;

	@FindBy(xpath = "//input[@placeholder='Search by Title, Barcode or SKU used on Amazon']")
	WebElement inputField;

	@FindBy(xpath = "//td[contains(@class,'ant-table-cell')][2]//h3")
	List<WebElement> shopifyProductName;

	@FindBy(xpath = "//span[text() = 'Unlink']")
	List<WebElement> unlinkBtn;

	@FindBy(xpath = "//span[text() = 'Yes']")
	WebElement yesBtn;

	@FindBy(id = "nextURL")
	WebElement nexPagebtn;

	public void unlinkProduct() {

		if (util.getConfigProperty("store").equalsIgnoreCase("staging")) {
			util.getWindoHandleByUrl(util.getConfigProperty("stroreUrlStaging")
					+ "apps/amazon-by-cedcommerce-staging/panel/user/dashboard");
		} else {
			util.getWindoHandleByUrl(
					util.getConfigProperty("storeUrl") + "apps/amazon-sales-channel-1/panel/user/dashboard");
		}

		util.switchToIFrame();

		util.click(linkedTab);
		util.enterValue(inputField, WebHookPage.sku);
		util.hold(15);
		util.click(inputField);
		util.pressBackSpace();
		util.hold(15);
		util.waitUntilElementIsVisible(unlinkBtn.get(0));
		util.click(unlinkBtn.get(0));
		util.hold(1);
		util.click(yesBtn);

		try {
			util.getDriver().close();
			util.getWindoHandleByUrl(CreateShopifyProduct.productUrl);
		} catch (NoSuchWindowException e) {
			util.logWarn("NoSuchWindowException occured");
		}
	}

	@FindBy(xpath = "//span[text() = 'Delete product']")
	WebElement deleteProductBtn;

	@FindBy(xpath = "//div[@role='dialog']//span[text() = 'Delete product']")
	WebElement confirmDeleteProductBtn;

	public void deleteProduct() {
		util.getWindoHandleByUrl(CreateShopifyProduct.productUrl);
		util.hold(1);
		util.click(deleteProductBtn);
		util.hold(1);
		util.click(confirmDeleteProductBtn);
	}

	@FindBy(xpath = "//span[text() = 'Config']")
	WebElement config;

	@FindBy(id = "productsettings")
	WebElement productSettings;

	@FindBy(id = "inventory")
	WebElement inventorySettings;

	@FindBy(xpath = "//span[text() = 'Save']/parent::button")
	WebElement saveBtn;

	@FindBy(id = "inventory_Btn_Enable")
	WebElement enabledInventorySettingBtn;

	@FindBy(id = "inventory_Btn_Disable")
	WebElement disabledInventorySettingBtn;

	@FindBy(xpath = "//input[@placeholder='Ex: 10']")
	WebElement timeHandleInputField;

	public void enableInventorySettings() {
		util.goToSection("settings");
		util.hold(2);
		util.refreshPage();
		util.switchToIFrame();
		util.waitUntilElementIsVisible(productSettings, 30);
		util.click(productSettings);

		if (util.extractValueByAttributes(enabledInventorySettingBtn, "aria-pressed").equals(falseVal)) {
			util.click(enabledInventorySettingBtn);
			util.switchToDefaultContent();
			util.click(saveBtn);
			util.switchToIFrame();
		}
	}

	@FindBy(id = "inventory_Switch_FixedInventory")
	WebElement setFixedInventoryToggleBtn;

	@FindBy(id = "inventory_TextField_FixedInventory")
	WebElement setFixedInventoryInputField;

	@FindBy(id = "inventory_Switch_CustomInventory")
	WebElement setCustomInventoryToggleBtn;

	@FindBy(id = "inventory_TextField_customInventory")
	WebElement setCustomInventoryInputField;

	@FindBy(id = "inventory_Switch_ReservedInventory")
	WebElement setReservedInventoryToggleBtn;

	@FindBy(id = "inventory_TextField_ReservedInventory")
	WebElement setReservedInventoryInputField;

	@FindBy(id = "inventory_Switch_ThresholdInventory")
	WebElement thresoldInventoryToggleBtn;

	@FindBy(id = "inventory_TextField_ThresholdInventory")
	WebElement thresoldInventoryInputField;

	@FindBy(id = "inventory_Switch_OutStockAmazonProduct")
	WebElement deleteOutOfStockToggleBtn;

	public void enableFixedInventory() {
		String isCustomInventoryToggleBtnEnabled;
		String isFixedInventoryToggleBtnEnabled;
		String isReservedInventoryToggleBtnEnabled;
		String isThresoldInventoryToggleBtnEnabled;
		String isDeleteOutOfStockInventoryToggleBtnEnabled;


		isCustomInventoryToggleBtnEnabled = util.extractValueByAttributes(setCustomInventoryToggleBtn, "aria-checked");
		isFixedInventoryToggleBtnEnabled = util.extractValueByAttributes(setFixedInventoryToggleBtn, "aria-checked");
		isReservedInventoryToggleBtnEnabled = util.extractValueByAttributes(setReservedInventoryToggleBtn,
				"aria-checked");
		isThresoldInventoryToggleBtnEnabled = util.extractValueByAttributes(thresoldInventoryToggleBtn, "aria-checked");
		isDeleteOutOfStockInventoryToggleBtnEnabled = util.extractValueByAttributes(deleteOutOfStockToggleBtn,
				"aria-checked");

//		if set custom inventory and fixed inventory both are active disable fixed inventory so that if any value is assigned to fixed inventory it gets reset
		if (isCustomInventoryToggleBtnEnabled.equals(trueVal) && (isFixedInventoryToggleBtnEnabled.equals(trueVal))) {
			util.click(setFixedInventoryToggleBtn);
			util.switchToDefaultContent();
			util.click(saveBtn);
			util.switchToIFrame();
		}
		
		if (isCustomInventoryToggleBtnEnabled.equals(falseVal) && (isFixedInventoryToggleBtnEnabled.equals(trueVal))) {
			util.click(setFixedInventoryToggleBtn);
			util.switchToDefaultContent();
			util.click(saveBtn);
			util.switchToIFrame();
		}

		if (isCustomInventoryToggleBtnEnabled.equals(trueVal)) {
			util.click(setCustomInventoryToggleBtn);
		}

		if (isReservedInventoryToggleBtnEnabled.equals(trueVal)) {
			util.click(setReservedInventoryToggleBtn);
		}

		if (isThresoldInventoryToggleBtnEnabled.equals(trueVal)) {
			util.click(thresoldInventoryToggleBtn);
		}

		if (isDeleteOutOfStockInventoryToggleBtnEnabled.equals(trueVal)) {
			util.click(deleteOutOfStockToggleBtn);
		}

		isFixedInventoryToggleBtnEnabled = util.extractValueByAttributes(setFixedInventoryToggleBtn, "aria-checked");

//		enable fixed inventory
		if (isFixedInventoryToggleBtnEnabled.equals(falseVal)) {
			util.click(setFixedInventoryToggleBtn);
			util.enterValue(setFixedInventoryInputField, FIXEDINVENTORY);
		}

		util.switchToDefaultContent();
		util.click(saveBtn);
		util.switchToIFrame();

	}

	public void enableCustomInventory() {
		String isCustomInventoryToggleBtnEnabled;
		String isFixedInventoryToggleBtnEnabled;
		String isReservedInventoryToggleBtnEnabled;
		String isThresoldInventoryToggleBtnEnabled;
		String isDeleteOutOfStockInventoryToggleBtnEnabled;

		isCustomInventoryToggleBtnEnabled = util.extractValueByAttributes(setCustomInventoryToggleBtn, "aria-checked");
		isFixedInventoryToggleBtnEnabled = util.extractValueByAttributes(setFixedInventoryToggleBtn, "aria-checked");
		isReservedInventoryToggleBtnEnabled = util.extractValueByAttributes(setReservedInventoryToggleBtn,
				"aria-checked");
		isThresoldInventoryToggleBtnEnabled = util.extractValueByAttributes(thresoldInventoryToggleBtn, "aria-checked");
		isDeleteOutOfStockInventoryToggleBtnEnabled = util.extractValueByAttributes(deleteOutOfStockToggleBtn,
				"aria-checked");

		if (isThresoldInventoryToggleBtnEnabled.equals(trueVal)) {
			util.click(thresoldInventoryToggleBtn);
		}

		if (isDeleteOutOfStockInventoryToggleBtnEnabled.equals(trueVal)) {
			util.click(deleteOutOfStockToggleBtn);
		}

		if (isFixedInventoryToggleBtnEnabled.equals(trueVal)) {
			util.click(setFixedInventoryToggleBtn);
		}

		if (isReservedInventoryToggleBtnEnabled.equals(trueVal)) {
			util.click(setReservedInventoryToggleBtn);
		}

		if (isCustomInventoryToggleBtnEnabled.equals("true")) {
			util.click(setCustomInventoryToggleBtn);
			util.switchToDefaultContent();
			util.click(saveBtn);
			util.switchToIFrame();
			util.hold(5);
		}

		isCustomInventoryToggleBtnEnabled = util.extractValueByAttributes(setCustomInventoryToggleBtn, "aria-checked");

		if (isCustomInventoryToggleBtnEnabled.equals("false")) {
			util.click(setCustomInventoryToggleBtn);
			util.switchToDefaultContent();
			util.click(saveBtn);
			util.switchToIFrame();
		}
	}

	public void enableReservedInventory() {
		String isCustomInventoryToggleBtnEnabled;
		String isFixedInventoryToggleBtnEnabled;
		String isReservedInventoryToggleBtnEnabled;
		String isThresoldInventoryToggleBtnEnabled;
		String isDeleteOutOfStockInventoryToggleBtnEnabled;
//		if set custom inventory and fixed inventory both are active disable reserve inventory so that if any value is assigned to reserve inventory it gets reset

		isCustomInventoryToggleBtnEnabled = util.extractValueByAttributes(setCustomInventoryToggleBtn, "aria-checked");
		isFixedInventoryToggleBtnEnabled = util.extractValueByAttributes(setFixedInventoryToggleBtn, "aria-checked");
		isReservedInventoryToggleBtnEnabled = util.extractValueByAttributes(setReservedInventoryToggleBtn,
				"aria-checked");
		isThresoldInventoryToggleBtnEnabled = util.extractValueByAttributes(thresoldInventoryToggleBtn, "aria-checked");
		isDeleteOutOfStockInventoryToggleBtnEnabled = util.extractValueByAttributes(deleteOutOfStockToggleBtn,
				"aria-checked");

		if (isCustomInventoryToggleBtnEnabled.equals(trueVal)
				&& (isReservedInventoryToggleBtnEnabled.equals(trueVal))) {
			util.click(setReservedInventoryToggleBtn);
			util.switchToDefaultContent();
			util.click(saveBtn);
			util.switchToIFrame();
		}

		if (isCustomInventoryToggleBtnEnabled.equals(trueVal)) {
			util.click(setCustomInventoryToggleBtn);
		}

		if (isThresoldInventoryToggleBtnEnabled.equals(trueVal)) {
			util.click(thresoldInventoryToggleBtn);
		}

		if (isDeleteOutOfStockInventoryToggleBtnEnabled.equals(trueVal)) {
			util.click(deleteOutOfStockToggleBtn);
		}

		if (isFixedInventoryToggleBtnEnabled.equals(trueVal)) {
			util.click(setFixedInventoryToggleBtn);
		}

		isReservedInventoryToggleBtnEnabled = util.extractValueByAttributes(setReservedInventoryToggleBtn,
				"aria-checked");

//		enable reserve inventory
		if (isReservedInventoryToggleBtnEnabled.equals(falseVal)) {
			util.click(setReservedInventoryToggleBtn);
			util.enterValue(setReservedInventoryInputField, RESERVEDINVENTORY);
		}

		util.switchToDefaultContent();
		util.click(saveBtn);
		util.switchToIFrame();

	}

	public void disableMaximumInventorySetting() {

		if (util.extractValueByAttributes(setCustomInventoryToggleBtn, "aria-checked").equals(trueVal)) {
			util.click(setCustomInventoryToggleBtn);
			util.switchToDefaultContent();
			util.click(saveBtn);
			util.switchToIFrame();
			util.hold(3);
		}
	}

	public void disableSetReservedInventorySetting() {

		if (util.extractValueByAttributes(setReservedInventoryToggleBtn, "aria-checked").equals(trueVal)) {
			util.click(setReservedInventoryToggleBtn);
			util.switchToDefaultContent();
			util.click(saveBtn);
			util.switchToIFrame();
			util.hold(3);
		}
	}

	public void disableFixedInventorySetting() {

		if (util.extractValueByAttributes(setFixedInventoryToggleBtn, "aria-checked").equals(trueVal)) {
			util.click(setFixedInventoryToggleBtn);
			util.switchToDefaultContent();
			util.click(saveBtn);
			util.switchToIFrame();
			util.hold(3);
		}
	}

	public void disableInventorySettings() {
		util.goToSection("settings");
		util.hold(2);
		util.refreshPage();
		util.switchToIFrame();
		util.waitUntilElementIsVisible(productSettings, 30);
		util.waitUntilElementIsVisible(productSettings, 30);
		util.click(productSettings);

		if (util.extractValueByAttributes(disabledInventorySettingBtn, "aria-pressed").equals(falseVal)) {
			util.click(disabledInventorySettingBtn);
			util.switchToDefaultContent();
			util.click(saveBtn);
			util.switchToIFrame();
		}

	}

	public void enableMaximumInventorySetting() {
		if (util.extractValueByAttributes(setCustomInventoryToggleBtn, "aria-checked").equals(falseVal)) {
			util.click(setCustomInventoryToggleBtn);

		}
		util.enterValue(setCustomInventoryInputField, "500");
		util.switchToDefaultContent();
		util.click(saveBtn);
		util.switchToIFrame();
		util.hold(3);
	}

	public void disableAllSettingsExceptInventorySettings() {
		enableInventorySettings();
		disableSetReservedInventorySetting();
		disableFixedInventorySetting();
		disableMaximumInventorySetting();
	}

	@FindBy(css = "button[id*='inventory_Switch_Warehouse_']")
	List<WebElement> allWareHouseList;

	public void enableAllWareHouses() {

		for (WebElement wareHouse : allWareHouseList) {
			if (util.extractValueByAttributes(wareHouse, "disabled") == null) {
				if (util.extractValueByAttributes(wareHouse, "aria-checked").equals(falseVal)) {
					util.click(wareHouse);
					util.switchToDefaultContent();
					util.click(saveBtn);
					util.switchToIFrame();
				}
			}
		}
	}

	@FindBy(css = "tr[class='ant-table-row ant-table-row-level-0']>td:nth-of-type(3)>p")
	WebElement linkedSku;

	@FindBy(xpath = "(//img[@width='60px'])[2]/following-sibling::div/h3")
	WebElement shopifyProdName;

	public void productIsUnlinked() {
		boolean isTrue = false;
		util.refreshPage();
		util.switchToIFrame();
		util.waitUntilElementIsVisible(linkingRequried, 30);
		util.click(linkingRequried);
//		util.click(linkedTab);
		util.enterValue(searchInpFieldLinking, util.getConfigProperty("sku"));
		util.hold(5);
		util.enterValue(searchInpFieldLinking, util.getConfigProperty("sku"));
		util.hold(5);

		if (util.getConfigProperty("sku").equals(linkedSku.getText())) {
			isTrue = true;

		}

		Assert.assertTrue(isTrue);
	}

	@FindBy(xpath = "//div[contains(text(),'Showing')]/following-sibling::div[2]//span[text()='Edit']/parent::button")
	protected WebElement editVariantsAttributeBtn;

	@FindBy(xpath = "//span[normalize-space()='Edit quantities']")
	protected WebElement editQuantities;

	@FindBy(xpath = "//div[contains(@id,'EditQuantitiesModalgid')]")
	protected List<WebElement> wareHousesList;

	@FindBy(id = "EditQuantitiesModalApplyToAllField")
	protected WebElement editQuantitiesModalApplyToAllFieldInputField;

	@FindBy(id = "EditQuantitiesModalApplyToAllButton")
	protected WebElement editQuantitiesModalApplyToAllButton;

	@FindBy(xpath = "//div[@role = 'dialog']//span[text() = 'Save']/parent::button")
	WebElement saveBtnShopify;
	
	@FindBy(xpath = "//span[text()='Bulk edit']/ancestor::div[contains(@class,'BulkActions__BulkActionButton')]/following-sibling::div//button")
	protected WebElement moreActionsBulkEdit;
	
	@FindBy(xpath = "//span[text()='Continue selling when out of stock']/ancestor::button")
	protected WebElement continueSellingButtonVariants;
	

	public void updateVariantInventory(String inventory) {
		util.waitUntilElementIsVisible(moreActionsBulkEdit, 30);
		util.click(moreActionsBulkEdit);
		util.hold(1);
		util.click(editQuantities);
		util.hold(1);
		util.click(wareHousesList.get(0));
		util.waitUntilElementIsVisible(editQuantitiesModalApplyToAllFieldInputField);
		util.enterValue(editQuantitiesModalApplyToAllFieldInputField, inventory);
		util.click(editQuantitiesModalApplyToAllButton);
		util.click(saveBtnShopify);
		util.hold(30);
	}

}
