package com.ami.pageobjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ami.resources.ProductDetails;
import com.ami.resources.Utilities;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CreateShopifyProduct extends CreateShopifyProductOR {
	private final String ARIACHECKED = "aria-checked";
	private final String TRUEVAL = "true";
	private final String FALSEVAL = "false";

	public static String productUrl = "";
	public static String productName = "";
	public static String productId = "";
	public static String locationId = "";
	public static String locationUrl = "";
	public static String barCode = "";
	public static String sku = "";
	public static String productType = "";
	public static String vendorName = "";
	private String urlOfStore = "storeUrl";
	private String urlStaging = "stroreUrlStaging";
	private String newProduct = "products/new";
	Utilities util;
	ObjectMapper objMapper;

	public CreateShopifyProduct(Utilities util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
	}

	public void goToCreateNewProductPage(String server) {

		if (server.equals("live")) {
			util.getDriver().get(util.getConfigProperty(urlOfStore) + newProduct);
		} else {
			util.getDriver().get(util.getConfigProperty(urlStaging) + newProduct);
		}

		util.waitUntilElementIsVisible(titleInputField);
	}

	public void goToLocationsShopify() {
		util.getDriver().switchTo().newWindow(WindowType.TAB);
		util.getDriver().get(util.getConfigProperty(urlOfStore) + "settings/locations");
		util.waitUntilElementIsVisible(locationsHeading);
	}

	public static String locationName;

	public void addNewLocation(Map<String, String> input) {
		locationName = input.get("location_name") + util.randomNum();
		util.click(addLocationBtn);
		util.enterValue(locationNameInputField, locationName);
		util.enterValue(addressInputField, input.get("address"));
		util.enterValue(apartmentNameInputField, input.get("apartment"));
		util.enterValue(cityInputField, input.get("city"));
		util.selectByValue(selectCountry, "UP");
		util.enterValue(pinCode, input.get("pin_code"));
		util.enterValue(phoneInputField, input.get("phone"));
		util.click(saveBtn);
		util.hold(3);
		locationUrl = util.getDriver().getCurrentUrl();
		locationId = StringUtils.substringAfterLast(locationUrl, "/");
	}

	public static String updatedLocationName;

	public void updateLocation(Map<String, String> input) {
		updatedLocationName = "updated_" + locationName;
		util.getDriver().get(locationUrl);
		util.enterValue(locationNameInputField, updatedLocationName);
		util.click(saveBtn);
		util.hold(2);
	}

	public void deactivateLocation() {
		util.getDriver().get(locationUrl);
		util.click(deactivateLocationBtn);
		util.hold(2);
		util.click(confirmDeactivateLocationBtn);
	}

	public void deleteLocation() {
		util.getDriver().get(locationUrl);
		util.click(deleteLocationBtn);
		util.hold(2);
		util.click(confirmDeleteLocationBtn);
	}

	public void createNewProduct(Map<String, String> input, String status) {
		productUrl = "";
		productName = input.get("title_simple");
		util.enterValue(productTagsInputField, input.get("tags"));
		util.pressEnter();
		util.enterValue(titleInputField, productName);
		util.switchToIFrame(iframe);
		util.enterValue(productDescriptionBox, input.get("description_simple"));
		util.switchToDefaultContent();
		util.click(addFromUrl);
		util.hold(1);
		util.click(enterUrlInputField);
		util.enterValue(enterUrlInputField, input.get("image_url"));
		new WebDriverWait(util.getDriver(), Duration.ofSeconds(30))
				.until(ExpectedConditions.elementToBeClickable(addFileBtn));
		util.click(addFileBtn);
		util.hold(5);
		util.selectByValue(productStatus, status.toUpperCase());
		util.enterValue(vendorInputField, input.get("product_type"));
		util.enterValue(priceInputField, input.get("price"));
		util.enterValue(compareAtPriceInputField, input.get("compare_price"));
		util.moveToElement(openSKUCheckbox);
		util.scrollBy(0, 50);
		util.enableButton(openSKUCheckbox);
		util.enterValue(skuInpField, input.get("sku"));
		util.enterValue(barcodeInputField, input.get("barcode"));
		util.enterValue(locationsInpFieldList, input.get("inventory"));
		util.enterValue(weightInpField, input.get("weight"));
		util.selectByValue(selectWtUnit, input.get("weight_unit"));
		util.waitUntilElementIsVisible(saveBtn, 20);
		util.click(saveBtn);
		util.hold(2);
		util.waitUntilElementIsVisible(productCreatedMsg);

		if (productUrl.equals("")) {
			productUrl = util.getPageURL();
		}

		productId = StringUtils.substringAfterLast(productUrl, "/").trim();
	}

	public void createNewVariantProduct(Map<String, String> input, String status) {
		productName = input.get("title_variants");
		util.enterValue(productTagsInputField, input.get("tags"));
		util.pressEnter();
		util.enterValue(titleInputField, productName);
		util.switchToIFrame(iframe);
		util.enterValue(productDescriptionBox, input.get("description_variants"));
		util.switchToDefaultContent();
		util.click(addFromUrl);
		util.hold(1);
		util.click(enterUrlInputField);
		util.enterValue(enterUrlInputField, input.get("image_url"));
		new WebDriverWait(util.getDriver(), Duration.ofSeconds(30))
				.until(ExpectedConditions.elementToBeClickable(addFileBtn));
		util.click(addFileBtn);
		util.hold(5);
		util.selectByValue(productStatus, status.toUpperCase());
		util.enterValue(vendorInputField, input.get("product_type"));
		util.enterValue(priceInputField, input.get("price"));
		util.enterValue(compareAtPriceInputField, input.get("compare_price"));
		util.moveToElement(openSKUCheckbox);
		util.scrollBy(0, 50);
		util.enableButton(openSKUCheckbox);
		util.enterValue(skuInpField, input.get("sku1"));
		util.enterValue(barcodeInputField, input.get("barcode1"));
		util.enterValue(locationsInpFieldList, input.get("inventory"));
		util.enterValue(weightInpField, input.get("weight"));
		util.selectByValue(selectWtUnit, input.get("weight_unit"));
		util.click(addOptionsBtn);
		util.click(sizeOptionInputField);
		util.click(sizeOptionValue);
		util.click(sizeOptionValueInputField);
		util.enterValue(sizeOptionValueInputField, input.get("size_value1"));
		util.enterValue(addAnotherValueInputField, input.get("size_value2"));
		util.click(doneBtn);

		for (int i = 0; i < variantsList.size(); i++) {

			util.click(variantsList.get(i));
			util.hold(2);
			util.enterValue(variantSKUEditInputField, input.get("sku" + (i + 1)));
			util.hold(1);
			util.enterValue(variantBarcodeEditInputField, input.get("barcode" + (i + 1)));
			util.hold(1);
			util.click(doneBtnModal);
			util.hold(2);
		}

		util.click(saveBtn);
		util.hold(2);
		util.waitUntilElementIsVisible(productCreatedMsg);

		if (productUrl.equals("")) {
			productUrl = util.getPageURL();
		}

		productId = StringUtils.substringAfterLast(productUrl, "/").trim();

	}

	public String getProductId() {
		productId = StringUtils.substringAfterLast(productUrl, "/");

		return productId;

	}

	public void updateValue(Map<String, String> input, String attributeName, String key) {
		switch (attributeName) {

		case "title":
			util.enterValue(titleInputField, input.get(key));
			util.pressEnter();
			util.waitUntilElementIsVisible(productSaveMsg);
			break;
		case "description":
			util.switchToIFrame(iframe);
			util.enterValue(productDescriptionBox, input.get(key));
			util.switchToDefaultContent();
			util.click(saveBtn);
			util.hold(7);
			break;
		case "image_src":
			util.actionClick(imgCheckBox);
			util.hold(1);
			util.click(deleteImgBtn);
			util.waitUntilElementIsVisible(confirmDeleteImgBtn);
			util.click(confirmDeleteImgBtn);
			util.waitUntilElementIsVisible(fileDeletedMsg);
			util.click(addFromUrl);
			util.hold(1);
			util.click(enterUrlInputField);
			util.enterValue(enterUrlInputField, input.get("image_url"));
			new WebDriverWait(util.getDriver(), Duration.ofSeconds(30))
					.until(ExpectedConditions.elementToBeClickable(addFileBtn));
			util.click(addFileBtn);
			util.hold(5);
			break;
		case "price":
			util.enterValue(priceInputField, input.get(key));
			util.pressEnter();
			util.waitUntilElementIsVisible(productSaveMsg);
			break;
		case "compareAtPrice":
			util.enterValue(compareAtPriceInputField, input.get(key));
			util.pressEnter();
			util.waitUntilElementIsVisible(productSaveMsg);
			break;
		case "sku":
			util.enterValue(skuInpField, input.get(key));
			util.pressEnter();
			util.waitUntilElementIsVisible(productSaveMsg);
			break;
		case "barcode":
			util.enterValue(barcodeInputField, input.get(key));
			util.pressEnter();
			util.waitUntilElementIsVisible(productSaveMsg);
			break;
		case "inventory":
			util.moveToElement(totalHeading);
			util.hold(1);
			util.click(availableInventoryBtn);
			util.enterValue(newInventoryInputField, input.get(key));
			util.click(confirmSaveBtnToUpdateInventory);
			util.waitUntilElementIsVisible(qtyUpdatedMsg);
			break;
		case "weight":
			util.enterValue(weightInpField, input.get(key));
			util.pressEnter();
			util.waitUntilElementIsVisible(productSaveMsg);
			break;
		case "weight_unit":
			util.moveToElement(weightInpField);
			util.selectByValue(selectWtUnit, input.get(key));
			util.click(saveBtn);
			util.waitUntilElementIsVisible(productSaveMsg);
			break;
		case "productType":
			util.enterValue(productTypeInputField, input.get(key));
			util.click(saveBtn);
			util.waitUntilElementIsVisible(productSaveMsg);
			break;
		case "vendor":
			util.enterValue(vendorInputField, input.get(key));
			util.click(saveBtn);
			util.waitUntilElementIsVisible(productSaveMsg);
			break;
		case "tags":

			if ((removeTagsList.size() > 1) || removeTagsList.size() == 1) {
				util.click(removeTagsList.get(0));
			}

			util.enterValue(productTagsInputField, input.get(key));
			util.pressEnter();
			util.hold(2);
			util.click(saveBtn);
			util.waitUntilElementIsVisible(productSaveMsg);
			break;

		default:
			break;
		}

	}

	public void updateVariantsValue(Map<String, String> input, String attributeName, String key) {
		switch (attributeName) {
		case "price":
			util.click(editVariantsAttributeBtn);
			util.hold(1);
			util.click(editPrices);
			util.enterValue(editPricesInputFieldForAll, input.get(key));
			util.hold(1);
			util.click(editModalApplyToAllBtn);
			util.hold(1);
			util.click(doneBtnModal);
			util.hold(1);
			util.click(saveBtn);
			break;
		case "weight":
			util.click(editVariantsAttributeBtn);
			util.hold(1);
			util.click(editWeight);
			util.enterValue(editWeightModalBulkCurrencyInputField, input.get(key));
			util.hold(1);
			util.click(editModalApplyToAllBtn);
			util.hold(1);
			util.click(doneBtnModal);
			break;
		case "weight_unit":
			util.click(editVariantsAttributeBtn);
			util.hold(1);
			util.click(editWeight);
			util.selectByValue(editWeightUnitModalBulkCurrencySelect, input.get(key));
			util.hold(1);
			util.click(editModalApplyToAllBtn);
			util.hold(1);
			util.click(doneBtnModal);
			util.hold(1);
			util.click(saveBtn);
			break;
		case "quantity":
			util.click(editVariantsAttributeBtn);
			util.hold(1);
			util.click(editQuantities);
			util.hold(1);
			util.click(wareHousesList.get(0));
			util.waitUntilElementIsVisible(editQuantitiesModalApplyToAllFieldInputField);
			util.enterValue(editQuantitiesModalApplyToAllFieldInputField, input.get(key));
			util.click(editQuantitiesModalApplyToAllButton);
			util.click(saveBtnModal);
			break;
		case "sku":
			util.click(editVariantsAttributeBtn);
			util.hold(1);
			util.click(editSKUs);
			util.hold(1);

			for (int i = 0; i < skuInputFieldList.size(); i++) {
				int j = 0;
				j = i + 1;
				util.enterValue(skuInputFieldList.get(i), input.get(key + j));
			}

			util.click(doneBtnModal);
			util.hold(1);
			util.click(saveBtn);
			util.hold(5);
			break;
		case "barcode":
			util.click(editVariantsAttributeBtn);
			util.hold(1);
			util.click(editBarcodes);
			util.hold(1);

			for (int i = 0; i < skuInputFieldList.size(); i++) {
				int j = 0;
				j = i + 1;

				util.enterValue(skuInputFieldList.get(i), input.get(key + j));
			}

			util.click(doneBtnModal);
			util.hold(1);
			util.click(saveBtn);
			util.hold(5);
			break;

		default:
			break;
		}
	}

	public void deleteShopifyProduct() {
		util.getDriver().get(productUrl);
		util.waitUntilElementIsVisible(titleInputField);
		util.click(deleteProductBtn);
		util.waitUntilElementIsVisible(confirmDeleteProductBtn);
		util.click(confirmDeleteProductBtn);
		util.hold(5);
	}

	public void deleteVariant() {
		util.click(lastVariant);
		util.click(moreActionsBtn);
		util.click(deleteVariantBtn);
//		util.click(confirmDeleteVariantBtn);
		util.click(saveBtn);
		util.click(saveProductBtn);
		util.hold(5);
	}

	public void draftShopifyProduct() {
		util.selectByVisibleText(statusDropDown, "Draft");
		util.click(saveBtn);
		util.waitUntilElementIsVisible(productSaveMsg, 30);
	}

	public void addImage(Map<String, String> input) {
		util.click(addFromUrl);
		util.hold(1);
		util.click(enterUrlInputField);
		util.enterValue(enterUrlInputField, input.get("google_img"));
		new WebDriverWait(util.getDriver(), Duration.ofSeconds(30))
				.until(ExpectedConditions.elementToBeClickable(addFileBtn));
		util.click(addFileBtn);
		util.hold(5);
	}

	public void changeImgByDragging() {
		util.dragAndDrop(imgList.get(1), imgList.get(0));
	}

	public void deleteAllImg() {
		for (WebElement imgCheckBox : imgCheckBoxList) {
			util.actionClick(imgCheckBox);
			util.hold(1);
		}

		util.click(deleteImgBtn);
		util.hold(2);
		util.click(confirmDeleteImgBtn);
	}

	public void archieveProduct() {
		util.click(achieveProductBtn);
		util.hold(1);
		util.click(confirmArchieveProductBtn);
		util.hold(4);
	}

	public void makeSalesChannelInactive() {
		util.click(manageBtn);
		util.hold(1);
		util.click(manageSalesChannelAndAppBtn);
		util.hold(1);
		util.click(deSelectAllBtn);
		util.hold(1);
		util.click(doneBtnModal);
		util.hold(2);
		util.click(saveBtn);
		util.hold(3);
	}

	public void checkContinueSellingCheck() {

		if (util.extractValueByAttributes(continueSellingCheckBox, ARIACHECKED).equals(FALSEVAL)) {
			util.click(continueSellingCheckBox);
			util.click(saveBtn);
			util.hold(5);
		}

	}

	public void createNewProduct() {
		productUrl = "";
		String random = util.randomString(4);
		productName = util.randomString(10);
		barCode = util.randomString(10);
		sku = util.randomString(10);
		util.enterValue(productTagsInputField, random);
		util.pressEnter();
		util.enterValue(titleInputField, productName);
		util.switchToIFrame(iframe);
		util.enterValue(productDescriptionBox, random);
		util.switchToDefaultContent();
		util.click(vendorInputField);
		util.enterValue(vendorInputField, random);
		util.enterValue(priceInputField, "100");
		util.enterValue(compareAtPriceInputField, "150");
		util.moveToElement(openSKUCheckbox);
		util.scrollBy(0, 50);
		util.enableButton(openSKUCheckbox);
		util.enterValue(skuInpField, sku);
		util.enterValue(barcodeInputField, barCode);
		util.enterValue(locationsInpFieldList, "50");
		util.enterValue(weightInpField, "150");
		util.selectByValue(selectWtUnit, "GRAMS");
		util.click(addOptionsBtn);
		util.click(sizeOptionInputField);
		util.click(sizeOptionValue);
		util.click(sizeOptionValueInputField);
		util.enterValue(sizeOptionValueInputField, "L");
		util.enterValue(addAnotherValueInputField, "M");
		util.click(doneBtn);
		util.click(saveBtn);
		util.hold(2);
		util.waitUntilElementIsVisible(productCreatedMsg);

		if (productUrl.equals("")) {
			productUrl = util.getPageURL();
		}

		productId = StringUtils.substringAfterLast(productUrl, "/").trim();
	}

	public static String nameOfProduct = "";
	public static String descriptionOfProduct = "";
	public static String priceOfProduct = "100";
	public static String skuOfProudct = "";
	public static String barcodeOfProduct = "";
	public static String iventoryOfProduct = "";
	public static String containerIDOfProduct = "";

	public void storeProductValues(String key, String val) {
		Map<String, String> productDetails = new HashMap<>();
		productDetails.put(key, val);
	}

	public void createNewShopifyProduct() {
		productUrl = "";
		nameOfProduct = "ykTestwebhook" + util.randString(4);
		skuOfProudct = "ykTestwebhookSKU" + util.randString(4);
		barcodeOfProduct = "ykTestwebhookbc" + util.randString(4);
		iventoryOfProduct = "100";

		String random = util.randString(4);

		if (util.getConfigProperty("store").equals("staging")) {
			util.openUrl(util.getConfigProperty(urlStaging) + newProduct);
		} else {
			util.openUrl(util.getConfigProperty(urlOfStore) + newProduct);
		}

		util.enterValue(productTagsInputField, random);
		util.pressEnter();
		util.enterValue(productTypeInputField, "Yatindra");
		util.enterValue(titleInputField, nameOfProduct);
		util.enterValue(priceInputField, priceOfProduct);
//		util.enableButton(openSKUCheckbox);
		util.enterValue(skuInpField, skuOfProudct);
		util.enterValue(barcodeInputField, barcodeOfProduct);
		util.enterValue(locationsInpFieldList, iventoryOfProduct);
		util.enterValue(weightInpField, "150");
		util.selectByValue(selectWtUnit, "GRAMS");
		util.click(saveBtn);
		util.hold(2);
		util.waitUntilElementIsVisible(productCreatedMsg);
		productUrl = util.getPageURL();

		containerIDOfProduct = StringUtils.substringAfterLast(productUrl, "/").trim();
		storeProductValues(containerIDOfProduct + ".name", nameOfProduct);
		storeProductValues(containerIDOfProduct + ".price", priceOfProduct);
		storeProductValues(containerIDOfProduct + ".sku", skuOfProudct);
		storeProductValues(containerIDOfProduct + ".barcode", barcodeOfProduct);
		storeProductValues(containerIDOfProduct + ".inventory", iventoryOfProduct);
		storeProductValues(containerIDOfProduct + ".productUrl", productUrl);
	}

	public void createNewVariantShopifyProduct() {

		productUrl = "";
		nameOfProduct = "ykTestwebhook" + util.randString(4);
		skuOfProudct = "ykTestwebhookSKU" + util.randString(4);
		barcodeOfProduct = "ykTestwebhookbc" + util.randString(4);
		iventoryOfProduct = "100";

		String random = util.randString(4);

		if (util.getConfigProperty("store").equals("staging")) {
			util.openUrl(util.getConfigProperty(urlStaging) + newProduct);
		} else {
			util.openUrl(util.getConfigProperty(urlOfStore) + newProduct);
		}

		util.enterValue(productTagsInputField, random);
		util.pressEnter();
		util.enterValue(titleInputField, nameOfProduct);
		util.enterValue(priceInputField, priceOfProduct);
		util.enableButton(openSKUCheckbox);
		util.enterValue(skuInpField, skuOfProudct);
		util.enterValue(barcodeInputField, barcodeOfProduct);
		util.enterValue(locationsInpFieldList, iventoryOfProduct);
		util.enterValue(weightInpField, "150");
		util.selectByValue(selectWtUnit, "GRAMS");
		util.click(addOptionsBtn);
		util.click(sizeOptionInputField);
		util.click(sizeOptionValue);
		util.click(sizeOptionValueInputField);
		util.enterValue(sizeOptionValueInputField, "l");
		util.enterValue(addAnotherValueInputField, "m");
		util.click(doneBtn);
		util.click(saveBtn);
		util.hold(2);
		util.waitUntilElementIsVisible(productCreatedMsg);
		productUrl = util.getPageURL();

		containerIDOfProduct = StringUtils.substringAfterLast(productUrl, "/").trim();
		storeProductValues(containerIDOfProduct + ".name", nameOfProduct);
		storeProductValues(containerIDOfProduct + ".price", priceOfProduct);
		storeProductValues(containerIDOfProduct + ".sku", skuOfProudct);
		storeProductValues(containerIDOfProduct + ".barcode", barcodeOfProduct);
		storeProductValues(containerIDOfProduct + ".inventory", iventoryOfProduct);
		storeProductValues(containerIDOfProduct + ".productUrl", productUrl);
	}

	public static String updatedInventory = "";

	public void updateInventory() {
		util.scrollToBottom();
		util.hold(1);
		util.click(availableInventoryBtn);
		util.hold(2);
		String currentInventory = util.extractValueByAttributes(newInventoryInputField, "value");
		int inventory = Integer.parseInt(currentInventory);

		inventory += 10;

		currentInventory = String.valueOf(inventory);
		updatedInventory = currentInventory;

		util.enterValue(newInventoryInputField, updatedInventory);
		util.click(confirmSaveBtnToUpdateInventory);
		util.waitUntilElementIsVisible(qtyUpdatedMsg, 30);
	}

	/**
	 * new method
	 */
	public void updateShopifyInventory() {
		updatedInventory = "150";
		util.scrollToBottom();
		util.hold(1);

		if (availableInventoryBtn.getAttribute("aria-expanded").equalsIgnoreCase(FALSEVAL)) {
			util.click(availableInventoryBtn);
			util.hold(1);
		}

		if (!newInventoryInputFieldList.isEmpty()) {
			util.enterValue(newInventoryInputField, updatedInventory);
			util.click(confirmSaveBtnToUpdateInventory);
			util.waitUntilElementIsVisible(qtyUpdatedMsg, 30);
		} else {
			if (availableInventoryBtn.getAttribute("aria-expanded").equalsIgnoreCase(FALSEVAL)) {
				util.click(availableInventoryBtn);
				util.hold(1);
				util.enterValue(newInventoryInputField, updatedInventory);
				util.click(confirmSaveBtnToUpdateInventory);
				util.waitUntilElementIsVisible(qtyUpdatedMsg, 30);
			}
		}

	}

	public static String inventoryInSecondWareHouse;

	public void updateWareHouseInventory() {

		if (availableInventoryBtnList.size() > 1) {

			for (int i = 0; i <= 1; i++) {
				util.click(availableInventoryBtnList.get(i));
				util.hold(1);
				String currentInventory = util.extractValueByAttributes(newInventoryInputField, "value");
				int inventory = Integer.parseInt(currentInventory);

				inventory += 10;

				currentInventory = String.valueOf(inventory);
				if (i == 0) {
					updatedInventory = currentInventory;
				} else if (i == 1) {
					inventoryInSecondWareHouse = currentInventory;
				}

				util.enterValue(newInventoryInputField, currentInventory);
				util.click(confirmSaveBtnToUpdateInventory);
				util.waitUntilElementIsVisible(qtyUpdatedMsg);
			}
		}
	}

	private ArrayList<String> wareHouse;

	public void getWareHouseName() {
		wareHouse = new ArrayList<>();
		if (wareHouseNameList.size() > 1) {
			for (int i = 0; i <= 1; i++) {
				System.out.println(wareHouseNameList.get(i).getText());
				wareHouse.add(wareHouseNameList.get(i).getText());
				System.out.println(wareHouse.get(i));
			}
		}
	}

	public List<String> returnWareHouseName() {
		return wareHouse;
	}

	public void enableContinueSelling() {
		util.waitUntilElementIsVisible(continueSellingCheckBox, 30);
		util.hold(5);
		if (util.extractValueByAttributes(continueSellingCheckBox, ARIACHECKED).equals(FALSEVAL)) {
			util.jsClick(continueSellingCheckBoxLabel);
			util.hold(2);
			util.click(saveBtn);
		}
		util.hold(20);
	}

	public void enableContinueSellingForVariants() {
		util.scrollToBottom();
		util.waitUntilElementIsVisible(selectAllVariantsCheckBox, 30);
		
		if (util.extractValueByAttributes(selectAllVariantsCheckBox, ARIACHECKED).equals(FALSEVAL)) {
			util.click(selectAllVariantsCheckBox);
			util.hold(2);
		}

		if (util.extractValueByAttributes(moreActionsBulkEdit, "aria-expanded").equals(FALSEVAL)) {
			util.click(moreActionsBulkEdit);
		}
		
		util.waitUntilElementIsVisible(continueSellingButtonVariants, 30);
		util.click(continueSellingButtonVariants);
		util.hold(5);
		
		util.refreshPage();
		util.waitUntilElementIsVisible(selectAllVariantsCheckBox, 30);
		util.hold(30);
	}

	public void updateVariantInventory(String inventory) {
		util.scrollToBottom();
		util.waitUntilElementIsVisible(selectAllVariantsCheckBox, 30);
		
		if (util.extractValueByAttributes(selectAllVariantsCheckBox, ARIACHECKED).equals(FALSEVAL)) {
			util.click(selectAllVariantsCheckBox);
			util.hold(2);
		}
		
		util.waitUntilElementIsVisible(moreActionsBulkEdit, 30);
		util.click(moreActionsBulkEdit);
		util.waitUntilElementIsVisible(editQuantities, 30);
		util.click(editQuantities);
		util.waitUntilElementIsVisible(wareHousesList.get(0), 30);
		util.click(wareHousesList.get(0));
		util.waitUntilElementIsVisible(editQuantitiesModalApplyToAllFieldInputField, 30);
		util.enterValue(editQuantitiesModalApplyToAllFieldInputField, inventory);
//		util.waitUntilElementIsVisible(editQuantitiesModalApplyToAllButton, 30);
//		util.click(editQuantitiesModalApplyToAllButton);
		util.click(saveBtnShopify);
		util.hold(5);

		util.refreshPage();
		util.waitUntilElementIsVisible(selectAllVariantsCheckBox, 30);
		util.hold(30);
	}

	public void disableContinueSelling() {
		util.waitUntilElementIsVisible(continueSellingCheckBox, 30);
		util.hold(5);
		if (util.extractValueByAttributes(continueSellingCheckBox, ARIACHECKED).equals("true")) {
			util.jsClick(continueSellingCheckBoxLabel);
			util.hold(2);
			util.click(saveBtn);
		}
	}

	public void makeSimpleProductToVariants() {
		util.click(addOptionsBtn);
		util.click(sizeOptionInputField);
		util.click(sizeOptionValue);
		util.click(sizeOptionValueInputField);
		util.enterValue(sizeOptionValueInputField, "l");
		util.enterValue(addAnotherValueInputField, "m");
		util.click(doneBtn);
		util.click(saveBtn);
	}

	public void addInventoryInNewLocation() {
		util.hold(30);
		util.click(editVariantsAttributeBtn);
		util.hold(1);
		util.click(editQuantities);
		util.hold(1);

		for (WebElement ware : wareHousesList) {
			if (ware.getAttribute("id").contains(locationId)) {
				util.click(ware);
				break;
			}
		}

		util.waitUntilElementIsVisible(editQuantitiesModalApplyToAllFieldInputField);
		util.enterValue(editQuantitiesModalApplyToAllFieldInputField, "5");
		util.click(editQuantitiesModalApplyToAllButton);
		util.click(saveBtnModal);
	}

	public void addInventory() {
		util.click(editVariantsAttributeBtn);
		util.hold(1);
		util.click(editQuantities);
		util.hold(1);
		util.click(wareHousesList.get(0));
		util.waitUntilElementIsVisible(editQuantitiesModalApplyToAllFieldInputField);
		util.enterValue(editQuantitiesModalApplyToAllFieldInputField, "5");
		util.click(editQuantitiesModalApplyToAllButton);
		util.click(saveBtnModal);
	}

	public void addImage(String url) {
		util.click(addFromUrl);
		util.enterValue(enterUrlInputField, url);
		util.click(addFileBtn);
		util.waitUntilElementIsVisible(mediaAddedMsg, 10);

	}
	
String[] imgs = {	
	"https://m.media-amazon.com/images/I/518kvtHhDqL._SX300_SY300_QL70_FMwebp_.jpg",
	"https://m.media-amazon.com/images/I/61m5aDOE9BL._SL1024_.jpg",
	"https://m.media-amazon.com/images/I/51OLAm5JSJL.jpg",
	"https://m.media-amazon.com/images/I/51axV48s6+L._SL1500_.jpg",
	"https://m.media-amazon.com/images/I/51-aJY6fHVL.jpg",
	"https://m.media-amazon.com/images/I/51EweflGBiL.jpg",
	"https://m.media-amazon.com/images/I/71VMVW-QE5L._SL1500_.jpg",
	"https://m.media-amazon.com/images/I/71zkBUHPasL._SL1500_.jpg",
	"https://m.media-amazon.com/images/I/71ggaX8dVCL._SL1500_.jpg",
	"https://m.media-amazon.com/images/I/719-dfbYUkL._SL1500_.jpg",
	"https://m.media-amazon.com/images/I/71hskUJb5SL._SL1500_.jpg",
	"https://m.media-amazon.com/images/I/71x7inKOMjL._SL1080_.jpg",
	"https://m.media-amazon.com/images/I/61hEdtgjcnL._SL1097_.jpg",
	"https://m.media-amazon.com/images/I/71Jq3twi1iL._SL1080_.jpg",
	"https://m.media-amazon.com/images/I/71jVb7ec5KL._SL1080_.jpg",
};
	
	public void createNewShopifyProductWithLargeDetails() {
		productUrl = "";
		nameOfProduct = util.generateRandomString(255) ;
		skuOfProudct = "SKU" + util.randString(12);
		barcodeOfProduct = "BARCODE" + util.randString(7);
		iventoryOfProduct = "2000";
		productType = "PRODUCT TYPE " + util.generateRandomString(100) ;
		vendorName = "VENDOR " + util.generateRandomString(100) ;

		if (util.getConfigProperty("store").equals("staging")) {
			util.openUrl(util.getConfigProperty(urlStaging) + newProduct);
		} else {
			util.openUrl(util.getConfigProperty(urlOfStore) + newProduct);
		}

		for(int i = 0; i <= 100; i++) {
			productTagsInputField.sendKeys(util.randString(4) + ",");
		}
		util.pressEnter();
		util.hold(2);
		
		util.enterValue(productTypeInputField, productType);
		util.enterValue(titleInputField, nameOfProduct);
		util.switchToIFrame(iframe);
		util.enterValue(productDescriptionBox, ProductDetails.productDescription());
		util.switchToDefaultContent();
		for(String img : imgs) {
			util.click(addFromUrl);
			util.hold(1);
			util.click(enterUrlInputField);
			util.enterValue(enterUrlInputField, img);
			new WebDriverWait(util.getDriver(), Duration.ofSeconds(30))
					.until(ExpectedConditions.elementToBeClickable(addFileBtn));
			util.click(addFileBtn);
		}
		
		util.hold(5);
		util.enterValue(priceInputField, "149999");
		util.enableButton(openSKUCheckbox);
		util.enterValue(skuInpField, skuOfProudct);
		util.enterValue(barcodeInputField, barcodeOfProduct);
		util.enterValue(locationsInpFieldList, "5000");
		util.enterValue(weightInpField, "150");
		util.selectByValue(selectWtUnit, "GRAMS");
		util.click(saveBtn);
		util.hold(2);
		util.waitUntilElementIsVisible(productCreatedMsg);
		productUrl = util.getPageURL();

		containerIDOfProduct = StringUtils.substringAfterLast(productUrl, "/").trim();
//		util.hold(30);
	}
	
	public void addVariants(int size) {
		util.click(addOptionsBtn);
		util.click(sizeOptionInputField);
		util.click(sizeOptionValue);
		util.click(sizeOptionValueInputField);
		
		util.enterValue(sizeOptionValueInputField, "l");
		util.hold(2);
		
		for(int i = 1; i < size; i++) {
			
			util.enterValue(addAnotherValueInputField, "l"+i);
		}
		util.click(doneBtn);
		util.click(saveBtn);
	}
}

