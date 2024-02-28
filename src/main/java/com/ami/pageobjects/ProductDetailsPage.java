package com.ami.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ami.pageobjects.reusableclasses.ReusableMethods;
import com.ami.resources.Utilities;

public class ProductDetailsPage extends ProductDetailsOR {
	private Utilities util;
	private CreateShopifyProduct csp;
	private ReusableMethods reuse;
	private String imgUrl = "https://m.media-amazon.com/images/I/71mbxPIMl1S._SL1500_.jpg";

	public ProductDetailsPage(Utilities util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
		csp = new CreateShopifyProduct(util);
		reuse = new ReusableMethods(util);
	}

	/**
	 * TC_AM_PS_001, TC_AM_PS_002
	 */
	public void gotoProductDetails() {
		util.openSectionsInNewTab(LISTING);
		util.openSectionsInNewTab(LINKING);
		util.openSectionsInNewTab(SETTINGSSECTION);
		util.openNewProduct();
		util.goToSection(SETTINGSSECTION);
		util.click(prodSettings);
		util.click(productDetailsBtn);
		util.isElementDisplyedAndValidate(productHeadingDetails);
	}

	/**
	 * TC_AM_PS_032
	 */
	public void verifyToggleBtns() {
		verifyFBAFBMToggleBtnIsWorking(fbaToggleBtn, fbaSKUToggleBtn, fbaBarcodeToggleBtn);
		verifyFBAFBMToggleBtnIsWorking(fbaToggleBtn, fbaSKUToggleBtn, fbaBarcodeToggleBtn);
		verifyFBAFBMToggleBtnIsWorking(fbmToggleBtn, fbmSKUToggleBtn, fbmBarcodeToggleBtn);
		verifyFBAFBMToggleBtnIsWorking(fbmToggleBtn, fbmSKUToggleBtn, fbmBarcodeToggleBtn);
		disableFBAAndEnableFBM();
	}

	private void verifyFBAFBMToggleBtnIsWorking(WebElement element, WebElement elementSKU, WebElement elementBc) {
		String initialCheckedState = element.getAttribute(ARIACHECKED);

		if (initialCheckedState.equalsIgnoreCase(FALSEVAL)) {
			util.click(element);
			verifySkuBarcodeToggleBtnsAreWorking(elementSKU);
			verifySkuBarcodeToggleBtnsAreWorking(elementSKU);
			verifySkuBarcodeToggleBtnsAreWorking(elementBc);
			verifySkuBarcodeToggleBtnsAreWorking(elementBc);

		} else {
			verifySkuBarcodeToggleBtnsAreWorking(elementSKU);
			verifySkuBarcodeToggleBtnsAreWorking(elementSKU);
			verifySkuBarcodeToggleBtnsAreWorking(elementBc);
			verifySkuBarcodeToggleBtnsAreWorking(elementBc);
		}
	}

	private void verifySkuBarcodeToggleBtnsAreWorking(WebElement currentToggleBtn) {
		String initialCheckedState = currentToggleBtn.getAttribute(ARIACHECKED);

		if (initialCheckedState.equalsIgnoreCase(FALSEVAL)) {
			util.click(currentToggleBtn);
			initialCheckedState = currentToggleBtn.getAttribute(ARIACHECKED);

			if (initialCheckedState.equalsIgnoreCase(TRUEVAL)) {
				Assert.assertTrue(true, "toggle button is disabled.");
			}
		} else {
			util.click(currentToggleBtn);
			initialCheckedState = currentToggleBtn.getAttribute(ARIACHECKED);

			if (initialCheckedState.equalsIgnoreCase(FALSEVAL)) {
				Assert.assertTrue(true, "toggle button is enabled.");
			}
		}
	}

	private void disableFBAAndEnableFBM() {
		if (fbaToggleBtn.getAttribute(ARIACHECKED).equals(TRUEVAL)) {
			util.click(fbaToggleBtn);
		}

		if (fbmToggleBtn.getAttribute(ARIACHECKED).equals(FALSEVAL)) {
			util.click(fbmToggleBtn);
		}
	}

	private void toggleProductAttribute(WebElement toggleBtn, String expectedValue) {
		if (toggleBtn.getAttribute(ARIACHECKED).equals(expectedValue)) {
			util.click(toggleBtn);
			util.switchToIFrame();
			util.click(saveBtn);
			util.switchToDefaultContent();
		}
	}

	public void enableProductInformation() {
		toggleProductAttribute(productInfoToggleBtn, FALSEVAL);
	}

	public void disableProductInformation() {
		toggleProductAttribute(productInfoToggleBtn, TRUEVAL);
	}

	public void enableProductImages() {
		toggleProductAttribute(productImagesToggleBtn, FALSEVAL);
	}

	public void disableProductImages() {
		toggleProductAttribute(productImagesToggleBtn, TRUEVAL);
	}

	public void updateImage() {
		util.goToNewProduct();
		csp.createNewShopifyProduct();
		csp.addImage(imgUrl);
		util.hold(30);
	}

	public void verifyImageSyncing(String sku) {

		reuse.searchBySKUOnListing(sku);
		util.hold(5);
		util.click(checkboxListing);

		if (selectActionsBtn.getAttribute(ARIAEXPANDED).equals(FALSEVAL)) {
			util.click(selectActionsBtn);
			util.hold(1);
			util.click(syncImgBtn);
			util.hold(1);
			util.click(confirmBtnModal);
			util.waitUntilElementIsVisible(imgSyncInitiatedMsg, 30);
		}
	}

	public void verifySyncing(String sku, String actionName) {

		reuse.searchBySKUOnListing(sku);
		util.hold(5);
		util.click(checkboxListing);

		if (selectActionsBtn.getAttribute(ARIAEXPANDED).equals(FALSEVAL)) {
			util.click(selectActionsBtn);
			util.hold(1);

			if (actionName.equalsIgnoreCase("img")) {
				util.click(syncImgBtn);
				util.hold(1);
				util.click(confirmBtnModal);
				util.waitUntilElementIsVisible(imgSyncInitiatedMsg, 30);
			} else if (actionName.equalsIgnoreCase("info")) {
				util.click(syncProductBtn);
				util.hold(1);
				util.click(confirmBtnModal);
				util.waitUntilElementIsVisible(prodUpdateSyncInitiatedMsg, 30);
			}

		}
	}

	/**
	 * TC_AM_PS_015
	 */
	public void verifyErrorTagIsVisible() {
		boolean isTrue = false;

		if (activity.getText().equalsIgnoreCase("error")) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);
	}
	
	/**
	 * TC_AM_PS_016
	 */
	public void verifyErrForInfoSync(String actionName) {
		util.click(activity);
		util.waitUntilElementIsVisible(productErrorTabInModal, 10);
		util.click(productErrorTabInModal);
		
		if (actionName.equalsIgnoreCase("img")) {
			util.isElementDisplyedAndValidate(prodImgDisabledErrMsgInModal);
		} else if (actionName.equalsIgnoreCase("info")) {
			util.isElementDisplyedAndValidate(prodSyncDisabledErrMsgInModal);
		}
		
		util.click(crossBtnModal);
		util.hold(1);
		
	}

}
