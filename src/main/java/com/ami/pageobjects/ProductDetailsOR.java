package com.ami.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ami.pageobjects.reusableclasses.ReusableMethodsOR;

public class ProductDetailsOR extends ReusableMethodsOR{
	@FindBy(css = "button[aria-label='Manage']")
	protected WebElement manageBtn;

	@FindBy(xpath = "//span[text() = 'Manage sales channels']")
	protected WebElement manageSalesChannelAndAppBtn;

	@FindBy(xpath = "//div[@role='dialog']//span[text() = 'Deselect all']")
	protected WebElement deSelectAllBtn;
	
	@FindBy(xpath = "//div[@role= 'dialog']//span[text() = 'Done']")
	protected WebElement doneBtnModal;
}
