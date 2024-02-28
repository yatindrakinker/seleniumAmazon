package com.ami.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ami.resources.Utilities;

public class ProductLinkingOverviewPage extends ProductLinkingPageOR {
	Utilities util;
	ProductLinkingPage plp;
	CreateShopifyProduct create;
	private static String amazonProdAtZerothIndex;

	public ProductLinkingOverviewPage(Utilities util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
	}

	/**
	 * manage linking button working
	 */
	public void onClickingManageLinking() {
		plp = new ProductLinkingPage(util);
		util.clickOnOverview();
		util.click(manageLinkingBtn);
		util.waitUntilElementIsVisible(linked, 30);
		util.isElementDisplyedAndValidate(linked);
		util.isElementDisplyedAndValidate(headingLinkAmzProduct);
		util.isElementDisplyedAndValidate(addNewSellerBtn);
	}
	
	public void fulfillmentTypeIsShown() {
		util.isElementDisplyedAndValidate(fulfillmentTypeLabel);
	}

	public void clickOnAcntSwitcher() {
		util.click(addNewSellerBtn);
		String activeAcntVal = util.getTagValue(activeAcnt);
		StringUtils.substringBefore(activeAcntVal, "3333");
		boolean isTrue = false;

		if (util.getConfigProperty("storeNameStaging").contains(activeAcntVal)) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);
	}

	public void addNewAcntIsWorking() {
		if (!addNewAcntList.isEmpty()) {
			plp.validateAddNewSellerWorking();
		} else {
			util.click(addNewSellerBtn);
			plp.validateAddNewSellerWorking();
		}
	}

	/**
	 * each section contains a search input field
	 */
	public void eachSectionContainsSearchField() {
		WebElement[] eleArr = { linked, linkingRequired, linkingRequired };

		for (WebElement ele : eleArr) {
			util.click(ele);
			util.isElementDisplyedAndValidate(searchInputField);
		}
	}

	public void searchInputFieldIsWorkingForSKUInLinkingReq() {
		String sku = util.getConfigProperty("sku");
		boolean isTrue = false;
		util.click(linkingRequired);
		util.enterValue(searchInputField, sku);

		if (!noDataList.isEmpty() || (!searchedProductList.isEmpty())) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);

	}

	public void searchInputFieldIsWorkingForBarcodeInLinkingReq() {
		String barcode = util.getConfigProperty("barcode");
		boolean isTrue = false;
		util.click(linkingRequired);
		util.enterValue(searchInputField, barcode);

		if (!noDataList.isEmpty() || (!searchedProductList.isEmpty())) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);

	}

	public void searchInputFieldIsWorkingForTitleInLinkingReq() {
		String name = util.getConfigProperty("name");
		boolean isTrue = false;
		util.click(linkingRequired);
		util.enterValue(searchInputField, name);

		if (noDataList.isEmpty() || (!searchedProductList.isEmpty())) {
			isTrue = true;
		}

		Assert.assertTrue(isTrue);

	}

	public void closeMatchSection() {
		WebElement[] eleArr = { amazonSectionCloseMatch, shopifySectionCloseMatch, actionSectionCloseMatch };
		util.click(closeMatchTab);
		util.hold(2);

		for (WebElement ele : eleArr) {
			util.isElementDisplyedAndValidate(ele);
		}
	}

	public void productInCloseMatchContains() {
		ArrayList<List<WebElement>> eleArrList = new ArrayList<>();
		eleArrList.add(productTitleCloseMatch);
		eleArrList.add(sellerSKUCloseMatch);
		eleArrList.add(productTitleCloseMatch);
		eleArrList.add(viewOnAmazonLinkCloseMatch);

		if (!noDataList.isEmpty()){
			Assert.assertTrue(true);
		}else if (noDataList.isEmpty()) {
			for (List<WebElement> eleList : eleArrList) {
				util.listIsNotEmpty(eleList);
			}	
		} 

	}

	public void viewOnAmazonLinkIsWorking() {
		if (!noDataList.isEmpty()) {
			Assert.assertTrue(true);
		} else {
			String url = util.extractValueByAttributes(viewOnAmazonLinkCloseMatch.get(0), "href");
			util.validateLinkIsWorking(url);
		}
	}

	public void actionColContainsTwoBtns() {
		if (!noDataList.isEmpty()) {
			Assert.assertTrue(true);
		} else {

			util.listIsNotEmpty(linkBtnCloseMatch);
			util.listIsNotEmpty(selectManuallyBtn);
		}
	}

