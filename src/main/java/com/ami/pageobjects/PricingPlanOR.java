package com.ami.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ami.pageobjects.reusableclasses.ReusableMethodsOR;

public class PricingPlanOR extends ReusableMethodsOR {

	@FindBy(xpath = "//span[text() = 'Save']")
	protected WebElement saveBtn;

//	-------------------------------<Pricing Plan>-------------------------------

	@FindBy(xpath = "//h1[text()='Pricing Plans']")
	protected WebElement pricingPlanPageHeading;

	@FindBy(xpath = "//span[text()='View all plans']")
	protected WebElement viewAllPlanBtn;

	@FindBy(id = "marketing")
	protected WebElement marketingPlansBtn;

	@FindBy(id = "regular")
	protected WebElement regularPlansBtn;

	@FindBy(xpath = "//span[text()='(Pay for 11 months, Get one month extra FREE)']")
	protected WebElement getOneMonthFreeOnSelectingAnualPlan;

	@FindBy(xpath = "//h1[text()='Pricing Plans']")
	protected WebElement pricingPlansSectionHeading;

	@FindBy(xpath = "//button[text()='View all features']")
	protected List<WebElement> viewAllFeaturesBtn;

	@FindBy(xpath = "//div[@role = 'dialog']//p[text()='All pricing features']")
	protected WebElement allFeaturesModal;

	@FindBy(xpath = "//div[@role = 'dialog']//button/span")
	protected WebElement closeBtnModal;

	@FindBy(xpath = "//span[text()='Get 1 month FREE subscription']")
	protected WebElement get1MonthFreeText;

	@FindBy(xpath = "//div[@class='Custom-Pricing--Card']//div[@class='Custom-Alpha-Card__Section']/div/div[3]/p")
	protected List<WebElement> pricingPlansNameList;

	@FindBy(xpath = "//span[text() = 'Try For Free']/parent::span/parent::button")
	protected WebElement tryForFreeBtn;

	@FindBy(css = "button[class*='Polaris-Button--sizeLarge Polaris-Button--fullWidth']")
	protected List<WebElement> buyNowBtn;

	@FindBy(xpath = "//button[text()='Next']")
	protected WebElement nextPricingPlanBtn;

	@FindBy(xpath = "//button[text()='Previous']")
	protected WebElement prevPricingPlanBtn;

	@FindBy(xpath = "//div[@class='slick-track']/div")
	protected List<WebElement> pricingPlanCards;

	@FindBy(xpath = "//ul[@class='slick-dots slick-dots-bottom']")
	protected WebElement carouselIndicator;

	@FindBy(xpath = "//h1[text()='Approve subscription']")
	protected WebElement headingApproveSubscriptionPage;
	
	
	@FindBy(css = "button[class*='slick-arrow slick-next']")
	protected WebElement nextCorouselBtn;
	
	@FindBy(css = "button[class='slick-arrow slick-next slick-disabled']")
	protected List<WebElement> nextCorouselDisabledBtn;

	@FindBy(xpath = "//div[@class='Custom-Pricing--Card Custom__enterprice-card--height']//p[text()='Custom Solutions']")
	protected WebElement customSolForHighVolMerchants;
	
	@FindBy(xpath = "//div[@class='Custom-Pricing--Card Custom__enterprice-card--height']//p[text()='for High-volume Merchants']")
	protected WebElement customSolForHighVolMerchants2;

	@FindBy(xpath = "//span[text()='Contact Us']/parent::span/parent::button")
	protected WebElement contactUsBtn;

	@FindBy(xpath = "//p[text()='Frequently Asked Questions']")
	protected WebElement faqHeadingPricingPlan;

	@FindBy(xpath = "//div[@class='Custom-Spacing']/following-sibling::div/div[@class='Polaris-Box']//div[@id='basic-collapsible']")
	List<WebElement> faqs;

	@FindBy(xpath = "//span[text()='View all questions']/parent::span/parent::button")
	protected WebElement viewAllQuestions;

	@FindBy(xpath = "//h1[text()='FAQ & Troubleshoot']")
	protected WebElement faqAndTroubleShootPageHeading;

	@FindBy(xpath = "//span[text()='Current Plan:']")
	List<WebElement> currentPlanBtnList;

	@FindBy(xpath = "//span[text()='Current Plan:']/following-sibling::span")
	protected WebElement currentActivePlanName;

	@FindBy(xpath = "//span[text()='Current Plan']/parent::span/parent::div/p/parent::div/following-sibling::div/p")
	protected WebElement ordersLimit;

	@FindBy(xpath = "//nav[@role='navigation']")
	protected WebElement backBtn;

	@FindBy(xpath = "//span[normalize-space()='Payment Details:']/following-sibling::span")
	protected WebElement planNameOverview;

	@FindBy(xpath = "//span[contains(text(),'Purchased On ')]")
	protected WebElement purchasedOnDate;

