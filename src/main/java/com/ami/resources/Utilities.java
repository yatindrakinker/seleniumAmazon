package com.ami.resources;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.manager.SeleniumManager;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ami.customexceptions.EmptyListException;
import com.ami.pageobjects.ShopifyPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.datafaker.Faker;

public class Utilities {
	private static Logger log = LogManager.getLogger(Utilities.class.getName());
	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	private static Utilities util;
	private Actions action;
	private Properties prop;
	private String parentWindow;
	private String childWindow;
	private String salesChannelXpath = "//span[text() = 'Sales channels']";
	private String salesChannelNameXpath = "//div[text() = 'CedCommerce Amazon Channel']";
	private String settingsXpath = "//span[text() = 'Settings']";
	private String prodTemplatesXpath = "templates";
	private String ariaChecked = "aria-checked";
	private final String ARIADISABLED = "aria-disabled";
	private String FALSE = "false";
	private final String STORE = "store";
	private final String STAGING = "staging";
	private final String LIVE = "live";
	Random random;

	String path = "";
	private String selectMsg = "button is selected";
	private String displayedMsg = "element is displayed";

	private Utilities() {

	}

	public static Utilities getObject() {
		if (util == null) {
			util = new Utilities();
		}
		return util;
	}

	public WebDriver getDriver() {
		return driver.get();
	}

