package com.AMI.TestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ami.pageobjects.ShopifyPage;
import com.ami.resources.BaseClass;

public class LoginToAPP extends BaseClass{
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = util.getJsonDataToHashmap("ShopifyLoginData");
		return new Object [] [] {{data.get(0)}} ;
	}
	
//	@Test(dataProvider = "getData", priority = 1)
//	public void login(String store) {
//		ShopifyPage sp = new ShopifyPage(util);
//		sp.shopifyLogin();
//		sp.selectStore(store);
//	}
	
//	@DataProvider
//	public Object[][] getNewProductData() throws IOException {
//		List<HashMap<String, String>> data = util.getJsonDataToHashmap("NewProduct");
//		return new Object [] [] {{data.get(0)}} ;
//	}

//	@Test(dataProvider = "getNewProductData", priority = 1)
//	public void createProduct(HashMap<String, String> input) {
//		ShopifyPage sp = new ShopifyPage(util);
//		sp.goToCreateNewProductPage(input);
//	}
//
//	@Test(dataProvider = "getNewProductData", priority = 1)
//	public void deleteProduct(HashMap<String, String> input) {
//		ShopifyPage sp = new ShopifyPage(util);
//		sp.deleteShopifyProduct();
//	}

}
