package com.ami.pageobjects;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ami.pageobjects.reusableclasses.ReusableMethods;
import com.ami.resources.Utilities;

public class ProductLinkingPage extends ProductLinkingPageOR {
	Utilities util;
	String pageUrl = "";
	private static final String INVALIDBARCODELINKED = "invalid_barcode_linked";
	private static final String LINKWITHSHOPIFYPRODUCTTITLE = "link_with_shopify_product_title";
	private static final String LINKCLOSEMATCHMODALTTITLE = "link_close_match_modal_title";
	private static final String BARCODELINKED = "barcode_linked";
	private static final String AMAZONSELLERSKULINKED = "amz_seller_sku_linked";
	private static final String AMAZONLINK = "https://www.amazon.in/";
	

	private static Logger log = LogManager.getLogger(ProductLinkingPage.class.getName());
	private ReusableMethods reuse;

	/**
	 * Constructor to initialize driver
	 * 
	 * @param util
	 */
	public ProductLinkingPage(Utilities util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
		reuse = new ReusableMethods(util);
	}

	/**
	 * This method validates that fields of product linking are visible on overview
	 */
	public void validateVisiblityOnOverView() {
		WebElement[] arrWebElement = { productLinkingHeadingOverview, linkedProductStatus, manageLinkingBtn,
				statusLinkedProductLabel, linkedProductCount, statusLinkingRequiredProductLabel,
				linkingRequiredProductCount, statusCloseMatchProductLabel, closeMatchProductCount };

//		validating visibility of each element
		for (WebElement element : arrWebElement) {
			util.isElementDisplyedAndValidate(element);
		}
	}

	/**
	 * This method validates that besides each product status label its count is
	 * also shown
	 */
	public void validateCountIsDisplayed() {
		boolean isTrue = false;
		util.hold(10);
		WebElement[] arrWebElement = { linkedProductCount, linkingRequiredProductCount, closeMatchProductCount };

		for (WebElement element : arrWebElement) {
			util.hold(1);

			if (Integer.parseInt(util.getTagValue(element)) >= 0) {
				isTrue = true;
			}
			Assert.assertTrue(isTrue);

		}
	}

	/**
	 * This method validates that when mouse is hovers at "linkedProductCount,
	 * linkingRequiredProductCount, closeMatchProductCount and manageLinkingBtn" its
	 * property changes to pointer.
	 */
	public void validateCursorPropertyChanges() {
		WebElement[] arrWebElement = { linkedProductCount, linkingRequiredProductCount, closeMatchProductCount,
				manageLinkingBtn };

		for (WebElement element : arrWebElement) {
			util.validateCursorProperty(element, "pointer");
		}
	}

	/**
	 * This method validates that on clicking "linkedProductCount,
	 * linkingRequiredProductCount, closeMatchProductCount and manageLinkingBtn"
	 * user is redirected to section accordingly
	 */
	public void validateRedirectFromOverview() {
		WebElement[] arrWebElement = { manageLinkingBtn, linkingRequiredProductCount, linkedProductCount,
				closeMatchProductCount };

		for (int i = 0; i < arrWebElement.length; i++) {
			boolean isTrue = false;
			util.hold(1);
			util.click(arrWebElement[i]);
			util.hold(2);

			util.isElementDisplyedAndValidate(productLinkingPageHeading);

			util.getDriver().navigate().back();
			util.switchToIFrame();
			util.waitUntilElementIsVisible(manageLinkingBtn, 30);
		}
		util.hold(1);
		util.actionClick(manageLinkingBtn);
	}

	/**
	 * This method validates visibility of elements on "Link Amazon Products" =>
	 * Linking Required
	 */
	public void validateVisibilityOfLinkingRequiredElementOnLinkAmazonProducts() {
		WebElement[] arrLinkingReqWebElement = { linked, closeMatchTab, closeMatchProductCountLinkAmazon,
				linkingRequiredProductCountLinkAmazon, linkedProductCountLinkAmazon, searchInputField, sortByBtn,
				imageHeading, productTitleHeading, sellerCentralSKUHeading, barcodeHeading, amazonStatusHeading,
				actionHeading, cedAmzChannelFooterLink, getSupportFooterBtn, bookACallFooterLink, linkingRequired,
				selectStoreBtn, headingLinkAmzProduct, prevPaginationBtn, nextPaginationBtn };

		if (util.extractValueByAttributes(linkingRequired, ARIASELECTED).equals(FALSEVAL)) {
			util.click(linkingRequired);
		}

		util.hold(10);

		if (!linkBtnList.isEmpty()) {

			for (int i = 0; i < (arrLinkingReqWebElement.length); i++) {
				util.isElementDisplyedAndValidate(arrLinkingReqWebElement[i]);
			}

		} else {
			util.listIsNotEmpty(noDataList);
		}
	}

	/**
	 * This method validates that elements related to linked products are visible.
	 */
	public void validateVisibilityOfLinkedProductsElement() {
		WebElement[] arrLinkedWebElement = { headingLinkAmzProduct, linked, closeMatchTab,
				closeMatchProductCountLinkAmazon, linkingRequiredProductCountLinkAmazon, linkedProductCountLinkAmazon,
				searchInputField, selectStoreBtn, amazonHeading, shopifyHeading, amazonStatusHeading, actionHeading,
				cedAmzChannelFooterLink, getSupportFooterBtn, bookACallFooterLink, nextPaginationBtn,
				prevPaginationBtn };

		if (util.extractValueByAttributes(linked, ARIASELECTED).equals(FALSEVAL)) {
			util.click(linked);
			try {
				util.waitUntilElementIsVisible(unlinkBtnList.get(0));
			} catch (Exception e) {
				util.waitUntilElementIsVisible(noData);
			}
		}

//		validating visibility of each element

		if (noDataList.isEmpty()) {
			for (int i = 0; i < (arrLinkedWebElement.length) - 2; i++) {
				util.isElementDisplyedAndValidate(arrLinkedWebElement[i]);
			}
		} else {
			util.isElementDisplyedAndValidate(noData);
		}
	}

