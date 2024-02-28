package com.ami.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchFieldListingGridPageOR {

	@FindBy(xpath = "//input[@placeholder='Search with Title, Vendor, Product type or Barcode']")
	WebElement searchInputFieldListing;

	@FindBy(xpath = "//div[@class='Polaris-TextField Polaris-TextField--focus']")
	WebElement searchInpFocus;
	
	@FindBy(xpath = "//p[@style = 'font-size: 13px; margin: 1px 0px;']")
	List<WebElement> suggestionList;
	
	@FindBy(xpath = "//div[text()='No Suggestions Found.']")
	WebElement noSuggestionsFound;

	@FindBy(xpath = "//input[@placeholder = 'Search with Title, Vendor, Product type or Barcode']/parent::div/parent::div/following-sibling::div/div")
	WebElement searchWithBtn;

	@FindBy(xpath = "//span[text() = 'Title']")
	WebElement titleLabel;

	@FindBy(xpath = "//span[text() = 'Title']/preceding-sibling::span/span/input")
	WebElement titleCheckbox;

	@FindBy(xpath = "//span[text() = 'Vendor']")
	WebElement vendorLabel;

	@FindBy(xpath = "//span[text() = 'Vendor']/preceding-sibling::span/span/input")
	WebElement vendorCheckbox;

	@FindBy(xpath = "//span[text() = 'Product type']")
	WebElement productTypeLabel;

	@FindBy(xpath = "//span[text() = 'Product type']/preceding-sibling::span/span/input")
	WebElement productTypeCheckbox;

	@FindBy(xpath = "//span[text() = 'Barcode']")
	WebElement barcodeLabel;

	@FindBy(xpath = "//span[text() ='Barcode']/preceding-sibling::span/span/input")
	WebElement barcodeCheckbox;

	@FindBy(xpath = "//span[text() = 'SKU']")
	WebElement SKULabel;

	@FindBy(xpath = "//span[text() ='SKU']/preceding-sibling::span/span/input")
	WebElement SKUCheckbox;

	@FindBy(xpath = "//span[text() = 'More filters']/parent::span/parent::button")
	WebElement moreFilterBtn;

	@FindBy(xpath = "//h3[text() = 'More filters']")
	WebElement moreFiltersHeading;
}
