package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistraionPage;
import pageObjects.HomePage;
import testBase.BaseClass;


public class TC001_AccountRegistrationTest extends BaseClass 
{
	@Test(groups= {"Regression","Master"})
	public void verify_account_registration() throws InterruptedException 
	{
		logger.info("***** Starting TC001_AccountRegistrationTest ****");
		try
		{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Click on my Account Link..");
		hp.clickRegister();
		logger.info("Click on Register Link..");
		AccountRegistraionPage regpage = new AccountRegistraionPage(driver);
		logger.info("Providing Customer Details..");
		regpage.setFirstname(randomeString().toUpperCase());
		regpage.setLastname(randomeString().toUpperCase());
		regpage.setEmail(randomeString() + "@test.com");
		regpage.setTelephone(randomeNumber());
		// create local variable
		String password = randomeAlphaNumeric();
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		regpage.setPrivacyPolicy();
		regpage.clickContinue();

		logger.info("Validating Expected Message..");
		String confmsg = regpage.getConfirmationMsg();
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			logger.error("Test failed..");
			logger.debug("Debug logs..");
			Assert.fail();
		}
		logger.info("***** Finished TC001_AccountRegistrationTest ****");
	}

	

}