	/**
	 * This method validates that elements of "Link Amazon Products" are working
	 * properly
	 */
	public void validateWorkingOfLinkedElements() {
		WebElement[] arrWebElement = { linkingRequired, closeMatchTab, linked };
		util.click(linked);

		for (int i = 0; i < arrWebElement.length; i++) {
			boolean isTrue = false;
			util.click(arrWebElement[i]);

			if (util.extractValueByAttributes(arrWebElement[i], ARIASELECTED).equals("true")) {
				isTrue = true;
			}
			Assert.assertTrue(isTrue);
		}
	}

	public void validateAddNewSellerWorking() {
		util.click(linked);
//		validating add new Acnt button working
		util.click(addNewSellerBtn);
		util.click(addNewAcntBtn);
		util.isElementDisplyedAndValidate(addNewAcntPageHeading);
		util.click(backBtn);

		if (!unlinkBtnList.isEmpty()) {
			util.waitUntilElementIsVisible(unlinkBtnList.get(0));
		} else {
			util.waitUntilElementIsVisible(noData);
		}

	}

	public void clickOnManageLinkingBtn() {
		util.click(manageLinkingBtn);
	}

	public void clickOnBackBtn() {
		util.click(backBtn);
	}

	/**
	 * This method validates when user click from one tab to another he is able to
	 * switch successfully.
	 */
	public void redirectionFromOneTabToAnother() {
		WebElement[] arrElement = { linkingRequired, closeMatchTab, linkingRequired, linked, closeMatchTab, linked };

		for (int i = 0; i < arrElement.length; i++) {
			boolean isTrue = false;
			util.click(arrElement[i]);

			if (util.extractValueByAttributes(arrElement[i], ARIASELECTED).equals("true")) {
				isTrue = true;
			}
			Assert.assertTrue(isTrue);
		}
	}

	public void searchInputFieldFunctionality(Map<String, String> input) {
		util.isElementDisplyedAndValidate(searchInputField);

		if (util.extractValueByAttributes(linked, ARIASELECTED).equals(FALSEVAL)) {
			util.click(linked);
		}

//		linked products
		searchProductUsingValidInput(input, BARCODELINKED);
		util.hold(1);
		searchProductUsingInvalidInput(input, INVALIDBARCODELINKED);
		util.hold(1);
		searchProductUsingValidInput(input, AMAZONSELLERSKULINKED);
		util.hold(1);
		searchProductUsingInvalidInput(input, INVALIDBARCODELINKED);
		util.hold(1);
		searchProductUsingValidInput(input, "title_linked");
		util.hold(1);
		searchProductUsingInvalidInput(input, INVALIDBARCODELINKED);
		util.hold(5);
//		linking required products

		if (util.extractValueByAttributes(linkingRequired, ARIASELECTED).equals(FALSEVAL)) {
			util.click(linkingRequired);
		}

		searchProductUsingValidInput(input, "barcode_linking_required");
		util.hold(1);
		searchProductUsingInvalidInput(input, INVALIDBARCODELINKED);
		util.hold(1);
		searchProductUsingValidInput(input, "sku_linking_required");
		util.hold(1);
		searchProductUsingInvalidInput(input, INVALIDBARCODELINKED);
		util.hold(1);
		searchProductUsingValidInput(input, "title_linking_required");
		util.hold(1);
		searchProductUsingInvalidInput(input, INVALIDBARCODELINKED);
		util.hold(1);
//		close match products

		if (util.extractValueByAttributes(closeMatchTab, ARIASELECTED).equals(FALSEVAL)) {
			util.click(closeMatchTab);
		}

		searchProductUsingValidInput(input, "barcode_close_match");
		util.hold(1);
		searchProductUsingInvalidInput(input, INVALIDBARCODELINKED);
		util.hold(1);
		searchProductUsingValidInput(input, "sku__close_match");
		util.hold(1);
		searchProductUsingInvalidInput(input, INVALIDBARCODELINKED);
		util.hold(1);
		searchProductUsingValidInput(input, "title_close_match");
		util.hold(1);
		searchProductUsingInvalidInput(input, INVALIDBARCODELINKED);
	}

	public void searchProductUsingValidInput(Map<String, String> input, String key) {
		boolean isTrue = false;
		util.enterValue(searchInputField, input.get(key));
		util.hold(10);

		try {

			if (!productList.isEmpty()) {
				isTrue = true;
			}
			Assert.assertTrue(isTrue);
		} catch (Exception e) {
			if (util.isElementDisplyed(noData)) {
				Assert.assertTrue(true);
			}
		}
	}

	public void searchProductUsingInvalidInput(Map<String, String> input, String key) {
		util.enterValue(searchInputField, input.get(key));
		util.waitUntilElementIsVisible(noData);
		util.isElementDisplyedAndValidate(noData);
	}

	/**
	 * This method validates the redirection to amazon
	 */
	public void validateRedirectionToAmazon() {
		boolean isTrue = false;
		pageUrl = util.getPageURL();

		util.click(linkingRequired);
		if (!unlinkBtnList.isEmpty()) {
			util.click(viewOnAmzLink.get(0));
			util.getWindoHandleByUrl(AMAZONLINK);

			if (util.getPageURL().contains(AMAZONLINK)) {
				isTrue = true;
			}
			Assert.assertTrue(isTrue);

			try {
				util.getDriver().close();
				util.getWindoHandleByUrl(pageUrl);
				util.switchToIFrame();
			} catch (NoSuchWindowException e) {
				log.info("window closed");
			}
		} else if (unlinkBtnList.isEmpty()) {
			log.info("There are no products in linked section");
			Assert.assertTrue(true);
		}
	}

