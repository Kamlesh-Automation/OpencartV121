package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_UserLoginTest extends BaseClass
{
	
	
	@Test(groups= {"Sanity","Master"})
	public void verify_user_login()
	{
		logger.info("******** Starting TC_UserLoginTest *********");
		
		try
		{
		//Home page
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login Page
		LoginPage lp = new LoginPage(driver);
		lp.setUserEmail(p.getProperty("email"));
		lp.setUserPassword(p.getProperty("password"));
		lp.loginButton();
		//MyAccount Page
		
		MyAccountPage myacc = new MyAccountPage(driver);
		boolean targetPage = myacc.isMyAccountPageExists();
		
		//Assert.assertEquals(targetPage, true, "Login failed");
		Assert.assertTrue(targetPage);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("******** Finished TC_UserLoginTest *********");
	}
}