	public void launchBrowser(String browsername) {
		String os = System.getProperty("os.name");
		String userName = System.getProperty("user.name");

		if (browsername == null || browsername.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();

			System.out.println(SeleniumManager.getInstance().getDriverPath(options, false));
			options.addArguments("--no-sandbox");
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--disable-dev-shm-usage");

			if (getConfigProperty("headless").equalsIgnoreCase("true")) {
				options.addArguments(
						"--user-agent=\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36\"");
				options.addArguments("--headless=new");
			}

			if (getConfigProperty("profiling").equalsIgnoreCase("true")) {
				if (os.equalsIgnoreCase("Linux")) {

					options.addArguments("user-data-dir=/home/" + userName + "/.config/google-chrome/Default");
				} else if (os.equalsIgnoreCase("Windows")) {

					options.addArguments(
							"user-data-dir=C:/Users/" + userName + "/AppData/Local/Google/Chrome/User Data");
				}
			}

			driver.set(new ChromeDriver(options));

		} else if (browsername.equalsIgnoreCase("firefox")) {
			FirefoxProfile profile = new FirefoxProfile();
			FirefoxOptions options = new FirefoxOptions();
			driver.set(new FirefoxDriver(options));
			profile.setPreference("privacy.trackingprotection.enabled", false);
			options.setProfile(profile);
//			options.addPreference("security.fileuri.strict_origin_policy", false);
//			options.addArguments("-headless");

			driver.set(new FirefoxDriver(options));
		} else if (browsername.equalsIgnoreCase("safari")) {
			SafariOptions options = new SafariOptions();
			driver.set(new SafariDriver(options));

		} else if (browsername.equalsIgnoreCase("edge")) {
			EdgeOptions options = new EdgeOptions();
			driver.set(new EdgeDriver(options));
		}
		driver.get().manage().window().maximize();
		driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	public void openUrl(String url) {
		try {
			driver.get().get(url);
			logInfo(url + " is launched successfully.");
		} catch (Exception e) {
			logWarn("page load error.");
		}
	}

	public void openLoginPage(String browserName, String url) {
		launchBrowser(browserName);
		openUrl(url);
	}

	public void switchToIFrame() {
		switchToDefaultContent();
		hold(2);

		WebElement iframe = getDriver().findElement(By.name("app-iframe"));
		new WebDriverWait(getDriver(), Duration.ofSeconds(30))
				.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));

	}

	public void enterValue(WebElement element, String value) {
		try {
//			moveToElement(element);
			jsMoveToElement(element);
			element.sendKeys(Keys.CONTROL + "a");
			element.sendKeys(Keys.BACK_SPACE);
			element.sendKeys(value);
			logInfo(value + " is entered successfully in the input field.");
		} catch (NoSuchElementException | StaleElementReferenceException | MoveTargetOutOfBoundsException e) {
			logWarn(value + " can not be entered as ELEMENT is not available in DOM.");
			moveToElement(element);
			logInfo(value + " is entered successfully in the input field.");
		}

	}

	public void enterValueUsingJSExe(WebElement ele, String val) {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].value = arguments[1];", ele, val);
	}

	public void enterSingleValue(WebElement element, char value) {
		element.clear();
		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(String.valueOf(value));
	}

	public void selectAll(WebElement element) {
		element.sendKeys(Keys.CONTROL + "a");
	}

	public void pressEnter() {
		action = new Actions(getDriver());
		action.keyUp(Keys.ENTER).build().perform();
		action.keyDown(Keys.ENTER).build().perform();
	}

	public void pressBackSpace() {
		action = new Actions(getDriver());
		action.keyUp(Keys.BACK_SPACE).build().perform();
		action.keyDown(Keys.BACK_SPACE).build().perform();
	}

	public void pressTab() {
		action = new Actions(getDriver());
		action.keyUp(Keys.TAB).build().perform();
		action.keyDown(Keys.TAB).build().perform();
	}

	public void pressEscape() {
		action = new Actions(getDriver());
		action.keyDown(Keys.ESCAPE).build().perform();
		action.keyUp(Keys.ESCAPE).build().perform();
	}

	public void click(WebElement element) {
		try {
			moveToElement(element);
			element.click();
			logInfo("clicked on ELEMENT successfully.");
		} catch (ElementClickInterceptedException | StaleElementReferenceException | MoveTargetOutOfBoundsException e) {
			logWarn(" can not be clicked on ELEMENT via normal click EXCEPTION OCCURRED.");
			waitUntilElementIsClickable(element, 30);
			hold(2);
			jsClick(element);
		}

	}

	public boolean retryingFindClick(WebElement element) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 2) {
			try {
				element.click();
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
		return result;
	}

	public void clickOnOrders() {
		getDriver().findElement(By.xpath("//span[text() = 'Orders']")).click();
	}

	public void getShopifyOrder(String orderId) {
		if (getConfigProperty(STORE).equalsIgnoreCase(STAGING)) {
			getDriver().get(getConfigProperty("stroreUrlStaging") + "orders/" + orderId);
		} else if (getConfigProperty(STORE).equalsIgnoreCase(LIVE)) {
			System.out.println(getConfigProperty("storeUrl") + "orders/" + orderId);
			String url = getConfigProperty("storeUrl") + "orders/" + orderId;
			getDriver().get(url);
		}
	}

	public void searchOrders(String orderId) {
		getDriver().findElement(By.xpath("//input[@placeholder = 'Filter orders']")).sendKeys(orderId);
		pressEnter();
		util.hold(3);
	}

	public void clickOnOverview() {
		switchToDefaultContent();
		getDriver().findElement(By.xpath(salesChannelXpath)).click();

		if (util.getConfigProperty("store").equals("live")) {
			getDriver().findElement(By.xpath(salesChannelNameXpath)).click();
			getDriver().findElement(By.cssSelector("a[href*=\"/panel/user/dashboard\"]")).click();
		} else if (util.getConfigProperty("store").equals("staging")) {
			getDriver().findElement(By.xpath("//div[text()='" + getConfigProperty("sales_channel_staging") + "']"))
					.click();
			getDriver().findElement(By.cssSelector("a[href*=\"/panel/user/dashboard\"]")).click();
		}
		util.hold(1);
		switchToIFrame();
		waitUntilElementIsVisible(getDriver().findElement(By.id("subscription")), 30);

	}

	public void clickOnOverviewDemo() {
		WebElement element = getDriver().findElement(By.xpath("//span[text() = 'Overview']"));
		moveToElement(element);
		element.click();
		try {
			WebElement iframe = getDriver().findElement(By.name("app-iframe"));
			getDriver().switchTo().frame(iframe);
		} catch (NoSuchElementException e) {
			logWarn("no such element exception occured");
		}
	}

	public void searchFailedOrder(String orderId) {

		WebElement searchWithAmazonOrderIdInputField = getDriver()
				.findElement(By.xpath("//input[@placeholder = 'Search with Amazon Order Id']"));
		searchWithAmazonOrderIdInputField.sendKeys(orderId);
		hold(3);

	}

	public void clickOnListing() {
		getDriver().findElement(By.xpath("//span[text() = 'Listing']")).click();
	}

	public void clickOnProductLinking() {
		getDriver().findElement(By.xpath("//span[text() = 'Product Linking']")).click();
	}

	public void clickOnSettings() {
		util.hold(1);
		util.switchToDefaultContent();

		if (getConfigProperty("store").equals("staging")) {
			getDriver().findElement(By.xpath("//span[text() = 'Config']")).click();
		} else {
			getDriver().findElement(By.cssSelector("a[href*='panel/user/config']")).click();
		}

		switchToIFrame();
	}

	public void clickOnListings(String server) {
		switchToDefaultContent();
		getDriver().findElement(By.xpath(salesChannelXpath)).click();

		if (server.equals("staging")) {
			getDriver().findElement(By.xpath("//div[text() = 'Amazon By Cedcommerce-Staging']")).click();
		} else {
			getDriver().findElement(By.xpath(salesChannelNameXpath)).click();
		}

		getDriver().findElement(By.xpath("//span[contains(text(),'Listing')]")).click();
		switchToIFrame();
		waitUntilElementIsVisible(getDriver()
				.findElement(By.xpath("//input[@placeholder = 'Search with Title, Vendor, Product type or Barcode']")));
	}

	public void clickOnFAQ() {
		util.switchToDefaultContent();
		WebElement element = getDriver().findElement(By.xpath("//span[text() = 'FAQ']"));
		element.click();
		util.hold(10);
		util.switchToIFrame();
	}

	public void goToMultiAccountsSettingsClick() {
		switchToDefaultContent();
		getDriver().findElement(By.xpath(salesChannelXpath)).click();

		if (util.getConfigProperty("store").equals("live")) {
			getDriver().findElement(By.xpath(salesChannelNameXpath)).click();
			getDriver().findElement(By.cssSelector("a[href*='panel/user/config']")).click();
		} else if (util.getConfigProperty("store").equals("staging")) {
			getDriver().findElement(By.cssSelector("a[href*='panel/user/config']")).click();

		}

		refreshPage();
		switchToIFrame();
		waitUntilElementIsVisible(getDriver().findElement(By.id(prodTemplatesXpath)), 30);
	}

	public void goToListingsClick() {
		switchToDefaultContent();
		getDriver().findElement(By.xpath(salesChannelXpath)).click();

		if (util.getConfigProperty("store").equals("live")) {
			getDriver().findElement(By.xpath(salesChannelNameXpath)).click();
			getDriver().findElement(By.cssSelector("a[href$='panel/user/product']")).click();
		} else if (util.getConfigProperty("store").equals("staging")) {
			getDriver().findElement(By.xpath("//div[text() = 'Amazon By Cedcommerce-Staging']")).click();
			getDriver().findElement(By.cssSelector("a[href$='panel/user/product']")).click();
		}
		util.hold(1);
		util.refreshPage();
		switchToIFrame();
		waitUntilElementIsVisible(getDriver().findElement(
				By.cssSelector("input[placeholder='Search with Title, Vendor, Product type or Barcode']")), 30);
	}

	public void closeShopifySettings() {
		util.hold(2);
		click(getDriver().findElement(By.cssSelector("div#SettingsDialog button[aria-label='Close']>span")));
	}

	public void goToMultiAcccounSettings() {
		getDriver().get(ShopifyPage.pageUrl + "/config");
		switchToIFrame();
		WebElement settingsHeading = getDriver()
				.findElement(By.xpath("//h1[text() = 'Settings' or text() = 'Account & Configuration']"));
		new WebDriverWait(getDriver(), Duration.ofSeconds(180)).until(ExpectedConditions.visibilityOf(settingsHeading));
	}

	public void clickOnMultiAcccounSettings() {
		switchToDefaultContent();
		getDriver().findElement(By.xpath(salesChannelXpath)).click();

		if (util.getConfigProperty("store").equals("live")) {
			getDriver().findElement(By.xpath(salesChannelNameXpath)).click();
		} else if (util.getConfigProperty("store").equals("staging")) {
			getDriver().findElement(By.xpath("//div[text() = 'Amazon By Cedcommerce-Staging']")).click();
		}

		getDriver().findElement(By.cssSelector("a[href$='/amazon-sales-channel-1/panel/user/config']")).click();
		switchToIFrame();
		waitUntilElementIsVisible(getDriver().findElement(By.id("ordersettings")), 30);

	}

	public void goToMultiAcccounFAQ() {
		getDriver().get(ShopifyPage.pageUrl + "/faq");
		switchToIFrame();
		WebElement heading = getDriver().findElement(By.xpath("//h1[text() = 'FAQ & Troubleshoot']"));
		new WebDriverWait(getDriver(), Duration.ofSeconds(60)).until(ExpectedConditions.visibilityOf(heading));
	}

	public void goToMultiAccountOverView() {
		switchToDefaultContent();
		getDriver().findElement(By.xpath(salesChannelXpath)).click();

		if (util.getConfigProperty("store").equals("live")) {
			getDriver().findElement(By.xpath(salesChannelNameXpath)).click();
		} else if (util.getConfigProperty("store").equals("staging")) {
			getDriver().findElement(By.xpath("//div[text() = '" + getConfigProperty("sales_channel_staging") + "']"))
					.click();
		}

		getDriver().findElement(By.cssSelector("a[href$='panel/user/dashboard']")).click();
		util.hold(1);
		switchToIFrame();
		waitUntilElementIsVisible(getDriver().findElement(By.cssSelector("#activities")), 30);
	}

	public void goToMultiAccountOrders() {
		switchToDefaultContent();
		getDriver().findElement(By.xpath(salesChannelXpath)).click();

		if (util.getConfigProperty("store").equals("live")) {

			getDriver().findElement(By.cssSelector("ul#search-results a[href*='/apps/amazon-sales-channel-1']"))
					.click();
			getDriver().findElement(By.cssSelector("a[href*='/apps/amazon-sales-channel-1/panel/user/allSales']"))
					.click();
		} else if (util.getConfigProperty("store").equals("staging")) {
			
			getDriver().findElement(By.cssSelector("ul#search-results a[href*='/apps/amazon-by-cedcommerce-staging']"))
					.click();
			getDriver().findElement(By.cssSelector("a[href$='panel/user/dashboard']")).click();
			switchToIFrame();
			waitUntilElementIsVisible(getDriver().findElement(By.cssSelector("#activities")), 30);

//			click(getDriver().findElement(By.xpath("//p[text()='Total Orders']/following-sibling::button")));
//
//			waitUntilElementIsVisible(
//					getDriver().findElement(By.cssSelector("input[placeholder='Search using the Amazon order ID']")),
//					30);
		}

		util.hold(1);
		switchToIFrame();
		waitUntilElementIsVisible(getDriver().findElement(By.id("sales")), 30);
		getDriver().findElement(By.xpath("//p[text()='Total Orders']/following-sibling::button")).click();

	}

	public void goToMultiAccountListings() {
		getDriver().get(ShopifyPage.pageUrl + "/product");
		switchToIFrame();
		WebElement heading = getDriver().findElement(By.xpath("//h1[text() = 'Listings']"));
		new WebDriverWait(getDriver(), Duration.ofSeconds(180)).until(ExpectedConditions.visibilityOf(heading));
	}

	public void clickOnMultiListings() {
		getDriver().switchTo().defaultContent();
		getDriver().findElement(By.cssSelector("a[href$='panel/user/product']")).click();
		switchToIFrame();
		WebElement heading = getDriver().findElement(By.xpath("//h1[text() = 'Listings']"));
		new WebDriverWait(getDriver(), Duration.ofSeconds(180)).until(ExpectedConditions.visibilityOf(heading));

	}

	public void clickOnAmazonByCedcommerce() {
		getDriver().findElement(By.xpath("//span[text() = 'Amazon by CedCommerce']")).click();
	}

	public void clickOnMultiAccount() {
		getDriver().findElement(By.xpath("//span[text() = 'Amazon Multi Account Demo']")).click();
	}

	public void switchToIFrame(WebElement element) {
		new WebDriverWait(getDriver(), Duration.ofSeconds(15))
				.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
	}

	public void switchToDefaultContent() {
		getDriver().switchTo().defaultContent();
	}

	public void clickOnTemplates() {
		WebElement ele = getDriver().findElement(By.id(prodTemplatesXpath));
		waitUntilElementIsVisible(ele, 30);
		click(ele);
		hold(1);
		waitUntilElementIsVisible(getDriver().findElement(By.xpath("//span[text() = 'Add New Template']")), 30);
		click(getDriver().findElement(By.xpath("//span[text() = 'Add New Template']")));

	}

	public void clickOnCustomTemplates() {
		click(getDriver().findElement(By.id(prodTemplatesXpath)));
		hold(1);
		actionClick(getDriver().findElement(
				By.xpath("//span[text() = 'Product Templates' or text() = 'Create new listing template']")));
	}

	public void clickOnKebabMenu() {
		List<WebElement> kebabMenuList = getDriver().findElements(
				By.xpath("//div[@class='custom-product--templateName']/parent::div/following-sibling::div[2]//button"));
		click(kebabMenuList.get(0));
	}

	public void actionClick(WebElement element) {
		action = new Actions(getDriver());
		action.moveToElement(element);
		action.click(element).build().perform();

	}

	/**
	 * to open link in new tab
	 */
	public void controlClickToOpenNewTab(WebElement element) {
		action = new Actions(getDriver());
		action.moveToElement(element);
		action.keyDown(Keys.LEFT_CONTROL).click(element).keyUp(Keys.LEFT_CONTROL).build().perform();

	}

	public void scrollToBottom() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
		jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollBy(int start, int end) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
		jsExecutor.executeScript("window.scrollBy(" + start + "," + end + ");");
	}

	public void scrollToTop() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
		jsExecutor.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
	}

	public void jsClick(WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void jsMoveToElement(WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public String extractValueByAttributes(WebElement element, String attributeName) {
		try {
			return element.getAttribute(attributeName);
		} catch (NoSuchElementException e) {
			moveToElement(element);
			return element.getAttribute(attributeName);
		}

	}

	public void enableButton(WebElement element) {
		if (extractValueByAttributes(element, ariaChecked).equals(FALSE)) {

			try {
				util.click(element);
				log.info("element is enabled");
			} catch (NoSuchElementException e) {
				actionClick(element);
			}

		}
	}

	public void enableButtonAndValidate(WebElement element) {
		if (extractValueByAttributes(element, ariaChecked).equals(FALSE)) {
			util.click(element);

			if (extractValueByAttributes(element, ariaChecked).equals("true")) {
				log.info("element is enabled");
				Assert.assertTrue(true);
			} else {
				log.error("element is disabled.");
				Assert.assertTrue(false);
			}
		}
	}

	public void disableButton(WebElement element) {
		if (extractValueByAttributes(element, ariaChecked).equals("true")) {
			try {
				moveToElement(element);
				util.click(element);
				log.info("element is disabled");
			} catch (NoSuchElementException | ElementClickInterceptedException e) {
				actionClick(element);
			}
		}
	}

	public void disableButtonAndValidate(WebElement element) {
		if (extractValueByAttributes(element, ariaChecked).equals("true")) {
			util.click(element);

			if (extractValueByAttributes(element, ariaChecked).equals(FALSE)) {
				log.info("element is disabled.");
				Assert.assertTrue(true);
			} else {
				log.error("element is enabled.");
				Assert.assertTrue(false);
			}
		}
	}

	public String getTagValue(WebElement element) {
		return element.getText();
	}

	public void validateContainsValue(WebElement element, String value) {

		if (StringUtils.containsIgnoreCase(util.getTagValue(element), value)) {
			log.info("contains value");
			Assert.assertTrue(true);
		} else {
			log.error("does not contains value");
			Assert.assertTrue(false);
		}
	}

	public boolean validateReturnContainsValue(WebElement element, String value) {
		boolean isTrue = false;

		if (StringUtils.containsIgnoreCase(util.getTagValue(element), value)) {
			isTrue = true;
			return isTrue;
		}
		return isTrue;
	}

	public boolean isElementDisplyed(WebElement element) {

		try {
			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			jsMoveToElement(element);
			return element.isDisplayed();
		}
	}

	public void listIsEmpty(List<WebElement> eleList) {
		boolean isTrue = false;

		if (eleList.isEmpty()) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);
	}

	public void listIsNotEmpty(List<WebElement> eleList) {
		boolean isTrue = false;

		if (!eleList.isEmpty()) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);
	}

	public void verifyElementVisible(WebElement weEle) {
		boolean isTrue = false;
		try {
			boolean weStatus = weEle.isDisplayed();
			Dimension dim = weEle.getSize();
			int height = dim.getHeight();
			int width = dim.getWidth();

			if (weStatus && height > 0 && width > 0) {
				isTrue = true;
				Assert.assertTrue(isTrue);
			}
			Assert.assertTrue(isTrue);
		} catch (Exception e) {
			logWarn("exception occured in verifyElementVisible()");
		}

	}

	public void isElementDisplyedAndValidate(WebElement element) {

		try {
			if (element.isDisplayed()) {
				Assert.assertTrue(true);
				log.info("element is displayed");
			} else {
				log.error("element is not displayed");
				Assert.assertTrue(false);
			}
		} catch (NoSuchElementException e) {
			jsMoveToElement(element);

			if (element.isDisplayed()) {
				Assert.assertTrue(true);
				log.info("element is displayed");
			} else {
				log.error("element is not displayed");
				Assert.assertTrue(false);
			}
		}
	}

	public void isElementNotDisplyedAndValidate(WebElement element) {

		try {
			if (!element.isDisplayed()) {
				Assert.assertTrue(true);
				log.info("element is displayed");
			} else {
				log.error("element is not displayed");
				Assert.assertTrue(false);
			}
		} catch (NoSuchElementException e) {
			jsMoveToElement(element);

			if (!element.isDisplayed()) {
				Assert.assertTrue(true);
				log.info("element is displayed");
			} else {
				log.error("element is not displayed");
				Assert.assertTrue(false);
			}
		}
	}

	public boolean isElementVisible(WebElement element) {
		boolean isTrue = false;

		if ((getTagValue(element).length()) > 0) {
			isTrue = true;
			return isTrue;
		}
		return isTrue;
	}

	public boolean isElementEnabled(WebElement element) {
		return element.isEnabled();
	}

	public void validateIsElementEnabled(WebElement element) {
		if (element.isEnabled()) {
			logInfo("button is enabled");
			Assert.assertTrue(true);
		} else {
			logInfo("button is disabled");
			Assert.assertTrue(false);
		}
	}

	public void validateIsElementDisabled(WebElement element) {
		if (!element.isEnabled()) {
			logInfo("button is disabled");
			Assert.assertTrue(true);
		} else {
			logInfo("button is enabled");
			Assert.assertTrue(false);
		}
	}

	public void validateIsElementDisabledUsingAttribute(WebElement element) {
		if (extractValueByAttributes(element, "disabled").equals("true")) {
			logInfo("button is disabled");
			Assert.assertTrue(true);
		} else {
			logInfo("button is enabled");
			Assert.assertTrue(false);
		}
	}

	public void validateIsElementEnabledUsingAttribute(WebElement element) {
		if (extractValueByAttributes(element, "disabled").equals(FALSE)) {
			logInfo("button is enabled");
			Assert.assertTrue(true);
		} else {
			logInfo("button is disabled");
			Assert.assertTrue(false);
		}
	}

	public void validateWorking(WebElement element) {
		boolean isTrue = false;

		if (extractValueByAttributes(element, ariaChecked).equals(FALSE)) {
			enableButton(element);

			if (extractValueByAttributes(element, ariaChecked).equals("true")) {
				isTrue = true;
				Assert.assertTrue(isTrue);
			}
			Assert.assertTrue(isTrue);

		} else if (extractValueByAttributes(element, ariaChecked).equals("true")) {
			disableButton(element);
			isTrue = false;

			if (extractValueByAttributes(element, ariaChecked).equals(FALSE)) {
				isTrue = true;
				Assert.assertTrue(isTrue);
			}
			Assert.assertTrue(isTrue);
		}
	}

	public void validateElementIsClickable(WebElement element) {

		boolean status = element.isSelected();
		String attrStatus = extractValueByAttributes(element, ariaChecked);

		if (!status || attrStatus.equals(FALSE)) {

			click(element);

			if (element.isSelected() || (extractValueByAttributes(element, ariaChecked).equals("true"))) {
				logInfo(selectMsg);
				Assert.assertTrue(true);
			} else {
				logError("button is not selected");
				Assert.assertTrue(false);
			}
		} else if (status || attrStatus.equals("true")) {

			click(element);

			if (!element.isSelected() || (extractValueByAttributes(element, ariaChecked).equals(FALSE))) {
				logInfo("button is not selected");
				Assert.assertTrue(true);
			} else {
				logError(selectMsg);
				Assert.assertTrue(false);
			}
		}
	}

	public void enableAndDisableElement(WebElement element) {
		String value = extractValueByAttributes(element, ariaChecked);

		switch (value) {
		case "true":
			disableButton(element);
			break;
		case "false":
			enableButton(element);
			break;
		default:
			break;
		}
	}

	WebElement saveBtn;

	public void clickOnSaveBtn() {
		switchToDefaultContent();
		saveBtn = getDriver().findElement(By.xpath("//span[text() = 'Save']"));
		click(saveBtn);
		switchToIFrame();
	}

	public void enableAndDisableElementAndValidate(WebElement element) {
		String value = extractValueByAttributes(element, ariaChecked);

		switch (value) {
		case "true":
			disableButton(element);
			clickOnSaveBtn();
			validateIsElementDisabledUsingAttribute(element);
			break;
		case "false":
			enableButton(element);
			clickOnSaveBtn();
			validateIsElementEnabledUsingAttribute(element);
			break;
		default:
			break;
		}
	}

	public boolean isElementSelected(WebElement element) {
		return element.isSelected();
	}

	public void validateIsElementSelected(WebElement element) {
		if (element.isSelected()) {
			logInfo(selectMsg);
			Assert.assertTrue(true);
		} else {
			log.warn("button is not selected");
			Assert.assertTrue(false);
		}
	}

	public void validateIsElementSelectedUsingAttribute(WebElement element) {

		if (extractValueByAttributes(element, ariaChecked).equals("true")) {
			logInfo(selectMsg);
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

	public void validateIsNotElementSelectedUsingAttribute(WebElement element) {

		if (extractValueByAttributes(element, ariaChecked).equals(FALSE)) {
			logInfo(selectMsg);
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

	public void validateIsElementNotSelected(WebElement element) {
		if (!element.isSelected()) {
			logInfo("button is not selected");
			Assert.assertTrue(true);
		} else {
			logInfo(selectMsg);
			Assert.assertTrue(false);
		}
	}

	public void getWindoHandleByUrl(String expURLValue) {
		Set<String> allWindowValue = getDriver().getWindowHandles();
		for (String allValue : allWindowValue) {
			getDriver().switchTo().window(allValue);
			String getTitleValue = getDriver().getCurrentUrl();
			if (getTitleValue.contains(expURLValue)) {
				break;
			}

		}
	}

	public void goToChildWindow() {
		Set<String> windows = getDriver().getWindowHandles();
		Iterator<String> itr = windows.iterator();
		parentWindow = itr.next();
		childWindow = itr.next();
		getDriver().switchTo().window(childWindow);
	}

	public void gotoWindowByClosingCurrentOne(String url) {
		try {
			getDriver().close();
			getWindoHandleByUrl(url);
		} catch (NoSuchWindowException e) {
			logWarn("NoSuchWindowException ");
		}
	}

	public void closeCurrentWindow() {
		try {
			getDriver().close();
		} catch (NoSuchWindowException e) {
			logWarn("NoSuchWindowException ");
		}
	}

	public void goToParentWindow() {
		Set<String> windows = getDriver().getWindowHandles();
		Iterator<String> itr = windows.iterator();
		parentWindow = itr.next();
		childWindow = itr.next();
		getDriver().switchTo().window(parentWindow);
	}

	public String getPageURL() {
		return getDriver().getCurrentUrl();
	}

	public String getPageTitle() {
		return getDriver().getTitle();
	}

	public boolean clickOnEachElementOfList(List<WebElement> elementList, WebElement elementToBeDisplayed) {
		for (WebElement element : elementList) {
			element.click();

			if (elementToBeDisplayed.isDisplayed()) {
				return true;
			}
		}
		return false;
	}

	public void failIfTimeIsExceed(List<WebElement> allQuerySugesstion, int seconds) {
		new WebDriverWait(getDriver(), Duration.ofSeconds(seconds))
				.until(ExpectedConditions.visibilityOfAllElements(allQuerySugesstion));
	}

	public void failIfTimeIsExceed(WebElement element, int seconds) {
		new WebDriverWait(getDriver(), Duration.ofSeconds(seconds)).until(ExpectedConditions.visibilityOf(element));
	}

	public void waitUntilElementIsVisible(WebElement element) {
		new WebDriverWait(getDriver(), Duration.ofSeconds(180)).until(ExpectedConditions.visibilityOf(element));
	}

	public void fluentWaitToInVisiblity(WebElement element, int waitTime, int pollTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver()).withTimeout(Duration.ofMinutes(waitTime))
				.pollingEvery(Duration.ofMinutes(pollTime)).ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public void waitUntilElementIsVisible(WebElement element, int sec) {
		new WebDriverWait(getDriver(), Duration.ofSeconds(sec)).until(ExpectedConditions.visibilityOf(element));
	}

	public void waitUntilListIsVisible(List<WebElement> list, int sec) {
		new WebDriverWait(getDriver(), Duration.ofSeconds(sec)).until(ExpectedConditions.visibilityOfAllElements(list));
	}

	public void waitUntilElementIsInvisible(WebElement element) {
		new WebDriverWait(getDriver(), Duration.ofSeconds(180)).until(ExpectedConditions.invisibilityOf(element));
	}

	public void waitUntilPageIsLoaded(int sec) {
		new WebDriverWait(getDriver(), Duration.ofSeconds(sec))
				.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));
	}

	public void waitUntilElementIsClickable(WebElement element, int sec) {
		new WebDriverWait(getDriver(), Duration.ofSeconds(sec)).until(ExpectedConditions.elementToBeClickable(element));
	}

	public void hold(int seconds) {
		seconds *= 1000;
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			logWarn("exception occured in hold().");
			Thread.currentThread().interrupt();
		}
	}

	public void refreshPage() {
		getDriver().navigate().refresh();
	}

	public void hardReloadPage() {
		action = new Actions(getDriver());

		action.keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).sendKeys("r").keyUp(Keys.CONTROL).keyUp(Keys.SHIFT).build()
				.perform();
		switchToIFrame();
	}

	public void copy(WebElement element) {

		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(Keys.CONTROL + "c");

	}

	public void paste(WebElement element) {
		action = new Actions(getDriver());
		element.sendKeys(Keys.CONTROL + "a");

		element.sendKeys(Keys.CONTROL + "v");

	}

	public boolean isPasswordHave8Characters(String password) {
		boolean isTrue = false;

		if (password.length() > 7) {
			isTrue = true;
			return isTrue;
		}
		return isTrue;
	}

	public void selectFromList(List<WebElement> listOfElements, String value) {
		for (WebElement element : listOfElements) {

			if (element.getText().equals(value)) {
				element.click();
				break;
			}
		}
	}

	public boolean selectedInterest(List<WebElement> listOfElements, String interestValue) {
		boolean isTrue = false;
		for (WebElement interest : listOfElements) {
			if (interest.getText().equalsIgnoreCase(interestValue)) {
				isTrue = true;
				return isTrue;
			}
		}
		return isTrue;
	}

	public void selectFromListByAttribute(List<WebElement> listOfElements, String attribute, String position) {
		for (WebElement element : listOfElements) {
			if (element.getAttribute(attribute).equalsIgnoreCase(element + position)) {
				element.click();
				break;
			}
		}
	}

	public int getSize(List<WebElement> list) {
		return list.size();
	}

	public void goBack() {
		action = new Actions(getDriver());
		action.keyDown(Keys.ALT).build().perform();
		action.keyDown(Keys.ARROW_LEFT).build().perform();
		action.keyUp(Keys.ARROW_LEFT).build().perform();
		action.keyUp(Keys.ALT).build().perform();

	}

	public void moveToElement(WebElement element) {
		action = new Actions(getDriver());
		action.moveToElement(element).build().perform();
	}

	public void dragAndDrop(WebElement draggableElement, WebElement targetElement) {
		action = new Actions(getDriver());
		action.moveToElement(draggableElement).pause(Duration.ofSeconds(1)).clickAndHold(draggableElement)
				.pause(Duration.ofSeconds(1)).moveByOffset(1, 0).moveToElement(targetElement).moveByOffset(1, 0)
				.pause(Duration.ofSeconds(1)).release().perform();
	}

	public String getConfigProperty(String key) {
		prop = new Properties();
		path = System.getProperty("user.dir");

		try (FileInputStream fis = new FileInputStream(path + "/src/main/java/com/ami/resources/config.Properties")) {
			prop.load(fis);
		} catch (FileNotFoundException e) {
			log.fatal("Exception occurred: file not found in utilities.");
		} catch (IOException e) {
			log.fatal("Exception occurred: while loading file in Utilities.");
		}

		return prop.getProperty(key.trim());
	}

	public String getProperty(String fileName, String key) {
		prop = new Properties();
		path = System.getProperty("user.dir");

		try (FileInputStream fis = new FileInputStream(path + "/Properties/" + fileName + ".properties")) {
			prop.load(fis);
		} catch (FileNotFoundException e) {
			log.fatal("Exception occurred: file not found in utilities.");
		} catch (IOException e) {
			log.fatal("Exception occurred: while loading file in Utilities.");
		}

		return prop.getProperty(key.trim());
	}

	public String getValueFromVariableProperties(String key) {
		Properties prop = new Properties();
		String path = System.getProperty("user.dir");

		try (FileInputStream fis = new FileInputStream(path + "/Properties/variables.properties")) {
			prop.load(fis);
		} catch (FileNotFoundException e) {
			log.fatal("Exception occurred: file not found in utilities.");
		} catch (IOException e) {
			log.fatal("Exception occurred: error while loading file in Utilities.");
		}

		return prop.getProperty(key.trim());
	}

	public void updateProperty(String key, String value) {
		String filePath = System.getProperty("user.dir") + "/Properties/variables.properties";
		prop = new Properties();

		try (FileInputStream fis = new FileInputStream(filePath);
				FileOutputStream fos = new FileOutputStream(filePath)) {

			// Load the existing properties from the file
			prop.load(fis);

			// Update a property
			prop.setProperty(key.trim(), value);

			// Save the updated properties to the file
			prop.store(fos, "Updated properties");

		} catch (IOException e) {
			log.warn(e.getMessage());
		}
	}

	public void updateWebHooksProperty(String fileName, String key, String value) {
		String filePath = System.getProperty("user.dir") + "/Properties/" + fileName + ".properties";

		try {
			PropertiesConfiguration config = new PropertiesConfiguration(filePath);
			config.setProperty(key, value);
			config.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

	}

	public LocalDate getToday() {

		return LocalDate.now(); // Create a date object
	}

	public String getDateInDDMMYYYY(LocalDate date) {
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		return date.format(formatters);

	}

	public LocalDate getTomorrow() {
		return getToday().plusDays(1);
	}

	public LocalDate getDayAfterTomorrow() {
		return getToday().plusDays(2);
	}

	public LocalDate getYesterday() {
		return getToday().plusDays(-1);
	}

	public int randomNum() {
		SecureRandom secureRandom = new SecureRandom();
		int min = 1000; // Minimum 4-digit number
		int max = 9999; // Maximum 4-digit number
		return secureRandom.nextInt(max - min + 1) + min;
	}

	public static String amazonOrderId() {

		SecureRandom secureRandom = new SecureRandom();
		int randomNum1 = secureRandom.nextInt(1000);
		int randomNum2 = secureRandom.nextInt(10000001);
		int randomNum3 = secureRandom.nextInt(10000001);
		return Integer.toString(randomNum1) + "-" + Integer.toString(randomNum2) + "-" + Integer.toString(randomNum3);

	}

	public String orderItemId() {
		SecureRandom secureRandom = new SecureRandom();
		int randomNum1 = secureRandom.nextInt(1000001);
		int randomNum2 = secureRandom.nextInt(1000001);
		return Integer.toString(randomNum1) + Integer.toString(randomNum2);
	}

	public float floatRandomNumber() {
		SecureRandom secureRandom = new SecureRandom();
		return secureRandom.nextFloat() * 201; // 0 to 100
	}

	public float itemTax() {
		return floatRandomNumber() / 10;
	}

	public String extractWordAfterACharacter(String str, String character, int position) {
		String[] arr = str.split(character);
		return arr[position];

	}

	public void inpuData(WebElement webEle, String inputvalue) {

		try {
			webEle.clear();
			webEle.sendKeys(inputvalue);
		} catch (StaleElementReferenceException e) {
			webEle.clear();
			webEle.sendKeys(inputvalue);
		} catch (ElementNotInteractableException e) {

		}
	}

	public int getRowCountofTcID(Sheet sheetobj, String testcaseIdName) {
		int activateRowNum = sheetobj.getLastRowNum();
		int count = 0;
		for (int i = 1; i <= activateRowNum; i++) {
			Row rowObj = sheetobj.getRow(i);
			Cell cellObj = rowObj.getCell(1);
			String actualCellvalue = cellObj.getStringCellValue();
			if (actualCellvalue.equals(testcaseIdName)) {
				count++;
			}
		}
		return count;

	}

//	Data Reader from json file

	public List<HashMap<String, String>> getJsonDataToHashmap(String jsonFileName) throws IOException {
//		reading json to string
		String jsonContent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "/src/main/java/com/ami/resources/" + jsonFileName + ".json"),
				StandardCharsets.UTF_8);

