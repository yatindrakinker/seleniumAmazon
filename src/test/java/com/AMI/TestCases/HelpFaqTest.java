package com.AMI.TestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ami.pageobjects.HelpFaqPage;
import com.ami.resources.BaseClass;

public class HelpFaqTest extends BaseClass {

	@DataProvider
	public Object[][] getFAQData() throws IOException {
		List<HashMap<String, String>> data = util.getJsonDataToHashmap("FAQ");
		return new Object[][] { { data.get(0) } };
	}

	@Test(priority = 1)
	public void testHyperlinksOnBottomOfThePage() {
		HelpFaqPage obj = new HelpFaqPage(util);
		obj.validateHyperlinksRedirection();
	}

	@Test(priority = 2)
	public void testVisibilityOfHyperLinksOnEachPage() {
		HelpFaqPage obj = new HelpFaqPage(util);
		obj.validateAvailabilityOfHyperLinksOnEachPage();
	}

	@Test(priority = 3)
	public void testGetSupportPageFunctionality() {
		HelpFaqPage obj = new HelpFaqPage(util);
		obj.getSupportPageFunctionality();
	}

	@Test(priority = 4)
	public void testFAQFunctionality() {
		HelpFaqPage obj = new HelpFaqPage(util);
		obj.faqFunctionality();
	}

	@Test(priority = 5, dataProvider = "getFAQData")
	public void testSearchBarFAQ(HashMap<String, String> input) {
		HelpFaqPage obj = new HelpFaqPage(util);
		obj.searchBarFAQ(input);
	}

	@Test(priority = 6)
	public void testShowMoreBtnWorking() {
		HelpFaqPage obj = new HelpFaqPage(util);
		obj.showMoreBtnWorking();
	}

	@Test(priority = 7)
	public void testQueriesSectionWise() {
		HelpFaqPage obj = new HelpFaqPage(util);
		obj.queriesSectionWise();
		
	}

}
