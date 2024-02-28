package com.ami.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ami.pageobjects.reusableclasses.ReusableMethods;
import com.ami.resources.Utilities;

public class ValueMappingPage extends DefaultTemplatePageOR {
	Utilities util;
	CustomTemplatePage ctp;
	ReusableMethods reuse;
	ProductListingPage plp;

	public ValueMappingPage(Utilities util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
		ctp = new CustomTemplatePage(util);
		reuse = new ReusableMethods(util);
		plp = new ProductListingPage(util);
	}

	public void createTemplateForValueMapping(String category, String key) {
		reuse.clickOnSettings();
//		create simple product.
		ctp.gotoCreateNewProduct();
		reuse.goToSettings();
		reuse.openProductTemplatesPage();
		enterTemplateName(CreateShopifyProduct.nameOfProduct);
		searchAndSelectCategory(category, key);
		enterHandlingTime();
		reuse.fetchShopifyProdByAdvanceSelection();
		reuse.validateProductIsImportedOnAppAndSave();
		util.waitUntilElementIsVisible(mapValuesBtn, 150);
		util.click(doItLaterBtn);
//		assign a category (not default) => search gift card => select 1st category
//		if any required attribute contains set shopify attribute/set amazon recommendation we can select some value for value mapping.
//		assign a template to it
//		
	}

	public void enterTemplateName(String templateName) {
		util.enterValue(templateNameInputField, templateName);
	}

	public void searchAndSelectCategory(String category, String key) {
		util.waitUntilElementIsClickable(searchCategoryInputField, 60);
		util.enterValue(searchCategoryInputField, category);
		util.click(searchCategoryBtn);
		util.waitUntilListIsVisible(clearSearchBtn, 30);
		util.click(searchedCategoriesList.get(0));

		for (WebElement shopifyAttr : selectShopifyAttribute) {
			util.click(shopifyAttr);

			if (!setCustomAttributeList.isEmpty()) {
				util.click(setCustomAttribute);
				int size = setCustomInputFieldList.size();
				util.enterValue(setCustomInputFieldList.get(size - 1), "test");
			} else if (!setAmazonRecommendationOptionList.isEmpty()) {
				util.click(setShopifyAttributeOption);
				int size = setShopifyAttributeSelectorInput.size();
				util.click(setShopifyAttributeSelectorInput.get(size - 1));
				util.hold(2);
				ctp.selectSetShopifyAttribute(key);
			}
		}
	}

	public void enterHandlingTime() {
		util.enterValue(handlingTimeInputField, "3");
	}

	public void mapAttributeLinkIsDisplayed(String name) {
		util.enterValue(searchTemplateInputField, name);
		List<WebElement> list = util.getDriver()
				.findElements(By.xpath("//h6[text()='" + name + "']/following-sibling::button"));
		Assert.assertTrue(!list.isEmpty(), "Map Attribute link is not displayed.");
	}

	public void mapAttributeLinkIsNotDisplayed(String name) {
		util.enterValue(searchTemplateInputField, name);
		List<WebElement> list = util.getDriver()
				.findElements(By.xpath("//h6[text()='" + name + "']/following-sibling::button"));
		Assert.assertTrue(list.isEmpty(), "Map Attribute link is displayed.");
	}
	
	public void zeroProdAssignIsDisplayed() {
		util.listIsNotEmpty(zeroProdBadge);
	}

	public void openValueMapping() {
		util.waitUntilElementIsVisible(mapAttributeValuesList.get(0), 30);
		util.click(mapAttributeValuesList.get(0));
		util.waitUntilElementIsVisible(valueMappingHeading, 30);
	}

	public void verifyAttributeIsMapped() {
		String country = "India";
		openValueMapping();
		mapCorrespondingValue(country);
		clickOnSaveValBtnValMapping();
		util.hold(30);
	}

	public void selectAttributeMatchingToAmzVal() {
		openValueMapping();

	}

	public void mapCorrespondingValue(String country) {
		if (correspondingAmzAttributeValueMapping.getAttribute(ARIAEXPANDED).equals(FALSEVAL)) {
			util.click(correspondingAmzAttributeValueMapping);
		}

		util.hold(5);
		util.selectByVisibleText(selectAmzValueSelectValueMapping, country);

	}

	public void mapValuesForVariantProduct() {
		int j = 0;
		for (int i = 0; i < correspondingAmzAttributeValueMappingList.size(); i++) {
//			get text of Corresponding amazon attribute
			String text = correspondingAmzAttributeNameList.get(i).getText();
//			open all Corresponding amazon attribute
			if (correspondingAmzAttributeValueMappingList.get(i).getAttribute(ARIAEXPANDED).equals(FALSEVAL)) {
				util.click(correspondingAmzAttributeValueMappingList.get(i));
			}

			util.hold(5);

			List<WebElement> eleList = util.getDriver()
					.findElements(By.cssSelector("div.custom__value--heading+div>div"));

			int startJ = j;

			for (j = startJ; j < eleList.size(); j++) {
				if (eleList.get(j).isDisplayed() && !text.contains("country_of_origin")) {
					util.selectByVisibleText(selectAmzValueSelectValueMappingList.get(j), "CM");
					util.hold(1);
				} else if (eleList.get(j).isDisplayed() && text.contains("country_of_origin")) {
					util.selectByVisibleText(selectAmzValueSelectValueMappingList.get(j), "India");
					util.hold(1);
				}

			}
			
			if(saveValuesBtnValueMapping.get(i).getAttribute(ARIADISABLED).equals(FALSEVAL)) {
				util.click(saveValuesBtnValueMapping.get(i));
				util.hold(3);
			}
			
		}
	}

