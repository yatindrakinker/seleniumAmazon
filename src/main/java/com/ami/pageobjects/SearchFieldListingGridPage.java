package com.ami.pageobjects;

import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ami.resources.Utilities;

public class SearchFieldListingGridPage extends SearchFieldListingGridPageOR {
	Utilities util;
	private String ariaChecked = "aria-checked";
	private String falseVal = "false";
	private String invalid = "invalid";

	public SearchFieldListingGridPage(Utilities util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
	}

	public void elementsAreDisplayed() {
		WebElement[] arr = { searchWithBtn, moreFilterBtn };
		WebElement[] searchWithBtnArr = { titleLabel, titleCheckbox, vendorLabel, vendorCheckbox, productTypeLabel,
				productTypeCheckbox, barcodeLabel, barcodeCheckbox, SKULabel, SKUCheckbox };

		for (WebElement element : arr) {
			util.isElementDisplyedAndValidate(element);
		}

		util.click(searchWithBtn);

		for (WebElement element : searchWithBtnArr) {
			util.isElementDisplyedAndValidate(element);
		}
	}

	public void allCheckboxAreSelectedByDefaultInSearchWith() {
		WebElement[] searchWithBtnCheckBoxArr = { titleCheckbox, vendorCheckbox, productTypeCheckbox, barcodeCheckbox,
				SKUCheckbox };

		for (WebElement element : searchWithBtnCheckBoxArr) {
			util.validateIsElementSelected(element);
		}
	}

