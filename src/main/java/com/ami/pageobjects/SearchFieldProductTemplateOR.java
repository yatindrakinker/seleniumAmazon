package com.ami.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchFieldProductTemplateOR {

	@FindBy(xpath = "//input[@placeholder = 'Search with template name']")
	WebElement searchTemplateInputField;
	
	@FindBy(xpath = "//input[@class='Polaris-TextField__Input Polaris-TextField__Input--hasClearButton focus-visible']")
	WebElement searchTemplateInputFieldFocused;
	
	@FindBy(xpath = "//span[text() = 'Product Templates']")
	WebElement productTemplates;
	
	@FindBy(xpath = "//div[@class='custom-product--templateName']/span/span")
	List<WebElement>  templateNameList;
	
	@FindBy(xpath = "//p[text() = 'No Templates Found']")
	List<WebElement> noTemplatesFound;
	
	@FindBy(xpath = "//button[@id='previousURL']/parent::div/following::div/div[@aria-live='polite']/span")
	WebElement paginationVal;
	
	@FindBy(id="nextURL")
	WebElement nextPageBtn;
	
	@FindBy(xpath = "//div[@class='custom-product--template']/div/div[3]/div/button")
	List<WebElement> kebebMenuList;
	
	@FindBy(xpath = "//span[text() = 'Clone']")
	WebElement cloneMenuItem;
	
	@FindBy(xpath = "//span[text() = 'Edit']")
	WebElement editMenuItem;
	
	@FindBy(xpath = "//span[text() = 'Delete']")
	WebElement deleteMenuItem;
	
	@FindBy(xpath = "//input[@placeholder='999']")
	WebElement setMaxInventory;
	
	@FindBy(xpath = "//span[contains(text(),'Continue selling when out of stock')]/preceding-sibling::span/span/input")
	WebElement contSellingCheckBox;
	
	@FindBy(xpath = "//div[@class = 'Polaris-Frame-Toast']")
	WebElement toastMsg;
	
	@FindBy(xpath = "//span[text() = 'Add New Template']/parent::span/parent::button")
	List<WebElement> addNewTemplateBtn;
	
	@FindBy(xpath = "//input[@placeholder = 'Enter Template Name']")
	WebElement templateNameInputField;
	
	@FindBy(xpath = "//div[text() = 'New']")
	WebElement setAmazonRecommendationOptionNew;
	
	@FindBy(xpath = "//a[text()= 'Default']")
	WebElement defaultCategory;
	
	@FindBy(xpath = "//h2[text() = 'Required Attributes']")
	WebElement reqAttributeHeading;
	
	@FindBy(xpath = "//div[text() = 'Set Amazon Recommendation']")
	WebElement setAmazonRecommendation;
	
	@FindBy(xpath = "//div[text() = 'Please Select' and @class = ' css-qc6sy-singleValue']/parent::div/parent::div")
	WebElement defaultSelectAttribute;

	@FindBy(xpath = "//div[@class = ' css-14el2xx-placeholder']")
	WebElement setAmazonRecommendationSelector;
	
	@FindBy(xpath = "//input[@placeholder = 'Enter value...']")
	WebElement handlingTimeInputField;
	
	@FindBy(id = "manual")
	WebElement manualRadioBtn;
	
	@FindBy(xpath = "//span[text() = 'Save']")
	WebElement saveBtn;
	
	@FindBy(xpath = "//div[@class='Polaris-Frame-Loading__Level']")
	WebElement loadingPageBar;
	
	@FindBy(xpath = "//div[@class='custom__progress--section']")
	List<WebElement> customProgressBar;
	
	@FindBy(xpath = "//div[text() = 'Kindly wait, some products are still assigning.']")
	WebElement productsAreStillAssigningErrorMsg;
	
	@FindBy(id="productTemplates")
	WebElement productTemplatesHeading;
	
	@FindBy(xpath = "//span[text()='Override Existing Products']/preceding-sibling::span/span/input")
	WebElement overrideExistingProductsCheckBox;
	
	@FindBy(id="advanced")
	WebElement advancedSelectionRadioBtn;
	
	@FindBy(xpath = "(//div[@class='custom-selection']//select)[1]")
	WebElement selectByAttributeDropDown;
	
	@FindBy(xpath = "(//div[@class='custom-selection']//select)[2]")
	WebElement selectByConditionropDown;
	
	@FindBy(xpath = "//div[@class='custom-selection']//input[(@placeholder='Enter value')]")
	WebElement valueInputFieldAdvancedSelection;
}
