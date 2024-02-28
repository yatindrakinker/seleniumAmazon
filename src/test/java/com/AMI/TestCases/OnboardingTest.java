package com.AMI.TestCases;

import org.testng.annotations.Test;

import com.ami.pageobjects.OnboardingPage;
import com.ami.resources.BaseClass;

public class OnboardingTest extends BaseClass {
	OnboardingPage onboard;

	@Test(priority = 1)
	public void testCreateNewStore() {
		onboard = new OnboardingPage(util);
//		onboard.openShopifyPartners();
//		loginShopify();
//		onboard.createNewStore();
//		onboard.openShopifyAppStore();
//		onboard.searchAndInstallApp();
//		util.switchToIFrame();
		onboard.selectAppStore();
	}

	@Test(priority = 2)
	public void testRouteToOnboardingPage() {
		onboard.redirectionIsProperToOnboarding();
		onboard.connectSellerBtnIsDisabled();
	}

	@Test(priority = 3)
	public void testBeforeOnboardingIsCompleted() {
		onboard.onboardingPageIsDisplayedOnEverySection();
		onboard.onboardingStepsAreIsDisplayed(1);
	}

	@Test(priority = 4)
	public void testSearchFieldIsWorking() {
		onboard.choseCountryIsMandatory();
		onboard.countryListIsDisplayed();
		onboard.searchInputFieldAcceptsOnlyCharacters();
		onboard.errMsgIsDisplayedWhenCountryIsInvalid();
	}

	@Test(priority = 5)
	public void testSelectCountry() {
		onboard.chooseCountry();
	}

	/*
	 * Functionality removed
	 */
//	@Test(priority = 6)
//	public void testCheckList() {
//		onboard.checklistForSuccessfulAcntConnection();
//	}

//	removed
	
//	@Test(priority = 7)
//	public void testTooltipPaymentIsAvailable() {
//		onboard.paymentMethodTooltipIsDisplayed();
//	}

//	failed for now => 05/02/2024
	
	@Test(priority = 8)
	public void testTermsConditionCheckBoxLinkIsWorking() {
		onboard.openTermsConditionCheckBoxDoc();
	}

	@Test(priority = 9)
	public void testCedLinkFooterIsFunctioning() {
		onboard.cedLinkFooterIsFunctioning();
	}

	@Test(priority = 10)
	public void testBookCallLinkFooterIsFunctioning() {
		onboard.bookCallLinkFooterIsFunctioning();
	}
	
	@Test(priority = 10)
	public void testDataPrivacyLinkFooterIsFunctioning() {
		onboard.dataPrivacyLinkFooterIsFunctioning();
	}

	@Test(priority = 11)
	public void testGetSupportLinkFooterIsFunctioning() {
		onboard.getSupportLinkFooterIsFunctioning();
	}

	@Test(priority = 12)
	public void testConnectSellerAcnt() {
		onboard.connectSellerAcntBtnFunctioning();
	}

	@Test(priority = 13)
	public void testAcntConnectionFailedReasonsAreDisplayed() {
		onboard.connectionUnsuccessfulReasonAreDisplayed();
	}
	
	@Test(priority = 14)
	public void testClickHereLinkIsWorking() {
		onboard.clickHereToKnowMoreLinkIsFunctioning();
	}
	
	@Test(priority = 15)
	public void testRetryConnectingBtnIsWorking() {
		onboard.retryConnectingBtnIsWorking();
	}
		
	/*
	 * Functionality removed
	 */
//	@Test(priority = 16)
//	public void testAllCheckListCheckBoxAreChecked() {
//		onboard.allCheckListCheckBoxAreChecked();
//	}
	
	@Test(priority = 17)
	public void testSearchCountryInputFieldIsEmpty() {
		onboard.checkSearchCountryInputFieldIsEmpty();
	}
	
	@Test(priority = 18)
	public void testRedirectionToSecStep() {
		onboard.chooseCountry();
		onboard.clickOnConnectSellerAcnt();
	}
	
//	 @Test(priority = 19)
//	public void testEnterSellerCentralCredentials() {
//		onboard.enterCredentials();
//	}
	
//	-----------------------------------STEP 2 ONBOARDING----------------------------------------

//	Breakpoint for connecting amazon seller central account manually.
	
