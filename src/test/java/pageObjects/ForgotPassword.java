package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgotPassword extends BasePage
{
	public ForgotPassword(WebDriver driver)
	{
		super(driver);
	}
	
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement forgotemail;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement continueButton;
	
	
	public void setemailaddress(String forgotemailaddress)
	{
		forgotemail.sendKeys(forgotemailaddress);
	}
	public void forgotcontinuebtn()
	{
		continueButton.click();
	}
	
}

