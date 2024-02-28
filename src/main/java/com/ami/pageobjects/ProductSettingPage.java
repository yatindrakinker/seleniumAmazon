package com.ami.pageobjects;

import org.openqa.selenium.support.PageFactory;

import com.ami.resources.Utilities;

public class ProductSettingPage extends ProductSettingPageOR {
	Utilities util;

	/**
	 * constructor
	 * 
	 * @param util
	 */
	public ProductSettingPage(Utilities util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
	}

	public void productSettingsIsClickable() {
		util.click(productSettings);
		util.isElementDisplyedAndValidate(productSettingDescription);
	}

	public void prdouctDetailsToggleBtnIsWorking() {
		util.moveToElement(productDetailsToggleButton);
		util.enableAndDisableElementAndValidate(productDetailsToggleButton);
		util.clickOnSaveBtn();
		util.hold(1);
		util.click(productDetailsToggleButton);
		util.enableAndDisableElementAndValidate(productDetailsToggleButton);
		util.click(productDetailsToggleButton);
		util.clickOnSaveBtn();
		util.hold(1);
		util.enableAndDisableElementAndValidate(productDetailsToggleButton);
	}

	public void imageToggleBtnIsWorking() {
		util.moveToElement(imagesToggleButton);
		util.enableAndDisableElementAndValidate(imagesToggleButton);
		util.click(imagesToggleButton);
		util.clickOnSaveBtn();
		util.hold(1);
		util.enableAndDisableElementAndValidate(imagesToggleButton);
		util.click(imagesToggleButton);
		util.clickOnSaveBtn();
		util.hold(1);
		util.enableAndDisableElementAndValidate(imagesToggleButton);
	}

	public void skuToggleBtnIsWorking() {
		util.moveToElement(skuToggleBtn);
		util.enableAndDisableElementAndValidate(skuToggleBtn);
		util.click(skuToggleBtn);
		util.clickOnSaveBtn();
		util.hold(1);
		util.enableAndDisableElementAndValidate(skuToggleBtn);
		util.click(skuToggleBtn);
		util.clickOnSaveBtn();
		util.hold(1);
		util.enableAndDisableElementAndValidate(skuToggleBtn);
	}

	public void barcodeToggleBtnIsWorking() {
		util.moveToElement(barcodeToggleBtn);
		util.enableAndDisableElementAndValidate(barcodeToggleBtn);
		util.click(barcodeToggleBtn);
		util.clickOnSaveBtn();
		util.hold(1);
		util.enableAndDisableElementAndValidate(barcodeToggleBtn);
		util.click(barcodeToggleBtn);
		util.clickOnSaveBtn();
		util.hold(1);
		util.enableAndDisableElementAndValidate(barcodeToggleBtn);
	}
}
