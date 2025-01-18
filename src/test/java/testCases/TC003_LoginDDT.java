package testCases;
/*
 Data is Valid  - login success - test pass - logout
 data is Valid -- login failed -test fail
 
  Data is Invalid -- login success - test fail - logout
  Data is Invalid -- login failed - test pass
  
 * */

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class) // getting data provider from different class																		
	public void verify_loginDDT(String email, String pwd, String exp) {

		logger.info("******** Starting TC_003_LoginDDT *********");
		try {
			// Home page
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			// Login Page
			LoginPage lp = new LoginPage(driver);
			lp.setUserEmail(email);
			lp.setUserPassword(pwd);
			lp.loginButton();

			// MyAccount Page
			MyAccountPage myacc = new MyAccountPage(driver);
			boolean targetPage = myacc.isMyAccountPageExists();

			if (exp.equalsIgnoreCase("Valid")) {
				if (targetPage == true) {
					Assert.assertTrue(true);
					myacc.clickLogout();
				} else {
					Assert.assertTrue(false);
				}
			}
			if (exp.equalsIgnoreCase("Invalid")) {
				if (targetPage == true) {
					myacc.clickLogout(); // always put assert after action
					Assert.assertTrue(false);
				} else {
					assertTrue(true);
				}
			}
		} 
		catch (Exception e)
		{
			Assert.fail();
		}
		logger.info("******** Finished TC_003_LoginDDT *********");
	}
}
