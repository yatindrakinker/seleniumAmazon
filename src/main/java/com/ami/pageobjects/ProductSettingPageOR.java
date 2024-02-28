package com.ami.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductSettingPageOR {
	
	@FindBy(xpath = "//span[text() = 'Save']")
	WebElement saveBtn;

	@FindBy(xpath = "//span[text() = 'Product Settings']")
	WebElement productSettings;
	
	@FindBy(xpath = "//span[text() = 'Enable/Disable syncing of product details and images and price between app and Amazon']")
	WebElement productSettingDescription;
	
	@FindBy(xpath = "//span[text() = 'Product Details']/parent::span/parent::div/parent::div/preceding-sibling::div/button")
	WebElement productDetailsToggleButton;
	
	@FindBy(xpath = "//span[text() = 'Images']/parent::div/preceding-sibling::div/button")
	WebElement imagesToggleButton;
	
	@FindBy(xpath = "//span[text() = 'SKU']/parent::div/preceding-sibling::div/button")
	WebElement skuToggleBtn;
	
	@FindBy(xpath = "//span[text() = 'Barcode']/parent::div/preceding-sibling::div/button")
	WebElement barcodeToggleBtn;
}
