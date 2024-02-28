package com.AMI.TestCases;

import org.testng.annotations.Test;

import com.ami.pageobjects.ProductSettingPage;
import com.ami.resources.BaseClass;

public class ProductSettingTest extends BaseClass {
	ProductSettingPage psp;

	@Test(priority = 1)
	public void testPrdouctDetailsToggleBtnIsWorking() {
//		util.goToSettings();
		psp = new ProductSettingPage(util);
		psp.productSettingsIsClickable();
		psp.prdouctDetailsToggleBtnIsWorking();
	}

	@Test(priority = 2)
	public void testImageToggleBtnIsWorking() {
		psp = new ProductSettingPage(util);
		psp.imageToggleBtnIsWorking();
	}

	@Test(priority = 3)
	public void testSkuToggleBtnIsWorking() {
		psp = new ProductSettingPage(util);
		psp.skuToggleBtnIsWorking();
	}

	@Test(priority = 4)
	public void testBarcodeToggleBtnIsWorking() {
		psp = new ProductSettingPage(util);
		psp.barcodeToggleBtnIsWorking();
	}
}
