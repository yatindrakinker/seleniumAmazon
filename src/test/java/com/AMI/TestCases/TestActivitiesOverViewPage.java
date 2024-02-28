package com.AMI.TestCases;

import org.testng.annotations.Test;

import com.ami.pageobjects.ActivitiesOverViewPage;
import com.ami.resources.BaseClass;

public class TestActivitiesOverViewPage extends BaseClass {
	ActivitiesOverViewPage activitiesOverViewPage;

	@Test(priority = 1)
	public void testInitializeInstance() {
		activitiesOverViewPage = new ActivitiesOverViewPage(util);
	}

	@Test(priority = 2, description = "test case of activities from 150-152")
	public void testActivitiesSectionIsDisplayed() {
		activitiesOverViewPage.validateActivitiesIsShown();
	}
	
	@Test(priority = 3, description = "test case of activities from 158-169")
	public void validateSwitcherIsDisplayed() {
		activitiesOverViewPage.validateSwitcherIsDisplayed();
	}
	
	@Test(priority = 4, description = "")
	public void validateHyperlinksAreVisible() {
		activitiesOverViewPage.validateActivitiesIsShown();
	}
}