//	--------------------------<Linking Required>------------------------

	public void linkingReqProducts() {
		util.click(linkingRequired);

	}

	public void linkingProdAreShown() {
		ArrayList<List<WebElement>> eleArrList = new ArrayList<>();
		eleArrList.add(productTitleCloseMatch);
		eleArrList.add(imgsOfProduct);
		eleArrList.add(skuOfProduct);
		eleArrList.add(barcodeOfProduct);
		eleArrList.add(inventoryOfProduct);
		eleArrList.add(amazonStatusOfProduct);
		eleArrList.add(actionOfProduct);
		eleArrList.add(viewOnAmazonLinkCloseMatch);

		if (noDataList.isEmpty()) {
			Assert.assertTrue(true);
		} else {

			for (List<WebElement> eleList : eleArrList) {
				util.listIsNotEmpty(eleList);
			}

			String url = util.extractValueByAttributes(viewOnAmazonLinkCloseMatch.get(0), "href");
			util.validateLinkIsWorking(url);
		}

	}

	public void linkProductFunctionality() {
		if (noDataList.isEmpty()) {
			String title = util.getTagValue(productTitleCloseMatch.get(0));
			amazonProdAtZerothIndex = title;
			util.click(linkBtnCloseMatch.get(0));
			util.listIsNotEmpty(linkProductsHeadingModal);
			String titleModal = productName.getText();

			Assert.assertEquals(title, titleModal);

			viewOnAmazonInLinkProductModalIsWorking();
		} else if (!noDataList.isEmpty()) {
			Assert.assertTrue(true);
		}
	}

	public void searchLinkProductModalContainsSearchInputField() {
		util.enterValue(searchInputProductLinkProductsModal, "toys-001");
		util.listIsNotEmpty(noDataList);
		util.clear(searchInputProductLinkProductsModal);
		util.waitUntilListIsVisible(linkBtnLinkProductsModal, 30);
	}

	public void viewOnShopifyLinkIsWorking() {
		String href = util.extractValueByAttributes(viewOnShopifyLinkLinkProductsModal.get(0), "href");
		String url = util.getPageURL();
		util.click(viewOnShopifyLinkLinkProductsModal.get(0));
		util.getWindoHandleByUrl(href);
		
		util.isElementDisplyedAndValidate(shopifyTitleInputField);
		util.gotoWindowByClosingCurrentOne(url);
		util.switchToIFrame();
	}

	public void viewOnAmazonInLinkProductModalIsWorking() {
		boolean isTrue = false;
		String url = util.getPageURL();
		String titleModal = productName.getText();
		titleModal = StringUtils.substringBefore(titleModal, "...");
		String href = util.extractValueByAttributes(viewOnAmazonLinkLinkProductsModal.get(0), "href");
		util.click(viewOnAmazonLinkLinkProductsModal.get(0));
		util.getWindoHandleByUrl(href);
		
		if(util.getPageURL().contains("https://www.amazon.in/")) {
			isTrue = true;
		}
		
		Assert.assertTrue(isTrue);
		
		util.gotoWindowByClosingCurrentOne(url);
		util.switchToIFrame();
	}
	
	public void linkAShopifyProduct() {
			util.click(linkBtnLinkProductsModal.get(0));
			util.isElementDisplyedAndValidate(confirmLinkProductModalHeading);
			util.isElementDisplyedAndValidate(productName);
			util.listIsNotEmpty(shopifyProductsLinkProductsModal);
			util.click(backBtnModal);
			util.hold(1);
			util.listIsEmpty(confirmBtnModalList);
			util.click(linkBtnLinkProductsModal.get(0));
			util.click(confirmBtnModal);
			util.hold(1);
			util.isElementDisplyedAndValidate(productLinkedSuccessfullyToast);
			util.switchToIFrame();
			util.hold(10);
			String title = util.getTagValue(productTitleCloseMatch.get(0));
			Assert.assertNotEquals(amazonProdAtZerothIndex, title);
	}

//	--------------------------</Linking Required>-----------------------

}