	public void workingOfSearchInputFieldWithValidValues(Map<String, String> input, String key) {
		boolean isTrue = false;
		util.enterValue(searchInputFieldListing, input.get(key));
		util.pressEscape();
		util.click(searchInputFieldListing);
		util.hold(5);

		if ((!suggestionList.isEmpty())) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);

	}

	public void workingOfSearchInputFieldWithInValidValues(Map<String, String> input, String key) {
		boolean isTrue = false;
		util.enterValue(searchInputFieldListing, input.get(key));
		util.pressEscape();
		util.click(searchInputFieldListing);
		util.hold(5);

		if (noSuggestionsFound.isDisplayed()) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);
	}

	public void searchOnlyForProductType(Map<String, String> input) {
		WebElement[] searchWithBtnCheckBoxArr = { titleCheckbox, vendorCheckbox, barcodeCheckbox, SKUCheckbox };
		WebElement[] searchWithBtnCheckBoxLabelArr = { titleLabel, vendorLabel, barcodeLabel, SKULabel };
		util.click(searchWithBtn);

		for (int i = 0; i < searchWithBtnCheckBoxArr.length; i++) {

			if (util.extractValueByAttributes(searchWithBtnCheckBoxArr[i], ariaChecked).equals("true")) {
				util.click(searchWithBtnCheckBoxLabelArr[i]);
				util.hold(1);
			}

		}

		if (util.extractValueByAttributes(productTypeCheckbox, ariaChecked).equals(falseVal)) {
			util.click(productTypeLabel);
			util.hold(1);
		}

		workingOfSearchInputFieldWithValidValues(input, "valid_product_type");
		workingOfSearchInputFieldWithInValidValues(input, invalid);
	}

	public void searchOnlyForVendor(Map<String, String> input) {
		WebElement[] searchWithBtnCheckBoxArr = { titleCheckbox, barcodeCheckbox, productTypeCheckbox, SKUCheckbox };
		WebElement[] searchWithBtnCheckBoxLabelArr = { titleLabel, barcodeLabel, productTypeLabel, SKULabel };
		util.click(searchWithBtn);

		for (int i = 0; i < searchWithBtnCheckBoxArr.length; i++) {

			if (util.extractValueByAttributes(searchWithBtnCheckBoxArr[i], ariaChecked).equals("true")) {
				util.click(searchWithBtnCheckBoxLabelArr[i]);
				util.hold(1);
			}

		}

		if (util.extractValueByAttributes(vendorCheckbox, ariaChecked).equals(falseVal)) {
			util.click(vendorLabel);
			util.hold(1);
		}

		workingOfSearchInputFieldWithValidValues(input, "valid_vendor");
		workingOfSearchInputFieldWithInValidValues(input, invalid);
	}

	public void searchOnlyForTitle(Map<String, String> input) {
		WebElement[] searchWithBtnCheckBoxArr = { vendorCheckbox, barcodeCheckbox, productTypeCheckbox, SKUCheckbox };
		WebElement[] searchWithBtnCheckBoxLabelArr = { vendorLabel, barcodeLabel, productTypeLabel, SKULabel };
		util.click(searchWithBtn);

		for (int i = 0; i < searchWithBtnCheckBoxArr.length; i++) {

			if (util.extractValueByAttributes(searchWithBtnCheckBoxArr[i], ariaChecked).equals("true")) {
				util.click(searchWithBtnCheckBoxLabelArr[i]);
				util.hold(1);
			}

		}

		if (util.extractValueByAttributes(titleCheckbox, ariaChecked).equals(falseVal)) {
			util.click(titleLabel);
			util.hold(1);
		}

		workingOfSearchInputFieldWithValidValues(input, "valid_title");
		workingOfSearchInputFieldWithInValidValues(input, invalid);
	}

	public void searchOnlyForSKU(Map<String, String> input) {
		WebElement[] searchWithBtnCheckBoxArr = { vendorCheckbox, barcodeCheckbox, productTypeCheckbox, titleCheckbox };
		WebElement[] searchWithBtnCheckBoxLabelArr = { vendorLabel, barcodeLabel, productTypeLabel, titleLabel };
		util.click(searchWithBtn);

		for (int i = 0; i < searchWithBtnCheckBoxArr.length; i++) {

			if (util.extractValueByAttributes(searchWithBtnCheckBoxArr[i], ariaChecked).equals("true")) {
				util.click(searchWithBtnCheckBoxLabelArr[i]);
				util.hold(1);
			}

		}

		if (util.extractValueByAttributes(SKUCheckbox, ariaChecked).equals(falseVal)) {
			util.click(SKULabel);
			util.hold(1);
		}

		workingOfSearchInputFieldWithValidValues(input, "valid_sku");
		workingOfSearchInputFieldWithInValidValues(input, invalid);
	}

	public void deselectAllCheckbox() {
		WebElement[] searchWithBtnCheckBoxArr = { titleCheckbox, barcodeCheckbox, productTypeCheckbox, SKUCheckbox,
				vendorCheckbox };
		WebElement[] searchWithBtnCheckBoxLabelArr = { titleLabel, barcodeLabel, productTypeLabel, SKULabel,
				vendorLabel };
		util.pressEscape();
		util.click(searchWithBtn);

		for (int i = 0; i < searchWithBtnCheckBoxArr.length; i++) {

			if (util.extractValueByAttributes(searchWithBtnCheckBoxArr[i], ariaChecked).equals("true")) {
				util.click(searchWithBtnCheckBoxLabelArr[i]);
				util.hold(1);
			}

		}
	}

	public void validateAllCheckboxAreUnchecked() {
		boolean isTrue = false;
		WebElement[] searchWithBtnCheckBoxArr = { titleCheckbox, barcodeCheckbox, productTypeCheckbox, SKUCheckbox,
				vendorCheckbox };
		util.pressEscape();
		util.click(searchWithBtn);

		for (int i = 0; i < searchWithBtnCheckBoxArr.length; i++) {

			if (util.extractValueByAttributes(searchWithBtnCheckBoxArr[i], ariaChecked).equals(falseVal)) {
				isTrue = true;
				Assert.assertTrue(isTrue);
			}
			Assert.assertTrue(isTrue);

		}
	}

	public void waitAfterRefresh() {
		util.refreshPage();
		util.switchToIFrame();
		util.waitUntilElementIsVisible(moreFilterBtn, 45);
	}

	public void workingOfSearchInputFieldWithValidValuesWhenAllCheckboxAreUnchecked(Map<String, String> input,
			String key) {
		boolean isTrue = false;
		util.enterValue(searchInputFieldListing, input.get(key));
		util.pressEscape();
		util.click(searchInputFieldListing);
		util.hold(8);

		if (noSuggestionsFound.isDisplayed()) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);

	}

	public void selectAllCheckBox() {
		WebElement[] searchWithBtnCheckBoxArr = { titleCheckbox, barcodeCheckbox, productTypeCheckbox, SKUCheckbox,
				vendorCheckbox };
		WebElement[] searchWithBtnCheckBoxLabelArr = { titleLabel, barcodeLabel, productTypeLabel, SKULabel,
				vendorLabel };
		util.click(searchWithBtn);

		for (int i = 0; i < searchWithBtnCheckBoxArr.length; i++) {

			if (util.extractValueByAttributes(searchWithBtnCheckBoxArr[i], ariaChecked).equals(falseVal)) {
				util.click(searchWithBtnCheckBoxLabelArr[i]);
				util.hold(1);
			}

		}

		util.pressEscape();
	}

	public void clickCheckBox(String name) {
		util.pressEscape();
		util.click(searchWithBtn);

		switch (name) {
		case "title":
			util.click(titleLabel);
			break;
		case "barcode":
			util.click(barcodeLabel);
			break;
		case "productType":
			util.click(productTypeLabel);
			break;
		case "sku":
			util.click(SKULabel);
			break;
		case "vendor":
			util.click(vendorLabel);
			break;

		default:
			break;
		}
	}
}