	@Test(priority = 20)
	public void testOnboardingStep1IsCompleted(){
		onboard.onboardingStepsAreIsDisplayed(2);
	}
	
	
	@Test(priority = 21)
	public void testSuccessAcntBannerIsDisplayed() {
		onboard.successAcntBannerIsDisplayed();
	}
	
	@Test(priority = 22)
	public void testCreateAcntNameIsMandatory() {
		onboard.createAcntNameIsMandatory();
	}
	
	@Test(priority = 23)
	public void testAcntNameInputFieldAcceptsVals() {
		onboard.acntNameInputFieldAcceptsVals();
	}
	
	@Test(priority = 24)
	public void testMousePropertyChangesWhileHoveringAtLinksOnStep2() {
		onboard.acntNameInputFieldAcceptsVals();
	}
	
	@Test(priority = 25)
	public void testCedLinkFooterIsFunctioningOnStep2() {
		onboard.cedLinkFooterIsFunctioning();
	}
	
	@Test(priority = 26)
	public void testBookCallLinkFooterIsFunctioningOnStep2() {
		onboard.bookCallLinkFooterIsFunctioning();
	}
	
	@Test(priority = 26)
	public void testDataPrivacyFooterIsFunctioningOnStep2() {
		onboard.dataPrivacyLinkFooterIsFunctioning();
	}

	@Test(priority = 27)
	public void testGetSupportLinkFooterIsFunctioningOnStep2() {
		onboard.getSupportLinkFooterIsFunctioning();
	}
	
	@Test(priority = 28)
	public void testSetAcntNameOnStep2() {
		onboard.setAcntNameAndGoTo3rdStep();
	}

//	-----------------------------------STEP 3 ONBOARDING----------------------------------------
	
	@Test(priority = 29)
	public void testRedirectionToThirdStep() {
		util.switchToIFrame();
		onboard.onboardingStepsAreIsDisplayed(3);
		
	}
	
	@Test(priority = 30)
	public void testAtleastOneRadioBtnMustBeSelected() {
		onboard.atleastOneRBMustBeSelected();
		
	}
	
//	removed
	
//	@Test(priority = 31)
//	public void testRadioBtnsAreWorking() {
//		onboard.radioBtnsAreWorking();
//	}
	
	@Test(priority = 32)
	public void testExistingAmzSellerRadioBtnIsWorking() {
		onboard.selectExistingAmzSellerRadioBtn();
		onboard.configSyncPrefSection();
	}

	@Test(priority = 32)
	public void testDefaultSelectedCheckbox() {
		onboard.byDefaultSKUAndFBMAreChecked();
		onboard.bothSKUAndBarCodeCanNotBeUnchecked();
	}
	
	@Test(priority = 33)
	public void testAllCheckBoxAreClickable() {
		onboard.allCheckBoxAreClickable();
		onboard.disableExistingAmzSeller();
	}
	

	@Test(priority = 34)
	public void testCheckManufacturerCheckBtnIsWorking() {
		onboard.checkManufacturerCheckBoxIsWorking();
	}
	
	@Test(priority = 35)
	public void testBrandNameInputFieldIsEmpty() {
		onboard.brandNameInputFieldIsEmpty();
	}
	
	@Test(priority = 36)
	public void testBrandNameInputFieldAcceptsAnyValue() {
		onboard.brandNameInputFieldAcceptsAnyValue();
	}
	
	@Test(priority = 37)
	public void testUncheckManufacturerCheckBox() {
		onboard.uncheckManufacturerCheckBox();
	}
		
	@Test(priority = 38)
	public void testOtherCheckBoxIsWorking() {
		onboard.checkOtherBtnIsWorking();
	}

	
	@Test(priority = 39)
	public void testOtherBusinessNameInputFieldIsEmpty() {
		onboard.otherBusinessNameInputFieldIsEmpty();
	}

	
	@Test(priority = 40)
	public void testBusinessNameInputFieldAcceptsAnyValue() {
		onboard.businessNameInputFieldAcceptsAnyValue();
	}
	
	@Test(priority = 41)
	public void testUncheckOtherCheckBox() {
		onboard.uncheckOtherCheckBox();
	}
	
//	removed
	