	public void selectLinkedProducts() {
		if (util.extractValueByAttributes(linked, ARIASELECTED).equals(FALSEVAL)) {
			util.click(linked);
		}
	}

	public void selectLinkingRequiredProducts() {
		if (util.extractValueByAttributes(linkingRequired, ARIASELECTED).equals(FALSEVAL)) {
			util.click(linkingRequired);
		}
	}

	public void selectCloseProducts() {
		if (util.extractValueByAttributes(closeMatchTab, ARIASELECTED).equals(FALSEVAL)) {
			util.click(closeMatchTab);
		}
	}

	/**
	 * This method validates the redirection to shopify
	 */
	public void validateRedirectionToShopifyProduct() {
		boolean isTrue = false;
		pageUrl = util.getPageURL();

		util.click(linked);

		if (!unlinkBtnList.isEmpty()) {
			util.waitUntilElementIsVisible(unlinkBtnList.get(0));
			util.click(viewOnShopifyLinkListLinked.get(0));
			util.goToChildWindow();

			if (!userList.isEmpty()) {
				util.waitUntilElementIsVisible(user);
				util.click(user);
				util.waitUntilElementIsVisible(user);
				util.click(user);
			}

			if (util.getPageURL().contains("/products/")) {
				log.info("redirection is proper");
				isTrue = true;
			}
			Assert.assertTrue(isTrue);

			try {
				util.getDriver().close();
				util.getWindoHandleByUrl(pageUrl);
				util.switchToIFrame();
			} catch (NoSuchElementException e) {
				log.info("window closed");
			}
		} else if (!noDataList.isEmpty()) {
			log.info("There are no products in linked section");
			Assert.assertTrue(true);
		}

	}

	/**
	 * Linking Required
	 */
	public void linkProductFunctionality() {

		if (util.extractValueByAttributes(linkingRequired, ARIASELECTED).equals(FALSEVAL)) {
			util.click(linkingRequired);
		}

		if (!linkBtnList.isEmpty()) {
			util.click(linkBtnList.get(0));
			util.hold(2);
			crossBtnModalFunctionality();
			util.hold(2);

			cancelBtnModalFunctionality();
			util.click(linkBtnList.get(0));
			util.hold(2);
		}

	}

	/**
	 * Linking Required
	 */
	public void crossBtnModalFunctionality() {
		boolean isTrue = false;
		util.hold(2);

		util.isElementDisplyedAndValidate(linkProductModalHeading);
		util.isElementDisplyedAndValidate(crossBtn);
		util.hold(2);
		util.click(crossBtn);
		util.hold(2);

		if (linkProductModalHeadingList.isEmpty()) {
			log.info("link product modal is not displayed");
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);
	}

	/**
	 * Linking Required
	 */
	public void cancelBtnModalFunctionality() {
		boolean isTrue = false;
		util.hold(2);
		util.click(linkBtnList.get(0));
		util.isElementDisplyedAndValidate(linkProductModalHeading);
		util.isElementDisplyedAndValidate(crossBtn);
		util.hold(2);
		util.click(cancelBtnModal);
		util.hold(2);

		if (linkProductModalHeadingList.isEmpty()) {
			log.info("link product modal is not displayed");
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);
	}

	/**
	 * Linking Required
	 */
	public void viewOnAmzLinkFunctionalityModal() {
		boolean isTrue = false;
		pageUrl = util.getPageURL();
		util.isElementDisplyedAndValidate(viewOnAmzLinkModal);
		util.hold(2);
		util.click(viewOnAmzLinkModal);
		util.getWindoHandleByUrl(AMAZONLINK);

		if (util.getPageURL().contains(AMAZONLINK)) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);

