package com.AMI.TestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ami.pageobjects.SearchFieldProductTemplate;
import com.ami.resources.BaseClass;

public class SearchFieldProductTemplateTest extends BaseClass {

	SearchFieldProductTemplate sfpt;

	@DataProvider
	public Object[][] getTemplateData() throws IOException {
		List<HashMap<String, String>> data = util.getJsonDataToHashmap("Template");
		return new Object[][] { { data.get(0) } };
	}

	@Test(priority = 1)
	public void testProductTemplatesIsClickable() {
		sfpt = new SearchFieldProductTemplate(util);
		util.clickOnSettings();
		sfpt.productTemplatesIsClickable();
	}
	
	@Test(priority = 2)
	public void testSearchBarIsDisplayed() {
		sfpt = new SearchFieldProductTemplate(util);
		sfpt.searchBarIsDisplayed();
	}

	@Test(priority = 3)
	public void testSearchBarWorking() {
		sfpt.searchBarWorking();
	}

	@Test(priority = 4, dataProvider = "getTemplateData")
	public void testSearchTemplateName(Map<String, String> input) {
		sfpt.searchTemplateName(input);
	}

	@Test(priority = 5, dataProvider = "getTemplateData")
	public void testSearchTemplateNameWithSpace(Map<String, String> input) {
		sfpt.searchTemplateNameWithSpace(input);
	}

	@Test(priority = 6, dataProvider = "getTemplateData")
	public void testSearchTemplateNameWithInvalidName(Map<String, String> input) {
		sfpt.searchTemplateNameWithInvalid(input);
	}

	@Test(priority = 7)
	public void testCheckPagination() {
		sfpt.checkPagination();
	}

	@Test(priority = 8, dataProvider = "getTemplateData")
	public void testNewlyClonedTemplateAppearsOnTopOfAllTemplates(Map <String, String> input) {
		sfpt.newlyClonedTemplateAppearsOnTopOfAllTemplates(input);
		sfpt.verifyWhenProgressBarIsVisible();
	}
	
	@Test(priority = 9, dataProvider = "getTemplateData")
	public void testCreateNewTemplate(Map <String, String> input) {
		sfpt.createNewTemplate(input);
		sfpt.verifyWhenProgressBarIsVisible();
	}

}
