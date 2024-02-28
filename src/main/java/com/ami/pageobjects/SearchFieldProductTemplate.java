package com.ami.pageobjects;

import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ami.resources.Utilities;

public class SearchFieldProductTemplate extends SearchFieldProductTemplateOR {
	Random random = new Random();
	String templateNameJson = "template_for_search_input_field";
	String templateName = "";

	Utilities util;
	
	public SearchFieldProductTemplate(Utilities util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);
	}

	/**
	 * creates name that contains random number.
	 * 
	 * @param input
	 * @return
	 */
	public String createRandomName(Map<String, String> input) {
		SecureRandom secureRandom = new SecureRandom();
		int randomNum = secureRandom.nextInt();
        return input.get("new_template_name") + randomNum;
	}

	public void selectAmazonRecommendationValueFromDropDown() {
		util.hold(1);
		util.jsClick(setAmazonRecommendationOptionNew);
	}

	public void clickOnSave() {
		util.switchToDefaultContent();
		util.click(saveBtn);
		util.switchToIFrame();
	}

	public void selectDefaultcategoryAndRequireAttribute() {
		util.click(defaultCategory);
		util.waitUntilElementIsVisible(reqAttributeHeading);
		util.hold(1);
		util.actionClick(defaultSelectAttribute);
		util.hold(1);
		util.jsClick(setAmazonRecommendation);
		util.hold(1);
		util.actionClick(setAmazonRecommendationSelector);
	}

	public void productTemplatesIsClickable() {
		util.click(productTemplates);
		util.isElementDisplyedAndValidate(searchTemplateInputField);
	}

	/**
	 * validate search bar is visible and when
	 */
	public void searchBarIsDisplayed() {
		util.isElementDisplyedAndValidate(searchTemplateInputField);
		util.click(searchTemplateInputField);
		util.isElementDisplyedAndValidate(searchTemplateInputFieldFocused);
	}

	/**
	 * This method validates that results are reflected as we type in search input
	 * field.
	 */
	public void searchBarWorking() {
		boolean isSame = false;
		int templateListSize = templateNameList.size();
		util.enterValue(searchTemplateInputField, "te");
		util.hold(2);

		if (templateListSize != templateNameList.size()) {
			isSame = true;
			Assert.assertTrue(isSame);
		}

		Assert.assertTrue(isSame);
	}

	/**
	 * Validate search for exact input
	 * 
	 * @param input
	 */
	public void searchTemplateName(Map<String, String> input) {
		boolean isResultSame = false;
		util.enterValue(searchTemplateInputField, input.get(templateNameJson));
		util.hold(2);

		templateName = util.getTagValue(templateNameList.get(0));

		if (templateName.contains(input.get(templateNameJson))) {
			isResultSame = true;
			Assert.assertTrue(isResultSame);
		}

		Assert.assertTrue(isResultSame);
	}

	/**
	 * Validate search for exact input
	 * 
	 * @param input
	 */
	public void searchTemplateNameWithSpace(Map<String, String> input) {
		String templateNameWithSpace = input.get("template_for_search_input_field");
		boolean isResultTrue = false;
		util.enterValue(searchTemplateInputField, " " + templateNameWithSpace + " ");
		util.hold(2);

		if (!noTemplatesFound.isEmpty()) {
			isResultTrue = true;
			Assert.assertTrue(isResultTrue);
		}

		Assert.assertTrue(isResultTrue);
	}

	/**
	 * Validate search for exact input
	 * 
	 * @param input
	 */
	public void searchTemplateNameWithInvalid(Map<String, String> input) {
		boolean isResultTrue = false;
		util.clear(searchTemplateInputField);
		util.enterValue(searchTemplateInputField, input.get("invalid_search_keyword2"));
		util.hold(2);
		if (util.extractValueByAttributes(searchTemplateInputField, "value").equals("")) {
			isResultTrue = true;
			Assert.assertTrue(isResultTrue);
		}

		Assert.assertTrue(isResultTrue);
	}

	public void checkPagination() {
		String pageVal = util.getTagValue(paginationVal);

		if (!pageVal.equals("1/1")) {
			util.click(nextPageBtn);
		}
	}

	/**
	 * Clone an existing template.
	 */
	public void cloneTemplate() {
		templateName = util.getTagValue(templateNameList.get(0));
		util.click(kebebMenuList.get(0));
		util.click(cloneMenuItem);
		util.hold(1);
		String currentInventory = util.extractValueByAttributes(setMaxInventory, "value");
		int inventory = Integer.parseInt(currentInventory) + 1;
		currentInventory = Integer.toString(inventory);
		util.enableButton(contSellingCheckBox);
		util.enterValue(setMaxInventory, currentInventory);
		util.waitUntilElementIsInvisible(loadingPageBar);
		util.click(overrideExistingProductsCheckBox);
		util.click(advancedSelectionRadioBtn);
		util.selectByValue(selectByAttributeDropDown, "price");
		util.selectByValue(selectByAttributeDropDown, "not equals");
		util.enterValue(valueInputFieldAdvancedSelection, "2500");
		clickOnSave();
		util.waitUntilElementIsVisible(toastMsg);
	}

	/**
	 * Create a new template.
	 * 
	 * @param input
	 */
	public void createNewTemplate(Map<String, String> input) {
		String newTemplateName = createRandomName(input);
		boolean appearOnTop = false;
		util.click(productTemplates);
		util.click(addNewTemplateBtn.get(0));
		util.enterValue(templateNameInputField, newTemplateName);
		selectDefaultcategoryAndRequireAttribute();
		selectAmazonRecommendationValueFromDropDown();
		util.enterValue(handlingTimeInputField, "3");
		util.click(overrideExistingProductsCheckBox);
		util.click(advancedSelectionRadioBtn);
		util.selectByValue(selectByAttributeDropDown, "price");
		util.selectByValue(selectByAttributeDropDown, "not equals");
		util.enterValue(valueInputFieldAdvancedSelection, "2500");
		clickOnSave();
		util.isElementDisplyedAndValidate(productTemplatesHeading);

		if (templateNameList.get(0).getText().equals(newTemplateName)) {
			appearOnTop = true;
			Assert.assertTrue(appearOnTop);
		}

		Assert.assertTrue(appearOnTop);
	}

	/**
	 * When progress bar is visible Edit and Delete functionality is Disabled in
	 * particular template.
	 */
	public void verifyWhenProgressBarIsVisible() {
		if(!customProgressBar.isEmpty()) {
			util.click(kebebMenuList.get(0));
			util.click(editMenuItem);
			util.isElementDisplyedAndValidate(productsAreStillAssigningErrorMsg);
			util.hold(5);
			util.click(deleteMenuItem);
			util.isElementDisplyedAndValidate(productsAreStillAssigningErrorMsg);
			
		}
	}

	/**
	 * Validate that most recently created template appears on top of all existing
	 * templates.
	 * 
	 * @param input
	 */
	public void newlyClonedTemplateAppearsOnTopOfAllTemplates(Map<String, String> input) {
		boolean appearOnTop = false;
		String temName = createRandomName(input);

		if (!templateNameList.isEmpty()) {
			cloneTemplate();
			util.isElementDisplyedAndValidate(productTemplatesHeading);

			if (templateNameList.get(0).getText().equals("Copy of " + templateName)) {
				appearOnTop = true;
				Assert.assertTrue(appearOnTop);
			}

			Assert.assertTrue(appearOnTop);
		} else {
			util.click(addNewTemplateBtn.get(0));
			util.enterValue(templateNameInputField, temName);
			selectDefaultcategoryAndRequireAttribute();
			selectAmazonRecommendationValueFromDropDown();
			util.enterValue(handlingTimeInputField, "3");
			util.waitUntilElementIsInvisible(loadingPageBar);
			clickOnSave();
			cloneTemplate();

			if (templateNameList.get(0).getText().equals("Copy of " + templateName)) {
				appearOnTop = true;
				Assert.assertTrue(appearOnTop);
			}

			Assert.assertTrue(appearOnTop);
		}
	}

}