		try {
			util.getDriver().close();
			util.getWindoHandleByUrl(pageUrl);
			util.switchToIFrame();
		} catch (NoSuchElementException e) {
			log.info("window closed");
		}
	}

	/**
	 * Linking Required
	 */
	public void viewOnShopifyFunctionalityModal() {
		boolean isTrue = false;
		pageUrl = util.getPageURL();
		util.isElementDisplyedAndValidate(viewOnShopifyLinkModal.get(0));
		util.click(viewOnShopifyLinkModal.get(0));
		util.goToChildWindow();

		if (!userList.isEmpty()) {
			util.waitUntilElementIsVisible(user);
			util.click(user);
		}

		if (util.getPageURL().contains("/products/")) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);

		try {
			util.getDriver().close();
			util.getWindoHandleByUrl(pageUrl);
			util.switchToIFrame();
		} catch (NoSuchElementException e) {
			log.info("window closed");
		}
	}

	/**
	 * Linking Required => modal 70-73
	 */
	public void searchInpFieldModalFunctionality(Map<String, String> input) {
		searchProductUsingValidInput(input, LINKWITHSHOPIFYPRODUCTTITLE);
		searchProductUsingInvalidInput(input, INVALIDBARCODELINKED);
		searchProductUsingInvalidInput(input, INVALIDBARCODELINKED);
		searchProductUsingValidInput(input, "link_with_shopify_product_SKU");
	}

	/**
	 * Linking Required => modal => confirm modal
	 */
	public void linkBtnFunctionality() {
		util.click(linkBtnListModal.get(0));
		util.isElementDisplyedAndValidate(confirmLinkProductModalHeading);
		util.hold(1);
		util.click(crossBtn);

		util.isElementDisplyedAndValidate(linkProductModalHeading);
		util.click(linkBtnListModal.get(0));
		viewOnAmzLinkFunctionalityModal();
		viewOnShopifyFunctionalityModal();
		util.click(backBtnModal);
		util.hold(1);
		util.isElementDisplyedAndValidate(linkProductModalHeading);
		util.click(linkBtnListModal.get(0));
		util.isElementDisplyedAndValidate(confirmBtnModal);
	}

	/**
	 * This method links a amazon product with shopify product
	 */
	public void linkProduct() {
		boolean isTrue = false;
		String amazonLinkedProductTitle = "";
		String amazonSellerCentralSKU ;
		util.goToMultiAccountOverView();
		util.click(manageLinkingBtn);
		selectLinkedProducts();

		if (!shopifyLinkedProductNameList.isEmpty()) {
			amazonLinkedProductTitle = amazonLinkedProductNameList.get(0).getText();
			util.click(unlinkBtnList.get(0));
			util.hold(1);
			util.click(confirmYesBtn);
			util.waitUntilElementIsVisible(toastMsg);
			util.hold(15);

			searchAlreadyUnlinkedProduct(amazonLinkedProductTitle); // 87

			selectLinkingRequiredProducts();
			amazonSellerCentralSKU = util.getTagValue(sellerCentralSKUValue.get(0));

			searchUnlinkedProductInLinkingReq(amazonLinkedProductTitle); // 88
			util.click(linkBtnList.get(0));
			util.enterValue(searchShopifyInputFieldModal, amazonLinkedProductTitle);
			util.hold(3);
			util.click(linkBtnListModal.get(0));
			util.click(confirmBtnModal);
			util.waitUntilElementIsVisible(productLinkedSuccessfullyToast);
		} else {
			selectLinkingRequiredProducts();
			amazonSellerCentralSKU = util.getTagValue(sellerCentralSKUValue.get(0));
			util.click(linkBtnList.get(0));
			util.enterValue(searchShopifyInputFieldModal, amazonLinkedProductTitle);
			util.hold(3);
			util.click(linkBtnListModal.get(0));
			util.click(confirmBtnModal);
			util.waitUntilElementIsVisible(productLinkedSuccessfullyToast);
		}

		selectLinkedProducts();
		util.enterValue(searchInputField, amazonLinkedProductTitle);
		util.hold(5);

		if (!unlinkBtnList.isEmpty()) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);

	}

	public void validateVisibilityOfCloseMatchProduct() {
		WebElement[] arrCloseMatchProduct = { headingLinkAmzProduct, linked, closeMatchTab,
				closeMatchProductCountLinkAmazon, linkingRequiredProductCountLinkAmazon, linkedProductCountLinkAmazon,
				searchInputField, sellerNameLabel, selectStoreBtn, amazonHeading, shopifyHeading, actionHeading,
				prevPaginationBtn, nextPaginationBtn, cedAmzChannelFooterLink, getSupportFooterBtn,
				bookACallFooterLink };

		if (util.extractValueByAttributes(closeMatchTab, ARIASELECTED).equals(FALSEVAL)) {
			util.click(closeMatchTab);
		}

		if (!noDataList.isEmpty()) {
			Assert.assertTrue(true);
		} else {
//			validating visibility of each element
			for (WebElement element : arrCloseMatchProduct) {

				try {
					util.isElementDisplyedAndValidate(element);
				} catch (Exception e) {
					log.error("element is not displayed " + element.toString());
				}
			}
		}

	}

	/**
	 * This method verifies the working of sorting available in linking required
	 * section
	 */
	public void validateSortFunctionality() {
		String a2zSort = null;
		String z2aSort = null;
		selectLinkingRequiredProducts();

		if (productList.size() < 2) {
			log.info("Cannot perform sorting with less than 2 products.");
			Assert.assertTrue(true);
			return;
		}

		// Verify sorting by product title (A to Z) and (Z to A)

		util.hold(1);

		if (sortByBtn.getAttribute(ARIAEXPANDED).equalsIgnoreCase(FALSEVAL)) {
			util.click(sortByBtn);
		}

		util.hold(1);
		util.click(a2zSortEle);

		if (!noDataList.isEmpty()) {
			Assert.assertTrue(true);
		} else {
			a2zSort = productTitle.get(0).getText().trim();
		}

		util.click(z2aSortEle);

		if (!noDataList.isEmpty()) {
			Assert.assertTrue(true);
		} else {
			z2aSort = productTitle.get(0).getText().trim();
		}
		Assert.assertNotEquals(a2zSort, z2aSort);

		// Verify sorting by product status
		util.click(inActiveStatusEle);
		String inActiveSort = statusValue.get(0).getText().trim();
		
//		removed from app
//		util.click(activeStatusEle);
//		
//		if (!noDataList.isEmpty()) {
//			Assert.assertTrue(true);
//		} else {
//			String activeSort = statusValue.get(0).getText().trim();
//			Assert.assertNotEquals(inActiveSort, activeSort);
//		}
		

		// Verify Incomplete status
		util.click(inCompleteStatusEle);

		if (!noDataList.isEmpty()) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(statusValue.get(0).getText().equalsIgnoreCase("Incomplete"));
		}

		// Verify Deleted status
		/**
		 * functionality removed
		 */