	@FindBy(xpath = "//progress/parent::div/parent::div/following-sibling::div/span[@class='Polaris-Text--root']")
	protected WebElement orderProcessed;

	@FindBy(xpath = "//div[@class='slick-track']/div[@data-index = '0']//span[text()='Current Plan']/parent::span/parent::button")
	protected WebElement currentPlanAtFirstPlace;
	
	@FindBy(xpath = "//span[text()='Confirm and proceed']/ancestor::button")
	protected WebElement confirmAndProceedBtn;
	
	@FindBy(css = ".Custom-Alpha-Card__Section>div>div+div>div>p:nth-of-type(1)")
	protected List<WebElement> pricingPlanPriceList;
	
	@FindBy(xpath = "//span[contains(text(),'Payment Details:')]/following-sibling::span")
	protected WebElement currentPlanDetailsOverview;
	

//	-------------------------------</Pricing Plan>-------------------------------

//	-------------------------------<Overview>-------------------------------

	@FindBy(xpath = "//span[contains(text(),'Annually')]/ancestor::button")
	protected WebElement anuallybtn;

	@FindBy(xpath = "//span[contains(text(),'Monthly')]/ancestor::button")
	protected WebElement monthlyBtn;

	@FindBy(xpath = "//span[text() = 'Upgrade Now']/parent::span/parent::button")
	protected WebElement upgradeNowBtn;

	@FindBy(xpath = "//h1[text() = 'Approve subscription']")
	protected WebElement approveSubscriptionHeading;

	@FindBy(id = "approve-charges-button")
	protected WebElement approveBtn;

	@FindBy(id = "subscription")
	protected WebElement subscriptionSectionHeading;

	@FindBy(xpath = "//progress")
	protected WebElement orderProgressBar;

	@FindBy(xpath = "(//progress[@class='Polaris-ProgressBar__Progress'])[1]/following-sibling::div")
	protected WebElement orderProgressBarColor;
	
	@FindBy(xpath = "//span[text()='Recommended']")
	protected WebElement recommendedPlanBanner;
	

//	-------------------------------</Overview>-------------------------------

//	-------------------------------<Orders>-------------------------------

	@FindBy(id = "ordersettings")
	protected WebElement orderSettings;

	@FindBy(id="order_Btn_Enable")
	protected WebElement enableOrderSettingsBtn;

	@FindBy(id="order_Btn_Disable")
	protected WebElement disbleOrderSettingsBtn;

	@FindBy(id="order_Switch_CreateOrderForNonExistingProducts")
	protected WebElement createOrdersForNonExistingProductsToggleBtn;

//	-------------------------------</Orders>-------------------------------
	
//	-------------------------------<Orders>-------------------------------
	
	@FindBy(css = "div.custom-modal--Pricing")
	protected WebElement excessUsageChargesCard;
	
	@FindBy(css = "div.custom-modal--Pricing button[aria-disabled='false']")
	protected WebElement payExcessChargesBtn;
	
	@FindBy(xpath = "//div[@class = 'Polaris-Modal-Section']//p[text()='Kindly pay the Excess Usage Charges to proceed choosing a new plan.']")
	protected List<WebElement> modalForPayExcessChargesList;
	
	@FindBy(css = "div[role='dialog'] div[class='Polaris-InlineStack']>button[class*='Polaris-Button--primary']")
	protected WebElement payExcessChargesBtnAttentionModal;
	
	@FindBy(id="regular")
	protected WebElement regularPlansTab;
	
	@FindBy(css = "div[role='dialog'] div[class='Polaris-InlineStack']>button[class^='Polaris-Link']:first-of-type")
	protected WebElement contactUsBtnAttentionModal;
	
	@FindBy(css = "div[role='dialog'] div[class='Polaris-InlineStack']>button[class^='Polaris-Link']:last-of-type")
	protected WebElement faqBtnAttentionModal;
	
	@FindBy(xpath = "//h1[text() = 'Approve charge']")
	protected WebElement approveChargePageHeading;
	
	@FindBy(css = ".Custom--Settlement-Card")
	protected WebElement excessUsageChargesDetails;
	
	@FindBy(css = "div.Custom--Settlement-Card+hr+hr+div button.Polaris-Button.Polaris-Button--primary")
	protected WebElement payExcessUsageChargesDetails;
	
	@FindBy(id="approve-charges-button")
	protected WebElement approveChargeBtn;
	
	@FindBy(xpath = "//span[contains(text(),'Excess usage charge successfully paid')]")
	protected WebElement excessChargesPaidSuccessfullyToastMsg;
	
	@FindBy(xpath = "//span[text()='Contact us']")
	protected WebElement contactUsBtnExcessChargesCard;
	
	@FindBy(xpath = "//span[text()='frequently asked questions']")
	protected WebElement faqBtnExcessChargesCard;
	
	@FindBy(xpath = "//p[text()='Orders']/following-sibling::p")
	protected WebElement ordersText;
	
	
	
//	-------------------------------</Orders>-------------------------------

}