//		String to Hashmap

		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});
	}

	public void logInfo(String msg) {
		log.info(msg);
	}

	public void logError(String msg) {
		log.error(msg);
	}

	public void logWarn(String msg) {
		log.warn(msg);
	}

	public void logFatal(String msg) {
		log.fatal(msg);
	}

	public void clear(WebElement element) {
		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(Keys.BACK_SPACE);

	}

	public boolean matchAllRegExpression(String value) {
		// The regex ".*" matches any sequence of characters
		return value.matches(".*");
	}

	public boolean matchCharacterRegExpression(String value) {
		String regex = "^[a-zA-Z]+$";
		Pattern p = Pattern.compile(regex);
		hold(1);

		try {
			Matcher m = p.matcher(value);
			return m.matches();
		} catch (Exception e) {
			logWarn("Exception occured in matchCharacterRegExpression().");
			return false;
		}
	}

	public boolean matchRegExpressionNumSpecialChar(String value) {
		String regex = "[\\d\\W]";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(value);
		return m.find();
	}

	public boolean matchIfStringContainsSpecialChar(String value) {
		String regex = "[^a-zA-Z0-9\\s]";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		return matcher.find();
	}

	public boolean matchRegExpressionNumbersOnly(String value) {
		if (value == null || value.isEmpty()) {
			return false;
		}

		String regex = "^\\d*$";

		try {
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(value);
			return matcher.matches();
		} catch (PatternSyntaxException e) {
			logWarn("Exception occured in matchRegExpressionNumbersOnly().");
			return false;
		}
	}

	public boolean matchRegExpressionDecimalNumbers(String value) {

		String regex = "^-?\\d+(\\.\\d+)?$";

		try {
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(value);
			return m.matches();
		} catch (PatternSyntaxException e) {
			logWarn("Exception occured in matchRegExpressionDecimalNumbers().");
			return false;
		}
	}

	public void validateCursorProperty(WebElement element, String expectedProperty) {
		moveToElement(element);
		if (element.getCssValue("cursor").equals(expectedProperty)) {
			Assert.assertTrue(true);
			log.info("cursor property is changing ");
		} else {
			log.info("cursor property is not changing.");
			Assert.assertTrue(false);
		}
	}

	public void clickOnAmzByCedLogo() {
		switchToDefaultContent();
		WebElement element = getDriver().findElement(By.className("uYfDd"));
		hold(1);
		actionClick(element);
		hold(1);
		switchToIFrame();
		WebElement sellerHeading = getDriver().findElement(By.xpath("//span[text() = 'Seller Name']"));
		waitUntilElementIsVisible(sellerHeading);
	}

	public void selectByValue(WebElement element, String value) {
		Select drpDwn = new Select(element);
		drpDwn.selectByValue(value);
	}

	public void selectByIndex(WebElement element, int index) {
		Select drpDwn = new Select(element);
		drpDwn.selectByIndex(index);
	}

	public void selectByVisibleText(WebElement element, String value) {
		Select drpDwn = new Select(element);
		drpDwn.selectByVisibleText(value);
	}

	public String getSelectedOptionInDropDown(WebElement element) {
		return new Select(element).getFirstSelectedOption().getText();
	}

	public void validateSelectedOption(WebElement element, String expecValue) {
		boolean isTrue = false;
		Select drpDwn = new Select(element);
		String selectedVal = drpDwn.getFirstSelectedOption().getText();

		if (expecValue.equals(selectedVal)) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);
	}

	public List<WebElement> getOptionList(WebElement element) {
		Select select = new Select(element);
		return select.getOptions();

	}

	public void selectContainsValues(WebElement element, String value) {

		if (element.getText().equals(value)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

	public void contextualSaveBarIsDisplayed() {
		switchToDefaultContent();
		WebElement contextualSaveBar = getDriver().findElement(By.id("AppFrameTopBar"));
		isElementDisplyed(contextualSaveBar);
		switchToIFrame();
	}

	public void contextualSaveBarIsNotDisplayed() {
		boolean isTrue = false;
		List<WebElement> contextualSaveBar = getDriver().findElements(By.id("AppFrameTopBar"));
		switchToDefaultContent();

		if (contextualSaveBar.isEmpty()) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);

		switchToIFrame();
	}

	public void discardAndSaveBtnAreNotDisplayed() {
		boolean isTrue = false;
		switchToDefaultContent();
		List<WebElement> discardBtnList = getDriver().findElements(By.xpath("//span[contains(text(),'Discard')]"));
		List<WebElement> saveBtnList = getDriver().findElements(By.xpath("//span[contains(text(),'Save')]"));

		if ((discardBtnList.isEmpty()) && (saveBtnList.isEmpty())) {
			isTrue = true;
			Assert.assertTrue(isTrue);
		}
		Assert.assertTrue(isTrue);

		switchToIFrame();
	}

	public void disableElement() {
		WebElement element = getDriver().findElement(By.id("q1aenit76ig81679396972740"));
		getDriver().switchTo().frame(element);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
		jsExecutor.executeScript("document.getElementByClass(\"tawk-min-container\").disabled = true;");
	}

	public String randomString(int n) {

		// choose a Character random from this String
		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz"
				+ "!@#$%^&*()";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		// Use SecureRandom for better randomness
		SecureRandom secureRandom = new SecureRandom();

		for (int i = 0; i < n; i++) {
			// generate a random number between 0 to AlphaNumericString variable length
			int index = secureRandom.nextInt(alphaNumericString.length());

			// add Character one by one at the end of sb
			sb.append(alphaNumericString.charAt(index));
		}

		return sb.toString();

	}

	public String randString(int n) {

		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		// Use SecureRandom for better randomness
		SecureRandom secureRandom = new SecureRandom();

		for (int i = 0; i < n; i++) {
			// generate a random number between 0 to AlphaNumericString variable length
			int index = secureRandom.nextInt(alphaNumericString.length());

			// add Character one by one at the end of sb
			sb.append(alphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	public void validateListIsEmpty(List<WebElement> eleList) {
		boolean isEmpty = false;

		if (eleList.isEmpty()) {
			isEmpty = true;
			Assert.assertTrue(isEmpty);
		}
		Assert.assertTrue(isEmpty);
	}

	public void validateListIsNotEmpty(List<WebElement> eleList) {
		boolean isNotEmpty = false;

		if (!eleList.isEmpty()) {
			isNotEmpty = true;
			Assert.assertTrue(isNotEmpty);
		}
		Assert.assertTrue(isNotEmpty);
	}

	public void openAndSwitchToNewTab() {
		getDriver().switchTo().newWindow(WindowType.TAB);
	}

	public void openAndSwitchToNewWindow() {
		getDriver().switchTo().newWindow(WindowType.WINDOW);
	}

	public void closeAllWindows() {
		// Get the window handle of the currently active window
		String currentWindowHandle = getDriver().getWindowHandle();
		// Get all the window handles
		Set<String> windowHandles = getDriver().getWindowHandles();
		// Iterate through the window handles
		for (String window : windowHandles) {
			// If the current window handle is not the active window, close it
			if (!window.equals(currentWindowHandle)) {
				getDriver().switchTo().window(window);
				getDriver().close();
			}
		}

		// Switch back to the original window
		getDriver().switchTo().window(currentWindowHandle);
	}

	String currentFrame = null;
	// make this currentFrame as global variable

	public void switchToFrame() {
		String frame = util.getConfigProperty("iframe");

		if ((null != frame) && (!"".equals(frame))) {
			if (!frame.equals(currentFrame)) {
				getDriver().switchTo().defaultContent();
				getDriver().switchTo().frame(frame);
				currentFrame = frame;
			}
		} else {
			currentFrame = "";
			getDriver().switchTo().defaultContent();
		}
	}

	public void compareStrings(String val1, String val2) {
		boolean isTrue = false;

		if (val1.equals(val2)) {
			isTrue = true;
		}
		Assert.assertTrue(isTrue);
	}

	public void openListingsInNewTabUsingUrl() {
		if (getConfigProperty("store").equals("live")) {
			openAndSwitchToNewTab();
			openUrl(util.getConfigProperty("storeUrl") + "apps/amazon-sales-channel-1/panel/user/product");
			switchToDefaultContent();
			util.hold(1);
			switchToIFrame();
			util.hold(1);

		} else if (getConfigProperty("store").equals("staging")) {
			openAndSwitchToNewTab();
			openUrl(util.getConfigProperty("stroreUrlStaging") + "apps/amazon-sales-channel-1/panel/user/product");
			switchToDefaultContent();
			util.hold(1);
			switchToIFrame();
			util.hold(1);

		}
	}

	public String getElementColor(WebElement element) {

		String color = element.getCssValue("background-color");
		String[] hexValue = color.replace("rgba(", "").replace(")", "").split(",");

		int hexValue1 = Integer.parseInt(hexValue[0]);
		hexValue[1] = hexValue[1].trim();
		int hexValue2 = Integer.parseInt(hexValue[1]);
		hexValue[2] = hexValue[2].trim();
		int hexValue3 = Integer.parseInt(hexValue[2]);

		return String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);

	}

	public void getAllCookies() throws IOException {
		Set<Cookie> allCookies = getDriver().manage().getCookies();
		File cookieFile = new File(System.getProperty("user.dir") + "/cookie.data");
		FileWriter writer = new FileWriter(cookieFile);
		BufferedWriter bWriter = new BufferedWriter(writer);

		try {
			for (Cookie cookie : allCookies) {
				bWriter.write((cookie.getName() + ";" + cookie.getValue() + ";" + cookie.getDomain() + ";"
						+ cookie.getPath() + ";" + cookie.getExpiry() + ";" + cookie.isSecure()));
				bWriter.newLine();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {

			bWriter.close();
			writer.close();
		}

	}

	public void validateLinkIsWorking(String url) {
		boolean isTrue = false;
		try {
			URL link = new URL(url);
			HttpURLConnection urlConn = (HttpURLConnection) link.openConnection();
			urlConn.setConnectTimeout(10000);
			urlConn.connect();

			if (urlConn.getResponseCode() == 200) {
				isTrue = true;
			}
		} catch (Exception e) {
			e.getMessage();
		}

		Assert.assertTrue(isTrue);
	}

	public void openSectionsInNewTab(String sectionName) {

		if (getConfigProperty("store").equalsIgnoreCase("live")) {
			switch (sectionName) {
			case "linking":
				openAndSwitchToNewTab();
				openUrl(getConfigProperty("storeUrl") + "apps/amazon-sales-channel-1/panel/user/productlinkings");
				break;

			case "settings":
				openAndSwitchToNewTab();
				openUrl(getConfigProperty("storeUrl") + "apps/amazon-sales-channel-1/panel/user/config");
				break;

			case "listings":
				openAndSwitchToNewTab();
				openUrl(getConfigProperty("storeUrl") + "apps/amazon-sales-channel-1/panel/user/product");
				break;

			case "overview":
				openAndSwitchToNewTab();
				openUrl(getConfigProperty("storeUrl") + "apps/amazon-sales-channel-1/panel/user/dashboard");
				break;

			default:
				break;
			}

		} else if (getConfigProperty("store").equalsIgnoreCase("staging")) {
			switch (sectionName) {
			case "linking":
				openAndSwitchToNewTab();
				openUrl(getConfigProperty("stroreUrlStaging")
						+ "apps/amazon-by-cedcommerce-staging/panel/user/dashboard");
				switchToIFrame();
				WebElement linkingRequried = getDriver().findElement(
						By.xpath("//span[text() = 'Linking Required']/parent::span/following-sibling::button"));
				util.waitUntilElementIsVisible(linkingRequried);
				util.hold(2);
				util.click(linkingRequried);
				util.hold(1);
				break;

			case "settings":
				openAndSwitchToNewTab();
				openUrl(getConfigProperty("stroreUrlStaging") + "apps/amazon-by-cedcommerce-staging/panel/user/config");
				break;

			case "listings":
				openAndSwitchToNewTab();
				openUrl(getConfigProperty("stroreUrlStaging")
						+ "apps/amazon-by-cedcommerce-staging/panel/user/product");
				break;

			case "overview":
				openAndSwitchToNewTab();
				openUrl(getConfigProperty("stroreUrlStaging")
						+ "apps/amazon-by-cedcommerce-staging/panel/user/dashboard");
				break;

			default:
				break;
			}

		}

		switchToIFrame();
	}

	public void goToSection(String sectionName) {

		if (getConfigProperty("store").equalsIgnoreCase("live")) {
			switch (sectionName) {
			case "linking":
				getWindoHandleByUrl(
						getConfigProperty("storeUrl") + "apps/amazon-sales-channel-1/panel/user/productlinkings");
				break;

			case "settings":
				getWindoHandleByUrl(getConfigProperty("storeUrl") + "apps/amazon-sales-channel-1/panel/user/config");
				break;

			case "listings":
				getWindoHandleByUrl(getConfigProperty("storeUrl") + "apps/amazon-sales-channel-1/panel/user/product");
				break;

			case "overview":
				getWindoHandleByUrl(getConfigProperty("storeUrl") + "apps/amazon-sales-channel-1/panel/user/dashboard");
				break;

			case "product":
				getWindoHandleByUrl(getConfigProperty("storeUrl") + "products/new");
				break;

			default:
				break;
			}
			switchToIFrame();

		} else if (getConfigProperty("store").equalsIgnoreCase("staging")) {
			switch (sectionName) {
			case "linking":
				getWindoHandleByUrl(getConfigProperty("stroreUrlStaging")
						+ "apps/amazon-by-cedcommerce-staging/panel/user/productlinkings");
				break;

			case "settings":
				getWindoHandleByUrl(
						getConfigProperty("stroreUrlStaging") + "apps/amazon-by-cedcommerce-staging/panel/user/config");
				break;

			case "listings":
				getWindoHandleByUrl(getConfigProperty("stroreUrlStaging")
						+ "apps/amazon-by-cedcommerce-staging/panel/user/product");
				break;

			case "overview":
				String url = getConfigProperty("stroreUrlStaging")
						+ "apps/amazon-by-cedcommerce-staging/panel/user/dashboard";
				getWindoHandleByUrl(url);
				break;

			case "product":
				getWindoHandleByUrl(getConfigProperty("stroreUrlStaging") + "products/new");
				break;

			default:
				break;
			}
			switchToDefaultContent();
			switchToIFrame();
		}
	}

	public void openNewProduct() {
		openAndSwitchToNewTab();
		hitNewProductUrl();
	}

	public void hitNewProductUrl() {
		if (getConfigProperty("store").equalsIgnoreCase("live")) {
			openUrl(getConfigProperty("storeUrl") + "products/new");
		} else if (getConfigProperty("store").equalsIgnoreCase("staging")) {
			openUrl(getConfigProperty("stroreUrlStaging") + "products/new");
		}
	}

	public void goToNewProduct() {
		if (getConfigProperty("store").equalsIgnoreCase("live")) {

			getWindoHandleByUrl(getConfigProperty("storeUrl") + "products/new");

		} else if (getConfigProperty("store").equalsIgnoreCase("staging")) {

			getWindoHandleByUrl(getConfigProperty("stroreUrlStaging") + "products/new");

		}

		WebElement ele = getDriver().findElement(By.name("title"));
		waitUntilElementIsVisible(ele, 30);
	}

	public void throwExceptionIfListIsEmpty(List<WebElement> list) throws EmptyListException {
		hold(10);

		if (list.isEmpty()) {
			throw new EmptyListException("List is empty after waiting for 10 seconds.");
		}

		// Your processing logic for the non-empty list goes here
		System.out.println("List is not empty. Processing...");
	}

	public String generateRandomString(int length) {
		Faker faker = new Faker();

		StringBuilder randomString = new StringBuilder();

		while (randomString.length() < length) {
			// Append random data types (e.g., name, address, number, etc.)
			randomString.append(faker.lorem().characters());
			randomString.append(faker.address().buildingNumber());
			randomString.append(faker.number().digits(5));
			randomString.append(faker.chuckNorris().fact());
		}

		// Trim the string to the desired length
		return randomString.substring(0, length);
	}

	public Map<String, String> readMail(String userName, String password) {
		Map<String, String> emailMap = new HashMap<>();
		String host = "imap.gmail.com";
		try {
			// create properties field
			Properties properties = new Properties();

			properties.setProperty("mail.imap.ssl.enable", "true");
			properties.setProperty("mail.imap.ssl.protocols", "TLSv1.2"); // Set the appropriate SSL/TLS protocol

			Session emailSession = Session.getInstance(properties);

			// create the IMAP store object and connect with the IMAP server
			Store store = emailSession.getStore("imap");

			store.connect(host, userName, password);

			// create the folder object and open it
			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			// retrieve the messages from the folder in an array and print them
			Message[] messages = emailFolder.getMessages();
			int size = (messages.length - 1);
			Message message = messages[size];
			System.out.println("---------------------------------");
			System.out.println("Email Number " + (size));
			System.out.println("Subject: " + message.getSubject());
			System.out.println("From: " + message.getFrom()[0]);

			emailMap.putIfAbsent("mailSubject", message.getSubject());
			emailMap.putIfAbsent("from", message.getFrom()[0].toString());

			// close the store and folder objects
			emailFolder.close(false);
			store.close();

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return emailMap;
	}

	public void setAttribute(WebElement element, String attName, String attValue) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attName, attValue);
	}

	public JSONObject jsonToHashMap(String jsonInString) {
		Map<String, Object> map = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();

		try {
			// convert JSON string to Map
			map = mapper.readValue(jsonInString, new TypeReference<HashMap<String, Object>>() {
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new JSONObject(map);
	}

	public void enterUsingJS(WebElement ele, String val) {
		JavascriptExecutor js = (JavascriptExecutor) util.getDriver();
		js.executeScript("arguments[0].value = arguments[1]", ele, val);
	}

	public void openOrdersInNewTab() {
		getDriver().switchTo().newWindow(WindowType.TAB);

		if (getConfigProperty(STORE).equalsIgnoreCase(STAGING)) {
			getDriver().get(getConfigProperty("stroreUrlStaging") + "orders/");
		} else if (getConfigProperty(STORE).equalsIgnoreCase(LIVE)) {
			getDriver().get(getConfigProperty("storeUrl") + "orders/");
		}
	}

	public void hideConversations() {
		WebElement element = getDriver().findElement(By.cssSelector("#siqiframe"));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
		jsExecutor.executeScript("arguments[0].setAttribute('style', 'display:none;');", element);

	}

}
