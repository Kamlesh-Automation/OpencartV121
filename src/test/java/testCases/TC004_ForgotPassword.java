package testCases;

import org.testng.annotations.Test;

import pageObjects.ForgotPassword;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC004_ForgotPassword extends BaseClass
{
	@Test(groups= {"Regression","Master"})
	public void reset_password()
	{
		//Homepage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login page
		LoginPage lp = new LoginPage(driver);
		lp.forgotLink();
		
		//forgot page
	
		ForgotPassword fp = new ForgotPassword(driver);
		fp.setemailaddress(p.getProperty("email"));
		fp.forgotcontinuebtn();
	}
	
}