	@Test(priority = 42)
	public void testWhenNewUserIsSelected() {
		onboard.enableDropshipperBtn();
		onboard.newSellerModal();
	}
		
	@Test(priority = 43)
	public void testManageItHereLinkIsWorking() {
		onboard.manageItHereLinkIsWorking();
	}

	@Test(priority = 44)
	public void testReferenceLinkIsWorking() {
		onboard.referenceLinkIsWorking();
	}
	
	@Test(priority = 46)
	public void testYesIsSelectedByDefault() {
		onboard.validateSelectedOption("Yes");
		onboard.selectForBrandRegister("not_registered");
	}
	
	@Test(priority = 47)
	public void checkAllCheckBoxInFBMAndSelectProductDetails() {
		onboard.verifyCanSelectMultipleRoles();
		//sku and inventory is selected
		onboard.selectFbmCheckboxAndProductDetails();
	}
	
	@Test(priority = 48)
	public void testCompleteOnboarding() {
		onboard.complete3rdStep();
	}
	
	@Test(priority = 49)
	public void testSameSettingsAreAppliedInAppAfterOnboarding() {
		onboard.verifySettingsSelectedInOnbordingAreSameInSettings();
	}

//	It is visible only when user is new
//	@Test(priority = 50)
//	public void testRedirectionToPricingPlan() {
//		onboard.okIUnderstandBtnIsWorking();
//	}

////	--------------------------Subscription Plan--------------------------------------



//	@Test(priority = 51)
//	public void testCedLinkFooterIsFunctioningOnPricingPlan() {
//		util.switchToIFrame();
//		onboard.cedLinkFooterIsFunctioning();
//	}
//	
//	@Test(priority = 48)
//	public void testBookCallLinkFooterIsFunctioningOnPricingPlan() {
//		onboard.bookCallLinkFooterIsFunctioning();
//	}
//
//	@Test(priority = 49)
//	public void testGetSupportLinkFooterIsFunctioningOnPricingPlan() {
//		onboard.getSupportLinkFooterIsFunctioning();
//	}
//	
//	@Test(priority = 50)
//	public void testPricingPlansAreShown() {
////		onboard.selectAppStore();
//		onboard.pricingPlansAreShown();
//	}
//	
//	@Test(priority = 51)
//	public void testPlansAreDisplyed() {
//		onboard.plansAreDisplyed();
//	}
//	
//	@Test(priority = 52)
//	public void testEachPlanContainsBuyBtn() {
//		onboard.eachPlanContainsBuyBtn();
//	}
//	
//	@Test(priority = 53)
//	public void testCustomPlanCardIsDisplayed() {
//		onboard.customPlanCardIsDisplayed();
//	}
//	
//	@Test(priority = 54)
//	public void testCardsMoveWhenClickAtForwardBackwardBtn() {
//		onboard.cardsMoveWhenClickAtForwardBackwardBtn();
//	}
//	
//	@Test(priority = 55)
//	public void testCarouselIndicatorIsDisplayedUnderPricingPlan() {
//		onboard.carouselIndicatorIsDisplayedUnderPricingPlan();
//	}
//	
//	@Test(priority = 56)
//	public void testOnClickingBuyNowBtnRedirectsToPaymentPage() {
//		onboard.onClickingBuyNowBtnRedirectsToPaymentPage();
//	}
//	
//	@Test(priority = 57)
//	public void testContactUsBtnWorking() {
//		onboard.contactUsBtnWorking();
//	}
//	
//	@Test(priority = 58)
//	public void testFaqsAreDisplayed() {
//		onboard.faqsAreDisplayed();
//	}
//	
//	@Test(priority = 59)
//	public void testViewAllQuestionsBtnIsWorking() {
//		onboard.viewAllQuestionsBtnIsWorking();
//	}
//	
//	@Test(priority = 60)
//	public void testSelectFreePlan() {
//		onboard.selectFreePlan();
//	}
//
//	@Test(priority = 61)
//	public void testAfterSelectingFreePricingPlanCurrentPlanIsShownOnOverView() {
//		onboard.afterSelectingFreePricingPlanCurrentPlanIsShownOnOverView();
//	}
	

}
