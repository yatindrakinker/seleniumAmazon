package com.ami.resources;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.ami.pageobjects.ShopifyPage;
public class BaseClass {
	private static final String STORE = "store";
	private static final String TRUE = "true";
	private static final String FALSE = "false";
	private static final String PROFILING = "profiling";

	protected Utilities util = Utilities.getObject();

	protected Properties prop;

	@BeforeClass(alwaysRun = true)
	@Parameters("browser")
	public void launchingMethod(@Optional String browser){
			if (util.getConfigProperty(STORE).equalsIgnoreCase("live")) {
				util.openLoginPage(browser, util.getConfigProperty("storeUrl"));
			} else {
				util.openLoginPage(browser, util.getConfigProperty("stroreUrlStaging"));
			}
			
			if(util.getConfigProperty(PROFILING).equalsIgnoreCase(TRUE)) {
				List<WebElement> shopifyHome = util.getDriver().findElements(By.id("app"));
				List<WebElement> userCard = util.getDriver().findElements(By.cssSelector("div[class*='user-card '] div.user-card__email"));
				
				if (!shopifyHome.isEmpty()) {
					selectStore(util.getConfigProperty(STORE));
				} else if(!userCard.isEmpty()) {
					util.click(userCard.get(0));

				}
			}else if(util.getConfigProperty(PROFILING).equalsIgnoreCase(FALSE)) {
				login(util.getConfigProperty(STORE));
			}

	}

	public void login(String store) {
		ShopifyPage sp = new ShopifyPage(util);
		sp.shopifyLogin();

		if (util.getConfigProperty("onboarding").equals(FALSE)) {
			sp.selectStore(store);
		}
	}

	public void selectStore(String store) {
		ShopifyPage sp = new ShopifyPage(util);

		if (util.getConfigProperty("onboarding").equals(FALSE)) {
			sp.selectStore(store);
		}
	}

	public void loginShopify() {
		ShopifyPage sp = new ShopifyPage(util);
		sp.shopifyPartnersLogin();
	}

//	@AfterClass(alwaysRun = true)
//	public void closeBrowser() {
//		util.getDriver().quit();
//	}

	public String getScreenShotPath(String testCaseName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) util.getDriver();
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "/ScreenshotFailedTestCases/" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
	}

}
