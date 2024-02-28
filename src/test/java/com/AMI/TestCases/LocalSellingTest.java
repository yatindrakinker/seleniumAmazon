package com.AMI.TestCases;

import java.util.Map;

import org.testng.annotations.Test;

import com.ami.pageobjects.LocalSellingPage;
import com.ami.pageobjects.reusableclasses.ReusableMethods;
import com.ami.resources.BaseClass;

import api.CreateOrder;
import api.GetStoreFromDBAPI;
import apiBodyPayload.JsonBody;

public class LocalSellingTest extends BaseClass {

	private LocalSellingPage lsp;
	private GetStoreFromDBAPI getStoreAPI;
	private ReusableMethods reuse;
	private CreateOrder createOrder;
	private String shopifyOrderId;
	private String supplySrcId;

	@Test(priority = 1)
	public void testInitialize() {
		lsp = new LocalSellingPage(util);
		getStoreAPI = new GetStoreFromDBAPI();
		reuse = new ReusableMethods(util);
		createOrder = new CreateOrder();

	}

	@Test(priority = 2)
	public void testLocalSellingIsDisabledFromSetting() {
		lsp.localSellingIsDisabledFromSetting();
	}

	@Test(priority = 3)
	public void testLocalSellingIsEnabledFromSetting() {
		lsp.localSellingIsEnabledFromSetting();
	}

	@Test(priority = 4)
	public void testWhenManageBtnIsWorking() {
		lsp.manageBtnLocalSellingIsClickable();
	}

	@Test(priority = 5)
	public void testOrderCountBtnIsWorking() {
		lsp.orderCountLocalSellingIsClickable();
		lsp.validateOrderCount();
	}

	@Test(priority = 6)
	public void testStoresCountBtnIsWorking() {
		lsp.storesCountLocalSellingIsClickable();
		lsp.validateStoresCount();
	}

	@Test(priority = 7)
	public void testSearchInputFieldIsWorking() {
		lsp.searchInputFieldIsWorking();
	}

	@Test(priority = 8)
	public void testMoreFilterWorking() {
		lsp.selectStoresTab();
		lsp.moreFilterWorking();
		lsp.selectStoreStatus();
	}

	@Test(priority = 9)
	public void testClearBtnInMoreFiltersModalIsWorking() {
		lsp.clearBtnInMoreFiltersModalIsWorking();
	}

	@Test(priority = 10)
	public void testClearAllFiltersBtnInMoreFiltersModalIsWorking() {
		lsp.selectInactiveStatus();
		lsp.clickOnClearAllFiltersBtn();
	}

	@Test(priority = 11)
	public void testCreateNewStoreBtnIsWorking() {
		lsp.createNewStoreBtnIsWorking();
		lsp.backBtnIsWorking();
	}

	@Test(priority = 12, description = "test case 531 to 576")
	public void testValidateMandatoryFields() {
		lsp.validateStoreNameIsMandatoryField();
		lsp.validateStoreCodeIsMandatoryField();
		lsp.validateAddressLine1IsMandatoryField();
		lsp.validateCountryIsMandatoryField();
		lsp.validateStateIsMandatoryField();
		lsp.validateCityIsMandatoryField();
		lsp.validateUTCIsMandatoryField();
		lsp.validateZipCodeIsMandatoryField();
		lsp.validateContactNumberIsMandatoryField();
		lsp.validateEmailIsMandatoryField();

	}

	@Test(priority = 13, description = "test case 577 to 593")
	public void testOperationalDaysCanBeSelected() {
		lsp.operationalDaysCanBeSelected();
		lsp.openingAndClosingTimeCanNotBeEqual();
		lsp.openingTimeShouldBeLessThanClosingTime();
		lsp.handlingTimeDropdown();
		lsp.storeStatusDropdown();

	}

	@Test(priority = 14, description = "test case 708-711")
	public void testWhenUserQuitsWhileCreatingStore() {

		lsp.fillAllMandatoryFields();
		lsp.pressBackBtnWhileFillingFields();
	}