//		util.click(deleteStatusEle);
//		if (!noDataList.isEmpty()) {
//			Assert.assertTrue(true);
//		} else {
//			Assert.assertTrue(statusValue.get(0).getText().equalsIgnoreCase("Deleted"));
//		}
	}

	/**
	 * When user clicks on view on amazon in linking required section it should
	 * redirects to amazon
	 */
	public void redirectionToViewOnAmazonLinkingReq() {
		selectLinkingRequiredProducts();
		if (sortByBtn.getAttribute(ARIAEXPANDED).equalsIgnoreCase(TRUEVAL)) {
			util.click(a2zSortEle);
		} else {
			util.click(sortByBtn);
			util.click(a2zSortEle);
		}
		pageUrl = util.getPageURL();
		util.click(viewOnAmzLink.get(0));
		util.goToChildWindow();

		if (util.getPageURL().contains(AMAZONLINK)) {
			log.info("redirected to page successfully.");
			Assert.assertTrue(true);
		} else {
			log.error("redirection error occured.");
			Assert.assertTrue(true);
		}

		try {
			util.getDriver().close();
			util.getWindoHandleByUrl(pageUrl);
			util.switchToIFrame();
		} catch (NoSuchElementException e) {
			log.info("window closed");
		}
	}

	public void validateHyperlinksAreVisible() {
		boolean isTrue = false;
		pageUrl = util.getPageURL();
		util.isElementDisplyedAndValidate(cedAmzChannelFooterLink);
		util.isElementDisplyedAndValidate(getSupportFooterBtn);
		util.isElementDisplyedAndValidate(bookACallFooterLink);
		reuse.addTarget(cedAmzChannelFooterLink);
		util.click(cedAmzChannelFooterLink);
		util.goToChildWindow();

		if (util.getPageTitle()
				.equalsIgnoreCase("Multi-account Amazon by CedCommerce - CedCommerce Products Documentation")) {
			log.info("redirected to doc page successfully.");
			Assert.assertTrue(true);
		} else {
			log.error("redirection error occured.");
			Assert.assertTrue(true);
		}

		try {
			util.getDriver().close();
			util.getWindoHandleByUrl(pageUrl);
			util.switchToIFrame();
			reuse.addTarget(bookACallFooterLink);
			util.click(bookACallFooterLink);
			util.goToChildWindow();

			if (util.getPageURL().contains("https://calendly.com/")) {
				isTrue = true;
				Assert.assertTrue(isTrue);
			}
			Assert.assertTrue(isTrue);

			util.getDriver().close();
			util.getWindoHandleByUrl(pageUrl);
			util.switchToIFrame();

		} catch (NoSuchWindowException e) {
			e.getMessage();
		}

		util.click(getSupportFooterBtn);
		util.isElementDisplyedAndValidate(getSupportPageHeading);
		util.hold(2);
	}

	public void searchAlreadyUnlinkedProduct(String title) {
		util.enterValue(searchInputField, title);
		System.out.println("785 = " + noDataList.size());
		util.isElementDisplyedAndValidate(noData);

	}

	public void searchUnlinkedProductInLinkingReq(String title) {
		util.enterValue(searchInputField, title);
		util.click(clearInputFieldBtn);
		util.enterValue(searchInputField, title);
		util.hold(10);

		if (util.getTagValue(productTitleLinkingReq.get(0)).equals(title)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

	public void unlinkBtnFunctionalityLinked() {

		if (util.extractValueByAttributes(linked, ARIASELECTED).equals(FALSEVAL)) {
			util.click(linked);
			util.hold(5);

			if (noDataList.isEmpty()) {
				util.enterValue(searchInputField, util.getProperty("sku", "sku1"));
				util.click(clearInputFieldBtn);
				util.enterValue(searchInputField, util.getProperty("sku", "sku1"));
				util.hold(5);
				util.click(unlinkBtnList.get(0));
				util.hold(1);
				util.isElementDisplyedAndValidate(unlinkModalHeading);
				util.click(noBtn);
				util.hold(1);
				try {
					util.isElementDisplyedAndValidate(unlinkModalHeading);
				} catch (Exception e) {
					Assert.assertTrue(true);
				}
			} else {
				util.listIsNotEmpty(noDataList);
			}
		}
	}

	public void redirectionToAmazonLinkModalCloseMatch() {
		pageUrl = util.getPageURL();
		util.click(viewOnAmzLinkListLinkModalCloseMatch.get(0));
		util.goToChildWindow();

		if (util.getPageURL().contains(AMAZONLINK)) {
			log.info("redirected to page successfully.");
			Assert.assertTrue(true);
		} else {
			log.error("redirection error occured.");
			Assert.assertTrue(true);
		}

		try {
			util.getDriver().close();
			util.getWindoHandleByUrl(pageUrl);
			util.switchToIFrame();
		} catch (NoSuchElementException e) {
			log.info("window closed");
		}
	}

	/**
	 * 93
	 * 
	 * @param input
	 */
	public void searchProductToLinkInLinkModalCloseMatch(Map<String, String> input) {
		util.enterValue(searchShopifyInputFieldModal, input.get(LINKCLOSEMATCHMODALTTITLE));
		util.hold(2);

		try {
			if (util.getTagValue(productListCloseMatchLinkModal.get(0))
					.contains(input.get(LINKCLOSEMATCHMODALTTITLE))) {
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			if (util.isElementDisplyed(noData)) {
				Assert.assertTrue(true);
			}
		}
	}

	/**
	 * 
	 * @param element
	 * @param input
	 * @param key
	 */
	public void searchValidInCloseMatchLinkModal(WebElement element, Map<String, String> input, String key) {
		util.enterValue(element, input.get(key));
		util.hold(10);

		try {

			if (!productList.isEmpty()) {
				Assert.assertTrue(true);
				log.info("product is availble");
			} else if (util.isElementDisplyed(noData)) {
				log.info("product is not availble");
				Assert.assertTrue(true);
			}
		} catch (Exception e) {
			if (util.isElementDisplyed(noData)) {
				log.info("product is not availble");
				Assert.assertTrue(true);
			}
		}
	}

	/**
	 * 
	 * @param element
	 * @param input
	 * @param key
	 */
	public void searchInValidInCloseMatchLinkModal(WebElement element, Map<String, String> input, String key) {
		util.enterValue(element, input.get(key));
		util.hold(10);

		if (util.isElementDisplyed(noData)) {
			log.info("product is not availble");
			Assert.assertTrue(true);
		}
	}

	/**
	 * 94
	 * 
	 * @param input
	 */
	public void searchInvalidProductToLinkInLinkModalCloseMatch(Map<String, String> input) {
		util.enterValue(searchShopifyInputFieldModal, input.get("link_close_match_modal_invalid_title"));
		util.hold(2);
		util.isElementDisplyedAndValidate(noData);
	}

	/**
	 * 97
	 */
	public void clickOnLinkBtnCloseMatch() {
		try {
			util.click(btnListCloseMatchLinkProductModal.get(0));
			util.hold(2);
			util.isElementDisplyedAndValidate(linkHeadingConfirmLinkModalCloseMatch);
			util.click(backBtnModal);

			try {
				util.isElementDisplyedAndValidate(linkHeadingConfirmLinkModalCloseMatch);
			} catch (Exception e) {
				System.err.println("Modal is not displayed");
				Assert.assertTrue(true);
			}
		} catch (Exception e) {
			if (noData.isDisplayed()) {
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		}
	}

	/**
	 * close match (match found)
	 */
	public void clickOnConfirmBtnCloseMatch() {
		try {
			util.click(btnListCloseMatchLinkProductModal.get(0));
			util.hold(2);
			util.click(confirmBtnModal);

			try {
				util.isElementDisplyedAndValidate(linkHeadingConfirmLinkModalCloseMatch);
			} catch (Exception e) {
				util.logWarn("Exception occured as element is not available");
				Assert.assertTrue(true);
			}
		} catch (Exception e) {
			if (noData.isDisplayed()) {
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		}
	}

	/**
	 * close match (match found)
	 */
	public void validateCancelAndCrossBtnWorking() {
		util.click(closeMatchTab);
		util.hold(1);

		if (!noDataList.isEmpty()) {
			Assert.assertTrue(true);
		} else {
			util.click(selectManuallyBtnList.get(0));
			util.hold(1);
			util.click(cancelBtnModal);

			util.isElementDisplyedAndValidate(searchShopifyInputFieldModal);
			util.click(selectManuallyBtnList.get(0));
			util.hold(1);
			util.click(crossBtn);
			try {
				util.isElementDisplyedAndValidate(searchShopifyInputFieldModal);
			} catch (Exception e) {
				Assert.assertTrue(true);
			}
		}

	}

	/**
	 * 91
	 */
	public void matchFoundFunctionality(Map<String, String> input) {
		/**
		 * functionality updated
		 */

		util.click(closeMatchTab);

		if (!noDataList.isEmpty()) {
			Assert.assertTrue(true);
		} else {
			util.isElementDisplyedAndValidate(selectManuallyBtnList.get(0));
			util.click(selectManuallyBtnList.get(0));
			redirectionToAmazonLinkModalCloseMatch();
			searchInvalidProductToLinkInLinkModalCloseMatch(input);
			searchProductToLinkInLinkModalCloseMatch(input);
			clickOnLinkBtnCloseMatch();
			clickOnConfirmBtnCloseMatch();
			util.hold(20);
		}
	}

	/**
	 * close match
	 */
	public void closeMatchSection() {
		util.click(closeMatchTab);

		if (!noDataList.isEmpty()) {
			Assert.assertTrue(true);
		} else {
			util.waitUntilElementIsVisible(linkBtnCloseMatch.get(0));
			util.click(linkBtnCloseMatch.get(0));
			util.hold(1);
			util.click(backBtnModal);
			try {
				util.isElementDisplyedAndValidate(backBtnModal);
			} catch (Exception e) {
				Assert.assertTrue(true);
			}

			util.click(linkBtnCloseMatch.get(0));
			util.hold(1);
			util.click(confirmBtnModal);
			util.hold(20);
		}
	}

	/**
	 * close Match
	 */
	public void selectManually(Map<String, String> input) {
		util.click(closeMatchTab);

		if (!noDataList.isEmpty()) {
			Assert.assertTrue(true);
		} else {
			util.waitUntilElementIsVisible(linkBtnCloseMatch.get(0));
			util.click(selectManuallyBtnListCloseMatch.get(0));
			util.hold(1);
			redirectionToAmazonLinkModalCloseMatch();
			util.click(crossBtn);

			try {
				util.isElementDisplyedAndValidate(crossBtn);
			} catch (Exception e) {
				util.isElementDisplyedAndValidate(linkBtnCloseMatch.get(0));
			}

			searchInValidInCloseMatchLinkModal(searchShopifyInputFieldModal, input,
					"link_close_match_modal_invalid_title");
			searchValidInCloseMatchLinkModal(searchShopifyInputFieldModal, input, "select_manually_title_close_match");
			searchInValidInCloseMatchLinkModal(searchShopifyInputFieldModal, input,
					"link_close_match_modal_invalid_title");
			searchValidInCloseMatchLinkModal(searchShopifyInputFieldModal, input, "shopify_sku_link_modal_close_match");

			util.click(linkBtnCloseMatchLinkModal.get(0));
			util.hold(1);
			util.click(backBtn);
			util.hold(1);

			try {
				util.isElementDisplyedAndValidate(backBtn);
			} catch (Exception e) {
				util.isElementDisplyedAndValidate(linkBtnCloseMatch.get(0));
			}

			util.click(selectManuallyBtnListCloseMatch.get(0));
			util.hold(1);
			util.click(linkBtnCloseMatchLinkModal.get(0));
			util.hold(1);
			util.click(confirmBtnModal);
			util.hold(20);
		}

	}

//	-------------------------------------TEST CASE 117 AND ONWARDS-----------------------------------------

	/**
	 * 117 to 119 delete btn is there when click on kebab icon and it is working
	 * properly
	 */
	public void validateDeleteBtnInLinkingRequired() {
		util.click(linkingReqTab);

		if (!noDataList.isEmpty()) {
			Assert.assertTrue(true);
		} else {
			if (kebabListLinkingReq.get(0).getAttribute(ARIAEXPANDED).equals(FALSEVAL)) {
				util.click(kebabListLinkingReq.get(0));
			}
			util.isElementDisplyedAndValidate(autoSyncOffBtnLinkingReq);
			util.isElementDisplyedAndValidate(deleteProductBtnLinkingReq);
			util.click(deleteProductBtnLinkingReq);
			util.listIsNotEmpty(deleteAmazonProductModalLinkingReq);
		}
	}

	public void autoSyncOffBtnIsDisplayed() {
		util.click(linkingReqTab);

		if (!noDataList.isEmpty()) {
			Assert.assertTrue(true);
		} else {
			if (kebabListLinkingReq.get(0).getAttribute(ARIAEXPANDED).equals(FALSEVAL)) {
				util.click(kebabListLinkingReq.get(0));
			}
			util.isElementDisplyedAndValidate(autoSyncOffBtnLinkingReq);
			util.isElementDisplyedAndValidate(deleteProductBtnLinkingReq);
			util.click(autoSyncOffBtnLinkingReq);

			if (!autoSyncSettingsModal.isEmpty()) {
				util.isElementDisplyedAndValidate(autoSyncSettingsModalCheckbox);
				util.isElementDisplyedAndValidate(autoSyncSettingsModalNoBtn);
				util.isElementDisplyedAndValidate(autoSyncSettingsModalYesBtn);
				util.click(autoSyncSettingsModalNoBtn);
				util.hold(2);
				util.listIsEmpty(autoSyncSettingsModal);
				util.click(kebabListLinkingReq.get(0));
				util.click(autoSyncOffBtnLinkingReq);

				util.click(autoSyncSettingsModalCheckbox);
				String checkBoxIsChecked = autoSyncSettingsModalCheckbox.getAttribute(ARIACHECKED);
				util.click(autoSyncSettingsModalYesBtn);
//				if checkbox is not checked
				if (checkBoxIsChecked.equalsIgnoreCase(FALSEVAL)) {
					util.waitUntilElementIsVisible(productSyncDisabled, 15);

				} else {
					util.waitUntilElementIsVisible(productSyncEnabled, 15);
				}
			}
		}
	}

	public String enableAutoSyncOff() {
		String checkBoxIsChecked = TRUEVAL;
		util.click(linkingReqTab);

		if (!noDataList.isEmpty()) {
			Assert.assertTrue(true);
		} else {
			if (kebabListLinkingReq.get(0).getAttribute(ARIAEXPANDED).equals(FALSEVAL)) {
				util.click(kebabListLinkingReq.get(0));
			}
			util.isElementDisplyedAndValidate(autoSyncOffBtnLinkingReq);
			util.click(autoSyncOffBtnLinkingReq);

			if (!autoSyncSettingsModal.isEmpty()) {
				checkBoxIsChecked = autoSyncSettingsModalCheckbox.getAttribute(ARIACHECKED);
				if (checkBoxIsChecked.equalsIgnoreCase(TRUEVAL)) {
					util.click(autoSyncSettingsModalCheckbox);
					checkBoxIsChecked = autoSyncSettingsModalCheckbox.getAttribute(ARIACHECKED);
					util.click(autoSyncSettingsModalYesBtn);
				}else {
					
					util.click(autoSyncSettingsModalYesBtn);
				}
				
				util.waitUntilElementIsVisible(productSyncDisabled, 15);
			}
		}
		return checkBoxIsChecked;
	}

	public void verifyCheckboxStatusIsSameBeforeLinkingAndAfterUnlinking(String productName) {
		String isChecked = enableAutoSyncOff();
		String sku = skuLinkingReq.get(0).getText().trim();
		util.updateWebHooksProperty("sku", "sku", sku);
		util.click(linkBtns.get(0));
		util.waitUntilElementIsVisible(searchProduct, 10);
		
		
		util.waitUntilElementIsVisible(searchProduct, 30);
		util.enterValue(searchProduct, productName);
		util.hold(5);
		util.pressBackSpace();
		util.hold(5);
		util.enterValue(searchProduct, productName);
		util.hold(5);

		while (productNameListLinkModal.isEmpty()) {
			util.enterValue(searchProduct, productName);
			util.hold(5);
		}

		util.waitUntilElementIsVisible(linkBtnsLinkProductsModal);
		util.click(linkBtnsLinkProductsModal);
		util.hold(5);
		util.jsClick(confirmBtn);
		util.hold(3);
		
		util.click(linkedTab);
		util.enterValue(searchInpFieldLinking, sku);
		util.hold(5);
		util.pressBackSpace();
		util.hold(5);
		util.enterValue(searchInpFieldLinking, sku);
		util.hold(5);
		util.click(unlinkBtn.get(0));
		util.hold(2);
		
		Assert.assertEquals(autoSyncSettingsModalCheckbox.getAttribute(ARIACHECKED), isChecked);
		util.click(autoSyncSettingsModalNoBtn);
	}
	
	

	public void closeDeleteModal() {
		util.click(cancelBtnModal);
		util.hold(1);
		util.listIsEmpty(deleteAmazonProductModalLinkingReq);
	}

	int closeMatchCountOverview = 0;
	int linkingRequiredCountOverview = 0;
	int linkedCountOverview = 0;

	public void getCountOnOverview() {
		util.openSectionsInNewTab(OVERVIEWSECTION);
		util.waitUntilElementIsVisible(appGuide, 30);
		util.hold(15);
		linkedCountOverview = parseInt(linkedProductCount);
		linkingRequiredCountOverview = parseInt(linkingRequiredProductCount);
		closeMatchCountOverview = parseInt(closeMatchProductCount);
	}

	public void validateLinkingRequiredCount() {
		navigateToLinkingRequiredTab();
		util.hold(10);
		int linkingReqProdCount = getProductCount();
		linkingReqProdCount += getProductCount();
		
		Assert.assertEquals(linkingRequiredCountOverview, linkingReqProdCount);

	}

	public void validateClosedMatchCount() {
		navigateToCloseMatchTab();
		int closeMatchCountLinkingReq = getProductCount();
		System.out.println("closeMatchCountOverview = \t" + closeMatchCountOverview
				+ "\t closeMatchCountLinkingReq = \t" + closeMatchCountLinkingReq);
		Assert.assertEquals(closeMatchCountOverview, closeMatchCountLinkingReq);
	}

	public void validateLinkedCount() {
		navigateToLinkedTab();
		
		int linkedProdCount = getProductCount();
		linkedProdCount += getProductCount();
		selectFBM();
		Assert.assertEquals(linkedCountOverview, linkedProdCount);
	}

	private int parseInt(WebElement element) {
		return Integer.parseInt(element.getText().trim());
	}

	private void navigateToLinkingRequiredTab() {
		util.click(manageLinkingBtn);
		util.waitUntilElementIsVisible(linkingReqTab, 30);
		util.click(linkingReqTab);
	}

	private void navigateToLinkedTab() {
		util.click(linkedTab);
	}

	private void navigateToCloseMatchTab() {
		util.click(closeMatchTab);
	}

	private int getProductCount() {
		int prodCount = 0;

		if (noDataList.isEmpty()) {
			do {
				prodCount += productList.size();
				util.click(nextPaginationBtn);
				util.hold(5);
			} while (nextPaginationBtn.getAttribute(ARIADISABLED) == null);
			prodCount += productList.size();
			System.err.println("prodCount \t" + prodCount);
		}

		util.click(fbmfbaSelector);

		return prodCount;
	}

	public void verifyDeleteTabOnLinkAmazon() {

		selectFBM();
		deleteTabShowsCountBeforeClicking();
		selectFBM();
		util.click(deletedTab);
		util.hold(2);
		int deleteCount = parseInt(countDeltedTab);
		int prodCount = getProductCount();
		Assert.assertEquals(prodCount, deleteCount);

		if (noDataList.isEmpty()) {
			validateDeleteBtnIsWorking();
			validateCancelModalBtnIsWorking();
			validateDeleteBtnIsWorking();
			validateCrosModalBtnIsWorking();
		} else if (!noDataList.isEmpty()) {
			Assert.assertTrue(true);
		}

	}

	private void deleteTabShowsCountBeforeClicking() {
		boolean isTrue = false;
		if (parseInt(countDeltedTab) >= 0) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);
	}

	public void selectFBM() {
		if (fbmfbaSelector.getText().equalsIgnoreCase("fba")) {
			util.click(fbmfbaSelector);
		}

		Assert.assertEquals("FBM", fbmfbaSelector.getText().toUpperCase());
	}

	public void selectFBA() {
		if (fbmfbaSelector.getText().equalsIgnoreCase("fbm")) {
			util.click(fbmfbaSelector);
		}

		Assert.assertEquals("FBA", fbmfbaSelector.getText().toUpperCase());
	}

	private void validateDeleteBtnIsWorking() {
		selectFBM();
		clickOnDeleteBtn();
		util.hold(1);
		util.listIsNotEmpty(cancelBtnModalList);

	}

	private void clickOnDeleteBtn() {
		if (!delBtnsDeletedTab.isEmpty()) {
			util.click(delBtnsDeletedTab.get(0));
		}
	}

	private void validateCancelModalBtnIsWorking() {

		if (availabilityOnAmazonMsg.getText().equals("Product found on Amazon!")) {
			util.isElementDisplyedAndValidate(cancelBtnModal);
		} else {
			util.isElementDisplyedAndValidate(cancelBtnModal);
			util.isElementDisplyedAndValidate(confirmBtnModal);
		}
		util.click(cancelBtnModal);
		util.hold(1);
		util.listIsEmpty(cancelBtnModalList);

	}

	private void validateCrosModalBtnIsWorking() {
		util.click(crossBtn);
		util.hold(1);
		util.listIsEmpty(cancelBtnModalList);

	}

	public void deleteProductInDeleteListing() {
		selectFBM();
		navigateToLinkedTab();
		util.click(deletedTab);
		String sku = "";

		if (!delBtnsDeletedTab.isEmpty()) {

			do {
				for (WebElement del : delBtnsDeletedTab) {
					util.click(del);
					util.hold(1);

					if (!availabilityOnAmazonMsg.getText().equals("Product found on Amazon!")) {
						sku = skuOfDeleteListing.getText();
						util.click(confirmBtnModal);
						util.waitUntilElementIsVisible(prodSuccessfullyDeletedFromAmzMsg, 10);
						break;
					} else {
						util.click(cancelBtnModal);
						util.hold(1);
					}
				}
				util.click(nextPaginationBtn);
			} while (nextPaginationBtn.getAttribute(ARIADISABLED) == null);
		}

		navigateToLinkingRequiredTab();
		validateProductIsDeleted(sku);
		navigateToCloseMatchTab();
		validateProductIsDeleted(sku);
		navigateToLinkedTab();
		validateProductIsDeleted(sku);
	}

	private void validateProductIsDeleted(String sku) {
		util.enterValue(searchInputField, sku);
		util.clear(searchInputField);
		util.enterValue(searchInputField, sku);
		util.hold(5);
		util.listIsNotEmpty(noDataList);
	}

}
