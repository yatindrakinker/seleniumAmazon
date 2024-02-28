package com.AMI.TestCases;

import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ami.pageobjects.ProductListingPage;
import com.ami.pageobjects.SearchFieldListingGridPage;
import com.ami.resources.BaseClass;

import RetryLogic.Retrylogic;

public class SearchFieldListingGridTest extends BaseClass {
	SearchFieldListingGridPage obj;
	ProductListingPage plp;

	@DataProvider
	public Object[][] getListingData() throws IOException {
		List<HashMap<String, String>> data = util.getJsonDataToHashmap("SearchFieldListingGrid");
		return new Object[][] { { data.get(0) } };
	}

	@Test(priority = 1)
	public void initializeInstances() {
		obj = new SearchFieldListingGridPage(util);
		plp = new ProductListingPage(util);
	}

	@Test(priority = 1)
	public void testElementsAreDisplayed() {
		plp.listingSubMenuIsDisplayed();
		plp.redirectionToListing();
		util.hold(5);
		obj.elementsAreDisplayed();
		obj.allCheckboxAreSelectedByDefaultInSearchWith();
		util.pressEscape();
	}

	@Test(priority = 2, dataProvider = "getListingData")
	public void testWorkingOfSearchInputField(HashMap<String, String> input) {
		obj.workingOfSearchInputFieldWithValidValues(input, "valid_title");
		util.hold(3);
		obj.workingOfSearchInputFieldWithValidValues(input, "valid_vendor");
		util.hold(3);
		obj.workingOfSearchInputFieldWithValidValues(input, "valid_product_type");
		util.hold(3);
		obj.workingOfSearchInputFieldWithInValidValues(input, "invalid");
		util.hold(3);
		obj.workingOfSearchInputFieldWithInValidValues(input, "invalid");
		util.hold(3);
		obj.workingOfSearchInputFieldWithInValidValues(input, "invalid");
		util.hold(3);
	}

	@Test(priority = 3, dataProvider = "getListingData")
	public void testSearchOnlyForProductType(HashMap<String, String> input) {
		obj.searchOnlyForProductType(input);
	}

	@Test(priority = 4, dataProvider = "getListingData")
	public void testSearchOnlyForVendor(HashMap<String, String> input) {
		obj.searchOnlyForVendor(input);
	}

	@Test(priority = 5, dataProvider = "getListingData")
	public void testSearchOnlyForTitle(HashMap<String, String> input) {
		obj.searchOnlyForTitle(input);
	}

	@Test(priority = 6, dataProvider = "getListingData")
	public void testSearchOnlyForSKU(HashMap<String, String> input) {
		obj.searchOnlyForSKU(input);
	}

	@Test(priority = 7, dataProvider = "getListingData")
	public void testSearchForTitleWhenAllCheckboxAreUnchecked(HashMap<String, String> input) {
		obj.deselectAllCheckbox();
		obj.workingOfSearchInputFieldWithValidValuesWhenAllCheckboxAreUnchecked(input, "valid_title");
	}

	@Test(priority = 8, dataProvider = "getListingData")
	public void testSearchForVendorWhenAllCheckboxAreUnchecked(HashMap<String, String> input) {
		obj.deselectAllCheckbox();
		obj.workingOfSearchInputFieldWithValidValuesWhenAllCheckboxAreUnchecked(input, "valid_vendor");
	}

	@Test(priority = 9, dataProvider = "getListingData")
	public void testSearchForBarcodeWhenAllCheckboxAreUnchecked(HashMap<String, String> input) {
		obj.deselectAllCheckbox();
		obj.workingOfSearchInputFieldWithValidValuesWhenAllCheckboxAreUnchecked(input, "valid_barcode");
	}

	@Test(priority = 10, dataProvider = "getListingData")
	public void testSearchForProductTypeWhenAllCheckboxAreUnchecked(HashMap<String, String> input) {
		obj.deselectAllCheckbox();
		obj.workingOfSearchInputFieldWithValidValuesWhenAllCheckboxAreUnchecked(input, "valid_product_type");
	}

	@Test(priority = 11, dataProvider = "getListingData")
	public void testSearchForSKUWhenAllCheckboxAreUnchecked(HashMap<String, String> input) {
		obj.deselectAllCheckbox();
		obj.workingOfSearchInputFieldWithValidValuesWhenAllCheckboxAreUnchecked(input, "valid_sku");
	}