	@Test(priority = 15, description = "test case 712-714")
	public void testCreateStore() {
		lsp.createNewStoreBtnIsWorking();
		lsp.createNewStore();
		lsp.storeIsCreatedSuccessFully();
		lsp.validateStatusAndLocation();
	}

	@Test(priority = 16, description = "test case 715-723 and 727-729")
	public void testEditAndDeleteStoreBtnIsWorking() {

		lsp.editStoreBtnIsWorking();
		lsp.validateDisableFields();
		lsp.moreActionBtnIsWorking();
		lsp.onClickingDiscardBtn();
		lsp.onClickingDeleteBtn();
	}

	@Test(priority = 17, description = "test case 724, 729")
	public void testPaginationBtnsAreWorking() {
		lsp.paginationBtnsAreWorking();
	}

	@Test(priority = 18, description = "test case 735, 748")
	public void testTabRedirectionIsProper() {
		lsp.verifyTabRedirectionIsProper();
	}

	@Test(priority = 19, description = "test case 747, 749")
	public void testMoreFilterInOrdersTab() {
		lsp.clickOnOrdersTab();
		lsp.getOrderByStoreName();
	}

	@Test(priority = 20, description = "test case 750, 774")
	public void testArchievedOrderPageIsFunctioning() {
		lsp.openArchievedOrders();
	}

	@Test(priority = 21, description = "test case 775, 778")
	public void testImportBtnIsWorking() {
		lsp.importOrdersFunctionality();
	}

	@Test(priority = 22, description = "test case 784 to 787", groups = "api")
	public void testOrderForUnfulfilledStatusAndInactiveStore() {

		lsp.createNewStoreBtnIsWorking();
		lsp.createNewStore();
		lsp.storeIsCreatedSuccessFully();
		String apiResponse = getStoreAPI.hitGetStoreFromDBAPI();
		Map<String, String> storeInfo = reuse.getNewlyCreatedStoreFromDB(apiResponse);
		supplySrcId = storeInfo.get("supplySrcId");
		lsp.selectOrderStatus("unfulfilled");
		String orderResponse = createOrder.createOrdersForLocalSellingStore(supplySrcId);
		shopifyOrderId = reuse.getOrderId(orderResponse);
		lsp.validateOrderIsNotAvailableOnGrid();
	}

	@Test(priority = 23, description = "Create order for local selling active store. ", groups = {"api"})
	public void testOrderForUnfulfilledStatusAndActiveStore() {
		reuse.goToOverview();
		lsp.searchCreatedStore();
		lsp.changeStoreStatus();
		lsp.selectOrderStatus("unfulfilled");
		String orderResponse = createOrder.createOrdersForLocalSellingStore(supplySrcId);
		shopifyOrderId = reuse.getOrderId(orderResponse);
		lsp.validateOrderIsCreatedSuccessfully();
		util.logInfo("shopifyOrderId = " + shopifyOrderId);
		reuse.openOrderOnShopify(shopifyOrderId);
		reuse.validateOrderStatusOnShopify("Unfulfilled");
		JsonBody.amazonOrderId = "";
	}

	@Test(priority = 24, description = "create order for fulfilled status.", groups = {"api"})
	public void testOrderForFulfilledStatus() {
		lsp.selectOrderStatus("fulfilled");
		String orderResponse = createOrder.createOrdersForLocalSellingStore(supplySrcId);
		shopifyOrderId = reuse.getOrderId(orderResponse);
		lsp.validateOrderIsCreatedSuccessfully();
		util.logInfo("shopifyOrderId = " + shopifyOrderId);
		reuse.openOrderOnShopify(shopifyOrderId);
		reuse.validateOrderStatusOnShopify("fulfilled");
		JsonBody.amazonOrderId = "";
	}

	@Test(priority = 25, description = "Create local selling order when setting is disabled.", groups = {"api"})
	public void testWhenLocalSellingIsDisabledOrderCanNotBeCreatedOnAPP() {
		lsp.goToSettingsAndDisableLocalSelling();
		String orderResponse = createOrder.createOrdersForLocalSellingStore(supplySrcId);
		reuse.validateLocalSellingOrderIsNotCreated(orderResponse);
		JsonBody.amazonOrderId = "";
	}

}
