package com.ami.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HelpFaqPageOR {

	@FindBy(xpath = "//h1[text() = 'FAQ & Troubleshoot']")
	WebElement headingFaqPage;
	
	@FindBy(xpath = "//input[@placeholder = 'Search your query here...']")
	WebElement searchQryInpField;
	
	@FindBy(xpath = "//h2[text() = 'General Queries']")
	WebElement genQryHeadingFAQPage;
	
	@FindBy(xpath = "//h2[text() = 'General Queries']/parent::div/parent::div/parent::div/following-sibling::div//button")
	WebElement genQryShowMoreLessBtn;
	
	@FindBy(xpath = "//h2[text() = 'Product Section']")
	WebElement productSectionHeadingFAQPage;
	
	@FindBy(xpath = "//h2[text() = 'Product Section']/parent::div/following-sibling::div/div/div")
	List<WebElement> qryListBelowProductSection;
	
	@FindBy(xpath = "//h2[text() = 'Product Section']/parent::div/parent::div/parent::div/following-sibling::div//button")
	WebElement productSectionShowMoreLessBtn;
	
	@FindBy(xpath = "//h2[text() = 'Shipment setting']")
	WebElement shipmentSettingHeadingFAQPage;
	
	@FindBy(xpath = "//h2[text() = 'Shipment setting']/parent::div/following-sibling::div/div/div")
	List<WebElement> qryListBelowShipmentSettingSection;
	
	@FindBy(xpath = "//h2[text() = 'Shipment setting']/parent::div/parent::div/parent::div/following-sibling::div//button")
	WebElement shipmentSectionShowMoreLessBtn;
	
	@FindBy(xpath = "//h2[text() = 'Order Section']")
	WebElement orderSectionHeadingFAQPage;
	
	@FindBy(xpath = "//h2[text() = 'Order Section']/parent::div/following-sibling::div/div/div")
	List<WebElement> qryListBelowOrderSection;
	
	@FindBy(xpath = "//h2[text() = 'Order Section']/parent::div/parent::div/parent::div/following-sibling::div//button")
	WebElement orderSectionShowMoreLessBtn;
	
	@FindBy(xpath = "//h2[text() = 'Errors']")
	WebElement errorHeadingFAQPageSection;
	
	@FindBy(xpath = "//h2[text() = 'Errors']/parent::div/following-sibling::div/div/div")
	List<WebElement> qryListBelowErrors;
	
	@FindBy(xpath = "//h2[text() = 'Errors']/parent::div/parent::div/parent::div/following-sibling::div//button")
	WebElement errorsSectionShowMoreLessBtn;
	
	@FindBy(xpath = "//a[text() = 'Amazon By CedCommerce']")
	WebElement amzByCedLink;
	
	@FindBy(xpath = "//button[text() = 'Get Support']")
	WebElement getSupportBtn;
	
	@FindBy(xpath = "//h1[text() = 'How can we help you ?']")
	WebElement getSupportPageHeading;
	
	@FindBy(xpath = "//a[text() = 'Book a Call']")
	WebElement bookACallLink;
	
	@FindBy(xpath = "//p[text() = 'WhatsApp']")
	WebElement whatsappHeading;
	
	@FindBy(xpath = "(//span[text() = 'Start Chat'])[1]")
	WebElement startChatBtnWApp;
	
	@FindBy(xpath = "//p[text() = 'Skype']")
	WebElement skypeHeading;
	
	@FindBy(xpath = "(//span[text() = 'Start Chat'])[2]")
	WebElement startChatBtnSkype;
	
	@FindBy(xpath = "//p[text() = 'Email']")
	WebElement emailHeading;
	
	@FindBy(xpath = "//span[text() = 'Mail Us']")
	WebElement mailUsBtn;
	
	@FindBy(xpath = "//button[@class = 'Polaris-Button Polaris-Button--plain Polaris-Button--iconOnly']")
	List<WebElement> btnList;
	
	@FindBy(id = "basic-collapsible")
	List<WebElement> faqList;
	
	@FindBy(className = "Polaris-Tag__TagText")
	WebElement searchResultFor;
	
	@FindBy(xpath = "//div[@style='cursor: pointer;']")
	List<WebElement> allQryList;
	
	@FindBy(xpath = "//span[text() = 'Nothing matches to your query.']")
	WebElement nothingMatchMsg;
	
	@FindBy(xpath = "//h1[text() = 'FAQ & Troubleshoot']")
	WebElement headingFAQ;
	
	@FindBy(xpath = "//span[text() = 'Show More']")
	List<WebElement> showMoreBtn;
	
	@FindBy(xpath = "//span[text() = 'Show Less']")
	List<WebElement> showLessBtn;
}