	@Test(priority = 12, dataProvider = "getListingData")
	public void testSearchAllWhenOnlyTitleIsChecked(HashMap<String, String> input) {
		obj.deselectAllCheckbox();
		obj.clickCheckBox("title");
		obj.workingOfSearchInputFieldWithValidValuesWhenAllCheckboxAreUnchecked(input, "valid_vendor");
		obj.workingOfSearchInputFieldWithValidValuesWhenAllCheckboxAreUnchecked(input, "valid_barcode");
		obj.workingOfSearchInputFieldWithValidValuesWhenAllCheckboxAreUnchecked(input, "valid_product_type");
		obj.workingOfSearchInputFieldWithValidValuesWhenAllCheckboxAreUnchecked(input, "valid_sku");
	}

	@Test(priority = 13, dataProvider = "getListingData")
	public void testSearchAllWhenOnlyVendorIsChecked(HashMap<String, String> input) {
		obj.deselectAllCheckbox();
		obj.clickCheckBox("vendor");
		obj.workingOfSearchInputFieldWithValidValuesWhenAllCheckboxAreUnchecked(input, "valid_title");
		obj.workingOfSearchInputFieldWithValidValuesWhenAllCheckboxAreUnchecked(input, "valid_barcode");
		obj.workingOfSearchInputFieldWithValidValuesWhenAllCheckboxAreUnchecked(input, "valid_product_type");
		obj.workingOfSearchInputFieldWithValidValuesWhenAllCheckboxAreUnchecked(input, "valid_sku");
	}

	@Test(priority = 14, dataProvider = "getListingData")
	public void testSearchAllWhenOnlyBarcodeIsChecked(HashMap<String, String> input) {

		obj.deselectAllCheckbox();
		obj.clickCheckBox("barcode");
		obj.workingOfSearchInputFieldWithValidValuesWhenAllCheckboxAreUnchecked(input, "valid_title");
		obj.workingOfSearchInputFieldWithValidValuesWhenAllCheckboxAreUnchecked(input, "valid_vendor");
		obj.workingOfSearchInputFieldWithValidValuesWhenAllCheckboxAreUnchecked(input, "valid_product_type");
		obj.workingOfSearchInputFieldWithValidValuesWhenAllCheckboxAreUnchecked(input, "valid_sku");
	}

	@Test(priority = 15, dataProvider = "getListingData")
	public void testSearchAllWhenOnlyProductTypeIsChecked(HashMap<String, String> input) {

		obj.deselectAllCheckbox();
		obj.clickCheckBox("productType");
		obj.workingOfSearchInputFieldWithValidValuesWhenAllCheckboxAreUnchecked(input, "valid_title");
		obj.workingOfSearchInputFieldWithValidValuesWhenAllCheckboxAreUnchecked(input, "valid_vendor");
		obj.workingOfSearchInputFieldWithValidValuesWhenAllCheckboxAreUnchecked(input, "valid_barcode");
		obj.workingOfSearchInputFieldWithValidValuesWhenAllCheckboxAreUnchecked(input, "valid_sku");
	}

	@Test(priority = 16, dataProvider = "getListingData")
	public void testSearchAllWhenOnlySKUIsChecked(HashMap<String, String> input) {

		obj.deselectAllCheckbox();
		obj.clickCheckBox("sku");
		obj.workingOfSearchInputFieldWithValidValuesWhenAllCheckboxAreUnchecked(input, "valid_title");
		obj.workingOfSearchInputFieldWithValidValuesWhenAllCheckboxAreUnchecked(input, "valid_vendor");
		obj.workingOfSearchInputFieldWithValidValuesWhenAllCheckboxAreUnchecked(input, "valid_barcode");
		obj.workingOfSearchInputFieldWithValidValuesWhenAllCheckboxAreUnchecked(input, "valid_product_type");
	}

	@Test(priority = 17, dataProvider = "getListingData")
	public void testAllDeselectedCheckboxMustBeUncheckedAfterPageIsRefreshed(HashMap<String, String> input) {

		obj.deselectAllCheckbox();
		obj.waitAfterRefresh();
		obj.validateAllCheckboxAreUnchecked();
	}

}