	public void mapMultipleCorrespondingValue(String val) {
		for (WebElement valueMap : correspondingAmzAttributeValueMappingList) {

			if (valueMap.getAttribute(ARIAEXPANDED).equals(FALSEVAL)) {
				util.click(valueMap);
			}

			util.hold(3);

			for (int i = 0; i < selectAmzValueSelectValueMappingList.size(); i++) {

				System.out.println(selectAmzValueSelectValueMappingList.get(i).isDisplayed());

				if (selectAmzValueSelectValueMappingList.get(i).isDisplayed()) {
					util.selectByVisibleText(selectAmzValueSelectValueMappingList.get(i), val);
				}
			}

		}

	}

	public void clickOnSaveValBtnValMapping() {
		if (saveValuesBtnValueMapping.get(0).getAttribute(ARIADISABLED).equals(FALSEVAL)) {
			util.click(saveValuesBtnValueMapping.get(0));
		}
	}

	public void verifyFeedIsGeneratedOnListing() {
		util.hold(5);
//		util.openSectionsInNewTab("listings");
		reuse.openSectionInNewTab(overViewNavigation, LISTING);
		plp.searchAndSelectProduct(CreateShopifyProduct.nameOfProduct);
		util.hold(2);
		uploadProduct();
		reuse.clickOnActivityFeedBtn();
	}

	public void uploadProduct() {
		util.hold(2);

		util.jsClick(productListingCheckboxList.get(0));

		if (!productListingCheckboxList.get(0).isSelected()) {
			util.actionClick(productListingCheckboxList.get(0));
		}

		util.hold(1);
		util.click(selectActionsBtn);
		util.hold(1);
		util.click(uploadProductBtnSelectActions);
		util.hold(1);
		util.click(confirmBtnModal);
		util.waitUntilElementIsVisible(prodUpdateInitiatedMsg, 30);
		util.hold(3);

	}

	public void validateCountryInFeeds(String countryName) {
		boolean isTrue = false;

		if (noFeedsFound.isEmpty()) {
			for (WebElement country : countryListInFeedsModal) {
				if (country.getText().equalsIgnoreCase(countryName)) {
					isTrue = true;
				}
			}
		}

		util.click(crossBtnModal);

		Assert.assertTrue(isTrue, "country name not found.");
	}

	public void validateMappedValuesInFeedsForVariant(String key, String expectedVal) {

		if (noFeedsFound.isEmpty()) {
			List<String> mappedValsInFeed = mappedValue(key);
			for(String val : mappedValsInFeed) {
				Assert.assertEquals(val, expectedVal);
			}
		}
	}

	private List<String> mappedValue(String key) {
		List<String> val = new ArrayList<>();
		switch (key) {
		case "width":

			for (WebElement ele : itemWidthUnitOfMeasureListInFeedsModal) {
				val.add(ele.getText());
			}
			return val;
		case "height":
			for (WebElement ele : itemHeightUnitOfMeasureListInFeedsModal) {
				val.add(ele.getText());

			}
			return val;

		case "length":

			for (WebElement ele : itemLengthUnitOfMeasureListInFeedsModal) {
				val.add(ele.getText());

			}
			return val;

		case "country":

			for (WebElement ele : variantCountryListInFeedsModal) {
				val.add(ele.getText());
			}
			return val;

		default:
			break;
		}
		return val;
	}

	public void editShopifyAttributeValueMapping(String name, String category, String key) {
		util.goToSection(SETTINGSSECTION);
		reuse.searchAndOpenEditTemplatePage(name);

		searchAndSelectCategory(category, key);
		reuse.clickOnSave();
		util.waitUntilElementIsVisible(mapValuesBtn, 150);
	}

	public void verifyProductIsNotEligibleForValueMapping() {
		util.isElementDisplyedAndValidate(notEligible);
	}

	public void verifyValueMappingLinkIsNotGenerated(String templateName, String category, String attribute) {
		editShopifyAttributeValueMapping(templateName, category, attribute);
		verifyProductIsNotEligibleForValueMapping();
		reuse.closeModal();
		mapAttributeLinkIsNotDisplayed(templateName);
	}
	
	public void waitUntilMapValueBtnIsDisplayed() {
		util.waitUntilElementIsVisible(mapValuesBtn, 150);
	}

	public void createTemplateForVariantProductValueMapping(String category, String key) {
		ctp.gotoCreateNewVariantProduct();
		reuse.goToSettings();
		reuse.openProductTemplatesPage();
		enterTemplateName(CreateShopifyProduct.nameOfProduct);
		searchAndSelectCategory(category, key);
		enterHandlingTime();
		reuse.fetchShopifyProdByAdvanceSelection();
		reuse.validateProductIsImportedOnAppAndSave();
		util.waitUntilElementIsVisible(mapValuesBtn, 150);
		util.click(doItLaterBtn);
	}

}
